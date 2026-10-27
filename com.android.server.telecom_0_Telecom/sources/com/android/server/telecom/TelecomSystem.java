package com.android.server.telecom;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.UserHandle;
import android.os.UserManager;
import android.telecom.Log;
import android.telecom.PhoneAccountHandle;
import android.widget.Toast;
import com.android.internal.annotations.VisibleForTesting;
import com.android.server.telecom.AppLabelProxy;
import com.android.server.telecom.BluetoothPhoneServiceImpl;
import com.android.server.telecom.CallAudioManager;
import com.android.server.telecom.CallAudioModeStateMachine;
import com.android.server.telecom.CallAudioRouteStateMachine;
import com.android.server.telecom.CallIntentProcessor;
import com.android.server.telecom.ConnectionServiceFocusManager;
import com.android.server.telecom.ContactsAsyncHelper;
import com.android.server.telecom.DefaultDialerCache;
import com.android.server.telecom.InCallTonePlayer;
import com.android.server.telecom.TelecomServiceImpl;
import com.android.server.telecom.Timeouts;
import com.android.server.telecom.bluetooth.BluetoothDeviceManager;
import com.android.server.telecom.bluetooth.BluetoothRouteManager;
import com.android.server.telecom.bluetooth.BluetoothStateReceiver;
import com.android.server.telecom.callfiltering.IncomingCallFilter$Factory;
import com.android.server.telecom.components.UserCallIntentProcessor;
import com.android.server.telecom.components.UserCallIntentProcessorFactory;
import com.android.server.telecom.ui.AudioProcessingNotification;
import com.android.server.telecom.ui.DisconnectedCallNotifier;
import com.android.server.telecom.ui.IncomingCallNotifier;
import com.android.server.telecom.ui.MissedCallNotifierImpl;
import com.android.server.telecom.ui.ToastFactory;
import java.io.FileNotFoundException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class TelecomSystem {
    private static final IntentFilter DIALER_SECRET_CODE_FILTER;
    private static TelecomSystem INSTANCE;
    private final BluetoothPhoneServiceImpl mBluetoothPhoneServiceImpl;
    private final CallIntentProcessor mCallIntentProcessor;
    private final CallsManager mCallsManager;
    private final ContactsAsyncHelper mContactsAsyncHelper;
    private final Context mContext;
    private final DialerCodeReceiver mDialerCodeReceiver;
    private final IncomingCallNotifier mIncomingCallNotifier;
    private final MissedCallNotifier mMissedCallNotifier;
    private final PhoneAccountRegistrar mPhoneAccountRegistrar;
    private final RespondViaSmsManager mRespondViaSmsManager;
    private final TelecomBroadcastIntentProcessor mTelecomBroadcastIntentProcessor;
    private final TelecomServiceImpl mTelecomServiceImpl;
    private static final IntentFilter USER_SWITCHED_FILTER = new IntentFilter("android.intent.action.USER_SWITCHED");
    private static final IntentFilter USER_STARTING_FILTER = new IntentFilter("android.intent.action.USER_STARTING");
    private static final IntentFilter BOOT_COMPLETE_FILTER = new IntentFilter("android.intent.action.BOOT_COMPLETED");
    private final SyncRoot mLock = new SyncRoot(this) { // from class: com.android.server.telecom.TelecomSystem.1
    };
    private boolean mIsBootComplete = false;
    private final BroadcastReceiver mUserSwitchedReceiver = new BroadcastReceiver() { // from class: com.android.server.telecom.TelecomSystem.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.startSession("TSSwR.oR");
            try {
                synchronized (TelecomSystem.this.mLock) {
                    UserHandle userHandle = new UserHandle(intent.getIntExtra("android.intent.extra.user_handle", 0));
                    TelecomSystem.this.mPhoneAccountRegistrar.setCurrentUserHandle(userHandle);
                    TelecomSystem.this.mCallsManager.onUserSwitch(userHandle);
                }
            } finally {
                Log.endSession();
            }
        }
    };
    private final BroadcastReceiver mUserStartingReceiver = new BroadcastReceiver() { // from class: com.android.server.telecom.TelecomSystem.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.startSession("TSStR.oR");
            try {
                synchronized (TelecomSystem.this.mLock) {
                    TelecomSystem.this.mCallsManager.onUserStarting(new UserHandle(intent.getIntExtra("android.intent.extra.user_handle", 0)));
                }
            } finally {
                Log.endSession();
            }
        }
    };
    private final BroadcastReceiver mBootCompletedReceiver = new BroadcastReceiver() { // from class: com.android.server.telecom.TelecomSystem.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.startSession("TSBCR.oR");
            try {
                synchronized (TelecomSystem.this.mLock) {
                    TelecomSystem.this.mIsBootComplete = true;
                    TelecomSystem.this.mCallsManager.onBootCompleted();
                }
            } finally {
                Log.endSession();
            }
        }
    };

    public interface SyncRoot {
    }

    static {
        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SECRET_CODE");
        DIALER_SECRET_CODE_FILTER = intentFilter;
        intentFilter.addDataScheme("android_secret_code");
        DIALER_SECRET_CODE_FILTER.addDataAuthority("823241", null);
        DIALER_SECRET_CODE_FILTER.addDataAuthority("823240", null);
        DIALER_SECRET_CODE_FILTER.addDataAuthority("826275", null);
        DIALER_SECRET_CODE_FILTER.addDataAuthority("828282", null);
        INSTANCE = null;
    }

    public static TelecomSystem getInstance() {
        return INSTANCE;
    }

    public static void setInstance(TelecomSystem telecomSystem) {
        if (INSTANCE != null) {
            Log.w("TelecomSystem", "Attempt to set TelecomSystem.INSTANCE twice", new Object[0]);
        }
        Log.i(TelecomSystem.class, "TelecomSystem.INSTANCE being set", new Object[0]);
        INSTANCE = telecomSystem;
    }

    public TelecomSystem(Context context, MissedCallNotifierImpl.MissedCallNotifierImplFactory missedCallNotifierImplFactory, CallerInfoAsyncQueryFactory callerInfoAsyncQueryFactory, HeadsetMediaButtonFactory headsetMediaButtonFactory, ProximitySensorManagerFactory proximitySensorManagerFactory, InCallWakeLockControllerFactory inCallWakeLockControllerFactory, CallAudioManager.AudioServiceFactory audioServiceFactory, BluetoothPhoneServiceImpl.BluetoothPhoneServiceImplFactory bluetoothPhoneServiceImplFactory, ConnectionServiceFocusManager.ConnectionServiceFocusManagerFactory connectionServiceFocusManagerFactory, Timeouts.Adapter adapter, AsyncRingtonePlayer asyncRingtonePlayer, PhoneNumberUtilsAdapter phoneNumberUtilsAdapter, IncomingCallNotifier incomingCallNotifier, InCallTonePlayer.ToneGeneratorFactory toneGeneratorFactory, CallAudioRouteStateMachine.Factory factory, CallAudioModeStateMachine.Factory factory2, final ClockProxy clockProxy, RoleManagerAdapter roleManagerAdapter, IncomingCallFilter$Factory incomingCallFilter$Factory, ContactsAsyncHelper.Factory factory3, DeviceIdleControllerAdapter deviceIdleControllerAdapter) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        LogUtils.initLogging(applicationContext);
        DefaultDialerCache defaultDialerCache = new DefaultDialerCache(this.mContext, new DefaultDialerCache.DefaultDialerManagerAdapterImpl(), roleManagerAdapter, this.mLock);
        Log.startSession("TS.init");
        this.mPhoneAccountRegistrar = new PhoneAccountRegistrar(this.mContext, defaultDialerCache, new AppLabelProxy() { // from class: com.android.server.telecom.-$$Lambda$TelecomSystem$bNQ5LPj1Dc2ONBXYit32bO7fOkk
            @Override // com.android.server.telecom.AppLabelProxy
            public final CharSequence getAppLabel(String str) {
                return this.f$0.lambda$new$0$TelecomSystem(str);
            }
        });
        this.mContactsAsyncHelper = factory3.create(new ContactsAsyncHelper.ContentResolverAdapter(this) { // from class: com.android.server.telecom.TelecomSystem.5
            @Override // com.android.server.telecom.ContactsAsyncHelper.ContentResolverAdapter
            public InputStream openInputStream(Context context2, Uri uri) throws FileNotFoundException {
                return context2.getContentResolver().openInputStream(uri);
            }
        });
        BluetoothDeviceManager bluetoothDeviceManager = new BluetoothDeviceManager(this.mContext, new BluetoothAdapterProxy());
        BluetoothRouteManager bluetoothRouteManager = new BluetoothRouteManager(this.mContext, this.mLock, bluetoothDeviceManager, new Timeouts.Adapter());
        BluetoothStateReceiver bluetoothStateReceiver = new BluetoothStateReceiver(bluetoothDeviceManager, bluetoothRouteManager);
        this.mContext.registerReceiver(bluetoothStateReceiver, BluetoothStateReceiver.INTENT_FILTER);
        WiredHeadsetManager wiredHeadsetManager = new WiredHeadsetManager(this.mContext);
        SystemStateHelper systemStateHelper = new SystemStateHelper(this.mContext);
        this.mMissedCallNotifier = missedCallNotifierImplFactory.makeMissedCallNotifierImpl(this.mContext, this.mPhoneAccountRegistrar, defaultDialerCache, deviceIdleControllerAdapter);
        DisconnectedCallNotifier.Default r2 = new DisconnectedCallNotifier.Default();
        CallerInfoLookupHelper callerInfoLookupHelper = new CallerInfoLookupHelper(context, callerInfoAsyncQueryFactory, this.mContactsAsyncHelper, this.mLock);
        EmergencyCallHelper emergencyCallHelper = new EmergencyCallHelper(this.mContext, defaultDialerCache, adapter);
        InCallControllerFactory inCallControllerFactory = new InCallControllerFactory(this) { // from class: com.android.server.telecom.TelecomSystem.6
            @Override // com.android.server.telecom.InCallControllerFactory
            public InCallController create(Context context2, SyncRoot syncRoot, CallsManager callsManager, SystemStateHelper systemStateHelper2, DefaultDialerCache defaultDialerCache2, Timeouts.Adapter adapter2, EmergencyCallHelper emergencyCallHelper2) {
                return new InCallController(context2, syncRoot, callsManager, systemStateHelper2, defaultDialerCache2, adapter2, emergencyCallHelper2, new CarModeTracker(), clockProxy);
            }
        };
        this.mCallsManager = new CallsManager(this.mContext, this.mLock, callerInfoLookupHelper, this.mMissedCallNotifier, r2, this.mPhoneAccountRegistrar, headsetMediaButtonFactory, proximitySensorManagerFactory, inCallWakeLockControllerFactory, connectionServiceFocusManagerFactory, audioServiceFactory, bluetoothRouteManager, wiredHeadsetManager, systemStateHelper, defaultDialerCache, adapter, asyncRingtonePlayer, phoneNumberUtilsAdapter, emergencyCallHelper, toneGeneratorFactory, clockProxy, new AudioProcessingNotification(this.mContext), bluetoothStateReceiver, factory, factory2, inCallControllerFactory, roleManagerAdapter, incomingCallFilter$Factory, new ToastFactory(this) { // from class: com.android.server.telecom.TelecomSystem.7
            @Override // com.android.server.telecom.ui.ToastFactory
            public Toast makeText(Context context2, int i, int i2) {
                return Toast.makeText(context2, context2.getMainLooper(), context2.getString(i), i2);
            }

            @Override // com.android.server.telecom.ui.ToastFactory
            public Toast makeText(Context context2, CharSequence charSequence, int i) {
                return Toast.makeText(context2, context2.getMainLooper(), charSequence, i);
            }
        });
        this.mIncomingCallNotifier = incomingCallNotifier;
        incomingCallNotifier.setCallsManagerProxy(new IncomingCallNotifier.CallsManagerProxy() { // from class: com.android.server.telecom.TelecomSystem.8
            @Override // com.android.server.telecom.ui.IncomingCallNotifier.CallsManagerProxy
            public boolean hasUnholdableCallsForOtherConnectionService(PhoneAccountHandle phoneAccountHandle) {
                return TelecomSystem.this.mCallsManager.hasUnholdableCallsForOtherConnectionService(phoneAccountHandle);
            }

            @Override // com.android.server.telecom.ui.IncomingCallNotifier.CallsManagerProxy
            public int getNumUnholdableCallsForOtherConnectionService(PhoneAccountHandle phoneAccountHandle) {
                return TelecomSystem.this.mCallsManager.getNumUnholdableCallsForOtherConnectionService(phoneAccountHandle);
            }

            @Override // com.android.server.telecom.ui.IncomingCallNotifier.CallsManagerProxy
            public Call getActiveCall() {
                return TelecomSystem.this.mCallsManager.getActiveCall();
            }
        });
        this.mCallsManager.setIncomingCallNotifier(this.mIncomingCallNotifier);
        RespondViaSmsManager respondViaSmsManager = new RespondViaSmsManager(this.mCallsManager, this.mLock);
        this.mRespondViaSmsManager = respondViaSmsManager;
        this.mCallsManager.setRespondViaSmsManager(respondViaSmsManager);
        this.mContext.registerReceiverAsUser(this.mUserSwitchedReceiver, UserHandle.ALL, USER_SWITCHED_FILTER, null, null);
        this.mContext.registerReceiverAsUser(this.mUserStartingReceiver, UserHandle.ALL, USER_STARTING_FILTER, null, null);
        this.mContext.registerReceiverAsUser(this.mBootCompletedReceiver, UserHandle.ALL, BOOT_COMPLETE_FILTER, null, null);
        synchronized (this.mLock) {
            UserHandle userHandleOf = UserHandle.of(ActivityManager.getCurrentUser());
            this.mPhoneAccountRegistrar.setCurrentUserHandle(userHandleOf);
            this.mCallsManager.onUserSwitch(userHandleOf);
        }
        this.mBluetoothPhoneServiceImpl = bluetoothPhoneServiceImplFactory.makeBluetoothPhoneServiceImpl(this.mContext, this.mLock, this.mCallsManager, this.mPhoneAccountRegistrar);
        this.mCallIntentProcessor = new CallIntentProcessor(this.mContext, this.mCallsManager, defaultDialerCache);
        this.mTelecomBroadcastIntentProcessor = new TelecomBroadcastIntentProcessor(this.mContext, this.mCallsManager);
        DialerCodeReceiver dialerCodeReceiver = new DialerCodeReceiver(this.mCallsManager);
        this.mDialerCodeReceiver = dialerCodeReceiver;
        this.mContext.registerReceiver(dialerCodeReceiver, DIALER_SECRET_CODE_FILTER, "android.permission.CONTROL_INCALL_EXPERIENCE", null);
        UserManager.get(this.mContext);
        this.mTelecomServiceImpl = new TelecomServiceImpl(this.mContext, this.mCallsManager, this.mPhoneAccountRegistrar, new CallIntentProcessor.AdapterImpl(defaultDialerCache), new UserCallIntentProcessorFactory(this) { // from class: com.android.server.telecom.TelecomSystem.9
            @Override // com.android.server.telecom.components.UserCallIntentProcessorFactory
            public UserCallIntentProcessor create(Context context2, UserHandle userHandle) {
                return new UserCallIntentProcessor(context2, userHandle);
            }
        }, defaultDialerCache, new TelecomServiceImpl.SubscriptionManagerAdapterImpl(), new TelecomServiceImpl.SettingsSecureAdapterImpl(), this.mLock);
        Log.endSession();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ CharSequence lambda$new$0$TelecomSystem(String str) {
        return AppLabelProxy.Util.getAppLabel(this.mContext.getPackageManager(), str);
    }

    @VisibleForTesting
    public PhoneAccountRegistrar getPhoneAccountRegistrar() {
        return this.mPhoneAccountRegistrar;
    }

    @VisibleForTesting
    public CallsManager getCallsManager() {
        return this.mCallsManager;
    }

    public BluetoothPhoneServiceImpl getBluetoothPhoneServiceImpl() {
        return this.mBluetoothPhoneServiceImpl;
    }

    public CallIntentProcessor getCallIntentProcessor() {
        return this.mCallIntentProcessor;
    }

    public TelecomBroadcastIntentProcessor getTelecomBroadcastIntentProcessor() {
        return this.mTelecomBroadcastIntentProcessor;
    }

    public TelecomServiceImpl getTelecomServiceImpl() {
        return this.mTelecomServiceImpl;
    }

    public Object getLock() {
        return this.mLock;
    }

    public boolean isBootComplete() {
        return this.mIsBootComplete;
    }
}

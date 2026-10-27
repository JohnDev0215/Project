package com.android.server.telecom;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.UserInfo;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.AudioSystem;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.PersistableBundle;
import android.os.Process;
import android.os.SystemClock;
import android.os.SystemVibrator;
import android.os.Trace;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.BlockedNumberContract;
import android.provider.Settings;
import android.sysprop.TelephonyProperties;
import android.telecom.CallAudioState;
import android.telecom.CallerInfo;
import android.telecom.DisconnectCause;
import android.telecom.GatewayInfo;
import android.telecom.Log;
import android.telecom.Logging.Runnable;
import android.telecom.Logging.Session;
import android.telecom.ParcelableConference;
import android.telecom.ParcelableConnection;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.PhoneAccountSuggestion;
import android.telecom.TelecomManager;
import android.telecom.VideoProfile;
import android.telephony.CarrierConfigManager;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.util.IndentingPrintWriter;
import com.android.server.telecom.AppLabelProxy;
import com.android.server.telecom.Call;
import com.android.server.telecom.CallAudioManager;
import com.android.server.telecom.CallAudioModeStateMachine;
import com.android.server.telecom.CallAudioRouteStateMachine;
import com.android.server.telecom.CallerInfoLookupHelper;
import com.android.server.telecom.ConnectionServiceFocusManager;
import com.android.server.telecom.DtmfLocalTonePlayer;
import com.android.server.telecom.InCallTonePlayer;
import com.android.server.telecom.MissedCallNotifier;
import com.android.server.telecom.ParcelableCallUtils;
import com.android.server.telecom.PhoneAccountRegistrar;
import com.android.server.telecom.Ringer;
import com.android.server.telecom.TelecomSystem;
import com.android.server.telecom.Timeouts;
import com.android.server.telecom.VideoProviderProxy;
import com.android.server.telecom.bluetooth.BluetoothRouteManager;
import com.android.server.telecom.bluetooth.BluetoothStateReceiver;
import com.android.server.telecom.callfiltering.BlockCheckerAdapter;
import com.android.server.telecom.callfiltering.BlockCheckerFilter;
import com.android.server.telecom.callfiltering.CallFilterResultCallback;
import com.android.server.telecom.callfiltering.CallFilteringResult;
import com.android.server.telecom.callfiltering.CallScreeningServiceFilter;
import com.android.server.telecom.callfiltering.DirectToVoicemailFilter;
import com.android.server.telecom.callfiltering.IncomingCallFilter$Factory;
import com.android.server.telecom.callfiltering.IncomingCallFilterGraph;
import com.android.server.telecom.components.ErrorDialogActivity;
import com.android.server.telecom.components.TelecomBroadcastReceiver;
import com.android.server.telecom.settings.BlockedNumbersUtil;
import com.android.server.telecom.ui.AudioProcessingNotification;
import com.android.server.telecom.ui.ConfirmCallDialogActivity;
import com.android.server.telecom.ui.DisconnectedCallNotifier;
import com.android.server.telecom.ui.IncomingCallNotifier;
import com.android.server.telecom.ui.ToastFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
public class CallsManager extends Call.ListenerBase implements VideoProviderProxy.Listener, CallFilterResultCallback, CurrentUserProxy {
    private static final Map<Integer, Integer> sAnalyticsTechnologyMap;
    private final BluetoothRouteManager mBluetoothRouteManager;
    private final CallAudioManager mCallAudioManager;
    private final CallLogManager mCallLogManager;
    private final CallRecordingTonePlayer mCallRecordingTonePlayer;
    private final CallerInfoLookupHelper mCallerInfoLookupHelper;
    private final ClockProxy mClockProxy;
    private final ConnectionServiceRepository mConnectionServiceRepository;
    private final ConnectionServiceFocusManager mConnectionSvrFocusMgr;
    private final Context mContext;
    private final DefaultDialerCache mDefaultDialerCache;
    private final DisconnectedCallNotifier mDisconnectedCallNotifier;
    private final DockManager mDockManager;
    private final DtmfLocalTonePlayer mDtmfLocalTonePlayer;
    private final EmergencyCallHelper mEmergencyCallHelper;
    private LinkedList<HandlerThread> mGraphHandlerThreads;
    private final HeadsetMediaButton mHeadsetMediaButton;
    private final InCallController mInCallController;
    private final InCallWakeLockController mInCallWakeLockController;
    private IncomingCallNotifier mIncomingCallNotifier;
    private CompletableFuture<Call> mLatestPostSelectionProcessingFuture;
    private CompletableFuture<Pair<Call, List<PhoneAccountSuggestion>>> mLatestPreAccountSelectionFuture;
    private final TelecomSystem.SyncRoot mLock;
    private final MissedCallNotifier mMissedCallNotifier;
    private CompletableFuture<Pair<Call, PhoneAccountHandle>> mPendingAccountSelection;
    private Call mPendingAudioProcessingCall;
    private Call mPendingCall;
    private CompletableFuture<Call> mPendingCallConfirm;
    private Call mPendingRedirectedOutgoingCall;
    private final PhoneAccountRegistrar mPhoneAccountRegistrar;
    private final PhoneNumberUtilsAdapter mPhoneNumberUtilsAdapter;
    private final PhoneStateBroadcaster mPhoneStateBroadcaster;
    private final ProximitySensorManager mProximitySensorManager;
    private RespondViaSmsManager mRespondViaSmsManager;
    private final Ringer mRinger;
    private final RoleManagerAdapter mRoleManagerAdapter;
    private Runnable mStopTone;
    private final SystemStateHelper mSystemStateHelper;
    private final Timeouts.Adapter mTimeoutsAdapter;
    private final ToastFactory mToastFactory;
    private final TtyManager mTtyManager;
    private final WiredHeadsetManager mWiredHeadsetManager;
    private static final int[] OUTGOING_CALL_STATES = {1, 2, 3, 10};
    private static final int[] LIVE_CALL_STATES = {1, 2, 3, 10, 5, 12};
    public static final int[] ONGOING_CALL_STATES = {2, 3, 10, 5, 6, 4, 13, 11, 12};
    private static final int[] ANY_CALL_STATE = {0, 1, 2, 3, 4, 13, 5, 6, 7, 8, 9, 10, 11, 12};
    private final Set<Call> mCalls = Collections.newSetFromMap(new ConcurrentHashMap(8, 0.9f, 1));
    private final Map<String, Runnable> mPendingRedirectedOutgoingCallInfo = new ConcurrentHashMap();
    private final Map<String, Runnable> mPendingUnredirectedOutgoingCallInfo = new ConcurrentHashMap();
    private int mCallId = 0;
    private int mRttRequestId = 0;
    private UserHandle mCurrentUserHandle = UserHandle.of(ActivityManager.getCurrentUser());
    private final Set<CallsManagerListener> mListeners = Collections.newSetFromMap(new ConcurrentHashMap(16, 0.9f, 1));
    private final Set<Call> mLocallyDisconnectingCalls = new HashSet();
    private final Set<Call> mPendingCallsToDisconnect = new HashSet();
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final ConnectionServiceFocusManager.CallsManagerRequester mRequester = new AnonymousClass1();
    private boolean mCanAddCall = true;
    private int mMaxNumberOfSimultaneouslyActiveSims = -1;
    private boolean mHasActiveRttCall = false;
    private PhoneAccountRegistrar.Listener mPhoneAccountListener = new PhoneAccountRegistrar.Listener() { // from class: com.android.server.telecom.CallsManager.2
        @Override // com.android.server.telecom.PhoneAccountRegistrar.Listener
        public void onPhoneAccountRegistered(PhoneAccountRegistrar phoneAccountRegistrar, PhoneAccountHandle phoneAccountHandle) {
            CallsManager.this.broadcastRegisterIntent(phoneAccountHandle);
        }

        @Override // com.android.server.telecom.PhoneAccountRegistrar.Listener
        public void onPhoneAccountUnRegistered(PhoneAccountRegistrar phoneAccountRegistrar, PhoneAccountHandle phoneAccountHandle) {
            CallsManager.this.broadcastUnregisterIntent(phoneAccountHandle);
        }

        @Override // com.android.server.telecom.PhoneAccountRegistrar.Listener
        public void onPhoneAccountChanged(PhoneAccountRegistrar phoneAccountRegistrar, PhoneAccount phoneAccount) {
            CallsManager.this.handlePhoneAccountChanged(phoneAccountRegistrar, phoneAccount);
        }
    };
    private final BroadcastReceiver mReceiver = new BroadcastReceiver(this) { // from class: com.android.server.telecom.CallsManager.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.startSession("CM.CCCR");
            String action = intent.getAction();
            if ("android.telephony.action.CARRIER_CONFIG_CHANGED".equals(action) || "android.provider.action.BLOCK_SUPPRESSION_STATE_CHANGED".equals(action)) {
                new UpdateEmergencyCallNotificationTask(null).doInBackground(Pair.create(context, Log.createSubsession()));
            }
        }
    };

    @VisibleForTesting
    public interface CallsManagerListener {
        void onCallAdded(Call call);

        void onCallAudioStateChanged(CallAudioState callAudioState, CallAudioState callAudioState2);

        void onCallRemoved(Call call);

        void onCallStateChanged(Call call, int i, int i2);

        void onCanAddCallChanged(boolean z);

        void onCdmaConferenceSwap(Call call);

        void onConferenceStateChanged(Call call, boolean z);

        void onConnectionServiceChanged(Call call, ConnectionServiceWrapper connectionServiceWrapper, ConnectionServiceWrapper connectionServiceWrapper2);

        void onConnectionTimeChanged(Call call);

        void onDisconnectedTonePlaying(boolean z);

        void onExternalCallChanged(Call call, boolean z);

        void onHoldToneRequested(Call call);

        void onIncomingCallAnswered(Call call);

        void onIncomingCallRejected(Call call, boolean z, String str);

        void onIsConferencedChanged(Call call);

        void onIsVoipAudioModeChanged(Call call);

        void onRingbackRequested(Call call, boolean z);

        void onSessionModifyRequestReceived(Call call, VideoProfile videoProfile);

        void onSetCamera(Call call, String str);

        void onVideoStateChanged(Call call, int i, int i2);
    }

    interface PendingAction {
        void performAction();
    }

    static /* synthetic */ boolean lambda$getNumCallsWithState$23(Call call, Call call2) {
        return call2 != call;
    }

    static {
        HashMap map = new HashMap(5);
        sAnalyticsTechnologyMap = map;
        map.put(2, 1);
        sAnalyticsTechnologyMap.put(1, 2);
        sAnalyticsTechnologyMap.put(5, 4);
        sAnalyticsTechnologyMap.put(3, 8);
        sAnalyticsTechnologyMap.put(4, 16);
    }

    /* JADX INFO: renamed from: com.android.server.telecom.CallsManager$1, reason: invalid class name */
    class AnonymousClass1 implements ConnectionServiceFocusManager.CallsManagerRequester {
        AnonymousClass1() {
        }

        @Override // com.android.server.telecom.ConnectionServiceFocusManager.CallsManagerRequester
        public void releaseConnectionService(final ConnectionServiceFocusManager.ConnectionServiceFocus connectionServiceFocus) {
            CallsManager.this.mCalls.stream().filter(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$1$BasC5jbBRQE1s-HZ1QUxXZtJYCY
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((Call) obj).getConnectionServiceWrapper().equals(connectionServiceFocus);
                }
            }).forEach(new Consumer() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$1$cwnexVsCXMLX3KScxYjUJRf1-xY
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((Call) obj).disconnect("release " + connectionServiceFocus.getComponentName().getPackageName());
                }
            });
        }

        @Override // com.android.server.telecom.ConnectionServiceFocusManager.CallsManagerRequester
        public void setCallsManagerListener(CallsManagerListener callsManagerListener) {
            CallsManager.this.mListeners.add(callsManagerListener);
        }
    }

    private static class UpdateEmergencyCallNotificationTask extends AsyncTask<Pair<Context, Session>, Void, Void> {
        private UpdateEmergencyCallNotificationTask() {
        }

        /* synthetic */ UpdateEmergencyCallNotificationTask(AnonymousClass1 anonymousClass1) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        @SafeVarargs
        public final Void doInBackground(Pair<Context, Session>... pairArr) {
            if (pairArr == null || pairArr.length != 1 || pairArr[0] == null) {
                Log.e(this, new IllegalArgumentException(), "Incorrect invocation", new Object[0]);
                return null;
            }
            Log.continueSession((Session) pairArr[0].second, "CM.UECNT");
            Context context = (Context) pairArr[0].first;
            BlockedNumbersUtil.updateEmergencyCallNotification(context, BlockedNumberContract.SystemContract.shouldShowEmergencyCallNotification(context));
            Log.endSession();
            return null;
        }
    }

    @VisibleForTesting
    public CallsManager(Context context, TelecomSystem.SyncRoot syncRoot, CallerInfoLookupHelper callerInfoLookupHelper, MissedCallNotifier missedCallNotifier, DisconnectedCallNotifier.Factory factory, PhoneAccountRegistrar phoneAccountRegistrar, HeadsetMediaButtonFactory headsetMediaButtonFactory, ProximitySensorManagerFactory proximitySensorManagerFactory, InCallWakeLockControllerFactory inCallWakeLockControllerFactory, ConnectionServiceFocusManager.ConnectionServiceFocusManagerFactory connectionServiceFocusManagerFactory, CallAudioManager.AudioServiceFactory audioServiceFactory, BluetoothRouteManager bluetoothRouteManager, WiredHeadsetManager wiredHeadsetManager, SystemStateHelper systemStateHelper, DefaultDialerCache defaultDialerCache, Timeouts.Adapter adapter, AsyncRingtonePlayer asyncRingtonePlayer, PhoneNumberUtilsAdapter phoneNumberUtilsAdapter, EmergencyCallHelper emergencyCallHelper, InCallTonePlayer.ToneGeneratorFactory toneGeneratorFactory, ClockProxy clockProxy, AudioProcessingNotification audioProcessingNotification, BluetoothStateReceiver bluetoothStateReceiver, CallAudioRouteStateMachine.Factory factory2, CallAudioModeStateMachine.Factory factory3, InCallControllerFactory inCallControllerFactory, RoleManagerAdapter roleManagerAdapter, IncomingCallFilter$Factory incomingCallFilter$Factory, ToastFactory toastFactory) {
        this.mContext = context;
        this.mLock = syncRoot;
        this.mPhoneNumberUtilsAdapter = phoneNumberUtilsAdapter;
        this.mPhoneAccountRegistrar = phoneAccountRegistrar;
        phoneAccountRegistrar.addListener(this.mPhoneAccountListener);
        this.mMissedCallNotifier = missedCallNotifier;
        this.mDisconnectedCallNotifier = factory.create(this.mContext, this);
        StatusBarNotifier statusBarNotifier = new StatusBarNotifier(context, this);
        this.mWiredHeadsetManager = wiredHeadsetManager;
        this.mSystemStateHelper = systemStateHelper;
        this.mDefaultDialerCache = defaultDialerCache;
        this.mBluetoothRouteManager = bluetoothRouteManager;
        this.mDockManager = new DockManager(context);
        this.mTimeoutsAdapter = adapter;
        this.mEmergencyCallHelper = emergencyCallHelper;
        this.mCallerInfoLookupHelper = callerInfoLookupHelper;
        this.mDtmfLocalTonePlayer = new DtmfLocalTonePlayer(new DtmfLocalTonePlayer.ToneGeneratorProxy());
        CallAudioRouteStateMachine callAudioRouteStateMachineCreate = factory2.create(context, this, bluetoothRouteManager, wiredHeadsetManager, statusBarNotifier, audioServiceFactory, 2);
        callAudioRouteStateMachineCreate.initialize();
        CallAudioRoutePeripheralAdapter callAudioRoutePeripheralAdapter = new CallAudioRoutePeripheralAdapter(callAudioRouteStateMachineCreate, bluetoothRouteManager, wiredHeadsetManager, this.mDockManager);
        final AudioManager audioManager = (AudioManager) this.mContext.getSystemService("audio");
        InCallTonePlayer.Factory factory4 = new InCallTonePlayer.Factory(callAudioRoutePeripheralAdapter, syncRoot, toneGeneratorFactory, new InCallTonePlayer.MediaPlayerFactory() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$65JHytcdOHVx9cE1VoGm_RmOl2I
            @Override // com.android.server.telecom.InCallTonePlayer.MediaPlayerFactory
            public final InCallTonePlayer.MediaPlayerAdapter get(int i, AudioAttributes audioAttributes) {
                return this.f$0.lambda$new$0$CallsManager(audioManager, i, audioAttributes);
            }
        }, new InCallTonePlayer.AudioManagerAdapter() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$bnfXs-9TMNZiQ9lE3hfkHY3bB6k
            @Override // com.android.server.telecom.InCallTonePlayer.AudioManagerAdapter
            public final boolean isVolumeOverZero() {
                return CallsManager.lambda$new$1(audioManager);
            }
        });
        SystemSettingsUtil systemSettingsUtil = new SystemSettingsUtil();
        RingtoneFactory ringtoneFactory = new RingtoneFactory(this, context);
        SystemVibrator systemVibrator = new SystemVibrator(context);
        this.mInCallController = inCallControllerFactory.create(context, this.mLock, this, systemStateHelper, defaultDialerCache, this.mTimeoutsAdapter, emergencyCallHelper);
        this.mRinger = new Ringer(factory4, context, systemSettingsUtil, asyncRingtonePlayer, ringtoneFactory, systemVibrator, new Ringer.VibrationEffectProxy(), this.mInCallController);
        this.mCallRecordingTonePlayer = new CallRecordingTonePlayer(this.mContext, audioManager, this.mTimeoutsAdapter, this.mLock);
        this.mCallAudioManager = new CallAudioManager(callAudioRouteStateMachineCreate, this, factory3.create(systemStateHelper, (AudioManager) this.mContext.getSystemService("audio")), factory4, this.mRinger, new RingbackPlayer(factory4), bluetoothStateReceiver, this.mDtmfLocalTonePlayer);
        this.mConnectionSvrFocusMgr = connectionServiceFocusManagerFactory.create(this.mRequester);
        this.mHeadsetMediaButton = headsetMediaButtonFactory.create(context, this, this.mLock);
        this.mTtyManager = new TtyManager(context, this.mWiredHeadsetManager);
        this.mProximitySensorManager = proximitySensorManagerFactory.create(context, this);
        this.mPhoneStateBroadcaster = new PhoneStateBroadcaster(this);
        this.mCallLogManager = new CallLogManager(context, phoneAccountRegistrar, this.mMissedCallNotifier);
        this.mConnectionServiceRepository = new ConnectionServiceRepository(this.mPhoneAccountRegistrar, this.mContext, this.mLock, this);
        InCallWakeLockController inCallWakeLockControllerCreate = inCallWakeLockControllerFactory.create(context, this);
        this.mInCallWakeLockController = inCallWakeLockControllerCreate;
        this.mClockProxy = clockProxy;
        this.mToastFactory = toastFactory;
        this.mRoleManagerAdapter = roleManagerAdapter;
        this.mListeners.add(inCallWakeLockControllerCreate);
        this.mListeners.add(statusBarNotifier);
        this.mListeners.add(this.mCallLogManager);
        this.mListeners.add(this.mPhoneStateBroadcaster);
        this.mListeners.add(this.mInCallController);
        this.mListeners.add(this.mCallAudioManager);
        this.mListeners.add(this.mCallRecordingTonePlayer);
        this.mListeners.add(missedCallNotifier);
        this.mListeners.add(this.mDisconnectedCallNotifier);
        this.mListeners.add(this.mHeadsetMediaButton);
        this.mListeners.add(this.mProximitySensorManager);
        this.mListeners.add(audioProcessingNotification);
        if (UserManager.get(this.mContext).isPrimaryUser()) {
            onUserSwitch(Process.myUserHandle());
        }
        IntentFilter intentFilter = new IntentFilter("android.telephony.action.CARRIER_CONFIG_CHANGED");
        intentFilter.addAction("android.provider.action.BLOCK_SUPPRESSION_STATE_CHANGED");
        context.registerReceiver(this.mReceiver, intentFilter);
        this.mGraphHandlerThreads = new LinkedList<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ InCallTonePlayer.MediaPlayerAdapter lambda$new$0$CallsManager(AudioManager audioManager, int i, AudioAttributes audioAttributes) {
        return new InCallTonePlayer.MediaPlayerAdapterImpl(MediaPlayer.create(this.mContext, i, audioAttributes, audioManager.generateAudioSessionId()));
    }

    static /* synthetic */ boolean lambda$new$1(AudioManager audioManager) {
        return audioManager.getStreamVolume(2) > 0;
    }

    public void setIncomingCallNotifier(IncomingCallNotifier incomingCallNotifier) {
        IncomingCallNotifier incomingCallNotifier2 = this.mIncomingCallNotifier;
        if (incomingCallNotifier2 != null) {
            this.mListeners.remove(incomingCallNotifier2);
        }
        this.mIncomingCallNotifier = incomingCallNotifier;
        this.mListeners.add(incomingCallNotifier);
    }

    public void setRespondViaSmsManager(RespondViaSmsManager respondViaSmsManager) {
        RespondViaSmsManager respondViaSmsManager2 = this.mRespondViaSmsManager;
        if (respondViaSmsManager2 != null) {
            this.mListeners.remove(respondViaSmsManager2);
        }
        this.mRespondViaSmsManager = respondViaSmsManager;
        this.mListeners.add(respondViaSmsManager);
    }

    public RespondViaSmsManager getRespondViaSmsManager() {
        return this.mRespondViaSmsManager;
    }

    public CallerInfoLookupHelper getCallerInfoLookupHelper() {
        return this.mCallerInfoLookupHelper;
    }

    public RoleManagerAdapter getRoleManagerAdapter() {
        return this.mRoleManagerAdapter;
    }

    @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
    public void onSuccessfulOutgoingCall(Call call, int i) {
        Log.v(this, "onSuccessfulOutgoingCall, %s", new Object[]{call});
        call.setPostCallPackageName(getRoleManagerAdapter().getDefaultCallScreeningApp());
        setCallState(call, i, "successful outgoing call");
        if (!this.mCalls.contains(call)) {
            addCall(call);
        }
        Iterator<CallsManagerListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onConnectionServiceChanged(call, null, call.getConnectionService());
        }
        markCallAsDialing(call);
    }

    @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
    public void onFailedOutgoingCall(Call call, DisconnectCause disconnectCause) {
        Log.v(this, "onFailedOutgoingCall, call: %s", new Object[]{call});
        markCallAsRemoved(call);
    }

    @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
    public void onSuccessfulIncomingCall(Call call) {
        Bundle bundle;
        Log.d(this, "onSuccessfulIncomingCall", new Object[0]);
        PhoneAccount phoneAccountUnchecked = this.mPhoneAccountRegistrar.getPhoneAccountUnchecked(call.getTargetPhoneAccount());
        if (phoneAccountUnchecked == null || phoneAccountUnchecked.getExtras() == null) {
            bundle = new Bundle();
        } else {
            bundle = phoneAccountUnchecked.getExtras();
        }
        if (call.hasProperty(1) || call.isSelfManaged() || bundle.getBoolean("android.telecom.extra.SKIP_CALL_FILTERING")) {
            Log.i(this, "Skipping call filtering for %s (ecm=%b, selfMgd=%b, skipExtra=%b)", new Object[]{call.getId(), Boolean.valueOf(call.hasProperty(1)), Boolean.valueOf(call.isSelfManaged()), Boolean.valueOf(bundle.getBoolean("android.telecom.extra.SKIP_CALL_FILTERING"))});
            CallFilteringResult.Builder builder = new CallFilteringResult.Builder();
            builder.setShouldAllowCall(true);
            builder.setShouldReject(false);
            builder.setShouldAddToCallLog(true);
            builder.setShouldShowNotification(true);
            onCallFilteringComplete(call, builder.build());
            call.setIsUsingCallFiltering(false);
            return;
        }
        setUpCallFilterGraph(call).performFiltering();
    }

    private IncomingCallFilterGraph setUpCallFilterGraph(Call call) {
        CallScreeningServiceFilter callScreeningServiceFilter;
        call.setIsUsingCallFiltering(true);
        String carrierPackageName = getCarrierPackageName();
        String defaultDialerPackage = TelecomManager.from(this.mContext).getDefaultDialerPackage();
        String defaultCallScreeningApp = getRoleManagerAdapter().getDefaultCallScreeningApp();
        AppLabelProxy appLabelProxy = new AppLabelProxy() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$48yCk1y6vZAkdiZtJdUeDqj_zWo
            @Override // com.android.server.telecom.AppLabelProxy
            public final CharSequence getAppLabel(String str) {
                return this.f$0.lambda$setUpCallFilterGraph$2$CallsManager(str);
            }
        };
        ParcelableCallUtils.Converter converter = new ParcelableCallUtils.Converter();
        IncomingCallFilterGraph incomingCallFilterGraph = new IncomingCallFilterGraph(call, new CallFilterResultCallback() { // from class: com.android.server.telecom.-$$Lambda$DHwVK2az6CP63VasdNK7jjDf66o
            @Override // com.android.server.telecom.callfiltering.CallFilterResultCallback
            public final void onCallFilteringComplete(Call call2, CallFilteringResult callFilteringResult) {
                this.f$0.onCallFilteringComplete(call2, callFilteringResult);
            }
        }, this.mContext, this.mTimeoutsAdapter, this.mLock);
        DirectToVoicemailFilter directToVoicemailFilter = new DirectToVoicemailFilter(call, this.mCallerInfoLookupHelper);
        BlockCheckerFilter blockCheckerFilter = new BlockCheckerFilter(this.mContext, call, this.mCallerInfoLookupHelper, new BlockCheckerAdapter());
        CallScreeningServiceFilter callScreeningServiceFilter2 = new CallScreeningServiceFilter(call, carrierPackageName, 0, this.mContext, this, appLabelProxy, converter);
        if (defaultCallScreeningApp != null && !defaultCallScreeningApp.equals(defaultDialerPackage)) {
            callScreeningServiceFilter = new CallScreeningServiceFilter(call, defaultCallScreeningApp, 2, this.mContext, this, appLabelProxy, converter);
        } else {
            callScreeningServiceFilter = new CallScreeningServiceFilter(call, defaultDialerPackage, 1, this.mContext, this, appLabelProxy, converter);
        }
        incomingCallFilterGraph.addFilter(directToVoicemailFilter);
        incomingCallFilterGraph.addFilter(blockCheckerFilter);
        incomingCallFilterGraph.addFilter(callScreeningServiceFilter2);
        incomingCallFilterGraph.addFilter(callScreeningServiceFilter);
        IncomingCallFilterGraph.addEdge(directToVoicemailFilter, callScreeningServiceFilter2);
        IncomingCallFilterGraph.addEdge(blockCheckerFilter, callScreeningServiceFilter2);
        IncomingCallFilterGraph.addEdge(callScreeningServiceFilter2, callScreeningServiceFilter);
        this.mGraphHandlerThreads.add(incomingCallFilterGraph.getHandlerThread());
        return incomingCallFilterGraph;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$setUpCallFilterGraph$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ CharSequence lambda$setUpCallFilterGraph$2$CallsManager(String str) {
        return AppLabelProxy.Util.getAppLabel(this.mContext.getPackageManager(), str);
    }

    private String getCarrierPackageName() {
        PersistableBundle config = ((CarrierConfigManager) this.mContext.getSystemService("carrier_config")).getConfig();
        ComponentName componentNameUnflattenFromString = config != null ? ComponentName.unflattenFromString(config.getString("call_screening_app", "")) : null;
        if (componentNameUnflattenFromString != null) {
            return componentNameUnflattenFromString.getPackageName();
        }
        return null;
    }

    @Override // com.android.server.telecom.callfiltering.CallFilterResultCallback
    public void onCallFilteringComplete(Call call, CallFilteringResult callFilteringResult) {
        this.mGraphHandlerThreads.clear();
        if (call.getState() != 7 && call.getState() != 9) {
            setCallState(call, 4, callFilteringResult.shouldAllowCall ? "successful incoming call" : "blocking call");
            if (callFilteringResult.shouldAllowCall) {
                call.setPostCallPackageName(getRoleManagerAdapter().getDefaultCallScreeningApp());
                if (hasMaximumManagedRingingCalls(call)) {
                    if (shouldSilenceInsteadOfReject(call)) {
                        call.silence();
                        return;
                    } else {
                        Log.i(this, "onCallFilteringCompleted: Call rejected! Exceeds maximum number of ringing calls.", new Object[0]);
                        rejectCallAndLog(call, callFilteringResult);
                        return;
                    }
                }
                if (hasMaximumManagedDialingCalls(call)) {
                    if (shouldSilenceInsteadOfReject(call)) {
                        call.silence();
                        return;
                    } else {
                        Log.i(this, "onCallFilteringCompleted: Call rejected! Exceeds maximum number of dialing calls.", new Object[0]);
                        rejectCallAndLog(call, callFilteringResult);
                        return;
                    }
                }
                if (callFilteringResult.shouldScreenViaAudio) {
                    Log.i(this, "onCallFilteringCompleted: starting background audio processing", new Object[0]);
                    answerCallForAudioProcessing(call);
                    call.setAudioProcessingRequestingApp(callFilteringResult.mCallScreeningAppName);
                    return;
                } else {
                    if (callFilteringResult.shouldSilence) {
                        Log.i(this, "onCallFilteringCompleted: setting the call to silent ringing state", new Object[0]);
                        call.setSilentRingingRequested(true);
                        addCall(call);
                        return;
                    }
                    addCall(call);
                    return;
                }
            }
            if (callFilteringResult.shouldReject) {
                Log.i(this, "onCallFilteringCompleted: blocked call, rejecting.", new Object[0]);
                call.reject(false, null);
            }
            if (callFilteringResult.shouldAddToCallLog) {
                Log.i(this, "onCallScreeningCompleted: blocked call, adding to call log.", new Object[0]);
                if (callFilteringResult.shouldShowNotification) {
                    Log.w(this, "onCallScreeningCompleted: blocked call, showing notification.", new Object[0]);
                }
                this.mCallLogManager.logCall(call, 6, callFilteringResult.shouldShowNotification, callFilteringResult);
                return;
            }
            if (callFilteringResult.shouldShowNotification) {
                Log.i(this, "onCallScreeningCompleted: blocked call, showing notification.", new Object[0]);
                this.mMissedCallNotifier.showMissedCallNotification(new MissedCallNotifier.CallInfo(call));
                return;
            }
            return;
        }
        Log.i(this, "onCallFilteringCompleted: call already disconnected.", new Object[0]);
    }

    private boolean shouldSilenceInsteadOfReject(Call call) {
        if (!this.mContext.getResources().getBoolean(R.bool.silence_incoming_when_different_service_and_maximum_ringing)) {
            return false;
        }
        for (Call call2 : this.mCalls) {
            if (call2.getParentCall() == null && !call2.isExternalCall() && call2.getConnectionService() == call.getConnectionService()) {
                return false;
            }
        }
        return true;
    }

    @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
    public void onFailedIncomingCall(Call call) {
        setCallState(call, 7, "failed incoming call");
        call.removeListener(this);
    }

    @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
    public void onSuccessfulUnknownCall(Call call, int i) {
        setCallState(call, i, "successful unknown call");
        Log.i(this, "onSuccessfulUnknownCall for call %s", new Object[]{call});
        addCall(call);
    }

    @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
    public void onFailedUnknownCall(Call call) {
        Log.i(this, "onFailedUnknownCall for call %s", new Object[]{call});
        setCallState(call, 7, "failed unknown call");
        call.removeListener(this);
    }

    @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
    public void onRingbackRequested(Call call, boolean z) {
        Iterator<CallsManagerListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onRingbackRequested(call, z);
        }
    }

    @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
    public void onPostDialWait(Call call, String str) {
        this.mInCallController.onPostDialWait(call, str);
    }

    @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
    public void onPostDialChar(final Call call, char c) {
        if (PhoneNumberUtils.is12Key(c)) {
            Runnable runnable = this.mStopTone;
            if (runnable != null) {
                this.mHandler.removeCallbacks(runnable.getRunnableToCancel());
                this.mStopTone.cancel();
            }
            this.mDtmfLocalTonePlayer.playTone(call, c);
            Runnable runnable2 = new Runnable("CM.oPDC", this.mLock) { // from class: com.android.server.telecom.CallsManager.4
                public void loggedRun() {
                    CallsManager.this.mDtmfLocalTonePlayer.stopTone(call);
                }
            };
            this.mStopTone = runnable2;
            this.mHandler.postDelayed(runnable2.prepare(), Timeouts.getDelayBetweenDtmfTonesMillis(this.mContext.getContentResolver()));
            return;
        }
        if (c != 0 && c != ';' && c != ',') {
            Log.w(this, "onPostDialChar: invalid value %d", new Object[]{Character.valueOf(c)});
            return;
        }
        Runnable runnable3 = this.mStopTone;
        if (runnable3 != null) {
            this.mHandler.removeCallbacks(runnable3.getRunnableToCancel());
            this.mStopTone.cancel();
        }
        this.mDtmfLocalTonePlayer.stopTone(call);
    }

    @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
    public void onConnectionPropertiesChanged(Call call, boolean z) {
        if (z) {
            updateHasActiveRttCall();
        }
    }

    @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
    public void onParentChanged(Call call) {
        updateCanAddCall();
        Iterator<CallsManagerListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onIsConferencedChanged(call);
        }
    }

    @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
    public void onChildrenChanged(Call call) {
        updateCanAddCall();
        Iterator<CallsManagerListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onIsConferencedChanged(call);
        }
    }

    @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
    public void onConferenceStateChanged(Call call, boolean z) {
        updateCanAddCall();
        Iterator<CallsManagerListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onConferenceStateChanged(call, z);
        }
    }

    @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
    public void onCdmaConferenceSwap(Call call) {
        Iterator<CallsManagerListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onCdmaConferenceSwap(call);
        }
    }

    @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
    public void onIsVoipAudioModeChanged(Call call) {
        Iterator<CallsManagerListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onIsVoipAudioModeChanged(call);
        }
    }

    @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
    public void onVideoStateChanged(Call call, int i, int i2) {
        Iterator<CallsManagerListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onVideoStateChanged(call, i, i2);
        }
    }

    @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
    public boolean onCanceledViaNewOutgoingCallBroadcast(final Call call, long j) {
        this.mPendingCallsToDisconnect.add(call);
        this.mHandler.postDelayed(new Runnable("CM.oCVNOCB", this.mLock) { // from class: com.android.server.telecom.CallsManager.5
            public void loggedRun() {
                if (CallsManager.this.mPendingCallsToDisconnect.remove(call)) {
                    Log.i(this, "Delayed disconnection of call: %s", new Object[]{call});
                    call.disconnect();
                }
            }
        }.prepare(), j);
        return true;
    }

    @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
    public void onVideoCallProviderChanged(Call call) {
        VideoProviderProxy videoProviderProxy = call.getVideoProviderProxy();
        if (videoProviderProxy == null) {
            return;
        }
        videoProviderProxy.addListener(this);
    }

    @Override // com.android.server.telecom.VideoProviderProxy.Listener
    public void onSessionModifyRequestReceived(Call call, VideoProfile videoProfile) {
        Log.v("CallsManager", "onSessionModifyRequestReceived : videoProfile = " + VideoProfile.videoStateToString(videoProfile != null ? videoProfile.getVideoState() : 0), new Object[0]);
        Iterator<CallsManagerListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onSessionModifyRequestReceived(call, videoProfile);
        }
    }

    @Override // com.android.server.telecom.VideoProviderProxy.Listener
    public void onSetCamera(Call call, String str) {
        Iterator<CallsManagerListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onSetCamera(call, str);
        }
    }

    public Collection<Call> getCalls() {
        return Collections.unmodifiableCollection(this.mCalls);
    }

    @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
    public void onHoldToneRequested(Call call) {
        Iterator<CallsManagerListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onHoldToneRequested(call);
        }
    }

    @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
    public void onHandoverRequested(Call call, PhoneAccountHandle phoneAccountHandle, int i, Bundle bundle, boolean z) {
        if (z) {
            requestHandoverViaEvents(call, phoneAccountHandle, i, bundle);
        } else {
            requestHandover(call, phoneAccountHandle, i, bundle);
        }
    }

    @VisibleForTesting
    public Call getForegroundCall() {
        CallAudioManager callAudioManager = this.mCallAudioManager;
        if (callAudioManager == null) {
            return null;
        }
        return callAudioManager.getForegroundCall();
    }

    @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
    public void onCallHoldFailed(Call call) {
        markAllAnsweredCallAsRinging(call, "hold");
    }

    @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
    public void onCallSwitchFailed(Call call) {
        markAllAnsweredCallAsRinging(call, "switch");
    }

    private void markAllAnsweredCallAsRinging(Call call, String str) {
        for (Call call2 : this.mCalls) {
            if (call2 != call && call2.getState() == 11) {
                setCallState(call2, 4, str + " failed on other call");
            }
        }
    }

    @Override // com.android.server.telecom.CurrentUserProxy
    public UserHandle getCurrentUserHandle() {
        return this.mCurrentUserHandle;
    }

    public CallAudioManager getCallAudioManager() {
        return this.mCallAudioManager;
    }

    InCallController getInCallController() {
        return this.mInCallController;
    }

    EmergencyCallHelper getEmergencyCallHelper() {
        return this.mEmergencyCallHelper;
    }

    @VisibleForTesting
    public PhoneAccountRegistrar.Listener getPhoneAccountListener() {
        return this.mPhoneAccountListener;
    }

    @VisibleForTesting
    public boolean hasOnlyDisconnectedCalls() {
        if (this.mCalls.size() == 0) {
            return false;
        }
        Iterator<Call> it = this.mCalls.iterator();
        while (it.hasNext()) {
            if (!it.next().isDisconnected()) {
                return false;
            }
        }
        return true;
    }

    public boolean hasVideoCall() {
        Iterator<Call> it = this.mCalls.iterator();
        while (it.hasNext()) {
            if (VideoProfile.isVideo(it.next().getVideoState())) {
                return true;
            }
        }
        return false;
    }

    @VisibleForTesting
    public CallAudioState getAudioState() {
        return this.mCallAudioManager.getCallAudioState();
    }

    boolean isTtySupported() {
        return this.mTtyManager.isTtySupported();
    }

    int getCurrentTtyMode() {
        return this.mTtyManager.getCurrentTtyMode();
    }

    @VisibleForTesting
    public void addListener(CallsManagerListener callsManagerListener) {
        this.mListeners.add(callsManagerListener);
    }

    @VisibleForTesting
    public void removeListener(CallsManagerListener callsManagerListener) {
        this.mListeners.remove(callsManagerListener);
    }

    void processIncomingConference(PhoneAccountHandle phoneAccountHandle, Bundle bundle) {
        Log.d(this, "processIncomingCallConference", new Object[0]);
        processIncomingCallIntent(phoneAccountHandle, bundle, true);
    }

    void processIncomingCallIntent(PhoneAccountHandle phoneAccountHandle, Bundle bundle) {
        processIncomingCallIntent(phoneAccountHandle, bundle, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0224  */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void processIncomingCallIntent(android.telecom.PhoneAccountHandle r24, android.os.Bundle r25, boolean r26) {
        /*
            Method dump skipped, instruction units count: 552
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.server.telecom.CallsManager.processIncomingCallIntent(android.telecom.PhoneAccountHandle, android.os.Bundle, boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$processIncomingCallIntent$3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$processIncomingCallIntent$3$CallsManager(String str, Call call) {
        return this.mPhoneNumberUtilsAdapter.isSamePhoneNumber(call.getHandle() == null ? null : call.getHandle().getSchemeSpecificPart(), str);
    }

    void addNewUnknownCall(PhoneAccountHandle phoneAccountHandle, Bundle bundle) {
        Uri uri = (Uri) bundle.getParcelable("android.telecom.extra.UNKNOWN_CALL_HANDLE");
        Log.i(this, "addNewUnknownCall with handle: %s", new Object[]{Log.pii(uri)});
        Call call = new Call(getNextCallId(), this.mContext, this, this.mLock, this.mConnectionServiceRepository, this.mPhoneNumberUtilsAdapter, uri, null, null, phoneAccountHandle, 3, true, false, this.mClockProxy, this.mToastFactory);
        call.initAnalytics();
        setIntentExtrasAndStartTime(call, bundle);
        call.addListener(this);
        call.startCreateConnection(this.mPhoneAccountRegistrar);
    }

    private boolean areHandlesEqual(Uri uri, Uri uri2) {
        if (uri == null || uri2 == null) {
            return uri == uri2;
        }
        if (TextUtils.equals(uri.getScheme(), uri2.getScheme())) {
            return TextUtils.equals(PhoneNumberUtils.normalizeNumber(uri.getSchemeSpecificPart()), PhoneNumberUtils.normalizeNumber(uri2.getSchemeSpecificPart()));
        }
        return false;
    }

    private Call reuseOutgoingCall(Uri uri) {
        Iterator<Call> it = this.mPendingCallsToDisconnect.iterator();
        Call call = null;
        while (it.hasNext()) {
            Call next = it.next();
            if (call == null && areHandlesEqual(next.getHandle(), uri)) {
                it.remove();
                Log.i(this, "Reusing disconnected call %s", new Object[]{next});
                call = next;
            } else {
                Log.i(this, "Not reusing disconnected call %s", new Object[]{next});
                it.remove();
                next.disconnect();
            }
        }
        return call;
    }

    @VisibleForTesting
    public CompletableFuture<Call> startOutgoingCall(Uri uri, PhoneAccountHandle phoneAccountHandle, Bundle bundle, UserHandle userHandle, Intent intent, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(uri);
        return startOutgoingCall(arrayList, phoneAccountHandle, bundle, userHandle, intent, str, false);
    }

    private CompletableFuture<Call> startOutgoingCall(List<Uri> list, final PhoneAccountHandle phoneAccountHandle, final Bundle bundle, final UserHandle userHandle, final Intent intent, String str, final boolean z) {
        List<Uri> list2;
        Uri uri;
        PhoneAccount phoneAccount;
        Uri uri2;
        String str2;
        final boolean z2;
        final Bundle bundle2;
        boolean z3;
        final Call call;
        final CallsManager callsManager;
        final int i;
        PhoneAccount phoneAccount2;
        if (z) {
            uri = Uri.parse("tel:conf-factory");
            list2 = list;
        } else {
            list2 = list;
            uri = list2.get(0);
        }
        Call callReuseOutgoingCall = reuseOutgoingCall(uri);
        PhoneAccount phoneAccount3 = this.mPhoneAccountRegistrar.getPhoneAccount(phoneAccountHandle, userHandle);
        boolean z4 = phoneAccount3 != null && phoneAccount3.isSelfManaged();
        if (callReuseOutgoingCall == null) {
            str2 = null;
            phoneAccount = phoneAccount3;
            uri2 = uri;
            Call call2 = new Call(getNextCallId(), this.mContext, this, this.mLock, this.mConnectionServiceRepository, this.mPhoneNumberUtilsAdapter, uri2, z ? list2 : null, null, null, null, 1, false, z, this.mClockProxy, this.mToastFactory);
            call2.initAnalytics(str);
            z2 = z4;
            call2.setIsSelfManaged(z2);
            if (z2) {
                call2.setIsVoipAudioMode(true);
            }
            call2.setInitiatingUser(userHandle);
            bundle2 = bundle;
            call = call2;
            z3 = false;
        } else {
            phoneAccount = phoneAccount3;
            uri2 = uri;
            str2 = null;
            z2 = z4;
            bundle2 = bundle;
            z3 = true;
            call = callReuseOutgoingCall;
        }
        if (bundle2 != null) {
            int i2 = bundle2.getInt("android.telecom.extra.START_CALL_WITH_VIDEO_STATE", 0);
            if (VideoProfile.isVideo(i2)) {
                if (call.isEmergencyCall()) {
                    phoneAccount2 = phoneAccount;
                    if (phoneAccount2 == null || phoneAccount2.hasCapabilities(512)) {
                        callsManager = this;
                    } else {
                        callsManager = this;
                        Log.i(callsManager, "startOutgoingCall - emergency video calls not supported; falling back to audio-only", new Object[0]);
                        i2 = 0;
                    }
                } else {
                    callsManager = this;
                    phoneAccount2 = phoneAccount;
                }
                if (phoneAccount2 != null && !phoneAccount2.hasCapabilities(8)) {
                    Log.i(callsManager, "startOutgoingCall - video calls not supported; fallback to audio-only.", new Object[0]);
                    i2 = 0;
                }
            } else {
                callsManager = this;
            }
            call.setVideoState(i2);
            i = i2;
        } else {
            callsManager = this;
            i = 0;
        }
        Handler handler = new Handler(Looper.getMainLooper());
        final Uri uri3 = uri2;
        final Call call3 = call;
        CompletableFuture completableFutureThenComposeAsync = CompletableFuture.completedFuture(str2).thenComposeAsync(new Function() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$ZdlPbBqlVrgcNgN_dYHQ0kRpiok
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$startOutgoingCall$4$CallsManager(phoneAccountHandle, uri3, i, call3, userHandle, z, (Void) obj);
            }
        }, (Executor) new LoggedHandlerExecutor(handler, "CM.fOCP", callsManager.mLock));
        final Uri uri4 = uri2;
        final boolean z5 = z2;
        final Call call4 = call;
        final boolean z6 = z3;
        CompletableFuture<Pair<Call, List<PhoneAccountSuggestion>>> completableFutureThenCombine = completableFutureThenComposeAsync.whenCompleteAsync(new BiConsumer() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$WF9xtNFK0FSuZxU8SMfnwcatAms
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.f$0.lambda$startOutgoingCall$5$CallsManager(call, (List) obj, (Throwable) obj2);
            }
        }, (Executor) new LoggedHandlerExecutor(handler, "CM.sOCPA", callsManager.mLock)).thenComposeAsync(new Function() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$kZrFe_4FpWwLeLjppZYzl_GeFbk
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$startOutgoingCall$7$CallsManager(uri4, z5, call4, z6, intent, z, (List) obj);
            }
        }, (Executor) new LoggedHandlerExecutor(handler, "CM.dSMCP", callsManager.mLock)).thenCombine((CompletionStage) completableFutureThenComposeAsync.thenComposeAsync(new Function() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$Y9eEZTN8nr3V8R5ij4Dc7vK9nAw
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$startOutgoingCall$6$CallsManager(call, (List) obj);
            }
        }, (Executor) new LoggedHandlerExecutor(handler, "CM.cOCSS", callsManager.mLock)), (BiFunction) new BiFunction() { // from class: com.android.server.telecom.-$$Lambda$zmmFQ0B_4APpYqltOmbuyDYPuaM
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return Pair.create((Call) obj, (List) obj2);
            }
        });
        callsManager.mLatestPreAccountSelectionFuture = completableFutureThenCombine;
        CompletableFuture<U> completableFutureThenComposeAsync2 = completableFutureThenCombine.thenComposeAsync(new Function() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$sug2_Gtq2Ndz05c1cHhumnWpYb8
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$startOutgoingCall$8$CallsManager(z2, bundle2, (Pair) obj);
            }
        }, (Executor) new LoggedHandlerExecutor(handler, "CM.dSPA", callsManager.mLock));
        if ("tel".equals(uri2.getScheme())) {
            completableFutureThenComposeAsync2.thenAcceptBothAsync((CompletionStage) callsManager.mCallerInfoLookupHelper.startLookup(Uri.fromParts(uri2.getScheme(), PhoneNumberUtils.extractNetworkPortion(uri2.getSchemeSpecificPart()), str2)), (BiConsumer<? super U, ? super U>) new BiConsumer() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$zD_yt6jJwoV29toGaW1cBWczElg
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    this.f$0.lambda$startOutgoingCall$9$CallsManager((Pair) obj, (Pair) obj2);
                }
            }, (Executor) new LoggedHandlerExecutor(handler, "CM.pCSB", callsManager.mLock));
        }
        final Uri uri5 = uri2;
        final boolean z7 = z2;
        CompletableFuture<Call> completableFutureThenComposeAsync3 = completableFutureThenComposeAsync2.thenComposeAsync((Function<? super U, ? extends CompletionStage<U>>) new Function() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$E-hY1SV6_6hl6Oj0Jd3zbgHLjSs
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$startOutgoingCall$10$CallsManager(userHandle, bundle, intent, uri5, z7, (Pair) obj);
            }
        }, (Executor) new LoggedHandlerExecutor(handler, "CM.pASP", callsManager.mLock));
        callsManager.mLatestPostSelectionProcessingFuture = completableFutureThenComposeAsync3;
        return completableFutureThenComposeAsync3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$startOutgoingCall$4, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ CompletionStage lambda$startOutgoingCall$4$CallsManager(PhoneAccountHandle phoneAccountHandle, Uri uri, int i, Call call, UserHandle userHandle, boolean z, Void r14) {
        return findOutgoingCallPhoneAccount(phoneAccountHandle, uri, VideoProfile.isVideo(i), call.isEmergencyCall(), userHandle, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$startOutgoingCall$5, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$startOutgoingCall$5$CallsManager(Call call, List list, Throwable th) {
        Log.i(this, "set outgoing call phone acct stage", new Object[0]);
        call.setTargetPhoneAccount(list.size() == 1 ? (PhoneAccountHandle) list.get(0) : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$startOutgoingCall$6, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ CompletionStage lambda$startOutgoingCall$6$CallsManager(Call call, List list) {
        Log.i(this, "call outgoing call suggestion service stage", new Object[0]);
        if (list.size() == 1) {
            return CompletableFuture.completedFuture(Collections.singletonList(new PhoneAccountSuggestion((PhoneAccountHandle) list.get(0), 0, true)));
        }
        return PhoneAccountSuggestionHelper.bindAndGetSuggestions(this.mContext, call.getHandle(), list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$startOutgoingCall$7, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ CompletionStage lambda$startOutgoingCall$7$CallsManager(Uri uri, boolean z, Call call, boolean z2, Intent intent, boolean z3, List list) {
        boolean zMakeRoomForOutgoingCall;
        Log.i(this, "make room for outgoing call stage", new Object[0]);
        if (isPotentialInCallMMICode(uri) && !z) {
            return CompletableFuture.completedFuture(call);
        }
        if (z2) {
            return CompletableFuture.completedFuture(call);
        }
        if (call.isEmergencyCall()) {
            zMakeRoomForOutgoingCall = makeRoomForOutgoingEmergencyCall(call);
        } else {
            zMakeRoomForOutgoingCall = makeRoomForOutgoingCall(call);
        }
        if (!zMakeRoomForOutgoingCall) {
            Call foregroundCall = getForegroundCall();
            Log.d(this, "No more room for outgoing call %s ", new Object[]{call});
            if (foregroundCall.isSelfManaged()) {
                Log.i(this, "Prompting user to disconnect self-managed call", new Object[0]);
                call.setOriginalCallIntent(intent);
                CompletableFuture<Call> completableFuture = new CompletableFuture<>();
                startCallConfirmation(call, completableFuture);
                return completableFuture;
            }
            if (z3) {
                notifyCreateConferenceFailed(call.getTargetPhoneAccount(), call);
            } else {
                notifyCreateConnectionFailed(call.getTargetPhoneAccount(), call);
            }
            Log.i(this, "Aborting call since there's no room", new Object[0]);
            return CompletableFuture.completedFuture(null);
        }
        return CompletableFuture.completedFuture(call);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$startOutgoingCall$8, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ CompletionStage lambda$startOutgoingCall$8$CallsManager(boolean z, Bundle bundle, Pair pair) {
        Log.i(this, "dialer phone acct select stage", new Object[0]);
        Call call = (Call) pair.first;
        List list = (List) pair.second;
        if (call == null) {
            return CompletableFuture.completedFuture(null);
        }
        if (list == null || list.isEmpty()) {
            Log.i(this, "Aborting call since there are no available accounts.", new Object[0]);
            showErrorMessage(R.string.cant_call_due_to_no_supported_service);
            return CompletableFuture.completedFuture(null);
        }
        if (!((list.size() <= 1 || call.isEmergencyCall() || z) ? false : true)) {
            return CompletableFuture.completedFuture(Pair.create(call, ((PhoneAccountSuggestion) list.get(0)).getPhoneAccountHandle()));
        }
        call.setState(2, "needs account selection");
        Bundle bundle2 = new Bundle(bundle);
        bundle2.putParcelableList("selectPhoneAccountAccounts", (List) list.stream().map($$Lambda$yRlpl3dkbrkzuO0fcR2P1LnNvk.INSTANCE).collect(Collectors.toList()));
        bundle2.putParcelableList("android.telecom.extra.SUGGESTED_PHONE_ACCOUNTS", list);
        this.mPendingAccountSelection = new CompletableFuture<>();
        call.setIntentExtras(bundle2);
        addCall(call);
        return this.mPendingAccountSelection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$startOutgoingCall$9, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$startOutgoingCall$9$CallsManager(Pair pair, Pair pair2) {
        Call call = (Call) pair.first;
        Object obj = pair2.second;
        boolean z = obj != null && ((CallerInfo) obj).contactExists;
        Log.d(this, "outgoingCallIdStage: isInContacts=%s", new Object[]{Boolean.valueOf(z)});
        int iCheckPermission = this.mContext.getPackageManager().checkPermission("android.permission.READ_CONTACTS", this.mRoleManagerAdapter.getDefaultCallScreeningApp());
        Object[] objArr = new Object[2];
        objArr[0] = this.mRoleManagerAdapter.getDefaultCallScreeningApp();
        objArr[1] = Boolean.valueOf(iCheckPermission == 0);
        Log.d(this, "default call screening service package %s has permissions=%s", objArr);
        if (!z || iCheckPermission == 0) {
            bindForOutgoingCallerId(call);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$startOutgoingCall$10, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ CompletionStage lambda$startOutgoingCall$10$CallsManager(UserHandle userHandle, Bundle bundle, Intent intent, Uri uri, boolean z, Pair pair) {
        if (pair == null) {
            return CompletableFuture.completedFuture(null);
        }
        Log.i(this, "post acct selection stage", new Object[0]);
        Call call = (Call) pair.first;
        PhoneAccountHandle phoneAccountHandle = (PhoneAccountHandle) pair.second;
        PhoneAccount phoneAccount = this.mPhoneAccountRegistrar.getPhoneAccount(phoneAccountHandle, userHandle);
        call.setTargetPhoneAccount(phoneAccountHandle);
        if (phoneAccount != null && phoneAccount.getExtras() != null && phoneAccount.getExtras().getBoolean("android.telecom.extra.ALWAYS_USE_VOIP_AUDIO_MODE")) {
            Log.d(this, "startOutgoingCall: defaulting to voip mode for call %s", new Object[]{call.getId()});
            call.setIsVoipAudioMode(true);
        }
        call.setState(1, phoneAccountHandle == null ? "no-handle" : phoneAccountHandle.toString());
        boolean zIsVoicemail = isVoicemail(call.getHandle(), phoneAccount);
        boolean zIsRttSettingOn = isRttSettingOn(phoneAccountHandle);
        if (!zIsVoicemail && (zIsRttSettingOn || (bundle != null && bundle.getBoolean("android.telecom.extra.START_CALL_WITH_RTT", false)))) {
            Log.d(this, "Outgoing call requesting RTT, rtt setting is %b", new Object[]{Boolean.valueOf(zIsRttSettingOn)});
            if (call.isEmergencyCall() || (phoneAccount != null && phoneAccount.hasCapabilities(4096))) {
                call.createRttStreams();
            }
            call.setRequestedToStartWithRtt();
        }
        setIntentExtrasAndStartTime(call, bundle);
        setCallSourceToAnalytics(call, intent);
        if (isPotentialMMICode(uri) && !z) {
            call.addListener(this);
        } else if (!this.mCalls.contains(call)) {
            addCall(call);
        }
        return CompletableFuture.completedFuture(call);
    }

    public void startConference(List<Uri> list, Bundle bundle, String str, UserHandle userHandle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        CompletableFuture<Call> completableFutureStartOutgoingCall = startOutgoingCall(list, (PhoneAccountHandle) bundle.getParcelable("android.telecom.extra.PHONE_ACCOUNT_HANDLE"), bundle, userHandle, null, str, true);
        final boolean z = bundle.getBoolean("android.telecom.extra.START_CALL_WITH_SPEAKERPHONE");
        final int i = bundle.getInt("android.telecom.extra.START_CALL_WITH_VIDEO_STATE");
        final Session sessionCreateSubsession = Log.createSubsession();
        completableFutureStartOutgoingCall.thenAccept(new Consumer() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$nvlBLONfapeiNGSnC1y_uJ_yQj0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$startConference$11$CallsManager(sessionCreateSubsession, z, i, (Call) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$startConference$11, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$startConference$11$CallsManager(Session session, boolean z, int i, Call call) {
        if (call != null) {
            Log.continueSession(session, "CM.pOGC");
            try {
                placeOutgoingCall(call, call.getHandle(), null, z, i);
            } finally {
                Log.endSession();
            }
        }
    }

    private void bindForOutgoingCallerId(Call call) {
        new CallScreeningServiceHelper(this.mContext, this.mLock, this.mRoleManagerAdapter.getDefaultCallScreeningApp(), new ParcelableCallUtils.Converter(), this.mCurrentUserHandle, call, new AppLabelProxy() { // from class: com.android.server.telecom.CallsManager.6
            @Override // com.android.server.telecom.AppLabelProxy
            public CharSequence getAppLabel(String str) {
                return AppLabelProxy.Util.getAppLabel(CallsManager.this.mContext.getPackageManager(), str);
            }
        }).process().thenApply(new Function() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$tNpFOTnwmZnkoxisC5jcV6cEMFw
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$bindForOutgoingCallerId$12$CallsManager(obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$bindForOutgoingCallerId$12, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ Object lambda$bindForOutgoingCallerId$12$CallsManager(Object obj) {
        Log.i(this, "Outgoing caller ID complete", new Object[0]);
        return null;
    }

    @VisibleForTesting
    public CompletableFuture<List<PhoneAccountHandle>> findOutgoingCallPhoneAccount(PhoneAccountHandle phoneAccountHandle, Uri uri, boolean z, boolean z2, UserHandle userHandle) {
        return findOutgoingCallPhoneAccount(phoneAccountHandle, uri, z, z2, userHandle, false);
    }

    public CompletableFuture<List<PhoneAccountHandle>> findOutgoingCallPhoneAccount(PhoneAccountHandle phoneAccountHandle, final Uri uri, boolean z, boolean z2, final UserHandle userHandle, boolean z3) {
        if (isSelfManaged(phoneAccountHandle, userHandle)) {
            return CompletableFuture.completedFuture(Arrays.asList(phoneAccountHandle));
        }
        final List<PhoneAccountHandle> listConstructPossiblePhoneAccounts = constructPossiblePhoneAccounts(uri, userHandle, z, z2, z3);
        if (z && listConstructPossiblePhoneAccounts.size() == 0) {
            listConstructPossiblePhoneAccounts = constructPossiblePhoneAccounts(uri, userHandle, false, z2, z3);
        }
        Log.v(this, "findOutgoingCallPhoneAccount: accounts = " + listConstructPossiblePhoneAccounts, new Object[0]);
        if (phoneAccountHandle != null && listConstructPossiblePhoneAccounts.contains(phoneAccountHandle)) {
            return CompletableFuture.completedFuture(Arrays.asList(phoneAccountHandle));
        }
        if (listConstructPossiblePhoneAccounts.isEmpty() || listConstructPossiblePhoneAccounts.size() == 1) {
            return CompletableFuture.completedFuture(listConstructPossiblePhoneAccounts);
        }
        final CompletableFuture completableFuture = new CompletableFuture();
        this.mCallerInfoLookupHelper.startLookup(uri, new CallerInfoLookupHelper.OnQueryCompleteListener(this) { // from class: com.android.server.telecom.CallsManager.7
            @Override // com.android.server.telecom.CallerInfoLookupHelper.OnQueryCompleteListener
            public void onContactPhotoQueryComplete(Uri uri2, CallerInfo callerInfo) {
            }

            @Override // com.android.server.telecom.CallerInfoLookupHelper.OnQueryCompleteListener
            public void onCallerInfoQueryComplete(Uri uri2, CallerInfo callerInfo) {
                String str;
                if (callerInfo != null && callerInfo.preferredPhoneAccountComponent != null && (str = callerInfo.preferredPhoneAccountId) != null && !str.isEmpty()) {
                    completableFuture.complete(new PhoneAccountHandle(callerInfo.preferredPhoneAccountComponent, callerInfo.preferredPhoneAccountId, userHandle));
                } else {
                    completableFuture.complete(null);
                }
            }
        });
        return completableFuture.thenApply(new Function() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$5ZvZmZ8rBqcG77G3ECd_HXKUIAM
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$findOutgoingCallPhoneAccount$13$CallsManager(uri, userHandle, listConstructPossiblePhoneAccounts, (PhoneAccountHandle) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$findOutgoingCallPhoneAccount$13, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ List lambda$findOutgoingCallPhoneAccount$13$CallsManager(Uri uri, UserHandle userHandle, List list, PhoneAccountHandle phoneAccountHandle) {
        if (phoneAccountHandle != null) {
            return Collections.singletonList(phoneAccountHandle);
        }
        PhoneAccountHandle outgoingPhoneAccountForScheme = this.mPhoneAccountRegistrar.getOutgoingPhoneAccountForScheme(uri.getScheme(), userHandle);
        return (outgoingPhoneAccountForScheme == null || !list.contains(outgoingPhoneAccountForScheme)) ? list : Collections.singletonList(outgoingPhoneAccountForScheme);
    }

    public boolean isSelfManaged(PhoneAccountHandle phoneAccountHandle, UserHandle userHandle) {
        PhoneAccount phoneAccount = this.mPhoneAccountRegistrar.getPhoneAccount(phoneAccountHandle, userHandle);
        return phoneAccount != null && phoneAccount.isSelfManaged();
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0031  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onCallRedirectionComplete(final com.android.server.telecom.Call r17, final android.net.Uri r18, final android.telecom.PhoneAccountHandle r19, final android.telecom.GatewayInfo r20, final boolean r21, final int r22, boolean r23, java.lang.String r24) {
        /*
            Method dump skipped, instruction units count: 305
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.server.telecom.CallsManager.onCallRedirectionComplete(com.android.server.telecom.Call, android.net.Uri, android.telecom.PhoneAccountHandle, android.telecom.GatewayInfo, boolean, int, boolean, java.lang.String):void");
    }

    private void showRedirectionDialog(final String str, CharSequence charSequence) {
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(this.mContext).create();
        View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.call_redirection_confirm_dialog, (ViewGroup) null);
        ((Button) viewInflate.findViewById(R.id.buttonFirstLine)).setOnClickListener(new View.OnClickListener() { // from class: com.android.server.telecom.CallsManager.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent("com.android.server.telecom.PROCEED_WITH_UNREDIRECTED_CALL", null, CallsManager.this.mContext, TelecomBroadcastReceiver.class);
                intent.putExtra("android.telecom.extra.REDIRECTION_OUTGOING_CALL_ID", str);
                CallsManager.this.mContext.sendBroadcast(intent);
                alertDialogCreate.dismiss();
            }
        });
        Button button = (Button) viewInflate.findViewById(R.id.buttonSecondLine);
        button.setText(this.mContext.getString(R.string.alert_place_outgoing_call_with_redirection, charSequence));
        button.setOnClickListener(new View.OnClickListener() { // from class: com.android.server.telecom.CallsManager.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent("com.android.server.telecom.PROCEED_WITH_REDIRECTED_CALL", null, CallsManager.this.mContext, TelecomBroadcastReceiver.class);
                intent.putExtra("android.telecom.extra.REDIRECTION_OUTGOING_CALL_ID", str);
                CallsManager.this.mContext.sendBroadcast(intent);
                alertDialogCreate.dismiss();
            }
        });
        ((Button) viewInflate.findViewById(R.id.buttonThirdLine)).setOnClickListener(new View.OnClickListener() { // from class: com.android.server.telecom.CallsManager.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CallsManager.this.cancelRedirection(str);
                alertDialogCreate.dismiss();
            }
        });
        alertDialogCreate.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.android.server.telecom.CallsManager.13
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                CallsManager.this.cancelRedirection(str);
                alertDialogCreate.dismiss();
            }
        });
        alertDialogCreate.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        alertDialogCreate.getWindow().setType(2003);
        alertDialogCreate.setCancelable(false);
        alertDialogCreate.setCanceledOnTouchOutside(false);
        alertDialogCreate.setView(viewInflate);
        alertDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelRedirection(String str) {
        Intent intent = new Intent("com.android.server.telecom.CANCEL_REDIRECTED_CALL", null, this.mContext, TelecomBroadcastReceiver.class);
        intent.putExtra("android.telecom.extra.REDIRECTION_OUTGOING_CALL_ID", str);
        this.mContext.sendBroadcastAsUser(intent, UserHandle.CURRENT);
    }

    public void processRedirectedOutgoingCallAfterUserInteraction(String str, String str2) {
        Log.i(this, "processRedirectedOutgoingCallAfterUserInteraction for Call ID %s, action=%s", new Object[]{str, str2});
        Call call = this.mPendingRedirectedOutgoingCall;
        if (call != null && call.getId().equals(str)) {
            if (str2.equals("com.android.server.telecom.PROCEED_WITH_REDIRECTED_CALL")) {
                this.mHandler.post(this.mPendingRedirectedOutgoingCallInfo.get(str).prepare());
            } else if (str2.equals("com.android.server.telecom.PROCEED_WITH_UNREDIRECTED_CALL")) {
                this.mHandler.post(this.mPendingUnredirectedOutgoingCallInfo.get(str).prepare());
            } else if (str2.equals("com.android.server.telecom.CANCEL_REDIRECTED_CALL")) {
                Log.addEvent(this.mPendingRedirectedOutgoingCall, "REDIRECTION_USER_CANCELLED");
                this.mPendingRedirectedOutgoingCall.disconnect("User canceled the redirected call.");
            }
            this.mPendingRedirectedOutgoingCall = null;
            this.mPendingRedirectedOutgoingCallInfo.remove(str);
            this.mPendingUnredirectedOutgoingCallInfo.remove(str);
            return;
        }
        Log.w(this, "processRedirectedOutgoingCallAfterUserInteraction for non-matched Call ID %s", new Object[]{str});
    }

    @VisibleForTesting
    public void placeOutgoingCall(Call call, Uri uri, GatewayInfo gatewayInfo, boolean z, int i) {
        if (call == null) {
            Log.i(this, "Canceling unknown call.", new Object[0]);
            return;
        }
        Uri gatewayAddress = gatewayInfo == null ? uri : gatewayInfo.getGatewayAddress();
        if (gatewayInfo == null) {
            Log.i(this, "Creating a new outgoing call with handle: %s", new Object[]{Log.piiHandle(gatewayAddress)});
        } else {
            Log.i(this, "Creating a new outgoing call with gateway handle: %s, original handle: %s", new Object[]{Log.pii(gatewayAddress), Log.pii(uri)});
        }
        call.setHandle(gatewayAddress);
        call.setGatewayInfo(gatewayInfo);
        boolean z2 = this.mContext.getResources().getBoolean(R.bool.use_speaker_when_docked);
        boolean zIsSpeakerphoneEnabledForDock = isSpeakerphoneEnabledForDock();
        boolean zIsSpeakerphoneAutoEnabledForVideoCalls = isSpeakerphoneAutoEnabledForVideoCalls(i);
        PhoneAccount phoneAccount = this.mPhoneAccountRegistrar.getPhoneAccount(call.getTargetPhoneAccount(), call.getInitiatingUser());
        call.setStartWithSpeakerphoneOn(z || (zIsSpeakerphoneAutoEnabledForVideoCalls && (phoneAccount != null ? phoneAccount.hasCapabilities(8) : false)) || (z2 && zIsSpeakerphoneEnabledForDock));
        call.setVideoState(i);
        if (z) {
            Log.i(this, "%s Starting with speakerphone as requested", new Object[]{call});
        } else if (z2 && zIsSpeakerphoneEnabledForDock) {
            Log.i(this, "%s Starting with speakerphone because car is docked.", new Object[]{call});
        } else if (zIsSpeakerphoneAutoEnabledForVideoCalls) {
            Log.i(this, "%s Starting with speakerphone because its a video call.", new Object[]{call});
        }
        if (call.isEmergencyCall()) {
            Executors.defaultThreadFactory().newThread(new Runnable() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$4PG7IEE1ZKzesluAKYHFpmu3Ryk
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$placeOutgoingCall$14$CallsManager();
                }
            }).start();
        }
        boolean z3 = this.mContext.getResources().getBoolean(android.R.bool.config_cecQuerySadMaxEnabled_default);
        boolean zIsOutgoingCallPermitted = isOutgoingCallPermitted(call, call.getTargetPhoneAccount());
        String scheme = call.getHandle() == null ? null : call.getHandle().getScheme();
        if (call.getTargetPhoneAccount() != null || call.isEmergencyCall()) {
            if (call.isSelfManaged() && !zIsOutgoingCallPermitted) {
                if (call.isAdhocConferenceCall()) {
                    notifyCreateConferenceFailed(call.getTargetPhoneAccount(), call);
                    return;
                } else {
                    notifyCreateConnectionFailed(call.getTargetPhoneAccount(), call);
                    return;
                }
            }
            if (call.isEmergencyCall()) {
                disconnectSelfManagedCalls("place emerg call");
            }
            call.startCreateConnection(this.mPhoneAccountRegistrar);
            return;
        }
        if (this.mPhoneAccountRegistrar.getCallCapablePhoneAccounts(z3 ? scheme : null, false, call.getInitiatingUser()).isEmpty()) {
            markCallAsDisconnected(call, new DisconnectCause(4, "No registered PhoneAccounts"));
            markCallAsRemoved(call);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$placeOutgoingCall$14, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$placeOutgoingCall$14$CallsManager() {
        BlockedNumberContract.SystemContract.notifyEmergencyContact(this.mContext);
    }

    @VisibleForTesting
    public void conference(Call call, Call call2) {
        call.conferenceWith(call2);
    }

    @VisibleForTesting
    public void answerCall(Call call, int i) {
        if (!this.mCalls.contains(call)) {
            Log.i(this, "Request to answer a non-existent call %s", new Object[]{call});
            return;
        }
        Log.d(this, "answerCall: Incoming call = %s Ongoing call %s", new Object[]{call, (Call) this.mConnectionSvrFocusMgr.getCurrentFocusCall()});
        holdActiveCallForNewCall(call);
        this.mConnectionSvrFocusMgr.requestFocus(call, new RequestCallback(new ActionAnswerCall(call, i)));
    }

    private void answerCallForAudioProcessing(final Call call) {
        Call call2 = (Call) this.mConnectionSvrFocusMgr.getCurrentFocusCall();
        if (call2 != null && call2 != call) {
            Log.w(this, "answerCallForAudioProcessing: another active call already exists. Ignoring request for audio processing and letting the incoming call through.", new Object[0]);
            addCall(call);
        } else {
            Log.d(this, "answerCallForAudioProcessing: Incoming call = %s", new Object[]{call});
            this.mConnectionSvrFocusMgr.requestFocus(call, new RequestCallback(new PendingAction() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$CRVwXlGSLhzmuKK1F7oDu5dInYg
                @Override // com.android.server.telecom.CallsManager.PendingAction
                public final void performAction() {
                    this.f$0.lambda$answerCallForAudioProcessing$15$CallsManager(call);
                }
            }));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$answerCallForAudioProcessing$15, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$answerCallForAudioProcessing$15$CallsManager(Call call) {
        synchronized (this.mLock) {
            Log.d(this, "answering call %s for audio processing with cs focus", new Object[]{call});
            call.answerForAudioProcessing();
            this.mPendingAudioProcessingCall = call;
        }
    }

    public void enterBackgroundAudioProcessing(Call call, String str) {
        if (!this.mCalls.contains(call)) {
            Log.w(this, "Trying to exit audio processing on an untracked call", new Object[0]);
            return;
        }
        Call activeCall = getActiveCall();
        if (activeCall != null && activeCall != call) {
            Log.w(this, "Ignoring enter audio processing because there's already a call active", new Object[0]);
            return;
        }
        CharSequence appLabel = AppLabelProxy.Util.getAppLabel(this.mContext.getPackageManager(), str);
        CharSequence charSequence = str;
        if (appLabel != null) {
            charSequence = appLabel;
        }
        if (call.getState() == 4) {
            answerCallForAudioProcessing(call);
            call.setAudioProcessingRequestingApp(charSequence);
        } else if (call.getState() == 5) {
            setCallState(call, 12, "audio processing set by dialer request");
            call.setAudioProcessingRequestingApp(charSequence);
        }
    }

    public void exitBackgroundAudioProcessing(Call call, boolean z) {
        if (!this.mCalls.contains(call)) {
            Log.w(this, "Trying to exit audio processing on an untracked call", new Object[0]);
            return;
        }
        if (getActiveCall() != null) {
            Log.w(this, "Ignoring exit audio processing because there's already a call active", new Object[0]);
        }
        if (z) {
            setCallState(call, 13, "exitBackgroundAudioProcessing");
        } else {
            setCallState(call, 5, "exitBackgroundAudioProcessing");
        }
    }

    @VisibleForTesting
    public void deflectCall(Call call, Uri uri) {
        if (!this.mCalls.contains(call)) {
            Log.i(this, "Request to deflect a non-existent call %s", new Object[]{call});
        } else {
            call.deflect(uri);
        }
    }

    public boolean isSpeakerphoneAutoEnabledForVideoCalls(int i) {
        return VideoProfile.isVideo(i) && !this.mWiredHeadsetManager.isPluggedIn() && !this.mBluetoothRouteManager.isBluetoothAvailable() && isSpeakerEnabledForVideoCalls();
    }

    private boolean isSpeakerphoneEnabledForDock() {
        return (!this.mDockManager.isDocked() || this.mWiredHeadsetManager.isPluggedIn() || this.mBluetoothRouteManager.isBluetoothAvailable()) ? false : true;
    }

    private static boolean isSpeakerEnabledForVideoCalls() {
        return ((Integer) TelephonyProperties.videocall_audio_output().orElse(0)).intValue() == 0;
    }

    @VisibleForTesting
    public void rejectCall(Call call, boolean z, String str) {
        if (!this.mCalls.contains(call)) {
            Log.i(this, "Request to reject a non-existent call %s", new Object[]{call});
            return;
        }
        Iterator<CallsManagerListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onIncomingCallRejected(call, z, str);
        }
        call.reject(z, str);
    }

    @VisibleForTesting
    public void rejectCall(Call call, int i) {
        if (!this.mCalls.contains(call)) {
            Log.i(this, "Request to reject a non-existent call %s", new Object[]{call});
            return;
        }
        Iterator<CallsManagerListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onIncomingCallRejected(call, false, null);
        }
        call.reject(i);
    }

    @VisibleForTesting
    public void transferCall(Call call, Uri uri, boolean z) {
        if (!this.mCalls.contains(call)) {
            Log.i(this, "transferCall - Request to transfer a non-existent call %s", new Object[]{call});
        } else {
            call.transfer(uri, z);
        }
    }

    @VisibleForTesting
    public void transferCall(Call call, Call call2) {
        if (!this.mCalls.contains(call) || !this.mCalls.contains(call2)) {
            Log.i(this, "transferCall - Non-existent call %s or %s", new Object[]{call, call2});
        } else {
            call.transfer(call2);
        }
    }

    @VisibleForTesting
    public void playDtmfTone(Call call, char c) {
        if (!this.mCalls.contains(call)) {
            Log.i(this, "Request to play DTMF in a non-existent call %s", new Object[]{call});
        } else if (call.getState() == 6) {
            Log.i(this, "Request to play DTMF tone for held call %s", new Object[]{call.getId()});
        } else {
            call.playDtmfTone(c);
            this.mDtmfLocalTonePlayer.playTone(call, c);
        }
    }

    @VisibleForTesting
    public void stopDtmfTone(Call call) {
        if (!this.mCalls.contains(call)) {
            Log.i(this, "Request to stop DTMF in a non-existent call %s", new Object[]{call});
        } else {
            call.stopDtmfTone();
            this.mDtmfLocalTonePlayer.stopTone(call);
        }
    }

    void postDialContinue(Call call, boolean z) {
        if (!this.mCalls.contains(call)) {
            Log.i(this, "Request to continue post-dial string in a non-existent call %s", new Object[]{call});
        } else {
            call.postDialContinue(z);
        }
    }

    @VisibleForTesting
    /* JADX INFO: renamed from: disconnectCall, reason: merged with bridge method [inline-methods] */
    public void lambda$disconnectOtherCalls$17$CallsManager(Call call) {
        Log.v(this, "disconnectCall %s", new Object[]{call});
        if (!this.mCalls.contains(call)) {
            Log.w(this, "Unknown call (%s) asked to disconnect", new Object[]{call});
            return;
        }
        this.mLocallyDisconnectingCalls.add(call);
        int state = call.getState();
        call.disconnect();
        Iterator<CallsManagerListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onCallStateChanged(call, state, call.getState());
        }
        CompletableFuture<Call> completableFuture = this.mPendingCallConfirm;
        if (completableFuture != null && !completableFuture.isDone()) {
            this.mPendingCallConfirm.complete(null);
            this.mPendingCallConfirm = null;
        }
        CompletableFuture<Pair<Call, PhoneAccountHandle>> completableFuture2 = this.mPendingAccountSelection;
        if (completableFuture2 == null || completableFuture2.isDone()) {
            return;
        }
        this.mPendingAccountSelection.complete(null);
        this.mPendingAccountSelection = null;
    }

    private void disconnectOtherCalls(final PhoneAccountHandle phoneAccountHandle) {
        this.mCalls.stream().filter(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$wz3uuPI6qiwaZ_t8-XynzokzODw
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CallsManager.lambda$disconnectOtherCalls$16(phoneAccountHandle, (Call) obj);
            }
        }).forEach(new Consumer() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$fhaRAv9C9rmlH4huoewrNE-Qv8E
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$disconnectOtherCalls$17$CallsManager((Call) obj);
            }
        });
    }

    static /* synthetic */ boolean lambda$disconnectOtherCalls$16(PhoneAccountHandle phoneAccountHandle, Call call) {
        return (call.isEmergencyCall() || call.getTargetPhoneAccount().equals(phoneAccountHandle)) ? false : true;
    }

    @VisibleForTesting
    public void holdCall(Call call) {
        if (!this.mCalls.contains(call)) {
            Log.w(this, "Unknown call (%s) asked to be put on hold", new Object[]{call});
        } else {
            Log.d(this, "Putting call on hold: (%s)", new Object[]{call});
            call.hold();
        }
    }

    @VisibleForTesting
    public void unholdCall(Call call) {
        if (!this.mCalls.contains(call)) {
            Log.w(this, "Unknown call (%s) asked to be removed from hold", new Object[]{call});
            return;
        }
        if (getOutgoingCall() != null) {
            Log.w(this, "There is an outgoing call, so it is unable to unhold this call %s", new Object[]{call});
            return;
        }
        Call call2 = (Call) this.mConnectionSvrFocusMgr.getCurrentFocusCall();
        String id = null;
        if (call2 != null && !call2.isLocallyDisconnecting()) {
            id = call2.getId();
            if (canHold(call2)) {
                call2.hold("Swap to " + call.getId());
                Log.addEvent(call2, "SWAP", "To " + call.getId());
                Log.addEvent(call, "SWAP", "From " + call2.getId());
            } else if (!areFromSameSource(call2, call)) {
                if (!call2.isEmergencyCall()) {
                    call2.disconnect("Swap to " + call.getId());
                } else {
                    Log.w(this, "unholdCall: % is an emergency call, aborting swap to %s", new Object[]{call2.getId(), call.getId()});
                    return;
                }
            } else {
                call2.hold("Swap to " + call.getId());
            }
        }
        this.mConnectionSvrFocusMgr.requestFocus(call, new RequestCallback(new ActionUnHoldCall(call, id)));
    }

    @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
    public void onExtrasRemoved(Call call, int i, List<String> list) {
        if (i != 1) {
            return;
        }
        updateCanAddCall();
    }

    @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
    public void onExtrasChanged(Call call, int i, Bundle bundle) {
        if (i != 1) {
            return;
        }
        handleCallTechnologyChange(call);
        handleChildAddressChange(call);
        updateCanAddCall();
    }

    @VisibleForTesting
    public List<PhoneAccountHandle> constructPossiblePhoneAccounts(Uri uri, UserHandle userHandle, boolean z, boolean z2) {
        return constructPossiblePhoneAccounts(uri, userHandle, z, z2, false);
    }

    public List<PhoneAccountHandle> constructPossiblePhoneAccounts(Uri uri, UserHandle userHandle, boolean z, boolean z2, boolean z3) {
        if (uri == null) {
            return Collections.emptyList();
        }
        List<PhoneAccountHandle> callCapablePhoneAccounts = this.mPhoneAccountRegistrar.getCallCapablePhoneAccounts(uri.getScheme(), false, userHandle, (z ? 8 : 0) | (z3 ? 16384 : 0), z2 ? 0 : 128);
        if (this.mMaxNumberOfSimultaneouslyActiveSims < 0) {
            this.mMaxNumberOfSimultaneouslyActiveSims = getTelephonyManager().getMaxNumberOfSimultaneouslyActiveSims();
        }
        if (this.mMaxNumberOfSimultaneouslyActiveSims == 1) {
            List<PhoneAccountHandle> simPhoneAccountsOfCurrentUser = this.mPhoneAccountRegistrar.getSimPhoneAccountsOfCurrentUser();
            PhoneAccountHandle targetPhoneAccount = null;
            Iterator<Call> it = this.mCalls.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Call next = it.next();
                if (!next.isDisconnected() && !next.isNew() && simPhoneAccountsOfCurrentUser.contains(next.getTargetPhoneAccount())) {
                    targetPhoneAccount = next.getTargetPhoneAccount();
                    break;
                }
            }
            if (targetPhoneAccount != null) {
                simPhoneAccountsOfCurrentUser.remove(targetPhoneAccount);
                callCapablePhoneAccounts.removeAll(simPhoneAccountsOfCurrentUser);
            }
        }
        return callCapablePhoneAccounts;
    }

    private TelephonyManager getTelephonyManager() {
        return (TelephonyManager) this.mContext.getSystemService(TelephonyManager.class);
    }

    @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
    public void onExternalCallChanged(Call call, boolean z) {
        Log.v(this, "onConnectionPropertiesChanged: %b", new Object[]{Boolean.valueOf(z)});
        Iterator<CallsManagerListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onExternalCallChanged(call, z);
        }
    }

    private void handleCallTechnologyChange(Call call) {
        if (call.getExtras() == null || !call.getExtras().containsKey("android.telecom.extra.CALL_TECHNOLOGY_TYPE")) {
            return;
        }
        Integer num = sAnalyticsTechnologyMap.get(Integer.valueOf(call.getExtras().getInt("android.telecom.extra.CALL_TECHNOLOGY_TYPE")));
        if (num == null) {
            num = 16;
        }
        call.getAnalytics().addCallTechnology(num.intValue());
    }

    public void handleChildAddressChange(Call call) {
        if (call.getExtras() == null || !call.getExtras().containsKey("android.telecom.extra.CHILD_ADDRESS")) {
            return;
        }
        call.setViaNumber(call.getExtras().getString("android.telecom.extra.CHILD_ADDRESS"));
    }

    void mute(boolean z) {
        if (isInEmergencyCall() && z) {
            Log.i(this, "Refusing to turn on mute because we're in an emergency call", new Object[0]);
            z = false;
        }
        this.mCallAudioManager.mute(z);
    }

    void setAudioRoute(int i, String str) {
        this.mCallAudioManager.setAudioRoute(i, str);
    }

    void turnOnProximitySensor() {
        this.mProximitySensorManager.turnOn();
    }

    void turnOffProximitySensor(boolean z) {
        this.mProximitySensorManager.turnOff(z);
    }

    private boolean isRttSettingOn(PhoneAccountHandle phoneAccountHandle) {
        return (Settings.Secure.getInt(this.mContext.getContentResolver(), "rtt_calling_mode", 0) != 0) && !getCarrierConfigForPhoneAccount(phoneAccountHandle).getBoolean("ignore_rtt_mode_setting_bool", false);
    }

    private PersistableBundle getCarrierConfigForPhoneAccount(PhoneAccountHandle phoneAccountHandle) {
        PersistableBundle configForSubId = ((CarrierConfigManager) this.mContext.getSystemService(CarrierConfigManager.class)).getConfigForSubId(this.mPhoneAccountRegistrar.getSubscriptionIdForPhoneAccount(phoneAccountHandle));
        return configForSubId == null ? new PersistableBundle() : configForSubId;
    }

    void phoneAccountSelected(Call call, PhoneAccountHandle phoneAccountHandle, boolean z) {
        if (!this.mCalls.contains(call)) {
            Log.i(this, "Attempted to add account to unknown call %s", new Object[]{call});
            return;
        }
        if (z) {
            this.mPhoneAccountRegistrar.setUserSelectedOutgoingPhoneAccount(phoneAccountHandle, call.getInitiatingUser());
        }
        CompletableFuture<Pair<Call, PhoneAccountHandle>> completableFuture = this.mPendingAccountSelection;
        if (completableFuture != null) {
            completableFuture.complete(Pair.create(call, phoneAccountHandle));
            this.mPendingAccountSelection = null;
        }
    }

    @VisibleForTesting
    public void onCallAudioStateChanged(CallAudioState callAudioState, CallAudioState callAudioState2) {
        Log.v(this, "onAudioStateChanged, audioState: %s -> %s", new Object[]{callAudioState, callAudioState2});
        Iterator<CallsManagerListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onCallAudioStateChanged(callAudioState, callAudioState2);
        }
    }

    @VisibleForTesting
    public void onDisconnectedTonePlaying(boolean z) {
        Object[] objArr = new Object[1];
        objArr[0] = z ? "started" : "stopped";
        Log.v(this, "onDisconnectedTonePlaying, %s", objArr);
        Iterator<CallsManagerListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onDisconnectedTonePlaying(z);
        }
    }

    void markCallAsRinging(Call call) {
        setCallState(call, 4, "ringing set explicitly");
    }

    void markCallAsDialing(Call call) {
        setCallState(call, 3, "dialing set explicitly");
        maybeMoveToSpeakerPhone(call);
        maybeTurnOffMute(call);
        ensureCallAudible();
    }

    void markCallAsPulling(Call call) {
        setCallState(call, 10, "pulling set explicitly");
        maybeMoveToSpeakerPhone(call);
    }

    boolean holdActiveCallForNewCall(Call call) {
        Call call2 = (Call) this.mConnectionSvrFocusMgr.getCurrentFocusCall();
        if (call2 != null && call2 != call) {
            if (canHold(call2)) {
                call2.hold();
                return true;
            }
            if (supportsHold(call2) && areFromSameSource(call2, call)) {
                Call heldCallByConnectionService = getHeldCallByConnectionService(call.getTargetPhoneAccount());
                if (heldCallByConnectionService != null) {
                    heldCallByConnectionService.disconnect();
                    Log.i(this, "holdActiveCallForNewCall: Disconnect held call %s before holding active call %s.", new Object[]{heldCallByConnectionService.getId(), call2.getId()});
                }
                Log.i(this, "holdActiveCallForNewCall: Holding active %s before making %s active.", new Object[]{call2.getId(), call.getId()});
                call2.hold();
                return true;
            }
            if (!areFromSameSource(call2, call)) {
                Log.i(this, "holdActiveCallForNewCall: disconnecting %s so that %s can be made active.", new Object[]{call2.getId(), call.getId()});
                if (!call2.isEmergencyCall()) {
                    call2.disconnect();
                } else {
                    Log.w(this, "holdActiveCallForNewCall: rejecting incoming call %s as the active call is an emergency call and it cannot be held.", new Object[]{call.getId()});
                    call.reject(false, "", "active emergency call can't be held");
                }
            }
        }
        return false;
    }

    @VisibleForTesting
    public void markCallAsActive(Call call) {
        if (call.isSelfManaged()) {
            holdActiveCallForNewCall(call);
            this.mConnectionSvrFocusMgr.requestFocus(call, new RequestCallback(new ActionSetCallState(call, 5, "active set explicitly for self-managed")));
        } else {
            if (this.mPendingAudioProcessingCall == call) {
                if (this.mCalls.contains(call)) {
                    setCallState(call, 12, "active set explicitly");
                } else {
                    call.setState(12, "active set explicitly and adding");
                    addCall(call);
                }
                this.mPendingAudioProcessingCall = null;
                return;
            }
            setCallState(call, 5, "active set explicitly");
            maybeMoveToSpeakerPhone(call);
            ensureCallAudible();
        }
    }

    @VisibleForTesting
    public void markCallAsOnHold(Call call) {
        setCallState(call, 6, "on-hold set explicitly");
    }

    void markCallAsDisconnected(Call call, DisconnectCause disconnectCause) {
        int state = call.getState();
        if (call.getState() == 13 && disconnectCause.getCode() == 3) {
            call.setOverrideDisconnectCauseCode(new DisconnectCause(5));
        }
        call.setDisconnectCause(disconnectCause);
        setCallState(call, 7, "disconnected set explicitly");
        if (state == 0 && disconnectCause.getCode() == 5) {
            Log.i(this, "markCallAsDisconnected: logging missed call ", new Object[0]);
            this.mCallLogManager.logCall(call, 3, true, (CallFilteringResult) null);
        }
    }

    void markCallAsRemoved(final Call call) {
        this.mInCallController.getBindingFuture().thenRunAsync(new Runnable() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$AG5fnPM-PwA_01mxLXCLPc9I6-w
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$markCallAsRemoved$18$CallsManager(call);
            }
        }, (Executor) new LoggedHandlerExecutor(this.mHandler, "CM.mCAR", this.mLock));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$markCallAsRemoved$18, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$markCallAsRemoved$18$CallsManager(Call call) {
        call.maybeCleanupHandover();
        removeCall(call);
        Call possiblyHeldForegroundCall = this.mCallAudioManager.getPossiblyHeldForegroundCall();
        if (this.mLocallyDisconnectingCalls.contains(call)) {
            boolean zIsDisconnectingChildCall = call.isDisconnectingChildCall();
            Log.v(this, "markCallAsRemoved: isDisconnectingChildCall = " + zIsDisconnectingChildCall + "call -> %s", new Object[]{call});
            this.mLocallyDisconnectingCalls.remove(call);
            if (zIsDisconnectingChildCall || possiblyHeldForegroundCall == null || possiblyHeldForegroundCall.getState() != 6) {
                return;
            }
            possiblyHeldForegroundCall.unhold();
            return;
        }
        if (possiblyHeldForegroundCall == null || possiblyHeldForegroundCall.can(2) || possiblyHeldForegroundCall.getState() != 6) {
            return;
        }
        Log.i(this, "Auto-unholding held foreground call (call doesn't support hold)", new Object[0]);
        possiblyHeldForegroundCall.unhold();
    }

    void markCallDisconnectedDueToSelfManagedCall(Call call) {
        CharSequence string;
        Call activeCall = getActiveCall();
        if (activeCall == null) {
            string = this.mContext.getText(R.string.cant_call_due_to_ongoing_unknown_call);
        } else {
            string = this.mContext.getString(R.string.cant_call_due_to_ongoing_call, activeCall.getTargetPhoneAccountLabel());
        }
        markCallAsDisconnected(call, new DisconnectCause(1, string, string, "Ongoing call in another app."));
        markCallAsRemoved(call);
    }

    void handleConnectionServiceDeath(ConnectionServiceWrapper connectionServiceWrapper) {
        if (connectionServiceWrapper != null) {
            Log.i(this, "handleConnectionServiceDeath: service %s died", new Object[]{connectionServiceWrapper});
            for (Call call : this.mCalls) {
                if (call.getConnectionService() == connectionServiceWrapper) {
                    if (call.getState() != 7) {
                        markCallAsDisconnected(call, new DisconnectCause(1, null, null, "CS_DEATH", 27));
                    }
                    markCallAsRemoved(call);
                }
            }
        }
    }

    boolean hasAnyCalls() {
        if (this.mCalls.isEmpty()) {
            return false;
        }
        Iterator<Call> it = this.mCalls.iterator();
        while (it.hasNext()) {
            if (!it.next().isExternalCall()) {
                return true;
            }
        }
        return false;
    }

    boolean hasRingingCall() {
        return getFirstCallWithState(4, 11) != null;
    }

    boolean hasRingingOrSimulatedRingingCall() {
        return getFirstCallWithState(13, 4, 11) != null;
    }

    @VisibleForTesting
    public boolean onMediaButton(int i) {
        if (hasAnyCalls()) {
            Call firstCallWithState = getFirstCallWithState(4, 13);
            if (1 == i) {
                if (firstCallWithState == null) {
                    Call firstCallWithState2 = getFirstCallWithState(5);
                    Call firstCallWithState3 = getFirstCallWithState(6);
                    if (firstCallWithState2 != null && firstCallWithState3 != null) {
                        Log.addEvent(firstCallWithState3, "INFO", "two calls, media btn short press - switch call.");
                        unholdCall(firstCallWithState3);
                        return true;
                    }
                    Call firstCallWithState4 = getFirstCallWithState(4, 3, 10, 5, 6);
                    Log.addEvent(firstCallWithState4, "INFO", "media btn short press - end call.");
                    if (firstCallWithState4 != null) {
                        lambda$disconnectOtherCalls$17(firstCallWithState4);
                        return true;
                    }
                } else {
                    answerCall(firstCallWithState, 0);
                    return true;
                }
            } else if (2 == i) {
                if (firstCallWithState != null) {
                    Log.addEvent(getForegroundCall(), "INFO", "media btn long press - reject");
                    firstCallWithState.reject(false, null);
                } else {
                    Call firstCallWithState5 = getFirstCallWithState(5);
                    Call firstCallWithState6 = getFirstCallWithState(6);
                    if (firstCallWithState5 != null && firstCallWithState6 != null) {
                        Log.addEvent(firstCallWithState5, "INFO", "two calls, media btn long press - end current call.");
                        lambda$disconnectOtherCalls$17(firstCallWithState5);
                        return true;
                    }
                    Log.addEvent(getForegroundCall(), "INFO", "media btn long press - mute");
                    this.mCallAudioManager.toggleMute();
                }
                return true;
            }
        }
        return false;
    }

    @VisibleForTesting
    public boolean canAddCall() {
        if (!(Settings.Global.getInt(this.mContext.getContentResolver(), "device_provisioned", 0) != 0)) {
            Log.d("CallsManager", "Device not provisioned, canAddCall is false.", new Object[0]);
            return false;
        }
        if (getFirstCallWithState(OUTGOING_CALL_STATES) != null) {
            return false;
        }
        int i = 0;
        for (Call call : this.mCalls) {
            if (call.isEmergencyCall()) {
                return false;
            }
            if (!call.isExternalCall()) {
                if (call.getParentCall() == null) {
                    i++;
                }
                Bundle extras = call.getExtras();
                if ((extras != null && extras.getBoolean("android.telecom.extra.DISABLE_ADD_CALL", false)) || i >= 2) {
                    return false;
                }
            }
        }
        return true;
    }

    @VisibleForTesting
    public Call getRingingOrSimulatedRingingCall() {
        return getFirstCallWithState(4, 11, 13);
    }

    public Call getActiveCall() {
        return getFirstCallWithState(5);
    }

    @VisibleForTesting
    public Call getHeldCall() {
        return getFirstCallWithState(6);
    }

    public Call getHeldCallByConnectionService(final PhoneAccountHandle phoneAccountHandle) {
        Optional<Call> optionalFindFirst = this.mCalls.stream().filter(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$sQyepX4w1O7F82UTZOpITHVCvnI
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CallsManager.lambda$getHeldCallByConnectionService$19(phoneAccountHandle, (Call) obj);
            }
        }).findFirst();
        if (optionalFindFirst.isPresent()) {
            return optionalFindFirst.get();
        }
        return null;
    }

    static /* synthetic */ boolean lambda$getHeldCallByConnectionService$19(PhoneAccountHandle phoneAccountHandle, Call call) {
        return PhoneAccountHandle.areFromSamePackage(call.getTargetPhoneAccount(), phoneAccountHandle) && call.getParentCall() == null && call.getState() == 6;
    }

    @VisibleForTesting
    public int getNumHeldCalls() {
        int i = 0;
        for (Call call : this.mCalls) {
            if (call.getParentCall() == null && call.getState() == 6) {
                i++;
            }
        }
        return i;
    }

    @VisibleForTesting
    public Call getOutgoingCall() {
        return getFirstCallWithState(OUTGOING_CALL_STATES);
    }

    @VisibleForTesting
    public Call getFirstCallWithState(int... iArr) {
        return getFirstCallWithState(null, iArr);
    }

    @VisibleForTesting
    public PhoneNumberUtilsAdapter getPhoneNumberUtilsAdapter() {
        return this.mPhoneNumberUtilsAdapter;
    }

    @VisibleForTesting
    public CompletableFuture<Call> getLatestPostSelectionProcessingFuture() {
        return this.mLatestPostSelectionProcessingFuture;
    }

    @VisibleForTesting
    public CompletableFuture getLatestPreAccountSelectionFuture() {
        return this.mLatestPreAccountSelectionFuture;
    }

    Call getFirstCallWithState(Call call, int... iArr) {
        for (int i : iArr) {
            Call foregroundCall = getForegroundCall();
            if (foregroundCall != null && foregroundCall.getState() == i) {
                return foregroundCall;
            }
            for (Call call2 : this.mCalls) {
                if (!Objects.equals(call, call2) && call2.getParentCall() == null && !call2.isExternalCall() && i == call2.getState()) {
                    return call2;
                }
            }
        }
        return null;
    }

    Call createConferenceCall(String str, PhoneAccountHandle phoneAccountHandle, ParcelableConference parcelableConference) {
        long connectTimeMillis = parcelableConference.getConnectTimeMillis() == 0 ? 0L : parcelableConference.getConnectTimeMillis();
        long connectElapsedTimeMillis = parcelableConference.getConnectElapsedTimeMillis() == 0 ? 0L : parcelableConference.getConnectElapsedTimeMillis();
        Call call = new Call(str, this.mContext, this, this.mLock, this.mConnectionServiceRepository, this.mPhoneNumberUtilsAdapter, null, null, this.mPhoneAccountRegistrar.getSimCallManagerFromHandle(phoneAccountHandle, this.mCurrentUserHandle), phoneAccountHandle, Call.getRemappedCallDirection(parcelableConference.getCallDirection()), false, true, connectTimeMillis, connectElapsedTimeMillis, this.mClockProxy, this.mToastFactory);
        setCallState(call, Call.getStateFromConnectionState(parcelableConference.getState()), "new conference call");
        call.setHandle(parcelableConference.getHandle(), parcelableConference.getHandlePresentation());
        call.setConnectionCapabilities(parcelableConference.getConnectionCapabilities());
        call.setConnectionProperties(parcelableConference.getConnectionProperties());
        call.setVideoState(parcelableConference.getVideoState());
        call.setVideoProvider(parcelableConference.getVideoProvider());
        call.setStatusHints(parcelableConference.getStatusHints());
        call.putExtras(1, parcelableConference.getExtras());
        Bundle extras = parcelableConference.getExtras();
        if (extras != null && extras.containsKey("android.telecom.extra.ORIGINAL_CONNECTION_ID")) {
            call.setOriginalConnectionId(extras.getString("android.telecom.extra.ORIGINAL_CONNECTION_ID"));
        }
        call.addListener(this);
        addCall(call);
        return call;
    }

    int getCallState() {
        return this.mPhoneStateBroadcaster.getCallState();
    }

    @VisibleForTesting
    public PhoneAccountRegistrar getPhoneAccountRegistrar() {
        return this.mPhoneAccountRegistrar;
    }

    DisconnectedCallNotifier getDisconnectedCallNotifier() {
        return this.mDisconnectedCallNotifier;
    }

    MissedCallNotifier getMissedCallNotifier() {
        return this.mMissedCallNotifier;
    }

    IncomingCallNotifier getIncomingCallNotifier() {
        return this.mIncomingCallNotifier;
    }

    private void rejectCallAndLog(Call call, CallFilteringResult callFilteringResult) {
        if (call.getConnectionService() != null) {
            call.reject(false, null);
        } else {
            Log.i(this, "rejectCallAndLog - call already destroyed.", new Object[0]);
        }
        this.mCallLogManager.logCall(call, 3, true, callFilteringResult);
    }

    @VisibleForTesting
    public void addCall(Call call) {
        Trace.beginSection("addCall");
        Log.v(this, "addCall(%s)", new Object[]{call});
        call.addListener(this);
        this.mCalls.add(call);
        call.getIntentExtras().putLong("android.telecom.extra.CALL_TELECOM_ROUTING_END_TIME_MILLIS", SystemClock.elapsedRealtime());
        updateCanAddCall();
        updateHasActiveRttCall();
        updateExternalCallCanPullSupport();
        Iterator<CallsManagerListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onCallAdded(call);
        }
        Trace.endSection();
    }

    @VisibleForTesting
    public void removeCall(Call call) {
        Trace.beginSection("removeCall");
        boolean z = true;
        Log.v(this, "removeCall(%s)", new Object[]{call});
        call.setParentAndChildCall(null);
        call.removeListener(this);
        call.clearConnectionService();
        if (this.mCalls.contains(call)) {
            this.mCalls.remove(call);
        } else {
            z = false;
        }
        call.destroy();
        updateExternalCallCanPullSupport();
        if (z) {
            updateCanAddCall();
            updateHasActiveRttCall();
            Iterator<CallsManagerListener> it = this.mListeners.iterator();
            while (it.hasNext()) {
                it.next().onCallRemoved(call);
            }
        }
        Trace.endSection();
    }

    private void updateHasActiveRttCall() {
        boolean zHasActiveRttCall = hasActiveRttCall();
        boolean z = this.mHasActiveRttCall;
        if (zHasActiveRttCall != z) {
            Log.i(this, "updateHasActiveRttCall %s -> %s", new Object[]{Boolean.valueOf(z), Boolean.valueOf(zHasActiveRttCall)});
            AudioManager.setRttEnabled(zHasActiveRttCall);
            this.mHasActiveRttCall = zHasActiveRttCall;
        }
    }

    private boolean hasActiveRttCall() {
        for (Call call : this.mCalls) {
            if (call.isActive() && call.isRttCall()) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCallState(Call call, int i, String str) {
        if (call == null) {
            return;
        }
        int state = call.getState();
        Log.i(this, "setCallState %s -> %s, call: %s", new Object[]{CallState.toString(call.getParcelableCallState()), CallState.toString(i), call});
        if (i != state) {
            if (i == 6 && call.isDtmfTonePlaying()) {
                stopDtmfTone(call);
            }
            if (call.setState(i, str)) {
                if (state != 12 && i == 7) {
                    maybeSendPostCallScreenIntent(call);
                }
                maybeShowErrorDialogOnDisconnect(call);
                Trace.beginSection("onCallStateChanged");
                maybeHandleHandover(call, i);
                if (this.mCalls.contains(call)) {
                    updateCanAddCall();
                    updateHasActiveRttCall();
                    Iterator<CallsManagerListener> it = this.mListeners.iterator();
                    while (it.hasNext()) {
                        it.next().onCallStateChanged(call, state, i);
                    }
                }
                Trace.endSection();
                return;
            }
            Log.i(this, "failed in setting the state to new state", new Object[0]);
        }
    }

    private void maybeHandleHandover(Call call, int i) {
        if (call.getHandoverSourceCall() != null) {
            if (call.getHandoverState() == 2) {
                if (i == 5) {
                    Log.i(this, "setCallState: handover to accepted", new Object[0]);
                    acceptHandoverTo(call);
                    return;
                } else {
                    if (i == 7) {
                        Log.i(this, "setCallState: handover to rejected", new Object[0]);
                        rejectHandoverTo(call);
                        return;
                    }
                    return;
                }
            }
            return;
        }
        if (call.getHandoverDestinationCall() == null || i != 7) {
            return;
        }
        int handoverState = call.getHandoverState();
        if (handoverState == 3) {
            Log.i(this, "setCallState: disconnect before handover accepted", new Object[0]);
            call.getHandoverDestinationCall().sendCallEvent("android.telecom.event.HANDOVER_SOURCE_DISCONNECTED", null);
        } else if (handoverState == 4) {
            Log.i(this, "setCallState: handover from complete", new Object[0]);
            completeHandoverFrom(call);
        }
    }

    private void completeHandoverFrom(Call call) {
        Call handoverDestinationCall = call.getHandoverDestinationCall();
        Log.addEvent(handoverDestinationCall, "HANDOVER_COMPLETE", "from=%s, to=%s", new Object[]{call.getId(), handoverDestinationCall.getId()});
        Log.addEvent(call, "HANDOVER_COMPLETE", "from=%s, to=%s", new Object[]{call.getId(), handoverDestinationCall.getId()});
        call.onConnectionEvent("android.telecom.event.HANDOVER_COMPLETE", null);
        call.onHandoverComplete();
        handoverDestinationCall.sendCallEvent("android.telecom.event.HANDOVER_COMPLETE", null);
        handoverDestinationCall.onHandoverComplete();
        answerCall(handoverDestinationCall, handoverDestinationCall.getVideoState());
        call.markFinishedHandoverStateAndCleanup(5);
        if (handoverDestinationCall.isSelfManaged()) {
            disconnectOtherCalls(handoverDestinationCall.getTargetPhoneAccount());
        }
    }

    private void rejectHandoverTo(Call call) {
        Call handoverSourceCall = call.getHandoverSourceCall();
        Log.i(this, "rejectHandoverTo: from=%s, to=%s", new Object[]{handoverSourceCall.getId(), call.getId()});
        Log.addEvent(handoverSourceCall, "HANDOVER_FAILED", "from=%s, to=%s, rejected", new Object[]{call.getId(), handoverSourceCall.getId()});
        Log.addEvent(call, "HANDOVER_FAILED", "from=%s, to=%s, rejected", new Object[]{call.getId(), handoverSourceCall.getId()});
        handoverSourceCall.onConnectionEvent("android.telecom.event.HANDOVER_FAILED", null);
        handoverSourceCall.onHandoverFailed(3);
        if (call.getConnectionService() != null) {
            call.sendCallEvent("android.telecom.event.HANDOVER_FAILED", null);
            call.getConnectionService().handoverFailed(call, 3);
        }
        call.markFinishedHandoverStateAndCleanup(6);
    }

    private void acceptHandoverTo(Call call) {
        Call handoverSourceCall = call.getHandoverSourceCall();
        Log.i(this, "acceptHandoverTo: from=%s, to=%s", new Object[]{handoverSourceCall.getId(), call.getId()});
        call.setHandoverState(4);
        call.onHandoverComplete();
        handoverSourceCall.setHandoverState(4);
        handoverSourceCall.onHandoverComplete();
        Log.addEvent(call, "ACCEPT_HANDOVER", "from=%s, to=%s", new Object[]{handoverSourceCall.getId(), call.getId()});
        Log.addEvent(handoverSourceCall, "ACCEPT_HANDOVER", "from=%s, to=%s", new Object[]{handoverSourceCall.getId(), call.getId()});
        lambda$disconnectOtherCalls$17(handoverSourceCall);
        if (call.isSelfManaged()) {
            disconnectOtherCalls(call.getTargetPhoneAccount());
        }
    }

    private void updateCanAddCall() {
        boolean zCanAddCall = canAddCall();
        if (zCanAddCall != this.mCanAddCall) {
            this.mCanAddCall = zCanAddCall;
            Iterator<CallsManagerListener> it = this.mListeners.iterator();
            while (it.hasNext()) {
                it.next().onCanAddCallChanged(this.mCanAddCall);
            }
        }
    }

    private boolean isPotentialMMICode(Uri uri) {
        return (uri == null || uri.getSchemeSpecificPart() == null || !uri.getSchemeSpecificPart().contains("#")) ? false : true;
    }

    private boolean isPotentialInCallMMICode(Uri uri) {
        if (uri == null || uri.getSchemeSpecificPart() == null || uri.getScheme() == null || !uri.getScheme().equals("tel")) {
            return false;
        }
        String schemeSpecificPart = uri.getSchemeSpecificPart();
        return schemeSpecificPart.equals("0") || (schemeSpecificPart.startsWith("1") && schemeSpecificPart.length() <= 2) || ((schemeSpecificPart.startsWith("2") && schemeSpecificPart.length() <= 2) || schemeSpecificPart.equals("3") || schemeSpecificPart.equals("4") || schemeSpecificPart.equals("5"));
    }

    @VisibleForTesting
    public int getNumCallsWithState(boolean z, Call call, PhoneAccountHandle phoneAccountHandle, int... iArr) {
        return getNumCallsWithState(z ? 1 : 2, call, phoneAccountHandle, iArr);
    }

    @VisibleForTesting
    public int getNumCallsWithState(int i, final Call call, final PhoneAccountHandle phoneAccountHandle, int... iArr) {
        final Set set = (Set) IntStream.of(iArr).boxed().collect(Collectors.toSet());
        Stream<Call> streamFilter = this.mCalls.stream().filter(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$02zLGs4bDiAl3c2f4qH5Rmic2cA
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CallsManager.lambda$getNumCallsWithState$20(set, (Call) obj);
            }
        });
        if (i == 2) {
            streamFilter = streamFilter.filter(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$YQRxyFrt1D-JqL7K3jodhtGTduQ
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return CallsManager.lambda$getNumCallsWithState$21((Call) obj);
                }
            });
        } else if (i == 1) {
            streamFilter = streamFilter.filter(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$N3mn2YMOjtEEOLuaNEISuNzd8eA
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((Call) obj).isSelfManaged();
                }
            });
        }
        if (call != null) {
            streamFilter = streamFilter.filter(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$fDSmhvdTKJ01uyeuLJsrRy8raCI
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return CallsManager.lambda$getNumCallsWithState$23(call, (Call) obj);
                }
            });
        }
        if (phoneAccountHandle != null) {
            streamFilter = streamFilter.filter(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$ziXhuY5KmeOEmx4MlBTMFMiU8sc
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return phoneAccountHandle.equals(((Call) obj).getTargetPhoneAccount());
                }
            });
        }
        return (int) streamFilter.count();
    }

    static /* synthetic */ boolean lambda$getNumCallsWithState$20(Set set, Call call) {
        return set.contains(Integer.valueOf(call.getState())) && call.getParentCall() == null && !call.isExternalCall();
    }

    static /* synthetic */ boolean lambda$getNumCallsWithState$21(Call call) {
        return !call.isSelfManaged();
    }

    private boolean hasMaximumLiveCalls(Call call) {
        return 1 <= getNumCallsWithState(3, call, (PhoneAccountHandle) null, LIVE_CALL_STATES);
    }

    private boolean hasMaximumManagedLiveCalls(Call call) {
        return 1 <= getNumCallsWithState(false, call, (PhoneAccountHandle) null, LIVE_CALL_STATES);
    }

    private boolean hasMaximumSelfManagedCalls(Call call, PhoneAccountHandle phoneAccountHandle) {
        return 10 <= getNumCallsWithState(true, call, phoneAccountHandle, ANY_CALL_STATE);
    }

    private boolean hasMaximumManagedHoldingCalls(Call call) {
        return 1 <= getNumCallsWithState(false, call, (PhoneAccountHandle) null, 6);
    }

    private boolean hasMaximumManagedRingingCalls(Call call) {
        return 1 <= getNumCallsWithState(false, call, (PhoneAccountHandle) null, 4, 11);
    }

    private boolean hasMaximumSelfManagedRingingCalls(Call call, PhoneAccountHandle phoneAccountHandle) {
        return 1 <= getNumCallsWithState(true, call, phoneAccountHandle, 4, 11);
    }

    private boolean hasMaximumOutgoingCalls(Call call) {
        return 1 <= getNumCallsWithState(3, call, (PhoneAccountHandle) null, OUTGOING_CALL_STATES);
    }

    private boolean hasMaximumManagedOutgoingCalls(Call call) {
        return 1 <= getNumCallsWithState(false, call, (PhoneAccountHandle) null, OUTGOING_CALL_STATES);
    }

    private boolean hasMaximumManagedDialingCalls(Call call) {
        return 1 <= getNumCallsWithState(false, call, (PhoneAccountHandle) null, 3, 10);
    }

    public boolean hasUnholdableCallsForOtherConnectionService(PhoneAccountHandle phoneAccountHandle) {
        return getNumUnholdableCallsForOtherConnectionService(phoneAccountHandle) > 0;
    }

    public int getNumUnholdableCallsForOtherConnectionService(final PhoneAccountHandle phoneAccountHandle) {
        return (int) this.mCalls.stream().filter(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$V77qcOcj5wH_hnwUtAHPy9YIosA
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$getNumUnholdableCallsForOtherConnectionService$25$CallsManager(phoneAccountHandle, (Call) obj);
            }
        }).count();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$getNumUnholdableCallsForOtherConnectionService$25, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$getNumUnholdableCallsForOtherConnectionService$25$CallsManager(PhoneAccountHandle phoneAccountHandle, Call call) {
        return (phoneAccountHandle.getComponentName().equals(call.getTargetPhoneAccount().getComponentName()) || call.getParentCall() != null || call.isExternalCall() || canHold(call)) ? false : true;
    }

    public boolean hasSelfManagedCalls() {
        return this.mCalls.stream().filter(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$yFn1BFJ0ed2Eud4Tu4xXHBbLFkw
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((Call) obj).isSelfManaged();
            }
        }).count() > 0;
    }

    public boolean hasOngoingCalls() {
        return getNumCallsWithState(3, (Call) null, (PhoneAccountHandle) null, ONGOING_CALL_STATES) > 0;
    }

    public boolean hasOngoingManagedCalls() {
        return getNumCallsWithState(2, (Call) null, (PhoneAccountHandle) null, ONGOING_CALL_STATES) > 0;
    }

    public boolean shouldShowSystemIncomingCallUi(Call call) {
        return call.isIncoming() && call.isSelfManaged() && hasUnholdableCallsForOtherConnectionService(call.getTargetPhoneAccount()) && call.getHandoverSourceCall() == null;
    }

    @VisibleForTesting
    public boolean makeRoomForOutgoingEmergencyCall(Call call) {
        if (hasRingingOrSimulatedRingingCall()) {
            Call ringingOrSimulatedRingingCall = getRingingOrSimulatedRingingCall();
            ringingOrSimulatedRingingCall.getAnalytics().setCallIsAdditional(true);
            ringingOrSimulatedRingingCall.getAnalytics().setCallIsInterrupted(true);
            if (ringingOrSimulatedRingingCall.getState() == 13) {
                if (!ringingOrSimulatedRingingCall.hasGoneActiveBefore()) {
                    ringingOrSimulatedRingingCall.disconnect("emergency call dialed during simulated ringing after screen.");
                } else {
                    ringingOrSimulatedRingingCall.reject(false, null, "emergency call dialed during simulated ringing.");
                }
            } else {
                ringingOrSimulatedRingingCall.setOverrideDisconnectCauseCode(new DisconnectCause(5));
                ringingOrSimulatedRingingCall.reject(false, null, "emergency call dialed during ringing.");
            }
        }
        if (!hasMaximumLiveCalls(call)) {
            return true;
        }
        Call firstCallWithState = getFirstCallWithState(LIVE_CALL_STATES);
        Log.i(this, "makeRoomForOutgoingEmergencyCall call = " + call + " livecall = " + firstCallWithState, new Object[0]);
        if (call == firstCallWithState) {
            return true;
        }
        if (hasMaximumOutgoingCalls(call)) {
            Call firstCallWithState2 = getFirstCallWithState(OUTGOING_CALL_STATES);
            if (!firstCallWithState2.isEmergencyCall()) {
                call.getAnalytics().setCallIsAdditional(true);
                firstCallWithState2.getAnalytics().setCallIsInterrupted(true);
                firstCallWithState2.disconnect("Disconnecting dialing call in favor of new dialing emergency call.");
                return true;
            }
            if (firstCallWithState2.getState() != 2) {
                return false;
            }
            call.getAnalytics().setCallIsAdditional(true);
            firstCallWithState2.getAnalytics().setCallIsInterrupted(true);
            firstCallWithState2.disconnect("Disconnecting call in SELECT_PHONE_ACCOUNT in favor of new outgoing call.");
            return true;
        }
        if (firstCallWithState.getState() == 12) {
            call.getAnalytics().setCallIsAdditional(true);
            firstCallWithState.getAnalytics().setCallIsInterrupted(true);
            firstCallWithState.disconnect("disconnecting audio processing call for emergency");
            return true;
        }
        if (hasMaximumManagedHoldingCalls(call) && !canHold(firstCallWithState)) {
            call.getAnalytics().setCallIsAdditional(true);
            firstCallWithState.getAnalytics().setCallIsInterrupted(true);
            firstCallWithState.disconnect("disconnecting to make room for emergency call " + call.getId());
            return true;
        }
        PhoneAccountHandle targetPhoneAccount = firstCallWithState.getTargetPhoneAccount();
        if (targetPhoneAccount == null && firstCallWithState.isConference() && !firstCallWithState.getChildCalls().isEmpty()) {
            targetPhoneAccount = getFirstChildPhoneAccount(firstCallWithState);
            Log.i(this, "makeRoomForOutgoingEmergencyCall: using child call PhoneAccount = " + targetPhoneAccount, new Object[0]);
        }
        if (firstCallWithState.getTargetPhoneAccount() != null) {
            if ((this.mPhoneAccountRegistrar.getPhoneAccountUnchecked(firstCallWithState.getTargetPhoneAccount()).getCapabilities() & 16) == 0) {
                firstCallWithState.setOverrideDisconnectCauseCode(new DisconnectCause(2, "REASON_EMERGENCY_CALL_PLACED"));
                firstCallWithState.disconnect("outgoing call does not support emergency calls, disconnecting.");
            }
            return true;
        }
        if (PhoneAccountHandle.areFromSamePackage(targetPhoneAccount, call.getTargetPhoneAccount())) {
            Log.i(this, "makeRoomForOutgoingEmergencyCall: phoneAccount matches.", new Object[0]);
            call.getAnalytics().setCallIsAdditional(true);
            firstCallWithState.getAnalytics().setCallIsInterrupted(true);
            return true;
        }
        if (call.getTargetPhoneAccount() == null) {
            return true;
        }
        if (!canHold(firstCallWithState)) {
            return false;
        }
        Log.i(this, "makeRoomForOutgoingEmergencyCall: holding live call.", new Object[0]);
        call.getAnalytics().setCallIsAdditional(true);
        firstCallWithState.getAnalytics().setCallIsInterrupted(true);
        firstCallWithState.hold("calling " + call.getId());
        return true;
    }

    private boolean makeRoomForOutgoingCall(Call call) {
        if (!hasMaximumLiveCalls(call)) {
            return true;
        }
        Call firstCallWithState = getFirstCallWithState(LIVE_CALL_STATES);
        Log.i(this, "makeRoomForOutgoingCall call = " + call + " livecall = " + firstCallWithState, new Object[0]);
        if (call == firstCallWithState) {
            return true;
        }
        if (hasMaximumOutgoingCalls(call)) {
            Call firstCallWithState2 = getFirstCallWithState(OUTGOING_CALL_STATES);
            if (firstCallWithState2.getState() != 2) {
                return false;
            }
            call.getAnalytics().setCallIsAdditional(true);
            firstCallWithState2.getAnalytics().setCallIsInterrupted(true);
            firstCallWithState2.disconnect("Disconnecting call in SELECT_PHONE_ACCOUNT in favor of new outgoing call.");
            return true;
        }
        PhoneAccountHandle targetPhoneAccount = firstCallWithState.getTargetPhoneAccount();
        if (targetPhoneAccount == null && firstCallWithState.isConference() && !firstCallWithState.getChildCalls().isEmpty()) {
            targetPhoneAccount = getFirstChildPhoneAccount(firstCallWithState);
            Log.i(this, "makeRoomForOutgoingCall: using child call PhoneAccount = " + targetPhoneAccount, new Object[0]);
        }
        if (PhoneAccountHandle.areFromSamePackage(targetPhoneAccount, call.getTargetPhoneAccount())) {
            Log.i(this, "makeRoomForOutgoingCall: phoneAccount matches.", new Object[0]);
            call.getAnalytics().setCallIsAdditional(true);
            firstCallWithState.getAnalytics().setCallIsInterrupted(true);
            return true;
        }
        if (call.getTargetPhoneAccount() == null) {
            return true;
        }
        if (!canHold(firstCallWithState)) {
            return false;
        }
        Log.i(this, "makeRoomForOutgoingCall: holding live call.", new Object[0]);
        call.getAnalytics().setCallIsAdditional(true);
        firstCallWithState.getAnalytics().setCallIsInterrupted(true);
        firstCallWithState.hold("calling " + call.getId());
        return true;
    }

    private PhoneAccountHandle getFirstChildPhoneAccount(Call call) {
        Iterator<Call> it = call.getChildCalls().iterator();
        while (it.hasNext()) {
            PhoneAccountHandle targetPhoneAccount = it.next().getTargetPhoneAccount();
            if (targetPhoneAccount != null) {
                return targetPhoneAccount;
            }
        }
        return null;
    }

    private void maybeMoveToSpeakerPhone(Call call) {
        if (!(call.isHandoverInProgress() && call.getState() == 3) && call.getStartWithSpeakerphoneOn()) {
            setAudioRoute(8, null);
            call.setStartWithSpeakerphoneOn(false);
        }
    }

    private void maybeTurnOffMute(Call call) {
        if (call.isEmergencyCall()) {
            mute(false);
        }
    }

    private void ensureCallAudible() {
        AudioManager audioManager = (AudioManager) this.mContext.getSystemService(AudioManager.class);
        if (audioManager == null) {
            Log.w(this, "ensureCallAudible: audio manager is null", new Object[0]);
        } else if (audioManager.getStreamVolume(0) == 0) {
            Log.i(this, "ensureCallAudible: voice call stream has volume 0. Adjusting to default.", new Object[0]);
            audioManager.setStreamVolume(0, AudioSystem.getDefaultStreamVolume(0), 0);
        }
    }

    Call createCallForExistingConnection(String str, ParcelableConnection parcelableConnection) {
        Call call = new Call(str, this.mContext, this, this.mLock, this.mConnectionServiceRepository, this.mPhoneNumberUtilsAdapter, parcelableConnection.getHandle(), null, this.mPhoneAccountRegistrar.getSimCallManagerFromHandle(parcelableConnection.getPhoneAccount(), this.mCurrentUserHandle), parcelableConnection.getPhoneAccount(), Call.getRemappedCallDirection(parcelableConnection.getCallDirection()), false, (parcelableConnection.getConnectionProperties() & 64) != 0, parcelableConnection.getConnectTimeMillis(), parcelableConnection.getConnectElapsedTimeMillis(), this.mClockProxy, this.mToastFactory);
        call.initAnalytics();
        call.getAnalytics().setCreatedFromExistingConnection(true);
        setCallState(call, Call.getStateFromConnectionState(parcelableConnection.getState()), "existing connection");
        call.setVideoState(parcelableConnection.getVideoState());
        call.setConnectionCapabilities(parcelableConnection.getConnectionCapabilities());
        call.setConnectionProperties(parcelableConnection.getConnectionProperties());
        call.setHandle(parcelableConnection.getHandle(), parcelableConnection.getHandlePresentation());
        call.setCallerDisplayName(parcelableConnection.getCallerDisplayName(), parcelableConnection.getCallerDisplayNamePresentation());
        call.addListener(this);
        call.putExtras(1, parcelableConnection.getExtras());
        Log.i(this, "createCallForExistingConnection: %s", new Object[]{parcelableConnection});
        Call callOrElse = null;
        if (!TextUtils.isEmpty(parcelableConnection.getParentCallId())) {
            final String parentCallId = parcelableConnection.getParentCallId();
            callOrElse = this.mCalls.stream().filter(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$6XFEDWlZ3Ce7UhzFplA3rYXbDrU
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((Call) obj).getId().equals(parentCallId);
                }
            }).findFirst().orElse(null);
            if (callOrElse != null) {
                Log.i(this, "createCallForExistingConnection: %s added as child of %s.", new Object[]{call.getId(), callOrElse.getId()});
                call.setParentCall(callOrElse);
            }
        }
        addCall(call);
        if (callOrElse != null) {
            call.setChildOf(callOrElse);
            call.notifyParentChanged(callOrElse);
        }
        return call;
    }

    Call getAlreadyAddedConnection(final String str) {
        Optional<Call> optionalFindFirst = this.mCalls.stream().filter(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$QJGKT-gRg1f5HLkzoDlQ31sl8nI
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CallsManager.lambda$getAlreadyAddedConnection$29(str, (Call) obj);
            }
        }).findFirst();
        if (!optionalFindFirst.isPresent()) {
            return null;
        }
        Log.i(this, "isExistingConnectionAlreadyAdded - call %s already added with id %s", new Object[]{str, optionalFindFirst.get().getId()});
        return optionalFindFirst.get();
    }

    static /* synthetic */ boolean lambda$getAlreadyAddedConnection$29(String str, Call call) {
        return str.equals(call.getOriginalConnectionId()) || str.equals(call.getId());
    }

    private String getNextCallId() {
        String string;
        synchronized (this.mLock) {
            StringBuilder sb = new StringBuilder();
            sb.append("TC@");
            int i = this.mCallId + 1;
            this.mCallId = i;
            sb.append(i);
            string = sb.toString();
        }
        return string;
    }

    public int getNextRttRequestId() {
        int i;
        synchronized (this.mLock) {
            i = this.mRttRequestId + 1;
            this.mRttRequestId = i;
        }
        return i;
    }

    @VisibleForTesting
    public void onUserSwitch(UserHandle userHandle) {
        this.mCurrentUserHandle = userHandle;
        this.mMissedCallNotifier.setCurrentUserHandle(userHandle);
        this.mRoleManagerAdapter.setCurrentUserHandle(userHandle);
        Iterator it = UserManager.get(this.mContext).getEnabledProfiles(userHandle.getIdentifier()).iterator();
        while (it.hasNext()) {
            reloadMissedCallsOfUser(((UserInfo) it.next()).getUserHandle());
        }
    }

    void onUserStarting(UserHandle userHandle) {
        if (UserUtil.isProfile(this.mContext, userHandle)) {
            reloadMissedCallsOfUser(userHandle);
        }
    }

    public TelecomSystem.SyncRoot getLock() {
        return this.mLock;
    }

    public Timeouts.Adapter getTimeoutsAdapter() {
        return this.mTimeoutsAdapter;
    }

    public SystemStateHelper getSystemStateHelper() {
        return this.mSystemStateHelper;
    }

    private void reloadMissedCallsOfUser(UserHandle userHandle) {
        this.mMissedCallNotifier.reloadFromDatabase(this.mCallerInfoLookupHelper, new MissedCallNotifier.CallInfoFactory(), userHandle);
    }

    public void onBootCompleted() {
        this.mMissedCallNotifier.reloadAfterBootComplete(this.mCallerInfoLookupHelper, new MissedCallNotifier.CallInfoFactory());
    }

    public boolean isIncomingCallPermitted(PhoneAccountHandle phoneAccountHandle) {
        return isIncomingCallPermitted(null, phoneAccountHandle);
    }

    public boolean isIncomingCallPermitted(Call call, PhoneAccountHandle phoneAccountHandle) {
        PhoneAccount phoneAccountUnchecked;
        if (phoneAccountHandle == null || (phoneAccountUnchecked = this.mPhoneAccountRegistrar.getPhoneAccountUnchecked(phoneAccountHandle)) == null || isInEmergencyCall()) {
            return false;
        }
        return !phoneAccountUnchecked.isSelfManaged() ? (hasMaximumManagedRingingCalls(call) || hasMaximumManagedHoldingCalls(call)) ? false : true : (hasMaximumSelfManagedRingingCalls(call, phoneAccountHandle) || hasMaximumSelfManagedCalls(call, phoneAccountHandle)) ? false : true;
    }

    public boolean isOutgoingCallPermitted(PhoneAccountHandle phoneAccountHandle) {
        return isOutgoingCallPermitted(null, phoneAccountHandle);
    }

    public boolean isOutgoingCallPermitted(Call call, PhoneAccountHandle phoneAccountHandle) {
        PhoneAccount phoneAccountUnchecked;
        if (phoneAccountHandle == null || (phoneAccountUnchecked = this.mPhoneAccountRegistrar.getPhoneAccountUnchecked(phoneAccountHandle)) == null) {
            return false;
        }
        if (!phoneAccountUnchecked.isSelfManaged()) {
            return (hasMaximumManagedOutgoingCalls(call) || hasMaximumManagedDialingCalls(call) || hasMaximumManagedLiveCalls(call) || hasMaximumManagedHoldingCalls(call)) ? false : true;
        }
        Call call2 = (Call) this.mConnectionSvrFocusMgr.getCurrentFocusCall();
        if (isInEmergencyCall()) {
            return false;
        }
        if (call == null || call.getHandoverSourceCall() == null) {
            if (hasMaximumSelfManagedCalls(call, phoneAccountHandle)) {
                return false;
            }
            if (call2 != null && !canHold(call2)) {
                return false;
            }
        }
        return true;
    }

    public boolean isReplyWithSmsAllowed(int i) {
        UserHandle userHandleOf = UserHandle.of(UserHandle.getUserId(i));
        UserManager userManager = (UserManager) this.mContext.getSystemService(UserManager.class);
        KeyguardManager keyguardManager = (KeyguardManager) this.mContext.getSystemService(KeyguardManager.class);
        boolean z = userManager != null && userManager.hasUserRestriction("no_sms", userHandleOf);
        Log.d(this, "isReplyWithSmsAllowed: isUserRestricted: %s, isLockscreenRestricted: %s", new Object[]{Boolean.valueOf(z), Boolean.valueOf(keyguardManager != null && keyguardManager.isDeviceLocked())});
        return !z;
    }

    public void waitOnHandlers() {
        final CountDownLatch countDownLatch = new CountDownLatch(3);
        this.mHandler.post(new Runnable() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$GohjX4fmhz3RQP14hB5uDCwa8IU
            @Override // java.lang.Runnable
            public final void run() {
                countDownLatch.countDown();
            }
        });
        this.mCallAudioManager.getCallAudioModeStateMachine().getHandler().post(new Runnable() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$HA9VcZCSFuczEL613uIIvY2C7Iw
            @Override // java.lang.Runnable
            public final void run() {
                countDownLatch.countDown();
            }
        });
        this.mCallAudioManager.getCallAudioRouteStateMachine().getHandler().post(new Runnable() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$xESWRigz2zocUlRsAeNcoKlwftI
            @Override // java.lang.Runnable
            public final void run() {
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(10000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Log.w(this, "waitOnHandlers: interrupted %s", new Object[]{e});
        }
    }

    public void confirmPendingCall(String str) {
        Log.i(this, "confirmPendingCall: callId=%s", new Object[]{str});
        Call call = this.mPendingCall;
        if (call == null || !call.getId().equals(str)) {
            return;
        }
        Log.addEvent(this.mPendingCall, "USER_CONFIRMED");
        disconnectSelfManagedCalls("outgoing call " + str);
        this.mPendingCallConfirm.complete(this.mPendingCall);
        this.mPendingCallConfirm = null;
        this.mPendingCall = null;
    }

    public void cancelPendingCall(String str) {
        Log.i(this, "cancelPendingCall: callId=%s", new Object[]{str});
        Call call = this.mPendingCall;
        if (call == null || !call.getId().equals(str)) {
            return;
        }
        Log.addEvent(this.mPendingCall, "USER_CANCELLED");
        markCallAsDisconnected(this.mPendingCall, new DisconnectCause(4));
        markCallAsRemoved(this.mPendingCall);
        this.mPendingCall = null;
        this.mPendingCallConfirm.complete(null);
        this.mPendingCallConfirm = null;
    }

    private void startCallConfirmation(Call call, CompletableFuture<Call> completableFuture) {
        Call call2 = this.mPendingCall;
        if (call2 != null) {
            Log.i(this, "startCallConfirmation: call %s is already pending; disconnecting %s", new Object[]{call2.getId(), call.getId()});
            markCallDisconnectedDueToSelfManagedCall(call);
            completableFuture.complete(null);
            return;
        }
        Log.addEvent(call, "USER_CONFIRMATION");
        this.mPendingCall = call;
        this.mPendingCallConfirm = completableFuture;
        Call call3 = (Call) this.mConnectionSvrFocusMgr.getCurrentFocusCall();
        if (call3 != null) {
            CharSequence targetPhoneAccountLabel = call3.getTargetPhoneAccountLabel();
            Log.i(this, "startCallConfirmation: callId=%s, ongoingApp=%s", new Object[]{call.getId(), targetPhoneAccountLabel});
            Intent intent = new Intent(this.mContext, (Class<?>) ConfirmCallDialogActivity.class);
            intent.putExtra("android.telecom.extra.OUTGOING_CALL_ID", call.getId());
            intent.putExtra("android.telecom.extra.ONGOING_APP_NAME", targetPhoneAccountLabel);
            intent.setFlags(268435456);
            this.mContext.startActivityAsUser(intent, UserHandle.CURRENT);
        }
    }

    private void disconnectSelfManagedCalls(final String str) {
        this.mCalls.stream().filter(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$kHexgilY4ntRWvA9zotduKj05nM
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((Call) obj).isSelfManaged();
            }
        }).forEach(new Consumer() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$PYPSsClpoXvh0Mf3WteHm2q0M-k
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Call) obj).disconnect(str);
            }
        });
        this.mCallAudioManager.switchBaseline();
    }

    public void dump(IndentingPrintWriter indentingPrintWriter) {
        this.mContext.enforceCallingOrSelfPermission("android.permission.DUMP", "CallsManager");
        if (this.mCalls != null) {
            indentingPrintWriter.println("mCalls: ");
            indentingPrintWriter.increaseIndent();
            Iterator<Call> it = this.mCalls.iterator();
            while (it.hasNext()) {
                indentingPrintWriter.println(it.next());
            }
            indentingPrintWriter.decreaseIndent();
        }
        if (this.mPendingCall != null) {
            indentingPrintWriter.print("mPendingCall:");
            indentingPrintWriter.println(this.mPendingCall.getId());
        }
        if (this.mPendingRedirectedOutgoingCallInfo.size() > 0) {
            indentingPrintWriter.print("mPendingRedirectedOutgoingCallInfo:");
            indentingPrintWriter.println((String) this.mPendingRedirectedOutgoingCallInfo.keySet().stream().collect(Collectors.joining(", ")));
        }
        if (this.mPendingUnredirectedOutgoingCallInfo.size() > 0) {
            indentingPrintWriter.print("mPendingUnredirectedOutgoingCallInfo:");
            indentingPrintWriter.println((String) this.mPendingUnredirectedOutgoingCallInfo.keySet().stream().collect(Collectors.joining(", ")));
        }
        if (this.mCallAudioManager != null) {
            indentingPrintWriter.println("mCallAudioManager:");
            indentingPrintWriter.increaseIndent();
            this.mCallAudioManager.dump(indentingPrintWriter);
            indentingPrintWriter.decreaseIndent();
        }
        if (this.mTtyManager != null) {
            indentingPrintWriter.println("mTtyManager:");
            indentingPrintWriter.increaseIndent();
            this.mTtyManager.dump(indentingPrintWriter);
            indentingPrintWriter.decreaseIndent();
        }
        if (this.mInCallController != null) {
            indentingPrintWriter.println("mInCallController:");
            indentingPrintWriter.increaseIndent();
            this.mInCallController.dump(indentingPrintWriter);
            indentingPrintWriter.decreaseIndent();
        }
        if (this.mDefaultDialerCache != null) {
            indentingPrintWriter.println("mDefaultDialerCache:");
            indentingPrintWriter.increaseIndent();
            this.mDefaultDialerCache.dumpCache(indentingPrintWriter);
            indentingPrintWriter.decreaseIndent();
        }
        if (this.mConnectionServiceRepository != null) {
            indentingPrintWriter.println("mConnectionServiceRepository:");
            indentingPrintWriter.increaseIndent();
            this.mConnectionServiceRepository.dump(indentingPrintWriter);
            indentingPrintWriter.decreaseIndent();
        }
        RoleManagerAdapter roleManagerAdapter = this.mRoleManagerAdapter;
        if (roleManagerAdapter == null || !(roleManagerAdapter instanceof RoleManagerAdapterImpl)) {
            return;
        }
        indentingPrintWriter.println("mRoleManager:");
        indentingPrintWriter.increaseIndent();
        ((RoleManagerAdapterImpl) roleManagerAdapter).dump(indentingPrintWriter);
        indentingPrintWriter.decreaseIndent();
    }

    private void maybeShowErrorDialogOnDisconnect(Call call) {
        if (call.getState() == 7) {
            if ((isPotentialMMICode(call.getHandle()) || isPotentialInCallMMICode(call.getHandle())) && !this.mCalls.contains(call)) {
                DisconnectCause disconnectCause = call.getDisconnectCause();
                if (TextUtils.isEmpty(disconnectCause.getDescription()) || disconnectCause.getCode() != 1) {
                    return;
                }
                Intent intent = new Intent(this.mContext, (Class<?>) ErrorDialogActivity.class);
                intent.putExtra("error_message_string", disconnectCause.getDescription());
                intent.setFlags(268435456);
                this.mContext.startActivityAsUser(intent, UserHandle.CURRENT);
            }
        }
    }

    private void setIntentExtrasAndStartTime(Call call, Bundle bundle) {
        Bundle bundle2;
        if (bundle != null) {
            bundle2 = new Bundle(bundle);
        } else {
            bundle2 = new Bundle();
        }
        bundle2.putLong("android.telecom.extra.CALL_TELECOM_ROUTING_START_TIME_MILLIS", SystemClock.elapsedRealtime());
        call.setIntentExtras(bundle2);
    }

    private void setCallSourceToAnalytics(Call call, Intent intent) {
        if (intent == null) {
            return;
        }
        call.getAnalytics().setCallSource(intent.getIntExtra("android.telecom.extra.CALL_SOURCE", 0));
    }

    private boolean isVoicemail(Uri uri, PhoneAccount phoneAccount) {
        if (uri == null) {
            return false;
        }
        if ("voicemail".equals(uri.getScheme())) {
            return true;
        }
        return phoneAccount != null && this.mPhoneAccountRegistrar.isVoiceMailNumber(phoneAccount.getAccountHandle(), uri.getSchemeSpecificPart());
    }

    private void notifyCreateConnectionFailed(PhoneAccountHandle phoneAccountHandle, Call call) {
        if (phoneAccountHandle == null) {
            return;
        }
        ConnectionServiceWrapper service = this.mConnectionServiceRepository.getService(phoneAccountHandle.getComponentName(), phoneAccountHandle.getUserHandle());
        if (service == null) {
            Log.i(this, "Found no connection service.", new Object[0]);
        } else {
            call.setConnectionService(service);
            service.createConnectionFailed(call);
        }
    }

    private void notifyCreateConferenceFailed(PhoneAccountHandle phoneAccountHandle, Call call) {
        if (phoneAccountHandle == null) {
            return;
        }
        ConnectionServiceWrapper service = this.mConnectionServiceRepository.getService(phoneAccountHandle.getComponentName(), phoneAccountHandle.getUserHandle());
        if (service == null) {
            Log.i(this, "Found no connection service.", new Object[0]);
        } else {
            call.setConnectionService(service);
            service.createConferenceFailed(call);
        }
    }

    private void notifyHandoverFailed(Call call, int i) {
        call.getConnectionService().handoverFailed(call, i);
        call.setDisconnectCause(new DisconnectCause(4));
        call.disconnect("handover failed");
    }

    private void requestHandoverViaEvents(Call call, PhoneAccountHandle phoneAccountHandle, int i, Bundle bundle) {
        call.sendCallEvent("android.telecom.event.HANDOVER_FAILED", null);
        Log.addEvent(call, "HANDOVER_REQUEST", "legacy request denied");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01a5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void requestHandover(com.android.server.telecom.Call r29, android.telecom.PhoneAccountHandle r30, int r31, android.os.Bundle r32) {
        /*
            Method dump skipped, instruction units count: 438
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.server.telecom.CallsManager.requestHandover(com.android.server.telecom.Call, android.telecom.PhoneAccountHandle, int, android.os.Bundle):void");
    }

    private boolean isHandoverFromPhoneAccountSupported(PhoneAccountHandle phoneAccountHandle) {
        return getBooleanPhoneAccountExtra(phoneAccountHandle, "android.telecom.extra.SUPPORTS_HANDOVER_FROM");
    }

    private boolean isHandoverToPhoneAccountSupported(PhoneAccountHandle phoneAccountHandle) {
        return getBooleanPhoneAccountExtra(phoneAccountHandle, "android.telecom.extra.SUPPORTS_HANDOVER_TO");
    }

    private boolean getBooleanPhoneAccountExtra(PhoneAccountHandle phoneAccountHandle, String str) {
        Bundle extras;
        PhoneAccount phoneAccountUnchecked = getPhoneAccountRegistrar().getPhoneAccountUnchecked(phoneAccountHandle);
        if (phoneAccountUnchecked == null || (extras = phoneAccountUnchecked.getExtras()) == null) {
            return false;
        }
        return extras.getBoolean(str);
    }

    private boolean isHandoverInProgress() {
        return this.mCalls.stream().filter(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$8Al2AGnleG9O6WTZoKVxl6hsc6A
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CallsManager.lambda$isHandoverInProgress$35((Call) obj);
            }
        }).count() > 0;
    }

    static /* synthetic */ boolean lambda$isHandoverInProgress$35(Call call) {
        return (call.getHandoverSourceCall() == null && call.getHandoverDestinationCall() == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastUnregisterIntent(PhoneAccountHandle phoneAccountHandle) {
        Intent intent = new Intent("android.telecom.action.PHONE_ACCOUNT_UNREGISTERED");
        intent.addFlags(16777216);
        intent.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandle);
        Log.i(this, "Sending phone-account %s unregistered intent as user", new Object[]{phoneAccountHandle});
        this.mContext.sendBroadcastAsUser(intent, UserHandle.ALL, "android.permission.PROCESS_PHONE_ACCOUNT_REGISTRATION");
        String defaultDialerApplication = this.mDefaultDialerCache.getDefaultDialerApplication(getCurrentUserHandle().getIdentifier());
        if (TextUtils.isEmpty(defaultDialerApplication)) {
            return;
        }
        Intent intent2 = new Intent("android.telecom.action.PHONE_ACCOUNT_UNREGISTERED").setPackage(defaultDialerApplication);
        intent2.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandle);
        Log.i(this, "Sending phone-account unregistered intent to default dialer", new Object[0]);
        this.mContext.sendBroadcastAsUser(intent2, UserHandle.ALL, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastRegisterIntent(PhoneAccountHandle phoneAccountHandle) {
        Intent intent = new Intent("android.telecom.action.PHONE_ACCOUNT_REGISTERED");
        intent.addFlags(16777216);
        intent.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandle);
        Log.i(this, "Sending phone-account %s registered intent as user", new Object[]{phoneAccountHandle});
        this.mContext.sendBroadcastAsUser(intent, UserHandle.ALL, "android.permission.PROCESS_PHONE_ACCOUNT_REGISTRATION");
        String defaultDialerApplication = this.mDefaultDialerCache.getDefaultDialerApplication(getCurrentUserHandle().getIdentifier());
        if (TextUtils.isEmpty(defaultDialerApplication)) {
            return;
        }
        Intent intent2 = new Intent("android.telecom.action.PHONE_ACCOUNT_REGISTERED").setPackage(defaultDialerApplication);
        intent2.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandle);
        Log.i(this, "Sending phone-account registered intent to default dialer", new Object[0]);
        this.mContext.sendBroadcastAsUser(intent2, UserHandle.ALL, null);
    }

    public void acceptHandover(Uri uri, int i, PhoneAccountHandle phoneAccountHandle) {
        CallsManager callsManager;
        final String schemeSpecificPart = uri.getSchemeSpecificPart();
        Call callOrElse = this.mCalls.stream().filter(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$khtJt0tsbYtGoBd_2fjU0N-mlR8
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$acceptHandover$36$CallsManager(schemeSpecificPart, (Call) obj);
            }
        }).findFirst().orElse(null);
        Call call = new Call(getNextCallId(), this.mContext, this, this.mLock, this.mConnectionServiceRepository, this.mPhoneNumberUtilsAdapter, uri, null, null, phoneAccountHandle, 2, false, false, this.mClockProxy, this.mToastFactory);
        if (callOrElse == null || isHandoverInProgress()) {
            callsManager = this;
        } else {
            callsManager = this;
            if (callsManager.isHandoverFromPhoneAccountSupported(callOrElse.getTargetPhoneAccount()) && callsManager.isHandoverToPhoneAccountSupported(phoneAccountHandle) && !isInEmergencyCall()) {
                PhoneAccount phoneAccountUnchecked = callsManager.mPhoneAccountRegistrar.getPhoneAccountUnchecked(phoneAccountHandle);
                if (phoneAccountUnchecked == null) {
                    Log.w(callsManager, "acceptHandover: Handover not supported. phoneAccount = null", new Object[0]);
                    callsManager.notifyHandoverFailed(call, 2);
                    return;
                }
                call.setIsSelfManaged(phoneAccountUnchecked.isSelfManaged());
                if (call.isSelfManaged() || (phoneAccountUnchecked.getExtras() != null && phoneAccountUnchecked.getExtras().getBoolean("android.telecom.extra.ALWAYS_USE_VOIP_AUDIO_MODE"))) {
                    call.setIsVoipAudioMode(true);
                }
                if (!phoneAccountUnchecked.hasCapabilities(8)) {
                    call.setVideoState(0);
                } else {
                    call.setVideoState(i);
                }
                call.initAnalytics();
                call.addListener(callsManager);
                callOrElse.setHandoverDestinationCall(call);
                call.setHandoverSourceCall(callOrElse);
                call.setHandoverState(2);
                callOrElse.setHandoverState(3);
                if (isSpeakerEnabledForVideoCalls() && VideoProfile.isVideo(i)) {
                    call.setStartWithSpeakerphoneOn(true);
                }
                Bundle intentExtras = call.getIntentExtras();
                if (intentExtras == null) {
                    intentExtras = new Bundle();
                }
                intentExtras.putBoolean("android.telecom.extra.IS_HANDOVER_CONNECTION", true);
                intentExtras.putParcelable("android.telecom.extra.HANDOVER_FROM_PHONE_ACCOUNT", callOrElse.getTargetPhoneAccount());
                call.startCreateConnection(callsManager.mPhoneAccountRegistrar);
                return;
            }
        }
        Log.w(callsManager, "acceptHandover: Handover not supported", new Object[0]);
        callsManager.notifyHandoverFailed(call, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$acceptHandover$36, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$acceptHandover$36$CallsManager(String str, Call call) {
        return this.mPhoneNumberUtilsAdapter.isSamePhoneNumber(call.getHandle() == null ? null : call.getHandle().getSchemeSpecificPart(), str);
    }

    public ConnectionServiceFocusManager getConnectionServiceFocusManager() {
        return this.mConnectionSvrFocusMgr;
    }

    private boolean canHold(Call call) {
        return call.can(1) && call.getState() != 3;
    }

    private boolean supportsHold(Call call) {
        return call.can(2);
    }

    private final class ActionSetCallState implements PendingAction {
        private final Call mCall;
        private final int mState;
        private final String mTag;

        ActionSetCallState(Call call, int i, String str) {
            this.mCall = call;
            this.mState = i;
            this.mTag = str;
        }

        @Override // com.android.server.telecom.CallsManager.PendingAction
        public void performAction() {
            synchronized (CallsManager.this.mLock) {
                Log.d(this, "perform set call state for %s, state = %s", new Object[]{this.mCall, Integer.valueOf(this.mState)});
                CallsManager.this.setCallState(this.mCall, this.mState, this.mTag);
            }
        }
    }

    private final class ActionUnHoldCall implements PendingAction {
        private final Call mCall;
        private final String mPreviouslyHeldCallId;

        ActionUnHoldCall(Call call, String str) {
            this.mCall = call;
            this.mPreviouslyHeldCallId = str;
        }

        @Override // com.android.server.telecom.CallsManager.PendingAction
        public void performAction() {
            synchronized (CallsManager.this.mLock) {
                Log.d(this, "perform unhold call for %s", new Object[]{this.mCall});
                this.mCall.unhold("held " + this.mPreviouslyHeldCallId);
            }
        }
    }

    private final class ActionAnswerCall implements PendingAction {
        private final Call mCall;
        private final int mVideoState;

        ActionAnswerCall(Call call, int i) {
            this.mCall = call;
            this.mVideoState = i;
        }

        @Override // com.android.server.telecom.CallsManager.PendingAction
        public void performAction() {
            synchronized (CallsManager.this.mLock) {
                Log.d(this, "perform answer call for %s, videoState = %d", new Object[]{this.mCall, Integer.valueOf(this.mVideoState)});
                Iterator it = CallsManager.this.mListeners.iterator();
                while (it.hasNext()) {
                    ((CallsManagerListener) it.next()).onIncomingCallAnswered(this.mCall);
                }
                if (this.mCall.getState() == 4) {
                    this.mCall.answer(this.mVideoState);
                    CallsManager.this.setCallState(this.mCall, 11, "answered");
                } else if (this.mCall.getState() == 13) {
                    CallsManager.this.setCallState(this.mCall, 5, "answering simulated ringing");
                    Log.addEvent(this.mCall, "REQUEST_SIMULATED_ACCEPT");
                } else if (this.mCall.getState() == 11) {
                    this.mCall.answer(this.mVideoState);
                    Log.w(this, "Duplicate answer request for call %s", new Object[]{this.mCall.getId()});
                }
                if (CallsManager.this.isSpeakerphoneAutoEnabledForVideoCalls(this.mVideoState)) {
                    this.mCall.setStartWithSpeakerphoneOn(true);
                }
            }
        }
    }

    @VisibleForTesting(visibility = VisibleForTesting.Visibility.PACKAGE)
    public static final class RequestCallback implements ConnectionServiceFocusManager.RequestFocusCallback {
        private PendingAction mPendingAction;

        RequestCallback(PendingAction pendingAction) {
            this.mPendingAction = pendingAction;
        }

        @Override // com.android.server.telecom.ConnectionServiceFocusManager.RequestFocusCallback
        public void onRequestFocusDone(ConnectionServiceFocusManager.CallFocus callFocus) {
            PendingAction pendingAction = this.mPendingAction;
            if (pendingAction != null) {
                pendingAction.performAction();
            }
        }
    }

    public void resetConnectionTime(Call call) {
        call.setConnectTimeMillis(System.currentTimeMillis());
        call.setConnectElapsedTimeMillis(SystemClock.elapsedRealtime());
        if (this.mCalls.contains(call)) {
            Iterator<CallsManagerListener> it = this.mListeners.iterator();
            while (it.hasNext()) {
                it.next().onConnectionTimeChanged(call);
            }
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    static /* synthetic */ boolean lambda$isInEmergencyCall$37(Call call) {
        return (call.isEmergencyCall() || call.isNetworkIdentifiedEmergencyCall()) && !call.isDisconnected();
    }

    public boolean isInEmergencyCall() {
        return this.mCalls.stream().filter(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$Wn2cTx2ieI5f6JhZIvdMq5BkAOU
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CallsManager.lambda$isInEmergencyCall$37((Call) obj);
            }
        }).count() > 0;
    }

    private void updateExternalCallCanPullSupport() {
        final boolean zIsInEmergencyCall = isInEmergencyCall();
        this.mCalls.stream().filter(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$zOJ1B5LEtUkHsqhD9nXW2uj9Cns
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((Call) obj).isExternalCall();
            }
        }).forEach(new Consumer() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$lS8aicxlSqROBCmTc7ATMb26yzo
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Call) obj).setIsPullExternalCallSupported(!zIsInEmergencyCall);
            }
        });
    }

    private void showErrorMessage(int i) {
        Intent intent = new Intent(this.mContext, (Class<?>) ErrorDialogActivity.class);
        intent.putExtra("error_message_id", i);
        intent.setFlags(268435456);
        this.mContext.startActivityAsUser(intent, UserHandle.CURRENT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePhoneAccountChanged(PhoneAccountRegistrar phoneAccountRegistrar, final PhoneAccount phoneAccount) {
        Log.i(this, "handlePhoneAccountChanged: phoneAccount=%s", new Object[]{phoneAccount});
        final boolean zHasCapabilities = phoneAccount.hasCapabilities(8);
        this.mCalls.stream().filter(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$_mNqpFt5zdiZQ7Nc8QT9TuS3zzA
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return phoneAccount.getAccountHandle().equals(((Call) obj).getTargetPhoneAccount());
            }
        }).forEach(new Consumer() { // from class: com.android.server.telecom.-$$Lambda$CallsManager$cpCSwmZwd8ADuouxw2bpcOCxFaE
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Call) obj).setVideoCallingSupportedByPhoneAccount(zHasCapabilities);
            }
        });
    }

    public static boolean areFromSameSource(Call call, Call call2) {
        PhoneAccountHandle connectionManagerPhoneAccount = call.getConnectionManagerPhoneAccount();
        PhoneAccountHandle connectionManagerPhoneAccount2 = call2.getConnectionManagerPhoneAccount();
        if (connectionManagerPhoneAccount == null || connectionManagerPhoneAccount2 == null || !PhoneAccountHandle.areFromSamePackage(connectionManagerPhoneAccount, connectionManagerPhoneAccount2)) {
            return PhoneAccountHandle.areFromSamePackage(call.getTargetPhoneAccount(), call2.getTargetPhoneAccount());
        }
        return true;
    }

    private void maybeSendPostCallScreenIntent(Call call) {
        if (call.isEmergencyCall() || call.isNetworkIdentifiedEmergencyCall() || call.getPostCallPackageName() == null) {
            return;
        }
        Intent intent = new Intent("android.telecom.action.POST_CALL");
        intent.setPackage(call.getPostCallPackageName());
        intent.putExtra("android.telecom.extra.HANDLE", call.getHandle());
        intent.putExtra("android.telecom.extra.DISCONNECT_CAUSE", call.getDisconnectCause().getCode());
        long ageMillis = call.getAgeMillis();
        int i = 0;
        if (ageMillis >= 3000 && ageMillis < 60000) {
            i = 1;
        } else if (ageMillis >= 60000 && ageMillis < 120000) {
            i = 2;
        } else if (ageMillis >= 120000) {
            i = 3;
        }
        intent.putExtra("android.telecom.extra.CALL_DURATION", i);
        intent.addFlags(268435456);
        this.mContext.startActivityAsUser(intent, this.mCurrentUserHandle);
    }
}

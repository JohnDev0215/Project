package com.android.server.telecom.components;

import android.app.Service;
import android.app.role.RoleManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.media.IAudioService;
import android.media.ToneGenerator;
import android.os.IBinder;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.telecom.CallerInfoAsyncQuery;
import android.telecom.Log;
import com.android.internal.telecom.IInternalServiceRetriever;
import com.android.internal.telecom.ITelecomLoader;
import com.android.internal.telecom.ITelecomService;
import com.android.server.telecom.AsyncRingtonePlayer;
import com.android.server.telecom.BluetoothAdapterProxy;
import com.android.server.telecom.BluetoothPhoneServiceImpl;
import com.android.server.telecom.CallAudioManager;
import com.android.server.telecom.CallAudioModeStateMachine;
import com.android.server.telecom.CallAudioRouteStateMachine;
import com.android.server.telecom.CallerInfoAsyncQueryFactory;
import com.android.server.telecom.CallsManager;
import com.android.server.telecom.ClockProxy;
import com.android.server.telecom.ConnectionServiceFocusManager;
import com.android.server.telecom.ContactsAsyncHelper;
import com.android.server.telecom.DefaultDialerCache;
import com.android.server.telecom.DeviceIdleControllerAdapter;
import com.android.server.telecom.HeadsetMediaButton;
import com.android.server.telecom.HeadsetMediaButtonFactory;
import com.android.server.telecom.InCallTonePlayer;
import com.android.server.telecom.InCallWakeLockController;
import com.android.server.telecom.InCallWakeLockControllerFactory;
import com.android.server.telecom.InternalServiceRetrieverAdapter;
import com.android.server.telecom.PhoneAccountRegistrar;
import com.android.server.telecom.PhoneNumberUtilsAdapterImpl;
import com.android.server.telecom.ProximitySensorManager;
import com.android.server.telecom.ProximitySensorManagerFactory;
import com.android.server.telecom.RoleManagerAdapterImpl;
import com.android.server.telecom.TelecomSystem;
import com.android.server.telecom.TelecomWakeLock;
import com.android.server.telecom.Timeouts;
import com.android.server.telecom.ui.IncomingCallNotifier;
import com.android.server.telecom.ui.MissedCallNotifierImpl;
import com.android.server.telecom.ui.NotificationChannelManager;

/* JADX INFO: loaded from: classes.dex */
public class TelecomService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Log.d(this, "onBind", new Object[0]);
        return new ITelecomLoader.Stub() { // from class: com.android.server.telecom.components.TelecomService.1
            public ITelecomService createTelecomService(IInternalServiceRetriever iInternalServiceRetriever) {
                ITelecomService.Stub binder;
                TelecomService.initializeTelecomSystem(TelecomService.this, new InternalServiceRetrieverAdapter(iInternalServiceRetriever));
                synchronized (TelecomService.this.getTelecomSystem().getLock()) {
                    binder = TelecomService.this.getTelecomSystem().getTelecomServiceImpl().getBinder();
                }
                return binder;
            }
        };
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [com.android.server.telecom.callfiltering.IncomingCallFilter$Factory] */
    static void initializeTelecomSystem(Context context, InternalServiceRetrieverAdapter internalServiceRetrieverAdapter) {
        if (TelecomSystem.getInstance() == null) {
            new NotificationChannelManager().createChannels(context);
            TelecomSystem.setInstance(new TelecomSystem(context, new MissedCallNotifierImpl.MissedCallNotifierImplFactory() { // from class: com.android.server.telecom.components.TelecomService.2
                @Override // com.android.server.telecom.ui.MissedCallNotifierImpl.MissedCallNotifierImplFactory
                public MissedCallNotifierImpl makeMissedCallNotifierImpl(Context context2, PhoneAccountRegistrar phoneAccountRegistrar, DefaultDialerCache defaultDialerCache, DeviceIdleControllerAdapter deviceIdleControllerAdapter) {
                    return new MissedCallNotifierImpl(context2, phoneAccountRegistrar, defaultDialerCache, deviceIdleControllerAdapter);
                }
            }, new CallerInfoAsyncQueryFactory() { // from class: com.android.server.telecom.components.TelecomService.3
                @Override // com.android.server.telecom.CallerInfoAsyncQueryFactory
                public CallerInfoAsyncQuery startQuery(int i, Context context2, String str, CallerInfoAsyncQuery.OnQueryCompleteListener onQueryCompleteListener, Object obj) {
                    Log.i(TelecomSystem.getInstance(), "CallerInfoAsyncQuery.startQuery number=%s cookie=%s", new Object[]{Log.pii(str), obj});
                    return CallerInfoAsyncQuery.startQuery(i, context2, str, onQueryCompleteListener, obj);
                }
            }, new HeadsetMediaButtonFactory() { // from class: com.android.server.telecom.components.TelecomService.4
                @Override // com.android.server.telecom.HeadsetMediaButtonFactory
                public HeadsetMediaButton create(Context context2, CallsManager callsManager, TelecomSystem.SyncRoot syncRoot) {
                    return new HeadsetMediaButton(context2, callsManager, syncRoot);
                }
            }, new ProximitySensorManagerFactory() { // from class: com.android.server.telecom.components.TelecomService.5
                @Override // com.android.server.telecom.ProximitySensorManagerFactory
                public ProximitySensorManager create(Context context2, CallsManager callsManager) {
                    return new ProximitySensorManager(new TelecomWakeLock(context2, 32, ProximitySensorManager.class.getSimpleName()), callsManager);
                }
            }, new InCallWakeLockControllerFactory() { // from class: com.android.server.telecom.components.TelecomService.6
                @Override // com.android.server.telecom.InCallWakeLockControllerFactory
                public InCallWakeLockController create(Context context2, CallsManager callsManager) {
                    return new InCallWakeLockController(new TelecomWakeLock(context2, 26, InCallWakeLockController.class.getSimpleName()), callsManager);
                }
            }, new CallAudioManager.AudioServiceFactory() { // from class: com.android.server.telecom.components.TelecomService.7
                @Override // com.android.server.telecom.CallAudioManager.AudioServiceFactory
                public IAudioService getAudioService() {
                    return IAudioService.Stub.asInterface(ServiceManager.getService("audio"));
                }
            }, new BluetoothPhoneServiceImpl.BluetoothPhoneServiceImplFactory() { // from class: com.android.server.telecom.components.TelecomService.8
                @Override // com.android.server.telecom.BluetoothPhoneServiceImpl.BluetoothPhoneServiceImplFactory
                public BluetoothPhoneServiceImpl makeBluetoothPhoneServiceImpl(Context context2, TelecomSystem.SyncRoot syncRoot, CallsManager callsManager, PhoneAccountRegistrar phoneAccountRegistrar) {
                    return new BluetoothPhoneServiceImpl(context2, syncRoot, callsManager, new BluetoothAdapterProxy(), phoneAccountRegistrar);
                }
            }, new ConnectionServiceFocusManager.ConnectionServiceFocusManagerFactory() { // from class: com.android.server.telecom.components.-$$Lambda$ov5J_2psWkUoinlNz5JrnduvWIw
                @Override // com.android.server.telecom.ConnectionServiceFocusManager.ConnectionServiceFocusManagerFactory
                public final ConnectionServiceFocusManager create(ConnectionServiceFocusManager.CallsManagerRequester callsManagerRequester) {
                    return new ConnectionServiceFocusManager(callsManagerRequester);
                }
            }, new Timeouts.Adapter(), new AsyncRingtonePlayer(), new PhoneNumberUtilsAdapterImpl(), new IncomingCallNotifier(context), new InCallTonePlayer.ToneGeneratorFactory() { // from class: com.android.server.telecom.components.-$$Lambda$zKQgBFeCPgPWMWVGOqypdYQ6XRA
                @Override // com.android.server.telecom.InCallTonePlayer.ToneGeneratorFactory
                public final ToneGenerator get(int i, int i2) {
                    return new ToneGenerator(i, i2);
                }
            }, new CallAudioRouteStateMachine.Factory(), new CallAudioModeStateMachine.Factory(), new ClockProxy() { // from class: com.android.server.telecom.components.TelecomService.9
                @Override // com.android.server.telecom.ClockProxy
                public long currentTimeMillis() {
                    return System.currentTimeMillis();
                }

                @Override // com.android.server.telecom.ClockProxy
                public long elapsedRealtime() {
                    return SystemClock.elapsedRealtime();
                }
            }, new RoleManagerAdapterImpl(context, (RoleManager) context.getSystemService("role")), new Object() { // from class: com.android.server.telecom.callfiltering.IncomingCallFilter$Factory
            }, new ContactsAsyncHelper.Factory(), internalServiceRetrieverAdapter.getDeviceIdleController()));
        }
        if (BluetoothAdapter.getDefaultAdapter() != null) {
            context.startService(new Intent(context, (Class<?>) BluetoothPhoneService.class));
        }
    }

    public TelecomSystem getTelecomSystem() {
        return TelecomSystem.getInstance();
    }
}

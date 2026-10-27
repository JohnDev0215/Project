package com.android.car;

import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadsetClient;
import android.bluetooth.BluetoothProfile;
import android.car.CarProjectionManager;
import android.car.input.CarInputHandlingService;
import android.car.input.ICarInput;
import android.car.input.ICarInputCallback;
import android.car.input.ICarInputListener;
import android.car.input.RotaryEvent;
import android.car.user.CarUserManager;
import android.car.userlib.UserHelper;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.hardware.input.InputManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.UserHandle;
import android.provider.CallLog;
import android.provider.Settings;
import android.telecom.TelecomManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import androidx.mediarouter.media.MediaRouter;
import com.android.car.hal.InputHalService;
import com.android.car.user.CarUserService;
import com.android.internal.app.AssistUtils;
import com.android.internal.app.IVoiceInteractionSessionShowCallback;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
public class CarInputService extends ICarInput.Stub implements CarServiceBase, InputHalService.InputListener {
    private static final boolean DBG = false;
    static final String EXTRA_CAR_PUSH_TO_TALK = "com.android.car.input.EXTRA_CAR_PUSH_TO_TALK";
    private final AssistUtils mAssistUtils;
    private final BluetoothAdapter mBluetoothAdapter;
    private BluetoothHeadsetClient mBluetoothHeadsetClient;
    private final BluetoothProfile.ServiceListener mBluetoothProfileServiceListener;
    private final KeyPressTimer mCallKeyTimer;
    private final Binder mCallback;
    private final InputCaptureClientController mCaptureController;
    ICarInputListener mCarInputListener;
    private boolean mCarInputListenerBound;
    private final Context mContext;
    private final ComponentName mCustomInputServiceComponent;
    private final SetMultimap<Integer, Integer> mHandledKeys;
    private final InputHalService mInputHalService;
    private final ServiceConnection mInputServiceConnection;
    private KeyEventListener mInstrumentClusterKeyListener;
    private final Supplier<String> mLastCalledNumberSupplier;
    private final Object mLock;
    private final IntSupplier mLongPressDelaySupplier;
    private final KeyEventListener mMainDisplayHandler;
    private CarProjectionManager.ProjectionKeyEventHandler mProjectionKeyEventHandler;
    private final BitSet mProjectionKeyEventsSubscribed;
    private final String mRotaryServiceComponentName;
    private final BooleanSupplier mShouldCallButtonEndOngoingCallSupplier;
    private final IVoiceInteractionSessionShowCallback mShowCallback;
    private final TelecomManager mTelecomManager;
    private final CarUserManager.UserLifecycleListener mUserLifecycleListener;
    private final CarUserService mUserService;
    private final KeyPressTimer mVoiceKeyTimer;

    public interface KeyEventListener {
        void onKeyEvent(KeyEvent keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class KeyPressTimer {
        private boolean mDown;
        private final Handler mHandler;
        private final IntSupplier mLongPressDelaySupplier;
        private final Runnable mLongPressRunnable;
        private final Runnable mCallback = new Runnable() { // from class: com.android.car.-$$Lambda$CarInputService$KeyPressTimer$RxS3zZd0nzAQkCrwfSaxcRP5l2k
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.onTimerExpired();
            }
        };
        private boolean mLongPress = false;

        KeyPressTimer(Handler handler, IntSupplier longPressDelaySupplier, Runnable longPressRunnable) {
            this.mHandler = handler;
            this.mLongPressRunnable = longPressRunnable;
            this.mLongPressDelaySupplier = longPressDelaySupplier;
        }

        void keyDown() {
            synchronized (CarInputService.this.mLock) {
                this.mDown = true;
                this.mLongPress = false;
                this.mHandler.removeCallbacks(this.mCallback);
                this.mHandler.postDelayed(this.mCallback, this.mLongPressDelaySupplier.getAsInt());
            }
        }

        boolean keyUp() {
            boolean z;
            synchronized (CarInputService.this.mLock) {
                this.mHandler.removeCallbacks(this.mCallback);
                this.mDown = false;
                z = this.mLongPress;
            }
            return z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onTimerExpired() {
            synchronized (CarInputService.this.mLock) {
                if (this.mDown) {
                    this.mLongPress = true;
                    this.mLongPressRunnable.run();
                }
            }
        }
    }

    public /* synthetic */ void lambda$new$0$CarInputService(CarUserManager.UserLifecycleEvent event) {
        Log.d(CarLog.TAG_INPUT, "CarInputService.onEvent(" + event + ")");
        if (2 == event.getEventType()) {
            updateRotaryServiceSettings(event.getUserId());
        }
    }

    private static ComponentName getDefaultInputComponent(Context context) {
        String carInputService = context.getString(R.string.inputService);
        if (TextUtils.isEmpty(carInputService)) {
            return null;
        }
        return ComponentName.unflattenFromString(carInputService);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getViewLongPressDelay(ContentResolver cr) {
        return Settings.Secure.getIntForUser(cr, "long_press_timeout", ViewConfiguration.getLongPressTimeout(), -2);
    }

    public CarInputService(final Context context, InputHalService inputHalService, CarUserService userService) {
        this(context, inputHalService, userService, new Handler(Looper.getMainLooper()), (TelecomManager) context.getSystemService(TelecomManager.class), new AssistUtils(context), new KeyEventListener() { // from class: com.android.car.-$$Lambda$CarInputService$E8Gt77iseTaHgCFonU9JhTT7zWg
            @Override // com.android.car.CarInputService.KeyEventListener
            public final void onKeyEvent(KeyEvent keyEvent) {
                ((InputManager) context.getSystemService(InputManager.class)).injectInputEvent(keyEvent, 0);
            }
        }, new Supplier() { // from class: com.android.car.-$$Lambda$CarInputService$E5hHzCn1MvjeLilFW0s8WXENkyc
            @Override // java.util.function.Supplier
            public final Object get() {
                return CallLog.Calls.getLastOutgoingCall(context);
            }
        }, getDefaultInputComponent(context), new IntSupplier() { // from class: com.android.car.-$$Lambda$CarInputService$NL3fBhY4XftHPLqHodYEh-vlF8g
            @Override // java.util.function.IntSupplier
            public final int getAsInt() {
                return CarInputService.getViewLongPressDelay(context.getContentResolver());
            }
        }, new BooleanSupplier() { // from class: com.android.car.-$$Lambda$CarInputService$e0yOE6dRUwrVaM3ZtuXV5OrWg-w
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return context.getResources().getBoolean(R.bool.config_callButtonEndsOngoingCall);
            }
        });
    }

    CarInputService(Context context, InputHalService inputHalService, CarUserService userService, Handler handler, TelecomManager telecomManager, AssistUtils assistUtils, KeyEventListener mainDisplayHandler, Supplier<String> lastCalledNumberSupplier, ComponentName customInputServiceComponent, IntSupplier longPressDelaySupplier, BooleanSupplier shouldCallButtonEndOngoingCallSupplier) {
        this.mShowCallback = new IVoiceInteractionSessionShowCallback.Stub() { // from class: com.android.car.CarInputService.1
            public void onFailed() {
                Log.w(CarLog.TAG_INPUT, "Failed to show VoiceInteractionSession");
            }

            public void onShown() {
            }
        };
        this.mLock = new Object();
        this.mProjectionKeyEventsSubscribed = new BitSet();
        this.mCarInputListenerBound = false;
        this.mHandledKeys = new SetMultimap<>();
        this.mCallback = new Binder() { // from class: com.android.car.CarInputService.2
            @Override // android.os.Binder
            protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) {
                if (code != 1) {
                    return false;
                }
                data.setDataPosition(0);
                CarInputHandlingService.InputFilter[] handledKeys = (CarInputHandlingService.InputFilter[]) data.createTypedArray(CarInputHandlingService.InputFilter.CREATOR);
                if (handledKeys != null) {
                    CarInputService.this.setHandledKeys(handledKeys);
                }
                return true;
            }
        };
        this.mInputServiceConnection = new ServiceConnection() { // from class: com.android.car.CarInputService.3
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder binder) {
                synchronized (CarInputService.this.mLock) {
                    CarInputService.this.mCarInputListener = ICarInputListener.Stub.asInterface(binder);
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
                Log.d(CarLog.TAG_INPUT, "onServiceDisconnected, name: " + name);
                synchronized (CarInputService.this.mLock) {
                    CarInputService.this.mCarInputListener = null;
                }
            }
        };
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mBluetoothProfileServiceListener = new BluetoothProfile.ServiceListener() { // from class: com.android.car.CarInputService.4
            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceConnected(int profile, BluetoothProfile proxy) {
                if (profile == 16) {
                    Log.d(CarLog.TAG_INPUT, "Bluetooth proxy connected for HEADSET_CLIENT profile");
                    synchronized (CarInputService.this.mLock) {
                        CarInputService.this.mBluetoothHeadsetClient = (BluetoothHeadsetClient) proxy;
                    }
                }
            }

            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceDisconnected(int profile) {
                if (profile == 16) {
                    Log.d(CarLog.TAG_INPUT, "Bluetooth proxy disconnected for HEADSET_CLIENT profile");
                    synchronized (CarInputService.this.mLock) {
                        CarInputService.this.mBluetoothHeadsetClient = null;
                    }
                }
            }
        };
        this.mUserLifecycleListener = new CarUserManager.UserLifecycleListener() { // from class: com.android.car.-$$Lambda$CarInputService$Bra7tkjqy3YN8-KZN-QyFq70IxE
            public final void onEvent(CarUserManager.UserLifecycleEvent userLifecycleEvent) {
                this.f$0.lambda$new$0$CarInputService(userLifecycleEvent);
            }
        };
        this.mContext = context;
        this.mCaptureController = new InputCaptureClientController(context);
        this.mInputHalService = inputHalService;
        this.mUserService = userService;
        this.mTelecomManager = telecomManager;
        this.mAssistUtils = assistUtils;
        this.mMainDisplayHandler = mainDisplayHandler;
        this.mLastCalledNumberSupplier = lastCalledNumberSupplier;
        this.mCustomInputServiceComponent = customInputServiceComponent;
        this.mLongPressDelaySupplier = longPressDelaySupplier;
        this.mVoiceKeyTimer = new KeyPressTimer(handler, longPressDelaySupplier, new Runnable() { // from class: com.android.car.-$$Lambda$CarInputService$L9JhV3ODz0L9p2ms-aiF7bgzD6c
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.handleVoiceAssistLongPress();
            }
        });
        this.mCallKeyTimer = new KeyPressTimer(handler, longPressDelaySupplier, new Runnable() { // from class: com.android.car.-$$Lambda$CarInputService$o3-C1DSyq366z7712pSu54NAEg8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.handleCallLongPress();
            }
        });
        this.mRotaryServiceComponentName = this.mContext.getString(R.string.rotaryService);
        this.mShouldCallButtonEndOngoingCallSupplier = shouldCallButtonEndOngoingCallSupplier;
    }

    void setHandledKeys(CarInputHandlingService.InputFilter[] handledKeys) {
        synchronized (this.mLock) {
            this.mHandledKeys.clear();
            for (CarInputHandlingService.InputFilter handledKey : handledKeys) {
                this.mHandledKeys.put(Integer.valueOf(handledKey.mTargetDisplay), Integer.valueOf(handledKey.mKeyCode));
            }
        }
    }

    public void setProjectionKeyEventHandler(CarProjectionManager.ProjectionKeyEventHandler listener, BitSet events) {
        synchronized (this.mLock) {
            this.mProjectionKeyEventHandler = listener;
            this.mProjectionKeyEventsSubscribed.clear();
            if (events != null) {
                this.mProjectionKeyEventsSubscribed.or(events);
            }
        }
    }

    public void setInstrumentClusterKeyListener(KeyEventListener listener) {
        synchronized (this.mLock) {
            this.mInstrumentClusterKeyListener = listener;
        }
    }

    @Override // com.android.car.CarServiceBase
    public void init() {
        if (!this.mInputHalService.isKeyInputSupported()) {
            Log.w(CarLog.TAG_INPUT, "Hal does not support key input.");
            return;
        }
        this.mInputHalService.setInputListener(this);
        synchronized (this.mLock) {
            this.mCarInputListenerBound = bindCarInputService();
        }
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter != null) {
            bluetoothAdapter.getProfileProxy(this.mContext, this.mBluetoothProfileServiceListener, 16);
        }
        if (!TextUtils.isEmpty(this.mRotaryServiceComponentName)) {
            this.mUserService.addUserLifecycleListener(this.mUserLifecycleListener);
        }
    }

    @Override // com.android.car.CarServiceBase
    public void release() {
        synchronized (this.mLock) {
            this.mProjectionKeyEventHandler = null;
            this.mProjectionKeyEventsSubscribed.clear();
            this.mInstrumentClusterKeyListener = null;
            if (this.mCarInputListenerBound) {
                this.mContext.unbindService(this.mInputServiceConnection);
                this.mCarInputListenerBound = false;
            }
            if (this.mBluetoothHeadsetClient != null) {
                this.mBluetoothAdapter.closeProfileProxy(16, this.mBluetoothHeadsetClient);
                this.mBluetoothHeadsetClient = null;
            }
        }
        if (!TextUtils.isEmpty(this.mRotaryServiceComponentName)) {
            this.mUserService.removeUserLifecycleListener(this.mUserLifecycleListener);
        }
    }

    @Override // com.android.car.hal.InputHalService.InputListener
    public void onKeyEvent(KeyEvent event, int targetDisplay) {
        ICarInputListener carInputListener;
        synchronized (this.mLock) {
            carInputListener = this.mCarInputListener;
        }
        if (carInputListener != null && isCustomEventHandler(event, targetDisplay)) {
            try {
                carInputListener.onKeyEvent(event, targetDisplay);
                return;
            } catch (RemoteException e) {
                Log.e(CarLog.TAG_INPUT, "Error while calling car input service", e);
                return;
            }
        }
        int keyCode = event.getKeyCode();
        if (keyCode == 5) {
            handleCallKey(event);
            return;
        }
        if (keyCode == 231) {
            handleVoiceAssistKey(event);
        } else if (targetDisplay == 1) {
            handleInstrumentClusterKey(event);
        } else {
            if (this.mCaptureController.onKeyEvent(0, event)) {
                return;
            }
            this.mMainDisplayHandler.onKeyEvent(event);
        }
    }

    @Override // com.android.car.hal.InputHalService.InputListener
    public void onRotaryEvent(RotaryEvent event, int targetDisplay) {
        if (!this.mCaptureController.onRotaryEvent(targetDisplay, event)) {
            List<KeyEvent> keyEvents = rotaryEventToKeyEvents(event);
            for (KeyEvent keyEvent : keyEvents) {
                onKeyEvent(keyEvent, targetDisplay);
            }
        }
    }

    private static List<KeyEvent> rotaryEventToKeyEvents(RotaryEvent event) {
        int keyCode;
        int numClicks = event.getNumberOfClicks();
        int numEvents = numClicks * 2;
        boolean clockwise = event.isClockwise();
        int inputType = event.getInputType();
        if (inputType != 10) {
            if (inputType == 11) {
                if (clockwise) {
                    keyCode = 24;
                } else {
                    keyCode = 25;
                }
            } else {
                Log.e(CarLog.TAG_INPUT, "Unknown rotary input type: " + event.getInputType());
                return Collections.EMPTY_LIST;
            }
        } else if (clockwise) {
            keyCode = MediaRouter.GlobalMediaRouter.CallbackHandler.MSG_ROUTE_PRESENTATION_DISPLAY_CHANGED;
        } else {
            keyCode = MediaRouter.GlobalMediaRouter.CallbackHandler.MSG_ROUTE_VOLUME_CHANGED;
        }
        ArrayList<KeyEvent> keyEvents = new ArrayList<>(numEvents);
        for (int i = 0; i < numClicks; i++) {
            long uptime = event.getUptimeMillisForClick(i);
            int i2 = keyCode;
            KeyEvent downEvent = createKeyEvent(true, uptime, uptime, i2);
            KeyEvent upEvent = createKeyEvent(false, uptime, uptime, i2);
            keyEvents.add(downEvent);
            keyEvents.add(upEvent);
        }
        return keyEvents;
    }

    private static KeyEvent createKeyEvent(boolean z, long j, long j2, int i) {
        return new KeyEvent(j, j2, !z ? 1 : 0, i, 0, 0, 0, 0, 0, 1);
    }

    public int requestInputEventCapture(ICarInputCallback callback, int targetDisplayType, int[] inputTypes, int requestFlags) {
        return this.mCaptureController.requestInputEventCapture(callback, targetDisplayType, inputTypes, requestFlags);
    }

    public void releaseInputEventCapture(ICarInputCallback callback, int targetDisplayType) {
        this.mCaptureController.releaseInputEventCapture(callback, targetDisplayType);
    }

    private boolean isCustomEventHandler(KeyEvent event, int targetDisplay) {
        boolean zContainsEntry;
        synchronized (this.mLock) {
            zContainsEntry = this.mHandledKeys.containsEntry(Integer.valueOf(targetDisplay), Integer.valueOf(event.getKeyCode()));
        }
        return zContainsEntry;
    }

    private void handleVoiceAssistKey(KeyEvent event) {
        int action = event.getAction();
        if (action == 0 && event.getRepeatCount() == 0) {
            this.mVoiceKeyTimer.keyDown();
            dispatchProjectionKeyEvent(0);
        } else if (action == 1) {
            if (this.mVoiceKeyTimer.keyUp()) {
                dispatchProjectionKeyEvent(3);
            } else {
                if (dispatchProjectionKeyEvent(1)) {
                    return;
                }
                launchDefaultVoiceAssistantHandler();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleVoiceAssistLongPress() {
        if (dispatchProjectionKeyEvent(2) || launchBluetoothVoiceRecognition()) {
            return;
        }
        launchDefaultVoiceAssistantHandler();
    }

    private void handleCallKey(KeyEvent event) {
        int action = event.getAction();
        if (action == 0 && event.getRepeatCount() == 0) {
            this.mCallKeyTimer.keyDown();
            dispatchProjectionKeyEvent(4);
            return;
        }
        if (action == 1) {
            if (this.mCallKeyTimer.keyUp()) {
                dispatchProjectionKeyEvent(7);
                return;
            }
            if (acceptCallIfRinging()) {
                return;
            }
            if ((this.mShouldCallButtonEndOngoingCallSupplier.getAsBoolean() && endCall()) || dispatchProjectionKeyEvent(5)) {
                return;
            }
            launchDialerHandler();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCallLongPress() {
        if (acceptCallIfRinging()) {
            return;
        }
        if ((this.mShouldCallButtonEndOngoingCallSupplier.getAsBoolean() && endCall()) || dispatchProjectionKeyEvent(6)) {
            return;
        }
        dialLastCallHandler();
    }

    private boolean dispatchProjectionKeyEvent(int event) {
        synchronized (this.mLock) {
            CarProjectionManager.ProjectionKeyEventHandler projectionKeyEventHandler = this.mProjectionKeyEventHandler;
            if (projectionKeyEventHandler != null && this.mProjectionKeyEventsSubscribed.get(event)) {
                projectionKeyEventHandler.onKeyEvent(event);
                return true;
            }
            return false;
        }
    }

    private void launchDialerHandler() {
        Log.i(CarLog.TAG_INPUT, "call key, launch dialer intent");
        Intent dialerIntent = new Intent("android.intent.action.DIAL");
        this.mContext.startActivityAsUser(dialerIntent, null, UserHandle.CURRENT_OR_SELF);
    }

    private void dialLastCallHandler() {
        Log.i(CarLog.TAG_INPUT, "call key, dialing last call");
        String lastNumber = this.mLastCalledNumberSupplier.get();
        if (!TextUtils.isEmpty(lastNumber)) {
            Intent callLastNumberIntent = new Intent("android.intent.action.CALL").setData(Uri.fromParts("tel", lastNumber, null)).setFlags(268435456);
            this.mContext.startActivityAsUser(callLastNumberIntent, null, UserHandle.CURRENT_OR_SELF);
        }
    }

    private boolean acceptCallIfRinging() {
        TelecomManager telecomManager = this.mTelecomManager;
        if (telecomManager != null && telecomManager.isRinging()) {
            Log.i(CarLog.TAG_INPUT, "call key while ringing. Answer the call!");
            this.mTelecomManager.acceptRingingCall();
            return true;
        }
        return false;
    }

    private boolean endCall() {
        TelecomManager telecomManager = this.mTelecomManager;
        if (telecomManager != null && telecomManager.isInCall()) {
            Log.i(CarLog.TAG_INPUT, "End the call!");
            this.mTelecomManager.endCall();
            return true;
        }
        return false;
    }

    private boolean isBluetoothVoiceRecognitionEnabled() {
        Resources res = this.mContext.getResources();
        return res.getBoolean(R.bool.enableLongPressBluetoothVoiceRecognition);
    }

    private boolean launchBluetoothVoiceRecognition() {
        synchronized (this.mLock) {
            if (this.mBluetoothHeadsetClient != null && isBluetoothVoiceRecognitionEnabled()) {
                List<BluetoothDevice> devices = this.mBluetoothHeadsetClient.getConnectedDevices();
                if (devices != null) {
                    for (BluetoothDevice device : devices) {
                        Bundle bundle = this.mBluetoothHeadsetClient.getCurrentAgFeatures(device);
                        if (bundle != null && bundle.getBoolean("android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_VOICE_RECOGNITION") && this.mBluetoothHeadsetClient.startVoiceRecognition(device)) {
                            Log.d(CarLog.TAG_INPUT, "started voice recognition on BT device at " + device.getAddress());
                            return true;
                        }
                    }
                }
                return false;
            }
            return false;
        }
    }

    private void launchDefaultVoiceAssistantHandler() {
        Log.i(CarLog.TAG_INPUT, "voice key, invoke AssistUtils");
        if (this.mAssistUtils.getAssistComponentForUser(ActivityManager.getCurrentUser()) == null) {
            Log.w(CarLog.TAG_INPUT, "Unable to retrieve assist component for current user");
            return;
        }
        Bundle args = new Bundle();
        args.putBoolean(EXTRA_CAR_PUSH_TO_TALK, true);
        this.mAssistUtils.showSessionForActiveService(args, 32, this.mShowCallback, (IBinder) null);
    }

    private void handleInstrumentClusterKey(KeyEvent event) {
        KeyEventListener listener;
        synchronized (this.mLock) {
            listener = this.mInstrumentClusterKeyListener;
        }
        if (listener == null) {
            return;
        }
        listener.onKeyEvent(event);
    }

    @Override // com.android.car.CarServiceBase
    public void dump(PrintWriter writer) {
        writer.println("*Input Service*");
        writer.println("mCustomInputServiceComponent: " + this.mCustomInputServiceComponent);
        synchronized (this.mLock) {
            writer.println("mCarInputListenerBound: " + this.mCarInputListenerBound);
            writer.println("mCarInputListener: " + this.mCarInputListener);
        }
        writer.println("Long-press delay: " + this.mLongPressDelaySupplier.getAsInt() + "ms");
        StringBuilder sb = new StringBuilder();
        sb.append("Call button ends ongoing call: ");
        sb.append(this.mShouldCallButtonEndOngoingCallSupplier.getAsBoolean());
        writer.println(sb.toString());
        this.mCaptureController.dump(writer);
    }

    private boolean bindCarInputService() {
        if (this.mCustomInputServiceComponent == null) {
            Log.i(CarLog.TAG_INPUT, "Custom input service was not configured");
            return false;
        }
        Log.d(CarLog.TAG_INPUT, "bindCarInputService, component: " + this.mCustomInputServiceComponent);
        Intent intent = new Intent();
        Bundle extras = new Bundle();
        extras.putBinder("callback_binder", this.mCallback);
        intent.putExtras(extras);
        intent.setComponent(this.mCustomInputServiceComponent);
        return this.mContext.bindService(intent, this.mInputServiceConnection, 1);
    }

    private void updateRotaryServiceSettings(int userId) {
        if (UserHelper.isHeadlessSystemUser(userId)) {
            return;
        }
        ContentResolver contentResolver = this.mContext.getContentResolver();
        Settings.Secure.putStringForUser(contentResolver, "enabled_accessibility_services", this.mRotaryServiceComponentName, userId);
        Settings.Secure.putStringForUser(contentResolver, "accessibility_enabled", "1", userId);
    }
}

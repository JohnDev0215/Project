package com.google.android.projection.protocol;

import android.os.Handler;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.projection.proto.Common;
import com.google.android.projection.proto.Protos;
import com.google.android.projection.protocol.InputOutputThreads;
import com.google.android.projection.protocol.Transport;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class GalReceiver implements NativeObject, ControllerCallbacks {
    public static final int BYEBYE_REASON_DEVICE_SWITCH = 2;
    public static final int BYEBYE_REASON_NOT_CURRENTLY_SUPPORTED = 4;
    public static final int BYEBYE_REASON_NOT_SUPPORTED = 3;
    public static final int BYEBYE_REASON_PROBE_SUPPORTED = 5;
    public static final int BYEBYE_REASON_USER_SELECTION = 1;
    public static final int DRIVER_SIDE_CENTER = 2;
    public static final int DRIVER_SIDE_LEFT = 0;
    public static final int DRIVER_SIDE_RIGHT = 1;
    private static final String TAG = "AHU.GAL_RECEIVER";
    private static final boolean VERBOSE_LOGGING_ENABLED = false;
    private final Handler handler;
    private final AppMessageListener mAppMessageListener;
    private final AudioFocusListener mAudioFocusListener;
    private final ByeByeHandler mByeByeHandler;
    private final InputOutputThreads mGalio;
    private long mNativeGalReceiver;
    private final PhoneInfoListener mPhoneInfoListener;
    private final ProjectedModeIconReceiver mProjectedModeIconReceiver;
    private final SparseArray<CarServiceProvider> mRegisteredServices;
    private final AtomicBoolean mStopping;
    public static final int AUDIO_FOCUS_STATE_GAIN = Protos.AudioFocusStateType.AUDIO_FOCUS_STATE_GAIN.getNumber();
    public static final int AUDIO_FOCUS_STATE_LOSS = Protos.AudioFocusStateType.AUDIO_FOCUS_STATE_LOSS.getNumber();
    public static final int AUDIO_FOCUS_STATE_LOSS_TRANSIENT_CAN_DUCK = Protos.AudioFocusStateType.AUDIO_FOCUS_STATE_LOSS_TRANSIENT_CAN_DUCK.getNumber();
    public static final int AUDIO_FOCUS_STATE_LOSS_TRANSIENT = Protos.AudioFocusStateType.AUDIO_FOCUS_STATE_LOSS_TRANSIENT.getNumber();
    public static final int AUDIO_FOCUS_STATE_GAIN_TRANSIENT = Protos.AudioFocusStateType.AUDIO_FOCUS_STATE_GAIN_TRANSIENT.getNumber();
    public static final int AUDIO_FOCUS_STATE_GAIN_MEDIA_ONLY = Protos.AudioFocusStateType.AUDIO_FOCUS_STATE_GAIN_MEDIA_ONLY.getNumber();
    public static final int AUDIO_FOCUS_STATE_GAIN_TRANSIENT_GUIDANCE_ONLY = Protos.AudioFocusStateType.AUDIO_FOCUS_STATE_GAIN_TRANSIENT_GUIDANCE_ONLY.getNumber();
    public static final int AUDIO_FOCUS_RELEASE = Protos.AudioFocusRequestType.AUDIO_FOCUS_RELEASE.getNumber();
    public static final int AUDIO_FOCUS_GAIN = Protos.AudioFocusRequestType.AUDIO_FOCUS_GAIN.getNumber();
    public static final int AUDIO_FOCUS_GAIN_TRANSIENT = Protos.AudioFocusRequestType.AUDIO_FOCUS_GAIN_TRANSIENT.getNumber();
    public static final int AUDIO_FOCUS_GAIN_TRANSIENT_MAY_DUCK = Protos.AudioFocusRequestType.AUDIO_FOCUS_GAIN_TRANSIENT_MAY_DUCK.getNumber();
    public static final int VOICE_RECOGNITION_STATE_STARTED = Protos.VoiceSessionStatus.VOICE_SESSION_START.getNumber();
    public static final int VOICE_RECOGNITION_STATE_ENDED = Protos.VoiceSessionStatus.VOICE_SESSION_END.getNumber();
    public static final int NAVIGATION_FOCUS_STATE_NATIVE = Protos.NavFocusType.NAV_FOCUS_NATIVE.getNumber();
    public static final int NAVIGATION_FOCUS_STATE_PROJECTED = Protos.NavFocusType.NAV_FOCUS_PROJECTED.getNumber();

    public interface AppMessageListener {
        void onBatteryStatusNotification(int i, int i2, boolean z);

        void onDisconnected(Transport.StopReason stopReason);

        void onNavigationFocusRequest(int i);

        void onPoorWirelessNetworkConnectivity();

        void onUnrecoverableError(Protos.MessageStatus messageStatus);

        void onVoiceSessionNotification(int i);
    }

    public interface AudioFocusListener {
        void onAudioFocusRequest(int i);
    }

    public interface ByeByeHandler {
        void onByeByeRequest(int i);

        void onByeByeResponse();
    }

    public interface PhoneInfoListener {
        void onPhoneInfoAvailable(Common.PhoneInfo phoneInfo, String str);
    }

    public interface ProjectedModeIconReceiver {
        void setProjectedModeIcon(byte[] bArr, String str);
    }

    public interface UserSwitchListener {
        void onCarConnectedDevicesRequest();

        void onUserSwitchRequest(ConnectedDevice connectedDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeFlushControllerChannelQueue();

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeGetAdditionalBytesToRead(byte[] bArr);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeGetEncodedFrame(byte[] bArr);

    private native void nativeInit();

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativePrepareShutdown();

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeQueueIncoming(byte[] bArr, int i);

    private native boolean nativeRegister(long j);

    private native void nativeSendByeByeRequest(int i);

    private native void nativeSendByeByeResponse();

    private native void nativeSendCarConnectedDevices(List<ConnectedDevice> list, boolean z, boolean z2);

    private native void nativeSendUserSwitchResponse(int i, ConnectedDevice connectedDevice);

    private native void nativeSetAudioFocus(int i, boolean z);

    private native void nativeSetCallAvailability(boolean z);

    private native void nativeSetCarInfo(CarInfo carInfo);

    private native boolean nativeSetCreds(String str, String str2, String str3);

    private native void nativeSetHeadUnitInfo(String str, String str2, String str3, String str4);

    private native void nativeSetIdentityInfo(String str, String str2, String str3, String str4);

    private native void nativeSetNavFocus(int i);

    private native boolean nativeSetTransportConfigurationListener(ConnectionConfigurationCallbacks connectionConfigurationCallbacks);

    private native void nativeShutdown();

    private native void nativeStart();

    public static class CarInfo {
        public boolean canPlayNativeMediaDuringVr;
        public String displayName;
        public int driverPosition;
        public String headUnitMake;
        public String headUnitModel;
        public String headUnitSoftwareBuild;
        public String headUnitSoftwareVersion;
        public String manufacturer;
        public String model;
        public String modelYear;
        public int sessionConfiguration;
        public String vehicleId;

        public CarInfo(String manufacturer, String model, String modelYear, String vehicleId, int driverPosition, String headUnitMake, String headUnitModel, String headUnitSoftwareBuild, String headUnitSoftwareVersion, boolean canPlayNativeMediaDuringVr, int sessionConfiguration, String displayName) {
            this.manufacturer = manufacturer;
            this.model = model;
            this.modelYear = modelYear;
            this.vehicleId = vehicleId;
            this.driverPosition = driverPosition;
            this.headUnitMake = headUnitMake;
            this.headUnitModel = headUnitModel;
            this.headUnitSoftwareBuild = headUnitSoftwareBuild;
            this.headUnitSoftwareVersion = headUnitSoftwareVersion;
            this.canPlayNativeMediaDuringVr = canPlayNativeMediaDuringVr;
            this.sessionConfiguration = sessionConfiguration;
            this.displayName = displayName;
        }
    }

    public static class ConnectedDevice {
        public final int deviceId;
        public final String deviceName;

        public ConnectedDevice(String deviceName, int deviceId) {
            this.deviceName = deviceName;
            this.deviceId = deviceId;
        }
    }

    static {
        ReceiverLibJni.load();
    }

    public GalReceiver(AppMessageListener appMessageListener, AudioFocusListener audioFocusListener, ProjectedModeIconReceiver projectedModeIconReceiver, ByeByeHandler byeByeHandler, Handler handler, CarInfo carInfo, String rootCert, String clientCert, String privateKey) {
        this(appMessageListener, audioFocusListener, projectedModeIconReceiver, new PhoneInfoListener() { // from class: com.google.android.projection.protocol.-$$Lambda$GalReceiver$k9xpn3VczDJ23gT6pDhxnQOi2o0
            @Override // com.google.android.projection.protocol.GalReceiver.PhoneInfoListener
            public final void onPhoneInfoAvailable(Common.PhoneInfo phoneInfo, String str) {
                GalReceiver.lambda$new$0(phoneInfo, str);
            }
        }, byeByeHandler, handler, carInfo, rootCert, clientCert, privateKey);
    }

    static /* synthetic */ void lambda$new$0(Common.PhoneInfo phoneInfo, String deviceName) {
    }

    public GalReceiver(AppMessageListener appMessageListener, AudioFocusListener audioFocusListener, ProjectedModeIconReceiver projectedModeIconReceiver, PhoneInfoListener phoneInfoListener, ByeByeHandler byeByeHandler, Handler handler, CarInfo carInfo, String rootCert, String clientCert, String privateKey) {
        this.mRegisteredServices = new SparseArray<>();
        this.mStopping = new AtomicBoolean();
        this.mAppMessageListener = appMessageListener;
        this.mAudioFocusListener = audioFocusListener;
        this.mProjectedModeIconReceiver = projectedModeIconReceiver;
        this.mPhoneInfoListener = phoneInfoListener;
        this.mByeByeHandler = byeByeHandler;
        this.handler = handler;
        this.mGalio = new InputOutputThreads(handler, new FrameParser(this));
        nativeInit();
        if (!nativeSetCreds(rootCert, clientCert, privateKey)) {
            throw new RuntimeException("SSL initialization failed");
        }
        nativeSetCarInfo(carInfo);
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public synchronized long getNativeInstance() {
        return this.mNativeGalReceiver;
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public synchronized void destroy() {
        destroyCarServices();
        nativeShutdown();
    }

    public synchronized void start(Transport transport) {
        if (this.mStopping.get()) {
            throw new RuntimeException("can't be started after teardown");
        }
        startCarServices();
        nativeSetTransportConfigurationListener(transport);
        nativeStart();
        this.mGalio.start(transport);
    }

    public synchronized void stop(Transport.StopReason reason) {
        this.mGalio.stop(reason);
    }

    public synchronized boolean registerCarService(int serviceId, CarServiceProvider service) {
        if (this.mStopping.get()) {
            throw new RuntimeException("can't register services after teardown");
        }
        if (this.mRegisteredServices.get(serviceId) != null) {
            throw new IllegalArgumentException("this service ID is already taken");
        }
        this.mRegisteredServices.put(serviceId, service);
        if (!service.create(serviceId, this.mNativeGalReceiver)) {
            Log.e("AHU.GAL_RECEIVER", "Service " + serviceId + " failed to init.");
            return VERBOSE_LOGGING_ENABLED;
        }
        return true;
    }

    @Override // com.google.android.projection.protocol.ControllerCallbacks
    public void unrecoverableErrorCallback(final int err) {
        this.handler.post(new Runnable() { // from class: com.google.android.projection.protocol.GalReceiver.1
            @Override // java.lang.Runnable
            public void run() {
                Log.d("AHU.GAL_RECEIVER", "Encountered unrecoverable error!");
                GalReceiver.this.mAppMessageListener.onUnrecoverableError(Protos.MessageStatus.forNumber(err));
            }
        });
    }

    @Override // com.google.android.projection.protocol.ControllerCallbacks
    public void serviceDiscoveryRequestCallback(byte[] smallIcon, byte[] mediumIcon, byte[] largeIcon, String label, String deviceName, String instanceId) {
        this.mProjectedModeIconReceiver.setProjectedModeIcon(mediumIcon, label);
        Common.PhoneInfo.Builder phoneInfoBuilder = Common.PhoneInfo.newBuilder();
        if (instanceId != null) {
            phoneInfoBuilder.setInstanceId(instanceId);
        }
        this.mPhoneInfoListener.onPhoneInfoAvailable((Common.PhoneInfo) phoneInfoBuilder.build(), deviceName);
    }

    @Override // com.google.android.projection.protocol.ControllerCallbacks
    public void bugreportRequestCallback(long timestamp) {
    }

    public synchronized void sendNavigationFocusState(int state) {
        nativeSetNavFocus(state);
    }

    @Override // com.google.android.projection.protocol.ControllerCallbacks
    public void navigationFocusCallback(int focusType) {
        Log.i("AHU.GAL_RECEIVER", "navigation focus request from phone");
        this.mAppMessageListener.onNavigationFocusRequest(focusType);
    }

    @Override // com.google.android.projection.protocol.ControllerCallbacks
    public void byeByeRequestCallback(int reason) {
        Log.i("AHU.GAL_RECEIVER", "ByeByeRequest from phone, reason:" + reason);
        ByeByeHandler byeByeHandler = this.mByeByeHandler;
        if (byeByeHandler != null) {
            byeByeHandler.onByeByeRequest(reason);
        }
    }

    @Override // com.google.android.projection.protocol.ControllerCallbacks
    public void byeByeResponseCallback() {
        Log.i("AHU.GAL_RECEIVER", "ByeByeResponse from phone");
        ByeByeHandler byeByeHandler = this.mByeByeHandler;
        if (byeByeHandler != null) {
            byeByeHandler.onByeByeResponse();
        }
    }

    @Override // com.google.android.projection.protocol.ControllerCallbacks
    public void voiceSessionNotificationCallback(int status) {
        Log.i("AHU.GAL_RECEIVER", "Voice session notification, status = " + status);
        this.mAppMessageListener.onVoiceSessionNotification(status);
    }

    public synchronized void sendByeByeRequest(int code) {
        nativeSendByeByeRequest(code);
    }

    public synchronized void sendByeByeResponse() {
        nativeSendByeByeResponse();
    }

    @Deprecated
    public void sendCarConnectedDevices(List<ConnectedDevice> connectedDevices, boolean unsolicited, boolean finalList) {
        nativeSendCarConnectedDevices(connectedDevices, unsolicited, finalList);
    }

    public void sendUserSwitchResponse(int status, ConnectedDevice connectedDevice) {
        nativeSendUserSwitchResponse(status, connectedDevice);
    }

    @Override // com.google.android.projection.protocol.ControllerCallbacks
    public void carConnectedDevicesRequestCallback() {
        Log.w("AHU.GAL_RECEIVER", "carConnectedDevicesRequestCallback: user switch is not support, ignored.");
    }

    @Override // com.google.android.projection.protocol.ControllerCallbacks
    public void userSwitchRequestCallback(ConnectedDevice selectedDevice) {
        Log.w("AHU.GAL_RECEIVER", "userSwitchRequestCallback: user switch is not support, ignored.");
    }

    @Override // com.google.android.projection.protocol.ControllerCallbacks
    public void batteryStatusNotificationCallback(int batteryLevel, int timeRemainingS, boolean batteryCritical) {
        this.mAppMessageListener.onBatteryStatusNotification(batteryLevel, timeRemainingS, batteryCritical);
    }

    public synchronized void setAudioFocus(int focusState, boolean unsolicited) {
        nativeSetAudioFocus(focusState, unsolicited);
    }

    public synchronized void setCallAvailability(boolean callAvailable) {
        nativeSetCallAvailability(callAvailable);
    }

    @Override // com.google.android.projection.protocol.ControllerCallbacks
    public void audioFocusRequestCallback(int request) {
        this.mAudioFocusListener.onAudioFocusRequest(request);
    }

    @Override // com.google.android.projection.protocol.ControllerCallbacks
    public void highLatencyCallback(int[] latencies) {
        this.handler.post(new Runnable() { // from class: com.google.android.projection.protocol.GalReceiver.2
            @Override // java.lang.Runnable
            public void run() {
                GalReceiver.this.mAppMessageListener.onPoorWirelessNetworkConnectivity();
            }
        });
    }

    @Override // com.google.android.projection.protocol.ControllerCallbacks
    public void latencyBackToNormalCallback(int[] latencies) {
    }

    private boolean startCarServices() {
        int failedCount = 0;
        for (int i = 0; i < this.mRegisteredServices.size(); i++) {
            int serviceId = this.mRegisteredServices.keyAt(i);
            CarServiceProvider service = this.mRegisteredServices.get(serviceId);
            if (!nativeRegister(service.getNativeInstance())) {
                Log.e("AHU.GAL_RECEIVER", "Service " + serviceId + " failed to register.");
                failedCount++;
            }
        }
        if (failedCount < this.mRegisteredServices.size()) {
            return true;
        }
        return VERBOSE_LOGGING_ENABLED;
    }

    private void destroyCarServices() {
        for (int i = 0; i < this.mRegisteredServices.size(); i++) {
            int serviceId = this.mRegisteredServices.keyAt(i);
            CarServiceProvider service = this.mRegisteredServices.get(serviceId);
            service.destroy();
        }
        this.mRegisteredServices.clear();
    }

    private static class FrameParser implements InputOutputThreads.FrameParser {
        private final GalReceiver galReceiver;
        private Runnable mOnQueueFlushed;

        public FrameParser(GalReceiver galReceiver) {
            this.galReceiver = galReceiver;
        }

        @Override // com.google.android.projection.protocol.InputOutputThreads.FrameParser
        public void onTerminating(Runnable onQueueFlushedAction) {
            this.galReceiver.mStopping.set(true);
            if (onQueueFlushedAction != null) {
                Log.i("AHU.GAL_RECEIVER", "onTerminating, flushing controller queue");
                this.mOnQueueFlushed = onQueueFlushedAction;
                this.galReceiver.nativeFlushControllerChannelQueue();
            } else {
                Log.i("AHU.GAL_RECEIVER", "onTerminating, prepare shutdown");
                this.galReceiver.nativePrepareShutdown();
            }
        }

        @Override // com.google.android.projection.protocol.InputOutputThreads.FrameParser
        public void onThreadsTerminated(Transport.StopReason stopReason) {
            this.galReceiver.mAppMessageListener.onDisconnected(stopReason);
        }

        @Override // com.google.android.projection.protocol.InputOutputThreads.FrameParser
        public int getFrameSizeToRead(byte[] header, int length) {
            return this.galReceiver.nativeGetAdditionalBytesToRead(header);
        }

        @Override // com.google.android.projection.protocol.InputOutputThreads.FrameParser
        public void enqueueIncomingFrame(byte[] frame, int length) {
            this.galReceiver.nativeQueueIncoming(frame, length);
        }

        @Override // com.google.android.projection.protocol.InputOutputThreads.FrameParser
        public int dequeueOutgoingFrame(byte[] buffer) {
            int len = this.galReceiver.nativeGetEncodedFrame(buffer);
            if (len == 0 && this.galReceiver.mStopping.get() && this.mOnQueueFlushed != null) {
                this.galReceiver.nativePrepareShutdown();
                Log.i("AHU.GAL_RECEIVER", "Controller queue is empty");
                this.mOnQueueFlushed.run();
            }
            return len;
        }
    }
}

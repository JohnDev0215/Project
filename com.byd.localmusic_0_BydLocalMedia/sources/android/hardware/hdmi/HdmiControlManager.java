package android.hardware.hdmi;

import android.annotation.SystemApi;
import android.hardware.hdmi.IHdmiCecVolumeControlFeatureListener;
import android.hardware.hdmi.IHdmiControlStatusChangeListener;
import android.hardware.hdmi.IHdmiHotplugEventListener;
import android.os.Binder;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.util.ArrayMap;
import android.util.Log;
import com.android.internal.os.RoSystemProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class HdmiControlManager {
    public static final String ACTION_OSD_MESSAGE = "android.hardware.hdmi.action.OSD_MESSAGE";
    public static final int AVR_VOLUME_MUTED = 101;
    public static final int CLEAR_TIMER_STATUS_CEC_DISABLE = 162;
    public static final int CLEAR_TIMER_STATUS_CHECK_RECORDER_CONNECTION = 160;
    public static final int CLEAR_TIMER_STATUS_FAIL_TO_CLEAR_SELECTED_SOURCE = 161;
    public static final int CLEAR_TIMER_STATUS_TIMER_CLEARED = 128;
    public static final int CLEAR_TIMER_STATUS_TIMER_NOT_CLEARED_NO_INFO_AVAILABLE = 2;
    public static final int CLEAR_TIMER_STATUS_TIMER_NOT_CLEARED_NO_MATCHING = 1;
    public static final int CLEAR_TIMER_STATUS_TIMER_NOT_CLEARED_RECORDING = 0;
    public static final int CONTROL_STATE_CHANGED_REASON_SETTING = 1;
    public static final int CONTROL_STATE_CHANGED_REASON_STANDBY = 3;
    public static final int CONTROL_STATE_CHANGED_REASON_START = 0;
    public static final int CONTROL_STATE_CHANGED_REASON_WAKEUP = 2;
    public static final int DEVICE_EVENT_ADD_DEVICE = 1;
    public static final int DEVICE_EVENT_REMOVE_DEVICE = 2;
    public static final int DEVICE_EVENT_UPDATE_DEVICE = 3;
    public static final String EXTRA_MESSAGE_EXTRA_PARAM1 = "android.hardware.hdmi.extra.MESSAGE_EXTRA_PARAM1";
    public static final String EXTRA_MESSAGE_ID = "android.hardware.hdmi.extra.MESSAGE_ID";
    private static final int INVALID_PHYSICAL_ADDRESS = 65535;
    public static final int ONE_TOUCH_RECORD_ALREADY_RECORDING = 18;
    public static final int ONE_TOUCH_RECORD_CEC_DISABLED = 51;
    public static final int ONE_TOUCH_RECORD_CHECK_RECORDER_CONNECTION = 49;
    public static final int ONE_TOUCH_RECORD_DISALLOW_TO_COPY = 13;
    public static final int ONE_TOUCH_RECORD_DISALLOW_TO_FUTHER_COPIES = 14;
    public static final int ONE_TOUCH_RECORD_FAIL_TO_RECORD_DISPLAYED_SCREEN = 50;
    public static final int ONE_TOUCH_RECORD_INVALID_EXTERNAL_PHYSICAL_ADDRESS = 10;
    public static final int ONE_TOUCH_RECORD_INVALID_EXTERNAL_PLUG_NUMBER = 9;
    public static final int ONE_TOUCH_RECORD_MEDIA_PROBLEM = 21;
    public static final int ONE_TOUCH_RECORD_MEDIA_PROTECTED = 19;
    public static final int ONE_TOUCH_RECORD_NOT_ENOUGH_SPACE = 22;
    public static final int ONE_TOUCH_RECORD_NO_MEDIA = 16;
    public static final int ONE_TOUCH_RECORD_NO_OR_INSUFFICIENT_CA_ENTITLEMENTS = 12;
    public static final int ONE_TOUCH_RECORD_NO_SOURCE_SIGNAL = 20;
    public static final int ONE_TOUCH_RECORD_OTHER_REASON = 31;
    public static final int ONE_TOUCH_RECORD_PARENT_LOCK_ON = 23;
    public static final int ONE_TOUCH_RECORD_PLAYING = 17;
    public static final int ONE_TOUCH_RECORD_PREVIOUS_RECORDING_IN_PROGRESS = 48;
    public static final int ONE_TOUCH_RECORD_RECORDING_ALREADY_TERMINATED = 27;
    public static final int ONE_TOUCH_RECORD_RECORDING_ANALOGUE_SERVICE = 3;
    public static final int ONE_TOUCH_RECORD_RECORDING_CURRENTLY_SELECTED_SOURCE = 1;
    public static final int ONE_TOUCH_RECORD_RECORDING_DIGITAL_SERVICE = 2;
    public static final int ONE_TOUCH_RECORD_RECORDING_EXTERNAL_INPUT = 4;
    public static final int ONE_TOUCH_RECORD_RECORDING_TERMINATED_NORMALLY = 26;
    public static final int ONE_TOUCH_RECORD_UNABLE_ANALOGUE_SERVICE = 6;
    public static final int ONE_TOUCH_RECORD_UNABLE_DIGITAL_SERVICE = 5;
    public static final int ONE_TOUCH_RECORD_UNABLE_SELECTED_SERVICE = 7;
    public static final int ONE_TOUCH_RECORD_UNSUPPORTED_CA = 11;
    public static final int OSD_MESSAGE_ARC_CONNECTED_INVALID_PORT = 1;
    public static final int OSD_MESSAGE_AVR_VOLUME_CHANGED = 2;
    public static final int POWER_STATUS_ON = 0;
    public static final int POWER_STATUS_STANDBY = 1;
    public static final int POWER_STATUS_TRANSIENT_TO_ON = 2;
    public static final int POWER_STATUS_TRANSIENT_TO_STANDBY = 3;
    public static final int POWER_STATUS_UNKNOWN = -1;

    @Deprecated
    public static final int RESULT_ALREADY_IN_PROGRESS = 4;
    public static final int RESULT_COMMUNICATION_FAILED = 7;
    public static final int RESULT_EXCEPTION = 5;
    public static final int RESULT_INCORRECT_MODE = 6;
    public static final int RESULT_SOURCE_NOT_AVAILABLE = 2;
    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_TARGET_NOT_AVAILABLE = 3;
    public static final int RESULT_TIMEOUT = 1;
    private static final String TAG = "HdmiControlManager";
    public static final int TIMER_RECORDING_RESULT_EXTRA_CEC_DISABLED = 3;
    public static final int TIMER_RECORDING_RESULT_EXTRA_CHECK_RECORDER_CONNECTION = 1;
    public static final int TIMER_RECORDING_RESULT_EXTRA_FAIL_TO_RECORD_SELECTED_SOURCE = 2;
    public static final int TIMER_RECORDING_RESULT_EXTRA_NO_ERROR = 0;
    public static final int TIMER_RECORDING_TYPE_ANALOGUE = 2;
    public static final int TIMER_RECORDING_TYPE_DIGITAL = 1;
    public static final int TIMER_RECORDING_TYPE_EXTERNAL = 3;
    public static final int TIMER_STATUS_MEDIA_INFO_NOT_PRESENT = 2;
    public static final int TIMER_STATUS_MEDIA_INFO_PRESENT_NOT_PROTECTED = 0;
    public static final int TIMER_STATUS_MEDIA_INFO_PRESENT_PROTECTED = 1;
    public static final int TIMER_STATUS_NOT_PROGRAMMED_CA_NOT_SUPPORTED = 6;
    public static final int TIMER_STATUS_NOT_PROGRAMMED_CLOCK_FAILURE = 10;
    public static final int TIMER_STATUS_NOT_PROGRAMMED_DATE_OUT_OF_RANGE = 2;
    public static final int TIMER_STATUS_NOT_PROGRAMMED_DUPLICATED = 14;
    public static final int TIMER_STATUS_NOT_PROGRAMMED_INVALID_EXTERNAL_PHYSICAL_NUMBER = 5;
    public static final int TIMER_STATUS_NOT_PROGRAMMED_INVALID_EXTERNAL_PLUG_NUMBER = 4;
    public static final int TIMER_STATUS_NOT_PROGRAMMED_INVALID_SEQUENCE = 3;
    public static final int TIMER_STATUS_NOT_PROGRAMMED_NO_CA_ENTITLEMENTS = 7;
    public static final int TIMER_STATUS_NOT_PROGRAMMED_NO_FREE_TIME = 1;
    public static final int TIMER_STATUS_NOT_PROGRAMMED_PARENTAL_LOCK_ON = 9;
    public static final int TIMER_STATUS_NOT_PROGRAMMED_UNSUPPORTED_RESOLUTION = 8;
    public static final int TIMER_STATUS_PROGRAMMED_INFO_ENOUGH_SPACE = 8;
    public static final int TIMER_STATUS_PROGRAMMED_INFO_MIGHT_NOT_ENOUGH_SPACE = 11;
    public static final int TIMER_STATUS_PROGRAMMED_INFO_NOT_ENOUGH_SPACE = 9;
    public static final int TIMER_STATUS_PROGRAMMED_INFO_NO_MEDIA_INFO = 10;
    private final boolean mHasAudioSystemDevice;
    private final boolean mHasPlaybackDevice;
    private final boolean mHasSwitchDevice;
    private final boolean mHasTvDevice;
    private final boolean mIsSwitchDevice;
    private final IHdmiControlService mService;
    private int mLocalPhysicalAddress = 65535;
    private final Object mLock = new Object();
    private final ArrayMap<HotplugEventListener, IHdmiHotplugEventListener> mHotplugEventListeners = new ArrayMap<>();
    private final ArrayMap<HdmiControlStatusChangeListener, IHdmiControlStatusChangeListener> mHdmiControlStatusChangeListeners = new ArrayMap<>();
    private final ArrayMap<HdmiCecVolumeControlFeatureListener, IHdmiCecVolumeControlFeatureListener> mHdmiCecVolumeControlFeatureListeners = new ArrayMap<>();

    @SystemApi
    public @interface ControlCallbackResult {
    }

    public interface HdmiCecVolumeControlFeatureListener {
        void onHdmiCecVolumeControlFeature(boolean z);
    }

    public interface HdmiControlStatusChangeListener {
        void onStatusChange(boolean z, boolean z2);
    }

    @SystemApi
    public interface HotplugEventListener {
        void onReceived(HdmiHotplugEvent hdmiHotplugEvent);
    }

    @SystemApi
    public interface VendorCommandListener {
        void onControlStateChanged(boolean z, int i);

        void onReceived(int i, int i2, byte[] bArr, boolean z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLocalPhysicalAddress(int i) {
        synchronized (this.mLock) {
            this.mLocalPhysicalAddress = i;
        }
    }

    private int getLocalPhysicalAddress() {
        int i;
        synchronized (this.mLock) {
            i = this.mLocalPhysicalAddress;
        }
        return i;
    }

    public HdmiControlManager(IHdmiControlService iHdmiControlService) {
        int[] supportedTypes;
        this.mService = iHdmiControlService;
        if (iHdmiControlService != null) {
            try {
                supportedTypes = iHdmiControlService.getSupportedTypes();
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            supportedTypes = null;
        }
        this.mHasTvDevice = hasDeviceType(supportedTypes, 0);
        this.mHasPlaybackDevice = hasDeviceType(supportedTypes, 4);
        this.mHasAudioSystemDevice = hasDeviceType(supportedTypes, 5);
        this.mHasSwitchDevice = hasDeviceType(supportedTypes, 6);
        this.mIsSwitchDevice = SystemProperties.getBoolean(RoSystemProperties.PROPERTY_HDMI_IS_DEVICE_HDMI_CEC_SWITCH, false);
        addHotplugEventListener(new ClientHotplugEventListener());
    }

    private final class ClientHotplugEventListener implements HotplugEventListener {
        private ClientHotplugEventListener() {
        }

        @Override // android.hardware.hdmi.HdmiControlManager.HotplugEventListener
        public void onReceived(HdmiHotplugEvent hdmiHotplugEvent) {
            new ArrayList();
            try {
                List<HdmiPortInfo> portInfo = HdmiControlManager.this.mService.getPortInfo();
                if (portInfo.isEmpty()) {
                    Log.e(HdmiControlManager.TAG, "Can't find port info, not updating connected status. Hotplug event:" + hdmiHotplugEvent);
                    return;
                }
                for (HdmiPortInfo hdmiPortInfo : portInfo) {
                    if (hdmiPortInfo.getId() == hdmiHotplugEvent.getPort()) {
                        if (hdmiPortInfo.getType() == 1) {
                            HdmiControlManager.this.setLocalPhysicalAddress(hdmiHotplugEvent.isConnected() ? hdmiPortInfo.getAddress() : 65535);
                            return;
                        }
                        return;
                    }
                }
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    private static boolean hasDeviceType(int[] iArr, int i) {
        if (iArr == null) {
            return false;
        }
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    @SystemApi
    public HdmiClient getClient(int i) {
        if (this.mService == null) {
            return null;
        }
        if (i == 0) {
            if (this.mHasTvDevice) {
                return new HdmiTvClient(this.mService);
            }
            return null;
        }
        if (i == 4) {
            if (this.mHasPlaybackDevice) {
                return new HdmiPlaybackClient(this.mService);
            }
            return null;
        }
        if (i == 5) {
            if (this.mHasAudioSystemDevice) {
                return new HdmiAudioSystemClient(this.mService);
            }
            return null;
        }
        if (i != 6) {
            return null;
        }
        if (this.mHasSwitchDevice || this.mIsSwitchDevice) {
            return new HdmiSwitchClient(this.mService);
        }
        return null;
    }

    @SystemApi
    public HdmiPlaybackClient getPlaybackClient() {
        return (HdmiPlaybackClient) getClient(4);
    }

    @SystemApi
    public HdmiTvClient getTvClient() {
        return (HdmiTvClient) getClient(0);
    }

    public HdmiAudioSystemClient getAudioSystemClient() {
        return (HdmiAudioSystemClient) getClient(5);
    }

    public HdmiSwitchClient getSwitchClient() {
        return (HdmiSwitchClient) getClient(6);
    }

    @SystemApi
    public List<HdmiDeviceInfo> getConnectedDevices() {
        try {
            return this.mService.getDeviceList();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    @Deprecated
    public List<HdmiDeviceInfo> getConnectedDevicesList() {
        try {
            return this.mService.getDeviceList();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void powerOffDevice(HdmiDeviceInfo hdmiDeviceInfo) {
        Objects.requireNonNull(hdmiDeviceInfo);
        try {
            this.mService.powerOffRemoteDevice(hdmiDeviceInfo.getLogicalAddress(), hdmiDeviceInfo.getDevicePowerStatus());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    @Deprecated
    public void powerOffRemoteDevice(HdmiDeviceInfo hdmiDeviceInfo) {
        Objects.requireNonNull(hdmiDeviceInfo);
        try {
            this.mService.powerOffRemoteDevice(hdmiDeviceInfo.getLogicalAddress(), hdmiDeviceInfo.getDevicePowerStatus());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void powerOnDevice(HdmiDeviceInfo hdmiDeviceInfo) {
        Objects.requireNonNull(hdmiDeviceInfo);
        try {
            this.mService.powerOnRemoteDevice(hdmiDeviceInfo.getLogicalAddress(), hdmiDeviceInfo.getDevicePowerStatus());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    @Deprecated
    public void powerOnRemoteDevice(HdmiDeviceInfo hdmiDeviceInfo) {
        Objects.requireNonNull(hdmiDeviceInfo);
        try {
            this.mService.powerOnRemoteDevice(hdmiDeviceInfo.getLogicalAddress(), hdmiDeviceInfo.getDevicePowerStatus());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void setActiveSource(HdmiDeviceInfo hdmiDeviceInfo) {
        Objects.requireNonNull(hdmiDeviceInfo);
        try {
            this.mService.askRemoteDeviceToBecomeActiveSource(hdmiDeviceInfo.getPhysicalAddress());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    @Deprecated
    public void requestRemoteDeviceToBecomeActiveSource(HdmiDeviceInfo hdmiDeviceInfo) {
        Objects.requireNonNull(hdmiDeviceInfo);
        try {
            this.mService.askRemoteDeviceToBecomeActiveSource(hdmiDeviceInfo.getPhysicalAddress());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setStandbyMode(boolean z) {
        try {
            this.mService.setStandbyMode(z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setHdmiCecVolumeControlEnabled(boolean z) {
        try {
            this.mService.setHdmiCecVolumeControlEnabled(z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isHdmiCecVolumeControlEnabled() {
        try {
            return this.mService.isHdmiCecVolumeControlEnabled();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean getSystemAudioMode() {
        try {
            return this.mService.getSystemAudioMode();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public int getPhysicalAddress() {
        return getLocalPhysicalAddress();
    }

    @SystemApi
    public boolean isDeviceConnected(HdmiDeviceInfo hdmiDeviceInfo) {
        int physicalAddress;
        Objects.requireNonNull(hdmiDeviceInfo);
        int localPhysicalAddress = getLocalPhysicalAddress();
        return (localPhysicalAddress == 65535 || (physicalAddress = hdmiDeviceInfo.getPhysicalAddress()) == 65535 || HdmiUtils.getLocalPortFromPhysicalAddress(physicalAddress, localPhysicalAddress) == -1) ? false : true;
    }

    @SystemApi
    @Deprecated
    public boolean isRemoteDeviceConnected(HdmiDeviceInfo hdmiDeviceInfo) {
        int physicalAddress;
        Objects.requireNonNull(hdmiDeviceInfo);
        int localPhysicalAddress = getLocalPhysicalAddress();
        return (localPhysicalAddress == 65535 || (physicalAddress = hdmiDeviceInfo.getPhysicalAddress()) == 65535 || HdmiUtils.getLocalPortFromPhysicalAddress(physicalAddress, localPhysicalAddress) == -1) ? false : true;
    }

    @SystemApi
    public void addHotplugEventListener(HotplugEventListener hotplugEventListener) {
        if (this.mService == null) {
            Log.e(TAG, "HdmiControlService is not available");
            return;
        }
        if (this.mHotplugEventListeners.containsKey(hotplugEventListener)) {
            Log.e(TAG, "listener is already registered");
            return;
        }
        IHdmiHotplugEventListener hotplugEventListenerWrapper = getHotplugEventListenerWrapper(hotplugEventListener);
        this.mHotplugEventListeners.put(hotplugEventListener, hotplugEventListenerWrapper);
        try {
            this.mService.addHotplugEventListener(hotplugEventListenerWrapper);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void removeHotplugEventListener(HotplugEventListener hotplugEventListener) {
        if (this.mService == null) {
            Log.e(TAG, "HdmiControlService is not available");
            return;
        }
        IHdmiHotplugEventListener iHdmiHotplugEventListenerRemove = this.mHotplugEventListeners.remove(hotplugEventListener);
        if (iHdmiHotplugEventListenerRemove == null) {
            Log.e(TAG, "tried to remove not-registered listener");
            return;
        }
        try {
            this.mService.removeHotplugEventListener(iHdmiHotplugEventListenerRemove);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private IHdmiHotplugEventListener getHotplugEventListenerWrapper(final HotplugEventListener hotplugEventListener) {
        return new IHdmiHotplugEventListener.Stub() { // from class: android.hardware.hdmi.HdmiControlManager.1
            @Override // android.hardware.hdmi.IHdmiHotplugEventListener
            public void onReceived(HdmiHotplugEvent hdmiHotplugEvent) {
                hotplugEventListener.onReceived(hdmiHotplugEvent);
            }
        };
    }

    public void addHdmiControlStatusChangeListener(HdmiControlStatusChangeListener hdmiControlStatusChangeListener) {
        if (this.mService == null) {
            Log.e(TAG, "HdmiControlService is not available");
            return;
        }
        if (this.mHdmiControlStatusChangeListeners.containsKey(hdmiControlStatusChangeListener)) {
            Log.e(TAG, "listener is already registered");
            return;
        }
        IHdmiControlStatusChangeListener hdmiControlStatusChangeListenerWrapper = getHdmiControlStatusChangeListenerWrapper(hdmiControlStatusChangeListener);
        this.mHdmiControlStatusChangeListeners.put(hdmiControlStatusChangeListener, hdmiControlStatusChangeListenerWrapper);
        try {
            this.mService.addHdmiControlStatusChangeListener(hdmiControlStatusChangeListenerWrapper);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void removeHdmiControlStatusChangeListener(HdmiControlStatusChangeListener hdmiControlStatusChangeListener) {
        if (this.mService == null) {
            Log.e(TAG, "HdmiControlService is not available");
            return;
        }
        IHdmiControlStatusChangeListener iHdmiControlStatusChangeListenerRemove = this.mHdmiControlStatusChangeListeners.remove(hdmiControlStatusChangeListener);
        if (iHdmiControlStatusChangeListenerRemove == null) {
            Log.e(TAG, "tried to remove not-registered listener");
            return;
        }
        try {
            this.mService.removeHdmiControlStatusChangeListener(iHdmiControlStatusChangeListenerRemove);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private IHdmiControlStatusChangeListener getHdmiControlStatusChangeListenerWrapper(final HdmiControlStatusChangeListener hdmiControlStatusChangeListener) {
        return new IHdmiControlStatusChangeListener.Stub() { // from class: android.hardware.hdmi.HdmiControlManager.2
            @Override // android.hardware.hdmi.IHdmiControlStatusChangeListener
            public void onStatusChange(boolean z, boolean z2) {
                hdmiControlStatusChangeListener.onStatusChange(z, z2);
            }
        };
    }

    public void addHdmiCecVolumeControlFeatureListener(Executor executor, HdmiCecVolumeControlFeatureListener hdmiCecVolumeControlFeatureListener) {
        if (this.mService == null) {
            Log.e(TAG, "HdmiControlService is not available");
            return;
        }
        if (this.mHdmiCecVolumeControlFeatureListeners.containsKey(hdmiCecVolumeControlFeatureListener)) {
            Log.e(TAG, "listener is already registered");
            return;
        }
        IHdmiCecVolumeControlFeatureListener iHdmiCecVolumeControlFeatureListenerCreateHdmiCecVolumeControlFeatureListenerWrapper = createHdmiCecVolumeControlFeatureListenerWrapper(executor, hdmiCecVolumeControlFeatureListener);
        this.mHdmiCecVolumeControlFeatureListeners.put(hdmiCecVolumeControlFeatureListener, iHdmiCecVolumeControlFeatureListenerCreateHdmiCecVolumeControlFeatureListenerWrapper);
        try {
            this.mService.addHdmiCecVolumeControlFeatureListener(iHdmiCecVolumeControlFeatureListenerCreateHdmiCecVolumeControlFeatureListenerWrapper);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void removeHdmiCecVolumeControlFeatureListener(HdmiCecVolumeControlFeatureListener hdmiCecVolumeControlFeatureListener) {
        if (this.mService == null) {
            Log.e(TAG, "HdmiControlService is not available");
            return;
        }
        IHdmiCecVolumeControlFeatureListener iHdmiCecVolumeControlFeatureListenerRemove = this.mHdmiCecVolumeControlFeatureListeners.remove(hdmiCecVolumeControlFeatureListener);
        if (iHdmiCecVolumeControlFeatureListenerRemove == null) {
            Log.e(TAG, "tried to remove not-registered listener");
            return;
        }
        try {
            this.mService.removeHdmiCecVolumeControlFeatureListener(iHdmiCecVolumeControlFeatureListenerRemove);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: renamed from: android.hardware.hdmi.HdmiControlManager$3, reason: invalid class name */
    class AnonymousClass3 extends IHdmiCecVolumeControlFeatureListener.Stub {
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ HdmiCecVolumeControlFeatureListener val$listener;

        AnonymousClass3(Executor executor, HdmiCecVolumeControlFeatureListener hdmiCecVolumeControlFeatureListener) {
            this.val$executor = executor;
            this.val$listener = hdmiCecVolumeControlFeatureListener;
        }

        @Override // android.hardware.hdmi.IHdmiCecVolumeControlFeatureListener
        public void onHdmiCecVolumeControlFeature(final boolean z) {
            Binder.clearCallingIdentity();
            Executor executor = this.val$executor;
            final HdmiCecVolumeControlFeatureListener hdmiCecVolumeControlFeatureListener = this.val$listener;
            executor.execute(new Runnable() { // from class: android.hardware.hdmi.-$$Lambda$HdmiControlManager$3$nhJsWlQLW7H_zQCJ36JgrRbU5zI
                @Override // java.lang.Runnable
                public final void run() {
                    hdmiCecVolumeControlFeatureListener.onHdmiCecVolumeControlFeature(z);
                }
            });
        }
    }

    private IHdmiCecVolumeControlFeatureListener createHdmiCecVolumeControlFeatureListenerWrapper(Executor executor, HdmiCecVolumeControlFeatureListener hdmiCecVolumeControlFeatureListener) {
        return new AnonymousClass3(executor, hdmiCecVolumeControlFeatureListener);
    }
}

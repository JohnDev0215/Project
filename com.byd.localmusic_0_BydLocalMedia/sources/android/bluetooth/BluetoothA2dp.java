package android.bluetooth;

import android.annotation.SystemApi;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.IBluetoothA2dp;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.ParcelUuid;
import android.os.RemoteException;
import android.telephony.ims.ImsConferenceState;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class BluetoothA2dp implements BluetoothProfile {
    public static final String ACTION_ACTIVE_DEVICE_CHANGED = "android.bluetooth.a2dp.profile.action.ACTIVE_DEVICE_CHANGED";
    public static final String ACTION_AVRCP_CONNECTION_STATE_CHANGED = "android.bluetooth.a2dp.profile.action.AVRCP_CONNECTION_STATE_CHANGED";
    public static final String ACTION_CODEC_CONFIG_CHANGED = "android.bluetooth.a2dp.profile.action.CODEC_CONFIG_CHANGED";
    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED";
    public static final String ACTION_PLAYING_STATE_CHANGED = "android.bluetooth.a2dp.profile.action.PLAYING_STATE_CHANGED";
    private static final boolean DBG = true;

    @SystemApi
    public static final int OPTIONAL_CODECS_NOT_SUPPORTED = 0;

    @SystemApi
    public static final int OPTIONAL_CODECS_PREF_DISABLED = 0;

    @SystemApi
    public static final int OPTIONAL_CODECS_PREF_ENABLED = 1;

    @SystemApi
    public static final int OPTIONAL_CODECS_PREF_UNKNOWN = -1;

    @SystemApi
    public static final int OPTIONAL_CODECS_SUPPORTED = 1;

    @SystemApi
    public static final int OPTIONAL_CODECS_SUPPORT_UNKNOWN = -1;
    public static final int STATE_NOT_PLAYING = 11;
    public static final int STATE_PLAYING = 10;
    private static final String TAG = "BluetoothA2dp";
    private static final boolean VDBG = false;
    private final BluetoothProfileConnector<IBluetoothA2dp> mProfileConnector = new BluetoothProfileConnector(this, 2, TAG, IBluetoothA2dp.class.getName()) { // from class: android.bluetooth.BluetoothA2dp.1
        @Override // android.bluetooth.BluetoothProfileConnector
        public IBluetoothA2dp getServiceInterface(IBinder iBinder) {
            return IBluetoothA2dp.Stub.asInterface(Binder.allowBlocking(iBinder));
        }
    };
    private BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();

    @Retention(RetentionPolicy.SOURCE)
    public @interface OptionalCodecsPreferenceStatus {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface OptionalCodecsSupportStatus {
    }

    public void finalize() {
    }

    BluetoothA2dp(Context context, BluetoothProfile.ServiceListener serviceListener) {
        this.mProfileConnector.connect(context, serviceListener);
    }

    void close() {
        this.mProfileConnector.disconnect();
    }

    private IBluetoothA2dp getService() {
        return this.mProfileConnector.getService();
    }

    public boolean connect(BluetoothDevice bluetoothDevice) {
        log("connect(" + bluetoothDevice + ")");
        try {
            IBluetoothA2dp service = getService();
            if (service != null && isEnabled() && isValidDevice(bluetoothDevice)) {
                return service.connect(bluetoothDevice);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return false;
        } catch (RemoteException unused) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return false;
        }
    }

    public boolean disconnect(BluetoothDevice bluetoothDevice) {
        log("disconnect(" + bluetoothDevice + ")");
        try {
            IBluetoothA2dp service = getService();
            if (service != null && isEnabled() && isValidDevice(bluetoothDevice)) {
                return service.disconnect(bluetoothDevice);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return false;
        } catch (RemoteException unused) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return false;
        }
    }

    @Override // android.bluetooth.BluetoothProfile
    public List<BluetoothDevice> getConnectedDevices() {
        try {
            IBluetoothA2dp service = getService();
            if (service != null && isEnabled()) {
                return service.getConnectedDevices();
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return new ArrayList();
        } catch (RemoteException unused) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return new ArrayList();
        }
    }

    @Override // android.bluetooth.BluetoothProfile
    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] iArr) {
        try {
            IBluetoothA2dp service = getService();
            if (service != null && isEnabled()) {
                return service.getDevicesMatchingConnectionStates(iArr);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return new ArrayList();
        } catch (RemoteException unused) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return new ArrayList();
        }
    }

    @Override // android.bluetooth.BluetoothProfile
    public int getConnectionState(BluetoothDevice bluetoothDevice) {
        try {
            IBluetoothA2dp service = getService();
            if (service != null && isEnabled() && isValidDevice(bluetoothDevice)) {
                return service.getConnectionState(bluetoothDevice);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return 0;
        } catch (RemoteException unused) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return 0;
        }
    }

    public boolean setActiveDevice(BluetoothDevice bluetoothDevice) {
        log("setActiveDevice(" + bluetoothDevice + ")");
        try {
            IBluetoothA2dp service = getService();
            if (service != null && isEnabled() && (bluetoothDevice == null || isValidDevice(bluetoothDevice))) {
                return service.setActiveDevice(bluetoothDevice);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return false;
        } catch (RemoteException unused) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return false;
        }
    }

    public BluetoothDevice getActiveDevice() {
        try {
            IBluetoothA2dp service = getService();
            if (service != null && isEnabled()) {
                return service.getActiveDevice();
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return null;
        } catch (RemoteException unused) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return null;
        }
    }

    public boolean setPriority(BluetoothDevice bluetoothDevice, int i) {
        log("setPriority(" + bluetoothDevice + ", " + i + ")");
        return setConnectionPolicy(bluetoothDevice, BluetoothAdapter.priorityToConnectionPolicy(i));
    }

    public boolean setPriority2(BluetoothDevice bluetoothDevice, int i) {
        log("setPriority2(" + bluetoothDevice + ", " + i + ")");
        return setConnectionPolicy2(bluetoothDevice, BluetoothAdapter.priorityToConnectionPolicy(i));
    }

    @SystemApi
    public boolean setConnectionPolicy(BluetoothDevice bluetoothDevice, int i) {
        log("setConnectionPolicy(" + bluetoothDevice + ", " + i + ")");
        try {
            IBluetoothA2dp service = getService();
            if (service == null || !isEnabled() || !isValidDevice(bluetoothDevice)) {
                if (service == null) {
                    Log.w(TAG, "Proxy not attached to service");
                }
                return false;
            }
            if (i == 0 || i == 100) {
                return service.setConnectionPolicy(bluetoothDevice, i);
            }
            return false;
        } catch (RemoteException unused) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return false;
        }
    }

    @SystemApi
    public boolean setConnectionPolicy2(BluetoothDevice bluetoothDevice, int i) {
        log("setConnectionPolicy(" + bluetoothDevice + ", " + i + ")");
        try {
            IBluetoothA2dp service = getService();
            if (service == null || !isEnabled() || !isValidDevice(bluetoothDevice)) {
                if (service == null) {
                    Log.w(TAG, "Proxy not attached to service");
                }
                return false;
            }
            if (i == 0 || i == 100) {
                return service.setConnectionPolicy2(bluetoothDevice, i);
            }
            return false;
        } catch (RemoteException unused) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return false;
        }
    }

    public int getPriority(BluetoothDevice bluetoothDevice) {
        try {
            IBluetoothA2dp service = getService();
            if (service != null && isEnabled() && isValidDevice(bluetoothDevice)) {
                return BluetoothAdapter.connectionPolicyToPriority(service.getPriority(bluetoothDevice));
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return 0;
        } catch (RemoteException unused) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return 0;
        }
    }

    @SystemApi
    public int getConnectionPolicy(BluetoothDevice bluetoothDevice) {
        try {
            IBluetoothA2dp service = getService();
            if (service != null && isEnabled() && isValidDevice(bluetoothDevice)) {
                return service.getConnectionPolicy(bluetoothDevice);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return 0;
        } catch (RemoteException unused) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return 0;
        }
    }

    public boolean isAvrcpAbsoluteVolumeSupported() {
        Log.d(TAG, "isAvrcpAbsoluteVolumeSupported");
        try {
            IBluetoothA2dp service = getService();
            if (service != null && isEnabled()) {
                return service.isAvrcpAbsoluteVolumeSupported();
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error talking to BT service in isAvrcpAbsoluteVolumeSupported()", e);
            return false;
        }
    }

    public void setAvrcpAbsoluteVolume(int i) {
        Log.d(TAG, "setAvrcpAbsoluteVolume");
        try {
            IBluetoothA2dp service = getService();
            if (service != null && isEnabled()) {
                service.setAvrcpAbsoluteVolume(i);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error talking to BT service in setAvrcpAbsoluteVolume()", e);
        }
    }

    public boolean isA2dpPlaying(BluetoothDevice bluetoothDevice) {
        try {
            IBluetoothA2dp service = getService();
            if (service != null && isEnabled() && isValidDevice(bluetoothDevice)) {
                return service.isA2dpPlaying(bluetoothDevice);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return false;
        } catch (RemoteException unused) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return false;
        }
    }

    public boolean shouldSendVolumeKeys(BluetoothDevice bluetoothDevice) {
        ParcelUuid[] uuids;
        if (!isEnabled() || !isValidDevice(bluetoothDevice) || (uuids = bluetoothDevice.getUuids()) == null) {
            return false;
        }
        for (ParcelUuid parcelUuid : uuids) {
            if (parcelUuid.equals(BluetoothUuid.AVRCP_TARGET)) {
                return true;
            }
        }
        return false;
    }

    public BluetoothCodecStatus getCodecStatus(BluetoothDevice bluetoothDevice) {
        Log.d(TAG, "getCodecStatus(" + bluetoothDevice + ")");
        verifyDeviceNotNull(bluetoothDevice, "getCodecStatus");
        try {
            IBluetoothA2dp service = getService();
            if (service != null && isEnabled()) {
                return service.getCodecStatus(bluetoothDevice);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "Error talking to BT service in getCodecStatus()", e);
            return null;
        }
    }

    public void setCodecConfigPreference(BluetoothDevice bluetoothDevice, BluetoothCodecConfig bluetoothCodecConfig) {
        Log.d(TAG, "setCodecConfigPreference(" + bluetoothDevice + ")");
        verifyDeviceNotNull(bluetoothDevice, "setCodecConfigPreference");
        if (bluetoothCodecConfig == null) {
            Log.e(TAG, "setCodecConfigPreference: Codec config can't be null");
            throw new IllegalArgumentException("codecConfig cannot be null");
        }
        try {
            IBluetoothA2dp service = getService();
            if (service != null && isEnabled()) {
                service.setCodecConfigPreference(bluetoothDevice, bluetoothCodecConfig);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error talking to BT service in setCodecConfigPreference()", e);
        }
    }

    public void enableOptionalCodecs(BluetoothDevice bluetoothDevice) {
        Log.d(TAG, "enableOptionalCodecs(" + bluetoothDevice + ")");
        verifyDeviceNotNull(bluetoothDevice, "enableOptionalCodecs");
        enableDisableOptionalCodecs(bluetoothDevice, true);
    }

    public void disableOptionalCodecs(BluetoothDevice bluetoothDevice) {
        Log.d(TAG, "disableOptionalCodecs(" + bluetoothDevice + ")");
        verifyDeviceNotNull(bluetoothDevice, "disableOptionalCodecs");
        enableDisableOptionalCodecs(bluetoothDevice, false);
    }

    private void enableDisableOptionalCodecs(BluetoothDevice bluetoothDevice, boolean z) {
        try {
            IBluetoothA2dp service = getService();
            if (service != null && isEnabled()) {
                if (z) {
                    service.enableOptionalCodecs(bluetoothDevice);
                } else {
                    service.disableOptionalCodecs(bluetoothDevice);
                }
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error talking to BT service in enableDisableOptionalCodecs()", e);
        }
    }

    public int isOptionalCodecsSupported(BluetoothDevice bluetoothDevice) {
        verifyDeviceNotNull(bluetoothDevice, "isOptionalCodecsSupported");
        try {
            IBluetoothA2dp service = getService();
            if (service != null && isEnabled() && isValidDevice(bluetoothDevice)) {
                return service.supportsOptionalCodecs(bluetoothDevice);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Error talking to BT service in supportsOptionalCodecs()", e);
            return -1;
        }
    }

    public int isOptionalCodecsEnabled(BluetoothDevice bluetoothDevice) {
        verifyDeviceNotNull(bluetoothDevice, "isOptionalCodecsEnabled");
        try {
            IBluetoothA2dp service = getService();
            if (service != null && isEnabled() && isValidDevice(bluetoothDevice)) {
                return service.getOptionalCodecsEnabled(bluetoothDevice);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Error talking to BT service in getOptionalCodecsEnabled()", e);
            return -1;
        }
    }

    public void setOptionalCodecsEnabled(BluetoothDevice bluetoothDevice, int i) {
        verifyDeviceNotNull(bluetoothDevice, "setOptionalCodecsEnabled");
        try {
            if (i != -1 && i != 0 && i != 1) {
                Log.e(TAG, "Invalid value passed to setOptionalCodecsEnabled: " + i);
                return;
            }
            IBluetoothA2dp service = getService();
            if (service != null && isEnabled() && isValidDevice(bluetoothDevice)) {
                service.setOptionalCodecsEnabled(bluetoothDevice, i);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
        } catch (RemoteException unused) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
        }
    }

    public static String stateToString(int i) {
        if (i == 0) {
            return "disconnected";
        }
        if (i == 1) {
            return "connecting";
        }
        if (i == 2) {
            return "connected";
        }
        if (i == 3) {
            return ImsConferenceState.STATUS_DISCONNECTING;
        }
        if (i == 10) {
            return "playing";
        }
        if (i == 11) {
            return "not playing";
        }
        return "<unknown state " + i + ">";
    }

    private boolean isEnabled() {
        return this.mAdapter.getState() == 12;
    }

    private void verifyDeviceNotNull(BluetoothDevice bluetoothDevice, String str) {
        if (bluetoothDevice != null) {
            return;
        }
        Log.e(TAG, str + ": device param is null");
        throw new IllegalArgumentException("Device cannot be null");
    }

    private boolean isValidDevice(BluetoothDevice bluetoothDevice) {
        return bluetoothDevice != null && BluetoothAdapter.checkBluetoothAddress(bluetoothDevice.getAddress());
    }

    private static void log(String str) {
        Log.d(TAG, str);
    }
}

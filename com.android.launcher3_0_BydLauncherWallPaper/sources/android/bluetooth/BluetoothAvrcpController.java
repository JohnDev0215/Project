package android.bluetooth;

import android.bluetooth.BluetoothProfile;
import android.bluetooth.IBluetoothAvrcpController;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class BluetoothAvrcpController implements BluetoothProfile {
    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.avrcp-controller.profile.action.CONNECTION_STATE_CHANGED";
    public static final String ACTION_PLAYER_SETTING = "android.bluetooth.avrcp-controller.profile.action.PLAYER_SETTING";
    public static final String ACTION_UIDS_EVENT = "android.bluetooth.avrcp-controller.profile.action.UIDS_EVENT";
    public static final int BTRC_FEAT_ABSOLUTE_VOLUME = 2;
    public static final int BTRC_FEAT_BROWSE = 4;
    public static final int BTRC_FEAT_COVER_ART = 8;
    public static final int BTRC_FEAT_METADATA = 1;
    public static final int BTRC_FEAT_NONE = 0;
    private static final boolean DBG = false;
    public static final String EXTRA_PLAYER_SETTING = "android.bluetooth.avrcp-controller.profile.extra.PLAYER_SETTING";
    private static final String TAG = "BluetoothAvrcpController";
    private static final boolean VDBG = false;
    private BluetoothAdapter mAdapter;
    private final BluetoothProfileConnector<IBluetoothAvrcpController> mProfileConnector;

    BluetoothAvrcpController(Context context, BluetoothProfile.ServiceListener serviceListener) {
        BluetoothProfileConnector<IBluetoothAvrcpController> bluetoothProfileConnector = new BluetoothProfileConnector(this, 12, TAG, IBluetoothAvrcpController.class.getName()) { // from class: android.bluetooth.BluetoothAvrcpController.1
            @Override // android.bluetooth.BluetoothProfileConnector
            public IBluetoothAvrcpController getServiceInterface(IBinder iBinder) {
                return IBluetoothAvrcpController.Stub.asInterface(Binder.allowBlocking(iBinder));
            }
        };
        this.mProfileConnector = bluetoothProfileConnector;
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        bluetoothProfileConnector.connect(context, serviceListener);
    }

    void close() {
        this.mProfileConnector.disconnect();
    }

    private IBluetoothAvrcpController getService() {
        return this.mProfileConnector.getService();
    }

    public void finalize() {
        close();
    }

    @Override // android.bluetooth.BluetoothProfile
    public List<BluetoothDevice> getConnectedDevices() {
        IBluetoothAvrcpController service = getService();
        if (service != null && isEnabled()) {
            try {
                return service.getConnectedDevices();
            } catch (RemoteException unused) {
                Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
                return new ArrayList();
            }
        }
        if (service == null) {
            Log.w(TAG, "Proxy not attached to service");
        }
        return new ArrayList();
    }

    @Override // android.bluetooth.BluetoothProfile
    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] iArr) {
        IBluetoothAvrcpController service = getService();
        if (service != null && isEnabled()) {
            try {
                return service.getDevicesMatchingConnectionStates(iArr);
            } catch (RemoteException unused) {
                Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
                return new ArrayList();
            }
        }
        if (service == null) {
            Log.w(TAG, "Proxy not attached to service");
        }
        return new ArrayList();
    }

    @Override // android.bluetooth.BluetoothProfile
    public int getConnectionState(BluetoothDevice bluetoothDevice) {
        IBluetoothAvrcpController service = getService();
        if (service != null && isEnabled() && isValidDevice(bluetoothDevice)) {
            try {
                return service.getConnectionState(bluetoothDevice);
            } catch (RemoteException unused) {
                Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
                return 0;
            }
        }
        if (service == null) {
            Log.w(TAG, "Proxy not attached to service");
        }
        return 0;
    }

    public BluetoothAvrcpPlayerSettings getPlayerSettings(BluetoothDevice bluetoothDevice) {
        IBluetoothAvrcpController service = getService();
        if (service == null || !isEnabled()) {
            return null;
        }
        try {
            return service.getPlayerSettings(bluetoothDevice);
        } catch (RemoteException e) {
            Log.e(TAG, "Error talking to BT service in getMetadata() " + e);
            return null;
        }
    }

    public boolean setPlayerApplicationSetting(BluetoothAvrcpPlayerSettings bluetoothAvrcpPlayerSettings) {
        IBluetoothAvrcpController service = getService();
        if (service == null || !isEnabled()) {
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return false;
        }
        try {
            return service.setPlayerApplicationSetting(bluetoothAvrcpPlayerSettings);
        } catch (RemoteException e) {
            Log.e(TAG, "Error talking to BT service in setPlayerApplicationSetting() " + e);
            return false;
        }
    }

    public void sendGroupNavigationCmd(BluetoothDevice bluetoothDevice, int i, int i2) {
        Log.d(TAG, "sendGroupNavigationCmd dev = " + bluetoothDevice.getAddressOnlyforLog() + " key " + i + " State = " + i2);
        IBluetoothAvrcpController service = getService();
        if (service == null || !isEnabled()) {
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
        } else {
            try {
                service.sendGroupNavigationCmd(bluetoothDevice, i, i2);
            } catch (RemoteException e) {
                Log.e(TAG, "Error talking to BT service in sendGroupNavigationCmd()", e);
            }
        }
    }

    public int getSupportedFeatures(BluetoothDevice bluetoothDevice) {
        Log.d(TAG, "getSupportedFeatures dev = " + bluetoothDevice.getAddressOnlyforLog());
        IBluetoothAvrcpController service = getService();
        if (service == null || !isEnabled()) {
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return 0;
        }
        try {
            return service.getSupportedFeatures(bluetoothDevice);
        } catch (RemoteException e) {
            Log.e(TAG, "Error talking to BT service in getSupportedFeatures()", e);
            return 0;
        }
    }

    public void startFetchingAlbumArt(BluetoothDevice bluetoothDevice, String str, String str2, String str3, int i, int i2, int i3) {
        IBluetoothAvrcpController service = getService();
        if (service != null && isEnabled()) {
            try {
                service.startFetchingAlbumArt(bluetoothDevice, str, str2, str3, i, i2, i3);
            } catch (RemoteException e) {
                Log.e(TAG, "Error talking to BT service in startFetchingAlbumArt() " + e);
                return;
            }
        }
        if (service == null) {
            Log.w(TAG, "Proxy not attached to service");
        }
    }

    private boolean isEnabled() {
        return this.mAdapter.getState() == 12;
    }

    private static boolean isValidDevice(BluetoothDevice bluetoothDevice) {
        return bluetoothDevice != null && BluetoothAdapter.checkBluetoothAddress(bluetoothDevice.getAddress());
    }

    private static void log(String str) {
        Log.d(TAG, str);
    }
}

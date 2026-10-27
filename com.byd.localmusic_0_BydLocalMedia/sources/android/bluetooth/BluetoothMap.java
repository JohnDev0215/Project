package android.bluetooth;

import android.annotation.SystemApi;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.IBluetoothMap;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.CloseGuard;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@SystemApi
public final class BluetoothMap implements BluetoothProfile, AutoCloseable {

    @SystemApi
    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.map.profile.action.CONNECTION_STATE_CHANGED";
    private static final boolean DBG = true;
    public static final int RESULT_CANCELED = 2;
    public static final int RESULT_FAILURE = 0;
    public static final int RESULT_SUCCESS = 1;
    public static final int STATE_ERROR = -1;
    private static final String TAG = "BluetoothMap";
    private static final boolean VDBG = false;
    private BluetoothAdapter mAdapter;
    private CloseGuard mCloseGuard;
    private final BluetoothProfileConnector<IBluetoothMap> mProfileConnector = new BluetoothProfileConnector(this, 9, TAG, IBluetoothMap.class.getName()) { // from class: android.bluetooth.BluetoothMap.1
        @Override // android.bluetooth.BluetoothProfileConnector
        public IBluetoothMap getServiceInterface(IBinder iBinder) {
            return IBluetoothMap.Stub.asInterface(Binder.allowBlocking(iBinder));
        }
    };

    BluetoothMap(Context context, BluetoothProfile.ServiceListener serviceListener) {
        Log.d(TAG, "Create BluetoothMap proxy object");
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mProfileConnector.connect(context, serviceListener);
        CloseGuard closeGuard = new CloseGuard();
        this.mCloseGuard = closeGuard;
        closeGuard.open("close");
    }

    protected void finalize() {
        CloseGuard closeGuard = this.mCloseGuard;
        if (closeGuard != null) {
            closeGuard.warnIfOpen();
        }
        close();
    }

    @Override // java.lang.AutoCloseable
    @SystemApi
    public void close() {
        this.mProfileConnector.disconnect();
    }

    private IBluetoothMap getService() {
        return this.mProfileConnector.getService();
    }

    public int getState() {
        IBluetoothMap service = getService();
        if (service != null) {
            try {
                return service.getState();
            } catch (RemoteException e) {
                Log.e(TAG, e.toString());
                return -1;
            }
        }
        Log.w(TAG, "Proxy not attached to service");
        log(Log.getStackTraceString(new Throwable()));
        return -1;
    }

    public BluetoothDevice getClient() {
        IBluetoothMap service = getService();
        if (service != null) {
            try {
                return service.getClient();
            } catch (RemoteException e) {
                Log.e(TAG, e.toString());
                return null;
            }
        }
        Log.w(TAG, "Proxy not attached to service");
        log(Log.getStackTraceString(new Throwable()));
        return null;
    }

    public boolean isConnected(BluetoothDevice bluetoothDevice) {
        IBluetoothMap service = getService();
        if (service != null) {
            try {
                return service.isConnected(bluetoothDevice);
            } catch (RemoteException e) {
                Log.e(TAG, e.toString());
                return false;
            }
        }
        Log.w(TAG, "Proxy not attached to service");
        log(Log.getStackTraceString(new Throwable()));
        return false;
    }

    public boolean connect(BluetoothDevice bluetoothDevice) {
        log("connect(" + bluetoothDevice + ")not supported for MAPS");
        return false;
    }

    public boolean disconnect(BluetoothDevice bluetoothDevice) {
        log("disconnect(" + bluetoothDevice + ")");
        IBluetoothMap service = getService();
        if (service != null && isEnabled() && isValidDevice(bluetoothDevice)) {
            try {
                return service.disconnect(bluetoothDevice);
            } catch (RemoteException unused) {
                Log.e(TAG, Log.getStackTraceString(new Throwable()));
                return false;
            }
        }
        if (service == null) {
            Log.w(TAG, "Proxy not attached to service");
        }
        return false;
    }

    public static boolean doesClassMatchSink(BluetoothClass bluetoothClass) {
        int deviceClass = bluetoothClass.getDeviceClass();
        return deviceClass == 256 || deviceClass == 260 || deviceClass == 264 || deviceClass == 268;
    }

    @Override // android.bluetooth.BluetoothProfile
    @SystemApi
    public List<BluetoothDevice> getConnectedDevices() {
        log("getConnectedDevices()");
        IBluetoothMap service = getService();
        if (service != null && isEnabled()) {
            try {
                return service.getConnectedDevices();
            } catch (RemoteException unused) {
                Log.e(TAG, Log.getStackTraceString(new Throwable()));
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
        log("getDevicesMatchingStates()");
        IBluetoothMap service = getService();
        if (service != null && isEnabled()) {
            try {
                return service.getDevicesMatchingConnectionStates(iArr);
            } catch (RemoteException unused) {
                Log.e(TAG, Log.getStackTraceString(new Throwable()));
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
        log("getConnectionState(" + bluetoothDevice + ")");
        IBluetoothMap service = getService();
        if (service != null && isEnabled() && isValidDevice(bluetoothDevice)) {
            try {
                return service.getConnectionState(bluetoothDevice);
            } catch (RemoteException unused) {
                Log.e(TAG, Log.getStackTraceString(new Throwable()));
                return 0;
            }
        }
        if (service == null) {
            Log.w(TAG, "Proxy not attached to service");
        }
        return 0;
    }

    public boolean setPriority(BluetoothDevice bluetoothDevice, int i) {
        log("setPriority(" + bluetoothDevice + ", " + i + ")");
        return setConnectionPolicy(bluetoothDevice, BluetoothAdapter.priorityToConnectionPolicy(i));
    }

    @SystemApi
    public boolean setConnectionPolicy(BluetoothDevice bluetoothDevice, int i) {
        log("setConnectionPolicy(" + bluetoothDevice + ", " + i + ")");
        IBluetoothMap service = getService();
        if (service == null || !isEnabled() || !isValidDevice(bluetoothDevice)) {
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return false;
        }
        if (i != 0 && i != 100) {
            return false;
        }
        try {
            return service.setConnectionPolicy(bluetoothDevice, i);
        } catch (RemoteException unused) {
            Log.e(TAG, Log.getStackTraceString(new Throwable()));
            return false;
        }
    }

    public int getPriority(BluetoothDevice bluetoothDevice) {
        return BluetoothAdapter.connectionPolicyToPriority(getConnectionPolicy(bluetoothDevice));
    }

    @SystemApi
    public int getConnectionPolicy(BluetoothDevice bluetoothDevice) {
        IBluetoothMap service = getService();
        if (service != null && isEnabled() && isValidDevice(bluetoothDevice)) {
            try {
                return service.getConnectionPolicy(bluetoothDevice);
            } catch (RemoteException unused) {
                Log.e(TAG, Log.getStackTraceString(new Throwable()));
                return 0;
            }
        }
        if (service == null) {
            Log.w(TAG, "Proxy not attached to service");
        }
        return 0;
    }

    private static void log(String str) {
        Log.d(TAG, str);
    }

    private boolean isEnabled() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null && defaultAdapter.getState() == 12) {
            return true;
        }
        log("Bluetooth is Not enabled");
        return false;
    }

    private static boolean isValidDevice(BluetoothDevice bluetoothDevice) {
        return bluetoothDevice != null && BluetoothAdapter.checkBluetoothAddress(bluetoothDevice.getAddress());
    }
}

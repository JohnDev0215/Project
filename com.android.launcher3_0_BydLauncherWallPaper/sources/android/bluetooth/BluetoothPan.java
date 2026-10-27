package android.bluetooth;

import android.annotation.SystemApi;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.IBluetoothPan;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@SystemApi
public final class BluetoothPan implements BluetoothProfile {
    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.pan.profile.action.CONNECTION_STATE_CHANGED";
    private static final boolean DBG = true;
    public static final String EXTRA_LOCAL_ROLE = "android.bluetooth.pan.extra.LOCAL_ROLE";
    public static final int LOCAL_NAP_ROLE = 1;
    public static final int LOCAL_PANU_ROLE = 2;
    public static final int PAN_CONNECT_FAILED_ALREADY_CONNECTED = 1001;
    public static final int PAN_CONNECT_FAILED_ATTEMPT_FAILED = 1002;
    public static final int PAN_DISCONNECT_FAILED_NOT_CONNECTED = 1000;
    public static final int PAN_OPERATION_GENERIC_FAILURE = 1003;
    public static final int PAN_OPERATION_SUCCESS = 1004;
    public static final int PAN_ROLE_NONE = 0;
    public static final int REMOTE_NAP_ROLE = 1;
    public static final int REMOTE_PANU_ROLE = 2;
    private static final String TAG = "BluetoothPan";
    private static final boolean VDBG = false;
    private BluetoothAdapter mAdapter;
    private final Context mContext;
    private final BluetoothProfileConnector<IBluetoothPan> mProfileConnector;

    @Retention(RetentionPolicy.SOURCE)
    public @interface LocalPanRole {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RemotePanRole {
    }

    BluetoothPan(Context context, BluetoothProfile.ServiceListener serviceListener) {
        BluetoothProfileConnector<IBluetoothPan> bluetoothProfileConnector = new BluetoothProfileConnector(this, 5, TAG, IBluetoothPan.class.getName()) { // from class: android.bluetooth.BluetoothPan.1
            @Override // android.bluetooth.BluetoothProfileConnector
            public IBluetoothPan getServiceInterface(IBinder iBinder) {
                return IBluetoothPan.Stub.asInterface(Binder.allowBlocking(iBinder));
            }
        };
        this.mProfileConnector = bluetoothProfileConnector;
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mContext = context;
        bluetoothProfileConnector.connect(context, serviceListener);
    }

    void close() {
        this.mProfileConnector.disconnect();
    }

    private IBluetoothPan getService() {
        return this.mProfileConnector.getService();
    }

    protected void finalize() {
        close();
    }

    public boolean connect(BluetoothDevice bluetoothDevice) {
        log("connect(" + bluetoothDevice + ")");
        IBluetoothPan service = getService();
        if (service != null && isEnabled() && isValidDevice(bluetoothDevice)) {
            try {
                return service.connect(bluetoothDevice);
            } catch (RemoteException unused) {
                Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
                return false;
            }
        }
        if (service == null) {
            Log.w(TAG, "Proxy not attached to service");
        }
        return false;
    }

    public boolean disconnect(BluetoothDevice bluetoothDevice) {
        log("disconnect(" + bluetoothDevice + ")");
        IBluetoothPan service = getService();
        if (service != null && isEnabled() && isValidDevice(bluetoothDevice)) {
            try {
                return service.disconnect(bluetoothDevice);
            } catch (RemoteException unused) {
                Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
                return false;
            }
        }
        if (service == null) {
            Log.w(TAG, "Proxy not attached to service");
        }
        return false;
    }

    @SystemApi
    public boolean setConnectionPolicy(BluetoothDevice bluetoothDevice, int i) {
        log("setConnectionPolicy(" + bluetoothDevice + ", " + i + ")");
        try {
            IBluetoothPan service = getService();
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

    @Override // android.bluetooth.BluetoothProfile
    @SystemApi
    public List<BluetoothDevice> getConnectedDevices() {
        IBluetoothPan service = getService();
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
        IBluetoothPan service = getService();
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
    @SystemApi
    public int getConnectionState(BluetoothDevice bluetoothDevice) {
        IBluetoothPan service = getService();
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

    @SystemApi
    public void setBluetoothTethering(boolean z) {
        String opPackageName = this.mContext.getOpPackageName();
        log("setBluetoothTethering(" + z + "), calling package:" + opPackageName);
        IBluetoothPan service = getService();
        if (service == null || !isEnabled()) {
            return;
        }
        try {
            service.setBluetoothTethering(z, opPackageName);
        } catch (RemoteException unused) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
        }
    }

    @SystemApi
    public boolean isTetheringOn() {
        IBluetoothPan service = getService();
        if (service == null || !isEnabled()) {
            return false;
        }
        try {
            return service.isTetheringOn();
        } catch (RemoteException unused) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return false;
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

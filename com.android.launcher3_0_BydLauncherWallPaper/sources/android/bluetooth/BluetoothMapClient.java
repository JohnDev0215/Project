package android.bluetooth;

import android.annotation.SystemApi;
import android.app.PendingIntent;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.IBluetoothMapClient;
import android.content.Context;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class BluetoothMapClient implements BluetoothProfile {
    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.mapmce.profile.action.CONNECTION_STATE_CHANGED";
    public static final String ACTION_MESSAGE_DELETED_STATUS_CHANGED = "android.bluetooth.mapmce.profile.action.MESSAGE_DELETED_STATUS_CHANGED";
    public static final String ACTION_MESSAGE_DELIVERED_SUCCESSFULLY = "android.bluetooth.mapmce.profile.action.MESSAGE_DELIVERED_SUCCESSFULLY";
    public static final String ACTION_MESSAGE_READ_STATUS_CHANGED = "android.bluetooth.mapmce.profile.action.MESSAGE_READ_STATUS_CHANGED";
    public static final String ACTION_MESSAGE_RECEIVED = "android.bluetooth.mapmce.profile.action.MESSAGE_RECEIVED";
    public static final String ACTION_MESSAGE_SENT_SUCCESSFULLY = "android.bluetooth.mapmce.profile.action.MESSAGE_SENT_SUCCESSFULLY";
    private static final boolean DBG = false;
    public static final int DELETED = 3;
    public static final String EXTRA_MESSAGE_DELETED_STATUS = "android.bluetooth.mapmce.profile.extra.MESSAGE_DELETED_STATUS";
    public static final String EXTRA_MESSAGE_HANDLE = "android.bluetooth.mapmce.profile.extra.MESSAGE_HANDLE";
    public static final String EXTRA_MESSAGE_READ_STATUS = "android.bluetooth.mapmce.profile.extra.MESSAGE_READ_STATUS";
    public static final String EXTRA_MESSAGE_TIMESTAMP = "android.bluetooth.mapmce.profile.extra.MESSAGE_TIMESTAMP";
    public static final String EXTRA_RESULT_CODE = "android.bluetooth.device.extra.RESULT_CODE";
    public static final String EXTRA_SENDER_CONTACT_NAME = "android.bluetooth.mapmce.profile.extra.SENDER_CONTACT_NAME";
    public static final String EXTRA_SENDER_CONTACT_URI = "android.bluetooth.mapmce.profile.extra.SENDER_CONTACT_URI";
    public static final int READ = 1;
    public static final int RESULT_CANCELED = 2;
    public static final int RESULT_FAILURE = 0;
    public static final int RESULT_SUCCESS = 1;
    public static final int STATE_ERROR = -1;
    private static final String TAG = "BluetoothMapClient";
    public static final int UNDELETED = 2;
    public static final int UNREAD = 0;
    private static final int UPLOADING_FEATURE_BITMASK = 8;
    private static final boolean VDBG = false;
    private BluetoothAdapter mAdapter;
    private final BluetoothProfileConnector<IBluetoothMapClient> mProfileConnector;

    BluetoothMapClient(Context context, BluetoothProfile.ServiceListener serviceListener) {
        BluetoothProfileConnector<IBluetoothMapClient> bluetoothProfileConnector = new BluetoothProfileConnector(this, 18, TAG, IBluetoothMapClient.class.getName()) { // from class: android.bluetooth.BluetoothMapClient.1
            @Override // android.bluetooth.BluetoothProfileConnector
            public IBluetoothMapClient getServiceInterface(IBinder iBinder) {
                return IBluetoothMapClient.Stub.asInterface(Binder.allowBlocking(iBinder));
            }
        };
        this.mProfileConnector = bluetoothProfileConnector;
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        bluetoothProfileConnector.connect(context, serviceListener);
    }

    protected void finalize() throws Throwable {
        try {
            close();
        } finally {
            super.finalize();
        }
    }

    public void close() {
        this.mProfileConnector.disconnect();
    }

    private IBluetoothMapClient getService() {
        return this.mProfileConnector.getService();
    }

    public boolean isConnected(BluetoothDevice bluetoothDevice) {
        IBluetoothMapClient service = getService();
        if (service != null) {
            try {
                return service.isConnected(bluetoothDevice);
            } catch (RemoteException e) {
                Log.e(TAG, e.toString());
                return false;
            }
        }
        Log.w(TAG, "Proxy not attached to service");
        return false;
    }

    public boolean connect(BluetoothDevice bluetoothDevice) {
        IBluetoothMapClient service = getService();
        if (service != null) {
            try {
                return service.connect(bluetoothDevice);
            } catch (RemoteException e) {
                Log.e(TAG, e.toString());
                return false;
            }
        }
        Log.w(TAG, "Proxy not attached to service");
        return false;
    }

    public boolean disconnect(BluetoothDevice bluetoothDevice) {
        IBluetoothMapClient service = getService();
        if (service != null && isEnabled() && isValidDevice(bluetoothDevice)) {
            try {
                return service.disconnect(bluetoothDevice);
            } catch (RemoteException unused) {
                Log.e(TAG, Log.getStackTraceString(new Throwable()));
            }
        }
        if (service != null) {
            return false;
        }
        Log.w(TAG, "Proxy not attached to service");
        return false;
    }

    @Override // android.bluetooth.BluetoothProfile
    public List<BluetoothDevice> getConnectedDevices() {
        IBluetoothMapClient service = getService();
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
        IBluetoothMapClient service = getService();
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
        IBluetoothMapClient service = getService();
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
        return setConnectionPolicy(bluetoothDevice, BluetoothAdapter.priorityToConnectionPolicy(i));
    }

    @SystemApi
    public boolean setConnectionPolicy(BluetoothDevice bluetoothDevice, int i) {
        IBluetoothMapClient service = getService();
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
        IBluetoothMapClient service = getService();
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

    public boolean sendMessage(BluetoothDevice bluetoothDevice, Uri[] uriArr, String str, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
        IBluetoothMapClient service = getService();
        if (service != null && isEnabled() && isValidDevice(bluetoothDevice)) {
            try {
                return service.sendMessage(bluetoothDevice, uriArr, str, pendingIntent, pendingIntent2);
            } catch (RemoteException unused) {
                Log.e(TAG, Log.getStackTraceString(new Throwable()));
            }
        }
        return false;
    }

    public boolean getUnreadMessages(BluetoothDevice bluetoothDevice) {
        IBluetoothMapClient service = getService();
        if (service != null && isEnabled() && isValidDevice(bluetoothDevice)) {
            try {
                return service.getUnreadMessages(bluetoothDevice);
            } catch (RemoteException unused) {
                Log.e(TAG, Log.getStackTraceString(new Throwable()));
            }
        }
        return false;
    }

    public boolean isUploadingSupported(BluetoothDevice bluetoothDevice) {
        IBluetoothMapClient service = getService();
        if (service == null) {
            return false;
        }
        try {
            if (isEnabled() && isValidDevice(bluetoothDevice)) {
                return (service.getSupportedFeatures(bluetoothDevice) & 8) > 0;
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    public boolean setMessageStatus(BluetoothDevice bluetoothDevice, String str, int i) {
        IBluetoothMapClient service = getService();
        if (service != null && isEnabled() && isValidDevice(bluetoothDevice) && str != null && (i == 1 || i == 0 || i == 2 || i == 3)) {
            try {
                return service.setMessageStatus(bluetoothDevice, str, i);
            } catch (RemoteException unused) {
                Log.e(TAG, Log.getStackTraceString(new Throwable()));
            }
        }
        return false;
    }

    private boolean isEnabled() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter != null && defaultAdapter.getState() == 12;
    }

    private static boolean isValidDevice(BluetoothDevice bluetoothDevice) {
        return bluetoothDevice != null && BluetoothAdapter.checkBluetoothAddress(bluetoothDevice.getAddress());
    }
}

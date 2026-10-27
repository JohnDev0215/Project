package android.bluetooth;

import android.annotation.SystemApi;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.IBluetoothPbapCallBack;
import android.bluetooth.IBluetoothPbapClient;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class BluetoothPbapClient implements BluetoothProfile {
    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.pbapclient.profile.action.CONNECTION_STATE_CHANGED";
    public static final String ACTION_PULLPHONEBOOK_FAIL = "android.bluetooth.pbapclient.profile.action.PULLPHONEBOOK_FAIL";
    public static final String ACTION_PULLPHONEBOOK_START = "android.bluetooth.pbapclient.profile.action.PULLPHONEBOOK_START";
    public static final String ACTION_PULLPHONEBOOK_SUCCESS = "android.bluetooth.pbapclient.profile.action.PULLPHONEBOOK_SUCCESS";
    public static final String ACTION_PULLPHONECALLLOG_FAIL = "android.bluetooth.pbapclient.profile.action.PULLPHONECALLLOG_FAIL";
    public static final String ACTION_PULLPHONECALLLOG_START = "android.bluetooth.pbapclient.profile.action.PULLPHONECALLLOG_START";
    public static final String ACTION_PULLPHONECALLLOG_SUCCESS = "android.bluetooth.pbapclient.profile.action.PULLPHONECALLLOG_SUCCESS";
    private static final boolean DBG = false;
    public static final int RESULT_CANCELED = 2;
    public static final int RESULT_FAILURE = 0;
    public static final int RESULT_SUCCESS = 1;
    public static final int STATE_ERROR = -1;
    private static final String TAG = "BluetoothPbapClient";
    private static final boolean VDBG = false;
    private BluetoothPbapCallBack mBluetoothPbapCallBack = new BluetoothPbapCallBack();
    private final BluetoothProfileConnector<IBluetoothPbapClient> mProfileConnector = new BluetoothProfileConnector(this, 17, TAG, IBluetoothPbapClient.class.getName()) { // from class: android.bluetooth.BluetoothPbapClient.1
        @Override // android.bluetooth.BluetoothProfileConnector
        public IBluetoothPbapClient getServiceInterface(IBinder iBinder) {
            return IBluetoothPbapClient.Stub.asInterface(Binder.allowBlocking(iBinder));
        }
    };
    private BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();

    public interface IPbabClientStateListener {
        void onGetCalllogComplete(String str, List<BluetoothCardEntry> list, String str2);

        void onGetCalllogState(String str, int i, String str2);

        void onGetPhoneBookComplete(String str, List<BluetoothCardEntry> list, String str2);

        void onGetPhoneBookSize(String str, int i, String str2);

        void onGetPhoneBookState(String str, int i, String str2);
    }

    public void pullPhoneBook(BluetoothDevice bluetoothDevice) {
    }

    public void pullPhoneCallLog(BluetoothDevice bluetoothDevice) {
    }

    BluetoothPbapClient(Context context, BluetoothProfile.ServiceListener serviceListener) {
        this.mProfileConnector.connect(context, serviceListener);
    }

    protected void finalize() throws Throwable {
        try {
            close();
        } finally {
            super.finalize();
        }
    }

    public synchronized void close() {
        this.mProfileConnector.disconnect();
    }

    private IBluetoothPbapClient getService() {
        return this.mProfileConnector.getService();
    }

    public boolean connect(BluetoothDevice bluetoothDevice) {
        if (this.mBluetoothPbapCallBack.getListener() == null) {
            log("autoconnect but listener is null, do not connect");
            return false;
        }
        IBluetoothPbapClient service = getService();
        if (service == null || !isEnabled() || !isValidDevice(bluetoothDevice)) {
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return false;
        }
        try {
            service.registerCallback(this.mBluetoothPbapCallBack);
            return service.connect(bluetoothDevice);
        } catch (RemoteException unused) {
            Log.e(TAG, Log.getStackTraceString(new Throwable()));
            return false;
        }
    }

    public boolean connectFromScene(BluetoothDevice bluetoothDevice, int i) {
        if (this.mBluetoothPbapCallBack.getListener() == null) {
            log("autoconnect but listener is null, do not connect");
            return false;
        }
        IBluetoothPbapClient service = getService();
        if (service == null || !isEnabled() || !isValidDevice(bluetoothDevice)) {
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return false;
        }
        try {
            service.registerCallback(this.mBluetoothPbapCallBack);
            return service.connectFromScene(bluetoothDevice, i);
        } catch (RemoteException unused) {
            Log.e(TAG, Log.getStackTraceString(new Throwable()));
            return false;
        }
    }

    public boolean disconnect(BluetoothDevice bluetoothDevice) {
        IBluetoothPbapClient service = getService();
        if (service == null || !isEnabled() || !isValidDevice(bluetoothDevice)) {
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return false;
        }
        try {
            service.unregisterCallback(this.mBluetoothPbapCallBack);
            service.disconnect(bluetoothDevice);
            return true;
        } catch (RemoteException unused) {
            Log.e(TAG, Log.getStackTraceString(new Throwable()));
            return false;
        }
    }

    @Override // android.bluetooth.BluetoothProfile
    public List<BluetoothDevice> getConnectedDevices() {
        IBluetoothPbapClient service = getService();
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
        IBluetoothPbapClient service = getService();
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
        IBluetoothPbapClient service = getService();
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

    public boolean setPriority(BluetoothDevice bluetoothDevice, int i) {
        log("setPriority(" + bluetoothDevice.getAddressOnlyforLog() + ", " + i + ")");
        return setConnectionPolicy(bluetoothDevice, BluetoothAdapter.priorityToConnectionPolicy(i));
    }

    @SystemApi
    public boolean setConnectionPolicy(BluetoothDevice bluetoothDevice, int i) {
        IBluetoothPbapClient service = getService();
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
        IBluetoothPbapClient service = getService();
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

    @SystemApi
    public void registerPbabClientCallback(IPbabClientStateListener iPbabClientStateListener) {
        Log.w(TAG, "registerPbabClientCallback listener: " + iPbabClientStateListener);
        this.mBluetoothPbapCallBack.setListener(iPbabClientStateListener);
    }

    class BluetoothPbapCallBack extends IBluetoothPbapCallBack.Stub {
        private IPbabClientStateListener iPbabClientStateListener;

        BluetoothPbapCallBack() {
        }

        public void setListener(IPbabClientStateListener iPbabClientStateListener) {
            Log.d(BluetoothPbapClient.TAG, "IPbabClientStateListener listener: " + iPbabClientStateListener);
            this.iPbabClientStateListener = iPbabClientStateListener;
        }

        public IPbabClientStateListener getListener() {
            return this.iPbabClientStateListener;
        }

        @Override // android.bluetooth.IBluetoothPbapCallBack
        public void onGetPhoneBookState(String str, int i, String str2) {
            Log.d(BluetoothPbapClient.TAG, "onGetPhoneBookState enter path: " + str + "  state: " + i + " mIPbabClientStateListener: " + this.iPbabClientStateListener);
            IPbabClientStateListener iPbabClientStateListener = this.iPbabClientStateListener;
            if (iPbabClientStateListener != null) {
                iPbabClientStateListener.onGetPhoneBookState(str, i, str2);
            }
        }

        @Override // android.bluetooth.IBluetoothPbapCallBack
        public void onGetPhoneBookSize(String str, int i, String str2) {
            Log.d(BluetoothPbapClient.TAG, "onGetPhoneBookSize enter path: " + str + "  size: " + i + " mIPbabClientStateListener: " + this.iPbabClientStateListener);
            IPbabClientStateListener iPbabClientStateListener = this.iPbabClientStateListener;
            if (iPbabClientStateListener != null) {
                iPbabClientStateListener.onGetPhoneBookSize(str, i, str2);
            }
        }

        @Override // android.bluetooth.IBluetoothPbapCallBack
        public void onGetPhoneBookComplete(String str, List<BluetoothCardEntry> list, String str2) {
            Log.d(BluetoothPbapClient.TAG, "onGetPhoneBookComplete enter path: " + str);
            if (list != null && !list.isEmpty()) {
                Log.d(BluetoothPbapClient.TAG, "onGetPhoneBookComplete cardEntryList.size:" + list.size() + " mIPbabClientStateListener: " + this.iPbabClientStateListener);
            } else {
                Log.d(BluetoothPbapClient.TAG, "onGetPhoneBookComplete is Null path: " + str);
            }
            if (this.iPbabClientStateListener != null) {
                Log.d(BluetoothPbapClient.TAG, "onGetPhoneBookComplete callback path: " + str);
                this.iPbabClientStateListener.onGetPhoneBookComplete(str, list, str2);
            }
        }

        @Override // android.bluetooth.IBluetoothPbapCallBack
        public void onGetCalllogState(String str, int i, String str2) {
            Log.d(BluetoothPbapClient.TAG, "onGetCalllogState enter path: " + str + "  state: " + i + " mIPbabClientStateListener: " + this.iPbabClientStateListener);
            if (this.iPbabClientStateListener != null) {
                Log.d(BluetoothPbapClient.TAG, "onGetCalllogState callback path: " + str);
                this.iPbabClientStateListener.onGetCalllogState(str, i, str2);
            }
        }

        @Override // android.bluetooth.IBluetoothPbapCallBack
        public void onGetCalllogComplete(String str, List<BluetoothCardEntry> list, String str2) {
            Log.d(BluetoothPbapClient.TAG, "onGetCalllogComplete enter path: " + str);
            if (list != null && !list.isEmpty()) {
                Log.d(BluetoothPbapClient.TAG, "onGetCalllogComplete cardEntryList.size:" + list.size() + " mIPbabClientStateListener: " + this.iPbabClientStateListener);
            } else {
                Log.d(BluetoothPbapClient.TAG, "onGetCalllogComplete is Null  path: " + str);
            }
            if (this.iPbabClientStateListener != null) {
                Log.d(BluetoothPbapClient.TAG, "onGetCalllogComplete callback path: " + str);
                this.iPbabClientStateListener.onGetCalllogComplete(str, list, str2);
            }
        }
    }
}

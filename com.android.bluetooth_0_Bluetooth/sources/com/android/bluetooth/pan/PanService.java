package com.android.bluetooth.pan;

import android.R;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.IBluetoothPan;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.os.UserManager;
import android.util.Log;
import com.android.bluetooth.BluetoothMetricsProto;
import com.android.bluetooth.Utils;
import com.android.bluetooth.btservice.AdapterService;
import com.android.bluetooth.btservice.MetricsLogger;
import com.android.bluetooth.btservice.ProfileService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PanService extends ProfileService {
    private static final String ACTION_TETHERING_STATE_CHANGED = "android.bluetooth.pan.profile.action.TETHERING_STATE_CHANGED";
    private static final String BLUETOOTH_IFACE_ADDR_START = "192.168.44.1";
    private static final int BLUETOOTH_MAX_PAN_CONNECTIONS = 5;
    private static final int BLUETOOTH_PREFIX_LENGTH = 24;
    private static final int CONN_STATE_CONNECTED = 0;
    private static final int CONN_STATE_CONNECTING = 1;
    private static final int CONN_STATE_DISCONNECTED = 2;
    private static final int CONN_STATE_DISCONNECTING = 3;
    private static final boolean DBG = false;
    private static final String EXTRA_TETHERING_STATE = "android.bluetooth.pan.extra.TETHERING_STATE";
    private static final int MESSAGE_CONNECT = 1;
    private static final int MESSAGE_CONNECT_STATE_CHANGED = 11;
    private static final int MESSAGE_DISCONNECT = 2;
    private static final String TAG = "PanService";
    private static final int TETHERING_STATE_OFF = 1;
    private static final int TETHERING_STATE_ON = 2;
    private static PanService sPanService;
    private ArrayList<String> mBluetoothIfaceAddresses;
    private int mMaxPanDevices;
    private String mNapIfaceAddr;
    private boolean mNativeAvailable;
    private BluetoothTetheringNetworkFactory mNetworkFactory;
    private HashMap<BluetoothDevice, BluetoothPanDevice> mPanDevices;
    private String mPanIfName;
    UserManager mUserManager;
    private boolean mTetherOn = false;
    private boolean mStarted = false;
    private final Handler mHandler = new Handler() { // from class: com.android.bluetooth.pan.PanService.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            int i = msg.what;
            if (i == 1) {
                BluetoothDevice device = (BluetoothDevice) msg.obj;
                if (!PanService.this.connectPanNative(Utils.getByteAddress(device), 2, 1)) {
                    PanService.this.handlePanDeviceStateChange(device, null, 1, 2, 1);
                    PanService.this.handlePanDeviceStateChange(device, null, 0, 2, 1);
                    return;
                }
                return;
            }
            if (i != 2) {
                if (i == 11) {
                    ConnectState cs = (ConnectState) msg.obj;
                    BluetoothDevice device2 = PanService.this.getDevice(cs.addr);
                    PanService panService = PanService.this;
                    panService.handlePanDeviceStateChange(device2, panService.mPanIfName, PanService.convertHalState(cs.state), cs.local_role, cs.remote_role);
                    return;
                }
                return;
            }
            BluetoothDevice device3 = (BluetoothDevice) msg.obj;
            if (!PanService.this.disconnectPanNative(Utils.getByteAddress(device3))) {
                PanService panService2 = PanService.this;
                panService2.handlePanDeviceStateChange(device3, panService2.mPanIfName, 3, 2, 1);
                PanService panService3 = PanService.this;
                panService3.handlePanDeviceStateChange(device3, panService3.mPanIfName, 0, 2, 1);
            }
        }
    };

    private static native void classInitNative();

    private native void cleanupNative();

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean connectPanNative(byte[] bArr, int i, int i2);

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean disconnectPanNative(byte[] bArr);

    private native boolean enablePanNative(int i);

    private native int getPanLocalRoleNative();

    private native void initializeNative();

    static {
        classInitNative();
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    public ProfileService.IProfileServiceBinder initBinder() {
        return new BluetoothPanBinder(this);
    }

    public static synchronized PanService getPanService() {
        if (sPanService == null) {
            Log.w(TAG, "getPanService(): service is null");
            return null;
        }
        if (!sPanService.isAvailable()) {
            Log.w(TAG, "getPanService(): service is not available ");
            return null;
        }
        return sPanService;
    }

    private static synchronized void setPanService(PanService instance) {
        sPanService = instance;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected boolean start() {
        this.mPanDevices = new HashMap<>();
        this.mBluetoothIfaceAddresses = new ArrayList<>();
        try {
            this.mMaxPanDevices = getResources().getInteger(R.integer.config_displayWhiteBalanceColorTemperatureDefault);
        } catch (Resources.NotFoundException e) {
            this.mMaxPanDevices = 5;
        }
        initializeNative();
        this.mNativeAvailable = true;
        this.mUserManager = (UserManager) getSystemService("user");
        setPanService(this);
        this.mStarted = true;
        return true;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected boolean stop() {
        this.mHandler.removeCallbacksAndMessages(null);
        return true;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected void cleanup() {
        setPanService(null);
        if (this.mNativeAvailable) {
            cleanupNative();
            this.mNativeAvailable = false;
        }
        this.mUserManager = null;
        if (this.mPanDevices != null) {
            int[] desiredStates = {1, 2, 3};
            List<BluetoothDevice> devList = getDevicesMatchingConnectionStates(desiredStates);
            for (BluetoothDevice device : devList) {
                BluetoothPanDevice panDevice = this.mPanDevices.get(device);
                Log.d(TAG, "panDevice: " + panDevice + " device address: " + device.getAddressOnlyforLog());
                if (panDevice != null) {
                    handlePanDeviceStateChange(device, this.mPanIfName, 0, panDevice.mLocalRole, panDevice.mRemoteRole);
                }
            }
            this.mPanDevices.clear();
        }
    }

    private static class BluetoothPanBinder extends IBluetoothPan.Stub implements ProfileService.IProfileServiceBinder {
        private PanService mService;

        BluetoothPanBinder(PanService svc) {
            this.mService = svc;
        }

        @Override // com.android.bluetooth.btservice.ProfileService.IProfileServiceBinder
        public void cleanup() {
            this.mService = null;
        }

        private PanService getService() {
            if (!Utils.checkCaller()) {
                Log.w(PanService.TAG, "Pan call not allowed for non-active user");
                return null;
            }
            PanService panService = this.mService;
            if (panService == null || !panService.isAvailable()) {
                return null;
            }
            return this.mService;
        }

        public boolean connect(BluetoothDevice device) {
            PanService service = getService();
            if (service == null) {
                return false;
            }
            return service.connect(device);
        }

        public boolean disconnect(BluetoothDevice device) {
            PanService service = getService();
            if (service == null) {
                return false;
            }
            return service.disconnect(device);
        }

        public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
            PanService service = getService();
            if (service == null) {
                return false;
            }
            return service.setConnectionPolicy(device, connectionPolicy);
        }

        public int getConnectionState(BluetoothDevice device) {
            PanService service = getService();
            if (service == null) {
                return 0;
            }
            return service.getConnectionState(device);
        }

        private boolean isPanNapOn() {
            PanService service = getService();
            if (service == null) {
                return false;
            }
            return service.isPanNapOn();
        }

        private boolean isPanUOn() {
            PanService service = getService();
            if (service == null) {
                return false;
            }
            return service.isPanUOn();
        }

        public boolean isTetheringOn() {
            PanService service = getService();
            if (service == null) {
                return false;
            }
            return service.isTetheringOn();
        }

        public void setBluetoothTethering(boolean value, String pkgName) {
            PanService service = getService();
            if (service == null) {
                return;
            }
            Log.d(PanService.TAG, "setBluetoothTethering: " + value + ", pkgName: " + pkgName + ", mTetherOn: " + service.mTetherOn);
            service.setBluetoothTethering(value, pkgName);
        }

        public List<BluetoothDevice> getConnectedDevices() {
            PanService service = getService();
            if (service == null) {
                return new ArrayList(0);
            }
            return service.getConnectedDevices();
        }

        public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
            PanService service = getService();
            if (service == null) {
                return new ArrayList(0);
            }
            return service.getDevicesMatchingConnectionStates(states);
        }
    }

    public boolean connect(BluetoothDevice device) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        if (this.mUserManager.isGuestUser()) {
            Log.w(TAG, "Guest user does not have the permission to change the WiFi network");
            return false;
        }
        if (getConnectionState(device) != 0) {
            Log.e(TAG, "Pan Device not disconnected: " + device.getAddressOnlyforLog());
            return false;
        }
        Message msg = this.mHandler.obtainMessage(1, device);
        this.mHandler.sendMessage(msg);
        return true;
    }

    public boolean disconnect(BluetoothDevice device) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        Message msg = this.mHandler.obtainMessage(2, device);
        this.mHandler.sendMessage(msg);
        return true;
    }

    public int getConnectionState(BluetoothDevice device) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        BluetoothPanDevice panDevice = this.mPanDevices.get(device);
        if (panDevice == null) {
            return 0;
        }
        return panDevice.mState;
    }

    boolean isPanNapOn() {
        return (getPanLocalRoleNative() & 1) != 0;
    }

    boolean isPanUOn() {
        return (getPanLocalRoleNative() & 2) != 0;
    }

    public boolean isTetheringOn() {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        return this.mTetherOn;
    }

    void setBluetoothTethering(boolean value, String pkgName) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        enforceCallingOrSelfPermission("android.permission.TETHER_PRIVILEGED", "Need TETHER_PRIVILEGED permission");
        UserManager um = (UserManager) getSystemService("user");
        if (um.hasUserRestriction("no_config_tethering") && value) {
            throw new SecurityException("DISALLOW_CONFIG_TETHERING is enabled for this user.");
        }
        if (this.mTetherOn != value) {
            this.mTetherOn = value;
            List<BluetoothDevice> devList = getConnectedDevices();
            for (BluetoothDevice dev : devList) {
                disconnect(dev);
            }
            Intent intent = new Intent(ACTION_TETHERING_STATE_CHANGED);
            intent.putExtra(EXTRA_TETHERING_STATE, this.mTetherOn ? 2 : 1);
            sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
        }
    }

    public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        boolean setSuccessfully = AdapterService.getAdapterService().getDatabase().setProfileConnectionPolicy(device, 5, connectionPolicy);
        if (setSuccessfully && connectionPolicy == 100) {
            connect(device);
        } else if (setSuccessfully && connectionPolicy == 0) {
            disconnect(device);
        }
        return setSuccessfully;
    }

    public int getConnectionPolicy(BluetoothDevice device) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH_ADMIN permission");
        return AdapterService.getAdapterService().getDatabase().getProfileConnectionPolicy(device, 5);
    }

    public List<BluetoothDevice> getConnectedDevices() {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        List<BluetoothDevice> devices = getDevicesMatchingConnectionStates(new int[]{2});
        return devices;
    }

    List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        List<BluetoothDevice> panDevices = new ArrayList<>();
        for (BluetoothDevice device : this.mPanDevices.keySet()) {
            int panDeviceState = getConnectionState(device);
            int length = states.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    int state = states[i];
                    if (state != panDeviceState) {
                        i++;
                    } else {
                        panDevices.add(device);
                        break;
                    }
                }
            }
        }
        return panDevices;
    }

    protected static class ConnectState {
        public byte[] addr;
        public int error;
        public int local_role;
        public int remote_role;
        public int state;

        public ConnectState(byte[] address, int state, int error, int localRole, int remoteRole) {
            this.addr = address;
            this.state = state;
            this.error = error;
            this.local_role = localRole;
            this.remote_role = remoteRole;
        }
    }

    private void onConnectStateChanged(byte[] address, int state, int error, int localRole, int remoteRole) {
        Message msg = this.mHandler.obtainMessage(11);
        msg.obj = new ConnectState(address, state, error, localRole, remoteRole);
        this.mHandler.sendMessage(msg);
    }

    private void onControlStateChanged(int localRole, int state, int error, String ifname) {
        if (error == 0) {
            this.mPanIfName = ifname;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int convertHalState(int halState) {
        if (halState == 0) {
            return 2;
        }
        if (halState == 1) {
            return 1;
        }
        if (halState == 2) {
            return 0;
        }
        if (halState == 3) {
            return 3;
        }
        Log.e(TAG, "bad pan connection state: " + halState);
        return 0;
    }

    void handlePanDeviceStateChange(BluetoothDevice device, String iface, int state, int localRole, int remoteRole) {
        int prevState;
        BluetoothPanDevice panDevice = this.mPanDevices.get(device);
        if (panDevice == null) {
            Log.i(TAG, "state " + state + " Num of connected pan devices: " + this.mPanDevices.size());
            prevState = 0;
            this.mPanDevices.put(device, new BluetoothPanDevice(state, iface, localRole, remoteRole));
        } else {
            prevState = panDevice.mState;
            panDevice.mState = state;
            panDevice.mLocalRole = localRole;
            panDevice.mRemoteRole = remoteRole;
            panDevice.mIface = iface;
        }
        if (prevState == 0 && state == 3) {
            Log.d(TAG, "Ignoring state change from " + prevState + " to " + state);
            this.mPanDevices.remove(device);
            return;
        }
        Log.d(TAG, "handlePanDeviceStateChange preState: " + prevState + " state: " + state);
        if (prevState == state) {
            return;
        }
        if (remoteRole == 2) {
            if (state == 2) {
                if (!this.mTetherOn || localRole == 2) {
                    Log.d(TAG, "handlePanDeviceStateChange BT tethering is off/Local role is PANU drop the connection");
                    this.mPanDevices.remove(device);
                    disconnectPanNative(Utils.getByteAddress(device));
                    return;
                }
                Log.d(TAG, "handlePanDeviceStateChange LOCAL_NAP_ROLE:REMOTE_PANU_ROLE");
                if (this.mNapIfaceAddr == null) {
                    String strStartTethering = startTethering(iface);
                    this.mNapIfaceAddr = strStartTethering;
                    if (strStartTethering == null) {
                        Log.e(TAG, "Error seting up tether interface");
                        this.mPanDevices.remove(device);
                        disconnectPanNative(Utils.getByteAddress(device));
                        return;
                    }
                }
            } else if (state == 0) {
                this.mPanDevices.remove(device);
                Log.i(TAG, "remote(PANU) is disconnected, Remaining connected PANU devices: " + this.mPanDevices.size());
                if (this.mNapIfaceAddr != null && this.mPanDevices.size() == 0) {
                    stopTethering(iface);
                    this.mNapIfaceAddr = null;
                }
            }
        } else if (this.mStarted) {
            Log.d(TAG, "handlePanDeviceStateChange LOCAL_PANU_ROLE:REMOTE_NAP_ROLE state = " + state + ", prevState = " + prevState);
            if (state == 2) {
                BluetoothTetheringNetworkFactory bluetoothTetheringNetworkFactory = new BluetoothTetheringNetworkFactory(getBaseContext(), getMainLooper(), this);
                this.mNetworkFactory = bluetoothTetheringNetworkFactory;
                bluetoothTetheringNetworkFactory.startReverseTether(iface);
            } else if (state == 0) {
                BluetoothTetheringNetworkFactory bluetoothTetheringNetworkFactory2 = this.mNetworkFactory;
                if (bluetoothTetheringNetworkFactory2 != null) {
                    bluetoothTetheringNetworkFactory2.stopReverseTether();
                    this.mNetworkFactory = null;
                }
                this.mPanDevices.remove(device);
            }
        }
        if (state == 2) {
            MetricsLogger.logProfileConnectionEvent(BluetoothMetricsProto.ProfileId.PAN);
        }
        Log.d(TAG, "Pan Device state : device: " + device.getAddressOnlyforLog() + " State:" + prevState + "->" + state);
        Intent intent = new Intent("android.bluetooth.pan.profile.action.CONNECTION_STATE_CHANGED");
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        intent.putExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", prevState);
        intent.putExtra("android.bluetooth.profile.extra.STATE", state);
        intent.putExtra("android.bluetooth.pan.extra.LOCAL_ROLE", localRole);
        sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
    }

    private String startTethering(String iface) {
        return configureBtIface(true, iface);
    }

    private String stopTethering(String iface) {
        return configureBtIface(false, iface);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0089 A[Catch: Exception -> 0x00f6, TryCatch #0 {Exception -> 0x00f6, blocks: (B:13:0x005c, B:15:0x0063, B:17:0x006a, B:19:0x0071, B:21:0x007d, B:24:0x0091, B:26:0x009d, B:28:0x00a4, B:30:0x00a9, B:32:0x00af, B:35:0x00d2, B:27:0x00a1, B:23:0x0089), top: B:44:0x005c }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String configureBtIface(boolean r18, java.lang.String r19) {
        /*
            Method dump skipped, instruction units count: 302
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.pan.PanService.configureBtIface(boolean, java.lang.String):java.lang.String");
    }

    private List<BluetoothDevice> getConnectedPanDevices() {
        List<BluetoothDevice> devices = new ArrayList<>();
        for (BluetoothDevice device : this.mPanDevices.keySet()) {
            if (getPanDeviceConnectionState(device) == 2) {
                devices.add(device);
            }
        }
        return devices;
    }

    private int getPanDeviceConnectionState(BluetoothDevice device) {
        BluetoothPanDevice panDevice = this.mPanDevices.get(device);
        if (panDevice == null) {
            return 0;
        }
        return panDevice.mState;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    public void dump(StringBuilder sb) {
        super.dump(sb);
        println(sb, "mMaxPanDevices: " + this.mMaxPanDevices);
        println(sb, "mPanIfName: " + this.mPanIfName);
        println(sb, "mTetherOn: " + this.mTetherOn);
        println(sb, "mPanDevices:");
        for (BluetoothDevice device : this.mPanDevices.keySet()) {
            println(sb, "  " + device.getAddressOnlyforLog() + " : " + this.mPanDevices.get(device));
        }
    }

    private class BluetoothPanDevice {
        private String mIface;
        private int mLocalRole;
        private int mRemoteRole;
        private int mState;

        BluetoothPanDevice(int state, String iface, int localRole, int remoteRole) {
            this.mState = state;
            this.mIface = iface;
            this.mLocalRole = localRole;
            this.mRemoteRole = remoteRole;
        }
    }
}

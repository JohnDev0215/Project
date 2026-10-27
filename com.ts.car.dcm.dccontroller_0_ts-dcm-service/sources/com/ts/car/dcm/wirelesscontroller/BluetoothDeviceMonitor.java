package com.ts.car.dcm.wirelesscontroller;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.ts.bluetooth.BluetoothCallback;
import com.ts.bluetooth.BluetoothConnectManager;
import com.ts.bluetooth.profile.MyBluetoothClass;
import com.ts.bluetooth.profile.Profile;
import com.ts.car.dcm.common.data.CallItem;
import com.ts.car.dcm.common.data.DeviceItem;
import com.ts.car.dcm.common.data.LinkDevice;
import com.ts.car.dcm.common.data.LogEncrypt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import ts.car.dcm.common.data.BtOobInfo;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothDeviceMonitor extends DeviceMonitor implements BluetoothCallback {
    private static final String TAG = BluetoothDeviceMonitor.class.getSimpleName();
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothConnectManager mBluetoothManager;
    private Looper mBtConnLooper;
    private final HashMap<String, BtConnection> mBtConnections;
    private boolean mBtServiceConnected;
    private Context mContext;
    private ArrayList<String> mDeletingBtDevices;
    private ArrayList<String> mDisconnectingOtherDevices;
    private boolean mMonitorReady;
    private int mPlatformCapability;
    private WirelessConnController mWirelessController;

    public static final class BtProfile {
        public static final int A2DP = 4;
        public static final int AVRCP = 8;
        public static final int HFP = 1;
        public static final int MAP = 16;
        public static final int PAN = 32;
        public static final int PBAP = 2;
    }

    public BluetoothDeviceMonitor(WirelessConnController controller, Looper looper) {
        super(looper);
        this.mBtConnections = new HashMap<>();
        this.mDeletingBtDevices = new ArrayList<>();
        this.mDisconnectingOtherDevices = new ArrayList<>();
        this.mWirelessController = controller;
        this.mBtConnLooper = looper;
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public BluetoothDeviceMonitor(Looper looper) {
        super(looper);
        this.mBtConnections = new HashMap<>();
        this.mDeletingBtDevices = new ArrayList<>();
        this.mDisconnectingOtherDevices = new ArrayList<>();
        this.mBtConnLooper = looper;
    }

    public boolean isBtServiceConnected() {
        return this.mBtServiceConnected;
    }

    @Override // com.ts.car.dcm.wirelesscontroller.DeviceMonitor
    public void init(Context context, int platformCapality) {
        this.mContext = context;
        this.mBtServiceConnected = true;
        this.mPlatformCapability = platformCapality;
        BluetoothConnectManager bluetoothConnectManager = new BluetoothConnectManager(context);
        this.mBluetoothManager = bluetoothConnectManager;
        bluetoothConnectManager.setBluetoothCallback(this);
        this.mMonitorReady = true;
    }

    @Override // com.ts.car.dcm.wirelesscontroller.DeviceMonitor
    public boolean isMonitorReady() {
        Log.d(TAG, "isMonitorReady:" + this.mMonitorReady);
        return this.mMonitorReady;
    }

    @Override // com.ts.car.dcm.wirelesscontroller.DeviceMonitor
    public WirelessDeviceDetection getWirelessDeviceDetection() {
        return null;
    }

    @Override // com.ts.car.dcm.wirelesscontroller.DeviceMonitor
    public void processMonitorStart() {
        List<BluetoothDevice> list = this.mBluetoothManager.getConnectedBtDevice();
        Log.d(TAG, "processMonitorStart0");
        if (list != null && list.size() > 0) {
            Log.d(TAG, "processMonitorStart:" + list.size());
            for (BluetoothDevice device : list) {
                BtConnection conn = this.mBtConnections.get(device.getAddress());
                if (conn == null) {
                    DeviceItem deviceItem = new DeviceItem();
                    deviceItem.address = device.getAddress();
                    deviceItem.name = device.getName();
                    connectionAndProfileChanged(deviceItem, device);
                }
            }
        }
    }

    private void connectionAndProfileChanged(DeviceItem deviceItem, BluetoothDevice device) {
        Log.d(TAG, "connectionAndProfileChanged " + device + ",deviceItem:" + deviceItem);
        BtConnection btConnection = BtConnection.make(this.mBtConnLooper, deviceItem, this, this.mPlatformCapability);
        this.mBtConnections.put(btConnection.getDeviceItem().address, btConnection);
        List<Integer> profiles = this.mBluetoothManager.getProfileManager().checkInitProfile(device);
        if (!profiles.isEmpty()) {
            Log.d(TAG, "connectionAndProfileChanged1 " + Arrays.toString(profiles.toArray()));
            Iterator<Integer> it = profiles.iterator();
            while (it.hasNext()) {
                int p = it.next().intValue();
                Integer profile = MyBluetoothClass.PROFILE_TO_SDK.get(Integer.valueOf(p));
                if (profile != null) {
                    btConnection.sendMessage(30, profile.intValue(), 2, deviceItem);
                } else {
                    Log.d(TAG, "connectionAndProfileChanged1.1:" + p);
                }
            }
            return;
        }
        btConnection.sendMessage(30, 7, 2, deviceItem);
    }

    @Override // com.ts.car.dcm.wirelesscontroller.DeviceMonitor
    public void processMonitorStop() {
        Log.d(TAG, "processMonitorStop ");
        for (BtConnection conn : this.mBtConnections.values()) {
            conn.quitNow();
        }
        this.mBtConnections.clear();
        getLooper().quit();
        this.mBtServiceConnected = false;
    }

    @Override // com.ts.car.dcm.wirelesscontroller.DeviceMonitor
    public void processSetCapability(int capability) {
        this.mPlatformCapability = capability;
        for (BtConnection conn : this.mBtConnections.values()) {
            conn.sendMessage(10, capability);
        }
        Log.d(TAG, "processSetCapability, mPlatformCapability:" + this.mPlatformCapability);
    }

    @Override // com.ts.car.dcm.wirelesscontroller.DeviceMonitor
    public void processAcceptDevice(Bundle data) {
        if (data == null) {
            return;
        }
        String devId = data.getString(WirelessMonitorConstants.BUNDLE_ADDRESS);
        boolean result = data.getBoolean(WirelessMonitorConstants.BUNDLE_RESULT);
        BtConnection conn = this.mBtConnections.get(devId);
        if (conn != null) {
            if (result) {
                conn.sendMessage(11, (Object) true);
                return;
            }
            BluetoothDevice device = getBluetoothDevice(devId);
            if (device == null) {
                Log.d(TAG, "connectBtProfile processAcceptDevice is null for mac:" + LogEncrypt.IpAddressEncrypt(devId));
                return;
            }
            this.mBluetoothManager.disconnectAllProfile(device);
        }
    }

    @Override // com.ts.car.dcm.wirelesscontroller.DeviceMonitor
    public void processWirelessDeviceAttach(LinkDevice linkDevice) {
        if (this.mWirelessController != null && linkDevice != null) {
            Log.d(TAG, "processWirelessDeviceAttach, address:" + LogEncrypt.IpAddressEncrypt(linkDevice.getId()));
            this.mWirelessController.notifyWirelessDeviceAttach((LinkDevice) linkDevice.clone());
            this.mWirelessController.notifyBtDevcieConnected(linkDevice.getId(), 0);
        }
    }

    @Override // com.ts.car.dcm.wirelesscontroller.DeviceMonitor
    public void processWirelessDeviceChange(LinkDevice linkDevice, int reason) {
        if (this.mWirelessController != null && linkDevice != null) {
            Log.d(TAG, "processWirelessDeviceChange, address:" + LogEncrypt.IpAddressEncrypt(linkDevice.getId()) + ", capability:" + linkDevice.getCapability());
            this.mWirelessController.notifyWirelssDeviceChange((LinkDevice) linkDevice.clone(), reason);
        }
    }

    @Override // com.ts.car.dcm.wirelesscontroller.DeviceMonitor
    public void processWirelessDeviceDetach(LinkDevice linkDevice) {
        if (linkDevice == null) {
            return;
        }
        Log.d(TAG, "processWirelessDeviceDetach0, address:" + LogEncrypt.IpAddressEncrypt(linkDevice.getId()));
        BtConnection conn = this.mBtConnections.get(linkDevice.getId());
        if (conn == null) {
            return;
        }
        conn.quit();
        Log.d(TAG, "processWirelessDeviceDetach1, mLinkDevice:" + conn.mLinkDevice);
        this.mBtConnections.remove(linkDevice.getId());
        WirelessConnController wirelessConnController = this.mWirelessController;
        if (wirelessConnController != null) {
            wirelessConnController.notifyWirelessDeviceDetach(linkDevice.getId(), 2);
            this.mWirelessController.notifyBtDevcieDisconnected(linkDevice.getId(), 0);
            for (String deleteDevice : this.mDeletingBtDevices) {
                if (deleteDevice.equals(linkDevice.getId())) {
                    this.mDeletingBtDevices.remove(deleteDevice);
                    this.mWirelessController.notifyBtDeviceDeleted(deleteDevice, 0);
                    return;
                }
            }
        }
    }

    public BluetoothDevice getBluetoothDevice(String address) {
        return this.bluetoothAdapter.getRemoteDevice(address);
    }

    public boolean deleteBtDevice(String address) {
        Log.d(TAG, "deleteBtDevice:" + LogEncrypt.MacAddressEncrypt(address));
        BluetoothDevice device = getBluetoothDevice(address);
        if (device == null) {
            return false;
        }
        boolean b1 = this.mBluetoothManager.removeBond(device);
        if (!b1) {
            Log.d(TAG, "deleteBtDevice failed , address:" + address);
        }
        return b1;
    }

    public boolean disconnectBtDevice(String address) {
        Log.d(TAG, "disconnectBtDevice:" + LogEncrypt.MacAddressEncrypt(address));
        BluetoothDevice device = getBluetoothDevice(address);
        if (device == null) {
            return false;
        }
        boolean b1 = this.mBluetoothManager.disconnectAllProfile(device);
        if (!b1) {
            Log.d(TAG, "disconnectBtDevice failed , address:" + address);
        }
        return b1;
    }

    @Deprecated
    public boolean connectBtDevices(String address) {
        Log.d(TAG, "connectBtDevices:" + LogEncrypt.MacAddressEncrypt(address));
        return false;
    }

    public boolean connectBtProfile(String mac, int profile) {
        Log.d(TAG, "connectBtProfile, profile:" + profile + ", mac:" + LogEncrypt.MacAddressEncrypt(mac));
        BluetoothDevice device = getBluetoothDevice(mac);
        if (device == null) {
            Log.d(TAG, "connectBtProfile BluetoothDevice is null for mac:" + LogEncrypt.MacAddressEncrypt(mac));
            return false;
        }
        Profile ret = getProfile(profile);
        if (ret == null) {
            Log.d(TAG, "connectBtProfile getProfile failed! profile:" + profile + ", mac:" + LogEncrypt.MacAddressEncrypt(mac));
            return false;
        }
        Profile retProfile = getProfile(profile);
        boolean b1 = false;
        if (retProfile != null && !(b1 = this.mBluetoothManager.connectProfile(device, retProfile))) {
            Log.d(TAG, "connectBtProfile connect failed! profile:" + profile + ", mac:" + LogEncrypt.MacAddressEncrypt(mac));
        }
        return b1;
    }

    private Profile getProfile(int profile) {
        if (profile == 1) {
            return Profile.Headset;
        }
        if (profile == 2) {
            return Profile.Pbap;
        }
        if (profile == 4) {
            return Profile.A2dp;
        }
        return null;
    }

    public boolean disconnectBtProfile(String mac, int profile) {
        Log.d(TAG, "disconnectBtProfile, profile:" + profile + ", mac:" + LogEncrypt.MacAddressEncrypt(mac));
        BluetoothDevice device = getBluetoothDevice(mac);
        if (device == null) {
            Log.d(TAG, "disconnectBtProfile BluetoothDevice is null for mac:" + LogEncrypt.MacAddressEncrypt(mac));
            return false;
        }
        boolean b1 = this.mBluetoothManager.disconnectProfile(device, getProfile(profile));
        if (!b1) {
            Log.d(TAG, "disconnectBtProfile disconnect failed! mac:" + LogEncrypt.MacAddressEncrypt(mac));
        }
        return b1;
    }

    public boolean isBluetoothEnabled() {
        Log.d(TAG, "isBluetoothEnabled");
        return this.mBluetoothManager.isEnabled();
    }

    public boolean enableBluetooth() {
        Log.d(TAG, "enableBluetooth");
        return this.mBluetoothManager.enable();
    }

    public boolean disableBluetooth() {
        Log.d(TAG, "disableBluetooth");
        return this.mBluetoothManager.disable();
    }

    public boolean requestBluetoothPairing(String mac) {
        Log.d(TAG, "requestBluetoothPairing:" + LogEncrypt.MacAddressEncrypt(mac));
        BluetoothDevice device = getBluetoothDevice(mac);
        if (device == null) {
            Log.d(TAG, "requestBluetoothPairing device is null for mac:" + LogEncrypt.MacAddressEncrypt(mac));
            return false;
        }
        if (device.getBondState() == 11) {
            return true;
        }
        boolean b1 = this.mBluetoothManager.createBond(device);
        if (!b1) {
            Log.d(TAG, "requestBluetoothPairing failed! mac:" + LogEncrypt.MacAddressEncrypt(mac));
        }
        return b1;
    }

    public Set<BluetoothDevice> getBondedDevices() {
        Log.d(TAG, "getBondedDevices");
        return this.mBluetoothManager.getBondedDevices();
    }

    public boolean isConnected(BluetoothDevice device) {
        Log.d(TAG, "isConnected：" + device);
        return this.mBluetoothManager.isConnected(device);
    }

    public List<BluetoothDevice> getConnectedBtDevice() {
        Log.d(TAG, "getConnectedBtDevice");
        return this.mBluetoothManager.getConnectedBtDevice();
    }

    public BtOobInfo getBtOobInfo() {
        Log.d(TAG, "getBtOobInfo");
        return new BtOobInfo(this.mBluetoothManager.getBluetoothName(), this.mBluetoothManager.getBluetoothAddress(), -1);
    }

    public boolean setBtOobInfo(String name, String linkKey, String mac, int linkKeyType) {
        Log.e(TAG, "setBtOobInfo, name:" + name + ", kink:" + linkKey + ", type:" + linkKeyType);
        return false;
    }

    public boolean deleteBtConnection(String address) {
        if (this.mBtConnections.get(address) != null) {
            this.mBtConnections.remove(address);
        } else {
            Log.d(TAG, "deleteBtConnection, no btConnection, address: " + LogEncrypt.MacAddressEncrypt(address));
        }
        if (this.mBtConnections.get(address) != null) {
            Log.d(TAG, "deleteBtConnection, failure, address: " + LogEncrypt.MacAddressEncrypt(address));
            return false;
        }
        Log.d(TAG, "deleteBtConnection, success, address: " + LogEncrypt.MacAddressEncrypt(address));
        return true;
    }

    @Override // com.ts.car.dcm.wirelesscontroller.DeviceMonitor, android.os.Handler
    public void handleMessage(Message msg) {
        Log.d(TAG, "handleMessage msg.what:" + eventState(msg.what));
        super.handleMessage(msg);
        int i = msg.what;
        if (i == 202) {
            String address = (String) msg.obj;
            int capability = msg.arg1;
            processSetDeviceCapability(address, capability);
        }
        if (i == 203) {
            String address2 = (String) msg.obj;
            processDisconnectOtherBtDevices(address2);
            return;
        }
        if (i == 601) {
            String address3 = (String) msg.obj;
            int profile = msg.arg1;
            int newState = msg.arg2;
            processBtConnProfileStateChanged(address3, profile, newState);
            return;
        }
        switch (i) {
            case 501:
                String address4 = (String) msg.obj;
                int profile2 = msg.arg1;
                int newState2 = msg.arg2;
                processBtConnectionStateChanged(address4, profile2, newState2);
                break;
            case 502:
                DeviceItem item = (DeviceItem) msg.obj;
                int oldState = msg.arg1;
                int newState3 = msg.arg2;
                processBtBondStateChanged(item, newState3, oldState);
                break;
            case 503:
                String address5 = (String) msg.obj;
                processBtDeviceDisconnected(address5);
                break;
            case 504:
                Bundle data = msg.getData();
                processBtCallStateChanged(data);
                break;
            case 505:
                String address6 = (String) msg.obj;
                int newState4 = msg.arg1;
                processBtAudioStateChanged(address6, newState4);
                break;
            case 506:
                int newState5 = msg.arg1;
                processBtStateChanged(newState5);
                break;
            case 507:
                String address7 = (String) msg.obj;
                int newState6 = msg.arg1;
                processBtOobPairComplete(address7, newState6);
                break;
            case 508:
                String address8 = (String) msg.obj;
                processBtReportUuids(address8);
                break;
        }
    }

    private String eventState(int what) {
        if (what == 202) {
            return "EVENT_DCM_SET_DEVICE_CAPABILITY";
        }
        if (what == 203) {
            return "EVENT_DCM_DISCONNECT_OTHER_BT_DEVICES";
        }
        if (what == 601) {
            return "EVENT_BTCONN_PROFILE_STATE_CHANGE";
        }
        switch (what) {
            case 501:
                return "EVENT_BLUETOOTH_PROFILE_STATE_CHANGE";
            case 502:
                return "EVENT_BLUETOOTH_BOND_STATE_CHANGE";
            case 503:
                return "EVENT_BLUETOOTH_DEVICE_DISCONNECTED";
            case 504:
                return "EVENT_BLUETOOTH_CALL_STATE_CHANGE";
            case 505:
                return "EVENT_BLUETOOTH_AUDIO_STATE_CHANGE";
            case 506:
                return "EVENT_BLUETOOTH_STATE_CHANGE";
            case 507:
                return "EVENT_BLUETOOTH_OOB_PAIR_COMPLETE";
            case 508:
                return "EVENT_BLUETOOTH_GET_REMOTE_UUIDS";
            default:
                return null;
        }
    }

    private DeviceItem getDeviceItem(String address) {
        Log.d(TAG, "getDeviceItem, address:" + LogEncrypt.MacAddressEncrypt(address));
        Set<BluetoothDevice> bondedDevices = this.mBluetoothManager.getBondedDevices();
        if (bondedDevices != null) {
            Log.d(TAG, "getDeviceItem, bondedDevices.size:" + bondedDevices.size());
            for (BluetoothDevice item : bondedDevices) {
                if (item.getAddress().equals(address)) {
                    DeviceItem deviceItem = new DeviceItem();
                    deviceItem.address = address;
                    deviceItem.name = item.getName();
                    return deviceItem;
                }
            }
            return null;
        }
        return null;
    }

    private BtConnection createBtConnection(DeviceItem deviceItem) {
        Log.d(TAG, "createBtConnection, address:" + LogEncrypt.MacAddressEncrypt(deviceItem.address));
        return BtConnection.make(this.mBtConnLooper, deviceItem, this, this.mPlatformCapability);
    }

    private void processBtReportUuids(String str) {
        DeviceItem deviceItem = getDeviceItem(str);
        BluetoothDevice bluetoothDevice = getBluetoothDevice(str);
        if (bluetoothDevice == null) {
            Log.d(TAG, "processBtReportUuids device is null for mac:" + LogEncrypt.MacAddressEncrypt(str));
            return;
        }
        BtConnection btConnection = this.mBtConnections.get(str);
        if (btConnection != null) {
            boolean zIsPaired = this.mBluetoothManager.isPaired(bluetoothDevice);
            Log.d(TAG, "processBtReportUuids, exist address:" + LogEncrypt.MacAddressEncrypt(str) + ", isPaired:" + zIsPaired);
            btConnection.sendMessage(28, zIsPaired ? 1 : 0, 0, deviceItem);
            btConnection.getHandler().removeMessages(12);
            return;
        }
        if (deviceItem != null) {
            BtConnection btConnectionCreateBtConnection = createBtConnection(deviceItem);
            if (btConnectionCreateBtConnection != null) {
                boolean zIsPaired2 = this.mBluetoothManager.isPaired(bluetoothDevice);
                Log.d(TAG, "processBtReportUuids, create address:" + LogEncrypt.MacAddressEncrypt(str) + ", isPaired:" + zIsPaired2);
                this.mBtConnections.put(btConnectionCreateBtConnection.getDeviceItem().address, btConnectionCreateBtConnection);
                btConnectionCreateBtConnection.sendMessage(28, zIsPaired2 ? 1 : 0, 0, deviceItem);
                return;
            }
            Log.e(TAG, "processBtReportUuids, crate connection failure.");
            return;
        }
        Log.e(TAG, "processBtReportUuids getDeviceItem is null.");
    }

    private void processBtConnectionStateChanged(String mac, int profile, int state) {
        Log.d(TAG, "processBtConnectionStateChanged, mac:" + LogEncrypt.MacAddressEncrypt(mac) + ", profile:" + profile + ", state:" + state);
        BtConnection btConnection = this.mBtConnections.get(mac);
        if (state != 0) {
            if (state == 1 || state == 2) {
                DeviceItem deviceItem = getDeviceItem(mac);
                if (btConnection == null && deviceItem != null) {
                    btConnection = createBtConnection(deviceItem);
                }
                if (btConnection != null) {
                    this.mBtConnections.put(btConnection.getDeviceItem().address, btConnection);
                    btConnection.sendMessage(30, profile, state, deviceItem);
                    return;
                }
                return;
            }
            if (state != 3) {
                return;
            }
        }
        if (btConnection != null) {
            btConnection.sendMessage(30, profile, state);
        }
    }

    private void processBtDeviceDisconnected(String address) {
        Log.d(TAG, "processBtDeviceDisconnected, address:" + LogEncrypt.MacAddressEncrypt(address));
        BtConnection conn = this.mBtConnections.get(address);
        if (conn != null) {
            conn.sendMessage(25);
        }
        for (String device : this.mDisconnectingOtherDevices) {
            Log.d(TAG, "processBtDeviceDisconnected, address:" + LogEncrypt.MacAddressEncrypt(address) + ", device:" + device);
            if (address.equals(device)) {
                this.mDisconnectingOtherDevices.remove(device);
                if (this.mDisconnectingOtherDevices.size() == 1) {
                    WirelessConnController wirelessConnController = this.mWirelessController;
                    if (wirelessConnController != null) {
                        wirelessConnController.notifyOtherBtDeviceDisconnected(this.mDisconnectingOtherDevices.get(0), 0);
                    }
                    this.mDisconnectingOtherDevices.clear();
                    return;
                }
                return;
            }
        }
    }

    private void processBtCallStateChanged(Bundle data) {
        BtConnection conn;
        Log.d(TAG, "processBtCallStateChanged, data:" + data);
        if (data != null) {
            String address = data.getString(WirelessMonitorConstants.BUNDLE_ADDRESS, null);
            CallItem item = (CallItem) data.getParcelable(WirelessMonitorConstants.BUNDLE_CALLITEM);
            if (address != null && item != null && (conn = this.mBtConnections.get(address)) != null) {
                boolean inCall = false;
                if (item.state != 7) {
                    inCall = true;
                }
                if (conn.isInCallState() != inCall) {
                    conn.setInCallState(inCall);
                    WirelessConnController wirelessConnController = this.mWirelessController;
                    if (wirelessConnController != null) {
                        wirelessConnController.notifyBtCallStateChange(address, inCall);
                    }
                }
            }
        }
    }

    private void processBtAudioStateChanged(String address, int state) {
        BtConnection conn;
        boolean isAudioPlay;
        Log.d(TAG, "processBtAudioStateChanged, address:" + LogEncrypt.MacAddressEncrypt(address) + ",state:" + state);
        if (address != null && (conn = this.mBtConnections.get(address)) != null) {
            if (state == 3) {
                isAudioPlay = true;
            } else if (state == 2) {
                isAudioPlay = false;
            } else {
                return;
            }
            if (conn.isInAudioPlayState() != isAudioPlay) {
                conn.setInAudioPlayState(isAudioPlay);
                WirelessConnController wirelessConnController = this.mWirelessController;
                if (wirelessConnController != null) {
                    wirelessConnController.notifyBtAudioStateChange(address, isAudioPlay);
                }
            }
        }
    }

    private void processBtStateChanged(int state) {
        Log.d(TAG, "processBtStateChanged, state:" + state);
        if (this.mWirelessController != null) {
            int localState = 0;
            if (state == 10) {
                for (Map.Entry<String, BtConnection> entry : this.mBtConnections.entrySet()) {
                    BtConnection conn = entry.getValue();
                    if (conn != null) {
                        conn.sendMessage(25);
                    }
                }
                localState = 10;
            } else if (state == 12) {
                localState = 12;
            } else if (state == 13) {
                localState = 13;
            } else if (state == 11) {
                localState = 11;
            }
            if (localState != 0) {
                this.mWirelessController.notifyBtStateChange(localState);
            }
        }
    }

    private void processBtOobPairComplete(String devId, int status) {
        Log.d(TAG, "processBtOobPairComplete, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ",status:" + status);
        WirelessConnController wirelessConnController = this.mWirelessController;
        if (wirelessConnController != null) {
            wirelessConnController.notifyBtOobPairComplete(devId, status);
        }
    }

    private void processBtBondStateChanged(DeviceItem deviceItem, int newState, int oldState) {
        BtConnection conn;
        if (this.mWirelessController != null && deviceItem != null) {
            if (newState == 10) {
                BtConnection conn2 = this.mBtConnections.get(deviceItem.address);
                if (conn2 == null) {
                    this.mWirelessController.notifyBtDeviceDeleted(deviceItem.address, 0);
                } else {
                    conn2.sendMessage(26, 0, newState, deviceItem);
                    this.mDeletingBtDevices.add(deviceItem.address);
                }
            } else if (newState == 12) {
                BtConnection conn3 = this.mBtConnections.get(deviceItem.address);
                if (conn3 != null) {
                    conn3.sendMessage(26, 1, newState, deviceItem);
                } else {
                    createBtConnAndSendConnMsg(deviceItem);
                }
            } else if (newState == 11 && (conn = this.mBtConnections.get(deviceItem.address)) != null) {
                conn.sendMessage(26, 0, newState, deviceItem);
            }
            this.mWirelessController.notifyBtPairingChanged(deviceItem.address, newState);
        }
    }

    private BtConnection createBtConnAndSendConnMsg(DeviceItem deviceItem) {
        int bondState = this.bluetoothAdapter.getRemoteDevice(deviceItem.address).getBondState();
        boolean isPaired = bondState == 12;
        BtConnection btConnection = createBtConnection(deviceItem);
        Log.d(TAG, "createBtConnAndSendConnMsg, address:" + LogEncrypt.MacAddressEncrypt(deviceItem.address) + ", isPaired:" + isPaired);
        if (btConnection != null) {
            this.mBtConnections.put(btConnection.getDeviceItem().address, btConnection);
            btConnection.sendMessage(26, isPaired ? 1 : 0, 0, deviceItem);
        }
        return btConnection;
    }

    public void processBtConnProfileStateChanged(String address, int profile, int newState) {
        Log.d(TAG, "processBtConnProfileStateChanged, address:" + LogEncrypt.MacAddressEncrypt(address) + ",profile:" + profile + ",newState:" + newState);
        WirelessConnController wirelessConnController = this.mWirelessController;
        if (wirelessConnController != null) {
            wirelessConnController.notifyBtProfileChange(address, profile, newState);
        }
    }

    private void processDisconnectOtherBtDevices(String devId) {
        BluetoothDevice device;
        Log.d(TAG, "processDisconnectOtherBtDevices, devId:" + devId);
        BtConnection conn = this.mBtConnections.get(devId);
        if (conn != null && this.mDisconnectingOtherDevices.size() == 0) {
            this.mDisconnectingOtherDevices.add(devId);
            for (String address : this.mBtConnections.keySet()) {
                Log.d(TAG, "processDisconnectOtherBtDevices, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", address:" + LogEncrypt.MacAddressEncrypt(address));
                if (!devId.equals(address) && (device = getBluetoothDevice(devId)) != null) {
                    this.mBluetoothManager.disconnectAllProfile(device);
                    this.mDisconnectingOtherDevices.add(address);
                }
            }
            return;
        }
        WirelessConnController wirelessConnController = this.mWirelessController;
        if (wirelessConnController != null) {
            wirelessConnController.notifyOtherBtDeviceDisconnected(devId, -1);
        }
    }

    private void processSetDeviceCapability(String devId, int capability) {
        Log.d(TAG, "processSetDeviceCapability, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ",capability:" + capability);
        BtConnection conn = this.mBtConnections.get(devId);
        if (conn != null) {
            conn.sendMessage(27, capability);
        }
    }

    public void checkWirelessCarplayCapability(String address) {
        Log.d(TAG, "checkWirelessCarplayCapability, address:" + LogEncrypt.MacAddressEncrypt(address));
        WirelessConnController wirelessConnController = this.mWirelessController;
        if (wirelessConnController != null) {
            wirelessConnController.checkWirelessCarplayCapability(address);
        }
    }

    public String getDeviceInfo(BluetoothDevice device) {
        if (device == null) {
            return null;
        }
        return "name:" + device.getName() + ",Address:" + device.getAddress() + ",Alias:" + device.getAlias() + ",UUID:" + Arrays.toString(device.getUuids()) + ",BondState:" + device.getBondState() + ",type:" + device.getType();
    }

    @Override // com.ts.bluetooth.BluetoothCallback
    public void onBondedStateChanged(BluetoothDevice device, int bondStatus) {
        DeviceItem deviceItem = new DeviceItem();
        deviceItem.name = device.getName();
        deviceItem.address = device.getAddress();
        Message msg = obtainMessage(502);
        msg.obj = deviceItem;
        msg.arg2 = bondStatus;
        msg.sendToTarget();
    }

    @Override // com.ts.bluetooth.BluetoothCallback
    public void onProfileConnectedStateChanged(BluetoothDevice device, Profile profile, boolean connected) {
        Log.d(TAG, "onProfileConnectedStateChanged:" + device + ",profile:" + profile + ",connected:" + connected);
        int i = AnonymousClass1.$SwitchMap$com$ts$bluetooth$profile$Profile[profile.ordinal()];
        if (i == 1) {
            Message msg = obtainMessage(501);
            msg.obj = device.getAddress();
            msg.arg1 = 2;
            msg.arg2 = connected ? 2 : 0;
            msg.sendToTarget();
            return;
        }
        if (i == 2) {
            Message hfpMsg = obtainMessage(501);
            hfpMsg.obj = device.getAddress();
            hfpMsg.arg1 = 0;
            hfpMsg.arg2 = connected ? 2 : 0;
            hfpMsg.sendToTarget();
        }
    }

    /* JADX INFO: renamed from: com.ts.car.dcm.wirelesscontroller.BluetoothDeviceMonitor$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$ts$bluetooth$profile$Profile;

        static {
            int[] iArr = new int[Profile.values().length];
            $SwitchMap$com$ts$bluetooth$profile$Profile = iArr;
            try {
                iArr[Profile.A2dp.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$ts$bluetooth$profile$Profile[Profile.Headset.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    @Override // com.ts.bluetooth.BluetoothCallback
    public void onBtStateChanged(int newState) {
        Log.e(TAG, "onBtStateChanged, newState:" + newState);
        Message msg = obtainMessage(506);
        switch (newState) {
            case 10:
                msg.arg1 = 10;
                break;
            case 11:
                msg.arg1 = 11;
                break;
            case 12:
                msg.arg1 = 12;
                break;
            case 13:
                msg.arg1 = 13;
                break;
            default:
                msg.arg1 = 0;
                Log.e(TAG, "onBtStateChanged, invalid newState:" + newState);
                break;
        }
        if (msg.arg1 != 0) {
            msg.sendToTarget();
        }
    }
}

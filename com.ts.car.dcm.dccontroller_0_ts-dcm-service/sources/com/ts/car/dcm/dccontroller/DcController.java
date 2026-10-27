package com.ts.car.dcm.dccontroller;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.SoftApConfiguration;
import android.net.wifi.WifiConfiguration;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.ts.car.dcm.common.data.DcmConsumer;
import com.ts.car.dcm.common.data.DcmUtils;
import com.ts.car.dcm.common.data.LinkDevice;
import com.ts.car.dcm.common.data.LogEncrypt;
import com.ts.car.dcm.dccontroller.configdata.ConfigManager;
import com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback;
import com.ts.car.dcm.dccontroller.controllermanager.ControllerManager;
import com.ts.car.dcm.dccontroller.projectionmanager.ProjectionManager;
import com.ts.car.dcm.dccontroller.storemanager.Device;
import com.ts.car.dcm.dccontroller.storemanager.DeviceManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import ts.car.dcm.common.data.AbnormalDevice;
import ts.car.dcm.common.data.BtOobInfo;
import ts.car.dcm.common.data.WifiApConfiguration;
import ts.car.dcm.common.data.WifiConfigInfo;

/* JADX INFO: loaded from: classes.dex */
public class DcController {
    private static final String ACTION_SHUTDOWN = "android.intent.action.ACTION_SHUTDOWN";
    private static final int DEVICE_INCLUDE_CONNECTION_TYPE_BT = 2;
    private static final int DEVICE_INCLUDE_CONNECTION_TYPE_USB = 1;
    private static final int DEVICE_INCLUDE_CONNECTION_TYPE_WIFI = 4;
    private static final String DHCP_LEASES_FILE = "/proc/net/arp";
    public static final int EVENT_ABNORMAL_DEVICE_ATTACH = 300;
    public static final int EVENT_ABNORMAL_DEVICE_DETACH = 301;
    public static final int EVENT_BT_AUDIO_STATE_CHANGED = 207;
    public static final int EVENT_BT_CALL_STATE_CHANGED = 206;
    public static final int EVENT_BT_CONNECTED = 204;
    public static final int EVENT_BT_DELETED = 203;
    public static final int EVENT_BT_DISCONNECTED = 202;
    public static final int EVENT_BT_OOB_PAIR_COMPLETE = 209;
    public static final int EVENT_BT_OTHER_DISCONNECTED = 205;
    public static final int EVENT_BT_PAIR_STATE_CHANGED = 210;
    public static final int EVENT_BT_PROFILE_CHANGED = 201;
    public static final int EVENT_BT_STATE_CHANGED = 208;
    public static final int EVENT_CHECK_WIRELESS_CARPLAY_CAPABILITY = 292;
    public static final int EVENT_DEVICE_ATTACH = 100;
    public static final int EVENT_DEVICE_CONNECTION_CHANGED = 102;
    public static final int EVENT_DEVICE_DETACH = 101;
    public static final int EVENT_NOTIFY_DEVICES_DELETED = 282;
    public static final int EVENT_NOTIFY_DEVICES_MERGED = 281;
    public static final int EVENT_USB_AOA_FINISHED = 155;
    public static final int EVENT_USB_AOA_STARTING = 154;
    public static final int EVENT_USB_RESET_FINISHED = 153;
    public static final int EVENT_USB_RESET_STARTING = 152;
    public static final int EVENT_USB_ROLESWITCH_FINISHED = 151;
    public static final int EVENT_USB_ROLESWITCH_STARTING = 150;
    public static final int EVENT_WIFI_AP_INFO = 271;
    public static final int EVENT_WIFI_CONNECT_STATE = 270;
    public static final int EVENT_WIRELESS_PROJECTION_DEVICE_FOUND = 291;
    private static final int FLAG_RECEIVER_EXCLUDE_BACKGROUND = 16777216;
    private static final String TAG = "DcController";
    private static DcController sInstance = null;
    private ICmsCallback mCmsCallback;
    private ConfigManager mConfigManager;
    private Context mContext;
    private ControllerManager mControllerManager;
    private DcBroadcastReceiver mDcBroadcastReceiver;
    private DeviceManager mDeviceManager;
    private DccMessageHandler mHandler;
    private HandlerThread mHandlerThread;
    private Looper mLooper;
    private ProjectionManager mProjectionManager;
    private final Object mCmsCbLock = new Object();
    private List<String> mBtDeviceBondedMessageList = new ArrayList();

    private DcController(Context context) {
        Log.e(TAG, "Init DcController.");
        this.mContext = context;
        Log.d(TAG, "init:" + BluetoothAdapter.getDefaultAdapter());
        ConfigManager configManager = new ConfigManager(context, this);
        this.mConfigManager = configManager;
        this.mDeviceManager = new DeviceManager(context, this, configManager);
        this.mControllerManager = new ControllerManager(this, context);
        if (this.mHandlerThread == null) {
            HandlerThread handlerThread = new HandlerThread("DcControllerHandlerThread");
            this.mHandlerThread = handlerThread;
            handlerThread.start();
        }
        this.mLooper = this.mHandlerThread.getLooper();
        this.mProjectionManager = new ProjectionManager(this, this.mLooper, context);
        this.mHandler = new DccMessageHandler(this.mLooper);
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_SHUTDOWN);
        DcBroadcastReceiver dcBroadcastReceiver = new DcBroadcastReceiver();
        this.mDcBroadcastReceiver = dcBroadcastReceiver;
        context.registerReceiver(dcBroadcastReceiver, filter);
    }

    public static synchronized DcController getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DcController(context);
        }
        return sInstance;
    }

    public synchronized void dispose() {
        if (this.mDeviceManager != null) {
            this.mDeviceManager.dispose();
        }
    }

    public ConfigManager getConfigManager() {
        return this.mConfigManager;
    }

    public DeviceManager getDeviceManager() {
        return this.mDeviceManager;
    }

    public ProjectionManager getProjectionManager() {
        return this.mProjectionManager;
    }

    public ControllerManager getControllerManager() {
        return this.mControllerManager;
    }

    public Context getContext() {
        return this.mContext;
    }

    public void registerCmsCallback(ICmsCallback cb) {
        synchronized (this.mCmsCbLock) {
            this.mCmsCallback = cb;
        }
    }

    public boolean connectDevice(String devId, int type) {
        Log.d(TAG, "connectDevice, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", type:" + type);
        Device connDevice = this.mDeviceManager.getDevice(devId);
        if (connDevice != null) {
            if (type == 1) {
                boolean ret = this.mControllerManager.enableUsbDevice(connDevice);
                return ret;
            }
            if (type == 2) {
                boolean ret2 = this.mControllerManager.connectBtDevice(connDevice.getBtAddr());
                return ret2;
            }
            Log.e(TAG, "connectDevice, do not support the connection type:" + type);
            return false;
        }
        Log.e(TAG, "connectDevice, can not find the device, devId:" + LogEncrypt.IpAddressEncrypt(devId));
        return false;
    }

    public boolean disconnectDevice(String devId, int type) {
        Log.e(TAG, "disconnectDevice, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", type:" + type);
        Device connDevice = this.mDeviceManager.getDevice(devId);
        if (connDevice != null) {
            if (type == 1) {
                boolean ret = this.mControllerManager.disableUsbDevice(connDevice);
                return ret;
            }
            if (type == 2) {
                boolean ret2 = this.mControllerManager.disconnectBtDevice(connDevice.getBtAddr());
                return ret2;
            }
            Log.e(TAG, "disconnectDevice, do not support the connection type:" + type);
            return false;
        }
        Log.e(TAG, "disconnectDevice, can not find the device, devId:" + LogEncrypt.IpAddressEncrypt(devId));
        return false;
    }

    private boolean removeOnlineDevice(Device device) {
        Log.d(TAG, "removeOnlineDevice :" + device);
        boolean ret = true;
        device.setDeviceConnectedState(2);
        Set<Integer> typeSet = device.getDeviceConnTypeSet();
        Iterator<Integer> it = typeSet.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            int type = it.next().intValue();
            if (device.getConnectedTypeState(type) == 3) {
                if (type == 1) {
                    ret = this.mControllerManager.removeUsbDevice(device);
                }
                if (!ret) {
                    Log.w(TAG, "removeOnlineDevice, failed, the device type:" + type);
                    break;
                }
            }
        }
        if (device.isPaired()) {
            if (!this.mControllerManager.removeBtDevice(device)) {
                Log.w(TAG, "removeOnlineDevice, removeBtDevice failed, id:" + LogEncrypt.IpAddressEncrypt(device.getId()));
            } else {
                this.mProjectionManager.handleBtDeviceRemoved(device.getId(), device.getBtAddr());
            }
        }
        return ret;
    }

    private boolean removeDisabledDevice(Device device) {
        device.setDeviceConnectedState(2);
        if (device.getConnectedTypeState(1) == 4) {
            boolean ret = this.mControllerManager.removeUsbDevice(device);
            return ret;
        }
        Log.e(TAG, "removeDisabledDevice, the usb device is not disconnected. devId:" + LogEncrypt.IpAddressEncrypt(device.getId()));
        return false;
    }

    private boolean removeOfflineDevice(final Device device) {
        device.setDeviceConnectedState(2);
        if (device.isPaired()) {
            if (!this.mControllerManager.removeBtDevice(device)) {
                Log.e(TAG, "removeOfflineDevice, removeBtDevice failed, id:" + LogEncrypt.IpAddressEncrypt(device.getId()));
            } else {
                this.mProjectionManager.handleBtDeviceRemoved(device.getId(), device.getBtAddr());
            }
        }
        if (this.mDeviceManager.removeDevice(device.getId()) != 0) {
            Log.e(TAG, "removeOfflineDevice, failed, the device id:" + LogEncrypt.IpAddressEncrypt(device.getId()));
            return false;
        }
        notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$m4wwMxWsnl4A7TMLqWbG-n2CR5E
            @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
            public final void accept(Object obj) {
                ((ICmsCallback) obj).onDeviceStatusChanged(device);
            }
        });
        Log.e(TAG, "removeOfflineDevice, success.");
        if (this.mControllerManager.removeUsbConnection(device)) {
            Log.d(TAG, "removeOfflineDevice, deleteUsbConnection success.");
        }
        if (!this.mControllerManager.removeBtConnection(device)) {
            return true;
        }
        Log.d(TAG, "removeOfflineDevice, deleteBtConnection success.");
        return true;
    }

    public boolean removeDevice(Device device) {
        int state = device.getDeviceConnectedState();
        Log.e(TAG, "removeDevice, state: " + state);
        if (state == 1) {
            boolean ret = removeOnlineDevice(device);
            return ret;
        }
        if (state == 3) {
            boolean ret2 = removeDisabledDevice(device);
            return ret2;
        }
        if (state == 0 || state == 4) {
            boolean ret3 = removeOfflineDevice(device);
            return ret3;
        }
        return true;
    }

    public boolean reattachWifiDevice(String mac) {
        return this.mControllerManager.reattachWifiDevice(mac);
    }

    public int getServiceTypeByRoleswitchPurpose(int purpose) {
        if (purpose == 2) {
            return 2;
        }
        if (purpose != 1 && purpose != 3) {
            return 0;
        }
        return 1;
    }

    public boolean doUsbRoleSwitch(String devId, int mode, int purpose) {
        Device dev = this.mDeviceManager.getDevice(devId);
        if (dev != null && dev.hasConnection(1)) {
            int service = getServiceTypeByRoleswitchPurpose(purpose);
            if (this.mProjectionManager.isAllowUsbRoleSwitch(devId, purpose) && this.mControllerManager.doUsbRoleSwitch(dev, mode, service)) {
                if (mode == 1) {
                    dev.setInUsbDeviceMode(true);
                }
                dev.setUsbRoleSwitchPurpose(purpose);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean resetUsb(String devId) {
        Device dev = this.mDeviceManager.getDevice(devId);
        if (dev != null && dev.hasConnection(1)) {
            return this.mControllerManager.resetUsbBus(dev);
        }
        return false;
    }

    public boolean enableUsbAoA(String devId, int mode, boolean flag) {
        Device dev = this.mDeviceManager.getDevice(devId);
        if (dev != null && dev.hasConnection(1)) {
            return this.mControllerManager.enableUsbAoA(dev, mode, DcmUtils.boolToInt(flag));
        }
        return false;
    }

    public boolean enableUsbAoA(String devId, String serialNumber, int mode, boolean flag) {
        Device dev = this.mDeviceManager.getDevice(devId);
        if (dev != null && dev.hasConnection(1)) {
            return this.mControllerManager.enableUsbAoA(dev, serialNumber, mode, DcmUtils.boolToInt(flag));
        }
        return false;
    }

    public boolean connectBtDevice(String devId, String mac) {
        Device dev = this.mDeviceManager.getDevice(devId);
        if (dev != null) {
            if ((dev.getBtAddr() == null || dev.getBtAddr().isEmpty()) && mac != null) {
                dev.setBtAddr(mac);
                this.mDeviceManager.saveDevice(dev);
            }
            return this.mControllerManager.connectBtDevice(mac);
        }
        return false;
    }

    public boolean disconnectBtDevice(String devId, String mac) {
        Device dev = this.mDeviceManager.getDevice(devId);
        if (dev != null && dev.hasConnection(2)) {
            if ((dev.getBtAddr() == null || dev.getBtAddr().isEmpty()) && mac != null) {
                dev.setBtAddr(mac);
                this.mDeviceManager.saveDevice(dev);
            }
            return this.mControllerManager.disconnectBtDevice(mac);
        }
        return false;
    }

    public boolean disconnectOtherBtDevices(String devId, String mac) {
        Device dev = this.mDeviceManager.getDevice(devId);
        if (dev == null) {
            return false;
        }
        if ((dev.getBtAddr() == null || dev.getBtAddr().isEmpty()) && (mac == null || mac.isEmpty())) {
            Log.e(TAG, "disconnectOtherBtDevices, Do not have available bt mac address.");
            return false;
        }
        if ((dev.getBtAddr() == null || dev.getBtAddr().isEmpty()) && mac != null) {
            dev.setBtAddr(mac);
            this.mDeviceManager.saveDevice(dev);
        }
        return this.mControllerManager.disconnectOtherBtDevices(dev);
    }

    public boolean isBluetoothEnabled() {
        return this.mControllerManager.isBluetoothEnabled();
    }

    public boolean enableBluetooth(boolean flag) {
        return this.mControllerManager.enableBluetooth(flag);
    }

    public boolean connectBtProfile(String devId, String mac, int profile) {
        if (mac == null || mac.isEmpty()) {
            Log.e(TAG, "connectBtProfile, Invalid parameter, BT mac address is null or empty.");
            return false;
        }
        Device dev = this.mDeviceManager.getDevice(devId);
        if (dev == null) {
            return false;
        }
        if ((dev.getBtAddr() == null || dev.getBtAddr().isEmpty()) && mac != null) {
            dev.setBtAddr(mac);
            this.mDeviceManager.saveDevice(dev);
        }
        return this.mControllerManager.connectBtProfile(mac, profile);
    }

    public boolean disconnectBtProfile(String devId, String mac, int profile) {
        if (mac == null || mac.isEmpty()) {
            Log.e(TAG, "disconnectBtProfile, Invalid parameterm, BT mac address is null or empty.");
            return false;
        }
        Device dev = this.mDeviceManager.getDevice(devId);
        if (dev == null) {
            return false;
        }
        if ((dev.getBtAddr() == null || dev.getBtAddr().isEmpty()) && mac != null) {
            dev.setBtAddr(mac);
            this.mDeviceManager.saveDevice(dev);
        }
        return this.mControllerManager.disconnectBtProfile(mac, profile);
    }

    public BtOobInfo getBtOobInfo() {
        return this.mControllerManager.getBtOobInfo();
    }

    public boolean setBtOobInfo(String name, String linkKey, String mac, int linkKeyType) {
        return this.mControllerManager.setBtOobInfo(name, linkKey, mac, linkKeyType);
    }

    public boolean requestBluetoothPairing(String mac) {
        Log.d(TAG, " requestBluetoothPairing, mac:" + LogEncrypt.MacAddressEncrypt(mac));
        return this.mControllerManager.requestBluetoothPairing(mac);
    }

    public boolean enableWifiAp(boolean enable, WifiApConfiguration config) {
        return this.mControllerManager.enableWifiAp(enable, config);
    }

    public void setPlatformCapability(int capability) {
        this.mControllerManager.setCapability(capability);
    }

    public void updateCarlifeSupportStatus(int deviceType, boolean flag) {
        this.mControllerManager.updateCarlifeSupportStatus(deviceType, flag);
    }

    public void notifyDeviceCapabilityChanged(Device device) {
        this.mControllerManager.setDeviceCapabilitys(device);
    }

    public void startConnectionMonitor() {
        this.mControllerManager.startConnectionMonitor();
    }

    public void stopConnectionMonitor() {
        this.mControllerManager.stopConnectionMonitor();
    }

    private int getAccessTypesForMergeDev(Device device) {
        int accessTypes = 0;
        if (device.getUsbSerialNumber() != null) {
            accessTypes = 0 | 1;
        }
        if (device.getBtAddr() != null) {
            accessTypes |= 2;
        }
        if (device.getWifiAddr() != null) {
            return accessTypes | 4;
        }
        return accessTypes;
    }

    private Device getPreferenceDevice(Device firstDevice, int firstDevConnTypes, Device secondDevice, int secondDevConnTypes) {
        if ((firstDevice.getActiveCapability() & 4040) > 0) {
            return firstDevice;
        }
        if ((secondDevice.getActiveCapability() & 4040) > 0) {
            return secondDevice;
        }
        if ((firstDevConnTypes & 1) == 1) {
            return firstDevice;
        }
        if ((secondDevConnTypes & 1) == 1) {
            return secondDevice;
        }
        if ((firstDevConnTypes & 2) == 2) {
            return firstDevice;
        }
        if ((secondDevConnTypes & 2) == 2) {
            return secondDevice;
        }
        if ((firstDevConnTypes & 4) == 4) {
            return firstDevice;
        }
        if ((secondDevConnTypes & 4) != 4) {
            return null;
        }
        return secondDevice;
    }

    private boolean mergeDevicesByDevId(Device device, String id) {
        Device secondDevice = this.mDeviceManager.getDevice(id);
        if (secondDevice == null) {
            return false;
        }
        int firstConnTypes = getAccessTypesForMergeDev(device);
        int secondConnTypes = getAccessTypesForMergeDev(secondDevice);
        if (firstConnTypes == 0 || secondConnTypes == 0) {
            Log.e(TAG, "mergeDevicesByDevId, Could not merge devices due to invalid serial number.");
            return false;
        }
        if ((firstConnTypes & secondConnTypes) > 0) {
            Log.e(TAG, "mergeDevicesByDevId, Could not merge devices due to they have same connection type.");
            return false;
        }
        Device device2 = getPreferenceDevice(device, firstConnTypes, secondDevice, secondConnTypes);
        Device cdev = device2.equals(device) ? secondDevice : device;
        if (!cdev.equals(device2)) {
            return mergeDevices(device2, cdev);
        }
        return true;
    }

    private boolean mergeDevicesByUsbSsid(Device device, String ssid) {
        Device cdev = this.mDeviceManager.getDeviceByConntype(1, ssid);
        if (device.getUsbSerialNumber() != null && device.getUsbSerialNumber().equals(ssid)) {
            return true;
        }
        device.setUsbSerialNumber(ssid);
        if (cdev != null && !cdev.equals(device)) {
            return mergeDevices(device, cdev);
        }
        sendMessage(EVENT_NOTIFY_DEVICES_MERGED, 0, 0, device);
        return true;
    }

    private boolean mergeDevicesByBtMac(Device device, String mac) {
        Device cdev = this.mDeviceManager.getDeviceByConntype(2, mac);
        if (device.getBtAddr() != null && device.getBtAddr().equals(mac)) {
            return true;
        }
        device.setBtAddr(mac);
        if (cdev != null && !cdev.equals(device)) {
            return mergeDevices(device, cdev);
        }
        sendMessage(EVENT_NOTIFY_DEVICES_MERGED, 0, 0, device);
        return true;
    }

    private boolean mergeDevicesByWifiMac(Device device, String mac) {
        Device cdev = this.mDeviceManager.getDeviceByConntype(3, mac);
        if (device.getWifiAddr() != null && device.getWifiAddr().equals(mac)) {
            return true;
        }
        device.setWifiAddr(mac);
        if (cdev != null && !cdev.equals(device)) {
            return mergeDevices(device, cdev);
        }
        sendMessage(EVENT_NOTIFY_DEVICES_MERGED, 0, 0, device);
        return true;
    }

    private boolean mergeDevices(Device device, Device dev) {
        device.merge(dev);
        this.mDeviceManager.removeDevice(dev.getId());
        this.mDeviceManager.saveDevice(device);
        if (this.mProjectionManager.getActiveProjectionDevice(dev.getId()) != null) {
            this.mProjectionManager.updateProjectionMap(dev.getId(), device);
        }
        if (this.mProjectionManager.getActiveService(device.getId()) == 1 && this.mProjectionManager.getActiveService(dev.getId()) == 4) {
            Log.d(TAG, "mergeDevices, Carplay service is setup stop Ipod for same device.");
            this.mProjectionManager.disconnectProjection(dev.getId(), 8, 5);
            device.removeActiveCapability(8);
        }
        sendMessage(EVENT_NOTIFY_DEVICES_MERGED, 0, 0, device);
        return true;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:22:0x009c
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1182)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    private synchronized java.lang.String parseWifiMac(java.lang.String r13) {
        /*
            r12 = this;
            monitor-enter(r12)
            r0 = 0
            r1 = 0
            r2 = 3
            r3 = 0
            r4 = 0
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L9e java.io.IOException -> La0
            java.io.InputStreamReader r6 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L9e java.io.IOException -> La0
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L9e java.io.IOException -> La0
            java.lang.String r8 = "/proc/net/arp"
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L9e java.io.IOException -> La0
            java.lang.String r8 = "UTF-8"
            r6.<init>(r7, r8)     // Catch: java.lang.Throwable -> L9e java.io.IOException -> La0
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L9e java.io.IOException -> La0
            r0 = r5
        L1a:
            java.lang.String r5 = r0.readLine()     // Catch: java.lang.Throwable -> L9e java.io.IOException -> La0
            r1 = r5
            if (r5 == 0) goto L97
            int r5 = r1.length()     // Catch: java.io.IOException -> L95 java.lang.Throwable -> Lb0
            if (r5 == 0) goto L97
            java.lang.String r5 = "\\s{1,}"
            java.lang.String r6 = " "
            java.lang.String r5 = r1.replaceAll(r5, r6)     // Catch: java.io.IOException -> L95 java.lang.Throwable -> Lb0
            r1 = r5
            java.lang.String r5 = " "
            java.lang.String[] r5 = r1.split(r5)     // Catch: java.io.IOException -> L95 java.lang.Throwable -> Lb0
            r6 = 3
            int r7 = r5.length     // Catch: java.io.IOException -> L95 java.lang.Throwable -> Lb0
            r8 = 3
            if (r7 <= r8) goto L94
            r7 = r5[r8]     // Catch: java.io.IOException -> L95 java.lang.Throwable -> Lb0
            r8 = 0
            r8 = r5[r8]     // Catch: java.io.IOException -> L95 java.lang.Throwable -> Lb0
            java.lang.String r9 = "DcController"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L95 java.lang.Throwable -> Lb0
            r10.<init>()     // Catch: java.io.IOException -> L95 java.lang.Throwable -> Lb0
            java.lang.String r11 = "parseWifiMac, macAddr:"
            r10.append(r11)     // Catch: java.io.IOException -> L95 java.lang.Throwable -> Lb0
            java.lang.String r11 = com.ts.car.dcm.common.data.LogEncrypt.MacAddressEncrypt(r7)     // Catch: java.io.IOException -> L95 java.lang.Throwable -> Lb0
            r10.append(r11)     // Catch: java.io.IOException -> L95 java.lang.Throwable -> Lb0
            java.lang.String r11 = ", ipAddr:"
            r10.append(r11)     // Catch: java.io.IOException -> L95 java.lang.Throwable -> Lb0
            java.lang.String r11 = com.ts.car.dcm.common.data.LogEncrypt.IpAddressEncrypt(r8)     // Catch: java.io.IOException -> L95 java.lang.Throwable -> Lb0
            r10.append(r11)     // Catch: java.io.IOException -> L95 java.lang.Throwable -> Lb0
            java.lang.String r10 = r10.toString()     // Catch: java.io.IOException -> L95 java.lang.Throwable -> Lb0
            android.util.Log.d(r9, r10)     // Catch: java.io.IOException -> L95 java.lang.Throwable -> Lb0
            boolean r9 = r13.equals(r8)     // Catch: java.io.IOException -> L95 java.lang.Throwable -> Lb0
            if (r9 == 0) goto L6e
            r4 = r7
            goto L97
        L6e:
            java.lang.String r9 = "DcController"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L95 java.lang.Throwable -> Lb0
            r10.<init>()     // Catch: java.io.IOException -> L95 java.lang.Throwable -> Lb0
            java.lang.String r11 = "parseWifiMac, wifiMac:"
            r10.append(r11)     // Catch: java.io.IOException -> L95 java.lang.Throwable -> Lb0
            java.lang.String r11 = com.ts.car.dcm.common.data.LogEncrypt.MacAddressEncrypt(r4)     // Catch: java.io.IOException -> L95 java.lang.Throwable -> Lb0
            r10.append(r11)     // Catch: java.io.IOException -> L95 java.lang.Throwable -> Lb0
            java.lang.String r11 = ", wifiIp:"
            r10.append(r11)     // Catch: java.io.IOException -> L95 java.lang.Throwable -> Lb0
            java.lang.String r11 = com.ts.car.dcm.common.data.LogEncrypt.IpAddressEncrypt(r13)     // Catch: java.io.IOException -> L95 java.lang.Throwable -> Lb0
            r10.append(r11)     // Catch: java.io.IOException -> L95 java.lang.Throwable -> Lb0
            java.lang.String r10 = r10.toString()     // Catch: java.io.IOException -> L95 java.lang.Throwable -> Lb0
            android.util.Log.d(r9, r10)     // Catch: java.io.IOException -> L95 java.lang.Throwable -> Lb0
        L94:
            goto L1a
        L95:
            r5 = move-exception
            goto La1
        L97:
            r0.close()     // Catch: java.io.IOException -> L9c java.lang.Throwable -> Lb9
            goto Lae
        L9c:
            r5 = move-exception
            goto Lae
        L9e:
            r5 = move-exception
            goto Lb1
        La0:
            r5 = move-exception
        La1:
            java.lang.String r6 = "DcController"
            java.lang.String r7 = "parseWifiMac, IOException:"
            android.util.Log.e(r6, r7, r5)     // Catch: java.lang.Throwable -> Lb0
            if (r0 == 0) goto Lae
            r0.close()     // Catch: java.io.IOException -> L9c java.lang.Throwable -> Lb9
        Lae:
            monitor-exit(r12)
            return r4
        Lb0:
            r5 = move-exception
        Lb1:
            if (r0 == 0) goto Lb8
            r0.close()     // Catch: java.io.IOException -> Lb7 java.lang.Throwable -> Lb9
            goto Lb8
        Lb7:
            r6 = move-exception
        Lb8:
            throw r5     // Catch: java.lang.Throwable -> Lb9
        Lb9:
            r13 = move-exception
            monitor-exit(r12)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.car.dcm.dccontroller.DcController.parseWifiMac(java.lang.String):java.lang.String");
    }

    public synchronized boolean mergeDevices(String devId, String key, int keyType) {
        Device device = this.mDeviceManager.getDevice(devId);
        Log.d(TAG, "mergeDevices, devId:" + device + ", key:" + key + ", keyType:" + keyType);
        if (device != null && key != null) {
            if (keyType == 5) {
                String wifiMac = null;
                LinkDevice dev = this.mControllerManager.getWifiLinkDeviceByIpaddr(key);
                if (dev != null) {
                    wifiMac = dev.getWifiAddress();
                }
                Log.d(TAG, "mergeDevices, wifiMac: " + LogEncrypt.MacAddressEncrypt(wifiMac));
                if (wifiMac == null) {
                    Log.d(TAG, "mergeDevices, failed to find wifi device with ip: " + key);
                    wifiMac = parseWifiMac(key);
                    Log.d(TAG, "mergeDevices, wifiMac: " + LogEncrypt.MacAddressEncrypt(wifiMac));
                    if (wifiMac == null) {
                        return false;
                    }
                }
                key = wifiMac;
                keyType = 3;
            }
            if (keyType == 1) {
                return mergeDevicesByUsbSsid(device, key);
            }
            if (keyType == 2) {
                return mergeDevicesByBtMac(device, key);
            }
            if (keyType != 3) {
                return false;
            }
            return mergeDevicesByWifiMac(device, key);
        }
        Log.e(TAG, "mergeDevices, invalid parameter!");
        return false;
    }

    public synchronized boolean mergeDevices(String usb, String bt) {
        if (usb == null || bt == null) {
            Log.e(TAG, "mergeDevices, invalid parameter!");
            return false;
        }
        Log.d(TAG, "mergeDevices, usb:" + LogEncrypt.IpAddressEncrypt(usb) + ", bt:" + LogEncrypt.MacAddressEncrypt(bt));
        Device usbDevice = this.mDeviceManager.getDeviceByConntype(1, usb);
        if (usbDevice != null) {
            Log.d(TAG, "mergeDevices, mergeDevicesByBtMac device:" + usbDevice);
            return mergeDevicesByBtMac(usbDevice, bt);
        }
        Device btDevice = this.mDeviceManager.getDeviceByConntype(2, bt);
        if (btDevice == null) {
            return false;
        }
        Log.d(TAG, "mergeDevices, mergeDevicesByUsbSsid device:" + btDevice);
        return mergeDevicesByUsbSsid(btDevice, usb);
    }

    public Device findMergedDevice(String devId) {
        Device dev = this.mDeviceManager.getDevice(devId);
        return dev;
    }

    public void sendMessage(int event, int arg1, int arg2, Object handleObject) {
        this.mHandler.obtainMessage(event, arg1, arg2, handleObject).sendToTarget();
    }

    public void sendMessage(int event) {
        this.mHandler.obtainMessage(event, 0, 0, null).sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyCmService(DcmConsumer.ReConsumer<ICmsCallback> consumer) {
        synchronized (this.mCmsCbLock) {
            try {
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            if (this.mCmsCallback != null) {
                consumer.accept(this.mCmsCallback);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDeviceAttachEvent(final LinkDevice ldev) {
        Log.d(TAG, "handleDeviceAttachEvent. ldev:" + ldev);
        final Device conDev = this.mDeviceManager.getDevice(ldev);
        if (conDev == null) {
            final Device tmpDev = new Device(ldev);
            if (!this.mConfigManager.isDeviceAccess()) {
                tmpDev.setAccepted(0);
                this.mDeviceManager.saveDeviceMemory(tmpDev);
            } else {
                tmpDev.setAccepted(1);
                tmpDev.setLastUptime(String.valueOf(System.currentTimeMillis()));
                this.mDeviceManager.saveDevice(tmpDev);
                this.mControllerManager.acceptDeviceConnect(tmpDev, true);
            }
            notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$tBcz7ZyxTKOi6YpDHJW7yClgtjY
                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                public final void accept(Object obj) {
                    ((ICmsCallback) obj).onDeviceStatusChanged(tmpDev);
                }
            });
            notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$kjFOK54uqjSWUElsrMbsy5tvO-E
                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                public final void accept(Object obj) {
                    ((ICmsCallback) obj).onDeviceAttach(tmpDev, ldev.getConnectionType());
                }
            });
            return;
        }
        updateLinkDeviceCapability(ldev, conDev);
        boolean isOnline = conDev.isOnline();
        if (isOnline) {
            Device tmpDevice = new Device(ldev);
            conDev.merge(tmpDevice);
            if (ldev.getConnectionType() == 2) {
                conDev.setIsPaired(ldev.isPaired());
            }
        } else {
            conDev.updateDevice(ldev);
        }
        if (!this.mConfigManager.isDeviceAccess() && conDev.getAccepted() == 0) {
            return;
        }
        conDev.setAccepted(1);
        conDev.setLastUptime(String.valueOf(System.currentTimeMillis()));
        this.mDeviceManager.saveDevice(conDev);
        this.mControllerManager.acceptDeviceConnect(conDev, true);
        boolean needRequestProjection = false;
        if (conDev.getConnectedTypeState(ldev.getConnectionType()) == 3) {
            conDev.setUsbDevice(this.mControllerManager.getUsbConnectedDevices(conDev));
            needRequestProjection = true;
            if (conDev.hasConnection(2)) {
                handleBtCapabilityChanged(conDev);
            }
        } else {
            Log.d(TAG, "handleDeviceAttachEvent, waiting device probed completed.");
        }
        notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$nxy3sWGrFpsVP-LnNKOAIMPlqQs
            @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
            public final void accept(Object obj) {
                ((ICmsCallback) obj).onDeviceStatusChanged(conDev);
            }
        });
        notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$1f1H8-Soq-yR8CSeGPDgAg0J89I
            @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
            public final void accept(Object obj) {
                ((ICmsCallback) obj).onDeviceAttach(conDev, ldev.getConnectionType());
            }
        });
        if (needRequestProjection) {
            this.mProjectionManager.requestProjectionConnection(conDev, ldev.getConnectionType(), 2);
        }
    }

    private void disconnectIapSession(Device device) {
        if (device.isInUsbDeviceMode() && device.getUsbRoleSwitchPurpose() == 3) {
            this.mCmsCallback.onDisconnectIapSession(device.getId(), 1);
        }
    }

    public void deleteDeviceFromDb(String devId) {
        final Device device = this.mDeviceManager.getDevice(devId);
        if (device != null && device.getDeviceConnectedState() == 2 && (device.usbAndBluetoothIsDisconnected() || !device.isReady())) {
            if (this.mProjectionManager.getActiveService(devId) != 0) {
                Log.e(TAG, "deleteDeviceFromDb, projection service is not disconnected.");
                return;
            }
            this.mDeviceManager.removeDevice(devId);
            notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$I1pK09v3MzEQe3uCY5dncGl4RSA
                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                public final void accept(Object obj) {
                    ((ICmsCallback) obj).onDeviceStatusChanged(device);
                }
            });
            Log.e(TAG, "deleteDeviceFromDb, projection service is disconnected.");
            String wifiMac = this.mProjectionManager.getReattchWifiDevice(devId);
            if (wifiMac != null) {
                Log.e(TAG, "deleteDeviceFromDb, trigger reattachWifiDevice.");
                reattachWifiDevice(wifiMac);
            }
            if (this.mControllerManager.removeUsbConnection(device)) {
                Log.d(TAG, "removeOnlineDevice, deleteUsbConnection success.");
            }
            if (this.mControllerManager.removeBtConnection(device)) {
                Log.d(TAG, "removeOnlineDevice, deleteBtConnection success.");
                return;
            }
            return;
        }
        Log.i(TAG, "deleteDeviceFromDb, the device is not deleted from the database. device: " + device);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDeviceDetach(String devId, final int conntype) {
        Log.d(TAG, "handleDeviceDetach, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ",conntype:" + conntype);
        final Device device = this.mDeviceManager.getDeviceByConntype(conntype, devId);
        if (device == null) {
            Log.d(TAG, "handleDeviceDetach, receive a no accepted device.");
            return;
        }
        if (device.getAccepted() != 1) {
            this.mDeviceManager.removeDevice(device.getId());
        }
        if (this.mConfigManager.isNeedGetPhoneName() && conntype == 1) {
            disconnectIapSession(device);
        }
        device.removeCapabilitybyConnType(conntype);
        this.mProjectionManager.handleProjectionDeviceDetach(device, conntype);
        if (conntype == 2) {
            handleBtCapabilityChanged(device);
            device.setIsIapChecked(false);
        }
        device.removeDeviceConnectType(conntype);
        if (device.getDeviceConnectedState() == 2) {
            deleteDeviceFromDb(device.getId());
        } else if (conntype == 2 && !device.isPaired()) {
            device.setCpwlOverIapDetectedNotified(false);
            device.setCpwlOverIapDetected(false);
            this.mDeviceManager.saveDevice(device);
            notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$-I5_WuIL-GopicuWa7NEaLWoXws
                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                public final void accept(Object obj) {
                    ((ICmsCallback) obj).onDeviceStatusChanged(device);
                }
            });
        } else {
            this.mDeviceManager.saveDevice(device);
            notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$LLRc1cEuezlc9GPNXuVKRRuPgog
                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                public final void accept(Object obj) {
                    ((ICmsCallback) obj).onDeviceStatusChanged(device);
                }
            });
        }
        notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$rLni52P-gCjhsV1ElXXZE8qh5Jc
            @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
            public final void accept(Object obj) {
                ((ICmsCallback) obj).onDeviceDetach(device, conntype);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkBtCapabilityChanged(final Device device) {
        if (device.isPaired()) {
            if (device.getDeviceType() == 2) {
                Log.d(TAG, "checkBtCapabilityChanged, iphone connected.");
                if (this.mConfigManager.isEnableCarPlay()) {
                    if (!this.mConfigManager.isCpwlDetectingOverIapEnabled() && device.isAvailableCapability(512)) {
                        Log.d(TAG, "checkBtCapabilityChanged, do not enable detected wireless Carplay by IAP.");
                        this.mProjectionManager.requestProjectionConnection(device, 2, 2);
                        return;
                    }
                    Log.d(TAG, "checkBtCapabilityChanged, enable detected wireless Carplay by IAP.");
                    if (!device.isCpwlOverIapDetected()) {
                        if (this.mBtDeviceBondedMessageList.contains(device.getBtAddr())) {
                            this.mProjectionManager.checkWirelessCarplayCapability(device.getId(), 1);
                            this.mBtDeviceBondedMessageList.remove(device.getBtAddr());
                            return;
                        } else {
                            Log.d(TAG, "checkBtCapabilityChanged, wait bt paired message.");
                            return;
                        }
                    }
                    if (!device.isCpwlOverIapDetectStarting()) {
                        device.setIsIapChecked(true);
                    }
                    Log.d(TAG, "checkBtCapabilityChanged, Wireless Carplay has been detected by IAP. devId:" + LogEncrypt.IpAddressEncrypt(device.getId()));
                    return;
                }
                notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$-ickHA3bipWh_FByHtY0yNXvaok
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) {
                        ((ICmsCallback) obj).onWirelessCarplayDetected(device.getBtAddr(), 0);
                    }
                });
                Log.d(TAG, "checkBtCapabilityChanged, no enable carplay!");
                return;
            }
            Log.d(TAG, "checkBtCapabilityChanged, device capability update. devType:" + device.getDeviceType());
            if ((device.getDeviceType() == 4 || device.getDeviceType() == 1) && !device.isCpwlOverIapDetectedNotified()) {
                Log.d(TAG, "checkBtCapabilityChanged, Notify onWirelessCarplayDetected unsupport, device:" + device);
                notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$fYDXWshSh3O-zz1MM3ZFZeNRnV0
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) {
                        ((ICmsCallback) obj).onWirelessCarplayDetected(device.getBtAddr(), 0);
                    }
                });
                device.setCpwlOverIapDetectedNotified(true);
                this.mDeviceManager.saveDevice(device);
                return;
            }
            return;
        }
        Log.d(TAG, "checkBtCapabilityChanged, wait pairing completed. devId:" + LogEncrypt.IpAddressEncrypt(device.getId()));
    }

    private void checkProjectionCapabilityChanged(Device device, int connType) {
        Log.d(TAG, "checkProjectionCapabilityChanged, device:" + device + ", connType: " + connType);
        if (device.isReady()) {
            if (connType != 2) {
                this.mProjectionManager.requestProjectionConnection(device, connType, 2);
                return;
            } else {
                checkBtCapabilityChanged(device);
                return;
            }
        }
        Log.d(TAG, "checkProjectionCapabilityChanged, waiting device probed completed.");
    }

    private void handleDeviceCapabilityChanged(Device device, int connType) {
        Log.d(TAG, "handleDeviceCapabilityChanged, device:" + device + ", connType: " + connType);
        checkProjectionCapabilityChanged(device, connType);
        if (connType == 2) {
            handleBtCapabilityChanged(device);
        }
    }

    private void updateLinkDeviceCapability(LinkDevice ldev, Device device) {
        Log.d(TAG, "updateLinkDeviceCapability, linkDevice:" + LogEncrypt.IpAddressEncrypt(ldev.getId()) + ", status:" + ldev.getDeviceState());
        if (ldev.getConnectionType() != 1) {
            int capa = ldev.getCapability();
            if (this.mConfigManager.isEnableCarPlay() && this.mConfigManager.isCpwlDetectingOverIapEnabled() && ldev.getDeviceState() == 3 && device != null) {
                device.setCapability(device.getCapability() | capa);
                capa &= -513;
                if ((device.getAvailableCapability() & 512) > 0) {
                    Log.d(TAG, "updateLinkDeviceCapability, add carplay wireless to ldev:" + LogEncrypt.IpAddressEncrypt(device.getId()));
                    capa |= 512;
                }
            }
            int capa2 = capa & (-1025);
            if (device != null && (device.getAvailableCapability() & 1024) > 0) {
                Log.d(TAG, "updateLinkDeviceCapability, add android auto wireless to ldev:" + LogEncrypt.IpAddressEncrypt(device.getId()));
                capa2 |= 1024;
            }
            ldev.setCapability(capa2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDeviceConnectionChanged(LinkDevice ldev, int reason) {
        Log.d(TAG, "handleDeviceConnectionChanged, devId:" + LogEncrypt.IpAddressEncrypt(ldev.getId()));
        final Device conDev = this.mDeviceManager.getDevice(ldev);
        if (conDev == null || conDev.getAccepted() != 1) {
            Log.d(TAG, "handleDeviceConnectionChanged, wrong device information:" + conDev);
            return;
        }
        updateLinkDeviceCapability(ldev, conDev);
        if (reason == 3) {
            conDev.updateDevice(ldev);
            if (ldev.getConnectionType() == 1) {
                conDev.setUsbDevice(this.mControllerManager.getUsbConnectedDevices(conDev));
            }
            if (conDev.isReady()) {
                this.mDeviceManager.saveDevice(conDev);
            }
            notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$yOnnvlQTnSrQT5b6coSl2-HqmQc
                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                public final void accept(Object obj) {
                    ((ICmsCallback) obj).onDeviceStatusChanged(conDev);
                }
            });
            handleDeviceCapabilityChanged(conDev, ldev.getConnectionType());
            return;
        }
        if (reason == 4) {
            if (conDev.getConnectedTypeState().size() == 1 && conDev.hasConnection(1)) {
                conDev.setDeviceConnectedState(3);
            }
            conDev.updateDevice(ldev);
            conDev.removeCapabilitybyConnType(1);
            if (conDev.hasConnection(2)) {
                handleBtCapabilityChanged(conDev);
            }
            this.mDeviceManager.saveDevice(conDev);
            notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$QcekyOtltA1sAg1E3nZ4_CWH2hE
                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                public final void accept(Object obj) {
                    ((ICmsCallback) obj).onDeviceStatusChanged(conDev);
                }
            });
            this.mProjectionManager.requestProjectionConnection(conDev, ldev.getConnectionType(), 2);
            return;
        }
        if (reason == 5) {
            if (ldev.getConnectionType() == 1) {
                conDev.setDeviceConnectedState(1);
                conDev.updateDevice(ldev);
                if (ldev.getDeviceState() == 3) {
                    this.mProjectionManager.requestProjectionConnection(conDev, ldev.getConnectionType(), 2);
                    if (conDev.hasConnection(2)) {
                        handleBtCapabilityChanged(conDev);
                    }
                }
                notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$qHhGwPMZppcl5M_pILCdsmZ2xEk
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) {
                        ((ICmsCallback) obj).onDeviceStatusChanged(conDev);
                    }
                });
                this.mDeviceManager.saveDevice(conDev);
                return;
            }
            conDev.setDeviceConnectedState(1);
            conDev.updateDevice(ldev);
            if (conDev.isReady()) {
                this.mDeviceManager.saveDevice(conDev);
            }
            handleDeviceCapabilityChanged(conDev, ldev.getConnectionType());
            notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$bAQtWtuPMsa3n12vL9dY6N6ViGc
                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                public final void accept(Object obj) {
                    ((ICmsCallback) obj).onDeviceStatusChanged(conDev);
                }
            });
            Log.d(TAG, "handleDeviceConnectionChanged, Wireless device reconnection completed:" + conDev.getId());
            return;
        }
        if (reason == 6) {
            Log.e(TAG, "handleDeviceConnectionChanged, update device info.");
            conDev.updateDevice(ldev);
            this.mDeviceManager.saveDevice(conDev);
            notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$xY_rsJIspDwD4_UWa0R0YBbgrUk
                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                public final void accept(Object obj) {
                    ((ICmsCallback) obj).onDeviceStatusChanged(conDev);
                }
            });
        }
    }

    private void handleBtCapabilityChanged(Device device) {
        if (device.isReady()) {
            int availableCapa = device.getAvailableCapability();
            int lastAvailableCapa = device.getLastAvailableCapability();
            if (device.hasConnection(2)) {
                if ((availableCapa & 4) > 0 && (lastAvailableCapa & 4) == 0) {
                    device.addLastAvailableCapability(4);
                } else if ((availableCapa & 4) == 0 && (lastAvailableCapa & 4) > 0) {
                    device.removeLastAvailableCapability(4);
                }
                if ((availableCapa & 2) > 0 && (lastAvailableCapa & 2) == 0) {
                    device.addLastAvailableCapability(2);
                } else if ((availableCapa & 2) == 0 && (lastAvailableCapa & 2) > 0) {
                    device.removeLastAvailableCapability(2);
                }
            }
        }
    }

    private class DcBroadcastReceiver extends BroadcastReceiver {
        private DcBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null && action.equals(DcController.ACTION_SHUTDOWN)) {
                Log.e(DcController.TAG, "onReceive, ShutdownBroadcastReceiver.");
                DcController.this.dispose();
            }
        }
    }

    public void clearBtDeviceCapability(Device device) {
        device.removeCapabilitybyConnType(2);
        device.removeDeviceConnectType(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class DccMessageHandler extends Handler {
        private DccMessageHandler(Looper looper) {
            super(looper);
        }

        private void deleteBtDevice(Message msg) {
            final Device device;
            String mac = (String) msg.obj;
            int state = msg.arg1;
            Log.d(DcController.TAG, "handleBtDeleteEvent, received EVENT_BT_DELETED. mac:" + LogEncrypt.MacAddressEncrypt(mac) + ", status:" + state);
            if (state == 0 && (device = DcController.this.mDeviceManager.getDeviceByConntype(2, mac)) != null) {
                if (device.getConnectedTypeState(2) == 3) {
                    DcController.this.clearBtDeviceCapability(device);
                }
                if (device.getDeviceConnectedState() != 2) {
                    DcController.this.mProjectionManager.handleBtDeviceRemoved(device.getId(), mac);
                }
                if (device.getConnectionNumber() == 0 && device.getDeviceConnectedState() == 2) {
                    device.setDeviceConnectedState(2);
                    DcController.this.deleteDeviceFromDb(device.getId());
                    return;
                }
                Log.i(DcController.TAG, "handleBtDeleteEvent, device:" + device);
                device.setIsPaired(false);
                device.setIsIapChecked(false);
                DcController.this.mDeviceManager.saveDevice(device);
                DcController.this.notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$DccMessageHandler$PEZoTzB0HI4wyr_Fr70wPo-bS9s
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) {
                        ((ICmsCallback) obj).onDeviceStatusChanged(device);
                    }
                });
            }
        }

        private void handleAoaStartingEvent(Message msg) {
            String devId = (String) msg.obj;
            final int mode = msg.arg2;
            final Device device = DcController.this.mDeviceManager.getDeviceByConntype(1, devId);
            if (device != null) {
                device.setUsbDevice(DcController.this.mControllerManager.getUsbConnectedDevices(device));
                DcController.this.notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$DccMessageHandler$kRF3gQYYfc4L9Dm9cFJHaEsy4wA
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) {
                        ((ICmsCallback) obj).onEnableUsbAoAStarting(device, mode);
                    }
                });
            }
        }

        private void handleRoleSwitchStartingEvent(Message msg) {
            String devId = (String) msg.obj;
            final int mode = msg.arg1;
            final Device device = DcController.this.mDeviceManager.getDeviceByConntype(1, devId);
            if (device != null) {
                if (mode == 1) {
                    device.setUsbDevice(null);
                }
                DcController.this.notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$DccMessageHandler$XsrjZbZTRoVoJPR3FKgleUEDwJo
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) {
                        ((ICmsCallback) obj).onRoleSwitchStarting(device, mode);
                    }
                });
            }
        }

        private void handleRoleSwitchFinishEvent(Message msg) {
            LinkDevice ldev = (LinkDevice) msg.obj;
            final int mode = msg.arg1;
            final int result = msg.arg2;
            final Device device = DcController.this.mDeviceManager.getDevice(ldev);
            if (device != null) {
                if (mode == 1) {
                    device.setInUsbDeviceMode(true);
                    if (result == 0) {
                        Log.e(DcController.TAG, "handleRoleSwitchFinishEvent, USB role switch failed.");
                    }
                } else {
                    device.setInUsbDeviceMode(false);
                }
                DcController.this.notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$DccMessageHandler$JECcdn1Sgkwsjgz9xk8RAywjdPk
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) {
                        ((ICmsCallback) obj).onRoleSwitchFinished(device, mode, result);
                    }
                });
            }
        }

        private void handleBtCallStateChanged(Message msg) {
            String devId = (String) msg.obj;
            boolean inCall = DcmUtils.intToBool(msg.arg1);
            final Device device = DcController.this.mDeviceManager.getDeviceByConntype(2, devId);
            if (device != null) {
                if (inCall) {
                    device.addActiveCapability(2);
                } else {
                    device.removeActiveCapability(2);
                }
                DcController.this.notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$DccMessageHandler$aYJahlbnlssBtvgFKjaOa4sbKSo
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) {
                        ((ICmsCallback) obj).onDeviceStatusChanged(device);
                    }
                });
            }
        }

        private void handleBtOobPairComplete(Message msg) {
            final String mac = (String) msg.obj;
            final int status = msg.arg1;
            Device device = DcController.this.mDeviceManager.getDeviceByConntype(2, mac);
            if (device != null) {
                device.setCapability(device.getCapability() | 512);
                device.setDeviceType(2);
                DcController.this.notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$DccMessageHandler$AzQcviwfM9eBJ_1hsHurcCsFPsI
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) {
                        ((ICmsCallback) obj).notifyBtOobPairComplete(mac, status);
                    }
                });
                DcController.this.mDeviceManager.saveDevice(device);
                return;
            }
            Log.e(DcController.TAG, "handleBtOobPairComplete, Can not find the device.");
        }

        private void handleBtPairStateChanged(Message msg) {
            String mac = (String) msg.obj;
            int status = msg.arg1;
            final Device device = DcController.this.mDeviceManager.getDeviceByConntype(2, mac);
            if (status == 11 || status == 10) {
                DcController.this.mBtDeviceBondedMessageList.remove(mac);
            } else if (status == 12 && !DcController.this.mBtDeviceBondedMessageList.contains(mac)) {
                DcController.this.mBtDeviceBondedMessageList.add(mac);
            }
            if (device == null) {
                Log.e(DcController.TAG, "handleBtPairStateChanged, Can not find the device.");
                return;
            }
            if (status == 10) {
                Log.d(DcController.TAG, "handleBtPairStateChanged, EVENT_BT_PAIR_NONE mac:" + LogEncrypt.MacAddressEncrypt(mac));
                device.setIsPaired(false);
                device.setBtDevicePairedTime(0L);
            } else if (status == 12) {
                Log.d(DcController.TAG, "handleBtPairStateChanged, EVENT_BT_PAIR_BONDED mac:" + LogEncrypt.MacAddressEncrypt(mac));
                device.setIsPaired(true);
                device.setBtDevicePairedTime(System.currentTimeMillis());
                DcController.this.checkBtCapabilityChanged(device);
            } else if (status == 11) {
                Log.d(DcController.TAG, "handleBtPairStateChanged, EVENT_BT_PAIR_BONDING mac:" + LogEncrypt.MacAddressEncrypt(mac));
                if (device.isPaired()) {
                    device.setIsPaired(false);
                    device.setIsIapChecked(false);
                }
            }
            if (device.getDeviceConnectedState() != 2) {
                DcController.this.mDeviceManager.saveDevice(device);
            } else {
                DcController.this.deleteDeviceFromDb(device.getId());
            }
            DcController.this.notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$DccMessageHandler$ayuvmhWuDNDQa-Nh7sEUuhvSZZM
                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                public final void accept(Object obj) {
                    ((ICmsCallback) obj).onDeviceStatusChanged(device);
                }
            });
            DcController.this.mProjectionManager.handleBtPairingStateChanged(device, status);
        }

        private void handleAbnormalDeviceAttach(Message msg) {
            String deviceId = (String) msg.obj;
            int connType = msg.arg2;
            int abnormalType = msg.arg1;
            final AbnormalDevice abnormalDevice = DcController.this.mDeviceManager.getAbnormalDevice(deviceId);
            if (abnormalDevice != null) {
                abnormalDevice.setAbnormalType(abnormalType);
                abnormalDevice.setConnType(connType);
                DcController.this.mDeviceManager.saveAbnormalDevice(abnormalDevice);
                DcController.this.notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$DccMessageHandler$i_kAwL4GNIQyQmUKQu5oTUIU6dk
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) {
                        ((ICmsCallback) obj).onAbnormalDeviceStatusChanged(abnormalDevice, 1);
                    }
                });
                return;
            }
            final AbnormalDevice abnormalDeviceTmp = new AbnormalDevice(deviceId);
            abnormalDeviceTmp.setAbnormalType(abnormalType);
            abnormalDeviceTmp.setConnType(connType);
            DcController.this.mDeviceManager.saveAbnormalDevice(abnormalDeviceTmp);
            DcController.this.notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$DccMessageHandler$1VXcy_wy2ewyocmZ-RA3MeofTeA
                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                public final void accept(Object obj) {
                    ((ICmsCallback) obj).onAbnormalDeviceStatusChanged(abnormalDeviceTmp, 1);
                }
            });
        }

        private void handleAbnormalDeviceDetach(Message msg) {
            String deviceId = (String) msg.obj;
            final AbnormalDevice abnormalDevice = DcController.this.mDeviceManager.getAbnormalDevice(deviceId);
            if (abnormalDevice != null) {
                DcController.this.mDeviceManager.removeAbnormalDevice(deviceId);
                DcController.this.notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$DccMessageHandler$385--3znsUS4yDAGKsQA9jRkD0c
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) {
                        ((ICmsCallback) obj).onAbnormalDeviceStatusChanged(abnormalDevice, 2);
                    }
                });
            } else {
                Log.e(DcController.TAG, "handleAbnormalDeviceDetach, the device not exist.");
            }
        }

        private void checkCpwlCapability(Message msg) {
            Device device;
            String mac = (String) msg.obj;
            if (mac != null && (device = DcController.this.mDeviceManager.getDeviceByConntype(2, mac)) != null) {
                Log.i(DcController.TAG, "checkCpwlCapability, devId: " + LogEncrypt.IpAddressEncrypt(device.getId()) + ", availableCapability: " + device.getAvailableCapability());
                if (device.isPaired() && (device.getAvailableCapability() & 512) == 0) {
                    DcController.this.mProjectionManager.checkWirelessCarplayCapability(device.getId(), 5);
                }
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            Log.i(DcController.TAG, "DccMessageHandler:" + msg.what);
            int i = msg.what;
            if (i == 270) {
                String mac = (String) msg.obj;
                final int status = msg.arg1;
                final Device device = DcController.this.mDeviceManager.getDeviceByConntype(3, mac);
                if (device != null) {
                    DcController.this.notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$DccMessageHandler$rbSNhZZRnB43uTIQ19M-kRjjQ4k
                        @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                        public final void accept(Object obj) {
                            ((ICmsCallback) obj).onWifiConnectStatus(device, status);
                        }
                    });
                    return;
                }
                return;
            }
            if (i == 271) {
                SoftApConfiguration config = (SoftApConfiguration) msg.obj;
                final WifiConfigInfo configInfo = new WifiConfigInfo();
                WifiConfiguration wifiConfig = config.toWifiConfiguration();
                configInfo.setWifiConfiguration(wifiConfig);
                final int status2 = msg.arg1;
                Log.e(DcController.TAG, "Wifi ap status:" + status2);
                DcController.this.mProjectionManager.handleWifiApStateChanged(status2);
                DcController.this.notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$DccMessageHandler$dPu_w1EFoLlic7cmigGfK6yKCE0
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) {
                        ((ICmsCallback) obj).onWifiApInfo(configInfo, status2);
                    }
                });
                return;
            }
            if (i == 281) {
                final Device device2 = (Device) msg.obj;
                DcController.this.notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$DccMessageHandler$fwjNMncMioP5Jgz1_ZOyvDeGIWE
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) {
                        ((ICmsCallback) obj).onDevicesMerged(device2);
                    }
                });
                DcController.this.notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$DccMessageHandler$AjSf878LyPeh8O_iDhOl9-H1m8o
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) {
                        ((ICmsCallback) obj).onDeviceStatusChanged(device2);
                    }
                });
                if (DcController.this.mProjectionManager.getActiveProjectionDevice(device2.getId()) != null) {
                    DcController.this.mProjectionManager.requestProjectionConnection(device2, 0, 2);
                    return;
                }
                return;
            }
            if (i == 282) {
                List<Device> devices = (List) msg.obj;
                for (final Device device3 : devices) {
                    DcController.this.notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$DccMessageHandler$lSAVCiEpNYSLNYnsRLDVEOhvMHM
                        @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                        public final void accept(Object obj) {
                            ((ICmsCallback) obj).onDeviceStatusChanged(device3);
                        }
                    });
                }
                return;
            }
            if (i == 291) {
                String mac2 = (String) msg.obj;
                final Device device4 = DcController.this.mDeviceManager.getDeviceByConntype(2, mac2);
                if (device4 != null) {
                    if (device4.getDeviceType() == 0) {
                        device4.setDeviceType(4);
                    }
                    DcController.this.notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$DccMessageHandler$VuhnzqEp_3J_hidGdTgK-FzR-fs
                        @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                        public final void accept(Object obj) {
                            ((ICmsCallback) obj).onWirelessProjectionFound(device4);
                        }
                    });
                    return;
                }
                return;
            }
            if (i == 292) {
                checkCpwlCapability(msg);
                return;
            }
            if (i == 300) {
                handleAbnormalDeviceAttach(msg);
                return;
            }
            if (i != 301) {
                switch (i) {
                    case 100:
                        DcController.this.handleDeviceAttachEvent((LinkDevice) msg.obj);
                        break;
                    case 101:
                        DcController.this.handleDeviceDetach((String) msg.obj, msg.arg1);
                        break;
                    case 102:
                        DcController.this.handleDeviceConnectionChanged((LinkDevice) msg.obj, msg.arg1);
                        break;
                    default:
                        switch (i) {
                            case DcController.EVENT_USB_ROLESWITCH_STARTING /* 150 */:
                                handleRoleSwitchStartingEvent(msg);
                                break;
                            case 151:
                                handleRoleSwitchFinishEvent(msg);
                                break;
                            case 152:
                                String devId = (String) msg.obj;
                                final Device device5 = DcController.this.mDeviceManager.getDeviceByConntype(1, devId);
                                if (device5 != null) {
                                    device5.setUsbDevice(null);
                                    DcController.this.notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$DccMessageHandler$SIg7Ek_2TXP7gtF4Z0Orvt8Ktsw
                                        @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                                        public final void accept(Object obj) {
                                            ((ICmsCallback) obj).onResetUsbBusStarting(device5);
                                        }
                                    });
                                }
                                break;
                            case 153:
                                String devId2 = (String) msg.obj;
                                final int result = msg.arg1;
                                final Device device6 = DcController.this.mDeviceManager.getDeviceByConntype(1, devId2);
                                if (device6 != null) {
                                    if (device6.getConnectedTypeState(1) == 3) {
                                        device6.setResetUsbDone(true);
                                    } else {
                                        device6.setResetUsbDone(false);
                                    }
                                    device6.setInUsbDeviceMode(false);
                                    device6.setInUsbAoaMode(false);
                                    device6.setUsbDevice(DcController.this.mControllerManager.getUsbConnectedDevices(device6));
                                    device6.setUsbRoleSwitchPurpose(0);
                                    DcController.this.mProjectionManager.sendRestUsbFinishedMessage(device6, result);
                                    DcController.this.notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$DccMessageHandler$4dwuVplNRYy6-oqsa_keeYb7Ppo
                                        @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                                        public final void accept(Object obj) {
                                            ((ICmsCallback) obj).onResetUsbFinished(device6, result == 1);
                                        }
                                    });
                                }
                                break;
                            case 154:
                                handleAoaStartingEvent(msg);
                                break;
                            case 155:
                                LinkDevice ldev = (LinkDevice) msg.obj;
                                final int result2 = msg.arg1;
                                final int mode = msg.arg2;
                                final Device device7 = DcController.this.mDeviceManager.getDevice(ldev);
                                if (device7 != null) {
                                    if (result2 == 1) {
                                        device7.setInUsbAoaMode(true);
                                    }
                                    device7.setUsbDevice(DcController.this.mControllerManager.getUsbConnectedDevices(device7));
                                    DcController.this.notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$DccMessageHandler$qzowcFXMo6HWsDv8lHijeWqY7dE
                                        @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                                        public final void accept(Object obj) {
                                            ((ICmsCallback) obj).onEnableUsbAoAFinished(device7, mode, result2 == 1);
                                        }
                                    });
                                }
                                break;
                            default:
                                switch (i) {
                                    case 201:
                                        final String mac3 = (String) msg.obj;
                                        final int profile = msg.arg1;
                                        final int state = msg.arg2;
                                        final Device device8 = DcController.this.mDeviceManager.getDeviceByConntype(2, mac3);
                                        if (device8 != null) {
                                            device8.addBtProfileState(profile, state);
                                            Log.e(DcController.TAG, "EVENT_BT_PROFILE_CHANGED" + device8.getBtProfileState());
                                        }
                                        DcController.this.mProjectionManager.handleBtProfileStateChanged(mac3, profile, state);
                                        if (device8 != null) {
                                            DcController.this.notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$DccMessageHandler$GpQzwdolaKekBAb4ktv3HQtjGHo
                                                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                                                public final void accept(Object obj) {
                                                    ((ICmsCallback) obj).onDeviceStatusChanged(device8);
                                                }
                                            });
                                        }
                                        DcController.this.notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$DccMessageHandler$PPHV41cSklCMFdllqLlOz4PvvRc
                                            @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                                            public final void accept(Object obj) {
                                                ((ICmsCallback) obj).onBtProfileChanged(mac3, profile, state);
                                            }
                                        });
                                        break;
                                    case 202:
                                        final String mac4 = (String) msg.obj;
                                        final int status3 = msg.arg1;
                                        DcController.this.notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$DccMessageHandler$IUUrRclmlWYitG9UDuX956TqPe8
                                            @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                                            public final void accept(Object obj) {
                                                ((ICmsCallback) obj).onBtDisconnected(mac4, status3);
                                            }
                                        });
                                        break;
                                    case 203:
                                        deleteBtDevice(msg);
                                        break;
                                    case 204:
                                        final String mac5 = (String) msg.obj;
                                        final int status4 = msg.arg1;
                                        DcController.this.notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$DccMessageHandler$qvIk9PJ5yc_ikHmFVxZnySmePrs
                                            @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                                            public final void accept(Object obj) {
                                                ((ICmsCallback) obj).onBtConnected(mac5, status4);
                                            }
                                        });
                                        break;
                                    case DcController.EVENT_BT_OTHER_DISCONNECTED /* 205 */:
                                        final String mac6 = (String) msg.obj;
                                        final int status5 = msg.arg1;
                                        DcController.this.notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$DccMessageHandler$i-PBR0tIUNJy4ad7PJgxnqYWrTY
                                            @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                                            public final void accept(Object obj) {
                                                ((ICmsCallback) obj).onDisconnectedOtherBtDevices(mac6, status5);
                                            }
                                        });
                                        break;
                                    case DcController.EVENT_BT_CALL_STATE_CHANGED /* 206 */:
                                        handleBtCallStateChanged(msg);
                                        break;
                                    case DcController.EVENT_BT_AUDIO_STATE_CHANGED /* 207 */:
                                        String devId3 = (String) msg.obj;
                                        boolean inAudioPlay = DcmUtils.intToBool(msg.arg1);
                                        Device device9 = DcController.this.mDeviceManager.getDeviceByConntype(2, devId3);
                                        if (device9 != null) {
                                            if (inAudioPlay) {
                                                device9.addActiveCapability(4);
                                            } else {
                                                device9.removeActiveCapability(4);
                                            }
                                        }
                                        break;
                                    case DcController.EVENT_BT_STATE_CHANGED /* 208 */:
                                        final int status6 = msg.arg1;
                                        DcController.this.mProjectionManager.handleBluetoothStateChanged(status6);
                                        DcController.this.notifyCmService(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.-$$Lambda$DcController$DccMessageHandler$FQC5EfT1dJ5BSIvAq6ZV6vtbhN4
                                            @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                                            public final void accept(Object obj) {
                                                ((ICmsCallback) obj).onBtStateChanged(status6);
                                            }
                                        });
                                        break;
                                    case DcController.EVENT_BT_OOB_PAIR_COMPLETE /* 209 */:
                                        handleBtOobPairComplete(msg);
                                        break;
                                    case DcController.EVENT_BT_PAIR_STATE_CHANGED /* 210 */:
                                        handleBtPairStateChanged(msg);
                                        break;
                                    default:
                                        Log.e(DcController.TAG, "handleMessage, Unknown message id:" + msg.what);
                                        break;
                                }
                                break;
                        }
                        break;
                }
                return;
            }
            handleAbnormalDeviceDetach(msg);
        }
    }
}

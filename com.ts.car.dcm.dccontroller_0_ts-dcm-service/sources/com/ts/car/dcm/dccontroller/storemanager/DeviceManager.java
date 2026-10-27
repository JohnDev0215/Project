package com.ts.car.dcm.dccontroller.storemanager;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.util.Log;
import com.ts.car.dcm.common.data.LinkDevice;
import com.ts.car.dcm.common.data.LogEncrypt;
import com.ts.car.dcm.dccontroller.DcController;
import com.ts.car.dcm.dccontroller.configdata.ConfigManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import ts.car.dcm.common.data.AbnormalDevice;
import ts.car.dcm.common.data.CmDevice;
import ts.car.dcm.common.data.ConnDevice;

/* JADX INFO: loaded from: classes.dex */
public class DeviceManager {
    private static final int COUNT_OF_DEVICE_ADDR = 2;
    private static final int MAX_INDEX_SAME_DEVICE_NAME = 10;
    private static final String TAG = "DeviceManager";
    private static final int TELEPHONE_ALL = 578;
    private IDataBaseOperate mDataBaseOperate;
    private Handler mDataOperaterHandler;
    private int mDatabaseSyncTime;
    private DcController mDcController;
    private Map<String, Device> mDeviceMap;
    private boolean mEnableIndexOfSameDeviceName;
    private HandlerThread mHandlerThread;
    private int mSupportedProjectionCapability;
    private final List<Device> mInsertDeviceList = new CopyOnWriteArrayList();
    private final List<Device> mUpdateDeviceList = new CopyOnWriteArrayList();
    private boolean mAllowDbSave = true;
    private Map<String, ConnDevice> mConnDeviceMap = new HashMap();
    private HashMap<String, AbnormalDevice> mAbnormalDeviceMap = new HashMap<>();
    Runnable mDbOptRunnable = new Runnable() { // from class: com.ts.car.dcm.dccontroller.storemanager.DeviceManager.1
        @Override // java.lang.Runnable
        public void run() {
            if (DeviceManager.this.mAllowDbSave) {
                DeviceManager.this.saveDeviceToDb();
            } else {
                Log.d(DeviceManager.TAG, "run, not suspend save database.");
            }
            if (DeviceManager.this.mDatabaseSyncTime != 0) {
                DeviceManager.this.mDataOperaterHandler.postDelayed(this, DeviceManager.this.mDatabaseSyncTime);
            }
        }
    };

    public DeviceManager(Context context, DcController dcc, ConfigManager manager) {
        this.mDatabaseSyncTime = 0;
        this.mEnableIndexOfSameDeviceName = false;
        this.mSupportedProjectionCapability = 0;
        this.mDcController = dcc;
        Sqlite3DataBaseOperate sqlite3DataBaseOperate = new Sqlite3DataBaseOperate(context);
        this.mDataBaseOperate = sqlite3DataBaseOperate;
        this.mDeviceMap = sqlite3DataBaseOperate.selectAllDevice();
        this.mDatabaseSyncTime = manager.getDatabaseSyncTime();
        this.mEnableIndexOfSameDeviceName = manager.isIndexOfSameDeviceNameEnabled();
        this.mSupportedProjectionCapability = manager.getSupportedProjectionCapablity();
        if (this.mHandlerThread == null) {
            HandlerThread handlerThread = new HandlerThread("DcmDbHandlerThread");
            this.mHandlerThread = handlerThread;
            handlerThread.start();
        }
        Handler handler = new Handler(this.mHandlerThread.getLooper());
        this.mDataOperaterHandler = handler;
        int i = this.mDatabaseSyncTime;
        if (i != 0) {
            handler.postDelayed(this.mDbOptRunnable, i);
        }
    }

    private boolean isHashmapEqual(HashMap<Integer, Integer> hash1, HashMap<Integer, Integer> hash2) {
        if (hash1.size() != hash2.size()) {
            return false;
        }
        for (Map.Entry<Integer, Integer> entry1 : hash1.entrySet()) {
            Integer integer2 = hash2.get(entry1.getKey());
            if (integer2 == null || !integer2.equals(entry1.getValue())) {
                return false;
            }
        }
        return true;
    }

    private boolean isStringEqual(String str1, String str2) {
        boolean isEqual = true;
        if (str1 == null && str2 != null) {
            isEqual = false;
        }
        if (str1 != null && !str1.equals(str2)) {
            return false;
        }
        return isEqual;
    }

    private long getGenericChangedMask(ConnDevice oldConn, ConnDevice newConn) {
        long genericChangedMask = isStringEqual(oldConn.getDeviceUuid(), newConn.getDeviceUuid()) ? 0L : 0 | 16;
        if (!isStringEqual(oldConn.getFriendlyName(), newConn.getFriendlyName())) {
            Log.e(TAG, "getGenericChangedMask: FRIENDLIY NAME changed");
            genericChangedMask |= 32;
        }
        if (oldConn.getDeviceType() != newConn.getDeviceType()) {
            genericChangedMask |= 64;
        }
        if (oldConn.getAccepted() != newConn.getAccepted()) {
            genericChangedMask |= 128;
        }
        if (!isStringEqual(oldConn.getUsbSerialNumber(), newConn.getUsbSerialNumber())) {
            genericChangedMask |= 1024;
        }
        if (!isStringEqual(oldConn.getBtAddr(), newConn.getBtAddr())) {
            genericChangedMask |= 2048;
        }
        if (!isStringEqual(oldConn.getWifiAddr(), newConn.getWifiAddr())) {
            genericChangedMask |= 4096;
        }
        if (!isStringEqual(oldConn.getWifiIpAddress(), newConn.getWifiIpAddress())) {
            genericChangedMask |= 8192;
        }
        if (!isStringEqual(oldConn.getUsbHidNode(), newConn.getUsbHidNode())) {
            genericChangedMask |= ConnDevice.MASK_USB_HID_NODE;
        }
        if (!isStringEqual(oldConn.getIapNode(), newConn.getIapNode())) {
            genericChangedMask |= ConnDevice.MASK_IAP_NODE;
        }
        if (!isStringEqual(oldConn.getUsbNcmNode(), newConn.getUsbNcmNode())) {
            return genericChangedMask | ConnDevice.MASK_USB_NCM_NODE;
        }
        return genericChangedMask;
    }

    private long getConnectionChangedMask(ConnDevice oldConn, ConnDevice newConn) {
        long connectionChangedMask = oldConn.getDeviceConnectedState() != newConn.getDeviceConnectedState() ? 0 | 256 : 0L;
        if (!isHashmapEqual(oldConn.getConnectedTypeState(), newConn.getConnectedTypeState())) {
            connectionChangedMask |= 512;
        }
        if (!isStringEqual(oldConn.getLastUptime(), newConn.getLastUptime())) {
            connectionChangedMask |= 16384;
        }
        if (oldConn.getLastService() != newConn.getLastService()) {
            connectionChangedMask |= 32768;
        }
        if (oldConn.isLast() != newConn.isLast()) {
            connectionChangedMask |= 65536;
        }
        if (oldConn.isFavorite() != newConn.isFavorite()) {
            connectionChangedMask |= 131072;
        }
        if (oldConn.getCapability() != newConn.getCapability()) {
            connectionChangedMask |= 262144;
        }
        if (oldConn.getAvailableCapabilitys() != newConn.getAvailableCapabilitys()) {
            connectionChangedMask |= 524288;
        }
        if (oldConn.getActiveCapability() != newConn.getActiveCapability()) {
            connectionChangedMask |= 1048576;
        }
        if (!isHashmapEqual(oldConn.getActiveCapabilityState(), newConn.getActiveCapabilityState())) {
            Log.e(TAG, "getConnectionChangedMask: ACTIVE CAPABILITY STATE changed");
            connectionChangedMask |= 2097152;
        }
        if (oldConn.getAlwaysCapability() != newConn.getAlwaysCapability()) {
            connectionChangedMask |= ConnDevice.MASK_ALWAYS_CAPABILITY;
        }
        if (!isHashmapEqual(oldConn.getBtProfileState(), newConn.getBtProfileState())) {
            Log.e(TAG, "getConnectionChangedMask: BT PROFILE STATE changed");
            connectionChangedMask |= ConnDevice.MASK_BT_PROFILE_STATE;
        }
        if (oldConn.isInUsbDeviceMode() != newConn.isInUsbDeviceMode()) {
            connectionChangedMask |= ConnDevice.MASK_IS_USB_DEVICE_MODE;
        }
        if (oldConn.isPaired() != newConn.isPaired()) {
            Log.e(TAG, "getConnectionChangedMask: PAIR STATE changed");
            connectionChangedMask |= ConnDevice.MASK_IS_PAIRED;
        }
        if (oldConn.getBtDevicePairedTime() != newConn.getBtDevicePairedTime()) {
            connectionChangedMask |= ConnDevice.MASK_BT_DEVICE_PAIRED_TIME;
        }
        if (oldConn.getWirelessCarplayConnectedTime() != newConn.getWirelessCarplayConnectedTime()) {
            connectionChangedMask |= ConnDevice.MASK_WIRELESS_CARPLAY_CONNECTED_TIME;
        }
        if (oldConn.getUsbCarplayConnectedTime() != newConn.getUsbCarplayConnectedTime()) {
            connectionChangedMask |= ConnDevice.MASK_USB_CARPLAY_CONNECTED_TIME;
        }
        if (oldConn.isUsbOtgPort() != newConn.isUsbOtgPort()) {
            return connectionChangedMask | ConnDevice.MASK_IS_USB_OTG_PORT;
        }
        return connectionChangedMask;
    }

    private boolean isMergeDevice(Device device) {
        int count = 0;
        if (device.getBtAddr() != null && !device.getBtAddr().isEmpty()) {
            count = 0 + 1;
            Log.e(TAG, "isMergeDevice: bt addr is not null.");
        }
        if (device.getUsbSerialNumber() != null && !device.getUsbSerialNumber().isEmpty()) {
            count++;
            Log.e(TAG, "isMergeDevice: usb serial is not null.");
        }
        if (device.getWifiAddr() != null && !device.getWifiAddr().isEmpty()) {
            count++;
            Log.e(TAG, "isMergeDevice: wifi addr is not null.");
        }
        if (count < 2) {
            return false;
        }
        Log.e(TAG, "isMergeDevice: device is merged.");
        return true;
    }

    public synchronized ConnDevice getChangedConnDevice(Device device) {
        if (device != null) {
            if (!TextUtils.isEmpty(device.getId())) {
                ConnDevice connDev = device.getConnDevice();
                if (getDevice(device.getId()) != null && !isMergeDevice(device) && isTempDevice(device)) {
                    connDev.setChangedMask(8L);
                    Log.e(TAG, "getChangedConnDevice: It's a tempDevice.");
                    return connDev;
                }
                if ((device.getCapability() & (this.mSupportedProjectionCapability | 6)) == 0 && !device.isPaired() && device.getDeviceConnectedState() != 2) {
                    Log.e(TAG, "getChangedConnDevice: Non-Bluetooth devices and non-projection devices.");
                    return connDev;
                }
                String uuid = connDev.getDeviceUuid();
                if (this.mConnDeviceMap.get(uuid) == null) {
                    this.mConnDeviceMap.put(uuid, connDev);
                    connDev.setChangedMask(1L);
                    Log.e(TAG, "getChangedConnDevice: Add a new device to deviceList.");
                } else if (this.mConnDeviceMap.get(uuid) != null && getDevice(uuid) == null) {
                    this.mConnDeviceMap.remove(uuid);
                    connDev.setChangedMask(2L);
                    Log.e(TAG, "getChangedConnDevice: Remove a device from deviceList.");
                } else {
                    ConnDevice oldConnDev = this.mConnDeviceMap.get(uuid);
                    long mask = getGenericChangedMask(oldConnDev, connDev) | getConnectionChangedMask(oldConnDev, connDev);
                    if (mask == 0) {
                        Log.e(TAG, "getChangedConnDevice, connDevice not changed.");
                    } else {
                        this.mConnDeviceMap.put(uuid, connDev);
                        connDev.setChangedMask(mask | 4);
                        Log.e(TAG, "getChangedConnDevice: Update device information in deviceList.");
                    }
                }
                return connDev;
            }
        }
        Log.e(TAG, "getChangedConnDevice: Invalid device:" + device);
        return null;
    }

    public boolean isAllowDbSave() {
        return this.mAllowDbSave;
    }

    public void enableSaveDatabase(boolean enable) {
        this.mAllowDbSave = enable;
    }

    public synchronized void dispose() {
        this.mDataOperaterHandler.removeCallbacks(this.mDbOptRunnable);
        this.mDataOperaterHandler.getLooper().quitSafely();
        saveDeviceToDb();
    }

    private synchronized void setIndexOfSameDeviceName(Device dev) {
        Log.e(TAG, "setIndexOfSameDeviceName, devId: " + LogEncrypt.IpAddressEncrypt(dev.getId()) + " friendlyName: " + dev.getFriendlyName());
        int index = 0;
        int[] indexNum = new int[10];
        if (dev.getFriendlyName() == null) {
            Log.e(TAG, "setIndexOfSameDeviceName, friendly name is null");
            return;
        }
        if (dev.getIndexOfSameDeviceName() != -1) {
            Log.e(TAG, "setIndexOfSameDeviceName, friendly name has no change");
            return;
        }
        if (!isShow(dev)) {
            Log.e(TAG, "setIndexOfSameDeviceName, device is not show in device list");
            return;
        }
        Log.e(TAG, "setIndexOfSameDeviceName, the device of same name: begin");
        for (Map.Entry<String, Device> entry : this.mDeviceMap.entrySet()) {
            String deviceId = entry.getValue().getId();
            String friendlyName = entry.getValue().getFriendlyName();
            int indexOfSameDeviceName = entry.getValue().getIndexOfSameDeviceName();
            if (!isTempDevice(entry.getValue()) && !dev.getId().equals(deviceId) && isShow(entry.getValue()) && dev.getFriendlyName().equals(friendlyName)) {
                if (indexOfSameDeviceName - 1 < indexNum.length) {
                    indexNum[indexOfSameDeviceName - 1] = 1;
                }
                if (indexOfSameDeviceName > index) {
                    index = indexOfSameDeviceName;
                }
            }
        }
        Log.e(TAG, "setIndexOfSameDeviceName, the device of same name: end---index: " + index);
        int index2 = index + 1;
        if (index2 > 10) {
            int i = 0;
            while (true) {
                if (i >= 10) {
                    break;
                }
                if (indexNum[i] != 0) {
                    i++;
                } else {
                    index2 = i + 1;
                    break;
                }
            }
        }
        dev.setIndexOfSameDeviceName(index2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveDeviceToDb() {
        Log.d(TAG, "saveDeviceToDb, update device to database by schedule.");
        boolean success = true;
        this.mDataBaseOperate.beginTransaction();
        int insertNumber = this.mInsertDeviceList.size();
        if (insertNumber <= 0) {
            Log.d(TAG, "saveDeviceToDb, no device to insert to database.");
        } else {
            Iterator<Device> it = this.mInsertDeviceList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Device dev = it.next();
                if (!this.mDataBaseOperate.insertDevice(dev)) {
                    Log.e(TAG, "saveDeviceToDb, insert failed. devId:" + LogEncrypt.IpAddressEncrypt(dev.getId()));
                    success = false;
                    break;
                }
                Log.d(TAG, "saveDeviceToDb, insert success. devId:" + LogEncrypt.IpAddressEncrypt(dev.getId()));
            }
        }
        int updateNumber = 0;
        if (success) {
            updateNumber = this.mUpdateDeviceList.size();
            if (updateNumber <= 0) {
                Log.d(TAG, "saveDeviceToDb, no device to update to database.");
            } else {
                Iterator<Device> it2 = this.mUpdateDeviceList.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    Device dev2 = it2.next();
                    if (!this.mDataBaseOperate.updateDevice(dev2)) {
                        Log.e(TAG, "saveDeviceToDb, update failed. devId:" + LogEncrypt.IpAddressEncrypt(dev2.getId()));
                        success = false;
                        break;
                    }
                    Log.d(TAG, "saveDeviceToDb, update success. devId:" + LogEncrypt.IpAddressEncrypt(dev2.getId()));
                }
            }
        }
        if (success) {
            Log.d(TAG, "saveDeviceToDb, saveDeviceToDb success.");
            this.mDataBaseOperate.setTransactionSuccessful();
            if (insertNumber > 0) {
                this.mInsertDeviceList.clear();
            }
            if (updateNumber > 0) {
                this.mUpdateDeviceList.clear();
            }
        }
        this.mDataBaseOperate.endTransaction();
    }

    public synchronized int saveDevice(final Device device) {
        if (device != null) {
            if (device.getDeviceConnectedState() != 2 && device.getId() != null && !device.getId().isEmpty()) {
                if (isTempDevice(device)) {
                    Log.d(TAG, "saveDevice, this is a temporary device. devId:" + LogEncrypt.IpAddressEncrypt(device.getId()));
                } else {
                    this.mDataOperaterHandler.post(new Runnable() { // from class: com.ts.car.dcm.dccontroller.storemanager.-$$Lambda$DeviceManager$9Wf3GvTMqGnjOQdNmgjOYJRld9A
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$saveDevice$0$DeviceManager(device);
                        }
                    });
                }
                this.mDeviceMap.put(device.getId(), device);
                return 0;
            }
        }
        return 2;
    }

    public /* synthetic */ void lambda$saveDevice$0$DeviceManager(Device device) {
        String deviceId = device.getId();
        if (this.mDataBaseOperate.selectDevice(deviceId, IDataBaseOperate.COLUMN_DEVICE_UUID) != null || this.mInsertDeviceList.contains(device)) {
            Log.d(TAG, "saveDevice, update device to UpdateDeviceList. devId:" + LogEncrypt.IpAddressEncrypt(deviceId));
            if (!this.mUpdateDeviceList.contains(device)) {
                if (this.mEnableIndexOfSameDeviceName) {
                    setIndexOfSameDeviceName(device);
                }
                this.mUpdateDeviceList.add(device);
            }
        } else {
            if (this.mDataBaseOperate.getDataCount() >= 100) {
                deleteDatabaseLimitByOrderLastTime();
            }
            if (this.mEnableIndexOfSameDeviceName) {
                setIndexOfSameDeviceName(device);
            }
            Log.d(TAG, "saveDevice, add device to InsertDeviceList. devId:" + LogEncrypt.IpAddressEncrypt(deviceId));
            this.mInsertDeviceList.add(device);
        }
        if (this.mDatabaseSyncTime == 0) {
            saveDeviceToDb();
        }
    }

    public synchronized int saveDeviceMemory(Device device) {
        int resultCode;
        if (device != null) {
            this.mDeviceMap.put(device.getId(), device);
            resultCode = 0;
        } else {
            resultCode = 2;
        }
        return resultCode;
    }

    public synchronized int removeDevice(final String uuid) {
        int resultCode;
        resultCode = 0;
        if (uuid != null) {
            Device device = getDevice(uuid);
            if (device != null) {
                this.mDeviceMap.remove(device.getId());
                this.mDataOperaterHandler.post(new Runnable() { // from class: com.ts.car.dcm.dccontroller.storemanager.-$$Lambda$DeviceManager$7XiJz7kRPkirFa9qjto-B-1lgMA
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$removeDevice$1$DeviceManager(uuid);
                    }
                });
            } else {
                Log.e(TAG, "removeDevice, no found in memory. devId:" + LogEncrypt.IpAddressEncrypt(uuid));
                resultCode = 4;
            }
        } else {
            resultCode = 2;
        }
        return resultCode;
    }

    public /* synthetic */ void lambda$removeDevice$1$DeviceManager(String uuid) {
        boolean isExistInsert = false;
        Iterator<Device> it = this.mInsertDeviceList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Device deviceTmep = it.next();
            if (deviceTmep.getId() != null && deviceTmep.getId().equals(uuid)) {
                Log.d(TAG, "removeDevice, insertList remove, uuid:" + LogEncrypt.IpAddressEncrypt(uuid));
                this.mInsertDeviceList.remove(deviceTmep);
                isExistInsert = true;
                break;
            }
        }
        Log.d(TAG, "removeDevice, insert exist:" + isExistInsert);
        for (Device deviceTmep2 : this.mUpdateDeviceList) {
            if (deviceTmep2.getId() != null && deviceTmep2.getId().equals(uuid)) {
                Log.d(TAG, "removeDevice, updateList remove, uuid:" + LogEncrypt.IpAddressEncrypt(uuid));
                this.mUpdateDeviceList.remove(deviceTmep2);
            }
        }
        if (!isExistInsert && !this.mDataBaseOperate.deleteDevice(uuid)) {
            Log.e(TAG, "removeDevice, AsyncDataStoreTask remove one failed. devId:" + LogEncrypt.IpAddressEncrypt(uuid));
        }
    }

    public synchronized int removeDeviceMemory(String uuid) {
        int resultCode;
        if (uuid != null) {
            Device device = getDevice(uuid);
            if (device != null) {
                this.mDeviceMap.remove(device.getId());
                resultCode = 0;
            } else {
                Log.e(TAG, "removeDeviceMemory, not found in memory, devId:" + LogEncrypt.IpAddressEncrypt(uuid));
                resultCode = 4;
            }
        } else {
            resultCode = 2;
        }
        return resultCode;
    }

    public synchronized boolean removeAllDevice() {
        if (this.mDataBaseOperate != null) {
            this.mDeviceMap.clear();
            this.mDataOperaterHandler.post(new Runnable() { // from class: com.ts.car.dcm.dccontroller.storemanager.-$$Lambda$DeviceManager$ndKa8xqhJTh4qgvxviLsKO5BMCw
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$removeAllDevice$2$DeviceManager();
                }
            });
            return true;
        }
        return false;
    }

    public /* synthetic */ void lambda$removeAllDevice$2$DeviceManager() {
        Log.d(TAG, "removeAllDevice, start.");
        if (!this.mDataBaseOperate.deleteAllDevice()) {
            Log.e(TAG, "removeAllDevice, AsyncDataStoreTask remove all failed.");
        } else {
            this.mInsertDeviceList.clear();
            this.mUpdateDeviceList.clear();
        }
        Log.d(TAG, "removeAllDevice, end.");
    }

    public synchronized Device getDevice(String uuid) {
        Device dev;
        dev = this.mDeviceMap.get(uuid);
        if (dev == null) {
            Log.e(TAG, "findMergedDevice, device is merged.");
            dev = getDeviceByConntype(1, uuid);
            if (dev == null) {
                dev = getDeviceByConntype(2, uuid);
                if (dev == null) {
                    Log.e(TAG, "findMergedDevice, device not found.");
                } else {
                    Log.d(TAG, "findMergedDevice, devId is btMac: " + LogEncrypt.IpAddressEncrypt(uuid) + " ---> " + LogEncrypt.IpAddressEncrypt(dev.getId()));
                }
            } else {
                Log.d(TAG, "findMergedDevice, devId is usbSerial: " + LogEncrypt.IpAddressEncrypt(uuid) + " ---> " + LogEncrypt.IpAddressEncrypt(dev.getId()));
            }
        } else {
            Log.e(TAG, "findMergedDevice, device is not merged.");
        }
        return dev;
    }

    public synchronized Device getDevice(final LinkDevice ldev) {
        Device cdev;
        cdev = null;
        if (ldev.getConnectionType() == 1) {
            if (ldev.getId() != null) {
                cdev = this.mDeviceMap.values().stream().filter(new Predicate() { // from class: com.ts.car.dcm.dccontroller.storemanager.-$$Lambda$DeviceManager$g3vMOK_WXQEv5ExI2ODzYsLdQxE
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        Device device = (Device) obj;
                        return (device.getUsbSerialNumber() != null ? device.getUsbSerialNumber() : device.getId()).equals(ldev.getId());
                    }
                }).findAny().orElse(null);
            }
        } else if (ldev.getConnectionType() == 2) {
            if (ldev.getBtAddress() != null) {
                cdev = this.mDeviceMap.values().stream().filter(new Predicate() { // from class: com.ts.car.dcm.dccontroller.storemanager.-$$Lambda$DeviceManager$yScRjcjQ0GFXCnGrP5fwrt7l37s
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return ldev.getBtAddress().equalsIgnoreCase(((Device) obj).getBtAddr());
                    }
                }).findAny().orElse(null);
            }
        } else if (ldev.getConnectionType() == 3 && ldev.getWifiAddress() != null) {
            cdev = this.mDeviceMap.values().stream().filter(new Predicate() { // from class: com.ts.car.dcm.dccontroller.storemanager.-$$Lambda$DeviceManager$PFSNdy08m68PbxvfHbFAk5Jfl6w
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ldev.getWifiAddress().equalsIgnoreCase(((Device) obj).getWifiAddr());
                }
            }).findAny().orElse(null);
        }
        return cdev;
    }

    public synchronized Device getDeviceByConntype(int type, final String key) {
        Device cdev;
        cdev = null;
        if (key != null) {
            if (type == 1) {
                cdev = this.mDeviceMap.values().stream().filter(new Predicate() { // from class: com.ts.car.dcm.dccontroller.storemanager.-$$Lambda$DeviceManager$vP5gvaDRdspRS-mbAjRF7_ZTAyY
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        Device device = (Device) obj;
                        return key.equals(device.getUsbSerialNumber() != null ? device.getUsbSerialNumber() : device.getId());
                    }
                }).findAny().orElse(null);
            } else if (type == 2) {
                cdev = this.mDeviceMap.values().stream().filter(new Predicate() { // from class: com.ts.car.dcm.dccontroller.storemanager.-$$Lambda$DeviceManager$oE6pQXDVBM_Mvt4sbX_puy2ishs
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return key.equalsIgnoreCase(((Device) obj).getBtAddr());
                    }
                }).findAny().orElse(null);
            } else if (type == 3) {
                cdev = this.mDeviceMap.values().stream().filter(new Predicate() { // from class: com.ts.car.dcm.dccontroller.storemanager.-$$Lambda$DeviceManager$RsK3EjfwIeezQODbAV0p6izUMzE
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return key.equalsIgnoreCase(((Device) obj).getWifiAddr());
                    }
                }).findAny().orElse(null);
            }
        } else {
            Log.e(TAG, "getDeviceByConntype, parameter is null.");
        }
        return cdev;
    }

    public synchronized Map<String, Device> getDeviceMap() {
        return new HashMap(this.mDeviceMap);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized boolean isShow(com.ts.car.dcm.dccontroller.storemanager.Device r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            int r0 = r3.getCapability()     // Catch: java.lang.Throwable -> L26
            int r1 = r2.mSupportedProjectionCapability     // Catch: java.lang.Throwable -> L26
            r0 = r0 & r1
            if (r0 != 0) goto L23
            java.lang.String r0 = r3.getBtAddr()     // Catch: java.lang.Throwable -> L26
            if (r0 == 0) goto L21
            java.lang.String r0 = r3.getBtAddr()     // Catch: java.lang.Throwable -> L26
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L26
            if (r0 != 0) goto L21
            boolean r0 = r3.isPaired()     // Catch: java.lang.Throwable -> L26
            if (r0 == 0) goto L21
            goto L23
        L21:
            r0 = 0
            goto L24
        L23:
            r0 = 1
        L24:
            monitor-exit(r2)
            return r0
        L26:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.car.dcm.dccontroller.storemanager.DeviceManager.isShow(com.ts.car.dcm.dccontroller.storemanager.Device):boolean");
    }

    public synchronized void getConnDevices(Bundle devices) {
        Log.e(TAG, "getConnDevices, supportedCapability: " + this.mSupportedProjectionCapability);
        if (devices != null) {
            for (Map.Entry<String, Device> entry : this.mDeviceMap.entrySet()) {
                Device dev = entry.getValue();
                if (dev.getDeviceConnectedState() != 2 && dev.getDeviceConnectedState() != 4 && isShow(dev)) {
                    Log.d(TAG, "getConnDevices, queue devId:" + LogEncrypt.IpAddressEncrypt(dev.getId()) + " , capability: " + dev.getCapability());
                    devices.putParcelable(dev.getId(), dev.getConnDevice());
                }
            }
        } else {
            Log.e(TAG, "getConnDevices, devices is null.");
        }
    }

    public synchronized void getOnlineDevices(Bundle devices) {
        for (Map.Entry<String, Device> entry : this.mDeviceMap.entrySet()) {
            if (entry.getValue().isOnline()) {
                devices.putParcelable(entry.getKey(), entry.getValue().getConnDevice());
            }
        }
    }

    public synchronized boolean checkDeviceByUuid(String uuid) {
        if (uuid == null) {
            Log.e(TAG, "checkDeviceByUuid, paramter is null.");
            return false;
        }
        return this.mDeviceMap.containsKey(uuid);
    }

    public synchronized int updateDeviceFriendlyName(String devId, String name) {
        int result;
        result = 0;
        if (devId != null && name != null) {
            final Device device = getDevice(devId);
            if (device != null) {
                device.setFriendlyName(name);
                if (isTempDevice(device)) {
                    Log.d(TAG, "updateDeviceFriendlyName, this is a temporary. devId:" + LogEncrypt.IpAddressEncrypt(devId));
                } else {
                    if (this.mEnableIndexOfSameDeviceName) {
                        setIndexOfSameDeviceName(device);
                    }
                    this.mDataOperaterHandler.post(new Runnable() { // from class: com.ts.car.dcm.dccontroller.storemanager.-$$Lambda$DeviceManager$_JcyyESOp5UWQH7QwEROVClmd8U
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$updateDeviceFriendlyName$9$DeviceManager(device);
                        }
                    });
                }
            } else {
                result = 4;
                Log.e(TAG, "updateDeviceFriendlyName, no find devId:" + LogEncrypt.IpAddressEncrypt(devId));
            }
        } else {
            result = 2;
            Log.e(TAG, "updateDeviceFriendlyName, parameter is null. devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", name:" + name);
        }
        return result;
    }

    public /* synthetic */ void lambda$updateDeviceFriendlyName$9$DeviceManager(Device device) {
        this.mUpdateDeviceList.add(device);
        if (this.mDatabaseSyncTime == 0) {
            saveDeviceToDb();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x002a, code lost:
    
        r0 = r2.getValue();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized com.ts.car.dcm.dccontroller.storemanager.Device getLastDevice() {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 0
            java.util.Map<java.lang.String, com.ts.car.dcm.dccontroller.storemanager.Device> r1 = r4.mDeviceMap     // Catch: java.lang.Throwable -> L2f
            java.util.Set r1 = r1.entrySet()     // Catch: java.lang.Throwable -> L2f
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Throwable -> L2f
        Lc:
            boolean r2 = r1.hasNext()     // Catch: java.lang.Throwable -> L2f
            if (r2 == 0) goto L2d
            java.lang.Object r2 = r1.next()     // Catch: java.lang.Throwable -> L2f
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch: java.lang.Throwable -> L2f
            java.lang.Object r3 = r2.getValue()     // Catch: java.lang.Throwable -> L2f
            com.ts.car.dcm.dccontroller.storemanager.Device r3 = (com.ts.car.dcm.dccontroller.storemanager.Device) r3     // Catch: java.lang.Throwable -> L2f
            boolean r3 = r3.isLast()     // Catch: java.lang.Throwable -> L2f
            if (r3 == 0) goto L2c
            java.lang.Object r1 = r2.getValue()     // Catch: java.lang.Throwable -> L2f
            com.ts.car.dcm.dccontroller.storemanager.Device r1 = (com.ts.car.dcm.dccontroller.storemanager.Device) r1     // Catch: java.lang.Throwable -> L2f
            r0 = r1
            goto L2d
        L2c:
            goto Lc
        L2d:
            monitor-exit(r4)
            return r0
        L2f:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.car.dcm.dccontroller.storemanager.DeviceManager.getLastDevice():com.ts.car.dcm.dccontroller.storemanager.Device");
    }

    public synchronized int setLastDevice(String devId) {
        if (this.mDataBaseOperate != null && devId != null) {
            final Device newLastDevice = getDevice(devId);
            if (newLastDevice != null) {
                if (newLastDevice.isLast()) {
                    Log.d(TAG, "setLastDevice, devId:" + LogEncrypt.IpAddressEncrypt(devId) + " already is last device.");
                    return 0;
                }
                final Device oldLastDevice = getLastDevice();
                if (oldLastDevice != null) {
                    oldLastDevice.setLast(false);
                    Log.d(TAG, "setLastDevice, reset old last device:" + LogEncrypt.IpAddressEncrypt(oldLastDevice.getId()));
                }
                newLastDevice.setLast(true);
                this.mDataOperaterHandler.post(new Runnable() { // from class: com.ts.car.dcm.dccontroller.storemanager.-$$Lambda$DeviceManager$pne44Bc6Sz2ti250KwpZs2Rp3bk
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$setLastDevice$10$DeviceManager(oldLastDevice, newLastDevice);
                    }
                });
                return 0;
            }
            return 4;
        }
        Log.e(TAG, "setLastDevice, mDataBaseProxy is null or id:" + LogEncrypt.IpAddressEncrypt(devId));
        return 1;
    }

    public /* synthetic */ void lambda$setLastDevice$10$DeviceManager(Device oldLastDevice, Device newLastDevice) {
        if (oldLastDevice != null) {
            this.mUpdateDeviceList.add(oldLastDevice);
        }
        this.mUpdateDeviceList.add(newLastDevice);
        if (this.mDatabaseSyncTime == 0) {
            saveDeviceToDb();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x002a, code lost:
    
        r0 = r2.getValue();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized com.ts.car.dcm.dccontroller.storemanager.Device getFavoritePhone() {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 0
            java.util.Map<java.lang.String, com.ts.car.dcm.dccontroller.storemanager.Device> r1 = r4.mDeviceMap     // Catch: java.lang.Throwable -> L2f
            java.util.Set r1 = r1.entrySet()     // Catch: java.lang.Throwable -> L2f
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Throwable -> L2f
        Lc:
            boolean r2 = r1.hasNext()     // Catch: java.lang.Throwable -> L2f
            if (r2 == 0) goto L2d
            java.lang.Object r2 = r1.next()     // Catch: java.lang.Throwable -> L2f
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch: java.lang.Throwable -> L2f
            java.lang.Object r3 = r2.getValue()     // Catch: java.lang.Throwable -> L2f
            com.ts.car.dcm.dccontroller.storemanager.Device r3 = (com.ts.car.dcm.dccontroller.storemanager.Device) r3     // Catch: java.lang.Throwable -> L2f
            boolean r3 = r3.isFavorite()     // Catch: java.lang.Throwable -> L2f
            if (r3 == 0) goto L2c
            java.lang.Object r1 = r2.getValue()     // Catch: java.lang.Throwable -> L2f
            com.ts.car.dcm.dccontroller.storemanager.Device r1 = (com.ts.car.dcm.dccontroller.storemanager.Device) r1     // Catch: java.lang.Throwable -> L2f
            r0 = r1
            goto L2d
        L2c:
            goto Lc
        L2d:
            monitor-exit(r4)
            return r0
        L2f:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.car.dcm.dccontroller.storemanager.DeviceManager.getFavoritePhone():com.ts.car.dcm.dccontroller.storemanager.Device");
    }

    public synchronized int setFavoritePhone(String uuid) {
        if (this.mDataBaseOperate != null && uuid != null) {
            final Device newFavoriteDevice = getDevice(uuid);
            if (newFavoriteDevice != null) {
                if (newFavoriteDevice.isFavorite()) {
                    Log.d(TAG, "setFavoritePhone, set the same device id.");
                    return 0;
                }
                int capability = newFavoriteDevice.getCapability();
                if ((capability & TELEPHONE_ALL) == 0) {
                    Log.e(TAG, "setFavoritePhone, id:" + LogEncrypt.IpAddressEncrypt(uuid) + ", not telephone or projection device.");
                    return 1;
                }
                final Device oldFavoriteDevice = getFavoritePhone();
                if (oldFavoriteDevice != null) {
                    oldFavoriteDevice.setFavorite(false);
                    Log.d(TAG, "setFavoritePhone, old:" + LogEncrypt.IpAddressEncrypt(oldFavoriteDevice.getId()));
                }
                newFavoriteDevice.setFavorite(true);
                this.mDataOperaterHandler.post(new Runnable() { // from class: com.ts.car.dcm.dccontroller.storemanager.-$$Lambda$DeviceManager$_gW0g-kO5eLc9tR40xKpuVF-rYY
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$setFavoritePhone$11$DeviceManager(oldFavoriteDevice, newFavoriteDevice);
                    }
                });
                return 0;
            }
            return 4;
        }
        Log.e(TAG, "setFavoritePhone, mDataBaseProxy is null or id:" + LogEncrypt.IpAddressEncrypt(uuid));
        return 1;
    }

    public /* synthetic */ void lambda$setFavoritePhone$11$DeviceManager(Device oldFavoriteDevice, Device newFavoriteDevice) {
        if (oldFavoriteDevice != null) {
            this.mUpdateDeviceList.add(oldFavoriteDevice);
        }
        this.mUpdateDeviceList.add(newFavoriteDevice);
        if (this.mDatabaseSyncTime == 0) {
            saveDeviceToDb();
        }
    }

    private void deleteDatabaseLimitByOrderLastTime() {
        List<Device> list = this.mDataBaseOperate.selectDeviceByOrderAscLimit("order by cast(LAST_ONLINE_TIME as signed) ", "asc", 10);
        if (list == null) {
            Log.e(TAG, "deleteDatabaseLimitByOrderLastTime, list is null.");
            return;
        }
        for (Device device : list) {
            removeDeviceMemory(device.getId());
        }
        DcController dcController = this.mDcController;
        if (dcController != null) {
            dcController.sendMessage(DcController.EVENT_NOTIFY_DEVICES_DELETED, 0, 0, list);
        }
        if (!this.mDataBaseOperate.deleteDeviceByOrderAscLimit("order by cast(LAST_ONLINE_TIME as signed) ", "asc", 10)) {
            Log.e(TAG, "deleteDatabaseLimitByOrderLastTime, AsyncDataStoreTask remove limit data failed.");
        }
    }

    private boolean isUsbSsidNull(Device device) {
        if (device == null) {
            Log.e(TAG, "isUsbSsidNull paramter is null");
            return false;
        }
        Set<Integer> set = device.getDeviceConnTypeSet();
        for (Integer conType : set) {
            if (conType.intValue() == 1 && device.getUsbSerialNumber() == null) {
                Log.d(TAG, "isUsbSsidNull, usb ssid is null, this is a temporary device.");
                return true;
            }
        }
        return false;
    }

    private boolean isBtTempDevice(Device device) {
        if (device == null) {
            return true;
        }
        Set<Integer> set = device.getDeviceConnTypeSet();
        if (set.size() == 1 && set.contains(2)) {
            return true ^ device.isPaired();
        }
        return false;
    }

    private boolean isTempDevice(Device device) {
        if (device == null || isUsbSsidNull(device) || isBtTempDevice(device)) {
            return true;
        }
        Log.d(TAG, "isTempDevice, device is not a tempDevice.");
        return false;
    }

    public synchronized List<CmDevice> getCmDeviceList(int service) {
        int devCapa;
        List<CmDevice> devices = new ArrayList<>();
        if (service == 1) {
            devCapa = 576;
        } else if (service == 2) {
            devCapa = 2304;
        } else if (service == 3) {
            devCapa = 1152;
        } else if (service == 4) {
            devCapa = 8;
        } else {
            return null;
        }
        for (Map.Entry<String, Device> entry : this.mDeviceMap.entrySet()) {
            int cmCapa = entry.getValue().getAvailableCapability() & devCapa;
            if (entry.getValue().isOnline() && cmCapa > 0) {
                CmDevice cmDev = entry.getValue().getCmDevice();
                cmDev.setAvailableCapability(cmDev.getAvailableCapability() & devCapa);
                devices.add(cmDev);
            }
        }
        return devices;
    }

    public AbnormalDevice getAbnormalDevice(String deviceId) {
        return this.mAbnormalDeviceMap.get(deviceId);
    }

    public void saveAbnormalDevice(AbnormalDevice abnormalDevice) {
        this.mAbnormalDeviceMap.put(abnormalDevice.getDeviceId(), abnormalDevice);
    }

    public void removeAbnormalDevice(String deviceId) {
        this.mAbnormalDeviceMap.remove(deviceId);
    }

    public List<AbnormalDevice> getAbnormalDeviceList() {
        List<AbnormalDevice> abnormalDevices = new ArrayList<>();
        for (Map.Entry<String, AbnormalDevice> entry : this.mAbnormalDeviceMap.entrySet()) {
            abnormalDevices.add(entry.getValue());
        }
        return abnormalDevices;
    }
}

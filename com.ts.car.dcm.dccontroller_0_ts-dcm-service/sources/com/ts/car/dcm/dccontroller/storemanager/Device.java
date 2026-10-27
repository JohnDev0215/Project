package com.ts.car.dcm.dccontroller.storemanager;

import android.hardware.usb.UsbDevice;
import android.util.Log;
import com.ts.car.dcm.common.data.LinkDevice;
import com.ts.car.dcm.common.data.LogEncrypt;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import ts.car.dcm.common.data.CmDevice;
import ts.car.dcm.common.data.CmsConstants;
import ts.car.dcm.common.data.ConnDevice;

/* JADX INFO: loaded from: classes.dex */
public class Device {
    private static final String TAG = "Device";
    private int mAccepted;
    private HashMap<Integer, Integer> mActiveCapaState;
    private int mActiveCapability;
    private int mAlwaysCapability;
    private boolean mAttached;
    private int mAvailableCapability;
    private String mBtAddr;
    private long mBtDevicePairedTime;
    private HashMap<Integer, Integer> mBtProfileState;
    private int mCapability;
    private final HashMap<Integer, Integer> mConnectedTypeState;
    private boolean mCpwlOverIapDetectStarting;
    private int mDeviceConnectedState;
    private int mDeviceType;
    private boolean mFavorite;
    private String mFriendlyName;
    private boolean mIapChecked;
    private String mIapNode;
    private String mId;
    private boolean mInUsbAoaMode;
    private boolean mInUsbDeviceMode;
    private int mIndexOfSameDeviceName;
    private boolean mIsAndroidAutoFirstConn;
    private boolean mIsCarlifeFirstConn;
    private boolean mIsCarplayFirstConn;
    private boolean mIsCpwlOverIapDetected;
    private boolean mIsCpwlOverIapDetectedNotified;
    private boolean mIsDisconnectByUserAction;
    private boolean mIsNeedRecoryBt;
    private boolean mIsPaired;
    private boolean mIsUsbOtgPort;
    private boolean mIsWirelessAaRequested;
    private boolean mLast;
    private int mLastActivateCapability;
    private int mLastAvailableCapability;
    private int mLastService;
    private String mLastUptime;
    private volatile String mMobileDeviceId;
    private String mPairedDevId;
    private int mQuickReconnectCapability;
    private boolean mResetUsbDone;
    private boolean mSaveAlways;
    private long mUsbAndroidAutoConnectedTime;
    private long mUsbCarplayConnectedTime;
    private UsbDevice mUsbDevice;
    private String mUsbHidNode;
    private String mUsbNcmNode;
    private int mUsbRoleSwitchPurpose;
    private String mUsbSerialNumber;
    private String mWifiAddr;
    private String mWifiIp;
    private long mWirelessAndroidAutoConnectedTime;
    private long mWirelessCarplayConnectedTime;

    private Device() {
        this.mConnectedTypeState = new HashMap<>();
        this.mActiveCapaState = new HashMap<>();
        this.mBtProfileState = new HashMap<>();
        this.mInUsbDeviceMode = false;
        this.mIsPaired = false;
        this.mAttached = false;
        this.mIapChecked = false;
        this.mCpwlOverIapDetectStarting = false;
        this.mIsCarplayFirstConn = true;
        this.mIsCarlifeFirstConn = true;
        this.mIsAndroidAutoFirstConn = true;
        this.mIsWirelessAaRequested = false;
        this.mIsCpwlOverIapDetectedNotified = false;
        this.mIsCpwlOverIapDetected = false;
        this.mInUsbAoaMode = false;
        this.mResetUsbDone = false;
        this.mIsUsbOtgPort = false;
        this.mIndexOfSameDeviceName = -1;
        this.mIsDisconnectByUserAction = false;
    }

    public Device(String deviceUuid) {
        this.mConnectedTypeState = new HashMap<>();
        this.mActiveCapaState = new HashMap<>();
        this.mBtProfileState = new HashMap<>();
        this.mInUsbDeviceMode = false;
        this.mIsPaired = false;
        this.mAttached = false;
        this.mIapChecked = false;
        this.mCpwlOverIapDetectStarting = false;
        this.mIsCarplayFirstConn = true;
        this.mIsCarlifeFirstConn = true;
        this.mIsAndroidAutoFirstConn = true;
        this.mIsWirelessAaRequested = false;
        this.mIsCpwlOverIapDetectedNotified = false;
        this.mIsCpwlOverIapDetected = false;
        this.mInUsbAoaMode = false;
        this.mResetUsbDone = false;
        this.mIsUsbOtgPort = false;
        this.mIndexOfSameDeviceName = -1;
        this.mIsDisconnectByUserAction = false;
        this.mId = deviceUuid;
    }

    public Device(LinkDevice ldev) {
        this.mConnectedTypeState = new HashMap<>();
        this.mActiveCapaState = new HashMap<>();
        this.mBtProfileState = new HashMap<>();
        this.mInUsbDeviceMode = false;
        this.mIsPaired = false;
        this.mAttached = false;
        this.mIapChecked = false;
        this.mCpwlOverIapDetectStarting = false;
        this.mIsCarplayFirstConn = true;
        this.mIsCarlifeFirstConn = true;
        this.mIsAndroidAutoFirstConn = true;
        this.mIsWirelessAaRequested = false;
        this.mIsCpwlOverIapDetectedNotified = false;
        this.mIsCpwlOverIapDetected = false;
        this.mInUsbAoaMode = false;
        this.mResetUsbDone = false;
        this.mIsUsbOtgPort = false;
        this.mIndexOfSameDeviceName = -1;
        this.mIsDisconnectByUserAction = false;
        this.mId = ldev.getId();
        int dcs = ldev.getDeviceState();
        this.mFriendlyName = ldev.getFriendlyName();
        if (ldev.getConnectionType() == 1) {
            this.mConnectedTypeState.put(1, Integer.valueOf(dcs));
        } else if (ldev.getConnectionType() == 2) {
            this.mConnectedTypeState.put(2, Integer.valueOf(dcs));
        }
        this.mUsbSerialNumber = ldev.getUsbSsid();
        this.mBtAddr = ldev.getBtAddress();
        this.mWifiAddr = ldev.getWifiAddress();
        this.mCapability = ldev.getCapability();
        if (dcs == 3) {
            this.mAvailableCapability = ldev.getCapability();
        }
        updateDeviceConnectedState();
        this.mWifiIp = ldev.getWifiIpAddrress();
        this.mDeviceType = ldev.getDeviceType();
        this.mUsbHidNode = ldev.getUsbHidNode();
        this.mIapNode = ldev.getIapNode();
        this.mUsbNcmNode = ldev.getUsbNcmNode();
        this.mIsPaired = ldev.isPaired();
        this.mIsUsbOtgPort = ldev.isUsbOtgPort();
        this.mSaveAlways = false;
    }

    public boolean isSaveAlways() {
        return this.mSaveAlways;
    }

    public void setSaveAlways(boolean always) {
        this.mSaveAlways = always;
    }

    public int getQuickReconnectCapability() {
        return this.mQuickReconnectCapability;
    }

    public void setQuickReconnectCapability(int quickReconnectCapability) {
        this.mQuickReconnectCapability = quickReconnectCapability;
    }

    public boolean isAttached() {
        return this.mAttached;
    }

    public void setAttached(boolean flag) {
        this.mAttached = flag;
    }

    public synchronized boolean isPaired() {
        return this.mIsPaired;
    }

    public synchronized void setIsPaired(boolean isPaired) {
        this.mIsPaired = isPaired;
        if (!isPaired) {
            this.mIsCpwlOverIapDetectedNotified = false;
            this.mIsCpwlOverIapDetected = false;
        }
    }

    public void setIsIapChecked(boolean isIapChecked) {
        this.mIapChecked = isIapChecked;
    }

    public boolean isIapChecked() {
        return this.mIapChecked;
    }

    public boolean isCpwlOverIapDetectStarting() {
        return this.mCpwlOverIapDetectStarting;
    }

    public void setCpwlOverIapDetectStarting(boolean flag) {
        this.mCpwlOverIapDetectStarting = flag;
    }

    private synchronized void updateDeviceConnectedState() {
        if (this.mDeviceConnectedState == 2) {
            Log.d(TAG, "Deleteing do not updateDeviceConnectedState");
            return;
        }
        if (getConnectionNumber() == 0 && this.mConnectedTypeState.size() == 0) {
            this.mDeviceConnectedState = 0;
        } else if (isReady() || this.mConnectedTypeState.size() > 0) {
            this.mDeviceConnectedState = 1;
        } else if (isDetecting()) {
            this.mDeviceConnectedState = 4;
        } else {
            Log.d(TAG, "unknow state, mConnectedTypeState:" + this.mConnectedTypeState);
        }
    }

    private void updateDeviceByUsb(LinkDevice dev) {
        if (dev.getUsbSsid() != null) {
            this.mUsbSerialNumber = dev.getUsbSsid();
        }
        if (dev.getUsbHidNode() != null) {
            this.mUsbHidNode = dev.getUsbHidNode();
        }
        if (dev.getUsbNcmNode() != null) {
            this.mUsbNcmNode = dev.getUsbNcmNode();
        }
        if (dev.getIapNode() != null) {
            this.mIapNode = dev.getIapNode();
        }
        this.mIsUsbOtgPort = dev.isUsbOtgPort();
        int dcs = dev.getDeviceState();
        addConnectedTypeState(1, dcs);
        updateDeviceCapability(dev, 33273);
    }

    private void updateDeviceByBt(LinkDevice dev) {
        this.mIsPaired = dev.isPaired();
        if (dev.getBtAddress() != null) {
            this.mBtAddr = dev.getBtAddress();
        }
        if (!this.mIsPaired) {
            this.mIsCpwlOverIapDetectedNotified = false;
            this.mIsCpwlOverIapDetected = false;
        }
        int dcs = dev.getDeviceState();
        addConnectedTypeState(2, dcs);
        updateDeviceCapability(dev, CmsConstants.Capability.CAP_BT_ALL);
    }

    private void updateDeviceByWifi(LinkDevice dev) {
        if (dev.getWifiAddress() != null) {
            this.mWifiAddr = dev.getWifiAddress();
        }
        if (dev.getWifiIpAddrress() != null) {
            this.mWifiIp = dev.getWifiIpAddrress();
        }
        updateDeviceCapability(dev, 30720);
    }

    private void updateDeviceCapability(LinkDevice dev, int allCapability) {
        this.mCapability |= dev.getCapability();
        int i = this.mDeviceConnectedState;
        if (i == 1) {
            int capa = getAvailableCapability();
            setLastAvailableCapability(capa);
            setAvailableCapability((capa & (~allCapability)) | dev.getCapability());
        } else if (i == 4) {
            setCapability(getCapability() | dev.getCapability());
        } else {
            setAvailableCapability(0);
            setActiveCapability(0);
        }
    }

    private boolean isFriendlyNameChanged(String oldName, String newName) {
        if (newName == null || newName.equals(oldName)) {
            return false;
        }
        return true;
    }

    public synchronized void updateDevice(LinkDevice dev) {
        Log.d(TAG, "updateDevice:" + dev);
        updateDeviceType(dev.getDeviceType());
        String oldFriendlyName = this.mFriendlyName;
        boolean isBtDevice = (this.mBtAddr == null || this.mBtAddr.isEmpty() || (this.mUsbSerialNumber != null && !this.mUsbSerialNumber.isEmpty()) || (this.mWifiAddr != null && !this.mWifiAddr.isEmpty())) ? false : true;
        if (dev.getConnectionType() == 2) {
            this.mFriendlyName = dev.getFriendlyName();
        } else if (dev.getConnectionType() == 1 && (this.mFriendlyName == null || this.mFriendlyName.isEmpty())) {
            this.mFriendlyName = dev.getFriendlyName();
        }
        if (isFriendlyNameChanged(oldFriendlyName, this.mFriendlyName)) {
            this.mIndexOfSameDeviceName = -1;
        }
        if (this.mDeviceConnectedState != 1) {
            if (this.mDeviceConnectedState == 2) {
                return;
            }
            if (dev.getDeviceState() == 3) {
                this.mDeviceConnectedState = 1;
            } else if (dev.getDeviceState() == 2) {
                if (dev.getConnectionType() == 2 && isBtDevice) {
                    this.mDeviceConnectedState = 4;
                    Log.e(TAG, "updateDevice, Bluetooth devices that have only been scanned");
                } else {
                    Log.d(TAG, "updateDevice1");
                    this.mDeviceConnectedState = 0;
                }
            }
        }
        Log.e(TAG, "updateDevice, mDeviceConnectedState: " + this.mDeviceConnectedState);
        if (dev.getConnectionType() == 1) {
            updateDeviceByUsb(dev);
        } else if (dev.getConnectionType() == 2) {
            updateDeviceByBt(dev);
        } else if (dev.getConnectionType() == 3) {
            updateDeviceByWifi(dev);
        } else {
            Log.d(TAG, "Do not supported device type:" + dev.getConnectionType());
        }
    }

    private void mergeDeviceByWifi(Device info) {
        if (info.hasConnection(3)) {
            if (this.mWifiAddr == null) {
                this.mWifiAddr = info.getWifiAddr();
            }
            if (this.mWifiIp == null) {
                this.mWifiIp = info.getWifiIp();
            }
        }
    }

    private void mergeDeviceByBt(Device info) {
        if (info.hasConnection(2)) {
            if (this.mBtAddr == null) {
                this.mBtAddr = info.getBtAddr();
            }
            if (info.isIsNeedRecoryBt()) {
                this.mIsNeedRecoryBt = info.isIsNeedRecoryBt();
            }
            String oldFriendlyName = this.mFriendlyName;
            this.mFriendlyName = info.getFriendlyName();
            int indexOfBtDevice = info.getIndexOfSameDeviceName();
            if (isFriendlyNameChanged(oldFriendlyName, this.mFriendlyName)) {
                this.mIndexOfSameDeviceName = -1;
            } else if (indexOfBtDevice != -1 && indexOfBtDevice < this.mIndexOfSameDeviceName) {
                this.mIndexOfSameDeviceName = indexOfBtDevice;
            }
            this.mIsPaired |= info.isPaired();
            this.mIsCpwlOverIapDetectedNotified |= info.isCpwlOverIapDetectedNotified();
            this.mIsCpwlOverIapDetected |= info.isCpwlOverIapDetected();
        }
    }

    private void mergeDeviceByUsb(Device info) {
        if (info.hasConnection(1)) {
            if (this.mIapNode == null) {
                this.mIapNode = info.getIapNode();
            }
            if (this.mUsbHidNode == null) {
                this.mUsbHidNode = info.getUsbHidNode();
            }
            if (this.mUsbNcmNode == null) {
                this.mUsbNcmNode = info.getUsbNcmNode();
            }
            if (this.mUsbSerialNumber == null) {
                this.mUsbSerialNumber = info.getUsbSerialNumber();
            }
            if (info.isInUsbDeviceMode()) {
                this.mInUsbDeviceMode = info.isInUsbDeviceMode();
            }
        }
    }

    private void updateDeviceType(int devType) {
        int i;
        if (devType != 0 && ((i = this.mDeviceType) == 0 || i == 3 || i == 4)) {
            this.mDeviceType = devType;
        }
        Log.d(TAG, "updateDeviceType: type:" + this.mDeviceType + ",devType" + devType);
    }

    public synchronized void merge(Device info) {
        if (info != null) {
            updateDeviceType(info.getDeviceType());
            if (info.getDeviceConnectedState() == 1) {
                setDeviceConnectedState(1);
            } else if (getDeviceConnectedState() == 2 && info.getDeviceConnectedState() != 2) {
                setDeviceConnectedState(info.getDeviceConnectedState());
            }
            if (info.getId().equals(info.getBtAddr()) && !info.isReady()) {
                Log.e(TAG, "merge, Do not add the connectedTypeState of the scanned bt device.");
            } else {
                addConnectedTypeState(info.getConnectedTypeState());
            }
            Log.e(TAG, "merge, connectedTypeState: " + getConnectedTypeState());
            int capa = getCapability();
            setCapability(capa | info.getCapability());
            int capa2 = getActiveCapability();
            setActiveCapability(capa2 | info.getActiveCapability());
            int capa3 = getLastAvailableCapability();
            setLastAvailableCapability(capa3 | info.getLastAvailableCapability());
            int capa4 = getAvailableCapability();
            setAvailableCapability(capa4 | info.getAvailableCapability());
            if (getBtDevicePairedTime() == 0 && info.getBtDevicePairedTime() != 0) {
                setBtDevicePairedTime(info.getBtDevicePairedTime());
            }
            if (getLastService() == 0 && info.getLastService() != 0) {
                setLastService(info.getLastService());
            }
            addActiveCapaState(info.getActiveCapaState());
            addBtProfileState(info.getBtProfileState());
            mergeDeviceByUsb(info);
            mergeDeviceByBt(info);
            mergeDeviceByWifi(info);
            if (info.isFavorite()) {
                this.mFavorite = info.isFavorite();
            }
            if (info.getAccepted() == 1) {
                this.mAccepted = info.getAccepted();
            }
            this.mPairedDevId = info.mId;
        } else {
            Log.d(TAG, "mergeDeviceInfo() failed, info is null");
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Device that = (Device) o;
        return Objects.equals(this.mId, that.mId);
    }

    public int hashCode() {
        return this.mId.hashCode();
    }

    public String getId() {
        return this.mId;
    }

    public void setId(String deviceUuid) {
        this.mId = deviceUuid;
    }

    public String getPairedDevId() {
        return this.mPairedDevId;
    }

    public synchronized String getFriendlyName() {
        return this.mFriendlyName;
    }

    public synchronized void setFriendlyName(String friendlyName) {
        if (isFriendlyNameChanged(this.mFriendlyName, friendlyName)) {
            this.mIndexOfSameDeviceName = -1;
        }
        this.mFriendlyName = friendlyName;
    }

    public synchronized String getUsbSerialNumber() {
        return this.mUsbSerialNumber;
    }

    public synchronized void setUsbSerialNumber(String usbSerial) {
        this.mUsbSerialNumber = usbSerial;
    }

    public synchronized String getBtAddr() {
        return this.mBtAddr;
    }

    public synchronized void setBtAddr(String btAddr) {
        this.mBtAddr = btAddr;
    }

    public synchronized String getWifiAddr() {
        return this.mWifiAddr;
    }

    public synchronized void setWifiAddr(String wifiAddr) {
        this.mWifiAddr = wifiAddr;
    }

    public synchronized String getWifiIp() {
        return this.mWifiIp;
    }

    public synchronized void setWifiIp(String wifiIp) {
        this.mWifiIp = wifiIp;
    }

    public synchronized String getLastUptime() {
        return this.mLastUptime;
    }

    public synchronized void setLastUptime(String lastUptime) {
        this.mLastUptime = lastUptime;
    }

    public synchronized int getLastService() {
        return this.mLastService;
    }

    public synchronized void setLastService(int lastService) {
        this.mLastService = lastService;
    }

    public synchronized boolean isLast() {
        return this.mLast;
    }

    public synchronized void setLast(boolean last) {
        this.mLast = last;
    }

    public synchronized boolean isFavorite() {
        return this.mFavorite;
    }

    public synchronized void setFavorite(boolean favorite) {
        this.mFavorite = favorite;
    }

    public synchronized int getDeviceConnectedState() {
        return this.mDeviceConnectedState;
    }

    public synchronized void setDeviceConnectedState(int state) {
        Log.d(TAG, "setDeviceConnectedState mDeviceConnectedState:" + this.mDeviceConnectedState + ",state" + state);
        this.mDeviceConnectedState = state;
        if (state == 3) {
            setAvailableCapability(0);
            setActiveCapability(0);
        }
    }

    public synchronized int getCapability() {
        return this.mCapability;
    }

    public synchronized void setCapability(int cap) {
        this.mCapability = cap;
    }

    public synchronized int getActiveCapability() {
        return this.mActiveCapability;
    }

    public synchronized void setActiveCapability(int caps) {
        this.mActiveCapability = caps;
    }

    public synchronized void addActiveCapability(int caps) {
        this.mActiveCapability |= caps;
    }

    public synchronized void removeActiveCapability(int caps) {
        this.mActiveCapability &= ~caps;
        HashMap<Integer, Integer> newActiveCapaState = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : this.mActiveCapaState.entrySet()) {
            if ((entry.getKey().intValue() & this.mActiveCapability) != 0) {
                newActiveCapaState.put(entry.getKey(), entry.getValue());
            }
        }
        this.mActiveCapaState.clear();
        this.mActiveCapaState.putAll(newActiveCapaState);
        updateDeviceConnectedState();
        if (!hasConnection(2)) {
            if (caps == 512 && (this.mAvailableCapability & 512) > 0) {
                removeAvailableCapability(512);
            } else if (caps == 1024 && (this.mAvailableCapability & 1024) > 0) {
                removeAvailableCapability(1024);
            }
        }
    }

    public synchronized boolean isActiveCapability(int cap) {
        return (this.mActiveCapability & cap) > 0;
    }

    public synchronized boolean isAvailableCapability(int cap) {
        return (this.mAvailableCapability & cap) > 0;
    }

    public synchronized boolean hasCapability(int cap) {
        return (this.mCapability & cap) > 0;
    }

    public synchronized int getConnectedTypeState(int key) {
        if (this.mConnectedTypeState.containsKey(Integer.valueOf(key))) {
            return this.mConnectedTypeState.get(Integer.valueOf(key)).intValue();
        }
        return 0;
    }

    public synchronized HashMap<Integer, Integer> getConnectedTypeState() {
        return this.mConnectedTypeState;
    }

    public synchronized void addConnectedTypeState(int key, int state) {
        this.mConnectedTypeState.put(Integer.valueOf(key), Integer.valueOf(state));
    }

    public synchronized void addConnectedTypeState(HashMap<Integer, Integer> state) {
        this.mConnectedTypeState.putAll(state);
    }

    public synchronized int getConnectionNumber() {
        return this.mConnectedTypeState.size();
    }

    public synchronized boolean usbAndBluetoothIsDisconnected() {
        if (this.mConnectedTypeState.size() == 0) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean isOnline() {
        /*
            r2 = this;
            monitor-enter(r2)
            java.util.HashMap<java.lang.Integer, java.lang.Integer> r0 = r2.mConnectedTypeState     // Catch: java.lang.Throwable -> L25
            int r0 = r0.size()     // Catch: java.lang.Throwable -> L25
            if (r0 > 0) goto L11
            java.util.HashMap<java.lang.Integer, java.lang.Integer> r0 = r2.mActiveCapaState     // Catch: java.lang.Throwable -> L25
            int r0 = r0.size()     // Catch: java.lang.Throwable -> L25
            if (r0 <= 0) goto L22
        L11:
            int r0 = r2.mDeviceConnectedState     // Catch: java.lang.Throwable -> L25
            r1 = 3
            if (r0 == r1) goto L22
            int r0 = r2.mDeviceConnectedState     // Catch: java.lang.Throwable -> L25
            r1 = 2
            if (r0 == r1) goto L22
            int r0 = r2.mDeviceConnectedState     // Catch: java.lang.Throwable -> L25
            r1 = 4
            if (r0 == r1) goto L22
            r0 = 1
            goto L23
        L22:
            r0 = 0
        L23:
            monitor-exit(r2)
            return r0
        L25:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.car.dcm.dccontroller.storemanager.Device.isOnline():boolean");
    }

    public synchronized void removeDeviceConnectType(int type) {
        this.mConnectedTypeState.remove(Integer.valueOf(type));
        if (getDeviceConnectedState() != 2 && this.mConnectedTypeState.size() == 0) {
            if (type == 2) {
                if (!isProjectionRunning(512) && !isProjectionRunning(1024)) {
                    setDeviceConnectedState(0);
                }
            } else {
                setDeviceConnectedState(0);
            }
        }
        if (getDeviceConnectedState() != 2 && this.mConnectedTypeState.size() != 0) {
            if (!isReady()) {
                setDeviceConnectedState(0);
            } else {
                Log.e(TAG, "removeDeviceConnectType: connectType is ready.");
            }
        } else {
            Log.e(TAG, "removeDeviceConnectType: deviceConnnectedState is removing.");
        }
    }

    public synchronized void removeCapabilitybyConnType(int type) {
        this.mLastAvailableCapability = this.mAvailableCapability;
        if (type == 1) {
            this.mAvailableCapability &= -33274;
            removeActiveCapability(CmsConstants.Capability.CAP_USB_BASE);
            setUsbDevice(null);
            setUsbHidNode(null);
            setUsbNcmNode(null);
            setInUsbAoaMode(false);
            setInUsbDeviceMode(false);
            setResetUsbDone(false);
            setUsbRoleSwitchPurpose(0);
        } else if (type == 2) {
            this.mAvailableCapability &= -7;
            removeActiveCapability(6);
        } else if (type == 3) {
            this.mAvailableCapability &= -30721;
            removeActiveCapability(30720);
            if (!hasConnection(2)) {
                if (isProjectionRunning(512)) {
                    removeAvailableCapability(512);
                } else if (isProjectionRunning(1024)) {
                    removeAvailableCapability(1024);
                }
            }
        }
    }

    public synchronized Set<Integer> getDeviceConnTypeSet() {
        return Collections.unmodifiableSet(this.mConnectedTypeState.keySet());
    }

    public synchronized boolean hasConnection(int key) {
        Log.d(TAG, "hasConnection,key:" + key + "," + this.mConnectedTypeState.toString());
        return this.mConnectedTypeState.containsKey(Integer.valueOf(key));
    }

    public synchronized boolean isReady() {
        return this.mConnectedTypeState.containsValue(3);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean isDetecting() {
        /*
            r2 = this;
            monitor-enter(r2)
            java.util.HashMap<java.lang.Integer, java.lang.Integer> r0 = r2.mConnectedTypeState     // Catch: java.lang.Throwable -> L20
            r1 = 2
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: java.lang.Throwable -> L20
            boolean r0 = r0.containsValue(r1)     // Catch: java.lang.Throwable -> L20
            if (r0 == 0) goto L1d
            java.util.HashMap<java.lang.Integer, java.lang.Integer> r0 = r2.mConnectedTypeState     // Catch: java.lang.Throwable -> L20
            r1 = 3
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: java.lang.Throwable -> L20
            boolean r0 = r0.containsValue(r1)     // Catch: java.lang.Throwable -> L20
            if (r0 != 0) goto L1d
            r0 = 1
            goto L1e
        L1d:
            r0 = 0
        L1e:
            monitor-exit(r2)
            return r0
        L20:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.car.dcm.dccontroller.storemanager.Device.isDetecting():boolean");
    }

    public synchronized int removeCapability(int capa) {
        int i;
        i = this.mCapability & (~capa);
        this.mCapability = i;
        return i;
    }

    public synchronized int getActiveCapaState(int capa) {
        if (this.mActiveCapaState.containsKey(Integer.valueOf(capa))) {
            return this.mActiveCapaState.get(Integer.valueOf(capa)).intValue();
        }
        return 0;
    }

    public synchronized HashMap<Integer, Integer> getActiveCapaState() {
        return this.mActiveCapaState;
    }

    public synchronized void updateActiveCapaState(int capa, int state) {
        this.mActiveCapaState.put(Integer.valueOf(capa), Integer.valueOf(state));
        updateDeviceConnectedState();
    }

    public synchronized void addActiveCapaState(HashMap<Integer, Integer> capaState) {
        this.mActiveCapaState.putAll(capaState);
        updateDeviceConnectedState();
    }

    public synchronized int getBtProfileState(int profile) {
        if (this.mBtProfileState.containsKey(Integer.valueOf(profile))) {
            return this.mBtProfileState.get(Integer.valueOf(profile)).intValue();
        }
        return 0;
    }

    public synchronized HashMap<Integer, Integer> getBtProfileState() {
        return this.mBtProfileState;
    }

    public synchronized void addBtProfileState(int profile, int state) {
        this.mBtProfileState.put(Integer.valueOf(profile), Integer.valueOf(state));
    }

    public synchronized void addBtProfileState(HashMap<Integer, Integer> profileState) {
        this.mBtProfileState.putAll(profileState);
    }

    public synchronized String getUsbHidNode() {
        return this.mUsbHidNode;
    }

    public synchronized void setUsbHidNode(String node) {
        this.mUsbHidNode = node;
    }

    public synchronized String getIapNode() {
        return this.mIapNode;
    }

    public synchronized void setIapNode(String node) {
        this.mIapNode = node;
    }

    public synchronized String getUsbNcmNode() {
        return this.mUsbNcmNode;
    }

    public synchronized void setUsbNcmNode(String node) {
        this.mUsbNcmNode = node;
    }

    public synchronized int getAccepted() {
        return this.mAccepted;
    }

    public synchronized void setAccepted(int accepted) {
        this.mAccepted = accepted;
    }

    public synchronized int getDeviceType() {
        return this.mDeviceType;
    }

    public synchronized void setDeviceType(int deviceType) {
        this.mDeviceType = deviceType;
    }

    public synchronized int getLastActivateCapability() {
        return this.mLastActivateCapability;
    }

    public synchronized void setLastActivateCapability(int capability) {
        this.mLastActivateCapability = capability;
    }

    public synchronized boolean isInUsbDeviceMode() {
        return this.mInUsbDeviceMode;
    }

    public synchronized void setInUsbDeviceMode(boolean mode) {
        this.mInUsbDeviceMode = mode;
    }

    public synchronized boolean isInUsbAoaMode() {
        return this.mInUsbAoaMode;
    }

    public synchronized void setInUsbAoaMode(boolean inUsbAoaMode) {
        this.mInUsbAoaMode = inUsbAoaMode;
    }

    public synchronized int getAvailableCapability() {
        return this.mAvailableCapability;
    }

    public synchronized void setAvailableCapability(int availableCapability) {
        this.mCapability |= availableCapability;
        this.mAvailableCapability = availableCapability;
    }

    public synchronized void addAvailableCapability(int availableCapability) {
        this.mCapability |= availableCapability;
        this.mAvailableCapability |= availableCapability;
    }

    public synchronized void removeAvailableCapability(int capability) {
        this.mAvailableCapability &= ~capability;
    }

    public synchronized int getLastAvailableCapability() {
        return this.mLastAvailableCapability;
    }

    public synchronized void setLastAvailableCapability(int lastAvailableCapability) {
        this.mLastAvailableCapability = lastAvailableCapability;
    }

    public synchronized void removeLastAvailableCapability(int capability) {
        this.mLastAvailableCapability &= ~capability;
    }

    public synchronized void addLastAvailableCapability(int capability) {
        this.mLastAvailableCapability |= capability;
    }

    public synchronized void resetLastProjectionCapability() {
        int i = this.mLastAvailableCapability & (-4041);
        this.mLastAvailableCapability = i;
        this.mLastAvailableCapability = i | (this.mAvailableCapability & 4040);
    }

    public synchronized boolean isIsNeedRecoryBt() {
        return this.mIsNeedRecoryBt;
    }

    public synchronized void setIsNeedRecoryBt(boolean isNeedRecoryBt) {
        this.mIsNeedRecoryBt = isNeedRecoryBt;
    }

    public synchronized boolean hasProjectionCapability() {
        return (this.mAvailableCapability & 4040) > 0;
    }

    public synchronized int getAlwaysCapability() {
        return this.mAlwaysCapability;
    }

    public synchronized void setAlwaysCapability(int capa) {
        this.mAlwaysCapability = capa;
    }

    public synchronized UsbDevice getUsbDevice() {
        return this.mUsbDevice;
    }

    public synchronized void setUsbDevice(UsbDevice device) {
        this.mUsbDevice = device;
    }

    public boolean isCarplayFirstConn() {
        return this.mIsCarplayFirstConn;
    }

    public void setIsCarplayFirstConn(boolean isCarplayFirstConn) {
        this.mIsCarplayFirstConn = isCarplayFirstConn;
    }

    public boolean isIsCarlifeFirstConn() {
        return this.mIsCarlifeFirstConn;
    }

    public void setIsCarlifeFirstConn(boolean isCarlifeFirstConn) {
        this.mIsCarlifeFirstConn = isCarlifeFirstConn;
    }

    public boolean isAndroidAutoFirstConn() {
        return this.mIsAndroidAutoFirstConn;
    }

    public void setIsAndroidAutoFirstConn(boolean isAndroidAutoFirstConn) {
        this.mIsAndroidAutoFirstConn = isAndroidAutoFirstConn;
    }

    public boolean isWirelessAaRequested() {
        return this.mIsWirelessAaRequested;
    }

    public void setIsWirelessAaRequested(boolean isWirelessAaRequested) {
        this.mIsWirelessAaRequested = isWirelessAaRequested;
    }

    public boolean isProjectionFirstConnecting(int serviceType) {
        if (serviceType == 1) {
            boolean isFirst = this.mIsCarplayFirstConn;
            return isFirst;
        }
        if (serviceType == 2) {
            boolean isFirst2 = this.mIsCarlifeFirstConn;
            return isFirst2;
        }
        if (serviceType != 3) {
            return false;
        }
        boolean isFirst3 = this.mIsAndroidAutoFirstConn;
        return isFirst3;
    }

    public void setProjectionFirstConnStatus(int srType, boolean flag) {
        if (srType == 1) {
            this.mIsCarplayFirstConn = flag;
        } else if (srType == 2) {
            this.mIsCarlifeFirstConn = flag;
        } else if (srType == 3) {
            this.mIsAndroidAutoFirstConn = flag;
        }
    }

    public synchronized boolean isCpwlOverIapDetectedNotified() {
        return this.mIsCpwlOverIapDetectedNotified;
    }

    public synchronized void setCpwlOverIapDetectedNotified(boolean notified) {
        this.mIsCpwlOverIapDetectedNotified = notified;
    }

    public synchronized boolean isCpwlOverIapDetected() {
        return this.mIsCpwlOverIapDetected;
    }

    public synchronized void setCpwlOverIapDetected(boolean detected) {
        this.mIsCpwlOverIapDetected = detected;
    }

    public long getBtDevicePairedTime() {
        return this.mBtDevicePairedTime;
    }

    public void setBtDevicePairedTime(long btDevicePairedTime) {
        this.mBtDevicePairedTime = btDevicePairedTime;
    }

    public long getWirelessCarplayConnectedTime() {
        return this.mWirelessCarplayConnectedTime;
    }

    public void setWirelessCarplayConnectedTime(long wirelessCarplayConnectedTime) {
        this.mWirelessCarplayConnectedTime = wirelessCarplayConnectedTime;
    }

    public long getUsbCarplayConnectedTime() {
        return this.mUsbCarplayConnectedTime;
    }

    public void setUsbCarplayConnectedTime(long usbCarplayConnectedTime) {
        this.mUsbCarplayConnectedTime = usbCarplayConnectedTime;
    }

    public long getWirelessAndroidAutoConnectedTime() {
        return this.mWirelessAndroidAutoConnectedTime;
    }

    public void setWirelessAndroidAutoConnectedTime(long wirelessAndroidAutoConnectedTime) {
        this.mWirelessAndroidAutoConnectedTime = wirelessAndroidAutoConnectedTime;
    }

    public long getUsbAndroidAutoConnectedTime() {
        return this.mUsbAndroidAutoConnectedTime;
    }

    public void setUsbAndroidAutoConnectedTime(long usbAndroidAutoConnectedTime) {
        this.mUsbAndroidAutoConnectedTime = usbAndroidAutoConnectedTime;
    }

    public boolean isResetUsbDone() {
        return this.mResetUsbDone;
    }

    public void setResetUsbDone(boolean resetUsbDone) {
        this.mResetUsbDone = resetUsbDone;
    }

    public synchronized boolean isUsbOtgPort() {
        return this.mIsUsbOtgPort;
    }

    public synchronized void setUsbOtgFlag(boolean isUsbOtgPort) {
        this.mIsUsbOtgPort = isUsbOtgPort;
    }

    public int getUsbRoleSwitchPurpose() {
        return this.mUsbRoleSwitchPurpose;
    }

    public void setUsbRoleSwitchPurpose(int usbRoleSwitchPurpose) {
        this.mUsbRoleSwitchPurpose = usbRoleSwitchPurpose;
    }

    public void setIndexOfSameDeviceName(int indexOfSameDeviceName) {
        this.mIndexOfSameDeviceName = indexOfSameDeviceName;
    }

    public int getIndexOfSameDeviceName() {
        return this.mIndexOfSameDeviceName;
    }

    public boolean isDisconnectByUserAction() {
        return this.mIsDisconnectByUserAction;
    }

    public void setDisconnectByUserAction(boolean disconnectByUserAction) {
        this.mIsDisconnectByUserAction = disconnectByUserAction;
    }

    public String getMobileDeviceId() {
        return this.mMobileDeviceId;
    }

    public void setMobileDeviceId(String mobileDeviceId) {
        this.mMobileDeviceId = mobileDeviceId;
    }

    public boolean isProjectionRunning(int capa) {
        if ((getActiveCapability() & capa) <= 0) {
            return false;
        }
        int state = getActiveCapaState(capa);
        return state == 3 || state == 4;
    }

    public boolean isProjectionDisconnecting(int capa) {
        if ((getActiveCapability() & capa) <= 0) {
            return false;
        }
        int state = getActiveCapaState(capa);
        return state == 2;
    }

    public synchronized CmDevice getCmDevice() {
        CmDevice cmDev;
        cmDev = new CmDevice(getId());
        cmDev.setBtAddr(getBtAddr());
        cmDev.setUsbSerialNumber(getUsbSerialNumber());
        cmDev.setDeviceType(getDeviceType());
        cmDev.setAvailableCapability(getAvailableCapability());
        cmDev.setUsbMode(isInUsbDeviceMode() ? 1 : 0);
        cmDev.setIapNode(getIapNode());
        cmDev.setUsbHidNode(getUsbHidNode());
        cmDev.setUsbNcmNode(getUsbNcmNode());
        cmDev.setWifiAddr(getWifiAddr());
        cmDev.setWifiIpAddress(getWifiIp());
        cmDev.setIsPaired(isPaired());
        cmDev.setUsbDevice(getUsbDevice());
        return cmDev;
    }

    public synchronized ConnDevice getConnDevice() {
        ConnDevice connDev;
        connDev = new ConnDevice(getId());
        connDev.setConnectedTypeState(this.mConnectedTypeState);
        connDev.setFriendlyName(this.mFriendlyName);
        if (this.mIndexOfSameDeviceName > 1) {
            connDev.setFriendlyName(this.mFriendlyName + " " + this.mIndexOfSameDeviceName);
            StringBuilder sb = new StringBuilder();
            sb.append("getConnDevice, friendlyName: ");
            sb.append(connDev.getFriendlyName());
            Log.e(TAG, sb.toString());
        }
        connDev.setDeviceType(this.mDeviceType);
        connDev.setAccepted(this.mAccepted);
        connDev.setAvailableCapability(this.mAvailableCapability);
        connDev.setInUsbDeviceMode(this.mInUsbDeviceMode);
        connDev.setUsbNcmNode(this.mUsbNcmNode);
        connDev.setIapNode(this.mIapNode);
        connDev.setUsbHidNode(this.mUsbHidNode);
        connDev.setActiveCapability(this.mActiveCapability);
        connDev.setCapability(this.mCapability);
        connDev.setDeviceConnectedState(this.mDeviceConnectedState);
        connDev.setFavorite(this.mFavorite);
        connDev.setLastUptime(this.mLastUptime);
        connDev.setLastService(this.mLastService);
        connDev.setWifiAddr(this.mWifiAddr);
        connDev.setWifiIpAddress(this.mWifiIp);
        connDev.setBtAddr(this.mBtAddr);
        connDev.setUsbSerialNumber(this.mUsbSerialNumber);
        connDev.setActiveCapaState(this.mActiveCapaState);
        connDev.setAlwaysCapability(this.mAlwaysCapability);
        connDev.setBtProfileState(this.mBtProfileState);
        connDev.setIsPaired(this.mIsPaired);
        connDev.setBtDevicePairedTime(this.mBtDevicePairedTime);
        connDev.setWirelessCarplayConnectedTime(this.mWirelessCarplayConnectedTime);
        connDev.setUsbCarplayConnectedTime(this.mUsbCarplayConnectedTime);
        connDev.setUsbOtgFlag(this.mIsUsbOtgPort);
        connDev.setChangedMask(0L);
        connDev.setWirelessAndroidAutoConnectedTime(this.mWirelessAndroidAutoConnectedTime);
        connDev.setUsbAndroidAutoConnectedTime(this.mUsbAndroidAutoConnectedTime);
        connDev.setIsCarplayFirstConn(this.mIsCarplayFirstConn);
        connDev.setIsAndroidAutoFirstConn(this.mIsAndroidAutoFirstConn);
        connDev.setMobileDeviceId(getMobileDeviceId());
        return connDev;
    }

    public synchronized String toString() {
        return "Device{mId='" + LogEncrypt.IpAddressEncrypt(this.mId) + "', mFriendlyName='" + this.mFriendlyName + "', mDeviceType=" + this.mDeviceType + ", mAccepted=" + this.mAccepted + ", mDeviceConnectedState=" + this.mDeviceConnectedState + ", mConnectedTypeState=" + this.mConnectedTypeState + ", mUsbSerialNumber='" + LogEncrypt.IpAddressEncrypt(this.mUsbSerialNumber) + "', mBtAddr='" + LogEncrypt.MacAddressEncrypt(this.mBtAddr) + "', mWifiAddr='" + LogEncrypt.MacAddressEncrypt(this.mWifiAddr) + "', mWifiIp='" + LogEncrypt.IpAddressEncrypt(this.mWifiIp) + "', mLastUptime='" + this.mLastUptime + "', mLastService='" + this.mLastService + "', mFavorite=" + this.mFavorite + ", mIsNeedRecoryBt=" + this.mIsNeedRecoryBt + ", mCapability=" + this.mCapability + ", mAvailableCapability=" + this.mAvailableCapability + ", mLastAvailableCapability=" + this.mLastAvailableCapability + ", mActiveCapability=" + this.mActiveCapability + ", mLastActivateCapability=" + this.mLastActivateCapability + ", mActiveCapaState=" + this.mActiveCapaState + ", mBtProfileState=" + this.mBtProfileState + ", mInUsbDeviceMode=" + this.mInUsbDeviceMode + ", mUsbHidNode='" + this.mUsbHidNode + "', mIapNode='" + this.mIapNode + "', mUsbNcmNode='" + this.mUsbNcmNode + "', mIsPaired='" + this.mIsPaired + "', mIsCarplayFirstConn='" + this.mIsCarplayFirstConn + "', mIsCarlifeFirstConn='" + this.mIsCarlifeFirstConn + "', mIsAndroidAutoFirstConn='" + this.mIsAndroidAutoFirstConn + "', mIsWirelessAaRequested='" + this.mIsWirelessAaRequested + "', mQuickReconnectCapability='" + this.mQuickReconnectCapability + "', mInUsbAoaMode='" + this.mInUsbAoaMode + "', mBtDevicePairedTime='" + this.mBtDevicePairedTime + "', mWirelessCarplayConnectedTime='" + this.mWirelessCarplayConnectedTime + "', mUsbCarplayConnectedTime='" + this.mUsbCarplayConnectedTime + "', mWirelessAndroidAutoConnectedTime='" + this.mWirelessAndroidAutoConnectedTime + "', mUsbAndroidAutoConnectedTime='" + this.mUsbAndroidAutoConnectedTime + "', mResetUsbDone='" + this.mResetUsbDone + "', mIsUsbOtgPort ='" + this.mIsUsbOtgPort + "', mUsbRoleSwitchPurpose ='" + this.mUsbRoleSwitchPurpose + "', mIsCpwlOverIapDetected ='" + this.mIsCpwlOverIapDetected + "', mIndexOfSameDeviceName ='" + this.mIndexOfSameDeviceName + "', mIsDisconnectByUserAction ='" + this.mIsDisconnectByUserAction + "', mMobileDeviceId ='" + LogEncrypt.MacAddressEncrypt(this.mMobileDeviceId) + "'}";
    }
}

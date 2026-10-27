package ts.car.service.dcm.data;

import java.util.HashMap;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class ConnectionDevice extends Device {
    private int mAccepted;
    private int mActiveCapability;
    private int mAlwaysCapability;
    private int mAvailableCapability;
    private long mBtDevicePairedTime;
    private int mCapability;
    private int mDeviceConnectedState;
    private int mDeviceType;
    private boolean mFavorite;
    private String mFriendlyName;
    private String mIapNode;
    private boolean mLast;
    private int mLastService;
    private String mLastUptime;
    private String mMobileDeviceId;
    private long mUsbAndroidAutoConnectedTime;
    private long mUsbCarplayConnectedTime;
    private String mUsbHidNode;
    private String mUsbNcmNode;
    private long mWirelessAndroidAutoConnectedTime;
    private long mWirelessCarplayConnectedTime;
    private final HashMap<Integer, Integer> mConnectedTypeState = new HashMap<>();
    private HashMap<Integer, Integer> mActiveCapabilityState = new HashMap<>();
    private HashMap<Integer, Integer> mBtProfileState = new HashMap<>();
    private boolean mInUsbDeviceMode = false;
    private boolean mIsPaired = false;
    private boolean mIsUsbOtgPort = false;
    private boolean mIsCarplayFirstConn = true;
    private boolean mIsAndroidAutoFirstConn = true;

    private ConnectionDevice() {
    }

    public ConnectionDevice(String deviceId) {
        setDeviceId(deviceId);
    }

    public String getFriendlyName() {
        return this.mFriendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.mFriendlyName = friendlyName;
    }

    public int getAccepted() {
        return this.mAccepted;
    }

    public void setAccepted(int accepted) {
        this.mAccepted = accepted;
    }

    public String getLastUptime() {
        return this.mLastUptime;
    }

    public void setLastUptime(String lastUptime) {
        this.mLastUptime = lastUptime;
    }

    public int getLastService() {
        return this.mLastService;
    }

    public void setLastService(int lastService) {
        this.mLastService = lastService;
    }

    public boolean isLast() {
        return this.mLast;
    }

    public void setLast(boolean last) {
        this.mLast = last;
    }

    public boolean isFavorite() {
        return this.mFavorite;
    }

    public void setFavorite(boolean favorite) {
        this.mFavorite = favorite;
    }

    public int getDeviceConnectedState() {
        return this.mDeviceConnectedState;
    }

    public void setDeviceConnectedState(int state) {
        this.mDeviceConnectedState = state;
    }

    public int getCapability() {
        return this.mCapability;
    }

    public void setCapability(int capability) {
        this.mCapability = capability;
    }

    public boolean isAvailableCapability(int capability) {
        return (this.mAvailableCapability & capability) > 0;
    }

    public String getUsbHidNode() {
        return this.mUsbHidNode;
    }

    public void setUsbHidNode(String node) {
        this.mUsbHidNode = node;
    }

    public String getIapNode() {
        return this.mIapNode;
    }

    public void setIapNode(String node) {
        this.mIapNode = node;
    }

    public String getUsbNcmNode() {
        return this.mUsbNcmNode;
    }

    public void setUsbNcmNode(String node) {
        this.mUsbNcmNode = node;
    }

    public int getDeviceType() {
        return this.mDeviceType;
    }

    public void setDeviceType(int deviceType) {
        this.mDeviceType = deviceType;
    }

    public boolean isInUsbDeviceMode() {
        return this.mInUsbDeviceMode;
    }

    public void setInUsbDeviceMode(boolean mode) {
        this.mInUsbDeviceMode = mode;
    }

    public int getAvailableCapabilitys() {
        return this.mAvailableCapability;
    }

    public void setAvailableCapability(int availableCapability) {
        this.mAvailableCapability = availableCapability;
    }

    public int getActiveCapability() {
        return this.mActiveCapability;
    }

    public void setActiveCapability(int activeCapability) {
        this.mActiveCapability = activeCapability;
    }

    public boolean isActivatedCapability(int capability) {
        return (this.mActiveCapability & capability) > 0;
    }

    public int getAlwaysCapability() {
        return this.mAlwaysCapability;
    }

    public void setAlwaysCapability(int alwaysCapability) {
        this.mAlwaysCapability = alwaysCapability;
    }

    public synchronized boolean isIsPaired() {
        return this.mIsPaired;
    }

    public boolean isPaired() {
        return this.mIsPaired;
    }

    public void setIsPaired(boolean isPaired) {
        this.mIsPaired = isPaired;
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

    public boolean isUsbOtgPort() {
        return this.mIsUsbOtgPort;
    }

    public void setUsbOtgFlag(boolean isUsbOtgPort) {
        this.mIsUsbOtgPort = isUsbOtgPort;
    }

    public boolean isCarplayFirstConn() {
        return this.mIsCarplayFirstConn;
    }

    public void setIsCarplayFirstConn(boolean isCarplayFirstConn) {
        this.mIsCarplayFirstConn = isCarplayFirstConn;
    }

    public boolean isAndroidAutoFirstConn() {
        return this.mIsAndroidAutoFirstConn;
    }

    public void setIsAndroidAutoFirstConn(boolean isAndroidAutoFirstConn) {
        this.mIsAndroidAutoFirstConn = isAndroidAutoFirstConn;
    }

    public int getConnectedTypeState(int connectedType) {
        if (this.mConnectedTypeState.containsKey(Integer.valueOf(connectedType))) {
            return this.mConnectedTypeState.get(Integer.valueOf(connectedType)).intValue();
        }
        return 0;
    }

    public HashMap<Integer, Integer> getConnectedTypeState() {
        return this.mConnectedTypeState;
    }

    public void setConnectedTypeState(HashMap<Integer, Integer> state) {
        this.mConnectedTypeState.clear();
        this.mConnectedTypeState.putAll(state);
    }

    public boolean isOnline() {
        int i;
        return (this.mConnectedTypeState.size() <= 0 || (i = this.mDeviceConnectedState) == 3 || i == 2 || i == 4) ? false : true;
    }

    public int getActiveCapabilityState(int capability) {
        if (this.mActiveCapabilityState.containsKey(Integer.valueOf(capability))) {
            return this.mActiveCapabilityState.get(Integer.valueOf(capability)).intValue();
        }
        return 0;
    }

    public HashMap<Integer, Integer> getActiveCapabilityState() {
        return this.mActiveCapabilityState;
    }

    public void setActiveCapaState(HashMap<Integer, Integer> capabilityState) {
        this.mActiveCapabilityState.clear();
        this.mActiveCapabilityState.putAll(capabilityState);
    }

    public int getBtProfileState(int profile) {
        if (this.mBtProfileState.containsKey(Integer.valueOf(profile))) {
            return this.mBtProfileState.get(Integer.valueOf(profile)).intValue();
        }
        return 4;
    }

    public HashMap<Integer, Integer> getBtProfileState() {
        return this.mBtProfileState;
    }

    public void setBtProfileState(HashMap<Integer, Integer> profileState) {
        this.mBtProfileState.clear();
        this.mBtProfileState.putAll(profileState);
    }

    public String getMobileDeviceId() {
        return this.mMobileDeviceId;
    }

    public void setMobileDeviceId(String mobileDeviceId) {
        this.mMobileDeviceId = mobileDeviceId;
    }

    @Override // ts.car.service.dcm.data.Device
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConnectionDevice that = (ConnectionDevice) o;
        return Objects.equals(getDeviceId(), that.getDeviceId());
    }

    @Override // ts.car.service.dcm.data.Device
    public int hashCode() {
        return getDeviceId().hashCode();
    }

    @Override // ts.car.service.dcm.data.Device
    public String toString() {
        return "ConnectionDevice{mConnectedTypeState=" + this.mConnectedTypeState + ", mDevId='" + LogEncrypt.IpAddressEncrypt(getDeviceId()) + "', mFriendlyName='" + this.mFriendlyName + "', mAccepted=" + this.mAccepted + ", mDeviceType=" + this.mDeviceType + ", mDeviceConnectedState=" + this.mDeviceConnectedState + ", mLastUptime='" + this.mLastUptime + "', mLastService='" + this.mLastService + "', mLast=" + this.mLast + ", mFavorite=" + this.mFavorite + ", mCapability=" + this.mCapability + ", mAvailableCapability=" + this.mAvailableCapability + ", mActiveCapability=" + this.mActiveCapability + ", mActiveCapabilityState=" + this.mActiveCapabilityState + ", mAlwaysCapability=" + this.mAlwaysCapability + ", mBtProfileState=" + this.mBtProfileState + ", mInUsbDeviceMode=" + this.mInUsbDeviceMode + ", mUsbHidNode='" + this.mUsbHidNode + "', mIapNode='" + this.mIapNode + "', mUsbNcmNode='" + this.mUsbNcmNode + "', mIsPaired='" + this.mIsPaired + "', mBtDevicePairedTime='" + this.mBtDevicePairedTime + "', mWirelessCarplayConnectedTime='" + this.mWirelessCarplayConnectedTime + "', mUsbCarplayConnectedTime='" + this.mUsbCarplayConnectedTime + "', mIsUsbOtgPort ='" + this.mIsUsbOtgPort + "', mIsCarplayFirstConn ='" + this.mIsCarplayFirstConn + "', mIsAndroidAutoFirstConn ='" + this.mIsAndroidAutoFirstConn + "', mWirelessAndroidAutoConnectedTime='" + this.mWirelessAndroidAutoConnectedTime + "', mUsbAndroidAutoConnectedTime='" + this.mUsbAndroidAutoConnectedTime + "', mMobileDeviceId=" + LogEncrypt.MacAddressEncrypt(this.mMobileDeviceId) + "'}";
    }
}

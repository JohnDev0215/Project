package com.ts.car.sdk.dm.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.ts.appservice.power.usb.UsbManager;
import com.ts.car.sdk.dm.data.CommonConstants;

/* JADX INFO: loaded from: classes.dex */
public class DmDevice implements Parcelable {
    public static final int ADD = 1;
    public static final Parcelable.Creator<DmDevice> CREATOR = new Parcelable.Creator<DmDevice>() { // from class: com.ts.car.sdk.dm.data.DmDevice.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DmDevice createFromParcel(Parcel source) {
            DmDevice info = new DmDevice(source.readString());
            info.mFriendlyName = source.readString();
            info.mCarPlayProjectionState = source.readByte();
            info.mCarlifeProjectionState = source.readByte();
            info.mAndroidAutoProjectionState = source.readByte();
            info.mHicarProjectionState = source.readByte();
            info.mHfpState = source.readByte();
            info.mA2dpState = source.readByte();
            info.mIsPaired = source.readBoolean();
            info.mMacAddress = source.readString();
            info.mOptAction = source.readByte();
            info.mUsbSerialNumber = source.readString();
            info.mLastProfile = source.readByte();
            info.mLastProfileTimeMillis = source.readLong();
            info.mIndexOfSameDeviceName = source.readByte();
            return info;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DmDevice[] newArray(int size) {
            return new DmDevice[size];
        }
    };
    public static final int DELETE = 2;
    public static final int UPDATE = 0;
    private volatile boolean mDeleted;
    private final String mDeviceUuid;
    private volatile String mFriendlyName;
    private volatile boolean mIsPaired;
    private volatile long mLastProfileTimeMillis;
    private volatile String mMacAddress;
    private volatile int mOptAction;
    private int mProjectionConnectType;
    private boolean mUsbDevice;
    private volatile String mUsbSerialNumber;
    private volatile int mCarPlayProjectionState = 0;
    private volatile int mCarlifeProjectionState = 0;
    private volatile int mAndroidAutoProjectionState = 0;
    private volatile int mHicarProjectionState = 0;
    private volatile int mHfpState = 0;
    private volatile int mA2dpState = 0;
    private volatile int mLastProfile = 0;
    private int mIndexOfSameDeviceName = -1;

    public DmDevice(String deviceUuid) {
        this.mDeviceUuid = deviceUuid;
    }

    public String getDeviceUuid() {
        return this.mDeviceUuid;
    }

    public String getFriendlyName() {
        return this.mFriendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        if (isFriendlyNameChanged(this.mFriendlyName, friendlyName)) {
            this.mIndexOfSameDeviceName = -1;
        }
        this.mFriendlyName = friendlyName;
    }

    private boolean isFriendlyNameChanged(String oldName, String newName) {
        return (newName == null || newName.equals(oldName)) ? false : true;
    }

    public void setIndexOfSameDeviceName(int indexOfSameDeviceName) {
        this.mIndexOfSameDeviceName = indexOfSameDeviceName;
    }

    public int getIndexOfSameDeviceName() {
        return this.mIndexOfSameDeviceName;
    }

    public boolean isPaired() {
        return this.mIsPaired;
    }

    public void setPaired(boolean isPaired) {
        this.mIsPaired = isPaired;
    }

    public void setCarPlayProjectionState(int state) {
        this.mCarPlayProjectionState = state;
        setLastProfile(1, state);
    }

    public int getCarPlayProjectionState() {
        return this.mCarPlayProjectionState;
    }

    public void setCarlifeProjectionState(int state) {
        this.mCarlifeProjectionState = state;
        setLastProfile(3, state);
    }

    public int getCarlifeProjectionState() {
        return this.mCarlifeProjectionState;
    }

    public void setAndroidAutoProjectionState(int state) {
        this.mAndroidAutoProjectionState = state;
        setLastProfile(2, state);
    }

    public int getAndroidAutoProjectionState() {
        return this.mAndroidAutoProjectionState;
    }

    public void setHicarProjectionState(int state) {
        this.mHicarProjectionState = state;
        setLastProfile(4, state);
    }

    public int getHicarProjectionState() {
        return this.mHicarProjectionState;
    }

    public void setHfpState(int state) {
        this.mHfpState = state;
        setLastProfile(5, state);
    }

    public int getHfpState() {
        return this.mHfpState;
    }

    public void setA2dpState(int state) {
        this.mA2dpState = state;
        setLastProfile(6, state);
    }

    public int getA2dpState() {
        return this.mA2dpState;
    }

    public void setMacAddress(String mac) {
        this.mMacAddress = mac;
    }

    public String getMacAddress() {
        return this.mMacAddress;
    }

    public String getUsbSerialNumber() {
        return this.mUsbSerialNumber;
    }

    public void setUsbSerialNumber(String usbSerialNumber) {
        this.mUsbSerialNumber = usbSerialNumber;
    }

    public void setOptAction(int optAction) {
        this.mOptAction = optAction;
    }

    public int getOptAction() {
        return this.mOptAction;
    }

    public void setLastProfile(int profile) {
        this.mLastProfile = profile;
    }

    private void setLastProfile(int profile, int state) {
        if (state == 4) {
            this.mLastProfile = profile;
        }
    }

    public int getLastProfile() {
        return this.mLastProfile;
    }

    public long getLastProfileTimeMillis() {
        return this.mLastProfileTimeMillis;
    }

    public void setLastProfileTimeMillis(long time) {
        this.mLastProfileTimeMillis = time;
    }

    public void setDelete(boolean delete) {
        this.mDeleted = delete;
    }

    public boolean isDeleted() {
        return this.mDeleted;
    }

    public void setUsbDevice(boolean isUsbDevice) {
        this.mUsbDevice = isUsbDevice;
    }

    public boolean isUsbDevice() {
        return this.mUsbDevice;
    }

    public void setProjectionConnectType(int projectionConnectType) {
        this.mProjectionConnectType = projectionConnectType;
    }

    public int getProjectionConnectType() {
        return this.mProjectionConnectType;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mDeviceUuid);
        dest.writeString(this.mFriendlyName);
        dest.writeByte((byte) this.mCarPlayProjectionState);
        dest.writeByte((byte) this.mCarlifeProjectionState);
        dest.writeByte((byte) this.mAndroidAutoProjectionState);
        dest.writeByte((byte) this.mHicarProjectionState);
        dest.writeByte((byte) this.mHfpState);
        dest.writeByte((byte) this.mA2dpState);
        dest.writeBoolean(this.mIsPaired);
        dest.writeString(this.mMacAddress);
        dest.writeByte((byte) this.mOptAction);
        dest.writeString(this.mUsbSerialNumber);
        dest.writeByte((byte) this.mLastProfile);
        dest.writeLong(this.mLastProfileTimeMillis);
        dest.writeByte((byte) this.mIndexOfSameDeviceName);
    }

    public int hashCode() {
        String str = this.mDeviceUuid;
        if (str == null) {
            str = UsbManager.USB_DISABLE;
        }
        return str.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DmDevice other = (DmDevice) obj;
        if (!TextUtils.isEmpty(getDeviceUuid()) && !TextUtils.isEmpty(other.getDeviceUuid()) && getDeviceUuid().equals(other.getDeviceUuid())) {
            return true;
        }
        if (!TextUtils.isEmpty(getUsbSerialNumber()) && !TextUtils.isEmpty(other.getUsbSerialNumber()) && getUsbSerialNumber().equals(other.getUsbSerialNumber())) {
            return true;
        }
        if (TextUtils.isEmpty(getMacAddress()) || TextUtils.isEmpty(other.getMacAddress())) {
            return false;
        }
        return getMacAddress().equals(other.getMacAddress());
    }

    private String getProjectionCapabilityString() {
        return "[mCarPlayProjectionState:" + CommonConstants.CapabilityState.getStateString(this.mCarPlayProjectionState) + ", mCarlifeProjectionState:" + CommonConstants.CapabilityState.getStateString(this.mCarlifeProjectionState) + ", mAndroidAutoProjectionState:" + CommonConstants.CapabilityState.getStateString(this.mAndroidAutoProjectionState) + ", mHicarProjectionState:" + CommonConstants.CapabilityState.getStateString(this.mHicarProjectionState) + "]";
    }

    public String toString() {
        return "DmDevice[mDeviceUuid:" + this.mDeviceUuid + ", mFriendlyName:" + this.mFriendlyName + ", mMacAddress:" + this.mMacAddress + ", mUsbSerialNumber:" + this.mUsbSerialNumber + ", mOptAction:" + this.mOptAction + ", mLastProfile:" + CommonConstants.Capability.getCapabilityString(this.mLastProfile) + ", mDeleted:" + this.mDeleted + ", mLastProfileTimeMillis:" + this.mLastProfileTimeMillis + ", mProjectionCapability:" + getProjectionCapabilityString() + ", mIsPaired:" + isPaired() + ", mUsbDevice:" + isUsbDevice() + ", mProjectionConnectType" + this.mProjectionConnectType + ", mHfpState:" + CommonConstants.CapabilityState.getStateString(this.mHfpState) + ", mA2dpState:" + CommonConstants.CapabilityState.getStateString(this.mA2dpState) + ", mIndexOfSameDeviceName:" + this.mIndexOfSameDeviceName + "]";
    }

    public synchronized boolean isCapabilityConnected(int type) {
        return checkCapabilityState(type, 4);
    }

    public synchronized boolean isConnectingProjection() {
        boolean z;
        z = true;
        if (!checkCapabilityState(1, 3) && !checkCapabilityState(2, 3) && !checkCapabilityState(3, 3)) {
            if (!checkCapabilityState(4, 3)) {
                z = false;
            }
        }
        return z;
    }

    public synchronized void setCapabilityState(int type, int state) {
        switch (type) {
            case 1:
                setCarPlayProjectionState(state);
                break;
            case 2:
                setAndroidAutoProjectionState(state);
                break;
            case 3:
                setCarlifeProjectionState(state);
                break;
            case 4:
                setHicarProjectionState(state);
                break;
            case 5:
                setHfpState(state);
                break;
            case 6:
                setA2dpState(state);
                break;
        }
    }

    public synchronized int getCapabilityState(int type) {
        switch (type) {
            case 1:
                return getCarPlayProjectionState();
            case 2:
                return getAndroidAutoProjectionState();
            case 3:
                return getCarlifeProjectionState();
            case 4:
                return getHicarProjectionState();
            case 5:
                return getHfpState();
            case 6:
                return getA2dpState();
            default:
                return 0;
        }
    }

    public synchronized boolean isDisconnectingProjection() {
        boolean z;
        z = true;
        if (!checkCapabilityState(1, 5) && !checkCapabilityState(2, 5) && !checkCapabilityState(3, 5)) {
            if (!checkCapabilityState(4, 5)) {
                z = false;
            }
        }
        return z;
    }

    public synchronized boolean isProjectionConnected() {
        boolean z;
        z = true;
        if (!checkCapabilityState(1, 4) && !checkCapabilityState(2, 4) && !checkCapabilityState(3, 4)) {
            if (!checkCapabilityState(4, 4)) {
                z = false;
            }
        }
        return z;
    }

    public boolean isProjectionInvalid() {
        return checkCapabilityState(1, 0) && checkCapabilityState(2, 0) && checkCapabilityState(3, 0) && checkCapabilityState(4, 0);
    }

    public boolean isDeviceInProjection() {
        return isUsingProjection(getAndroidAutoProjectionState()) || isUsingProjection(getCarPlayProjectionState()) || isUsingProjection(getCarlifeProjectionState()) || isUsingProjection(getHicarProjectionState());
    }

    private boolean isUsingProjection(int projectionState) {
        return (projectionState == 0 || projectionState == 1 || projectionState == 2) ? false : true;
    }

    public boolean checkCapabilityState(int type, int state) {
        switch (type) {
            case 1:
                if (this.mCarPlayProjectionState != state) {
                    break;
                }
                break;
            case 2:
                if (this.mAndroidAutoProjectionState != state) {
                    break;
                }
                break;
            case 3:
                if (this.mCarlifeProjectionState != state) {
                    break;
                }
                break;
            case 4:
                if (this.mHicarProjectionState != state) {
                    break;
                }
                break;
            case 5:
                if (this.mHfpState != state) {
                    break;
                }
                break;
            case 6:
                if (this.mA2dpState != state) {
                    break;
                }
                break;
        }
        return false;
    }
}

package com.ts.dm.lib.data;

import com.ts.car.sdk.dm.data.CommonConstants;

/* JADX INFO: loaded from: classes.dex */
public class DeviceRecord {
    private String mFriendlyName;
    private volatile boolean mIsPaired;
    private long mLastProfileTimeMillis;
    private String mMacAddress;
    private int mLastProfile = 0;
    private int mCarPlayProjectionState = 0;
    private int mCarlifeProjectionState = 0;
    private int mAndroidAutoProjectionState = 0;
    private int mHicarProjectionState = 0;
    private int mIndexOfSameDeviceName = -1;

    public void setMacAddress(String mac) {
        this.mMacAddress = mac;
    }

    public String getMacAddress() {
        return this.mMacAddress;
    }

    public void setFriendlyName(String name) {
        this.mFriendlyName = name;
    }

    public String getFriendlyName() {
        return this.mFriendlyName;
    }

    public void setLastProfile(int profile) {
        this.mLastProfile = profile;
    }

    public int getLastProfile() {
        return this.mLastProfile;
    }

    public long getLastProfileTimeMillis() {
        return this.mLastProfileTimeMillis;
    }

    public void setLastProfileTimeMillis(long timeMillis) {
        this.mLastProfileTimeMillis = timeMillis;
    }

    public synchronized void setCarPlayProjectionState(int state) {
        this.mCarPlayProjectionState = state;
    }

    public int getCarPlayProjectionState() {
        return this.mCarPlayProjectionState;
    }

    public synchronized void setCarlifeProjectionState(int state) {
        this.mCarlifeProjectionState = state;
    }

    public int getCarlifeProjectionState() {
        return this.mCarlifeProjectionState;
    }

    public synchronized void setAndroidAutoProjectionState(int state) {
        this.mAndroidAutoProjectionState = state;
    }

    public int getAndroidAutoProjectionState() {
        return this.mAndroidAutoProjectionState;
    }

    public synchronized void setHicarProjectionState(int state) {
        this.mHicarProjectionState = state;
    }

    public int getHicarProjectionState() {
        return this.mHicarProjectionState;
    }

    public boolean isPaired() {
        return this.mIsPaired;
    }

    public void setPaired(boolean isPaired) {
        this.mIsPaired = isPaired;
    }

    public void setIndexOfSameDeviceName(int indexOfSameDeviceName) {
        this.mIndexOfSameDeviceName = indexOfSameDeviceName;
    }

    public int getIndexOfSameDeviceName() {
        return this.mIndexOfSameDeviceName;
    }

    public String toString() {
        return "DeviceRecord[mMacAddress:" + this.mMacAddress + ", mFriendlyName:" + this.mFriendlyName + ", mLastProfile:" + CommonConstants.Capability.getCapabilityString(this.mLastProfile) + ", mLastProfileTimeMillis:" + this.mLastProfileTimeMillis + ", mIsPaired:" + this.mIsPaired + ", mIndexOfSameDeviceName:" + this.mIndexOfSameDeviceName + ", mProjectionCapability:" + getProjectionCapabilityString() + "]";
    }

    private String getProjectionCapabilityString() {
        return "[mCarPlayProjectionState:" + CommonConstants.CapabilityState.getStateString(this.mCarPlayProjectionState) + ", mCarlifeProjectionState:" + CommonConstants.CapabilityState.getStateString(this.mCarlifeProjectionState) + ", mAndroidAutoProjectionState:" + CommonConstants.CapabilityState.getStateString(this.mAndroidAutoProjectionState) + ", mHicarProjectionState:" + CommonConstants.CapabilityState.getStateString(this.mHicarProjectionState) + "]";
    }
}

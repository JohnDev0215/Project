package com.ts.androidauto.fsdk.data;

import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class VehicleFuleData {
    public static final int MAX_FUEL_STATUS_VALUE = 100;
    private int mFuleStatus = 100;
    private boolean mLowFuleStatus = false;
    private int mRemainingDrivingRange = 0;

    public int getFuleStatus() {
        return this.mFuleStatus;
    }

    public boolean getLowFuleStatus() {
        return this.mLowFuleStatus;
    }

    public int getRemainingDrivingRange() {
        return this.mRemainingDrivingRange;
    }

    public void setFuleStatus(int status) {
        this.mFuleStatus = status;
    }

    public void setLowFuleStatus(boolean warning) {
        this.mLowFuleStatus = warning;
    }

    public void setRemainingDrivingRange(int range) {
        this.mRemainingDrivingRange = range;
    }

    public void setFuelData(VehicleFuleData value) {
        this.mFuleStatus = value.getFuleStatus();
        this.mLowFuleStatus = value.getLowFuleStatus();
        this.mRemainingDrivingRange = value.getRemainingDrivingRange();
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mFuleStatus), Boolean.valueOf(this.mLowFuleStatus), Integer.valueOf(this.mRemainingDrivingRange));
    }

    public boolean equals(Object other) {
        if (other == null || !(other instanceof VehicleFuleData)) {
            return false;
        }
        VehicleFuleData otherObj = (VehicleFuleData) other;
        if (this.mFuleStatus != otherObj.getFuleStatus() || this.mLowFuleStatus != otherObj.getLowFuleStatus() || this.mRemainingDrivingRange != otherObj.getRemainingDrivingRange()) {
            return false;
        }
        return true;
    }
}

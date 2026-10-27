package com.engine.tbox.data;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class OtaFactoryNotification extends OtaNotificationBase {
    public static final int SLICE_UPGRADE_TYPE = 1;
    public static final int UPGRADE_TYPE = 0;
    private int timeout = 0;
    private int upgradeType = 0;
    private int minSoc = 0;
    private int minVoltage = 0;

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getUpgradeType() {
        return this.upgradeType;
    }

    public void setUpgradeType(int upgradeType) {
        this.upgradeType = upgradeType;
    }

    public int getMinSoc() {
        return this.minSoc;
    }

    public void setMinSoc(int minSoc) {
        this.minSoc = minSoc;
    }

    public int getMinVoltage() {
        return this.minVoltage;
    }

    public void setMinVoltage(int minVoltage) {
        this.minVoltage = minVoltage;
    }

    public String toString() {
        return "OtaFactoryNotification [timeout=" + this.timeout + ", upgradeType=" + this.upgradeType + ", minSoc=" + this.minSoc + ", minVoltage=" + this.minVoltage + ", mUuidString=" + this.mUuidString + ", mUuidArray=" + Arrays.toString(this.mUuidArray) + "]";
    }
}

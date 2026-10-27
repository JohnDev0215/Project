package com.engine.tbox.data;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class OtaPhoneOtaNotification extends OtaNotificationBase {
    private long timestamp;
    private int upgradeType;

    public int getUpgradeType() {
        return this.upgradeType;
    }

    public void setUpgradeType(int upgradeType) {
        this.upgradeType = upgradeType;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String toString() {
        return "OtaPhoneOtaNotification [mUuidString=" + this.mUuidString + ", upgradeType=" + this.upgradeType + ", timestamp=" + this.timestamp + ", mUuidArray=" + Arrays.toString(this.mUuidArray) + "]";
    }
}

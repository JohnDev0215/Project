package com.micphone.bean;

import android.util.Log;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class TsBydMicInfo {
    private static final String LINE_FEED = "\n";
    private static final String PRODUCT_NAME_KEY = "麦克风名称：";
    private static final String SERIAL_NUMBER_KEY = "序列号：";
    private static final String VENDOR_NAME_KEY = "VendorID：";
    private static final String VERSION_KEY = "固件版本：";
    public int battery;
    public boolean connectState;
    public long connectTime;
    public long disConnectTime;
    public boolean isCanUpgrade;
    public boolean isHasNewVersion;
    public int newSubId;
    public int oldSubId;
    public int pairType;
    public String productName;
    public String serialNumber;
    public int supplier;
    public int upgradeProcess;
    public MicUpResult upgradeResult;
    public String vendorName;
    public String version;

    public enum MicUpResult {
        NoNeed,
        Start,
        Success,
        Fail
    }

    public String getFormatName(String str) {
        return String.format(Locale.getDefault(), "%04x", Integer.valueOf(Integer.parseInt(str)));
    }

    public String infoCollection() {
        Log.d("TsBydMicInfo", "infoCollection " + toString());
        return PRODUCT_NAME_KEY + this.productName + LINE_FEED + VENDOR_NAME_KEY + this.vendorName + LINE_FEED + SERIAL_NUMBER_KEY + this.serialNumber + LINE_FEED + VERSION_KEY + this.version;
    }

    public String toString() {
        return "TsBydMicInfo{newSubId=" + this.newSubId + ", oldSubId=" + this.oldSubId + ", productName='" + this.productName + "', vendorName='" + this.vendorName + "', serialNumber='" + this.serialNumber + "', version='" + this.version + "', battery=" + this.battery + ", isHasNewVersion=" + this.isHasNewVersion + ", isCanUpgrade=" + this.isCanUpgrade + ", upgradeResult=" + this.upgradeResult + ", upgradeProcess=" + this.upgradeProcess + ", pairType=" + this.pairType + ", connectTime=" + this.connectTime + ", disConnectTime=" + this.disConnectTime + ", supplier=" + this.supplier + ", connectState=" + this.connectState + '}';
    }
}

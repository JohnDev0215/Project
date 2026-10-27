package com.micphone.bean;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class TsBydDongleInfo {
    private static final String LINE_FEED = "\n";
    private static final String PRODUCT_NAME_KEY = "设备名：";
    private static final String SERIAL_NUMBER_KEY = "序列号：";
    private static final String VENDOR_NAME_KEY = "厂商名：";
    private static final String VERSION_KEY = "固件版本：";
    public boolean hasNewVersion;
    public String productName;
    public String serialNumber;
    public int upgradeCode;
    public int upgradeProcess;
    public DongleUpResult upgradeResult;
    public String vendorName;
    public String version;

    public enum DongleUpResult {
        NoNeed,
        Start,
        Success,
        Fail
    }

    public String infoCollection() {
        Log.d("TsBydDongleInfo", "infoCollection " + toString());
        return PRODUCT_NAME_KEY + this.productName + LINE_FEED + VENDOR_NAME_KEY + this.vendorName + LINE_FEED + SERIAL_NUMBER_KEY + this.serialNumber + LINE_FEED + VERSION_KEY + this.version;
    }

    public String toString() {
        return "TsBydDongleInfo{productName='" + this.productName + "', vendorName='" + this.vendorName + "', serialNumber='" + this.serialNumber + "', version='" + this.version + "', hasNewVersion=" + this.hasNewVersion + ", upgradeResult=" + this.upgradeResult + ", upgradeCode=" + this.upgradeCode + ", upgradeProcess=" + this.upgradeProcess + '}';
    }
}

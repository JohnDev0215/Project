package com.byd.systemsettings.wifi2.model;

/* JADX INFO: loaded from: classes2.dex */
public enum WifiFreqBand {
    UNKNOW("UNKNOW"),
    G5("5GHZ"),
    G2_4("2.4GHZ");

    private String value;

    WifiFreqBand(String str) {
        this.value = str;
    }

    public static WifiFreqBand getType(String str) {
        for (WifiFreqBand wifiFreqBand : values()) {
            if (wifiFreqBand.name().equals(str)) {
                return wifiFreqBand;
            }
        }
        return UNKNOW;
    }
}

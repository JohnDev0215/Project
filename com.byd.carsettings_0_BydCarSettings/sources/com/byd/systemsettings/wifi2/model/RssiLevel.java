package com.byd.systemsettings.wifi2.model;

/* JADX INFO: loaded from: classes.dex */
public enum RssiLevel {
    LEVEL_0,
    LEVEL_1,
    LEVEL_2,
    LEVEL_3,
    LEVEL_4;

    public static RssiLevel getWifi(int i) {
        return i <= -100 ? LEVEL_0 : (i <= -100 || i > -88) ? (i <= -88 || i > -77) ? (i <= -77 || i > -55) ? LEVEL_4 : LEVEL_3 : LEVEL_2 : LEVEL_1;
    }
}

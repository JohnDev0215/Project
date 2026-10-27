package com.byd.systemsettings.wifi2.model;

/* JADX INFO: loaded from: classes2.dex */
public enum RssiLevel {
    LEVEL_0,
    LEVEL_1,
    LEVEL_2,
    LEVEL_3,
    LEVEL_4;

    public static RssiLevel getWifi(int i8) {
        return i8 <= -100 ? LEVEL_0 : (i8 <= -100 || i8 > -88) ? (i8 <= -88 || i8 > -77) ? (i8 <= -77 || i8 > -55) ? LEVEL_4 : LEVEL_3 : LEVEL_2 : LEVEL_1;
    }
}

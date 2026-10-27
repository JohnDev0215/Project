package com.ecarx.xui.adaptapi.vehicle.dashboard;

/* JADX INFO: loaded from: classes2.dex */
public interface ISafeBeltInfo {
    public static final int LF_SAFE_BELT = 1;
    public static final int LR_SAFE_BELT = 2;
    public static final int RF_SAFE_BELT = 3;
    public static final int RR_SAFE_BELT = 4;

    boolean isSafeBeltLocked(int i);
}

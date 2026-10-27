package com.ecarx.xui.adaptapi.vehicle.dashboard;

/* JADX INFO: loaded from: classes2.dex */
public interface ITpmsInfo {
    public static final int LF_TIRE = 1;
    public static final int LR_TIRE = 2;
    public static final int RF_TIRE = 3;
    public static final int RR_TIRE = 4;

    double getDetailTemperature(int i);

    float getPressure(int i);

    int getTemperature(int i);
}

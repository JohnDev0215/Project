package com.android.car;

/* JADX INFO: loaded from: classes3.dex */
public class CarStatsLogHelper {
    public static void logPowerState(int state) {
        CarStatsLog.write(203, state);
    }

    public static void logGarageModeStart() {
        CarStatsLog.write(CarStatsLog.GARAGE_MODE_INFO, true);
    }

    public static void logGarageModeStop() {
        CarStatsLog.write(CarStatsLog.GARAGE_MODE_INFO, false);
    }
}

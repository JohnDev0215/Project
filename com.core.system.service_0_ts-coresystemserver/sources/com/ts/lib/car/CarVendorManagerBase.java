package com.ts.lib.car;

import android.os.SystemClock;

/* JADX INFO: loaded from: classes.dex */
public abstract class CarVendorManagerBase {
    public static boolean DEBUG = true;
    public static final int ERROR_TWO = 2;
    public static final int LOG_FREQUENCY = 10;
    public static final float NS2MS = 1000000.0f;
    public static final String PERSIST_DEBUG_LOGGER_BASE = "persist.car.vendor.log.base";
    public static final String STATISTICS_TAG = "CAR.VENDOR.DATA_STATISTICS";
    public Car mEmbeddedCar;
    private long mCallbackCount = 0;
    private long mSdkRunTimeStatistics = 0;
    private long mSdkCallbackTimeStatistics = 0;
    private long mCurrentTimeNanos = SystemClock.elapsedRealtimeNanos();

    public void traceCallbackPrepare() {
        if (this.mCallbackCount % 10 == 0) {
            Log.debug(STATISTICS_TAG, genDateStatistics());
        }
        this.mCallbackCount++;
        this.mCurrentTimeNanos = SystemClock.elapsedRealtimeNanos();
    }

    public void traceCallbackBefore() {
        this.mSdkRunTimeStatistics += SystemClock.elapsedRealtimeNanos() - this.mCurrentTimeNanos;
        this.mCurrentTimeNanos = SystemClock.elapsedRealtimeNanos();
    }

    public void traceCallbackEnd() {
        this.mSdkCallbackTimeStatistics += SystemClock.elapsedRealtimeNanos() - this.mCurrentTimeNanos;
    }

    public String tenToHex(int valueTen) {
        String strHex = Integer.toHexString(valueTen);
        return strHex;
    }

    private String genDateStatistics() {
        return "STATISTICS mCallbackCount: " + this.mCallbackCount + " mSdkRunTime: " + (this.mSdkRunTimeStatistics / 1000000.0f) + "ms mSdkCallbackTime: " + (this.mSdkCallbackTimeStatistics / 1000000.0f) + "ms";
    }
}

package com.ecarx.xui.adaptapi.vehicle.ems;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IEms {
    public static final int ENGINE_RUNNING_STATUS_CRANKING = 2;
    public static final int ENGINE_RUNNING_STATUS_RUNNING = 3;
    public static final int ENGINE_RUNNING_STATUS_START_STOP = 4;
    public static final int ENGINE_RUNNING_STATUS_STOP = 1;
    public static final int ENGINE_STATUS_INVALID = Integer.MIN_VALUE;

    @Retention(RetentionPolicy.SOURCE)
    public @interface EngineRunningStatus {
    }

    public interface IEmsObserver {
        void onEngineRunningStatusChanged(int i);

        void onEngineSpeedChanged(int i);
    }

    boolean attachEmsObserver(IEmsObserver iEmsObserver);

    void detachEmsObserver(IEmsObserver iEmsObserver);

    float getAntifreezeCoolantTemp();

    float getEngineOilPressure();

    int getEngineRunningStatus();

    int getEngineSpeed();

    float getTransmissionTemp();
}

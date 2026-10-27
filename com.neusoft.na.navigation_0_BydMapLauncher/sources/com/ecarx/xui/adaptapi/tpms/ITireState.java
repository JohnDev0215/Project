package com.ecarx.xui.adaptapi.tpms;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface ITireState {
    public static final int WARNING_LEVEL_HIGH_PRESSURE = 1;
    public static final int WARNING_LEVEL_HIGH_TMP = 4;
    public static final int WARNING_LEVEL_LOW_PRESSURE = 2;
    public static final int WARNING_LEVEL_NO_WARN = 0;
    public static final int WARNING_LEVEL_SENSOR_FAULT = 3;
    public static final int WARNING_LEVEL_SYS_FAULT = 5;

    @Retention(RetentionPolicy.SOURCE)
    public @interface TireWarning {
    }

    double getDetailTemperature();

    float getPressure();

    @Deprecated
    int getTemperature();

    int getTireWarning();

    boolean hasPressureWarning();

    boolean isQuickLeaking();
}

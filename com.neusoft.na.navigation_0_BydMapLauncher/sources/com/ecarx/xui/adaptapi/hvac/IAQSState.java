package com.ecarx.xui.adaptapi.hvac;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IAQSState {
    public static final int PM25_SENSOR_STATE_COLLECTING = 1;
    public static final int PM25_SENSOR_STATE_COMPLETED = 2;
    public static final int PM25_SENSOR_STATE_ERROR = 5;
    public static final int PM25_SENSOR_STATE_INIT = 0;
    public static final int PM25_SENSOR_STATE_OFF = 3;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Pm25SensorState {
    }

    int getAQSLevel();

    int getAmbientAQSLevel();

    int getAmbientPM2_5AQI();

    int getAmbientPM2_5AQILevel();

    int getCO2Level();

    int getCO2Value();

    int getPM2_5AQI();

    int getPM2_5AQILevel();

    int getPM2_5SendorState();

    boolean isAqsOn();

    boolean isPm25Warning();
}

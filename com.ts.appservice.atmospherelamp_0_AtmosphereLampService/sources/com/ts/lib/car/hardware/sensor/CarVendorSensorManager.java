package com.ts.lib.car.hardware.sensor;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes3.dex */
public interface CarVendorSensorManager {
    public static final int ID_HUMIDITY_SENSOR_VALUE = 559958786;
    public static final int ID_LIGHT_INTENSITY = 557861632;
    public static final int ID_SLOPE = 557861635;
    public static final int ID_TEMPERATURE_SENSOR_VALUE = 559958785;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.sensor";
    public static final String TAG = "CAR.VENDOR.SENSOR";

    public interface OnCarSensorEventListener {
        void onSensorErrorEvent(int i, int i2);

        void onSensorEventChange(SensorEventChangeValue sensorEventChangeValue);
    }

    void addCarSensorEventListener(OnCarSensorEventListener onCarSensorEventListener) throws CarNotConnectedException;

    float getHumiditySensorValue() throws CarNotConnectedException;

    int getLightIntensity() throws CarNotConnectedException;

    int getSlope() throws CarNotConnectedException;

    float getTemperatureSensorValue() throws CarNotConnectedException;

    void removeCarSensorEventListener(OnCarSensorEventListener onCarSensorEventListener) throws CarNotConnectedException;
}

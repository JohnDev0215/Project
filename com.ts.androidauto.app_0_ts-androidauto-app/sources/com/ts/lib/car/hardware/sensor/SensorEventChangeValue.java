package com.ts.lib.car.hardware.sensor;

import com.ts.lib.car.CarBaseProperty;

/* JADX INFO: loaded from: classes2.dex */
public class SensorEventChangeValue extends CarBaseProperty {
    private float mHumiditySensorValue;
    private int mLightIntensity;
    private int mSlope;
    private float mTemperatureSensorValue;

    public float getHumiditySensorValue() {
        return this.mHumiditySensorValue;
    }

    public void setHumiditySensorValue(float humiditySensorValue) {
        this.mHumiditySensorValue = humiditySensorValue;
    }

    public int getLightIntensity() {
        return this.mLightIntensity;
    }

    public void setLightIntensity(int lightIntensity) {
        this.mLightIntensity = lightIntensity;
    }

    public int getSlope() {
        return this.mSlope;
    }

    public void setSlope(int slope) {
        this.mSlope = slope;
    }

    public float getTemperatureSensorValue() {
        return this.mTemperatureSensorValue;
    }

    public void setTemperatureSensorValue(float temperatureSensorValue) {
        this.mTemperatureSensorValue = temperatureSensorValue;
    }
}

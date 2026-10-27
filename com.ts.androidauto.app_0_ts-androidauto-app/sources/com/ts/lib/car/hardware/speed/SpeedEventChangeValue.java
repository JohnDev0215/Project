package com.ts.lib.car.hardware.speed;

import com.ts.lib.car.CarBaseProperty;

/* JADX INFO: loaded from: classes2.dex */
public class SpeedEventChangeValue extends CarBaseProperty {
    private int mAccelerateDeepnessR;
    private int mAccelerateDeepnessSignificantBitR;
    private int mBrakeDeepnessR;
    private int mCurrentSpeedR;
    private float mIpbVehicleSpeedStatusR;
    private float mSpeedAliveCounterR;
    private float mSpeedValueR;

    public int getAccelerateDeepnessSignificantBitR() {
        return this.mAccelerateDeepnessSignificantBitR;
    }

    public void setAccelerateDeepnessSignificantBitR(int result) {
        this.mAccelerateDeepnessSignificantBitR = result;
    }

    public float getSpeedValueR() {
        return this.mSpeedValueR;
    }

    public void setSpeedValueR(float speedValue) {
        this.mSpeedValueR = speedValue;
    }

    public int getAccelerateDeepnessR() {
        return this.mAccelerateDeepnessR;
    }

    public void setAccelerateDeepnessR(int accelerateDeepnessR) {
        this.mAccelerateDeepnessR = accelerateDeepnessR;
    }

    public int getBrakeDeepnessR() {
        return this.mBrakeDeepnessR;
    }

    public void setBrakeDeepnessR(int brakeDeepnessR) {
        this.mBrakeDeepnessR = brakeDeepnessR;
    }

    public int getCurrentSpeedR() {
        return this.mCurrentSpeedR;
    }

    public void setCurrentSpeedR(int currentSpeedR) {
        this.mCurrentSpeedR = currentSpeedR;
    }

    public float getIpbVehicleSpeedStatusR() {
        return this.mIpbVehicleSpeedStatusR;
    }

    public void setIpbVehicleSpeedStatusR(float ipbVehicleSpeedStatusR) {
        this.mIpbVehicleSpeedStatusR = ipbVehicleSpeedStatusR;
    }

    public float getSpeedAliveCounterR() {
        return this.mSpeedAliveCounterR;
    }

    public void setSpeedAliveCounterR(float speedAliveCounterR) {
        this.mSpeedAliveCounterR = speedAliveCounterR;
    }
}

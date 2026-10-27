package com.ecarx.xui.adaptapi.hvac.impl;

import android.util.Log;
import com.ecarx.xui.adaptapi.hvac.IAQSState;

/* JADX INFO: loaded from: classes2.dex */
public class AqsState implements IAQSState {
    private static final String TAG = "AqsState";
    private int mAcCO2Level;
    private int mAcCO2Value;
    private int mAmbientPM25AQI;
    private int mAmbientPM25AQILevel;
    private boolean mIsAqsOn;
    private boolean mIsPmWaring;
    private int mPM25AQI;
    private int mPM25AQILevel;
    private int mPmSensorState;

    @Override // com.ecarx.xui.adaptapi.hvac.IAQSState
    public int getAQSLevel() {
        return -2147483647;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IAQSState
    public int getAmbientAQSLevel() {
        return -2147483647;
    }

    public void notifyAqsOn(boolean z) {
        this.mIsAqsOn = z;
    }

    public void notifyPmWaring(boolean z) {
        this.mIsPmWaring = z;
    }

    public void notifyPmInsideValue(int i) {
        this.mPM25AQI = i;
    }

    public void notifyPmOutsideValue(int i) {
        this.mAmbientPM25AQI = i;
    }

    public void notifyPmSensorState(int i) {
        this.mPmSensorState = i;
    }

    public void notifyPM25AQILevel(int i) {
        this.mPM25AQILevel = i;
    }

    public void notifyAmbientPM25AQILevel(int i) {
        this.mAmbientPM25AQILevel = i;
    }

    public void notifyAcCO2Value(int i) {
        this.mAcCO2Value = i;
    }

    public void notifyAcCO2Level(int i) {
        this.mAcCO2Level = i;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IAQSState
    public boolean isAqsOn() {
        Log.d(TAG, "isAqsOn = " + this.mIsAqsOn);
        return this.mIsAqsOn;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IAQSState
    public boolean isPm25Warning() {
        Log.d(TAG, "isPm25Warning = " + this.mIsPmWaring);
        return this.mIsPmWaring;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IAQSState
    public int getPM2_5AQI() {
        Log.d(TAG, "getPM2_5AQI = " + this.mPM25AQI);
        int i = this.mPM25AQI;
        if (i > 999 || i < 0) {
            return Integer.MIN_VALUE;
        }
        return i;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IAQSState
    public int getPM2_5AQILevel() {
        Log.d(TAG, "getPM2_5AQILevel=" + this.mPM25AQILevel);
        int i = this.mPM25AQILevel;
        if (i > 5 || i < 0) {
            return Integer.MIN_VALUE;
        }
        return i;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IAQSState
    public int getAmbientPM2_5AQI() {
        Log.d(TAG, "getAmbientPM2_5AQI = " + this.mAmbientPM25AQI);
        int i = this.mAmbientPM25AQI;
        if (i > 999 || i < 0) {
            return Integer.MIN_VALUE;
        }
        return i;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IAQSState
    public int getAmbientPM2_5AQILevel() {
        Log.d(TAG, "getAmbientPM2_5AQILevel=" + this.mAmbientPM25AQILevel);
        int i = this.mAmbientPM25AQILevel;
        if (i > 5 || i < 0) {
            return Integer.MIN_VALUE;
        }
        return i;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IAQSState
    public int getPM2_5SendorState() {
        Log.d(TAG, "getPM2_5SendorState--" + this.mPmSensorState);
        int i = this.mPmSensorState;
        if (i == 0) {
            return 0;
        }
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2) {
                return 3;
            }
        }
        return i2;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IAQSState
    public int getCO2Value() {
        Log.d(TAG, "getCO2Value = " + this.mAcCO2Value);
        int i = this.mAcCO2Value;
        if (i * 10 > 5000 || i * 10 < 0) {
            return Integer.MIN_VALUE;
        }
        return i * 10;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IAQSState
    public int getCO2Level() {
        Log.d(TAG, "getCO2Level=" + this.mAcCO2Level);
        int i = this.mAcCO2Level;
        if (i > 3 || i < 0) {
            return Integer.MIN_VALUE;
        }
        return i;
    }
}

package android.hardware.bydauto.collectdata;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class AbsBYDAutoCollectDataListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoCollectData";

    @Override // android.hardware.IBYDAutoListener
    public void onDataChanged(IBYDAutoEvent var1) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int var1, String var2) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int var1, BYDAutoEventValue var2) {
    }

    public void onDriverMotorSpeed(int frontSpeed, int rearSpeed) {
        Log.v(TAG, "message onDriverMotorSpeed frontSpeed=" + frontSpeed + " rearSpeed=" + rearSpeed);
    }

    @Deprecated
    public void onDriverMotorTorque(int frontTorque, int rearTorque) {
        Log.v(TAG, "message onDriverMotorTorque frontTorque=" + frontTorque + " rearTorque=" + rearTorque);
    }

    public void onDriverMotorTemperature(int frontTemp, int rearTemp) {
        Log.v(TAG, "message onDriverMotorTemperature frontTemp=" + frontTemp + " rearTemp=" + rearTemp);
    }

    @Deprecated
    public void onMotorMCUGeneratrixVolt(int frontVolt, int rearVolt) {
        Log.v(TAG, "message onMotorMCUGeneratrixVolt frontVolt=" + frontVolt + " rearVolt=" + rearVolt);
    }

    @Deprecated
    public void onMotorMCUGeneratrixCurrent(int frontCurrent, int rearCurrent) {
        Log.v(TAG, "message onMotorMCUGeneratrixCurrent frontCurrent=" + frontCurrent + " rearCurrent=" + rearCurrent);
    }

    @Deprecated
    public void onEngineSpeed(int engineSpeed) {
        Log.v(TAG, "message onEngineSpeed engineSpeed=" + engineSpeed);
    }

    public void onDCChargingFault(int state) {
        Log.v(TAG, "message onDCChargingFault state=" + state);
    }

    public void onWaterTemperationAlarm(int state) {
        Log.v(TAG, "message onWaterTemperationAlarm onWaterTemperationAlarm=" + state);
    }

    public void onCollision(int value) {
        Log.v(TAG, "message onCollision value=" + value);
    }

    public void onSafeBeltWarnStateChanged(int state) {
        Log.v(TAG, "onSafeBeltWarnStateChanged  state=" + state);
    }
}

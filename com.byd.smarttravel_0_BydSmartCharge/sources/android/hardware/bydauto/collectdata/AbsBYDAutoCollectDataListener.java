package android.hardware.bydauto.collectdata;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class AbsBYDAutoCollectDataListener implements d {
    public static final String TAG = "AbsBYDAutoCollectData";

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onCollision(int i) {
        Log.v(TAG, "message onCollision value=" + i);
    }

    public void onDCChargingFault(int i) {
        Log.v(TAG, "message onDCChargingFault state=" + i);
    }

    public void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onDriverMotorSpeed(int i, int i2) {
        Log.v(TAG, "message onDriverMotorSpeed frontSpeed=" + i + " rearSpeed=" + i2);
    }

    public void onDriverMotorTemperature(int i, int i2) {
        Log.v(TAG, "message onDriverMotorTemperature frontTemp=" + i + " rearTemp=" + i2);
    }

    @Deprecated
    public void onDriverMotorTorque(int i, int i2) {
        Log.v(TAG, "message onDriverMotorTorque frontTorque=" + i + " rearTorque=" + i2);
    }

    @Deprecated
    public void onEngineSpeed(int i) {
        Log.v(TAG, "message onEngineSpeed engineSpeed=" + i);
    }

    public void onError(int i, String str) {
    }

    @Deprecated
    public void onMotorMCUGeneratrixCurrent(int i, int i2) {
        Log.v(TAG, "message onMotorMCUGeneratrixCurrent frontCurrent=" + i + " rearCurrent=" + i2);
    }

    @Deprecated
    public void onMotorMCUGeneratrixVolt(int i, int i2) {
        Log.v(TAG, "message onMotorMCUGeneratrixVolt frontVolt=" + i + " rearVolt=" + i2);
    }

    public void onSafeBeltWarnStateChanged(int i) {
        Log.v(TAG, "onSafeBeltWarnStateChanged  state=" + i);
    }

    public void onWaterTemperationAlarm(int i) {
        Log.v(TAG, "message onWaterTemperationAlarm onWaterTemperationAlarm=" + i);
    }
}

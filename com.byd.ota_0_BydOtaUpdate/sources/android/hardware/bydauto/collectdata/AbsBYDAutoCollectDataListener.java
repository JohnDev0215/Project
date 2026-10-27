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

    public void onCollision(int i2) {
        Log.v(TAG, "message onCollision value=" + i2);
    }

    public void onDCChargingFault(int i2) {
        Log.v(TAG, "message onDCChargingFault state=" + i2);
    }

    public void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onDriverMotorSpeed(int i2, int i3) {
        Log.v(TAG, "message onDriverMotorSpeed frontSpeed=" + i2 + " rearSpeed=" + i3);
    }

    public void onDriverMotorTemperature(int i2, int i3) {
        Log.v(TAG, "message onDriverMotorTemperature frontTemp=" + i2 + " rearTemp=" + i3);
    }

    @Deprecated
    public void onDriverMotorTorque(int i2, int i3) {
        Log.v(TAG, "message onDriverMotorTorque frontTorque=" + i2 + " rearTorque=" + i3);
    }

    @Deprecated
    public void onEngineSpeed(int i2) {
        Log.v(TAG, "message onEngineSpeed engineSpeed=" + i2);
    }

    public void onError(int i2, String str) {
    }

    @Deprecated
    public void onMotorMCUGeneratrixCurrent(int i2, int i3) {
        Log.v(TAG, "message onMotorMCUGeneratrixCurrent frontCurrent=" + i2 + " rearCurrent=" + i3);
    }

    @Deprecated
    public void onMotorMCUGeneratrixVolt(int i2, int i3) {
        Log.v(TAG, "message onMotorMCUGeneratrixVolt frontVolt=" + i2 + " rearVolt=" + i3);
    }

    public void onSafeBeltWarnStateChanged(int i2) {
        Log.v(TAG, "onSafeBeltWarnStateChanged  state=" + i2);
    }

    public void onWaterTemperationAlarm(int i2) {
        Log.v(TAG, "message onWaterTemperationAlarm onWaterTemperationAlarm=" + i2);
    }
}

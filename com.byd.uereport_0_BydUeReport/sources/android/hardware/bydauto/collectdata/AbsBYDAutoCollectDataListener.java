package android.hardware.bydauto.collectdata;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public class AbsBYDAutoCollectDataListener implements d {
    public static final String TAG = "AbsBYDAutoCollectData";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z7) {
        super.isConnected(z7);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z7) {
        super.isOptimizationApi(z7);
    }

    public void onCollision(int i8) {
        Log.v(TAG, "message onCollision value=" + i8);
    }

    public void onDCChargingFault(int i8) {
        Log.v(TAG, "message onDCChargingFault state=" + i8);
    }

    public void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onDriverMotorSpeed(int i8, int i9) {
        Log.v(TAG, "message onDriverMotorSpeed frontSpeed=" + i8 + " rearSpeed=" + i9);
    }

    public void onDriverMotorTemperature(int i8, int i9) {
        Log.v(TAG, "message onDriverMotorTemperature frontTemp=" + i8 + " rearTemp=" + i9);
    }

    @Deprecated
    public void onDriverMotorTorque(int i8, int i9) {
        Log.v(TAG, "message onDriverMotorTorque frontTorque=" + i8 + " rearTorque=" + i9);
    }

    @Deprecated
    public void onEngineSpeed(int i8) {
        Log.v(TAG, "message onEngineSpeed engineSpeed=" + i8);
    }

    public void onError(int i8, String str) {
    }

    @Deprecated
    public void onMotorMCUGeneratrixCurrent(int i8, int i9) {
        Log.v(TAG, "message onMotorMCUGeneratrixCurrent frontCurrent=" + i8 + " rearCurrent=" + i9);
    }

    @Deprecated
    public void onMotorMCUGeneratrixVolt(int i8, int i9) {
        Log.v(TAG, "message onMotorMCUGeneratrixVolt frontVolt=" + i8 + " rearVolt=" + i9);
    }

    public void onSafeBeltWarnStateChanged(int i8) {
        Log.v(TAG, "onSafeBeltWarnStateChanged  state=" + i8);
    }

    public void onWaterTemperationAlarm(int i8) {
        Log.v(TAG, "message onWaterTemperationAlarm onWaterTemperationAlarm=" + i8);
    }
}

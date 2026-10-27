package android.hardware.bydauto.collectdata;

import a.c;
import a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class AbsBYDAutoCollectDataListener implements d {
    protected static final String TAG = "AbsBYDAutoCollectData";

    @Override // a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
        super.isConnected(z6);
    }

    public void onCollision(int i7) {
        Log.v(TAG, "message onCollision value=" + i7);
    }

    public void onDCChargingFault(int i7) {
        Log.v(TAG, "message onDCChargingFault state=" + i7);
    }

    public void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onDriverMotorSpeed(int i7, int i8) {
        Log.v(TAG, "message onDriverMotorSpeed frontSpeed=" + i7 + " rearSpeed=" + i8);
    }

    public void onDriverMotorTemperature(int i7, int i8) {
        Log.v(TAG, "message onDriverMotorTemperature frontTemp=" + i7 + " rearTemp=" + i8);
    }

    @Deprecated
    public void onDriverMotorTorque(int i7, int i8) {
        Log.v(TAG, "message onDriverMotorTorque frontTorque=" + i7 + " rearTorque=" + i8);
    }

    @Deprecated
    public void onEngineSpeed(int i7) {
        Log.v(TAG, "message onEngineSpeed engineSpeed=" + i7);
    }

    public void onError(int i7, String str) {
    }

    @Deprecated
    public void onMotorMCUGeneratrixCurrent(int i7, int i8) {
        Log.v(TAG, "message onMotorMCUGeneratrixCurrent frontCurrent=" + i7 + " rearCurrent=" + i8);
    }

    @Deprecated
    public void onMotorMCUGeneratrixVolt(int i7, int i8) {
        Log.v(TAG, "message onMotorMCUGeneratrixVolt frontVolt=" + i7 + " rearVolt=" + i8);
    }

    public void onSafeBeltWarnStateChanged(int i7) {
        Log.v(TAG, "onSafeBeltWarnStateChanged  state=" + i7);
    }

    public void onWaterTemperationAlarm(int i7) {
        Log.v(TAG, "message onWaterTemperationAlarm onWaterTemperationAlarm=" + i7);
    }
}

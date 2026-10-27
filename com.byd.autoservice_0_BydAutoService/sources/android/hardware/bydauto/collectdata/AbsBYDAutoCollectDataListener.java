package android.hardware.bydauto.collectdata;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public class AbsBYDAutoCollectDataListener implements d {
    public static final String TAG = "AbsBYDAutoCollectData";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z3) {
        super.isConnected(z3);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z3) {
        super.isOptimizationApi(z3);
    }

    public void onCollision(int i3) {
        Log.v(TAG, "message onCollision value=" + i3);
    }

    public void onDCChargingFault(int i3) {
        Log.v(TAG, "message onDCChargingFault state=" + i3);
    }

    public void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onDriverMotorSpeed(int i3, int i4) {
        Log.v(TAG, "message onDriverMotorSpeed frontSpeed=" + i3 + " rearSpeed=" + i4);
    }

    public void onDriverMotorTemperature(int i3, int i4) {
        Log.v(TAG, "message onDriverMotorTemperature frontTemp=" + i3 + " rearTemp=" + i4);
    }

    @Deprecated
    public void onDriverMotorTorque(int i3, int i4) {
        Log.v(TAG, "message onDriverMotorTorque frontTorque=" + i3 + " rearTorque=" + i4);
    }

    @Deprecated
    public void onEngineSpeed(int i3) {
        Log.v(TAG, "message onEngineSpeed engineSpeed=" + i3);
    }

    public void onError(int i3, String str) {
    }

    @Deprecated
    public void onMotorMCUGeneratrixCurrent(int i3, int i4) {
        Log.v(TAG, "message onMotorMCUGeneratrixCurrent frontCurrent=" + i3 + " rearCurrent=" + i4);
    }

    @Deprecated
    public void onMotorMCUGeneratrixVolt(int i3, int i4) {
        Log.v(TAG, "message onMotorMCUGeneratrixVolt frontVolt=" + i3 + " rearVolt=" + i4);
    }

    public void onSafeBeltWarnStateChanged(int i3) {
        Log.v(TAG, "onSafeBeltWarnStateChanged  state=" + i3);
    }

    public void onWaterTemperationAlarm(int i3) {
        Log.v(TAG, "message onWaterTemperationAlarm onWaterTemperationAlarm=" + i3);
    }
}

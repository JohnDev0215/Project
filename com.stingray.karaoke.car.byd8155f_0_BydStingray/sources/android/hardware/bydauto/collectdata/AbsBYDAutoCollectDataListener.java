package android.hardware.bydauto.collectdata;

import M5.b;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public class AbsBYDAutoCollectDataListener implements d {
    protected static final String TAG = "AbsBYDAutoCollectData";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
    }

    public void onCollision(int i7) {
        b.o("message onCollision value=", i7, TAG);
    }

    public void onDCChargingFault(int i7) {
        b.o("message onDCChargingFault state=", i7, TAG);
    }

    public void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onDriverMotorSpeed(int i7, int i10) {
        Log.v(TAG, "message onDriverMotorSpeed frontSpeed=" + i7 + " rearSpeed=" + i10);
    }

    public void onDriverMotorTemperature(int i7, int i10) {
        Log.v(TAG, "message onDriverMotorTemperature frontTemp=" + i7 + " rearTemp=" + i10);
    }

    @Deprecated
    public void onDriverMotorTorque(int i7, int i10) {
        Log.v(TAG, "message onDriverMotorTorque frontTorque=" + i7 + " rearTorque=" + i10);
    }

    @Deprecated
    public void onEngineSpeed(int i7) {
        b.o("message onEngineSpeed engineSpeed=", i7, TAG);
    }

    public void onError(int i7, String str) {
    }

    @Deprecated
    public void onMotorMCUGeneratrixCurrent(int i7, int i10) {
        Log.v(TAG, "message onMotorMCUGeneratrixCurrent frontCurrent=" + i7 + " rearCurrent=" + i10);
    }

    @Deprecated
    public void onMotorMCUGeneratrixVolt(int i7, int i10) {
        Log.v(TAG, "message onMotorMCUGeneratrixVolt frontVolt=" + i7 + " rearVolt=" + i10);
    }

    public void onSafeBeltWarnStateChanged(int i7) {
        b.o("onSafeBeltWarnStateChanged  state=", i7, TAG);
    }

    public void onWaterTemperationAlarm(int i7) {
        b.o("message onWaterTemperationAlarm onWaterTemperationAlarm=", i7, TAG);
    }
}

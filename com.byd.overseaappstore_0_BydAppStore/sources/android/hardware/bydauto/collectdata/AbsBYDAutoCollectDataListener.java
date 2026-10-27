package android.hardware.bydauto.collectdata;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import spi.e;

/* JADX INFO: loaded from: classes.dex */
public class AbsBYDAutoCollectDataListener implements IBYDAutoListener {
    public static final String TAG = "AbsBYDAutoCollectData";

    public void onCollision(int i) {
        e.r("message onCollision value=", i, TAG);
    }

    public void onDCChargingFault(int i) {
        e.r("message onDCChargingFault state=", i, TAG);
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    @Override // android.hardware.IBYDAutoListener
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
        e.r("message onEngineSpeed engineSpeed=", i, TAG);
    }

    @Override // android.hardware.IBYDAutoListener
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
        e.r("onSafeBeltWarnStateChanged  state=", i, TAG);
    }

    public void onWaterTemperationAlarm(int i) {
        e.r("message onWaterTemperationAlarm onWaterTemperationAlarm=", i, TAG);
    }
}

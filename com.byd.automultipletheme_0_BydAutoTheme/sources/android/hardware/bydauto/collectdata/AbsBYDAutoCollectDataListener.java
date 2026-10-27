package android.hardware.bydauto.collectdata;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class AbsBYDAutoCollectDataListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoCollectData";

    @Override // android.hardware.IBYDAutoListener
    public /* synthetic */ void isConnected(boolean z) {
        IBYDAutoListener.CC.$default$isConnected(this, z);
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }

    public void onDriverMotorSpeed(int i, int i2) {
        Log.v(TAG, "message onDriverMotorSpeed frontSpeed=" + i + " rearSpeed=" + i2);
    }

    @Deprecated
    public void onDriverMotorTorque(int i, int i2) {
        Log.v(TAG, "message onDriverMotorTorque frontTorque=" + i + " rearTorque=" + i2);
    }

    public void onDriverMotorTemperature(int i, int i2) {
        Log.v(TAG, "message onDriverMotorTemperature frontTemp=" + i + " rearTemp=" + i2);
    }

    @Deprecated
    public void onMotorMCUGeneratrixVolt(int i, int i2) {
        Log.v(TAG, "message onMotorMCUGeneratrixVolt frontVolt=" + i + " rearVolt=" + i2);
    }

    @Deprecated
    public void onMotorMCUGeneratrixCurrent(int i, int i2) {
        Log.v(TAG, "message onMotorMCUGeneratrixCurrent frontCurrent=" + i + " rearCurrent=" + i2);
    }

    @Deprecated
    public void onEngineSpeed(int i) {
        Log.v(TAG, "message onEngineSpeed engineSpeed=" + i);
    }

    public void onDCChargingFault(int i) {
        Log.v(TAG, "message onDCChargingFault state=" + i);
    }

    public void onWaterTemperationAlarm(int i) {
        Log.v(TAG, "message onWaterTemperationAlarm onWaterTemperationAlarm=" + i);
    }

    public void onCollision(int i) {
        Log.v(TAG, "message onCollision value=" + i);
    }

    public void onSafeBeltWarnStateChanged(int i) {
        Log.v(TAG, "onSafeBeltWarnStateChanged  state=" + i);
    }
}

package android.hardware.bydauto.collectdata;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public class AbsBYDAutoCollectDataListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoCollectData";

    public void onCollision(int i) {
    }

    public void onDCChargingFault(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    @Deprecated
    public void onEngineSpeed(int i) {
    }

    public void onSafeBeltWarnStateChanged(int i) {
    }

    public void onWaterTemperationAlarm(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onDriverMotorSpeed(int i, int i2) {
    }

    public void onDriverMotorTemperature(int i, int i2) {
    }

    @Deprecated
    public void onDriverMotorTorque(int i, int i2) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }

    @Deprecated
    public void onMotorMCUGeneratrixCurrent(int i, int i2) {
    }

    @Deprecated
    public void onMotorMCUGeneratrixVolt(int i, int i2) {
    }
}

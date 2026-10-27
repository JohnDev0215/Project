package android.hardware.bydauto.motor;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoMotorListener implements IBYDAutoListener {
    public static final String TAG = "AbsBYDAutoMotorListener";

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }

    public void onMotorAngleChanged(int i) {
    }

    public void onMotorDirectionChanged(int i) {
    }

    public void onMotorLockChanged(int i) {
    }

    public void onMotorPositionChanged(int i) {
    }

    public void onMotorPowerChanged(int i) {
    }

    public void onMotorSpeedChanged(int i) {
    }
}

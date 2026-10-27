package android.hardware.bydauto.motor;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoMotorListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoMotorListener";

    public void onMotorPowerChanged(int state) {
    }

    public void onMotorLockChanged(int state) {
    }

    public void onMotorAngleChanged(int angle) {
    }

    public void onMotorPositionChanged(int position) {
    }

    public void onMotorDirectionChanged(int direction) {
    }

    public void onMotorSpeedChanged(int speed) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int errCode, String errMessage) {
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent event) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
    }
}

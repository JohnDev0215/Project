package android.hardware.bydauto.motor;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoMotorListener implements d {
    protected static final String TAG = "AbsBYDAutoMotorListener";

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    public final void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

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

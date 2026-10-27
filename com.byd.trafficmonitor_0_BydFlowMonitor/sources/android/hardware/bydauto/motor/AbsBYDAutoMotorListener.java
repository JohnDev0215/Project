package android.hardware.bydauto.motor;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoMotorListener implements d {
    public static final String TAG = "AbsBYDAutoMotorListener";

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public final void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onError(int i2, String str) {
    }

    public void onMotorAngleChanged(int i2) {
    }

    public void onMotorDirectionChanged(int i2) {
    }

    public void onMotorLockChanged(int i2) {
    }

    public void onMotorPositionChanged(int i2) {
    }

    public void onMotorPowerChanged(int i2) {
    }

    public void onMotorSpeedChanged(int i2) {
    }
}

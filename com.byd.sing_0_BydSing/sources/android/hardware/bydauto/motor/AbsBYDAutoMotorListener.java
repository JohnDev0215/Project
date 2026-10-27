package android.hardware.bydauto.motor;

import android.hardware.bydauto.BYDAutoEventValue;
import b.b.c;
import b.b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoMotorListener implements d {
    protected static final String TAG = "AbsBYDAutoMotorListener";

    @Override // b.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // b.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
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

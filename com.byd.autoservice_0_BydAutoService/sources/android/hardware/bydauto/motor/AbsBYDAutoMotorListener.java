package android.hardware.bydauto.motor;

import android.hardware.bydauto.BYDAutoEventValue;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoMotorListener implements d {
    public static final String TAG = "AbsBYDAutoMotorListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z3) {
        super.isConnected(z3);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z3) {
        super.isOptimizationApi(z3);
    }

    public final void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onError(int i3, String str) {
    }

    public void onMotorAngleChanged(int i3) {
    }

    public void onMotorDirectionChanged(int i3) {
    }

    public void onMotorLockChanged(int i3) {
    }

    public void onMotorPositionChanged(int i3) {
    }

    public void onMotorPowerChanged(int i3) {
    }

    public void onMotorSpeedChanged(int i3) {
    }
}

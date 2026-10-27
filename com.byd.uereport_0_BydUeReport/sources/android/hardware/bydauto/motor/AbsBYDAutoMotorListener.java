package android.hardware.bydauto.motor;

import android.hardware.bydauto.BYDAutoEventValue;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoMotorListener implements d {
    public static final String TAG = "AbsBYDAutoMotorListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z7) {
        super.isConnected(z7);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z7) {
        super.isOptimizationApi(z7);
    }

    public final void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onError(int i8, String str) {
    }

    public void onMotorAngleChanged(int i8) {
    }

    public void onMotorDirectionChanged(int i8) {
    }

    public void onMotorLockChanged(int i8) {
    }

    public void onMotorPositionChanged(int i8) {
    }

    public void onMotorPowerChanged(int i8) {
    }

    public void onMotorSpeedChanged(int i8) {
    }
}

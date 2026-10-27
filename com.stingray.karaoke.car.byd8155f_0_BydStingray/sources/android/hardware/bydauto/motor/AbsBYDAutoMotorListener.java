package android.hardware.bydauto.motor;

import android.hardware.bydauto.BYDAutoEventValue;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoMotorListener implements d {
    protected static final String TAG = "AbsBYDAutoMotorListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
    }

    public final void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onError(int i7, String str) {
    }

    public void onMotorAngleChanged(int i7) {
    }

    public void onMotorDirectionChanged(int i7) {
    }

    public void onMotorLockChanged(int i7) {
    }

    public void onMotorPositionChanged(int i7) {
    }

    public void onMotorPowerChanged(int i7) {
    }

    public void onMotorSpeedChanged(int i7) {
    }
}

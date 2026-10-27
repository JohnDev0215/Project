package android.hardware.bydauto.seat;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSeatListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoSeatListener";

    @Override // android.hardware.IBYDAutoListener
    public void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    public void onSteeringWheelWarmStateChanged(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }

    public void onSeatMassageLevelChange(int i, int i2) {
    }

    public void onSeatMassageTypeChange(int i, int i2) {
    }

    public void onSeatVentilationState(int i, int i2) {
    }

    public void onSeatWarmStateChanged(int i, int i2) {
    }
}

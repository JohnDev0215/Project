package android.hardware.bydauto.seat;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSeatListener implements d {
    public static final String TAG = "AbsBYDAutoSeatListener";

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onDataChanged(c cVar) {
        Log.d(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged");
    }

    public void onError(int i, String str) {
        Log.d(TAG, "onError");
    }

    public void onSeatMassageLevelChange(int i, int i2) {
        Log.d(TAG, "onSeatMassageLevelChange");
    }

    public void onSeatMassageTypeChange(int i, int i2) {
        Log.d(TAG, "onSeatMassageTypeChange");
    }

    public void onSeatVentilationState(int i, int i2) {
        Log.d(TAG, "onSeatVentilationState");
    }

    public void onSeatWarmStateChanged(int i, int i2) {
        Log.d(TAG, "onSeatWarmStateChanged");
    }

    public void onSteeringWheelWarmStateChanged(int i) {
        Log.d(TAG, "onSteeringWheelWarmStateChanged");
    }
}

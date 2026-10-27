package android.hardware.bydauto.seat;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSeatListener implements d {
    protected static final String TAG = "AbsBYDAutoSeatListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
    }

    public void onDataChanged(c cVar) {
        Log.d(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged");
    }

    public void onError(int i7, String str) {
        Log.d(TAG, "onError");
    }

    public void onSeatMassageLevelChange(int i7, int i10) {
        Log.d(TAG, "onSeatMassageLevelChange");
    }

    public void onSeatMassageTypeChange(int i7, int i10) {
        Log.d(TAG, "onSeatMassageTypeChange");
    }

    public void onSeatVentilationState(int i7, int i10) {
        Log.d(TAG, "onSeatVentilationState");
    }

    public void onSeatWarmStateChanged(int i7, int i10) {
        Log.d(TAG, "onSeatWarmStateChanged");
    }

    public void onSteeringWheelWarmStateChanged(int i7) {
        Log.d(TAG, "onSteeringWheelWarmStateChanged");
    }
}

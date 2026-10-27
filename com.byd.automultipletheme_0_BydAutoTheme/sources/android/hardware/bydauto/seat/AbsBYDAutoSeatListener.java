package android.hardware.bydauto.seat;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSeatListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoSeatListener";

    @Override // android.hardware.IBYDAutoListener
    public /* synthetic */ void isConnected(boolean z) {
        IBYDAutoListener.CC.$default$isConnected(this, z);
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
        Log.d(TAG, "onDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
        Log.d(TAG, "onError");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged");
    }

    public void onSteeringWheelWarmStateChanged(int i) {
        Log.d(TAG, "onSteeringWheelWarmStateChanged");
    }

    public void onSeatVentilationState(int i, int i2) {
        Log.d(TAG, "onSeatVentilationState");
    }

    public void onSeatWarmStateChanged(int i, int i2) {
        Log.d(TAG, "onSeatWarmStateChanged");
    }

    public void onSeatMassageLevelChange(int i, int i2) {
        Log.d(TAG, "onSeatMassageLevelChange");
    }

    public void onSeatMassageTypeChange(int i, int i2) {
        Log.d(TAG, "onSeatMassageTypeChange");
    }
}

package android.hardware.bydauto.seat;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSeatListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoSeatListener";

    @Override // android.hardware.IBYDAutoListener
    public void onDataChanged(IBYDAutoEvent var1) {
        Log.d(TAG, "onDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int var1, String var2) {
        Log.d(TAG, "onError");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int var1, BYDAutoEventValue var2) {
        Log.d(TAG, "onDataEventChanged");
    }

    public void onSteeringWheelWarmStateChanged(int state) {
        Log.d(TAG, "onSteeringWheelWarmStateChanged");
    }

    public void onSeatVentilationState(int area, int value) {
        Log.d(TAG, "onSeatVentilationState");
    }

    public void onSeatWarmStateChanged(int area, int value) {
        Log.d(TAG, "onSeatWarmStateChanged");
    }

    public void onSeatMassageLevelChange(int area, int value) {
        Log.d(TAG, "onSeatMassageLevelChange");
    }

    public void onSeatMassageTypeChange(int area, int value) {
        Log.d(TAG, "onSeatMassageTypeChange");
    }
}

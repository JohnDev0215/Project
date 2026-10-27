package android.hardware.bydauto.gearbox;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoGearboxListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoGearboxListener";

    public void onBrakeFluidLevelChanged(int i) {
        Log.v(TAG, "onBrakeFluidLevelChanged");
    }

    public void onBrakePedalStateChanged(int i) {
        Log.v(TAG, "onBrakePedalStateChanged");
    }

    public void onCurrentGearChanged(int i) {
        Log.v(TAG, "onCurrentGearChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
        Log.v(TAG, "onCurrentGearChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onCurrentGearChanged");
    }

    public void onEPBStateChanged(int i) {
        Log.v(TAG, "onEPBStateChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
        Log.v(TAG, "onCurrentGearChanged");
    }

    public void onGearChanged(int i) {
        Log.v(TAG, "onGearChanged");
    }

    public void onGearboxAutoModeLevelChanged(int i) {
        Log.v(TAG, "onGearboxAutoModeLevelChanged");
    }

    public void onGearboxAutoModeTypeChanged(int i) {
        Log.v(TAG, "onGearboxAutoModeTypeChanged");
    }

    public void onGearboxManualModeLevelChanged(int i) {
        Log.v(TAG, "onGearboxManualModeLevelChanged");
    }

    public void onGearboxStateChanged(int i) {
        Log.v(TAG, "onGearboxStateChanged");
    }

    public void onParkBrakeSwitchChanged(int i) {
        Log.v(TAG, "onParkBrakeSwitchChanged");
    }
}

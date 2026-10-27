package android.hardware.bydauto.gearbox;

import a.a.c;
import a.a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoGearboxListener implements d {
    protected static final String TAG = "AbsBYDAutoGearboxListener";

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onBrakeFluidLevelChanged(int i2) {
        Log.v(TAG, "onBrakeFluidLevelChanged");
    }

    public void onBrakePedalStateChanged(int i2) {
        Log.v(TAG, "onBrakePedalStateChanged");
    }

    public void onBrakePedalStatusChanged(int i2) {
        Log.v(TAG, "onBrakePedalStatusChanged state is " + i2);
    }

    public void onCurrentGearChanged(int i2) {
        Log.v(TAG, "onCurrentGearChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onCurrentGearChanged");
    }

    public void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onCurrentGearChanged");
    }

    public void onEPBStateChanged(int i2) {
        Log.v(TAG, "onEPBStateChanged");
    }

    public void onError(int i2, String str) {
        Log.v(TAG, "onCurrentGearChanged");
    }

    public void onGearChanged(int i2) {
        Log.v(TAG, "onGearChanged value is " + i2);
    }

    public void onGearboxAutoModeLevelChanged(int i2) {
        Log.v(TAG, "onGearboxAutoModeLevelChanged");
    }

    public void onGearboxAutoModeTypeChanged(int i2) {
        Log.v(TAG, "onGearboxAutoModeTypeChanged");
    }

    public void onGearboxManualModeLevelChanged(int i2) {
        Log.v(TAG, "onGearboxManualModeLevelChanged");
    }

    public void onGearboxStateChanged(int i2) {
        Log.v(TAG, "onGearboxStateChanged");
    }

    public void onParkBrakeSwitchChanged(int i2) {
        Log.v(TAG, "onParkBrakeSwitchChanged");
    }
}

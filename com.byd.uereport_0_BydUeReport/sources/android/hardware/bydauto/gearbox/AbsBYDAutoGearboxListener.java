package android.hardware.bydauto.gearbox;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoGearboxListener implements d {
    public static final String TAG = "AbsBYDAutoGearboxListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z7) {
        super.isConnected(z7);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z7) {
        super.isOptimizationApi(z7);
    }

    public void onBrakeFluidLevelChanged(int i8) {
        Log.v(TAG, "onBrakeFluidLevelChanged");
    }

    public void onBrakePedalStateChanged(int i8) {
        Log.v(TAG, "onBrakePedalStateChanged");
    }

    public void onBrakePedalStatusChanged(int i8) {
        Log.v(TAG, "onBrakePedalStatusChanged state is " + i8);
    }

    public void onCurrentGearChanged(int i8) {
        Log.v(TAG, "onCurrentGearChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onCurrentGearChanged");
    }

    public void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onCurrentGearChanged");
    }

    public void onEPBStateChanged(int i8) {
        Log.v(TAG, "onEPBStateChanged");
    }

    public void onError(int i8, String str) {
        Log.v(TAG, "onCurrentGearChanged");
    }

    public void onGearChanged(int i8) {
        Log.v(TAG, "onGearChanged value is " + i8);
    }

    public void onGearboxAutoModeLevelChanged(int i8) {
        Log.v(TAG, "onGearboxAutoModeLevelChanged");
    }

    public void onGearboxAutoModeTypeChanged(int i8) {
        Log.v(TAG, "onGearboxAutoModeTypeChanged");
    }

    public void onGearboxManualModeLevelChanged(int i8) {
        Log.v(TAG, "onGearboxManualModeLevelChanged");
    }

    public void onGearboxStateChanged(int i8) {
        Log.v(TAG, "onGearboxStateChanged");
    }

    public void onParkBrakeSwitchChanged(int i8) {
        Log.v(TAG, "onParkBrakeSwitchChanged");
    }
}

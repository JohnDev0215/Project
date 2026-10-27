package android.hardware.bydauto.gearbox;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoGearboxListener implements d {
    public static final String TAG = "AbsBYDAutoGearboxListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z3) {
        super.isConnected(z3);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z3) {
        super.isOptimizationApi(z3);
    }

    public void onBrakeFluidLevelChanged(int i3) {
        Log.v(TAG, "onBrakeFluidLevelChanged");
    }

    public void onBrakePedalStateChanged(int i3) {
        Log.v(TAG, "onBrakePedalStateChanged");
    }

    public void onBrakePedalStatusChanged(int i3) {
        Log.v(TAG, "onBrakePedalStatusChanged state is " + i3);
    }

    public void onCurrentGearChanged(int i3) {
        Log.v(TAG, "onCurrentGearChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onCurrentGearChanged");
    }

    public void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onCurrentGearChanged");
    }

    public void onEPBStateChanged(int i3) {
        Log.v(TAG, "onEPBStateChanged");
    }

    public void onError(int i3, String str) {
        Log.v(TAG, "onCurrentGearChanged");
    }

    public void onGearChanged(int i3) {
        Log.v(TAG, "onGearChanged value is " + i3);
    }

    public void onGearboxAutoModeLevelChanged(int i3) {
        Log.v(TAG, "onGearboxAutoModeLevelChanged");
    }

    public void onGearboxAutoModeTypeChanged(int i3) {
        Log.v(TAG, "onGearboxAutoModeTypeChanged");
    }

    public void onGearboxManualModeLevelChanged(int i3) {
        Log.v(TAG, "onGearboxManualModeLevelChanged");
    }

    public void onGearboxStateChanged(int i3) {
        Log.v(TAG, "onGearboxStateChanged");
    }

    public void onParkBrakeSwitchChanged(int i3) {
        Log.v(TAG, "onParkBrakeSwitchChanged");
    }
}

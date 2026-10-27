package android.hardware.bydauto.gearbox;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoGearboxListener implements d {
    public static final String TAG = "AbsBYDAutoGearboxListener";

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onBrakeFluidLevelChanged(int i) {
        Log.v(TAG, "onBrakeFluidLevelChanged");
    }

    public void onBrakePedalStateChanged(int i) {
        Log.v(TAG, "onBrakePedalStateChanged");
    }

    public void onBrakePedalStatusChanged(int i) {
        Log.v(TAG, "onBrakePedalStatusChanged state is " + i);
    }

    public void onCurrentGearChanged(int i) {
        Log.v(TAG, "onCurrentGearChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onCurrentGearChanged");
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onCurrentGearChanged");
    }

    public void onEPBStateChanged(int i) {
        Log.v(TAG, "onEPBStateChanged");
    }

    public void onError(int i, String str) {
        Log.v(TAG, "onCurrentGearChanged");
    }

    public void onGearChanged(int i) {
        Log.v(TAG, "onGearChanged value is " + i);
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

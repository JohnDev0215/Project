package android.hardware.bydauto.gearbox;

import a.c;
import a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoGearboxListener implements d {
    protected static final String TAG = "AbsBYDAutoGearboxListener";

    @Override // a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
        super.isConnected(z6);
    }

    public void onBrakeFluidLevelChanged(int i7) {
        Log.v(TAG, "onBrakeFluidLevelChanged");
    }

    public void onBrakePedalStateChanged(int i7) {
        Log.v(TAG, "onBrakePedalStateChanged");
    }

    public void onCurrentGearChanged(int i7) {
        Log.v(TAG, "onCurrentGearChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onCurrentGearChanged");
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onCurrentGearChanged");
    }

    public void onEPBStateChanged(int i7) {
        Log.v(TAG, "onEPBStateChanged");
    }

    public void onError(int i7, String str) {
        Log.v(TAG, "onCurrentGearChanged");
    }

    public void onGearChanged(int i7) {
        Log.v(TAG, "onGearChanged");
    }

    public void onGearboxAutoModeLevelChanged(int i7) {
        Log.v(TAG, "onGearboxAutoModeLevelChanged");
    }

    public void onGearboxAutoModeTypeChanged(int i7) {
        Log.v(TAG, "onGearboxAutoModeTypeChanged");
    }

    public void onGearboxManualModeLevelChanged(int i7) {
        Log.v(TAG, "onGearboxManualModeLevelChanged");
    }

    public void onGearboxStateChanged(int i7) {
        Log.v(TAG, "onGearboxStateChanged");
    }

    public void onParkBrakeSwitchChanged(int i7) {
        Log.v(TAG, "onParkBrakeSwitchChanged");
    }
}

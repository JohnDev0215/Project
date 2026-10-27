package android.hardware.bydauto.gearbox;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoGearboxListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoGearboxListener";

    public void onGearboxAutoModeTypeChanged(int level) {
        Log.v(TAG, "onGearboxAutoModeTypeChanged");
    }

    public void onGearboxManualModeLevelChanged(int level) {
        Log.v(TAG, "onGearboxManualModeLevelChanged");
    }

    public void onGearboxStateChanged(int state) {
        Log.v(TAG, "onGearboxStateChanged");
    }

    public void onEPBStateChanged(int state) {
        Log.v(TAG, "onEPBStateChanged");
    }

    public void onCurrentGearChanged(int gear) {
        Log.v(TAG, "onCurrentGearChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int errCode, String errMessage) {
        Log.v(TAG, "onCurrentGearChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent event) {
        Log.v(TAG, "onCurrentGearChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
        Log.v(TAG, "onCurrentGearChanged");
    }

    public void onBrakeFluidLevelChanged(int level) {
        Log.v(TAG, "onBrakeFluidLevelChanged");
    }

    public void onParkBrakeSwitchChanged(int level) {
        Log.v(TAG, "onParkBrakeSwitchChanged");
    }

    public void onBrakePedalStateChanged(int level) {
        Log.v(TAG, "onBrakePedalStateChanged");
    }

    public void onGearChanged(int value) {
        Log.v(TAG, "onGearChanged value is " + value);
    }

    public void onGearboxAutoModeLevelChanged(int level) {
        Log.v(TAG, "onGearboxAutoModeLevelChanged");
    }

    public void onBrakePedalStatusChanged(int state) {
        Log.v(TAG, "onBrakePedalStatusChanged state is " + state);
    }
}

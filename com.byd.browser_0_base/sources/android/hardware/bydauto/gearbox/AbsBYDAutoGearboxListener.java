package android.hardware.bydauto.gearbox;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoGearboxListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoGearboxListener";

    public void onBrakeFluidLevelChanged(int i) {
    }

    public void onBrakePedalStateChanged(int i) {
    }

    public void onBrakePedalStatusChanged(int i) {
    }

    public void onCurrentGearChanged(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    public void onEPBStateChanged(int i) {
    }

    public void onGearChanged(int i) {
    }

    public void onGearboxAutoModeLevelChanged(int i) {
    }

    public void onGearboxAutoModeTypeChanged(int i) {
    }

    public void onGearboxManualModeLevelChanged(int i) {
    }

    public void onGearboxStateChanged(int i) {
    }

    public void onParkBrakeSwitchChanged(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }
}

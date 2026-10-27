package android.hardware.bydauto.vehiclehealth;

import android.hardware.IBYDAutoListener;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoVehicleHealthListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDHealthListener";

    public void onOilLifetimeChanged(int i) {
    }

    public void onOilLifetimeChanged(int i, int i2) {
    }

    public void onBatteryHealthStatusChanged(int i) {
    }

    public void onEPBHintChanged(int i) {
    }

    public void onEPBStateChanged(int i) {
    }

    public void onFrontWiperCheckStateChanged(int i) {
    }

    public void onOilLevelChanged(int i) {
    }

    public void onOilLifetimeNewChanged(int i) {
    }

    public void onOilMaintainanceDialogHintChanged(int i) {
    }

    public void onOilUpdateStateChanged(int i) {
    }

    public void onRearWiperCheckStateChanged(int i) {
    }

    @Deprecated
    public void onResetTirePressureStateChanged(int i) {
    }

    public void onTransportModeChanged(int i) {
    }

    public void onTransportModeStateHintChanged(int i) {
    }

    public void onOilMaintainanceHintChanged(int i, int i2) {
    }

    public void onResetOilLifetimeChanged(int i, int i2) {
    }
}

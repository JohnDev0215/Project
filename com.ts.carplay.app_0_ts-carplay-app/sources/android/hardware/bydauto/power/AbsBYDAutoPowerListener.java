package android.hardware.bydauto.power;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoPowerListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoPowerListener";

    public void onBatteryRemainPowerEVChanged(double value) {
    }

    public void onPowerCtlStatusChanged(int event_type, int value) {
    }

    public void onTpDisplayControllerChanged(int event_type, int value) {
    }

    public void onTftBacklightChanged(int value) {
    }

    public void onMcuStatusChanged(int value) {
    }

    public void onShutdownInfoChanged(int device, int info) {
    }

    public void onBatteryLowVoltageStateChanged(int state) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int errCode, String errMessage) {
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent event) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
    }

    public void onPowerLevelChanged(int level) {
    }

    public void onPowerSTRChanged(int state) {
    }
}

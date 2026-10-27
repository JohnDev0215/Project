package android.hardware.bydauto.power;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoPowerListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoPowerListener";

    @Override // android.hardware.IBYDAutoListener
    public /* synthetic */ void isConnected(boolean z) {
        IBYDAutoListener.CC.$default$isConnected(this, z);
    }

    public void onBatteryLowVoltageStateChanged(int i) {
    }

    public void onBatteryRemainPowerEVChanged(double d) {
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }

    public void onMcuStatusChanged(int i) {
    }

    public void onPowerCtlStatusChanged(int i, int i2) {
    }

    public void onPowerLevelChanged(int i) {
    }

    public void onPowerSTRChanged(int i) {
    }

    public void onShutdownInfoChanged(int i, int i2) {
    }

    public void onTftBacklightChanged(int i) {
    }

    public void onTpDisplayControllerChanged(int i, int i2) {
    }
}

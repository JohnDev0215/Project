package android.hardware.bydauto.power;

import a.a.c;
import a.a.d;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoPowerListener implements d {
    protected static final String TAG = "AbsBYDAutoPowerListener";

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onBatteryLowVoltageStateChanged(int i2) {
    }

    public void onBatteryRemainPowerEVChanged(double d2) {
    }

    public final void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onError(int i2, String str) {
    }

    public void onMcuStatusChanged(int i2) {
    }

    public void onPowerCtlStatusChanged(int i2, int i3) {
    }

    public void onPowerLevelChanged(int i2) {
    }

    public void onPowerSTRChanged(int i2) {
    }

    public void onShutdownInfoChanged(int i2, int i3) {
    }

    public void onTftBacklightChanged(int i2) {
    }

    public void onTpDisplayControllerChanged(int i2, int i3) {
    }
}

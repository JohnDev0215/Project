package android.hardware.bydauto.power;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoPowerListener implements d {
    public static final String TAG = "AbsBYDAutoPowerListener";

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onBatteryLowVoltageStateChanged(int i) {
    }

    public void onBatteryRemainPowerEVChanged(double d) {
    }

    public final void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

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

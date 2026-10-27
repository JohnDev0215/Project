package android.hardware.bydauto.power;

import android.hardware.bydauto.BYDAutoEventValue;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoPowerListener implements d {
    public static final String TAG = "AbsBYDAutoPowerListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z7) {
        super.isConnected(z7);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z7) {
        super.isOptimizationApi(z7);
    }

    public void onBatteryLowVoltageStateChanged(int i8) {
    }

    public void onBatteryRemainPowerEVChanged(double d8) {
    }

    public final void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onError(int i8, String str) {
    }

    public void onMcuStatusChanged(int i8) {
    }

    public void onPowerCtlStatusChanged(int i8, int i9) {
    }

    public void onPowerLevelChanged(int i8) {
    }

    public void onPowerSTRChanged(int i8) {
    }

    public void onShutdownInfoChanged(int i8, int i9) {
    }

    public void onTftBacklightChanged(int i8) {
    }

    public void onTpDisplayControllerChanged(int i8, int i9) {
    }
}

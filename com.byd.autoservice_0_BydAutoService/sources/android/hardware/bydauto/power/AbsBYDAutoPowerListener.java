package android.hardware.bydauto.power;

import android.hardware.bydauto.BYDAutoEventValue;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoPowerListener implements d {
    public static final String TAG = "AbsBYDAutoPowerListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z3) {
        super.isConnected(z3);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z3) {
        super.isOptimizationApi(z3);
    }

    public void onBatteryLowVoltageStateChanged(int i3) {
    }

    public void onBatteryRemainPowerEVChanged(double d3) {
    }

    public final void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onError(int i3, String str) {
    }

    public void onMcuStatusChanged(int i3) {
    }

    public void onPowerCtlStatusChanged(int i3, int i4) {
    }

    public void onPowerLevelChanged(int i3) {
    }

    public void onPowerSTRChanged(int i3) {
    }

    public void onShutdownInfoChanged(int i3, int i4) {
    }

    public void onTftBacklightChanged(int i3) {
    }

    public void onTpDisplayControllerChanged(int i3, int i4) {
    }
}

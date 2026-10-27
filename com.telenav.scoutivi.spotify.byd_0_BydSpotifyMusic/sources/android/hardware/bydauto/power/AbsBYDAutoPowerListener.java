package android.hardware.bydauto.power;

import a.c;
import a.d;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoPowerListener implements d {
    protected static final String TAG = "AbsBYDAutoPowerListener";

    @Override // a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
        super.isConnected(z6);
    }

    public void onBatteryLowVoltageStateChanged(int i7) {
    }

    public void onBatteryRemainPowerEVChanged(double d7) {
    }

    public final void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onError(int i7, String str) {
    }

    public void onMcuStatusChanged(int i7) {
    }

    public void onPowerCtlStatusChanged(int i7, int i8) {
    }

    public void onPowerLevelChanged(int i7) {
    }

    public void onPowerSTRChanged(int i7) {
    }

    public void onShutdownInfoChanged(int i7, int i8) {
    }

    public void onTftBacklightChanged(int i7) {
    }

    public void onTpDisplayControllerChanged(int i7, int i8) {
    }
}

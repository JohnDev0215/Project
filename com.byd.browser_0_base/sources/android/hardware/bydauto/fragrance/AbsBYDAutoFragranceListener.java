package android.hardware.bydauto.fragrance;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoFragranceListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutFragranceListener";

    @Override // android.hardware.IBYDAutoListener
    public void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    public void onFragranceOnlineStateChange(int i) {
    }

    public void onFragranceSystemStateChange(int i) {
    }

    public void onFragranceTypeChanged(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }

    public void onFragranceConcentrationChanged(int i, int i2) {
    }

    public void onFragranceInstallStateChanged(int i, int i2) {
    }

    public void onFragranceLessAllowance(int i, int i2) {
    }

    public void onFragranceNameChanged(int i, int i2) {
    }

    public void onFragranceResidueChanged(int i, int i2) {
    }
}

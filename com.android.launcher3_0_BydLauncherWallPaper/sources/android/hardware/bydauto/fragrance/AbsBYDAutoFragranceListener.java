package android.hardware.bydauto.fragrance;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoFragranceListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutFragranceListener";

    @Override // android.hardware.IBYDAutoListener
    public void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
        Log.v(TAG, "onDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
        Log.v(TAG, "onError");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onFragranceTypeChanged(int i) {
        Log.v(TAG, "onFragranceTypeChanged");
    }

    public void onFragranceOnlineStateChange(int i) {
        Log.v(TAG, "message onFragranceOnlineStateChange = " + i);
    }

    public void onFragranceConcentrationChanged(int i, int i2) {
        Log.v(TAG, "onFragranceConcentrationChanged");
    }

    public void onFragranceResidueChanged(int i, int i2) {
        Log.v(TAG, "onFragranceResidueChanged");
    }

    public void onFragranceSystemStateChange(int i) {
        Log.v(TAG, "onFragranceSystemStateChange");
    }

    public void onFragranceLessAllowance(int i, int i2) {
        Log.v(TAG, "onFragranceLessAllowance");
    }

    public void onFragranceInstallStateChanged(int i, int i2) {
        Log.v(TAG, "onFragranceInstallStateChanged");
    }

    public void onFragranceNameChanged(int i, int i2) {
        Log.v(TAG, "onFragranceNameChanged");
    }
}

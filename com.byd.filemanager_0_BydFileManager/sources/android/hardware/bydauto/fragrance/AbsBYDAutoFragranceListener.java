package android.hardware.bydauto.fragrance;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoFragranceListener implements d {
    protected static final String TAG = "AbsBYDAutFragranceListener";

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    public void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onError(int i, String str) {
        Log.v(TAG, "onError");
    }

    public void onFragranceConcentrationChanged(int i, int i2) {
        Log.v(TAG, "onFragranceConcentrationChanged");
    }

    public void onFragranceInstallStateChanged(int i, int i2) {
        Log.v(TAG, "onFragranceInstallStateChanged");
    }

    public void onFragranceLessAllowance(int i, int i2) {
        Log.v(TAG, "onFragranceLessAllowance");
    }

    public void onFragranceNameChanged(int i, int i2) {
        Log.v(TAG, "onFragranceNameChanged");
    }

    public void onFragranceOnlineStateChange(int i) {
        Log.v(TAG, "message onFragranceOnlineStateChange = " + i);
    }

    public void onFragranceResidueChanged(int i, int i2) {
        Log.v(TAG, "onFragranceResidueChanged");
    }

    public void onFragranceSystemStateChange(int i) {
        Log.v(TAG, "onFragranceSystemStateChange");
    }

    public void onFragranceTypeChanged(int i) {
        Log.v(TAG, "onFragranceTypeChanged");
    }
}

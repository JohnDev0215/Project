package android.hardware.bydauto.fragrance;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoFragranceListener implements d {
    public static final String TAG = "AbsBYDAutFragranceListener";

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onError(int i2, String str) {
        Log.v(TAG, "onError");
    }

    public void onFragranceConcentrationChanged(int i2, int i3) {
        Log.v(TAG, "onFragranceConcentrationChanged");
    }

    public void onFragranceInstallStateChanged(int i2, int i3) {
        Log.v(TAG, "onFragranceInstallStateChanged");
    }

    public void onFragranceLessAllowance(int i2, int i3) {
        Log.v(TAG, "onFragranceLessAllowance");
    }

    public void onFragranceNameChanged(int i2, int i3) {
        Log.v(TAG, "onFragranceNameChanged");
    }

    public void onFragranceOnlineStateChange(int i2) {
        Log.v(TAG, "message onFragranceOnlineStateChange = " + i2);
    }

    public void onFragranceResidueChanged(int i2, int i3) {
        Log.v(TAG, "onFragranceResidueChanged");
    }

    public void onFragranceSystemStateChange(int i2) {
        Log.v(TAG, "onFragranceSystemStateChange");
    }

    public void onFragranceTypeChanged(int i2) {
        Log.v(TAG, "onFragranceTypeChanged");
    }
}

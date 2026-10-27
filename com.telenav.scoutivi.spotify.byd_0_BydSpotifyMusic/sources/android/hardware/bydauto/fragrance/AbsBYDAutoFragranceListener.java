package android.hardware.bydauto.fragrance;

import a.c;
import a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoFragranceListener implements d {
    protected static final String TAG = "AbsBYDAutFragranceListener";

    @Override // a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
        super.isConnected(z6);
    }

    public void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onError(int i7, String str) {
        Log.v(TAG, "onError");
    }

    public void onFragranceConcentrationChanged(int i7, int i8) {
        Log.v(TAG, "onFragranceConcentrationChanged");
    }

    public void onFragranceInstallStateChanged(int i7, int i8) {
        Log.v(TAG, "onFragranceInstallStateChanged");
    }

    public void onFragranceLessAllowance(int i7, int i8) {
        Log.v(TAG, "onFragranceLessAllowance");
    }

    public void onFragranceNameChanged(int i7, int i8) {
        Log.v(TAG, "onFragranceNameChanged");
    }

    public void onFragranceOnlineStateChange(int i7) {
        Log.v(TAG, "message onFragranceOnlineStateChange = " + i7);
    }

    public void onFragranceResidueChanged(int i7, int i8) {
        Log.v(TAG, "onFragranceResidueChanged");
    }

    public void onFragranceSystemStateChange(int i7) {
        Log.v(TAG, "onFragranceSystemStateChange");
    }

    public void onFragranceTypeChanged(int i7) {
        Log.v(TAG, "onFragranceTypeChanged");
    }
}

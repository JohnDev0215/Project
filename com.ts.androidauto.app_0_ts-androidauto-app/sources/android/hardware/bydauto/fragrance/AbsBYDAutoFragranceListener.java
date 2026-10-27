package android.hardware.bydauto.fragrance;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoFragranceListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutFragranceListener";

    @Override // android.hardware.IBYDAutoListener
    public void onDataChanged(IBYDAutoEvent var1) {
        Log.v(TAG, "onDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int var1, String var2) {
        Log.v(TAG, "onError");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int var1, BYDAutoEventValue var2) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onFragranceTypeChanged(int type) {
        Log.v(TAG, "onFragranceTypeChanged");
    }

    public void onFragranceOnlineStateChange(int state) {
        Log.v(TAG, "message onFragranceOnlineStateChange = " + state);
    }

    public void onFragranceConcentrationChanged(int type, int value) {
        Log.v(TAG, "onFragranceConcentrationChanged");
    }

    public void onFragranceResidueChanged(int type, int value) {
        Log.v(TAG, "onFragranceResidueChanged");
    }

    public void onFragranceSystemStateChange(int state) {
        Log.v(TAG, "onFragranceSystemStateChange");
    }

    public void onFragranceLessAllowance(int type, int value) {
        Log.v(TAG, "onFragranceLessAllowance");
    }

    public void onFragranceInstallStateChanged(int type, int state) {
        Log.v(TAG, "onFragranceInstallStateChanged");
    }

    public void onFragranceNameChanged(int type, int value) {
        Log.v(TAG, "onFragranceNameChanged");
    }
}

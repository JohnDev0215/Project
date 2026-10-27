package android.hardware.bydauto.radio;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoRadioListener implements d {
    public static final String TAG = "AbsBYDAutoRadioListener";

    public AbsBYDAutoRadioListener() {
        Log.d(TAG, TAG);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onCurFreqChanged(int i2, int i3, int i4) {
        Log.d(TAG, "onCurFreqChanged Listener" + i2 + "," + i3 + "," + i4);
    }

    public final void onDataChanged(c cVar) {
        Log.d(TAG, "onDataChanged Listener" + cVar);
    }

    public void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged Listener" + i2 + "," + i2);
    }

    public void onEffectiveFreqSearched(int i2, int i3, int i4, int i5) {
        Log.d(TAG, "onEffectiveFreqSearched Listener" + i2 + "," + i3 + "," + i4 + "," + i5);
    }

    public void onError(int i2, String str) {
        Log.d(TAG, "onError Listener" + i2 + "," + str);
    }

    public void onRadioParamChanged(int i2, int i3, int i4, int i5) {
        Log.d(TAG, "onRadioParamChanged Listener" + i2 + "," + i3 + "," + i4 + "," + i5);
    }

    public void onRadioStateChanged(int i2) {
        Log.d(TAG, "onRadioStateChanged Listener " + i2);
    }

    public void onSearchResultChanged(int i2, int i3, int i4, int i5) {
        Log.d(TAG, "onSearchResultChanged Listener" + i2 + "," + i4 + "," + i4 + "," + i5);
    }
}

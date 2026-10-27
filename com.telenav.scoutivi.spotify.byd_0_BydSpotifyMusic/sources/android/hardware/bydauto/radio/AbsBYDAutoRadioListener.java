package android.hardware.bydauto.radio;

import a.c;
import a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoRadioListener implements d {
    protected static final String TAG = "AbsBYDAutoRadioListener";

    public AbsBYDAutoRadioListener() {
        Log.d(TAG, TAG);
    }

    @Override // a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
        super.isConnected(z6);
    }

    public void onCurFreqChanged(int i7, int i8, int i9) {
        Log.d(TAG, "onCurFreqChanged Listener" + i7 + "," + i8 + "," + i9);
    }

    public final void onDataChanged(c cVar) {
        Log.d(TAG, "onDataChanged Listener" + cVar);
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged Listener" + i7 + "," + i7);
    }

    public void onEffectiveFreqSearched(int i7, int i8, int i9, int i10) {
        Log.d(TAG, "onEffectiveFreqSearched Listener" + i7 + "," + i8 + "," + i9 + "," + i10);
    }

    public void onError(int i7, String str) {
        Log.d(TAG, "onError Listener" + i7 + "," + str);
    }

    public void onRadioParamChanged(int i7, int i8, int i9, int i10) {
        Log.d(TAG, "onRadioParamChanged Listener" + i7 + "," + i8 + "," + i9 + "," + i10);
    }

    public void onRadioStateChanged(int i7) {
        Log.d(TAG, "onRadioStateChanged Listener " + i7);
    }

    public void onSearchResultChanged(int i7, int i8, int i9, int i10) {
        Log.d(TAG, "onSearchResultChanged Listener" + i7 + "," + i9 + "," + i9 + "," + i10);
    }
}

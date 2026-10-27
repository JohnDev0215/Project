package android.hardware.bydauto.radio;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoRadioListener implements d {
    protected static final String TAG = "AbsBYDAutoRadioListener";

    public AbsBYDAutoRadioListener() {
        Log.d(TAG, TAG);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    public void onCurFreqChanged(int i, int i2, int i3) {
        Log.d(TAG, "onCurFreqChanged Listener" + i + "," + i2 + "," + i3);
    }

    public final void onDataChanged(c cVar) {
        Log.d(TAG, "onDataChanged Listener" + cVar);
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged Listener" + i + "," + i);
    }

    public void onEffectiveFreqSearched(int i, int i2, int i3, int i4) {
        Log.d(TAG, "onEffectiveFreqSearched Listener" + i + "," + i2 + "," + i3 + "," + i4);
    }

    public void onError(int i, String str) {
        Log.d(TAG, "onError Listener" + i + "," + str);
    }

    public void onRadioParamChanged(int i, int i2, int i3, int i4) {
        Log.d(TAG, "onRadioParamChanged Listener" + i + "," + i2 + "," + i3 + "," + i4);
    }

    public void onRadioStateChanged(int i) {
        Log.d(TAG, "onRadioStateChanged Listener " + i);
    }

    public void onSearchResultChanged(int i, int i2, int i3, int i4) {
        Log.d(TAG, "onSearchResultChanged Listener" + i + "," + i3 + "," + i3 + "," + i4);
    }
}

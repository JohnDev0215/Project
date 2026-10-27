package android.hardware.bydauto.radio;

import M5.b;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.ac.tsimpl.a;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoRadioListener implements d {
    protected static final String TAG = "AbsBYDAutoRadioListener";

    public AbsBYDAutoRadioListener() {
        Log.d(TAG, TAG);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
    }

    public void onCurFreqChanged(int i7, int i10, int i11) {
        StringBuilder sbL = b.l("onCurFreqChanged Listener", ",", i7, i10, ",");
        sbL.append(i11);
        Log.d(TAG, sbL.toString());
    }

    public final void onDataChanged(c cVar) {
        Log.d(TAG, "onDataChanged Listener" + cVar);
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged Listener" + i7 + "," + i7);
    }

    public void onEffectiveFreqSearched(int i7, int i10, int i11, int i12) {
        StringBuilder sbL = b.l("onEffectiveFreqSearched Listener", ",", i7, i10, ",");
        sbL.append(i11);
        sbL.append(",");
        sbL.append(i12);
        Log.d(TAG, sbL.toString());
    }

    public void onError(int i7, String str) {
        Log.d(TAG, "onError Listener" + i7 + "," + str);
    }

    public void onRadioParamChanged(int i7, int i10, int i11, int i12) {
        StringBuilder sbL = b.l("onRadioParamChanged Listener", ",", i7, i10, ",");
        sbL.append(i11);
        sbL.append(",");
        sbL.append(i12);
        Log.d(TAG, sbL.toString());
    }

    public void onRadioStateChanged(int i7) {
        a.o("onRadioStateChanged Listener ", i7, TAG);
    }

    public void onSearchResultChanged(int i7, int i10, int i11, int i12) {
        StringBuilder sbL = b.l("onSearchResultChanged Listener", ",", i7, i11, ",");
        sbL.append(i11);
        sbL.append(",");
        sbL.append(i12);
        Log.d(TAG, sbL.toString());
    }
}

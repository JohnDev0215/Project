package android.hardware.bydauto.radio;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoRadioListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoRadioListener";

    @Override // android.hardware.IBYDAutoListener
    public /* synthetic */ void isConnected(boolean z) {
        IBYDAutoListener.CC.$default$isConnected(this, z);
    }

    public AbsBYDAutoRadioListener() {
        Log.d(TAG, TAG);
    }

    public void onRadioStateChanged(int i) {
        Log.d(TAG, "onRadioStateChanged Listener " + i);
    }

    public void onRadioParamChanged(int i, int i2, int i3, int i4) {
        Log.d(TAG, "onRadioParamChanged Listener" + i + "," + i2 + "," + i3 + "," + i4);
    }

    public void onCurFreqChanged(int i, int i2, int i3) {
        Log.d(TAG, "onCurFreqChanged Listener" + i + "," + i2 + "," + i3);
    }

    public void onEffectiveFreqSearched(int i, int i2, int i3, int i4) {
        Log.d(TAG, "onEffectiveFreqSearched Listener" + i + "," + i2 + "," + i3 + "," + i4);
    }

    public void onSearchResultChanged(int i, int i2, int i3, int i4) {
        Log.d(TAG, "onSearchResultChanged Listener" + i + "," + i3 + "," + i3 + "," + i4);
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
        Log.d(TAG, "onError Listener" + i + "," + str);
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
        Log.d(TAG, "onDataChanged Listener" + iBYDAutoEvent);
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged Listener" + i + "," + i);
    }
}

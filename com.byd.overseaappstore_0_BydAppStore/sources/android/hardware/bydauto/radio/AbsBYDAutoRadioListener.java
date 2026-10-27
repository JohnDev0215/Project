package android.hardware.bydauto.radio;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import spi.d;
import spi.e;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoRadioListener implements IBYDAutoListener {
    public static final String TAG = "AbsBYDAutoRadioListener";

    public AbsBYDAutoRadioListener() {
        Log.d(TAG, TAG);
    }

    public void onCurFreqChanged(int i, int i2, int i3) {
        StringBuilder sbL = e.l("onCurFreqChanged Listener", i, ",", i2, ",");
        sbL.append(i3);
        Log.d(TAG, sbL.toString());
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
        Log.d(TAG, "onDataChanged Listener" + iBYDAutoEvent);
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged Listener" + i + "," + i);
    }

    public void onEffectiveFreqSearched(int i, int i2, int i3, int i4) {
        StringBuilder sbL = e.l("onEffectiveFreqSearched Listener", i, ",", i2, ",");
        sbL.append(i3);
        sbL.append(",");
        sbL.append(i4);
        Log.d(TAG, sbL.toString());
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
        Log.d(TAG, "onError Listener" + i + "," + str);
    }

    public void onRadioParamChanged(int i, int i2, int i3, int i4) {
        StringBuilder sbL = e.l("onRadioParamChanged Listener", i, ",", i2, ",");
        sbL.append(i3);
        sbL.append(",");
        sbL.append(i4);
        Log.d(TAG, sbL.toString());
    }

    public void onRadioStateChanged(int i) {
        d.w("onRadioStateChanged Listener ", i, TAG);
    }

    public void onSearchResultChanged(int i, int i2, int i3, int i4) {
        StringBuilder sbL = e.l("onSearchResultChanged Listener", i, ",", i3, ",");
        sbL.append(i3);
        sbL.append(",");
        sbL.append(i4);
        Log.d(TAG, sbL.toString());
    }
}

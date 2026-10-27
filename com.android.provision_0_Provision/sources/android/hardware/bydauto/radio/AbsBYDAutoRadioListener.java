package android.hardware.bydauto.radio;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import com.bydux.api.common.tools.util.ListUtils;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoRadioListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoRadioListener";

    public AbsBYDAutoRadioListener() {
        Log.d(TAG, TAG);
    }

    public void onRadioStateChanged(int i) {
        Log.d(TAG, "onRadioStateChanged Listener " + i);
    }

    public void onRadioParamChanged(int i, int i2, int i3, int i4) {
        Log.d(TAG, "onRadioParamChanged Listener" + i + ListUtils.DEFAULT_JOIN_SEPARATOR + i2 + ListUtils.DEFAULT_JOIN_SEPARATOR + i3 + ListUtils.DEFAULT_JOIN_SEPARATOR + i4);
    }

    public void onCurFreqChanged(int i, int i2, int i3) {
        Log.d(TAG, "onCurFreqChanged Listener" + i + ListUtils.DEFAULT_JOIN_SEPARATOR + i2 + ListUtils.DEFAULT_JOIN_SEPARATOR + i3);
    }

    public void onEffectiveFreqSearched(int i, int i2, int i3, int i4) {
        Log.d(TAG, "onEffectiveFreqSearched Listener" + i + ListUtils.DEFAULT_JOIN_SEPARATOR + i2 + ListUtils.DEFAULT_JOIN_SEPARATOR + i3 + ListUtils.DEFAULT_JOIN_SEPARATOR + i4);
    }

    public void onSearchResultChanged(int i, int i2, int i3, int i4) {
        Log.d(TAG, "onSearchResultChanged Listener" + i + ListUtils.DEFAULT_JOIN_SEPARATOR + i3 + ListUtils.DEFAULT_JOIN_SEPARATOR + i3 + ListUtils.DEFAULT_JOIN_SEPARATOR + i4);
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
        Log.d(TAG, "onError Listener" + i + ListUtils.DEFAULT_JOIN_SEPARATOR + str);
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
        Log.d(TAG, "onDataChanged Listener" + iBYDAutoEvent);
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged Listener" + i + ListUtils.DEFAULT_JOIN_SEPARATOR + i);
    }
}

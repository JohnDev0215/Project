package android.hardware.bydauto.radio;

import a.a.c;
import a.a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import com.bydux.api.common.tools.util.ListUtils;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoRadioListener implements d {
    protected static final String TAG = "AbsBYDAutoRadioListener";

    public AbsBYDAutoRadioListener() {
        Log.d(TAG, TAG);
    }

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    public void onCurFreqChanged(int i, int i2, int i3) {
        Log.d(TAG, "onCurFreqChanged Listener" + i + ListUtils.DEFAULT_JOIN_SEPARATOR + i2 + ListUtils.DEFAULT_JOIN_SEPARATOR + i3);
    }

    public final void onDataChanged(c cVar) {
        Log.d(TAG, "onDataChanged Listener" + cVar);
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged Listener" + i + ListUtils.DEFAULT_JOIN_SEPARATOR + i);
    }

    public void onEffectiveFreqSearched(int i, int i2, int i3, int i4) {
        Log.d(TAG, "onEffectiveFreqSearched Listener" + i + ListUtils.DEFAULT_JOIN_SEPARATOR + i2 + ListUtils.DEFAULT_JOIN_SEPARATOR + i3 + ListUtils.DEFAULT_JOIN_SEPARATOR + i4);
    }

    public void onError(int i, String str) {
        Log.d(TAG, "onError Listener" + i + ListUtils.DEFAULT_JOIN_SEPARATOR + str);
    }

    public void onRadioParamChanged(int i, int i2, int i3, int i4) {
        Log.d(TAG, "onRadioParamChanged Listener" + i + ListUtils.DEFAULT_JOIN_SEPARATOR + i2 + ListUtils.DEFAULT_JOIN_SEPARATOR + i3 + ListUtils.DEFAULT_JOIN_SEPARATOR + i4);
    }

    public void onRadioStateChanged(int i) {
        Log.d(TAG, "onRadioStateChanged Listener " + i);
    }

    public void onSearchResultChanged(int i, int i2, int i3, int i4) {
        Log.d(TAG, "onSearchResultChanged Listener" + i + ListUtils.DEFAULT_JOIN_SEPARATOR + i3 + ListUtils.DEFAULT_JOIN_SEPARATOR + i3 + ListUtils.DEFAULT_JOIN_SEPARATOR + i4);
    }
}

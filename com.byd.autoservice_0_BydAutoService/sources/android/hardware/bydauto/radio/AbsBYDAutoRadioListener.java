package android.hardware.bydauto.radio;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;
import com.bydux.api.common.tools.util.ListUtils;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoRadioListener implements d {
    public static final String TAG = "AbsBYDAutoRadioListener";

    public AbsBYDAutoRadioListener() {
        Log.d(TAG, TAG);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z3) {
        super.isConnected(z3);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z3) {
        super.isOptimizationApi(z3);
    }

    public void onCurFreqChanged(int i3, int i4, int i5) {
        Log.d(TAG, "onCurFreqChanged Listener" + i3 + ListUtils.DEFAULT_JOIN_SEPARATOR + i4 + ListUtils.DEFAULT_JOIN_SEPARATOR + i5);
    }

    public final void onDataChanged(c cVar) {
        Log.d(TAG, "onDataChanged Listener" + cVar);
    }

    public void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged Listener" + i3 + ListUtils.DEFAULT_JOIN_SEPARATOR + i3);
    }

    public void onEffectiveFreqSearched(int i3, int i4, int i5, int i6) {
        Log.d(TAG, "onEffectiveFreqSearched Listener" + i3 + ListUtils.DEFAULT_JOIN_SEPARATOR + i4 + ListUtils.DEFAULT_JOIN_SEPARATOR + i5 + ListUtils.DEFAULT_JOIN_SEPARATOR + i6);
    }

    public void onError(int i3, String str) {
        Log.d(TAG, "onError Listener" + i3 + ListUtils.DEFAULT_JOIN_SEPARATOR + str);
    }

    public void onRadioParamChanged(int i3, int i4, int i5, int i6) {
        Log.d(TAG, "onRadioParamChanged Listener" + i3 + ListUtils.DEFAULT_JOIN_SEPARATOR + i4 + ListUtils.DEFAULT_JOIN_SEPARATOR + i5 + ListUtils.DEFAULT_JOIN_SEPARATOR + i6);
    }

    public void onRadioStateChanged(int i3) {
        Log.d(TAG, "onRadioStateChanged Listener " + i3);
    }

    public void onSearchResultChanged(int i3, int i4, int i5, int i6) {
        Log.d(TAG, "onSearchResultChanged Listener" + i3 + ListUtils.DEFAULT_JOIN_SEPARATOR + i5 + ListUtils.DEFAULT_JOIN_SEPARATOR + i5 + ListUtils.DEFAULT_JOIN_SEPARATOR + i6);
    }
}

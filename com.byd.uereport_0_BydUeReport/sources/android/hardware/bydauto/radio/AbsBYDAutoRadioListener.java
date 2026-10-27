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
    public /* bridge */ /* synthetic */ void isConnected(boolean z7) {
        super.isConnected(z7);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z7) {
        super.isOptimizationApi(z7);
    }

    public void onCurFreqChanged(int i8, int i9, int i10) {
        Log.d(TAG, "onCurFreqChanged Listener" + i8 + ListUtils.DEFAULT_JOIN_SEPARATOR + i9 + ListUtils.DEFAULT_JOIN_SEPARATOR + i10);
    }

    public final void onDataChanged(c cVar) {
        Log.d(TAG, "onDataChanged Listener" + cVar);
    }

    public void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged Listener" + i8 + ListUtils.DEFAULT_JOIN_SEPARATOR + i8);
    }

    public void onEffectiveFreqSearched(int i8, int i9, int i10, int i11) {
        Log.d(TAG, "onEffectiveFreqSearched Listener" + i8 + ListUtils.DEFAULT_JOIN_SEPARATOR + i9 + ListUtils.DEFAULT_JOIN_SEPARATOR + i10 + ListUtils.DEFAULT_JOIN_SEPARATOR + i11);
    }

    public void onError(int i8, String str) {
        Log.d(TAG, "onError Listener" + i8 + ListUtils.DEFAULT_JOIN_SEPARATOR + str);
    }

    public void onRadioParamChanged(int i8, int i9, int i10, int i11) {
        Log.d(TAG, "onRadioParamChanged Listener" + i8 + ListUtils.DEFAULT_JOIN_SEPARATOR + i9 + ListUtils.DEFAULT_JOIN_SEPARATOR + i10 + ListUtils.DEFAULT_JOIN_SEPARATOR + i11);
    }

    public void onRadioStateChanged(int i8) {
        Log.d(TAG, "onRadioStateChanged Listener " + i8);
    }

    public void onSearchResultChanged(int i8, int i9, int i10, int i11) {
        Log.d(TAG, "onSearchResultChanged Listener" + i8 + ListUtils.DEFAULT_JOIN_SEPARATOR + i10 + ListUtils.DEFAULT_JOIN_SEPARATOR + i10 + ListUtils.DEFAULT_JOIN_SEPARATOR + i11);
    }
}

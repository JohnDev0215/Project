package android.hardware.bydauto.showcarmode;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public interface IShowCarModelChangeListener extends d {
    public static final String TAG = "IShowCarModelChangeListener";

    @Override // b.d
    /* bridge */ /* synthetic */ default void isConnected(boolean z6) {
    }

    default void onAirCtrlChangeEvent(int i7) {
        Log.d(TAG, "onAirCtrlChangeEvent");
    }

    default void onAirCtrlPressEvent(int i7) {
        Log.d(TAG, "onAirCtrlPressEvent");
    }

    default void onDataChanged(c cVar) {
    }

    default void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
    }

    default void onError(int i7, String str) {
    }

    @Deprecated
    void showCarGearChange(int i7);

    void showCarModelChange(int i7);
}

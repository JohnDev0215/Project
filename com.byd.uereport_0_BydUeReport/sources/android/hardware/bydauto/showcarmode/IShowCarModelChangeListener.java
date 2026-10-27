package android.hardware.bydauto.showcarmode;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public interface IShowCarModelChangeListener extends d {
    public static final String TAG = "IShowCarModelChangeListener";

    @Override // b.d
    /* bridge */ /* synthetic */ default void isConnected(boolean z7) {
        super.isConnected(z7);
    }

    @Override // b.d
    /* bridge */ /* synthetic */ default void isOptimizationApi(boolean z7) {
        super.isOptimizationApi(z7);
    }

    default void onAirCtrlChangeEvent(int i8) {
        Log.d(TAG, "onAirCtrlChangeEvent");
    }

    default void onAirCtrlPressEvent(int i8) {
        Log.d(TAG, "onAirCtrlPressEvent");
    }

    default void onDataChanged(c cVar) {
    }

    default void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
    }

    default void onError(int i8, String str) {
    }

    @Deprecated
    void showCarGearChange(int i8);

    void showCarModelChange(int i8);
}

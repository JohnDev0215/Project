package android.hardware.bydauto.showcarmode;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public interface IShowCarModelChangeListener extends d {
    public static final String TAG = "IShowCarModelChangeListener";

    @Override // b.d
    /* bridge */ /* synthetic */ default void isConnected(boolean z3) {
        super.isConnected(z3);
    }

    @Override // b.d
    /* bridge */ /* synthetic */ default void isOptimizationApi(boolean z3) {
        super.isOptimizationApi(z3);
    }

    default void onAirCtrlChangeEvent(int i3) {
        Log.d(TAG, "onAirCtrlChangeEvent");
    }

    default void onAirCtrlPressEvent(int i3) {
        Log.d(TAG, "onAirCtrlPressEvent");
    }

    default void onDataChanged(c cVar) {
    }

    default void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
    }

    default void onError(int i3, String str) {
    }

    @Deprecated
    void showCarGearChange(int i3);

    void showCarModelChange(int i3);
}

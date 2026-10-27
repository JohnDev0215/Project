package android.hardware.bydauto.showcarmode;

import a.a.c;
import a.a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public interface IShowCarModelChangeListener extends d {
    public static final String TAG = "IShowCarModelChangeListener";

    @Override // a.a.d
    /* bridge */ /* synthetic */ default void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.a.d
    /* bridge */ /* synthetic */ default void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    default void onAirCtrlChangeEvent(int i2) {
        Log.d(TAG, "onAirCtrlChangeEvent");
    }

    default void onAirCtrlPressEvent(int i2) {
        Log.d(TAG, "onAirCtrlPressEvent");
    }

    default void onDataChanged(c cVar) {
    }

    default void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue) {
    }

    default void onError(int i2, String str) {
    }

    @Deprecated
    void showCarGearChange(int i2);

    void showCarModelChange(int i2);
}

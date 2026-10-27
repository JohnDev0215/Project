package android.hardware.bydauto.showcarmode;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import defpackage.d;
import defpackage.e;

/* JADX INFO: loaded from: classes.dex */
public interface IShowCarModelChangeListener extends e {
    public static final String TAG = "IShowCarModelChangeListener";

    @Override // defpackage.e
    /* bridge */ /* synthetic */ default void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // defpackage.e
    /* bridge */ /* synthetic */ default void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    default void onAirCtrlChangeEvent(int i) {
        Log.d(TAG, "onAirCtrlChangeEvent");
    }

    default void onAirCtrlPressEvent(int i) {
        Log.d(TAG, "onAirCtrlPressEvent");
    }

    default void onDataChanged(d dVar) {
    }

    default void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    default void onError(int i, String str) {
    }

    @Deprecated
    void showCarGearChange(int i);

    void showCarModelChange(int i);
}

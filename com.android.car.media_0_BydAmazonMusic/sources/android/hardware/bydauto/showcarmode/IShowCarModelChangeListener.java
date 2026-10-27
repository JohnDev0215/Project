package android.hardware.bydauto.showcarmode;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public interface IShowCarModelChangeListener extends IBYDAutoListener {
    public static final String TAG = "IShowCarModelChangeListener";

    default void onAirCtrlChangeEvent(int i) {
        Log.d(TAG, "onAirCtrlChangeEvent");
    }

    default void onAirCtrlPressEvent(int i) {
        Log.d(TAG, "onAirCtrlPressEvent");
    }

    @Override // android.hardware.IBYDAutoListener
    default void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    @Override // android.hardware.IBYDAutoListener
    default void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    default void onError(int i, String str) {
    }

    @Deprecated
    void showCarGearChange(int i);

    void showCarModelChange(int i);
}

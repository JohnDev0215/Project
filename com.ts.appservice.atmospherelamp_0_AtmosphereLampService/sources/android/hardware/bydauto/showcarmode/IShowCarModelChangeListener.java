package android.hardware.bydauto.showcarmode;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public interface IShowCarModelChangeListener extends IBYDAutoListener {
    public static final String TAG = "IShowCarModelChangeListener";

    @Deprecated
    void showCarGearChange(int i);

    void showCarModelChange(int i);

    @Override // android.hardware.IBYDAutoListener
    default void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    default void onDataChanged(IBYDAutoEvent var1) {
    }

    @Override // android.hardware.IBYDAutoListener
    default void onError(int var1, String var2) {
    }

    default void onAirCtrlChangeEvent(int eventType) {
        Log.d(TAG, "onAirCtrlChangeEvent");
    }

    default void onAirCtrlPressEvent(int eventType) {
        Log.d(TAG, "onAirCtrlPressEvent");
    }
}

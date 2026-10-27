package android.hardware.bydauto.showcarmode;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public interface IShowCarModelChangeListener extends IBYDAutoListener {
    public static final String TAG = "IShowCarModelChangeListener";

    @Deprecated
    void showCarGearChange(int i);

    void showCarModelChange(int i);

    default void onAirCtrlChangeEvent(int i) {
    }

    default void onAirCtrlPressEvent(int i) {
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
}

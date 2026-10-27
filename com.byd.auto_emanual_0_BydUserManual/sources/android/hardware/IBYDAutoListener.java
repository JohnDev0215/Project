package android.hardware;

import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public interface IBYDAutoListener {
    default void isConnected(boolean z) {
    }

    default void isOptimizationApi(boolean z) {
    }

    void onDataChanged(IBYDAutoEvent iBYDAutoEvent);

    void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue);

    void onError(int i, String str);
}

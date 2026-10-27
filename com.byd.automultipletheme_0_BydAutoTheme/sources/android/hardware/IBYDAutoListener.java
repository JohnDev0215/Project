package android.hardware;

import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public interface IBYDAutoListener {

    /* JADX INFO: renamed from: android.hardware.IBYDAutoListener$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$isConnected(IBYDAutoListener _this, boolean z) {
        }
    }

    void isConnected(boolean z);

    void onDataChanged(IBYDAutoEvent iBYDAutoEvent);

    void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue);

    void onError(int i, String str);
}

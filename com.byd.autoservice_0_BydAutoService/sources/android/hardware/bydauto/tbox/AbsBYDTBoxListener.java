package android.hardware.bydauto.tbox;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDTBoxListener implements d {
    public static final String TAG = "AbsBYDTBoxListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z3) {
        super.isConnected(z3);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z3) {
        super.isOptimizationApi(z3);
    }

    public void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onError(int i3, String str) {
    }

    public void onTBoxCmd2IVI(int i3) {
        Log.w(TAG, "onTBoxCmd2IVI cmd : " + i3);
    }

    public void onUpdateTrafficData() {
        Log.w(TAG, "onUpdateTrafficData");
    }
}

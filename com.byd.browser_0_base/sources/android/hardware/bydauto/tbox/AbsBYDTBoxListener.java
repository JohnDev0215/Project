package android.hardware.bydauto.tbox;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import defpackage.C0175b;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDTBoxListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDTBoxListener";

    public void onTBoxCmd2IVI(int i) {
        C0175b.s("onTBoxCmd2IVI cmd : ", i, TAG);
    }

    public void onUpdateTrafficData() {
        Log.w(TAG, "onUpdateTrafficData");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }
}

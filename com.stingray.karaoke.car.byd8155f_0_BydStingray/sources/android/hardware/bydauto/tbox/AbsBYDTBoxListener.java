package android.hardware.bydauto.tbox;

import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.ac.tsimpl.a;
import b.c;
import b.d;
import h6.n;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDTBoxListener implements d {
    protected static final String TAG = "AbsBYDTBoxListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
    }

    public void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onError(int i7, String str) {
    }

    public void onTBoxCmd2IVI(int i7) {
        a.r("onTBoxCmd2IVI cmd : ", i7, TAG);
    }

    public void onUpdateTrafficData() {
        n.t(TAG, "onUpdateTrafficData");
    }
}

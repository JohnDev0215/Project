package android.hardware.bydauto.tbox;

import a.c;
import a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDTBoxListener implements d {
    protected static final String TAG = "AbsBYDTBoxListener";

    @Override // a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
        super.isConnected(z6);
    }

    public void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onError(int i7, String str) {
    }

    public void onTBoxCmd2IVI(int i7) {
        Log.w(TAG, "onTBoxCmd2IVI cmd : " + i7);
    }

    public void onUpdateTrafficData() {
        Log.w(TAG, "onUpdateTrafficData");
    }
}

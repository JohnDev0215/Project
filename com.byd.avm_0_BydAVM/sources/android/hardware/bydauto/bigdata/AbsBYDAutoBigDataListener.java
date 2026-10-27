package android.hardware.bydauto.bigdata;

import a.a.c;
import a.a.d;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoBigDataListener implements d {
    protected static final String TAG = "AbsBYDAutoBigDataListener";

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    public final void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onError(int i, String str) {
    }

    public void onNeedRendRegisterTable(int i) {
    }

    public void onWholeFrameDataChanged(byte[] bArr) {
    }
}

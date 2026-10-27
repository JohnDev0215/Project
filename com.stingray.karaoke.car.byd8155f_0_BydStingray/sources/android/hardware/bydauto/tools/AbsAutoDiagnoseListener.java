package android.hardware.bydauto.tools;

import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.ac.tsimpl.a;
import android.os.Bundle;
import b.c;
import b.d;
import h6.n;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsAutoDiagnoseListener implements d {
    private static final String TAG = "AbsAutoDiagnoseListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
    }

    public void onDataChanged(c cVar) {
        n.t(TAG, "onDataChanged: ");
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        n.t(TAG, "onDataEventChanged: ");
    }

    public void onError(int i7, String str) {
        n.t(TAG, "onError: ");
    }

    public void onHWTemperature(Bundle bundle) {
    }

    public void onSynchronousQNXData(int i7) {
        a.r("onSynchronousQNXData: ", i7, TAG);
    }
}

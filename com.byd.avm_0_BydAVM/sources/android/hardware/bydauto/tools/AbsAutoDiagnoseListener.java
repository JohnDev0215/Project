package android.hardware.bydauto.tools;

import a.a.c;
import a.a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.os.Bundle;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsAutoDiagnoseListener implements d {
    private static final String TAG = "AbsAutoDiagnoseListener";

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    public void onDataChanged(c cVar) {
        Log.w(TAG, "onDataChanged: ");
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.w(TAG, "onDataEventChanged: ");
    }

    public void onError(int i, String str) {
        Log.w(TAG, "onError: ");
    }

    public void onHWTemperature(Bundle bundle) {
    }

    public void onSynchronousQNXData(int i) {
        Log.w(TAG, "onSynchronousQNXData: " + i);
    }
}

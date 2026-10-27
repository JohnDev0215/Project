package android.hardware.bydauto.tools;

import a.c;
import a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.os.Bundle;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsAutoDiagnoseListener implements d {
    private static final String TAG = "AbsAutoDiagnoseListener";

    @Override // a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
        super.isConnected(z6);
    }

    public void onDataChanged(c cVar) {
        Log.w(TAG, "onDataChanged: ");
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        Log.w(TAG, "onDataEventChanged: ");
    }

    public void onError(int i7, String str) {
        Log.w(TAG, "onError: ");
    }

    public void onHWTemperature(Bundle bundle) {
    }

    public void onSynchronousQNXData(int i7) {
        Log.w(TAG, "onSynchronousQNXData: " + i7);
    }
}

package android.hardware.bydauto.tools;

import android.hardware.bydauto.BYDAutoEventValue;
import android.os.Bundle;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsAutoDiagnoseListener implements d {
    private static final String TAG = "AbsAutoDiagnoseListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z7) {
        super.isConnected(z7);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z7) {
        super.isOptimizationApi(z7);
    }

    public void onDataChanged(c cVar) {
        Log.w(TAG, "onDataChanged: ");
    }

    public void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
        Log.w(TAG, "onDataEventChanged: ");
    }

    public void onError(int i8, String str) {
        Log.w(TAG, "onError: ");
    }

    public void onHWTemperature(Bundle bundle) {
    }

    public void onSynchronousQNXData(int i8) {
        Log.w(TAG, "onSynchronousQNXData: " + i8);
    }
}

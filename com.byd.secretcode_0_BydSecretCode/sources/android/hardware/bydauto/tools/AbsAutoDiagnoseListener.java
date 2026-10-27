package android.hardware.bydauto.tools;

import android.hardware.bydauto.BYDAutoEventValue;
import android.os.Bundle;
import android.util.Log;
import defpackage.d;
import defpackage.e;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsAutoDiagnoseListener implements e {
    private static final String TAG = "AbsAutoDiagnoseListener";

    @Override // defpackage.e
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // defpackage.e
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onDataChanged(d dVar) {
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

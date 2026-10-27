package android.hardware.bydauto.tools;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.os.Bundle;
import android.util.Log;
import defpackage.C0175b;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsAutoDiagnoseListener implements IBYDAutoListener {
    private static final String TAG = "AbsAutoDiagnoseListener";

    @Override // android.hardware.IBYDAutoListener
    public void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
        Log.w(TAG, "onDataChanged: ");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.w(TAG, "onDataEventChanged: ");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
        Log.w(TAG, "onError: ");
    }

    public void onSynchronousQNXData(int i) {
        C0175b.s("onSynchronousQNXData: ", i, TAG);
    }

    public void onHWTemperature(Bundle bundle) {
    }
}

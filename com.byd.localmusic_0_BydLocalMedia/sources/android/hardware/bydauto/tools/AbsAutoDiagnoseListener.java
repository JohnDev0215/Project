package android.hardware.bydauto.tools;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.os.Bundle;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbsAutoDiagnoseListener implements IBYDAutoListener {
    private static final String TAG = "AbsAutoDiagnoseListener";

    public void onHWTemperature(Bundle bundle) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
        Log.w(TAG, "onDataChanged: ");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
        Log.w(TAG, "onError: ");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.w(TAG, "onDataEventChanged: ");
    }

    public void onSynchronousQNXData(int i) {
        Log.w(TAG, "onSynchronousQNXData: " + i);
    }
}

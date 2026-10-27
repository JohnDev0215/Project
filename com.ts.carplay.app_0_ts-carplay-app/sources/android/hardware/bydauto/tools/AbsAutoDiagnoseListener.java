package android.hardware.bydauto.tools;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.os.Bundle;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsAutoDiagnoseListener implements IBYDAutoListener {
    private static final String TAG = "AbsAutoDiagnoseListener";

    @Override // android.hardware.IBYDAutoListener
    public void onDataChanged(IBYDAutoEvent var1) {
        Log.w(TAG, "onDataChanged: ");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int var1, String var2) {
        Log.w(TAG, "onError: ");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int var1, BYDAutoEventValue var2) {
        Log.w(TAG, "onDataEventChanged: ");
    }

    public void onSynchronousQNXData(int state) {
        Log.w(TAG, "onSynchronousQNXData: " + state);
    }

    public void onHWTemperature(Bundle bundle) {
    }
}

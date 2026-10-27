package android.hardware.bydauto.dms;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class IDMSListener implements IBYDAutoListener {
    private static final String TAG = "IDMSListener";

    public IDMSListener() {
        Log.d(TAG, TAG);
    }

    public void onAttentionDetectChanged(int i) {
    }

    public void onAttentionSwitchChanged(int i) {
    }

    public void onBlockStatusChanged(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onDmsModeChanged(int i) {
    }

    public void onDmsSwitchChanged(int i) {
    }

    public void onDriverNotResponse() {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }

    public void onFatigueDetectChanged(int i) {
    }

    public void onFatigueSwitchChanged(int i) {
    }

    public void onNotifyDmsServerState(int i) {
    }

    public void onRestoreFatigueDetect() {
    }
}

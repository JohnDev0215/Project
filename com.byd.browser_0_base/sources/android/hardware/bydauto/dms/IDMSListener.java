package android.hardware.bydauto.dms;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class IDMSListener implements IBYDAutoListener {
    private static final String TAG = "IDMSListener";

    public void onDriverNotResponse() {
    }

    public void onRestoreFatigueDetect() {
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

    public void onDmsModeChanged(int i) {
    }

    public void onDmsSwitchChanged(int i) {
    }

    public void onFatigueDetectChanged(int i) {
    }

    public void onFatigueSwitchChanged(int i) {
    }

    public void onNotifyDmsServerState(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }
}

package android.hardware.bydauto.dms;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.b.c;
import b.b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class IDMSListener implements d {
    private static final String TAG = "IDMSListener";

    public IDMSListener() {
        Log.d(TAG, TAG);
    }

    @Override // b.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // b.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onAttentionDetectChanged(int i) {
    }

    public void onAttentionSwitchChanged(int i) {
    }

    public void onBlockStatusChanged(int i) {
    }

    public final void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onDmsModeChanged(int i) {
    }

    public void onDmsSwitchChanged(int i) {
    }

    public void onDriverNotResponse() {
    }

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

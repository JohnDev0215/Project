package android.hardware.bydauto.dms;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class IDMSListener implements d {
    private static final String TAG = "IDMSListener";

    public IDMSListener() {
        Log.d(TAG, TAG);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onAttentionDetectChanged(int i2) {
    }

    public void onAttentionSwitchChanged(int i2) {
    }

    public void onBlockStatusChanged(int i2) {
    }

    public final void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onDmsModeChanged(int i2) {
    }

    public void onDmsSwitchChanged(int i2) {
    }

    public void onDriverNotResponse() {
    }

    public void onError(int i2, String str) {
    }

    public void onFatigueDetectChanged(int i2) {
    }

    public void onFatigueSwitchChanged(int i2) {
    }

    public void onNotifyDmsServerState(int i2) {
    }

    public void onRestoreFatigueDetect() {
    }
}

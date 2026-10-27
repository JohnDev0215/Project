package android.hardware.bydauto.dms;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class IDMSListener implements d {
    private static final String TAG = "IDMSListener";

    public IDMSListener() {
        Log.d(TAG, TAG);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z7) {
        super.isConnected(z7);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z7) {
        super.isOptimizationApi(z7);
    }

    public void onAttentionDetectChanged(int i8) {
    }

    public void onAttentionSwitchChanged(int i8) {
    }

    public void onBlockStatusChanged(int i8) {
    }

    public final void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onDmsModeChanged(int i8) {
    }

    public void onDmsSwitchChanged(int i8) {
    }

    public void onDriverNotResponse() {
    }

    public void onError(int i8, String str) {
    }

    public void onFatigueDetectChanged(int i8) {
    }

    public void onFatigueSwitchChanged(int i8) {
    }

    public void onNotifyDmsServerState(int i8) {
    }

    public void onRestoreFatigueDetect() {
    }
}

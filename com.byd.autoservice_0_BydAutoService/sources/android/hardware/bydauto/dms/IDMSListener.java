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
    public /* bridge */ /* synthetic */ void isConnected(boolean z3) {
        super.isConnected(z3);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z3) {
        super.isOptimizationApi(z3);
    }

    public void onAttentionDetectChanged(int i3) {
    }

    public void onAttentionSwitchChanged(int i3) {
    }

    public void onBlockStatusChanged(int i3) {
    }

    public final void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onDmsModeChanged(int i3) {
    }

    public void onDmsSwitchChanged(int i3) {
    }

    public void onDriverNotResponse() {
    }

    public void onError(int i3, String str) {
    }

    public void onFatigueDetectChanged(int i3) {
    }

    public void onFatigueSwitchChanged(int i3) {
    }

    public void onNotifyDmsServerState(int i3) {
    }

    public void onRestoreFatigueDetect() {
    }
}

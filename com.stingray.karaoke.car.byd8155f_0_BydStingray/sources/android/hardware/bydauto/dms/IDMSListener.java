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
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
    }

    public void onAttentionDetectChanged(int i7) {
    }

    public void onAttentionSwitchChanged(int i7) {
    }

    public void onBlockStatusChanged(int i7) {
    }

    public final void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onDmsModeChanged(int i7) {
    }

    public void onDmsSwitchChanged(int i7) {
    }

    public void onDriverNotResponse() {
    }

    public void onError(int i7, String str) {
    }

    public void onFatigueDetectChanged(int i7) {
    }

    public void onFatigueSwitchChanged(int i7) {
    }

    public void onNotifyDmsServerState(int i7) {
    }

    public void onRestoreFatigueDetect() {
    }
}

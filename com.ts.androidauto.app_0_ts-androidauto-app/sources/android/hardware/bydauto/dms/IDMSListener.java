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

    public void onDmsSwitchChanged(int state) {
    }

    public void onFatigueSwitchChanged(int state) {
    }

    public void onAttentionSwitchChanged(int state) {
    }

    public void onFatigueDetectChanged(int state) {
    }

    public void onAttentionDetectChanged(int state) {
    }

    public void onBlockStatusChanged(int state) {
    }

    public void onDriverNotResponse() {
    }

    public void onRestoreFatigueDetect() {
    }

    public void onNotifyDmsServerState(int state) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int errCode, String errMessage) {
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent event) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
    }

    public void onDmsModeChanged(int mode) {
    }
}

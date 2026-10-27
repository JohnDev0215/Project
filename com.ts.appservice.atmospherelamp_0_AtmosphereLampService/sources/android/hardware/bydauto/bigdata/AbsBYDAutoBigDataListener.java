package android.hardware.bydauto.bigdata;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoBigDataListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoBigDataListener";

    public void onNeedRendRegisterTable(int value) {
    }

    public void onWholeFrameDataChanged(byte[] data) {
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
}

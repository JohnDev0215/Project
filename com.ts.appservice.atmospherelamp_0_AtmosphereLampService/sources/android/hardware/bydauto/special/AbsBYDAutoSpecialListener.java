package android.hardware.bydauto.special;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSpecialListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoSpecialListener";

    public AbsBYDAutoSpecialListener() {
        throw new RuntimeException("Stub!");
    }

    public void onWheelSpeedChanged(byte[] speed) {
        throw new RuntimeException("Stub!");
    }

    public void onWheelDirectionChanged(byte[] direction) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int errCode, String errMessage) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent event) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
        throw new RuntimeException("Stub!");
    }
}

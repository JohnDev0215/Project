package android.hardware.bydauto.special;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbsBYDAutoSpecialListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoSpecialListener";

    public AbsBYDAutoSpecialListener() {
        throw new RuntimeException("Stub!");
    }

    public void onWheelSpeedChanged(byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public void onWheelDirectionChanged(byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        throw new RuntimeException("Stub!");
    }
}

package android.hardware.bydauto.special;

import android.hardware.bydauto.BYDAutoEventValue;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSpecialListener implements d {
    public static final String TAG = "AbsBYDAutoSpecialListener";

    public AbsBYDAutoSpecialListener() {
        throw new RuntimeException("Stub!");
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z7) {
        super.isConnected(z7);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z7) {
        super.isOptimizationApi(z7);
    }

    public final void onDataChanged(c cVar) {
        throw new RuntimeException("Stub!");
    }

    public void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
        throw new RuntimeException("Stub!");
    }

    public void onError(int i8, String str) {
        throw new RuntimeException("Stub!");
    }

    public void onWheelDirectionChanged(byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public void onWheelSpeedChanged(byte[] bArr) {
        throw new RuntimeException("Stub!");
    }
}

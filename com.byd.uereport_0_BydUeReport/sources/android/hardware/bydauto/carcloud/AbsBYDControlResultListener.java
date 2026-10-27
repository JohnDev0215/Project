package android.hardware.bydauto.carcloud;

import android.hardware.bydauto.BYDAutoEventValue;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDControlResultListener implements d {
    public static final String TAG = "AbsBYDControlResultListener";

    public AbsBYDControlResultListener() {
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
}

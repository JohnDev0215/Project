package android.hardware.bydauto.rescue;

import android.hardware.bydauto.BYDAutoEventValue;
import defpackage.d;
import defpackage.e;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoRescueListener implements e {
    public static final String TAG = "AbsBYDAutoRescueListener";

    public AbsBYDAutoRescueListener() {
        throw new RuntimeException("Stub!");
    }

    @Override // defpackage.e
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // defpackage.e
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onAirbagStateChanged(int i) {
        throw new RuntimeException("Stub!");
    }

    public final void onDataChanged(d dVar) {
        throw new RuntimeException("Stub!");
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        throw new RuntimeException("Stub!");
    }

    public void onError(int i, String str) {
        throw new RuntimeException("Stub!");
    }

    public void onInfoFromMCUChanged(int i) {
        throw new RuntimeException("Stub!");
    }
}

package android.hardware.bydauto.rse;

import android.hardware.bydauto.BYDAutoEventValue;
import defpackage.d;
import defpackage.e;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoRSEListener implements e {
    public static final String TAG = "AbsBYDAutoRSEListener";

    public AbsBYDAutoRSEListener() {
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

    public void onAudioRequestFromRseChanged(int i, int i2) {
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

    public void onWriteFeedbackFromRseChanged(int i, int i2, int i3) {
        throw new RuntimeException("Stub!");
    }
}

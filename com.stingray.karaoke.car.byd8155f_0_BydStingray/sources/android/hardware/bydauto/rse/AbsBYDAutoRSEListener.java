package android.hardware.bydauto.rse;

import android.hardware.bydauto.BYDAutoEventValue;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoRSEListener implements d {
    protected static final String TAG = "AbsBYDAutoRSEListener";

    public AbsBYDAutoRSEListener() {
        throw new RuntimeException("Stub!");
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
    }

    public void onAudioRequestFromRseChanged(int i7, int i10) {
        throw new RuntimeException("Stub!");
    }

    public final void onDataChanged(c cVar) {
        throw new RuntimeException("Stub!");
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        throw new RuntimeException("Stub!");
    }

    public void onError(int i7, String str) {
        throw new RuntimeException("Stub!");
    }

    public void onWriteFeedbackFromRseChanged(int i7, int i10, int i11) {
        throw new RuntimeException("Stub!");
    }
}

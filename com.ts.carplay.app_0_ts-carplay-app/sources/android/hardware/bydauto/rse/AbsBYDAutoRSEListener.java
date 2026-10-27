package android.hardware.bydauto.rse;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoRSEListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoRSEListener";

    public AbsBYDAutoRSEListener() {
        throw new RuntimeException("Stub!");
    }

    public void onWriteFeedbackFromRseChanged(int source, int item, int state) {
        throw new RuntimeException("Stub!");
    }

    public void onAudioRequestFromRseChanged(int source, int state) {
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

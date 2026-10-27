package android.hardware.bydauto.safetybelt;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSafetyBeltListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoSafetyBeltListener";

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    public void onDriverBeltStatusChanged(int i) {
    }

    public void onSafetyBeltMsrStateChanged(int i) {
    }

    public void onSafetyBeltReminderReceived(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }

    public void onMessage5sOnlineStateChanged(int i, int i2) {
    }

    public void onPassengerStatusChanged(int i, int i2) {
    }

    public void onSafetyBeltStatusChanged(int i, int i2) {
    }
}

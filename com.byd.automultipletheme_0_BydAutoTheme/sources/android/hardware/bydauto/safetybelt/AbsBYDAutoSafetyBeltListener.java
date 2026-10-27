package android.hardware.bydauto.safetybelt;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSafetyBeltListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoSafetyBeltListener";

    @Override // android.hardware.IBYDAutoListener
    public /* synthetic */ void isConnected(boolean z) {
        IBYDAutoListener.CC.$default$isConnected(this, z);
    }

    public AbsBYDAutoSafetyBeltListener() {
        Log.v(TAG, TAG);
    }

    public void onSafetyBeltStatusChanged(int i, int i2) {
        Log.v(TAG, "onSafetyBeltStatusChanged");
    }

    public void onPassengerStatusChanged(int i, int i2) {
        Log.v(TAG, "onPassengerStatusChanged");
    }

    public void onSafetyBeltReminderReceived(int i) {
        Log.v(TAG, "onSafetyBeltReminderReceived");
    }

    public void onSafetyBeltMsrStateChanged(int i) {
        Log.v(TAG, "onSafetyBeltMsrStateChanged");
    }

    public void onMessage5sOnlineStateChanged(int i, int i2) {
        Log.v(TAG, "onMessage5sOnlineStateChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
        Log.v(TAG, "onError");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
        Log.v(TAG, "onDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }
}

package android.hardware.bydauto.safetybelt;

import a.c;
import a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSafetyBeltListener implements d {
    protected static final String TAG = "AbsBYDAutoSafetyBeltListener";

    public AbsBYDAutoSafetyBeltListener() {
        Log.v(TAG, TAG);
    }

    @Override // a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
        super.isConnected(z6);
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onError(int i7, String str) {
        Log.v(TAG, "onError");
    }

    public void onMessage5sOnlineStateChanged(int i7, int i8) {
        Log.v(TAG, "onMessage5sOnlineStateChanged");
    }

    public void onPassengerStatusChanged(int i7, int i8) {
        Log.v(TAG, "onPassengerStatusChanged");
    }

    public void onSafetyBeltMsrStateChanged(int i7) {
        Log.v(TAG, "onSafetyBeltMsrStateChanged");
    }

    public void onSafetyBeltReminderReceived(int i7) {
        Log.v(TAG, "onSafetyBeltReminderReceived");
    }

    public void onSafetyBeltStatusChanged(int i7, int i8) {
        Log.v(TAG, "onSafetyBeltStatusChanged");
    }
}

package android.hardware.bydauto.safetybelt;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.b.c;
import b.b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSafetyBeltListener implements d {
    protected static final String TAG = "AbsBYDAutoSafetyBeltListener";

    public AbsBYDAutoSafetyBeltListener() {
        Log.v(TAG, TAG);
    }

    @Override // b.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // b.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onDriverBeltStatusChanged(int i) {
        Log.v(TAG, "onDriverBeltStatusChanged state is " + i);
    }

    public void onError(int i, String str) {
        Log.v(TAG, "onError");
    }

    public void onMessage5sOnlineStateChanged(int i, int i2) {
        Log.v(TAG, "onMessage5sOnlineStateChanged");
    }

    public void onPassengerStatusChanged(int i, int i2) {
        Log.v(TAG, "onPassengerStatusChanged");
    }

    public void onSafetyBeltMsrStateChanged(int i) {
        Log.v(TAG, "onSafetyBeltMsrStateChanged");
    }

    public void onSafetyBeltReminderReceived(int i) {
        Log.v(TAG, "onSafetyBeltReminderReceived");
    }

    public void onSafetyBeltStatusChanged(int i, int i2) {
        Log.v(TAG, "onSafetyBeltStatusChanged");
    }
}

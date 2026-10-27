package android.hardware.bydauto.safetybelt;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSafetyBeltListener implements d {
    public static final String TAG = "AbsBYDAutoSafetyBeltListener";

    public AbsBYDAutoSafetyBeltListener() {
        Log.v(TAG, TAG);
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
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onDriverBeltStatusChanged(int i8) {
        Log.v(TAG, "onDriverBeltStatusChanged state is " + i8);
    }

    public void onError(int i8, String str) {
        Log.v(TAG, "onError");
    }

    public void onMainSeatStateChanged(int i8) {
        Log.v(TAG, "onMainSeatStateChanged state is " + i8);
    }

    public void onMessage5sOnlineStateChanged(int i8, int i9) {
        Log.v(TAG, "onMessage5sOnlineStateChanged");
    }

    public void onPassengerStatusChanged(int i8, int i9) {
        Log.v(TAG, "onPassengerStatusChanged");
    }

    public void onSafetyBeltMsrStateChanged(int i8) {
        Log.v(TAG, "onSafetyBeltMsrStateChanged");
    }

    public void onSafetyBeltReminderReceived(int i8) {
        Log.v(TAG, "onSafetyBeltReminderReceived");
    }

    public void onSafetyBeltStatusChanged(int i8, int i9) {
        Log.v(TAG, "onSafetyBeltStatusChanged");
    }
}

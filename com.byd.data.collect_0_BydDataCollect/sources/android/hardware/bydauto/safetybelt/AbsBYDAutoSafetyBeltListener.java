package android.hardware.bydauto.safetybelt;

import a.a.c;
import a.a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSafetyBeltListener implements d {
    protected static final String TAG = "AbsBYDAutoSafetyBeltListener";

    public AbsBYDAutoSafetyBeltListener() {
        Log.v(TAG, TAG);
    }

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onDriverBeltStatusChanged(int i2) {
        Log.v(TAG, "onDriverBeltStatusChanged state is " + i2);
    }

    public void onError(int i2, String str) {
        Log.v(TAG, "onError");
    }

    public void onMainSeatStateChanged(int i2) {
        Log.v(TAG, "onMainSeatStateChanged state is " + i2);
    }

    public void onMessage5sOnlineStateChanged(int i2, int i3) {
        Log.v(TAG, "onMessage5sOnlineStateChanged");
    }

    public void onPassengerStatusChanged(int i2, int i3) {
        Log.v(TAG, "onPassengerStatusChanged");
    }

    public void onSafetyBeltMsrStateChanged(int i2) {
        Log.v(TAG, "onSafetyBeltMsrStateChanged");
    }

    public void onSafetyBeltReminderReceived(int i2) {
        Log.v(TAG, "onSafetyBeltReminderReceived");
    }

    public void onSafetyBeltStatusChanged(int i2, int i3) {
        Log.v(TAG, "onSafetyBeltStatusChanged");
    }
}

package android.hardware.bydauto.safetybelt;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSafetyBeltListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoSafetyBeltListener";

    public AbsBYDAutoSafetyBeltListener() {
        Log.v(TAG, TAG);
    }

    public void onSafetyBeltStatusChanged(int area, int state) {
        Log.v(TAG, "onSafetyBeltStatusChanged");
    }

    public void onDriverBeltStatusChanged(int state) {
        Log.v(TAG, "onDriverBeltStatusChanged state is " + state);
    }

    public void onPassengerStatusChanged(int area, int state) {
        Log.v(TAG, "onPassengerStatusChanged");
    }

    public void onSafetyBeltReminderReceived(int value) {
        Log.v(TAG, "onSafetyBeltReminderReceived");
    }

    public void onSafetyBeltMsrStateChanged(int value) {
        Log.v(TAG, "onSafetyBeltMsrStateChanged");
    }

    public void onMessage5sOnlineStateChanged(int id, int state) {
        Log.v(TAG, "onMessage5sOnlineStateChanged");
    }

    public void onMainSeatStateChanged(int state) {
        Log.v(TAG, "onMainSeatStateChanged state is " + state);
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int errCode, String errMessage) {
        Log.v(TAG, "onError");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent event) {
        Log.v(TAG, "onDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
        Log.v(TAG, "onDataEventChanged");
    }
}

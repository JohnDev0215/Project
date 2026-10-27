package android.hardware.bydauto.pm2p5;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoPM2p5Listener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoPM2p5Listener";

    public AbsBYDAutoPM2p5Listener() {
        Log.v(TAG, TAG);
    }

    public void onPM2p5CheckStateChanged(int stateIn, int stateOut) {
        Log.v(TAG, "onPM2p5CheckStateChanged");
    }

    public void onPM2p5ValueChanged(int valueIn, int valueOut) {
        Log.v(TAG, "onPM2p5ValueChanged");
    }

    public void onPM2p5LevelChanged(int levelIn, int levelOut) {
        Log.v(TAG, "onPM2p5LevelChanged");
    }

    public void onPM2p5OnlineStateChanged(int state) {
        Log.v(TAG, "onPM2p5OnlineStateChanged");
    }

    public void onPromptInfoChanged(int info) {
        Log.v(TAG, "onPromptInfoChanged");
    }

    public void onPM2p5WarningInfoChanged(int info) {
        Log.v(TAG, "onPM2p5WarningInfoChanged");
    }

    public void onPM2p5DualChannelDetectChanged(int state) {
        Log.v(TAG, "onPM2p5DualChannelDetectChanged");
    }

    public void onPM2p5AnionDetectChanged(int state) {
        Log.v(TAG, "onPM2p5AnionDetectChanged");
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

    public void onRapidPurificationPM2p5StateChanged(int state) {
        Log.v(TAG, "onRapidPurificationPM2p5StateChanged");
    }

    public void onNegativeIonStateChange(int state) {
        Log.v(TAG, "onNegativeIonStateChange");
    }

    public void onGreenSystemStateChange() {
        Log.v(TAG, "onGreenSystemStateChange");
    }
}

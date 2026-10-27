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

    public void onPM2p5CheckStateChanged(int i, int i2) {
        Log.v(TAG, "onPM2p5CheckStateChanged");
    }

    public void onPM2p5ValueChanged(int i, int i2) {
        Log.v(TAG, "onPM2p5ValueChanged");
    }

    public void onPM2p5LevelChanged(int i, int i2) {
        Log.v(TAG, "onPM2p5LevelChanged");
    }

    public void onPM2p5OnlineStateChanged(int i) {
        Log.v(TAG, "onPM2p5OnlineStateChanged");
    }

    public void onPromptInfoChanged(int i) {
        Log.v(TAG, "onPromptInfoChanged");
    }

    public void onPM2p5WarningInfoChanged(int i) {
        Log.v(TAG, "onPM2p5WarningInfoChanged");
    }

    public void onPM2p5DualChannelDetectChanged(int i) {
        Log.v(TAG, "onPM2p5DualChannelDetectChanged");
    }

    public void onPM2p5AnionDetectChanged(int i) {
        Log.v(TAG, "onPM2p5AnionDetectChanged");
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

    public void onRapidPurificationPM2p5StateChanged(int i) {
        Log.v(TAG, "onRapidPurificationPM2p5StateChanged");
    }

    public void onNegativeIonStateChange(int i) {
        Log.v(TAG, "onNegativeIonStateChange");
    }

    public void onGreenSystemStateChange() {
        Log.v(TAG, "onGreenSystemStateChange");
    }
}

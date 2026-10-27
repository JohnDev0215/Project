package android.hardware.bydauto.pm2p5;

import a.c;
import a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoPM2p5Listener implements d {
    protected static final String TAG = "AbsBYDAutoPM2p5Listener";

    public AbsBYDAutoPM2p5Listener() {
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

    public void onGreenSystemStateChange() {
        Log.v(TAG, "onGreenSystemStateChange");
    }

    public void onNegativeIonStateChange(int i7) {
        Log.v(TAG, "onNegativeIonStateChange");
    }

    public void onPM2p5AnionDetectChanged(int i7) {
        Log.v(TAG, "onPM2p5AnionDetectChanged");
    }

    public void onPM2p5CheckStateChanged(int i7, int i8) {
        Log.v(TAG, "onPM2p5CheckStateChanged");
    }

    public void onPM2p5DualChannelDetectChanged(int i7) {
        Log.v(TAG, "onPM2p5DualChannelDetectChanged");
    }

    public void onPM2p5LevelChanged(int i7, int i8) {
        Log.v(TAG, "onPM2p5LevelChanged");
    }

    public void onPM2p5OnlineStateChanged(int i7) {
        Log.v(TAG, "onPM2p5OnlineStateChanged");
    }

    public void onPM2p5ValueChanged(int i7, int i8) {
        Log.v(TAG, "onPM2p5ValueChanged");
    }

    public void onPM2p5WarningInfoChanged(int i7) {
        Log.v(TAG, "onPM2p5WarningInfoChanged");
    }

    public void onPromptInfoChanged(int i7) {
        Log.v(TAG, "onPromptInfoChanged");
    }

    public void onRapidPurificationPM2p5StateChanged(int i7) {
        Log.v(TAG, "onRapidPurificationPM2p5StateChanged");
    }
}

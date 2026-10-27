package android.hardware.bydauto.pm2p5;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoPM2p5Listener implements d {
    public static final String TAG = "AbsBYDAutoPM2p5Listener";

    public AbsBYDAutoPM2p5Listener() {
        Log.v(TAG, TAG);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z3) {
        super.isConnected(z3);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z3) {
        super.isOptimizationApi(z3);
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onError(int i3, String str) {
        Log.v(TAG, "onError");
    }

    public void onGreenSystemStateChange() {
        Log.v(TAG, "onGreenSystemStateChange");
    }

    public void onNegativeIonStateChange(int i3) {
        Log.v(TAG, "onNegativeIonStateChange");
    }

    public void onPM2p5AnionDetectChanged(int i3) {
        Log.v(TAG, "onPM2p5AnionDetectChanged");
    }

    public void onPM2p5CheckStateChanged(int i3, int i4) {
        Log.v(TAG, "onPM2p5CheckStateChanged");
    }

    public void onPM2p5DualChannelDetectChanged(int i3) {
        Log.v(TAG, "onPM2p5DualChannelDetectChanged");
    }

    public void onPM2p5LevelChanged(int i3, int i4) {
        Log.v(TAG, "onPM2p5LevelChanged");
    }

    public void onPM2p5OnlineStateChanged(int i3) {
        Log.v(TAG, "onPM2p5OnlineStateChanged");
    }

    public void onPM2p5ValueChanged(int i3, int i4) {
        Log.v(TAG, "onPM2p5ValueChanged");
    }

    public void onPM2p5WarningInfoChanged(int i3) {
        Log.v(TAG, "onPM2p5WarningInfoChanged");
    }

    public void onPromptInfoChanged(int i3) {
        Log.v(TAG, "onPromptInfoChanged");
    }

    public void onRapidPurificationPM2p5StateChanged(int i3) {
        Log.v(TAG, "onRapidPurificationPM2p5StateChanged");
    }
}

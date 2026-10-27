package android.hardware.bydauto.pm2p5;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoPM2p5Listener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoPM2p5Listener";

    public void onGreenSystemStateChange() {
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    public void onNegativeIonStateChange(int i) {
    }

    public void onPM2p5AnionDetectChanged(int i) {
    }

    public void onPM2p5DualChannelDetectChanged(int i) {
    }

    public void onPM2p5OnlineStateChanged(int i) {
    }

    public void onPM2p5WarningInfoChanged(int i) {
    }

    public void onPromptInfoChanged(int i) {
    }

    public void onRapidPurificationPM2p5StateChanged(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }

    public void onPM2p5CheckStateChanged(int i, int i2) {
    }

    public void onPM2p5LevelChanged(int i, int i2) {
    }

    public void onPM2p5ValueChanged(int i, int i2) {
    }
}

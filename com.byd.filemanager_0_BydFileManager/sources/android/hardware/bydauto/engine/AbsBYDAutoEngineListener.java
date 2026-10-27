package android.hardware.bydauto.engine;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoEngineListener implements d {
    protected static final String TAG = "AbsBYDAutoEngineListener";

    public AbsBYDAutoEngineListener() {
        Log.v(TAG, TAG);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onEngineConditionChanged(int i) {
        Log.v(TAG, "onEngineConditionChanged");
    }

    public void onEngineCoolantLevelChanged(int i) {
        Log.v(TAG, "onEngineCoolantLevelChanged");
    }

    public void onEngineSimulatorVoiceSourceChanged(int i) {
        Log.v(TAG, "onEngineSimulatorVoiceSourceChanged");
    }

    public void onEngineSpeedChanged(int i) {
        Log.v(TAG, "onEngineSpeedChanged");
    }

    public void onEngineSpeedWarningChanged(int i) {
        Log.v(TAG, "onEngineSpeedWarningChanged");
    }

    public void onEngineStateChanged(byte[] bArr) {
        Log.v(TAG, "onEngineStateChanged");
    }

    public void onEngineVoiceSimulatorStateChanged(int i) {
        Log.v(TAG, "onEngineVoiceSimulatorStateChanged");
    }

    public void onError(int i, String str) {
        Log.v(TAG, "onError");
    }

    public void onFeatureChanged(String str, int i) {
        Log.v(TAG, "onFeatureChanged");
    }

    public void onOilLevelChanged(int i) {
        Log.v(TAG, "onOilLevelChanged");
    }
}

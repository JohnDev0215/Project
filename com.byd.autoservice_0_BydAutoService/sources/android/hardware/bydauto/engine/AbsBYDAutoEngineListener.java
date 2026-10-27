package android.hardware.bydauto.engine;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoEngineListener implements d {
    public static final String TAG = "AbsBYDAutoEngineListener";

    public AbsBYDAutoEngineListener() {
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

    public void onEngineConditionChanged(int i3) {
        Log.v(TAG, "onEngineConditionChanged");
    }

    public void onEngineCoolantLevelChanged(int i3) {
        Log.v(TAG, "onEngineCoolantLevelChanged");
    }

    public void onEngineSimulatorVoiceSourceChanged(int i3) {
        Log.v(TAG, "onEngineSimulatorVoiceSourceChanged");
    }

    public void onEngineSpeedChanged(int i3) {
        Log.v(TAG, "onEngineSpeedChanged");
    }

    public void onEngineSpeedWarningChanged(int i3) {
        Log.v(TAG, "onEngineSpeedWarningChanged");
    }

    public void onEngineStateChanged(byte[] bArr) {
        Log.v(TAG, "onEngineStateChanged");
    }

    public void onEngineVoiceSimulatorStateChanged(int i3) {
        Log.v(TAG, "onEngineVoiceSimulatorStateChanged");
    }

    public void onError(int i3, String str) {
        Log.v(TAG, "onError");
    }

    public void onFeatureChanged(String str, int i3) {
        Log.v(TAG, "onFeatureChanged");
    }

    public void onOilLevelChanged(int i3) {
        Log.v(TAG, "onOilLevelChanged");
    }
}

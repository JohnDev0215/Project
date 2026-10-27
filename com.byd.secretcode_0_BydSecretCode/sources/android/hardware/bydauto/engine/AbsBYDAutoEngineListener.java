package android.hardware.bydauto.engine;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import defpackage.d;
import defpackage.e;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoEngineListener implements e {
    public static final String TAG = "AbsBYDAutoEngineListener";

    public AbsBYDAutoEngineListener() {
        Log.v(TAG, TAG);
    }

    @Override // defpackage.e
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // defpackage.e
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public final void onDataChanged(d dVar) {
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

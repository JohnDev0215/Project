package android.hardware.bydauto.engine;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoEngineListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoEngineListener";

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    public void onEngineConditionChanged(int i) {
    }

    public void onEngineCoolantLevelChanged(int i) {
    }

    public void onEngineSimulatorVoiceSourceChanged(int i) {
    }

    public void onEngineSpeedChanged(int i) {
    }

    public void onEngineSpeedWarningChanged(int i) {
    }

    public void onEngineStateChanged(byte[] bArr) {
    }

    public void onEngineVoiceSimulatorStateChanged(int i) {
    }

    public void onOilLevelChanged(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }

    public void onFeatureChanged(String str, int i) {
    }
}

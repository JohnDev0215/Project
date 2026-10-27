package android.hardware.bydauto.engine;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoEngineListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoEngineListener";

    public AbsBYDAutoEngineListener() {
        Log.v(TAG, TAG);
    }

    public void onEngineStateChanged(byte[] state) {
        Log.v(TAG, "onEngineStateChanged");
    }

    public void onFeatureChanged(String feature, int ifHas) {
        Log.v(TAG, "onFeatureChanged");
    }

    public void onEngineVoiceSimulatorStateChanged(int state) {
        Log.v(TAG, "onEngineVoiceSimulatorStateChanged");
    }

    public void onEngineSpeedChanged(int value) {
        Log.v(TAG, "onEngineSpeedChanged");
    }

    public void onEngineCoolantLevelChanged(int state) {
        Log.v(TAG, "onEngineCoolantLevelChanged");
    }

    public void onOilLevelChanged(int value) {
        Log.v(TAG, "onOilLevelChanged");
    }

    public void onEngineSimulatorVoiceSourceChanged(int type) {
        Log.v(TAG, "onEngineSimulatorVoiceSourceChanged");
    }

    public void onEngineSpeedWarningChanged(int value) {
        Log.v(TAG, "onEngineSpeedWarningChanged");
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

    public void onEngineConditionChanged(int value) {
        Log.v(TAG, "onEngineConditionChanged");
    }
}

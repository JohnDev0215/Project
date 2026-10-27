package b.b.g.k;

import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.engine.AbsBYDAutoEngineListener;

/* JADX INFO: compiled from: BydEngineListener.java */
/* JADX INFO: loaded from: classes2.dex */
public class k extends AbsBYDAutoEngineListener {
    @Override // android.hardware.bydauto.engine.AbsBYDAutoEngineListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        super.onDataEventChanged(i, bYDAutoEventValue);
    }

    @Override // android.hardware.bydauto.engine.AbsBYDAutoEngineListener
    public void onEngineConditionChanged(int i) {
        super.onEngineConditionChanged(i);
    }

    @Override // android.hardware.bydauto.engine.AbsBYDAutoEngineListener
    public void onEngineCoolantLevelChanged(int i) {
        super.onEngineCoolantLevelChanged(i);
    }

    @Override // android.hardware.bydauto.engine.AbsBYDAutoEngineListener
    public void onEngineSimulatorVoiceSourceChanged(int i) {
        super.onEngineSimulatorVoiceSourceChanged(i);
    }

    @Override // android.hardware.bydauto.engine.AbsBYDAutoEngineListener
    public void onEngineSpeedChanged(int i) {
        super.onEngineSpeedChanged(i);
    }

    @Override // android.hardware.bydauto.engine.AbsBYDAutoEngineListener
    public void onEngineSpeedWarningChanged(int i) {
        super.onEngineSpeedWarningChanged(i);
    }

    @Override // android.hardware.bydauto.engine.AbsBYDAutoEngineListener
    public void onEngineStateChanged(byte[] bArr) {
        super.onEngineStateChanged(bArr);
    }

    @Override // android.hardware.bydauto.engine.AbsBYDAutoEngineListener
    public void onEngineVoiceSimulatorStateChanged(int i) {
        super.onEngineVoiceSimulatorStateChanged(i);
    }

    @Override // android.hardware.bydauto.engine.AbsBYDAutoEngineListener
    public void onError(int i, String str) {
        super.onError(i, str);
    }

    @Override // android.hardware.bydauto.engine.AbsBYDAutoEngineListener
    public void onFeatureChanged(String str, int i) {
        super.onFeatureChanged(str, i);
    }

    @Override // android.hardware.bydauto.engine.AbsBYDAutoEngineListener
    public void onOilLevelChanged(int i) {
        super.onOilLevelChanged(i);
    }
}

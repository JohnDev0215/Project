package android.hardware.bydauto.energy;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoEnergyListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoEnergyListener";

    public void onBCMStateChanged(int i) {
    }

    public void onChangePHintChanged(int i) {
    }

    public void onChargePortCoverHintChanged(int i) {
    }

    public void onDCWorkModeChanged(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    public void onDriveDistanceShowModeChanged(int i) {
    }

    public void onDriveDistanceShowStandardChanged(int i) {
    }

    public void onEPedalStateChanged(int i) {
    }

    public void onElectricPointFlagChanged(int i) {
    }

    public void onElectricShowModeChanged(int i) {
    }

    public void onEnergyFeedbackLevelChanged(int i) {
    }

    public void onEnergyModeChanged(int i) {
    }

    public void onEnergyModelChanged(int i) {
    }

    public void onEnergyStateChanged(int i) {
    }

    public void onHEVElectricPointFlagChanged(int i) {
    }

    public void onHEVLast200MElectricConChanged(float f) {
    }

    public void onLast200MElectricConChanged(float f) {
    }

    public void onLast200MOilConChanged(float f) {
    }

    public void onLast50KMElectricConAvgChanged(float f) {
    }

    public void onLast50KMOilConAvgChanged(float f) {
    }

    public void onMandatoryElectricStateChanged(int i) {
    }

    public void onMemoryElectricStateChanged(int i) {
    }

    public void onMemoryMaxEvStateChanged(int i) {
    }

    public void onMemorySportStateChanged(int i) {
    }

    public void onOilPointFlagChanged(int i) {
    }

    public void onOperationModeChanged(int i) {
    }

    public void onOperationModeNormalChanged(int i) {
    }

    public void onPowerGenerationStateChanged(int i) {
    }

    public void onPowerGenerationValueChanged(int i) {
    }

    public void onPreSaleModelStateChanged(int i) {
    }

    public void onRoadSurfaceChanged(int i) {
    }

    public void onRoadSurfaceKindChanged(int i) {
    }

    public void onRoadSurfaceModeNormalChanged(int i) {
    }

    public void onSOCTargetChanged(int i) {
    }

    public void oniTACAllowTagChanged(int i) {
    }

    public void oniTACExitTagChanged(int i) {
    }

    public void oniTACModeChanged(int i) {
    }

    public void oniTACStateChanged(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }

    public void onResetCurveDataChanged(int i, int i2) {
    }
}

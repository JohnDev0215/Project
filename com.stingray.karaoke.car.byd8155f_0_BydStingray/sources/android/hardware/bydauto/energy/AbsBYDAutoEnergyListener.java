package android.hardware.bydauto.energy;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoEnergyListener implements d {
    protected static final String TAG = "AbsBYDAutoEnergyListener";

    public AbsBYDAutoEnergyListener() {
        Log.v(TAG, TAG);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
    }

    public void onBCMStateChanged(int i7) {
        Log.v(TAG, "onBCMStateChanged");
    }

    public void onDCWorkModeChanged(int i7) {
        Log.v(TAG, "onDCWorkModeChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onDriveDistanceShowModeChanged(int i7) {
        Log.v(TAG, "onDriveDistanceShowModeChanged");
    }

    public void onDriveDistanceShowStandardChanged(int i7) {
        Log.v(TAG, "onDriveDistanceShowStandardChanged");
    }

    public void onEPedalStateChanged(int i7) {
        Log.v(TAG, "onEPedalStateChanged");
    }

    public void onElectricPointFlagChanged(int i7) {
        Log.v(TAG, "onElectricPointFlagChanged");
    }

    public void onElectricShowModeChanged(int i7) {
        Log.v(TAG, "onElectricShowModeChanged");
    }

    public void onEnergyFeedbackLevelChanged(int i7) {
        Log.v(TAG, "onEnergyFeedbackLevelChanged");
    }

    public void onEnergyModeChanged(int i7) {
        Log.v(TAG, "onEnergyModeChanged");
    }

    public void onEnergyModelChanged(int i7) {
        Log.v(TAG, "onEnergyModelChanged");
    }

    public void onEnergyStateChanged(int i7) {
        Log.v(TAG, "onEnergyStateChanged");
    }

    public void onError(int i7, String str) {
        Log.v(TAG, "onError");
    }

    public void onHEVElectricPointFlagChanged(int i7) {
        Log.v(TAG, "onHEVElectricPointFlagChanged");
    }

    public void onHEVLast200MElectricConChanged(float f10) {
        Log.v(TAG, "onHEVLast200MElectricConChanged");
    }

    public void onLast200MElectricConChanged(float f10) {
        Log.v(TAG, "onLast200MElectricConChanged");
    }

    public void onLast200MOilConChanged(float f10) {
        Log.v(TAG, "onLast200MOilConChanged");
    }

    public void onLast50KMElectricConAvgChanged(float f10) {
        Log.v(TAG, "onLast50KMElectricConAvgChanged");
    }

    public void onLast50KMOilConAvgChanged(float f10) {
        Log.v(TAG, "onLast50KMOilConAvgChanged");
    }

    public void onMandatoryElectricStateChanged(int i7) {
        Log.v(TAG, "onMandatoryElectricStateChanged");
    }

    public void onMemoryElectricStateChanged(int i7) {
        Log.v(TAG, "onMemoryElectricStateChanged");
    }

    public void onMemoryMaxEvStateChanged(int i7) {
        Log.v(TAG, "onMemoryMaxEvStateChanged");
    }

    public void onMemorySportStateChanged(int i7) {
        Log.v(TAG, "onMemorySportStateChanged");
    }

    public void onOilPointFlagChanged(int i7) {
        Log.v(TAG, "onOilPointFlagChanged");
    }

    public void onOperationModeChanged(int i7) {
        Log.v(TAG, "onOperationModeChanged");
    }

    public void onOperationModeNormalChanged(int i7) {
        Log.v(TAG, "onOperationModeNormalChanged");
    }

    public void onPowerGenerationStateChanged(int i7) {
        Log.v(TAG, "onPowerGenerationStateChanged");
    }

    public void onPowerGenerationValueChanged(int i7) {
        Log.v(TAG, "onPowerGenerationValueChanged");
    }

    public void onPreSaleModelStateChanged(int i7) {
        Log.v(TAG, "onPreSaleModelStateChanged");
    }

    public void onResetCurveDataChanged(int i7, int i10) {
        Log.v(TAG, "onResetCurveDataChanged");
    }

    public void onRoadSurfaceChanged(int i7) {
        Log.v(TAG, "onRoadSurfaceChanged");
    }

    public void onRoadSurfaceKindChanged(int i7) {
        Log.v(TAG, "onRoadSurfaceKindChanged");
    }

    public void onRoadSurfaceModeNormalChanged(int i7) {
        Log.v(TAG, "onOperationModeNormalChanged");
    }

    public void onSOCTargetChanged(int i7) {
        Log.v(TAG, "onSOCTargetChanged");
    }
}

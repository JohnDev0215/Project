package android.hardware.bydauto.energy;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoEnergyListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoEnergyListener";

    public AbsBYDAutoEnergyListener() {
        Log.v(TAG, TAG);
    }

    public void onBCMStateChanged(int i) {
        Log.v(TAG, "onBCMStateChanged");
    }

    public void onDCWorkModeChanged(int i) {
        Log.v(TAG, "onDCWorkModeChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
        Log.v(TAG, "onDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onDriveDistanceShowModeChanged(int i) {
        Log.v(TAG, "onDriveDistanceShowModeChanged");
    }

    public void onDriveDistanceShowStandardChanged(int i) {
        Log.v(TAG, "onDriveDistanceShowStandardChanged");
    }

    public void onEPedalStateChanged(int i) {
        Log.v(TAG, "onEPedalStateChanged");
    }

    public void onElectricPointFlagChanged(int i) {
        Log.v(TAG, "onElectricPointFlagChanged");
    }

    public void onElectricShowModeChanged(int i) {
        Log.v(TAG, "onElectricShowModeChanged");
    }

    public void onEnergyFeedbackLevelChanged(int i) {
        Log.v(TAG, "onEnergyFeedbackLevelChanged");
    }

    public void onEnergyModeChanged(int i) {
        Log.v(TAG, "onEnergyModeChanged");
    }

    public void onEnergyModelChanged(int i) {
        Log.v(TAG, "onEnergyModelChanged");
    }

    public void onEnergyStateChanged(int i) {
        Log.v(TAG, "onEnergyStateChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
        Log.v(TAG, "onError");
    }

    public void onHEVElectricPointFlagChanged(int i) {
        Log.v(TAG, "onHEVElectricPointFlagChanged");
    }

    public void onHEVLast200MElectricConChanged(float f) {
        Log.v(TAG, "onHEVLast200MElectricConChanged");
    }

    public void onLast200MElectricConChanged(float f) {
        Log.v(TAG, "onLast200MElectricConChanged");
    }

    public void onLast200MOilConChanged(float f) {
        Log.v(TAG, "onLast200MOilConChanged");
    }

    public void onLast50KMElectricConAvgChanged(float f) {
        Log.v(TAG, "onLast50KMElectricConAvgChanged");
    }

    public void onLast50KMOilConAvgChanged(float f) {
        Log.v(TAG, "onLast50KMOilConAvgChanged");
    }

    public void onMandatoryElectricStateChanged(int i) {
        Log.v(TAG, "onMandatoryElectricStateChanged");
    }

    public void onMemoryElectricStateChanged(int i) {
        Log.v(TAG, "onMemoryElectricStateChanged");
    }

    public void onMemoryMaxEvStateChanged(int i) {
        Log.v(TAG, "onMemoryMaxEvStateChanged");
    }

    public void onMemorySportStateChanged(int i) {
        Log.v(TAG, "onMemorySportStateChanged");
    }

    public void onOilPointFlagChanged(int i) {
        Log.v(TAG, "onOilPointFlagChanged");
    }

    public void onOperationModeChanged(int i) {
        Log.v(TAG, "onOperationModeChanged");
    }

    public void onPowerGenerationStateChanged(int i) {
        Log.v(TAG, "onPowerGenerationStateChanged");
    }

    public void onPowerGenerationValueChanged(int i) {
        Log.v(TAG, "onPowerGenerationValueChanged");
    }

    public void onPreSaleModelStateChanged(int i) {
        Log.v(TAG, "onPreSaleModelStateChanged");
    }

    public void onResetCurveDataChanged(int i, int i2) {
        Log.v(TAG, "onResetCurveDataChanged");
    }

    public void onRoadSurfaceChanged(int i) {
        Log.v(TAG, "onRoadSurfaceChanged");
    }

    public void onRoadSurfaceKindChanged(int i) {
        Log.v(TAG, "onRoadSurfaceKindChanged");
    }

    public void onSOCTargetChanged(int i) {
        Log.v(TAG, "onSOCTargetChanged");
    }
}

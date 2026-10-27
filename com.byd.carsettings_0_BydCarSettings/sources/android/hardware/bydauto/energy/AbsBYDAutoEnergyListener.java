package android.hardware.bydauto.energy;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoEnergyListener implements d {
    public static final String TAG = "AbsBYDAutoEnergyListener";

    public AbsBYDAutoEnergyListener() {
        Log.v(TAG, TAG);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onBCMStateChanged(int i) {
        Log.v(TAG, "onBCMStateChanged");
    }

    public void onChangePHintChanged(int i) {
        Log.v(TAG, "onChangePHintChanged");
    }

    public void onChargePortCoverHintChanged(int i) {
        Log.v(TAG, "onChargePortCoverHintChanged value is = " + i);
    }

    public void onDCWorkModeChanged(int i) {
        Log.v(TAG, "onDCWorkModeChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

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

    public void onError(int i, String str) {
        Log.v(TAG, "onError");
    }

    public void onHEVElectricPointFlagChanged(int i) {
        Log.v(TAG, "onHEVElectricPointFlagChanged");
    }

    public void onHEVLast200MElectricConChanged(float f2) {
        Log.v(TAG, "onHEVLast200MElectricConChanged");
    }

    public void onLast200MElectricConChanged(float f2) {
        Log.v(TAG, "onLast200MElectricConChanged");
    }

    public void onLast200MOilConChanged(float f2) {
        Log.v(TAG, "onLast200MOilConChanged");
    }

    public void onLast50KMElectricConAvgChanged(float f2) {
        Log.v(TAG, "onLast50KMElectricConAvgChanged");
    }

    public void onLast50KMOilConAvgChanged(float f2) {
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

    public void onOperationModeNormalChanged(int i) {
        Log.v(TAG, "onOperationModeNormalChanged");
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

    public void onRoadSurfaceModeNormalChanged(int i) {
        Log.v(TAG, "onOperationModeNormalChanged");
    }

    public void onRollBenchModeStatusChanged(int i) {
        Log.v(TAG, "onRollBenchModeStatusChanged status is = " + i);
    }

    public void onSOCTargetChanged(int i) {
        Log.v(TAG, "onSOCTargetChanged");
    }

    public void onTripACPercent50KMChanged(int i) {
        Log.v(TAG, "onTripACPercent50KMChanged");
    }

    public void onTripACPercentChanged(int i) {
        Log.v(TAG, "onTripACPercentChanged");
    }

    public void onTripDrivePercent50KMChanged(int i) {
        Log.v(TAG, "onTripDrivePercent50KMChanged");
    }

    public void onTripDrivePercentChanged(int i) {
        Log.v(TAG, "onTripDrivePercentChanged");
    }

    public void onTripElecAppliancePercent50KMChanged(int i) {
        Log.v(TAG, "onTripElecAppliancePercent50KMChanged");
    }

    public void onTripElecAppliancePercentChanged(int i) {
        Log.v(TAG, "onTripElecAppliancePercentChanged");
    }

    public void onTripElecConAvgChanged(float f2) {
        Log.v(TAG, "onTripElecConAvgChanged");
    }

    public void onTripEnergyConAvgChanged(float f2) {
        Log.v(TAG, "onTripEnergyConAvgChanged");
    }

    public void onTripLast50KMElectricConAvgChanged(float f2) {
        Log.v(TAG, "onTripLast50KMElectricConAvgChanged");
    }

    public void onTripLast50KMFuelConAvgChanged(float f2) {
        Log.v(TAG, "onTripLast50KMFuelConAvgChanged");
    }

    public void onTripLast50KMOilConAvgChanged(float f2) {
        Log.v(TAG, "onTripLast50KMOilConAvgChanged");
    }

    public void onTripOilConAvgChanged(float f2) {
        Log.v(TAG, "onTripOilConAvgChanged");
    }

    public void onTripOtherPercent50KMChanged(int i) {
        Log.v(TAG, "onTripOtherPercent50KMChanged");
    }

    public void onTripOtherPercentChanged(int i) {
        Log.v(TAG, "onTripOtherPercentChanged");
    }

    public void onTripPointFlagChanged(int i) {
        Log.v(TAG, "onTripPointFlagChanged");
    }

    public void onYEnergyConChanged(float f2) {
        Log.v(TAG, "onYEnergyConChanged");
    }

    public void onYEnergyConLimitChanged(int i) {
        Log.v(TAG, "onYEnergyConLimitChanged");
    }

    public void oniTACAllowTagChanged(int i) {
        Log.v(TAG, "oniTACAllowTagChanged");
    }

    public void oniTACExitTagChanged(int i) {
        Log.v(TAG, "oniTACExitTagChanged");
    }

    public void oniTACModeChanged(int i) {
        Log.v(TAG, "oniTACModeChanged");
    }

    public void oniTACStateChanged(int i) {
        Log.v(TAG, "oniTACStateChanged");
    }
}

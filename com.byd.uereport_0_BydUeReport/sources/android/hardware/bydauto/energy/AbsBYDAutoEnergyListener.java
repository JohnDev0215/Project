package android.hardware.bydauto.energy;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoEnergyListener implements d {
    public static final String TAG = "AbsBYDAutoEnergyListener";

    public AbsBYDAutoEnergyListener() {
        Log.v(TAG, TAG);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z7) {
        super.isConnected(z7);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z7) {
        super.isOptimizationApi(z7);
    }

    public void onBCMStateChanged(int i8) {
        Log.v(TAG, "onBCMStateChanged");
    }

    public void onChangePHintChanged(int i8) {
        Log.v(TAG, "onChangePHintChanged");
    }

    public void onChargePortCoverHintChanged(int i8) {
        Log.v(TAG, "onChargePortCoverHintChanged value is = " + i8);
    }

    public void onDCWorkModeChanged(int i8) {
        Log.v(TAG, "onDCWorkModeChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onDriveDistanceShowModeChanged(int i8) {
        Log.v(TAG, "onDriveDistanceShowModeChanged");
    }

    public void onDriveDistanceShowStandardChanged(int i8) {
        Log.v(TAG, "onDriveDistanceShowStandardChanged");
    }

    public void onEPedalStateChanged(int i8) {
        Log.v(TAG, "onEPedalStateChanged");
    }

    public void onElectricPointFlagChanged(int i8) {
        Log.v(TAG, "onElectricPointFlagChanged");
    }

    public void onElectricShowModeChanged(int i8) {
        Log.v(TAG, "onElectricShowModeChanged");
    }

    public void onEnergyFeedbackLevelChanged(int i8) {
        Log.v(TAG, "onEnergyFeedbackLevelChanged");
    }

    public void onEnergyModeChanged(int i8) {
        Log.v(TAG, "onEnergyModeChanged");
    }

    public void onEnergyModelChanged(int i8) {
        Log.v(TAG, "onEnergyModelChanged");
    }

    public void onEnergyStateChanged(int i8) {
        Log.v(TAG, "onEnergyStateChanged");
    }

    public void onError(int i8, String str) {
        Log.v(TAG, "onError");
    }

    public void onHEVElectricPointFlagChanged(int i8) {
        Log.v(TAG, "onHEVElectricPointFlagChanged");
    }

    public void onHEVLast200MElectricConChanged(float f8) {
        Log.v(TAG, "onHEVLast200MElectricConChanged");
    }

    public void onLast200MElectricConChanged(float f8) {
        Log.v(TAG, "onLast200MElectricConChanged");
    }

    public void onLast200MOilConChanged(float f8) {
        Log.v(TAG, "onLast200MOilConChanged");
    }

    public void onLast50KMElectricConAvgChanged(float f8) {
        Log.v(TAG, "onLast50KMElectricConAvgChanged");
    }

    public void onLast50KMOilConAvgChanged(float f8) {
        Log.v(TAG, "onLast50KMOilConAvgChanged");
    }

    public void onMandatoryElectricStateChanged(int i8) {
        Log.v(TAG, "onMandatoryElectricStateChanged");
    }

    public void onMemoryElectricStateChanged(int i8) {
        Log.v(TAG, "onMemoryElectricStateChanged");
    }

    public void onMemoryMaxEvStateChanged(int i8) {
        Log.v(TAG, "onMemoryMaxEvStateChanged");
    }

    public void onMemorySportStateChanged(int i8) {
        Log.v(TAG, "onMemorySportStateChanged");
    }

    public void onOilPointFlagChanged(int i8) {
        Log.v(TAG, "onOilPointFlagChanged");
    }

    public void onOperationModeChanged(int i8) {
        Log.v(TAG, "onOperationModeChanged");
    }

    public void onOperationModeNormalChanged(int i8) {
        Log.v(TAG, "onOperationModeNormalChanged");
    }

    public void onPowerGenerationStateChanged(int i8) {
        Log.v(TAG, "onPowerGenerationStateChanged");
    }

    public void onPowerGenerationValueChanged(int i8) {
        Log.v(TAG, "onPowerGenerationValueChanged");
    }

    public void onPreSaleModelStateChanged(int i8) {
        Log.v(TAG, "onPreSaleModelStateChanged");
    }

    public void onResetCurveDataChanged(int i8, int i9) {
        Log.v(TAG, "onResetCurveDataChanged");
    }

    public void onRoadSurfaceChanged(int i8) {
        Log.v(TAG, "onRoadSurfaceChanged");
    }

    public void onRoadSurfaceKindChanged(int i8) {
        Log.v(TAG, "onRoadSurfaceKindChanged");
    }

    public void onRoadSurfaceModeNormalChanged(int i8) {
        Log.v(TAG, "onOperationModeNormalChanged");
    }

    public void onRollBenchModeStatusChanged(int i8) {
        Log.v(TAG, "onRollBenchModeStatusChanged status is = " + i8);
    }

    public void onSOCTargetChanged(int i8) {
        Log.v(TAG, "onSOCTargetChanged");
    }

    public void onTripACPercent50KMChanged(int i8) {
        Log.v(TAG, "onTripACPercent50KMChanged");
    }

    public void onTripACPercentChanged(int i8) {
        Log.v(TAG, "onTripACPercentChanged");
    }

    public void onTripDrivePercent50KMChanged(int i8) {
        Log.v(TAG, "onTripDrivePercent50KMChanged");
    }

    public void onTripDrivePercentChanged(int i8) {
        Log.v(TAG, "onTripDrivePercentChanged");
    }

    public void onTripElecAppliancePercent50KMChanged(int i8) {
        Log.v(TAG, "onTripElecAppliancePercent50KMChanged");
    }

    public void onTripElecAppliancePercentChanged(int i8) {
        Log.v(TAG, "onTripElecAppliancePercentChanged");
    }

    public void onTripElecConAvgChanged(float f8) {
        Log.v(TAG, "onTripElecConAvgChanged");
    }

    public void onTripEnergyConAvgChanged(float f8) {
        Log.v(TAG, "onTripEnergyConAvgChanged");
    }

    public void onTripLast50KMElectricConAvgChanged(float f8) {
        Log.v(TAG, "onTripLast50KMElectricConAvgChanged");
    }

    public void onTripLast50KMFuelConAvgChanged(float f8) {
        Log.v(TAG, "onTripLast50KMFuelConAvgChanged");
    }

    public void onTripLast50KMOilConAvgChanged(float f8) {
        Log.v(TAG, "onTripLast50KMOilConAvgChanged");
    }

    public void onTripOilConAvgChanged(float f8) {
        Log.v(TAG, "onTripOilConAvgChanged");
    }

    public void onTripOtherPercent50KMChanged(int i8) {
        Log.v(TAG, "onTripOtherPercent50KMChanged");
    }

    public void onTripOtherPercentChanged(int i8) {
        Log.v(TAG, "onTripOtherPercentChanged");
    }

    public void onTripPointFlagChanged(int i8) {
        Log.v(TAG, "onTripPointFlagChanged");
    }

    public void onYEnergyConChanged(float f8) {
        Log.v(TAG, "onYEnergyConChanged");
    }

    public void onYEnergyConLimitChanged(int i8) {
        Log.v(TAG, "onYEnergyConLimitChanged");
    }

    public void oniTACAllowTagChanged(int i8) {
        Log.v(TAG, "oniTACAllowTagChanged");
    }

    public void oniTACExitTagChanged(int i8) {
        Log.v(TAG, "oniTACExitTagChanged");
    }

    public void oniTACModeChanged(int i8) {
        Log.v(TAG, "oniTACModeChanged");
    }

    public void oniTACStateChanged(int i8) {
        Log.v(TAG, "oniTACStateChanged");
    }
}

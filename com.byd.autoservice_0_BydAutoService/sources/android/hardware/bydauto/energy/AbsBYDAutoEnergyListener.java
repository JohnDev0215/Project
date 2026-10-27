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
    public /* bridge */ /* synthetic */ void isConnected(boolean z3) {
        super.isConnected(z3);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z3) {
        super.isOptimizationApi(z3);
    }

    public void onBCMStateChanged(int i3) {
        Log.v(TAG, "onBCMStateChanged");
    }

    public void onChangePHintChanged(int i3) {
        Log.v(TAG, "onChangePHintChanged");
    }

    public void onChargePortCoverHintChanged(int i3) {
        Log.v(TAG, "onChargePortCoverHintChanged value is = " + i3);
    }

    public void onDCWorkModeChanged(int i3) {
        Log.v(TAG, "onDCWorkModeChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onDriveDistanceShowModeChanged(int i3) {
        Log.v(TAG, "onDriveDistanceShowModeChanged");
    }

    public void onDriveDistanceShowStandardChanged(int i3) {
        Log.v(TAG, "onDriveDistanceShowStandardChanged");
    }

    public void onEPedalStateChanged(int i3) {
        Log.v(TAG, "onEPedalStateChanged");
    }

    public void onElectricPointFlagChanged(int i3) {
        Log.v(TAG, "onElectricPointFlagChanged");
    }

    public void onElectricShowModeChanged(int i3) {
        Log.v(TAG, "onElectricShowModeChanged");
    }

    public void onEnergyFeedbackLevelChanged(int i3) {
        Log.v(TAG, "onEnergyFeedbackLevelChanged");
    }

    public void onEnergyModeChanged(int i3) {
        Log.v(TAG, "onEnergyModeChanged");
    }

    public void onEnergyModelChanged(int i3) {
        Log.v(TAG, "onEnergyModelChanged");
    }

    public void onEnergyStateChanged(int i3) {
        Log.v(TAG, "onEnergyStateChanged");
    }

    public void onError(int i3, String str) {
        Log.v(TAG, "onError");
    }

    public void onHEVElectricPointFlagChanged(int i3) {
        Log.v(TAG, "onHEVElectricPointFlagChanged");
    }

    public void onHEVLast200MElectricConChanged(float f3) {
        Log.v(TAG, "onHEVLast200MElectricConChanged");
    }

    public void onLast200MElectricConChanged(float f3) {
        Log.v(TAG, "onLast200MElectricConChanged");
    }

    public void onLast200MOilConChanged(float f3) {
        Log.v(TAG, "onLast200MOilConChanged");
    }

    public void onLast50KMElectricConAvgChanged(float f3) {
        Log.v(TAG, "onLast50KMElectricConAvgChanged");
    }

    public void onLast50KMOilConAvgChanged(float f3) {
        Log.v(TAG, "onLast50KMOilConAvgChanged");
    }

    public void onMandatoryElectricStateChanged(int i3) {
        Log.v(TAG, "onMandatoryElectricStateChanged");
    }

    public void onMemoryElectricStateChanged(int i3) {
        Log.v(TAG, "onMemoryElectricStateChanged");
    }

    public void onMemoryMaxEvStateChanged(int i3) {
        Log.v(TAG, "onMemoryMaxEvStateChanged");
    }

    public void onMemorySportStateChanged(int i3) {
        Log.v(TAG, "onMemorySportStateChanged");
    }

    public void onOilPointFlagChanged(int i3) {
        Log.v(TAG, "onOilPointFlagChanged");
    }

    public void onOperationModeChanged(int i3) {
        Log.v(TAG, "onOperationModeChanged");
    }

    public void onOperationModeNormalChanged(int i3) {
        Log.v(TAG, "onOperationModeNormalChanged");
    }

    public void onPowerGenerationStateChanged(int i3) {
        Log.v(TAG, "onPowerGenerationStateChanged");
    }

    public void onPowerGenerationValueChanged(int i3) {
        Log.v(TAG, "onPowerGenerationValueChanged");
    }

    public void onPreSaleModelStateChanged(int i3) {
        Log.v(TAG, "onPreSaleModelStateChanged");
    }

    public void onResetCurveDataChanged(int i3, int i4) {
        Log.v(TAG, "onResetCurveDataChanged");
    }

    public void onRoadSurfaceChanged(int i3) {
        Log.v(TAG, "onRoadSurfaceChanged");
    }

    public void onRoadSurfaceKindChanged(int i3) {
        Log.v(TAG, "onRoadSurfaceKindChanged");
    }

    public void onRoadSurfaceModeNormalChanged(int i3) {
        Log.v(TAG, "onOperationModeNormalChanged");
    }

    public void onSOCTargetChanged(int i3) {
        Log.v(TAG, "onSOCTargetChanged");
    }

    public void onTripACPercent50KMChanged(int i3) {
        Log.v(TAG, "onTripACPercent50KMChanged");
    }

    public void onTripACPercentChanged(int i3) {
        Log.v(TAG, "onTripACPercentChanged");
    }

    public void onTripDrivePercent50KMChanged(int i3) {
        Log.v(TAG, "onTripDrivePercent50KMChanged");
    }

    public void onTripDrivePercentChanged(int i3) {
        Log.v(TAG, "onTripDrivePercentChanged");
    }

    public void onTripElecAppliancePercent50KMChanged(int i3) {
        Log.v(TAG, "onTripElecAppliancePercent50KMChanged");
    }

    public void onTripElecAppliancePercentChanged(int i3) {
        Log.v(TAG, "onTripElecAppliancePercentChanged");
    }

    public void onTripElecConAvgChanged(float f3) {
        Log.v(TAG, "onTripElecConAvgChanged");
    }

    public void onTripEnergyConAvgChanged(float f3) {
        Log.v(TAG, "onTripEnergyConAvgChanged");
    }

    public void onTripLast50KMElectricConAvgChanged(float f3) {
        Log.v(TAG, "onTripLast50KMElectricConAvgChanged");
    }

    public void onTripLast50KMFuelConAvgChanged(float f3) {
        Log.v(TAG, "onTripLast50KMFuelConAvgChanged");
    }

    public void onTripLast50KMOilConAvgChanged(float f3) {
        Log.v(TAG, "onTripLast50KMOilConAvgChanged");
    }

    public void onTripOilConAvgChanged(float f3) {
        Log.v(TAG, "onTripOilConAvgChanged");
    }

    public void onTripOtherPercent50KMChanged(int i3) {
        Log.v(TAG, "onTripOtherPercent50KMChanged");
    }

    public void onTripOtherPercentChanged(int i3) {
        Log.v(TAG, "onTripOtherPercentChanged");
    }

    public void onTripPointFlagChanged(int i3) {
        Log.v(TAG, "onTripPointFlagChanged");
    }

    public void onYEnergyConChanged(float f3) {
        Log.v(TAG, "onYEnergyConChanged");
    }

    public void onYEnergyConLimitChanged(int i3) {
        Log.v(TAG, "onYEnergyConLimitChanged");
    }

    public void oniTACAllowTagChanged(int i3) {
        Log.v(TAG, "oniTACAllowTagChanged");
    }

    public void oniTACExitTagChanged(int i3) {
        Log.v(TAG, "oniTACExitTagChanged");
    }

    public void oniTACModeChanged(int i3) {
        Log.v(TAG, "oniTACModeChanged");
    }

    public void oniTACStateChanged(int i3) {
        Log.v(TAG, "oniTACStateChanged");
    }
}

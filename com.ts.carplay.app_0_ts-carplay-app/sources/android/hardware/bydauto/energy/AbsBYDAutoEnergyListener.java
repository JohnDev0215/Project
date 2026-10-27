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

    public void onEnergyStateChanged(int state) {
        Log.v(TAG, "onEnergyStateChanged");
    }

    public void onEnergyModeChanged(int mode) {
        Log.v(TAG, "onEnergyModeChanged");
    }

    public void onOperationModeChanged(int mode) {
        Log.v(TAG, "onOperationModeChanged");
    }

    public void onPowerGenerationStateChanged(int mode) {
        Log.v(TAG, "onPowerGenerationStateChanged");
    }

    public void onPowerGenerationValueChanged(int value) {
        Log.v(TAG, "onPowerGenerationValueChanged");
    }

    public void onRoadSurfaceChanged(int type) {
        Log.v(TAG, "onRoadSurfaceChanged");
    }

    public void onDCWorkModeChanged(int state) {
        Log.v(TAG, "onDCWorkModeChanged");
    }

    public void onBCMStateChanged(int state) {
        Log.v(TAG, "onBCMStateChanged");
    }

    public void onRoadSurfaceKindChanged(int type) {
        Log.v(TAG, "onRoadSurfaceKindChanged");
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

    public void onEPedalStateChanged(int value) {
        Log.v(TAG, "onEPedalStateChanged");
    }

    public void onElectricShowModeChanged(int mode) {
        Log.v(TAG, "onElectricShowModeChanged");
    }

    public void onMandatoryElectricStateChanged(int value) {
        Log.v(TAG, "onMandatoryElectricStateChanged");
    }

    public void onDriveDistanceShowStandardChanged(int mode) {
        Log.v(TAG, "onDriveDistanceShowStandardChanged");
    }

    public void onDriveDistanceShowModeChanged(int value) {
        Log.v(TAG, "onDriveDistanceShowModeChanged");
    }

    public void onMemorySportStateChanged(int value) {
        Log.v(TAG, "onMemorySportStateChanged");
    }

    public void onMemoryMaxEvStateChanged(int value) {
        Log.v(TAG, "onMemoryMaxEvStateChanged");
    }

    public void onEnergyFeedbackLevelChanged(int value) {
        Log.v(TAG, "onEnergyFeedbackLevelChanged");
    }

    public void onSOCTargetChanged(int value) {
        Log.v(TAG, "onSOCTargetChanged");
    }

    public void onEnergyModelChanged(int mode) {
        Log.v(TAG, "onEnergyModelChanged");
    }

    public void onMemoryElectricStateChanged(int value) {
        Log.v(TAG, "onMemoryElectricStateChanged");
    }

    public void onPreSaleModelStateChanged(int state) {
        Log.v(TAG, "onPreSaleModelStateChanged");
    }

    public void onResetCurveDataChanged(int type, int value) {
        Log.v(TAG, "onResetCurveDataChanged");
    }

    public void onOilPointFlagChanged(int flag) {
        Log.v(TAG, "onOilPointFlagChanged");
    }

    public void onElectricPointFlagChanged(int flag) {
        Log.v(TAG, "onElectricPointFlagChanged");
    }

    public void onLast200MElectricConChanged(float value) {
        Log.v(TAG, "onLast200MElectricConChanged");
    }

    public void onLast50KMElectricConAvgChanged(float value) {
        Log.v(TAG, "onLast50KMElectricConAvgChanged");
    }

    public void onLast50KMOilConAvgChanged(float value) {
        Log.v(TAG, "onLast50KMOilConAvgChanged");
    }

    public void onLast200MOilConChanged(float value) {
        Log.v(TAG, "onLast200MOilConChanged");
    }

    public void onHEVElectricPointFlagChanged(int flag) {
        Log.v(TAG, "onHEVElectricPointFlagChanged");
    }

    public void onHEVLast200MElectricConChanged(float value) {
        Log.v(TAG, "onHEVLast200MElectricConChanged");
    }

    public void onOperationModeNormalChanged(int value) {
        Log.v(TAG, "onOperationModeNormalChanged");
    }

    public void onRoadSurfaceModeNormalChanged(int value) {
        Log.v(TAG, "onOperationModeNormalChanged");
    }

    public void oniTACStateChanged(int state) {
        Log.v(TAG, "oniTACStateChanged");
    }

    public void oniTACAllowTagChanged(int state) {
        Log.v(TAG, "oniTACAllowTagChanged");
    }

    public void onChangePHintChanged(int state) {
        Log.v(TAG, "onChangePHintChanged");
    }

    public void oniTACExitTagChanged(int state) {
        Log.v(TAG, "oniTACExitTagChanged");
    }

    public void oniTACModeChanged(int state) {
        Log.v(TAG, "oniTACModeChanged");
    }

    public void onChargePortCoverHintChanged(int value) {
        Log.v(TAG, "onChargePortCoverHintChanged value is = " + value);
    }

    public void onTripDrivePercentChanged(int state) {
        Log.v(TAG, "onTripDrivePercentChanged");
    }

    public void onTripACPercentChanged(int state) {
        Log.v(TAG, "onTripACPercentChanged");
    }

    public void onTripElecAppliancePercentChanged(int state) {
        Log.v(TAG, "onTripElecAppliancePercentChanged");
    }

    public void onTripOtherPercentChanged(int state) {
        Log.v(TAG, "onTripOtherPercentChanged");
    }

    public void onTripPointFlagChanged(int state) {
        Log.v(TAG, "onTripPointFlagChanged");
    }

    public void onTripDrivePercent50KMChanged(int state) {
        Log.v(TAG, "onTripDrivePercent50KMChanged");
    }

    public void onTripACPercent50KMChanged(int state) {
        Log.v(TAG, "onTripACPercent50KMChanged");
    }

    public void onTripElecAppliancePercent50KMChanged(int state) {
        Log.v(TAG, "onTripElecAppliancePercent50KMChanged");
    }

    public void onTripOtherPercent50KMChanged(int state) {
        Log.v(TAG, "onTripOtherPercent50KMChanged");
    }

    public void onTripOilConAvgChanged(float state) {
        Log.v(TAG, "onTripOilConAvgChanged");
    }

    public void onTripEnergyConAvgChanged(float state) {
        Log.v(TAG, "onTripEnergyConAvgChanged");
    }

    public void onYEnergyConChanged(float state) {
        Log.v(TAG, "onYEnergyConChanged");
    }

    public void onTripElecConAvgChanged(float state) {
        Log.v(TAG, "onTripElecConAvgChanged");
    }

    public void onYEnergyConLimitChanged(int state) {
        Log.v(TAG, "onYEnergyConLimitChanged");
    }

    public void onTripLast50KMElectricConAvgChanged(float state) {
        Log.v(TAG, "onTripLast50KMElectricConAvgChanged");
    }

    public void onTripLast50KMOilConAvgChanged(float state) {
        Log.v(TAG, "onTripLast50KMOilConAvgChanged");
    }

    public void onTripLast50KMFuelConAvgChanged(float state) {
        Log.v(TAG, "onTripLast50KMFuelConAvgChanged");
    }

    public void onRollBenchModeStatusChanged(int status) {
        Log.v(TAG, "onRollBenchModeStatusChanged status is = " + status);
    }
}

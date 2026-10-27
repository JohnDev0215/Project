package android.hardware.bydauto.bodywork;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoBodyworkListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoBodyworkListener";

    public AbsBYDAutoBodyworkListener() {
        Log.v(TAG, TAG);
    }

    public void onWindowStateChanged(int i, int i2) {
        Log.v(TAG, "onWindowStateChanged");
    }

    public void onDoorStateChanged(int i, int i2) {
        Log.v(TAG, "onDoorStateChanged");
    }

    public void onAutoSystemStateChanged(int i) {
        Log.v(TAG, "onAutoSystemStateChanged");
    }

    public void onSteeringWheelValueChanged(int i, double d) {
        Log.v(TAG, "onSteeringWheelValueChanged");
    }

    public void onSteeringWheelAngleChanged(double d, int i, int i2) {
        Log.v(TAG, "onSteeringWheelAngleChanged");
    }

    public void onSteeringWheelSpeedChanged(double d, int i, int i2) {
        Log.v(TAG, "onSteeringWheelSpeedChanged");
    }

    public void onPowerLevelChanged(int i) {
        Log.v(TAG, "onPowerLevelChanged");
    }

    public void onBatteryVoltageLevelChanged(int i) {
        Log.v(TAG, "onBatteryVoltageLevelChanged");
    }

    public void onPowerDayModeChanged(int i) {
        Log.v(TAG, "onPowerDayModeChanged");
    }

    public void onAutoVINChanged(String str) {
        Log.v(TAG, "onAutoVINChanged");
    }

    public void onMoonRoofConfigChanged(int i) {
        Log.v(TAG, "onMoonRoofConfigChanged");
    }

    public void onFuelElecLowPowerChanged(int i) {
        Log.v(TAG, "onFuelElecLowPowerChanged");
    }

    public void onAlarmStateChanged(int i) {
        Log.v(TAG, "onAlarmStateChanged");
    }

    public void onWindowOpenPercentChanged(int i, int i2) {
        Log.v(TAG, "onWindowOpenPercentChanged");
    }

    public void onCarWindowAntiPinchConfigChanged(int i) {
        Log.v(TAG, "onCarWindowAntiPinchConfigChanged");
    }

    public void onRainCloseWindowChanged(int i) {
        Log.v(TAG, "onRainCloseWindowChanged");
    }

    public void onMessage5sOnlineStateChanged(int i, int i2) {
        Log.v(TAG, "onMessage5sOnlineStateChanged");
    }

    public void onHasMessageChanged(int i, int i2) {
        Log.v(TAG, "onHasMessageChanged");
    }

    public void onSunroofStateChanged(int i) {
        Log.v(TAG, "onSunroofStateChanged");
    }

    public void onWindowPermitStateChanged(int i) {
        Log.v(TAG, "onWindowPermitStateChanged");
    }

    public void onWindoblindInitStateChanged(int i) {
        Log.v(TAG, "onWindoblindInitStateChanged");
    }

    public void onSunroofInitStateChanged(int i) {
        Log.v(TAG, "onSunroofInitStateChanged");
    }

    public void onSunroofCloseNoticeChanged(int i) {
        Log.v(TAG, "onSunroofCloseNoticeChanged");
    }

    public void onSunroofWindowblindPositionChanged(int i) {
        Log.v(TAG, "onSunroofWindowblindPositionChanged");
    }

    public void onSunroofPositionChanged(int i) {
        Log.v(TAG, "onSunroofPositionChanged");
    }

    public void onSmartVoiceLimitChanged(int i) {
        Log.v(TAG, "onSmartVoiceLimitChanged");
    }

    @Deprecated
    public void onBatteryPowerChanged(int i) {
        Log.v(TAG, "onBatteryPowerChanged");
    }

    public void onBatteryPowerHEVChanged(double d) {
        Log.v(TAG, "onBatteryPowerHEVChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
        Log.v(TAG, "onError");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
        Log.v(TAG, "onDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onWindowLockStateChanged(int i, int i2) {
        Log.v(TAG, "onWindowLockStateChanged");
    }

    public void onBatteryVoltageLevelLowTips(int i) {
        Log.v(TAG, "onBatteryVoltageLevelLowTips");
    }

    public void onWindowControlStateChanged(int i, int i2) {
        Log.v(TAG, "onWindowControlStateChanged");
    }

    public void onRemoteWhistleChange(int i) {
        Log.v(TAG, "onRemoteWhistleChange");
    }

    public void onRemoteWhistleFail(int i) {
        Log.v(TAG, "onRemoteWhistleFail");
    }

    public void onWindowblindStateChanged(int i) {
        Log.v(TAG, "onWindowblindStateChanged");
    }

    public void onRearWindowblindStateChanged(int i) {
        Log.v(TAG, "onRearWindowblindStateChanged");
    }

    public void onRearWindowblindInitStateChanged(int i) {
        Log.v(TAG, "onRearWindowblindInitStateChanged");
    }

    public void onRearWindowblindPositionChanged(int i) {
        Log.v(TAG, "onRearWindowblindPositionChanged");
    }

    public void onBackDoorInitStateChanged(int i) {
        Log.v(TAG, "onBackDoorInitStateChanged");
    }

    public void onBackDoorCurStateChanged(int i) {
        Log.v(TAG, "onBackDoorCurStateChanged");
    }

    public void onRightStrutMorotRunningStateChanged(int i) {
        Log.v(TAG, "onRightStrutMorotRunningStateChanged");
    }

    public void onBackDoorPositionChanged(int i) {
        Log.v(TAG, "onBackDoorPositionChanged");
    }

    public void onBackDoorAntiPlayModeChanged(int i) {
        Log.v(TAG, "onBackDoorAntiPlayModeChanged");
    }

    public void onElecSlideDoorStateChanged(int i) {
        Log.v(TAG, "onElecSlideDoorStateChanged");
    }

    public void onLeftSlideDoorStateChanged(int i) {
        Log.v(TAG, "onLeftSlideDoorStateChanged");
    }

    public void onRightSlideDoorStateChanged(int i) {
        Log.v(TAG, "onRightSlideDoorStateChanged");
    }

    public void onLeftSlideDoorActionChanged(int i) {
        Log.v(TAG, "onLeftSlideDoorActionChanged");
    }

    public void onRightSlideDoorActionChanged(int i) {
        Log.v(TAG, "onRightSlideDoorActionChanged");
    }

    public void onLeftSlideDoorPercentChanged(int i) {
        Log.v(TAG, "onLeftSlideDoorPercentChanged");
    }

    public void onRightSlideDoorPercentChanged(int i) {
        Log.v(TAG, "onRightSlideDoorPercentChanged");
    }

    public void onWindowInitStateChanged(int i, int i2) {
        Log.v(TAG, "onWindowInitStateChanged");
    }

    public void onWindowThermalProtectionStateChanged(int i, int i2) {
        Log.v(TAG, "onWindowThermalProtectionStateChanged");
    }

    public void onLeftStrutMorotRunningStateChanged(int i) {
        Log.v(TAG, "onLeftStrutMorotRunningStateChanged");
    }

    public void onReadyIndicatorLightStateChanged(int i) {
        Log.v(TAG, "onReadyIndicatorLightStateChanged");
    }

    public void onEnergyTypeChanged(int i) {
        Log.v(TAG, "onEnergyTypeChanged");
    }

    public void onDoorLockStatusChanged(int i, int i2) {
        Log.v(TAG, "onDoorLockStatusChanged");
    }

    public void onLightOn(int i) {
        Log.v(TAG, "onLightOn");
    }

    public void onLightOff(int i) {
        Log.v(TAG, "onLightOff");
    }

    public void onTurnRearviewMirrorChanged(int i, float f, float f2) {
        Log.v(TAG, "onTurnRearviewMirrorChanged");
    }

    public void onAutoExternalRearMirrorFoldActionStateChanged(int i) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldActionStateChanged");
    }

    public void onAutoExternalRearMirrorFoldedState(int i) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldedState");
    }

    public void turnSteeringWheelChanged(int i, float f, float f2) {
        Log.v(TAG, "turnSteeringWheelChanged");
    }

    public void onRecoverOrSaveParamsChanged(int i, int i2, int i3, int i4) {
        Log.v(TAG, "onRecoverOrSaveParamsChanged");
    }

    @Deprecated
    public void onRearLampLightStatusChanged(int i) {
        Log.v(TAG, "onRearLampLightStatusChanged");
    }

    public void onRearLampLightStateChanged(int i) {
        Log.v(TAG, "onRearLampLightStateChanged");
    }

    public void onBCMVechileStatusChanged(int i) {
        Log.v(TAG, "onBCMVechileStatusChanged");
    }

    public void onMainSaveOrRecoverStateChanged(int i, int i2) {
        Log.v(TAG, "onMainSaveOrRecoverStateChanged");
    }

    public void onSeatModeWakeupFeedbackStateChanged(int i) {
        Log.v(TAG, "onSeatModeWakeupFeedbackStateChanged");
    }

    public void onInternalDoorHandleStateChanged(int i, int i2) {
        Log.v(TAG, "onInternalDoorHandleStateChanged");
    }

    public void onShiftModeChanged(int i) {
        Log.v(TAG, "onShiftModeChanged");
    }

    public void onScreenControlSlideDoorChanged(int i, int i2) {
        Log.v(TAG, "onScreenControlSlideDoorChanged");
    }

    public void onEvReadyIndicatorLightStateChanged(int i) {
        Log.v(TAG, "onEvReadyIndicatorLightStateChanged state is " + i);
    }
}

package android.hardware.bydauto.bodywork;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoBodyworkListener implements d {
    public static final String TAG = "AbsBYDAutoBodyworkListener";

    public AbsBYDAutoBodyworkListener() {
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

    public void onAlarmStateChanged(int i3) {
        Log.v(TAG, "onAlarmStateChanged");
    }

    public void onAutoExternalRearMirrorFoldActionStateChanged(int i3) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldActionStateChanged");
    }

    public void onAutoExternalRearMirrorFoldedState(int i3) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldedState");
    }

    public void onAutoSystemStateChanged(int i3) {
        Log.v(TAG, "onAutoSystemStateChanged");
    }

    public void onAutoVINChanged(String str) {
        Log.v(TAG, "onAutoVINChanged");
    }

    public void onBCMVechileStatusChanged(int i3) {
        Log.v(TAG, "onBCMVechileStatusChanged");
    }

    public void onBackDoorAntiPlayModeChanged(int i3) {
        Log.v(TAG, "onBackDoorAntiPlayModeChanged");
    }

    public void onBackDoorCurStateChanged(int i3) {
        Log.v(TAG, "onBackDoorCurStateChanged");
    }

    public void onBackDoorInitStateChanged(int i3) {
        Log.v(TAG, "onBackDoorInitStateChanged");
    }

    public void onBackDoorPositionChanged(int i3) {
        Log.v(TAG, "onBackDoorPositionChanged");
    }

    @Deprecated
    public void onBatteryPowerChanged(int i3) {
        Log.v(TAG, "onBatteryPowerChanged");
    }

    public void onBatteryPowerHEVChanged(double d3) {
        Log.v(TAG, "onBatteryPowerHEVChanged");
    }

    public void onBatteryVoltageLevelChanged(int i3) {
        Log.v(TAG, "onBatteryVoltageLevelChanged");
    }

    public void onBatteryVoltageLevelLowTips(int i3) {
        Log.v(TAG, "onBatteryVoltageLevelLowTips");
    }

    public void onCarWindowAntiPinchConfigChanged(int i3) {
        Log.v(TAG, "onCarWindowAntiPinchConfigChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onDoorHandleStateChanged(int i3, int i4) {
        Log.v(TAG, "onDoorHandleStateChanged");
    }

    public void onDoorLockStatusChanged(int i3, int i4) {
        Log.v(TAG, "onDoorLockStatusChanged");
    }

    public void onDoorStateChanged(int i3, int i4) {
        Log.v(TAG, "onDoorStateChanged");
    }

    public void onElecSlideDoorStateChanged(int i3) {
        Log.v(TAG, "onElecSlideDoorStateChanged");
    }

    public void onEnergyTypeChanged(int i3) {
        Log.v(TAG, "onEnergyTypeChanged");
    }

    public void onError(int i3, String str) {
        Log.v(TAG, "onError");
    }

    public void onEvReadyIndicatorLightStateChanged(int i3) {
        Log.v(TAG, "onEvReadyIndicatorLightStateChanged state is " + i3);
    }

    public void onFuelElecLowPowerChanged(int i3) {
        Log.v(TAG, "onFuelElecLowPowerChanged");
    }

    public void onHasMessageChanged(int i3, int i4) {
        Log.v(TAG, "onHasMessageChanged");
    }

    public void onInternalDoorHandleStateByRearBCMChanged(int i3, int i4) {
        Log.v(TAG, "onInternalDoorHandleStateByRearBCMChanged area is " + i3 + " state is " + i4);
    }

    public void onInternalDoorHandleStateChanged(int i3, int i4) {
        Log.v(TAG, "onInternalDoorHandleStateChanged");
    }

    public void onLeftSlideDoorActionChanged(int i3) {
        Log.v(TAG, "onLeftSlideDoorActionChanged");
    }

    public void onLeftSlideDoorPercentChanged(int i3) {
        Log.v(TAG, "onLeftSlideDoorPercentChanged");
    }

    public void onLeftSlideDoorStateChanged(int i3) {
        Log.v(TAG, "onLeftSlideDoorStateChanged");
    }

    public void onLeftStrutMorotRunningStateChanged(int i3) {
        Log.v(TAG, "onLeftStrutMorotRunningStateChanged");
    }

    public void onLightOff(int i3) {
        Log.v(TAG, "onLightOff");
    }

    public void onLightOn(int i3) {
        Log.v(TAG, "onLightOn");
    }

    public void onMainSaveOrRecoverStateChanged(int i3, int i4) {
        Log.v(TAG, "onMainSaveOrRecoverStateChanged");
    }

    public void onMessage5sOnlineStateChanged(int i3, int i4) {
        Log.v(TAG, "onMessage5sOnlineStateChanged");
    }

    public void onMoonRoofConfigChanged(int i3) {
        Log.v(TAG, "onMoonRoofConfigChanged");
    }

    public void onPowerDayModeChanged(int i3) {
        Log.v(TAG, "onPowerDayModeChanged");
    }

    public void onPowerLevelChanged(int i3) {
        Log.v(TAG, "onPowerLevelChanged");
    }

    public void onRainCloseWindowChanged(int i3) {
        Log.v(TAG, "onRainCloseWindowChanged");
    }

    public void onReadyIndicatorLightStateChanged(int i3) {
        Log.v(TAG, "onReadyIndicatorLightStateChanged");
    }

    public void onRearLampLightStateChanged(int i3) {
        Log.v(TAG, "onRearLampLightStateChanged");
    }

    @Deprecated
    public void onRearLampLightStatusChanged(int i3) {
        Log.v(TAG, "onRearLampLightStatusChanged");
    }

    public void onRearWindowblindInitStateChanged(int i3) {
        Log.v(TAG, "onRearWindowblindInitStateChanged");
    }

    public void onRearWindowblindPositionChanged(int i3) {
        Log.v(TAG, "onRearWindowblindPositionChanged");
    }

    public void onRearWindowblindStateChanged(int i3) {
        Log.v(TAG, "onRearWindowblindStateChanged");
    }

    public void onRecoverOrSaveParamsChanged(int i3, int i4, int i5, int i6) {
        Log.v(TAG, "onRecoverOrSaveParamsChanged");
    }

    public void onRemoteWhistleChange(int i3) {
        Log.v(TAG, "onRemoteWhistleChange");
    }

    public void onRemoteWhistleFail(int i3) {
        Log.v(TAG, "onRemoteWhistleFail");
    }

    public void onRightSlideDoorActionChanged(int i3) {
        Log.v(TAG, "onRightSlideDoorActionChanged");
    }

    public void onRightSlideDoorPercentChanged(int i3) {
        Log.v(TAG, "onRightSlideDoorPercentChanged");
    }

    public void onRightSlideDoorStateChanged(int i3) {
        Log.v(TAG, "onRightSlideDoorStateChanged");
    }

    public void onRightStrutMorotRunningStateChanged(int i3) {
        Log.v(TAG, "onRightStrutMorotRunningStateChanged");
    }

    public void onScreenControlSlideDoorChanged(int i3, int i4) {
        Log.v(TAG, "onScreenControlSlideDoorChanged");
    }

    public void onSeatModeWakeupFeedbackStateChanged(int i3) {
        Log.v(TAG, "onSeatModeWakeupFeedbackStateChanged");
    }

    public void onShiftModeChanged(int i3) {
        Log.v(TAG, "onShiftModeChanged");
    }

    public void onSmartVoiceLimitChanged(int i3) {
        Log.v(TAG, "onSmartVoiceLimitChanged");
    }

    public void onSteeringWheelAngleChanged(double d3, int i3, int i4) {
        Log.v(TAG, "onSteeringWheelAngleChanged");
    }

    public void onSteeringWheelSpeedChanged(double d3, int i3, int i4) {
        Log.v(TAG, "onSteeringWheelSpeedChanged");
    }

    public void onSteeringWheelValueChanged(int i3, double d3) {
        Log.v(TAG, "onSteeringWheelValueChanged");
    }

    public void onSunroofCloseNoticeChanged(int i3) {
        Log.v(TAG, "onSunroofCloseNoticeChanged");
    }

    public void onSunroofInitStateChanged(int i3) {
        Log.v(TAG, "onSunroofInitStateChanged");
    }

    public void onSunroofPositionChanged(int i3) {
        Log.v(TAG, "onSunroofPositionChanged");
    }

    public void onSunroofStateChanged(int i3) {
        Log.v(TAG, "onSunroofStateChanged");
    }

    public void onSunroofWindowblindPositionChanged(int i3) {
        Log.v(TAG, "onSunroofWindowblindPositionChanged");
    }

    public void onTurnRearviewMirrorChanged(int i3, float f3, float f4) {
        Log.v(TAG, "onTurnRearviewMirrorChanged");
    }

    public void onWindoblindInitStateChanged(int i3) {
        Log.v(TAG, "onWindoblindInitStateChanged");
    }

    public void onWindowControlStateChanged(int i3, int i4) {
        Log.v(TAG, "onWindowControlStateChanged");
    }

    public void onWindowInitStateChanged(int i3, int i4) {
        Log.v(TAG, "onWindowInitStateChanged");
    }

    public void onWindowLockStateChanged(int i3, int i4) {
        Log.v(TAG, "onWindowLockStateChanged");
    }

    public void onWindowOpenPercentChanged(int i3, int i4) {
        Log.v(TAG, "onWindowOpenPercentChanged");
    }

    public void onWindowPermitStateChanged(int i3) {
        Log.v(TAG, "onWindowPermitStateChanged");
    }

    public void onWindowStateChanged(int i3, int i4) {
        Log.v(TAG, "onWindowStateChanged");
    }

    public void onWindowThermalProtectionStateChanged(int i3, int i4) {
        Log.v(TAG, "onWindowThermalProtectionStateChanged");
    }

    public void onWindowblindStateChanged(int i3) {
        Log.v(TAG, "onWindowblindStateChanged");
    }

    public void turnSteeringWheelChanged(int i3, float f3, float f4) {
        Log.v(TAG, "turnSteeringWheelChanged");
    }
}

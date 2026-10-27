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
    public /* bridge */ /* synthetic */ void isConnected(boolean z7) {
        super.isConnected(z7);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z7) {
        super.isOptimizationApi(z7);
    }

    public void onAlarmStateChanged(int i8) {
        Log.v(TAG, "onAlarmStateChanged");
    }

    public void onAutoExternalRearMirrorFoldActionStateChanged(int i8) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldActionStateChanged");
    }

    public void onAutoExternalRearMirrorFoldedState(int i8) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldedState");
    }

    public void onAutoSystemStateChanged(int i8) {
        Log.v(TAG, "onAutoSystemStateChanged");
    }

    public void onAutoVINChanged(String str) {
        Log.v(TAG, "onAutoVINChanged");
    }

    public void onBCMVechileStatusChanged(int i8) {
        Log.v(TAG, "onBCMVechileStatusChanged");
    }

    public void onBackDoorAntiPlayModeChanged(int i8) {
        Log.v(TAG, "onBackDoorAntiPlayModeChanged");
    }

    public void onBackDoorCurStateChanged(int i8) {
        Log.v(TAG, "onBackDoorCurStateChanged");
    }

    public void onBackDoorInitStateChanged(int i8) {
        Log.v(TAG, "onBackDoorInitStateChanged");
    }

    public void onBackDoorPositionChanged(int i8) {
        Log.v(TAG, "onBackDoorPositionChanged");
    }

    @Deprecated
    public void onBatteryPowerChanged(int i8) {
        Log.v(TAG, "onBatteryPowerChanged");
    }

    public void onBatteryPowerHEVChanged(double d8) {
        Log.v(TAG, "onBatteryPowerHEVChanged");
    }

    public void onBatteryVoltageLevelChanged(int i8) {
        Log.v(TAG, "onBatteryVoltageLevelChanged");
    }

    public void onBatteryVoltageLevelLowTips(int i8) {
        Log.v(TAG, "onBatteryVoltageLevelLowTips");
    }

    public void onCarWindowAntiPinchConfigChanged(int i8) {
        Log.v(TAG, "onCarWindowAntiPinchConfigChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onDoorHandleStateChanged(int i8, int i9) {
        Log.v(TAG, "onDoorHandleStateChanged");
    }

    public void onDoorLockStatusChanged(int i8, int i9) {
        Log.v(TAG, "onDoorLockStatusChanged");
    }

    public void onDoorStateChanged(int i8, int i9) {
        Log.v(TAG, "onDoorStateChanged");
    }

    public void onElecSlideDoorStateChanged(int i8) {
        Log.v(TAG, "onElecSlideDoorStateChanged");
    }

    public void onEnergyTypeChanged(int i8) {
        Log.v(TAG, "onEnergyTypeChanged");
    }

    public void onError(int i8, String str) {
        Log.v(TAG, "onError");
    }

    public void onEvReadyIndicatorLightStateChanged(int i8) {
        Log.v(TAG, "onEvReadyIndicatorLightStateChanged state is " + i8);
    }

    public void onFseSeatSystemWorkModeChanged(int i8) {
        Log.v(TAG, "onFseSeatSystemWorkModeChanged state is " + i8);
    }

    public void onFuelElecLowPowerChanged(int i8) {
        Log.v(TAG, "onFuelElecLowPowerChanged");
    }

    public void onHasMessageChanged(int i8, int i9) {
        Log.v(TAG, "onHasMessageChanged");
    }

    public void onInternalDoorHandleStateByRearBCMChanged(int i8, int i9) {
        Log.v(TAG, "onInternalDoorHandleStateByRearBCMChanged area is " + i8 + " state is " + i9);
    }

    public void onInternalDoorHandleStateChanged(int i8, int i9) {
        Log.v(TAG, "onInternalDoorHandleStateChanged");
    }

    public void onLeftSeatRailsRow3FeedbackPositionChanged(int i8) {
        Log.v(TAG, "onLeftSeatRailsRow3FeedbackPositionChanged state is " + i8);
    }

    public void onLeftSeatRow3NonElectricallyAdjustStateChanged(int i8) {
        Log.v(TAG, "onLeftSeatRow3NonElectricallyAdjustStateChanged state is " + i8);
    }

    public void onLeftSlideDoorActionChanged(int i8) {
        Log.v(TAG, "onLeftSlideDoorActionChanged");
    }

    public void onLeftSlideDoorPercentChanged(int i8) {
        Log.v(TAG, "onLeftSlideDoorPercentChanged");
    }

    public void onLeftSlideDoorStateChanged(int i8) {
        Log.v(TAG, "onLeftSlideDoorStateChanged");
    }

    public void onLeftStrutMorotRunningStateChanged(int i8) {
        Log.v(TAG, "onLeftStrutMorotRunningStateChanged");
    }

    public void onLightOff(int i8) {
        Log.v(TAG, "onLightOff");
    }

    public void onLightOn(int i8) {
        Log.v(TAG, "onLightOn");
    }

    public void onMainSaveOrRecoverStateChanged(int i8, int i9) {
        Log.v(TAG, "onMainSaveOrRecoverStateChanged");
    }

    public void onMessage5sOnlineStateChanged(int i8, int i9) {
        Log.v(TAG, "onMessage5sOnlineStateChanged");
    }

    public void onMoonRoofConfigChanged(int i8) {
        Log.v(TAG, "onMoonRoofConfigChanged");
    }

    public void onPowerDayModeChanged(int i8) {
        Log.v(TAG, "onPowerDayModeChanged");
    }

    public void onPowerLevelChanged(int i8) {
        Log.v(TAG, "onPowerLevelChanged");
    }

    public void onRainCloseWindowChanged(int i8) {
        Log.v(TAG, "onRainCloseWindowChanged");
    }

    public void onReadyIndicatorLightStateChanged(int i8) {
        Log.v(TAG, "onReadyIndicatorLightStateChanged");
    }

    public void onRearLampLightStateChanged(int i8) {
        Log.v(TAG, "onRearLampLightStateChanged");
    }

    @Deprecated
    public void onRearLampLightStatusChanged(int i8) {
        Log.v(TAG, "onRearLampLightStatusChanged");
    }

    public void onRearWindowblindInitStateChanged(int i8) {
        Log.v(TAG, "onRearWindowblindInitStateChanged");
    }

    public void onRearWindowblindPositionChanged(int i8) {
        Log.v(TAG, "onRearWindowblindPositionChanged");
    }

    public void onRearWindowblindStateChanged(int i8) {
        Log.v(TAG, "onRearWindowblindStateChanged");
    }

    public void onRecoverOrSaveParamsChanged(int i8, int i9, int i10, int i11) {
        Log.v(TAG, "onRecoverOrSaveParamsChanged");
    }

    public void onRemoteWhistleChange(int i8) {
        Log.v(TAG, "onRemoteWhistleChange");
    }

    public void onRemoteWhistleFail(int i8) {
        Log.v(TAG, "onRemoteWhistleFail");
    }

    public void onRightSeatRailsRow3FeedbackPositionChanged(int i8) {
        Log.v(TAG, "onRightSeatRailsRow3FeedbackPositionChanged state is " + i8);
    }

    public void onRightSlideDoorActionChanged(int i8) {
        Log.v(TAG, "onRightSlideDoorActionChanged");
    }

    public void onRightSlideDoorPercentChanged(int i8) {
        Log.v(TAG, "onRightSlideDoorPercentChanged");
    }

    public void onRightSlideDoorStateChanged(int i8) {
        Log.v(TAG, "onRightSlideDoorStateChanged");
    }

    public void onRightStrutMorotRunningStateChanged(int i8) {
        Log.v(TAG, "onRightStrutMorotRunningStateChanged");
    }

    public void onScreenControlSlideDoorChanged(int i8, int i9) {
        Log.v(TAG, "onScreenControlSlideDoorChanged");
    }

    public void onSeatModeCurFeedbackStatusChanged(int i8) {
        Log.v(TAG, "onSeatModeCurFeedbackStatusChanged state is " + i8);
    }

    public void onSeatModeCurFeedbackStatusChanged2(int i8) {
        Log.v(TAG, "onSeatModeCurFeedbackStatusChanged2 state is " + i8);
    }

    public void onSeatModeFailFeedbackStatusChanged(int i8) {
        Log.v(TAG, "onSeatModeFailFeedbackStatusChanged state is " + i8);
    }

    public void onSeatModeFailFeedbackStatusChanged2(int i8) {
        Log.v(TAG, "onSeatModeFailFeedbackStatusChanged2 state is " + i8);
    }

    public void onSeatModeSuccessFeedbackStatusChanged(int i8) {
        Log.v(TAG, "onSeatModeSuccessFeedbackStatusChanged state is " + i8);
    }

    public void onSeatModeSuccessFeedbackStatusChanged2(int i8) {
        Log.v(TAG, "onSeatModeSuccessFeedbackStatusChanged2 state is " + i8);
    }

    public void onSeatModeWakeupFeedbackStateChanged(int i8) {
        Log.v(TAG, "onSeatModeWakeupFeedbackStateChanged");
    }

    public void onShiftModeChanged(int i8) {
        Log.v(TAG, "onShiftModeChanged");
    }

    public void onSmartVoiceLimitChanged(int i8) {
        Log.v(TAG, "onSmartVoiceLimitChanged");
    }

    public void onSteeringWheelAngleChanged(double d8, int i8, int i9) {
        Log.v(TAG, "onSteeringWheelAngleChanged");
    }

    public void onSteeringWheelSpeedChanged(double d8, int i8, int i9) {
        Log.v(TAG, "onSteeringWheelSpeedChanged");
    }

    public void onSteeringWheelValueChanged(int i8, double d8) {
        Log.v(TAG, "onSteeringWheelValueChanged");
    }

    public void onSunroofCloseNoticeChanged(int i8) {
        Log.v(TAG, "onSunroofCloseNoticeChanged");
    }

    public void onSunroofInitStateChanged(int i8) {
        Log.v(TAG, "onSunroofInitStateChanged");
    }

    public void onSunroofPositionChanged(int i8) {
        Log.v(TAG, "onSunroofPositionChanged");
    }

    public void onSunroofStateChanged(int i8) {
        Log.v(TAG, "onSunroofStateChanged");
    }

    public void onSunroofWindowblindPositionChanged(int i8) {
        Log.v(TAG, "onSunroofWindowblindPositionChanged");
    }

    public void onThirdRowSeatsExecutionResultsChanged(int i8) {
        Log.v(TAG, "onThirdRowSeatsExecutionResultsChanged state is " + i8);
    }

    public void onTurnRearviewMirrorChanged(int i8, float f8, float f9) {
        Log.v(TAG, "onTurnRearviewMirrorChanged");
    }

    public void onWindoblindInitStateChanged(int i8) {
        Log.v(TAG, "onWindoblindInitStateChanged");
    }

    public void onWindowControlStateChanged(int i8, int i9) {
        Log.v(TAG, "onWindowControlStateChanged");
    }

    public void onWindowInitStateChanged(int i8, int i9) {
        Log.v(TAG, "onWindowInitStateChanged");
    }

    public void onWindowLockStateChanged(int i8, int i9) {
        Log.v(TAG, "onWindowLockStateChanged");
    }

    public void onWindowOpenPercentChanged(int i8, int i9) {
        Log.v(TAG, "onWindowOpenPercentChanged");
    }

    public void onWindowPermitStateChanged(int i8) {
        Log.v(TAG, "onWindowPermitStateChanged");
    }

    public void onWindowStateChanged(int i8, int i9) {
        Log.v(TAG, "onWindowStateChanged");
    }

    public void onWindowThermalProtectionStateChanged(int i8, int i9) {
        Log.v(TAG, "onWindowThermalProtectionStateChanged");
    }

    public void onWindowblindStateChanged(int i8) {
        Log.v(TAG, "onWindowblindStateChanged");
    }

    public void turnSteeringWheelChanged(int i8, float f8, float f9) {
        Log.v(TAG, "turnSteeringWheelChanged");
    }
}

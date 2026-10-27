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

    public void onWindowStateChanged(int area, int state) {
        Log.v(TAG, "onWindowStateChanged");
    }

    public void onDoorStateChanged(int area, int state) {
        Log.v(TAG, "onDoorStateChanged");
    }

    public void onAutoSystemStateChanged(int state) {
        Log.v(TAG, "onAutoSystemStateChanged");
    }

    public void onSteeringWheelValueChanged(int type, double value) {
        Log.v(TAG, "onSteeringWheelValueChanged");
    }

    public void onSteeringWheelAngleChanged(double value, int sensorState, int calibrationState) {
        Log.v(TAG, "onSteeringWheelAngleChanged");
    }

    public void onSteeringWheelSpeedChanged(double value, int sensorState, int calibrationState) {
        Log.v(TAG, "onSteeringWheelSpeedChanged");
    }

    public void onPowerLevelChanged(int level) {
        Log.v(TAG, "onPowerLevelChanged");
    }

    public void onBatteryVoltageLevelChanged(int level) {
        Log.v(TAG, "onBatteryVoltageLevelChanged");
    }

    public void onPowerDayModeChanged(int state) {
        Log.v(TAG, "onPowerDayModeChanged");
    }

    public void onAutoVINChanged(String vin) {
        Log.v(TAG, "onAutoVINChanged");
    }

    public void onMoonRoofConfigChanged(int config) {
        Log.v(TAG, "onMoonRoofConfigChanged");
    }

    public void onFuelElecLowPowerChanged(int state) {
        Log.v(TAG, "onFuelElecLowPowerChanged");
    }

    public void onAlarmStateChanged(int state) {
        Log.v(TAG, "onAlarmStateChanged");
    }

    public void onWindowOpenPercentChanged(int area, int percent) {
        Log.v(TAG, "onWindowOpenPercentChanged");
    }

    public void onCarWindowAntiPinchConfigChanged(int config) {
        Log.v(TAG, "onCarWindowAntiPinchConfigChanged");
    }

    public void onRainCloseWindowChanged(int state) {
        Log.v(TAG, "onRainCloseWindowChanged");
    }

    public void onMessage5sOnlineStateChanged(int id, int state) {
        Log.e(TAG, "onMessage5sOnlineStateChanged id " + id + " state " + state);
    }

    public void onHasMessageChanged(int id, int state) {
        Log.e(TAG, "onHasMessageChanged id " + id + " state " + state);
    }

    public void onSunroofStateChanged(int state) {
        Log.v(TAG, "onSunroofStateChanged");
    }

    public void onWindowPermitStateChanged(int state) {
        Log.v(TAG, "onWindowPermitStateChanged");
    }

    public void onWindoblindInitStateChanged(int state) {
        Log.v(TAG, "onWindoblindInitStateChanged");
    }

    public void onSunroofInitStateChanged(int state) {
        Log.v(TAG, "onSunroofInitStateChanged");
    }

    public void onSunroofCloseNoticeChanged(int state) {
        Log.v(TAG, "onSunroofCloseNoticeChanged");
    }

    public void onSunroofWindowblindPositionChanged(int value) {
        Log.v(TAG, "onSunroofWindowblindPositionChanged");
    }

    public void onSunroofPositionChanged(int value) {
        Log.v(TAG, "onSunroofPositionChanged");
    }

    public void onSmartVoiceLimitChanged(int value) {
        Log.v(TAG, "onSmartVoiceLimitChanged");
    }

    @Deprecated
    public void onBatteryPowerChanged(int value) {
        Log.v(TAG, "onBatteryPowerChanged");
    }

    public void onBatteryPowerHEVChanged(double value) {
        Log.v(TAG, "onBatteryPowerHEVChanged");
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

    public void onWindowLockStateChanged(int area, int state) {
        Log.v(TAG, "onWindowLockStateChanged");
    }

    public void onBatteryVoltageLevelLowTips(int value) {
        Log.v(TAG, "onBatteryVoltageLevelLowTips");
    }

    public void onWindowControlStateChanged(int area, int state) {
        Log.v(TAG, "onWindowControlStateChanged");
    }

    public void onRemoteWhistleChange(int state) {
        Log.v(TAG, "onRemoteWhistleChange");
    }

    public void onRemoteWhistleFail(int state) {
        Log.v(TAG, "onRemoteWhistleFail");
    }

    public void onWindowblindStateChanged(int state) {
        Log.v(TAG, "onWindowblindStateChanged");
    }

    public void onRearWindowblindStateChanged(int state) {
        Log.v(TAG, "onRearWindowblindStateChanged");
    }

    public void onRearWindowblindInitStateChanged(int state) {
        Log.v(TAG, "onRearWindowblindInitStateChanged");
    }

    public void onRearWindowblindPositionChanged(int value) {
        Log.v(TAG, "onRearWindowblindPositionChanged");
    }

    public void onBackDoorInitStateChanged(int state) {
        Log.v(TAG, "onBackDoorInitStateChanged");
    }

    public void onBackDoorCurStateChanged(int state) {
        Log.v(TAG, "onBackDoorCurStateChanged");
    }

    public void onRightStrutMorotRunningStateChanged(int state) {
        Log.v(TAG, "onRightStrutMorotRunningStateChanged");
    }

    public void onBackDoorPositionChanged(int value) {
        Log.v(TAG, "onBackDoorPositionChanged");
    }

    public void onBackDoorAntiPlayModeChanged(int mode) {
        Log.v(TAG, "onBackDoorAntiPlayModeChanged");
    }

    public void onElecSlideDoorStateChanged(int state) {
        Log.v(TAG, "onElecSlideDoorStateChanged");
    }

    public void onLeftSlideDoorStateChanged(int state) {
        Log.v(TAG, "onLeftSlideDoorStateChanged");
    }

    public void onRightSlideDoorStateChanged(int state) {
        Log.v(TAG, "onRightSlideDoorStateChanged");
    }

    public void onLeftSlideDoorActionChanged(int state) {
        Log.v(TAG, "onLeftSlideDoorActionChanged");
    }

    public void onRightSlideDoorActionChanged(int state) {
        Log.v(TAG, "onRightSlideDoorActionChanged");
    }

    public void onLeftSlideDoorPercentChanged(int state) {
        Log.v(TAG, "onLeftSlideDoorPercentChanged");
    }

    public void onRightSlideDoorPercentChanged(int value) {
        Log.v(TAG, "onRightSlideDoorPercentChanged");
    }

    public void onWindowInitStateChanged(int area, int state) {
        Log.v(TAG, "onWindowInitStateChanged");
    }

    public void onWindowThermalProtectionStateChanged(int area, int state) {
        Log.v(TAG, "onWindowThermalProtectionStateChanged");
    }

    public void onLeftStrutMorotRunningStateChanged(int state) {
        Log.v(TAG, "onLeftStrutMorotRunningStateChanged");
    }

    public void onReadyIndicatorLightStateChanged(int state) {
        Log.v(TAG, "onReadyIndicatorLightStateChanged");
    }

    public void onEnergyTypeChanged(int type) {
        Log.v(TAG, "onEnergyTypeChanged");
    }

    public void onDoorLockStatusChanged(int area, int state) {
        Log.v(TAG, "onDoorLockStatusChanged");
    }

    public void onLightOn(int type) {
        Log.v(TAG, "onLightOn");
    }

    public void onLightOff(int type) {
        Log.v(TAG, "onLightOff");
    }

    public void onTurnRearviewMirrorChanged(int action, float valueH, float valueV) {
        Log.v(TAG, "onTurnRearviewMirrorChanged");
    }

    public void onAutoExternalRearMirrorFoldActionStateChanged(int state) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldActionStateChanged");
    }

    public void onAutoExternalRearMirrorFoldedState(int state) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldedState");
    }

    public void turnSteeringWheelChanged(int action, float valueH, float valueV) {
        Log.v(TAG, "turnSteeringWheelChanged");
    }

    public void onRecoverOrSaveParamsChanged(int position, int location, int state, int code) {
        Log.v(TAG, "onRecoverOrSaveParamsChanged");
    }

    @Deprecated
    public void onRearLampLightStatusChanged(int state) {
        Log.v(TAG, "onRearLampLightStatusChanged");
    }

    public void onRearLampLightStateChanged(int state) {
        Log.v(TAG, "onRearLampLightStateChanged");
    }

    public void onBCMVechileStatusChanged(int value) {
        Log.v(TAG, "onBCMVechileStatusChanged");
    }

    public void onMainSaveOrRecoverStateChanged(int canValue, int value) {
        Log.v(TAG, "onMainSaveOrRecoverStateChanged");
    }

    public void onSeatModeWakeupFeedbackStateChanged(int state) {
        Log.v(TAG, "onSeatModeWakeupFeedbackStateChanged");
    }

    public void onInternalDoorHandleStateChanged(int area, int state) {
        Log.v(TAG, "onInternalDoorHandleStateChanged");
    }

    public void onInternalDoorHandleStateByRearBCMChanged(int area, int state) {
        Log.v(TAG, "onInternalDoorHandleStateByRearBCMChanged area is " + area + " state is " + state);
    }

    public void onDoorHandleStateChanged(int area, int state) {
        Log.v(TAG, "onDoorHandleStateChanged");
    }

    public void onShiftModeChanged(int state) {
        Log.v(TAG, "onShiftModeChanged");
    }

    public void onScreenControlSlideDoorChanged(int area, int state) {
        Log.v(TAG, "onScreenControlSlideDoorChanged");
    }

    public void onEvReadyIndicatorLightStateChanged(int state) {
        Log.v(TAG, "onEvReadyIndicatorLightStateChanged state is " + state);
    }

    public void onThirdRowSeatsExecutionResultsChanged(int state) {
        Log.v(TAG, "onThirdRowSeatsExecutionResultsChanged state is " + state);
    }

    public void onLeftSeatRailsRow3FeedbackPositionChanged(int state) {
        Log.v(TAG, "onLeftSeatRailsRow3FeedbackPositionChanged state is " + state);
    }

    public void onRightSeatRailsRow3FeedbackPositionChanged(int state) {
        Log.v(TAG, "onRightSeatRailsRow3FeedbackPositionChanged state is " + state);
    }

    public void onLeftSeatRow3NonElectricallyAdjustStateChanged(int state) {
        Log.v(TAG, "onLeftSeatRow3NonElectricallyAdjustStateChanged state is " + state);
    }

    public void onFseSeatSystemWorkModeChanged(int state) {
        Log.v(TAG, "onFseSeatSystemWorkModeChanged state is " + state);
    }

    public void onSeatModeCurFeedbackStatusChanged2(int state) {
        Log.v(TAG, "onSeatModeCurFeedbackStatusChanged2 state is " + state);
    }

    public void onSeatModeSuccessFeedbackStatusChanged2(int state) {
        Log.v(TAG, "onSeatModeSuccessFeedbackStatusChanged2 state is " + state);
    }

    public void onSeatModeFailFeedbackStatusChanged2(int state) {
        Log.v(TAG, "onSeatModeFailFeedbackStatusChanged2 state is " + state);
    }

    public void onSeatModeCurFeedbackStatusChanged(int state) {
        Log.v(TAG, "onSeatModeCurFeedbackStatusChanged state is " + state);
    }

    public void onSeatModeSuccessFeedbackStatusChanged(int state) {
        Log.v(TAG, "onSeatModeSuccessFeedbackStatusChanged state is " + state);
    }

    public void onSeatModeFailFeedbackStatusChanged(int state) {
        Log.v(TAG, "onSeatModeFailFeedbackStatusChanged state is " + state);
    }

    public void onLeftMidDoorVoiceWakeUpChanged(int status) {
        Log.d(TAG, "onLeftMidDoorVoiceWakeUpChanged status:" + status);
    }

    public void onRightMidDoorVoiceWakeUpChanged(int status) {
        Log.d(TAG, "onRightMidDoorVoiceWakeUpChanged status:" + status);
    }
}

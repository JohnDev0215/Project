package android.hardware.bydauto.bodywork;

import a.c;
import a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoBodyworkListener implements d {
    protected static final String TAG = "AbsBYDAutoBodyworkListener";

    public AbsBYDAutoBodyworkListener() {
        Log.v(TAG, TAG);
    }

    @Override // a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
        super.isConnected(z6);
    }

    public void onAlarmStateChanged(int i7) {
        Log.v(TAG, "onAlarmStateChanged");
    }

    public void onAutoExternalRearMirrorFoldActionStateChanged(int i7) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldActionStateChanged");
    }

    public void onAutoExternalRearMirrorFoldedState(int i7) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldedState");
    }

    public void onAutoSystemStateChanged(int i7) {
        Log.v(TAG, "onAutoSystemStateChanged");
    }

    public void onAutoVINChanged(String str) {
        Log.v(TAG, "onAutoVINChanged");
    }

    public void onBCMVechileStatusChanged(int i7) {
        Log.v(TAG, "onBCMVechileStatusChanged");
    }

    public void onBackDoorAntiPlayModeChanged(int i7) {
        Log.v(TAG, "onBackDoorAntiPlayModeChanged");
    }

    public void onBackDoorCurStateChanged(int i7) {
        Log.v(TAG, "onBackDoorCurStateChanged");
    }

    public void onBackDoorInitStateChanged(int i7) {
        Log.v(TAG, "onBackDoorInitStateChanged");
    }

    public void onBackDoorPositionChanged(int i7) {
        Log.v(TAG, "onBackDoorPositionChanged");
    }

    @Deprecated
    public void onBatteryPowerChanged(int i7) {
        Log.v(TAG, "onBatteryPowerChanged");
    }

    public void onBatteryPowerHEVChanged(double d7) {
        Log.v(TAG, "onBatteryPowerHEVChanged");
    }

    public void onBatteryVoltageLevelChanged(int i7) {
        Log.v(TAG, "onBatteryVoltageLevelChanged");
    }

    public void onBatteryVoltageLevelLowTips(int i7) {
        Log.v(TAG, "onBatteryVoltageLevelLowTips");
    }

    public void onCarWindowAntiPinchConfigChanged(int i7) {
        Log.v(TAG, "onCarWindowAntiPinchConfigChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onDoorLockStatusChanged(int i7, int i8) {
        Log.v(TAG, "onDoorLockStatusChanged");
    }

    public void onDoorStateChanged(int i7, int i8) {
        Log.v(TAG, "onDoorStateChanged");
    }

    public void onElecSlideDoorStateChanged(int i7) {
        Log.v(TAG, "onElecSlideDoorStateChanged");
    }

    public void onEnergyTypeChanged(int i7) {
        Log.v(TAG, "onEnergyTypeChanged");
    }

    public void onError(int i7, String str) {
        Log.v(TAG, "onError");
    }

    public void onFuelElecLowPowerChanged(int i7) {
        Log.v(TAG, "onFuelElecLowPowerChanged");
    }

    public void onHasMessageChanged(int i7, int i8) {
        Log.v(TAG, "onHasMessageChanged");
    }

    public void onLeftSlideDoorActionChanged(int i7) {
        Log.v(TAG, "onLeftSlideDoorActionChanged");
    }

    public void onLeftSlideDoorPercentChanged(int i7) {
        Log.v(TAG, "onLeftSlideDoorPercentChanged");
    }

    public void onLeftSlideDoorStateChanged(int i7) {
        Log.v(TAG, "onLeftSlideDoorStateChanged");
    }

    public void onLeftStrutMorotRunningStateChanged(int i7) {
        Log.v(TAG, "onLeftStrutMorotRunningStateChanged");
    }

    public void onLightOff(int i7) {
        Log.v(TAG, "onLightOff");
    }

    public void onLightOn(int i7) {
        Log.v(TAG, "onLightOn");
    }

    public void onMainSaveOrRecoverStateChanged(int i7, int i8) {
        Log.v(TAG, "onMainSaveOrRecoverStateChanged");
    }

    public void onMessage5sOnlineStateChanged(int i7, int i8) {
        Log.v(TAG, "onMessage5sOnlineStateChanged");
    }

    public void onMoonRoofConfigChanged(int i7) {
        Log.v(TAG, "onMoonRoofConfigChanged");
    }

    public void onPowerDayModeChanged(int i7) {
        Log.v(TAG, "onPowerDayModeChanged");
    }

    public void onPowerLevelChanged(int i7) {
        Log.v(TAG, "onPowerLevelChanged");
    }

    public void onRainCloseWindowChanged(int i7) {
        Log.v(TAG, "onRainCloseWindowChanged");
    }

    public void onReadyIndicatorLightStateChanged(int i7) {
        Log.v(TAG, "onReadyIndicatorLightStateChanged");
    }

    public void onRearLampLightStatusChanged(int i7) {
        Log.v(TAG, "onRearLampLightStatusChanged");
    }

    public void onRearWindowblindInitStateChanged(int i7) {
        Log.v(TAG, "onRearWindowblindInitStateChanged");
    }

    public void onRearWindowblindPositionChanged(int i7) {
        Log.v(TAG, "onRearWindowblindPositionChanged");
    }

    public void onRearWindowblindStateChanged(int i7) {
        Log.v(TAG, "onRearWindowblindStateChanged");
    }

    public void onRecoverOrSaveParamsChanged(int i7, int i8, int i9, int i10) {
        Log.v(TAG, "onRecoverOrSaveParamsChanged");
    }

    public void onRemoteWhistleChange(int i7) {
        Log.v(TAG, "onRemoteWhistleChange");
    }

    public void onRemoteWhistleFail(int i7) {
        Log.v(TAG, "onRemoteWhistleFail");
    }

    public void onRightSlideDoorActionChanged(int i7) {
        Log.v(TAG, "onRightSlideDoorActionChanged");
    }

    public void onRightSlideDoorPercentChanged(int i7) {
        Log.v(TAG, "onRightSlideDoorPercentChanged");
    }

    public void onRightSlideDoorStateChanged(int i7) {
        Log.v(TAG, "onRightSlideDoorStateChanged");
    }

    public void onRightStrutMorotRunningStateChanged(int i7) {
        Log.v(TAG, "onRightStrutMorotRunningStateChanged");
    }

    public void onSmartVoiceLimitChanged(int i7) {
        Log.v(TAG, "onSmartVoiceLimitChanged");
    }

    public void onSteeringWheelAngleChanged(double d7, int i7, int i8) {
        Log.v(TAG, "onSteeringWheelAngleChanged");
    }

    public void onSteeringWheelSpeedChanged(double d7, int i7, int i8) {
        Log.v(TAG, "onSteeringWheelSpeedChanged");
    }

    public void onSteeringWheelValueChanged(int i7, double d7) {
        Log.v(TAG, "onSteeringWheelValueChanged");
    }

    public void onSunroofCloseNoticeChanged(int i7) {
        Log.v(TAG, "onSunroofCloseNoticeChanged");
    }

    public void onSunroofInitStateChanged(int i7) {
        Log.v(TAG, "onSunroofInitStateChanged");
    }

    public void onSunroofPositionChanged(int i7) {
        Log.v(TAG, "onSunroofPositionChanged");
    }

    public void onSunroofStateChanged(int i7) {
        Log.v(TAG, "onSunroofStateChanged");
    }

    public void onSunroofWindowblindPositionChanged(int i7) {
        Log.v(TAG, "onSunroofWindowblindPositionChanged");
    }

    public void onTurnRearviewMirrorChanged(int i7, float f7, float f8) {
        Log.v(TAG, "onTurnRearviewMirrorChanged");
    }

    public void onWindoblindInitStateChanged(int i7) {
        Log.v(TAG, "onWindoblindInitStateChanged");
    }

    public void onWindowControlStateChanged(int i7, int i8) {
        Log.v(TAG, "onWindowControlStateChanged");
    }

    public void onWindowInitStateChanged(int i7, int i8) {
        Log.v(TAG, "onWindowInitStateChanged");
    }

    public void onWindowLockStateChanged(int i7, int i8) {
        Log.v(TAG, "onWindowLockStateChanged");
    }

    public void onWindowOpenPercentChanged(int i7, int i8) {
        Log.v(TAG, "onWindowOpenPercentChanged");
    }

    public void onWindowPermitStateChanged(int i7) {
        Log.v(TAG, "onWindowPermitStateChanged");
    }

    public void onWindowStateChanged(int i7, int i8) {
        Log.v(TAG, "onWindowStateChanged");
    }

    public void onWindowThermalProtectionStateChanged(int i7, int i8) {
        Log.v(TAG, "onWindowThermalProtectionStateChanged");
    }

    public void onWindowblindStateChanged(int i7) {
        Log.v(TAG, "onWindowblindStateChanged");
    }

    public void turnSteeringWheelChanged(int i7, float f7, float f8) {
        Log.v(TAG, "turnSteeringWheelChanged");
    }
}

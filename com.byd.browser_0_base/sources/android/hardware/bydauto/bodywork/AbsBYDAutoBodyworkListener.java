package android.hardware.bydauto.bodywork;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoBodyworkListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoBodyworkListener";

    public void onAlarmStateChanged(int i) {
    }

    public void onAutoExternalRearMirrorFoldActionStateChanged(int i) {
    }

    public void onAutoExternalRearMirrorFoldedState(int i) {
    }

    public void onAutoSystemStateChanged(int i) {
    }

    public void onAutoVINChanged(String str) {
    }

    public void onBCMVechileStatusChanged(int i) {
    }

    public void onBackDoorAntiPlayModeChanged(int i) {
    }

    public void onBackDoorCurStateChanged(int i) {
    }

    public void onBackDoorInitStateChanged(int i) {
    }

    public void onBackDoorPositionChanged(int i) {
    }

    @Deprecated
    public void onBatteryPowerChanged(int i) {
    }

    public void onBatteryPowerHEVChanged(double d) {
    }

    public void onBatteryVoltageLevelChanged(int i) {
    }

    public void onBatteryVoltageLevelLowTips(int i) {
    }

    public void onCarWindowAntiPinchConfigChanged(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    public void onElecSlideDoorStateChanged(int i) {
    }

    public void onEnergyTypeChanged(int i) {
    }

    public void onEvReadyIndicatorLightStateChanged(int i) {
    }

    public void onFuelElecLowPowerChanged(int i) {
    }

    public void onLeftSlideDoorActionChanged(int i) {
    }

    public void onLeftSlideDoorPercentChanged(int i) {
    }

    public void onLeftSlideDoorStateChanged(int i) {
    }

    public void onLeftStrutMorotRunningStateChanged(int i) {
    }

    public void onLightOff(int i) {
    }

    public void onLightOn(int i) {
    }

    public void onMoonRoofConfigChanged(int i) {
    }

    public void onPowerDayModeChanged(int i) {
    }

    public void onPowerLevelChanged(int i) {
    }

    public void onRainCloseWindowChanged(int i) {
    }

    public void onReadyIndicatorLightStateChanged(int i) {
    }

    public void onRearLampLightStateChanged(int i) {
    }

    @Deprecated
    public void onRearLampLightStatusChanged(int i) {
    }

    public void onRearWindowblindInitStateChanged(int i) {
    }

    public void onRearWindowblindPositionChanged(int i) {
    }

    public void onRearWindowblindStateChanged(int i) {
    }

    public void onRemoteWhistleChange(int i) {
    }

    public void onRemoteWhistleFail(int i) {
    }

    public void onRightSlideDoorActionChanged(int i) {
    }

    public void onRightSlideDoorPercentChanged(int i) {
    }

    public void onRightSlideDoorStateChanged(int i) {
    }

    public void onRightStrutMorotRunningStateChanged(int i) {
    }

    public void onSeatModeWakeupFeedbackStateChanged(int i) {
    }

    public void onShiftModeChanged(int i) {
    }

    public void onSmartVoiceLimitChanged(int i) {
    }

    public void onSunroofCloseNoticeChanged(int i) {
    }

    public void onSunroofInitStateChanged(int i) {
    }

    public void onSunroofPositionChanged(int i) {
    }

    public void onSunroofStateChanged(int i) {
    }

    public void onSunroofWindowblindPositionChanged(int i) {
    }

    public void onWindoblindInitStateChanged(int i) {
    }

    public void onWindowPermitStateChanged(int i) {
    }

    public void onWindowblindStateChanged(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onDoorHandleStateChanged(int i, int i2) {
    }

    public void onDoorLockStatusChanged(int i, int i2) {
    }

    public void onDoorStateChanged(int i, int i2) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }

    public void onHasMessageChanged(int i, int i2) {
    }

    public void onInternalDoorHandleStateByRearBCMChanged(int i, int i2) {
    }

    public void onInternalDoorHandleStateChanged(int i, int i2) {
    }

    public void onMainSaveOrRecoverStateChanged(int i, int i2) {
    }

    public void onMessage5sOnlineStateChanged(int i, int i2) {
    }

    public void onScreenControlSlideDoorChanged(int i, int i2) {
    }

    public void onSteeringWheelValueChanged(int i, double d) {
    }

    public void onWindowControlStateChanged(int i, int i2) {
    }

    public void onWindowInitStateChanged(int i, int i2) {
    }

    public void onWindowLockStateChanged(int i, int i2) {
    }

    public void onWindowOpenPercentChanged(int i, int i2) {
    }

    public void onWindowStateChanged(int i, int i2) {
    }

    public void onWindowThermalProtectionStateChanged(int i, int i2) {
    }

    public void onSteeringWheelAngleChanged(double d, int i, int i2) {
    }

    public void onSteeringWheelSpeedChanged(double d, int i, int i2) {
    }

    public void onTurnRearviewMirrorChanged(int i, float f, float f2) {
    }

    public void turnSteeringWheelChanged(int i, float f, float f2) {
    }

    public void onRecoverOrSaveParamsChanged(int i, int i2, int i3, int i4) {
    }
}

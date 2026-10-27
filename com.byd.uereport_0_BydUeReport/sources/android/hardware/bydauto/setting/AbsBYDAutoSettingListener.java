package android.hardware.bydauto.setting;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSettingListener implements d {
    public static final String TAG = "AbsBYDAutoSettingListener";

    public AbsBYDAutoSettingListener() {
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

    public void on1LevelSeatHeatingStateChanged(int i8, int i9) {
        Log.v(TAG, "on1LevelSeatHeatingStateChanged");
    }

    public void on5LevelSeatHeatingLevelChanged(int i8, int i9) {
        Log.v(TAG, "on5LevelSeatHeatingLevelChanged");
    }

    public void on5LevelSeatHeatingModeChanged(int i8, int i9) {
        Log.v(TAG, "on5LevelSeatHeatingModeChanged");
    }

    public void on5LevelSeatHeatingStateChanged(int i8, int i9) {
        Log.v(TAG, "on5LevelSeatHeatingStateChanged");
    }

    public void on5LevelSeatVentilatingLevelChanged(int i8, int i9) {
        Log.v(TAG, "on5LevelSeatVentilatingLevelChanged");
    }

    public void on5LevelSeatVentilatingModeChanged(int i8, int i9) {
        Log.v(TAG, "on5LevelSeatVentilatingModeChanged");
    }

    public void on5LevelSeatVentilatingStateChanged(int i8, int i9) {
        Log.v(TAG, "on5LevelSeatVentilatingStateChanged");
    }

    public void onACAutoAirModeChanged(int i8) {
        Log.v(TAG, "onACAutoAirModeChanged");
    }

    public void onACAutoWindLevelChanged(int i8) {
        Log.v(TAG, "onACAutoWindLevelChanged");
    }

    public void onACBTWindSwitchChanged(int i8) {
        Log.v(TAG, "onACBTWindSwitchChanged");
    }

    public void onACPauseCycleSwitchChanged(int i8) {
        Log.v(TAG, "onACPauseCycleSwitchChanged");
    }

    public void onACTunnelCycleSwitchChanged(int i8) {
        Log.v(TAG, "onACTunnelCycleSwitchChanged");
    }

    public void onAcAutoPurificationStateChange(int i8) {
        Log.v(TAG, "onAcAutoPurificationStateChange");
    }

    public void onAcAutoPurificationStateChanged(int i8) {
        Log.v(TAG, "onAcAutoPurificationStateChanged");
    }

    public void onAcKeyStartTimeChange(int i8) {
        Log.v(TAG, "onAcKeyStartTimeChange");
    }

    public void onAcKeyStartTimeChanged(int i8) {
        Log.v(TAG, "onAcKeyStartTimeChanged");
    }

    public void onAcTempUnitChange(int i8) {
        Log.v(TAG, "onAcTempUnitChange");
    }

    public void onAcTempUnitChanged(int i8) {
        Log.v(TAG, "onAcTempUnitChanged");
    }

    public void onAcWarmModeChanged(int i8) {
        Log.v(TAG, "onAcWarmModeChanged");
    }

    public void onAidedDrivingIndChang(int i8) {
        Log.v(TAG, "onAidedDrivingIndChang");
    }

    public void onAirLightPanelStateChanged(int i8) {
        Log.v(TAG, "onAirLightPanelStateChanged");
    }

    public void onAirLightTextNumberChanged(int i8) {
        Log.v(TAG, "onAirLightTextNumberChanged");
    }

    public void onAirLightTextStateChanged(int i8) {
        Log.v(TAG, "onAirLightTextStateChanged");
    }

    public void onAlarmPromptStateChanged(int i8) {
        Log.v(TAG, "onAlarmPromptStateChanged");
    }

    public void onAmbientFeedBackChanged(int i8) {
        Log.v(TAG, "onAmbientFeedBackChanged");
    }

    public void onAmbientMulticolorModeChanged(int i8, int i9) {
        Log.v(TAG, "onAmbientMulticolorModeChanged");
    }

    public void onAmbientMulticolorStateChanged(int i8, int i9) {
        Log.v(TAG, "onAmbientMulticolorStateChanged");
    }

    public void onAmbientMusicModeState(int i8, int i9) {
        Log.v(TAG, "onAmbientMusicModeState");
    }

    public void onAmbientMusicModechanged(int i8, int i9) {
        Log.v(TAG, "onAmbientMusicModechanged");
    }

    public void onAppUseLimitStateChanged(int i8) {
        Log.v(TAG, "onAppUseLimitStateChanged");
    }

    public void onAutoExternalRearMirrorFoldChanged(int i8) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldChanged");
    }

    public void onAutoExternalRearMirrorFollowUpStateChanged(int i8) {
        Log.v(TAG, "onAutoExternalRearMirrorFollowUpStateChanged");
    }

    public void onAutoExternalRearMirrorFollowUpSwitchChanged(int i8) {
        Log.v(TAG, "onAutoExternalRearMirrorFollowUpSwitchChanged");
    }

    public void onAutoLockChanged(int i8) {
        Log.v(TAG, "onAutoLockChanged");
    }

    public void onAutoLockSwitchChanged(int i8) {
        Log.v(TAG, "onAutoLockSwitchChanged");
    }

    public void onAutoLockTimeChanged(int i8) {
        Log.v(TAG, "onAutoLockTimeChanged");
    }

    public void onAutoPowerOffStateChanged(int i8) {
        Log.v(TAG, "onAutoPowerOffStateChanged");
    }

    public void onAutoPowerOnStateChanged(int i8) {
        Log.v(TAG, "onAutoPowerOnStateChanged");
    }

    public void onAutoRainWiperStateChanged(int i8) {
        Log.v(TAG, "onAutoRainWiperStateChanged");
    }

    public void onAutoReportContentChanged(int i8, int i9) {
        Log.v(TAG, "onAutoReportContentChanged");
    }

    public void onAutomaticParkingSwitchChanged(int i8) {
        Log.v(TAG, "onAutomaticParkingSwitchChanged");
    }

    public void onBSISCollisionStateChanged(int i8) {
        Log.v(TAG, "onBSISCollisionStateChanged");
    }

    public void onBSISFuncStatusChanged(int i8) {
        Log.v(TAG, "onBSISFuncStatusChanged");
    }

    public void onBTCallReductionWindMenuStateChanged(int i8) {
        Log.v(TAG, "onBTCallReductionWindMenuStateChanged");
    }

    public void onBackDoorElectricModeChanged(int i8) {
        Log.v(TAG, "onBackDoorElectricModeChanged");
    }

    public void onBackDoorOpenedHeightChanged(int i8) {
        Log.v(TAG, "onBackDoorOpenedHeightChanged");
    }

    public void onBackHomeLightDelaySwitchChanged(int i8) {
        Log.v(TAG, "onBackHomeLightDelaySwitchChanged");
    }

    public void onBackHomeLightDelayValueChanged(int i8) {
        Log.v(TAG, "onBackHomeLightDelayValueChanged");
    }

    public void onBackRowControlSwitchChanged(int i8) {
        Log.v(TAG, "onBackRowControlSwitchChanged");
    }

    public void onBasePadAutoRiseStateChanged(int i8) {
        Log.v(TAG, "onBasePadAutoRiseStateChanged");
    }

    public void onBleOpenDriverDoorStateChanged(int i8) {
        Log.v(TAG, "onBleOpenDriverDoorStateChanged");
    }

    public void onBrakeCloseDoorChanged(int i8) {
        Log.v(TAG, "onBrakeCloseDoorChanged");
    }

    public void onBrakePedalModeChanged(int i8) {
        Log.v(TAG, "onBrakePedalModeChanged");
    }

    public void onCVCModeChanged(int i8) {
        Log.v(TAG, "onCVCModeChanged");
    }

    public void onCampingBlanceActionChanged(int i8) {
        Log.v(TAG, "onCampingBlanceActionChanged");
    }

    public void onCampingBlanceSupportedChanged(int i8) {
        Log.v(TAG, "onCampingBlanceSupportedChanged");
    }

    public void onCampintBlacneStateChanged(int i8) {
        Log.v(TAG, "onCampintBlacneStateChanged");
    }

    public void onCancelRecoverParms(int i8, int i9, int i10) {
        Log.v(TAG, "onCancelRecoverParms");
    }

    public void onCanopyAutomaticDimming(int i8) {
        Log.v(TAG, "onCanopyAutomaticDimming");
    }

    public void onCarRecorderRecordingChanged(int i8) {
        Log.v(TAG, "onCarRecorderRecordingChanged");
    }

    public void onCarTipsSoundTypeChanged(int i8) {
        Log.v(TAG, "onCarTipsSoundTypeChanged");
    }

    public void onCarWarnINDSwitchChanged(int i8) {
        Log.v(TAG, "onCarWarnINDSwitchChanged");
    }

    public void onCenterRearSafebeltStatusChanged(int i8) {
        Log.v(TAG, "onCenterRearSafebeltStatusChanged state is " + i8);
    }

    public void onChargingPortSwitchChanged(int i8) {
        Log.v(TAG, "onChargingPortSwitchChanged");
    }

    public void onCloseDoorLevelChanged(int i8) {
        Log.v(TAG, "onCloseDoorLevelChanged");
    }

    public void onComSpeedDownStateChanged(int i8) {
        Log.v(TAG, "onComSpeedDownStateChanged");
    }

    public void onComSpeedUpStateChanged(int i8) {
        Log.v(TAG, "onComSpeedUpStateChanged");
    }

    public void onComfortableParkingStateChanged(int i8) {
        Log.v(TAG, "onComfortableParkingStateChanged");
    }

    public void onControlCallbackStateChanged(int i8) {
        Log.v(TAG, "onControlCallbackStateChanged");
    }

    public void onControlWindowSwitchChanged(int i8) {
        Log.v(TAG, "onControlWindowSwitchChanged");
    }

    public void onCourtesyLampTimeChanged(int i8) {
        Log.v(TAG, "onCourtesyLampTimeChanged");
    }

    public void onCpdImsSwitchStateChanged(int i8) {
        Log.v(TAG, "onCpdImsSwitchStateChanged");
    }

    public void onCreepDoorStateChanged(int i8) {
        Log.v(TAG, "onCreepDoorStateChanged state is " + i8);
    }

    public void onCreepModeFailStateChanged(int i8) {
        Log.v(TAG, "onCreepModeFailStateChanged state is " + i8);
    }

    public void onCreepModeStateChanged(int i8) {
        Log.v(TAG, "onCreepModeStateChanged state is " + i8);
    }

    public void onCreepModeWorkStateChanged(int i8) {
        Log.v(TAG, "onCreepModeWorkStateChanged state is " + i8);
    }

    public void onCreepSpeedStateChanged(int i8) {
        Log.v(TAG, "onCreepSpeedStateChanged state is " + i8);
    }

    public void onCruiseBulletBoxChanged(int i8) {
        Log.v(TAG, "onCruiseBulletBoxChanged");
    }

    public void onCuraSwitchStateChanged(int i8) {
        Log.v(TAG, "onCuraSwitchStateChanged state = " + i8);
    }

    public void onCurrentModeOfTailStateChanged(int i8) {
        Log.v(TAG, "onCurrentModeOfTailStateChanged");
    }

    public void onCurrentStateOfTheTailStateChanged(int i8) {
        Log.v(TAG, "onCurrentStateOfTheTailStateChanged");
    }

    public void onCurrentStatusOfDimmingSunroof(int i8) {
        Log.v(TAG, "onCurrentStatusOfDimmingSunroof");
    }

    public void onCurrentTransmittanceOfSkylight(int i8) {
        Log.v(TAG, "onCurrentTransmittanceOfSkylight");
    }

    public void onDampStateChanged(int i8) {
        Log.v(TAG, "onDampStateChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onDelayDownPowerTimeChanged(int i8) {
        Log.v(TAG, "onDelayDownPowerTimeChanged");
    }

    public void onDelayPowerDownTimeChanged(int i8) {
        Log.v(TAG, "onDelayPowerDownTimeChanged");
    }

    public void onDelayPowerOffStateChanged(int i8) {
        Log.v(TAG, "onDelayPowerOffStateChanged");
    }

    public void onDelayPowerOffStatusChanged(int i8) {
        Log.v(TAG, "onDelayPowerOffStatusChanged");
    }

    public void onDelayPowerOffTimeChanged(int i8) {
        Log.v(TAG, "onDelayPowerOffTimeChanged");
    }

    public void onDimmingCanopyLowCode(int i8) {
        Log.v(TAG, "onDimmingCanopyLowCode");
    }

    public void onDisinfStateChanged(int i8) {
        Log.v(TAG, "onDisinfStateChanged");
    }

    public void onDlgMessageCodeChanged(int i8) {
        Log.v(TAG, "onDlgMessageCodeChanged");
    }

    public void onDmPlatformChanged(int i8) {
        Log.v(TAG, "onDmPlatformChanged");
    }

    public void onDoorLockChanged(int i8) {
        Log.v(TAG, "onDoorLockChanged");
    }

    public void onDoorknobAutoShrinkStateChanged(int i8) {
        Log.v(TAG, "onDoorknobAutoShrinkStateChanged");
    }

    public void onDragAndDropModeTipsChanged(int i8) {
        Log.v(TAG, "onDragAndDropModeTipsChanged");
    }

    public void onDragModeForwardAndBackwardChanged(int i8) {
        Log.v(TAG, "onDragModeForwardAndBackwardChanged");
    }

    public void onDriveAutoLockWindowStateChanged(int i8) {
        Log.v(TAG, "onDriveAutoLockWindowStateChanged");
    }

    public void onDriveConfigTypeChanged(int i8) {
        Log.v(TAG, "onDriveConfigTypeChanged");
    }

    public void onDriveDoorCloseIndSwitchChanged(int i8) {
        Log.v(TAG, "onDriveDoorCloseIndSwitchChanged");
    }

    public void onDriveModeChanged(int i8) {
        Log.v(TAG, "onDriveModeChanged mode = " + i8);
    }

    public void onDriverSeatAutoReturnB3Changded(int i8) {
        Log.v(TAG, "onDriverSeatAutoReturnB3Changded");
    }

    public void onDriverSeatAutoReturnStateChanged(int i8) {
        Log.v(TAG, "onDriverSeatAutoReturnStateChanged");
    }

    public void onDriverSeatAutoReturnSwitchChanged(int i8) {
        Log.v(TAG, "onDriverSeatAutoReturnSwitchChanged");
    }

    public void onDriverSeatBackChanged(int i8) {
        Log.v(TAG, "onDriverSeatBackChanged");
    }

    public void onDriverSeatHeating437StateChanged(int i8) {
        Log.v(TAG, "onDriverSeatHeating437StateChanged");
    }

    public void onDriverSeatVentilating437StateChanged(int i8) {
        Log.v(TAG, "onDriverSeatVentilating437StateChanged");
    }

    public void onDrivingRecorderChanged(int i8) {
        Log.v(TAG, "onDrivingRecorderChanged");
    }

    public void onDrivingRecorderSwitchChanged(int i8) {
        Log.v(TAG, "onDrivingRecorderSwitchChanged");
    }

    public void onDyHeightLevelChanged(int i8) {
        Log.v(TAG, "onDyHeightLevelChanged");
    }

    public void onDyHeightToggleStateChanged(int i8) {
        Log.v(TAG, "onDyHeightToggleStateChanged");
    }

    public void onDyHeightTurnActionChanged(int i8) {
        Log.v(TAG, "onDyHeightTurnActionChanged");
    }

    public void onDynamicSuspensionModeChanged(int i8) {
        Log.v(TAG, "onDynamicSuspensionModeChanged");
    }

    public void onEPedalModeChanged(int i8) {
        Log.v(TAG, "onEPedalModeChanged");
    }

    public void onElecHandbrakeStateChanged(int i8) {
        Log.v(TAG, "onElecHandbrakeStateChanged");
    }

    public void onElectricPedalStateChanged(int i8, int i9) {
        Log.v(TAG, "onElectricPedalStateChanged");
    }

    public void onElectricTailMaintenanceFuntatusStateChanged(int i8) {
        Log.v(TAG, "onElectricTailMaintenanceFuntatusStateChanged");
    }

    public void onEnergyFeedbackStrengthChanged(int i8) {
        Log.v(TAG, "onEnergyFeedbackStrengthChanged");
    }

    public void onEngineOilExitUpdateStateChanged(int i8) {
        Log.v(TAG, "onEngineOilExitUpdateStateChanged");
    }

    public void onEngineOilLevelChanged(int i8) {
        Log.v(TAG, "onEngineOilLevelChanged");
    }

    public void onEngineOilUpdateSignalChanged(int i8) {
        Log.v(TAG, "onEngineOilUpdateSignalChanged");
    }

    public void onEngineSoundTypeChanged(int i8) {
        Log.v(TAG, "onEngineSoundTypeChanged");
    }

    public void onError(int i8, String str) {
        Log.v(TAG, "onError");
    }

    public void onExhibitionModeChanged(int i8) {
        Log.v(TAG, "onExhibitionModeChanged");
    }

    public void onExhibitionStartEnginePromptChanged(int i8) {
        Log.v(TAG, "onExhibitionStartEnginePromptChanged");
    }

    public void onFdCRCCheckNum(int i8) {
        Log.v(TAG, "onFdCRCCheckNum");
    }

    public void onFdCampingBlanceActionChanged(int i8) {
        Log.v(TAG, "onFdCampingBlanceActionChanged");
    }

    public void onFdCampingBlanceSupportedChanged(int i8) {
        Log.v(TAG, "onFdCampingBlanceSupportedChanged");
    }

    public void onFdCampintBlacneStateChanged(int i8) {
        Log.v(TAG, "onFdCampintBlacneStateChanged");
    }

    public void onFdComSpeedUpStateChanged(int i8) {
        Log.v(TAG, "onFdComSpeedUpStateChanged");
    }

    public void onFdDampStateChanged(int i8) {
        Log.v(TAG, "onFdDampStateChanged");
    }

    public void onFdDyHeightLevelChanged(int i8) {
        Log.v(TAG, "onFdDyHeightLevelChanged");
    }

    public void onFdDyHeightToggleStateChanged(int i8) {
        Log.v(TAG, "onFdDyHeightToggleStateChanged");
    }

    public void onFdDyHeightTurnActionChanged(int i8) {
        Log.v(TAG, "onFdDyHeightTurnActionChanged");
    }

    public void onFdDynamicSuspensionMode(int i8) {
        Log.v(TAG, "onFdDynamicSuspensionMode");
    }

    public void onFdFixedStateChanged(int i8) {
        Log.v(TAG, "onFdFixedStateChanged");
    }

    public void onFdFrontBoxTakeStateChanged(int i8) {
        Log.v(TAG, "onFdFrontBoxTakeStateChanged");
    }

    public void onFdLiftModeStateChanged(int i8) {
        Log.v(TAG, "onFdLiftModeStateChanged");
    }

    public void onFdModeSupportedHturnChanged(int i8) {
        Log.v(TAG, "onFdModeSupportedHturnChanged");
    }

    public void onFdSpeedSupportedTurn(int i8) {
        Log.v(TAG, "onFdSpeedSupportedTurn");
    }

    public void onFdTailBoxTakeStateChanged(int i8) {
        Log.v(TAG, "onFdTailBoxTakeStateChanged");
    }

    public void onFdTailBoxTakeSupportedChanged(int i8) {
        Log.v(TAG, "onFdTailBoxTakeSupportedChanged");
    }

    public void onFdTrailerModeCodeChanged(int i8) {
        Log.v(TAG, "onFdTrailerModeCodeChanged");
    }

    public void onFdWelcomeActiveChanged(int i8) {
        Log.v(TAG, "onFdWelcomeActiveChanged");
    }

    public void onFdWelcomeStateChanged(int i8) {
        Log.v(TAG, "onFdWelcomeStateChanged");
    }

    public void onFdWelcomeSupportedChanged(int i8) {
        Log.v(TAG, "onFdWelcomeSupportedChanged");
    }

    public void onFixedStateChanged(int i8) {
        Log.v(TAG, "onFixedStateChanged");
    }

    public void onFridgeChildLockChanged(int i8) {
        Log.v(TAG, "onFridgeChildLockChanged");
    }

    public void onFridgeDoorStateChanged(int i8) {
        Log.v(TAG, "onFridgeDoorStateChanged");
    }

    public void onFridgeLocationChanged(int i8) {
        Log.v(TAG, "onFridgeLocationChanged");
    }

    public void onFridgeWorkColdTemperatureChanged(int i8) {
        Log.v(TAG, "onFridgeWorkColdTemperatureChanged");
    }

    public void onFridgeWorkHeatTemperatureChanged(int i8) {
        Log.v(TAG, "onFridgeWorkHeatTemperatureChanged");
    }

    public void onFridgeWorkSaveTemperatureChanged(int i8) {
        Log.v(TAG, "onFridgeWorkSaveTemperatureChanged");
    }

    public void onFrontWiperSpeedValueChanged(int i8) {
        Log.v(TAG, "onFrontWiperSpeedValueChanged");
    }

    public void onGoalRoadModeChanged(int i8) {
        Log.v(TAG, "onGoalRoadModeChanged state = " + i8);
    }

    public void onHSpeedAutoCloseStateChanged(int i8) {
        Log.v(TAG, "onHSpeedAutoCloseStateChanged");
    }

    public void onHandleAutoResetTimeChanged(int i8) {
        Log.v(TAG, "onHandleAutoResetTimeChanged");
    }

    public void onHeadlampLevelChanged(int i8) {
        Log.v(TAG, "onHeadlampLevelChanged");
    }

    public void onHeadrestHorizationChanged(int i8, float f8, int i9, int i10) {
        Log.v(TAG, "onHeadrestHorizationChanged");
    }

    public void onHeadrestTurnHeightChanged(int i8, int i9, float f8) {
        Log.v(TAG, "onHeadrestTurnHeightChanged");
    }

    public void onHeadrestTurnHorizationChanged(int i8, int i9, float f8) {
        Log.v(TAG, "onHeadrestTurnHorizationChanged");
    }

    public void onHevChargingPortSwitchChanged(int i8) {
        Log.v(TAG, "onHevChargingPortSwitchChanged");
    }

    public void onHomeLightTimeChanged(int i8) {
        Log.v(TAG, "onHomeLightTimeChanged");
    }

    public void onHsMassageStateChanged(int i8, int i9) {
        Log.v(TAG, "onHsMassageStateChanged");
    }

    public void onHudBrightnessChanged(int i8) {
        Log.v(TAG, "onHudBrightnessChanged");
    }

    public void onHudDisplayContentChanged(int i8, int i9) {
        Log.v(TAG, "onHudDisplayContentChanged");
    }

    public void onHudHeightChanged(int i8) {
        Log.v(TAG, "onHudHeightChanged");
    }

    public void onHudModeChanged(int i8) {
        Log.v(TAG, "onHudModeChanged");
    }

    public void onHudNavigationStateChanged(int i8) {
        Log.v(TAG, "onHudNavigationStateChanged");
    }

    public void onHudParamsEffect(int i8) {
        Log.v(TAG, "onHudParamsEffect");
    }

    public void onHudPatternChanged(int i8) {
        Log.v(TAG, "onHudPatternChanged");
    }

    public void onHudRestartAndEffect(int i8) {
        Log.v(TAG, "onHudRestartAndEffect");
    }

    public void onHudRotationAngleChanged(int i8) {
        Log.v(TAG, "onHudRotationAngleChanged");
    }

    public void onHudSPStateChanged(int i8) {
        Log.v(TAG, "onHudSPStateChanged");
    }

    public void onHudSafeDriveStateChanged(int i8) {
        Log.v(TAG, "onHudSafeDriveStateChanged");
    }

    public void onHudStateChanged(int i8) {
        Log.v(TAG, "onHudStateChanged");
    }

    public void onIALAreaChanged(int i8) {
        Log.v(TAG, "onIALAreaChanged");
    }

    public void onIALBrightnessChanged(int i8, int i9) {
        Log.v(TAG, "onIALBrightnessChanged");
    }

    public void onIALColorChanged(int i8, int i9) {
        Log.v(TAG, "onIALColorChanged");
    }

    public void onIKEYBTLowPowerModeChanged(int i8) {
        Log.v(TAG, "onIKEYBTLowPowerModeChanged");
    }

    public void onILDurationChanged(int i8) {
        Log.v(TAG, "onILDurationChanged");
    }

    public void onINSThemeChanged(int i8) {
        Log.v(TAG, "onINSThemeChanged");
    }

    public void onIVIProfileSettingsChanged(int i8) {
        Log.v(TAG, "onIVIProfileSettingsChanged");
    }

    public void onInsideLightDoorStateChanged(int i8) {
        Log.v(TAG, "onInsideLightDoorStateChanged");
    }

    public void onInsideRearMirrorScreenSwitchChanged(int i8) {
        Log.v(TAG, "onInsideRearMirrorScreenSwitchChanged");
    }

    public void onInstrumentAcKeyTypeChanged(int i8) {
        Log.v(TAG, "onInstrumentAcKeyTypeChanged");
    }

    public void onIntelLockOpenDriverDoorChanged(int i8) {
        Log.v(TAG, "onIntelLockOpenDriverDoorChanged");
    }

    public void onIntelligentAcAreaStateChanged(int i8) {
        Log.v(TAG, "onIntelligentAcAreaStateChanged");
    }

    public void onIntelligentLockStateChanged(int i8) {
        Log.v(TAG, "onIntelligentLockStateChanged");
    }

    public void onIntelligentUnlockStateChanged(int i8) {
        Log.v(TAG, "onIntelligentUnlockStateChanged");
    }

    public void onIntelligentVoiceINDLevelChanged(int i8) {
        Log.v(TAG, "onIntelligentVoiceINDLevelChanged");
    }

    public void onIntelligentVoiceINDSwitchChanged(int i8) {
        Log.v(TAG, "onIntelligentVoiceINDSwitchChanged");
    }

    public void onKeyPowerLowIndSwitchChanged(int i8) {
        Log.v(TAG, "onKeyPowerLowIndSwitchChanged");
    }

    public void onKeyVibratorLevelChanged(int i8) {
        Log.v(TAG, "onKeyVibratorLevelChanged");
    }

    public void onLPSwitchDownwindowChanged(int i8) {
        Log.v(TAG, "onLPSwitchDownwindowChanged");
    }

    public void onLPSwitchUpwindowChanged(int i8) {
        Log.v(TAG, "onLPSwitchUpwindowChanged");
    }

    public void onLRSEChildModeCodeWriteFb(int i8) {
        Log.v(TAG, "onLRSEChildModeCodeWriteFb");
    }

    public void onLRSEChildModeTimeWriteFb(int i8) {
        Log.v(TAG, "onLRSEChildModeTimeWriteFb");
    }

    public void onLRSEMachineSwitchStateChanged(int i8) {
        Log.v(TAG, "onLRSEMachineSwitchStateChanged");
    }

    public void onLandAdvanceAtSateChanged(int i8) {
        Log.v(TAG, "onLandAdvanceAtSateChanged");
    }

    public void onLanguageChanged(int i8) {
        Log.v(TAG, "onLanguageChanged");
    }

    public void onLanternLanguageModeChanged(int i8) {
        Log.v(TAG, "onLanternLanguageModeChanged");
    }

    public void onLearnerDriverAccLimitStateChanged(int i8) {
        Log.v(TAG, "onLearnerDriverAccLimitStateChanged");
    }

    public void onLearnerDriverModeStateChanged(int i8) {
        Log.v(TAG, "onLearnerDriverModeStateChanged");
    }

    public void onLearnerDriverModeSwitchStateChanged(int i8) {
        Log.v(TAG, "onLearnerDriverModeSwitchStateChanged");
    }

    public void onLeaveCarPIndSwitchChanged(int i8) {
        Log.v(TAG, "onLeaveCarPIndSwitchChanged");
    }

    public void onLeaveHomeLightTimeChanged(int i8) {
        Log.v(TAG, "onLeaveHomeLightTimeChanged");
    }

    public void onLeftFieldPlatformChanged(int i8) {
        Log.v(TAG, "onLeftFieldPlatformChanged");
    }

    public void onLeftHeadlampLevelChanged(int i8) {
        Log.v(TAG, "onLeftHeadlampLevelChanged");
    }

    public void onLeftHomeLightDelaySwitchChanged(int i8) {
        Log.v(TAG, "onLeftHomeLightDelaySwitchChanged");
    }

    public void onLeftHomeLightDelayValueChanged(int i8) {
        Log.v(TAG, "onLeftHomeLightDelayValueChanged");
    }

    public void onLeftRearSafebeltStatusChanged(int i8) {
        Log.v(TAG, "onLeftRearSafebeltStatusChanged state is " + i8);
    }

    public void onLeftThirdSafebeltStatusChanged(int i8) {
        Log.v(TAG, "onLeftThirdSafebeltStatusChanged state is " + i8);
    }

    public void onLeftViewMirrorFlipAngleChanged(int i8) {
        Log.v(TAG, "onLeftViewMirrorFlipAngleChanged");
    }

    public void onLiftModeStateChanged(int i8) {
        Log.v(TAG, "onLiftModeStateChanged");
    }

    public void onLockCarRiseWindowChanged(int i8) {
        Log.v(TAG, "onLockCarRiseWindowChanged");
    }

    public void onLockCarRiseWindowStateChange(int i8) {
        Log.v(TAG, "onLockCarRiseWindowStateChange");
    }

    public void onLockOffChanged(int i8) {
        Log.v(TAG, "onLockOffChanged");
    }

    public void onLockOffDoorChanged(int i8) {
        Log.v(TAG, "onLockOffDoorChanged");
    }

    public void onLockUpwindowChanged(int i8) {
        Log.v(TAG, "onLockUpwindowChanged");
    }

    public void onLongPressUnlockWindowSwitchChanged(int i8) {
        Log.v(TAG, "onLongPressUnlockWindowSwitchChanged");
    }

    public void onLowBatteryIndSwitchChanged(int i8) {
        Log.v(TAG, "onLowBatteryIndSwitchChanged");
    }

    public void onLowOilIndSwitchChanged(int i8) {
        Log.v(TAG, "onLowOilIndSwitchChanged");
    }

    public void onMOISAssembleStateChanged(int i8) {
        Log.v(TAG, "onMOISAssembleStateChanged");
    }

    public void onMOISFuncStatusChanged(int i8) {
        Log.v(TAG, "onMOISFuncStatusChanged");
    }

    public void onMainSeatBackInitChanged(int i8) {
        Log.v(TAG, "onMainSeatBackInitChanged state is " + i8);
    }

    public void onMainSeatHorizontalInitChanged(int i8) {
        Log.v(TAG, "onMainSeatHorizontalInitChanged state is " + i8);
    }

    public void onMaintainRemindStateChanged(int i8) {
        Log.v(TAG, "onMaintainRemindStateChanged");
    }

    public void onMassageLevelStateChanged(int i8, int i9) {
        Log.v(TAG, "onMassageLevelStateChanged");
    }

    public void onMassageModeStateChanged(int i8, int i9) {
        Log.v(TAG, "onMassageModeStateChanged");
    }

    public void onMcuRestartFlagChanged(int i8) {
        Log.v(TAG, "onMcuRestartFlagChanged");
    }

    public void onMemorySetOperationFlagChanged(int i8) {
        Log.v(TAG, "onMemorySetOperationFlagChanged flag = " + i8);
    }

    public void onMicroSwitchLockWindowStateChanged(int i8) {
        Log.v(TAG, "onMicroSwitchLockWindowStateChanged");
    }

    public void onMicroSwitchUnlockWindowStateChanged(int i8) {
        Log.v(TAG, "onMicroSwitchUnlockWindowStateChanged");
    }

    public void onMissKeyIndSwitchChanged(int i8) {
        Log.v(TAG, "onMissKeyIndSwitchChanged");
    }

    public void onMissPackageCountChanged(int i8) {
        Log.v(TAG, "onMissPackageCountChanged");
    }

    public void onModeSupportedHturnChanged(int i8) {
        Log.v(TAG, "onModeSupportedHturnChanged（int");
    }

    public void onMotorPretensionerSeatbeltStateChanged(int i8) {
        Log.v(TAG, "onMotorPretensionerSeatbeltStateChanged");
    }

    public void onMultimediaMotorTemperatureStateChanged(int i8) {
        Log.v(TAG, "onMultimediaMotorTemperatureStateChanged");
    }

    public void onNavigationMediaStateChanged(int i8) {
        Log.v(TAG, "onNavigationMediaStateChanged");
    }

    public void onNfcOpenDriverDoorChanged(int i8) {
        Log.v(TAG, "onNfcOpenDriverDoorChanged");
    }

    public void onNightDRLightOnIndSwitchChanged(int i8) {
        Log.v(TAG, "onNightDRLightOnIndSwitchChanged");
    }

    public void onNightDriveLightOnReminderChanged(int i8) {
        Log.v(TAG, "onNightDriveLightOnReminderChanged");
    }

    public void onNightSystemModeSwitchChanged(int i8) {
        Log.v(TAG, "onNightSystemModeSwitchChanged");
    }

    public void onOffReminderChanged(int i8) {
        Log.v(TAG, "onOffReminderChanged");
    }

    public void onOverSpeedLockStateChanged(int i8) {
        Log.v(TAG, "onOverSpeedLockStateChanged");
    }

    public void onOverspeedLockStateChanged(int i8) {
        Log.v(TAG, "onOverspeedLockStateChanged");
    }

    public void onPABStateChanged(int i8) {
        Log.v(TAG, "onPABStateChanged");
    }

    public void onPABStateCodeChanged(int i8) {
        Log.v(TAG, "onPABStateCodeChanged");
    }

    public void onPM25PowerSwitchChanged(int i8) {
        Log.v(TAG, "onPM25PowerSwitchChanged");
    }

    public void onPM25SwitchChanged(int i8) {
        Log.v(TAG, "onPM25SwitchChanged");
    }

    public void onPM25SwitchCheckChanged(int i8) {
        Log.v(TAG, "onPM25SwitchCheckChanged");
    }

    public void onPM25TimeCheckChanged(int i8) {
        Log.v(TAG, "onPM25TimeCheckChanged");
    }

    public void onParkBrakeIndSwitchChanged(int i8) {
        Log.v(TAG, "onParkBrakeIndSwitchChanged");
    }

    public void onPassangerSeatBackInitChanged(int i8) {
        Log.v(TAG, "onPassangerSeatBackInitChanged state is " + i8);
    }

    public void onPassangerSeatHorizontalInitChanged(int i8) {
        Log.v(TAG, "onPassangerSeatHorizontalInitChanged state is " + i8);
    }

    public void onPassengerSeatHeating3CAStateChanged(int i8) {
        Log.v(TAG, "onPassengerSeatHeating3CAStateChanged");
    }

    public void onPassengerSeatVentilating3CAStateChanged(int i8) {
        Log.v(TAG, "onPassengerSeatVentilating3CAStateChanged");
    }

    public void onPassengerStateChanged(int i8) {
        Log.v(TAG, "onPassengerStateChanged location is " + i8);
    }

    public void onPedalSuppressionSystemStateChanged(int i8) {
        Log.v(TAG, "onPedalSuppressionSystemStateChanged state = " + i8);
    }

    public void onPedestrianRecognizeSwitchChanged(int i8) {
        Log.v(TAG, "onPedestrianRecognizeSwitchChanged");
    }

    public void onPhoneRemindCodeChanged(int i8) {
        Log.v(TAG, "onPhoneRemindCodeChanged");
    }

    public void onRRSEChildModeCodeWriteFb(int i8) {
        Log.v(TAG, "onRRSEChildModeCodeWriteFb");
    }

    public void onRRSEChildModeTimeWriteFb(int i8) {
        Log.v(TAG, "onRRSEChildModeTimeWriteFb");
    }

    public void onRRSEMachineSwitchStateChanged(int i8) {
        Log.v(TAG, "onRRSEMachineSwitchStateChanged");
    }

    public void onRainAutoLockWindowStateChanged(int i8) {
        Log.v(TAG, "onRainAutoLockWindowStateChanged");
    }

    public void onRearAcOnlineStateChanged(int i8) {
        Log.v(TAG, "onRearAcOnlineStateChanged");
    }

    public void onRearAcPanelAutoLockTimeChanged(int i8) {
        Log.v(TAG, "onRearAcPanelAutoLockTimeChanged");
    }

    public void onRearDoorStateChanged(int i8) {
        Log.v(TAG, "onRearDoorStateChanged");
    }

    public void onRearHeatTempChanged(int i8) {
        Log.v(TAG, "onRearHeatTempChanged");
    }

    public void onRearMirrorFlipChanged(int i8) {
        Log.v(TAG, "onRearMirrorFlipChanged");
    }

    public void onRearPanelLockTimeModeChange(int i8) {
        Log.v(TAG, "onRearPanelLockTimeModeChange");
    }

    public void onRearPanelLockTimeModeChanged(int i8) {
        Log.v(TAG, "onRearPanelLockTimeModeChanged");
    }

    public void onRearRightSeatGreetStateChanged(int i8) {
        Log.v(TAG, "onRearRightSeatGreetStateChanged state = " + i8);
    }

    public void onRearSaveTempChanged(int i8) {
        Log.v(TAG, "onRearSaveTempChanged");
    }

    public void onRearSeatGreetStateChanged(int i8) {
        Log.v(TAG, "onRearSeatGreetStateChanged");
    }

    public void onRearViewEcuMirrorFlipStateChanged(int i8) {
        Log.v(TAG, "onRearViewEcuMirrorFlipStateChanged");
    }

    public void onRearViewMirrorAngleChanged(int i8) {
        Log.v(TAG, "onRearViewMirrorAngleChanged");
    }

    public void onRearViewMirrorAutoFoldModeChanged(int i8) {
        Log.v(TAG, "onRearViewMirrorAutoFoldModeChanged");
    }

    public void onRearViewMirrorFlipChanged(int i8) {
        Log.v(TAG, "onRearViewMirrorFlipChanged");
    }

    public void onRearViewMirrorFlipSetChanged(int i8) {
        Log.v(TAG, "onRearViewMirrorFlipSetChanged");
    }

    public void onRearViewMirrorFlipSwitchChanged(int i8) {
        Log.v(TAG, "onRearViewMirrorFlipSwitchChanged");
    }

    public void onRearWiperSpeedValueChanged(int i8) {
        Log.v(TAG, "onRearWiperSpeedValueChanged");
    }

    public void onRearviewMirrorIsAutoLightStateChanged(int i8) {
        Log.v(TAG, "onRearviewMirrorIsAutoLightStateChanged");
    }

    public void onRearviewMirrorIsStateChanged(int i8) {
        Log.v(TAG, "onRearviewMirrorIsStateChanged");
    }

    public void onRearviewMirrorIsTurnedStateChanged(int i8) {
        Log.v(TAG, "onRearviewMirrorIsTurnedStateChanged");
    }

    public void onRecoverCaliperSplintHeight(int i8, int i9, int i10) {
        Log.v(TAG, "onRecoverCaliperSplintHeight");
    }

    public void onRecoverCushionHeight(int i8, int i9, int i10) {
        Log.v(TAG, "onRecoverCushionHeight");
    }

    public void onRecoverOrSaveParamsChanged(int i8, int i9, int i10, int i11) {
        Log.v(TAG, "onRecoverOrSaveParamsChanged position:" + i8 + " location:" + i9 + " state:" + i10 + " code:" + i11);
    }

    public void onRecoverRearviewMirrorParams(int i8, int i9, int i10) {
        Log.v(TAG, "onRecoverRearviewMirrorParams");
    }

    public void onRecoverSeatParams(int i8, int i9, int i10) {
        Log.v(TAG, "onRecoverSeatParams");
    }

    public void onRecoverSteeringWheelPosition(int i8, int i9) {
        Log.v(TAG, "onRecoverSteeringWheelPosition");
    }

    public void onRefrigWorkModeChanged(int i8) {
        Log.v(TAG, "onRefrigWorkModeChanged mode = " + i8);
    }

    public void onRefrigeratorMobileTerminalPushReChanged(int i8) {
        Log.v(TAG, "onRefrigeratorMobileTerminalPushReChanged code = " + i8);
    }

    public void onRefrigeratorModeChanged(int i8) {
        Log.v(TAG, "onRefrigeratorModeChanged");
    }

    public void onRefrigeratorState(int i8) {
        Log.v(TAG, "onRefrigeratorState");
    }

    public void onRefrigeratorTemperatureChanged(int i8) {
        Log.v(TAG, "onRefrigeratorTemperatureChanged");
    }

    public void onRefrigeratorWorkModeChanged(int i8) {
        Log.v(TAG, "onRefrigeratorWorkModeChanged");
    }

    public void onRefrigeratorWorkStateChanged(int i8) {
        Log.v(TAG, "onRefrigeratorWorkStateChanged");
    }

    public void onRefrigeratorWorkTemperatureChanged(int i8) {
        Log.v(TAG, "onRefrigeratorWorkTemperatureChanged");
    }

    public void onRemberPowerOffStateChanged(int i8) {
        Log.v(TAG, "onRemberPowerOffStateChanged");
    }

    public void onRemenberPowerOffStateChanged(int i8) {
        Log.v(TAG, "onRemenberPowerOffStateChanged");
    }

    public void onRemoteControlDownwindowChanged(int i8) {
        Log.v(TAG, "onRemoteControlDownwindowChanged");
    }

    public void onRemoteControlSwitchChanged(int i8) {
        Log.v(TAG, "onRemoteControlSwitchChanged");
    }

    public void onRemoteControlUnlockStateChanged(int i8) {
        Log.v(TAG, "onRemoteControlUnlockStateChanged");
    }

    public void onRemoteControlUpwindowChanged(int i8) {
        Log.v(TAG, "onRemoteControlUpwindowChanged");
    }

    public void onRemoteControlUpwindowStateChanged(int i8) {
        Log.v(TAG, "onRemoteControlUpwindowStateChanged");
    }

    public void onRemoteCtlUnlockingStateChanged(int i8) {
        Log.v(TAG, "onRemoteCtlUnlockingStateChanged");
    }

    public void onRemoteLockWindowStateChanged(int i8) {
        Log.v(TAG, "onRemoteLockWindowStateChanged");
    }

    public void onRemoteUnlockWindowStateChanged(int i8) {
        Log.v(TAG, "onRemoteUnlockWindowStateChanged");
    }

    public void onReportVolumeChanged(int i8) {
        Log.v(TAG, "onReportVolumeChanged");
    }

    public void onResetAutoExternalRearMirrorFollowUp(int i8) {
        Log.v(TAG, "onResetAutoExternalRearMirrorFollowUp");
    }

    public void onResetAutoPowerOnState(int i8) {
        Log.v(TAG, "onResetAutoPowerOnState");
    }

    public void onResetHudBrightness(int i8) {
        Log.v(TAG, "onResetHudBrightness");
    }

    public void onResetHudDisplayContent(int i8) {
        Log.v(TAG, "onResetHudDisplayContent");
    }

    public void onResetHudHeight(int i8) {
        Log.v(TAG, "onResetHudHeight");
    }

    public void onResetHudMode(int i8) {
        Log.v(TAG, "onResetHudMode");
    }

    public void onResetHudRotationAngle(int i8) {
        Log.v(TAG, "onResetHudRotationAngle");
    }

    public void onResetMotorPretensionerSeatbelt(int i8) {
        Log.v(TAG, "onResetMotorPretensionerSeatbelt");
    }

    public void onRightHeadlampLevelChanged(int i8) {
        Log.v(TAG, "onRightHeadlampLevelChanged");
    }

    public void onRightRearSafebeltStatusChanged(int i8) {
        Log.v(TAG, "onRightRearSafebeltStatusChanged state is " + i8);
    }

    public void onRightThirdSafebeltStatusChanged(int i8) {
        Log.v(TAG, "onRightThirdSafebeltStatusChanged state is " + i8);
    }

    public void onRightViewMirrorFlipAngleChanged(int i8) {
        Log.v(TAG, "onRightViewMirrorFlipAngleChanged");
    }

    public void onRncStateChanged(int i8) {
        Log.v(TAG, "onRncStateChanged");
    }

    public void onRuningCloseDoorStateChanged(int i8) {
        Log.v(TAG, "onRuningCloseDoorStateChanged");
    }

    public void onSOCConfigChanged(int i8) {
        Log.v(TAG, "onSOCConfigChanged");
    }

    public void onSOCTargetLowLimitChanged(int i8) {
        Log.v(TAG, "onSOCTargetLowLimitChanged");
    }

    public void onSOCTargetRangeChange(int i8) {
        Log.v(TAG, "onSOCTargetRangeChange");
    }

    public void onSOCTargetRangeChanged(int i8) {
        Log.v(TAG, "onSOCTargetRangeChanged");
    }

    public void onSafeBeltCloseDriveDoorStateChanged(int i8) {
        Log.v(TAG, "onSafeBeltCloseDriveDoorStateChanged");
    }

    public void onSafeBeltCreepStateChanged(int i8) {
        Log.v(TAG, "onSafeBeltCreepStateChanged state is " + i8);
    }

    public void onSafeBeltIndSwitchChanged(int i8) {
        Log.v(TAG, "onSafeBeltIndSwitchChanged");
    }

    public void onSafeBeltWarnStateChanged(int i8) {
        Log.v(TAG, "onSafeBeltWarnStateChanged  state=" + i8);
    }

    public void onSafeWarnStateChanged(int i8) {
        Log.v(TAG, "onSafeWarnStateChanged");
    }

    public void onSandAbsModeStateChanged(int i8) {
        Log.v(TAG, "onSandAbsModeStateChanged state = " + i8);
    }

    public void onSandAbsTipMessageChanged(int i8) {
        Log.v(TAG, "onSandAbsTipMessageChanged state = " + i8);
    }

    public void onSaveOwnerStateChanged(int i8) {
        Log.v(TAG, "onSaveOwnerStateChanged sate = " + i8);
    }

    public void onSaveSeatParams(int i8, int i9, float f8, float f9, float f10, int i10) {
        Log.v(TAG, "onSaveSeatParams");
    }

    @Deprecated
    public void onSaveSeatParamsAllChanged(int i8, int i9, int i10) {
        Log.v(TAG, "onSaveSeatParamsAllChanged");
    }

    public void onScreenAngleChanged(int i8) {
        Log.v(TAG, "onScreenAngleChanged");
    }

    public void onScreenAngleValueChanged(int i8) {
        Log.v(TAG, "onScreenAngleValueChanged");
    }

    public void onScreenAntiPinchStateChanged(int i8) {
        Log.v(TAG, "onScreenAntiPinchStateChanged");
    }

    public void onScreenCallbackMessageChanged(int i8) {
        Log.v(TAG, "onScreenCallbackMessageChanged");
    }

    public void onScreenRotationActionChanged(int i8) {
        Log.v(TAG, "onScreenRotationActionChanged");
    }

    public void onScreenRotationOverHotStateChanged(int i8) {
        Log.v(TAG, "onScreenRotationOverHotStateChanged");
    }

    public void onScreenRotationStatus(int i8) {
        Log.v(TAG, "onScreenRotationStatus state = " + i8);
    }

    public void onScreenVoltageStateChanged(int i8) {
        Log.v(TAG, "onScreenVoltageStateChanged");
    }

    public void onSeaModeChargingSocTypeChanged(int i8) {
        Log.v(TAG, "onSeaModeChargingSocTypeChanged value = " + i8);
    }

    public void onSeatHeatingNotL1PStateChanged(int i8) {
        Log.v(TAG, "onSeatHeatingNotL1PStateChanged");
    }

    public void onSeatHeatingState2Changed(int i8, int i9) {
        Log.v(TAG, "onSeatHeatingState2Changed");
    }

    public void onSeatHeatingStateChanged(int i8, int i9) {
        Log.v(TAG, "onSeatHeatingStateChanged");
    }

    public void onSeatHeatingStateChanged1(int i8, int i9) {
        Log.v(TAG, "onSeatHeatingStateChanged1");
    }

    public void onSeatHorizationChanged(int i8, float f8, int i9, int i10) {
        Log.v(TAG, "onSeatHorizationChanged");
    }

    public void onSeatTurnHeight(int i8, float f8, int i9, int i10) {
        Log.v(TAG, "onSeatTurnHeight");
    }

    public void onSeatVentilatingNotL1PStateChanged(int i8) {
        Log.v(TAG, "onSeatVentilatingNotL1PStateChanged");
    }

    public void onSeatVentilatingState2Changed(int i8, int i9) {
        Log.v(TAG, "onSeatVentilatingState2Changed");
    }

    public void onSeatVentilatingStateChanged(int i8, int i9) {
        Log.v(TAG, "onSeatVentilatingStateChanged");
    }

    public void onSeatbackrestPostion(int i8, float f8, int i9, int i10) {
        Log.v(TAG, "onSeatbackrestPostion");
    }

    public void onSecondLeftSeatBackInitChanged(int i8) {
        Log.v(TAG, "onSecondLeftSeatBackInitChanged state is " + i8);
    }

    public void onSecondLeftSeatHorizontalInitChanged(int i8) {
        Log.v(TAG, "onSecondLeftSeatHorizontalInitChanged state is " + i8);
    }

    public void onSecondRightSeatBackInitChanged(int i8) {
        Log.v(TAG, "onSecondRightSeatBackInitChanged state is " + i8);
    }

    public void onSecondRightSeatHorizontalInitChanged(int i8) {
        Log.v(TAG, "onSecondRightSeatHorizontalInitChanged state is " + i8);
    }

    public void onServiceControlCallbackChanged(int i8) {
        Log.v(TAG, "onServiceControlCallbackChanged");
    }

    public void onSetHudParamsEffectChanged(int i8) {
        Log.v(TAG, "onSetHudParamsEffectChanged");
    }

    public void onSkyCurtainPowerOffAndDim(int i8) {
        Log.v(TAG, "onSkyCurtainPowerOffAndDim");
    }

    public void onSmartWelcomeLightStateChanged(int i8) {
        Log.v(TAG, "onSmartWelcomeLightStateChanged");
    }

    public void onSomethingLeftInRefrigeratorNotice(int i8) {
        Log.v(TAG, "onSomethingLeftInRefrigeratorNotice");
    }

    public void onSpeedReminderValueChanged(int i8) {
        Log.v(TAG, "onSpeedReminderValueChanged");
    }

    public void onSpeedSupportedTurn(int i8) {
        Log.v(TAG, "onSpeedSupportedTurn");
    }

    public void onStartKeyStateChanged(int i8) {
        Log.v(TAG, "onStartKeyStateChanged");
    }

    public void onStartOrPowerIndSwitchChanged(int i8) {
        Log.v(TAG, "onStartOrPowerIndSwitchChanged");
    }

    public void onSteerAssisModeChanged(int i8) {
        Log.v(TAG, "onSteerAssisModeChanged");
    }

    public void onSteerAssisSupportChanged(int i8) {
        Log.v(TAG, "onSteerAssisSupportChanged  state=" + i8);
    }

    public void onSteerAssistPermissionChanged(int i8) {
        Log.v(TAG, "onSteerAssistPermissionChanged");
    }

    public void onSteerBackChanged(int i8) {
        Log.v(TAG, "onSteerBackChanged");
    }

    public void onSteerPositionAutoReturnStateChanged(int i8) {
        Log.v(TAG, "onSteerPositionAutoReturnStateChanged");
    }

    public void onSteerPositionAutoReturnSwitchChanged(int i8) {
        Log.v(TAG, "onSteerPositionAutoReturnSwitchChanged");
    }

    public void onSteeringWheelHeatingAutoStateChanged(int i8) {
        Log.v(TAG, "onSteeringWheelHeatingAutoStateChanged");
    }

    public void onSteeringWheelHeatingGearChanged(int i8) {
        Log.v(TAG, "onSteeringWheelHeatingGearChanged");
    }

    public void onSteeringWheelHeatingStateChanged(int i8) {
        Log.v(TAG, "onSteeringWheelHeatingStateChanged");
    }

    public void onSteeringWheelYChanged(int i8, int i9) {
        Log.v(TAG, "onSteeringWheelYChanged");
    }

    public void onSteeringWheelZChanged(int i8, int i9) {
        Log.v(TAG, "onSteeringWheelZChanged");
    }

    public void onSterilizationStateChanged(int i8) {
        Log.v(TAG, "onSterilizationStateChanged");
    }

    public void onSterilizationWorkStateChanged(int i8) {
        Log.v(TAG, "onSterilizationWorkStateChanged");
    }

    public void onStopRemoteCtrlDriveIndChanged(int i8) {
        Log.v(TAG, "onStopRemoteCtrlDriveIndChanged");
    }

    public void onSwControlStateChanged(int i8) {
        Log.v(TAG, "onSwControlStateChanged state = " + i8);
    }

    public void onSwsMediumRelatedKeys(int i8) {
        Log.v(TAG, "onSwsMediumRelatedKeys");
    }

    public void onTailBoxTakeStateChanged(int i8) {
        Log.v(TAG, "onTailBoxTakeStateChanged");
    }

    public void onTailBoxTakeSupportedChanged(int i8) {
        Log.v(TAG, "onTailBoxTakeSupportedChanged");
    }

    public void onTailInitializationStateChanged(int i8) {
        Log.v(TAG, "onTailInitializationStateChanged");
    }

    public void onTailMotorThermalProtectionStateChanged(int i8) {
        Log.v(TAG, "onTailMotorThermalProtectionStateChanged");
    }

    public void onTailPositionFeedbackChanged(int i8) {
        Log.v(TAG, "onTailPositionFeedbackChanged");
    }

    public void onTailPositionPercentageChanged(int i8) {
        Log.v(TAG, "onTailPositionPercentageChanged");
    }

    public void onThirdRowSeatWorkModeChanged(int i8) {
        Log.v(TAG, "onThirdRowSeatWorkModeChanged state is " + i8);
    }

    public void onTrailerLightCheckStateChanged(int i8) {
        Log.v(TAG, "onTrailerLightCheckStateChanged");
    }

    public void onTrailerModeCodeChanged(int i8) {
        Log.v(TAG, "onTrailerModeCodeChanged");
    }

    public void onTrailerModeMileageChanged(int i8) {
        Log.v(TAG, "onTrailerModeMileageChanged");
    }

    public void onTrailerModeStateChanged(int i8) {
        Log.v(TAG, "onTrailerModeStateChanged");
    }

    public void onTrayActionStateChanged(int i8) {
        Log.v(TAG, "onTrayActionStateChanged");
    }

    public void onTrayStateChanged(int i8, int i9) {
        Log.v(TAG, "onTrayStateChanged");
    }

    public void onTurnCaliperSplintHeightChanged(int i8, int i9, float f8, int i10) {
        Log.v(TAG, "onTurnCaliperSplintHeightChanged");
    }

    public void onTurnCushionHeightChanged(int i8, int i9, float f8, int i10) {
        Log.v(TAG, "onTurnCushionHeightChanged");
    }

    public void onTurnRearviewMirrorChanged(int i8, float f8, float f9) {
        Log.v(TAG, "onTurnRearviewMirrorChanged");
    }

    public void onTurnSeatCaliperSplintChanged(int i8, int i9, float f8) {
        Log.v(TAG, "onTurnSeatCaliperSplintChanged");
    }

    public void onTurnSeatCushionChanged(int i8, int i9, float f8) {
        Log.v(TAG, "onTurnSeatCushionChanged");
    }

    public void onTurnSeatHeightChanged(int i8, int i9, float f8) {
        Log.v(TAG, "onTurnSeatHeightChanged");
    }

    public void onTurnSeatHorizationChanged(int i8, int i9, float f8) {
        Log.v(TAG, "onTurnSeatHorizationChanged");
    }

    public void onTurnSeatbackrestChanged(int i8, int i9, float f8) {
        Log.v(TAG, "onTurnSeatbackrestChanged");
    }

    public void onTurnSteeringWheelChanged(int i8, float f8, float f9) {
        Log.v(TAG, "onTurnSteeringWheelChanged");
    }

    public void onUnlockDownwindowChanged(int i8) {
        Log.v(TAG, "onUnlockDownwindowChanged");
    }

    public void onVSPStateChanged(int i8) {
        Log.v(TAG, "onVSPStateChanged");
    }

    public void onVehicleStateChanged(int i8) {
        Log.v(TAG, "onVehicleStateChanged");
    }

    public void onVoiceTipsMessageChanged(int i8) {
        Log.v(TAG, "onVoiceTipsMessageChanged");
    }

    public void onWadingPatternSocTipsChanaged(int i8) {
        Log.v(TAG, "onWadingPatternSocTipsChanaged state = " + i8);
    }

    public void onWadingPatternSpeedTipsChanged(int i8) {
        Log.v(TAG, "onWadingPatternSpeedTipsChanged state = " + i8);
    }

    public void onWadingPatternStateTipsChanged(int i8) {
        Log.v(TAG, "onWadingPatternStateTipsChanged state = " + i8);
    }

    public void onWadingPatternTestTipsChanged(int i8) {
        Log.v(TAG, "onWadingPatternTestTipsChanged state = " + i8);
    }

    public void onWelcomeActiveChanged(int i8) {
        Log.v(TAG, "onWelcomeActiveChanged");
    }

    public void onWelcomeStateChanged(int i8) {
        Log.v(TAG, "onWelcomeStateChanged");
    }

    public void onWelcomeSupportedChanged(int i8) {
        Log.v(TAG, "onWelcomeSupportedChanged");
    }

    public void onWindscreenWiperOverhaulStateChanged(int i8, int i9) {
        Log.v(TAG, "onWindscreenWiperOverhaulStateChanged");
    }

    public void onWorkStateChanged(int i8) {
        Log.v(TAG, "onWorkStateChanged");
    }

    public void setKeypadToneStatechanged(int i8) {
        Log.v(TAG, "setKeypadToneStatechanged");
    }
}

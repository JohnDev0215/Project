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
    public /* bridge */ /* synthetic */ void isConnected(boolean z3) {
        super.isConnected(z3);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z3) {
        super.isOptimizationApi(z3);
    }

    public void on1LevelSeatHeatingStateChanged(int i3, int i4) {
        Log.v(TAG, "on1LevelSeatHeatingStateChanged");
    }

    public void on5LevelSeatHeatingLevelChanged(int i3, int i4) {
        Log.v(TAG, "on5LevelSeatHeatingLevelChanged");
    }

    public void on5LevelSeatHeatingModeChanged(int i3, int i4) {
        Log.v(TAG, "on5LevelSeatHeatingModeChanged");
    }

    public void on5LevelSeatHeatingStateChanged(int i3, int i4) {
        Log.v(TAG, "on5LevelSeatHeatingStateChanged");
    }

    public void on5LevelSeatVentilatingLevelChanged(int i3, int i4) {
        Log.v(TAG, "on5LevelSeatVentilatingLevelChanged");
    }

    public void on5LevelSeatVentilatingModeChanged(int i3, int i4) {
        Log.v(TAG, "on5LevelSeatVentilatingModeChanged");
    }

    public void on5LevelSeatVentilatingStateChanged(int i3, int i4) {
        Log.v(TAG, "on5LevelSeatVentilatingStateChanged");
    }

    public void onACAutoAirModeChanged(int i3) {
        Log.v(TAG, "onACAutoAirModeChanged");
    }

    public void onACAutoWindLevelChanged(int i3) {
        Log.v(TAG, "onACAutoWindLevelChanged");
    }

    public void onACBTWindSwitchChanged(int i3) {
        Log.v(TAG, "onACBTWindSwitchChanged");
    }

    public void onACPauseCycleSwitchChanged(int i3) {
        Log.v(TAG, "onACPauseCycleSwitchChanged");
    }

    public void onACTunnelCycleSwitchChanged(int i3) {
        Log.v(TAG, "onACTunnelCycleSwitchChanged");
    }

    public void onAcAutoPurificationStateChange(int i3) {
        Log.v(TAG, "onAcAutoPurificationStateChange");
    }

    public void onAcAutoPurificationStateChanged(int i3) {
        Log.v(TAG, "onAcAutoPurificationStateChanged");
    }

    public void onAcKeyStartTimeChange(int i3) {
        Log.v(TAG, "onAcKeyStartTimeChange");
    }

    public void onAcKeyStartTimeChanged(int i3) {
        Log.v(TAG, "onAcKeyStartTimeChanged");
    }

    public void onAcTempUnitChange(int i3) {
        Log.v(TAG, "onAcTempUnitChange");
    }

    public void onAcTempUnitChanged(int i3) {
        Log.v(TAG, "onAcTempUnitChanged");
    }

    public void onAcWarmModeChanged(int i3) {
        Log.v(TAG, "onAcWarmModeChanged");
    }

    public void onAidedDrivingIndChang(int i3) {
        Log.v(TAG, "onAidedDrivingIndChang");
    }

    public void onAirLightPanelStateChanged(int i3) {
        Log.v(TAG, "onAirLightPanelStateChanged");
    }

    public void onAirLightTextNumberChanged(int i3) {
        Log.v(TAG, "onAirLightTextNumberChanged");
    }

    public void onAirLightTextStateChanged(int i3) {
        Log.v(TAG, "onAirLightTextStateChanged");
    }

    public void onAlarmPromptStateChanged(int i3) {
        Log.v(TAG, "onAlarmPromptStateChanged");
    }

    public void onAmbientFeedBackChanged(int i3) {
        Log.v(TAG, "onAmbientFeedBackChanged");
    }

    public void onAmbientMulticolorModeChanged(int i3, int i4) {
        Log.v(TAG, "onAmbientMulticolorModeChanged");
    }

    public void onAmbientMulticolorStateChanged(int i3, int i4) {
        Log.v(TAG, "onAmbientMulticolorStateChanged");
    }

    public void onAmbientMusicModeState(int i3, int i4) {
        Log.v(TAG, "onAmbientMusicModeState");
    }

    public void onAmbientMusicModechanged(int i3, int i4) {
        Log.v(TAG, "onAmbientMusicModechanged");
    }

    public void onAppUseLimitStateChanged(int i3) {
        Log.v(TAG, "onAppUseLimitStateChanged");
    }

    public void onAutoExternalRearMirrorFoldChanged(int i3) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldChanged");
    }

    public void onAutoExternalRearMirrorFollowUpStateChanged(int i3) {
        Log.v(TAG, "onAutoExternalRearMirrorFollowUpStateChanged");
    }

    public void onAutoExternalRearMirrorFollowUpSwitchChanged(int i3) {
        Log.v(TAG, "onAutoExternalRearMirrorFollowUpSwitchChanged");
    }

    public void onAutoLockChanged(int i3) {
        Log.v(TAG, "onAutoLockChanged");
    }

    public void onAutoLockSwitchChanged(int i3) {
        Log.v(TAG, "onAutoLockSwitchChanged");
    }

    public void onAutoLockTimeChanged(int i3) {
        Log.v(TAG, "onAutoLockTimeChanged");
    }

    public void onAutoPowerOffStateChanged(int i3) {
        Log.v(TAG, "onAutoPowerOffStateChanged");
    }

    public void onAutoPowerOnStateChanged(int i3) {
        Log.v(TAG, "onAutoPowerOnStateChanged");
    }

    public void onAutoRainWiperStateChanged(int i3) {
        Log.v(TAG, "onAutoRainWiperStateChanged");
    }

    public void onAutoReportContentChanged(int i3, int i4) {
        Log.v(TAG, "onAutoReportContentChanged");
    }

    public void onAutomaticParkingSwitchChanged(int i3) {
        Log.v(TAG, "onAutomaticParkingSwitchChanged");
    }

    public void onBSISCollisionStateChanged(int i3) {
        Log.v(TAG, "onBSISCollisionStateChanged");
    }

    public void onBSISFuncStatusChanged(int i3) {
        Log.v(TAG, "onBSISFuncStatusChanged");
    }

    public void onBTCallReductionWindMenuStateChanged(int i3) {
        Log.v(TAG, "onBTCallReductionWindMenuStateChanged");
    }

    public void onBackDoorElectricModeChanged(int i3) {
        Log.v(TAG, "onBackDoorElectricModeChanged");
    }

    public void onBackDoorOpenedHeightChanged(int i3) {
        Log.v(TAG, "onBackDoorOpenedHeightChanged");
    }

    public void onBackHomeLightDelaySwitchChanged(int i3) {
        Log.v(TAG, "onBackHomeLightDelaySwitchChanged");
    }

    public void onBackHomeLightDelayValueChanged(int i3) {
        Log.v(TAG, "onBackHomeLightDelayValueChanged");
    }

    public void onBackRowControlSwitchChanged(int i3) {
        Log.v(TAG, "onBackRowControlSwitchChanged");
    }

    public void onBasePadAutoRiseStateChanged(int i3) {
        Log.v(TAG, "onBasePadAutoRiseStateChanged");
    }

    public void onBleOpenDriverDoorStateChanged(int i3) {
        Log.v(TAG, "onBleOpenDriverDoorStateChanged");
    }

    public void onBrakeCloseDoorChanged(int i3) {
        Log.v(TAG, "onBrakeCloseDoorChanged");
    }

    public void onBrakePedalModeChanged(int i3) {
        Log.v(TAG, "onBrakePedalModeChanged");
    }

    public void onCVCModeChanged(int i3) {
        Log.v(TAG, "onCVCModeChanged");
    }

    public void onCampingBlanceActionChanged(int i3) {
        Log.v(TAG, "onCampingBlanceActionChanged");
    }

    public void onCampingBlanceSupportedChanged(int i3) {
        Log.v(TAG, "onCampingBlanceSupportedChanged");
    }

    public void onCampintBlacneStateChanged(int i3) {
        Log.v(TAG, "onCampintBlacneStateChanged");
    }

    public void onCancelRecoverParms(int i3, int i4, int i5) {
        Log.v(TAG, "onCancelRecoverParms");
    }

    public void onCanopyAutomaticDimming(int i3) {
        Log.v(TAG, "onCanopyAutomaticDimming");
    }

    public void onCarRecorderRecordingChanged(int i3) {
        Log.v(TAG, "onCarRecorderRecordingChanged");
    }

    public void onCarTipsSoundTypeChanged(int i3) {
        Log.v(TAG, "onCarTipsSoundTypeChanged");
    }

    public void onCarWarnINDSwitchChanged(int i3) {
        Log.v(TAG, "onCarWarnINDSwitchChanged");
    }

    public void onChargingPortSwitchChanged(int i3) {
        Log.v(TAG, "onChargingPortSwitchChanged");
    }

    public void onCloseDoorLevelChanged(int i3) {
        Log.v(TAG, "onCloseDoorLevelChanged");
    }

    public void onComSpeedDownStateChanged(int i3) {
        Log.v(TAG, "onComSpeedDownStateChanged");
    }

    public void onComSpeedUpStateChanged(int i3) {
        Log.v(TAG, "onComSpeedUpStateChanged");
    }

    public void onComfortableParkingStateChanged(int i3) {
        Log.v(TAG, "onComfortableParkingStateChanged");
    }

    public void onControlCallbackStateChanged(int i3) {
        Log.v(TAG, "onControlCallbackStateChanged");
    }

    public void onControlWindowSwitchChanged(int i3) {
        Log.v(TAG, "onControlWindowSwitchChanged");
    }

    public void onCourtesyLampTimeChanged(int i3) {
        Log.v(TAG, "onCourtesyLampTimeChanged");
    }

    public void onCpdImsSwitchStateChanged(int i3) {
        Log.v(TAG, "onCpdImsSwitchStateChanged");
    }

    public void onCruiseBulletBoxChanged(int i3) {
        Log.v(TAG, "onCruiseBulletBoxChanged");
    }

    public void onCuraSwitchStateChanged(int i3) {
        Log.v(TAG, "onCuraSwitchStateChanged state = " + i3);
    }

    public void onCurrentModeOfTailStateChanged(int i3) {
        Log.v(TAG, "onCurrentModeOfTailStateChanged");
    }

    public void onCurrentStateOfTheTailStateChanged(int i3) {
        Log.v(TAG, "onCurrentStateOfTheTailStateChanged");
    }

    public void onCurrentStatusOfDimmingSunroof(int i3) {
        Log.v(TAG, "onCurrentStatusOfDimmingSunroof");
    }

    public void onCurrentTransmittanceOfSkylight(int i3) {
        Log.v(TAG, "onCurrentTransmittanceOfSkylight");
    }

    public void onDampStateChanged(int i3) {
        Log.v(TAG, "onDampStateChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onDelayDownPowerTimeChanged(int i3) {
        Log.v(TAG, "onDelayDownPowerTimeChanged");
    }

    public void onDelayPowerDownTimeChanged(int i3) {
        Log.v(TAG, "onDelayPowerDownTimeChanged");
    }

    public void onDelayPowerOffStateChanged(int i3) {
        Log.v(TAG, "onDelayPowerOffStateChanged");
    }

    public void onDelayPowerOffStatusChanged(int i3) {
        Log.v(TAG, "onDelayPowerOffStatusChanged");
    }

    public void onDelayPowerOffTimeChanged(int i3) {
        Log.v(TAG, "onDelayPowerOffTimeChanged");
    }

    public void onDimmingCanopyLowCode(int i3) {
        Log.v(TAG, "onDimmingCanopyLowCode");
    }

    public void onDisinfStateChanged(int i3) {
        Log.v(TAG, "onDisinfStateChanged");
    }

    public void onDlgMessageCodeChanged(int i3) {
        Log.v(TAG, "onDlgMessageCodeChanged");
    }

    public void onDmPlatformChanged(int i3) {
        Log.v(TAG, "onDmPlatformChanged");
    }

    public void onDoorLockChanged(int i3) {
        Log.v(TAG, "onDoorLockChanged");
    }

    public void onDoorknobAutoShrinkStateChanged(int i3) {
        Log.v(TAG, "onDoorknobAutoShrinkStateChanged");
    }

    public void onDragAndDropModeTipsChanged(int i3) {
        Log.v(TAG, "onDragAndDropModeTipsChanged");
    }

    public void onDragModeForwardAndBackwardChanged(int i3) {
        Log.v(TAG, "onDragModeForwardAndBackwardChanged");
    }

    public void onDriveAutoLockWindowStateChanged(int i3) {
        Log.v(TAG, "onDriveAutoLockWindowStateChanged");
    }

    public void onDriveConfigTypeChanged(int i3) {
        Log.v(TAG, "onDriveConfigTypeChanged");
    }

    public void onDriveDoorCloseIndSwitchChanged(int i3) {
        Log.v(TAG, "onDriveDoorCloseIndSwitchChanged");
    }

    public void onDriveModeChanged(int i3) {
        Log.v(TAG, "onDriveModeChanged mode = " + i3);
    }

    public void onDriverSeatAutoReturnB3Changded(int i3) {
        Log.v(TAG, "onDriverSeatAutoReturnB3Changded");
    }

    public void onDriverSeatAutoReturnStateChanged(int i3) {
        Log.v(TAG, "onDriverSeatAutoReturnStateChanged");
    }

    public void onDriverSeatAutoReturnSwitchChanged(int i3) {
        Log.v(TAG, "onDriverSeatAutoReturnSwitchChanged");
    }

    public void onDriverSeatBackChanged(int i3) {
        Log.v(TAG, "onDriverSeatBackChanged");
    }

    public void onDriverSeatHeating437StateChanged(int i3) {
        Log.v(TAG, "onDriverSeatHeating437StateChanged");
    }

    public void onDriverSeatVentilating437StateChanged(int i3) {
        Log.v(TAG, "onDriverSeatVentilating437StateChanged");
    }

    public void onDrivingRecorderChanged(int i3) {
        Log.v(TAG, "onDrivingRecorderChanged");
    }

    public void onDrivingRecorderSwitchChanged(int i3) {
        Log.v(TAG, "onDrivingRecorderSwitchChanged");
    }

    public void onDyHeightLevelChanged(int i3) {
        Log.v(TAG, "onDyHeightLevelChanged");
    }

    public void onDyHeightToggleStateChanged(int i3) {
        Log.v(TAG, "onDyHeightToggleStateChanged");
    }

    public void onDyHeightTurnActionChanged(int i3) {
        Log.v(TAG, "onDyHeightTurnActionChanged");
    }

    public void onDynamicSuspensionModeChanged(int i3) {
        Log.v(TAG, "onDynamicSuspensionModeChanged");
    }

    public void onEPedalModeChanged(int i3) {
        Log.v(TAG, "onEPedalModeChanged");
    }

    public void onElecHandbrakeStateChanged(int i3) {
        Log.v(TAG, "onElecHandbrakeStateChanged");
    }

    public void onElectricPedalStateChanged(int i3, int i4) {
        Log.v(TAG, "onElectricPedalStateChanged");
    }

    public void onElectricTailMaintenanceFuntatusStateChanged(int i3) {
        Log.v(TAG, "onElectricTailMaintenanceFuntatusStateChanged");
    }

    public void onEnergyFeedbackStrengthChanged(int i3) {
        Log.v(TAG, "onEnergyFeedbackStrengthChanged");
    }

    public void onEngineOilExitUpdateStateChanged(int i3) {
        Log.v(TAG, "onEngineOilExitUpdateStateChanged");
    }

    public void onEngineOilLevelChanged(int i3) {
        Log.v(TAG, "onEngineOilLevelChanged");
    }

    public void onEngineOilUpdateSignalChanged(int i3) {
        Log.v(TAG, "onEngineOilUpdateSignalChanged");
    }

    public void onEngineSoundTypeChanged(int i3) {
        Log.v(TAG, "onEngineSoundTypeChanged");
    }

    public void onError(int i3, String str) {
        Log.v(TAG, "onError");
    }

    public void onExhibitionModeChanged(int i3) {
        Log.v(TAG, "onExhibitionModeChanged");
    }

    public void onExhibitionStartEnginePromptChanged(int i3) {
        Log.v(TAG, "onExhibitionStartEnginePromptChanged");
    }

    public void onFdCRCCheckNum(int i3) {
        Log.v(TAG, "onFdCRCCheckNum");
    }

    public void onFdCampingBlanceActionChanged(int i3) {
        Log.v(TAG, "onFdCampingBlanceActionChanged");
    }

    public void onFdCampingBlanceSupportedChanged(int i3) {
        Log.v(TAG, "onFdCampingBlanceSupportedChanged");
    }

    public void onFdCampintBlacneStateChanged(int i3) {
        Log.v(TAG, "onFdCampintBlacneStateChanged");
    }

    public void onFdComSpeedUpStateChanged(int i3) {
        Log.v(TAG, "onFdComSpeedUpStateChanged");
    }

    public void onFdDampStateChanged(int i3) {
        Log.v(TAG, "onFdDampStateChanged");
    }

    public void onFdDyHeightLevelChanged(int i3) {
        Log.v(TAG, "onFdDyHeightLevelChanged");
    }

    public void onFdDyHeightToggleStateChanged(int i3) {
        Log.v(TAG, "onFdDyHeightToggleStateChanged");
    }

    public void onFdDyHeightTurnActionChanged(int i3) {
        Log.v(TAG, "onFdDyHeightTurnActionChanged");
    }

    public void onFdDynamicSuspensionMode(int i3) {
        Log.v(TAG, "onFdDynamicSuspensionMode");
    }

    public void onFdFixedStateChanged(int i3) {
        Log.v(TAG, "onFdFixedStateChanged");
    }

    public void onFdFrontBoxTakeStateChanged(int i3) {
        Log.v(TAG, "onFdFrontBoxTakeStateChanged");
    }

    public void onFdLiftModeStateChanged(int i3) {
        Log.v(TAG, "onFdLiftModeStateChanged");
    }

    public void onFdModeSupportedHturnChanged(int i3) {
        Log.v(TAG, "onFdModeSupportedHturnChanged");
    }

    public void onFdSpeedSupportedTurn(int i3) {
        Log.v(TAG, "onFdSpeedSupportedTurn");
    }

    public void onFdTailBoxTakeStateChanged(int i3) {
        Log.v(TAG, "onFdTailBoxTakeStateChanged");
    }

    public void onFdTailBoxTakeSupportedChanged(int i3) {
        Log.v(TAG, "onFdTailBoxTakeSupportedChanged");
    }

    public void onFdTrailerModeCodeChanged(int i3) {
        Log.v(TAG, "onFdTrailerModeCodeChanged");
    }

    public void onFdWelcomeActiveChanged(int i3) {
        Log.v(TAG, "onFdWelcomeActiveChanged");
    }

    public void onFdWelcomeStateChanged(int i3) {
        Log.v(TAG, "onFdWelcomeStateChanged");
    }

    public void onFdWelcomeSupportedChanged(int i3) {
        Log.v(TAG, "onFdWelcomeSupportedChanged");
    }

    public void onFixedStateChanged(int i3) {
        Log.v(TAG, "onFixedStateChanged");
    }

    public void onFridgeChildLockChanged(int i3) {
        Log.v(TAG, "onFridgeChildLockChanged");
    }

    public void onFridgeDoorStateChanged(int i3) {
        Log.v(TAG, "onFridgeDoorStateChanged");
    }

    public void onFridgeLocationChanged(int i3) {
        Log.v(TAG, "onFridgeLocationChanged");
    }

    public void onFridgeWorkColdTemperatureChanged(int i3) {
        Log.v(TAG, "onFridgeWorkColdTemperatureChanged");
    }

    public void onFridgeWorkHeatTemperatureChanged(int i3) {
        Log.v(TAG, "onFridgeWorkHeatTemperatureChanged");
    }

    public void onFridgeWorkSaveTemperatureChanged(int i3) {
        Log.v(TAG, "onFridgeWorkSaveTemperatureChanged");
    }

    public void onFrontWiperSpeedValueChanged(int i3) {
        Log.v(TAG, "onFrontWiperSpeedValueChanged");
    }

    public void onGoalRoadModeChanged(int i3) {
        Log.v(TAG, "onGoalRoadModeChanged state = " + i3);
    }

    public void onHSpeedAutoCloseStateChanged(int i3) {
        Log.v(TAG, "onHSpeedAutoCloseStateChanged");
    }

    public void onHandleAutoResetTimeChanged(int i3) {
        Log.v(TAG, "onHandleAutoResetTimeChanged");
    }

    public void onHeadlampLevelChanged(int i3) {
        Log.v(TAG, "onHeadlampLevelChanged");
    }

    public void onHeadrestHorizationChanged(int i3, float f3, int i4, int i5) {
        Log.v(TAG, "onHeadrestHorizationChanged");
    }

    public void onHeadrestTurnHeightChanged(int i3, int i4, float f3) {
        Log.v(TAG, "onHeadrestTurnHeightChanged");
    }

    public void onHeadrestTurnHorizationChanged(int i3, int i4, float f3) {
        Log.v(TAG, "onHeadrestTurnHorizationChanged");
    }

    public void onHevChargingPortSwitchChanged(int i3) {
        Log.v(TAG, "onHevChargingPortSwitchChanged");
    }

    public void onHomeLightTimeChanged(int i3) {
        Log.v(TAG, "onHomeLightTimeChanged");
    }

    public void onHsMassageStateChanged(int i3, int i4) {
        Log.v(TAG, "onHsMassageStateChanged");
    }

    public void onHudBrightnessChanged(int i3) {
        Log.v(TAG, "onHudBrightnessChanged");
    }

    public void onHudDisplayContentChanged(int i3, int i4) {
        Log.v(TAG, "onHudDisplayContentChanged");
    }

    public void onHudHeightChanged(int i3) {
        Log.v(TAG, "onHudHeightChanged");
    }

    public void onHudModeChanged(int i3) {
        Log.v(TAG, "onHudModeChanged");
    }

    public void onHudNavigationStateChanged(int i3) {
        Log.v(TAG, "onHudNavigationStateChanged");
    }

    public void onHudParamsEffect(int i3) {
        Log.v(TAG, "onHudParamsEffect");
    }

    public void onHudPatternChanged(int i3) {
        Log.v(TAG, "onHudPatternChanged");
    }

    public void onHudRestartAndEffect(int i3) {
        Log.v(TAG, "onHudRestartAndEffect");
    }

    public void onHudRotationAngleChanged(int i3) {
        Log.v(TAG, "onHudRotationAngleChanged");
    }

    public void onHudSPStateChanged(int i3) {
        Log.v(TAG, "onHudSPStateChanged");
    }

    public void onHudSafeDriveStateChanged(int i3) {
        Log.v(TAG, "onHudSafeDriveStateChanged");
    }

    public void onHudStateChanged(int i3) {
        Log.v(TAG, "onHudStateChanged");
    }

    public void onIALAreaChanged(int i3) {
        Log.v(TAG, "onIALAreaChanged");
    }

    public void onIALBrightnessChanged(int i3, int i4) {
        Log.v(TAG, "onIALBrightnessChanged");
    }

    public void onIALColorChanged(int i3, int i4) {
        Log.v(TAG, "onIALColorChanged");
    }

    public void onIKEYBTLowPowerModeChanged(int i3) {
        Log.v(TAG, "onIKEYBTLowPowerModeChanged");
    }

    public void onILDurationChanged(int i3) {
        Log.v(TAG, "onILDurationChanged");
    }

    public void onINSThemeChanged(int i3) {
        Log.v(TAG, "onINSThemeChanged");
    }

    public void onIVIProfileSettingsChanged(int i3) {
        Log.v(TAG, "onIVIProfileSettingsChanged");
    }

    public void onInsideLightDoorStateChanged(int i3) {
        Log.v(TAG, "onInsideLightDoorStateChanged");
    }

    public void onInsideRearMirrorScreenSwitchChanged(int i3) {
        Log.v(TAG, "onInsideRearMirrorScreenSwitchChanged");
    }

    public void onInstrumentAcKeyTypeChanged(int i3) {
        Log.v(TAG, "onInstrumentAcKeyTypeChanged");
    }

    public void onIntelLockOpenDriverDoorChanged(int i3) {
        Log.v(TAG, "onIntelLockOpenDriverDoorChanged");
    }

    public void onIntelligentAcAreaStateChanged(int i3) {
        Log.v(TAG, "onIntelligentAcAreaStateChanged");
    }

    public void onIntelligentLockStateChanged(int i3) {
        Log.v(TAG, "onIntelligentLockStateChanged");
    }

    public void onIntelligentUnlockStateChanged(int i3) {
        Log.v(TAG, "onIntelligentUnlockStateChanged");
    }

    public void onIntelligentVoiceINDLevelChanged(int i3) {
        Log.v(TAG, "onIntelligentVoiceINDLevelChanged");
    }

    public void onIntelligentVoiceINDSwitchChanged(int i3) {
        Log.v(TAG, "onIntelligentVoiceINDSwitchChanged");
    }

    public void onKeyPowerLowIndSwitchChanged(int i3) {
        Log.v(TAG, "onKeyPowerLowIndSwitchChanged");
    }

    public void onKeyVibratorLevelChanged(int i3) {
        Log.v(TAG, "onKeyVibratorLevelChanged");
    }

    public void onLPSwitchDownwindowChanged(int i3) {
        Log.v(TAG, "onLPSwitchDownwindowChanged");
    }

    public void onLPSwitchUpwindowChanged(int i3) {
        Log.v(TAG, "onLPSwitchUpwindowChanged");
    }

    public void onLRSEChildModeCodeWriteFb(int i3) {
        Log.v(TAG, "onLRSEChildModeCodeWriteFb");
    }

    public void onLRSEChildModeTimeWriteFb(int i3) {
        Log.v(TAG, "onLRSEChildModeTimeWriteFb");
    }

    public void onLRSEMachineSwitchStateChanged(int i3) {
        Log.v(TAG, "onLRSEMachineSwitchStateChanged");
    }

    public void onLandAdvanceAtSateChanged(int i3) {
        Log.v(TAG, "onLandAdvanceAtSateChanged");
    }

    public void onLanguageChanged(int i3) {
        Log.v(TAG, "onLanguageChanged");
    }

    public void onLanternLanguageModeChanged(int i3) {
        Log.v(TAG, "onLanternLanguageModeChanged");
    }

    public void onLearnerDriverAccLimitStateChanged(int i3) {
        Log.v(TAG, "onLearnerDriverAccLimitStateChanged");
    }

    public void onLearnerDriverModeStateChanged(int i3) {
        Log.v(TAG, "onLearnerDriverModeStateChanged");
    }

    public void onLearnerDriverModeSwitchStateChanged(int i3) {
        Log.v(TAG, "onLearnerDriverModeSwitchStateChanged");
    }

    public void onLeaveCarPIndSwitchChanged(int i3) {
        Log.v(TAG, "onLeaveCarPIndSwitchChanged");
    }

    public void onLeaveHomeLightTimeChanged(int i3) {
        Log.v(TAG, "onLeaveHomeLightTimeChanged");
    }

    public void onLeftFieldPlatformChanged(int i3) {
        Log.v(TAG, "onLeftFieldPlatformChanged");
    }

    public void onLeftHeadlampLevelChanged(int i3) {
        Log.v(TAG, "onLeftHeadlampLevelChanged");
    }

    public void onLeftHomeLightDelaySwitchChanged(int i3) {
        Log.v(TAG, "onLeftHomeLightDelaySwitchChanged");
    }

    public void onLeftHomeLightDelayValueChanged(int i3) {
        Log.v(TAG, "onLeftHomeLightDelayValueChanged");
    }

    public void onLeftViewMirrorFlipAngleChanged(int i3) {
        Log.v(TAG, "onLeftViewMirrorFlipAngleChanged");
    }

    public void onLiftModeStateChanged(int i3) {
        Log.v(TAG, "onLiftModeStateChanged");
    }

    public void onLockCarRiseWindowChanged(int i3) {
        Log.v(TAG, "onLockCarRiseWindowChanged");
    }

    public void onLockCarRiseWindowStateChange(int i3) {
        Log.v(TAG, "onLockCarRiseWindowStateChange");
    }

    public void onLockOffChanged(int i3) {
        Log.v(TAG, "onLockOffChanged");
    }

    public void onLockOffDoorChanged(int i3) {
        Log.v(TAG, "onLockOffDoorChanged");
    }

    public void onLockUpwindowChanged(int i3) {
        Log.v(TAG, "onLockUpwindowChanged");
    }

    public void onLongPressUnlockWindowSwitchChanged(int i3) {
        Log.v(TAG, "onLongPressUnlockWindowSwitchChanged");
    }

    public void onLowBatteryIndSwitchChanged(int i3) {
        Log.v(TAG, "onLowBatteryIndSwitchChanged");
    }

    public void onLowOilIndSwitchChanged(int i3) {
        Log.v(TAG, "onLowOilIndSwitchChanged");
    }

    public void onMOISAssembleStateChanged(int i3) {
        Log.v(TAG, "onMOISAssembleStateChanged");
    }

    public void onMOISFuncStatusChanged(int i3) {
        Log.v(TAG, "onMOISFuncStatusChanged");
    }

    public void onMaintainRemindStateChanged(int i3) {
        Log.v(TAG, "onMaintainRemindStateChanged");
    }

    public void onMassageLevelStateChanged(int i3, int i4) {
        Log.v(TAG, "onMassageLevelStateChanged");
    }

    public void onMassageModeStateChanged(int i3, int i4) {
        Log.v(TAG, "onMassageModeStateChanged");
    }

    public void onMcuRestartFlagChanged(int i3) {
        Log.v(TAG, "onMcuRestartFlagChanged");
    }

    public void onMemorySetOperationFlagChanged(int i3) {
        Log.v(TAG, "onMemorySetOperationFlagChanged flag = " + i3);
    }

    public void onMicroSwitchLockWindowStateChanged(int i3) {
        Log.v(TAG, "onMicroSwitchLockWindowStateChanged");
    }

    public void onMicroSwitchUnlockWindowStateChanged(int i3) {
        Log.v(TAG, "onMicroSwitchUnlockWindowStateChanged");
    }

    public void onMissKeyIndSwitchChanged(int i3) {
        Log.v(TAG, "onMissKeyIndSwitchChanged");
    }

    public void onMissPackageCountChanged(int i3) {
        Log.v(TAG, "onMissPackageCountChanged");
    }

    public void onModeSupportedHturnChanged(int i3) {
        Log.v(TAG, "onModeSupportedHturnChanged（int");
    }

    public void onMotorPretensionerSeatbeltStateChanged(int i3) {
        Log.v(TAG, "onMotorPretensionerSeatbeltStateChanged");
    }

    public void onMultimediaMotorTemperatureStateChanged(int i3) {
        Log.v(TAG, "onMultimediaMotorTemperatureStateChanged");
    }

    public void onNavigationMediaStateChanged(int i3) {
        Log.v(TAG, "onNavigationMediaStateChanged");
    }

    public void onNfcOpenDriverDoorChanged(int i3) {
        Log.v(TAG, "onNfcOpenDriverDoorChanged");
    }

    public void onNightDRLightOnIndSwitchChanged(int i3) {
        Log.v(TAG, "onNightDRLightOnIndSwitchChanged");
    }

    public void onNightDriveLightOnReminderChanged(int i3) {
        Log.v(TAG, "onNightDriveLightOnReminderChanged");
    }

    public void onNightSystemModeSwitchChanged(int i3) {
        Log.v(TAG, "onNightSystemModeSwitchChanged");
    }

    public void onOffReminderChanged(int i3) {
        Log.v(TAG, "onOffReminderChanged");
    }

    public void onOverSpeedLockStateChanged(int i3) {
        Log.v(TAG, "onOverSpeedLockStateChanged");
    }

    public void onOverspeedLockStateChanged(int i3) {
        Log.v(TAG, "onOverspeedLockStateChanged");
    }

    public void onPABStateChanged(int i3) {
        Log.v(TAG, "onPABStateChanged");
    }

    public void onPABStateCodeChanged(int i3) {
        Log.v(TAG, "onPABStateCodeChanged");
    }

    public void onPM25PowerSwitchChanged(int i3) {
        Log.v(TAG, "onPM25PowerSwitchChanged");
    }

    public void onPM25SwitchChanged(int i3) {
        Log.v(TAG, "onPM25SwitchChanged");
    }

    public void onPM25SwitchCheckChanged(int i3) {
        Log.v(TAG, "onPM25SwitchCheckChanged");
    }

    public void onPM25TimeCheckChanged(int i3) {
        Log.v(TAG, "onPM25TimeCheckChanged");
    }

    public void onParkBrakeIndSwitchChanged(int i3) {
        Log.v(TAG, "onParkBrakeIndSwitchChanged");
    }

    public void onPassengerSeatHeating3CAStateChanged(int i3) {
        Log.v(TAG, "onPassengerSeatHeating3CAStateChanged");
    }

    public void onPassengerSeatVentilating3CAStateChanged(int i3) {
        Log.v(TAG, "onPassengerSeatVentilating3CAStateChanged");
    }

    public void onPedalSuppressionSystemStateChanged(int i3) {
        Log.v(TAG, "onPedalSuppressionSystemStateChanged state = " + i3);
    }

    public void onPedestrianRecognizeSwitchChanged(int i3) {
        Log.v(TAG, "onPedestrianRecognizeSwitchChanged");
    }

    public void onPhoneRemindCodeChanged(int i3) {
        Log.v(TAG, "onPhoneRemindCodeChanged");
    }

    public void onRRSEChildModeCodeWriteFb(int i3) {
        Log.v(TAG, "onRRSEChildModeCodeWriteFb");
    }

    public void onRRSEChildModeTimeWriteFb(int i3) {
        Log.v(TAG, "onRRSEChildModeTimeWriteFb");
    }

    public void onRRSEMachineSwitchStateChanged(int i3) {
        Log.v(TAG, "onRRSEMachineSwitchStateChanged");
    }

    public void onRainAutoLockWindowStateChanged(int i3) {
        Log.v(TAG, "onRainAutoLockWindowStateChanged");
    }

    public void onRearAcOnlineStateChanged(int i3) {
        Log.v(TAG, "onRearAcOnlineStateChanged");
    }

    public void onRearAcPanelAutoLockTimeChanged(int i3) {
        Log.v(TAG, "onRearAcPanelAutoLockTimeChanged");
    }

    public void onRearDoorStateChanged(int i3) {
        Log.v(TAG, "onRearDoorStateChanged");
    }

    public void onRearHeatTempChanged(int i3) {
        Log.v(TAG, "onRearHeatTempChanged");
    }

    public void onRearMirrorFlipChanged(int i3) {
        Log.v(TAG, "onRearMirrorFlipChanged");
    }

    public void onRearPanelLockTimeModeChange(int i3) {
        Log.v(TAG, "onRearPanelLockTimeModeChange");
    }

    public void onRearPanelLockTimeModeChanged(int i3) {
        Log.v(TAG, "onRearPanelLockTimeModeChanged");
    }

    public void onRearRightSeatGreetStateChanged(int i3) {
        Log.v(TAG, "onRearRightSeatGreetStateChanged state = " + i3);
    }

    public void onRearSaveTempChanged(int i3) {
        Log.v(TAG, "onRearSaveTempChanged");
    }

    public void onRearSeatGreetStateChanged(int i3) {
        Log.v(TAG, "onRearSeatGreetStateChanged");
    }

    public void onRearViewEcuMirrorFlipStateChanged(int i3) {
        Log.v(TAG, "onRearViewEcuMirrorFlipStateChanged");
    }

    public void onRearViewMirrorAngleChanged(int i3) {
        Log.v(TAG, "onRearViewMirrorAngleChanged");
    }

    public void onRearViewMirrorAutoFoldModeChanged(int i3) {
        Log.v(TAG, "onRearViewMirrorAutoFoldModeChanged");
    }

    public void onRearViewMirrorFlipChanged(int i3) {
        Log.v(TAG, "onRearViewMirrorFlipChanged");
    }

    public void onRearViewMirrorFlipSetChanged(int i3) {
        Log.v(TAG, "onRearViewMirrorFlipSetChanged");
    }

    public void onRearViewMirrorFlipSwitchChanged(int i3) {
        Log.v(TAG, "onRearViewMirrorFlipSwitchChanged");
    }

    public void onRearWiperSpeedValueChanged(int i3) {
        Log.v(TAG, "onRearWiperSpeedValueChanged");
    }

    public void onRearviewMirrorIsAutoLightStateChanged(int i3) {
        Log.v(TAG, "onRearviewMirrorIsAutoLightStateChanged");
    }

    public void onRearviewMirrorIsStateChanged(int i3) {
        Log.v(TAG, "onRearviewMirrorIsStateChanged");
    }

    public void onRearviewMirrorIsTurnedStateChanged(int i3) {
        Log.v(TAG, "onRearviewMirrorIsTurnedStateChanged");
    }

    public void onRecoverCaliperSplintHeight(int i3, int i4, int i5) {
        Log.v(TAG, "onRecoverCaliperSplintHeight");
    }

    public void onRecoverCushionHeight(int i3, int i4, int i5) {
        Log.v(TAG, "onRecoverCushionHeight");
    }

    public void onRecoverOrSaveParamsChanged(int i3, int i4, int i5, int i6) {
        Log.v(TAG, "onRecoverOrSaveParamsChanged position:" + i3 + " location:" + i4 + " state:" + i5 + " code:" + i6);
    }

    public void onRecoverRearviewMirrorParams(int i3, int i4, int i5) {
        Log.v(TAG, "onRecoverRearviewMirrorParams");
    }

    public void onRecoverSeatParams(int i3, int i4, int i5) {
        Log.v(TAG, "onRecoverSeatParams");
    }

    public void onRecoverSteeringWheelPosition(int i3, int i4) {
        Log.v(TAG, "onRecoverSteeringWheelPosition");
    }

    public void onRefrigWorkModeChanged(int i3) {
        Log.v(TAG, "onRefrigWorkModeChanged mode = " + i3);
    }

    public void onRefrigeratorMobileTerminalPushReChanged(int i3) {
        Log.v(TAG, "onRefrigeratorMobileTerminalPushReChanged code = " + i3);
    }

    public void onRefrigeratorModeChanged(int i3) {
        Log.v(TAG, "onRefrigeratorModeChanged");
    }

    public void onRefrigeratorState(int i3) {
        Log.v(TAG, "onRefrigeratorState");
    }

    public void onRefrigeratorTemperatureChanged(int i3) {
        Log.v(TAG, "onRefrigeratorTemperatureChanged");
    }

    public void onRefrigeratorWorkModeChanged(int i3) {
        Log.v(TAG, "onRefrigeratorWorkModeChanged");
    }

    public void onRefrigeratorWorkStateChanged(int i3) {
        Log.v(TAG, "onRefrigeratorWorkStateChanged");
    }

    public void onRefrigeratorWorkTemperatureChanged(int i3) {
        Log.v(TAG, "onRefrigeratorWorkTemperatureChanged");
    }

    public void onRemberPowerOffStateChanged(int i3) {
        Log.v(TAG, "onRemberPowerOffStateChanged");
    }

    public void onRemenberPowerOffStateChanged(int i3) {
        Log.v(TAG, "onRemenberPowerOffStateChanged");
    }

    public void onRemoteControlDownwindowChanged(int i3) {
        Log.v(TAG, "onRemoteControlDownwindowChanged");
    }

    public void onRemoteControlSwitchChanged(int i3) {
        Log.v(TAG, "onRemoteControlSwitchChanged");
    }

    public void onRemoteControlUnlockStateChanged(int i3) {
        Log.v(TAG, "onRemoteControlUnlockStateChanged");
    }

    public void onRemoteControlUpwindowChanged(int i3) {
        Log.v(TAG, "onRemoteControlUpwindowChanged");
    }

    public void onRemoteControlUpwindowStateChanged(int i3) {
        Log.v(TAG, "onRemoteControlUpwindowStateChanged");
    }

    public void onRemoteCtlUnlockingStateChanged(int i3) {
        Log.v(TAG, "onRemoteCtlUnlockingStateChanged");
    }

    public void onRemoteLockWindowStateChanged(int i3) {
        Log.v(TAG, "onRemoteLockWindowStateChanged");
    }

    public void onRemoteUnlockWindowStateChanged(int i3) {
        Log.v(TAG, "onRemoteUnlockWindowStateChanged");
    }

    public void onReportVolumeChanged(int i3) {
        Log.v(TAG, "onReportVolumeChanged");
    }

    public void onResetAutoExternalRearMirrorFollowUp(int i3) {
        Log.v(TAG, "onResetAutoExternalRearMirrorFollowUp");
    }

    public void onResetAutoPowerOnState(int i3) {
        Log.v(TAG, "onResetAutoPowerOnState");
    }

    public void onResetHudBrightness(int i3) {
        Log.v(TAG, "onResetHudBrightness");
    }

    public void onResetHudDisplayContent(int i3) {
        Log.v(TAG, "onResetHudDisplayContent");
    }

    public void onResetHudHeight(int i3) {
        Log.v(TAG, "onResetHudHeight");
    }

    public void onResetHudMode(int i3) {
        Log.v(TAG, "onResetHudMode");
    }

    public void onResetHudRotationAngle(int i3) {
        Log.v(TAG, "onResetHudRotationAngle");
    }

    public void onResetMotorPretensionerSeatbelt(int i3) {
        Log.v(TAG, "onResetMotorPretensionerSeatbelt");
    }

    public void onRightHeadlampLevelChanged(int i3) {
        Log.v(TAG, "onRightHeadlampLevelChanged");
    }

    public void onRightViewMirrorFlipAngleChanged(int i3) {
        Log.v(TAG, "onRightViewMirrorFlipAngleChanged");
    }

    public void onRncStateChanged(int i3) {
        Log.v(TAG, "onRncStateChanged");
    }

    public void onRuningCloseDoorStateChanged(int i3) {
        Log.v(TAG, "onRuningCloseDoorStateChanged");
    }

    public void onSOCConfigChanged(int i3) {
        Log.v(TAG, "onSOCConfigChanged");
    }

    public void onSOCTargetLowLimitChanged(int i3) {
        Log.v(TAG, "onSOCTargetLowLimitChanged");
    }

    public void onSOCTargetRangeChange(int i3) {
        Log.v(TAG, "onSOCTargetRangeChange");
    }

    public void onSOCTargetRangeChanged(int i3) {
        Log.v(TAG, "onSOCTargetRangeChanged");
    }

    public void onSafeBeltCloseDriveDoorStateChanged(int i3) {
        Log.v(TAG, "onSafeBeltCloseDriveDoorStateChanged");
    }

    public void onSafeBeltIndSwitchChanged(int i3) {
        Log.v(TAG, "onSafeBeltIndSwitchChanged");
    }

    public void onSafeBeltWarnStateChanged(int i3) {
        Log.v(TAG, "onSafeBeltWarnStateChanged  state=" + i3);
    }

    public void onSafeWarnStateChanged(int i3) {
        Log.v(TAG, "onSafeWarnStateChanged");
    }

    public void onSandAbsModeStateChanged(int i3) {
        Log.v(TAG, "onSandAbsModeStateChanged state = " + i3);
    }

    public void onSandAbsTipMessageChanged(int i3) {
        Log.v(TAG, "onSandAbsTipMessageChanged state = " + i3);
    }

    public void onSaveOwnerStateChanged(int i3) {
        Log.v(TAG, "onSaveOwnerStateChanged sate = " + i3);
    }

    public void onSaveSeatParams(int i3, int i4, float f3, float f4, float f5, int i5) {
        Log.v(TAG, "onSaveSeatParams");
    }

    @Deprecated
    public void onSaveSeatParamsAllChanged(int i3, int i4, int i5) {
        Log.v(TAG, "onSaveSeatParamsAllChanged");
    }

    public void onScreenAngleChanged(int i3) {
        Log.v(TAG, "onScreenAngleChanged");
    }

    public void onScreenAngleValueChanged(int i3) {
        Log.v(TAG, "onScreenAngleValueChanged");
    }

    public void onScreenAntiPinchStateChanged(int i3) {
        Log.v(TAG, "onScreenAntiPinchStateChanged");
    }

    public void onScreenCallbackMessageChanged(int i3) {
        Log.v(TAG, "onScreenCallbackMessageChanged");
    }

    public void onScreenRotationActionChanged(int i3) {
        Log.v(TAG, "onScreenRotationActionChanged");
    }

    public void onScreenRotationOverHotStateChanged(int i3) {
        Log.v(TAG, "onScreenRotationOverHotStateChanged");
    }

    public void onScreenRotationStatus(int i3) {
        Log.v(TAG, "onScreenRotationStatus state = " + i3);
    }

    public void onScreenVoltageStateChanged(int i3) {
        Log.v(TAG, "onScreenVoltageStateChanged");
    }

    public void onSeaModeChargingSocTypeChanged(int i3) {
        Log.v(TAG, "onSeaModeChargingSocTypeChanged value = " + i3);
    }

    public void onSeatHeatingNotL1PStateChanged(int i3) {
        Log.v(TAG, "onSeatHeatingNotL1PStateChanged");
    }

    public void onSeatHeatingState2Changed(int i3, int i4) {
        Log.v(TAG, "onSeatHeatingState2Changed");
    }

    public void onSeatHeatingStateChanged(int i3, int i4) {
        Log.v(TAG, "onSeatHeatingStateChanged");
    }

    public void onSeatHeatingStateChanged1(int i3, int i4) {
        Log.v(TAG, "onSeatHeatingStateChanged1");
    }

    public void onSeatHorizationChanged(int i3, float f3, int i4, int i5) {
        Log.v(TAG, "onSeatHorizationChanged");
    }

    public void onSeatTurnHeight(int i3, float f3, int i4, int i5) {
        Log.v(TAG, "onSeatTurnHeight");
    }

    public void onSeatVentilatingNotL1PStateChanged(int i3) {
        Log.v(TAG, "onSeatVentilatingNotL1PStateChanged");
    }

    public void onSeatVentilatingState2Changed(int i3, int i4) {
        Log.v(TAG, "onSeatVentilatingState2Changed");
    }

    public void onSeatVentilatingStateChanged(int i3, int i4) {
        Log.v(TAG, "onSeatVentilatingStateChanged");
    }

    public void onSeatbackrestPostion(int i3, float f3, int i4, int i5) {
        Log.v(TAG, "onSeatbackrestPostion");
    }

    public void onServiceControlCallbackChanged(int i3) {
        Log.v(TAG, "onServiceControlCallbackChanged");
    }

    public void onSetHudParamsEffectChanged(int i3) {
        Log.v(TAG, "onSetHudParamsEffectChanged");
    }

    public void onSkyCurtainPowerOffAndDim(int i3) {
        Log.v(TAG, "onSkyCurtainPowerOffAndDim");
    }

    public void onSmartWelcomeLightStateChanged(int i3) {
        Log.v(TAG, "onSmartWelcomeLightStateChanged");
    }

    public void onSomethingLeftInRefrigeratorNotice(int i3) {
        Log.v(TAG, "onSomethingLeftInRefrigeratorNotice");
    }

    public void onSpeedReminderValueChanged(int i3) {
        Log.v(TAG, "onSpeedReminderValueChanged");
    }

    public void onSpeedSupportedTurn(int i3) {
        Log.v(TAG, "onSpeedSupportedTurn");
    }

    public void onStartKeyStateChanged(int i3) {
        Log.v(TAG, "onStartKeyStateChanged");
    }

    public void onStartOrPowerIndSwitchChanged(int i3) {
        Log.v(TAG, "onStartOrPowerIndSwitchChanged");
    }

    public void onSteerAssisModeChanged(int i3) {
        Log.v(TAG, "onSteerAssisModeChanged");
    }

    public void onSteerAssisSupportChanged(int i3) {
        Log.v(TAG, "onSteerAssisSupportChanged  state=" + i3);
    }

    public void onSteerAssistPermissionChanged(int i3) {
        Log.v(TAG, "onSteerAssistPermissionChanged");
    }

    public void onSteerBackChanged(int i3) {
        Log.v(TAG, "onSteerBackChanged");
    }

    public void onSteerPositionAutoReturnStateChanged(int i3) {
        Log.v(TAG, "onSteerPositionAutoReturnStateChanged");
    }

    public void onSteerPositionAutoReturnSwitchChanged(int i3) {
        Log.v(TAG, "onSteerPositionAutoReturnSwitchChanged");
    }

    public void onSteeringWheelHeatingAutoStateChanged(int i3) {
        Log.v(TAG, "onSteeringWheelHeatingAutoStateChanged");
    }

    public void onSteeringWheelHeatingGearChanged(int i3) {
        Log.v(TAG, "onSteeringWheelHeatingGearChanged");
    }

    public void onSteeringWheelHeatingStateChanged(int i3) {
        Log.v(TAG, "onSteeringWheelHeatingStateChanged");
    }

    public void onSteeringWheelYChanged(int i3, int i4) {
        Log.v(TAG, "onSteeringWheelYChanged");
    }

    public void onSteeringWheelZChanged(int i3, int i4) {
        Log.v(TAG, "onSteeringWheelZChanged");
    }

    public void onSterilizationStateChanged(int i3) {
        Log.v(TAG, "onSterilizationStateChanged");
    }

    public void onSterilizationWorkStateChanged(int i3) {
        Log.v(TAG, "onSterilizationWorkStateChanged");
    }

    public void onStopRemoteCtrlDriveIndChanged(int i3) {
        Log.v(TAG, "onStopRemoteCtrlDriveIndChanged");
    }

    public void onSwControlStateChanged(int i3) {
        Log.v(TAG, "onSwControlStateChanged state = " + i3);
    }

    public void onSwsMediumRelatedKeys(int i3) {
        Log.v(TAG, "onSwsMediumRelatedKeys");
    }

    public void onTailBoxTakeStateChanged(int i3) {
        Log.v(TAG, "onTailBoxTakeStateChanged");
    }

    public void onTailBoxTakeSupportedChanged(int i3) {
        Log.v(TAG, "onTailBoxTakeSupportedChanged");
    }

    public void onTailInitializationStateChanged(int i3) {
        Log.v(TAG, "onTailInitializationStateChanged");
    }

    public void onTailMotorThermalProtectionStateChanged(int i3) {
        Log.v(TAG, "onTailMotorThermalProtectionStateChanged");
    }

    public void onTailPositionFeedbackChanged(int i3) {
        Log.v(TAG, "onTailPositionFeedbackChanged");
    }

    public void onTailPositionPercentageChanged(int i3) {
        Log.v(TAG, "onTailPositionPercentageChanged");
    }

    public void onTrailerLightCheckStateChanged(int i3) {
        Log.v(TAG, "onTrailerLightCheckStateChanged");
    }

    public void onTrailerModeCodeChanged(int i3) {
        Log.v(TAG, "onTrailerModeCodeChanged");
    }

    public void onTrailerModeMileageChanged(int i3) {
        Log.v(TAG, "onTrailerModeMileageChanged");
    }

    public void onTrailerModeStateChanged(int i3) {
        Log.v(TAG, "onTrailerModeStateChanged");
    }

    public void onTrayActionStateChanged(int i3) {
        Log.v(TAG, "onTrayActionStateChanged");
    }

    public void onTrayStateChanged(int i3, int i4) {
        Log.v(TAG, "onTrayStateChanged");
    }

    public void onTurnCaliperSplintHeightChanged(int i3, int i4, float f3, int i5) {
        Log.v(TAG, "onTurnCaliperSplintHeightChanged");
    }

    public void onTurnCushionHeightChanged(int i3, int i4, float f3, int i5) {
        Log.v(TAG, "onTurnCushionHeightChanged");
    }

    public void onTurnRearviewMirrorChanged(int i3, float f3, float f4) {
        Log.v(TAG, "onTurnRearviewMirrorChanged");
    }

    public void onTurnSeatCaliperSplintChanged(int i3, int i4, float f3) {
        Log.v(TAG, "onTurnSeatCaliperSplintChanged");
    }

    public void onTurnSeatCushionChanged(int i3, int i4, float f3) {
        Log.v(TAG, "onTurnSeatCushionChanged");
    }

    public void onTurnSeatHeightChanged(int i3, int i4, float f3) {
        Log.v(TAG, "onTurnSeatHeightChanged");
    }

    public void onTurnSeatHorizationChanged(int i3, int i4, float f3) {
        Log.v(TAG, "onTurnSeatHorizationChanged");
    }

    public void onTurnSeatbackrestChanged(int i3, int i4, float f3) {
        Log.v(TAG, "onTurnSeatbackrestChanged");
    }

    public void onTurnSteeringWheelChanged(int i3, float f3, float f4) {
        Log.v(TAG, "onTurnSteeringWheelChanged");
    }

    public void onUnlockDownwindowChanged(int i3) {
        Log.v(TAG, "onUnlockDownwindowChanged");
    }

    public void onVSPStateChanged(int i3) {
        Log.v(TAG, "onVSPStateChanged");
    }

    public void onVehicleStateChanged(int i3) {
        Log.v(TAG, "onVehicleStateChanged");
    }

    public void onVoiceTipsMessageChanged(int i3) {
        Log.v(TAG, "onVoiceTipsMessageChanged");
    }

    public void onWadingPatternSocTipsChanaged(int i3) {
        Log.v(TAG, "onWadingPatternSocTipsChanaged state = " + i3);
    }

    public void onWadingPatternSpeedTipsChanged(int i3) {
        Log.v(TAG, "onWadingPatternSpeedTipsChanged state = " + i3);
    }

    public void onWadingPatternStateTipsChanged(int i3) {
        Log.v(TAG, "onWadingPatternStateTipsChanged state = " + i3);
    }

    public void onWadingPatternTestTipsChanged(int i3) {
        Log.v(TAG, "onWadingPatternTestTipsChanged state = " + i3);
    }

    public void onWelcomeActiveChanged(int i3) {
        Log.v(TAG, "onWelcomeActiveChanged");
    }

    public void onWelcomeStateChanged(int i3) {
        Log.v(TAG, "onWelcomeStateChanged");
    }

    public void onWelcomeSupportedChanged(int i3) {
        Log.v(TAG, "onWelcomeSupportedChanged");
    }

    public void onWindscreenWiperOverhaulStateChanged(int i3, int i4) {
        Log.v(TAG, "onWindscreenWiperOverhaulStateChanged");
    }

    public void onWorkStateChanged(int i3) {
        Log.v(TAG, "onWorkStateChanged");
    }

    public void setKeypadToneStatechanged(int i3) {
        Log.v(TAG, "setKeypadToneStatechanged");
    }
}

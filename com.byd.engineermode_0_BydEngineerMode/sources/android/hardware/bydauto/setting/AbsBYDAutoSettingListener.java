package android.hardware.bydauto.setting;

import a.a.c;
import a.a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSettingListener implements d {
    protected static final String TAG = "AbsBYDAutoSettingListener";

    public AbsBYDAutoSettingListener() {
        Log.v(TAG, TAG);
    }

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void on1LevelSeatHeatingStateChanged(int i, int i2) {
        Log.v(TAG, "on1LevelSeatHeatingStateChanged");
    }

    public void on5LevelSeatHeatingLevelChanged(int i, int i2) {
        Log.v(TAG, "on5LevelSeatHeatingLevelChanged");
    }

    public void on5LevelSeatHeatingModeChanged(int i, int i2) {
        Log.v(TAG, "on5LevelSeatHeatingModeChanged");
    }

    public void on5LevelSeatHeatingStateChanged(int i, int i2) {
        Log.v(TAG, "on5LevelSeatHeatingStateChanged");
    }

    public void on5LevelSeatVentilatingLevelChanged(int i, int i2) {
        Log.v(TAG, "on5LevelSeatVentilatingLevelChanged");
    }

    public void on5LevelSeatVentilatingModeChanged(int i, int i2) {
        Log.v(TAG, "on5LevelSeatVentilatingModeChanged");
    }

    public void on5LevelSeatVentilatingStateChanged(int i, int i2) {
        Log.v(TAG, "on5LevelSeatVentilatingStateChanged");
    }

    public void onACAutoAirModeChanged(int i) {
        Log.v(TAG, "onACAutoAirModeChanged");
    }

    public void onACAutoWindLevelChanged(int i) {
        Log.v(TAG, "onACAutoWindLevelChanged");
    }

    public void onACBTWindSwitchChanged(int i) {
        Log.v(TAG, "onACBTWindSwitchChanged");
    }

    public void onACPauseCycleSwitchChanged(int i) {
        Log.v(TAG, "onACPauseCycleSwitchChanged");
    }

    public void onACTunnelCycleSwitchChanged(int i) {
        Log.v(TAG, "onACTunnelCycleSwitchChanged");
    }

    public void onAcAutoPurificationStateChange(int i) {
        Log.v(TAG, "onAcAutoPurificationStateChange");
    }

    public void onAcAutoPurificationStateChanged(int i) {
        Log.v(TAG, "onAcAutoPurificationStateChanged");
    }

    public void onAcKeyStartTimeChange(int i) {
        Log.v(TAG, "onAcKeyStartTimeChange");
    }

    public void onAcKeyStartTimeChanged(int i) {
        Log.v(TAG, "onAcKeyStartTimeChanged");
    }

    public void onAcTempUnitChange(int i) {
        Log.v(TAG, "onAcTempUnitChange");
    }

    public void onAcTempUnitChanged(int i) {
        Log.v(TAG, "onAcTempUnitChanged");
    }

    public void onAcWarmModeChanged(int i) {
        Log.v(TAG, "onAcWarmModeChanged");
    }

    public void onAidedDrivingIndChang(int i) {
        Log.v(TAG, "onAidedDrivingIndChang");
    }

    public void onAirLightPanelStateChanged(int i) {
        Log.v(TAG, "onAirLightPanelStateChanged");
    }

    public void onAirLightTextNumberChanged(int i) {
        Log.v(TAG, "onAirLightTextNumberChanged");
    }

    public void onAirLightTextStateChanged(int i) {
        Log.v(TAG, "onAirLightTextStateChanged");
    }

    public void onAlarmPromptStateChanged(int i) {
        Log.v(TAG, "onAlarmPromptStateChanged");
    }

    public void onAmbientFeedBackChanged(int i) {
        Log.v(TAG, "onAmbientFeedBackChanged");
    }

    public void onAmbientMulticolorModeChanged(int i, int i2) {
        Log.v(TAG, "onAmbientMulticolorModeChanged");
    }

    public void onAmbientMulticolorStateChanged(int i, int i2) {
        Log.v(TAG, "onAmbientMulticolorStateChanged");
    }

    public void onAmbientMusicModeState(int i, int i2) {
        Log.v(TAG, "onAmbientMusicModeState");
    }

    public void onAmbientMusicModechanged(int i, int i2) {
        Log.v(TAG, "onAmbientMusicModechanged");
    }

    public void onAppUseLimitStateChanged(int i) {
        Log.v(TAG, "onAppUseLimitStateChanged");
    }

    public void onAutoExternalRearMirrorFoldChanged(int i) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldChanged");
    }

    public void onAutoExternalRearMirrorFollowUpStateChanged(int i) {
        Log.v(TAG, "onAutoExternalRearMirrorFollowUpStateChanged");
    }

    public void onAutoExternalRearMirrorFollowUpSwitchChanged(int i) {
        Log.v(TAG, "onAutoExternalRearMirrorFollowUpSwitchChanged");
    }

    public void onAutoLockChanged(int i) {
        Log.v(TAG, "onAutoLockChanged");
    }

    public void onAutoLockSwitchChanged(int i) {
        Log.v(TAG, "onAutoLockSwitchChanged");
    }

    public void onAutoLockTimeChanged(int i) {
        Log.v(TAG, "onAutoLockTimeChanged");
    }

    public void onAutoPowerOffStateChanged(int i) {
        Log.v(TAG, "onAutoPowerOffStateChanged");
    }

    public void onAutoPowerOnStateChanged(int i) {
        Log.v(TAG, "onAutoPowerOnStateChanged");
    }

    public void onAutoRainWiperStateChanged(int i) {
        Log.v(TAG, "onAutoRainWiperStateChanged");
    }

    public void onAutoReportContentChanged(int i, int i2) {
        Log.v(TAG, "onAutoReportContentChanged");
    }

    public void onAutomaticParkingSwitchChanged(int i) {
        Log.v(TAG, "onAutomaticParkingSwitchChanged");
    }

    public void onBSISCollisionStateChanged(int i) {
        Log.v(TAG, "onBSISCollisionStateChanged");
    }

    public void onBSISFuncStatusChanged(int i) {
        Log.v(TAG, "onBSISFuncStatusChanged");
    }

    public void onBTCallReductionWindMenuStateChanged(int i) {
        Log.v(TAG, "onBTCallReductionWindMenuStateChanged");
    }

    public void onBackDoorElectricModeChanged(int i) {
        Log.v(TAG, "onBackDoorElectricModeChanged");
    }

    public void onBackDoorOpenedHeightChanged(int i) {
        Log.v(TAG, "onBackDoorOpenedHeightChanged");
    }

    public void onBackHomeLightDelaySwitchChanged(int i) {
        Log.v(TAG, "onBackHomeLightDelaySwitchChanged");
    }

    public void onBackHomeLightDelayValueChanged(int i) {
        Log.v(TAG, "onBackHomeLightDelayValueChanged");
    }

    public void onBackRowControlSwitchChanged(int i) {
        Log.v(TAG, "onBackRowControlSwitchChanged");
    }

    public void onBasePadAutoRiseStateChanged(int i) {
        Log.v(TAG, "onBasePadAutoRiseStateChanged");
    }

    public void onBleOpenDriverDoorStateChanged(int i) {
        Log.v(TAG, "onBleOpenDriverDoorStateChanged");
    }

    public void onBrakeCloseDoorChanged(int i) {
        Log.v(TAG, "onBrakeCloseDoorChanged");
    }

    public void onBrakePedalModeChanged(int i) {
        Log.v(TAG, "onBrakePedalModeChanged");
    }

    public void onCVCModeChanged(int i) {
        Log.v(TAG, "onCVCModeChanged");
    }

    public void onCampingBlanceActionChanged(int i) {
        Log.v(TAG, "onCampingBlanceActionChanged");
    }

    public void onCampingBlanceSupportedChanged(int i) {
        Log.v(TAG, "onCampingBlanceSupportedChanged");
    }

    public void onCampintBlacneStateChanged(int i) {
        Log.v(TAG, "onCampintBlacneStateChanged");
    }

    public void onCancelRecoverParms(int i, int i2, int i3) {
        Log.v(TAG, "onCancelRecoverParms");
    }

    public void onCanopyAutomaticDimming(int i) {
        Log.v(TAG, "onCanopyAutomaticDimming");
    }

    public void onCarRecorderRecordingChanged(int i) {
        Log.v(TAG, "onCarRecorderRecordingChanged");
    }

    public void onCarTipsSoundTypeChanged(int i) {
        Log.v(TAG, "onCarTipsSoundTypeChanged");
    }

    public void onCarWarnINDSwitchChanged(int i) {
        Log.v(TAG, "onCarWarnINDSwitchChanged");
    }

    public void onChargingPortSwitchChanged(int i) {
        Log.v(TAG, "onChargingPortSwitchChanged");
    }

    public void onCloseDoorLevelChanged(int i) {
        Log.v(TAG, "onCloseDoorLevelChanged");
    }

    public void onComSpeedDownStateChanged(int i) {
        Log.v(TAG, "onComSpeedDownStateChanged");
    }

    public void onComSpeedUpStateChanged(int i) {
        Log.v(TAG, "onComSpeedUpStateChanged");
    }

    public void onComfortableParkingStateChanged(int i) {
        Log.v(TAG, "onComfortableParkingStateChanged");
    }

    public void onControlCallbackStateChanged(int i) {
        Log.v(TAG, "onControlCallbackStateChanged");
    }

    public void onControlWindowSwitchChanged(int i) {
        Log.v(TAG, "onControlWindowSwitchChanged");
    }

    public void onCourtesyLampTimeChanged(int i) {
        Log.v(TAG, "onCourtesyLampTimeChanged");
    }

    public void onCpdImsSwitchStateChanged(int i) {
        Log.v(TAG, "onCpdImsSwitchStateChanged");
    }

    public void onCruiseBulletBoxChanged(int i) {
        Log.v(TAG, "onCruiseBulletBoxChanged");
    }

    public void onCuraSwitchStateChanged(int i) {
        Log.v(TAG, "onCuraSwitchStateChanged state = " + i);
    }

    public void onCurrentModeOfTailStateChanged(int i) {
        Log.v(TAG, "onCurrentModeOfTailStateChanged");
    }

    public void onCurrentStateOfTheTailStateChanged(int i) {
        Log.v(TAG, "onCurrentStateOfTheTailStateChanged");
    }

    public void onCurrentStatusOfDimmingSunroof(int i) {
        Log.v(TAG, "onCurrentStatusOfDimmingSunroof");
    }

    public void onCurrentTransmittanceOfSkylight(int i) {
        Log.v(TAG, "onCurrentTransmittanceOfSkylight");
    }

    public void onDampStateChanged(int i) {
        Log.v(TAG, "onDampStateChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.v(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.v(TAG, "onDataEventChanged");
    }

    public void onDelayDownPowerTimeChanged(int i) {
        Log.v(TAG, "onDelayDownPowerTimeChanged");
    }

    public void onDelayPowerDownTimeChanged(int i) {
        Log.v(TAG, "onDelayPowerDownTimeChanged");
    }

    public void onDelayPowerOffStateChanged(int i) {
        Log.v(TAG, "onDelayPowerOffStateChanged");
    }

    public void onDelayPowerOffStatusChanged(int i) {
        Log.v(TAG, "onDelayPowerOffStatusChanged");
    }

    public void onDelayPowerOffTimeChanged(int i) {
        Log.v(TAG, "onDelayPowerOffTimeChanged");
    }

    public void onDimmingCanopyLowCode(int i) {
        Log.v(TAG, "onDimmingCanopyLowCode");
    }

    public void onDisinfStateChanged(int i) {
        Log.v(TAG, "onDisinfStateChanged");
    }

    public void onDlgMessageCodeChanged(int i) {
        Log.v(TAG, "onDlgMessageCodeChanged");
    }

    public void onDmPlatformChanged(int i) {
        Log.v(TAG, "onDmPlatformChanged");
    }

    public void onDoorLockChanged(int i) {
        Log.v(TAG, "onDoorLockChanged");
    }

    public void onDoorknobAutoShrinkStateChanged(int i) {
        Log.v(TAG, "onDoorknobAutoShrinkStateChanged");
    }

    public void onDragAndDropModeTipsChanged(int i) {
        Log.v(TAG, "onDragAndDropModeTipsChanged");
    }

    public void onDragModeForwardAndBackwardChanged(int i) {
        Log.v(TAG, "onDragModeForwardAndBackwardChanged");
    }

    public void onDriveAutoLockWindowStateChanged(int i) {
        Log.v(TAG, "onDriveAutoLockWindowStateChanged");
    }

    public void onDriveConfigTypeChanged(int i) {
        Log.v(TAG, "onDriveConfigTypeChanged");
    }

    public void onDriveDoorCloseIndSwitchChanged(int i) {
        Log.v(TAG, "onDriveDoorCloseIndSwitchChanged");
    }

    public void onDriveModeChanged(int i) {
        Log.v(TAG, "onDriveModeChanged mode = " + i);
    }

    public void onDriverSeatAutoReturnB3Changded(int i) {
        Log.v(TAG, "onDriverSeatAutoReturnB3Changded");
    }

    public void onDriverSeatAutoReturnStateChanged(int i) {
        Log.v(TAG, "onDriverSeatAutoReturnStateChanged");
    }

    public void onDriverSeatAutoReturnSwitchChanged(int i) {
        Log.v(TAG, "onDriverSeatAutoReturnSwitchChanged");
    }

    public void onDriverSeatBackChanged(int i) {
        Log.v(TAG, "onDriverSeatBackChanged");
    }

    public void onDriverSeatHeating437StateChanged(int i) {
        Log.v(TAG, "onDriverSeatHeating437StateChanged");
    }

    public void onDriverSeatVentilating437StateChanged(int i) {
        Log.v(TAG, "onDriverSeatVentilating437StateChanged");
    }

    public void onDrivingRecorderChanged(int i) {
        Log.v(TAG, "onDrivingRecorderChanged");
    }

    public void onDrivingRecorderSwitchChanged(int i) {
        Log.v(TAG, "onDrivingRecorderSwitchChanged");
    }

    public void onDyHeightLevelChanged(int i) {
        Log.v(TAG, "onDyHeightLevelChanged");
    }

    public void onDyHeightToggleStateChanged(int i) {
        Log.v(TAG, "onDyHeightToggleStateChanged");
    }

    public void onDyHeightTurnActionChanged(int i) {
        Log.v(TAG, "onDyHeightTurnActionChanged");
    }

    public void onDynamicSuspensionModeChanged(int i) {
        Log.v(TAG, "onDynamicSuspensionModeChanged");
    }

    public void onEPedalModeChanged(int i) {
        Log.v(TAG, "onEPedalModeChanged");
    }

    public void onElecHandbrakeStateChanged(int i) {
        Log.v(TAG, "onElecHandbrakeStateChanged");
    }

    public void onElectricPedalStateChanged(int i, int i2) {
        Log.v(TAG, "onElectricPedalStateChanged");
    }

    public void onElectricTailMaintenanceFuntatusStateChanged(int i) {
        Log.v(TAG, "onElectricTailMaintenanceFuntatusStateChanged");
    }

    public void onEnergyFeedbackStrengthChanged(int i) {
        Log.v(TAG, "onEnergyFeedbackStrengthChanged");
    }

    public void onEngineOilExitUpdateStateChanged(int i) {
        Log.v(TAG, "onEngineOilExitUpdateStateChanged");
    }

    public void onEngineOilLevelChanged(int i) {
        Log.v(TAG, "onEngineOilLevelChanged");
    }

    public void onEngineOilUpdateSignalChanged(int i) {
        Log.v(TAG, "onEngineOilUpdateSignalChanged");
    }

    public void onEngineSoundTypeChanged(int i) {
        Log.v(TAG, "onEngineSoundTypeChanged");
    }

    public void onError(int i, String str) {
        Log.v(TAG, "onError");
    }

    public void onExhibitionModeChanged(int i) {
        Log.v(TAG, "onExhibitionModeChanged");
    }

    public void onExhibitionStartEnginePromptChanged(int i) {
        Log.v(TAG, "onExhibitionStartEnginePromptChanged");
    }

    public void onFdCRCCheckNum(int i) {
        Log.v(TAG, "onFdCRCCheckNum");
    }

    public void onFdCampingBlanceActionChanged(int i) {
        Log.v(TAG, "onFdCampingBlanceActionChanged");
    }

    public void onFdCampingBlanceSupportedChanged(int i) {
        Log.v(TAG, "onFdCampingBlanceSupportedChanged");
    }

    public void onFdCampintBlacneStateChanged(int i) {
        Log.v(TAG, "onFdCampintBlacneStateChanged");
    }

    public void onFdComSpeedUpStateChanged(int i) {
        Log.v(TAG, "onFdComSpeedUpStateChanged");
    }

    public void onFdDampStateChanged(int i) {
        Log.v(TAG, "onFdDampStateChanged");
    }

    public void onFdDyHeightLevelChanged(int i) {
        Log.v(TAG, "onFdDyHeightLevelChanged");
    }

    public void onFdDyHeightToggleStateChanged(int i) {
        Log.v(TAG, "onFdDyHeightToggleStateChanged");
    }

    public void onFdDyHeightTurnActionChanged(int i) {
        Log.v(TAG, "onFdDyHeightTurnActionChanged");
    }

    public void onFdDynamicSuspensionMode(int i) {
        Log.v(TAG, "onFdDynamicSuspensionMode");
    }

    public void onFdFixedStateChanged(int i) {
        Log.v(TAG, "onFdFixedStateChanged");
    }

    public void onFdFrontBoxTakeStateChanged(int i) {
        Log.v(TAG, "onFdFrontBoxTakeStateChanged");
    }

    public void onFdLiftModeStateChanged(int i) {
        Log.v(TAG, "onFdLiftModeStateChanged");
    }

    public void onFdModeSupportedHturnChanged(int i) {
        Log.v(TAG, "onFdModeSupportedHturnChanged");
    }

    public void onFdSpeedSupportedTurn(int i) {
        Log.v(TAG, "onFdSpeedSupportedTurn");
    }

    public void onFdTailBoxTakeStateChanged(int i) {
        Log.v(TAG, "onFdTailBoxTakeStateChanged");
    }

    public void onFdTailBoxTakeSupportedChanged(int i) {
        Log.v(TAG, "onFdTailBoxTakeSupportedChanged");
    }

    public void onFdTrailerModeCodeChanged(int i) {
        Log.v(TAG, "onFdTrailerModeCodeChanged");
    }

    public void onFdWelcomeActiveChanged(int i) {
        Log.v(TAG, "onFdWelcomeActiveChanged");
    }

    public void onFdWelcomeStateChanged(int i) {
        Log.v(TAG, "onFdWelcomeStateChanged");
    }

    public void onFdWelcomeSupportedChanged(int i) {
        Log.v(TAG, "onFdWelcomeSupportedChanged");
    }

    public void onFixedStateChanged(int i) {
        Log.v(TAG, "onFixedStateChanged");
    }

    public void onFridgeChildLockChanged(int i) {
        Log.v(TAG, "onFridgeChildLockChanged");
    }

    public void onFridgeDoorStateChanged(int i) {
        Log.v(TAG, "onFridgeDoorStateChanged");
    }

    public void onFridgeLocationChanged(int i) {
        Log.v(TAG, "onFridgeLocationChanged");
    }

    public void onFridgeWorkColdTemperatureChanged(int i) {
        Log.v(TAG, "onFridgeWorkColdTemperatureChanged");
    }

    public void onFridgeWorkHeatTemperatureChanged(int i) {
        Log.v(TAG, "onFridgeWorkHeatTemperatureChanged");
    }

    public void onFridgeWorkSaveTemperatureChanged(int i) {
        Log.v(TAG, "onFridgeWorkSaveTemperatureChanged");
    }

    public void onFrontWiperSpeedValueChanged(int i) {
        Log.v(TAG, "onFrontWiperSpeedValueChanged");
    }

    public void onGoalRoadModeChanged(int i) {
        Log.v(TAG, "onGoalRoadModeChanged state = " + i);
    }

    public void onHSpeedAutoCloseStateChanged(int i) {
        Log.v(TAG, "onHSpeedAutoCloseStateChanged");
    }

    public void onHandleAutoResetTimeChanged(int i) {
        Log.v(TAG, "onHandleAutoResetTimeChanged");
    }

    public void onHeadlampLevelChanged(int i) {
        Log.v(TAG, "onHeadlampLevelChanged");
    }

    public void onHeadrestHorizationChanged(int i, float f, int i2, int i3) {
        Log.v(TAG, "onHeadrestHorizationChanged");
    }

    public void onHeadrestTurnHeightChanged(int i, int i2, float f) {
        Log.v(TAG, "onHeadrestTurnHeightChanged");
    }

    public void onHeadrestTurnHorizationChanged(int i, int i2, float f) {
        Log.v(TAG, "onHeadrestTurnHorizationChanged");
    }

    public void onHevChargingPortSwitchChanged(int i) {
        Log.v(TAG, "onHevChargingPortSwitchChanged");
    }

    public void onHomeLightTimeChanged(int i) {
        Log.v(TAG, "onHomeLightTimeChanged");
    }

    public void onHsMassageStateChanged(int i, int i2) {
        Log.v(TAG, "onHsMassageStateChanged");
    }

    public void onHudBrightnessChanged(int i) {
        Log.v(TAG, "onHudBrightnessChanged");
    }

    public void onHudDisplayContentChanged(int i, int i2) {
        Log.v(TAG, "onHudDisplayContentChanged");
    }

    public void onHudHeightChanged(int i) {
        Log.v(TAG, "onHudHeightChanged");
    }

    public void onHudModeChanged(int i) {
        Log.v(TAG, "onHudModeChanged");
    }

    public void onHudNavigationStateChanged(int i) {
        Log.v(TAG, "onHudNavigationStateChanged");
    }

    public void onHudParamsEffect(int i) {
        Log.v(TAG, "onHudParamsEffect");
    }

    public void onHudPatternChanged(int i) {
        Log.v(TAG, "onHudPatternChanged");
    }

    public void onHudRestartAndEffect(int i) {
        Log.v(TAG, "onHudRestartAndEffect");
    }

    public void onHudRotationAngleChanged(int i) {
        Log.v(TAG, "onHudRotationAngleChanged");
    }

    public void onHudSPStateChanged(int i) {
        Log.v(TAG, "onHudSPStateChanged");
    }

    public void onHudSafeDriveStateChanged(int i) {
        Log.v(TAG, "onHudSafeDriveStateChanged");
    }

    public void onHudStateChanged(int i) {
        Log.v(TAG, "onHudStateChanged");
    }

    public void onIALAreaChanged(int i) {
        Log.v(TAG, "onIALAreaChanged");
    }

    public void onIALBrightnessChanged(int i, int i2) {
        Log.v(TAG, "onIALBrightnessChanged");
    }

    public void onIALColorChanged(int i, int i2) {
        Log.v(TAG, "onIALColorChanged");
    }

    public void onIKEYBTLowPowerModeChanged(int i) {
        Log.v(TAG, "onIKEYBTLowPowerModeChanged");
    }

    public void onILDurationChanged(int i) {
        Log.v(TAG, "onILDurationChanged");
    }

    public void onINSThemeChanged(int i) {
        Log.v(TAG, "onINSThemeChanged");
    }

    public void onIVIProfileSettingsChanged(int i) {
        Log.v(TAG, "onIVIProfileSettingsChanged");
    }

    public void onInsideLightDoorStateChanged(int i) {
        Log.v(TAG, "onInsideLightDoorStateChanged");
    }

    public void onInsideRearMirrorScreenSwitchChanged(int i) {
        Log.v(TAG, "onInsideRearMirrorScreenSwitchChanged");
    }

    public void onInstrumentAcKeyTypeChanged(int i) {
        Log.v(TAG, "onInstrumentAcKeyTypeChanged");
    }

    public void onIntelLockOpenDriverDoorChanged(int i) {
        Log.v(TAG, "onIntelLockOpenDriverDoorChanged");
    }

    public void onIntelligentAcAreaStateChanged(int i) {
        Log.v(TAG, "onIntelligentAcAreaStateChanged");
    }

    public void onIntelligentLockStateChanged(int i) {
        Log.v(TAG, "onIntelligentLockStateChanged");
    }

    public void onIntelligentUnlockStateChanged(int i) {
        Log.v(TAG, "onIntelligentUnlockStateChanged");
    }

    public void onIntelligentVoiceINDLevelChanged(int i) {
        Log.v(TAG, "onIntelligentVoiceINDLevelChanged");
    }

    public void onIntelligentVoiceINDSwitchChanged(int i) {
        Log.v(TAG, "onIntelligentVoiceINDSwitchChanged");
    }

    public void onKeyPowerLowIndSwitchChanged(int i) {
        Log.v(TAG, "onKeyPowerLowIndSwitchChanged");
    }

    public void onKeyVibratorLevelChanged(int i) {
        Log.v(TAG, "onKeyVibratorLevelChanged");
    }

    public void onLPSwitchDownwindowChanged(int i) {
        Log.v(TAG, "onLPSwitchDownwindowChanged");
    }

    public void onLPSwitchUpwindowChanged(int i) {
        Log.v(TAG, "onLPSwitchUpwindowChanged");
    }

    public void onLRSEChildModeCodeWriteFb(int i) {
        Log.v(TAG, "onLRSEChildModeCodeWriteFb");
    }

    public void onLRSEChildModeTimeWriteFb(int i) {
        Log.v(TAG, "onLRSEChildModeTimeWriteFb");
    }

    public void onLRSEMachineSwitchStateChanged(int i) {
        Log.v(TAG, "onLRSEMachineSwitchStateChanged");
    }

    public void onLandAdvanceAtSateChanged(int i) {
        Log.v(TAG, "onLandAdvanceAtSateChanged");
    }

    public void onLanguageChanged(int i) {
        Log.v(TAG, "onLanguageChanged");
    }

    public void onLanternLanguageModeChanged(int i) {
        Log.v(TAG, "onLanternLanguageModeChanged");
    }

    public void onLearnerDriverAccLimitStateChanged(int i) {
        Log.v(TAG, "onLearnerDriverAccLimitStateChanged");
    }

    public void onLearnerDriverModeStateChanged(int i) {
        Log.v(TAG, "onLearnerDriverModeStateChanged");
    }

    public void onLearnerDriverModeSwitchStateChanged(int i) {
        Log.v(TAG, "onLearnerDriverModeSwitchStateChanged");
    }

    public void onLeaveCarPIndSwitchChanged(int i) {
        Log.v(TAG, "onLeaveCarPIndSwitchChanged");
    }

    public void onLeaveHomeLightTimeChanged(int i) {
        Log.v(TAG, "onLeaveHomeLightTimeChanged");
    }

    public void onLeftFieldPlatformChanged(int i) {
        Log.v(TAG, "onLeftFieldPlatformChanged");
    }

    public void onLeftHeadlampLevelChanged(int i) {
        Log.v(TAG, "onLeftHeadlampLevelChanged");
    }

    public void onLeftHomeLightDelaySwitchChanged(int i) {
        Log.v(TAG, "onLeftHomeLightDelaySwitchChanged");
    }

    public void onLeftHomeLightDelayValueChanged(int i) {
        Log.v(TAG, "onLeftHomeLightDelayValueChanged");
    }

    public void onLeftViewMirrorFlipAngleChanged(int i) {
        Log.v(TAG, "onLeftViewMirrorFlipAngleChanged");
    }

    public void onLiftModeStateChanged(int i) {
        Log.v(TAG, "onLiftModeStateChanged");
    }

    public void onLockCarRiseWindowChanged(int i) {
        Log.v(TAG, "onLockCarRiseWindowChanged");
    }

    public void onLockCarRiseWindowStateChange(int i) {
        Log.v(TAG, "onLockCarRiseWindowStateChange");
    }

    public void onLockOffChanged(int i) {
        Log.v(TAG, "onLockOffChanged");
    }

    public void onLockOffDoorChanged(int i) {
        Log.v(TAG, "onLockOffDoorChanged");
    }

    public void onLockUpwindowChanged(int i) {
        Log.v(TAG, "onLockUpwindowChanged");
    }

    public void onLongPressUnlockWindowSwitchChanged(int i) {
        Log.v(TAG, "onLongPressUnlockWindowSwitchChanged");
    }

    public void onLowBatteryIndSwitchChanged(int i) {
        Log.v(TAG, "onLowBatteryIndSwitchChanged");
    }

    public void onLowOilIndSwitchChanged(int i) {
        Log.v(TAG, "onLowOilIndSwitchChanged");
    }

    public void onMOISAssembleStateChanged(int i) {
        Log.v(TAG, "onMOISAssembleStateChanged");
    }

    public void onMOISFuncStatusChanged(int i) {
        Log.v(TAG, "onMOISFuncStatusChanged");
    }

    public void onMaintainRemindStateChanged(int i) {
        Log.v(TAG, "onMaintainRemindStateChanged");
    }

    public void onMassageLevelStateChanged(int i, int i2) {
        Log.v(TAG, "onMassageLevelStateChanged");
    }

    public void onMassageModeStateChanged(int i, int i2) {
        Log.v(TAG, "onMassageModeStateChanged");
    }

    public void onMcuRestartFlagChanged(int i) {
        Log.v(TAG, "onMcuRestartFlagChanged");
    }

    public void onMemorySetOperationFlagChanged(int i) {
        Log.v(TAG, "onMemorySetOperationFlagChanged flag = " + i);
    }

    public void onMicroSwitchLockWindowStateChanged(int i) {
        Log.v(TAG, "onMicroSwitchLockWindowStateChanged");
    }

    public void onMicroSwitchUnlockWindowStateChanged(int i) {
        Log.v(TAG, "onMicroSwitchUnlockWindowStateChanged");
    }

    public void onMissKeyIndSwitchChanged(int i) {
        Log.v(TAG, "onMissKeyIndSwitchChanged");
    }

    public void onMissPackageCountChanged(int i) {
        Log.v(TAG, "onMissPackageCountChanged");
    }

    public void onModeSupportedHturnChanged(int i) {
        Log.v(TAG, "onModeSupportedHturnChanged（int");
    }

    public void onMotorPretensionerSeatbeltStateChanged(int i) {
        Log.v(TAG, "onMotorPretensionerSeatbeltStateChanged");
    }

    public void onMultimediaMotorTemperatureStateChanged(int i) {
        Log.v(TAG, "onMultimediaMotorTemperatureStateChanged");
    }

    public void onNavigationMediaStateChanged(int i) {
        Log.v(TAG, "onNavigationMediaStateChanged");
    }

    public void onNfcOpenDriverDoorChanged(int i) {
        Log.v(TAG, "onNfcOpenDriverDoorChanged");
    }

    public void onNightDRLightOnIndSwitchChanged(int i) {
        Log.v(TAG, "onNightDRLightOnIndSwitchChanged");
    }

    public void onNightDriveLightOnReminderChanged(int i) {
        Log.v(TAG, "onNightDriveLightOnReminderChanged");
    }

    public void onNightSystemModeSwitchChanged(int i) {
        Log.v(TAG, "onNightSystemModeSwitchChanged");
    }

    public void onOffReminderChanged(int i) {
        Log.v(TAG, "onOffReminderChanged");
    }

    public void onOverSpeedLockStateChanged(int i) {
        Log.v(TAG, "onOverSpeedLockStateChanged");
    }

    public void onOverspeedLockStateChanged(int i) {
        Log.v(TAG, "onOverspeedLockStateChanged");
    }

    public void onPABStateChanged(int i) {
        Log.v(TAG, "onPABStateChanged");
    }

    public void onPABStateCodeChanged(int i) {
        Log.v(TAG, "onPABStateCodeChanged");
    }

    public void onPM25PowerSwitchChanged(int i) {
        Log.v(TAG, "onPM25PowerSwitchChanged");
    }

    public void onPM25SwitchChanged(int i) {
        Log.v(TAG, "onPM25SwitchChanged");
    }

    public void onPM25SwitchCheckChanged(int i) {
        Log.v(TAG, "onPM25SwitchCheckChanged");
    }

    public void onPM25TimeCheckChanged(int i) {
        Log.v(TAG, "onPM25TimeCheckChanged");
    }

    public void onParkBrakeIndSwitchChanged(int i) {
        Log.v(TAG, "onParkBrakeIndSwitchChanged");
    }

    public void onPassengerSeatHeating3CAStateChanged(int i) {
        Log.v(TAG, "onPassengerSeatHeating3CAStateChanged");
    }

    public void onPassengerSeatVentilating3CAStateChanged(int i) {
        Log.v(TAG, "onPassengerSeatVentilating3CAStateChanged");
    }

    public void onPedalSuppressionSystemStateChanged(int i) {
        Log.v(TAG, "onPedalSuppressionSystemStateChanged state = " + i);
    }

    public void onPedestrianRecognizeSwitchChanged(int i) {
        Log.v(TAG, "onPedestrianRecognizeSwitchChanged");
    }

    public void onPhoneRemindCodeChanged(int i) {
        Log.v(TAG, "onPhoneRemindCodeChanged");
    }

    public void onRRSEChildModeCodeWriteFb(int i) {
        Log.v(TAG, "onRRSEChildModeCodeWriteFb");
    }

    public void onRRSEChildModeTimeWriteFb(int i) {
        Log.v(TAG, "onRRSEChildModeTimeWriteFb");
    }

    public void onRRSEMachineSwitchStateChanged(int i) {
        Log.v(TAG, "onRRSEMachineSwitchStateChanged");
    }

    public void onRainAutoLockWindowStateChanged(int i) {
        Log.v(TAG, "onRainAutoLockWindowStateChanged");
    }

    public void onRearAcOnlineStateChanged(int i) {
        Log.v(TAG, "onRearAcOnlineStateChanged");
    }

    public void onRearAcPanelAutoLockTimeChanged(int i) {
        Log.v(TAG, "onRearAcPanelAutoLockTimeChanged");
    }

    public void onRearDoorStateChanged(int i) {
        Log.v(TAG, "onRearDoorStateChanged");
    }

    public void onRearHeatTempChanged(int i) {
        Log.v(TAG, "onRearHeatTempChanged");
    }

    public void onRearMirrorFlipChanged(int i) {
        Log.v(TAG, "onRearMirrorFlipChanged");
    }

    public void onRearPanelLockTimeModeChange(int i) {
        Log.v(TAG, "onRearPanelLockTimeModeChange");
    }

    public void onRearPanelLockTimeModeChanged(int i) {
        Log.v(TAG, "onRearPanelLockTimeModeChanged");
    }

    public void onRearRightSeatGreetStateChanged(int i) {
        Log.v(TAG, "onRearRightSeatGreetStateChanged state = " + i);
    }

    public void onRearSaveTempChanged(int i) {
        Log.v(TAG, "onRearSaveTempChanged");
    }

    public void onRearSeatGreetStateChanged(int i) {
        Log.v(TAG, "onRearSeatGreetStateChanged");
    }

    public void onRearViewEcuMirrorFlipStateChanged(int i) {
        Log.v(TAG, "onRearViewEcuMirrorFlipStateChanged");
    }

    public void onRearViewMirrorAngleChanged(int i) {
        Log.v(TAG, "onRearViewMirrorAngleChanged");
    }

    public void onRearViewMirrorAutoFoldModeChanged(int i) {
        Log.v(TAG, "onRearViewMirrorAutoFoldModeChanged");
    }

    public void onRearViewMirrorFlipChanged(int i) {
        Log.v(TAG, "onRearViewMirrorFlipChanged");
    }

    public void onRearViewMirrorFlipSetChanged(int i) {
        Log.v(TAG, "onRearViewMirrorFlipSetChanged");
    }

    public void onRearViewMirrorFlipSwitchChanged(int i) {
        Log.v(TAG, "onRearViewMirrorFlipSwitchChanged");
    }

    public void onRearWiperSpeedValueChanged(int i) {
        Log.v(TAG, "onRearWiperSpeedValueChanged");
    }

    public void onRearviewMirrorIsAutoLightStateChanged(int i) {
        Log.v(TAG, "onRearviewMirrorIsAutoLightStateChanged");
    }

    public void onRearviewMirrorIsStateChanged(int i) {
        Log.v(TAG, "onRearviewMirrorIsStateChanged");
    }

    public void onRearviewMirrorIsTurnedStateChanged(int i) {
        Log.v(TAG, "onRearviewMirrorIsTurnedStateChanged");
    }

    public void onRecoverCaliperSplintHeight(int i, int i2, int i3) {
        Log.v(TAG, "onRecoverCaliperSplintHeight");
    }

    public void onRecoverCushionHeight(int i, int i2, int i3) {
        Log.v(TAG, "onRecoverCushionHeight");
    }

    public void onRecoverOrSaveParamsChanged(int i, int i2, int i3, int i4) {
        Log.v(TAG, "onRecoverOrSaveParamsChanged position:" + i + " location:" + i2 + " state:" + i3 + " code:" + i4);
    }

    public void onRecoverRearviewMirrorParams(int i, int i2, int i3) {
        Log.v(TAG, "onRecoverRearviewMirrorParams");
    }

    public void onRecoverSeatParams(int i, int i2, int i3) {
        Log.v(TAG, "onRecoverSeatParams");
    }

    public void onRecoverSteeringWheelPosition(int i, int i2) {
        Log.v(TAG, "onRecoverSteeringWheelPosition");
    }

    public void onRefrigWorkModeChanged(int i) {
        Log.v(TAG, "onRefrigWorkModeChanged mode = " + i);
    }

    public void onRefrigeratorMobileTerminalPushReChanged(int i) {
        Log.v(TAG, "onRefrigeratorMobileTerminalPushReChanged code = " + i);
    }

    public void onRefrigeratorModeChanged(int i) {
        Log.v(TAG, "onRefrigeratorModeChanged");
    }

    public void onRefrigeratorState(int i) {
        Log.v(TAG, "onRefrigeratorState");
    }

    public void onRefrigeratorTemperatureChanged(int i) {
        Log.v(TAG, "onRefrigeratorTemperatureChanged");
    }

    public void onRefrigeratorWorkModeChanged(int i) {
        Log.v(TAG, "onRefrigeratorWorkModeChanged");
    }

    public void onRefrigeratorWorkStateChanged(int i) {
        Log.v(TAG, "onRefrigeratorWorkStateChanged");
    }

    public void onRefrigeratorWorkTemperatureChanged(int i) {
        Log.v(TAG, "onRefrigeratorWorkTemperatureChanged");
    }

    public void onRemberPowerOffStateChanged(int i) {
        Log.v(TAG, "onRemberPowerOffStateChanged");
    }

    public void onRemenberPowerOffStateChanged(int i) {
        Log.v(TAG, "onRemenberPowerOffStateChanged");
    }

    public void onRemoteControlDownwindowChanged(int i) {
        Log.v(TAG, "onRemoteControlDownwindowChanged");
    }

    public void onRemoteControlSwitchChanged(int i) {
        Log.v(TAG, "onRemoteControlSwitchChanged");
    }

    public void onRemoteControlUnlockStateChanged(int i) {
        Log.v(TAG, "onRemoteControlUnlockStateChanged");
    }

    public void onRemoteControlUpwindowChanged(int i) {
        Log.v(TAG, "onRemoteControlUpwindowChanged");
    }

    public void onRemoteControlUpwindowStateChanged(int i) {
        Log.v(TAG, "onRemoteControlUpwindowStateChanged");
    }

    public void onRemoteCtlUnlockingStateChanged(int i) {
        Log.v(TAG, "onRemoteCtlUnlockingStateChanged");
    }

    public void onRemoteLockWindowStateChanged(int i) {
        Log.v(TAG, "onRemoteLockWindowStateChanged");
    }

    public void onRemoteUnlockWindowStateChanged(int i) {
        Log.v(TAG, "onRemoteUnlockWindowStateChanged");
    }

    public void onReportVolumeChanged(int i) {
        Log.v(TAG, "onReportVolumeChanged");
    }

    public void onResetAutoExternalRearMirrorFollowUp(int i) {
        Log.v(TAG, "onResetAutoExternalRearMirrorFollowUp");
    }

    public void onResetAutoPowerOnState(int i) {
        Log.v(TAG, "onResetAutoPowerOnState");
    }

    public void onResetHudBrightness(int i) {
        Log.v(TAG, "onResetHudBrightness");
    }

    public void onResetHudDisplayContent(int i) {
        Log.v(TAG, "onResetHudDisplayContent");
    }

    public void onResetHudHeight(int i) {
        Log.v(TAG, "onResetHudHeight");
    }

    public void onResetHudMode(int i) {
        Log.v(TAG, "onResetHudMode");
    }

    public void onResetHudRotationAngle(int i) {
        Log.v(TAG, "onResetHudRotationAngle");
    }

    public void onResetMotorPretensionerSeatbelt(int i) {
        Log.v(TAG, "onResetMotorPretensionerSeatbelt");
    }

    public void onRightHeadlampLevelChanged(int i) {
        Log.v(TAG, "onRightHeadlampLevelChanged");
    }

    public void onRightViewMirrorFlipAngleChanged(int i) {
        Log.v(TAG, "onRightViewMirrorFlipAngleChanged");
    }

    public void onRncStateChanged(int i) {
        Log.v(TAG, "onRncStateChanged");
    }

    public void onRuningCloseDoorStateChanged(int i) {
        Log.v(TAG, "onRuningCloseDoorStateChanged");
    }

    public void onSOCConfigChanged(int i) {
        Log.v(TAG, "onSOCConfigChanged");
    }

    public void onSOCTargetLowLimitChanged(int i) {
        Log.v(TAG, "onSOCTargetLowLimitChanged");
    }

    public void onSOCTargetRangeChange(int i) {
        Log.v(TAG, "onSOCTargetRangeChange");
    }

    public void onSOCTargetRangeChanged(int i) {
        Log.v(TAG, "onSOCTargetRangeChanged");
    }

    public void onSafeBeltCloseDriveDoorStateChanged(int i) {
        Log.v(TAG, "onSafeBeltCloseDriveDoorStateChanged");
    }

    public void onSafeBeltIndSwitchChanged(int i) {
        Log.v(TAG, "onSafeBeltIndSwitchChanged");
    }

    public void onSafeBeltWarnStateChanged(int i) {
        Log.v(TAG, "onSafeBeltWarnStateChanged  state=" + i);
    }

    public void onSafeWarnStateChanged(int i) {
        Log.v(TAG, "onSafeWarnStateChanged");
    }

    public void onSandAbsModeStateChanged(int i) {
        Log.v(TAG, "onSandAbsModeStateChanged state = " + i);
    }

    public void onSandAbsTipMessageChanged(int i) {
        Log.v(TAG, "onSandAbsTipMessageChanged state = " + i);
    }

    public void onSaveOwnerStateChanged(int i) {
        Log.v(TAG, "onSaveOwnerStateChanged sate = " + i);
    }

    public void onSaveSeatParams(int i, int i2, float f, float f2, float f3, int i3) {
        Log.v(TAG, "onSaveSeatParams");
    }

    @Deprecated
    public void onSaveSeatParamsAllChanged(int i, int i2, int i3) {
        Log.v(TAG, "onSaveSeatParamsAllChanged");
    }

    public void onScreenAngleChanged(int i) {
        Log.v(TAG, "onScreenAngleChanged");
    }

    public void onScreenAngleValueChanged(int i) {
        Log.v(TAG, "onScreenAngleValueChanged");
    }

    public void onScreenAntiPinchStateChanged(int i) {
        Log.v(TAG, "onScreenAntiPinchStateChanged");
    }

    public void onScreenCallbackMessageChanged(int i) {
        Log.v(TAG, "onScreenCallbackMessageChanged");
    }

    public void onScreenRotationActionChanged(int i) {
        Log.v(TAG, "onScreenRotationActionChanged");
    }

    public void onScreenRotationOverHotStateChanged(int i) {
        Log.v(TAG, "onScreenRotationOverHotStateChanged");
    }

    public void onScreenRotationStatus(int i) {
        Log.v(TAG, "onScreenRotationStatus state = " + i);
    }

    public void onScreenVoltageStateChanged(int i) {
        Log.v(TAG, "onScreenVoltageStateChanged");
    }

    public void onSeaModeChargingSocTypeChanged(int i) {
        Log.v(TAG, "onSeaModeChargingSocTypeChanged value = " + i);
    }

    public void onSeatHeatingNotL1PStateChanged(int i) {
        Log.v(TAG, "onSeatHeatingNotL1PStateChanged");
    }

    public void onSeatHeatingState2Changed(int i, int i2) {
        Log.v(TAG, "onSeatHeatingState2Changed");
    }

    public void onSeatHeatingStateChanged(int i, int i2) {
        Log.v(TAG, "onSeatHeatingStateChanged");
    }

    public void onSeatHeatingStateChanged1(int i, int i2) {
        Log.v(TAG, "onSeatHeatingStateChanged1");
    }

    public void onSeatHorizationChanged(int i, float f, int i2, int i3) {
        Log.v(TAG, "onSeatHorizationChanged");
    }

    public void onSeatTurnHeight(int i, float f, int i2, int i3) {
        Log.v(TAG, "onSeatTurnHeight");
    }

    public void onSeatVentilatingNotL1PStateChanged(int i) {
        Log.v(TAG, "onSeatVentilatingNotL1PStateChanged");
    }

    public void onSeatVentilatingState2Changed(int i, int i2) {
        Log.v(TAG, "onSeatVentilatingState2Changed");
    }

    public void onSeatVentilatingStateChanged(int i, int i2) {
        Log.v(TAG, "onSeatVentilatingStateChanged");
    }

    public void onSeatbackrestPostion(int i, float f, int i2, int i3) {
        Log.v(TAG, "onSeatbackrestPostion");
    }

    public void onServiceControlCallbackChanged(int i) {
        Log.v(TAG, "onServiceControlCallbackChanged");
    }

    public void onSetHudParamsEffectChanged(int i) {
        Log.v(TAG, "onSetHudParamsEffectChanged");
    }

    public void onSkyCurtainPowerOffAndDim(int i) {
        Log.v(TAG, "onSkyCurtainPowerOffAndDim");
    }

    public void onSmartWelcomeLightStateChanged(int i) {
        Log.v(TAG, "onSmartWelcomeLightStateChanged");
    }

    public void onSomethingLeftInRefrigeratorNotice(int i) {
        Log.v(TAG, "onSomethingLeftInRefrigeratorNotice");
    }

    public void onSpeedReminderValueChanged(int i) {
        Log.v(TAG, "onSpeedReminderValueChanged");
    }

    public void onSpeedSupportedTurn(int i) {
        Log.v(TAG, "onSpeedSupportedTurn");
    }

    public void onStartKeyStateChanged(int i) {
        Log.v(TAG, "onStartKeyStateChanged");
    }

    public void onStartOrPowerIndSwitchChanged(int i) {
        Log.v(TAG, "onStartOrPowerIndSwitchChanged");
    }

    public void onSteerAssisModeChanged(int i) {
        Log.v(TAG, "onSteerAssisModeChanged");
    }

    public void onSteerAssisSupportChanged(int i) {
        Log.v(TAG, "onSteerAssisSupportChanged  state=" + i);
    }

    public void onSteerAssistPermissionChanged(int i) {
        Log.v(TAG, "onSteerAssistPermissionChanged");
    }

    public void onSteerBackChanged(int i) {
        Log.v(TAG, "onSteerBackChanged");
    }

    public void onSteerPositionAutoReturnStateChanged(int i) {
        Log.v(TAG, "onSteerPositionAutoReturnStateChanged");
    }

    public void onSteerPositionAutoReturnSwitchChanged(int i) {
        Log.v(TAG, "onSteerPositionAutoReturnSwitchChanged");
    }

    public void onSteeringWheelHeatingAutoStateChanged(int i) {
        Log.v(TAG, "onSteeringWheelHeatingAutoStateChanged");
    }

    public void onSteeringWheelHeatingGearChanged(int i) {
        Log.v(TAG, "onSteeringWheelHeatingGearChanged");
    }

    public void onSteeringWheelHeatingStateChanged(int i) {
        Log.v(TAG, "onSteeringWheelHeatingStateChanged");
    }

    public void onSteeringWheelYChanged(int i, int i2) {
        Log.v(TAG, "onSteeringWheelYChanged");
    }

    public void onSteeringWheelZChanged(int i, int i2) {
        Log.v(TAG, "onSteeringWheelZChanged");
    }

    public void onSterilizationStateChanged(int i) {
        Log.v(TAG, "onSterilizationStateChanged");
    }

    public void onSterilizationWorkStateChanged(int i) {
        Log.v(TAG, "onSterilizationWorkStateChanged");
    }

    public void onStopRemoteCtrlDriveIndChanged(int i) {
        Log.v(TAG, "onStopRemoteCtrlDriveIndChanged");
    }

    public void onSwControlStateChanged(int i) {
        Log.v(TAG, "onSwControlStateChanged state = " + i);
    }

    public void onSwsMediumRelatedKeys(int i) {
        Log.v(TAG, "onSwsMediumRelatedKeys");
    }

    public void onTailBoxTakeStateChanged(int i) {
        Log.v(TAG, "onTailBoxTakeStateChanged");
    }

    public void onTailBoxTakeSupportedChanged(int i) {
        Log.v(TAG, "onTailBoxTakeSupportedChanged");
    }

    public void onTailInitializationStateChanged(int i) {
        Log.v(TAG, "onTailInitializationStateChanged");
    }

    public void onTailMotorThermalProtectionStateChanged(int i) {
        Log.v(TAG, "onTailMotorThermalProtectionStateChanged");
    }

    public void onTailPositionFeedbackChanged(int i) {
        Log.v(TAG, "onTailPositionFeedbackChanged");
    }

    public void onTailPositionPercentageChanged(int i) {
        Log.v(TAG, "onTailPositionPercentageChanged");
    }

    public void onTrailerLightCheckStateChanged(int i) {
        Log.v(TAG, "onTrailerLightCheckStateChanged");
    }

    public void onTrailerModeCodeChanged(int i) {
        Log.v(TAG, "onTrailerModeCodeChanged");
    }

    public void onTrailerModeMileageChanged(int i) {
        Log.v(TAG, "onTrailerModeMileageChanged");
    }

    public void onTrailerModeStateChanged(int i) {
        Log.v(TAG, "onTrailerModeStateChanged");
    }

    public void onTrayActionStateChanged(int i) {
        Log.v(TAG, "onTrayActionStateChanged");
    }

    public void onTrayStateChanged(int i, int i2) {
        Log.v(TAG, "onTrayStateChanged");
    }

    public void onTurnCaliperSplintHeightChanged(int i, int i2, float f, int i3) {
        Log.v(TAG, "onTurnCaliperSplintHeightChanged");
    }

    public void onTurnCushionHeightChanged(int i, int i2, float f, int i3) {
        Log.v(TAG, "onTurnCushionHeightChanged");
    }

    public void onTurnRearviewMirrorChanged(int i, float f, float f2) {
        Log.v(TAG, "onTurnRearviewMirrorChanged");
    }

    public void onTurnSeatCaliperSplintChanged(int i, int i2, float f) {
        Log.v(TAG, "onTurnSeatCaliperSplintChanged");
    }

    public void onTurnSeatCushionChanged(int i, int i2, float f) {
        Log.v(TAG, "onTurnSeatCushionChanged");
    }

    public void onTurnSeatHeightChanged(int i, int i2, float f) {
        Log.v(TAG, "onTurnSeatHeightChanged");
    }

    public void onTurnSeatHorizationChanged(int i, int i2, float f) {
        Log.v(TAG, "onTurnSeatHorizationChanged");
    }

    public void onTurnSeatbackrestChanged(int i, int i2, float f) {
        Log.v(TAG, "onTurnSeatbackrestChanged");
    }

    public void onTurnSteeringWheelChanged(int i, float f, float f2) {
        Log.v(TAG, "onTurnSteeringWheelChanged");
    }

    public void onUnlockDownwindowChanged(int i) {
        Log.v(TAG, "onUnlockDownwindowChanged");
    }

    public void onVSPStateChanged(int i) {
        Log.v(TAG, "onVSPStateChanged");
    }

    public void onVehicleStateChanged(int i) {
        Log.v(TAG, "onVehicleStateChanged");
    }

    public void onVoiceTipsMessageChanged(int i) {
        Log.v(TAG, "onVoiceTipsMessageChanged");
    }

    public void onWadingPatternSocTipsChanaged(int i) {
        Log.v(TAG, "onWadingPatternSocTipsChanaged state = " + i);
    }

    public void onWadingPatternSpeedTipsChanged(int i) {
        Log.v(TAG, "onWadingPatternSpeedTipsChanged state = " + i);
    }

    public void onWadingPatternStateTipsChanged(int i) {
        Log.v(TAG, "onWadingPatternStateTipsChanged state = " + i);
    }

    public void onWadingPatternTestTipsChanged(int i) {
        Log.v(TAG, "onWadingPatternTestTipsChanged state = " + i);
    }

    public void onWelcomeActiveChanged(int i) {
        Log.v(TAG, "onWelcomeActiveChanged");
    }

    public void onWelcomeStateChanged(int i) {
        Log.v(TAG, "onWelcomeStateChanged");
    }

    public void onWelcomeSupportedChanged(int i) {
        Log.v(TAG, "onWelcomeSupportedChanged");
    }

    public void onWindscreenWiperOverhaulStateChanged(int i, int i2) {
        Log.v(TAG, "onWindscreenWiperOverhaulStateChanged");
    }

    public void onWorkStateChanged(int i) {
        Log.v(TAG, "onWorkStateChanged");
    }

    public void setKeypadToneStatechanged(int i) {
        Log.v(TAG, "setKeypadToneStatechanged");
    }
}

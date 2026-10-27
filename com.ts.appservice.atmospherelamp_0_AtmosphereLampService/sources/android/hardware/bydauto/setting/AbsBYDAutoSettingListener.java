package android.hardware.bydauto.setting;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto_test.TestSettingManagerImplCallback;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoSettingListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoSettingListener";

    public AbsBYDAutoSettingListener() {
        Log.v(TAG, TAG);
    }

    public void onACBTWindSwitchChanged(int state) {
        Log.v(TAG, "onACBTWindSwitchChanged");
    }

    public void onACTunnelCycleSwitchChanged(int state) {
        Log.v(TAG, "onACTunnelCycleSwitchChanged");
    }

    public void onAmbientMulticolorModeChanged(int area, int value) {
        Log.v(TAG, "onAmbientMulticolorModeChanged");
    }

    public void onACPauseCycleSwitchChanged(int state) {
        Log.v(TAG, "onACPauseCycleSwitchChanged");
    }

    public void onACAutoAirModeChanged(int state) {
        Log.v(TAG, "onACAutoAirModeChanged");
    }

    public void onACAutoWindLevelChanged(int level) {
        Log.v(TAG, "onACAutoWindLevelChanged");
    }

    public void onEnergyFeedbackStrengthChanged(int level) {
        Log.v(TAG, "onEnergyFeedbackStrengthChanged");
    }

    public void onSOCTargetRangeChange(int state) {
        Log.v(TAG, "onSOCTargetRangeChange");
    }

    public void onChargingPortSwitchChanged(int state) {
        Log.v(TAG, "onChargingPortSwitchChanged");
    }

    public void onSteerAssisModeChanged(int state) {
        Log.v(TAG, "onSteerAssisModeChanged");
    }

    public void onCarWarnINDSwitchChanged(int state) {
        Log.v(TAG, "onCarWarnINDSwitchChanged");
    }

    public void onIntelligentVoiceINDSwitchChanged(int state) {
        Log.v(TAG, "onIntelligentVoiceINDSwitchChanged");
    }

    public void onIntelligentVoiceINDLevelChanged(int level) {
        Log.v(TAG, "onIntelligentVoiceINDLevelChanged");
    }

    public void onRearViewMirrorFlipSwitchChanged(int state) {
        Log.v(TAG, "onRearViewMirrorFlipSwitchChanged");
    }

    public void onLeftViewMirrorFlipAngleChanged(int angle) {
        Log.v(TAG, "onLeftViewMirrorFlipAngleChanged");
    }

    public void onRightViewMirrorFlipAngleChanged(int angle) {
        Log.v(TAG, "onRightViewMirrorFlipAngleChanged");
    }

    public void onAutoExternalRearMirrorFoldChanged(int state) {
        Log.v(TAG, "onAutoExternalRearMirrorFoldChanged");
    }

    public void onBackRowControlSwitchChanged(int state) {
        Log.v(TAG, "onBackRowControlSwitchChanged");
    }

    public void onDriverSeatAutoReturnSwitchChanged(int state) {
        Log.v(TAG, "onDriverSeatAutoReturnSwitchChanged");
    }

    public void onSteerPositionAutoReturnSwitchChanged(int state) {
        Log.v(TAG, "onSteerPositionAutoReturnSwitchChanged");
    }

    public void onPM25PowerSwitchChanged(int state) {
        Log.v(TAG, "onPM25PowerSwitchChanged");
    }

    public void onPM25SwitchCheckChanged(int state) {
        Log.v(TAG, "onPM25SwitchCheckChanged");
    }

    public void onPM25TimeCheckChanged(int state) {
        Log.v(TAG, "onPM25TimeCheckChanged");
    }

    public void onPM25SwitchChanged(int state) {
        Log.v(TAG, "onPM25SwitchChanged");
    }

    public void onNightSystemModeSwitchChanged(int state) {
        Log.v(TAG, "onNightSystemModeSwitchChanged");
    }

    public void onPedestrianRecognizeSwitchChanged(int state) {
        Log.v(TAG, "onPedestrianRecognizeSwitchChanged");
    }

    public void onControlWindowSwitchChanged(int state) {
        Log.v(TAG, "onControlWindowSwitchChanged");
    }

    public void onLockCarRiseWindowChanged(int state) {
        Log.v(TAG, "onLockCarRiseWindowChanged");
    }

    public void onLongPressUnlockWindowSwitchChanged(int state) {
        Log.v(TAG, "onLongPressUnlockWindowSwitchChanged");
    }

    public void onAutoLockSwitchChanged(int state) {
        Log.v(TAG, "onAutoLockSwitchChanged");
    }

    public void onBackHomeLightDelaySwitchChanged(int state) {
        Log.v(TAG, "onBackHomeLightDelaySwitchChanged");
    }

    public void onLeftHomeLightDelaySwitchChanged(int state) {
        Log.v(TAG, "onLeftHomeLightDelaySwitchChanged");
    }

    public void onLeftHomeLightDelayValueChanged(int value) {
        Log.v(TAG, "onLeftHomeLightDelayValueChanged");
    }

    public void onLockOffDoorChanged(int state) {
        Log.v(TAG, "onLockOffDoorChanged");
    }

    public void onDoorLockChanged(int lock) {
        Log.v(TAG, "onDoorLockChanged");
    }

    public void onCarRecorderRecordingChanged(int lock) {
        Log.v(TAG, "onCarRecorderRecordingChanged");
    }

    public void onRearMirrorFlipChanged(int value) {
        Log.v(TAG, "onRearMirrorFlipChanged");
    }

    public void onRemoteControlUpwindowChanged(int value) {
        Log.v(TAG, "onRemoteControlUpwindowChanged");
    }

    public void onRemoteControlDownwindowChanged(int value) {
        Log.v(TAG, "onRemoteControlDownwindowChanged");
    }

    public void onLockUpwindowChanged(int value) {
        Log.v(TAG, "onLockUpwindowChanged");
    }

    public void onUnlockDownwindowChanged(int value) {
        Log.v(TAG, "onUnlockDownwindowChanged");
    }

    public void onLPSwitchUpwindowChanged(int value) {
        Log.v(TAG, "onLPSwitchUpwindowChanged");
    }

    public void onLPSwitchDownwindowChanged(int value) {
        Log.v(TAG, "onLPSwitchDownwindowChanged");
    }

    public void onHomeLightTimeChanged(int value) {
        Log.v(TAG, "onHomeLightTimeChanged");
    }

    public void onLeaveHomeLightTimeChanged(int value) {
        Log.v(TAG, "onLeaveHomeLightTimeChanged");
    }

    public void onDriverSeatBackChanged(int value) {
        Log.v(TAG, "onDriverSeatBackChanged");
    }

    public void onSteerBackChanged(int value) {
        Log.v(TAG, "onSteerBackChanged");
    }

    public void onAutoLockChanged(int value) {
        Log.v(TAG, "onAutoLockChanged");
    }

    public void onDrivingRecorderChanged(int value) {
        Log.v(TAG, "onDrivingRecorderChanged");
    }

    public void onDrivingRecorderSwitchChanged(int lock) {
        Log.v(TAG, "onDrivingRecorderSwitchChanged");
    }

    public void onInsideRearMirrorScreenSwitchChanged(int lock) {
        Log.v(TAG, "onInsideRearMirrorScreenSwitchChanged");
    }

    public void onRemoteControlUpwindowStateChanged(int state) {
        Log.v(TAG, "onRemoteControlUpwindowStateChanged");
    }

    public void onMicroSwitchLockWindowStateChanged(int state) {
        Log.v(TAG, "onMicroSwitchLockWindowStateChanged");
    }

    public void onBasePadAutoRiseStateChanged(int state) {
        Log.v(TAG, "onBasePadAutoRiseStateChanged");
    }

    public void onElecHandbrakeStateChanged(int state) {
        Log.v(TAG, "onElecHandbrakeStateChanged");
    }

    public void onRearAcOnlineStateChanged(int state) {
        Log.v(TAG, "onRearAcOnlineStateChanged");
    }

    public void onBackDoorElectricModeChanged(int mode) {
        Log.v(TAG, "onBackDoorElectricModeChanged");
    }

    public void onRearViewMirrorAutoFoldModeChanged(int state) {
        Log.v(TAG, "onRearViewMirrorAutoFoldModeChanged");
    }

    public void onILDurationChanged(int duration) {
        Log.v(TAG, "onILDurationChanged");
    }

    public void onOverspeedLockStateChanged(int state) {
        Log.v(TAG, "onOverspeedLockStateChanged");
    }

    public void onAutoLockTimeChanged(int time) {
        Log.v(TAG, "onAutoLockTimeChanged");
    }

    public void onLanguageChanged(int value) {
        Log.v(TAG, "onLanguageChanged");
    }

    public void onSafeWarnStateChanged(int state) {
        Log.v(TAG, "onSafeWarnStateChanged");
    }

    public void onMaintainRemindStateChanged(int state) {
        Log.v(TAG, "onMaintainRemindStateChanged");
    }

    public void onRemoteCtlUnlockingStateChanged(int state) {
        Log.v(TAG, "onRemoteCtlUnlockingStateChanged");
    }

    public void onCourtesyLampTimeChanged(int time) {
        Log.v(TAG, "onCourtesyLampTimeChanged");
    }

    public void onMicroSwitchUnlockWindowStateChanged(int state) {
        Log.v(TAG, "onMicroSwitchUnlockWindowStateChanged");
    }

    public void onAutoExternalRearMirrorFollowUpSwitchChanged(int state) {
        Log.v(TAG, "onAutoExternalRearMirrorFollowUpSwitchChanged");
    }

    public void onWindscreenWiperOverhaulStateChanged(int area, int state) {
        Log.v(TAG, "onWindscreenWiperOverhaulStateChanged");
    }

    public void onNightDriveLightOnReminderChanged(int value) {
        Log.v(TAG, "onNightDriveLightOnReminderChanged");
    }

    public void onBTCallReductionWindMenuStateChanged(int state) {
        Log.v(TAG, "onBTCallReductionWindMenuStateChanged");
    }

    public void onEngineOilLevelChanged(int level) {
        Log.v(TAG, "onEngineOilLevelChanged");
    }

    public void onEngineOilUpdateSignalChanged(int signal) {
        Log.v(TAG, "onEngineOilUpdateSignalChanged");
    }

    public void onEngineOilExitUpdateStateChanged(int state) {
        Log.v(TAG, "onEngineOilExitUpdateStateChanged");
    }

    public void onMultimediaMotorTemperatureStateChanged(int state) {
        Log.v(TAG, "onMultimediaMotorTemperatureStateChanged");
    }

    public void onSOCConfigChanged(int config) {
        Log.v(TAG, "onSOCConfigChanged");
    }

    public void onSteerAssistPermissionChanged(int state) {
        Log.v(TAG, "onSteerAssistPermissionChanged");
    }

    public void onDriveConfigTypeChanged(int type) {
        Log.v(TAG, "onDriveConfigTypeChanged");
    }

    public void onStartKeyStateChanged(int state) {
        Log.v(TAG, "onStartKeyStateChanged");
    }

    public void onSeatHeatingStateChanged1(int seatID, int state) {
        Log.v(TAG, "onSeatHeatingStateChanged1");
    }

    public void onIKEYBTLowPowerModeChanged(int mode) {
        Log.v(TAG, "onIKEYBTLowPowerModeChanged");
    }

    public void onAirLightPanelStateChanged(int state) {
        Log.v(TAG, "onAirLightPanelStateChanged");
    }

    public void onAirLightTextStateChanged(int state) {
        Log.v(TAG, "onAirLightTextStateChanged");
    }

    public void onAirLightTextNumberChanged(int value) {
        Log.v(TAG, "onAirLightTextNumberChanged");
    }

    public void onCruiseBulletBoxChanged(int state) {
        Log.v(TAG, "onCruiseBulletBoxChanged");
    }

    public void onLearnerDriverModeStateChanged(int state) {
        Log.v(TAG, "onLearnerDriverModeStateChanged");
    }

    public void onLearnerDriverAccLimitStateChanged(int state) {
        Log.v(TAG, "onLearnerDriverAccLimitStateChanged");
    }

    public void onLearnerDriverModeSwitchStateChanged(int state) {
        Log.v(TAG, "onLearnerDriverModeSwitchStateChanged");
    }

    public void onMcuRestartFlagChanged(int flag) {
        Log.v(TAG, "onMcuRestartFlagChanged");
    }

    public void onExhibitionModeChanged(int mode) {
        Log.v(TAG, "onExhibitionModeChanged");
    }

    public void onHandleAutoResetTimeChanged(int time) {
        Log.v(TAG, "onHandleAutoResetTimeChanged");
    }

    public void onExhibitionStartEnginePromptChanged(int value) {
        Log.v(TAG, "onExhibitionStartEnginePromptChanged");
    }

    public void onEPedalModeChanged(int value) {
        Log.v(TAG, "onEPedalModeChanged");
    }

    public void onVehicleStateChanged(int state) {
        Log.v(TAG, "onVehicleStateChanged");
    }

    public void onInstrumentAcKeyTypeChanged(int type) {
        Log.v(TAG, "onInstrumentAcKeyTypeChanged");
    }

    public void onRearViewMirrorAngleChanged(int value) {
        Log.v(TAG, "onRearViewMirrorAngleChanged");
    }

    public void onDmPlatformChanged(int value) {
        Log.v(TAG, "onDmPlatformChanged");
    }

    public void onLeftFieldPlatformChanged(int value) {
        Log.v(TAG, "onLeftFieldPlatformChanged");
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

    public void onAcTempUnitChange(int unit) {
        Log.v(TAG, "onAcTempUnitChange");
    }

    public void onAcKeyStartTimeChange(int value) {
        Log.v(TAG, "onAcKeyStartTimeChange");
    }

    public void onAcAutoPurificationStateChange(int state) {
        Log.v(TAG, "onAcAutoPurificationStateChange");
    }

    public void onRearPanelLockTimeModeChange(int mode) {
        Log.v(TAG, "onRearPanelLockTimeModeChange");
    }

    public void onBackDoorOpenedHeightChanged(int height) {
        Log.v(TAG, "onBackDoorOpenedHeightChanged");
    }

    public void onRemoteLockWindowStateChanged(int state) {
        Log.v(TAG, "onRemoteLockWindowStateChanged");
    }

    public void onRemoteUnlockWindowStateChanged(int state) {
        Log.v(TAG, "onRemoteUnlockWindowStateChanged");
    }

    public void onLockCarRiseWindowStateChange(int value) {
        Log.v(TAG, "onLockCarRiseWindowStateChange");
    }

    public void onRainAutoLockWindowStateChanged(int state) {
        Log.v(TAG, "onRainAutoLockWindowStateChanged");
    }

    public void onDriveAutoLockWindowStateChanged(int state) {
        Log.v(TAG, "onDriveAutoLockWindowStateChanged");
    }

    public void onDoorknobAutoShrinkStateChanged(int state) {
        Log.v(TAG, "onDoorknobAutoShrinkStateChanged");
    }

    public void onRemoteControlUnlockStateChanged(int state) {
        Log.v(TAG, "onRemoteControlUnlockStateChanged");
    }

    public void onHudRestartAndEffect(int state) {
        Log.v(TAG, TestSettingManagerImplCallback.ONHUDRESTARTANDEFFECT);
    }

    public void onHudParamsEffect(int state) {
        Log.v(TAG, "onHudParamsEffect");
    }

    public void onHudStateChanged(int state) {
        Log.v(TAG, "onHudStateChanged");
    }

    public void onHudHeightChanged(int state) {
        Log.v(TAG, "onHudHeightChanged");
    }

    public void onHudBrightnessChanged(int state) {
        Log.v(TAG, "onHudBrightnessChanged");
    }

    public void onHudRotationAngleChanged(int state) {
        Log.v(TAG, "onHudRotationAngleChanged");
    }

    public void onHudModeChanged(int state) {
        Log.v(TAG, "onHudModeChanged");
    }

    public void onHudDisplayContentChanged(int setSource, int value) {
        Log.v(TAG, "onHudDisplayContentChanged");
    }

    public void onAutoReportContentChanged(int value, int state) {
        Log.v(TAG, "onAutoReportContentChanged");
    }

    public void onEngineSoundTypeChanged(int value) {
        Log.v(TAG, "onEngineSoundTypeChanged");
    }

    public void onReportVolumeChanged(int value) {
        Log.v(TAG, "onReportVolumeChanged");
    }

    public void onSpeedReminderValueChanged(int value) {
        Log.v(TAG, "onSpeedReminderValueChanged");
    }

    public void onRearViewMirrorFlipChanged(int value) {
        Log.v(TAG, "onRearViewMirrorFlipChanged");
    }

    public void onAutoPowerOnStateChanged(int value) {
        Log.v(TAG, "onAutoPowerOnStateChanged");
    }

    public void onAutoExternalRearMirrorFollowUpStateChanged(int value) {
        Log.v(TAG, "onAutoExternalRearMirrorFollowUpStateChanged");
    }

    public void onDriverSeatAutoReturnStateChanged(int value) {
        Log.v(TAG, "onDriverSeatAutoReturnStateChanged");
    }

    public void onSteerPositionAutoReturnStateChanged(int value) {
        Log.v(TAG, "onSteerPositionAutoReturnStateChanged");
    }

    public void onBrakePedalModeChanged(int value) {
        Log.v(TAG, "onBrakePedalModeChanged");
    }

    public void onComfortableParkingStateChanged(int value) {
        Log.v(TAG, "onComfortableParkingStateChanged");
    }

    public void onMotorPretensionerSeatbeltStateChanged(int value) {
        Log.v(TAG, "onMotorPretensionerSeatbeltStateChanged");
    }

    public void onAutoRainWiperStateChanged(int state) {
        Log.v(TAG, "onAutoRainWiperStateChanged");
    }

    public void onDynamicSuspensionModeChanged(int value) {
        Log.v(TAG, "onDynamicSuspensionModeChanged");
    }

    public void onDisinfStateChanged(int state) {
        Log.v(TAG, "onDisinfStateChanged");
    }

    public void onTrayStateChanged(int type, int state) {
        Log.v(TAG, "onTrayStateChanged");
    }

    public void onIntelligentAcAreaStateChanged(int value) {
        Log.v(TAG, "onIntelligentAcAreaStateChanged");
    }

    public void onCarTipsSoundTypeChanged(int value) {
        Log.v(TAG, "onCarTipsSoundTypeChanged");
    }

    public void onAppUseLimitStateChanged(int value) {
        Log.v(TAG, "onAppUseLimitStateChanged");
    }

    public void onNavigationMediaStateChanged(int value) {
        Log.v(TAG, "onNavigationMediaStateChanged");
    }

    public void setKeypadToneStatechanged(int state) {
        Log.v(TAG, "setKeypadToneStatechanged");
    }

    public void onRefrigeratorModeChanged(int state) {
        Log.v(TAG, "onRefrigeratorModeChanged");
    }

    public void onRemenberPowerOffStateChanged(int state) {
        Log.v(TAG, "onRemenberPowerOffStateChanged");
    }

    public void onSteeringWheelYChanged(int value, int state) {
        Log.v(TAG, "onSteeringWheelYChanged");
    }

    public void onSteeringWheelZChanged(int value, int state) {
        Log.v(TAG, "onSteeringWheelZChanged");
    }

    public void onSeatHorizationChanged(int position, float horizatial, int location, int state) {
        Log.v(TAG, "onSeatHorizationChanged");
    }

    public void onSeatbackrestPostion(int position, float value, int location, int state) {
        Log.v(TAG, "onSeatbackrestPostion");
    }

    public void onSaveSeatParams(int postion, int location, float horiaztial, float height, float backrest, int state) {
        Log.v(TAG, "onSaveSeatParams");
    }

    public void onTurnCushionHeightChanged(int position, int location, float height, int state) {
        Log.v(TAG, "onTurnCushionHeightChanged");
    }

    public void onTurnCaliperSplintHeightChanged(int position, int location, float height, int state) {
        Log.v(TAG, "onTurnCaliperSplintHeightChanged");
    }

    public void onHeadrestHorizationChanged(int position, float horizatial, int location, int state) {
        Log.v(TAG, "onHeadrestHorizationChanged");
    }

    public void onHeadrestTurnHeightChanged(int location, int orientation, float value) {
        Log.v(TAG, "onHeadrestTurnHeightChanged");
    }

    public void onSetHudParamsEffectChanged(int state) {
        Log.v(TAG, "onSetHudParamsEffectChanged");
    }

    public void onResetHudHeight(int state) {
        Log.v(TAG, "onResetHudHeight");
    }

    public void onResetHudBrightness(int state) {
        Log.v(TAG, "onResetHudBrightness");
    }

    public void onResetHudRotationAngle(int state) {
        Log.v(TAG, "onResetHudRotationAngle");
    }

    public void onResetHudMode(int state) {
        Log.v(TAG, "onResetHudMode");
    }

    public void onResetHudDisplayContent(int state) {
        Log.v(TAG, "onResetHudDisplayContent");
    }

    public void onResetAutoPowerOnState(int state) {
        Log.v(TAG, "onResetAutoPowerOnState");
    }

    public void onResetAutoExternalRearMirrorFollowUp(int state) {
        Log.v(TAG, "onResetAutoExternalRearMirrorFollowUp");
    }

    public void onResetMotorPretensionerSeatbelt(int state) {
        Log.v(TAG, "onResetMotorPretensionerSeatbelt");
    }

    public void onRecoverSteeringWheelPosition(int postion, int state) {
        Log.v(TAG, "onRecoverSteeringWheelPosition");
    }

    public void onRecoverRearviewMirrorParams(int position, int orientation, int state) {
        Log.v(TAG, "onRecoverRearviewMirrorParams");
    }

    public void onRecoverSeatParams(int postion, int location, int state) {
        Log.v(TAG, "onRecoverSeatParams");
    }

    public void onRecoverCushionHeight(int postion, int location, int state) {
        Log.v(TAG, "onRecoverCushionHeight");
    }

    public void onRecoverCaliperSplintHeight(int postion, int location, int state) {
        Log.v(TAG, "onRecoverCaliperSplintHeight");
    }

    public void onSeatTurnHeight(int position, float height, int location, int state) {
        Log.v(TAG, "onSeatTurnHeight");
    }

    public void onCancelRecoverParms(int postion, int location, int state) {
        Log.v(TAG, "onCancelRecoverParms");
    }

    public void onOverSpeedLockStateChanged(int state) {
        Log.v(TAG, "onOverSpeedLockStateChanged");
    }

    public void onAcWarmModeChanged(int mode) {
        Log.v(TAG, "onAcWarmModeChanged");
    }

    public void onAcAutoPurificationStateChanged(int state) {
        Log.v(TAG, "onAcAutoPurificationStateChanged");
    }

    public void onAcTempUnitChanged(int unit) {
        Log.v(TAG, "onAcTempUnitChanged");
    }

    public void onRearPanelLockTimeModeChanged(int mode) {
        Log.v(TAG, "onRearPanelLockTimeModeChanged");
    }

    public void onAcKeyStartTimeChanged(int value) {
        Log.v(TAG, "onAcKeyStartTimeChanged");
    }

    public void onAutoPowerOffStateChanged(int value) {
        Log.v(TAG, "onAutoPowerOffStateChanged");
    }

    public void onLandAdvanceAtSateChanged(int state) {
        Log.v(TAG, "onLandAdvanceAtSateChanged");
    }

    public void onRefrigeratorState(int state) {
        Log.v(TAG, "onRefrigeratorState");
    }

    public void onTrayActionStateChanged(int value) {
        Log.v(TAG, "onTrayActionStateChanged");
    }

    public void onLockOffChanged(int state) {
        Log.v(TAG, "onLockOffChanged");
    }

    public void onTurnSeatCaliperSplintChanged(int location, int orientation, float value) {
        Log.v(TAG, "onTurnSeatCaliperSplintChanged");
    }

    public void onTurnSeatCushionChanged(int location, int orientation, float value) {
        Log.v(TAG, "onTurnSeatCushionChanged");
    }

    public void onTurnSeatbackrestChanged(int location, int orientation, float value) {
        Log.v(TAG, "onTurnSeatbackrestChanged");
    }

    public void onTurnSeatHeightChanged(int location, int orientation, float value) {
        Log.v(TAG, "onTurnSeatHeightChanged");
    }

    public void onTurnSeatHorizationChanged(int location, int orientation, float value) {
        Log.v(TAG, "onTurnSeatHorizationChanged");
    }

    public void onTurnRearviewMirrorChanged(int action, float valueH, float valueV) {
        Log.v(TAG, "onTurnRearviewMirrorChanged");
    }

    public void onTurnSteeringWheelChanged(int action, float valueH, float valueV) {
        Log.v(TAG, "onTurnSteeringWheelChanged");
    }

    public void onHeadrestTurnHorizationChanged(int location, int orientation, float value) {
        Log.v(TAG, "onHeadrestTurnHorizationChanged");
    }

    public void onRecoverOrSaveParamsChanged(int position, int location, int state, int code) {
        Log.v(TAG, "onRecoverOrSaveParamsChanged position:" + position + " location:" + location + " state:" + state + " code:" + code);
    }

    public void onIntelligentUnlockStateChanged(int state) {
        Log.v(TAG, "onIntelligentUnlockStateChanged");
    }

    public void onIntelligentLockStateChanged(int state) {
        Log.v(TAG, "onIntelligentLockStateChanged");
    }

    public void onRemoteControlSwitchChanged(int state) {
        Log.v(TAG, "onRemoteControlSwitchChanged");
    }

    public void onBackHomeLightDelayValueChanged(int value) {
        Log.v(TAG, "onBackHomeLightDelayValueChanged");
    }

    public void onAutomaticParkingSwitchChanged(int state) {
        Log.v(TAG, "onAutomaticParkingSwitchChanged");
    }

    public void onStartOrPowerIndSwitchChanged(int state) {
        Log.v(TAG, "onStartOrPowerIndSwitchChanged");
    }

    public void onParkBrakeIndSwitchChanged(int state) {
        Log.v(TAG, "onParkBrakeIndSwitchChanged");
    }

    public void onDriveDoorCloseIndSwitchChanged(int state) {
        Log.v(TAG, "onDriveDoorCloseIndSwitchChanged");
    }

    public void onSafeBeltIndSwitchChanged(int state) {
        Log.v(TAG, "onSafeBeltIndSwitchChanged");
    }

    public void onLeaveCarPIndSwitchChanged(int state) {
        Log.v(TAG, "onLeaveCarPIndSwitchChanged");
    }

    public void onKeyPowerLowIndSwitchChanged(int state) {
        Log.v(TAG, "onKeyPowerLowIndSwitchChanged");
    }

    public void onMissKeyIndSwitchChanged(int state) {
        Log.v(TAG, "onMissKeyIndSwitchChanged");
    }

    public void onNightDRLightOnIndSwitchChanged(int state) {
        Log.v(TAG, "onNightDRLightOnIndSwitchChanged");
    }

    public void onLowOilIndSwitchChanged(int state) {
        Log.v(TAG, "onLowOilIndSwitchChanged");
    }

    public void onLowBatteryIndSwitchChanged(int state) {
        Log.v(TAG, "onLowBatteryIndSwitchChanged");
    }

    public void onStopRemoteCtrlDriveIndChanged(int state) {
        Log.v(TAG, "onStopRemoteCtrlDriveIndChanged");
    }

    public void onIALColorChanged(int area, int color) {
        Log.v(TAG, "onIALColorChanged");
    }

    public void onIALBrightnessChanged(int area, int brightness) {
        Log.v(TAG, "onIALBrightnessChanged");
    }

    public void onIALAreaChanged(int area) {
        Log.v(TAG, "onIALAreaChanged");
    }

    public void onSeatHeatingStateChanged(int seatID, int state) {
        Log.v(TAG, "onSeatHeatingStateChanged");
    }

    public void onSeatVentilatingStateChanged(int seatID, int state) {
        Log.v(TAG, "onSeatVentilatingStateChanged");
    }

    public void onLanternLanguageModeChanged(int mode) {
        Log.v(TAG, "onLanternLanguageModeChanged");
    }

    public void onINSThemeChanged(int state) {
        Log.v(TAG, "onINSThemeChanged");
    }

    public void onHeadlampLevelChanged(int level) {
        Log.v(TAG, "onHeadlampLevelChanged");
    }

    public void onLeftHeadlampLevelChanged(int level) {
        Log.v(TAG, "onLeftHeadlampLevelChanged");
    }

    public void onRightHeadlampLevelChanged(int level) {
        Log.v(TAG, "onRightHeadlampLevelChanged");
    }

    public void onSmartWelcomeLightStateChanged(int state) {
        Log.v(TAG, "onSmartWelcomeLightStateChanged");
    }

    public void onOffReminderChanged(int value) {
        Log.v(TAG, "onOffReminderChanged");
    }

    public void onRearAcPanelAutoLockTimeChanged(int time) {
        Log.v(TAG, "onRearAcPanelAutoLockTimeChanged");
    }

    public void onSteeringWheelHeatingStateChanged(int state) {
        Log.v(TAG, "onSteeringWheelHeatingStateChanged");
    }

    public void onInsideLightDoorStateChanged(int state) {
        Log.v(TAG, "onInsideLightDoorStateChanged");
    }

    public void onMassageModeStateChanged(int type, int state) {
        Log.v(TAG, "onMassageModeStateChanged");
    }

    public void onMassageLevelStateChanged(int type, int state) {
        Log.v(TAG, "onMassageLevelStateChanged");
    }

    @Deprecated
    public void onSaveSeatParamsAllChanged(int postion, int location, int state) {
        Log.v(TAG, "onSaveSeatParamsAllChanged");
    }

    public void onRearViewMirrorFlipSetChanged(int value) {
        Log.v(TAG, "onRearViewMirrorFlipSetChanged");
    }

    public void onComSpeedUpStateChanged(int value) {
        Log.v(TAG, "onComSpeedUpStateChanged");
    }

    public void onComSpeedDownStateChanged(int value) {
        Log.v(TAG, "onComSpeedDownStateChanged");
    }

    public void onHSpeedAutoCloseStateChanged(int value) {
        Log.v(TAG, "onHSpeedAutoCloseStateChanged");
    }

    public void onRuningCloseDoorStateChanged(int value) {
        Log.v(TAG, "onRuningCloseDoorStateChanged");
    }

    public void onAidedDrivingIndChang(int value) {
        Log.v(TAG, "onAidedDrivingIndChang");
    }

    public void onHudPatternChanged(int value) {
        Log.v(TAG, "onHudPatternChanged");
    }

    public void onHudSafeDriveStateChanged(int value) {
        Log.v(TAG, "onHudSafeDriveStateChanged");
    }

    public void onHudNavigationStateChanged(int value) {
        Log.v(TAG, "onHudNavigationStateChanged");
    }

    public void onSeatHeatingState2Changed(int seatId, int value) {
        Log.v(TAG, "onSeatHeatingState2Changed");
    }

    public void onSeatHeatingNotL1PStateChanged(int value) {
        Log.v(TAG, "onSeatHeatingNotL1PStateChanged");
    }

    public void onHudSPStateChanged(int value) {
        Log.v(TAG, "onHudSPStateChanged");
    }

    public void onSeatVentilatingState2Changed(int seatId, int value) {
        Log.v(TAG, "onSeatVentilatingState2Changed");
    }

    public void onSeatVentilatingNotL1PStateChanged(int value) {
        Log.v(TAG, "onSeatVentilatingNotL1PStateChanged");
    }

    public void on5LevelSeatVentilatingStateChanged(int seatId, int value) {
        Log.v(TAG, "on5LevelSeatVentilatingStateChanged");
    }

    public void on5LevelSeatHeatingStateChanged(int seatId, int value) {
        Log.v(TAG, "on5LevelSeatHeatingStateChanged");
    }

    public void on5LevelSeatVentilatingModeChanged(int seatId, int value) {
        Log.v(TAG, "on5LevelSeatVentilatingModeChanged");
    }

    public void on5LevelSeatHeatingModeChanged(int seatId, int value) {
        Log.v(TAG, "on5LevelSeatHeatingModeChanged");
    }

    public void on5LevelSeatVentilatingLevelChanged(int seatId, int value) {
        Log.v(TAG, "on5LevelSeatVentilatingLevelChanged");
    }

    public void on5LevelSeatHeatingLevelChanged(int seatId, int value) {
        Log.v(TAG, "on5LevelSeatHeatingLevelChanged");
    }

    public void onSteeringWheelHeatingGearChanged(int value) {
        Log.v(TAG, "onSteeringWheelHeatingGearChanged");
    }

    public void onSteeringWheelHeatingAutoStateChanged(int value) {
        Log.v(TAG, "onSteeringWheelHeatingAutoStateChanged");
    }

    public void onHsMassageStateChanged(int type, int value) {
        Log.v(TAG, "onHsMassageStateChanged");
    }

    public void onHevChargingPortSwitchChanged(int value) {
        Log.v(TAG, "onHevChargingPortSwitchChanged");
    }

    public void onKeyVibratorLevelChanged(int value) {
        Log.v(TAG, "onKeyVibratorLevelChanged");
    }

    public void onSafeBeltWarnStateChanged(int state) {
        Log.v(TAG, "onSafeBeltWarnStateChanged  state=" + state);
    }

    public void onSteerAssisSupportChanged(int state) {
        Log.v(TAG, "onSteerAssisSupportChanged  state=" + state);
    }

    public void onMissPackageCountChanged(int count) {
        Log.v(TAG, "onMissPackageCountChanged");
    }

    public void onScreenAngleChanged(int angle) {
        Log.v(TAG, "onScreenAngleChanged");
    }

    public void onScreenRotationActionChanged(int state) {
        Log.v(TAG, "onScreenRotationActionChanged");
    }

    public void onScreenAntiPinchStateChanged(int state) {
        Log.v(TAG, "onScreenAntiPinchStateChanged");
    }

    public void onScreenAngleValueChanged(int value) {
        Log.v(TAG, "onScreenAngleValueChanged");
    }

    public void onScreenVoltageStateChanged(int state) {
        Log.v(TAG, "onScreenVoltageStateChanged");
    }

    public void onScreenRotationOverHotStateChanged(int state) {
        Log.v(TAG, "onScreenRotationOverHotStateChanged");
    }

    public void onSomethingLeftInRefrigeratorNotice(int value) {
        Log.v(TAG, "onSomethingLeftInRefrigeratorNotice");
    }

    public void onPABStateCodeChanged(int state) {
        Log.v(TAG, "onPABStateCodeChanged");
    }

    public void onPABStateChanged(int state) {
        Log.v(TAG, "onPABStateChanged");
    }

    public void onAmbientFeedBackChanged(int state) {
        Log.v(TAG, "onAmbientFeedBackChanged");
    }

    public void onAmbientMusicModeState(int area, int state) {
        Log.v(TAG, "onAmbientMusicModeState");
    }

    public void onBleOpenDriverDoorStateChanged(int state) {
        Log.v(TAG, "onBleOpenDriverDoorStateChanged");
    }

    public void onBrakeCloseDoorChanged(int state) {
        Log.v(TAG, "onBrakeCloseDoorChanged");
    }

    public void onCampingBlanceActionChanged(int state) {
        Log.v(TAG, "onCampingBlanceActionChanged");
    }

    public void onCampintBlacneStateChanged(int state) {
        Log.v(TAG, "onCampintBlacneStateChanged");
    }

    public void onCampingBlanceSupportedChanged(int state) {
        Log.v(TAG, "onCampingBlanceSupportedChanged");
    }

    public void onCloseDoorLevelChanged(int level) {
        Log.v(TAG, "onCloseDoorLevelChanged");
    }

    public void onCVCModeChanged(int mode) {
        Log.v(TAG, "onCVCModeChanged");
    }

    public void onDampStateChanged(int state) {
        Log.v(TAG, "onDampStateChanged");
    }

    public void onDyHeightLevelChanged(int level) {
        Log.v(TAG, "onDyHeightLevelChanged");
    }

    public void onDyHeightToggleStateChanged(int state) {
        Log.v(TAG, "onDyHeightToggleStateChanged");
    }

    public void onDyHeightTurnActionChanged(int state) {
        Log.v(TAG, "onDyHeightTurnActionChanged");
    }

    public void onFdCampingBlanceActionChanged(int state) {
        Log.v(TAG, "onFdCampingBlanceActionChanged");
    }

    public void onFdCampintBlacneStateChanged(int state) {
        Log.v(TAG, "onFdCampintBlacneStateChanged");
    }

    public void onFdCampingBlanceSupportedChanged(int state) {
        Log.v(TAG, "onFdCampingBlanceSupportedChanged");
    }

    public void onFdDampStateChanged(int state) {
        Log.v(TAG, "onFdDampStateChanged");
    }

    public void onFdDyHeightLevelChanged(int level) {
        Log.v(TAG, "onFdDyHeightLevelChanged");
    }

    public void onFdDyHeightToggleStateChanged(int state) {
        Log.v(TAG, "onFdDyHeightToggleStateChanged");
    }

    public void onFdDyHeightTurnActionChanged(int state) {
        Log.v(TAG, "onFdDyHeightTurnActionChanged");
    }

    public void onFdFixedStateChanged(int state) {
        Log.v(TAG, "onFdFixedStateChanged");
    }

    public void onFdFrontBoxTakeStateChanged(int state) {
        Log.v(TAG, "onFdFrontBoxTakeStateChanged");
    }

    public void onFdLiftModeStateChanged(int state) {
        Log.v(TAG, "onFdLiftModeStateChanged");
    }

    public void onFdModeSupportedHturnChanged(int state) {
        Log.v(TAG, "onFdModeSupportedHturnChanged");
    }

    public void onFdSpeedSupportedTurn(int value) {
        Log.v(TAG, "onFdSpeedSupportedTurn");
    }

    public void onFdTrailerModeCodeChanged(int code) {
        Log.v(TAG, "onFdTrailerModeCodeChanged");
    }

    public void onFdWelcomeActiveChanged(int state) {
        Log.v(TAG, "onFdWelcomeActiveChanged");
    }

    public void onFdWelcomeStateChanged(int state) {
        Log.v(TAG, "onFdWelcomeStateChanged");
    }

    public void onFdWelcomeSupportedChanged(int state) {
        Log.v(TAG, "onFdWelcomeSupportedChanged");
    }

    public void onFixedStateChanged(int state) {
        Log.v(TAG, "onFixedStateChanged");
    }

    public void onIntelLockOpenDriverDoorChanged(int state) {
        Log.v(TAG, "onIntelLockOpenDriverDoorChanged");
    }

    public void onLiftModeStateChanged(int state) {
        Log.v(TAG, "onLiftModeStateChanged");
    }

    public void onModeSupportedHturnChanged(int state) {
        Log.v(TAG, "onModeSupportedHturnChanged（int");
    }

    public void onNfcOpenDriverDoorChanged(int state) {
        Log.v(TAG, "onNfcOpenDriverDoorChanged");
    }

    public void on1LevelSeatHeatingStateChanged(int seatID, int state) {
        Log.v(TAG, "on1LevelSeatHeatingStateChanged");
    }

    public void onFdCRCCheckNum(int state) {
        Log.v(TAG, "onFdCRCCheckNum");
    }

    public void onRearSeatGreetStateChanged(int state) {
        Log.v(TAG, "onRearSeatGreetStateChanged");
    }

    public void onRearViewEcuMirrorFlipStateChanged(int value) {
        Log.v(TAG, "onRearViewEcuMirrorFlipStateChanged");
    }

    public void onSafeBeltCloseDriveDoorStateChanged(int state) {
        Log.v(TAG, "onSafeBeltCloseDriveDoorStateChanged");
    }

    public void onSpeedSupportedTurn(int value) {
        Log.v(TAG, "onSpeedSupportedTurn");
    }

    public void onTailBoxTakeStateChanged(int state) {
        Log.v(TAG, "onTailBoxTakeStateChanged");
    }

    public void onFdTailBoxTakeSupportedChanged(int value) {
        Log.v(TAG, "onFdTailBoxTakeSupportedChanged");
    }

    public void onTailBoxTakeSupportedChanged(int value) {
        Log.v(TAG, "onTailBoxTakeSupportedChanged");
    }

    public void onTrailerLightCheckStateChanged(int state) {
        Log.v(TAG, "onTrailerLightCheckStateChanged");
    }

    public void onTrailerModeCodeChanged(int code) {
        Log.v(TAG, "onTrailerModeCodeChanged");
    }

    public void onTrailerModeStateChanged(int state) {
        Log.v(TAG, "onTrailerModeStateChanged");
    }

    public void onVSPStateChanged(int state) {
        Log.v(TAG, "onVSPStateChanged");
    }

    public void onWelcomeActiveChanged(int state) {
        Log.v(TAG, "onWelcomeActiveChanged");
    }

    public void onWelcomeStateChanged(int state) {
        Log.v(TAG, "onWelcomeStateChanged");
    }

    public void onWelcomeSupportedChanged(int state) {
        Log.v(TAG, "onWelcomeSupportedChanged");
    }

    public void onDriverSeatAutoReturnB3Changded(int state) {
        Log.v(TAG, "onDriverSeatAutoReturnB3Changded");
    }

    public void onAmbientMusicModechanged(int area, int value) {
        Log.v(TAG, "onAmbientMusicModechanged");
    }

    public void onAmbientMulticolorStateChanged(int area, int value) {
        Log.v(TAG, "onAmbientMulticolorStateChanged");
    }

    public void onFdTailBoxTakeStateChanged(int state) {
        Log.v(TAG, "onFdTailBoxTakeStateChanged");
    }

    public void onBSISCollisionStateChanged(int value) {
        Log.v(TAG, "onBSISCollisionStateChanged");
    }

    public void onBSISFuncStatusChanged(int value) {
        Log.v(TAG, "onBSISFuncStatusChanged");
    }

    public void onMOISAssembleStateChanged(int value) {
        Log.v(TAG, "onMOISAssembleStateChanged");
    }

    public void onMOISFuncStatusChanged(int value) {
        Log.v(TAG, "onMOISFuncStatusChanged");
    }

    public void onLRSEMachineSwitchStateChanged(int state) {
        Log.v(TAG, "onLRSEMachineSwitchStateChanged");
    }

    public void onRRSEMachineSwitchStateChanged(int state) {
        Log.v(TAG, "onRRSEMachineSwitchStateChanged");
    }

    public void onLRSEChildModeTimeWriteFb(int value) {
        Log.v(TAG, "onLRSEChildModeTimeWriteFb");
    }

    public void onRRSEChildModeTimeWriteFb(int value) {
        Log.v(TAG, "onRRSEChildModeTimeWriteFb");
    }

    public void onLRSEChildModeCodeWriteFb(int value) {
        Log.v(TAG, "onLRSEChildModeCodeWriteFb");
    }

    public void onRRSEChildModeCodeWriteFb(int value) {
        Log.v(TAG, "onRRSEChildModeCodeWriteFb");
    }

    public void onIVIProfileSettingsChanged(int value) {
        Log.v(TAG, "onIVIProfileSettingsChanged");
    }

    public void onDragModeForwardAndBackwardChanged(int state) {
        Log.v(TAG, "onDragModeForwardAndBackwardChanged");
    }

    public void onDragAndDropModeTipsChanged(int state) {
        Log.v(TAG, "onDragAndDropModeTipsChanged");
    }

    public void onFdDynamicSuspensionMode(int state) {
        Log.v(TAG, "onFdDynamicSuspensionMode");
    }

    public void onFrontWiperSpeedValueChanged(int value) {
        Log.v(TAG, "onFrontWiperSpeedValueChanged");
    }

    public void onTrailerModeMileageChanged(int state) {
        Log.v(TAG, "onTrailerModeMileageChanged");
    }

    public void onRearWiperSpeedValueChanged(int state) {
        Log.v(TAG, "onRearWiperSpeedValueChanged");
    }

    public void onSwsMediumRelatedKeys(int state) {
        Log.v(TAG, "onSwsMediumRelatedKeys");
    }

    public void onCanopyAutomaticDimming(int state) {
        Log.v(TAG, "onCanopyAutomaticDimming");
    }

    public void onSkyCurtainPowerOffAndDim(int state) {
        Log.v(TAG, "onSkyCurtainPowerOffAndDim");
    }

    public void onCurrentStatusOfDimmingSunroof(int state) {
        Log.v(TAG, "onCurrentStatusOfDimmingSunroof");
    }

    public void onDimmingCanopyLowCode(int state) {
        Log.v(TAG, "onDimmingCanopyLowCode");
    }

    public void onTailInitializationStateChanged(int state) {
        Log.v(TAG, "onTailInitializationStateChanged");
    }

    public void onCurrentStateOfTheTailStateChanged(int state) {
        Log.v(TAG, "onCurrentStateOfTheTailStateChanged");
    }

    public void onTailPositionPercentageChanged(int state) {
        Log.v(TAG, "onTailPositionPercentageChanged");
    }

    public void onTailMotorThermalProtectionStateChanged(int state) {
        Log.v(TAG, "onTailMotorThermalProtectionStateChanged");
    }

    public void onTailPositionFeedbackChanged(int state) {
        Log.v(TAG, "onTailPositionFeedbackChanged");
    }

    public void onAlarmPromptStateChanged(int state) {
        Log.v(TAG, "onAlarmPromptStateChanged");
    }

    public void onCurrentModeOfTailStateChanged(int state) {
        Log.v(TAG, "onCurrentModeOfTailStateChanged");
    }

    public void onElectricPedalStateChanged(int area, int state) {
        Log.v(TAG, "onElectricPedalStateChanged");
    }

    public void onFridgeChildLockChanged(int state) {
        Log.v(TAG, "onFridgeChildLockChanged");
    }

    public void onCurrentTransmittanceOfSkylight(int state) {
        Log.v(TAG, "onCurrentTransmittanceOfSkylight");
    }

    public void onElectricTailMaintenanceFuntatusStateChanged(int state) {
        Log.v(TAG, "onElectricTailMaintenanceFuntatusStateChanged");
    }

    public void onRearviewMirrorIsStateChanged(int state) {
        Log.v(TAG, "onRearviewMirrorIsStateChanged");
    }

    public void onRearviewMirrorIsTurnedStateChanged(int state) {
        Log.v(TAG, "onRearviewMirrorIsTurnedStateChanged");
    }

    public void onRearviewMirrorIsAutoLightStateChanged(int state) {
        Log.v(TAG, "onRearviewMirrorIsAutoLightStateChanged");
    }

    public void onFdComSpeedUpStateChanged(int state) {
        Log.v(TAG, "onFdComSpeedUpStateChanged");
    }

    public void onRefrigeratorWorkModeChanged(int state) {
        Log.v(TAG, "onRefrigeratorWorkModeChanged");
    }

    public void onRefrigeratorWorkTemperatureChanged(int state) {
        Log.v(TAG, "onRefrigeratorWorkTemperatureChanged");
    }

    public void onFridgeWorkColdTemperatureChanged(int state) {
        Log.v(TAG, "onFridgeWorkColdTemperatureChanged");
    }

    public void onFridgeWorkHeatTemperatureChanged(int state) {
        Log.v(TAG, "onFridgeWorkHeatTemperatureChanged");
    }

    public void onFridgeWorkSaveTemperatureChanged(int state) {
        Log.v(TAG, "onFridgeWorkSaveTemperatureChanged");
    }

    public void onDelayPowerOffStateChanged(int state) {
        Log.v(TAG, "onDelayPowerOffStateChanged");
    }

    public void onDelayPowerOffTimeChanged(int state) {
        Log.v(TAG, "onDelayPowerOffTimeChanged");
    }

    public void onDelayPowerDownTimeChanged(int state) {
        Log.v(TAG, "onDelayPowerDownTimeChanged");
    }

    public void onRemberPowerOffStateChanged(int state) {
        Log.v(TAG, "onRemberPowerOffStateChanged");
    }

    public void onSterilizationStateChanged(int state) {
        Log.v(TAG, "onSterilizationStateChanged");
    }

    public void onVoiceTipsMessageChanged(int state) {
        Log.v(TAG, "onVoiceTipsMessageChanged");
    }

    public void onFridgeDoorStateChanged(int state) {
        Log.v(TAG, "onFridgeDoorStateChanged");
    }

    public void onRefrigeratorWorkStateChanged(int state) {
        Log.v(TAG, "onRefrigeratorWorkStateChanged");
    }

    public void onRefrigeratorTemperatureChanged(int state) {
        Log.v(TAG, "onRefrigeratorTemperatureChanged");
    }

    public void onServiceControlCallbackChanged(int state) {
        Log.v(TAG, "onServiceControlCallbackChanged");
    }

    public void onPhoneRemindCodeChanged(int state) {
        Log.v(TAG, "onPhoneRemindCodeChanged");
    }

    public void onRearSaveTempChanged(int state) {
        Log.v(TAG, "onRearSaveTempChanged");
    }

    public void onRearHeatTempChanged(int state) {
        Log.v(TAG, "onRearHeatTempChanged");
    }

    public void onRearDoorStateChanged(int state) {
        Log.v(TAG, "onRearDoorStateChanged");
    }

    public void onScreenCallbackMessageChanged(int state) {
        Log.v(TAG, "onScreenCallbackMessageChanged");
    }

    public void onControlCallbackStateChanged(int state) {
        Log.v(TAG, "onControlCallbackStateChanged");
    }

    public void onWorkStateChanged(int state) {
        Log.v(TAG, "onWorkStateChanged");
    }

    public void onDelayPowerOffStatusChanged(int state) {
        Log.v(TAG, "onDelayPowerOffStatusChanged");
    }

    public void onDelayDownPowerTimeChanged(int state) {
        Log.v(TAG, "onDelayDownPowerTimeChanged");
    }

    public void onSterilizationWorkStateChanged(int state) {
        Log.v(TAG, "onSterilizationWorkStateChanged");
    }

    public void onDlgMessageCodeChanged(int state) {
        Log.v(TAG, "onDlgMessageCodeChanged");
    }

    public void onFridgeLocationChanged(int state) {
        Log.v(TAG, "onFridgeLocationChanged");
    }

    public void onRncStateChanged(int state) {
        Log.v(TAG, "onRncStateChanged");
    }

    public void onSOCTargetRangeChanged(int state) {
        Log.v(TAG, "onSOCTargetRangeChanged");
    }

    public void onSOCTargetLowLimitChanged(int state) {
        Log.v(TAG, "onSOCTargetLowLimitChanged");
    }

    public void onCpdImsSwitchStateChanged(int state) {
        Log.v(TAG, "onCpdImsSwitchStateChanged");
    }

    public void onScreenRotationStatus(int state) {
        Log.v(TAG, "onScreenRotationStatus state = " + state);
    }

    public void onSwControlStateChanged(int state) {
        Log.v(TAG, "onSwControlStateChanged state = " + state);
    }

    public void onPedalSuppressionSystemStateChanged(int state) {
        Log.v(TAG, "onPedalSuppressionSystemStateChanged state = " + state);
    }

    public void onRearRightSeatGreetStateChanged(int state) {
        Log.v(TAG, "onRearRightSeatGreetStateChanged state = " + state);
    }

    public void onCuraSwitchStateChanged(int state) {
        Log.v(TAG, "onCuraSwitchStateChanged state = " + state);
    }

    public void onGoalRoadModeChanged(int state) {
        Log.v(TAG, "onGoalRoadModeChanged state = " + state);
    }

    public void onWadingPatternSocTipsChanaged(int state) {
        Log.v(TAG, "onWadingPatternSocTipsChanaged state = " + state);
    }

    public void onWadingPatternSpeedTipsChanged(int state) {
        Log.v(TAG, "onWadingPatternSpeedTipsChanged state = " + state);
    }

    public void onWadingPatternTestTipsChanged(int state) {
        Log.v(TAG, "onWadingPatternTestTipsChanged state = " + state);
    }

    public void onWadingPatternStateTipsChanged(int state) {
        Log.v(TAG, "onWadingPatternStateTipsChanged state = " + state);
    }

    public void onSeaModeChargingSocTypeChanged(int value) {
        Log.v(TAG, "onSeaModeChargingSocTypeChanged value = " + value);
    }

    public void onSaveOwnerStateChanged(int sate) {
        Log.v(TAG, "onSaveOwnerStateChanged sate = " + sate);
    }

    public void onMemorySetOperationFlagChanged(int flag) {
        Log.v(TAG, "onMemorySetOperationFlagChanged flag = " + flag);
    }

    public void onDriveModeChanged(int mode) {
        Log.v(TAG, "onDriveModeChanged mode = " + mode);
    }

    public void onSandAbsModeStateChanged(int state) {
        Log.v(TAG, "onSandAbsModeStateChanged state = " + state);
    }

    public void onSandAbsTipMessageChanged(int state) {
        Log.v(TAG, "onSandAbsTipMessageChanged state = " + state);
    }

    public void onRefrigeratorMobileTerminalPushReChanged(int code) {
        Log.v(TAG, "onRefrigeratorMobileTerminalPushReChanged code = " + code);
    }

    public void onRefrigWorkModeChanged(int mode) {
        Log.v(TAG, "onRefrigWorkModeChanged mode = " + mode);
    }

    public void onDriverSeatVentilating437StateChanged(int state) {
        Log.v(TAG, "onDriverSeatVentilating437StateChanged");
    }

    public void onDriverSeatHeating437StateChanged(int state) {
        Log.v(TAG, "onDriverSeatHeating437StateChanged");
    }

    public void onPassengerSeatVentilating3CAStateChanged(int state) {
        Log.v(TAG, "onPassengerSeatVentilating3CAStateChanged");
    }

    public void onPassengerSeatHeating3CAStateChanged(int state) {
        Log.v(TAG, "onPassengerSeatHeating3CAStateChanged");
    }

    public void onThirdRowSeatWorkModeChanged(int state) {
        Log.v(TAG, "onThirdRowSeatWorkModeChanged state is " + state);
    }

    public void onLeftRearSafebeltStatusChanged(int state) {
        Log.v(TAG, "onLeftRearSafebeltStatusChanged state is " + state);
    }

    public void onCenterRearSafebeltStatusChanged(int state) {
        Log.v(TAG, "onCenterRearSafebeltStatusChanged state is " + state);
    }

    public void onRightRearSafebeltStatusChanged(int state) {
        Log.v(TAG, "onRightRearSafebeltStatusChanged state is " + state);
    }

    public void onLeftThirdSafebeltStatusChanged(int state) {
        Log.v(TAG, "onLeftThirdSafebeltStatusChanged state is " + state);
    }

    public void onRightThirdSafebeltStatusChanged(int state) {
        Log.v(TAG, "onRightThirdSafebeltStatusChanged state is " + state);
    }

    public void onMainSeatHorizontalInitChanged(int state) {
        Log.v(TAG, "onMainSeatHorizontalInitChanged state is " + state);
    }

    public void onMainSeatBackInitChanged(int state) {
        Log.v(TAG, "onMainSeatBackInitChanged state is " + state);
    }

    public void onPassangerSeatHorizontalInitChanged(int state) {
        Log.v(TAG, "onPassangerSeatHorizontalInitChanged state is " + state);
    }

    public void onPassangerSeatBackInitChanged(int state) {
        Log.v(TAG, "onPassangerSeatBackInitChanged state is " + state);
    }

    public void onSecondLeftSeatHorizontalInitChanged(int state) {
        Log.v(TAG, "onSecondLeftSeatHorizontalInitChanged state is " + state);
    }

    public void onSecondLeftSeatBackInitChanged(int state) {
        Log.v(TAG, "onSecondLeftSeatBackInitChanged state is " + state);
    }

    public void onSecondRightSeatHorizontalInitChanged(int state) {
        Log.v(TAG, "onSecondRightSeatHorizontalInitChanged state is " + state);
    }

    public void onSecondRightSeatBackInitChanged(int state) {
        Log.v(TAG, "onSecondRightSeatBackInitChanged state is " + state);
    }

    public void onPassengerStateChanged(int location) {
        Log.v(TAG, "onPassengerStateChanged location is " + location);
    }

    public void onMainSeatHorizontalInited(int value) {
        Log.v(TAG, "onMainSeatHorizontalInited value is " + value);
    }

    public void onMainSeatBackInited(int value) {
        Log.v(TAG, "onMainSeatBackInited value is " + value);
    }

    public void onMainSeatHightInited(int value) {
        Log.v(TAG, "onMainSeatHightInited value is " + value);
    }

    public void onWashElectricPedalChanged(int state) {
        Log.v(TAG, "onWashElectricPedalChanged state is " + state);
    }

    public void onTapDoorStateChanged(int state) {
        Log.v(TAG, "onTapDoorStateChanged state is " + state);
    }

    public void onSecondRightSeatBack2InitChanged(int state) {
        Log.v(TAG, "onSecondRightSeatBack2InitChanged state:" + state);
    }

    public void onSecondRightSeatHorizontal2InitChanged(int state) {
        Log.v(TAG, "onSecondRightSeatHorizontal2InitChanged state:" + state);
    }

    public void onDragTypeChanged(int type) {
        Log.v(TAG, "onDragTypeChanged type : " + type);
    }

    public void onDragConnectionTypeChanged(int type) {
        Log.v(TAG, "onDragConnectionTypeChanged type : " + type);
    }

    public void onTrailerDragTypeChanged(int type) {
        Log.v(TAG, "onTrailerDragTypeChanged type : " + type);
    }

    public void onTowingProhibitedStateChanged(int state) {
        Log.v(TAG, "onTowingProhibitedStateChanged state : " + state);
    }

    public void onUcuMassageModeChanged(int position, int mode) {
        Log.v(TAG, "onUcuMassageModeChanged position:" + position + ", mode:" + mode);
    }

    public void onUcuMassageLevelChanged(int position, int mode) {
        Log.v(TAG, "onUcuMassageLevelChanged position:" + position + ", mode:" + mode);
    }

    public void onBdMassageModeChanged(int position, int mode) {
        Log.v(TAG, "onBdMassageModeChanged position:" + position + ", mode:" + mode);
    }

    public void onBdMassageLevelChanged(int position, int mode) {
        Log.v(TAG, "onBdMassageLevelChanged position:" + position + ", mode:" + mode);
    }

    public void onSecuMassageModeChanged(int position, int mode) {
        Log.v(TAG, "onSecuMassageModeChanged position:" + position + ", mode:" + mode);
    }

    public void onSecuMassageLevelChanged(int position, int mode) {
        Log.v(TAG, "onSecuMassageLevelChanged position:" + position + ", mode:" + mode);
    }

    public void onRrEcuMassageMode(int mode) {
        Log.v(TAG, "onRrEcuMassageMode mode:" + mode);
    }

    public void onRrEcuMassageLevel(int level) {
        Log.v(TAG, "onRrEcuMassageLevel level:" + level);
    }
}

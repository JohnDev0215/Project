package android.hardware.bydauto.dipilot;

import android.hardware.IBYDAutoListener;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoDiPilotListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoDiPilotListener";

    public void onADASOnlineStateChanged(int i) {
    }

    public void onADASSoftStateChanged(int i) {
    }

    public void onAEBDecActiveChanged(int i) {
    }

    public void onAEBFaultStateStateChanged(int i) {
    }

    public void onAINavigationStateChanged(int i) {
    }

    public void onAbnormalVehicleWarningStateChanged(int i) {
    }

    public void onAdaptiveFrontLightFault(byte b) {
    }

    public void onAdativeCruiseControlFault(byte b) {
    }

    public void onAiCareStateChanged(int i) {
    }

    public void onAiDistanceLight2B4StateChanged(int i) {
    }

    public void onAiDistanceLight316StateChanged(int i) {
    }

    @Deprecated
    public void onAiDistanceLightStateChanged(int i) {
    }

    public void onAiEmergencyBrakeStateChanged(int i) {
    }

    public void onAiMatrixLight3ACStateChanged(int i) {
    }

    public void onAiMatrixLight3ADStateChanged(int i) {
    }

    public void onAiMatrixLightStateChanged(int i) {
    }

    public void onAiTurnLightStateChanged(int i) {
    }

    public void onAlarmTypeChanged(int i) {
    }

    public void onAttentionDetectionStateChanged(int i) {
    }

    public void onAutoEmergencyFault(byte b) {
    }

    public void onAutoParkingStateChanged(int i) {
    }

    public void onBSISFault(byte b) {
    }

    public void onBSISSwitchChange(int i) {
    }

    public void onBSISSystemChange(int i) {
    }

    public void onBlindFault(byte b) {
    }

    public void onBlindMonitorStateChanged(int i) {
    }

    public void onBlindMonitorStateChanged1(int i) {
    }

    public void onBlindMonitorStateChanged2(int i) {
    }

    public void onChargerCoverStateChanged(int i) {
    }

    public void onChildCareStateChanged(int i) {
    }

    public void onCollisionWarningStateChanged(int i) {
    }

    public void onControlLossWarningStateChanged(int i) {
    }

    @Deprecated
    public void onCpdDelayStatusChanged(int i) {
    }

    public void onCpdStatusChanged(int i) {
    }

    public void onCpdWarningStateChanged(int i) {
    }

    public void onCpdWorkingStatusChanged(int i) {
    }

    public void onCurveWarnFault(byte b) {
    }

    public void onDangerousActionDetectionStateChanged(int i) {
    }

    public void onDistractionDetectionAidStateChanged(int i) {
    }

    public void onDmsStatusChanged(int i) {
    }

    public void onDnpStatusChanged(int i) {
    }

    public void onDoNotPassWarningStateChanged(int i) {
    }

    public void onDoorOpenFault(byte b) {
    }

    public void onDriveDepartureStateChanged(int i) {
    }

    public void onDriveLevelChanged(int i) {
    }

    public void onDriveRemindStateChanged(int i) {
    }

    public void onDrivieDepartureStateChanged(int i) {
    }

    public void onDrivieRemindStateChanged(int i) {
    }

    public void onE2EAIAssistanceStateChanged(int i) {
    }

    public void onELKTakeOverStateChanged(int i) {
    }

    public void onElecCarBodyStablySysStateChanged(int i) {
    }

    public void onEmergencyBrakeWarningStateChanged(int i) {
    }

    public void onEmergencyLaneAssistance2F0StateChanged(int i) {
    }

    public void onEmergencyLaneAssistance32FStateChanged(int i) {
    }

    @Deprecated
    public void onEmergencyLaneAssistanceStateChanged(int i) {
    }

    public void onEmergencyLaneKeepFault(byte b) {
    }

    public void onEmergencyVehicleWarningStateChanged(int i) {
    }

    public void onEspOffKeyStatusChanged(int i) {
    }

    public void onFCWFaultStateChanged(int i) {
    }

    public void onFatigueDetectionAidStateChanged(int i) {
    }

    public void onFatigueMonitorChange(int i) {
    }

    public void onFaultHandlerStateChanged(int i) {
    }

    public void onForwardCollisionWarnFault(byte b) {
    }

    public void onFrontCarStartStateChange(int i) {
    }

    public void onFrontCrossAlarkFault(byte b) {
    }

    public void onFrontCrossBrakeFault(byte b) {
    }

    public void onFrontTrafficBrakeAlertStateChanged(int i) {
    }

    public void onFrontTrafficCrossAlertStateChanged(int i) {
    }

    public void onGazePerceptionStateChanged(int i) {
    }

    public void onGestureRecognitionStateChanged(int i) {
    }

    public void onGreenLightOptimalSpeedAdvisoryStateChanged(int i) {
    }

    public void onHighNaviFault(byte b) {
    }

    public void onHighSpeedAINavigationStateChanged(int i) {
    }

    public void onHighSpeedOpenWindowStateChanged(int i) {
    }

    public void onISLIWarningLevelSwitchStatusChanged(int i) {
    }

    public void onISLIWarningStateChanged(int i) {
    }

    public void onIntelligentLimitFaultCall(byte b) {
    }

    public void onIntelligentLimitFaultController(byte b) {
    }

    public void onIntelligentSpeedLimitAlert2B4StateChanged(int i) {
    }

    public void onIntelligentSpeedLimitAlert2D5StateChanged(int i) {
    }

    @Deprecated
    public void onIntelligentSpeedLimitAlertStateChanged(int i) {
    }

    public void onIntelligentSpeedLimitControl2D5StateChanged(int i) {
    }

    public void onIntelligentSpeedLimitControl32FStateChanged(int i) {
    }

    @Deprecated
    public void onIntelligentSpeedLimitControlStateChanged(int i) {
    }

    public void onInteractiveLaneChangeAssistanceStateChanged(int i) {
    }

    public void onInteractiveLaneChangeFault(byte b) {
    }

    public void onIntersectionCollisionAlertStateChanged(int i) {
    }

    public void onLDWFaultStateChanged(int i) {
    }

    public void onLDWSensitivityChange(int i) {
    }

    public void onLKSFaultStateStateChanged(int i) {
    }

    public void onLaneAssistTypeChanged(int i) {
    }

    public void onLaneCenteringControlFault(byte b) {
    }

    public void onLaneDeparturePreventionFault(byte b) {
    }

    public void onLaneDepartureWarnFault(byte b) {
    }

    public void onLaneKeepFault(byte b) {
    }

    public void onLeftBehindReminderStateChanged(int i) {
    }

    public void onLimitSpeedChangeFault(byte b) {
    }

    public void onLowSpeedEmergencyBrakingStateChanged(int i) {
    }

    public void onMOISFault(byte b) {
    }

    public void onMOISSwitchChange(int i) {
    }

    public void onMOISSystemChange(int i) {
    }

    public void onMechanismPositionChanged(int i) {
    }

    public void onMistakeGasStateChanged(int i) {
    }

    public void onNavigationPilotFault(byte b) {
    }

    public void onNrbanNaviFault(byte b) {
    }

    public void onOpenDoorAlertStateChanged(int i) {
    }

    public void onPredictCrashAlert138ModeChanged(int i) {
    }

    public void onPredictCrashAlert32DModeChanged(int i) {
    }

    @Deprecated
    public void onPredictCrashAlertModeChanged(int i) {
    }

    public void onPredictCrashAlertStateChange(int i) {
    }

    public void onRainOpenFogLightStateChanged(int i) {
    }

    public void onRearCollisionWarnFault(byte b) {
    }

    public void onRearCrashAlertStateChanged(int i) {
    }

    public void onRearCrossAlartFault(byte b) {
    }

    public void onRearCrossBrakeFault(byte b) {
    }

    public void onRearTrafficBrakeAlertStateChanged(int i) {
    }

    public void onRearTrafficCrossAlertStateChanged(int i) {
    }

    public void onRecommendRestSpotStateChanged(int i) {
    }

    public void onRedLightViolationWarningStateChanged(int i) {
    }

    public void onRelieveFatigueStateChanged(int i) {
    }

    public void onRemoteControlDriveCloseTops(int i) {
    }

    public void onRequestHUNPClose(int i) {
    }

    public void onSideDistanceWarningStateChanged(int i) {
    }

    public void onSpeedLimitRange2B4Changed(int i) {
    }

    public void onSpeedLimitRange2D5Changed(int i) {
    }

    @Deprecated
    public void onSpeedLimitRangeChanged(int i) {
    }

    public void onSteepDescentSlowDownStateChanged(int i) {
    }

    public void onTSRLitSpdChgInformStateChanged(int i) {
    }

    public void onTSRLitSpdChgSwitchStateChanged(int i) {
    }

    public void onTemperatureWhileFatigueChanged(int i) {
    }

    public void onTrafficAttentionFault(byte b) {
    }

    public void onTrafficIncidentWarningStateChanged(int i) {
    }

    public void onTrafficJamWarningStateChanged(int i) {
    }

    public void onTrafficLightInfoPushStateChanged(int i) {
    }

    public void onTrafficLightRecognize2B4StateChanged(int i) {
    }

    public void onTrafficLightRecognize2D5StateChanged(int i) {
    }

    @Deprecated
    public void onTrafficLightRecognizeStateChanged(int i) {
    }

    public void onTrafficSignRecognize2B4StateChanged(int i) {
    }

    public void onTrafficSignRecognize2D5StateChanged(int i) {
    }

    public void onTrafficSignRecognize316StateChanged(int i) {
    }

    @Deprecated
    public void onTrafficSignRecognizeStateChanged(int i) {
    }

    public void onTrafficSignalWarningStateChanged(int i) {
    }

    public void onTsrFault(byte b) {
    }

    public void onUrbanAIAssistanceStateChanged(int i) {
    }

    public void onVRUFaultStateStateChanged(int i) {
    }

    public void onVerifyModeChanged(int i) {
    }
}

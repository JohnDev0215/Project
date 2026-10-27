package android.hardware.bydauto.dipilot;

import android.hardware.IBYDAutoListener;
import android.util.Log;
import spi.e;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoDiPilotListener implements IBYDAutoListener {
    public static final String TAG = "AbsBYDAutoDiPilotListener";

    public void onADASOnlineStateChanged(int i) {
        e.r("onADASOnlineStateChanged  state=", i, TAG);
    }

    public void onADASSoftStateChanged(int i) {
        e.r("onADASSoftStateChanged: state = ", i, TAG);
    }

    public void onAEBDecActiveChanged(int i) {
        e.r("onAEBDecActiveChanged ", i, TAG);
    }

    public void onAEBFaultStateStateChanged(int i) {
        e.r("onAEBFaultStateStateChanged:", i, TAG);
    }

    public void onAINavigationStateChanged(int i) {
        Log.v(TAG, "onAINavigationStateChanged");
    }

    public void onAbnormalVehicleWarningStateChanged(int i) {
        e.r("onAbnormalVehicleWarningStateChanged:", i, TAG);
    }

    public void onAdaptiveFrontLightFault(byte b) {
        e.r("onAdaptiveFrontLightFault ", b, TAG);
    }

    public void onAdativeCruiseControlFault(byte b) {
        e.r("onAdativeCruiseControlFault ", b, TAG);
    }

    public void onAiCareStateChanged(int i) {
        Log.v(TAG, "onAiCareStateChanged");
    }

    public void onAiDistanceLight2B4StateChanged(int i) {
        e.r("onAiDistanceLight2B4StateChanged  state=", i, TAG);
    }

    public void onAiDistanceLight316StateChanged(int i) {
        e.r("onAiDistanceLight316StateChanged  state=", i, TAG);
    }

    @Deprecated
    public void onAiDistanceLightStateChanged(int i) {
        Log.v(TAG, "onAiDistanceLightStateChanged");
    }

    public void onAiEmergencyBrakeStateChanged(int i) {
        Log.v(TAG, "onAiEmergencyBrakeStateChanged");
    }

    public void onAiMatrixLight3ACStateChanged(int i) {
        e.r("onAiMatrixLight3ACStateChanged  state=", i, TAG);
    }

    public void onAiMatrixLight3ADStateChanged(int i) {
        e.r("onAiMatrixLight3ADStateChanged  state=", i, TAG);
    }

    public void onAiMatrixLightStateChanged(int i) {
        Log.v(TAG, "onAiMatrixLightStateChanged");
    }

    public void onAiTurnLightStateChanged(int i) {
        Log.v(TAG, "onAiTurnLightStateChanged");
    }

    public void onAlarmTypeChanged(int i) {
        Log.v(TAG, "onAlarmTypeChanged");
    }

    public void onAttentionDetectionStateChanged(int i) {
        Log.v(TAG, "onAttentionDetectionStateChanged");
    }

    public void onAutoEmergencyFault(byte b) {
        e.r("onAutoEmergencyFault ", b, TAG);
    }

    public void onAutoParkingStateChanged(int i) {
        Log.v(TAG, "onAutoParkingStateChanged");
    }

    public void onBSISFault(byte b) {
        e.r("onBSISFault ", b, TAG);
    }

    public void onBSISSwitchChange(int i) {
        e.r("onBSISSwitchChange", i, TAG);
    }

    public void onBSISSystemChange(int i) {
        e.r("onBSISSystemChange", i, TAG);
    }

    public void onBlindFault(byte b) {
        e.r("onBlindFault ", b, TAG);
    }

    public void onBlindMonitorStateChanged(int i) {
        Log.v(TAG, "onBlindMonitorStateChanged");
    }

    public void onBlindMonitorStateChanged1(int i) {
        e.r("onBlindMonitorStateChanged1  state=", i, TAG);
    }

    public void onBlindMonitorStateChanged2(int i) {
        e.r("onBlindMonitorStateChanged2  state=", i, TAG);
    }

    public void onChargerCoverStateChanged(int i) {
        Log.v(TAG, "onChargerCoverStateChanged");
    }

    public void onChildCareStateChanged(int i) {
        Log.v(TAG, "onChildCareStateChanged");
    }

    public void onCollisionWarningStateChanged(int i) {
        e.r("onCollisionWarningStateChanged:", i, TAG);
    }

    public void onControlLossWarningStateChanged(int i) {
        e.r("onControlLossWarningStateChanged:", i, TAG);
    }

    @Deprecated
    public void onCpdDelayStatusChanged(int i) {
        e.r("onCpdDelayStatusChanged  status=", i, TAG);
    }

    public void onCpdStatusChanged(int i) {
        e.r("onCpdStatusChanged  status=", i, TAG);
    }

    public void onCpdWarningStateChanged(int i) {
        e.r("onCpdWarningStateChanged ", i, TAG);
    }

    public void onCpdWorkingStatusChanged(int i) {
        e.r("onCpdWorkingStatusChanged ", i, TAG);
    }

    public void onCurveWarnFault(byte b) {
        e.r("onCurveWarnFault ", b, TAG);
    }

    public void onDangerousActionDetectionStateChanged(int i) {
        Log.v(TAG, "onDangerousActionDetectionStateChanged");
    }

    public void onDistractionDetectionAidStateChanged(int i) {
        Log.v(TAG, "onDistractionDetectionAidStateChanged");
    }

    public void onDmsStatusChanged(int i) {
        e.r("onDmsStatusChanged  status=", i, TAG);
    }

    public void onDnpStatusChanged(int i) {
        e.r("onDnpStatusChanged:", i, TAG);
    }

    public void onDoNotPassWarningStateChanged(int i) {
        e.r("onDoNotPassWarningStateChanged:", i, TAG);
    }

    public void onDoorOpenFault(byte b) {
        e.r("onDoorOpenFault ", b, TAG);
    }

    public void onDriveDepartureStateChanged(int i) {
        Log.v(TAG, "onDriveDepartureStateChanged");
    }

    public void onDriveLevelChanged(int i) {
        Log.v(TAG, "onDriveLevelChanged");
    }

    public void onDriveRemindStateChanged(int i) {
        Log.v(TAG, "onDriveRemindStateChanged");
    }

    public void onDrivieDepartureStateChanged(int i) {
        Log.v(TAG, "onDrivieDepartureStateChanged");
    }

    public void onDrivieRemindStateChanged(int i) {
        Log.v(TAG, "onDrivieRemindStateChanged");
    }

    public void onE2EAIAssistanceStateChanged(int i) {
        Log.v(TAG, "onE2EAIAssistanceStateChanged");
    }

    public void onELKTakeOverStateChanged(int i) {
        e.r("onELKTakeOverStateChanged: state = ", i, TAG);
    }

    public void onElecCarBodyStablySysStateChanged(int i) {
        Log.v(TAG, "onElecCarBodyStablySysStateChanged");
    }

    public void onEmergencyBrakeWarningStateChanged(int i) {
        e.r("onEmergencyBrakeWarningStateChanged:", i, TAG);
    }

    public void onEmergencyLaneAssistance2F0StateChanged(int i) {
        e.r("onEmergencyLaneAssistance2F0StateChanged  state=", i, TAG);
    }

    public void onEmergencyLaneAssistance32FStateChanged(int i) {
        e.r("onEmergencyLaneAssistance32FStateChanged  state=", i, TAG);
    }

    @Deprecated
    public void onEmergencyLaneAssistanceStateChanged(int i) {
        Log.v(TAG, "onEmergencyLaneAssistanceStateChanged");
    }

    public void onEmergencyLaneKeepFault(byte b) {
        e.r("onEmergencyLaneKeepFault ", b, TAG);
    }

    public void onEmergencyVehicleWarningStateChanged(int i) {
        e.r("onEmergencyVehicleWarningStateChanged:", i, TAG);
    }

    public void onEspOffKeyStatusChanged(int i) {
        e.r("onEspOffKeyStatusChanged ", i, TAG);
    }

    public void onFCWFaultStateChanged(int i) {
        e.r("onFCWFaultStateChanged:", i, TAG);
    }

    public void onFatigueDetectionAidStateChanged(int i) {
        Log.v(TAG, "onFatigueDetectionAidStateChanged");
    }

    public void onFatigueMonitorChange(int i) {
        e.r("onFatigueMonitorChange:", i, TAG);
    }

    public void onFaultHandlerStateChanged(int i) {
        Log.v(TAG, "onFaultHandlerStateChanged");
    }

    public void onForwardCollisionWarnFault(byte b) {
        e.r("onForwardCollisionWarnFault ", b, TAG);
    }

    public void onFrontCarStartStateChange(int i) {
        Log.v(TAG, "onFrontCarStartStateChange");
    }

    public void onFrontCrossAlarkFault(byte b) {
        e.r("onFrontCrossAlarkFault ", b, TAG);
    }

    public void onFrontCrossBrakeFault(byte b) {
        e.r("onFrontCrossBrakeFault ", b, TAG);
    }

    public void onFrontTrafficBrakeAlertStateChanged(int i) {
        Log.v(TAG, "onFrontTrafficBrakeAlertStateChanged");
    }

    public void onFrontTrafficCrossAlertStateChanged(int i) {
        Log.v(TAG, "onFrontTrafficCrossAlertStateChanged");
    }

    public void onGazePerceptionStateChanged(int i) {
        Log.v(TAG, "onGazePerceptionStateChanged");
    }

    public void onGestureRecognitionStateChanged(int i) {
        Log.v(TAG, "onGestureRecognitionStateChanged");
    }

    public void onGreenLightOptimalSpeedAdvisoryStateChanged(int i) {
        e.r("onGreenLightOptimalSpeedAdvisoryStateChanged:", i, TAG);
    }

    public void onHighNaviFault(byte b) {
        e.r("onHighNaviFault ", b, TAG);
    }

    public void onHighSpeedAINavigationStateChanged(int i) {
        Log.v(TAG, "onHighSpeedAINavigationStateChanged");
    }

    public void onHighSpeedOpenWindowStateChanged(int i) {
        Log.v(TAG, "onHighSpeedOpenWindowStateChanged");
    }

    public void onISLIWarningLevelSwitchStatusChanged(int i) {
        e.r("onISLIWarningLevelSwitchStatusChanged ", i, TAG);
    }

    public void onISLIWarningStateChanged(int i) {
        e.r("onISLIWarningStateChanged: state = ", i, TAG);
    }

    public void onIntelligentLimitFaultCall(byte b) {
        e.r("onIntelligentLimitFaultCall ", b, TAG);
    }

    public void onIntelligentLimitFaultController(byte b) {
        e.r("onIntelligentLimitFaultController ", b, TAG);
    }

    public void onIntelligentSpeedLimitAlert2B4StateChanged(int i) {
        e.r("onIntelligentSpeedLimitAlert2B4StateChanged  state=", i, TAG);
    }

    public void onIntelligentSpeedLimitAlert2D5StateChanged(int i) {
        e.r("onIntelligentSpeedLimitAlert2D5StateChanged  state=", i, TAG);
    }

    @Deprecated
    public void onIntelligentSpeedLimitAlertStateChanged(int i) {
        Log.v(TAG, "onIntelligentSpeedLimitAlertStateChanged");
    }

    public void onIntelligentSpeedLimitControl2D5StateChanged(int i) {
        e.r("onIntelligentSpeedLimitControl2D5StateChanged  state=", i, TAG);
    }

    public void onIntelligentSpeedLimitControl32FStateChanged(int i) {
        e.r("onIntelligentSpeedLimitControl32FStateChanged  state=", i, TAG);
    }

    @Deprecated
    public void onIntelligentSpeedLimitControlStateChanged(int i) {
        Log.v(TAG, "onIntelligentSpeedLimitControlStateChanged");
    }

    public void onInteractiveLaneChangeAssistanceStateChanged(int i) {
        Log.v(TAG, "onInteractiveLaneChangeAssistanceStateChanged");
    }

    public void onInteractiveLaneChangeFault(byte b) {
        e.r("onInteractiveLaneChangeFault ", b, TAG);
    }

    public void onIntersectionCollisionAlertStateChanged(int i) {
        e.r("onIntersectionCollisionAlertStateChanged:", i, TAG);
    }

    public void onLDWFaultStateChanged(int i) {
        e.r("onLDWFaultStateChanged:", i, TAG);
    }

    public void onLDWSensitivityChange(int i) {
        e.r("onLDWSensitivityChange", i, TAG);
    }

    public void onLKSFaultStateStateChanged(int i) {
        e.r("onLKSFaultStateStateChanged:", i, TAG);
    }

    public void onLaneAssistTypeChanged(int i) {
        Log.v(TAG, "onLaneAssistTypeChanged");
    }

    public void onLaneCenteringControlFault(byte b) {
        e.r("onLaneCenteringControlFault ", b, TAG);
    }

    public void onLaneDeparturePreventionFault(byte b) {
        e.r("onLaneDeparturePreventionFault ", b, TAG);
    }

    public void onLaneDepartureWarnFault(byte b) {
        e.r("onLaneDepartureWarnFault ", b, TAG);
    }

    public void onLaneKeepFault(byte b) {
        e.r("onLaneKeepFault ", b, TAG);
    }

    public void onLeftBehindReminderStateChanged(int i) {
        Log.v(TAG, "onLeftBehindReminderStateChanged");
    }

    public void onLimitSpeedChangeFault(byte b) {
        e.r("onLimitSpeedChangeFault ", b, TAG);
    }

    public void onLowSpeedEmergencyBrakingStateChanged(int i) {
        e.r("onLowSpeedEmergencyBrakingStateChanged:", i, TAG);
    }

    public void onMOISFault(byte b) {
        e.r("onMOISFault ", b, TAG);
    }

    public void onMOISSwitchChange(int i) {
        e.r("onMOISSwitchChange", i, TAG);
    }

    public void onMOISSystemChange(int i) {
        e.r("onMOISSystemChange", i, TAG);
    }

    public void onMechanismPositionChanged(int i) {
        e.r("onMechanismPositionChanged:", i, TAG);
    }

    public void onMistakeGasStateChanged(int i) {
        Log.v(TAG, "onMistakeGasStateChanged");
    }

    public void onNavigationPilotFault(byte b) {
        e.r("onNavigationPilotFault ", b, TAG);
    }

    public void onNrbanNaviFault(byte b) {
        e.r("onNrbanNaviFault ", b, TAG);
    }

    public void onOpenDoorAlertStateChanged(int i) {
        Log.v(TAG, "onOpenDoorAlertStateChanged");
    }

    public void onPredictCrashAlert138ModeChanged(int i) {
        e.r("onPredictCrashAlert138ModeChanged  mode=", i, TAG);
    }

    public void onPredictCrashAlert32DModeChanged(int i) {
        e.r("onPredictCrashAlert32DModeChanged  mode=", i, TAG);
    }

    @Deprecated
    public void onPredictCrashAlertModeChanged(int i) {
        Log.v(TAG, "onPredictCrashAlertModeChanged");
    }

    public void onPredictCrashAlertStateChange(int i) {
        Log.v(TAG, "onPredictCrashAlertStateChange");
    }

    public void onRainOpenFogLightStateChanged(int i) {
        Log.v(TAG, "onRainOpenFogLightStateChanged");
    }

    public void onRearCollisionWarnFault(byte b) {
        e.r("onRearCollisionWarnFault ", b, TAG);
    }

    public void onRearCrashAlertStateChanged(int i) {
        Log.v(TAG, "onRearCrashAlertStateChanged");
    }

    public void onRearCrossAlartFault(byte b) {
        e.r("onRearCrossAlartFault ", b, TAG);
    }

    public void onRearCrossBrakeFault(byte b) {
        e.r("onRearCrossBrakeFault ", b, TAG);
    }

    public void onRearTrafficBrakeAlertStateChanged(int i) {
        Log.v(TAG, "onRearTrafficBrakeAlertStateChanged");
    }

    public void onRearTrafficCrossAlertStateChanged(int i) {
        Log.v(TAG, "onRearTrafficCrossAlertStateChanged");
    }

    public void onRecommendRestSpotStateChanged(int i) {
        Log.v(TAG, "onRecommendRestSpotStateChanged");
    }

    public void onRedLightViolationWarningStateChanged(int i) {
        e.r("onRedLightViolationWarningStateChanged:", i, TAG);
    }

    public void onRelieveFatigueStateChanged(int i) {
        Log.v(TAG, "onRelieveFatigueStateChanged");
    }

    public void onRemoteControlDriveCloseTops(int i) {
        Log.v(TAG, "onRemoteControlDriveCloseTops");
    }

    public void onRequestHUNPClose(int i) {
        Log.v(TAG, "onRequestHUNPClose");
    }

    public void onSideDistanceWarningStateChanged(int i) {
        e.r("onSideDistanceWarningStateChanged:", i, TAG);
    }

    public void onSpeedLimitRange2B4Changed(int i) {
        e.r("onSpeedLimitRange2B4Changed  value=", i, TAG);
    }

    public void onSpeedLimitRange2D5Changed(int i) {
        e.r("onSpeedLimitRange2D5Changed  value=", i, TAG);
    }

    @Deprecated
    public void onSpeedLimitRangeChanged(int i) {
        Log.v(TAG, "onSpeedLimitRangeChanged");
    }

    public void onSteepDescentSlowDownStateChanged(int i) {
        Log.v(TAG, "onSteepDescentSlowDownStateChanged");
    }

    public void onTSRLitSpdChgInformStateChanged(int i) {
        e.r("onTSRLitSpdChgInformStateChanged: state = ", i, TAG);
    }

    public void onTSRLitSpdChgSwitchStateChanged(int i) {
        e.r("onTSRLitSpdChgSwitchStateChanged: state = ", i, TAG);
    }

    public void onTemperatureWhileFatigueChanged(int i) {
        Log.v(TAG, "onTemperatureWhileFatigueChanged");
    }

    public void onTrafficAttentionFault(byte b) {
        e.r("onTrafficAttentionFault ", b, TAG);
    }

    public void onTrafficIncidentWarningStateChanged(int i) {
        e.r("onTrafficIncidentWarningStateChanged:", i, TAG);
    }

    public void onTrafficJamWarningStateChanged(int i) {
        e.r("onTrafficJamWarningStateChanged:", i, TAG);
    }

    public void onTrafficLightInfoPushStateChanged(int i) {
        e.r("onTrafficLightInfoPushStateChanged:", i, TAG);
    }

    public void onTrafficLightRecognize2B4StateChanged(int i) {
        e.r("onTrafficLightRecognize2B4StateChanged  state=", i, TAG);
    }

    public void onTrafficLightRecognize2D5StateChanged(int i) {
        e.r("onTrafficLightRecognize2D5StateChanged  state=", i, TAG);
    }

    @Deprecated
    public void onTrafficLightRecognizeStateChanged(int i) {
        Log.v(TAG, "onTrafficLightRecognizeStateChanged");
    }

    public void onTrafficSignRecognize2B4StateChanged(int i) {
        e.r("onTrafficSignRecognize2B4StateChanged  state=", i, TAG);
    }

    public void onTrafficSignRecognize2D5StateChanged(int i) {
        e.r("onTrafficSignRecognize2D5StateChanged  state=", i, TAG);
    }

    public void onTrafficSignRecognize316StateChanged(int i) {
        e.r("onTrafficSignRecognize316StateChanged  state=", i, TAG);
    }

    @Deprecated
    public void onTrafficSignRecognizeStateChanged(int i) {
        Log.v(TAG, "onTrafficSignRecognizeStateChanged");
    }

    public void onTrafficSignalWarningStateChanged(int i) {
        e.r("onTrafficSignalWarningStateChanged:", i, TAG);
    }

    public void onTsrFault(byte b) {
        e.r("onTsrFault ", b, TAG);
    }

    public void onUrbanAIAssistanceStateChanged(int i) {
        Log.v(TAG, "onUrbanAIAssistanceStateChanged");
    }

    public void onVRUFaultStateStateChanged(int i) {
        e.r("onVRUFaultStateStateChanged:", i, TAG);
    }

    public void onVerifyModeChanged(int i) {
        Log.v(TAG, "onVerifyModeChanged");
    }
}

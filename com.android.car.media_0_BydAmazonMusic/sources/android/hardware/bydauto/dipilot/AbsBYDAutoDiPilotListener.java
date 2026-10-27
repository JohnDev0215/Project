package android.hardware.bydauto.dipilot;

import android.hardware.IBYDAutoListener;
import android.util.Log;
import spi.c;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoDiPilotListener implements IBYDAutoListener {
    public static final String TAG = "AbsBYDAutoDiPilotListener";

    public void onADASOnlineStateChanged(int i) {
        c.u("onADASOnlineStateChanged  state=", i, TAG);
    }

    public void onADASSoftStateChanged(int i) {
        c.u("onADASSoftStateChanged: state = ", i, TAG);
    }

    public void onAEBDecActiveChanged(int i) {
        c.u("onAEBDecActiveChanged ", i, TAG);
    }

    public void onAEBFaultStateStateChanged(int i) {
        c.u("onAEBFaultStateStateChanged:", i, TAG);
    }

    public void onAINavigationStateChanged(int i) {
        Log.v(TAG, "onAINavigationStateChanged");
    }

    public void onAbnormalVehicleWarningStateChanged(int i) {
        c.u("onAbnormalVehicleWarningStateChanged:", i, TAG);
    }

    public void onAdaptiveFrontLightFault(byte b) {
        c.u("onAdaptiveFrontLightFault ", b, TAG);
    }

    public void onAdativeCruiseControlFault(byte b) {
        c.u("onAdativeCruiseControlFault ", b, TAG);
    }

    public void onAiCareStateChanged(int i) {
        Log.v(TAG, "onAiCareStateChanged");
    }

    public void onAiDistanceLight2B4StateChanged(int i) {
        c.u("onAiDistanceLight2B4StateChanged  state=", i, TAG);
    }

    public void onAiDistanceLight316StateChanged(int i) {
        c.u("onAiDistanceLight316StateChanged  state=", i, TAG);
    }

    @Deprecated
    public void onAiDistanceLightStateChanged(int i) {
        Log.v(TAG, "onAiDistanceLightStateChanged");
    }

    public void onAiEmergencyBrakeStateChanged(int i) {
        Log.v(TAG, "onAiEmergencyBrakeStateChanged");
    }

    public void onAiMatrixLight3ACStateChanged(int i) {
        c.u("onAiMatrixLight3ACStateChanged  state=", i, TAG);
    }

    public void onAiMatrixLight3ADStateChanged(int i) {
        c.u("onAiMatrixLight3ADStateChanged  state=", i, TAG);
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
        c.u("onAutoEmergencyFault ", b, TAG);
    }

    public void onAutoParkingStateChanged(int i) {
        Log.v(TAG, "onAutoParkingStateChanged");
    }

    public void onBSISFault(byte b) {
        c.u("onBSISFault ", b, TAG);
    }

    public void onBSISSwitchChange(int i) {
        c.u("onBSISSwitchChange", i, TAG);
    }

    public void onBSISSystemChange(int i) {
        c.u("onBSISSystemChange", i, TAG);
    }

    public void onBlindFault(byte b) {
        c.u("onBlindFault ", b, TAG);
    }

    public void onBlindMonitorStateChanged(int i) {
        Log.v(TAG, "onBlindMonitorStateChanged");
    }

    public void onBlindMonitorStateChanged1(int i) {
        c.u("onBlindMonitorStateChanged1  state=", i, TAG);
    }

    public void onBlindMonitorStateChanged2(int i) {
        c.u("onBlindMonitorStateChanged2  state=", i, TAG);
    }

    public void onChargerCoverStateChanged(int i) {
        Log.v(TAG, "onChargerCoverStateChanged");
    }

    public void onChildCareStateChanged(int i) {
        Log.v(TAG, "onChildCareStateChanged");
    }

    public void onCollisionWarningStateChanged(int i) {
        c.u("onCollisionWarningStateChanged:", i, TAG);
    }

    public void onControlLossWarningStateChanged(int i) {
        c.u("onControlLossWarningStateChanged:", i, TAG);
    }

    @Deprecated
    public void onCpdDelayStatusChanged(int i) {
        c.u("onCpdDelayStatusChanged  status=", i, TAG);
    }

    public void onCpdStatusChanged(int i) {
        c.u("onCpdStatusChanged  status=", i, TAG);
    }

    public void onCpdWarningStateChanged(int i) {
        c.u("onCpdWarningStateChanged ", i, TAG);
    }

    public void onCpdWorkingStatusChanged(int i) {
        c.u("onCpdWorkingStatusChanged ", i, TAG);
    }

    public void onCurveWarnFault(byte b) {
        c.u("onCurveWarnFault ", b, TAG);
    }

    public void onDangerousActionDetectionStateChanged(int i) {
        Log.v(TAG, "onDangerousActionDetectionStateChanged");
    }

    public void onDistractionDetectionAidStateChanged(int i) {
        Log.v(TAG, "onDistractionDetectionAidStateChanged");
    }

    public void onDmsStatusChanged(int i) {
        c.u("onDmsStatusChanged  status=", i, TAG);
    }

    public void onDnpStatusChanged(int i) {
        c.u("onDnpStatusChanged:", i, TAG);
    }

    public void onDoNotPassWarningStateChanged(int i) {
        c.u("onDoNotPassWarningStateChanged:", i, TAG);
    }

    public void onDoorOpenFault(byte b) {
        c.u("onDoorOpenFault ", b, TAG);
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
        c.u("onELKTakeOverStateChanged: state = ", i, TAG);
    }

    public void onElecCarBodyStablySysStateChanged(int i) {
        Log.v(TAG, "onElecCarBodyStablySysStateChanged");
    }

    public void onEmergencyBrakeWarningStateChanged(int i) {
        c.u("onEmergencyBrakeWarningStateChanged:", i, TAG);
    }

    public void onEmergencyLaneAssistance2F0StateChanged(int i) {
        c.u("onEmergencyLaneAssistance2F0StateChanged  state=", i, TAG);
    }

    public void onEmergencyLaneAssistance32FStateChanged(int i) {
        c.u("onEmergencyLaneAssistance32FStateChanged  state=", i, TAG);
    }

    @Deprecated
    public void onEmergencyLaneAssistanceStateChanged(int i) {
        Log.v(TAG, "onEmergencyLaneAssistanceStateChanged");
    }

    public void onEmergencyLaneKeepFault(byte b) {
        c.u("onEmergencyLaneKeepFault ", b, TAG);
    }

    public void onEmergencyVehicleWarningStateChanged(int i) {
        c.u("onEmergencyVehicleWarningStateChanged:", i, TAG);
    }

    public void onEspOffKeyStatusChanged(int i) {
        c.u("onEspOffKeyStatusChanged ", i, TAG);
    }

    public void onFCWFaultStateChanged(int i) {
        c.u("onFCWFaultStateChanged:", i, TAG);
    }

    public void onFatigueDetectionAidStateChanged(int i) {
        Log.v(TAG, "onFatigueDetectionAidStateChanged");
    }

    public void onFatigueMonitorChange(int i) {
        c.u("onFatigueMonitorChange:", i, TAG);
    }

    public void onFaultHandlerStateChanged(int i) {
        Log.v(TAG, "onFaultHandlerStateChanged");
    }

    public void onForwardCollisionWarnFault(byte b) {
        c.u("onForwardCollisionWarnFault ", b, TAG);
    }

    public void onFrontCarStartStateChange(int i) {
        Log.v(TAG, "onFrontCarStartStateChange");
    }

    public void onFrontCrossAlarkFault(byte b) {
        c.u("onFrontCrossAlarkFault ", b, TAG);
    }

    public void onFrontCrossBrakeFault(byte b) {
        c.u("onFrontCrossBrakeFault ", b, TAG);
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
        c.u("onGreenLightOptimalSpeedAdvisoryStateChanged:", i, TAG);
    }

    public void onHighNaviFault(byte b) {
        c.u("onHighNaviFault ", b, TAG);
    }

    public void onHighSpeedAINavigationStateChanged(int i) {
        Log.v(TAG, "onHighSpeedAINavigationStateChanged");
    }

    public void onHighSpeedOpenWindowStateChanged(int i) {
        Log.v(TAG, "onHighSpeedOpenWindowStateChanged");
    }

    public void onISLIWarningLevelSwitchStatusChanged(int i) {
        c.u("onISLIWarningLevelSwitchStatusChanged ", i, TAG);
    }

    public void onISLIWarningStateChanged(int i) {
        c.u("onISLIWarningStateChanged: state = ", i, TAG);
    }

    public void onIntelligentLimitFaultCall(byte b) {
        c.u("onIntelligentLimitFaultCall ", b, TAG);
    }

    public void onIntelligentLimitFaultController(byte b) {
        c.u("onIntelligentLimitFaultController ", b, TAG);
    }

    public void onIntelligentSpeedLimitAlert2B4StateChanged(int i) {
        c.u("onIntelligentSpeedLimitAlert2B4StateChanged  state=", i, TAG);
    }

    public void onIntelligentSpeedLimitAlert2D5StateChanged(int i) {
        c.u("onIntelligentSpeedLimitAlert2D5StateChanged  state=", i, TAG);
    }

    @Deprecated
    public void onIntelligentSpeedLimitAlertStateChanged(int i) {
        Log.v(TAG, "onIntelligentSpeedLimitAlertStateChanged");
    }

    public void onIntelligentSpeedLimitControl2D5StateChanged(int i) {
        c.u("onIntelligentSpeedLimitControl2D5StateChanged  state=", i, TAG);
    }

    public void onIntelligentSpeedLimitControl32FStateChanged(int i) {
        c.u("onIntelligentSpeedLimitControl32FStateChanged  state=", i, TAG);
    }

    @Deprecated
    public void onIntelligentSpeedLimitControlStateChanged(int i) {
        Log.v(TAG, "onIntelligentSpeedLimitControlStateChanged");
    }

    public void onInteractiveLaneChangeAssistanceStateChanged(int i) {
        Log.v(TAG, "onInteractiveLaneChangeAssistanceStateChanged");
    }

    public void onInteractiveLaneChangeFault(byte b) {
        c.u("onInteractiveLaneChangeFault ", b, TAG);
    }

    public void onIntersectionCollisionAlertStateChanged(int i) {
        c.u("onIntersectionCollisionAlertStateChanged:", i, TAG);
    }

    public void onLDWFaultStateChanged(int i) {
        c.u("onLDWFaultStateChanged:", i, TAG);
    }

    public void onLDWSensitivityChange(int i) {
        c.u("onLDWSensitivityChange", i, TAG);
    }

    public void onLKSFaultStateStateChanged(int i) {
        c.u("onLKSFaultStateStateChanged:", i, TAG);
    }

    public void onLaneAssistTypeChanged(int i) {
        Log.v(TAG, "onLaneAssistTypeChanged");
    }

    public void onLaneCenteringControlFault(byte b) {
        c.u("onLaneCenteringControlFault ", b, TAG);
    }

    public void onLaneDeparturePreventionFault(byte b) {
        c.u("onLaneDeparturePreventionFault ", b, TAG);
    }

    public void onLaneDepartureWarnFault(byte b) {
        c.u("onLaneDepartureWarnFault ", b, TAG);
    }

    public void onLaneKeepFault(byte b) {
        c.u("onLaneKeepFault ", b, TAG);
    }

    public void onLeftBehindReminderStateChanged(int i) {
        Log.v(TAG, "onLeftBehindReminderStateChanged");
    }

    public void onLimitSpeedChangeFault(byte b) {
        c.u("onLimitSpeedChangeFault ", b, TAG);
    }

    public void onLowSpeedEmergencyBrakingStateChanged(int i) {
        c.u("onLowSpeedEmergencyBrakingStateChanged:", i, TAG);
    }

    public void onMOISFault(byte b) {
        c.u("onMOISFault ", b, TAG);
    }

    public void onMOISSwitchChange(int i) {
        c.u("onMOISSwitchChange", i, TAG);
    }

    public void onMOISSystemChange(int i) {
        c.u("onMOISSystemChange", i, TAG);
    }

    public void onMechanismPositionChanged(int i) {
        c.u("onMechanismPositionChanged:", i, TAG);
    }

    public void onMistakeGasStateChanged(int i) {
        Log.v(TAG, "onMistakeGasStateChanged");
    }

    public void onNavigationPilotFault(byte b) {
        c.u("onNavigationPilotFault ", b, TAG);
    }

    public void onNrbanNaviFault(byte b) {
        c.u("onNrbanNaviFault ", b, TAG);
    }

    public void onOpenDoorAlertStateChanged(int i) {
        Log.v(TAG, "onOpenDoorAlertStateChanged");
    }

    public void onPredictCrashAlert138ModeChanged(int i) {
        c.u("onPredictCrashAlert138ModeChanged  mode=", i, TAG);
    }

    public void onPredictCrashAlert32DModeChanged(int i) {
        c.u("onPredictCrashAlert32DModeChanged  mode=", i, TAG);
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
        c.u("onRearCollisionWarnFault ", b, TAG);
    }

    public void onRearCrashAlertStateChanged(int i) {
        Log.v(TAG, "onRearCrashAlertStateChanged");
    }

    public void onRearCrossAlartFault(byte b) {
        c.u("onRearCrossAlartFault ", b, TAG);
    }

    public void onRearCrossBrakeFault(byte b) {
        c.u("onRearCrossBrakeFault ", b, TAG);
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
        c.u("onRedLightViolationWarningStateChanged:", i, TAG);
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
        c.u("onSideDistanceWarningStateChanged:", i, TAG);
    }

    public void onSpeedLimitRange2B4Changed(int i) {
        c.u("onSpeedLimitRange2B4Changed  value=", i, TAG);
    }

    public void onSpeedLimitRange2D5Changed(int i) {
        c.u("onSpeedLimitRange2D5Changed  value=", i, TAG);
    }

    @Deprecated
    public void onSpeedLimitRangeChanged(int i) {
        Log.v(TAG, "onSpeedLimitRangeChanged");
    }

    public void onSteepDescentSlowDownStateChanged(int i) {
        Log.v(TAG, "onSteepDescentSlowDownStateChanged");
    }

    public void onTSRLitSpdChgInformStateChanged(int i) {
        c.u("onTSRLitSpdChgInformStateChanged: state = ", i, TAG);
    }

    public void onTSRLitSpdChgSwitchStateChanged(int i) {
        c.u("onTSRLitSpdChgSwitchStateChanged: state = ", i, TAG);
    }

    public void onTemperatureWhileFatigueChanged(int i) {
        Log.v(TAG, "onTemperatureWhileFatigueChanged");
    }

    public void onTrafficAttentionFault(byte b) {
        c.u("onTrafficAttentionFault ", b, TAG);
    }

    public void onTrafficIncidentWarningStateChanged(int i) {
        c.u("onTrafficIncidentWarningStateChanged:", i, TAG);
    }

    public void onTrafficJamWarningStateChanged(int i) {
        c.u("onTrafficJamWarningStateChanged:", i, TAG);
    }

    public void onTrafficLightInfoPushStateChanged(int i) {
        c.u("onTrafficLightInfoPushStateChanged:", i, TAG);
    }

    public void onTrafficLightRecognize2B4StateChanged(int i) {
        c.u("onTrafficLightRecognize2B4StateChanged  state=", i, TAG);
    }

    public void onTrafficLightRecognize2D5StateChanged(int i) {
        c.u("onTrafficLightRecognize2D5StateChanged  state=", i, TAG);
    }

    @Deprecated
    public void onTrafficLightRecognizeStateChanged(int i) {
        Log.v(TAG, "onTrafficLightRecognizeStateChanged");
    }

    public void onTrafficSignRecognize2B4StateChanged(int i) {
        c.u("onTrafficSignRecognize2B4StateChanged  state=", i, TAG);
    }

    public void onTrafficSignRecognize2D5StateChanged(int i) {
        c.u("onTrafficSignRecognize2D5StateChanged  state=", i, TAG);
    }

    public void onTrafficSignRecognize316StateChanged(int i) {
        c.u("onTrafficSignRecognize316StateChanged  state=", i, TAG);
    }

    @Deprecated
    public void onTrafficSignRecognizeStateChanged(int i) {
        Log.v(TAG, "onTrafficSignRecognizeStateChanged");
    }

    public void onTrafficSignalWarningStateChanged(int i) {
        c.u("onTrafficSignalWarningStateChanged:", i, TAG);
    }

    public void onTsrFault(byte b) {
        c.u("onTsrFault ", b, TAG);
    }

    public void onUrbanAIAssistanceStateChanged(int i) {
        Log.v(TAG, "onUrbanAIAssistanceStateChanged");
    }

    public void onVRUFaultStateStateChanged(int i) {
        c.u("onVRUFaultStateStateChanged:", i, TAG);
    }

    public void onVerifyModeChanged(int i) {
        Log.v(TAG, "onVerifyModeChanged");
    }
}

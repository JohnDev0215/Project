package android.hardware.bydauto.dipilot;

import android.hardware.IBYDAutoListener;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoDiPilotListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoDiPilotListener";

    public void onDrivieRemindStateChanged(int i) {
        Log.v(TAG, "onDrivieRemindStateChanged");
    }

    public void onDrivieDepartureStateChanged(int i) {
        Log.v(TAG, "onDrivieDepartureStateChanged");
    }

    public void onFrontCarStartStateChange(int i) {
        Log.v(TAG, "onFrontCarStartStateChange");
    }

    public void onFaultHandlerStateChanged(int i) {
        Log.v(TAG, "onFaultHandlerStateChanged");
    }

    public void onChargerCoverStateChanged(int i) {
        Log.v(TAG, "onChargerCoverStateChanged");
    }

    public void onAiTurnLightStateChanged(int i) {
        Log.v(TAG, "onAiTurnLightStateChanged");
    }

    public void onRainOpenFogLightStateChanged(int i) {
        Log.v(TAG, "onRainOpenFogLightStateChanged");
    }

    public void onHighSpeedOpenWindowStateChanged(int i) {
        Log.v(TAG, "onHighSpeedOpenWindowStateChanged");
    }

    public void onDriveLevelChanged(int i) {
        Log.v(TAG, "onDriveLevelChanged");
    }

    public void onHighSpeedAINavigationStateChanged(int i) {
        Log.v(TAG, "onHighSpeedAINavigationStateChanged");
    }

    public void onUrbanAIAssistanceStateChanged(int i) {
        Log.v(TAG, "onUrbanAIAssistanceStateChanged");
    }

    public void onE2EAIAssistanceStateChanged(int i) {
        Log.v(TAG, "onE2EAIAssistanceStateChanged");
    }

    public void onInteractiveLaneChangeAssistanceStateChanged(int i) {
        Log.v(TAG, "onInteractiveLaneChangeAssistanceStateChanged");
    }

    public void onAINavigationStateChanged(int i) {
        Log.v(TAG, "onAINavigationStateChanged");
    }

    public void onAlarmTypeChanged(int i) {
        Log.v(TAG, "onAlarmTypeChanged");
    }

    @Deprecated
    public void onTrafficSignRecognizeStateChanged(int i) {
        Log.v(TAG, "onTrafficSignRecognizeStateChanged");
    }

    public void onAiCareStateChanged(int i) {
        Log.v(TAG, "onAiCareStateChanged");
    }

    public void onMistakeGasStateChanged(int i) {
        Log.v(TAG, "onMistakeGasStateChanged");
    }

    public void onAiMatrixLightStateChanged(int i) {
        Log.v(TAG, "onAiMatrixLightStateChanged");
    }

    @Deprecated
    public void onAiDistanceLightStateChanged(int i) {
        Log.v(TAG, "onAiDistanceLightStateChanged");
    }

    @Deprecated
    public void onTrafficLightRecognizeStateChanged(int i) {
        Log.v(TAG, "onTrafficLightRecognizeStateChanged");
    }

    @Deprecated
    public void onEmergencyLaneAssistanceStateChanged(int i) {
        Log.v(TAG, "onEmergencyLaneAssistanceStateChanged");
    }

    public void onBlindMonitorStateChanged(int i) {
        Log.v(TAG, "onBlindMonitorStateChanged");
    }

    public void onOpenDoorAlertStateChanged(int i) {
        Log.v(TAG, "onOpenDoorAlertStateChanged");
    }

    public void onRearCrashAlertStateChanged(int i) {
        Log.v(TAG, "onRearCrashAlertStateChanged");
    }

    public void onRearTrafficCrossAlertStateChanged(int i) {
        Log.v(TAG, "onRearTrafficCrossAlertStateChanged");
    }

    public void onRearTrafficBrakeAlertStateChanged(int i) {
        Log.v(TAG, "onRearTrafficBrakeAlertStateChanged");
    }

    public void onAiEmergencyBrakeStateChanged(int i) {
        Log.v(TAG, "onAiEmergencyBrakeStateChanged");
    }

    @Deprecated
    public void onPredictCrashAlertModeChanged(int i) {
        Log.v(TAG, "onPredictCrashAlertModeChanged");
    }

    public void onFrontTrafficCrossAlertStateChanged(int i) {
        Log.v(TAG, "onFrontTrafficCrossAlertStateChanged");
    }

    public void onFrontTrafficBrakeAlertStateChanged(int i) {
        Log.v(TAG, "onFrontTrafficBrakeAlertStateChanged");
    }

    public void onElecCarBodyStablySysStateChanged(int i) {
        Log.v(TAG, "onElecCarBodyStablySysStateChanged");
    }

    public void onAutoParkingStateChanged(int i) {
        Log.v(TAG, "onAutoParkingStateChanged");
    }

    public void onSteepDescentSlowDownStateChanged(int i) {
        Log.v(TAG, "onSteepDescentSlowDownStateChanged");
    }

    public void onFatigueDetectionAidStateChanged(int i) {
        Log.v(TAG, "onFatigueDetectionAidStateChanged");
    }

    public void onRelieveFatigueStateChanged(int i) {
        Log.v(TAG, "onRelieveFatigueStateChanged");
    }

    public void onRecommendRestSpotStateChanged(int i) {
        Log.v(TAG, "onRecommendRestSpotStateChanged");
    }

    public void onTemperatureWhileFatigueChanged(int i) {
        Log.v(TAG, "onTemperatureWhileFatigueChanged");
    }

    public void onDistractionDetectionAidStateChanged(int i) {
        Log.v(TAG, "onDistractionDetectionAidStateChanged");
    }

    public void onAttentionDetectionStateChanged(int i) {
        Log.v(TAG, "onAttentionDetectionStateChanged");
    }

    public void onDangerousActionDetectionStateChanged(int i) {
        Log.v(TAG, "onDangerousActionDetectionStateChanged");
    }

    public void onGazePerceptionStateChanged(int i) {
        Log.v(TAG, "onGazePerceptionStateChanged");
    }

    public void onChildCareStateChanged(int i) {
        Log.v(TAG, "onChildCareStateChanged");
    }

    public void onGestureRecognitionStateChanged(int i) {
        Log.v(TAG, "onGestureRecognitionStateChanged");
    }

    public void onLeftBehindReminderStateChanged(int i) {
        Log.v(TAG, "onLeftBehindReminderStateChanged");
    }

    public void onRequestHUNPClose(int i) {
        Log.v(TAG, "onRequestHUNPClose");
    }

    @Deprecated
    public void onIntelligentSpeedLimitAlertStateChanged(int i) {
        Log.v(TAG, "onIntelligentSpeedLimitAlertStateChanged");
    }

    @Deprecated
    public void onIntelligentSpeedLimitControlStateChanged(int i) {
        Log.v(TAG, "onIntelligentSpeedLimitControlStateChanged");
    }

    public void onVerifyModeChanged(int i) {
        Log.v(TAG, "onVerifyModeChanged");
    }

    public void onDriveRemindStateChanged(int i) {
        Log.v(TAG, "onDriveRemindStateChanged");
    }

    public void onDriveDepartureStateChanged(int i) {
        Log.v(TAG, "onDriveDepartureStateChanged");
    }

    public void onPredictCrashAlertStateChange(int i) {
        Log.v(TAG, "onPredictCrashAlertStateChange");
    }

    public void onLaneAssistTypeChanged(int i) {
        Log.v(TAG, "onLaneAssistTypeChanged");
    }

    @Deprecated
    public void onSpeedLimitRangeChanged(int i) {
        Log.v(TAG, "onSpeedLimitRangeChanged");
    }

    public void onRemoteControlDriveCloseTops(int i) {
        Log.v(TAG, "onRemoteControlDriveCloseTops");
    }

    public void onSpeedLimitRange2D5Changed(int i) {
        Log.v(TAG, "onSpeedLimitRange2D5Changed  value=" + i);
    }

    public void onSpeedLimitRange2B4Changed(int i) {
        Log.v(TAG, "onSpeedLimitRange2B4Changed  value=" + i);
    }

    public void onIntelligentSpeedLimitAlert2D5StateChanged(int i) {
        Log.v(TAG, "onIntelligentSpeedLimitAlert2D5StateChanged  state=" + i);
    }

    public void onIntelligentSpeedLimitAlert2B4StateChanged(int i) {
        Log.v(TAG, "onIntelligentSpeedLimitAlert2B4StateChanged  state=" + i);
    }

    public void onIntelligentSpeedLimitControl2D5StateChanged(int i) {
        Log.v(TAG, "onIntelligentSpeedLimitControl2D5StateChanged  state=" + i);
    }

    public void onIntelligentSpeedLimitControl32FStateChanged(int i) {
        Log.v(TAG, "onIntelligentSpeedLimitControl32FStateChanged  state=" + i);
    }

    public void onTrafficSignRecognize2D5StateChanged(int i) {
        Log.v(TAG, "onTrafficSignRecognize2D5StateChanged  state=" + i);
    }

    public void onTrafficSignRecognize2B4StateChanged(int i) {
        Log.v(TAG, "onTrafficSignRecognize2B4StateChanged  state=" + i);
    }

    public void onTrafficSignRecognize316StateChanged(int i) {
        Log.v(TAG, "onTrafficSignRecognize316StateChanged  state=" + i);
    }

    public void onAiDistanceLight2B4StateChanged(int i) {
        Log.v(TAG, "onAiDistanceLight2B4StateChanged  state=" + i);
    }

    public void onAiDistanceLight316StateChanged(int i) {
        Log.v(TAG, "onAiDistanceLight316StateChanged  state=" + i);
    }

    public void onTrafficLightRecognize2D5StateChanged(int i) {
        Log.v(TAG, "onTrafficLightRecognize2D5StateChanged  state=" + i);
    }

    public void onTrafficLightRecognize2B4StateChanged(int i) {
        Log.v(TAG, "onTrafficLightRecognize2B4StateChanged  state=" + i);
    }

    public void onEmergencyLaneAssistance32FStateChanged(int i) {
        Log.v(TAG, "onEmergencyLaneAssistance32FStateChanged  state=" + i);
    }

    public void onEmergencyLaneAssistance2F0StateChanged(int i) {
        Log.v(TAG, "onEmergencyLaneAssistance2F0StateChanged  state=" + i);
    }

    public void onPredictCrashAlert138ModeChanged(int i) {
        Log.v(TAG, "onPredictCrashAlert138ModeChanged  mode=" + i);
    }

    public void onPredictCrashAlert32DModeChanged(int i) {
        Log.v(TAG, "onPredictCrashAlert32DModeChanged  mode=" + i);
    }

    public void onAiMatrixLight3ACStateChanged(int i) {
        Log.v(TAG, "onAiMatrixLight3ACStateChanged  state=" + i);
    }

    public void onAiMatrixLight3ADStateChanged(int i) {
        Log.v(TAG, "onAiMatrixLight3ADStateChanged  state=" + i);
    }

    public void onBlindMonitorStateChanged1(int i) {
        Log.v(TAG, "onBlindMonitorStateChanged1  state=" + i);
    }

    public void onBlindMonitorStateChanged2(int i) {
        Log.v(TAG, "onBlindMonitorStateChanged2  state=" + i);
    }

    public void onCpdStatusChanged(int i) {
        Log.v(TAG, "onCpdStatusChanged  status=" + i);
    }

    @Deprecated
    public void onCpdDelayStatusChanged(int i) {
        Log.v(TAG, "onCpdDelayStatusChanged  status=" + i);
    }

    public void onDmsStatusChanged(int i) {
        Log.v(TAG, "onDmsStatusChanged  status=" + i);
    }

    public void onADASOnlineStateChanged(int i) {
        Log.v(TAG, "onADASOnlineStateChanged  state=" + i);
    }

    public void onADASSoftStateChanged(int i) {
        Log.v(TAG, "onADASSoftStateChanged: state = " + i);
    }

    public void onTSRLitSpdChgSwitchStateChanged(int i) {
        Log.v(TAG, "onTSRLitSpdChgSwitchStateChanged: state = " + i);
    }

    public void onELKTakeOverStateChanged(int i) {
        Log.v(TAG, "onELKTakeOverStateChanged: state = " + i);
    }

    public void onTSRLitSpdChgInformStateChanged(int i) {
        Log.v(TAG, "onTSRLitSpdChgInformStateChanged: state = " + i);
    }

    public void onISLIWarningStateChanged(int i) {
        Log.v(TAG, "onISLIWarningStateChanged: state = " + i);
    }

    public void onLKSFaultStateStateChanged(int i) {
        Log.v(TAG, "onLKSFaultStateStateChanged:" + i);
    }

    public void onLDWFaultStateChanged(int i) {
        Log.v(TAG, "onLDWFaultStateChanged:" + i);
    }

    public void onAEBFaultStateStateChanged(int i) {
        Log.v(TAG, "onAEBFaultStateStateChanged:" + i);
    }

    public void onFCWFaultStateChanged(int i) {
        Log.v(TAG, "onFCWFaultStateChanged:" + i);
    }

    public void onVRUFaultStateStateChanged(int i) {
        Log.v(TAG, "onVRUFaultStateStateChanged:" + i);
    }

    public void onLowSpeedEmergencyBrakingStateChanged(int i) {
        Log.v(TAG, "onLowSpeedEmergencyBrakingStateChanged:" + i);
    }

    public void onSideDistanceWarningStateChanged(int i) {
        Log.v(TAG, "onSideDistanceWarningStateChanged:" + i);
    }

    public void onCollisionWarningStateChanged(int i) {
        Log.v(TAG, "onCollisionWarningStateChanged:" + i);
    }

    public void onIntersectionCollisionAlertStateChanged(int i) {
        Log.v(TAG, "onIntersectionCollisionAlertStateChanged:" + i);
    }

    public void onDoNotPassWarningStateChanged(int i) {
        Log.v(TAG, "onDoNotPassWarningStateChanged:" + i);
    }

    public void onEmergencyBrakeWarningStateChanged(int i) {
        Log.v(TAG, "onEmergencyBrakeWarningStateChanged:" + i);
    }

    public void onTrafficIncidentWarningStateChanged(int i) {
        Log.v(TAG, "onTrafficIncidentWarningStateChanged:" + i);
    }

    public void onAbnormalVehicleWarningStateChanged(int i) {
        Log.v(TAG, "onAbnormalVehicleWarningStateChanged:" + i);
    }

    public void onControlLossWarningStateChanged(int i) {
        Log.v(TAG, "onControlLossWarningStateChanged:" + i);
    }

    public void onTrafficJamWarningStateChanged(int i) {
        Log.v(TAG, "onTrafficJamWarningStateChanged:" + i);
    }

    public void onEmergencyVehicleWarningStateChanged(int i) {
        Log.v(TAG, "onEmergencyVehicleWarningStateChanged:" + i);
    }

    public void onTrafficSignalWarningStateChanged(int i) {
        Log.v(TAG, "onTrafficSignalWarningStateChanged:" + i);
    }

    public void onTrafficLightInfoPushStateChanged(int i) {
        Log.v(TAG, "onTrafficLightInfoPushStateChanged:" + i);
    }

    public void onRedLightViolationWarningStateChanged(int i) {
        Log.v(TAG, "onRedLightViolationWarningStateChanged:" + i);
    }

    public void onGreenLightOptimalSpeedAdvisoryStateChanged(int i) {
        Log.v(TAG, "onGreenLightOptimalSpeedAdvisoryStateChanged:" + i);
    }

    public void onMechanismPositionChanged(int i) {
        Log.v(TAG, "onMechanismPositionChanged:" + i);
    }

    public void onDnpStatusChanged(int i) {
        Log.v(TAG, "onDnpStatusChanged:" + i);
    }

    public void onFatigueMonitorChange(int i) {
        Log.v(TAG, "onFatigueMonitorChange:" + i);
    }

    public void onBSISSwitchChange(int i) {
        Log.v(TAG, "onBSISSwitchChange" + i);
    }

    public void onBSISSystemChange(int i) {
        Log.v(TAG, "onBSISSystemChange" + i);
    }

    public void onMOISSwitchChange(int i) {
        Log.v(TAG, "onMOISSwitchChange" + i);
    }

    public void onMOISSystemChange(int i) {
        Log.v(TAG, "onMOISSystemChange" + i);
    }

    public void onLDWSensitivityChange(int i) {
        Log.v(TAG, "onLDWSensitivityChange" + i);
    }

    public void onAdativeCruiseControlFault(byte b) {
        Log.v(TAG, "onAdativeCruiseControlFault " + ((int) b));
    }

    public void onLaneCenteringControlFault(byte b) {
        Log.v(TAG, "onLaneCenteringControlFault " + ((int) b));
    }

    public void onBlindFault(byte b) {
        Log.v(TAG, "onBlindFault " + ((int) b));
    }

    public void onDoorOpenFault(byte b) {
        Log.v(TAG, "onDoorOpenFault " + ((int) b));
    }

    public void onRearCrossAlartFault(byte b) {
        Log.v(TAG, "onRearCrossAlartFault " + ((int) b));
    }

    public void onRearCrossBrakeFault(byte b) {
        Log.v(TAG, "onRearCrossBrakeFault " + ((int) b));
    }

    public void onRearCollisionWarnFault(byte b) {
        Log.v(TAG, "onRearCollisionWarnFault " + ((int) b));
    }

    public void onFrontCrossAlarkFault(byte b) {
        Log.v(TAG, "onFrontCrossAlarkFault " + ((int) b));
    }

    public void onFrontCrossBrakeFault(byte b) {
        Log.v(TAG, "onFrontCrossBrakeFault " + ((int) b));
    }

    public void onInteractiveLaneChangeFault(byte b) {
        Log.v(TAG, "onInteractiveLaneChangeFault " + ((int) b));
    }

    public void onNrbanNaviFault(byte b) {
        Log.v(TAG, "onNrbanNaviFault " + ((int) b));
    }

    public void onHighNaviFault(byte b) {
        Log.v(TAG, "onHighNaviFault " + ((int) b));
    }

    public void onNavigationPilotFault(byte b) {
        Log.v(TAG, "onNavigationPilotFault " + ((int) b));
    }

    public void onAutoEmergencyFault(byte b) {
        Log.v(TAG, "onAutoEmergencyFault " + ((int) b));
    }

    public void onEmergencyLaneKeepFault(byte b) {
        Log.v(TAG, "onEmergencyLaneKeepFault " + ((int) b));
    }

    public void onIntelligentLimitFaultController(byte b) {
        Log.v(TAG, "onIntelligentLimitFaultController " + ((int) b));
    }

    public void onLaneDeparturePreventionFault(byte b) {
        Log.v(TAG, "onLaneDeparturePreventionFault " + ((int) b));
    }

    public void onLaneDepartureWarnFault(byte b) {
        Log.v(TAG, "onLaneDepartureWarnFault " + ((int) b));
    }

    public void onLaneKeepFault(byte b) {
        Log.v(TAG, "onLaneKeepFault " + ((int) b));
    }

    public void onForwardCollisionWarnFault(byte b) {
        Log.v(TAG, "onForwardCollisionWarnFault " + ((int) b));
    }

    public void onAdaptiveFrontLightFault(byte b) {
        Log.v(TAG, "onAdaptiveFrontLightFault " + ((int) b));
    }

    public void onTrafficAttentionFault(byte b) {
        Log.v(TAG, "onTrafficAttentionFault " + ((int) b));
    }

    public void onTsrFault(byte b) {
        Log.v(TAG, "onTsrFault " + ((int) b));
    }

    public void onIntelligentLimitFaultCall(byte b) {
        Log.v(TAG, "onIntelligentLimitFaultCall " + ((int) b));
    }

    public void onLimitSpeedChangeFault(byte b) {
        Log.v(TAG, "onLimitSpeedChangeFault " + ((int) b));
    }

    public void onCurveWarnFault(byte b) {
        Log.v(TAG, "onCurveWarnFault " + ((int) b));
    }

    public void onBSISFault(byte b) {
        Log.v(TAG, "onBSISFault " + ((int) b));
    }

    public void onMOISFault(byte b) {
        Log.v(TAG, "onMOISFault " + ((int) b));
    }

    public void onISLIWarningLevelSwitchStatusChanged(int i) {
        Log.v(TAG, "onISLIWarningLevelSwitchStatusChanged " + i);
    }

    public void onCpdWarningStateChanged(int i) {
        Log.v(TAG, "onCpdWarningStateChanged " + i);
    }

    public void onCpdWorkingStatusChanged(int i) {
        Log.v(TAG, "onCpdWorkingStatusChanged " + i);
    }

    public void onEspOffKeyStatusChanged(int i) {
        Log.v(TAG, "onEspOffKeyStatusChanged " + i);
    }

    public void onAEBDecActiveChanged(int i) {
        Log.v(TAG, "onAEBDecActiveChanged " + i);
    }

    public void onIOPStateChanged(int i) {
        Log.v(TAG, "onIOPStateChanged state is " + i);
    }

    public void onSAOCStateChanged(int i) {
        Log.v(TAG, "onSAOCStateChanged state is " + i);
    }

    public void onLowIOPToastChanged(int i) {
        Log.v(TAG, "onLowIOPToastChanged state is " + i);
    }

    public void onHighIOPToastChanged(int i) {
        Log.v(TAG, "onHighIOPToastChanged state is " + i);
    }

    public void onLowHODToastChanged(int i) {
        Log.v(TAG, "onLowHODToastChanged state is " + i);
    }

    public void onHighHODToastChanged(int i) {
        Log.v(TAG, "onHighHODToastChanged state is " + i);
    }

    public void onSLIFStateChanged(int i) {
        Log.v(TAG, "onSLIFStateChanged state is " + i);
    }

    public void onACCStateChanged(int i) {
        Log.v(TAG, "onACCStateChanged state is " + i);
    }

    public void onFCSRStateChanged(int i) {
        Log.v(TAG, "onFCSRStateChanged state is " + i);
    }

    public void onFCSRFault(int i) {
        Log.v(TAG, "onFCSRFault state is " + i);
    }
}

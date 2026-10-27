package android.hardware.bydauto.dipilot;

import android.hardware.IBYDAutoListener;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoDiPilotListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoDiPilotListener";

    public void onDrivieRemindStateChanged(int state) {
        Log.v(TAG, "onDrivieRemindStateChanged");
    }

    public void onDrivieDepartureStateChanged(int state) {
        Log.v(TAG, "onDrivieDepartureStateChanged");
    }

    public void onFrontCarStartStateChange(int state) {
        Log.v(TAG, "onFrontCarStartStateChange");
    }

    public void onFaultHandlerStateChanged(int state) {
        Log.v(TAG, "onFaultHandlerStateChanged");
    }

    public void onChargerCoverStateChanged(int state) {
        Log.v(TAG, "onChargerCoverStateChanged");
    }

    public void onAiTurnLightStateChanged(int state) {
        Log.v(TAG, "onAiTurnLightStateChanged");
    }

    public void onRainOpenFogLightStateChanged(int state) {
        Log.v(TAG, "onRainOpenFogLightStateChanged");
    }

    public void onHighSpeedOpenWindowStateChanged(int state) {
        Log.v(TAG, "onHighSpeedOpenWindowStateChanged");
    }

    public void onDriveLevelChanged(int level) {
        Log.v(TAG, "onDriveLevelChanged");
    }

    public void onHighSpeedAINavigationStateChanged(int state) {
        Log.v(TAG, "onHighSpeedAINavigationStateChanged");
    }

    public void onUrbanAIAssistanceStateChanged(int state) {
        Log.v(TAG, "onUrbanAIAssistanceStateChanged");
    }

    public void onE2EAIAssistanceStateChanged(int state) {
        Log.v(TAG, "onE2EAIAssistanceStateChanged");
    }

    public void onInteractiveLaneChangeAssistanceStateChanged(int state) {
        Log.v(TAG, "onInteractiveLaneChangeAssistanceStateChanged");
    }

    public void onAINavigationStateChanged(int value) {
        Log.v(TAG, "onAINavigationStateChanged");
    }

    public void onAlarmTypeChanged(int value) {
        Log.v(TAG, "onAlarmTypeChanged");
    }

    @Deprecated
    public void onTrafficSignRecognizeStateChanged(int state) {
        Log.v(TAG, "onTrafficSignRecognizeStateChanged");
    }

    public void onAiCareStateChanged(int state) {
        Log.v(TAG, "onAiCareStateChanged");
    }

    public void onMistakeGasStateChanged(int state) {
        Log.v(TAG, "onMistakeGasStateChanged");
    }

    public void onAiMatrixLightStateChanged(int state) {
        Log.v(TAG, "onAiMatrixLightStateChanged");
    }

    @Deprecated
    public void onAiDistanceLightStateChanged(int state) {
        Log.v(TAG, "onAiDistanceLightStateChanged");
    }

    @Deprecated
    public void onTrafficLightRecognizeStateChanged(int state) {
        Log.v(TAG, "onTrafficLightRecognizeStateChanged");
    }

    @Deprecated
    public void onEmergencyLaneAssistanceStateChanged(int state) {
        Log.v(TAG, "onEmergencyLaneAssistanceStateChanged");
    }

    public void onBlindMonitorStateChanged(int state) {
        Log.v(TAG, "onBlindMonitorStateChanged");
    }

    public void onOpenDoorAlertStateChanged(int state) {
        Log.v(TAG, "onOpenDoorAlertStateChanged");
    }

    public void onRearCrashAlertStateChanged(int state) {
        Log.v(TAG, "onRearCrashAlertStateChanged");
    }

    public void onRearTrafficCrossAlertStateChanged(int state) {
        Log.v(TAG, "onRearTrafficCrossAlertStateChanged");
    }

    public void onRearTrafficBrakeAlertStateChanged(int state) {
        Log.v(TAG, "onRearTrafficBrakeAlertStateChanged");
    }

    public void onAiEmergencyBrakeStateChanged(int state) {
        Log.v(TAG, "onAiEmergencyBrakeStateChanged");
    }

    @Deprecated
    public void onPredictCrashAlertModeChanged(int mode) {
        Log.v(TAG, "onPredictCrashAlertModeChanged");
    }

    public void onFrontTrafficCrossAlertStateChanged(int state) {
        Log.v(TAG, "onFrontTrafficCrossAlertStateChanged");
    }

    public void onFrontTrafficBrakeAlertStateChanged(int state) {
        Log.v(TAG, "onFrontTrafficBrakeAlertStateChanged");
    }

    public void onElecCarBodyStablySysStateChanged(int state) {
        Log.v(TAG, "onElecCarBodyStablySysStateChanged");
    }

    public void onAutoParkingStateChanged(int state) {
        Log.v(TAG, "onAutoParkingStateChanged");
    }

    public void onSteepDescentSlowDownStateChanged(int state) {
        Log.v(TAG, "onSteepDescentSlowDownStateChanged");
    }

    public void onFatigueDetectionAidStateChanged(int state) {
        Log.v(TAG, "onFatigueDetectionAidStateChanged");
    }

    public void onRelieveFatigueStateChanged(int state) {
        Log.v(TAG, "onRelieveFatigueStateChanged");
    }

    public void onRecommendRestSpotStateChanged(int state) {
        Log.v(TAG, "onRecommendRestSpotStateChanged");
    }

    public void onTemperatureWhileFatigueChanged(int value) {
        Log.v(TAG, "onTemperatureWhileFatigueChanged");
    }

    public void onDistractionDetectionAidStateChanged(int state) {
        Log.v(TAG, "onDistractionDetectionAidStateChanged");
    }

    public void onAttentionDetectionStateChanged(int state) {
        Log.v(TAG, "onAttentionDetectionStateChanged");
    }

    public void onDangerousActionDetectionStateChanged(int value) {
        Log.v(TAG, "onDangerousActionDetectionStateChanged");
    }

    public void onGazePerceptionStateChanged(int state) {
        Log.v(TAG, "onGazePerceptionStateChanged");
    }

    public void onChildCareStateChanged(int state) {
        Log.v(TAG, "onChildCareStateChanged");
    }

    public void onGestureRecognitionStateChanged(int state) {
        Log.v(TAG, "onGestureRecognitionStateChanged");
    }

    public void onLeftBehindReminderStateChanged(int state) {
        Log.v(TAG, "onLeftBehindReminderStateChanged");
    }

    public void onRequestHUNPClose(int state) {
        Log.v(TAG, "onRequestHUNPClose");
    }

    @Deprecated
    public void onIntelligentSpeedLimitAlertStateChanged(int state) {
        Log.v(TAG, "onIntelligentSpeedLimitAlertStateChanged");
    }

    @Deprecated
    public void onIntelligentSpeedLimitControlStateChanged(int state) {
        Log.v(TAG, "onIntelligentSpeedLimitControlStateChanged");
    }

    public void onVerifyModeChanged(int mode) {
        Log.v(TAG, "onVerifyModeChanged");
    }

    public void onDriveRemindStateChanged(int state) {
        Log.v(TAG, "onDriveRemindStateChanged");
    }

    public void onDriveDepartureStateChanged(int state) {
        Log.v(TAG, "onDriveDepartureStateChanged");
    }

    public void onPredictCrashAlertStateChange(int state) {
        Log.v(TAG, "onPredictCrashAlertStateChange");
    }

    public void onLaneAssistTypeChanged(int value) {
        Log.v(TAG, "onLaneAssistTypeChanged");
    }

    @Deprecated
    public void onSpeedLimitRangeChanged(int value) {
        Log.v(TAG, "onSpeedLimitRangeChanged");
    }

    public void onRemoteControlDriveCloseTops(int value) {
        Log.v(TAG, "onRemoteControlDriveCloseTops");
    }

    public void onSpeedLimitRange2D5Changed(int value) {
        Log.v(TAG, "onSpeedLimitRange2D5Changed  value=" + value);
    }

    public void onSpeedLimitRange2B4Changed(int value) {
        Log.v(TAG, "onSpeedLimitRange2B4Changed  value=" + value);
    }

    public void onIntelligentSpeedLimitAlert2D5StateChanged(int state) {
        Log.v(TAG, "onIntelligentSpeedLimitAlert2D5StateChanged  state=" + state);
    }

    public void onIntelligentSpeedLimitAlert2B4StateChanged(int state) {
        Log.v(TAG, "onIntelligentSpeedLimitAlert2B4StateChanged  state=" + state);
    }

    public void onIntelligentSpeedLimitControl2D5StateChanged(int state) {
        Log.v(TAG, "onIntelligentSpeedLimitControl2D5StateChanged  state=" + state);
    }

    public void onIntelligentSpeedLimitControl32FStateChanged(int state) {
        Log.v(TAG, "onIntelligentSpeedLimitControl32FStateChanged  state=" + state);
    }

    public void onTrafficSignRecognize2D5StateChanged(int state) {
        Log.v(TAG, "onTrafficSignRecognize2D5StateChanged  state=" + state);
    }

    public void onTrafficSignRecognize2B4StateChanged(int state) {
        Log.v(TAG, "onTrafficSignRecognize2B4StateChanged  state=" + state);
    }

    public void onTrafficSignRecognize316StateChanged(int state) {
        Log.v(TAG, "onTrafficSignRecognize316StateChanged  state=" + state);
    }

    public void onAiDistanceLight2B4StateChanged(int state) {
        Log.v(TAG, "onAiDistanceLight2B4StateChanged  state=" + state);
    }

    public void onAiDistanceLight316StateChanged(int state) {
        Log.v(TAG, "onAiDistanceLight316StateChanged  state=" + state);
    }

    public void onTrafficLightRecognize2D5StateChanged(int state) {
        Log.v(TAG, "onTrafficLightRecognize2D5StateChanged  state=" + state);
    }

    public void onTrafficLightRecognize2B4StateChanged(int state) {
        Log.v(TAG, "onTrafficLightRecognize2B4StateChanged  state=" + state);
    }

    public void onEmergencyLaneAssistance32FStateChanged(int state) {
        Log.v(TAG, "onEmergencyLaneAssistance32FStateChanged  state=" + state);
    }

    public void onEmergencyLaneAssistance2F0StateChanged(int state) {
        Log.v(TAG, "onEmergencyLaneAssistance2F0StateChanged  state=" + state);
    }

    public void onPredictCrashAlert138ModeChanged(int mode) {
        Log.v(TAG, "onPredictCrashAlert138ModeChanged  mode=" + mode);
    }

    public void onPredictCrashAlert32DModeChanged(int mode) {
        Log.v(TAG, "onPredictCrashAlert32DModeChanged  mode=" + mode);
    }

    public void onAiMatrixLight3ACStateChanged(int state) {
        Log.v(TAG, "onAiMatrixLight3ACStateChanged  state=" + state);
    }

    public void onAiMatrixLight3ADStateChanged(int state) {
        Log.v(TAG, "onAiMatrixLight3ADStateChanged  state=" + state);
    }

    public void onBlindMonitorStateChanged1(int state) {
        Log.v(TAG, "onBlindMonitorStateChanged1  state=" + state);
    }

    public void onBlindMonitorStateChanged2(int state) {
        Log.v(TAG, "onBlindMonitorStateChanged2  state=" + state);
    }

    public void onCpdStatusChanged(int status) {
        Log.v(TAG, "onCpdStatusChanged  status=" + status);
    }

    @Deprecated
    public void onCpdDelayStatusChanged(int status) {
        Log.v(TAG, "onCpdDelayStatusChanged  status=" + status);
    }

    public void onDmsStatusChanged(int status) {
        Log.v(TAG, "onDmsStatusChanged  status=" + status);
    }

    public void onADASOnlineStateChanged(int state) {
        Log.v(TAG, "onADASOnlineStateChanged  state=" + state);
    }

    public void onADASSoftStateChanged(int state) {
        Log.v(TAG, "onADASSoftStateChanged: state = " + state);
    }

    public void onTSRLitSpdChgSwitchStateChanged(int state) {
        Log.v(TAG, "onTSRLitSpdChgSwitchStateChanged: state = " + state);
    }

    public void onELKTakeOverStateChanged(int state) {
        Log.v(TAG, "onELKTakeOverStateChanged: state = " + state);
    }

    public void onTSRLitSpdChgInformStateChanged(int state) {
        Log.v(TAG, "onTSRLitSpdChgInformStateChanged: state = " + state);
    }

    public void onISLIWarningStateChanged(int state) {
        Log.v(TAG, "onISLIWarningStateChanged: state = " + state);
    }

    public void onLKSFaultStateStateChanged(int state) {
        Log.v(TAG, "onLKSFaultStateStateChanged:" + state);
    }

    public void onLDWFaultStateChanged(int state) {
        Log.v(TAG, "onLDWFaultStateChanged:" + state);
    }

    public void onAEBFaultStateStateChanged(int state) {
        Log.v(TAG, "onAEBFaultStateStateChanged:" + state);
    }

    public void onFCWFaultStateChanged(int state) {
        Log.v(TAG, "onFCWFaultStateChanged:" + state);
    }

    public void onVRUFaultStateStateChanged(int state) {
        Log.v(TAG, "onVRUFaultStateStateChanged:" + state);
    }

    public void onLowSpeedEmergencyBrakingStateChanged(int state) {
        Log.v(TAG, "onLowSpeedEmergencyBrakingStateChanged:" + state);
    }

    public void onSideDistanceWarningStateChanged(int state) {
        Log.v(TAG, "onSideDistanceWarningStateChanged:" + state);
    }

    public void onCollisionWarningStateChanged(int state) {
        Log.v(TAG, "onCollisionWarningStateChanged:" + state);
    }

    public void onIntersectionCollisionAlertStateChanged(int state) {
        Log.v(TAG, "onIntersectionCollisionAlertStateChanged:" + state);
    }

    public void onDoNotPassWarningStateChanged(int state) {
        Log.v(TAG, "onDoNotPassWarningStateChanged:" + state);
    }

    public void onEmergencyBrakeWarningStateChanged(int state) {
        Log.v(TAG, "onEmergencyBrakeWarningStateChanged:" + state);
    }

    public void onTrafficIncidentWarningStateChanged(int state) {
        Log.v(TAG, "onTrafficIncidentWarningStateChanged:" + state);
    }

    public void onAbnormalVehicleWarningStateChanged(int state) {
        Log.v(TAG, "onAbnormalVehicleWarningStateChanged:" + state);
    }

    public void onControlLossWarningStateChanged(int state) {
        Log.v(TAG, "onControlLossWarningStateChanged:" + state);
    }

    public void onTrafficJamWarningStateChanged(int state) {
        Log.v(TAG, "onTrafficJamWarningStateChanged:" + state);
    }

    public void onEmergencyVehicleWarningStateChanged(int state) {
        Log.v(TAG, "onEmergencyVehicleWarningStateChanged:" + state);
    }

    public void onTrafficSignalWarningStateChanged(int state) {
        Log.v(TAG, "onTrafficSignalWarningStateChanged:" + state);
    }

    public void onTrafficLightInfoPushStateChanged(int state) {
        Log.v(TAG, "onTrafficLightInfoPushStateChanged:" + state);
    }

    public void onRedLightViolationWarningStateChanged(int state) {
        Log.v(TAG, "onRedLightViolationWarningStateChanged:" + state);
    }

    public void onGreenLightOptimalSpeedAdvisoryStateChanged(int state) {
        Log.v(TAG, "onGreenLightOptimalSpeedAdvisoryStateChanged:" + state);
    }

    public void onMechanismPositionChanged(int pos) {
        Log.v(TAG, "onMechanismPositionChanged:" + pos);
    }

    public void onDnpStatusChanged(int status) {
        Log.v(TAG, "onDnpStatusChanged:" + status);
    }

    public void onFatigueMonitorChange(int level) {
        Log.v(TAG, "onFatigueMonitorChange:" + level);
    }

    public void onBSISSwitchChange(int state) {
        Log.v(TAG, "onBSISSwitchChange" + state);
    }

    public void onBSISSystemChange(int state) {
        Log.v(TAG, "onBSISSystemChange" + state);
    }

    public void onMOISSwitchChange(int state) {
        Log.v(TAG, "onMOISSwitchChange" + state);
    }

    public void onMOISSystemChange(int state) {
        Log.v(TAG, "onMOISSystemChange" + state);
    }

    public void onLDWSensitivityChange(int state) {
        Log.v(TAG, "onLDWSensitivityChange" + state);
    }

    public void onAdativeCruiseControlFault(byte fault) {
        Log.v(TAG, "onAdativeCruiseControlFault " + ((int) fault));
    }

    public void onLaneCenteringControlFault(byte fault) {
        Log.v(TAG, "onLaneCenteringControlFault " + ((int) fault));
    }

    public void onBlindFault(byte fault) {
        Log.v(TAG, "onBlindFault " + ((int) fault));
    }

    public void onDoorOpenFault(byte fault) {
        Log.v(TAG, "onDoorOpenFault " + ((int) fault));
    }

    public void onRearCrossAlartFault(byte fault) {
        Log.v(TAG, "onRearCrossAlartFault " + ((int) fault));
    }

    public void onRearCrossBrakeFault(byte fault) {
        Log.v(TAG, "onRearCrossBrakeFault " + ((int) fault));
    }

    public void onRearCollisionWarnFault(byte fault) {
        Log.v(TAG, "onRearCollisionWarnFault " + ((int) fault));
    }

    public void onFrontCrossAlarkFault(byte fault) {
        Log.v(TAG, "onFrontCrossAlarkFault " + ((int) fault));
    }

    public void onFrontCrossBrakeFault(byte fault) {
        Log.v(TAG, "onFrontCrossBrakeFault " + ((int) fault));
    }

    public void onInteractiveLaneChangeFault(byte fault) {
        Log.v(TAG, "onInteractiveLaneChangeFault " + ((int) fault));
    }

    public void onNrbanNaviFault(byte fault) {
        Log.v(TAG, "onNrbanNaviFault " + ((int) fault));
    }

    public void onHighNaviFault(byte fault) {
        Log.v(TAG, "onHighNaviFault " + ((int) fault));
    }

    public void onNavigationPilotFault(byte fault) {
        Log.v(TAG, "onNavigationPilotFault " + ((int) fault));
    }

    public void onAutoEmergencyFault(byte fault) {
        Log.v(TAG, "onAutoEmergencyFault " + ((int) fault));
    }

    public void onEmergencyLaneKeepFault(byte fault) {
        Log.v(TAG, "onEmergencyLaneKeepFault " + ((int) fault));
    }

    public void onIntelligentLimitFaultController(byte fault) {
        Log.v(TAG, "onIntelligentLimitFaultController " + ((int) fault));
    }

    public void onLaneDeparturePreventionFault(byte fault) {
        Log.v(TAG, "onLaneDeparturePreventionFault " + ((int) fault));
    }

    public void onLaneDepartureWarnFault(byte fault) {
        Log.v(TAG, "onLaneDepartureWarnFault " + ((int) fault));
    }

    public void onLaneKeepFault(byte fault) {
        Log.v(TAG, "onLaneKeepFault " + ((int) fault));
    }

    public void onForwardCollisionWarnFault(byte fault) {
        Log.v(TAG, "onForwardCollisionWarnFault " + ((int) fault));
    }

    public void onAdaptiveFrontLightFault(byte fault) {
        Log.v(TAG, "onAdaptiveFrontLightFault " + ((int) fault));
    }

    public void onTrafficAttentionFault(byte fault) {
        Log.v(TAG, "onTrafficAttentionFault " + ((int) fault));
    }

    public void onTsrFault(byte fault) {
        Log.v(TAG, "onTsrFault " + ((int) fault));
    }

    public void onIntelligentLimitFaultCall(byte fault) {
        Log.v(TAG, "onIntelligentLimitFaultCall " + ((int) fault));
    }

    public void onLimitSpeedChangeFault(byte fault) {
        Log.v(TAG, "onLimitSpeedChangeFault " + ((int) fault));
    }

    public void onCurveWarnFault(byte fault) {
        Log.v(TAG, "onCurveWarnFault " + ((int) fault));
    }

    public void onBSISFault(byte fault) {
        Log.v(TAG, "onBSISFault " + ((int) fault));
    }

    public void onMOISFault(byte fault) {
        Log.v(TAG, "onMOISFault " + ((int) fault));
    }

    public void onISLIWarningLevelSwitchStatusChanged(int status) {
        Log.v(TAG, "onISLIWarningLevelSwitchStatusChanged " + status);
    }

    public void onCpdWarningStateChanged(int state) {
        Log.v(TAG, "onCpdWarningStateChanged " + state);
    }

    public void onCpdWorkingStatusChanged(int state) {
        Log.v(TAG, "onCpdWorkingStatusChanged " + state);
    }

    public void onEspOffKeyStatusChanged(int state) {
        Log.v(TAG, "onEspOffKeyStatusChanged " + state);
    }

    public void onAEBDecActiveChanged(int state) {
        Log.v(TAG, "onAEBDecActiveChanged " + state);
    }

    public void onIOPStateChanged(int state) {
        Log.v(TAG, "onIOPStateChanged state is " + state);
    }

    public void onSAOCStateChanged(int state) {
        Log.v(TAG, "onSAOCStateChanged state is " + state);
    }

    public void onLowIOPToastChanged(int state) {
        Log.v(TAG, "onLowIOPToastChanged state is " + state);
    }

    public void onHighIOPToastChanged(int state) {
        Log.v(TAG, "onHighIOPToastChanged state is " + state);
    }

    public void onLowHODToastChanged(int state) {
        Log.v(TAG, "onLowHODToastChanged state is " + state);
    }

    public void onHighHODToastChanged(int state) {
        Log.v(TAG, "onHighHODToastChanged state is " + state);
    }

    public void onSLIFStateChanged(int state) {
        Log.v(TAG, "onSLIFStateChanged state is " + state);
    }

    public void onACCStateChanged(int state) {
        Log.v(TAG, "onACCStateChanged state is " + state);
    }

    public void onFCSRStateChanged(int state) {
        Log.v(TAG, "onFCSRStateChanged state is " + state);
    }

    public void onFCSRFault(int fault) {
        Log.v(TAG, "onFCSRFault state is " + fault);
    }
}

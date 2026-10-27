package android.hardware.bydauto.dipilot;

import a.a.c;
import a.a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoDiPilotListener implements d {
    protected static final String TAG = "AbsBYDAutoDiPilotListener";

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.a.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onADASOnlineStateChanged(int i) {
        Log.v(TAG, "onADASOnlineStateChanged  state=" + i);
    }

    public void onADASSoftStateChanged(int i) {
        Log.v(TAG, "onADASSoftStateChanged: state = " + i);
    }

    public void onAEBDecActiveChanged(int i) {
        Log.v(TAG, "onAEBDecActiveChanged " + i);
    }

    public void onAEBFaultStateStateChanged(int i) {
        Log.v(TAG, "onAEBFaultStateStateChanged:" + i);
    }

    public void onAINavigationStateChanged(int i) {
        Log.v(TAG, "onAINavigationStateChanged");
    }

    public void onAbnormalVehicleWarningStateChanged(int i) {
        Log.v(TAG, "onAbnormalVehicleWarningStateChanged:" + i);
    }

    public void onAdaptiveFrontLightFault(byte b2) {
        Log.v(TAG, "onAdaptiveFrontLightFault " + ((int) b2));
    }

    public void onAdativeCruiseControlFault(byte b2) {
        Log.v(TAG, "onAdativeCruiseControlFault " + ((int) b2));
    }

    public void onAiCareStateChanged(int i) {
        Log.v(TAG, "onAiCareStateChanged");
    }

    public void onAiDistanceLight2B4StateChanged(int i) {
        Log.v(TAG, "onAiDistanceLight2B4StateChanged  state=" + i);
    }

    public void onAiDistanceLight316StateChanged(int i) {
        Log.v(TAG, "onAiDistanceLight316StateChanged  state=" + i);
    }

    @Deprecated
    public void onAiDistanceLightStateChanged(int i) {
        Log.v(TAG, "onAiDistanceLightStateChanged");
    }

    public void onAiEmergencyBrakeStateChanged(int i) {
        Log.v(TAG, "onAiEmergencyBrakeStateChanged");
    }

    public void onAiMatrixLight3ACStateChanged(int i) {
        Log.v(TAG, "onAiMatrixLight3ACStateChanged  state=" + i);
    }

    public void onAiMatrixLight3ADStateChanged(int i) {
        Log.v(TAG, "onAiMatrixLight3ADStateChanged  state=" + i);
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

    public void onAutoEmergencyFault(byte b2) {
        Log.v(TAG, "onAutoEmergencyFault " + ((int) b2));
    }

    public void onAutoParkingStateChanged(int i) {
        Log.v(TAG, "onAutoParkingStateChanged");
    }

    public void onBSISFault(byte b2) {
        Log.v(TAG, "onBSISFault " + ((int) b2));
    }

    public void onBSISSwitchChange(int i) {
        Log.v(TAG, "onBSISSwitchChange" + i);
    }

    public void onBSISSystemChange(int i) {
        Log.v(TAG, "onBSISSystemChange" + i);
    }

    public void onBlindFault(byte b2) {
        Log.v(TAG, "onBlindFault " + ((int) b2));
    }

    public void onBlindMonitorStateChanged(int i) {
        Log.v(TAG, "onBlindMonitorStateChanged");
    }

    public void onBlindMonitorStateChanged1(int i) {
        Log.v(TAG, "onBlindMonitorStateChanged1  state=" + i);
    }

    public void onBlindMonitorStateChanged2(int i) {
        Log.v(TAG, "onBlindMonitorStateChanged2  state=" + i);
    }

    public void onChargerCoverStateChanged(int i) {
        Log.v(TAG, "onChargerCoverStateChanged");
    }

    public void onChildCareStateChanged(int i) {
        Log.v(TAG, "onChildCareStateChanged");
    }

    public void onCollisionWarningStateChanged(int i) {
        Log.v(TAG, "onCollisionWarningStateChanged:" + i);
    }

    public void onControlLossWarningStateChanged(int i) {
        Log.v(TAG, "onControlLossWarningStateChanged:" + i);
    }

    @Deprecated
    public void onCpdDelayStatusChanged(int i) {
        Log.v(TAG, "onCpdDelayStatusChanged  status=" + i);
    }

    public void onCpdStatusChanged(int i) {
        Log.v(TAG, "onCpdStatusChanged  status=" + i);
    }

    public void onCpdWarningStateChanged(int i) {
        Log.v(TAG, "onCpdWarningStateChanged " + i);
    }

    public void onCpdWorkingStatusChanged(int i) {
        Log.v(TAG, "onCpdWorkingStatusChanged " + i);
    }

    public void onCurveWarnFault(byte b2) {
        Log.v(TAG, "onCurveWarnFault " + ((int) b2));
    }

    public void onDangerousActionDetectionStateChanged(int i) {
        Log.v(TAG, "onDangerousActionDetectionStateChanged");
    }

    public abstract /* synthetic */ void onDataChanged(c cVar);

    public abstract /* synthetic */ void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue);

    public void onDistractionDetectionAidStateChanged(int i) {
        Log.v(TAG, "onDistractionDetectionAidStateChanged");
    }

    public void onDmsStatusChanged(int i) {
        Log.v(TAG, "onDmsStatusChanged  status=" + i);
    }

    public void onDnpStatusChanged(int i) {
        Log.v(TAG, "onDnpStatusChanged:" + i);
    }

    public void onDoNotPassWarningStateChanged(int i) {
        Log.v(TAG, "onDoNotPassWarningStateChanged:" + i);
    }

    public void onDoorOpenFault(byte b2) {
        Log.v(TAG, "onDoorOpenFault " + ((int) b2));
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
        Log.v(TAG, "onELKTakeOverStateChanged: state = " + i);
    }

    public void onElecCarBodyStablySysStateChanged(int i) {
        Log.v(TAG, "onElecCarBodyStablySysStateChanged");
    }

    public void onEmergencyBrakeWarningStateChanged(int i) {
        Log.v(TAG, "onEmergencyBrakeWarningStateChanged:" + i);
    }

    public void onEmergencyLaneAssistance2F0StateChanged(int i) {
        Log.v(TAG, "onEmergencyLaneAssistance2F0StateChanged  state=" + i);
    }

    public void onEmergencyLaneAssistance32FStateChanged(int i) {
        Log.v(TAG, "onEmergencyLaneAssistance32FStateChanged  state=" + i);
    }

    @Deprecated
    public void onEmergencyLaneAssistanceStateChanged(int i) {
        Log.v(TAG, "onEmergencyLaneAssistanceStateChanged");
    }

    public void onEmergencyLaneKeepFault(byte b2) {
        Log.v(TAG, "onEmergencyLaneKeepFault " + ((int) b2));
    }

    public void onEmergencyVehicleWarningStateChanged(int i) {
        Log.v(TAG, "onEmergencyVehicleWarningStateChanged:" + i);
    }

    public abstract /* synthetic */ void onError(int i, String str);

    public void onEspOffKeyStatusChanged(int i) {
        Log.v(TAG, "onEspOffKeyStatusChanged " + i);
    }

    public void onFCWFaultStateChanged(int i) {
        Log.v(TAG, "onFCWFaultStateChanged:" + i);
    }

    public void onFatigueDetectionAidStateChanged(int i) {
        Log.v(TAG, "onFatigueDetectionAidStateChanged");
    }

    public void onFatigueMonitorChange(int i) {
        Log.v(TAG, "onFatigueMonitorChange:" + i);
    }

    public void onFaultHandlerStateChanged(int i) {
        Log.v(TAG, "onFaultHandlerStateChanged");
    }

    public void onForwardCollisionWarnFault(byte b2) {
        Log.v(TAG, "onForwardCollisionWarnFault " + ((int) b2));
    }

    public void onFrontCarStartStateChange(int i) {
        Log.v(TAG, "onFrontCarStartStateChange");
    }

    public void onFrontCrossAlarkFault(byte b2) {
        Log.v(TAG, "onFrontCrossAlarkFault " + ((int) b2));
    }

    public void onFrontCrossBrakeFault(byte b2) {
        Log.v(TAG, "onFrontCrossBrakeFault " + ((int) b2));
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
        Log.v(TAG, "onGreenLightOptimalSpeedAdvisoryStateChanged:" + i);
    }

    public void onHighNaviFault(byte b2) {
        Log.v(TAG, "onHighNaviFault " + ((int) b2));
    }

    public void onHighSpeedAINavigationStateChanged(int i) {
        Log.v(TAG, "onHighSpeedAINavigationStateChanged");
    }

    public void onHighSpeedOpenWindowStateChanged(int i) {
        Log.v(TAG, "onHighSpeedOpenWindowStateChanged");
    }

    public void onISLIWarningLevelSwitchStatusChanged(int i) {
        Log.v(TAG, "onISLIWarningLevelSwitchStatusChanged " + i);
    }

    public void onISLIWarningStateChanged(int i) {
        Log.v(TAG, "onISLIWarningStateChanged: state = " + i);
    }

    public void onIntelligentLimitFaultCall(byte b2) {
        Log.v(TAG, "onIntelligentLimitFaultCall " + ((int) b2));
    }

    public void onIntelligentLimitFaultController(byte b2) {
        Log.v(TAG, "onIntelligentLimitFaultController " + ((int) b2));
    }

    public void onIntelligentSpeedLimitAlert2B4StateChanged(int i) {
        Log.v(TAG, "onIntelligentSpeedLimitAlert2B4StateChanged  state=" + i);
    }

    public void onIntelligentSpeedLimitAlert2D5StateChanged(int i) {
        Log.v(TAG, "onIntelligentSpeedLimitAlert2D5StateChanged  state=" + i);
    }

    @Deprecated
    public void onIntelligentSpeedLimitAlertStateChanged(int i) {
        Log.v(TAG, "onIntelligentSpeedLimitAlertStateChanged");
    }

    public void onIntelligentSpeedLimitControl2D5StateChanged(int i) {
        Log.v(TAG, "onIntelligentSpeedLimitControl2D5StateChanged  state=" + i);
    }

    public void onIntelligentSpeedLimitControl32FStateChanged(int i) {
        Log.v(TAG, "onIntelligentSpeedLimitControl32FStateChanged  state=" + i);
    }

    @Deprecated
    public void onIntelligentSpeedLimitControlStateChanged(int i) {
        Log.v(TAG, "onIntelligentSpeedLimitControlStateChanged");
    }

    public void onInteractiveLaneChangeAssistanceStateChanged(int i) {
        Log.v(TAG, "onInteractiveLaneChangeAssistanceStateChanged");
    }

    public void onInteractiveLaneChangeFault(byte b2) {
        Log.v(TAG, "onInteractiveLaneChangeFault " + ((int) b2));
    }

    public void onIntersectionCollisionAlertStateChanged(int i) {
        Log.v(TAG, "onIntersectionCollisionAlertStateChanged:" + i);
    }

    public void onLDWFaultStateChanged(int i) {
        Log.v(TAG, "onLDWFaultStateChanged:" + i);
    }

    public void onLDWSensitivityChange(int i) {
        Log.v(TAG, "onLDWSensitivityChange" + i);
    }

    public void onLKSFaultStateStateChanged(int i) {
        Log.v(TAG, "onLKSFaultStateStateChanged:" + i);
    }

    public void onLaneAssistTypeChanged(int i) {
        Log.v(TAG, "onLaneAssistTypeChanged");
    }

    public void onLaneCenteringControlFault(byte b2) {
        Log.v(TAG, "onLaneCenteringControlFault " + ((int) b2));
    }

    public void onLaneDeparturePreventionFault(byte b2) {
        Log.v(TAG, "onLaneDeparturePreventionFault " + ((int) b2));
    }

    public void onLaneDepartureWarnFault(byte b2) {
        Log.v(TAG, "onLaneDepartureWarnFault " + ((int) b2));
    }

    public void onLaneKeepFault(byte b2) {
        Log.v(TAG, "onLaneKeepFault " + ((int) b2));
    }

    public void onLeftBehindReminderStateChanged(int i) {
        Log.v(TAG, "onLeftBehindReminderStateChanged");
    }

    public void onLimitSpeedChangeFault(byte b2) {
        Log.v(TAG, "onLimitSpeedChangeFault " + ((int) b2));
    }

    public void onLowSpeedEmergencyBrakingStateChanged(int i) {
        Log.v(TAG, "onLowSpeedEmergencyBrakingStateChanged:" + i);
    }

    public void onMOISFault(byte b2) {
        Log.v(TAG, "onMOISFault " + ((int) b2));
    }

    public void onMOISSwitchChange(int i) {
        Log.v(TAG, "onMOISSwitchChange" + i);
    }

    public void onMOISSystemChange(int i) {
        Log.v(TAG, "onMOISSystemChange" + i);
    }

    public void onMechanismPositionChanged(int i) {
        Log.v(TAG, "onMechanismPositionChanged:" + i);
    }

    public void onMistakeGasStateChanged(int i) {
        Log.v(TAG, "onMistakeGasStateChanged");
    }

    public void onNavigationPilotFault(byte b2) {
        Log.v(TAG, "onNavigationPilotFault " + ((int) b2));
    }

    public void onNrbanNaviFault(byte b2) {
        Log.v(TAG, "onNrbanNaviFault " + ((int) b2));
    }

    public void onOpenDoorAlertStateChanged(int i) {
        Log.v(TAG, "onOpenDoorAlertStateChanged");
    }

    public void onPredictCrashAlert138ModeChanged(int i) {
        Log.v(TAG, "onPredictCrashAlert138ModeChanged  mode=" + i);
    }

    public void onPredictCrashAlert32DModeChanged(int i) {
        Log.v(TAG, "onPredictCrashAlert32DModeChanged  mode=" + i);
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

    public void onRearCollisionWarnFault(byte b2) {
        Log.v(TAG, "onRearCollisionWarnFault " + ((int) b2));
    }

    public void onRearCrashAlertStateChanged(int i) {
        Log.v(TAG, "onRearCrashAlertStateChanged");
    }

    public void onRearCrossAlartFault(byte b2) {
        Log.v(TAG, "onRearCrossAlartFault " + ((int) b2));
    }

    public void onRearCrossBrakeFault(byte b2) {
        Log.v(TAG, "onRearCrossBrakeFault " + ((int) b2));
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
        Log.v(TAG, "onRedLightViolationWarningStateChanged:" + i);
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
        Log.v(TAG, "onSideDistanceWarningStateChanged:" + i);
    }

    public void onSpeedLimitRange2B4Changed(int i) {
        Log.v(TAG, "onSpeedLimitRange2B4Changed  value=" + i);
    }

    public void onSpeedLimitRange2D5Changed(int i) {
        Log.v(TAG, "onSpeedLimitRange2D5Changed  value=" + i);
    }

    @Deprecated
    public void onSpeedLimitRangeChanged(int i) {
        Log.v(TAG, "onSpeedLimitRangeChanged");
    }

    public void onSteepDescentSlowDownStateChanged(int i) {
        Log.v(TAG, "onSteepDescentSlowDownStateChanged");
    }

    public void onTSRLitSpdChgInformStateChanged(int i) {
        Log.v(TAG, "onTSRLitSpdChgInformStateChanged: state = " + i);
    }

    public void onTSRLitSpdChgSwitchStateChanged(int i) {
        Log.v(TAG, "onTSRLitSpdChgSwitchStateChanged: state = " + i);
    }

    public void onTemperatureWhileFatigueChanged(int i) {
        Log.v(TAG, "onTemperatureWhileFatigueChanged");
    }

    public void onTrafficAttentionFault(byte b2) {
        Log.v(TAG, "onTrafficAttentionFault " + ((int) b2));
    }

    public void onTrafficIncidentWarningStateChanged(int i) {
        Log.v(TAG, "onTrafficIncidentWarningStateChanged:" + i);
    }

    public void onTrafficJamWarningStateChanged(int i) {
        Log.v(TAG, "onTrafficJamWarningStateChanged:" + i);
    }

    public void onTrafficLightInfoPushStateChanged(int i) {
        Log.v(TAG, "onTrafficLightInfoPushStateChanged:" + i);
    }

    public void onTrafficLightRecognize2B4StateChanged(int i) {
        Log.v(TAG, "onTrafficLightRecognize2B4StateChanged  state=" + i);
    }

    public void onTrafficLightRecognize2D5StateChanged(int i) {
        Log.v(TAG, "onTrafficLightRecognize2D5StateChanged  state=" + i);
    }

    @Deprecated
    public void onTrafficLightRecognizeStateChanged(int i) {
        Log.v(TAG, "onTrafficLightRecognizeStateChanged");
    }

    public void onTrafficSignRecognize2B4StateChanged(int i) {
        Log.v(TAG, "onTrafficSignRecognize2B4StateChanged  state=" + i);
    }

    public void onTrafficSignRecognize2D5StateChanged(int i) {
        Log.v(TAG, "onTrafficSignRecognize2D5StateChanged  state=" + i);
    }

    public void onTrafficSignRecognize316StateChanged(int i) {
        Log.v(TAG, "onTrafficSignRecognize316StateChanged  state=" + i);
    }

    @Deprecated
    public void onTrafficSignRecognizeStateChanged(int i) {
        Log.v(TAG, "onTrafficSignRecognizeStateChanged");
    }

    public void onTrafficSignalWarningStateChanged(int i) {
        Log.v(TAG, "onTrafficSignalWarningStateChanged:" + i);
    }

    public void onTsrFault(byte b2) {
        Log.v(TAG, "onTsrFault " + ((int) b2));
    }

    public void onUrbanAIAssistanceStateChanged(int i) {
        Log.v(TAG, "onUrbanAIAssistanceStateChanged");
    }

    public void onVRUFaultStateStateChanged(int i) {
        Log.v(TAG, "onVRUFaultStateStateChanged:" + i);
    }

    public void onVerifyModeChanged(int i) {
        Log.v(TAG, "onVerifyModeChanged");
    }
}

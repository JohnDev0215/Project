package android.hardware.bydauto.dipilot;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoDiPilotListener implements d {
    public static final String TAG = "AbsBYDAutoDiPilotListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z3) {
        super.isConnected(z3);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z3) {
        super.isOptimizationApi(z3);
    }

    public void onADASOnlineStateChanged(int i3) {
        Log.v(TAG, "onADASOnlineStateChanged  state=" + i3);
    }

    public void onADASSoftStateChanged(int i3) {
        Log.v(TAG, "onADASSoftStateChanged: state = " + i3);
    }

    public void onAEBDecActiveChanged(int i3) {
        Log.v(TAG, "onAEBDecActiveChanged " + i3);
    }

    public void onAEBFaultStateStateChanged(int i3) {
        Log.v(TAG, "onAEBFaultStateStateChanged:" + i3);
    }

    public void onAINavigationStateChanged(int i3) {
        Log.v(TAG, "onAINavigationStateChanged");
    }

    public void onAbnormalVehicleWarningStateChanged(int i3) {
        Log.v(TAG, "onAbnormalVehicleWarningStateChanged:" + i3);
    }

    public void onAdaptiveFrontLightFault(byte b3) {
        Log.v(TAG, "onAdaptiveFrontLightFault " + ((int) b3));
    }

    public void onAdativeCruiseControlFault(byte b3) {
        Log.v(TAG, "onAdativeCruiseControlFault " + ((int) b3));
    }

    public void onAiCareStateChanged(int i3) {
        Log.v(TAG, "onAiCareStateChanged");
    }

    public void onAiDistanceLight2B4StateChanged(int i3) {
        Log.v(TAG, "onAiDistanceLight2B4StateChanged  state=" + i3);
    }

    public void onAiDistanceLight316StateChanged(int i3) {
        Log.v(TAG, "onAiDistanceLight316StateChanged  state=" + i3);
    }

    @Deprecated
    public void onAiDistanceLightStateChanged(int i3) {
        Log.v(TAG, "onAiDistanceLightStateChanged");
    }

    public void onAiEmergencyBrakeStateChanged(int i3) {
        Log.v(TAG, "onAiEmergencyBrakeStateChanged");
    }

    public void onAiMatrixLight3ACStateChanged(int i3) {
        Log.v(TAG, "onAiMatrixLight3ACStateChanged  state=" + i3);
    }

    public void onAiMatrixLight3ADStateChanged(int i3) {
        Log.v(TAG, "onAiMatrixLight3ADStateChanged  state=" + i3);
    }

    public void onAiMatrixLightStateChanged(int i3) {
        Log.v(TAG, "onAiMatrixLightStateChanged");
    }

    public void onAiTurnLightStateChanged(int i3) {
        Log.v(TAG, "onAiTurnLightStateChanged");
    }

    public void onAlarmTypeChanged(int i3) {
        Log.v(TAG, "onAlarmTypeChanged");
    }

    public void onAttentionDetectionStateChanged(int i3) {
        Log.v(TAG, "onAttentionDetectionStateChanged");
    }

    public void onAutoEmergencyFault(byte b3) {
        Log.v(TAG, "onAutoEmergencyFault " + ((int) b3));
    }

    public void onAutoParkingStateChanged(int i3) {
        Log.v(TAG, "onAutoParkingStateChanged");
    }

    public void onBSISFault(byte b3) {
        Log.v(TAG, "onBSISFault " + ((int) b3));
    }

    public void onBSISSwitchChange(int i3) {
        Log.v(TAG, "onBSISSwitchChange" + i3);
    }

    public void onBSISSystemChange(int i3) {
        Log.v(TAG, "onBSISSystemChange" + i3);
    }

    public void onBlindFault(byte b3) {
        Log.v(TAG, "onBlindFault " + ((int) b3));
    }

    public void onBlindMonitorStateChanged(int i3) {
        Log.v(TAG, "onBlindMonitorStateChanged");
    }

    public void onBlindMonitorStateChanged1(int i3) {
        Log.v(TAG, "onBlindMonitorStateChanged1  state=" + i3);
    }

    public void onBlindMonitorStateChanged2(int i3) {
        Log.v(TAG, "onBlindMonitorStateChanged2  state=" + i3);
    }

    public void onChargerCoverStateChanged(int i3) {
        Log.v(TAG, "onChargerCoverStateChanged");
    }

    public void onChildCareStateChanged(int i3) {
        Log.v(TAG, "onChildCareStateChanged");
    }

    public void onCollisionWarningStateChanged(int i3) {
        Log.v(TAG, "onCollisionWarningStateChanged:" + i3);
    }

    public void onControlLossWarningStateChanged(int i3) {
        Log.v(TAG, "onControlLossWarningStateChanged:" + i3);
    }

    @Deprecated
    public void onCpdDelayStatusChanged(int i3) {
        Log.v(TAG, "onCpdDelayStatusChanged  status=" + i3);
    }

    public void onCpdStatusChanged(int i3) {
        Log.v(TAG, "onCpdStatusChanged  status=" + i3);
    }

    public void onCpdWarningStateChanged(int i3) {
        Log.v(TAG, "onCpdWarningStateChanged " + i3);
    }

    public void onCpdWorkingStatusChanged(int i3) {
        Log.v(TAG, "onCpdWorkingStatusChanged " + i3);
    }

    public void onCurveWarnFault(byte b3) {
        Log.v(TAG, "onCurveWarnFault " + ((int) b3));
    }

    public void onDangerousActionDetectionStateChanged(int i3) {
        Log.v(TAG, "onDangerousActionDetectionStateChanged");
    }

    public abstract /* synthetic */ void onDataChanged(c cVar);

    public abstract /* synthetic */ void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue);

    public void onDistractionDetectionAidStateChanged(int i3) {
        Log.v(TAG, "onDistractionDetectionAidStateChanged");
    }

    public void onDmsStatusChanged(int i3) {
        Log.v(TAG, "onDmsStatusChanged  status=" + i3);
    }

    public void onDnpStatusChanged(int i3) {
        Log.v(TAG, "onDnpStatusChanged:" + i3);
    }

    public void onDoNotPassWarningStateChanged(int i3) {
        Log.v(TAG, "onDoNotPassWarningStateChanged:" + i3);
    }

    public void onDoorOpenFault(byte b3) {
        Log.v(TAG, "onDoorOpenFault " + ((int) b3));
    }

    public void onDriveDepartureStateChanged(int i3) {
        Log.v(TAG, "onDriveDepartureStateChanged");
    }

    public void onDriveLevelChanged(int i3) {
        Log.v(TAG, "onDriveLevelChanged");
    }

    public void onDriveRemindStateChanged(int i3) {
        Log.v(TAG, "onDriveRemindStateChanged");
    }

    public void onDrivieDepartureStateChanged(int i3) {
        Log.v(TAG, "onDrivieDepartureStateChanged");
    }

    public void onDrivieRemindStateChanged(int i3) {
        Log.v(TAG, "onDrivieRemindStateChanged");
    }

    public void onE2EAIAssistanceStateChanged(int i3) {
        Log.v(TAG, "onE2EAIAssistanceStateChanged");
    }

    public void onELKTakeOverStateChanged(int i3) {
        Log.v(TAG, "onELKTakeOverStateChanged: state = " + i3);
    }

    public void onElecCarBodyStablySysStateChanged(int i3) {
        Log.v(TAG, "onElecCarBodyStablySysStateChanged");
    }

    public void onEmergencyBrakeWarningStateChanged(int i3) {
        Log.v(TAG, "onEmergencyBrakeWarningStateChanged:" + i3);
    }

    public void onEmergencyLaneAssistance2F0StateChanged(int i3) {
        Log.v(TAG, "onEmergencyLaneAssistance2F0StateChanged  state=" + i3);
    }

    public void onEmergencyLaneAssistance32FStateChanged(int i3) {
        Log.v(TAG, "onEmergencyLaneAssistance32FStateChanged  state=" + i3);
    }

    @Deprecated
    public void onEmergencyLaneAssistanceStateChanged(int i3) {
        Log.v(TAG, "onEmergencyLaneAssistanceStateChanged");
    }

    public void onEmergencyLaneKeepFault(byte b3) {
        Log.v(TAG, "onEmergencyLaneKeepFault " + ((int) b3));
    }

    public void onEmergencyVehicleWarningStateChanged(int i3) {
        Log.v(TAG, "onEmergencyVehicleWarningStateChanged:" + i3);
    }

    public abstract /* synthetic */ void onError(int i3, String str);

    public void onEspOffKeyStatusChanged(int i3) {
        Log.v(TAG, "onEspOffKeyStatusChanged " + i3);
    }

    public void onFCWFaultStateChanged(int i3) {
        Log.v(TAG, "onFCWFaultStateChanged:" + i3);
    }

    public void onFatigueDetectionAidStateChanged(int i3) {
        Log.v(TAG, "onFatigueDetectionAidStateChanged");
    }

    public void onFatigueMonitorChange(int i3) {
        Log.v(TAG, "onFatigueMonitorChange:" + i3);
    }

    public void onFaultHandlerStateChanged(int i3) {
        Log.v(TAG, "onFaultHandlerStateChanged");
    }

    public void onForwardCollisionWarnFault(byte b3) {
        Log.v(TAG, "onForwardCollisionWarnFault " + ((int) b3));
    }

    public void onFrontCarStartStateChange(int i3) {
        Log.v(TAG, "onFrontCarStartStateChange");
    }

    public void onFrontCrossAlarkFault(byte b3) {
        Log.v(TAG, "onFrontCrossAlarkFault " + ((int) b3));
    }

    public void onFrontCrossBrakeFault(byte b3) {
        Log.v(TAG, "onFrontCrossBrakeFault " + ((int) b3));
    }

    public void onFrontTrafficBrakeAlertStateChanged(int i3) {
        Log.v(TAG, "onFrontTrafficBrakeAlertStateChanged");
    }

    public void onFrontTrafficCrossAlertStateChanged(int i3) {
        Log.v(TAG, "onFrontTrafficCrossAlertStateChanged");
    }

    public void onGazePerceptionStateChanged(int i3) {
        Log.v(TAG, "onGazePerceptionStateChanged");
    }

    public void onGestureRecognitionStateChanged(int i3) {
        Log.v(TAG, "onGestureRecognitionStateChanged");
    }

    public void onGreenLightOptimalSpeedAdvisoryStateChanged(int i3) {
        Log.v(TAG, "onGreenLightOptimalSpeedAdvisoryStateChanged:" + i3);
    }

    public void onHighHODToastChanged(int i3) {
        Log.v(TAG, "onHighHODToastChanged state is " + i3);
    }

    public void onHighIOPToastChanged(int i3) {
        Log.v(TAG, "onHighIOPToastChanged state is " + i3);
    }

    public void onHighNaviFault(byte b3) {
        Log.v(TAG, "onHighNaviFault " + ((int) b3));
    }

    public void onHighSpeedAINavigationStateChanged(int i3) {
        Log.v(TAG, "onHighSpeedAINavigationStateChanged");
    }

    public void onHighSpeedOpenWindowStateChanged(int i3) {
        Log.v(TAG, "onHighSpeedOpenWindowStateChanged");
    }

    public void onIOPStateChanged(int i3) {
        Log.v(TAG, "onIOPStateChanged state is " + i3);
    }

    public void onISLIWarningLevelSwitchStatusChanged(int i3) {
        Log.v(TAG, "onISLIWarningLevelSwitchStatusChanged " + i3);
    }

    public void onISLIWarningStateChanged(int i3) {
        Log.v(TAG, "onISLIWarningStateChanged: state = " + i3);
    }

    public void onIntelligentLimitFaultCall(byte b3) {
        Log.v(TAG, "onIntelligentLimitFaultCall " + ((int) b3));
    }

    public void onIntelligentLimitFaultController(byte b3) {
        Log.v(TAG, "onIntelligentLimitFaultController " + ((int) b3));
    }

    public void onIntelligentSpeedLimitAlert2B4StateChanged(int i3) {
        Log.v(TAG, "onIntelligentSpeedLimitAlert2B4StateChanged  state=" + i3);
    }

    public void onIntelligentSpeedLimitAlert2D5StateChanged(int i3) {
        Log.v(TAG, "onIntelligentSpeedLimitAlert2D5StateChanged  state=" + i3);
    }

    @Deprecated
    public void onIntelligentSpeedLimitAlertStateChanged(int i3) {
        Log.v(TAG, "onIntelligentSpeedLimitAlertStateChanged");
    }

    public void onIntelligentSpeedLimitControl2D5StateChanged(int i3) {
        Log.v(TAG, "onIntelligentSpeedLimitControl2D5StateChanged  state=" + i3);
    }

    public void onIntelligentSpeedLimitControl32FStateChanged(int i3) {
        Log.v(TAG, "onIntelligentSpeedLimitControl32FStateChanged  state=" + i3);
    }

    @Deprecated
    public void onIntelligentSpeedLimitControlStateChanged(int i3) {
        Log.v(TAG, "onIntelligentSpeedLimitControlStateChanged");
    }

    public void onInteractiveLaneChangeAssistanceStateChanged(int i3) {
        Log.v(TAG, "onInteractiveLaneChangeAssistanceStateChanged");
    }

    public void onInteractiveLaneChangeFault(byte b3) {
        Log.v(TAG, "onInteractiveLaneChangeFault " + ((int) b3));
    }

    public void onIntersectionCollisionAlertStateChanged(int i3) {
        Log.v(TAG, "onIntersectionCollisionAlertStateChanged:" + i3);
    }

    public void onLDWFaultStateChanged(int i3) {
        Log.v(TAG, "onLDWFaultStateChanged:" + i3);
    }

    public void onLDWSensitivityChange(int i3) {
        Log.v(TAG, "onLDWSensitivityChange" + i3);
    }

    public void onLKSFaultStateStateChanged(int i3) {
        Log.v(TAG, "onLKSFaultStateStateChanged:" + i3);
    }

    public void onLaneAssistTypeChanged(int i3) {
        Log.v(TAG, "onLaneAssistTypeChanged");
    }

    public void onLaneCenteringControlFault(byte b3) {
        Log.v(TAG, "onLaneCenteringControlFault " + ((int) b3));
    }

    public void onLaneDeparturePreventionFault(byte b3) {
        Log.v(TAG, "onLaneDeparturePreventionFault " + ((int) b3));
    }

    public void onLaneDepartureWarnFault(byte b3) {
        Log.v(TAG, "onLaneDepartureWarnFault " + ((int) b3));
    }

    public void onLaneKeepFault(byte b3) {
        Log.v(TAG, "onLaneKeepFault " + ((int) b3));
    }

    public void onLeftBehindReminderStateChanged(int i3) {
        Log.v(TAG, "onLeftBehindReminderStateChanged");
    }

    public void onLimitSpeedChangeFault(byte b3) {
        Log.v(TAG, "onLimitSpeedChangeFault " + ((int) b3));
    }

    public void onLowHODToastChanged(int i3) {
        Log.v(TAG, "onLowHODToastChanged state is " + i3);
    }

    public void onLowIOPToastChanged(int i3) {
        Log.v(TAG, "onLowIOPToastChanged state is " + i3);
    }

    public void onLowSpeedEmergencyBrakingStateChanged(int i3) {
        Log.v(TAG, "onLowSpeedEmergencyBrakingStateChanged:" + i3);
    }

    public void onMOISFault(byte b3) {
        Log.v(TAG, "onMOISFault " + ((int) b3));
    }

    public void onMOISSwitchChange(int i3) {
        Log.v(TAG, "onMOISSwitchChange" + i3);
    }

    public void onMOISSystemChange(int i3) {
        Log.v(TAG, "onMOISSystemChange" + i3);
    }

    public void onMechanismPositionChanged(int i3) {
        Log.v(TAG, "onMechanismPositionChanged:" + i3);
    }

    public void onMistakeGasStateChanged(int i3) {
        Log.v(TAG, "onMistakeGasStateChanged");
    }

    public void onNavigationPilotFault(byte b3) {
        Log.v(TAG, "onNavigationPilotFault " + ((int) b3));
    }

    public void onNrbanNaviFault(byte b3) {
        Log.v(TAG, "onNrbanNaviFault " + ((int) b3));
    }

    public void onOpenDoorAlertStateChanged(int i3) {
        Log.v(TAG, "onOpenDoorAlertStateChanged");
    }

    public void onPredictCrashAlert138ModeChanged(int i3) {
        Log.v(TAG, "onPredictCrashAlert138ModeChanged  mode=" + i3);
    }

    public void onPredictCrashAlert32DModeChanged(int i3) {
        Log.v(TAG, "onPredictCrashAlert32DModeChanged  mode=" + i3);
    }

    @Deprecated
    public void onPredictCrashAlertModeChanged(int i3) {
        Log.v(TAG, "onPredictCrashAlertModeChanged");
    }

    public void onPredictCrashAlertStateChange(int i3) {
        Log.v(TAG, "onPredictCrashAlertStateChange");
    }

    public void onRainOpenFogLightStateChanged(int i3) {
        Log.v(TAG, "onRainOpenFogLightStateChanged");
    }

    public void onRearCollisionWarnFault(byte b3) {
        Log.v(TAG, "onRearCollisionWarnFault " + ((int) b3));
    }

    public void onRearCrashAlertStateChanged(int i3) {
        Log.v(TAG, "onRearCrashAlertStateChanged");
    }

    public void onRearCrossAlartFault(byte b3) {
        Log.v(TAG, "onRearCrossAlartFault " + ((int) b3));
    }

    public void onRearCrossBrakeFault(byte b3) {
        Log.v(TAG, "onRearCrossBrakeFault " + ((int) b3));
    }

    public void onRearTrafficBrakeAlertStateChanged(int i3) {
        Log.v(TAG, "onRearTrafficBrakeAlertStateChanged");
    }

    public void onRearTrafficCrossAlertStateChanged(int i3) {
        Log.v(TAG, "onRearTrafficCrossAlertStateChanged");
    }

    public void onRecommendRestSpotStateChanged(int i3) {
        Log.v(TAG, "onRecommendRestSpotStateChanged");
    }

    public void onRedLightViolationWarningStateChanged(int i3) {
        Log.v(TAG, "onRedLightViolationWarningStateChanged:" + i3);
    }

    public void onRelieveFatigueStateChanged(int i3) {
        Log.v(TAG, "onRelieveFatigueStateChanged");
    }

    public void onRemoteControlDriveCloseTops(int i3) {
        Log.v(TAG, "onRemoteControlDriveCloseTops");
    }

    public void onRequestHUNPClose(int i3) {
        Log.v(TAG, "onRequestHUNPClose");
    }

    public void onSAOCStateChanged(int i3) {
        Log.v(TAG, "onSAOCStateChanged state is " + i3);
    }

    public void onSLIFStateChanged(int i3) {
        Log.v(TAG, "onSLIFStateChanged state is " + i3);
    }

    public void onSideDistanceWarningStateChanged(int i3) {
        Log.v(TAG, "onSideDistanceWarningStateChanged:" + i3);
    }

    public void onSpeedLimitRange2B4Changed(int i3) {
        Log.v(TAG, "onSpeedLimitRange2B4Changed  value=" + i3);
    }

    public void onSpeedLimitRange2D5Changed(int i3) {
        Log.v(TAG, "onSpeedLimitRange2D5Changed  value=" + i3);
    }

    @Deprecated
    public void onSpeedLimitRangeChanged(int i3) {
        Log.v(TAG, "onSpeedLimitRangeChanged");
    }

    public void onSteepDescentSlowDownStateChanged(int i3) {
        Log.v(TAG, "onSteepDescentSlowDownStateChanged");
    }

    public void onTSRLitSpdChgInformStateChanged(int i3) {
        Log.v(TAG, "onTSRLitSpdChgInformStateChanged: state = " + i3);
    }

    public void onTSRLitSpdChgSwitchStateChanged(int i3) {
        Log.v(TAG, "onTSRLitSpdChgSwitchStateChanged: state = " + i3);
    }

    public void onTemperatureWhileFatigueChanged(int i3) {
        Log.v(TAG, "onTemperatureWhileFatigueChanged");
    }

    public void onTrafficAttentionFault(byte b3) {
        Log.v(TAG, "onTrafficAttentionFault " + ((int) b3));
    }

    public void onTrafficIncidentWarningStateChanged(int i3) {
        Log.v(TAG, "onTrafficIncidentWarningStateChanged:" + i3);
    }

    public void onTrafficJamWarningStateChanged(int i3) {
        Log.v(TAG, "onTrafficJamWarningStateChanged:" + i3);
    }

    public void onTrafficLightInfoPushStateChanged(int i3) {
        Log.v(TAG, "onTrafficLightInfoPushStateChanged:" + i3);
    }

    public void onTrafficLightRecognize2B4StateChanged(int i3) {
        Log.v(TAG, "onTrafficLightRecognize2B4StateChanged  state=" + i3);
    }

    public void onTrafficLightRecognize2D5StateChanged(int i3) {
        Log.v(TAG, "onTrafficLightRecognize2D5StateChanged  state=" + i3);
    }

    @Deprecated
    public void onTrafficLightRecognizeStateChanged(int i3) {
        Log.v(TAG, "onTrafficLightRecognizeStateChanged");
    }

    public void onTrafficSignRecognize2B4StateChanged(int i3) {
        Log.v(TAG, "onTrafficSignRecognize2B4StateChanged  state=" + i3);
    }

    public void onTrafficSignRecognize2D5StateChanged(int i3) {
        Log.v(TAG, "onTrafficSignRecognize2D5StateChanged  state=" + i3);
    }

    public void onTrafficSignRecognize316StateChanged(int i3) {
        Log.v(TAG, "onTrafficSignRecognize316StateChanged  state=" + i3);
    }

    @Deprecated
    public void onTrafficSignRecognizeStateChanged(int i3) {
        Log.v(TAG, "onTrafficSignRecognizeStateChanged");
    }

    public void onTrafficSignalWarningStateChanged(int i3) {
        Log.v(TAG, "onTrafficSignalWarningStateChanged:" + i3);
    }

    public void onTsrFault(byte b3) {
        Log.v(TAG, "onTsrFault " + ((int) b3));
    }

    public void onUrbanAIAssistanceStateChanged(int i3) {
        Log.v(TAG, "onUrbanAIAssistanceStateChanged");
    }

    public void onVRUFaultStateStateChanged(int i3) {
        Log.v(TAG, "onVRUFaultStateStateChanged:" + i3);
    }

    public void onVerifyModeChanged(int i3) {
        Log.v(TAG, "onVerifyModeChanged");
    }
}

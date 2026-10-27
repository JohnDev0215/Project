package android.hardware.bydauto.dipilot;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoDiPilotListener implements d {
    public static final String TAG = "AbsBYDAutoDiPilotListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z7) {
        super.isConnected(z7);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z7) {
        super.isOptimizationApi(z7);
    }

    public void onACCStateChanged(int i8) {
        Log.v(TAG, "onACCStateChanged state is " + i8);
    }

    public void onADASOnlineStateChanged(int i8) {
        Log.v(TAG, "onADASOnlineStateChanged  state=" + i8);
    }

    public void onADASSoftStateChanged(int i8) {
        Log.v(TAG, "onADASSoftStateChanged: state = " + i8);
    }

    public void onAEBDecActiveChanged(int i8) {
        Log.v(TAG, "onAEBDecActiveChanged " + i8);
    }

    public void onAEBFaultStateStateChanged(int i8) {
        Log.v(TAG, "onAEBFaultStateStateChanged:" + i8);
    }

    public void onAINavigationStateChanged(int i8) {
        Log.v(TAG, "onAINavigationStateChanged");
    }

    public void onAbnormalVehicleWarningStateChanged(int i8) {
        Log.v(TAG, "onAbnormalVehicleWarningStateChanged:" + i8);
    }

    public void onAdaptiveFrontLightFault(byte b8) {
        Log.v(TAG, "onAdaptiveFrontLightFault " + ((int) b8));
    }

    public void onAdativeCruiseControlFault(byte b8) {
        Log.v(TAG, "onAdativeCruiseControlFault " + ((int) b8));
    }

    public void onAiCareStateChanged(int i8) {
        Log.v(TAG, "onAiCareStateChanged");
    }

    public void onAiDistanceLight2B4StateChanged(int i8) {
        Log.v(TAG, "onAiDistanceLight2B4StateChanged  state=" + i8);
    }

    public void onAiDistanceLight316StateChanged(int i8) {
        Log.v(TAG, "onAiDistanceLight316StateChanged  state=" + i8);
    }

    @Deprecated
    public void onAiDistanceLightStateChanged(int i8) {
        Log.v(TAG, "onAiDistanceLightStateChanged");
    }

    public void onAiEmergencyBrakeStateChanged(int i8) {
        Log.v(TAG, "onAiEmergencyBrakeStateChanged");
    }

    public void onAiMatrixLight3ACStateChanged(int i8) {
        Log.v(TAG, "onAiMatrixLight3ACStateChanged  state=" + i8);
    }

    public void onAiMatrixLight3ADStateChanged(int i8) {
        Log.v(TAG, "onAiMatrixLight3ADStateChanged  state=" + i8);
    }

    public void onAiMatrixLightStateChanged(int i8) {
        Log.v(TAG, "onAiMatrixLightStateChanged");
    }

    public void onAiTurnLightStateChanged(int i8) {
        Log.v(TAG, "onAiTurnLightStateChanged");
    }

    public void onAlarmTypeChanged(int i8) {
        Log.v(TAG, "onAlarmTypeChanged");
    }

    public void onAttentionDetectionStateChanged(int i8) {
        Log.v(TAG, "onAttentionDetectionStateChanged");
    }

    public void onAutoEmergencyFault(byte b8) {
        Log.v(TAG, "onAutoEmergencyFault " + ((int) b8));
    }

    public void onAutoParkingStateChanged(int i8) {
        Log.v(TAG, "onAutoParkingStateChanged");
    }

    public void onBSISFault(byte b8) {
        Log.v(TAG, "onBSISFault " + ((int) b8));
    }

    public void onBSISSwitchChange(int i8) {
        Log.v(TAG, "onBSISSwitchChange" + i8);
    }

    public void onBSISSystemChange(int i8) {
        Log.v(TAG, "onBSISSystemChange" + i8);
    }

    public void onBlindFault(byte b8) {
        Log.v(TAG, "onBlindFault " + ((int) b8));
    }

    public void onBlindMonitorStateChanged(int i8) {
        Log.v(TAG, "onBlindMonitorStateChanged");
    }

    public void onBlindMonitorStateChanged1(int i8) {
        Log.v(TAG, "onBlindMonitorStateChanged1  state=" + i8);
    }

    public void onBlindMonitorStateChanged2(int i8) {
        Log.v(TAG, "onBlindMonitorStateChanged2  state=" + i8);
    }

    public void onChargerCoverStateChanged(int i8) {
        Log.v(TAG, "onChargerCoverStateChanged");
    }

    public void onChildCareStateChanged(int i8) {
        Log.v(TAG, "onChildCareStateChanged");
    }

    public void onCollisionWarningStateChanged(int i8) {
        Log.v(TAG, "onCollisionWarningStateChanged:" + i8);
    }

    public void onControlLossWarningStateChanged(int i8) {
        Log.v(TAG, "onControlLossWarningStateChanged:" + i8);
    }

    @Deprecated
    public void onCpdDelayStatusChanged(int i8) {
        Log.v(TAG, "onCpdDelayStatusChanged  status=" + i8);
    }

    public void onCpdStatusChanged(int i8) {
        Log.v(TAG, "onCpdStatusChanged  status=" + i8);
    }

    public void onCpdWarningStateChanged(int i8) {
        Log.v(TAG, "onCpdWarningStateChanged " + i8);
    }

    public void onCpdWorkingStatusChanged(int i8) {
        Log.v(TAG, "onCpdWorkingStatusChanged " + i8);
    }

    public void onCurveWarnFault(byte b8) {
        Log.v(TAG, "onCurveWarnFault " + ((int) b8));
    }

    public void onDangerousActionDetectionStateChanged(int i8) {
        Log.v(TAG, "onDangerousActionDetectionStateChanged");
    }

    public abstract /* synthetic */ void onDataChanged(c cVar);

    public abstract /* synthetic */ void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue);

    public void onDistractionDetectionAidStateChanged(int i8) {
        Log.v(TAG, "onDistractionDetectionAidStateChanged");
    }

    public void onDmsStatusChanged(int i8) {
        Log.v(TAG, "onDmsStatusChanged  status=" + i8);
    }

    public void onDnpStatusChanged(int i8) {
        Log.v(TAG, "onDnpStatusChanged:" + i8);
    }

    public void onDoNotPassWarningStateChanged(int i8) {
        Log.v(TAG, "onDoNotPassWarningStateChanged:" + i8);
    }

    public void onDoorOpenFault(byte b8) {
        Log.v(TAG, "onDoorOpenFault " + ((int) b8));
    }

    public void onDriveDepartureStateChanged(int i8) {
        Log.v(TAG, "onDriveDepartureStateChanged");
    }

    public void onDriveLevelChanged(int i8) {
        Log.v(TAG, "onDriveLevelChanged");
    }

    public void onDriveRemindStateChanged(int i8) {
        Log.v(TAG, "onDriveRemindStateChanged");
    }

    public void onDrivieDepartureStateChanged(int i8) {
        Log.v(TAG, "onDrivieDepartureStateChanged");
    }

    public void onDrivieRemindStateChanged(int i8) {
        Log.v(TAG, "onDrivieRemindStateChanged");
    }

    public void onE2EAIAssistanceStateChanged(int i8) {
        Log.v(TAG, "onE2EAIAssistanceStateChanged");
    }

    public void onELKTakeOverStateChanged(int i8) {
        Log.v(TAG, "onELKTakeOverStateChanged: state = " + i8);
    }

    public void onElecCarBodyStablySysStateChanged(int i8) {
        Log.v(TAG, "onElecCarBodyStablySysStateChanged");
    }

    public void onEmergencyBrakeWarningStateChanged(int i8) {
        Log.v(TAG, "onEmergencyBrakeWarningStateChanged:" + i8);
    }

    public void onEmergencyLaneAssistance2F0StateChanged(int i8) {
        Log.v(TAG, "onEmergencyLaneAssistance2F0StateChanged  state=" + i8);
    }

    public void onEmergencyLaneAssistance32FStateChanged(int i8) {
        Log.v(TAG, "onEmergencyLaneAssistance32FStateChanged  state=" + i8);
    }

    @Deprecated
    public void onEmergencyLaneAssistanceStateChanged(int i8) {
        Log.v(TAG, "onEmergencyLaneAssistanceStateChanged");
    }

    public void onEmergencyLaneKeepFault(byte b8) {
        Log.v(TAG, "onEmergencyLaneKeepFault " + ((int) b8));
    }

    public void onEmergencyVehicleWarningStateChanged(int i8) {
        Log.v(TAG, "onEmergencyVehicleWarningStateChanged:" + i8);
    }

    public abstract /* synthetic */ void onError(int i8, String str);

    public void onEspOffKeyStatusChanged(int i8) {
        Log.v(TAG, "onEspOffKeyStatusChanged " + i8);
    }

    public void onFCSRFault(int i8) {
        Log.v(TAG, "onFCSRFault state is " + i8);
    }

    public void onFCSRStateChanged(int i8) {
        Log.v(TAG, "onFCSRStateChanged state is " + i8);
    }

    public void onFCWFaultStateChanged(int i8) {
        Log.v(TAG, "onFCWFaultStateChanged:" + i8);
    }

    public void onFatigueDetectionAidStateChanged(int i8) {
        Log.v(TAG, "onFatigueDetectionAidStateChanged");
    }

    public void onFatigueMonitorChange(int i8) {
        Log.v(TAG, "onFatigueMonitorChange:" + i8);
    }

    public void onFaultHandlerStateChanged(int i8) {
        Log.v(TAG, "onFaultHandlerStateChanged");
    }

    public void onForwardCollisionWarnFault(byte b8) {
        Log.v(TAG, "onForwardCollisionWarnFault " + ((int) b8));
    }

    public void onFrontCarStartStateChange(int i8) {
        Log.v(TAG, "onFrontCarStartStateChange");
    }

    public void onFrontCrossAlarkFault(byte b8) {
        Log.v(TAG, "onFrontCrossAlarkFault " + ((int) b8));
    }

    public void onFrontCrossBrakeFault(byte b8) {
        Log.v(TAG, "onFrontCrossBrakeFault " + ((int) b8));
    }

    public void onFrontTrafficBrakeAlertStateChanged(int i8) {
        Log.v(TAG, "onFrontTrafficBrakeAlertStateChanged");
    }

    public void onFrontTrafficCrossAlertStateChanged(int i8) {
        Log.v(TAG, "onFrontTrafficCrossAlertStateChanged");
    }

    public void onGazePerceptionStateChanged(int i8) {
        Log.v(TAG, "onGazePerceptionStateChanged");
    }

    public void onGestureRecognitionStateChanged(int i8) {
        Log.v(TAG, "onGestureRecognitionStateChanged");
    }

    public void onGreenLightOptimalSpeedAdvisoryStateChanged(int i8) {
        Log.v(TAG, "onGreenLightOptimalSpeedAdvisoryStateChanged:" + i8);
    }

    public void onHighHODToastChanged(int i8) {
        Log.v(TAG, "onHighHODToastChanged state is " + i8);
    }

    public void onHighIOPToastChanged(int i8) {
        Log.v(TAG, "onHighIOPToastChanged state is " + i8);
    }

    public void onHighNaviFault(byte b8) {
        Log.v(TAG, "onHighNaviFault " + ((int) b8));
    }

    public void onHighSpeedAINavigationStateChanged(int i8) {
        Log.v(TAG, "onHighSpeedAINavigationStateChanged");
    }

    public void onHighSpeedOpenWindowStateChanged(int i8) {
        Log.v(TAG, "onHighSpeedOpenWindowStateChanged");
    }

    public void onIOPStateChanged(int i8) {
        Log.v(TAG, "onIOPStateChanged state is " + i8);
    }

    public void onISLIWarningLevelSwitchStatusChanged(int i8) {
        Log.v(TAG, "onISLIWarningLevelSwitchStatusChanged " + i8);
    }

    public void onISLIWarningStateChanged(int i8) {
        Log.v(TAG, "onISLIWarningStateChanged: state = " + i8);
    }

    public void onIntelligentLimitFaultCall(byte b8) {
        Log.v(TAG, "onIntelligentLimitFaultCall " + ((int) b8));
    }

    public void onIntelligentLimitFaultController(byte b8) {
        Log.v(TAG, "onIntelligentLimitFaultController " + ((int) b8));
    }

    public void onIntelligentSpeedLimitAlert2B4StateChanged(int i8) {
        Log.v(TAG, "onIntelligentSpeedLimitAlert2B4StateChanged  state=" + i8);
    }

    public void onIntelligentSpeedLimitAlert2D5StateChanged(int i8) {
        Log.v(TAG, "onIntelligentSpeedLimitAlert2D5StateChanged  state=" + i8);
    }

    @Deprecated
    public void onIntelligentSpeedLimitAlertStateChanged(int i8) {
        Log.v(TAG, "onIntelligentSpeedLimitAlertStateChanged");
    }

    public void onIntelligentSpeedLimitControl2D5StateChanged(int i8) {
        Log.v(TAG, "onIntelligentSpeedLimitControl2D5StateChanged  state=" + i8);
    }

    public void onIntelligentSpeedLimitControl32FStateChanged(int i8) {
        Log.v(TAG, "onIntelligentSpeedLimitControl32FStateChanged  state=" + i8);
    }

    @Deprecated
    public void onIntelligentSpeedLimitControlStateChanged(int i8) {
        Log.v(TAG, "onIntelligentSpeedLimitControlStateChanged");
    }

    public void onInteractiveLaneChangeAssistanceStateChanged(int i8) {
        Log.v(TAG, "onInteractiveLaneChangeAssistanceStateChanged");
    }

    public void onInteractiveLaneChangeFault(byte b8) {
        Log.v(TAG, "onInteractiveLaneChangeFault " + ((int) b8));
    }

    public void onIntersectionCollisionAlertStateChanged(int i8) {
        Log.v(TAG, "onIntersectionCollisionAlertStateChanged:" + i8);
    }

    public void onLDWFaultStateChanged(int i8) {
        Log.v(TAG, "onLDWFaultStateChanged:" + i8);
    }

    public void onLDWSensitivityChange(int i8) {
        Log.v(TAG, "onLDWSensitivityChange" + i8);
    }

    public void onLKSFaultStateStateChanged(int i8) {
        Log.v(TAG, "onLKSFaultStateStateChanged:" + i8);
    }

    public void onLaneAssistTypeChanged(int i8) {
        Log.v(TAG, "onLaneAssistTypeChanged");
    }

    public void onLaneCenteringControlFault(byte b8) {
        Log.v(TAG, "onLaneCenteringControlFault " + ((int) b8));
    }

    public void onLaneDeparturePreventionFault(byte b8) {
        Log.v(TAG, "onLaneDeparturePreventionFault " + ((int) b8));
    }

    public void onLaneDepartureWarnFault(byte b8) {
        Log.v(TAG, "onLaneDepartureWarnFault " + ((int) b8));
    }

    public void onLaneKeepFault(byte b8) {
        Log.v(TAG, "onLaneKeepFault " + ((int) b8));
    }

    public void onLeftBehindReminderStateChanged(int i8) {
        Log.v(TAG, "onLeftBehindReminderStateChanged");
    }

    public void onLimitSpeedChangeFault(byte b8) {
        Log.v(TAG, "onLimitSpeedChangeFault " + ((int) b8));
    }

    public void onLowHODToastChanged(int i8) {
        Log.v(TAG, "onLowHODToastChanged state is " + i8);
    }

    public void onLowIOPToastChanged(int i8) {
        Log.v(TAG, "onLowIOPToastChanged state is " + i8);
    }

    public void onLowSpeedEmergencyBrakingStateChanged(int i8) {
        Log.v(TAG, "onLowSpeedEmergencyBrakingStateChanged:" + i8);
    }

    public void onMOISFault(byte b8) {
        Log.v(TAG, "onMOISFault " + ((int) b8));
    }

    public void onMOISSwitchChange(int i8) {
        Log.v(TAG, "onMOISSwitchChange" + i8);
    }

    public void onMOISSystemChange(int i8) {
        Log.v(TAG, "onMOISSystemChange" + i8);
    }

    public void onMechanismPositionChanged(int i8) {
        Log.v(TAG, "onMechanismPositionChanged:" + i8);
    }

    public void onMistakeGasStateChanged(int i8) {
        Log.v(TAG, "onMistakeGasStateChanged");
    }

    public void onNavigationPilotFault(byte b8) {
        Log.v(TAG, "onNavigationPilotFault " + ((int) b8));
    }

    public void onNrbanNaviFault(byte b8) {
        Log.v(TAG, "onNrbanNaviFault " + ((int) b8));
    }

    public void onOpenDoorAlertStateChanged(int i8) {
        Log.v(TAG, "onOpenDoorAlertStateChanged");
    }

    public void onPredictCrashAlert138ModeChanged(int i8) {
        Log.v(TAG, "onPredictCrashAlert138ModeChanged  mode=" + i8);
    }

    public void onPredictCrashAlert32DModeChanged(int i8) {
        Log.v(TAG, "onPredictCrashAlert32DModeChanged  mode=" + i8);
    }

    @Deprecated
    public void onPredictCrashAlertModeChanged(int i8) {
        Log.v(TAG, "onPredictCrashAlertModeChanged");
    }

    public void onPredictCrashAlertStateChange(int i8) {
        Log.v(TAG, "onPredictCrashAlertStateChange");
    }

    public void onRainOpenFogLightStateChanged(int i8) {
        Log.v(TAG, "onRainOpenFogLightStateChanged");
    }

    public void onRearCollisionWarnFault(byte b8) {
        Log.v(TAG, "onRearCollisionWarnFault " + ((int) b8));
    }

    public void onRearCrashAlertStateChanged(int i8) {
        Log.v(TAG, "onRearCrashAlertStateChanged");
    }

    public void onRearCrossAlartFault(byte b8) {
        Log.v(TAG, "onRearCrossAlartFault " + ((int) b8));
    }

    public void onRearCrossBrakeFault(byte b8) {
        Log.v(TAG, "onRearCrossBrakeFault " + ((int) b8));
    }

    public void onRearTrafficBrakeAlertStateChanged(int i8) {
        Log.v(TAG, "onRearTrafficBrakeAlertStateChanged");
    }

    public void onRearTrafficCrossAlertStateChanged(int i8) {
        Log.v(TAG, "onRearTrafficCrossAlertStateChanged");
    }

    public void onRecommendRestSpotStateChanged(int i8) {
        Log.v(TAG, "onRecommendRestSpotStateChanged");
    }

    public void onRedLightViolationWarningStateChanged(int i8) {
        Log.v(TAG, "onRedLightViolationWarningStateChanged:" + i8);
    }

    public void onRelieveFatigueStateChanged(int i8) {
        Log.v(TAG, "onRelieveFatigueStateChanged");
    }

    public void onRemoteControlDriveCloseTops(int i8) {
        Log.v(TAG, "onRemoteControlDriveCloseTops");
    }

    public void onRequestHUNPClose(int i8) {
        Log.v(TAG, "onRequestHUNPClose");
    }

    public void onSAOCStateChanged(int i8) {
        Log.v(TAG, "onSAOCStateChanged state is " + i8);
    }

    public void onSLIFStateChanged(int i8) {
        Log.v(TAG, "onSLIFStateChanged state is " + i8);
    }

    public void onSideDistanceWarningStateChanged(int i8) {
        Log.v(TAG, "onSideDistanceWarningStateChanged:" + i8);
    }

    public void onSpeedLimitRange2B4Changed(int i8) {
        Log.v(TAG, "onSpeedLimitRange2B4Changed  value=" + i8);
    }

    public void onSpeedLimitRange2D5Changed(int i8) {
        Log.v(TAG, "onSpeedLimitRange2D5Changed  value=" + i8);
    }

    @Deprecated
    public void onSpeedLimitRangeChanged(int i8) {
        Log.v(TAG, "onSpeedLimitRangeChanged");
    }

    public void onSteepDescentSlowDownStateChanged(int i8) {
        Log.v(TAG, "onSteepDescentSlowDownStateChanged");
    }

    public void onTSRLitSpdChgInformStateChanged(int i8) {
        Log.v(TAG, "onTSRLitSpdChgInformStateChanged: state = " + i8);
    }

    public void onTSRLitSpdChgSwitchStateChanged(int i8) {
        Log.v(TAG, "onTSRLitSpdChgSwitchStateChanged: state = " + i8);
    }

    public void onTemperatureWhileFatigueChanged(int i8) {
        Log.v(TAG, "onTemperatureWhileFatigueChanged");
    }

    public void onTrafficAttentionFault(byte b8) {
        Log.v(TAG, "onTrafficAttentionFault " + ((int) b8));
    }

    public void onTrafficIncidentWarningStateChanged(int i8) {
        Log.v(TAG, "onTrafficIncidentWarningStateChanged:" + i8);
    }

    public void onTrafficJamWarningStateChanged(int i8) {
        Log.v(TAG, "onTrafficJamWarningStateChanged:" + i8);
    }

    public void onTrafficLightInfoPushStateChanged(int i8) {
        Log.v(TAG, "onTrafficLightInfoPushStateChanged:" + i8);
    }

    public void onTrafficLightRecognize2B4StateChanged(int i8) {
        Log.v(TAG, "onTrafficLightRecognize2B4StateChanged  state=" + i8);
    }

    public void onTrafficLightRecognize2D5StateChanged(int i8) {
        Log.v(TAG, "onTrafficLightRecognize2D5StateChanged  state=" + i8);
    }

    @Deprecated
    public void onTrafficLightRecognizeStateChanged(int i8) {
        Log.v(TAG, "onTrafficLightRecognizeStateChanged");
    }

    public void onTrafficSignRecognize2B4StateChanged(int i8) {
        Log.v(TAG, "onTrafficSignRecognize2B4StateChanged  state=" + i8);
    }

    public void onTrafficSignRecognize2D5StateChanged(int i8) {
        Log.v(TAG, "onTrafficSignRecognize2D5StateChanged  state=" + i8);
    }

    public void onTrafficSignRecognize316StateChanged(int i8) {
        Log.v(TAG, "onTrafficSignRecognize316StateChanged  state=" + i8);
    }

    @Deprecated
    public void onTrafficSignRecognizeStateChanged(int i8) {
        Log.v(TAG, "onTrafficSignRecognizeStateChanged");
    }

    public void onTrafficSignalWarningStateChanged(int i8) {
        Log.v(TAG, "onTrafficSignalWarningStateChanged:" + i8);
    }

    public void onTsrFault(byte b8) {
        Log.v(TAG, "onTsrFault " + ((int) b8));
    }

    public void onUrbanAIAssistanceStateChanged(int i8) {
        Log.v(TAG, "onUrbanAIAssistanceStateChanged");
    }

    public void onVRUFaultStateStateChanged(int i8) {
        Log.v(TAG, "onVRUFaultStateStateChanged:" + i8);
    }

    public void onVerifyModeChanged(int i8) {
        Log.v(TAG, "onVerifyModeChanged");
    }
}

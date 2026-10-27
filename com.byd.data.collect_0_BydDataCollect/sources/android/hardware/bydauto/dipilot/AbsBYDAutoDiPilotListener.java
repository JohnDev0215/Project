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

    public void onACCStateChanged(int i2) {
        Log.v(TAG, "onACCStateChanged state is " + i2);
    }

    public void onADASOnlineStateChanged(int i2) {
        Log.v(TAG, "onADASOnlineStateChanged  state=" + i2);
    }

    public void onADASSoftStateChanged(int i2) {
        Log.v(TAG, "onADASSoftStateChanged: state = " + i2);
    }

    public void onAEBDecActiveChanged(int i2) {
        Log.v(TAG, "onAEBDecActiveChanged " + i2);
    }

    public void onAEBFaultStateStateChanged(int i2) {
        Log.v(TAG, "onAEBFaultStateStateChanged:" + i2);
    }

    public void onAINavigationStateChanged(int i2) {
        Log.v(TAG, "onAINavigationStateChanged");
    }

    public void onAbnormalVehicleWarningStateChanged(int i2) {
        Log.v(TAG, "onAbnormalVehicleWarningStateChanged:" + i2);
    }

    public void onAdaptiveFrontLightFault(byte b2) {
        Log.v(TAG, "onAdaptiveFrontLightFault " + ((int) b2));
    }

    public void onAdativeCruiseControlFault(byte b2) {
        Log.v(TAG, "onAdativeCruiseControlFault " + ((int) b2));
    }

    public void onAiCareStateChanged(int i2) {
        Log.v(TAG, "onAiCareStateChanged");
    }

    public void onAiDistanceLight2B4StateChanged(int i2) {
        Log.v(TAG, "onAiDistanceLight2B4StateChanged  state=" + i2);
    }

    public void onAiDistanceLight316StateChanged(int i2) {
        Log.v(TAG, "onAiDistanceLight316StateChanged  state=" + i2);
    }

    @Deprecated
    public void onAiDistanceLightStateChanged(int i2) {
        Log.v(TAG, "onAiDistanceLightStateChanged");
    }

    public void onAiEmergencyBrakeStateChanged(int i2) {
        Log.v(TAG, "onAiEmergencyBrakeStateChanged");
    }

    public void onAiMatrixLight3ACStateChanged(int i2) {
        Log.v(TAG, "onAiMatrixLight3ACStateChanged  state=" + i2);
    }

    public void onAiMatrixLight3ADStateChanged(int i2) {
        Log.v(TAG, "onAiMatrixLight3ADStateChanged  state=" + i2);
    }

    public void onAiMatrixLightStateChanged(int i2) {
        Log.v(TAG, "onAiMatrixLightStateChanged");
    }

    public void onAiTurnLightStateChanged(int i2) {
        Log.v(TAG, "onAiTurnLightStateChanged");
    }

    public void onAlarmTypeChanged(int i2) {
        Log.v(TAG, "onAlarmTypeChanged");
    }

    public void onAttentionDetectionStateChanged(int i2) {
        Log.v(TAG, "onAttentionDetectionStateChanged");
    }

    public void onAutoEmergencyFault(byte b2) {
        Log.v(TAG, "onAutoEmergencyFault " + ((int) b2));
    }

    public void onAutoParkingStateChanged(int i2) {
        Log.v(TAG, "onAutoParkingStateChanged");
    }

    public void onBSISFault(byte b2) {
        Log.v(TAG, "onBSISFault " + ((int) b2));
    }

    public void onBSISSwitchChange(int i2) {
        Log.v(TAG, "onBSISSwitchChange" + i2);
    }

    public void onBSISSystemChange(int i2) {
        Log.v(TAG, "onBSISSystemChange" + i2);
    }

    public void onBlindFault(byte b2) {
        Log.v(TAG, "onBlindFault " + ((int) b2));
    }

    public void onBlindMonitorStateChanged(int i2) {
        Log.v(TAG, "onBlindMonitorStateChanged");
    }

    public void onBlindMonitorStateChanged1(int i2) {
        Log.v(TAG, "onBlindMonitorStateChanged1  state=" + i2);
    }

    public void onBlindMonitorStateChanged2(int i2) {
        Log.v(TAG, "onBlindMonitorStateChanged2  state=" + i2);
    }

    public void onChargerCoverStateChanged(int i2) {
        Log.v(TAG, "onChargerCoverStateChanged");
    }

    public void onChildCareStateChanged(int i2) {
        Log.v(TAG, "onChildCareStateChanged");
    }

    public void onCollisionWarningStateChanged(int i2) {
        Log.v(TAG, "onCollisionWarningStateChanged:" + i2);
    }

    public void onControlLossWarningStateChanged(int i2) {
        Log.v(TAG, "onControlLossWarningStateChanged:" + i2);
    }

    @Deprecated
    public void onCpdDelayStatusChanged(int i2) {
        Log.v(TAG, "onCpdDelayStatusChanged  status=" + i2);
    }

    public void onCpdStatusChanged(int i2) {
        Log.v(TAG, "onCpdStatusChanged  status=" + i2);
    }

    public void onCpdWarningStateChanged(int i2) {
        Log.v(TAG, "onCpdWarningStateChanged " + i2);
    }

    public void onCpdWorkingStatusChanged(int i2) {
        Log.v(TAG, "onCpdWorkingStatusChanged " + i2);
    }

    public void onCurveWarnFault(byte b2) {
        Log.v(TAG, "onCurveWarnFault " + ((int) b2));
    }

    public void onDangerousActionDetectionStateChanged(int i2) {
        Log.v(TAG, "onDangerousActionDetectionStateChanged");
    }

    public abstract /* synthetic */ void onDataChanged(c cVar);

    public abstract /* synthetic */ void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue);

    public void onDistractionDetectionAidStateChanged(int i2) {
        Log.v(TAG, "onDistractionDetectionAidStateChanged");
    }

    public void onDmsStatusChanged(int i2) {
        Log.v(TAG, "onDmsStatusChanged  status=" + i2);
    }

    public void onDnpStatusChanged(int i2) {
        Log.v(TAG, "onDnpStatusChanged:" + i2);
    }

    public void onDoNotPassWarningStateChanged(int i2) {
        Log.v(TAG, "onDoNotPassWarningStateChanged:" + i2);
    }

    public void onDoorOpenFault(byte b2) {
        Log.v(TAG, "onDoorOpenFault " + ((int) b2));
    }

    public void onDriveDepartureStateChanged(int i2) {
        Log.v(TAG, "onDriveDepartureStateChanged");
    }

    public void onDriveLevelChanged(int i2) {
        Log.v(TAG, "onDriveLevelChanged");
    }

    public void onDriveRemindStateChanged(int i2) {
        Log.v(TAG, "onDriveRemindStateChanged");
    }

    public void onDrivieDepartureStateChanged(int i2) {
        Log.v(TAG, "onDrivieDepartureStateChanged");
    }

    public void onDrivieRemindStateChanged(int i2) {
        Log.v(TAG, "onDrivieRemindStateChanged");
    }

    public void onE2EAIAssistanceStateChanged(int i2) {
        Log.v(TAG, "onE2EAIAssistanceStateChanged");
    }

    public void onELKTakeOverStateChanged(int i2) {
        Log.v(TAG, "onELKTakeOverStateChanged: state = " + i2);
    }

    public void onElecCarBodyStablySysStateChanged(int i2) {
        Log.v(TAG, "onElecCarBodyStablySysStateChanged");
    }

    public void onEmergencyBrakeWarningStateChanged(int i2) {
        Log.v(TAG, "onEmergencyBrakeWarningStateChanged:" + i2);
    }

    public void onEmergencyLaneAssistance2F0StateChanged(int i2) {
        Log.v(TAG, "onEmergencyLaneAssistance2F0StateChanged  state=" + i2);
    }

    public void onEmergencyLaneAssistance32FStateChanged(int i2) {
        Log.v(TAG, "onEmergencyLaneAssistance32FStateChanged  state=" + i2);
    }

    @Deprecated
    public void onEmergencyLaneAssistanceStateChanged(int i2) {
        Log.v(TAG, "onEmergencyLaneAssistanceStateChanged");
    }

    public void onEmergencyLaneKeepFault(byte b2) {
        Log.v(TAG, "onEmergencyLaneKeepFault " + ((int) b2));
    }

    public void onEmergencyVehicleWarningStateChanged(int i2) {
        Log.v(TAG, "onEmergencyVehicleWarningStateChanged:" + i2);
    }

    public abstract /* synthetic */ void onError(int i2, String str);

    public void onEspOffKeyStatusChanged(int i2) {
        Log.v(TAG, "onEspOffKeyStatusChanged " + i2);
    }

    public void onFCSRFault(int i2) {
        Log.v(TAG, "onFCSRFault state is " + i2);
    }

    public void onFCSRStateChanged(int i2) {
        Log.v(TAG, "onFCSRStateChanged state is " + i2);
    }

    public void onFCWFaultStateChanged(int i2) {
        Log.v(TAG, "onFCWFaultStateChanged:" + i2);
    }

    public void onFatigueDetectionAidStateChanged(int i2) {
        Log.v(TAG, "onFatigueDetectionAidStateChanged");
    }

    public void onFatigueMonitorChange(int i2) {
        Log.v(TAG, "onFatigueMonitorChange:" + i2);
    }

    public void onFaultHandlerStateChanged(int i2) {
        Log.v(TAG, "onFaultHandlerStateChanged");
    }

    public void onForwardCollisionWarnFault(byte b2) {
        Log.v(TAG, "onForwardCollisionWarnFault " + ((int) b2));
    }

    public void onFrontCarStartStateChange(int i2) {
        Log.v(TAG, "onFrontCarStartStateChange");
    }

    public void onFrontCrossAlarkFault(byte b2) {
        Log.v(TAG, "onFrontCrossAlarkFault " + ((int) b2));
    }

    public void onFrontCrossBrakeFault(byte b2) {
        Log.v(TAG, "onFrontCrossBrakeFault " + ((int) b2));
    }

    public void onFrontTrafficBrakeAlertStateChanged(int i2) {
        Log.v(TAG, "onFrontTrafficBrakeAlertStateChanged");
    }

    public void onFrontTrafficCrossAlertStateChanged(int i2) {
        Log.v(TAG, "onFrontTrafficCrossAlertStateChanged");
    }

    public void onGazePerceptionStateChanged(int i2) {
        Log.v(TAG, "onGazePerceptionStateChanged");
    }

    public void onGestureRecognitionStateChanged(int i2) {
        Log.v(TAG, "onGestureRecognitionStateChanged");
    }

    public void onGreenLightOptimalSpeedAdvisoryStateChanged(int i2) {
        Log.v(TAG, "onGreenLightOptimalSpeedAdvisoryStateChanged:" + i2);
    }

    public void onHighHODToastChanged(int i2) {
        Log.v(TAG, "onHighHODToastChanged state is " + i2);
    }

    public void onHighIOPToastChanged(int i2) {
        Log.v(TAG, "onHighIOPToastChanged state is " + i2);
    }

    public void onHighNaviFault(byte b2) {
        Log.v(TAG, "onHighNaviFault " + ((int) b2));
    }

    public void onHighSpeedAINavigationStateChanged(int i2) {
        Log.v(TAG, "onHighSpeedAINavigationStateChanged");
    }

    public void onHighSpeedOpenWindowStateChanged(int i2) {
        Log.v(TAG, "onHighSpeedOpenWindowStateChanged");
    }

    public void onIOPStateChanged(int i2) {
        Log.v(TAG, "onIOPStateChanged state is " + i2);
    }

    public void onISLIWarningLevelSwitchStatusChanged(int i2) {
        Log.v(TAG, "onISLIWarningLevelSwitchStatusChanged " + i2);
    }

    public void onISLIWarningStateChanged(int i2) {
        Log.v(TAG, "onISLIWarningStateChanged: state = " + i2);
    }

    public void onIntelligentLimitFaultCall(byte b2) {
        Log.v(TAG, "onIntelligentLimitFaultCall " + ((int) b2));
    }

    public void onIntelligentLimitFaultController(byte b2) {
        Log.v(TAG, "onIntelligentLimitFaultController " + ((int) b2));
    }

    public void onIntelligentSpeedLimitAlert2B4StateChanged(int i2) {
        Log.v(TAG, "onIntelligentSpeedLimitAlert2B4StateChanged  state=" + i2);
    }

    public void onIntelligentSpeedLimitAlert2D5StateChanged(int i2) {
        Log.v(TAG, "onIntelligentSpeedLimitAlert2D5StateChanged  state=" + i2);
    }

    @Deprecated
    public void onIntelligentSpeedLimitAlertStateChanged(int i2) {
        Log.v(TAG, "onIntelligentSpeedLimitAlertStateChanged");
    }

    public void onIntelligentSpeedLimitControl2D5StateChanged(int i2) {
        Log.v(TAG, "onIntelligentSpeedLimitControl2D5StateChanged  state=" + i2);
    }

    public void onIntelligentSpeedLimitControl32FStateChanged(int i2) {
        Log.v(TAG, "onIntelligentSpeedLimitControl32FStateChanged  state=" + i2);
    }

    @Deprecated
    public void onIntelligentSpeedLimitControlStateChanged(int i2) {
        Log.v(TAG, "onIntelligentSpeedLimitControlStateChanged");
    }

    public void onInteractiveLaneChangeAssistanceStateChanged(int i2) {
        Log.v(TAG, "onInteractiveLaneChangeAssistanceStateChanged");
    }

    public void onInteractiveLaneChangeFault(byte b2) {
        Log.v(TAG, "onInteractiveLaneChangeFault " + ((int) b2));
    }

    public void onIntersectionCollisionAlertStateChanged(int i2) {
        Log.v(TAG, "onIntersectionCollisionAlertStateChanged:" + i2);
    }

    public void onLDWFaultStateChanged(int i2) {
        Log.v(TAG, "onLDWFaultStateChanged:" + i2);
    }

    public void onLDWSensitivityChange(int i2) {
        Log.v(TAG, "onLDWSensitivityChange" + i2);
    }

    public void onLKSFaultStateStateChanged(int i2) {
        Log.v(TAG, "onLKSFaultStateStateChanged:" + i2);
    }

    public void onLaneAssistTypeChanged(int i2) {
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

    public void onLeftBehindReminderStateChanged(int i2) {
        Log.v(TAG, "onLeftBehindReminderStateChanged");
    }

    public void onLimitSpeedChangeFault(byte b2) {
        Log.v(TAG, "onLimitSpeedChangeFault " + ((int) b2));
    }

    public void onLowHODToastChanged(int i2) {
        Log.v(TAG, "onLowHODToastChanged state is " + i2);
    }

    public void onLowIOPToastChanged(int i2) {
        Log.v(TAG, "onLowIOPToastChanged state is " + i2);
    }

    public void onLowSpeedEmergencyBrakingStateChanged(int i2) {
        Log.v(TAG, "onLowSpeedEmergencyBrakingStateChanged:" + i2);
    }

    public void onMOISFault(byte b2) {
        Log.v(TAG, "onMOISFault " + ((int) b2));
    }

    public void onMOISSwitchChange(int i2) {
        Log.v(TAG, "onMOISSwitchChange" + i2);
    }

    public void onMOISSystemChange(int i2) {
        Log.v(TAG, "onMOISSystemChange" + i2);
    }

    public void onMechanismPositionChanged(int i2) {
        Log.v(TAG, "onMechanismPositionChanged:" + i2);
    }

    public void onMistakeGasStateChanged(int i2) {
        Log.v(TAG, "onMistakeGasStateChanged");
    }

    public void onNavigationPilotFault(byte b2) {
        Log.v(TAG, "onNavigationPilotFault " + ((int) b2));
    }

    public void onNrbanNaviFault(byte b2) {
        Log.v(TAG, "onNrbanNaviFault " + ((int) b2));
    }

    public void onOpenDoorAlertStateChanged(int i2) {
        Log.v(TAG, "onOpenDoorAlertStateChanged");
    }

    public void onPredictCrashAlert138ModeChanged(int i2) {
        Log.v(TAG, "onPredictCrashAlert138ModeChanged  mode=" + i2);
    }

    public void onPredictCrashAlert32DModeChanged(int i2) {
        Log.v(TAG, "onPredictCrashAlert32DModeChanged  mode=" + i2);
    }

    @Deprecated
    public void onPredictCrashAlertModeChanged(int i2) {
        Log.v(TAG, "onPredictCrashAlertModeChanged");
    }

    public void onPredictCrashAlertStateChange(int i2) {
        Log.v(TAG, "onPredictCrashAlertStateChange");
    }

    public void onRainOpenFogLightStateChanged(int i2) {
        Log.v(TAG, "onRainOpenFogLightStateChanged");
    }

    public void onRearCollisionWarnFault(byte b2) {
        Log.v(TAG, "onRearCollisionWarnFault " + ((int) b2));
    }

    public void onRearCrashAlertStateChanged(int i2) {
        Log.v(TAG, "onRearCrashAlertStateChanged");
    }

    public void onRearCrossAlartFault(byte b2) {
        Log.v(TAG, "onRearCrossAlartFault " + ((int) b2));
    }

    public void onRearCrossBrakeFault(byte b2) {
        Log.v(TAG, "onRearCrossBrakeFault " + ((int) b2));
    }

    public void onRearTrafficBrakeAlertStateChanged(int i2) {
        Log.v(TAG, "onRearTrafficBrakeAlertStateChanged");
    }

    public void onRearTrafficCrossAlertStateChanged(int i2) {
        Log.v(TAG, "onRearTrafficCrossAlertStateChanged");
    }

    public void onRecommendRestSpotStateChanged(int i2) {
        Log.v(TAG, "onRecommendRestSpotStateChanged");
    }

    public void onRedLightViolationWarningStateChanged(int i2) {
        Log.v(TAG, "onRedLightViolationWarningStateChanged:" + i2);
    }

    public void onRelieveFatigueStateChanged(int i2) {
        Log.v(TAG, "onRelieveFatigueStateChanged");
    }

    public void onRemoteControlDriveCloseTops(int i2) {
        Log.v(TAG, "onRemoteControlDriveCloseTops");
    }

    public void onRequestHUNPClose(int i2) {
        Log.v(TAG, "onRequestHUNPClose");
    }

    public void onSAOCStateChanged(int i2) {
        Log.v(TAG, "onSAOCStateChanged state is " + i2);
    }

    public void onSLIFStateChanged(int i2) {
        Log.v(TAG, "onSLIFStateChanged state is " + i2);
    }

    public void onSideDistanceWarningStateChanged(int i2) {
        Log.v(TAG, "onSideDistanceWarningStateChanged:" + i2);
    }

    public void onSpeedLimitRange2B4Changed(int i2) {
        Log.v(TAG, "onSpeedLimitRange2B4Changed  value=" + i2);
    }

    public void onSpeedLimitRange2D5Changed(int i2) {
        Log.v(TAG, "onSpeedLimitRange2D5Changed  value=" + i2);
    }

    @Deprecated
    public void onSpeedLimitRangeChanged(int i2) {
        Log.v(TAG, "onSpeedLimitRangeChanged");
    }

    public void onSteepDescentSlowDownStateChanged(int i2) {
        Log.v(TAG, "onSteepDescentSlowDownStateChanged");
    }

    public void onTSRLitSpdChgInformStateChanged(int i2) {
        Log.v(TAG, "onTSRLitSpdChgInformStateChanged: state = " + i2);
    }

    public void onTSRLitSpdChgSwitchStateChanged(int i2) {
        Log.v(TAG, "onTSRLitSpdChgSwitchStateChanged: state = " + i2);
    }

    public void onTemperatureWhileFatigueChanged(int i2) {
        Log.v(TAG, "onTemperatureWhileFatigueChanged");
    }

    public void onTrafficAttentionFault(byte b2) {
        Log.v(TAG, "onTrafficAttentionFault " + ((int) b2));
    }

    public void onTrafficIncidentWarningStateChanged(int i2) {
        Log.v(TAG, "onTrafficIncidentWarningStateChanged:" + i2);
    }

    public void onTrafficJamWarningStateChanged(int i2) {
        Log.v(TAG, "onTrafficJamWarningStateChanged:" + i2);
    }

    public void onTrafficLightInfoPushStateChanged(int i2) {
        Log.v(TAG, "onTrafficLightInfoPushStateChanged:" + i2);
    }

    public void onTrafficLightRecognize2B4StateChanged(int i2) {
        Log.v(TAG, "onTrafficLightRecognize2B4StateChanged  state=" + i2);
    }

    public void onTrafficLightRecognize2D5StateChanged(int i2) {
        Log.v(TAG, "onTrafficLightRecognize2D5StateChanged  state=" + i2);
    }

    @Deprecated
    public void onTrafficLightRecognizeStateChanged(int i2) {
        Log.v(TAG, "onTrafficLightRecognizeStateChanged");
    }

    public void onTrafficSignRecognize2B4StateChanged(int i2) {
        Log.v(TAG, "onTrafficSignRecognize2B4StateChanged  state=" + i2);
    }

    public void onTrafficSignRecognize2D5StateChanged(int i2) {
        Log.v(TAG, "onTrafficSignRecognize2D5StateChanged  state=" + i2);
    }

    public void onTrafficSignRecognize316StateChanged(int i2) {
        Log.v(TAG, "onTrafficSignRecognize316StateChanged  state=" + i2);
    }

    @Deprecated
    public void onTrafficSignRecognizeStateChanged(int i2) {
        Log.v(TAG, "onTrafficSignRecognizeStateChanged");
    }

    public void onTrafficSignalWarningStateChanged(int i2) {
        Log.v(TAG, "onTrafficSignalWarningStateChanged:" + i2);
    }

    public void onTsrFault(byte b2) {
        Log.v(TAG, "onTsrFault " + ((int) b2));
    }

    public void onUrbanAIAssistanceStateChanged(int i2) {
        Log.v(TAG, "onUrbanAIAssistanceStateChanged");
    }

    public void onVRUFaultStateStateChanged(int i2) {
        Log.v(TAG, "onVRUFaultStateStateChanged:" + i2);
    }

    public void onVerifyModeChanged(int i2) {
        Log.v(TAG, "onVerifyModeChanged");
    }
}

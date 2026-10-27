package android.hardware.bydauto.dipilot;

import a.c;
import a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoDiPilotListener implements d {
    protected static final String TAG = "AbsBYDAutoDiPilotListener";

    @Override // a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
        super.isConnected(z6);
    }

    public void onADASOnlineStateChanged(int i7) {
        Log.v(TAG, "onADASOnlineStateChanged  state=" + i7);
    }

    public void onADASSoftStateChanged(int i7) {
        Log.v(TAG, "onADASSoftStateChanged: state = " + i7);
    }

    public void onAEBFaultStateStateChanged(int i7) {
        Log.v(TAG, "onAEBFaultStateStateChanged:" + i7);
    }

    public void onAINavigationStateChanged(int i7) {
        Log.v(TAG, "onAINavigationStateChanged");
    }

    public void onAbnormalVehicleWarningStateChanged(int i7) {
        Log.v(TAG, "onAbnormalVehicleWarningStateChanged:" + i7);
    }

    public void onAdaptiveFrontLightFault(byte b7) {
        Log.v(TAG, "onAdaptiveFrontLightFault " + ((int) b7));
    }

    public void onAdativeCruiseControlFault(byte b7) {
        Log.v(TAG, "onAdativeCruiseControlFault " + ((int) b7));
    }

    public void onAiCareStateChanged(int i7) {
        Log.v(TAG, "onAiCareStateChanged");
    }

    public void onAiDistanceLight2B4StateChanged(int i7) {
        Log.v(TAG, "onAiDistanceLight2B4StateChanged  state=" + i7);
    }

    public void onAiDistanceLight316StateChanged(int i7) {
        Log.v(TAG, "onAiDistanceLight316StateChanged  state=" + i7);
    }

    @Deprecated
    public void onAiDistanceLightStateChanged(int i7) {
        Log.v(TAG, "onAiDistanceLightStateChanged");
    }

    public void onAiEmergencyBrakeStateChanged(int i7) {
        Log.v(TAG, "onAiEmergencyBrakeStateChanged");
    }

    public void onAiMatrixLight3ACStateChanged(int i7) {
        Log.v(TAG, "onAiMatrixLight3ACStateChanged  state=" + i7);
    }

    public void onAiMatrixLight3ADStateChanged(int i7) {
        Log.v(TAG, "onAiMatrixLight3ADStateChanged  state=" + i7);
    }

    public void onAiMatrixLightStateChanged(int i7) {
        Log.v(TAG, "onAiMatrixLightStateChanged");
    }

    public void onAiTurnLightStateChanged(int i7) {
        Log.v(TAG, "onAiTurnLightStateChanged");
    }

    public void onAlarmTypeChanged(int i7) {
        Log.v(TAG, "onAlarmTypeChanged");
    }

    public void onAttentionDetectionStateChanged(int i7) {
        Log.v(TAG, "onAttentionDetectionStateChanged");
    }

    public void onAutoEmergencyFault(byte b7) {
        Log.v(TAG, "onAutoEmergencyFault " + ((int) b7));
    }

    public void onAutoParkingStateChanged(int i7) {
        Log.v(TAG, "onAutoParkingStateChanged");
    }

    public void onBSISFault(byte b7) {
        Log.v(TAG, "onBSISFault " + ((int) b7));
    }

    public void onBSISSwitchChange(int i7) {
        Log.v(TAG, "onBSISSwitchChange" + i7);
    }

    public void onBSISSystemChange(int i7) {
        Log.v(TAG, "onBSISSystemChange" + i7);
    }

    public void onBlindFault(byte b7) {
        Log.v(TAG, "onBlindFault " + ((int) b7));
    }

    public void onBlindMonitorStateChanged(int i7) {
        Log.v(TAG, "onBlindMonitorStateChanged");
    }

    public void onBlindMonitorStateChanged1(int i7) {
        Log.v(TAG, "onBlindMonitorStateChanged1  state=" + i7);
    }

    public void onBlindMonitorStateChanged2(int i7) {
        Log.v(TAG, "onBlindMonitorStateChanged2  state=" + i7);
    }

    public void onChargerCoverStateChanged(int i7) {
        Log.v(TAG, "onChargerCoverStateChanged");
    }

    public void onChildCareStateChanged(int i7) {
        Log.v(TAG, "onChildCareStateChanged");
    }

    public void onCollisionWarningStateChanged(int i7) {
        Log.v(TAG, "onCollisionWarningStateChanged:" + i7);
    }

    public void onControlLossWarningStateChanged(int i7) {
        Log.v(TAG, "onControlLossWarningStateChanged:" + i7);
    }

    @Deprecated
    public void onCpdDelayStatusChanged(int i7) {
        Log.v(TAG, "onCpdDelayStatusChanged  status=" + i7);
    }

    public void onCpdStatusChanged(int i7) {
        Log.v(TAG, "onCpdStatusChanged  status=" + i7);
    }

    public void onCurveWarnFault(byte b7) {
        Log.v(TAG, "onCurveWarnFault " + ((int) b7));
    }

    public void onDangerousActionDetectionStateChanged(int i7) {
        Log.v(TAG, "onDangerousActionDetectionStateChanged");
    }

    public abstract /* synthetic */ void onDataChanged(c cVar);

    public abstract /* synthetic */ void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue);

    public void onDistractionDetectionAidStateChanged(int i7) {
        Log.v(TAG, "onDistractionDetectionAidStateChanged");
    }

    public void onDmsStatusChanged(int i7) {
        Log.v(TAG, "onDmsStatusChanged  status=" + i7);
    }

    public void onDnpStatusChanged(int i7) {
        Log.v(TAG, "onDnpStatusChanged:" + i7);
    }

    public void onDoNotPassWarningStateChanged(int i7) {
        Log.v(TAG, "onDoNotPassWarningStateChanged:" + i7);
    }

    public void onDoorOpenFault(byte b7) {
        Log.v(TAG, "onDoorOpenFault " + ((int) b7));
    }

    public void onDriveDepartureStateChanged(int i7) {
        Log.v(TAG, "onDriveDepartureStateChanged");
    }

    public void onDriveLevelChanged(int i7) {
        Log.v(TAG, "onDriveLevelChanged");
    }

    public void onDriveRemindStateChanged(int i7) {
        Log.v(TAG, "onDriveRemindStateChanged");
    }

    public void onDrivieDepartureStateChanged(int i7) {
        Log.v(TAG, "onDrivieDepartureStateChanged");
    }

    public void onDrivieRemindStateChanged(int i7) {
        Log.v(TAG, "onDrivieRemindStateChanged");
    }

    public void onE2EAIAssistanceStateChanged(int i7) {
        Log.v(TAG, "onE2EAIAssistanceStateChanged");
    }

    public void onELKTakeOverStateChanged(int i7) {
        Log.v(TAG, "onELKTakeOverStateChanged: state = " + i7);
    }

    public void onElecCarBodyStablySysStateChanged(int i7) {
        Log.v(TAG, "onElecCarBodyStablySysStateChanged");
    }

    public void onEmergencyBrakeWarningStateChanged(int i7) {
        Log.v(TAG, "onEmergencyBrakeWarningStateChanged:" + i7);
    }

    public void onEmergencyLaneAssistance2F0StateChanged(int i7) {
        Log.v(TAG, "onEmergencyLaneAssistance2F0StateChanged  state=" + i7);
    }

    public void onEmergencyLaneAssistance32FStateChanged(int i7) {
        Log.v(TAG, "onEmergencyLaneAssistance32FStateChanged  state=" + i7);
    }

    @Deprecated
    public void onEmergencyLaneAssistanceStateChanged(int i7) {
        Log.v(TAG, "onEmergencyLaneAssistanceStateChanged");
    }

    public void onEmergencyLaneKeepFault(byte b7) {
        Log.v(TAG, "onEmergencyLaneKeepFault " + ((int) b7));
    }

    public void onEmergencyVehicleWarningStateChanged(int i7) {
        Log.v(TAG, "onEmergencyVehicleWarningStateChanged:" + i7);
    }

    public abstract /* synthetic */ void onError(int i7, String str);

    public void onFCWFaultStateChanged(int i7) {
        Log.v(TAG, "onFCWFaultStateChanged:" + i7);
    }

    public void onFatigueDetectionAidStateChanged(int i7) {
        Log.v(TAG, "onFatigueDetectionAidStateChanged");
    }

    public void onFatigueMonitorChange(int i7) {
        Log.v(TAG, "onFatigueMonitorChange:" + i7);
    }

    public void onFaultHandlerStateChanged(int i7) {
        Log.v(TAG, "onFaultHandlerStateChanged");
    }

    public void onForwardCollisionWarnFault(byte b7) {
        Log.v(TAG, "onForwardCollisionWarnFault " + ((int) b7));
    }

    public void onFrontCarStartStateChange(int i7) {
        Log.v(TAG, "onFrontCarStartStateChange");
    }

    public void onFrontCrossAlarkFault(byte b7) {
        Log.v(TAG, "onFrontCrossAlarkFault " + ((int) b7));
    }

    public void onFrontCrossBrakeFault(byte b7) {
        Log.v(TAG, "onFrontCrossBrakeFault " + ((int) b7));
    }

    public void onFrontTrafficBrakeAlertStateChanged(int i7) {
        Log.v(TAG, "onFrontTrafficBrakeAlertStateChanged");
    }

    public void onFrontTrafficCrossAlertStateChanged(int i7) {
        Log.v(TAG, "onFrontTrafficCrossAlertStateChanged");
    }

    public void onGazePerceptionStateChanged(int i7) {
        Log.v(TAG, "onGazePerceptionStateChanged");
    }

    public void onGestureRecognitionStateChanged(int i7) {
        Log.v(TAG, "onGestureRecognitionStateChanged");
    }

    public void onGreenLightOptimalSpeedAdvisoryStateChanged(int i7) {
        Log.v(TAG, "onGreenLightOptimalSpeedAdvisoryStateChanged:" + i7);
    }

    public void onHighNaviFault(byte b7) {
        Log.v(TAG, "onHighNaviFault " + ((int) b7));
    }

    public void onHighSpeedAINavigationStateChanged(int i7) {
        Log.v(TAG, "onHighSpeedAINavigationStateChanged");
    }

    public void onHighSpeedOpenWindowStateChanged(int i7) {
        Log.v(TAG, "onHighSpeedOpenWindowStateChanged");
    }

    public void onISLIWarningStateChanged(int i7) {
        Log.v(TAG, "onISLIWarningStateChanged: state = " + i7);
    }

    public void onIntelligentLimitFaultCall(byte b7) {
        Log.v(TAG, "onIntelligentLimitFaultCall " + ((int) b7));
    }

    public void onIntelligentLimitFaultController(byte b7) {
        Log.v(TAG, "onIntelligentLimitFaultController " + ((int) b7));
    }

    public void onIntelligentSpeedLimitAlert2B4StateChanged(int i7) {
        Log.v(TAG, "onIntelligentSpeedLimitAlert2B4StateChanged  state=" + i7);
    }

    public void onIntelligentSpeedLimitAlert2D5StateChanged(int i7) {
        Log.v(TAG, "onIntelligentSpeedLimitAlert2D5StateChanged  state=" + i7);
    }

    @Deprecated
    public void onIntelligentSpeedLimitAlertStateChanged(int i7) {
        Log.v(TAG, "onIntelligentSpeedLimitAlertStateChanged");
    }

    public void onIntelligentSpeedLimitControl2D5StateChanged(int i7) {
        Log.v(TAG, "onIntelligentSpeedLimitControl2D5StateChanged  state=" + i7);
    }

    public void onIntelligentSpeedLimitControl32FStateChanged(int i7) {
        Log.v(TAG, "onIntelligentSpeedLimitControl32FStateChanged  state=" + i7);
    }

    @Deprecated
    public void onIntelligentSpeedLimitControlStateChanged(int i7) {
        Log.v(TAG, "onIntelligentSpeedLimitControlStateChanged");
    }

    public void onInteractiveLaneChangeAssistanceStateChanged(int i7) {
        Log.v(TAG, "onInteractiveLaneChangeAssistanceStateChanged");
    }

    public void onInteractiveLaneChangeFault(byte b7) {
        Log.v(TAG, "onInteractiveLaneChangeFault " + ((int) b7));
    }

    public void onIntersectionCollisionAlertStateChanged(int i7) {
        Log.v(TAG, "onIntersectionCollisionAlertStateChanged:" + i7);
    }

    public void onLDWFaultStateChanged(int i7) {
        Log.v(TAG, "onLDWFaultStateChanged:" + i7);
    }

    public void onLDWSensitivityChange(int i7) {
        Log.v(TAG, "onLDWSensitivityChange" + i7);
    }

    public void onLKSFaultStateStateChanged(int i7) {
        Log.v(TAG, "onLKSFaultStateStateChanged:" + i7);
    }

    public void onLaneAssistTypeChanged(int i7) {
        Log.v(TAG, "onLaneAssistTypeChanged");
    }

    public void onLaneCenteringControlFault(byte b7) {
        Log.v(TAG, "onLaneCenteringControlFault " + ((int) b7));
    }

    public void onLaneDeparturePreventionFault(byte b7) {
        Log.v(TAG, "onLaneDeparturePreventionFault " + ((int) b7));
    }

    public void onLaneDepartureWarnFault(byte b7) {
        Log.v(TAG, "onLaneDepartureWarnFault " + ((int) b7));
    }

    public void onLaneKeepFault(byte b7) {
        Log.v(TAG, "onLaneKeepFault " + ((int) b7));
    }

    public void onLeftBehindReminderStateChanged(int i7) {
        Log.v(TAG, "onLeftBehindReminderStateChanged");
    }

    public void onLimitSpeedChangeFault(byte b7) {
        Log.v(TAG, "onLimitSpeedChangeFault " + ((int) b7));
    }

    public void onLowSpeedEmergencyBrakingStateChanged(int i7) {
        Log.v(TAG, "onLowSpeedEmergencyBrakingStateChanged:" + i7);
    }

    public void onMOISFault(byte b7) {
        Log.v(TAG, "onMOISFault " + ((int) b7));
    }

    public void onMOISSwitchChange(int i7) {
        Log.v(TAG, "onMOISSwitchChange" + i7);
    }

    public void onMOISSystemChange(int i7) {
        Log.v(TAG, "onMOISSystemChange" + i7);
    }

    public void onMechanismPositionChanged(int i7) {
        Log.v(TAG, "onMechanismPositionChanged:" + i7);
    }

    public void onMistakeGasStateChanged(int i7) {
        Log.v(TAG, "onMistakeGasStateChanged");
    }

    public void onNavigationPilotFault(byte b7) {
        Log.v(TAG, "onNavigationPilotFault " + ((int) b7));
    }

    public void onNrbanNaviFault(byte b7) {
        Log.v(TAG, "onNrbanNaviFault " + ((int) b7));
    }

    public void onOpenDoorAlertStateChanged(int i7) {
        Log.v(TAG, "onOpenDoorAlertStateChanged");
    }

    public void onPredictCrashAlert138ModeChanged(int i7) {
        Log.v(TAG, "onPredictCrashAlert138ModeChanged  mode=" + i7);
    }

    public void onPredictCrashAlert32DModeChanged(int i7) {
        Log.v(TAG, "onPredictCrashAlert32DModeChanged  mode=" + i7);
    }

    @Deprecated
    public void onPredictCrashAlertModeChanged(int i7) {
        Log.v(TAG, "onPredictCrashAlertModeChanged");
    }

    public void onPredictCrashAlertStateChange(int i7) {
        Log.v(TAG, "onPredictCrashAlertStateChange");
    }

    public void onRainOpenFogLightStateChanged(int i7) {
        Log.v(TAG, "onRainOpenFogLightStateChanged");
    }

    public void onRearCollisionWarnFault(byte b7) {
        Log.v(TAG, "onRearCollisionWarnFault " + ((int) b7));
    }

    public void onRearCrashAlertStateChanged(int i7) {
        Log.v(TAG, "onRearCrashAlertStateChanged");
    }

    public void onRearCrossAlartFault(byte b7) {
        Log.v(TAG, "onRearCrossAlartFault " + ((int) b7));
    }

    public void onRearCrossBrakeFault(byte b7) {
        Log.v(TAG, "onRearCrossBrakeFault " + ((int) b7));
    }

    public void onRearTrafficBrakeAlertStateChanged(int i7) {
        Log.v(TAG, "onRearTrafficBrakeAlertStateChanged");
    }

    public void onRearTrafficCrossAlertStateChanged(int i7) {
        Log.v(TAG, "onRearTrafficCrossAlertStateChanged");
    }

    public void onRecommendRestSpotStateChanged(int i7) {
        Log.v(TAG, "onRecommendRestSpotStateChanged");
    }

    public void onRedLightViolationWarningStateChanged(int i7) {
        Log.v(TAG, "onRedLightViolationWarningStateChanged:" + i7);
    }

    public void onRelieveFatigueStateChanged(int i7) {
        Log.v(TAG, "onRelieveFatigueStateChanged");
    }

    public void onRemoteControlDriveCloseTops(int i7) {
        Log.v(TAG, "onRemoteControlDriveCloseTops");
    }

    public void onRequestHUNPClose(int i7) {
        Log.v(TAG, "onRequestHUNPClose");
    }

    public void onSideDistanceWarningStateChanged(int i7) {
        Log.v(TAG, "onSideDistanceWarningStateChanged:" + i7);
    }

    public void onSpeedLimitRange2B4Changed(int i7) {
        Log.v(TAG, "onSpeedLimitRange2B4Changed  value=" + i7);
    }

    public void onSpeedLimitRange2D5Changed(int i7) {
        Log.v(TAG, "onSpeedLimitRange2D5Changed  value=" + i7);
    }

    @Deprecated
    public void onSpeedLimitRangeChanged(int i7) {
        Log.v(TAG, "onSpeedLimitRangeChanged");
    }

    public void onSteepDescentSlowDownStateChanged(int i7) {
        Log.v(TAG, "onSteepDescentSlowDownStateChanged");
    }

    public void onTSRLitSpdChgInformStateChanged(int i7) {
        Log.v(TAG, "onTSRLitSpdChgInformStateChanged: state = " + i7);
    }

    public void onTSRLitSpdChgSwitchStateChanged(int i7) {
        Log.v(TAG, "onTSRLitSpdChgSwitchStateChanged: state = " + i7);
    }

    public void onTemperatureWhileFatigueChanged(int i7) {
        Log.v(TAG, "onTemperatureWhileFatigueChanged");
    }

    public void onTrafficAttentionFault(byte b7) {
        Log.v(TAG, "onTrafficAttentionFault " + ((int) b7));
    }

    public void onTrafficIncidentWarningStateChanged(int i7) {
        Log.v(TAG, "onTrafficIncidentWarningStateChanged:" + i7);
    }

    public void onTrafficJamWarningStateChanged(int i7) {
        Log.v(TAG, "onTrafficJamWarningStateChanged:" + i7);
    }

    public void onTrafficLightInfoPushStateChanged(int i7) {
        Log.v(TAG, "onTrafficLightInfoPushStateChanged:" + i7);
    }

    public void onTrafficLightRecognize2B4StateChanged(int i7) {
        Log.v(TAG, "onTrafficLightRecognize2B4StateChanged  state=" + i7);
    }

    public void onTrafficLightRecognize2D5StateChanged(int i7) {
        Log.v(TAG, "onTrafficLightRecognize2D5StateChanged  state=" + i7);
    }

    @Deprecated
    public void onTrafficLightRecognizeStateChanged(int i7) {
        Log.v(TAG, "onTrafficLightRecognizeStateChanged");
    }

    public void onTrafficSignRecognize2B4StateChanged(int i7) {
        Log.v(TAG, "onTrafficSignRecognize2B4StateChanged  state=" + i7);
    }

    public void onTrafficSignRecognize2D5StateChanged(int i7) {
        Log.v(TAG, "onTrafficSignRecognize2D5StateChanged  state=" + i7);
    }

    public void onTrafficSignRecognize316StateChanged(int i7) {
        Log.v(TAG, "onTrafficSignRecognize316StateChanged  state=" + i7);
    }

    @Deprecated
    public void onTrafficSignRecognizeStateChanged(int i7) {
        Log.v(TAG, "onTrafficSignRecognizeStateChanged");
    }

    public void onTrafficSignalWarningStateChanged(int i7) {
        Log.v(TAG, "onTrafficSignalWarningStateChanged:" + i7);
    }

    public void onTsrFault(byte b7) {
        Log.v(TAG, "onTsrFault " + ((int) b7));
    }

    public void onUrbanAIAssistanceStateChanged(int i7) {
        Log.v(TAG, "onUrbanAIAssistanceStateChanged");
    }

    public void onVRUFaultStateStateChanged(int i7) {
        Log.v(TAG, "onVRUFaultStateStateChanged:" + i7);
    }

    public void onVerifyModeChanged(int i7) {
        Log.v(TAG, "onVerifyModeChanged");
    }
}

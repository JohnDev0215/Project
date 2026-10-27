package android.hardware.bydauto.dipilot;

import M5.b;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoDiPilotListener implements d {
    protected static final String TAG = "AbsBYDAutoDiPilotListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
    }

    public void onADASOnlineStateChanged(int i7) {
        b.o("onADASOnlineStateChanged  state=", i7, TAG);
    }

    public void onADASSoftStateChanged(int i7) {
        b.o("onADASSoftStateChanged: state = ", i7, TAG);
    }

    public void onAEBFaultStateStateChanged(int i7) {
        b.o("onAEBFaultStateStateChanged:", i7, TAG);
    }

    public void onAINavigationStateChanged(int i7) {
        Log.v(TAG, "onAINavigationStateChanged");
    }

    public void onAbnormalVehicleWarningStateChanged(int i7) {
        b.o("onAbnormalVehicleWarningStateChanged:", i7, TAG);
    }

    public void onAdaptiveFrontLightFault(byte b10) {
        b.o("onAdaptiveFrontLightFault ", b10, TAG);
    }

    public void onAdativeCruiseControlFault(byte b10) {
        b.o("onAdativeCruiseControlFault ", b10, TAG);
    }

    public void onAiCareStateChanged(int i7) {
        Log.v(TAG, "onAiCareStateChanged");
    }

    public void onAiDistanceLight2B4StateChanged(int i7) {
        b.o("onAiDistanceLight2B4StateChanged  state=", i7, TAG);
    }

    public void onAiDistanceLight316StateChanged(int i7) {
        b.o("onAiDistanceLight316StateChanged  state=", i7, TAG);
    }

    @Deprecated
    public void onAiDistanceLightStateChanged(int i7) {
        Log.v(TAG, "onAiDistanceLightStateChanged");
    }

    public void onAiEmergencyBrakeStateChanged(int i7) {
        Log.v(TAG, "onAiEmergencyBrakeStateChanged");
    }

    public void onAiMatrixLight3ACStateChanged(int i7) {
        b.o("onAiMatrixLight3ACStateChanged  state=", i7, TAG);
    }

    public void onAiMatrixLight3ADStateChanged(int i7) {
        b.o("onAiMatrixLight3ADStateChanged  state=", i7, TAG);
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

    public void onAutoEmergencyFault(byte b10) {
        b.o("onAutoEmergencyFault ", b10, TAG);
    }

    public void onAutoParkingStateChanged(int i7) {
        Log.v(TAG, "onAutoParkingStateChanged");
    }

    public void onBSISFault(byte b10) {
        b.o("onBSISFault ", b10, TAG);
    }

    public void onBSISSwitchChange(int i7) {
        b.o("onBSISSwitchChange", i7, TAG);
    }

    public void onBSISSystemChange(int i7) {
        b.o("onBSISSystemChange", i7, TAG);
    }

    public void onBlindFault(byte b10) {
        b.o("onBlindFault ", b10, TAG);
    }

    public void onBlindMonitorStateChanged(int i7) {
        Log.v(TAG, "onBlindMonitorStateChanged");
    }

    public void onBlindMonitorStateChanged1(int i7) {
        b.o("onBlindMonitorStateChanged1  state=", i7, TAG);
    }

    public void onBlindMonitorStateChanged2(int i7) {
        b.o("onBlindMonitorStateChanged2  state=", i7, TAG);
    }

    public void onChargerCoverStateChanged(int i7) {
        Log.v(TAG, "onChargerCoverStateChanged");
    }

    public void onChildCareStateChanged(int i7) {
        Log.v(TAG, "onChildCareStateChanged");
    }

    public void onCollisionWarningStateChanged(int i7) {
        b.o("onCollisionWarningStateChanged:", i7, TAG);
    }

    public void onControlLossWarningStateChanged(int i7) {
        b.o("onControlLossWarningStateChanged:", i7, TAG);
    }

    @Deprecated
    public void onCpdDelayStatusChanged(int i7) {
        b.o("onCpdDelayStatusChanged  status=", i7, TAG);
    }

    public void onCpdStatusChanged(int i7) {
        b.o("onCpdStatusChanged  status=", i7, TAG);
    }

    public void onCurveWarnFault(byte b10) {
        b.o("onCurveWarnFault ", b10, TAG);
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
        b.o("onDmsStatusChanged  status=", i7, TAG);
    }

    public void onDnpStatusChanged(int i7) {
        b.o("onDnpStatusChanged:", i7, TAG);
    }

    public void onDoNotPassWarningStateChanged(int i7) {
        b.o("onDoNotPassWarningStateChanged:", i7, TAG);
    }

    public void onDoorOpenFault(byte b10) {
        b.o("onDoorOpenFault ", b10, TAG);
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
        b.o("onELKTakeOverStateChanged: state = ", i7, TAG);
    }

    public void onElecCarBodyStablySysStateChanged(int i7) {
        Log.v(TAG, "onElecCarBodyStablySysStateChanged");
    }

    public void onEmergencyBrakeWarningStateChanged(int i7) {
        b.o("onEmergencyBrakeWarningStateChanged:", i7, TAG);
    }

    public void onEmergencyLaneAssistance2F0StateChanged(int i7) {
        b.o("onEmergencyLaneAssistance2F0StateChanged  state=", i7, TAG);
    }

    public void onEmergencyLaneAssistance32FStateChanged(int i7) {
        b.o("onEmergencyLaneAssistance32FStateChanged  state=", i7, TAG);
    }

    @Deprecated
    public void onEmergencyLaneAssistanceStateChanged(int i7) {
        Log.v(TAG, "onEmergencyLaneAssistanceStateChanged");
    }

    public void onEmergencyLaneKeepFault(byte b10) {
        b.o("onEmergencyLaneKeepFault ", b10, TAG);
    }

    public void onEmergencyVehicleWarningStateChanged(int i7) {
        b.o("onEmergencyVehicleWarningStateChanged:", i7, TAG);
    }

    public abstract /* synthetic */ void onError(int i7, String str);

    public void onFCWFaultStateChanged(int i7) {
        b.o("onFCWFaultStateChanged:", i7, TAG);
    }

    public void onFatigueDetectionAidStateChanged(int i7) {
        Log.v(TAG, "onFatigueDetectionAidStateChanged");
    }

    public void onFatigueMonitorChange(int i7) {
        b.o("onFatigueMonitorChange:", i7, TAG);
    }

    public void onFaultHandlerStateChanged(int i7) {
        Log.v(TAG, "onFaultHandlerStateChanged");
    }

    public void onForwardCollisionWarnFault(byte b10) {
        b.o("onForwardCollisionWarnFault ", b10, TAG);
    }

    public void onFrontCarStartStateChange(int i7) {
        Log.v(TAG, "onFrontCarStartStateChange");
    }

    public void onFrontCrossAlarkFault(byte b10) {
        b.o("onFrontCrossAlarkFault ", b10, TAG);
    }

    public void onFrontCrossBrakeFault(byte b10) {
        b.o("onFrontCrossBrakeFault ", b10, TAG);
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
        b.o("onGreenLightOptimalSpeedAdvisoryStateChanged:", i7, TAG);
    }

    public void onHighNaviFault(byte b10) {
        b.o("onHighNaviFault ", b10, TAG);
    }

    public void onHighSpeedAINavigationStateChanged(int i7) {
        Log.v(TAG, "onHighSpeedAINavigationStateChanged");
    }

    public void onHighSpeedOpenWindowStateChanged(int i7) {
        Log.v(TAG, "onHighSpeedOpenWindowStateChanged");
    }

    public void onISLIWarningLevelSwitchStatusChanged(int i7) {
        b.o("onISLIWarningLevelSwitchStatusChanged ", i7, TAG);
    }

    public void onISLIWarningStateChanged(int i7) {
        b.o("onISLIWarningStateChanged: state = ", i7, TAG);
    }

    public void onIntelligentLimitFaultCall(byte b10) {
        b.o("onIntelligentLimitFaultCall ", b10, TAG);
    }

    public void onIntelligentLimitFaultController(byte b10) {
        b.o("onIntelligentLimitFaultController ", b10, TAG);
    }

    public void onIntelligentSpeedLimitAlert2B4StateChanged(int i7) {
        b.o("onIntelligentSpeedLimitAlert2B4StateChanged  state=", i7, TAG);
    }

    public void onIntelligentSpeedLimitAlert2D5StateChanged(int i7) {
        b.o("onIntelligentSpeedLimitAlert2D5StateChanged  state=", i7, TAG);
    }

    @Deprecated
    public void onIntelligentSpeedLimitAlertStateChanged(int i7) {
        Log.v(TAG, "onIntelligentSpeedLimitAlertStateChanged");
    }

    public void onIntelligentSpeedLimitControl2D5StateChanged(int i7) {
        b.o("onIntelligentSpeedLimitControl2D5StateChanged  state=", i7, TAG);
    }

    public void onIntelligentSpeedLimitControl32FStateChanged(int i7) {
        b.o("onIntelligentSpeedLimitControl32FStateChanged  state=", i7, TAG);
    }

    @Deprecated
    public void onIntelligentSpeedLimitControlStateChanged(int i7) {
        Log.v(TAG, "onIntelligentSpeedLimitControlStateChanged");
    }

    public void onInteractiveLaneChangeAssistanceStateChanged(int i7) {
        Log.v(TAG, "onInteractiveLaneChangeAssistanceStateChanged");
    }

    public void onInteractiveLaneChangeFault(byte b10) {
        b.o("onInteractiveLaneChangeFault ", b10, TAG);
    }

    public void onIntersectionCollisionAlertStateChanged(int i7) {
        b.o("onIntersectionCollisionAlertStateChanged:", i7, TAG);
    }

    public void onLDWFaultStateChanged(int i7) {
        b.o("onLDWFaultStateChanged:", i7, TAG);
    }

    public void onLDWSensitivityChange(int i7) {
        b.o("onLDWSensitivityChange", i7, TAG);
    }

    public void onLKSFaultStateStateChanged(int i7) {
        b.o("onLKSFaultStateStateChanged:", i7, TAG);
    }

    public void onLaneAssistTypeChanged(int i7) {
        Log.v(TAG, "onLaneAssistTypeChanged");
    }

    public void onLaneCenteringControlFault(byte b10) {
        b.o("onLaneCenteringControlFault ", b10, TAG);
    }

    public void onLaneDeparturePreventionFault(byte b10) {
        b.o("onLaneDeparturePreventionFault ", b10, TAG);
    }

    public void onLaneDepartureWarnFault(byte b10) {
        b.o("onLaneDepartureWarnFault ", b10, TAG);
    }

    public void onLaneKeepFault(byte b10) {
        b.o("onLaneKeepFault ", b10, TAG);
    }

    public void onLeftBehindReminderStateChanged(int i7) {
        Log.v(TAG, "onLeftBehindReminderStateChanged");
    }

    public void onLimitSpeedChangeFault(byte b10) {
        b.o("onLimitSpeedChangeFault ", b10, TAG);
    }

    public void onLowSpeedEmergencyBrakingStateChanged(int i7) {
        b.o("onLowSpeedEmergencyBrakingStateChanged:", i7, TAG);
    }

    public void onMOISFault(byte b10) {
        b.o("onMOISFault ", b10, TAG);
    }

    public void onMOISSwitchChange(int i7) {
        b.o("onMOISSwitchChange", i7, TAG);
    }

    public void onMOISSystemChange(int i7) {
        b.o("onMOISSystemChange", i7, TAG);
    }

    public void onMechanismPositionChanged(int i7) {
        b.o("onMechanismPositionChanged:", i7, TAG);
    }

    public void onMistakeGasStateChanged(int i7) {
        Log.v(TAG, "onMistakeGasStateChanged");
    }

    public void onNavigationPilotFault(byte b10) {
        b.o("onNavigationPilotFault ", b10, TAG);
    }

    public void onNrbanNaviFault(byte b10) {
        b.o("onNrbanNaviFault ", b10, TAG);
    }

    public void onOpenDoorAlertStateChanged(int i7) {
        Log.v(TAG, "onOpenDoorAlertStateChanged");
    }

    public void onPredictCrashAlert138ModeChanged(int i7) {
        b.o("onPredictCrashAlert138ModeChanged  mode=", i7, TAG);
    }

    public void onPredictCrashAlert32DModeChanged(int i7) {
        b.o("onPredictCrashAlert32DModeChanged  mode=", i7, TAG);
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

    public void onRearCollisionWarnFault(byte b10) {
        b.o("onRearCollisionWarnFault ", b10, TAG);
    }

    public void onRearCrashAlertStateChanged(int i7) {
        Log.v(TAG, "onRearCrashAlertStateChanged");
    }

    public void onRearCrossAlartFault(byte b10) {
        b.o("onRearCrossAlartFault ", b10, TAG);
    }

    public void onRearCrossBrakeFault(byte b10) {
        b.o("onRearCrossBrakeFault ", b10, TAG);
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
        b.o("onRedLightViolationWarningStateChanged:", i7, TAG);
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
        b.o("onSideDistanceWarningStateChanged:", i7, TAG);
    }

    public void onSpeedLimitRange2B4Changed(int i7) {
        b.o("onSpeedLimitRange2B4Changed  value=", i7, TAG);
    }

    public void onSpeedLimitRange2D5Changed(int i7) {
        b.o("onSpeedLimitRange2D5Changed  value=", i7, TAG);
    }

    @Deprecated
    public void onSpeedLimitRangeChanged(int i7) {
        Log.v(TAG, "onSpeedLimitRangeChanged");
    }

    public void onSteepDescentSlowDownStateChanged(int i7) {
        Log.v(TAG, "onSteepDescentSlowDownStateChanged");
    }

    public void onTSRLitSpdChgInformStateChanged(int i7) {
        b.o("onTSRLitSpdChgInformStateChanged: state = ", i7, TAG);
    }

    public void onTSRLitSpdChgSwitchStateChanged(int i7) {
        b.o("onTSRLitSpdChgSwitchStateChanged: state = ", i7, TAG);
    }

    public void onTemperatureWhileFatigueChanged(int i7) {
        Log.v(TAG, "onTemperatureWhileFatigueChanged");
    }

    public void onTrafficAttentionFault(byte b10) {
        b.o("onTrafficAttentionFault ", b10, TAG);
    }

    public void onTrafficIncidentWarningStateChanged(int i7) {
        b.o("onTrafficIncidentWarningStateChanged:", i7, TAG);
    }

    public void onTrafficJamWarningStateChanged(int i7) {
        b.o("onTrafficJamWarningStateChanged:", i7, TAG);
    }

    public void onTrafficLightInfoPushStateChanged(int i7) {
        b.o("onTrafficLightInfoPushStateChanged:", i7, TAG);
    }

    public void onTrafficLightRecognize2B4StateChanged(int i7) {
        b.o("onTrafficLightRecognize2B4StateChanged  state=", i7, TAG);
    }

    public void onTrafficLightRecognize2D5StateChanged(int i7) {
        b.o("onTrafficLightRecognize2D5StateChanged  state=", i7, TAG);
    }

    @Deprecated
    public void onTrafficLightRecognizeStateChanged(int i7) {
        Log.v(TAG, "onTrafficLightRecognizeStateChanged");
    }

    public void onTrafficSignRecognize2B4StateChanged(int i7) {
        b.o("onTrafficSignRecognize2B4StateChanged  state=", i7, TAG);
    }

    public void onTrafficSignRecognize2D5StateChanged(int i7) {
        b.o("onTrafficSignRecognize2D5StateChanged  state=", i7, TAG);
    }

    public void onTrafficSignRecognize316StateChanged(int i7) {
        b.o("onTrafficSignRecognize316StateChanged  state=", i7, TAG);
    }

    @Deprecated
    public void onTrafficSignRecognizeStateChanged(int i7) {
        Log.v(TAG, "onTrafficSignRecognizeStateChanged");
    }

    public void onTrafficSignalWarningStateChanged(int i7) {
        b.o("onTrafficSignalWarningStateChanged:", i7, TAG);
    }

    public void onTsrFault(byte b10) {
        b.o("onTsrFault ", b10, TAG);
    }

    public void onUrbanAIAssistanceStateChanged(int i7) {
        Log.v(TAG, "onUrbanAIAssistanceStateChanged");
    }

    public void onVRUFaultStateStateChanged(int i7) {
        b.o("onVRUFaultStateStateChanged:", i7, TAG);
    }

    public void onVerifyModeChanged(int i7) {
        Log.v(TAG, "onVerifyModeChanged");
    }
}

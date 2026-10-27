package android.hardware.bydauto.dipilot;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.dipilot.tsimpl.DiPilotManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoDiPilotDevice extends AbsBYDAutoDevice {
    public static final int ADAS_ONLINE_STATE_OFF = 0;
    public static final int ADAS_ONLINE_STATE_ON = 1;
    public static final int ALARM_TYPE_SHAKE = 1;
    public static final int ALARM_TYPE_VOICE = 2;
    public static final int ALARM_TYPE_VOICE_SHAKE = 3;
    public static final int DIPILOT_ALL = 4;
    public static final int DIPILOT_CLOSE = 1;
    public static final int DIPILOT_COMMAND_BUSY = -2147482647;
    public static final int DIPILOT_COMMAND_FAILED = -2147482648;
    public static final int DIPILOT_COMMAND_INVALID_VALUE = -2147482645;
    public static final int DIPILOT_COMMAND_SUCCESS = 0;
    public static final int DIPILOT_COMMAND_TIMEOUT = -2147482646;
    public static final int DIPILOT_LANE_DEPARTURE_STOP = 5;
    public static final int DIPILOT_LANE_DEPARTURE_WARNING = 2;
    public static final int DIPILOT_LANE_KEEP = 3;
    public static final int DOMESTIC_PLATFORM_VERSION = 0;
    public static final int DRIVE_LEVEL1 = 1;
    public static final int DRIVE_LEVEL2 = 2;
    public static final int DRIVE_LEVEL3 = 3;
    public static final int DRIVE_LEVEL4 = 4;
    public static final int DRIVE_LEVEL5 = 5;
    public static final int ELK_TAKE_OVER_STATE_INACTIVE = 0;
    public static final int ELK_TAKE_OVER_WARNING_LEVEL1 = 1;
    public static final int ELK_TAKE_OVER_WARNING_LEVEL2 = 2;
    public static final int ELK_TAKE_OVER_WARNING_LEVEL3 = 3;
    public static final int EU_VERSION = 1;
    public static final int GET_TSR_LIT_SPD_SWITCH_STATE_INVALID = 0;
    public static final int GET_TSR_LIT_SPD_SWITCH_STATE_OFF = 1;
    public static final int GET_TSR_LIT_SPD_SWITCH_STATE_ON = 2;
    public static final int GET_TSR_LIT_SPD_SWITCH_STATE_RESERVED = 3;
    public static final int ISLI_WARNING_STATE_LEVEL1 = 1;
    public static final int ISLI_WARNING_STATE_LEVEL2 = 2;
    public static final int ISLI_WARNING_STATE_NO_WARNING = 0;
    public static final int ISLI_WARNING_STATE_RESERVED = 3;
    public static final int NORTH_AMERICAN_VERSION = 2;
    public static final String PERMISSION_BYDAUTO_ADAS_COMMON = "android.permission.BYDAUTO_ADAS_COMMON";
    public static final String PERMISSION_BYDAUTO_ADAS_GET = "android.permission.BYDAUTO_ADAS_GET";
    public static final String PERMISSION_BYDAUTO_ADAS_SET = "android.permission.BYDAUTO_ADAS_SET";
    public static final int PREDICT_CRASH_ALERT_CLOSE = 4;
    public static final int PREDICT_CRASH_ALERT_EARLY = 3;
    public static final int PREDICT_CRASH_ALERT_FIT = 2;
    public static final int PREDICT_CRASH_ALERT_LATTER = 0;
    public static final int SET_AUTO_CONFIRM_MODE = 2;
    public static final int SET_CONFIRM_MODE_INVALID = 0;
    public static final int SET_INVALID = 0;
    public static final int SET_MANUAL_CONFIRM_MODE = 1;
    public static final int SET_OFF = 1;
    public static final int SET_ON = 2;
    public static final int SPEED_LIMIT_RANGE_0 = 0;
    public static final int SPEED_LIMIT_RANGE_10_KPH = 4;
    public static final int SPEED_LIMIT_RANGE_3_MPH = 7;
    public static final int SPEED_LIMIT_RANGE_5_KPH = 3;
    public static final int SPEED_LIMIT_RANGE_5_MPH = 8;
    public static final int SPEED_LIMIT_RANGE_INVALID = 9;
    public static final int SPEED_LIMIT_RANGE_SUB10_KPH = 1;
    public static final int SPEED_LIMIT_RANGE_SUB3_MPH = 6;
    public static final int SPEED_LIMIT_RANGE_SUB5_KPH = 2;
    public static final int SPEED_LIMIT_RANGE_SUB5_MPH = 5;
    public static final String TAG = "BYDAutoDiPilotDevice";
    public static final int TSR_LIT_SPD_INFORM_CHANGE = 1;
    public static final int TSR_LIT_SPD_INFORM_NO_CHANGE = 0;
    private static BYDAutoDiPilotDevice sBYDAutoDiPilotDevice;
    private DiPilotManagerImpl mDiPilotManagerImpl;

    public BYDAutoDiPilotDevice(Context context) {
        super(context.getApplicationContext());
        this.mDiPilotManagerImpl = DiPilotManagerImpl.getsInstance(context.getApplicationContext());
    }

    public static synchronized BYDAutoDiPilotDevice getInstance(Context context) {
        if (sBYDAutoDiPilotDevice == null) {
            sBYDAutoDiPilotDevice = new BYDAutoDiPilotDevice(context.getApplicationContext());
        }
        return sBYDAutoDiPilotDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.mDiPilotManagerImpl.get(iArr, cls);
    }

    public int getADASOnlineState() {
        return this.mDiPilotManagerImpl.getADASOnlineState();
    }

    public int getADASSoftVersion() {
        return this.mDiPilotManagerImpl.getAdasSoftVersion();
    }

    public int getAEBFaultState() {
        return this.mDiPilotManagerImpl.getAebFaultState();
    }

    public int getAINavigationState() {
        return this.mDiPilotManagerImpl.getAINavigationState();
    }

    public int getAbnormalVehicleWarningState() {
        return this.mDiPilotManagerImpl.getAbnormalVehicleWarningState();
    }

    public byte getAdaptiveFrontLightFault() {
        return this.mDiPilotManagerImpl.getAdaptiveFrontLightFault();
    }

    public byte getAdativeCruiseControlFault() {
        return this.mDiPilotManagerImpl.getAdativeCruiseControlFault();
    }

    public int getAebDecActive() {
        return this.mDiPilotManagerImpl.getAebDecActive();
    }

    public int getAiCareState() {
        return this.mDiPilotManagerImpl.getAiCareState();
    }

    public int getAiDistanceLight2B4State() {
        return this.mDiPilotManagerImpl.getAiDistanceLight2B4State();
    }

    public int getAiDistanceLight316State() {
        return this.mDiPilotManagerImpl.getAiDistanceLight316State();
    }

    @Deprecated
    public int getAiDistanceLightState() {
        return this.mDiPilotManagerImpl.getAiDistanceLightState();
    }

    public int getAiEmergencyBrakeState() {
        return this.mDiPilotManagerImpl.getAiEmergencyBrakeState();
    }

    public int getAiMatrixLight3ACState() {
        return this.mDiPilotManagerImpl.getAiMatrixLight3ACState();
    }

    public int getAiMatrixLight3ADState() {
        return this.mDiPilotManagerImpl.getAiMatrixLight3ADState();
    }

    public int getAiMatrixLightState() {
        return this.mDiPilotManagerImpl.getAiMatrixLightState();
    }

    public int getAiTurnLightState() {
        return this.mDiPilotManagerImpl.getAiTurnLightState();
    }

    public int getAlarmType() {
        return this.mDiPilotManagerImpl.getAlarmType();
    }

    public int getAttentionDetectionState() {
        return this.mDiPilotManagerImpl.getAttentionDetectionState();
    }

    public byte getAutoEmergencyFault() {
        return this.mDiPilotManagerImpl.getAutoEmergencyFault();
    }

    public int getAutoParkigAssistMode() {
        return this.mDiPilotManagerImpl.getAutoParkigAssistMode();
    }

    public int getAutoParkigAssistStatus() {
        return this.mDiPilotManagerImpl.getAutoParkigAssistStatus();
    }

    public int getAutoParkingState() {
        return this.mDiPilotManagerImpl.getAutoParkingState();
    }

    public int getAutomatedValetParkingStatus() {
        return this.mDiPilotManagerImpl.getAutomatedValetParkingStatus();
    }

    public int getBSISEnable() {
        return this.mDiPilotManagerImpl.getBsisEnableR();
    }

    public byte getBSISFault() {
        return this.mDiPilotManagerImpl.getBsisFault();
    }

    public int getBSISFuncStatus() {
        return this.mDiPilotManagerImpl.getBsisFuncStatusR();
    }

    public byte getBlindFault() {
        return this.mDiPilotManagerImpl.getBlindFault();
    }

    public int getBlindMonitorState() {
        return this.mDiPilotManagerImpl.getBlindMonitorState();
    }

    public int getBlindMonitorState1() {
        return this.mDiPilotManagerImpl.getBlindMonitorState1();
    }

    public int getBlindMonitorState2() {
        return this.mDiPilotManagerImpl.getBlindMonitorState2();
    }

    public int getChargerCoverState() {
        return this.mDiPilotManagerImpl.getChargerCoverState();
    }

    public int getChildCareState() {
        return this.mDiPilotManagerImpl.getChildCareState();
    }

    public int getCollisionWarningState() {
        return this.mDiPilotManagerImpl.getCollisionWarningState();
    }

    public int getControlLossWarningState() {
        return this.mDiPilotManagerImpl.getControlLossWarningState();
    }

    @Deprecated
    public int getCpdDelayStatus() {
        return this.mDiPilotManagerImpl.getCpdDelayStatus();
    }

    public int getCpdStatus() {
        return this.mDiPilotManagerImpl.getCpdStatus();
    }

    public int getCpdWarningState() {
        return this.mDiPilotManagerImpl.getCpdWarningState();
    }

    public int getCpdWorkingStatus() {
        return this.mDiPilotManagerImpl.getCpdWorkingStatus();
    }

    public byte getCurveWarnFault() {
        return this.mDiPilotManagerImpl.getCurveWarnFault();
    }

    public int getDNPState() {
        return this.mDiPilotManagerImpl.getDNPState();
    }

    public int getDangerousActionDetectionState() {
        return this.mDiPilotManagerImpl.getDangerousActionDetectionState();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return this.mDiPilotManagerImpl.getDevicetype();
    }

    public int getDistractionDetectionAidState() {
        return this.mDiPilotManagerImpl.getDistractionDetectionAidState();
    }

    public int getDmsSwitchStatus() {
        return this.mDiPilotManagerImpl.getDmsSwitchStatus();
    }

    public int getDnpStatus() {
        return this.mDiPilotManagerImpl.getDnpStatus();
    }

    public int getDoNotPassWarningState() {
        return this.mDiPilotManagerImpl.getDoNotPassWarningState();
    }

    public byte getDoorOpenFault() {
        return this.mDiPilotManagerImpl.getDoorOpenFault();
    }

    public int getDriveDepartureState() {
        return this.mDiPilotManagerImpl.getDriveDepartureState();
    }

    public int getDriveLevel() {
        return this.mDiPilotManagerImpl.getDriveLevel();
    }

    public int getDriveRemindState() {
        return this.mDiPilotManagerImpl.getDriveRemindState();
    }

    public int getDrivieDepartureState() {
        return this.mDiPilotManagerImpl.getDrivieDepartureState();
    }

    public int getDrivieRemindState() {
        return this.mDiPilotManagerImpl.getDrivieRemindState();
    }

    public int getE2EAIAssistanceState() {
        return this.mDiPilotManagerImpl.getE2EAIAssistanceState();
    }

    public int getELKTakeOverState() {
        return this.mDiPilotManagerImpl.getElkTakeOverState();
    }

    public int getElecCarBodyStablySysState() {
        return this.mDiPilotManagerImpl.getElecCarBodyStablySysState();
    }

    public int getEmergencyBrakeWarningState() {
        return this.mDiPilotManagerImpl.getEmergencyBrakeWarningState();
    }

    public int getEmergencyLaneAssistance2F0State() {
        return this.mDiPilotManagerImpl.getEmergencyLaneAssistance2F0State();
    }

    public int getEmergencyLaneAssistance32FState() {
        return this.mDiPilotManagerImpl.getEmergencyLaneAssistance32FState();
    }

    @Deprecated
    public int getEmergencyLaneAssistanceState() {
        return this.mDiPilotManagerImpl.getEmergencyLaneAssistanceState();
    }

    public byte getEmergencyLaneKeepFault() {
        return this.mDiPilotManagerImpl.getEmergencyLaneKeepFault();
    }

    public int getEmergencyVehicleWarningState() {
        return this.mDiPilotManagerImpl.getEmergencyVehicleWarningState();
    }

    public int getEspOffKeyStatus() {
        return this.mDiPilotManagerImpl.getEspOffKeyStatus();
    }

    public int getFCWFaultState() {
        return this.mDiPilotManagerImpl.getFcwFaultState();
    }

    public int getFatigueDetectionAidState() {
        return this.mDiPilotManagerImpl.getFatigueDetectionAidState();
    }

    public int getFaultHandlerState() {
        return this.mDiPilotManagerImpl.getFaultHandlerState();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        return this.mDiPilotManagerImpl.getFeatureList();
    }

    public byte getForwardCollisionWarnFault() {
        return this.mDiPilotManagerImpl.getForwardCollisionWarnFault();
    }

    public int getFrontCarStartState() {
        return this.mDiPilotManagerImpl.getFrontCarStartState();
    }

    public byte getFrontCrossAlarkFault() {
        return this.mDiPilotManagerImpl.getFrontCrossAlarkFault();
    }

    public byte getFrontCrossBrakeFault() {
        return this.mDiPilotManagerImpl.getFrontCrossBrakeFault();
    }

    public int getFrontTrafficBrakeAlertState() {
        return this.mDiPilotManagerImpl.getFrontTrafficBrakeAlertState();
    }

    public int getFrontTrafficCrossAlertState() {
        return this.mDiPilotManagerImpl.getFrontTrafficCrossAlertState();
    }

    public int getGazePerceptionState() {
        return this.mDiPilotManagerImpl.getGazePerceptionState();
    }

    public int getGestureRecognitionState() {
        return this.mDiPilotManagerImpl.getGestureRecognitionState();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_ADAS_GET;
    }

    public int getGreenLightOptimalSpeedAdvisoryState() {
        return this.mDiPilotManagerImpl.getGreenLightOptimalSpeedAdvisoryState();
    }

    public byte getHighNaviFault() {
        return this.mDiPilotManagerImpl.getHighNaviFault();
    }

    public int getHighSpeedAINavigationState() {
        return this.mDiPilotManagerImpl.getHighSpeedAINavigationState();
    }

    public int getHighSpeedOpenWindowState() {
        return this.mDiPilotManagerImpl.getHighSpeedOpenWindowState();
    }

    public int getHomeZoneParkingAssistStatus() {
        return this.mDiPilotManagerImpl.getHomeZoneParkingAssistStatus();
    }

    public int getISLIWarningLevelSwitchStatus() {
        return this.mDiPilotManagerImpl.getIsliWarningLevelSwitchStatus();
    }

    public int getISLIWarningState() {
        return this.mDiPilotManagerImpl.getIsliWarningState();
    }

    public byte getIntelligentLimitFaultCall() {
        return this.mDiPilotManagerImpl.getIntelligentLimitFaultCall();
    }

    public byte getIntelligentLimitFaultController() {
        return this.mDiPilotManagerImpl.getIntelligentLimitFaultController();
    }

    public int getIntelligentSpeedLimitAlert2B4State() {
        return this.mDiPilotManagerImpl.getIntelligentSpeedLimitAlert2B4State();
    }

    public int getIntelligentSpeedLimitAlert2D5State() {
        return this.mDiPilotManagerImpl.getIntelligentSpeedLimitAlert2D5State();
    }

    @Deprecated
    public int getIntelligentSpeedLimitAlertState() {
        return this.mDiPilotManagerImpl.getIntelligentSpeedLimitAlertState();
    }

    public int getIntelligentSpeedLimitControl2D5State() {
        return this.mDiPilotManagerImpl.getIntelligentSpeedLimitControl2D5State();
    }

    public int getIntelligentSpeedLimitControl32FState() {
        return this.mDiPilotManagerImpl.getIntelligentSpeedLimitControl32FState();
    }

    @Deprecated
    public int getIntelligentSpeedLimitControlState() {
        return this.mDiPilotManagerImpl.getIntelligentSpeedLimitControlState();
    }

    public int getInteractiveLaneChangeAssistanceState() {
        return this.mDiPilotManagerImpl.getInteractiveLaneChangeAssistanceState();
    }

    public byte getInteractiveLaneChangeFault() {
        return this.mDiPilotManagerImpl.getInteractiveLaneChangeFault();
    }

    public int getIntersectionCollisionAlertState() {
        return this.mDiPilotManagerImpl.getIntersectionCollisionAlertState();
    }

    public int getIopState() {
        return this.mDiPilotManagerImpl.getIopState();
    }

    public int getLDWFaultState() {
        return this.mDiPilotManagerImpl.getLdwFaultState();
    }

    public int getLDWSensitivity() {
        return this.mDiPilotManagerImpl.getLdwSensitivity();
    }

    public int getLKSFaultState() {
        return this.mDiPilotManagerImpl.getLksFaultState();
    }

    public int getLaneAssistType() {
        return this.mDiPilotManagerImpl.getLaneAssistType();
    }

    public byte getLaneCenteringControlFault() {
        return this.mDiPilotManagerImpl.getLaneCenteringControlFault();
    }

    public byte getLaneDeparturePreventionFault() {
        return this.mDiPilotManagerImpl.getLaneDeparturePreventionFault();
    }

    public byte getLaneDepartureWarnFault() {
        return this.mDiPilotManagerImpl.getLaneDepartureWarnFault();
    }

    public byte getLaneKeepFault() {
        return this.mDiPilotManagerImpl.getLaneKeepFault();
    }

    public int getLeftBehindReminderState() {
        return this.mDiPilotManagerImpl.getLeftBehindReminderState();
    }

    public byte getLimitSpeedChangeFault() {
        return this.mDiPilotManagerImpl.getLimitSpeedChangeFault();
    }

    public int getLowSpeedEmergencyBrakingState() {
        return this.mDiPilotManagerImpl.getLowSpeedEmergencyBrakingState();
    }

    public int getMOISEnable() {
        return this.mDiPilotManagerImpl.getMoisEnableR();
    }

    public byte getMOISFault() {
        return this.mDiPilotManagerImpl.getMoisFault();
    }

    public int getMOISFuncStatus() {
        return this.mDiPilotManagerImpl.getMoisFuncStateR();
    }

    public int getMechanismPosition() {
        return this.mDiPilotManagerImpl.getMechanismPosition();
    }

    public int getMistakeGasState() {
        return this.mDiPilotManagerImpl.getMistakeGasState();
    }

    public byte getNavigationPilotFault() {
        return this.mDiPilotManagerImpl.getNavigationPilotFault();
    }

    public byte getNrbanNaviFault() {
        return this.mDiPilotManagerImpl.getNrbanNaviFault();
    }

    public int getOpenDoorAlertState() {
        return this.mDiPilotManagerImpl.getOpenDoorAlertState();
    }

    public int getPredictCrashAlert138Mode() {
        return this.mDiPilotManagerImpl.getPredictCrashAlert138Mode();
    }

    public int getPredictCrashAlert32DMode() {
        return this.mDiPilotManagerImpl.getPredictCrashAlert32DMode();
    }

    public int getPredictCrashAlertState() {
        return this.mDiPilotManagerImpl.getPredictCrashAlertState();
    }

    public int getRainOpenFogLightState() {
        return this.mDiPilotManagerImpl.getRainOpenFogLightState();
    }

    public byte getRearCollisionWarnFault() {
        return this.mDiPilotManagerImpl.getRearCollisionWarnFault();
    }

    public int getRearCrashAlertState() {
        return this.mDiPilotManagerImpl.getRearCrashAlertState();
    }

    public byte getRearCrossAlartFault() {
        return this.mDiPilotManagerImpl.getRearCrossAlartFault();
    }

    public byte getRearCrossBrakeFault() {
        return this.mDiPilotManagerImpl.getRearCrossBrakeFault();
    }

    public int getRearTrafficBrakeAlertState() {
        return this.mDiPilotManagerImpl.getRearTrafficBrakeAlertState();
    }

    public int getRearTrafficCrossAlertState() {
        return this.mDiPilotManagerImpl.getRearTrafficCrossAlertState();
    }

    public int getRecommendRestSpotState() {
        return this.mDiPilotManagerImpl.getRecommendRestSpotState();
    }

    public int getRedLightViolationWarningState() {
        return this.mDiPilotManagerImpl.getRedLightViolationWarningState();
    }

    public int getRelieveFatigueState() {
        return this.mDiPilotManagerImpl.getRelieveFatigueState();
    }

    public int getSaocState() {
        return this.mDiPilotManagerImpl.getSaocState();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_ADAS_SET;
    }

    public int getSideDistanceWarningState() {
        return this.mDiPilotManagerImpl.getSideDistanceWarningState();
    }

    public int getSlifState() {
        return this.mDiPilotManagerImpl.getSlifState();
    }

    @Deprecated
    public int getSpeedLimitRange() {
        return this.mDiPilotManagerImpl.getSpeedLimitRange();
    }

    public int getSpeedLimitRange2B4() {
        return this.mDiPilotManagerImpl.getSpeedLimitRange2B4();
    }

    public int getSpeedLimitRange2D5() {
        return this.mDiPilotManagerImpl.getSpeedLimitRange2D5();
    }

    public int getSteepDescentSlowDownState() {
        return this.mDiPilotManagerImpl.getSteepDescentSlowDownState();
    }

    public int getSteepDescentSlowDownState4A9() {
        return this.mDiPilotManagerImpl.getSteepDescentSlowDownState4A9R();
    }

    public int getSupportAiCare() {
        return this.mDiPilotManagerImpl.getSupportAiCare();
    }

    public int getSupportAiDistanceLight() {
        return this.mDiPilotManagerImpl.getSupportAiDistanceLight();
    }

    public int getSupportAiEmergencyBrake() {
        return this.mDiPilotManagerImpl.getSupportAiEmergencyBrake();
    }

    public int getSupportAiMatrixLight() {
        return this.mDiPilotManagerImpl.getSupportAiMatrixLight();
    }

    public int getSupportAutoParking() {
        return this.mDiPilotManagerImpl.getSupportAutoParking();
    }

    public int getSupportBlindSpotAssist() {
        return this.mDiPilotManagerImpl.getSupportBlindSpotAssist();
    }

    public int getSupportESP() {
        return this.mDiPilotManagerImpl.getSupportESP();
    }

    public int getSupportIntelligentNavigation() {
        return this.mDiPilotManagerImpl.getSupportIntelligentNavigation();
    }

    public int getSupportLaneAssistMode() {
        return this.mDiPilotManagerImpl.getSupportLaneAssistMode();
    }

    public int getSupportMistakeGas() {
        return this.mDiPilotManagerImpl.getSupportMistakeGas();
    }

    public int getSupportPanoramaImage() {
        return this.mDiPilotManagerImpl.getSupportPanoramaImage();
    }

    public int getSupportPredictCrashAlert() {
        return this.mDiPilotManagerImpl.getSupportPredictCrashAlert();
    }

    public int getSupportRearCrossBrake() {
        return this.mDiPilotManagerImpl.getSupportRearCrossBrake();
    }

    public int getSupportShakeAlert() {
        return this.mDiPilotManagerImpl.getSupportShakeAlert();
    }

    public int getSupportTrafficSignsRecognition() {
        return this.mDiPilotManagerImpl.getSupportTrafficSignsRecognition();
    }

    public int getTSRLitSpdChgInformState() {
        return this.mDiPilotManagerImpl.getTsrLitSpdChgInformState();
    }

    public int getTSRLitSpdChgSwitchState() {
        return this.mDiPilotManagerImpl.getTsrLitSpdChgSwitchState();
    }

    public int getTemperatureWhileFatigue() {
        return this.mDiPilotManagerImpl.getTemperatureWhileFatigue();
    }

    public byte getTrafficAttentionFault() {
        return this.mDiPilotManagerImpl.getTrafficAttentionFault();
    }

    public int getTrafficIncidentWarningState() {
        return this.mDiPilotManagerImpl.getTrafficIncidentWarningState();
    }

    public int getTrafficJamWarningState() {
        return this.mDiPilotManagerImpl.getTrafficJamWarningState();
    }

    public int getTrafficLightInfoPushState() {
        return this.mDiPilotManagerImpl.getTrafficLightInfoPushState();
    }

    public int getTrafficLightRecognize2B4State() {
        return this.mDiPilotManagerImpl.getTrafficLightRecognize2B4State();
    }

    public int getTrafficLightRecognize2D5State() {
        return this.mDiPilotManagerImpl.getTrafficLightRecognize2D5State();
    }

    @Deprecated
    public int getTrafficLightRecognizeState() {
        return this.mDiPilotManagerImpl.getTrafficLightRecognizeState();
    }

    public int getTrafficSignRecognize2B4State() {
        return this.mDiPilotManagerImpl.getTrafficSignRecognize2B4State();
    }

    public int getTrafficSignRecognize2D5State() {
        return this.mDiPilotManagerImpl.getTrafficSignRecognize2D5State();
    }

    public int getTrafficSignRecognize316State() {
        return this.mDiPilotManagerImpl.getTrafficSignRecognize316State();
    }

    @Deprecated
    public int getTrafficSignRecognizeState() {
        return this.mDiPilotManagerImpl.getTrafficSignRecognizeState();
    }

    public int getTrafficSignalWarningState() {
        return this.mDiPilotManagerImpl.getTrafficSignalWarningState();
    }

    public byte getTsrFault() {
        return this.mDiPilotManagerImpl.getTsrFault();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return this.mDiPilotManagerImpl.getType();
    }

    public int getUrbanAIAssistanceState() {
        return this.mDiPilotManagerImpl.getUrbanAIAssistanceState();
    }

    public int getVRUFaultState() {
        return this.mDiPilotManagerImpl.getVruFaultState();
    }

    public int getVerifyMode() {
        return this.mDiPilotManagerImpl.getVerifyMode();
    }

    public void registerListener(AbsBYDAutoDiPilotListener absBYDAutoDiPilotListener) {
        this.mDiPilotManagerImpl.registerListener(absBYDAutoDiPilotListener);
    }

    public int requestHUNPClose() {
        return this.mDiPilotManagerImpl.requestHUNPClose();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        return this.mDiPilotManagerImpl.set(iArr, bYDAutoEventValue);
    }

    public int setAINavigationState(int i3) {
        return this.mDiPilotManagerImpl.setAINavigationState(i3);
    }

    public int setAbnormalVehicleWarningState(int i3) {
        return this.mDiPilotManagerImpl.setAbnormalVehicleWarningState(i3);
    }

    public int setAiCareState(int i3) {
        return this.mDiPilotManagerImpl.setAiCareState(i3);
    }

    public int setAiDistanceLightState(int i3) {
        return this.mDiPilotManagerImpl.setAiDistanceLightState(i3);
    }

    public int setAiEmergencyBrakeState(int i3) {
        return this.mDiPilotManagerImpl.setAiEmergencyBrakeState(i3);
    }

    public int setAiMatrixLightState(int i3) {
        return this.mDiPilotManagerImpl.setAiMatrixLightState(i3);
    }

    public int setAiTurnLightState(int i3) {
        return this.mDiPilotManagerImpl.setAiTurnLightState(i3);
    }

    public int setAlarmType(int i3) {
        return this.mDiPilotManagerImpl.setAlarmType(i3);
    }

    public int setAttentionDetectionState(int i3) {
        return this.mDiPilotManagerImpl.setAttentionDetectionState(i3);
    }

    public int setAutoParkingState(int i3) {
        return this.mDiPilotManagerImpl.setAutoParkingState(i3);
    }

    public int setBSISCollisionAlarmSwitch(int i3) {
        return this.mDiPilotManagerImpl.setBsisCollicionAlarmSwitchS(i3);
    }

    public int setBlindMonitorState(int i3) {
        return this.mDiPilotManagerImpl.setBlindMonitorState(i3);
    }

    public int setChargerCoverState(int i3) {
        return this.mDiPilotManagerImpl.setChargerCoverState(i3);
    }

    public int setChildCareState(int i3) {
        return this.mDiPilotManagerImpl.setChildCareState(i3);
    }

    public int setCollisionWarningState(int i3) {
        return this.mDiPilotManagerImpl.setCollisionWarningState(i3);
    }

    public int setControlLossWarningState(int i3) {
        return this.mDiPilotManagerImpl.setControlLossWarningState(i3);
    }

    public int setCpdStatus(int i3) {
        return this.mDiPilotManagerImpl.setCpdStatus(i3);
    }

    public int setDangerousActionDetectionState(int i3) {
        return this.mDiPilotManagerImpl.setDangerousActionDetectionState(i3);
    }

    public int setDangerousActionfSwitch(int i3) {
        return this.mDiPilotManagerImpl.setDangerousActionfSwitch(i3);
    }

    public int setDistractionDetectionAidState(int i3) {
        return this.mDiPilotManagerImpl.setDistractionDetectionAidState(i3);
    }

    public int setDistractionMonitoringAttention(int i3) {
        return this.mDiPilotManagerImpl.setDistractionMonitoringAttention(i3);
    }

    public int setDmsSelfCheckStatus(int i3) {
        return this.mDiPilotManagerImpl.setDmsSelfCheckStatus(i3);
    }

    public int setDmsSwitchStatus(int i3) {
        return this.mDiPilotManagerImpl.setDmsSwitchStatus(i3);
    }

    public int setDoNotPassWarningState(int i3) {
        return this.mDiPilotManagerImpl.setDoNotPassWarningState(i3);
    }

    public int setDriveDeparturState(int i3) {
        return this.mDiPilotManagerImpl.setDriveDeparturState(i3);
    }

    public int setDriveLevel(int i3) {
        return this.mDiPilotManagerImpl.setDriveLevel(i3);
    }

    public int setDriveRemindState(int i3) {
        return this.mDiPilotManagerImpl.setDriveRemindState(i3);
    }

    public int setDrivieDeparturState(int i3) {
        return this.mDiPilotManagerImpl.setDrivieDeparturState(i3);
    }

    public int setDrivieRemindState(int i3) {
        return this.mDiPilotManagerImpl.setDrivieRemindState(i3);
    }

    public int setE2EAIAssistanceState(int i3) {
        return this.mDiPilotManagerImpl.setE2EAIAssistanceState(i3);
    }

    public int setElecCarBodyStablySysState(int i3) {
        return this.mDiPilotManagerImpl.setElecCarBodyStablySysState(i3);
    }

    public int setEmergencyBrakeWarningState(int i3) {
        return this.mDiPilotManagerImpl.setEmergencyBrakeWarningState(i3);
    }

    public int setEmergencyLaneAssistanceState(int i3) {
        return this.mDiPilotManagerImpl.setEmergencyLaneAssistanceState(i3);
    }

    public int setEmergencyVehicleWarningState(int i3) {
        return this.mDiPilotManagerImpl.setEmergencyVehicleWarningState(i3);
    }

    public int setFatigueDetectionAidState(int i3) {
        return this.mDiPilotManagerImpl.setFatigueDetectionAidState(i3);
    }

    public int setFatigueMonitoringStatus(int i3) {
        return this.mDiPilotManagerImpl.setFatigueMonitoringStatus(i3);
    }

    public int setFatigueReliefSwitch(int i3) {
        return this.mDiPilotManagerImpl.setFatigueReliefSwitch(i3);
    }

    public int setFaultHandlerState(int i3) {
        return this.mDiPilotManagerImpl.setFaultHandlerState(i3);
    }

    public int setFrontCarStartState(int i3) {
        return this.mDiPilotManagerImpl.setFrontCarStartState(i3);
    }

    public int setFrontTrafficBrakeAlertState(int i3) {
        return this.mDiPilotManagerImpl.setFrontTrafficBrakeAlertState(i3);
    }

    public int setFrontTrafficCrossAlertState(int i3) {
        return this.mDiPilotManagerImpl.setFrontTrafficCrossAlertState(i3);
    }

    public int setGazePerceptionState(int i3) {
        return this.mDiPilotManagerImpl.setGazePerceptionState(i3);
    }

    public int setGestureRecognitionState(int i3) {
        return this.mDiPilotManagerImpl.setGestureRecognitionState(i3);
    }

    public int setGreenLightOptimalSpeedAdvisoryState(int i3) {
        return this.mDiPilotManagerImpl.setGreenLightOptimalSpeedAdvisoryState(i3);
    }

    public int setHighSpeedAINavigationState(int i3) {
        return this.mDiPilotManagerImpl.setHighSpeedAINavigationState(i3);
    }

    public int setHighSpeedOpenWindowState(int i3) {
        return this.mDiPilotManagerImpl.setHighSpeedOpenWindowState(i3);
    }

    public int setISLIWarningLevelSwitchStatus(int i3) {
        return this.mDiPilotManagerImpl.setIsliWarningLevelSwitchStatus(i3);
    }

    public int setIntelligentSpeedLimitAlertState(int i3) {
        return this.mDiPilotManagerImpl.setIntelligentSpeedLimitAlertState(i3);
    }

    public int setIntelligentSpeedLimitControlState(int i3) {
        return this.mDiPilotManagerImpl.setIntelligentSpeedLimitControlState(i3);
    }

    public int setInteractiveLaneChangeAssistanceState(int i3) {
        return this.mDiPilotManagerImpl.setInteractiveLaneChangeAssistanceState(i3);
    }

    public int setIntersectionCollisionAlertState(int i3) {
        return this.mDiPilotManagerImpl.setIntersectionCollisionAlertState(i3);
    }

    public int setIopState(int i3) {
        return this.mDiPilotManagerImpl.setIopState(i3);
    }

    public int setLDWStaticCalibration(int i3) {
        return this.mDiPilotManagerImpl.setLdwStaticCalibration(i3);
    }

    public int setLaneAssistType(int i3) {
        return this.mDiPilotManagerImpl.setLaneAssistType(i3);
    }

    public int setLeftBehindReminderState(int i3) {
        return this.mDiPilotManagerImpl.setLeftBehindReminderState(i3);
    }

    public int setLowSpeedEmergencyBrakingState(int i3) {
        return this.mDiPilotManagerImpl.setLowSpeedEmergencyBrakingState(i3);
    }

    public int setMOISFunctionSwitch(int i3) {
        return this.mDiPilotManagerImpl.setMoisFunctionSwitchS(i3);
    }

    public int setMistakeGasState(int i3) {
        return this.mDiPilotManagerImpl.setMistakeGasState(i3);
    }

    public int setOpenDoorAlertState(int i3) {
        return this.mDiPilotManagerImpl.setOpenDoorAlertState(i3);
    }

    public int setPredictCrashAlertMode(int i3) {
        return this.mDiPilotManagerImpl.setPredictCrashAlertMode(i3);
    }

    public int setPredictCrashAlertState(int i3) {
        return this.mDiPilotManagerImpl.setPredictCrashAlertState(i3);
    }

    public int setRainOpenFogLightState(int i3) {
        return this.mDiPilotManagerImpl.setRainOpenFogLightState(i3);
    }

    public int setRearCrashAlertState(int i3) {
        return this.mDiPilotManagerImpl.setRearCrashAlertState(i3);
    }

    public int setRearTrafficBrakeAlertState(int i3) {
        return this.mDiPilotManagerImpl.setRearTrafficBrakeAlertState(i3);
    }

    public int setRearTrafficCrossAlertState(int i3) {
        return this.mDiPilotManagerImpl.setRearTrafficCrossAlertState(i3);
    }

    public int setRecommendRestSpotState(int i3) {
        return this.mDiPilotManagerImpl.setRecommendRestSpotState(i3);
    }

    public int setRedLightViolationWarningState(int i3) {
        return this.mDiPilotManagerImpl.setRedLightViolationWarningState(i3);
    }

    public int setRelieveFatigueState(int i3) {
        return this.mDiPilotManagerImpl.setRelieveFatigueState(i3);
    }

    public int setSaocState(int i3) {
        return this.mDiPilotManagerImpl.setSaocState(i3);
    }

    public int setSideDistanceWarningState(int i3) {
        return this.mDiPilotManagerImpl.setSideDistanceWarningState(i3);
    }

    public int setSlifState(int i3) {
        return this.mDiPilotManagerImpl.setSlifState(i3);
    }

    public int setSpeedLimitRange(int i3) {
        return this.mDiPilotManagerImpl.setSpeedLimitRange(i3);
    }

    public int setSteepDescentSlowDownState(int i3) {
        return this.mDiPilotManagerImpl.setSteepDescentSlowDownState(i3);
    }

    public int setTSRLitSpdChgSwitchState(int i3) {
        return this.mDiPilotManagerImpl.setTsrLitSpdChgSwitchState(i3);
    }

    public int setTemperatureWhileFatigue(int i3) {
        return this.mDiPilotManagerImpl.setTemperatureWhileFatigue(i3);
    }

    public int setTrafficIncidentWarningState(int i3) {
        return this.mDiPilotManagerImpl.setTrafficIncidentWarningState(i3);
    }

    public int setTrafficJamWarningState(int i3) {
        return this.mDiPilotManagerImpl.setTrafficJamWarningState(i3);
    }

    public int setTrafficLightInfoPushState(int i3) {
        return this.mDiPilotManagerImpl.setTrafficLightInfoPushState(i3);
    }

    public int setTrafficLightRecognizeState(int i3) {
        return this.mDiPilotManagerImpl.setTrafficLightRecognizeState(i3);
    }

    public int setTrafficSignRecognizeState(int i3) {
        return this.mDiPilotManagerImpl.setTrafficSignRecognizeState(i3);
    }

    public int setTrafficSignalWarningState(int i3) {
        return this.mDiPilotManagerImpl.setTrafficSignalWarningState(i3);
    }

    public int setUrbanAIAssistanceState(int i3) {
        return this.mDiPilotManagerImpl.setUrbanAIAssistanceState(i3);
    }

    public int setVerifyMode(int i3) {
        return this.mDiPilotManagerImpl.setVerifyMode(i3);
    }

    public void unregisterListener(AbsBYDAutoDiPilotListener absBYDAutoDiPilotListener) {
        this.mDiPilotManagerImpl.unregisterListener(absBYDAutoDiPilotListener);
    }

    public void registerListener(AbsBYDAutoDiPilotListener absBYDAutoDiPilotListener, int[] iArr) {
        this.mDiPilotManagerImpl.registerListener(absBYDAutoDiPilotListener, iArr);
    }
}

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

    public int getACCState() {
        return this.mDiPilotManagerImpl.getAccState();
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

    public int getFCSRFault() {
        return this.mDiPilotManagerImpl.getFcsrFault();
    }

    public int getFCSRState() {
        return this.mDiPilotManagerImpl.getFcsrState();
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

    public int setACCState(int i2) {
        return this.mDiPilotManagerImpl.setAccState(i2);
    }

    public int setAINavigationState(int i2) {
        return this.mDiPilotManagerImpl.setAINavigationState(i2);
    }

    public int setAbnormalVehicleWarningState(int i2) {
        return this.mDiPilotManagerImpl.setAbnormalVehicleWarningState(i2);
    }

    public int setAiCareState(int i2) {
        return this.mDiPilotManagerImpl.setAiCareState(i2);
    }

    public int setAiDistanceLightState(int i2) {
        return this.mDiPilotManagerImpl.setAiDistanceLightState(i2);
    }

    public int setAiEmergencyBrakeState(int i2) {
        return this.mDiPilotManagerImpl.setAiEmergencyBrakeState(i2);
    }

    public int setAiMatrixLightState(int i2) {
        return this.mDiPilotManagerImpl.setAiMatrixLightState(i2);
    }

    public int setAiTurnLightState(int i2) {
        return this.mDiPilotManagerImpl.setAiTurnLightState(i2);
    }

    public int setAlarmType(int i2) {
        return this.mDiPilotManagerImpl.setAlarmType(i2);
    }

    public int setAttentionDetectionState(int i2) {
        return this.mDiPilotManagerImpl.setAttentionDetectionState(i2);
    }

    public int setAutoParkingState(int i2) {
        return this.mDiPilotManagerImpl.setAutoParkingState(i2);
    }

    public int setBSISCollisionAlarmSwitch(int i2) {
        return this.mDiPilotManagerImpl.setBsisCollicionAlarmSwitchS(i2);
    }

    public int setBlindMonitorState(int i2) {
        return this.mDiPilotManagerImpl.setBlindMonitorState(i2);
    }

    public int setChargerCoverState(int i2) {
        return this.mDiPilotManagerImpl.setChargerCoverState(i2);
    }

    public int setChildCareState(int i2) {
        return this.mDiPilotManagerImpl.setChildCareState(i2);
    }

    public int setCollisionWarningState(int i2) {
        return this.mDiPilotManagerImpl.setCollisionWarningState(i2);
    }

    public int setControlLossWarningState(int i2) {
        return this.mDiPilotManagerImpl.setControlLossWarningState(i2);
    }

    public int setCpdStatus(int i2) {
        return this.mDiPilotManagerImpl.setCpdStatus(i2);
    }

    public int setDangerousActionDetectionState(int i2) {
        return this.mDiPilotManagerImpl.setDangerousActionDetectionState(i2);
    }

    public int setDangerousActionfSwitch(int i2) {
        return this.mDiPilotManagerImpl.setDangerousActionfSwitch(i2);
    }

    public int setDistractionDetectionAidState(int i2) {
        return this.mDiPilotManagerImpl.setDistractionDetectionAidState(i2);
    }

    public int setDistractionMonitoringAttention(int i2) {
        return this.mDiPilotManagerImpl.setDistractionMonitoringAttention(i2);
    }

    public int setDmsSelfCheckStatus(int i2) {
        return this.mDiPilotManagerImpl.setDmsSelfCheckStatus(i2);
    }

    public int setDmsSwitchStatus(int i2) {
        return this.mDiPilotManagerImpl.setDmsSwitchStatus(i2);
    }

    public int setDoNotPassWarningState(int i2) {
        return this.mDiPilotManagerImpl.setDoNotPassWarningState(i2);
    }

    public int setDriveDeparturState(int i2) {
        return this.mDiPilotManagerImpl.setDriveDeparturState(i2);
    }

    public int setDriveLevel(int i2) {
        return this.mDiPilotManagerImpl.setDriveLevel(i2);
    }

    public int setDriveRemindState(int i2) {
        return this.mDiPilotManagerImpl.setDriveRemindState(i2);
    }

    public int setDrivieDeparturState(int i2) {
        return this.mDiPilotManagerImpl.setDrivieDeparturState(i2);
    }

    public int setDrivieRemindState(int i2) {
        return this.mDiPilotManagerImpl.setDrivieRemindState(i2);
    }

    public int setE2EAIAssistanceState(int i2) {
        return this.mDiPilotManagerImpl.setE2EAIAssistanceState(i2);
    }

    public int setElecCarBodyStablySysState(int i2) {
        return this.mDiPilotManagerImpl.setElecCarBodyStablySysState(i2);
    }

    public int setEmergencyBrakeWarningState(int i2) {
        return this.mDiPilotManagerImpl.setEmergencyBrakeWarningState(i2);
    }

    public int setEmergencyLaneAssistanceState(int i2) {
        return this.mDiPilotManagerImpl.setEmergencyLaneAssistanceState(i2);
    }

    public int setEmergencyVehicleWarningState(int i2) {
        return this.mDiPilotManagerImpl.setEmergencyVehicleWarningState(i2);
    }

    public int setFCSRState(int i2) {
        return this.mDiPilotManagerImpl.setFcsrState(i2);
    }

    public int setFatigueDetectionAidState(int i2) {
        return this.mDiPilotManagerImpl.setFatigueDetectionAidState(i2);
    }

    public int setFatigueMonitoringStatus(int i2) {
        return this.mDiPilotManagerImpl.setFatigueMonitoringStatus(i2);
    }

    public int setFatigueReliefSwitch(int i2) {
        return this.mDiPilotManagerImpl.setFatigueReliefSwitch(i2);
    }

    public int setFaultHandlerState(int i2) {
        return this.mDiPilotManagerImpl.setFaultHandlerState(i2);
    }

    public int setFrontCarStartState(int i2) {
        return this.mDiPilotManagerImpl.setFrontCarStartState(i2);
    }

    public int setFrontTrafficBrakeAlertState(int i2) {
        return this.mDiPilotManagerImpl.setFrontTrafficBrakeAlertState(i2);
    }

    public int setFrontTrafficCrossAlertState(int i2) {
        return this.mDiPilotManagerImpl.setFrontTrafficCrossAlertState(i2);
    }

    public int setGazePerceptionState(int i2) {
        return this.mDiPilotManagerImpl.setGazePerceptionState(i2);
    }

    public int setGestureRecognitionState(int i2) {
        return this.mDiPilotManagerImpl.setGestureRecognitionState(i2);
    }

    public int setGreenLightOptimalSpeedAdvisoryState(int i2) {
        return this.mDiPilotManagerImpl.setGreenLightOptimalSpeedAdvisoryState(i2);
    }

    public int setHighSpeedAINavigationState(int i2) {
        return this.mDiPilotManagerImpl.setHighSpeedAINavigationState(i2);
    }

    public int setHighSpeedOpenWindowState(int i2) {
        return this.mDiPilotManagerImpl.setHighSpeedOpenWindowState(i2);
    }

    public int setISLIWarningLevelSwitchStatus(int i2) {
        return this.mDiPilotManagerImpl.setIsliWarningLevelSwitchStatus(i2);
    }

    public int setIntelligentSpeedLimitAlertState(int i2) {
        return this.mDiPilotManagerImpl.setIntelligentSpeedLimitAlertState(i2);
    }

    public int setIntelligentSpeedLimitControlState(int i2) {
        return this.mDiPilotManagerImpl.setIntelligentSpeedLimitControlState(i2);
    }

    public int setInteractiveLaneChangeAssistanceState(int i2) {
        return this.mDiPilotManagerImpl.setInteractiveLaneChangeAssistanceState(i2);
    }

    public int setIntersectionCollisionAlertState(int i2) {
        return this.mDiPilotManagerImpl.setIntersectionCollisionAlertState(i2);
    }

    public int setIopState(int i2) {
        return this.mDiPilotManagerImpl.setIopState(i2);
    }

    public int setLDWStaticCalibration(int i2) {
        return this.mDiPilotManagerImpl.setLdwStaticCalibration(i2);
    }

    public int setLaneAssistType(int i2) {
        return this.mDiPilotManagerImpl.setLaneAssistType(i2);
    }

    public int setLeftBehindReminderState(int i2) {
        return this.mDiPilotManagerImpl.setLeftBehindReminderState(i2);
    }

    public int setLowSpeedEmergencyBrakingState(int i2) {
        return this.mDiPilotManagerImpl.setLowSpeedEmergencyBrakingState(i2);
    }

    public int setMOISFunctionSwitch(int i2) {
        return this.mDiPilotManagerImpl.setMoisFunctionSwitchS(i2);
    }

    public int setMistakeGasState(int i2) {
        return this.mDiPilotManagerImpl.setMistakeGasState(i2);
    }

    public int setOpenDoorAlertState(int i2) {
        return this.mDiPilotManagerImpl.setOpenDoorAlertState(i2);
    }

    public int setPredictCrashAlertMode(int i2) {
        return this.mDiPilotManagerImpl.setPredictCrashAlertMode(i2);
    }

    public int setPredictCrashAlertState(int i2) {
        return this.mDiPilotManagerImpl.setPredictCrashAlertState(i2);
    }

    public int setRainOpenFogLightState(int i2) {
        return this.mDiPilotManagerImpl.setRainOpenFogLightState(i2);
    }

    public int setRearCrashAlertState(int i2) {
        return this.mDiPilotManagerImpl.setRearCrashAlertState(i2);
    }

    public int setRearTrafficBrakeAlertState(int i2) {
        return this.mDiPilotManagerImpl.setRearTrafficBrakeAlertState(i2);
    }

    public int setRearTrafficCrossAlertState(int i2) {
        return this.mDiPilotManagerImpl.setRearTrafficCrossAlertState(i2);
    }

    public int setRecommendRestSpotState(int i2) {
        return this.mDiPilotManagerImpl.setRecommendRestSpotState(i2);
    }

    public int setRedLightViolationWarningState(int i2) {
        return this.mDiPilotManagerImpl.setRedLightViolationWarningState(i2);
    }

    public int setRelieveFatigueState(int i2) {
        return this.mDiPilotManagerImpl.setRelieveFatigueState(i2);
    }

    public int setSaocState(int i2) {
        return this.mDiPilotManagerImpl.setSaocState(i2);
    }

    public int setSideDistanceWarningState(int i2) {
        return this.mDiPilotManagerImpl.setSideDistanceWarningState(i2);
    }

    public int setSlifState(int i2) {
        return this.mDiPilotManagerImpl.setSlifState(i2);
    }

    public int setSpeedLimitRange(int i2) {
        return this.mDiPilotManagerImpl.setSpeedLimitRange(i2);
    }

    public int setSteepDescentSlowDownState(int i2) {
        return this.mDiPilotManagerImpl.setSteepDescentSlowDownState(i2);
    }

    public int setTSRLitSpdChgSwitchState(int i2) {
        return this.mDiPilotManagerImpl.setTsrLitSpdChgSwitchState(i2);
    }

    public int setTemperatureWhileFatigue(int i2) {
        return this.mDiPilotManagerImpl.setTemperatureWhileFatigue(i2);
    }

    public int setTrafficIncidentWarningState(int i2) {
        return this.mDiPilotManagerImpl.setTrafficIncidentWarningState(i2);
    }

    public int setTrafficJamWarningState(int i2) {
        return this.mDiPilotManagerImpl.setTrafficJamWarningState(i2);
    }

    public int setTrafficLightInfoPushState(int i2) {
        return this.mDiPilotManagerImpl.setTrafficLightInfoPushState(i2);
    }

    public int setTrafficLightRecognizeState(int i2) {
        return this.mDiPilotManagerImpl.setTrafficLightRecognizeState(i2);
    }

    public int setTrafficSignRecognizeState(int i2) {
        return this.mDiPilotManagerImpl.setTrafficSignRecognizeState(i2);
    }

    public int setTrafficSignalWarningState(int i2) {
        return this.mDiPilotManagerImpl.setTrafficSignalWarningState(i2);
    }

    public int setUrbanAIAssistanceState(int i2) {
        return this.mDiPilotManagerImpl.setUrbanAIAssistanceState(i2);
    }

    public int setVerifyMode(int i2) {
        return this.mDiPilotManagerImpl.setVerifyMode(i2);
    }

    public void unregisterListener(AbsBYDAutoDiPilotListener absBYDAutoDiPilotListener) {
        this.mDiPilotManagerImpl.unregisterListener(absBYDAutoDiPilotListener);
    }

    public void registerListener(AbsBYDAutoDiPilotListener absBYDAutoDiPilotListener, int[] iArr) {
        this.mDiPilotManagerImpl.registerListener(absBYDAutoDiPilotListener, iArr);
    }
}

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

    public int setACCState(int i8) {
        return this.mDiPilotManagerImpl.setAccState(i8);
    }

    public int setAINavigationState(int i8) {
        return this.mDiPilotManagerImpl.setAINavigationState(i8);
    }

    public int setAbnormalVehicleWarningState(int i8) {
        return this.mDiPilotManagerImpl.setAbnormalVehicleWarningState(i8);
    }

    public int setAiCareState(int i8) {
        return this.mDiPilotManagerImpl.setAiCareState(i8);
    }

    public int setAiDistanceLightState(int i8) {
        return this.mDiPilotManagerImpl.setAiDistanceLightState(i8);
    }

    public int setAiEmergencyBrakeState(int i8) {
        return this.mDiPilotManagerImpl.setAiEmergencyBrakeState(i8);
    }

    public int setAiMatrixLightState(int i8) {
        return this.mDiPilotManagerImpl.setAiMatrixLightState(i8);
    }

    public int setAiTurnLightState(int i8) {
        return this.mDiPilotManagerImpl.setAiTurnLightState(i8);
    }

    public int setAlarmType(int i8) {
        return this.mDiPilotManagerImpl.setAlarmType(i8);
    }

    public int setAttentionDetectionState(int i8) {
        return this.mDiPilotManagerImpl.setAttentionDetectionState(i8);
    }

    public int setAutoParkingState(int i8) {
        return this.mDiPilotManagerImpl.setAutoParkingState(i8);
    }

    public int setBSISCollisionAlarmSwitch(int i8) {
        return this.mDiPilotManagerImpl.setBsisCollicionAlarmSwitchS(i8);
    }

    public int setBlindMonitorState(int i8) {
        return this.mDiPilotManagerImpl.setBlindMonitorState(i8);
    }

    public int setChargerCoverState(int i8) {
        return this.mDiPilotManagerImpl.setChargerCoverState(i8);
    }

    public int setChildCareState(int i8) {
        return this.mDiPilotManagerImpl.setChildCareState(i8);
    }

    public int setCollisionWarningState(int i8) {
        return this.mDiPilotManagerImpl.setCollisionWarningState(i8);
    }

    public int setControlLossWarningState(int i8) {
        return this.mDiPilotManagerImpl.setControlLossWarningState(i8);
    }

    public int setCpdStatus(int i8) {
        return this.mDiPilotManagerImpl.setCpdStatus(i8);
    }

    public int setDangerousActionDetectionState(int i8) {
        return this.mDiPilotManagerImpl.setDangerousActionDetectionState(i8);
    }

    public int setDangerousActionfSwitch(int i8) {
        return this.mDiPilotManagerImpl.setDangerousActionfSwitch(i8);
    }

    public int setDistractionDetectionAidState(int i8) {
        return this.mDiPilotManagerImpl.setDistractionDetectionAidState(i8);
    }

    public int setDistractionMonitoringAttention(int i8) {
        return this.mDiPilotManagerImpl.setDistractionMonitoringAttention(i8);
    }

    public int setDmsSelfCheckStatus(int i8) {
        return this.mDiPilotManagerImpl.setDmsSelfCheckStatus(i8);
    }

    public int setDmsSwitchStatus(int i8) {
        return this.mDiPilotManagerImpl.setDmsSwitchStatus(i8);
    }

    public int setDoNotPassWarningState(int i8) {
        return this.mDiPilotManagerImpl.setDoNotPassWarningState(i8);
    }

    public int setDriveDeparturState(int i8) {
        return this.mDiPilotManagerImpl.setDriveDeparturState(i8);
    }

    public int setDriveLevel(int i8) {
        return this.mDiPilotManagerImpl.setDriveLevel(i8);
    }

    public int setDriveRemindState(int i8) {
        return this.mDiPilotManagerImpl.setDriveRemindState(i8);
    }

    public int setDrivieDeparturState(int i8) {
        return this.mDiPilotManagerImpl.setDrivieDeparturState(i8);
    }

    public int setDrivieRemindState(int i8) {
        return this.mDiPilotManagerImpl.setDrivieRemindState(i8);
    }

    public int setE2EAIAssistanceState(int i8) {
        return this.mDiPilotManagerImpl.setE2EAIAssistanceState(i8);
    }

    public int setElecCarBodyStablySysState(int i8) {
        return this.mDiPilotManagerImpl.setElecCarBodyStablySysState(i8);
    }

    public int setEmergencyBrakeWarningState(int i8) {
        return this.mDiPilotManagerImpl.setEmergencyBrakeWarningState(i8);
    }

    public int setEmergencyLaneAssistanceState(int i8) {
        return this.mDiPilotManagerImpl.setEmergencyLaneAssistanceState(i8);
    }

    public int setEmergencyVehicleWarningState(int i8) {
        return this.mDiPilotManagerImpl.setEmergencyVehicleWarningState(i8);
    }

    public int setFCSRState(int i8) {
        return this.mDiPilotManagerImpl.setFcsrState(i8);
    }

    public int setFatigueDetectionAidState(int i8) {
        return this.mDiPilotManagerImpl.setFatigueDetectionAidState(i8);
    }

    public int setFatigueMonitoringStatus(int i8) {
        return this.mDiPilotManagerImpl.setFatigueMonitoringStatus(i8);
    }

    public int setFatigueReliefSwitch(int i8) {
        return this.mDiPilotManagerImpl.setFatigueReliefSwitch(i8);
    }

    public int setFaultHandlerState(int i8) {
        return this.mDiPilotManagerImpl.setFaultHandlerState(i8);
    }

    public int setFrontCarStartState(int i8) {
        return this.mDiPilotManagerImpl.setFrontCarStartState(i8);
    }

    public int setFrontTrafficBrakeAlertState(int i8) {
        return this.mDiPilotManagerImpl.setFrontTrafficBrakeAlertState(i8);
    }

    public int setFrontTrafficCrossAlertState(int i8) {
        return this.mDiPilotManagerImpl.setFrontTrafficCrossAlertState(i8);
    }

    public int setGazePerceptionState(int i8) {
        return this.mDiPilotManagerImpl.setGazePerceptionState(i8);
    }

    public int setGestureRecognitionState(int i8) {
        return this.mDiPilotManagerImpl.setGestureRecognitionState(i8);
    }

    public int setGreenLightOptimalSpeedAdvisoryState(int i8) {
        return this.mDiPilotManagerImpl.setGreenLightOptimalSpeedAdvisoryState(i8);
    }

    public int setHighSpeedAINavigationState(int i8) {
        return this.mDiPilotManagerImpl.setHighSpeedAINavigationState(i8);
    }

    public int setHighSpeedOpenWindowState(int i8) {
        return this.mDiPilotManagerImpl.setHighSpeedOpenWindowState(i8);
    }

    public int setISLIWarningLevelSwitchStatus(int i8) {
        return this.mDiPilotManagerImpl.setIsliWarningLevelSwitchStatus(i8);
    }

    public int setIntelligentSpeedLimitAlertState(int i8) {
        return this.mDiPilotManagerImpl.setIntelligentSpeedLimitAlertState(i8);
    }

    public int setIntelligentSpeedLimitControlState(int i8) {
        return this.mDiPilotManagerImpl.setIntelligentSpeedLimitControlState(i8);
    }

    public int setInteractiveLaneChangeAssistanceState(int i8) {
        return this.mDiPilotManagerImpl.setInteractiveLaneChangeAssistanceState(i8);
    }

    public int setIntersectionCollisionAlertState(int i8) {
        return this.mDiPilotManagerImpl.setIntersectionCollisionAlertState(i8);
    }

    public int setIopState(int i8) {
        return this.mDiPilotManagerImpl.setIopState(i8);
    }

    public int setLDWStaticCalibration(int i8) {
        return this.mDiPilotManagerImpl.setLdwStaticCalibration(i8);
    }

    public int setLaneAssistType(int i8) {
        return this.mDiPilotManagerImpl.setLaneAssistType(i8);
    }

    public int setLeftBehindReminderState(int i8) {
        return this.mDiPilotManagerImpl.setLeftBehindReminderState(i8);
    }

    public int setLowSpeedEmergencyBrakingState(int i8) {
        return this.mDiPilotManagerImpl.setLowSpeedEmergencyBrakingState(i8);
    }

    public int setMOISFunctionSwitch(int i8) {
        return this.mDiPilotManagerImpl.setMoisFunctionSwitchS(i8);
    }

    public int setMistakeGasState(int i8) {
        return this.mDiPilotManagerImpl.setMistakeGasState(i8);
    }

    public int setOpenDoorAlertState(int i8) {
        return this.mDiPilotManagerImpl.setOpenDoorAlertState(i8);
    }

    public int setPredictCrashAlertMode(int i8) {
        return this.mDiPilotManagerImpl.setPredictCrashAlertMode(i8);
    }

    public int setPredictCrashAlertState(int i8) {
        return this.mDiPilotManagerImpl.setPredictCrashAlertState(i8);
    }

    public int setRainOpenFogLightState(int i8) {
        return this.mDiPilotManagerImpl.setRainOpenFogLightState(i8);
    }

    public int setRearCrashAlertState(int i8) {
        return this.mDiPilotManagerImpl.setRearCrashAlertState(i8);
    }

    public int setRearTrafficBrakeAlertState(int i8) {
        return this.mDiPilotManagerImpl.setRearTrafficBrakeAlertState(i8);
    }

    public int setRearTrafficCrossAlertState(int i8) {
        return this.mDiPilotManagerImpl.setRearTrafficCrossAlertState(i8);
    }

    public int setRecommendRestSpotState(int i8) {
        return this.mDiPilotManagerImpl.setRecommendRestSpotState(i8);
    }

    public int setRedLightViolationWarningState(int i8) {
        return this.mDiPilotManagerImpl.setRedLightViolationWarningState(i8);
    }

    public int setRelieveFatigueState(int i8) {
        return this.mDiPilotManagerImpl.setRelieveFatigueState(i8);
    }

    public int setSaocState(int i8) {
        return this.mDiPilotManagerImpl.setSaocState(i8);
    }

    public int setSideDistanceWarningState(int i8) {
        return this.mDiPilotManagerImpl.setSideDistanceWarningState(i8);
    }

    public int setSlifState(int i8) {
        return this.mDiPilotManagerImpl.setSlifState(i8);
    }

    public int setSpeedLimitRange(int i8) {
        return this.mDiPilotManagerImpl.setSpeedLimitRange(i8);
    }

    public int setSteepDescentSlowDownState(int i8) {
        return this.mDiPilotManagerImpl.setSteepDescentSlowDownState(i8);
    }

    public int setTSRLitSpdChgSwitchState(int i8) {
        return this.mDiPilotManagerImpl.setTsrLitSpdChgSwitchState(i8);
    }

    public int setTemperatureWhileFatigue(int i8) {
        return this.mDiPilotManagerImpl.setTemperatureWhileFatigue(i8);
    }

    public int setTrafficIncidentWarningState(int i8) {
        return this.mDiPilotManagerImpl.setTrafficIncidentWarningState(i8);
    }

    public int setTrafficJamWarningState(int i8) {
        return this.mDiPilotManagerImpl.setTrafficJamWarningState(i8);
    }

    public int setTrafficLightInfoPushState(int i8) {
        return this.mDiPilotManagerImpl.setTrafficLightInfoPushState(i8);
    }

    public int setTrafficLightRecognizeState(int i8) {
        return this.mDiPilotManagerImpl.setTrafficLightRecognizeState(i8);
    }

    public int setTrafficSignRecognizeState(int i8) {
        return this.mDiPilotManagerImpl.setTrafficSignRecognizeState(i8);
    }

    public int setTrafficSignalWarningState(int i8) {
        return this.mDiPilotManagerImpl.setTrafficSignalWarningState(i8);
    }

    public int setUrbanAIAssistanceState(int i8) {
        return this.mDiPilotManagerImpl.setUrbanAIAssistanceState(i8);
    }

    public int setVerifyMode(int i8) {
        return this.mDiPilotManagerImpl.setVerifyMode(i8);
    }

    public void unregisterListener(AbsBYDAutoDiPilotListener absBYDAutoDiPilotListener) {
        this.mDiPilotManagerImpl.unregisterListener(absBYDAutoDiPilotListener);
    }

    public void registerListener(AbsBYDAutoDiPilotListener absBYDAutoDiPilotListener, int[] iArr) {
        this.mDiPilotManagerImpl.registerListener(absBYDAutoDiPilotListener, iArr);
    }
}

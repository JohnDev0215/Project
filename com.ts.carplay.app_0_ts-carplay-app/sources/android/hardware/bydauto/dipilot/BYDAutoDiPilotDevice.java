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
    protected static final String TAG = "BYDAutoDiPilotDevice";
    public static final int TSR_LIT_SPD_INFORM_CHANGE = 1;
    public static final int TSR_LIT_SPD_INFORM_NO_CHANGE = 0;
    private static BYDAutoDiPilotDevice sBYDAutoDiPilotDevice;
    private DiPilotManagerImpl mDiPilotManagerImpl;

    BYDAutoDiPilotDevice(Context con) {
        super(con.getApplicationContext());
        this.mDiPilotManagerImpl = DiPilotManagerImpl.getsInstance(con.getApplicationContext());
    }

    public static synchronized BYDAutoDiPilotDevice getInstance(Context con) {
        if (sBYDAutoDiPilotDevice == null) {
            sBYDAutoDiPilotDevice = new BYDAutoDiPilotDevice(con.getApplicationContext());
        }
        return sBYDAutoDiPilotDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_ADAS_GET;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_ADAS_SET;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return this.mDiPilotManagerImpl.getDevicetype();
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return this.mDiPilotManagerImpl.getType();
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return this.mDiPilotManagerImpl.getFeatureList();
    }

    public int getDrivieRemindState() {
        return this.mDiPilotManagerImpl.getDrivieRemindState();
    }

    public int setDrivieRemindState(int state) {
        return this.mDiPilotManagerImpl.setDrivieRemindState(state);
    }

    public int getDrivieDepartureState() {
        return this.mDiPilotManagerImpl.getDrivieDepartureState();
    }

    public int setDrivieDeparturState(int state) {
        return this.mDiPilotManagerImpl.setDrivieDeparturState(state);
    }

    public int getFrontCarStartState() {
        return this.mDiPilotManagerImpl.getFrontCarStartState();
    }

    public int setFrontCarStartState(int state) {
        return this.mDiPilotManagerImpl.setFrontCarStartState(state);
    }

    public int getFaultHandlerState() {
        return this.mDiPilotManagerImpl.getFaultHandlerState();
    }

    public int setFaultHandlerState(int state) {
        return this.mDiPilotManagerImpl.setFaultHandlerState(state);
    }

    public int getChargerCoverState() {
        return this.mDiPilotManagerImpl.getChargerCoverState();
    }

    public int setChargerCoverState(int state) {
        return this.mDiPilotManagerImpl.setChargerCoverState(state);
    }

    public int getAiTurnLightState() {
        return this.mDiPilotManagerImpl.getAiTurnLightState();
    }

    public int setAiTurnLightState(int state) {
        return this.mDiPilotManagerImpl.setAiTurnLightState(state);
    }

    public int getRainOpenFogLightState() {
        return this.mDiPilotManagerImpl.getRainOpenFogLightState();
    }

    public int setRainOpenFogLightState(int state) {
        return this.mDiPilotManagerImpl.setRainOpenFogLightState(state);
    }

    public int getHighSpeedOpenWindowState() {
        return this.mDiPilotManagerImpl.getHighSpeedOpenWindowState();
    }

    public int setHighSpeedOpenWindowState(int state) {
        return this.mDiPilotManagerImpl.setHighSpeedOpenWindowState(state);
    }

    public int getDriveLevel() {
        return this.mDiPilotManagerImpl.getDriveLevel();
    }

    public int setDriveLevel(int value) {
        return this.mDiPilotManagerImpl.setDriveLevel(value);
    }

    public int getHighSpeedAINavigationState() {
        return this.mDiPilotManagerImpl.getHighSpeedAINavigationState();
    }

    public int setHighSpeedAINavigationState(int state) {
        return this.mDiPilotManagerImpl.setHighSpeedAINavigationState(state);
    }

    public int getUrbanAIAssistanceState() {
        return this.mDiPilotManagerImpl.getUrbanAIAssistanceState();
    }

    public int setUrbanAIAssistanceState(int state) {
        return this.mDiPilotManagerImpl.setUrbanAIAssistanceState(state);
    }

    public int getE2EAIAssistanceState() {
        return this.mDiPilotManagerImpl.getE2EAIAssistanceState();
    }

    public int setE2EAIAssistanceState(int state) {
        return this.mDiPilotManagerImpl.setE2EAIAssistanceState(state);
    }

    public int getInteractiveLaneChangeAssistanceState() {
        return this.mDiPilotManagerImpl.getInteractiveLaneChangeAssistanceState();
    }

    public int setInteractiveLaneChangeAssistanceState(int state) {
        return this.mDiPilotManagerImpl.setInteractiveLaneChangeAssistanceState(state);
    }

    public int getAINavigationState() {
        return this.mDiPilotManagerImpl.getAINavigationState();
    }

    public int setAINavigationState(int state) {
        return this.mDiPilotManagerImpl.setAINavigationState(state);
    }

    public int getLaneAssistType() {
        return this.mDiPilotManagerImpl.getLaneAssistType();
    }

    public int setLaneAssistType(int value) {
        return this.mDiPilotManagerImpl.setLaneAssistType(value);
    }

    public int getAlarmType() {
        return this.mDiPilotManagerImpl.getAlarmType();
    }

    public int setAlarmType(int value) {
        return this.mDiPilotManagerImpl.setAlarmType(value);
    }

    @Deprecated
    public int getTrafficSignRecognizeState() {
        return this.mDiPilotManagerImpl.getTrafficSignRecognizeState();
    }

    public int setTrafficSignRecognizeState(int state) {
        return this.mDiPilotManagerImpl.setTrafficSignRecognizeState(state);
    }

    public int getAiCareState() {
        return this.mDiPilotManagerImpl.getAiCareState();
    }

    public int setAiCareState(int state) {
        return this.mDiPilotManagerImpl.setAiCareState(state);
    }

    public int getMistakeGasState() {
        return this.mDiPilotManagerImpl.getMistakeGasState();
    }

    public int setMistakeGasState(int state) {
        return this.mDiPilotManagerImpl.setMistakeGasState(state);
    }

    public int getAiMatrixLightState() {
        return this.mDiPilotManagerImpl.getAiMatrixLightState();
    }

    public int setAiMatrixLightState(int state) {
        return this.mDiPilotManagerImpl.setAiMatrixLightState(state);
    }

    @Deprecated
    public int getAiDistanceLightState() {
        return this.mDiPilotManagerImpl.getAiDistanceLightState();
    }

    public int setAiDistanceLightState(int state) {
        return this.mDiPilotManagerImpl.setAiDistanceLightState(state);
    }

    @Deprecated
    public int getTrafficLightRecognizeState() {
        return this.mDiPilotManagerImpl.getTrafficLightRecognizeState();
    }

    public int setTrafficLightRecognizeState(int state) {
        return this.mDiPilotManagerImpl.setTrafficLightRecognizeState(state);
    }

    @Deprecated
    public int getEmergencyLaneAssistanceState() {
        return this.mDiPilotManagerImpl.getEmergencyLaneAssistanceState();
    }

    public int setEmergencyLaneAssistanceState(int state) {
        return this.mDiPilotManagerImpl.setEmergencyLaneAssistanceState(state);
    }

    public int getBlindMonitorState() {
        return this.mDiPilotManagerImpl.getBlindMonitorState();
    }

    public int setBlindMonitorState(int state) {
        return this.mDiPilotManagerImpl.setBlindMonitorState(state);
    }

    public int getOpenDoorAlertState() {
        return this.mDiPilotManagerImpl.getOpenDoorAlertState();
    }

    public int setOpenDoorAlertState(int state) {
        return this.mDiPilotManagerImpl.setOpenDoorAlertState(state);
    }

    public int getRearCrashAlertState() {
        return this.mDiPilotManagerImpl.getRearCrashAlertState();
    }

    public int setRearCrashAlertState(int state) {
        return this.mDiPilotManagerImpl.setRearCrashAlertState(state);
    }

    public int getRearTrafficCrossAlertState() {
        return this.mDiPilotManagerImpl.getRearTrafficCrossAlertState();
    }

    public int setRearTrafficCrossAlertState(int state) {
        return this.mDiPilotManagerImpl.setRearTrafficCrossAlertState(state);
    }

    public int getRearTrafficBrakeAlertState() {
        return this.mDiPilotManagerImpl.getRearTrafficBrakeAlertState();
    }

    public int setRearTrafficBrakeAlertState(int state) {
        return this.mDiPilotManagerImpl.setRearTrafficBrakeAlertState(state);
    }

    public int getAiEmergencyBrakeState() {
        return this.mDiPilotManagerImpl.getAiEmergencyBrakeState();
    }

    public int setAiEmergencyBrakeState(int state) {
        return this.mDiPilotManagerImpl.setAiEmergencyBrakeState(state);
    }

    public int getPredictCrashAlertState() {
        return this.mDiPilotManagerImpl.getPredictCrashAlertState();
    }

    public int setPredictCrashAlertState(int state) {
        return this.mDiPilotManagerImpl.setPredictCrashAlertState(state);
    }

    public int setPredictCrashAlertMode(int state) {
        return this.mDiPilotManagerImpl.setPredictCrashAlertMode(state);
    }

    public int getFrontTrafficCrossAlertState() {
        return this.mDiPilotManagerImpl.getFrontTrafficCrossAlertState();
    }

    public int setFrontTrafficCrossAlertState(int state) {
        return this.mDiPilotManagerImpl.setFrontTrafficCrossAlertState(state);
    }

    public int getFrontTrafficBrakeAlertState() {
        return this.mDiPilotManagerImpl.getFrontTrafficBrakeAlertState();
    }

    public int setFrontTrafficBrakeAlertState(int state) {
        return this.mDiPilotManagerImpl.setFrontTrafficBrakeAlertState(state);
    }

    public int getElecCarBodyStablySysState() {
        return this.mDiPilotManagerImpl.getElecCarBodyStablySysState();
    }

    public int setElecCarBodyStablySysState(int state) {
        return this.mDiPilotManagerImpl.setElecCarBodyStablySysState(state);
    }

    public int getAutoParkingState() {
        return this.mDiPilotManagerImpl.getAutoParkingState();
    }

    public int setAutoParkingState(int state) {
        return this.mDiPilotManagerImpl.setAutoParkingState(state);
    }

    public int getSteepDescentSlowDownState() {
        return this.mDiPilotManagerImpl.getSteepDescentSlowDownState();
    }

    public int setSteepDescentSlowDownState(int state) {
        return this.mDiPilotManagerImpl.setSteepDescentSlowDownState(state);
    }

    public int getFatigueDetectionAidState() {
        return this.mDiPilotManagerImpl.getFatigueDetectionAidState();
    }

    public int setFatigueDetectionAidState(int state) {
        return this.mDiPilotManagerImpl.setFatigueDetectionAidState(state);
    }

    public int getRelieveFatigueState() {
        return this.mDiPilotManagerImpl.getRelieveFatigueState();
    }

    public int setRelieveFatigueState(int state) {
        return this.mDiPilotManagerImpl.setRelieveFatigueState(state);
    }

    public int getRecommendRestSpotState() {
        return this.mDiPilotManagerImpl.getRecommendRestSpotState();
    }

    public int setRecommendRestSpotState(int state) {
        return this.mDiPilotManagerImpl.setRecommendRestSpotState(state);
    }

    public int getTemperatureWhileFatigue() {
        return this.mDiPilotManagerImpl.getTemperatureWhileFatigue();
    }

    public int setTemperatureWhileFatigue(int value) {
        return this.mDiPilotManagerImpl.setTemperatureWhileFatigue(value);
    }

    public int getDistractionDetectionAidState() {
        return this.mDiPilotManagerImpl.getDistractionDetectionAidState();
    }

    public int setDistractionDetectionAidState(int state) {
        return this.mDiPilotManagerImpl.setDistractionDetectionAidState(state);
    }

    public int getAttentionDetectionState() {
        return this.mDiPilotManagerImpl.getAttentionDetectionState();
    }

    public int setAttentionDetectionState(int state) {
        return this.mDiPilotManagerImpl.setAttentionDetectionState(state);
    }

    public int getDangerousActionDetectionState() {
        return this.mDiPilotManagerImpl.getDangerousActionDetectionState();
    }

    public int setDangerousActionDetectionState(int value) {
        return this.mDiPilotManagerImpl.setDangerousActionDetectionState(value);
    }

    public int getGazePerceptionState() {
        return this.mDiPilotManagerImpl.getGazePerceptionState();
    }

    public int setGazePerceptionState(int state) {
        return this.mDiPilotManagerImpl.setGazePerceptionState(state);
    }

    public int getChildCareState() {
        return this.mDiPilotManagerImpl.getChildCareState();
    }

    public int setChildCareState(int state) {
        return this.mDiPilotManagerImpl.setChildCareState(state);
    }

    public int getGestureRecognitionState() {
        return this.mDiPilotManagerImpl.getGestureRecognitionState();
    }

    public int setGestureRecognitionState(int state) {
        return this.mDiPilotManagerImpl.setGestureRecognitionState(state);
    }

    public int getLeftBehindReminderState() {
        return this.mDiPilotManagerImpl.getLeftBehindReminderState();
    }

    public int setLeftBehindReminderState(int state) {
        return this.mDiPilotManagerImpl.setLeftBehindReminderState(state);
    }

    public int getDNPState() {
        return this.mDiPilotManagerImpl.getDNPState();
    }

    public int requestHUNPClose() {
        return this.mDiPilotManagerImpl.requestHUNPClose();
    }

    public int getSupportIntelligentNavigation() {
        return this.mDiPilotManagerImpl.getSupportIntelligentNavigation();
    }

    public int getSupportLaneAssistMode() {
        return this.mDiPilotManagerImpl.getSupportLaneAssistMode();
    }

    public int getSupportShakeAlert() {
        return this.mDiPilotManagerImpl.getSupportShakeAlert();
    }

    public int getSupportTrafficSignsRecognition() {
        return this.mDiPilotManagerImpl.getSupportTrafficSignsRecognition();
    }

    public int getSupportPredictCrashAlert() {
        return this.mDiPilotManagerImpl.getSupportPredictCrashAlert();
    }

    public int getSupportAiEmergencyBrake() {
        return this.mDiPilotManagerImpl.getSupportAiEmergencyBrake();
    }

    public int getSupportAiCare() {
        return this.mDiPilotManagerImpl.getSupportAiCare();
    }

    public int getSupportMistakeGas() {
        return this.mDiPilotManagerImpl.getSupportMistakeGas();
    }

    public int getSupportAutoParking() {
        return this.mDiPilotManagerImpl.getSupportAutoParking();
    }

    public int getSupportBlindSpotAssist() {
        return this.mDiPilotManagerImpl.getSupportBlindSpotAssist();
    }

    public int getSupportRearCrossBrake() {
        return this.mDiPilotManagerImpl.getSupportRearCrossBrake();
    }

    public int getSupportAiDistanceLight() {
        return this.mDiPilotManagerImpl.getSupportAiDistanceLight();
    }

    public int getSupportAiMatrixLight() {
        return this.mDiPilotManagerImpl.getSupportAiMatrixLight();
    }

    public int getSupportPanoramaImage() {
        return this.mDiPilotManagerImpl.getSupportPanoramaImage();
    }

    public int getSupportESP() {
        return this.mDiPilotManagerImpl.getSupportESP();
    }

    public void registerListener(AbsBYDAutoDiPilotListener l) {
        this.mDiPilotManagerImpl.registerListener(l);
    }

    public void registerListener(AbsBYDAutoDiPilotListener listener, int[] featureIds) {
        this.mDiPilotManagerImpl.registerListener(listener, featureIds);
    }

    public void unregisterListener(AbsBYDAutoDiPilotListener l) {
        this.mDiPilotManagerImpl.unregisterListener(l);
    }

    public int getDriveRemindState() {
        return this.mDiPilotManagerImpl.getDriveRemindState();
    }

    public int setDriveRemindState(int state) {
        return this.mDiPilotManagerImpl.setDriveRemindState(state);
    }

    public int getDriveDepartureState() {
        return this.mDiPilotManagerImpl.getDriveDepartureState();
    }

    public int setDriveDeparturState(int state) {
        return this.mDiPilotManagerImpl.setDriveDeparturState(state);
    }

    @Deprecated
    public int getSpeedLimitRange() {
        return this.mDiPilotManagerImpl.getSpeedLimitRange();
    }

    public int setSpeedLimitRange(int value) {
        return this.mDiPilotManagerImpl.setSpeedLimitRange(value);
    }

    @Deprecated
    public int getIntelligentSpeedLimitAlertState() {
        return this.mDiPilotManagerImpl.getIntelligentSpeedLimitAlertState();
    }

    public int setIntelligentSpeedLimitAlertState(int state) {
        return this.mDiPilotManagerImpl.setIntelligentSpeedLimitAlertState(state);
    }

    @Deprecated
    public int getIntelligentSpeedLimitControlState() {
        return this.mDiPilotManagerImpl.getIntelligentSpeedLimitControlState();
    }

    public int setIntelligentSpeedLimitControlState(int state) {
        return this.mDiPilotManagerImpl.setIntelligentSpeedLimitControlState(state);
    }

    public int getVerifyMode() {
        return this.mDiPilotManagerImpl.getVerifyMode();
    }

    public int setVerifyMode(int mode) {
        return this.mDiPilotManagerImpl.setVerifyMode(mode);
    }

    public int getTrafficSignRecognize2D5State() {
        return this.mDiPilotManagerImpl.getTrafficSignRecognize2D5State();
    }

    public int getTrafficSignRecognize2B4State() {
        return this.mDiPilotManagerImpl.getTrafficSignRecognize2B4State();
    }

    public int getTrafficSignRecognize316State() {
        return this.mDiPilotManagerImpl.getTrafficSignRecognize316State();
    }

    public int getSpeedLimitRange2D5() {
        return this.mDiPilotManagerImpl.getSpeedLimitRange2D5();
    }

    public int getSpeedLimitRange2B4() {
        return this.mDiPilotManagerImpl.getSpeedLimitRange2B4();
    }

    public int getIntelligentSpeedLimitAlert2D5State() {
        return this.mDiPilotManagerImpl.getIntelligentSpeedLimitAlert2D5State();
    }

    public int getIntelligentSpeedLimitAlert2B4State() {
        return this.mDiPilotManagerImpl.getIntelligentSpeedLimitAlert2B4State();
    }

    public int getIntelligentSpeedLimitControl2D5State() {
        return this.mDiPilotManagerImpl.getIntelligentSpeedLimitControl2D5State();
    }

    public int getIntelligentSpeedLimitControl32FState() {
        return this.mDiPilotManagerImpl.getIntelligentSpeedLimitControl32FState();
    }

    public int getAiDistanceLight2B4State() {
        return this.mDiPilotManagerImpl.getAiDistanceLight2B4State();
    }

    public int getAiDistanceLight316State() {
        return this.mDiPilotManagerImpl.getAiDistanceLight316State();
    }

    public int getTrafficLightRecognize2D5State() {
        return this.mDiPilotManagerImpl.getTrafficLightRecognize2D5State();
    }

    public int getTrafficLightRecognize2B4State() {
        return this.mDiPilotManagerImpl.getTrafficLightRecognize2B4State();
    }

    public int getEmergencyLaneAssistance32FState() {
        return this.mDiPilotManagerImpl.getEmergencyLaneAssistance32FState();
    }

    public int getEmergencyLaneAssistance2F0State() {
        return this.mDiPilotManagerImpl.getEmergencyLaneAssistance2F0State();
    }

    public int getPredictCrashAlert138Mode() {
        return this.mDiPilotManagerImpl.getPredictCrashAlert138Mode();
    }

    public int getPredictCrashAlert32DMode() {
        return this.mDiPilotManagerImpl.getPredictCrashAlert32DMode();
    }

    public int getAiMatrixLight3ACState() {
        return this.mDiPilotManagerImpl.getAiMatrixLight3ACState();
    }

    public int getAiMatrixLight3ADState() {
        return this.mDiPilotManagerImpl.getAiMatrixLight3ADState();
    }

    public int getBlindMonitorState1() {
        return this.mDiPilotManagerImpl.getBlindMonitorState1();
    }

    public int getBlindMonitorState2() {
        return this.mDiPilotManagerImpl.getBlindMonitorState2();
    }

    public int setCpdStatus(int status) {
        return this.mDiPilotManagerImpl.setCpdStatus(status);
    }

    public int setDmsSwitchStatus(int status) {
        return this.mDiPilotManagerImpl.setDmsSwitchStatus(status);
    }

    public int getCpdStatus() {
        return this.mDiPilotManagerImpl.getCpdStatus();
    }

    @Deprecated
    public int getCpdDelayStatus() {
        return this.mDiPilotManagerImpl.getCpdDelayStatus();
    }

    public int getDmsSwitchStatus() {
        return this.mDiPilotManagerImpl.getDmsSwitchStatus();
    }

    public int getADASOnlineState() {
        return this.mDiPilotManagerImpl.getADASOnlineState();
    }

    public int getADASSoftVersion() {
        return this.mDiPilotManagerImpl.getAdasSoftVersion();
    }

    public int getTSRLitSpdChgSwitchState() {
        return this.mDiPilotManagerImpl.getTsrLitSpdChgSwitchState();
    }

    public int setTSRLitSpdChgSwitchState(int state) {
        return this.mDiPilotManagerImpl.setTsrLitSpdChgSwitchState(state);
    }

    public int getELKTakeOverState() {
        return this.mDiPilotManagerImpl.getElkTakeOverState();
    }

    public int getTSRLitSpdChgInformState() {
        return this.mDiPilotManagerImpl.getTsrLitSpdChgInformState();
    }

    public int getISLIWarningState() {
        return this.mDiPilotManagerImpl.getIsliWarningState();
    }

    public int getLKSFaultState() {
        return this.mDiPilotManagerImpl.getLksFaultState();
    }

    public int getLDWFaultState() {
        return this.mDiPilotManagerImpl.getLdwFaultState();
    }

    public int getAEBFaultState() {
        return this.mDiPilotManagerImpl.getAebFaultState();
    }

    public int getFCWFaultState() {
        return this.mDiPilotManagerImpl.getFcwFaultState();
    }

    public int getVRUFaultState() {
        return this.mDiPilotManagerImpl.getVruFaultState();
    }

    public int getLowSpeedEmergencyBrakingState() {
        return this.mDiPilotManagerImpl.getLowSpeedEmergencyBrakingState();
    }

    public int setLowSpeedEmergencyBrakingState(int state) {
        return this.mDiPilotManagerImpl.setLowSpeedEmergencyBrakingState(state);
    }

    public int getSideDistanceWarningState() {
        return this.mDiPilotManagerImpl.getSideDistanceWarningState();
    }

    public int setSideDistanceWarningState(int state) {
        return this.mDiPilotManagerImpl.setSideDistanceWarningState(state);
    }

    public int getCollisionWarningState() {
        return this.mDiPilotManagerImpl.getCollisionWarningState();
    }

    public int setCollisionWarningState(int state) {
        return this.mDiPilotManagerImpl.setCollisionWarningState(state);
    }

    public int getIntersectionCollisionAlertState() {
        return this.mDiPilotManagerImpl.getIntersectionCollisionAlertState();
    }

    public int setIntersectionCollisionAlertState(int state) {
        return this.mDiPilotManagerImpl.setIntersectionCollisionAlertState(state);
    }

    public int getDoNotPassWarningState() {
        return this.mDiPilotManagerImpl.getDoNotPassWarningState();
    }

    public int setDoNotPassWarningState(int state) {
        return this.mDiPilotManagerImpl.setDoNotPassWarningState(state);
    }

    public int getEmergencyBrakeWarningState() {
        return this.mDiPilotManagerImpl.getEmergencyBrakeWarningState();
    }

    public int setEmergencyBrakeWarningState(int state) {
        return this.mDiPilotManagerImpl.setEmergencyBrakeWarningState(state);
    }

    public int getTrafficIncidentWarningState() {
        return this.mDiPilotManagerImpl.getTrafficIncidentWarningState();
    }

    public int setTrafficIncidentWarningState(int state) {
        return this.mDiPilotManagerImpl.setTrafficIncidentWarningState(state);
    }

    public int getAbnormalVehicleWarningState() {
        return this.mDiPilotManagerImpl.getAbnormalVehicleWarningState();
    }

    public int setAbnormalVehicleWarningState(int state) {
        return this.mDiPilotManagerImpl.setAbnormalVehicleWarningState(state);
    }

    public int getControlLossWarningState() {
        return this.mDiPilotManagerImpl.getControlLossWarningState();
    }

    public int setControlLossWarningState(int state) {
        return this.mDiPilotManagerImpl.setControlLossWarningState(state);
    }

    public int getTrafficJamWarningState() {
        return this.mDiPilotManagerImpl.getTrafficJamWarningState();
    }

    public int setTrafficJamWarningState(int state) {
        return this.mDiPilotManagerImpl.setTrafficJamWarningState(state);
    }

    public int getEmergencyVehicleWarningState() {
        return this.mDiPilotManagerImpl.getEmergencyVehicleWarningState();
    }

    public int setEmergencyVehicleWarningState(int state) {
        return this.mDiPilotManagerImpl.setEmergencyVehicleWarningState(state);
    }

    public int getTrafficSignalWarningState() {
        return this.mDiPilotManagerImpl.getTrafficSignalWarningState();
    }

    public int setTrafficSignalWarningState(int state) {
        return this.mDiPilotManagerImpl.setTrafficSignalWarningState(state);
    }

    public int getTrafficLightInfoPushState() {
        return this.mDiPilotManagerImpl.getTrafficLightInfoPushState();
    }

    public int setTrafficLightInfoPushState(int state) {
        return this.mDiPilotManagerImpl.setTrafficLightInfoPushState(state);
    }

    public int getRedLightViolationWarningState() {
        return this.mDiPilotManagerImpl.getRedLightViolationWarningState();
    }

    public int setRedLightViolationWarningState(int state) {
        return this.mDiPilotManagerImpl.setRedLightViolationWarningState(state);
    }

    public int getGreenLightOptimalSpeedAdvisoryState() {
        return this.mDiPilotManagerImpl.getGreenLightOptimalSpeedAdvisoryState();
    }

    public int setGreenLightOptimalSpeedAdvisoryState(int state) {
        return this.mDiPilotManagerImpl.setGreenLightOptimalSpeedAdvisoryState(state);
    }

    public int setFatigueMonitoringStatus(int status) {
        return this.mDiPilotManagerImpl.setFatigueMonitoringStatus(status);
    }

    public int setDmsSelfCheckStatus(int status) {
        return this.mDiPilotManagerImpl.setDmsSelfCheckStatus(status);
    }

    public int setDistractionMonitoringAttention(int status) {
        return this.mDiPilotManagerImpl.setDistractionMonitoringAttention(status);
    }

    public int getMechanismPosition() {
        return this.mDiPilotManagerImpl.getMechanismPosition();
    }

    public int getDnpStatus() {
        return this.mDiPilotManagerImpl.getDnpStatus();
    }

    public int setFatigueReliefSwitch(int state) {
        return this.mDiPilotManagerImpl.setFatigueReliefSwitch(state);
    }

    public int setDangerousActionfSwitch(int state) {
        return this.mDiPilotManagerImpl.setDangerousActionfSwitch(state);
    }

    public int getBSISEnable() {
        return this.mDiPilotManagerImpl.getBsisEnableR();
    }

    public int getBSISFuncStatus() {
        return this.mDiPilotManagerImpl.getBsisFuncStatusR();
    }

    public int getMOISEnable() {
        return this.mDiPilotManagerImpl.getMoisEnableR();
    }

    public int getMOISFuncStatus() {
        return this.mDiPilotManagerImpl.getMoisFuncStateR();
    }

    public int getSteepDescentSlowDownState4A9() {
        return this.mDiPilotManagerImpl.getSteepDescentSlowDownState4A9R();
    }

    public int setLDWStaticCalibration(int value) {
        return this.mDiPilotManagerImpl.setLdwStaticCalibration(value);
    }

    public int setBSISCollisionAlarmSwitch(int state) {
        return this.mDiPilotManagerImpl.setBsisCollicionAlarmSwitchS(state);
    }

    public int setMOISFunctionSwitch(int state) {
        return this.mDiPilotManagerImpl.setMoisFunctionSwitchS(state);
    }

    public int getLDWSensitivity() {
        return this.mDiPilotManagerImpl.getLdwSensitivity();
    }

    public byte getAdativeCruiseControlFault() {
        return this.mDiPilotManagerImpl.getAdativeCruiseControlFault();
    }

    public byte getLaneCenteringControlFault() {
        return this.mDiPilotManagerImpl.getLaneCenteringControlFault();
    }

    public byte getBlindFault() {
        return this.mDiPilotManagerImpl.getBlindFault();
    }

    public byte getDoorOpenFault() {
        return this.mDiPilotManagerImpl.getDoorOpenFault();
    }

    public byte getRearCrossAlartFault() {
        return this.mDiPilotManagerImpl.getRearCrossAlartFault();
    }

    public byte getRearCrossBrakeFault() {
        return this.mDiPilotManagerImpl.getRearCrossBrakeFault();
    }

    public byte getRearCollisionWarnFault() {
        return this.mDiPilotManagerImpl.getRearCollisionWarnFault();
    }

    public byte getFrontCrossAlarkFault() {
        return this.mDiPilotManagerImpl.getFrontCrossAlarkFault();
    }

    public byte getFrontCrossBrakeFault() {
        return this.mDiPilotManagerImpl.getFrontCrossBrakeFault();
    }

    public byte getInteractiveLaneChangeFault() {
        return this.mDiPilotManagerImpl.getInteractiveLaneChangeFault();
    }

    public byte getNrbanNaviFault() {
        return this.mDiPilotManagerImpl.getNrbanNaviFault();
    }

    public byte getHighNaviFault() {
        return this.mDiPilotManagerImpl.getHighNaviFault();
    }

    public byte getNavigationPilotFault() {
        return this.mDiPilotManagerImpl.getNavigationPilotFault();
    }

    public byte getAutoEmergencyFault() {
        return this.mDiPilotManagerImpl.getAutoEmergencyFault();
    }

    public byte getEmergencyLaneKeepFault() {
        return this.mDiPilotManagerImpl.getEmergencyLaneKeepFault();
    }

    public byte getIntelligentLimitFaultController() {
        return this.mDiPilotManagerImpl.getIntelligentLimitFaultController();
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

    public byte getForwardCollisionWarnFault() {
        return this.mDiPilotManagerImpl.getForwardCollisionWarnFault();
    }

    public byte getAdaptiveFrontLightFault() {
        return this.mDiPilotManagerImpl.getAdaptiveFrontLightFault();
    }

    public byte getTrafficAttentionFault() {
        return this.mDiPilotManagerImpl.getTrafficAttentionFault();
    }

    public byte getTsrFault() {
        return this.mDiPilotManagerImpl.getTsrFault();
    }

    public byte getIntelligentLimitFaultCall() {
        return this.mDiPilotManagerImpl.getIntelligentLimitFaultCall();
    }

    public byte getLimitSpeedChangeFault() {
        return this.mDiPilotManagerImpl.getLimitSpeedChangeFault();
    }

    public byte getCurveWarnFault() {
        return this.mDiPilotManagerImpl.getCurveWarnFault();
    }

    public byte getBSISFault() {
        return this.mDiPilotManagerImpl.getBsisFault();
    }

    public byte getMOISFault() {
        return this.mDiPilotManagerImpl.getMoisFault();
    }

    public int getAutoParkigAssistMode() {
        return this.mDiPilotManagerImpl.getAutoParkigAssistMode();
    }

    public int getAutoParkigAssistStatus() {
        return this.mDiPilotManagerImpl.getAutoParkigAssistStatus();
    }

    public int getHomeZoneParkingAssistStatus() {
        return this.mDiPilotManagerImpl.getHomeZoneParkingAssistStatus();
    }

    public int getAutomatedValetParkingStatus() {
        return this.mDiPilotManagerImpl.getAutomatedValetParkingStatus();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        return this.mDiPilotManagerImpl.get(eventTypes, type);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] eventTypes, BYDAutoEventValue eventValue) {
        return this.mDiPilotManagerImpl.set(eventTypes, eventValue);
    }

    public int getISLIWarningLevelSwitchStatus() {
        return this.mDiPilotManagerImpl.getIsliWarningLevelSwitchStatus();
    }

    public int setISLIWarningLevelSwitchStatus(int status) {
        return this.mDiPilotManagerImpl.setIsliWarningLevelSwitchStatus(status);
    }

    public int getCpdWarningState() {
        return this.mDiPilotManagerImpl.getCpdWarningState();
    }

    public int getCpdWorkingStatus() {
        return this.mDiPilotManagerImpl.getCpdWorkingStatus();
    }

    public int getEspOffKeyStatus() {
        return this.mDiPilotManagerImpl.getEspOffKeyStatus();
    }

    public int getAebDecActive() {
        return this.mDiPilotManagerImpl.getAebDecActive();
    }

    public int getIopState() {
        return this.mDiPilotManagerImpl.getIopState();
    }

    public int setIopState(int state) {
        return this.mDiPilotManagerImpl.setIopState(state);
    }

    public int getSaocState() {
        return this.mDiPilotManagerImpl.getSaocState();
    }

    public int setSaocState(int state) {
        return this.mDiPilotManagerImpl.setSaocState(state);
    }

    public int getSlifState() {
        return this.mDiPilotManagerImpl.getSlifState();
    }

    public int setSlifState(int state) {
        return this.mDiPilotManagerImpl.setSlifState(state);
    }

    public int getACCState() {
        return this.mDiPilotManagerImpl.getAccState();
    }

    public int setACCState(int state) {
        return this.mDiPilotManagerImpl.setAccState(state);
    }

    public int getFCSRState() {
        return this.mDiPilotManagerImpl.getFcsrState();
    }

    public int setFCSRState(int state) {
        return this.mDiPilotManagerImpl.setFcsrState(state);
    }

    public int getFCSRFault() {
        return this.mDiPilotManagerImpl.getFcsrFault();
    }
}

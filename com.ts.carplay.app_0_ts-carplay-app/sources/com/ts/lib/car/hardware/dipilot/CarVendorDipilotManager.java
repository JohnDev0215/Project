package com.ts.lib.car.hardware.dipilot;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes.dex */
public interface CarVendorDipilotManager {
    public static final int ID_ABNORMAL_VEHICLE_WARNING_STATER = 557856865;
    public static final int ID_ABNORMAL_VEHICLE_WARNING_STATES = 557856866;
    public static final int ID_ADAPTIVE_FRONT_LIGHT_FAULT_R = 557856917;
    public static final int ID_ADAS_SOFT_VERSION_R = 557856845;
    public static final int ID_ADATIVE_CRUISE_CONTROL_FAULT_R = 557856897;
    public static final int ID_AEB_FAULT_STATER = 557870469;
    public static final int ID_AI_CARE_STATE_R = 557856796;
    public static final int ID_AI_CARE_STATE_S = 557856797;
    public static final int ID_AI_DISTANCE_LIGHT_STATER_FIRST = 557870472;
    public static final int ID_AI_DISTANCE_LIGHT_STATER_SECOND = 557856804;
    public static final int ID_AI_DISTANCE_LIGHT_STATES_S = 557856805;
    public static final int ID_AI_EMERGENCY_BRAKE_STATE_R = 557856823;
    public static final int ID_AI_EMERGENCY_BRAKE_STATE_S = 557856824;
    public static final int ID_AI_MATRIX_LIGHT_STATE_FIRST = 557856800;
    public static final int ID_AI_MATRIX_LIGHT_STATE_S = 557856802;
    public static final int ID_AI_MATRIX_LIGHT_STATE_SECOND = 557856801;
    public static final int ID_ALARM_TYPE_R = 557856780;
    public static final int ID_ALARM_TYPE_S = 557856781;
    public static final int ID_AUTOMATED_VALET_PARKING_STATUS_R = 557856928;
    public static final int ID_AUTO_EMERGENCY_FAULT_R = 557856910;
    public static final int ID_AUTO_PARKIG_ASSIST_MODE_R = 557856925;
    public static final int ID_AUTO_PARKIG_ASSIST_STATUS_R = 557856926;
    public static final int ID_AUTO_PARKING_STATE_R = 557856931;
    public static final int ID_AUTO_PARKING_STATE_S = 557856932;
    public static final int ID_A_C_C_STATE_R = 557856946;
    public static final int ID_A_C_C_STATE_S = 557856947;
    public static final int ID_A_E_B_DEC_ACTIVE_R = 557870413;
    public static final int ID_A_I_NAVIGATION_STATE_R = 557856776;
    public static final int ID_A_I_NAVIGATION_STATE_S = 557856777;
    public static final int ID_BLIND_FAULT_R = 557856899;
    public static final int ID_BLIND_MONITOR_STATE_FIRST = 557856812;
    public static final int ID_BLIND_MONITOR_STATE_S = 557856814;
    public static final int ID_BLIND_MONITOR_STATE_SECOND = 557856813;
    public static final int ID_BSIS_COLLISION_ALARM_SWITCH_S = 557856890;
    public static final int ID_BSIS_ENABLE_R = 557856889;
    public static final int ID_BSIS_FAULT_R = 557856923;
    public static final int ID_BSIS_FUNC_STATUS_R = 557856891;
    public static final int ID_COLLISION_WARNING_STATER = 557856855;
    public static final int ID_COLLISION_WARNING_STATES = 557856856;
    public static final int ID_CONTROL_LOSS_WARNING_STATER = 557856867;
    public static final int ID_CONTROL_LOSS_WARNING_STATES = 557856868;
    public static final int ID_CPD_DELAY_STATUS_R = 557856842;
    public static final int ID_CPD_STATUS_NEW_R = 557856896;
    public static final int ID_CPD_STATUS_R = 557856840;
    public static final int ID_CPD_STATUS_S = 557856841;
    public static final int ID_CPD_WARNING_STATE_R = 557856933;
    public static final int ID_CPD_WORKING_STATUS_R = 557856934;
    public static final int ID_CURVE_WARN_FAULT_R = 557856922;
    public static final int ID_DANGEROUS_ACTIONF_SWITCHS = 557856886;
    public static final int ID_DISTRACTION_MONITORING_ATTENTIONS = 557856883;
    public static final int ID_DMS_SELF_CHECK_STATUSS = 557856882;
    public static final int ID_DMS_SWITCH_STATUS_R = 557856843;
    public static final int ID_DMS_SWITCH_STATUS_S = 557856844;
    public static final int ID_DNP_STATUSR = 557870437;
    public static final int ID_DOOR_OPEN_FAULT_R = 557856900;
    public static final int ID_DO_NOT_PASS_WARNING_STATER = 557856859;
    public static final int ID_DO_NOT_PASS_WARNING_STATES = 557856860;
    public static final int ID_DRIVING_VOICE_BROADCAST_SWITCH_CONFIG_TWOR = 557870336;
    public static final int ID_ELEC_CARBODY_STABLY_SYS_STATE_CHANGED = 557856838;
    public static final int ID_ELEC_CAR_BODY_STABLY_SYS_STATE_S = 557856834;
    public static final int ID_ELK_TAKE_OVER_STATE_R = 557856848;
    public static final int ID_EMERGENCY_BRAKE_WARNING_STATER = 557856861;
    public static final int ID_EMERGENCY_BRAKE_WARNING_STATES = 557856862;
    public static final int ID_EMERGENCY_LANE_ASSISTANCE_STATE_FIRST = 557856809;
    public static final int ID_EMERGENCY_LANE_ASSISTANCE_STATE_S = 557856811;
    public static final int ID_EMERGENCY_LANE_ASSISTANCE_STATE_SECOND = 557856810;
    public static final int ID_EMERGENCY_LANE_KEEP_FAULT_R = 557856911;
    public static final int ID_EMERGENCY_VEHICLE_WARNING_STATER = 557856871;
    public static final int ID_EMERGENCY_VEHICLE_WARNING_STATES = 557856872;
    public static final int ID_ESP_OFF_KEY_STATUS_R = 557856935;
    public static final int ID_E_E_A_I_ASSISTANCE_STATE_R = 557856772;
    public static final int ID_E_E_A_I_ASSISTANCE_STATE_S = 557856773;
    public static final int ID_FATIGUE_MONITORING_STATUSS = 557856881;
    public static final int ID_FATIGUE_MONITOR_CHANGED_R = 557856888;
    public static final int ID_FATIGUE_RELIEF_SWITCHS = 557856885;
    public static final int ID_FCW_FAULT_STATER = 557870470;
    public static final int ID_FORWARD_COLLISION_WARN_FAULT_R = 557856916;
    public static final int ID_FRONT_CROSS_ALARK_FAULT_R = 557856904;
    public static final int ID_FRONT_CROSS_BRAKE_FAULT_R = 557856905;
    public static final int ID_FRONT_TRAFFIC_BRAKE_ALERT_STATE_R = 557856832;
    public static final int ID_FRONT_TRAFFIC_BRAKE_ALERT_STATE_S = 557856833;
    public static final int ID_FRONT_TRAFFIC_CROSS_ALERT_STATE_R = 557856830;
    public static final int ID_FRONT_TRAFFIC_CROSS_ALERT_STATE_S = 557856831;
    public static final int ID_F_C_S_R_FAULT_R = 557856950;
    public static final int ID_F_C_S_R_STATE_R = 557856948;
    public static final int ID_F_C_S_R_STATE_S = 557856949;
    public static final int ID_GREEN_LIGHT_OPTIMAL_SPEED_ADVISORY_STATER = 557856879;
    public static final int ID_GREEN_LIGHT_OPTIMAL_SPEED_ADVISORY_STATES = 557856880;
    public static final int ID_HIGH_H_O_D_TOAST_CHANGED_R = 557870400;
    public static final int ID_HIGH_I_O_P_TOAST_CHANGED_R = 557856945;
    public static final int ID_HIGH_NAVI_FAULT_R = 557856908;
    public static final int ID_HIGH_SPEED_A_I_NAVIGATION_STATE_R = 557856768;
    public static final int ID_HIGH_SPEED_A_I_NAVIGATION_STATE_S = 557856769;
    public static final int ID_HOME_ZONE_PARKING_ASSIST_STATUS_R = 557856927;
    public static final int ID_INTELLIGENT_LIMIT_FAULT_CALL_R = 557856920;
    public static final int ID_INTELLIGENT_LIMIT_FAULT_CONTROLLER_R = 557856912;
    public static final int ID_INTELLIGENT_SPEED_LIMIT_ALERT_STATE_FIRST = 557856789;
    public static final int ID_INTELLIGENT_SPEED_LIMIT_ALERT_STATE_S = 557856791;
    public static final int ID_INTELLIGENT_SPEED_LIMIT_ALERT_STATE_SECOND = 557856790;
    public static final int ID_INTELLIGENT_SPEED_LIMIT_CONTROL_STATE_FIRST = 557856839;
    public static final int ID_INTELLIGENT_SPEED_LIMIT_CONTROL_STATE_S = 557856793;
    public static final int ID_INTELLIGENT_SPEED_LIMIT_CONTROL_STATE_SECOND = 557856792;
    public static final int ID_INTERACTIVE_LANE_CHANGE_ASSISTANCE_STATE_R = 557856774;
    public static final int ID_INTERACTIVE_LANE_CHANGE_ASSISTANCE_STATE_S = 557856775;
    public static final int ID_INTERACTIVE_LANE_CHANGE_FAULT_R = 557856906;
    public static final int ID_INTERSECTION_COLLISION_ALERT_STATER = 557856857;
    public static final int ID_INTERSECTION_COLLISION_ALERT_STATES = 557856858;
    public static final int ID_ISLI_WARNING_STATE_R = 557856850;
    public static final int ID_I_O_P_STATE_R = 557856936;
    public static final int ID_I_O_P_STATE_S = 557856937;
    public static final int ID_I_S_L_I_WARNING_LEVEL_SWITCH_STATUS_R = 557856929;
    public static final int ID_I_S_L_I_WARNING_LEVEL_SWITCH_STATUS_S = 557856930;
    public static final int ID_LANE_ASSIST_TYPE_R = 557856778;
    public static final int ID_LANE_ASSIST_TYPE_S = 557856779;
    public static final int ID_LANE_CENTERING_CONTROL_FAULT_R = 557856898;
    public static final int ID_LANE_DEPARTURE_PREVENTION_FAULT_R = 557856913;
    public static final int ID_LANE_DEPARTURE_WARN_FAULT_R = 557856914;
    public static final int ID_LANE_KEEP_FAULT_R = 557856915;
    public static final int ID_LDW_FAULT_STATER = 557870416;
    public static final int ID_LDW_STATIC_CALIBRATION_S = 557856887;
    public static final int ID_LIMIT_SPEED_CHANGE_FAULT_R = 557856921;
    public static final int ID_LKS_FAULT_STATER = 557870415;
    public static final int ID_LOW_H_O_D_TOAST_CHANGED_R = 557845271;
    public static final int ID_LOW_I_O_P_TOAST_CHANGED_R = 557856944;
    public static final int ID_LOW_SPEED_EMERGENCY_BRAKING_STATER = 557856851;
    public static final int ID_LOW_SPEED_EMERGENCY_BRAKING_STATES = 557856852;
    public static final int ID_L_D_W_SENSITIVITY_R = 557872897;
    public static final int ID_MECHANISM_POSITIONR = 557856884;
    public static final int ID_MISTAKE_GAS_STATE_R = 557856798;
    public static final int ID_MISTAKE_GAS_STATE_S = 557856799;
    public static final int ID_MOIS_ENABLE_R = 557856892;
    public static final int ID_MOIS_FAULT_R = 557856924;
    public static final int ID_MOIS_FUNCTION_SWITCH_S = 557856893;
    public static final int ID_MOIS_FUNC_STATUS_R = 557856894;
    public static final int ID_NAVIGATION_PILOT_FAULT_R = 557856909;
    public static final int ID_NRBAN_NAVI_FAULT_R = 557856907;
    public static final int ID_OPEN_DOOR_ALERT_STATE_R = 557856815;
    public static final int ID_OPEN_DOOR_ALERT_STATE_S = 557856816;
    public static final int ID_PREDICT_CRASH_ALERT_MODE_FIRST = 557856827;
    public static final int ID_PREDICT_CRASH_ALERT_MODE_S = 557856829;
    public static final int ID_PREDICT_CRASH_ALERT_MODE_SECOND = 557856828;
    public static final int ID_PREDICT_CRASH_ALERT_STATE_R = 557856825;
    public static final int ID_PREDICT_CRASH_ALERT_STATE_S = 557856826;
    public static final int ID_REAR_COLLISION_WARN_FAULT_R = 557856903;
    public static final int ID_REAR_CRASH_ALERT_STATE_R = 557856817;
    public static final int ID_REAR_CRASH_ALERT_STATE_S = 557856818;
    public static final int ID_REAR_CROSS_ALART_FAULT_R = 557856901;
    public static final int ID_REAR_CROSS_BRAKE_FAULT_R = 557856902;
    public static final int ID_REAR_TRAFFIC_BRAKE_ALERT_STATE_R = 557856821;
    public static final int ID_REAR_TRAFFIC_BRAKE_ALERT_STATE_S = 557856822;
    public static final int ID_REAR_TRAFFIC_CROSS_ALERT_STATE_R = 557856819;
    public static final int ID_REAR_TRAFFIC_CROSS_ALERT_STATE_S = 557856820;
    public static final int ID_RED_LIGHT_VIOLATION_WARNING_STATER = 557856877;
    public static final int ID_RED_LIGHT_VIOLATION_WARNING_STATES = 557856878;
    public static final int ID_REMOTE_CONTROL_DRIVE_CLOSE_TOPS = 557845261;
    public static final int ID_SIDE_DISTANCE_WARNING_STATER = 557856853;
    public static final int ID_SIDE_DISTANCE_WARNING_STATES = 557856854;
    public static final int ID_SPEED_LIMIT_RANGE_FIRST = 557856786;
    public static final int ID_SPEED_LIMIT_RANGE_S = 557856788;
    public static final int ID_SPEED_LIMIT_RANGE_SECOND = 557856787;
    public static final int ID_STEEP_DESCENT_SLOW_DOWN_STATE_4A9_R = 557856895;
    public static final int ID_STEEP_DESCENT_SLOW_DOWN_STATE_R = 557856835;
    public static final int ID_STEEP_DESCENT_SLOW_DOWN_STATE_S = 557856836;
    public static final int ID_SUPPORT_AI_DISTANCE_LIGHT = 557856106;
    public static final int ID_SUPPORT_AI_EMERGENCY_BRAKE = 557856099;
    public static final int ID_SUPPORT_EMERGENCY_LANE_KEEP_ASSIST = 557856086;
    public static final int ID_SUPPORT_END_TO_END_NAVIGATION = 557856081;
    public static final int ID_SUPPORT_FRONT_CROSSING_TRAFFIC_ALERT = 557856094;
    public static final int ID_SUPPORT_FRONT_CROSSING_TRAFFIC_BRAKE = 557856095;
    public static final int ID_SUPPORT_HIGHWAY_NAVIGATION = 557856079;
    public static final int ID_SUPPORT_INTELLIGENT_NAVIGATION_FIRST = 557856077;
    public static final int ID_SUPPORT_INTELLIGENT_NAVIGATION_SECOND = 557856078;
    public static final int ID_SUPPORT_INTELLIGNET_SPEED_LIMIT_ALERT = 557856088;
    public static final int ID_SUPPORT_INTELLIGNET_SPEED_LIMIT_CONTROL = 557856089;
    public static final int ID_SUPPORT_LANE_CHANGE_ASSIST = 557856082;
    public static final int ID_SUPPORT_LANE_DEPARTURE_STOP = 557856085;
    public static final int ID_SUPPORT_LANE_DEPARTURE_WARNING = 557856083;
    public static final int ID_SUPPORT_LANE_KEEP = 557856084;
    public static final int ID_SUPPORT_PREDICT_CRASH_ALERT_MODE_FIRST = 557856097;
    public static final int ID_SUPPORT_PREDICT_CRASH_ALERT_MODE_SECOND = 557856098;
    public static final int ID_SUPPORT_TRAFFIC_LIGHT_ATTENTION = 557856090;
    public static final int ID_SUPPORT_TRAFFIC_SIGN_RECOGNITON = 557856087;
    public static final int ID_SUPPORT_URBAN_NAVIGATION = 557856080;
    public static final int ID_S_A_O_C_STATE_R = 557856938;
    public static final int ID_S_A_O_C_STATE_S = 557856939;
    public static final int ID_S_L_I_F_STATE_R = 557856940;
    public static final int ID_S_L_I_F_STATE_S = 557856941;
    public static final int ID_TRAFFIC_ATTENTION_FAULT_R = 557856918;
    public static final int ID_TRAFFIC_INCIDENT_WARNING_STATER = 557856863;
    public static final int ID_TRAFFIC_INCIDENT_WARNING_STATES = 557856864;
    public static final int ID_TRAFFIC_JAM_WARNING_STATER = 557856869;
    public static final int ID_TRAFFIC_JAM_WARNING_STATES = 557856870;
    public static final int ID_TRAFFIC_LIGHT_INFO_PUSH_STATER = 557856875;
    public static final int ID_TRAFFIC_LIGHT_INFO_PUSH_STATES = 557856876;
    public static final int ID_TRAFFIC_LIGHT_RECOGNIZE_STATE_FIRST = 557856806;
    public static final int ID_TRAFFIC_LIGHT_RECOGNIZE_STATE_S = 557856808;
    public static final int ID_TRAFFIC_LIGHT_RECOGNIZE_STATE_SECOND = 557870505;
    public static final int ID_TRAFFIC_SIGNAL_WARNING_STATER = 557856873;
    public static final int ID_TRAFFIC_SIGNAL_WARNING_STATES = 557856874;
    public static final int ID_TRAFFIC_SIGN_RECOGNIZE_STATE_FIRST = 557856782;
    public static final int ID_TRAFFIC_SIGN_RECOGNIZE_STATE_R = 557856784;
    public static final int ID_TRAFFIC_SIGN_RECOGNIZE_STATE_S = 557856785;
    public static final int ID_TRAFFIC_SIGN_RECOGNIZE_STATE_SECOND = 557856783;
    public static final int ID_TSR_FAULT_R = 557856919;
    public static final int ID_TSR_LIT_SPD_CHG_INFORM_STATE_R = 557856849;
    public static final int ID_TSR_LIT_SPD_CHG_SWITCH_STATE_R = 557856846;
    public static final int ID_TSR_LIT_SPD_CHG_SWITCH_STATE_S = 557856847;
    public static final int ID_URBAN_A_I_ASSISTANCE_STATE_R = 557856770;
    public static final int ID_URBAN_A_I_ASSISTANCE_STATE_S = 557856771;
    public static final int ID_VERIFY_MODE_R = 557856794;
    public static final int ID_VERIFY_MODE_S = 557856795;
    public static final int ID_VRU_FAULT_STATER = 557870471;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.dipilot";
    public static final String TAG = "CAR.VENDOR.DIPILOT";

    public interface OnCarDipilotEventListener {
        void onDipilotErrorEvent(int i, int i2);

        void onDipilotEventChange(DipilotEventChangeValue dipilotEventChangeValue);
    }

    void addCarDipilotEventListener(OnCarDipilotEventListener onCarDipilotEventListener) throws CarNotConnectedException;

    int getAEBDecActiveR() throws CarNotConnectedException;

    int getAINavigationStateR() throws CarNotConnectedException;

    int getAbnormalVehicleWarningStateR() throws CarNotConnectedException;

    int getAccStateR() throws CarNotConnectedException;

    int getAdaptiveFrontLightFaultR() throws CarNotConnectedException;

    int getAdasSoftVersionR() throws CarNotConnectedException;

    int getAdativeCruiseControlFaultR() throws CarNotConnectedException;

    int getAebFaultStateR() throws CarNotConnectedException;

    int getAiCareStateR() throws CarNotConnectedException;

    int getAiDistanceLightStateFirst() throws CarNotConnectedException;

    int getAiDistanceLightStateSecond() throws CarNotConnectedException;

    int getAiEmergencyBrakeStateR() throws CarNotConnectedException;

    int getAiMatrixLightStateFirst() throws CarNotConnectedException;

    int getAiMatrixLightStateSecond() throws CarNotConnectedException;

    int getAlarmTypeR() throws CarNotConnectedException;

    int getAutoEmergencyFaultR() throws CarNotConnectedException;

    int getAutoParkigAssistModeR() throws CarNotConnectedException;

    int getAutoParkigAssistStatusR() throws CarNotConnectedException;

    int getAutoParkingStateR() throws CarNotConnectedException;

    int getAutomatedValetParkingStatusR() throws CarNotConnectedException;

    int getBlindFaultR() throws CarNotConnectedException;

    int getBlindMonitorStateFirst() throws CarNotConnectedException;

    int getBlindMonitorStateSecond() throws CarNotConnectedException;

    int getBsisEnableR() throws CarNotConnectedException;

    int getBsisFaultR() throws CarNotConnectedException;

    int getBsisFuncStatusR() throws CarNotConnectedException;

    int getCollisionWarningStateR() throws CarNotConnectedException;

    int getControlLossWarningStateR() throws CarNotConnectedException;

    int getCpdDelayStatusR() throws CarNotConnectedException;

    int getCpdStatusNewR() throws CarNotConnectedException;

    int getCpdStatusR() throws CarNotConnectedException;

    int getCpdWarningStateR() throws CarNotConnectedException;

    int getCpdWorkingStatusR() throws CarNotConnectedException;

    int getCurveWarnFaultR() throws CarNotConnectedException;

    int getDmsSwitchStatusR() throws CarNotConnectedException;

    int getDnpStatusR() throws CarNotConnectedException;

    int getDoNotPassWarningStateR() throws CarNotConnectedException;

    int getDoorOpenFaultR() throws CarNotConnectedException;

    int getE2EAIAssistanceStateR() throws CarNotConnectedException;

    int getElecCarBodyStablySysStateChanged() throws CarNotConnectedException;

    int getElkTakeOverStateR() throws CarNotConnectedException;

    int getEmergencyBrakeWarningStateR() throws CarNotConnectedException;

    int getEmergencyLaneAssistanceStateFirst() throws CarNotConnectedException;

    int getEmergencyLaneAssistanceStateSecond() throws CarNotConnectedException;

    int getEmergencyLaneKeepFaultR() throws CarNotConnectedException;

    int getEmergencyVehicleWarningStateR() throws CarNotConnectedException;

    int getEspOffKeyStatusR() throws CarNotConnectedException;

    int getFcsrFaultR() throws CarNotConnectedException;

    int getFcsrStateR() throws CarNotConnectedException;

    int getFcwFaultStateR() throws CarNotConnectedException;

    int getForwardCollisionWarnFaultR() throws CarNotConnectedException;

    int getFrontCrossAlarkFaultR() throws CarNotConnectedException;

    int getFrontCrossBrakeFaultR() throws CarNotConnectedException;

    int getFrontTrafficBrakeAlertStateR() throws CarNotConnectedException;

    int getFrontTrafficCrossAlertStateR() throws CarNotConnectedException;

    int getGreenLightOptimalSpeedAdvisoryStateR() throws CarNotConnectedException;

    int getHighHODToastR() throws CarNotConnectedException;

    int getHighIOPToastR() throws CarNotConnectedException;

    int getHighNaviFaultR() throws CarNotConnectedException;

    int getHighSpeedAINavigationStateR() throws CarNotConnectedException;

    int getHomeZoneParkingAssistStatusR() throws CarNotConnectedException;

    int getIntelligentLimitFaultCallR() throws CarNotConnectedException;

    int getIntelligentLimitFaultControllerR() throws CarNotConnectedException;

    int getIntelligentSpeedLimitAlertStateFirst() throws CarNotConnectedException;

    int getIntelligentSpeedLimitAlertStateSecond() throws CarNotConnectedException;

    int getIntelligentSpeedLimitControlStateFirst() throws CarNotConnectedException;

    int getIntelligentSpeedLimitControlStateSecond() throws CarNotConnectedException;

    int getInteractiveLaneChangeAssistanceStateR() throws CarNotConnectedException;

    int getInteractiveLaneChangeFaultR() throws CarNotConnectedException;

    int getIntersectionCollisionAlertStateR() throws CarNotConnectedException;

    int getIopStateR() throws CarNotConnectedException;

    int getIsliWarningLevelSwitchStatusR() throws CarNotConnectedException;

    int getIsliWarningStateR() throws CarNotConnectedException;

    int getLaneAssistTypeR() throws CarNotConnectedException;

    int getLaneCenteringControlFaultR() throws CarNotConnectedException;

    int getLaneDeparturePreventionFaultR() throws CarNotConnectedException;

    int getLaneDepartureWarnFaultR() throws CarNotConnectedException;

    int getLaneKeepFaultR() throws CarNotConnectedException;

    int getLdwFaultStateR() throws CarNotConnectedException;

    int getLdwSensitivityR() throws CarNotConnectedException;

    int getLimitSpeedChangeFaultR() throws CarNotConnectedException;

    int getLksFaultStateR() throws CarNotConnectedException;

    int getLowHODToastR() throws CarNotConnectedException;

    int getLowIOPToastR() throws CarNotConnectedException;

    int getLowSpeedEmergencyBrakingStateR() throws CarNotConnectedException;

    int getMechanismPositionR() throws CarNotConnectedException;

    int getMistakeGasStateR() throws CarNotConnectedException;

    int getMoisEnableR() throws CarNotConnectedException;

    int getMoisFaultR() throws CarNotConnectedException;

    int getMoisFuncStateR() throws CarNotConnectedException;

    int getNavigationPilotFaultR() throws CarNotConnectedException;

    int getNrbanNaviFaultR() throws CarNotConnectedException;

    int getOpenDoorAlertStateR() throws CarNotConnectedException;

    int getPredictCrashAlertModeRFirst() throws CarNotConnectedException;

    int getPredictCrashAlertModeRSecond() throws CarNotConnectedException;

    int getPredictCrashAlertStateR() throws CarNotConnectedException;

    int getRearCollisionWarnFaultR() throws CarNotConnectedException;

    int getRearCrashAlertStateR() throws CarNotConnectedException;

    int getRearCrossAlartFaultR() throws CarNotConnectedException;

    int getRearCrossBrakeFaultR() throws CarNotConnectedException;

    int getRearTrafficBrakeAlertStateR() throws CarNotConnectedException;

    int getRearTrafficCrossAlertStateR() throws CarNotConnectedException;

    int getRedLightViolationWarningStateR() throws CarNotConnectedException;

    int getRemoteControlDriveCloseTops() throws CarNotConnectedException;

    int getSaocStateR() throws CarNotConnectedException;

    int getSideDistanceWarningStateR() throws CarNotConnectedException;

    int getSlifStateR() throws CarNotConnectedException;

    int getSpeedLimitRangeFirst() throws CarNotConnectedException;

    int getSpeedLimitRangeSecond() throws CarNotConnectedException;

    int getSteepDescentSlowDownState4A9R() throws CarNotConnectedException;

    int getSteepDescentSlowDownStateR() throws CarNotConnectedException;

    int getTrafficAttentionFaultR() throws CarNotConnectedException;

    int getTrafficIncidentWarningStateR() throws CarNotConnectedException;

    int getTrafficJamWarningStateR() throws CarNotConnectedException;

    int getTrafficLightInfoPushStateR() throws CarNotConnectedException;

    int getTrafficLightRecognizeStateFirst() throws CarNotConnectedException;

    int getTrafficLightRecognizeStateSecond() throws CarNotConnectedException;

    int getTrafficSignRecognizeStateFirst() throws CarNotConnectedException;

    int getTrafficSignRecognizeStateR() throws CarNotConnectedException;

    int getTrafficSignRecognizeStateSecond() throws CarNotConnectedException;

    int getTrafficSignalWarningStateR() throws CarNotConnectedException;

    int getTsrFaultR() throws CarNotConnectedException;

    int getTsrLitSpdChgInformStateR() throws CarNotConnectedException;

    int getTsrLitSpdChgSwitchStateR() throws CarNotConnectedException;

    int getUrbanAIAssistanceStateR() throws CarNotConnectedException;

    int getVerifyModeR() throws CarNotConnectedException;

    int getVruFaultStateR() throws CarNotConnectedException;

    void removeCarDipilotEventListener(OnCarDipilotEventListener onCarDipilotEventListener) throws CarNotConnectedException;

    void setAINavigationStateS(int i) throws CarNotConnectedException;

    void setAbnormalVehicleWarningStateS(int i) throws CarNotConnectedException;

    void setAccStateS(int i) throws CarNotConnectedException;

    void setAiCareStateS(int i) throws CarNotConnectedException;

    void setAiDistanceLightStateS(int i) throws CarNotConnectedException;

    void setAiEmergencyBrakeStateS(int i) throws CarNotConnectedException;

    void setAiMatrixLightStateS(int i) throws CarNotConnectedException;

    void setAlarmTypeS(int i) throws CarNotConnectedException;

    void setAutoParkingStateS(int i) throws CarNotConnectedException;

    void setBlindMonitorStateS(int i) throws CarNotConnectedException;

    void setBsisCollicionAlarmSwitchS(int i) throws CarNotConnectedException;

    void setCollisionWarningStateS(int i) throws CarNotConnectedException;

    void setControlLossWarningStateS(int i) throws CarNotConnectedException;

    void setCpdStatusS(int i) throws CarNotConnectedException;

    void setDangerousActionfSwitchS(int i) throws CarNotConnectedException;

    void setDistractionMonitoringAttentionS(int i) throws CarNotConnectedException;

    void setDmsSelfCheckStatusS(int i) throws CarNotConnectedException;

    void setDmsSwitchStatusS(int i) throws CarNotConnectedException;

    void setDoNotPassWarningStateS(int i) throws CarNotConnectedException;

    void setE2EAIAssistanceStateS(int i) throws CarNotConnectedException;

    void setElecCarBodyStablySysStateS(int i) throws CarNotConnectedException;

    void setEmergencyBrakeWarningStateS(int i) throws CarNotConnectedException;

    void setEmergencyLaneAssistanceStateS(int i) throws CarNotConnectedException;

    void setEmergencyVehicleWarningStateS(int i) throws CarNotConnectedException;

    void setFatigueMonitoringStatusS(int i) throws CarNotConnectedException;

    void setFatigueReliefSwitchS(int i) throws CarNotConnectedException;

    void setFcsrStateS(int i) throws CarNotConnectedException;

    void setFrontTrafficBrakeAlertStateS(int i) throws CarNotConnectedException;

    void setFrontTrafficCrossAlertStateS(int i) throws CarNotConnectedException;

    void setGreenLightOptimalSpeedAdvisoryStateS(int i) throws CarNotConnectedException;

    void setHighSpeedAINavigationStateS(int i) throws CarNotConnectedException;

    void setIntelligentSpeedLimitAlertStateS(int i) throws CarNotConnectedException;

    void setIntelligentSpeedLimitControlStateS(int i) throws CarNotConnectedException;

    void setInteractiveLaneChangeAssistanceStateS(int i) throws CarNotConnectedException;

    void setIntersectionCollisionAlertStateS(int i) throws CarNotConnectedException;

    void setIopStateS(int i) throws CarNotConnectedException;

    void setIsliWarningLevelSwitchStatusS(int i) throws CarNotConnectedException;

    void setLaneAssistTypeS(int i) throws CarNotConnectedException;

    void setLdwStaticCalibrationS(int i) throws CarNotConnectedException;

    void setLowSpeedEmergencyBrakingStateS(int i) throws CarNotConnectedException;

    void setMistakeGasStateS(int i) throws CarNotConnectedException;

    void setMoisFunctionSwitchS(int i) throws CarNotConnectedException;

    void setOpenDoorAlertStateS(int i) throws CarNotConnectedException;

    void setPredictCrashAlertModeS(int i) throws CarNotConnectedException;

    void setPredictCrashAlertStateS(int i) throws CarNotConnectedException;

    void setRearCrashAlertStateS(int i) throws CarNotConnectedException;

    void setRearTrafficBrakeAlertStateS(int i) throws CarNotConnectedException;

    void setRearTrafficCrossAlertStateS(int i) throws CarNotConnectedException;

    void setRedLightViolationWarningStateS(int i) throws CarNotConnectedException;

    void setSaocStateS(int i) throws CarNotConnectedException;

    void setSideDistanceWarningStateS(int i) throws CarNotConnectedException;

    void setSlifStateS(int i) throws CarNotConnectedException;

    void setSpeedLimitRangeS(int i) throws CarNotConnectedException;

    void setSteepDescentSlowDownStateS(int i) throws CarNotConnectedException;

    void setTrafficIncidentWarningStateS(int i) throws CarNotConnectedException;

    void setTrafficJamWarningStateS(int i) throws CarNotConnectedException;

    void setTrafficLightInfoPushStateS(int i) throws CarNotConnectedException;

    void setTrafficLightRecognizeStateS(int i) throws CarNotConnectedException;

    void setTrafficSignRecognizeStateS(int i) throws CarNotConnectedException;

    void setTrafficSignalWarningStateS(int i) throws CarNotConnectedException;

    void setTsrLitSpdChgSwitchStateS(int i) throws CarNotConnectedException;

    void setUrbanAIAssistanceStateS(int i) throws CarNotConnectedException;

    void setVerifyModeS(int i) throws CarNotConnectedException;
}

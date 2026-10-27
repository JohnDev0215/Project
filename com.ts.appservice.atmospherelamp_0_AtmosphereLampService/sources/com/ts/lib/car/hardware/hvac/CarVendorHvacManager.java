package com.ts.lib.car.hardware.hvac;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes3.dex */
public interface CarVendorHvacManager {
    public static final int ID_ACBT_WIND_TIPS = 557846611;
    public static final int ID_AC_ANTIBACTERIAL_CONTROL_STATES = 557846656;
    public static final int ID_AC_ASSIST_MODE_TIPS = 557846609;
    public static final int ID_AC_AUTOMATIC_BUTTON_TURNED_OFF = 557846546;
    public static final int ID_AC_A_Q_S_CONFIG_R = 557846704;
    public static final int ID_AC_CIRCLE_BUTTON = 557846554;
    public static final int ID_AC_CIRCLE_MODE = 557846553;
    public static final int ID_AC_CLOSE_BUTTON = 557846551;
    public static final int ID_AC_COCKPIT_PURIFICATION_STATUS_R = 557846705;
    public static final int ID_AC_COMPRESSOR_MANUAL_SIGN = 557846590;
    public static final int ID_AC_COMPRESSOR_MODE = 557846561;
    public static final int ID_AC_CONTROLLER_DRIVER_TEMP_SET = 557846563;
    public static final int ID_AC_CONTROLLER_MAX_COLD_STATE = 557846571;
    public static final int ID_AC_CONTROLLER_REAR_AC_CTRL_MODE = 557846578;
    public static final int ID_AC_CONTROLLER_REAR_LOCK = 557846588;
    public static final int ID_AC_CONTROLLER_REAR_STATE = 557846580;
    public static final int ID_AC_CONTROLLER_REAR_TEMP = 557846584;
    public static final int ID_AC_CONTROLLER_REAR_WIND_LEVEL = 557846586;
    public static final int ID_AC_CONTROLLER_REAR_WIND_MODE = 557846582;
    public static final int ID_AC_CONTROLLER_WIND_LEVEL = 557846567;
    public static final int ID_AC_CONTROLLER_WIND_MODE = 557846569;
    public static final int ID_AC_DEFROST = 557846549;
    public static final int ID_AC_DEFROST_FRONT_STATE = 557846550;
    public static final int ID_AC_DEFROST_OPEN_STATE_LEFT = 557846613;
    public static final int ID_AC_DEFROST_REAR_PANEL_NOT_AVAILABLE_TIPS = 557846612;
    public static final int ID_AC_DUAL_CHANNEL_PM25_FLAG = 557846539;
    public static final int ID_AC_DUAL_MODE = 557846562;
    public static final int ID_AC_FAULT_NUM_SHOWN_STATE = 557846542;
    public static final int ID_AC_FRONT_CTRL_MODE = 557846548;
    public static final int ID_AC_HAS_PERFUME_CONFIGURATION = 557846545;
    public static final int ID_AC_HEATING_BUTTON_CONFIG_R = 557846664;
    public static final int ID_AC_HEATING_STATE_R = 557846665;
    public static final int ID_AC_HEATING_STATE_S = 557846666;
    public static final int ID_AC_HEAT_MODE_FLAG = 557846557;
    public static final int ID_AC_HEAT_PUMP_COMPRESSOR_MODE = 557846558;
    public static final int ID_AC_HIGH_TEMP_STERILIZATION_FALG = 557846541;
    public static final int ID_AC_KEEP_ENGINE_RUN_TIPS = 557846658;
    public static final int ID_AC_MEDIA_DEFROST_BUTTON = 557846575;
    public static final int ID_AC_MEDIA_ION_STATE = 557846574;
    public static final int ID_AC_MEDIA_REAR_PANEL_LOCK = 557846577;
    public static final int ID_AC_NATURAL_WIND = 557846560;
    public static final int ID_AC_NATURAL_WIND_STATE = 557846559;
    public static final int ID_AC_OPEN_STATE = 557846552;
    public static final int ID_AC_PAD_DRIVER_TEMP_SET = 557846564;
    public static final int ID_AC_PAD_FRONT_WIND_MODE = 557846570;
    public static final int ID_AC_PAD_PASSENGER_TEMP_SET = 557846566;
    public static final int ID_AC_PAD_REAR_CTRL_MODE = 557846579;
    public static final int ID_AC_PAD_REAR_LOCK = 557846589;
    public static final int ID_AC_PAD_REAR_STATE = 557846581;
    public static final int ID_AC_PAD_REAR_TEMP = 557846585;
    public static final int ID_AC_PAD_REAR_WIND_LEVEL = 557846587;
    public static final int ID_AC_PAD_REAR_WIND_MODE = 557846583;
    public static final int ID_AC_PAD_WIND_LEVEL = 557846568;
    public static final int ID_AC_PROMPT_BOX_SHOWN_STATE_R = 557846657;
    public static final int ID_AC_PTC_PREHEAT_SIGNALR = 557846601;
    public static final int ID_AC_QUICK_CLEAN_AIR_STATER = 557846606;
    public static final int ID_AC_QUICK_CLEAN_AIR_STATES = 557846605;
    public static final int ID_AC_REAR_AC_BUTTON = 557846531;
    public static final int ID_AC_REAR_AC_FUNCTION = 557846536;
    public static final int ID_AC_REAR_AC_TEMP_ADJUST = 557846537;
    public static final int ID_AC_REAR_BUTTON_LOCK = 557846533;
    public static final int ID_AC_REAR_CTRL_MODE = 557846547;
    public static final int ID_AC_REAR_PANEL_LOCK = 557846576;
    public static final int ID_AC_REAR_WIND_AUTO_ADJUST = 557846535;
    public static final int ID_AC_REAR_WIND_LEVEL = 557846532;
    public static final int ID_AC_REAR_WIND_LEVEL_ADJUST = 557846538;
    public static final int ID_AC_REAR_WIND_MODE_ADJUST = 557846534;
    public static final int ID_AC_REMOTE_CTRL_TIMER = 557856063;
    public static final int ID_AC_REMOTE_CTRL_TIMES = 557846600;
    public static final int ID_AC_TEMPCTRL_SEPARATE_STATE = 557846543;
    public static final int ID_AC_TEMPERATURE_MID_LEFT_S = 557846662;
    public static final int ID_AC_TEMPERATURE_MID_RIGHT_S = 557842695;
    public static final int ID_AC_TEMPRATURE_OUT = 557846555;
    public static final int ID_AC_TEMP_DEPUTY = 557846603;
    public static final int ID_AC_TURN_ON = 557846556;
    public static final int ID_AC_TUYERE_SWITCH_STATE_COPILOT_LEFT_R = 557846698;
    public static final int ID_AC_TUYERE_SWITCH_STATE_COPILOT_LEFT_S = 557846702;
    public static final int ID_AC_TUYERE_SWITCH_STATE_COPILOT_RIGHT_R = 557846699;
    public static final int ID_AC_TUYERE_SWITCH_STATE_COPILOT_RIGHT_S = 557846703;
    public static final int ID_AC_TUYERE_SWITCH_STATE_MAIN_LEFT_R = 557846696;
    public static final int ID_AC_TUYERE_SWITCH_STATE_MAIN_LEFT_S = 557846700;
    public static final int ID_AC_TUYERE_SWITCH_STATE_MAIN_RIGHT_R = 557846697;
    public static final int ID_AC_TUYERE_SWITCH_STATE_MAIN_RIGHT_S = 557846701;
    public static final int ID_AC_TYPE = 557846529;
    public static final int ID_AC_WINDMODE_NUMR = 557846602;
    public static final int ID_AC_WIND_LEVEL_MANUAL_SIGN = 557846591;
    public static final int ID_AC_WIND_MODE_MANUAL_SIGN = 557846592;
    public static final int ID_AC_WORK_MODE_COPILOT_R = 557846635;
    public static final int ID_AC_WORK_MODE_DRIVER_R = 557846634;
    public static final int ID_AIR_OUTLET_STATE_COPILOT_R = 557846631;
    public static final int ID_AIR_OUTLET_STATE_COPILOT_S = 557846653;
    public static final int ID_AIR_OUTLET_STATE_DRIVER_R = 557846630;
    public static final int ID_AIR_OUTLET_STATE_DRIVER_S = 557846652;
    public static final int ID_AIR_OUTLET_VALUE_COPILOT_LEFT_HORIZONTAL_R = 559943771;
    public static final int ID_AIR_OUTLET_VALUE_COPILOT_LEFT_HORIZONTAL_S = 557846642;
    public static final int ID_AIR_OUTLET_VALUE_COPILOT_LEFT_VERTICAL_R = 559943770;
    public static final int ID_AIR_OUTLET_VALUE_COPILOT_LEFT_VERTICAL_S = 557846640;
    public static final int ID_AIR_OUTLET_VALUE_COPILOT_RIGHT_HORIZONTAL_R = 559943773;
    public static final int ID_AIR_OUTLET_VALUE_COPILOT_RIGHT_HORIZONTAL_S = 557846643;
    public static final int ID_AIR_OUTLET_VALUE_COPILOT_RIGHT_VERTICAL_R = 559943772;
    public static final int ID_AIR_OUTLET_VALUE_COPILOT_RIGHT_VERTICAL_S = 557846641;
    public static final int ID_AIR_OUTLET_VALUE_DRIVER_LEFT_HORIZONTAL_R = 559943767;
    public static final int ID_AIR_OUTLET_VALUE_DRIVER_LEFT_HORIZONTAL_S = 557846638;
    public static final int ID_AIR_OUTLET_VALUE_DRIVER_LEFT_VERTICAL_R = 559943766;
    public static final int ID_AIR_OUTLET_VALUE_DRIVER_LEFT_VERTICAL_S = 557846636;
    public static final int ID_AIR_OUTLET_VALUE_DRIVER_RIGHT_HORIZONTAL_R = 559943769;
    public static final int ID_AIR_OUTLET_VALUE_DRIVER_RIGHT_HORIZONTAL_S = 557846639;
    public static final int ID_AIR_OUTLET_VALUE_DRIVER_RIGHT_VERTICAL_R = 559943768;
    public static final int ID_AIR_OUTLET_VALUE_DRIVER_RIGHT_VERTICAL_S = 557846637;
    public static final int ID_AUTO_CLEAN_AIR_STATER = 557846608;
    public static final int ID_AUTO_CLEAN_AIR_STATES = 557846598;
    public static final int ID_AVOID_BLOW_PEOPLE_STATE_COPILOT_R = 557846627;
    public static final int ID_AVOID_BLOW_PEOPLE_STATE_COPILOT_S = 557846647;
    public static final int ID_AVOID_BLOW_PEOPLE_STATE_DRIVER_R = 557846626;
    public static final int ID_AVOID_BLOW_PEOPLE_STATE_DRIVER_S = 557846646;
    public static final int ID_A_C_WORK_MODE_MID_LEFT_R = 557846693;
    public static final int ID_A_C_WORK_MODE_MID_RIGHT_R = 557846694;
    public static final int ID_A_C_WORK_MODE_R = 557846692;
    public static final int ID_A_C_WORK_MODE_REAR_R = 557846695;
    public static final int ID_BLOW_PEOPLE_STATE_COPILOT_R = 557846625;
    public static final int ID_BLOW_PEOPLE_STATE_COPILOT_S = 557846645;
    public static final int ID_BLOW_PEOPLE_STATE_DRIVER_R = 557846624;
    public static final int ID_BLOW_PEOPLE_STATE_DRIVER_S = 557846644;
    public static final int ID_CONFIRM_CLEAN_AIR_STATE = 557846595;
    public static final int ID_CONSTANT_TEMPERATURE_S = 557846659;
    public static final int ID_DEFROST_REAR_CONFIG = 557846594;
    public static final int ID_DRIVER_SEAT_HEATING2_A_A_CONFIG_R = 557846671;
    public static final int ID_DRIVER_SEAT_HEATING2_E4_CONFIG_R = 557846670;
    public static final int ID_DRIVER_SEAT_VENTILATING2_A_A_CONFIG_R = 557846669;
    public static final int ID_DRIVER_SEAT_VENTILATING2_E4_CONFIG_R = 557846668;
    public static final int ID_FREE_WIND_BTN_STATE_COPILOT_R = 557846623;
    public static final int ID_FREE_WIND_BTN_STATE_COPILOT_S = 557846651;
    public static final int ID_FREE_WIND_BTN_STATE_DRIVER_R = 557846622;
    public static final int ID_FREE_WIND_BTN_STATE_DRIVER_S = 557846650;
    public static final int ID_FRONT_ROW_AIR_OUTLET_STATE_R = 557846633;
    public static final int ID_FRONT_ROW_AIR_OUTLET_STATE_S = 557846655;
    public static final int ID_HIGHTEMPANTIVIRUSSTATE = 557846597;
    public static final int ID_HIGH_TEMP_ANTIVIRUS_COUNT_DOWN = 557846596;
    public static final int ID_INTELLIGENT_CONTROL_STATE_R = 557846632;
    public static final int ID_INTELLIGENT_CONTROL_STATE_S = 557846654;
    public static final int ID_L_R_SEAT_HEATING2_A_A_CONFIG_R = 557846681;
    public static final int ID_L_R_SEAT_HEATING349_CONFIG_R = 557846683;
    public static final int ID_L_R_SEAT_HEATING35_D_CONFIG_R = 557846682;
    public static final int ID_L_R_SEAT_VENTILATING2_A_A_CONFIG_R = 557846678;
    public static final int ID_L_R_SEAT_VENTILATING349_CONFIG_R = 557846680;
    public static final int ID_L_R_SEAT_VENTILATING35_D_CONFIG_R = 557846679;
    public static final int ID_MAX_COOLING_STATES = 557846599;
    public static final int ID_PASSENGER_SEAT_HEATING2_A_A_CONFIG_R = 557846676;
    public static final int ID_PASSENGER_SEAT_HEATING2_F_F_CONFIG_R = 557846677;
    public static final int ID_PASSENGER_SEAT_HEATING41_C_CONFIG_R = 557846675;
    public static final int ID_PASSENGER_SEAT_VENTILATING2_A_A_CONFIG_R = 557846673;
    public static final int ID_PASSENGER_SEAT_VENTILATING2_F_F_CONFIG_R = 557846674;
    public static final int ID_PASSENGER_SEAT_VENTILATING41_C_CONFIG_R = 557846672;
    public static final int ID_QUICK_CLEAN_TIPR = 557846607;
    public static final int ID_R_R_SEAT_HEATING21_D_CONFIG_R = 557846691;
    public static final int ID_R_R_SEAT_HEATING2_A_A_CONFIG_R = 557846689;
    public static final int ID_R_R_SEAT_HEATING349_CONFIG_R = 557846690;
    public static final int ID_R_R_SEAT_HEATING363_CONFIG_R = 557846688;
    public static final int ID_R_R_SEAT_VENTILATING21_D_CONFIG_R = 557846687;
    public static final int ID_R_R_SEAT_VENTILATING2_A_A_CONFIG_R = 557846685;
    public static final int ID_R_R_SEAT_VENTILATING349_CONFIG_R = 557846686;
    public static final int ID_R_R_SEAT_VENTILATING363_CONFIG_R = 557846684;
    public static final int ID_SET_CAR_REAR_AC_DET = 557846530;
    public static final int ID_SWEEP_BLOW_STATE_COPILOT_R = 557846629;
    public static final int ID_SWEEP_BLOW_STATE_COPILOT_S = 557846649;
    public static final int ID_SWEEP_BLOW_STATE_DRIVER_R = 557846628;
    public static final int ID_SWEEP_BLOW_STATE_DRIVER_S = 557846648;
    public static final int ID_TEMPERATURE_UNIT = 557846528;
    public static final int ID_TEMPRATURE_MID_LEFT_R = 557846660;
    public static final int ID_TEMPRATURE_MID_RIGHT_R = 557846661;
    public static final int ID_TEMP_REGULATING_ACCURACY_R = 557846667;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.hvac";
    public static final String TAG = "CAR.VENDOR.HVAC";

    public interface OnCarHvacEventListener {
        void onHvacErrorEvent(int i, int i2);

        void onHvacEventChange(HvacEventChangeValue hvacEventChangeValue);
    }

    void addCarHvacEventListener(OnCarHvacEventListener onCarHvacEventListener) throws CarNotConnectedException;

    int getAcAqsConfigR() throws CarNotConnectedException;

    int getAcAssistModeTips() throws CarNotConnectedException;

    int getAcAutomaticButtonTurnOff() throws CarNotConnectedException;

    int getAcCircleMode() throws CarNotConnectedException;

    int getAcCockpitPurificationStatusR() throws CarNotConnectedException;

    int getAcCompressorManualSign() throws CarNotConnectedException;

    int getAcControllerDriverTempR() throws CarNotConnectedException;

    int getAcCtrlModeR() throws CarNotConnectedException;

    int getAcDefrosRearPanelNotAvailableTips() throws CarNotConnectedException;

    int getAcDefrostConfigLeft() throws CarNotConnectedException;

    int getAcDefrostOpenStateLeft() throws CarNotConnectedException;

    int getAcDefrostR() throws CarNotConnectedException;

    int getAcDualChannelPm25Flag() throws CarNotConnectedException;

    int getAcFaultNumShownState() throws CarNotConnectedException;

    int getAcFrontWindLevelR() throws CarNotConnectedException;

    int getAcHasPerfumeConfiguration() throws CarNotConnectedException;

    int getAcHeatModeFlag() throws CarNotConnectedException;

    int getAcHeatPumpCompressorMode() throws CarNotConnectedException;

    int getAcHeatingButtonConfigR() throws CarNotConnectedException;

    int getAcHeatingStateR() throws CarNotConnectedException;

    int getAcHighTempSterilizationFlag() throws CarNotConnectedException;

    int getAcKeepEngineRunTIps() throws CarNotConnectedException;

    int getAcMaxColdR() throws CarNotConnectedException;

    int getAcNaturalWindR() throws CarNotConnectedException;

    int getAcOpenState() throws CarNotConnectedException;

    int getAcPromptBoxShownStateR() throws CarNotConnectedException;

    int getAcPtcPreheatSignalR() throws CarNotConnectedException;

    int getAcRearAcButton() throws CarNotConnectedException;

    int getAcRearAcCtrlModeR() throws CarNotConnectedException;

    int getAcRearAcFunction() throws CarNotConnectedException;

    int getAcRearAcState() throws CarNotConnectedException;

    int getAcRearAcTempAdjust() throws CarNotConnectedException;

    int getAcRearAcWindLevel() throws CarNotConnectedException;

    int getAcRearButtonClick() throws CarNotConnectedException;

    int getAcRearLockR() throws CarNotConnectedException;

    int getAcRearPanelLockR() throws CarNotConnectedException;

    int getAcRearTempR() throws CarNotConnectedException;

    int getAcRearWindAutoAdjust() throws CarNotConnectedException;

    int getAcRearWindLevelAdjust() throws CarNotConnectedException;

    int getAcRearWindLevelR() throws CarNotConnectedException;

    int getAcRearWindModeAdjust() throws CarNotConnectedException;

    int getAcRearWindModeR() throws CarNotConnectedException;

    int getAcRemoteCtrlTimeR() throws CarNotConnectedException;

    int getAcRequestRight() throws CarNotConnectedException;

    int getAcTempCtrlSeparateState() throws CarNotConnectedException;

    int getAcTempratureDeputy() throws CarNotConnectedException;

    int getAcTuyereSwitchStateCopilotLeftR() throws CarNotConnectedException;

    int getAcTuyereSwitchStateCopilotRightR() throws CarNotConnectedException;

    int getAcTuyereSwitchStateMainLeftR() throws CarNotConnectedException;

    int getAcTuyereSwitchStateMainRightR() throws CarNotConnectedException;

    int getAcType() throws CarNotConnectedException;

    int getAcWindLevelManualSign() throws CarNotConnectedException;

    int getAcWindModeManualSign() throws CarNotConnectedException;

    int getAcWindModeNumR() throws CarNotConnectedException;

    int getAcWindModeR() throws CarNotConnectedException;

    int getAcWorkCommandR() throws CarNotConnectedException;

    int getAcWorkModeCopilotR() throws CarNotConnectedException;

    int getAcWorkModeDriverR() throws CarNotConnectedException;

    int getAcWorkModeMidLeftR() throws CarNotConnectedException;

    int getAcWorkModeMidRightR() throws CarNotConnectedException;

    int getAcWorkModeRearR() throws CarNotConnectedException;

    int getAcbtWindTips() throws CarNotConnectedException;

    int getAirOutletStateCopilotR() throws CarNotConnectedException;

    int getAirOutletStateDriverR() throws CarNotConnectedException;

    float getAirOutletValueCopilotLeftHorizontalR() throws CarNotConnectedException;

    float getAirOutletValueCopilotLeftVerticalR() throws CarNotConnectedException;

    float getAirOutletValueCopilotRightHorizontalR() throws CarNotConnectedException;

    float getAirOutletValueCopilotRightVerticalR() throws CarNotConnectedException;

    float getAirOutletValueDriverLeftHorizontalR() throws CarNotConnectedException;

    float getAirOutletValueDriverLeftVerticalR() throws CarNotConnectedException;

    float getAirOutletValueDriverRightHorizontalR() throws CarNotConnectedException;

    float getAirOutletValueDriverRightVerticalR() throws CarNotConnectedException;

    int getAutoCleanAirStateR() throws CarNotConnectedException;

    int getAvoidBlowPeopleStateCopilotR() throws CarNotConnectedException;

    int getAvoidBlowPeopleStateDriverR() throws CarNotConnectedException;

    int getBlowPeopleStateCopilotR() throws CarNotConnectedException;

    int getBlowPeopleStateDriverR() throws CarNotConnectedException;

    int getDriverSeatHeating2aaConfigR() throws CarNotConnectedException;

    int getDriverSeatHeating2e4ConfigR() throws CarNotConnectedException;

    int getDriverSeatVentilating2aaConfigR() throws CarNotConnectedException;

    int getDriverSeatVentilating2e4ConfigR() throws CarNotConnectedException;

    int getFreeWindBtnStateCopilotR() throws CarNotConnectedException;

    int getFreeWindBtnStateDriverR() throws CarNotConnectedException;

    int getFrontRowAirOutletStateR() throws CarNotConnectedException;

    int getHighTempAntivirusCountDown() throws CarNotConnectedException;

    int getHighTempAntivirusState() throws CarNotConnectedException;

    int getIntelligentControlStateR() throws CarNotConnectedException;

    int getLrSeatHeating2aaConfigR() throws CarNotConnectedException;

    int getLrSeatHeating349ConfigR() throws CarNotConnectedException;

    int getLrSeatHeating35dConfigR() throws CarNotConnectedException;

    int getLrSeatVentilating2aaConfigR() throws CarNotConnectedException;

    int getLrSeatVentilating349ConfigR() throws CarNotConnectedException;

    int getLrSeatVentilating35dConfigR() throws CarNotConnectedException;

    int getPassengerSeatHeating2aaConfigR() throws CarNotConnectedException;

    int getPassengerSeatHeating2ffConfigR() throws CarNotConnectedException;

    int getPassengerSeatHeating41cConfigR() throws CarNotConnectedException;

    int getPassengerSeatVentilating2aaConfigR() throws CarNotConnectedException;

    int getPassengerSeatVentilating2ffConfigR() throws CarNotConnectedException;

    int getPassengerSeatVentilating41cConfigR() throws CarNotConnectedException;

    int getQuickCleanAirStateR() throws CarNotConnectedException;

    int getQuickCleanTipR() throws CarNotConnectedException;

    int getRearAcDet() throws CarNotConnectedException;

    int getRrSeatHeating21dConfigR() throws CarNotConnectedException;

    int getRrSeatHeating2aaConfigR() throws CarNotConnectedException;

    int getRrSeatHeating349ConfigR() throws CarNotConnectedException;

    int getRrSeatHeating363ConfigR() throws CarNotConnectedException;

    int getRrSeatVentilating21dConfigR() throws CarNotConnectedException;

    int getRrSeatVentilating2aaConfigR() throws CarNotConnectedException;

    int getRrSeatVentilating349ConfigR() throws CarNotConnectedException;

    int getRrSeatVentilating363ConfigR() throws CarNotConnectedException;

    int getSweepBlowStateCopilotR() throws CarNotConnectedException;

    int getSweepBlowStateDriverR() throws CarNotConnectedException;

    int getTempRegulatingAccuracyR() throws CarNotConnectedException;

    int getTemperatureUnit() throws CarNotConnectedException;

    int getTempratureOut() throws CarNotConnectedException;

    void removeCarHvacEventListener(OnCarHvacEventListener onCarHvacEventListener) throws CarNotConnectedException;

    void setAcAntibacterialControlStateS(int i) throws CarNotConnectedException;

    void setAcCircleKey(int i) throws CarNotConnectedException;

    void setAcCloseKey(int i) throws CarNotConnectedException;

    void setAcControllerDriverTempS(int i) throws CarNotConnectedException;

    void setAcControllerPassengerTempS(int i) throws CarNotConnectedException;

    void setAcCtrlModeS(int i) throws CarNotConnectedException;

    void setAcDefrostS(int i) throws CarNotConnectedException;

    void setAcDualMode(int i) throws CarNotConnectedException;

    void setAcFrontWindLevelS(int i) throws CarNotConnectedException;

    void setAcHeatingStateS(int i) throws CarNotConnectedException;

    void setAcIonStateS(int i) throws CarNotConnectedException;

    void setAcMaxCoolingStateS(int i) throws CarNotConnectedException;

    void setAcMediaDefrostButton(int i) throws CarNotConnectedException;

    void setAcNaturalWindS(int i) throws CarNotConnectedException;

    void setAcRearAcCtrlModeS(int i) throws CarNotConnectedException;

    void setAcRearAcStateS(int i) throws CarNotConnectedException;

    void setAcRearLockS(int i) throws CarNotConnectedException;

    void setAcRearPanelLockS(int i) throws CarNotConnectedException;

    void setAcRearTempS(int i) throws CarNotConnectedException;

    void setAcRearWindLevelS(int i) throws CarNotConnectedException;

    void setAcRearWindModeS(int i) throws CarNotConnectedException;

    void setAcRemoteCtrlTimeS(int i) throws CarNotConnectedException;

    void setAcTurnOn(int i) throws CarNotConnectedException;

    void setAcTuyereSwitchStateCopilotLeftS(int i) throws CarNotConnectedException;

    void setAcTuyereSwitchStateCopilotRightS(int i) throws CarNotConnectedException;

    void setAcTuyereSwitchStateMainLeftS(int i) throws CarNotConnectedException;

    void setAcTuyereSwitchStateMainRightS(int i) throws CarNotConnectedException;

    void setAcWindModeS(int i) throws CarNotConnectedException;

    void setAirOutletStateCopilotS(int i) throws CarNotConnectedException;

    void setAirOutletStateDriverS(int i) throws CarNotConnectedException;

    void setAirOutletValueCopilotLeftHorizontalS(int i) throws CarNotConnectedException;

    void setAirOutletValueCopilotLeftVerticalS(int i) throws CarNotConnectedException;

    void setAirOutletValueCopilotRightHorizontalS(int i) throws CarNotConnectedException;

    void setAirOutletValueCopilotRightVerticalS(int i) throws CarNotConnectedException;

    void setAirOutletValueDriverLeftHorizontalS(int i) throws CarNotConnectedException;

    void setAirOutletValueDriverLeftVerticalS(int i) throws CarNotConnectedException;

    void setAirOutletValueDriverRightHorizontalS(int i) throws CarNotConnectedException;

    void setAirOutletValueDriverRightVerticalS(int i) throws CarNotConnectedException;

    void setAutoCleanAirStateS(int i) throws CarNotConnectedException;

    void setAvoidBlowPeopleStateCopilotS(int i) throws CarNotConnectedException;

    void setAvoidBlowPeopleStateDriverS(int i) throws CarNotConnectedException;

    void setBlowPeopleStateCopilotS(int i) throws CarNotConnectedException;

    void setBlowPeopleStateDriverS(int i) throws CarNotConnectedException;

    void setConfirmCleanAirState(int i) throws CarNotConnectedException;

    void setConstantTemperatureS(int i) throws CarNotConnectedException;

    void setFreeWindBtnStateCopilotS(int i) throws CarNotConnectedException;

    void setFreeWindBtnStateDriverS(int i) throws CarNotConnectedException;

    void setFrontRowAirOutletStateS(int i) throws CarNotConnectedException;

    void setIntelligentControlStateS(int i) throws CarNotConnectedException;

    void setQuickCleanAirStateS(int i) throws CarNotConnectedException;

    void setSweepBlowStateCopilotS(int i) throws CarNotConnectedException;

    void setSweepBlowStateDriverS(int i) throws CarNotConnectedException;
}

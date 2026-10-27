package com.ts.lib.car.hardware.instrument;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes3.dex */
public interface CarVendorInstrumentManager {
    public static final int ID_ACC_COUNTER_UI = 557867011;
    public static final int ID_ALARM_BUZZLE_STATE = 557857052;
    public static final int ID_ALL_WEATHER_LIGHTS_STATE_R = 557857169;
    public static final int ID_AREA_INFO_R = 557868037;
    public static final int ID_CALL_INFO_DOWN_S = 557872402;
    public static final int ID_CALL_INFO_UP_R = 557875467;
    public static final int ID_CLEAR_DISTANCE_MILES_TYPE = 557857242;
    public static final int ID_DAY_NIGHT_MODER = 557857144;
    public static final int ID_DAY_NIGHT_MODES = 557857142;
    public static final int ID_DISTANCE_MILEAGE_UNIT_R = 557868043;
    public static final int ID_EXTERNAL_CHARGING_POWER = 559954186;
    public static final int ID_HEADLIGHT_CONTROL_MODE_R = 557857171;
    public static final int ID_HEADLIGHT_CONTROL_MODE_S = 557857170;
    public static final int ID_INSTRUMENT_AUTO_DIMM_STATE_R = 557857029;
    public static final int ID_INSTRUMENT_AUTO_DIMM_STATE_S = 557857030;
    public static final int ID_INSTRUMENT_BRIGHTNESS_LEFT_R = 557857026;
    public static final int ID_INSTRUMENT_BRIGHTNESS_R = 557857027;
    public static final int ID_INSTRUMENT_BRIGHTNESS_S = 557857028;
    public static final int ID_INSTRUMENT_CONFIGURE = 557857155;
    public static final int ID_INSTRUMENT_SCREENTYPE = 557857154;
    public static final int ID_INSTRUMENT_THEME_R = 557857024;
    public static final int ID_INSTRUMENT_THEME_S = 557857025;
    public static final int ID_INS_ADAS_CENTER_DISPLAY = 557857138;
    public static final int ID_INS_ADAS_CENTER_DISPLAY_RESPOND_40C = 557857136;
    public static final int ID_INS_ADAS_CENTER_DISPLAY_SEND_26F = 557857137;
    public static final int ID_INS_ADAS_CENTER_DISPLAY_SEND_4C1 = 557857135;
    public static final int ID_INS_CENTER_DISPLAYR = 557875262;
    public static final int ID_INS_CENTER_DISPLAYS = 557857139;
    public static final int ID_INS_NAV_DISPLAY_MODE_R = 557857133;
    public static final int ID_INS_NAV_DISPLAY_MODE_S = 557857134;
    public static final int ID_LOW_FUEL_WARN_LIGHT_COLOR_R = 557869067;
    public static final int ID_LOW_POWER_BATTERY_WARN_LIGHT_COLOR_R = 557869062;
    public static final int ID_MAINTENANCE_INFO_FOURR = 557857156;
    public static final int ID_MAINTENANCE_INFO_MILE_STATER = 557857145;
    public static final int ID_MAINTENANCE_INFO_MILE_STATES = 557857038;
    public static final int ID_MAINTENANCE_INFO_ONER = 557857039;
    public static final int ID_MAINTENANCE_INFO_THREER = 557857147;
    public static final int ID_MAINTENANCE_INFO_TIME_STATE = 557857037;
    public static final int ID_MAINTENANCE_INFO_TIME_STATE_INSTRUMENT = 557857036;
    public static final int ID_MAINTENANCE_INFO_TIME_STATE_MAINTAIN = 557857035;
    public static final int ID_MAINTENANCE_INFO_TWOR = 557857146;
    public static final int ID_MALFUNCTION_INFO = 557857053;
    public static final int ID_MALFUNCTION_INFO_SECOND = 557857150;
    public static final int ID_MAL_FUNCTION_INFOS_AC_HOT_MANA_FAIL_YELLOW = 557857087;
    public static final int ID_MAL_FUNCTION_INFOS_AEB_FUNCT_LIMIT_YELLOW = 557857102;
    public static final int ID_MAL_FUNCTION_INFOS_APA_FUNCTION_LIMIT_WHITE = 557857129;
    public static final int ID_MAL_FUNCTION_INFOS_BRAKE_FLUID_LEVEL_IS_LOW_RED = 557857057;
    public static final int ID_MAL_FUNCTION_INFOS_CHARG_PORT_COVER_NOT_CLS_WHITE = 557857123;
    public static final int ID_MAL_FUNCTION_INFOS_CHECK_BRAKE_PLATE_YELLOW = 557857099;
    public static final int ID_MAL_FUNCTION_INFOS_CHECK_CHARG_OUT_EQUIP_YELLOW = 557857110;
    public static final int ID_MAL_FUNCTION_INFOS_CHECK_DISCHARGE_EQUIP_YELLOW = 557857109;
    public static final int ID_MAL_FUNCTION_INFOS_CHECK_FMRR_YELLOW = 557857101;
    public static final int ID_MAL_FUNCTION_INFOS_CHECK_GENERATION_SYS_YELLOW = 557857095;
    public static final int ID_MAL_FUNCTION_INFOS_CHECK_HEADLIGHT_SYS_YELLOW = 557857084;
    public static final int ID_MAL_FUNCTION_INFOS_CHECK_MOT_PRET_BELT_SYS_YELLOW = 557857130;
    public static final int ID_MAL_FUNCTION_INFOS_CHECK_ON_BOARDCHAR_SYS_YELLOW = 557857111;
    public static final int ID_MAL_FUNCTION_INFOS_CHECK_PEDESTRI_DETE_SYS_YELLOW = 557857104;
    public static final int ID_MAL_FUNCTION_INFOS_CHECK_PRED_COLL_ALA_SYS_YELLOW = 557857103;
    public static final int ID_MAL_FUNCTION_INFOS_CHECK_THE_BRAK_SYS_YELLOW = 557857093;
    public static final int ID_MAL_FUNCTION_INFOS_CHECK_THE_OIL_MONIT_SYS_YELLOW = 557857090;
    public static final int ID_MAL_FUNCTION_INFOS_CHECK_TIRE_PRES_MON_SYS_YELLOW = 557857091;
    public static final int ID_MAL_FUNCTION_INFOS_CHECK_VIDEO_CONTROLLER_YELLOW = 557857100;
    public static final int ID_MAL_FUNCTION_INFOS_COOLANT_LIQUI_LEVEL_LOW_YELLOW = 557857086;
    public static final int ID_MAL_FUNCTION_INFOS_ENGINE_COOL_TEMP_TOO_HIGH1_RED = 557857059;
    public static final int ID_MAL_FUNCTION_INFOS_ENGINE_COOL_TEMP_TOO_HIGH2_RED = 557857060;
    public static final int ID_MAL_FUNCTION_INFOS_ENGINE_START_FAILED_RED = 557857085;
    public static final int ID_MAL_FUNCTION_INFOS_ENG_ACCESSORIES_LIMITED_RED = 557857077;
    public static final int ID_MAL_FUNCTION_INFOS_EPB_IS_NOT_RELEASED_YELLOW = 557857127;
    public static final int ID_MAL_FUNCTION_INFOS_EV_FUNCTION_IS_LIMITED_RED = 557857069;
    public static final int ID_MAL_FUNCTION_INFOS_EXIT_SMART_DRIVING_MODE_RED = 557857070;
    public static final int ID_MAL_FUNCTION_INFOS_EXTER_DISCHRG_FUNCT_OFF_YELLOW = 557857121;
    public static final int ID_MAL_FUNCTION_INFOS_GEARBOX_ADAPTING_WHIITE = 557857120;
    public static final int ID_MAL_FUNCTION_INFOS_GEARBOX_OIL_TEMP_TOO_HIGH_RED = 557857080;
    public static final int ID_MAL_FUNCTION_INFOS_GPF_NEEDS_REGENERATION = 557857116;
    public static final int ID_MAL_FUNCTION_INFOS_HIGH_CLUTCH_TEMPERATURE_RED = 557857079;
    public static final int ID_MAL_FUNCTION_INFOS_INSUFFICIENT_OIL_PRESSURE_RED = 557857061;
    public static final int ID_MAL_FUNCTION_INFOS_INTELLIGENT_DRIVE_FAILURE_RED = 557857071;
    public static final int ID_MAL_FUNCTION_INFOS_LACK_OF_PARK_CAPACITY_YELLOW = 557857073;
    public static final int ID_MAL_FUNCTION_INFOS_LIMITED_ADAPTIVE_CRUISE_YELLOW = 557857106;
    public static final int ID_MAL_FUNCTION_INFOS_LIMITED_BLIND_SPOT_MONI_YELLOW = 557857108;
    public static final int ID_MAL_FUNCTION_INFOS_LIMITED_LANE_KEEPING_YELLOW = 557857105;
    public static final int ID_MAL_FUNCTION_INFOS_LIMIT_LANE_DEPARTU_FUNC_YELLOW = 557857107;
    public static final int ID_MAL_FUNCTION_INFOS_LOW_BATTERY_LIMITED_POWER_RED = 557857068;
    public static final int ID_MAL_FUNCTION_INFOS_LOW_BATTERY_LIMIT_POWER_YELLOW = 557857112;
    public static final int ID_MAL_FUNCTION_INFOS_LV_POWER_SUPPLY_SYS_FAULTY_RED = 557857063;
    public static final int ID_MAL_FUNCTION_INFOS_OIL_LEVEL_IS_TOO_HIGH_YELLOW = 557857089;
    public static final int ID_MAL_FUNCTION_INFOS_OIL_LEVEL_IS_TOO_LOW_YELLOW = 557857088;
    public static final int ID_MAL_FUNCTION_INFOS_PARKING_BRAKE_NOT_RELEA_YELLOW = 557857126;
    public static final int ID_MAL_FUNCTION_INFOS_PLEASE_ADD_FUEL_YELLOW = 557857128;
    public static final int ID_MAL_FUNCTION_INFOS_PLEASE_CHARGE_IN_TIME_YELLOW = 557857125;
    public static final int ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_ABS_SYS_YELLOW = 557857094;
    public static final int ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_ADB_SYSTEM_RED = 557857082;
    public static final int ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_CONNECTOR_YELLOW = 557857118;
    public static final int ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_ENGINE_SYSTEM_RED = 557857076;
    public static final int ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_EPB_RED = 557857072;
    public static final int ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_ESP_SYS_YELLOW = 557857097;
    public static final int ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_GEAR_SYS_YELLOW = 557857096;
    public static final int ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_HDC_SYS_YELLOW = 557857098;
    public static final int ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_LBMS_RED = 557857074;
    public static final int ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_LV_SUP_SYS_RED = 557857075;
    public static final int ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_MEMORY_SYS_YELLOW = 557857131;
    public static final int ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_POWER_SYS_YELLOW = 557857083;
    public static final int ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_SRS_SYSTEM_RED = 557857081;
    public static final int ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_SUNROO_SYS_YELLOW = 557857132;
    public static final int ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_THE_BRAKE_SYS_RED = 557857056;
    public static final int ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_THE_POWER_SYS_RED = 557857062;
    public static final int ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_THE_STEER_SYS_RED = 557857058;
    public static final int ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_TRANSMIS_SYS2_RED = 557857067;
    public static final int ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_TRANSMI_SYS1_RED = 557857066;
    public static final int ID_MAL_FUNCTION_INFOS_PLEASE_CHECK_VEHICLE_NET_RED = 557857065;
    public static final int ID_MAL_FUNCTION_INFOS_PLEASE_GO_BYD_SERV_SHOP_YELLOW = 557857114;
    public static final int ID_MAL_FUNCTION_INFOS_PLEASE_LEAVE_VEHICLE_IMMD_RED = 557857054;
    public static final int ID_MAL_FUNCTION_INFOS_PLEASE_STOP_DISCHARGING_YELLOW = 557857122;
    public static final int ID_MAL_FUNCTION_INFOS_REMOTE_CTRL_DRIVE_SUSPE_YELLOW = 557857055;
    public static final int ID_MAL_FUNCTION_INFOS_RESERVED_SONE = 557857115;
    public static final int ID_MAL_FUNCTION_INFOS_RESERVED_STWO = 557857148;
    public static final int ID_MAL_FUNCTION_INFOS_RESTRICTED_DRIVE_FUNCTION_RED = 557857117;
    public static final int ID_MAL_FUNCTION_INFOS_SOLENOID_VALVE_CLEAN_WHIITE = 557857119;
    public static final int ID_MAL_FUNCTION_INFOS_START_TYPE_IRON_BAT_FAULTY_RED = 557857064;
    public static final int ID_MAL_FUNCTION_INFOS_SWITCH_TO_P_RANGE_YELLOW = 557857124;
    public static final int ID_MAL_FUNCTION_INFOS_TIRE_PRESSURE_IS_LOW_YELLOW = 557857092;
    public static final int ID_MAL_FUNCTION_INFOS_TRANSMIS_LIMITEDIN_FUNC_RED = 557857078;
    public static final int ID_MAL_FUNCTION_INFOS_VEHICLE_ENT_FOR_EV_MODE_YELLOW = 557857113;
    public static final int ID_MASTER_VOLUME_FIRST_R = 557857207;
    public static final int ID_MASTER_VOLUME_S = 557857180;
    public static final int ID_MASTER_VOLUME_SECOND_R = 557857208;
    public static final int ID_MILEAGE_UNITR = 557868033;
    public static final int ID_MILES_BY_TYPE = 557857033;
    public static final int ID_MILES_TYPE_R = 557857032;
    public static final int ID_MILES_TYPE_S = 557857031;
    public static final int ID_MUSIC_CHANNEL_STATUS_FIRST_R = 557857186;
    public static final int ID_MUSIC_CHANNEL_STATUS_SECOND_R = 557857187;
    public static final int ID_MUSIC_CONTROL_STATUS_FIRST_R = 557857184;
    public static final int ID_MUSIC_CONTROL_STATUS_SECOND_R = 557857185;
    public static final int ID_MUSIC_INFO_NEW_S = 557857241;
    public static final int ID_MUSIC_INFO_S = 557922726;
    public static final int ID_MUSIC_NAME_S = 558971303;
    public static final int ID_MUSIC_NAME_S_B_2 = 561002939;
    public static final int ID_MUSIC_NAME_S_B_3 = 561002940;
    public static final int ID_MUSIC_NAME_S_B_4 = 561002941;
    public static final int ID_MUSIC_NAME_S_B_5 = 561002942;
    public static final int ID_MUSIC_NAME_S_B_6 = 561002943;
    public static final int ID_MUSIC_NAME_S_B_7 = 561002944;
    public static final int ID_MUSIC_NAME_S_B_8 = 561002945;
    public static final int ID_MUSIC_NAME_S_B_9 = 561002946;
    public static final int ID_MUSIC_NAME_S_B_A = 561002947;
    public static final int ID_MUSIC_NAME_S_B_B = 561002948;
    public static final int ID_MUSIC_NAME_S_B_C = 561002949;
    public static final int ID_MUSIC_NAME_S_B_D = 561002950;
    public static final int ID_MUSIC_NAME_S_B_E = 561002951;
    public static final int ID_MUSIC_PLAY_PROGRESSS = 557872397;
    public static final int ID_MUSIC_PLAY_STATES = 557872395;
    public static final int ID_MUSIC_PROGRESS_2F8_R = 557857182;
    public static final int ID_MUSIC_PROGRESS_2FC_R = 557857183;
    public static final int ID_MUSIC_RESOURCE_CONFIG_FIRST_R = 557857188;
    public static final int ID_MUSIC_RESOURCE_CONFIG_SECOND_R = 557857189;
    public static final int ID_MUSIC_SINGER_NAME_S = 558971305;
    public static final int ID_MUSIC_SINGER_NAME_S_92 = 561002922;
    public static final int ID_MUSIC_SINGER_NAME_S_93 = 561002923;
    public static final int ID_MUSIC_SINGER_NAME_S_94 = 561002924;
    public static final int ID_MUSIC_SINGER_NAME_S_95 = 561002925;
    public static final int ID_MUSIC_SINGER_NAME_S_96 = 561002926;
    public static final int ID_MUSIC_SINGER_NAME_S_97 = 561002927;
    public static final int ID_MUSIC_SINGER_NAME_S_98 = 561002928;
    public static final int ID_MUSIC_SINGER_NAME_S_99 = 561002929;
    public static final int ID_MUSIC_SINGER_NAME_S_9A = 561002930;
    public static final int ID_MUSIC_SINGER_NAME_S_9B = 561002931;
    public static final int ID_MUSIC_SINGER_NAME_S_9C = 561002932;
    public static final int ID_MUSIC_SINGER_NAME_S_9D = 561002933;
    public static final int ID_MUSIC_SINGER_NAME_S_9E = 561002934;
    public static final int ID_MUTE_STATE_FIRST_R = 557857209;
    public static final int ID_MUTE_STATE_S = 557857181;
    public static final int ID_MUTE_STATE_SECONDE_R = 557857210;
    public static final int ID_NOTIFY_MAP_STATE_S = 557857161;
    public static final int ID_NOTIFY_SCREEN_STATUS_S = 557857159;
    public static final int ID_PAD_PRSNT_TIME_S = 557922710;
    public static final int ID_PAD_TOTAL_TIME_S = 557922713;
    public static final int ID_RADIO_CHANNEL_S = 558971337;
    public static final int ID_RADIO_CHANNEL_S_D_2 = 561002954;
    public static final int ID_RADIO_CHANNEL_S_D_3 = 561002955;
    public static final int ID_RADIO_CHANNEL_S_D_4 = 561002957;
    public static final int ID_RADIO_CHANNEL_S_D_5 = 561002958;
    public static final int ID_RADIO_CHANNEL_S_D_6 = 561002959;
    public static final int ID_RADIO_CHANNEL_S_D_7 = 561002960;
    public static final int ID_RADIO_CHANNEL_S_D_8 = 561002961;
    public static final int ID_RADIO_CHANNEL_S_D_9 = 561002962;
    public static final int ID_RADIO_CHANNEL_S_D_A = 561002963;
    public static final int ID_RADIO_CHANNEL_S_D_B = 561002964;
    public static final int ID_RADIO_CHANNEL_S_D_C = 561002965;
    public static final int ID_RADIO_CHANNEL_S_D_D = 561002966;
    public static final int ID_RADIO_CHANNEL_S_D_E = 561002967;
    public static final int ID_RADIO_PLAY_STATES = 557872396;
    public static final int ID_REAR_FOG_LIGHT_STATE_R = 557857172;
    public static final int ID_REAR_FOG_LIGHT_STATE_S = 557857173;
    public static final int ID_SPEED_UNITR = 557867010;
    public static final int ID_SPEED_UP_COUNT_STATER = 557867009;
    public static final int ID_SPEED_UP_COUNT_STATES = 557857143;
    public static final int ID_SPEED_UP_FORM_INS_R = 557857158;
    public static final int ID_SPORT_MODE_STATE = 557857140;
    public static final int ID_TOTAL_MILEAGER = 557857152;
    public static final int ID_UNIT_FUEL_CONSUMPTION_AND_DISTANCE_INSTRUMENT_R = 557857048;
    public static final int ID_UNIT_FUEL_CONSUMPTION_AND_DISTANCE_LEFT_R = 557857049;
    public static final int ID_UNIT_FUEL_CONSUMPTION_AND_DISTANCE_S = 557857042;
    public static final int ID_UNIT_POWER_INSTRUMENT_R = 557857050;
    public static final int ID_UNIT_POWER_LEFT_R = 557857051;
    public static final int ID_UNIT_POWER_S = 557857043;
    public static final int ID_UNIT_PRESSURE_INSTRUMENT_R = 557857046;
    public static final int ID_UNIT_PRESSURE_LEFT_R = 557857047;
    public static final int ID_UNIT_PRESSURE_S = 557857041;
    public static final int ID_UNIT_TEMPERATURE_INSTRUMENT_R = 557857044;
    public static final int ID_UNIT_TEMPERATURE_LEFT_R = 557846528;
    public static final int ID_UNIT_TEMPERATURE_S = 557857040;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.instrument";
    public static final String TAG = "CAR.VENDOR.INSTRUMENT";

    public interface OnCarInstrumentEventListener {
        void onInstrumentErrorEvent(int i, int i2);

        void onInstrumentEventChange(InstrumentEventChangeValue instrumentEventChangeValue);
    }

    void addCarInstrumentEventListener(OnCarInstrumentEventListener onCarInstrumentEventListener) throws CarNotConnectedException;

    int getAccCounterUiR() throws CarNotConnectedException;

    int getAlarmBuzzleState() throws CarNotConnectedException;

    int getAllWeatherLightsStateR() throws CarNotConnectedException;

    int getAreaInfoR() throws CarNotConnectedException;

    int getCallInfoUpR() throws CarNotConnectedException;

    int getDayNightModeR() throws CarNotConnectedException;

    int getDistanceMileageUnitR() throws CarNotConnectedException;

    float getExternalChargingPower() throws CarNotConnectedException;

    int getHeadlightControlModeR() throws CarNotConnectedException;

    int getINSAdasCenterDisplay() throws CarNotConnectedException;

    int getINSCenterDisplayR() throws CarNotConnectedException;

    int getINSNavDisplayModeR() throws CarNotConnectedException;

    int getInsAdasCenterDisplayRespond40C() throws CarNotConnectedException;

    int getInstrumentAutoDimmStateR() throws CarNotConnectedException;

    int getInstrumentBrightnessLeftR() throws CarNotConnectedException;

    int getInstrumentBrightnessR() throws CarNotConnectedException;

    int getInstrumentConfigure() throws CarNotConnectedException;

    int getInstrumentScreenType() throws CarNotConnectedException;

    int getInstrumentThemeR() throws CarNotConnectedException;

    int getLowFuelWarnLightColorR() throws CarNotConnectedException;

    int getLowPowerBatteryWarnLightColorR() throws CarNotConnectedException;

    int getMaintenanceInfoMileStateR() throws CarNotConnectedException;

    int getMaintenanceInfoThreeR() throws CarNotConnectedException;

    int getMaintenanceInfoTimeStateInstrument() throws CarNotConnectedException;

    float getMaintenanceInfoTimeStateMaintain() throws CarNotConnectedException;

    int getMaintenanceInfoTwoR() throws CarNotConnectedException;

    int getMalFunctionInfosACHotManaFailYellow() throws CarNotConnectedException;

    int getMalFunctionInfosAEBFunctLimitYellow() throws CarNotConnectedException;

    int getMalFunctionInfosAPAFunctionLimitWhite() throws CarNotConnectedException;

    int getMalFunctionInfosBrakeFluidLevelIsLowRed() throws CarNotConnectedException;

    int getMalFunctionInfosChargPortCoverNotClsWhite() throws CarNotConnectedException;

    int getMalFunctionInfosCheckBrakePlateYellow() throws CarNotConnectedException;

    int getMalFunctionInfosCheckChargOutEquipYellow() throws CarNotConnectedException;

    int getMalFunctionInfosCheckDischargeEquipYellow() throws CarNotConnectedException;

    int getMalFunctionInfosCheckFMRRYellow() throws CarNotConnectedException;

    int getMalFunctionInfosCheckGenerationSysYellow() throws CarNotConnectedException;

    int getMalFunctionInfosCheckHeadlightSysYellow() throws CarNotConnectedException;

    int getMalFunctionInfosCheckMotPretBeltSysYellow() throws CarNotConnectedException;

    int getMalFunctionInfosCheckOnBoardcharSysYellow() throws CarNotConnectedException;

    int getMalFunctionInfosCheckPedestriDeteSysYellow() throws CarNotConnectedException;

    int getMalFunctionInfosCheckPredCollAlaSysYellow() throws CarNotConnectedException;

    int getMalFunctionInfosCheckTheBrakSysYellow() throws CarNotConnectedException;

    int getMalFunctionInfosCheckTheOilMonitSysYellow() throws CarNotConnectedException;

    int getMalFunctionInfosCheckTirePresMonSysYellow() throws CarNotConnectedException;

    int getMalFunctionInfosCheckVideoControllerYellow() throws CarNotConnectedException;

    int getMalFunctionInfosCoolantLiquiLevelLowYellow() throws CarNotConnectedException;

    int getMalFunctionInfosEPBIsNotReleasedYellow() throws CarNotConnectedException;

    int getMalFunctionInfosEVFunctionIsLimitedRed() throws CarNotConnectedException;

    int getMalFunctionInfosEngAccessoriesLimitedRed() throws CarNotConnectedException;

    int getMalFunctionInfosEngineCoolTempTooHigh1Red() throws CarNotConnectedException;

    int getMalFunctionInfosEngineCoolTempTooHigh2Red() throws CarNotConnectedException;

    int getMalFunctionInfosEngineStartFailedRed() throws CarNotConnectedException;

    int getMalFunctionInfosExitSmartDrivingModeRed() throws CarNotConnectedException;

    int getMalFunctionInfosExterDischrgFunctOffYellow() throws CarNotConnectedException;

    int getMalFunctionInfosGPFNeedsRegeneration() throws CarNotConnectedException;

    int getMalFunctionInfosGearboxAdaptingWhiite() throws CarNotConnectedException;

    int getMalFunctionInfosGearboxOilTempTooHighRed() throws CarNotConnectedException;

    int getMalFunctionInfosHighClutchTemperatureRed() throws CarNotConnectedException;

    int getMalFunctionInfosInsufficientOilPressureRed() throws CarNotConnectedException;

    int getMalFunctionInfosIntelligentDriveFailureRed() throws CarNotConnectedException;

    int getMalFunctionInfosLVPowerSupplySysFaultyRed() throws CarNotConnectedException;

    int getMalFunctionInfosLackOfParkCapacityYellow() throws CarNotConnectedException;

    int getMalFunctionInfosLimitLaneDepartuFuncYellow() throws CarNotConnectedException;

    int getMalFunctionInfosLimitedAdaptiveCruiseYellow() throws CarNotConnectedException;

    int getMalFunctionInfosLimitedBlindSpotMoniYellow() throws CarNotConnectedException;

    int getMalFunctionInfosLimitedLaneKeepingYellow() throws CarNotConnectedException;

    int getMalFunctionInfosLowBatteryLimitPowerYellow() throws CarNotConnectedException;

    int getMalFunctionInfosLowBatteryLimitedPowerRed() throws CarNotConnectedException;

    int getMalFunctionInfosOilLevelIsTooHighYellow() throws CarNotConnectedException;

    int getMalFunctionInfosOilLevelIsTooLowYellow() throws CarNotConnectedException;

    int getMalFunctionInfosParkingBrakeNotReleaYellow() throws CarNotConnectedException;

    int getMalFunctionInfosPleaseAddFuelYellow() throws CarNotConnectedException;

    int getMalFunctionInfosPleaseChargeInTimeYellow() throws CarNotConnectedException;

    int getMalFunctionInfosPleaseCheckABSSysYellow() throws CarNotConnectedException;

    int getMalFunctionInfosPleaseCheckADBSystemRed() throws CarNotConnectedException;

    int getMalFunctionInfosPleaseCheckConnectorYellow() throws CarNotConnectedException;

    int getMalFunctionInfosPleaseCheckEPBRed() throws CarNotConnectedException;

    int getMalFunctionInfosPleaseCheckESPSysYellow() throws CarNotConnectedException;

    int getMalFunctionInfosPleaseCheckEngineSystemRed() throws CarNotConnectedException;

    int getMalFunctionInfosPleaseCheckGearSysYellow() throws CarNotConnectedException;

    int getMalFunctionInfosPleaseCheckHDCSysYellow() throws CarNotConnectedException;

    int getMalFunctionInfosPleaseCheckLBMSRed() throws CarNotConnectedException;

    int getMalFunctionInfosPleaseCheckLVSupSysRed() throws CarNotConnectedException;

    int getMalFunctionInfosPleaseCheckMemorySysYellow() throws CarNotConnectedException;

    int getMalFunctionInfosPleaseCheckPowerSysYellow() throws CarNotConnectedException;

    int getMalFunctionInfosPleaseCheckSRSSystemRed() throws CarNotConnectedException;

    int getMalFunctionInfosPleaseCheckSunrooSysYellow() throws CarNotConnectedException;

    int getMalFunctionInfosPleaseCheckTheBrakeSysRed() throws CarNotConnectedException;

    int getMalFunctionInfosPleaseCheckThePowerSysRed() throws CarNotConnectedException;

    int getMalFunctionInfosPleaseCheckTheSteerSysRed() throws CarNotConnectedException;

    int getMalFunctionInfosPleaseCheckTransmiSys1Red() throws CarNotConnectedException;

    int getMalFunctionInfosPleaseCheckTransmisSys2Red() throws CarNotConnectedException;

    int getMalFunctionInfosPleaseCheckVehicleNetRed() throws CarNotConnectedException;

    int getMalFunctionInfosPleaseGoBydServShopYellow() throws CarNotConnectedException;

    int getMalFunctionInfosPleaseLeaveVehicleImmdRed() throws CarNotConnectedException;

    int getMalFunctionInfosPleaseStopDischargingYellow() throws CarNotConnectedException;

    int getMalFunctionInfosRemoteCtrlDriveSuspeYellow() throws CarNotConnectedException;

    int getMalFunctionInfosReservedSOne() throws CarNotConnectedException;

    int getMalFunctionInfosReservedSTwo() throws CarNotConnectedException;

    int getMalFunctionInfosRestrictedDriveFunctionRed() throws CarNotConnectedException;

    int getMalFunctionInfosSolenoidValveCleanWhiite() throws CarNotConnectedException;

    int getMalFunctionInfosStartTypeIronBatFaultyRed() throws CarNotConnectedException;

    int getMalFunctionInfosSwitchToPRangeYellow() throws CarNotConnectedException;

    int getMalFunctionInfosTirePressureIsLowYellow() throws CarNotConnectedException;

    int getMalFunctionInfosTransmisLimitedinFuncRed() throws CarNotConnectedException;

    int getMalFunctionInfosVehicleEntForEvModeYellow() throws CarNotConnectedException;

    int getMalfunctionInfo() throws CarNotConnectedException;

    int getMalfunctionInfoSecond() throws CarNotConnectedException;

    int getMasterVolumeFirstR() throws CarNotConnectedException;

    int getMasterVolumeSecondR() throws CarNotConnectedException;

    int getMileageUnitR() throws CarNotConnectedException;

    int getMilesByType() throws CarNotConnectedException;

    int getMilesTypeR() throws CarNotConnectedException;

    int getMusicChannelStatusFirstR() throws CarNotConnectedException;

    int getMusicChannelStatusSecondR() throws CarNotConnectedException;

    int getMusicControlStatusFirstR() throws CarNotConnectedException;

    int getMusicControlStatusSecondR() throws CarNotConnectedException;

    int getMusicProgressFirstR() throws CarNotConnectedException;

    int getMusicProgressSecondR() throws CarNotConnectedException;

    int getMusicResourceConfigFirstR() throws CarNotConnectedException;

    int getMusicResourceConfigSecondR() throws CarNotConnectedException;

    int getMuteStateFirstR() throws CarNotConnectedException;

    int getMuteStateSecondR() throws CarNotConnectedException;

    int getRearFogLightStateR() throws CarNotConnectedException;

    int getSpeedUnitR() throws CarNotConnectedException;

    int getSpeedUpCountStateR() throws CarNotConnectedException;

    int getSpeedUpFormInsR() throws CarNotConnectedException;

    int getSportModeState() throws CarNotConnectedException;

    int getTotalMileageR() throws CarNotConnectedException;

    int getUnitFuelConsumptionAndDistanceInstrumentR() throws CarNotConnectedException;

    int getUnitFuelConsumptionAndDistanceLeftR() throws CarNotConnectedException;

    int getUnitPowerInstrumentR() throws CarNotConnectedException;

    int getUnitPowerLeftR() throws CarNotConnectedException;

    int getUnitPressureInstrumentR() throws CarNotConnectedException;

    int getUnitPressureLeftR() throws CarNotConnectedException;

    int getUnitTemperatureInstrumentR() throws CarNotConnectedException;

    int getUnitTemperatureLeftR() throws CarNotConnectedException;

    void removeCarInstrumentEventListener(OnCarInstrumentEventListener onCarInstrumentEventListener) throws CarNotConnectedException;

    void setCallInfoDownS(int i) throws CarNotConnectedException;

    void setDayNightModeS(int i) throws CarNotConnectedException;

    void setHeadlightControlModeS(int i) throws CarNotConnectedException;

    void setINSAdasCenterDisplaySend26F(int i) throws CarNotConnectedException;

    void setINSAdasCenterDisplaySend4C1(int i) throws CarNotConnectedException;

    void setINSCenterDisplayS(int i) throws CarNotConnectedException;

    void setINSNavDisplayModeS(int i) throws CarNotConnectedException;

    void setInstrumentAutoDimmStateS(int i) throws CarNotConnectedException;

    void setInstrumentBrightnessS(int i) throws CarNotConnectedException;

    void setInstrumentThemeS(int i) throws CarNotConnectedException;

    void setMaintenanceInfoMileStateS(int i) throws CarNotConnectedException;

    void setMaintenanceInfoTimeState(int i) throws CarNotConnectedException;

    void setMasterVolumeS(int i) throws CarNotConnectedException;

    void setMilesTypeS(int i) throws CarNotConnectedException;

    void setMusicInfoMusicSourceS(int i) throws CarNotConnectedException;

    void setMusicInfoPlayProgressS(int i) throws CarNotConnectedException;

    void setMusicInfoPlayStateS(int i) throws CarNotConnectedException;

    void setMusicInfoRadioStateS(int i) throws CarNotConnectedException;

    void setMusicInfoS(int[] iArr) throws CarNotConnectedException;

    void setMusicNameS(int i) throws CarNotConnectedException;

    void setMusicNameS(long[] jArr) throws CarNotConnectedException;

    void setMusicNameSb2(byte[] bArr) throws CarNotConnectedException;

    void setMusicNameSb3(byte[] bArr) throws CarNotConnectedException;

    void setMusicNameSb4(byte[] bArr) throws CarNotConnectedException;

    void setMusicNameSb5(byte[] bArr) throws CarNotConnectedException;

    void setMusicNameSb6(byte[] bArr) throws CarNotConnectedException;

    void setMusicNameSb7(byte[] bArr) throws CarNotConnectedException;

    void setMusicNameSb8(byte[] bArr) throws CarNotConnectedException;

    void setMusicNameSb9(byte[] bArr) throws CarNotConnectedException;

    void setMusicNameSbA(byte[] bArr) throws CarNotConnectedException;

    void setMusicNameSbB(byte[] bArr) throws CarNotConnectedException;

    void setMusicNameSbC(byte[] bArr) throws CarNotConnectedException;

    void setMusicNameSbD(byte[] bArr) throws CarNotConnectedException;

    void setMusicNameSbE(byte[] bArr) throws CarNotConnectedException;

    void setMusicSingerNameS(long[] jArr) throws CarNotConnectedException;

    void setMusicSingerNameS92(byte[] bArr) throws CarNotConnectedException;

    void setMusicSingerNameS93(byte[] bArr) throws CarNotConnectedException;

    void setMusicSingerNameS94(byte[] bArr) throws CarNotConnectedException;

    void setMusicSingerNameS95(byte[] bArr) throws CarNotConnectedException;

    void setMusicSingerNameS96(byte[] bArr) throws CarNotConnectedException;

    void setMusicSingerNameS97(byte[] bArr) throws CarNotConnectedException;

    void setMusicSingerNameS98(byte[] bArr) throws CarNotConnectedException;

    void setMusicSingerNameS99(byte[] bArr) throws CarNotConnectedException;

    void setMusicSingerNameS9A(byte[] bArr) throws CarNotConnectedException;

    void setMusicSingerNameS9B(byte[] bArr) throws CarNotConnectedException;

    void setMusicSingerNameS9C(byte[] bArr) throws CarNotConnectedException;

    void setMusicSingerNameS9D(byte[] bArr) throws CarNotConnectedException;

    void setMusicSingerNameS9E(byte[] bArr) throws CarNotConnectedException;

    void setMuteStateS(int i) throws CarNotConnectedException;

    void setNotifyMapStateS(int i) throws CarNotConnectedException;

    void setNotifyScreenStatusS(int i) throws CarNotConnectedException;

    void setPadPrsntTimeS(int[] iArr) throws CarNotConnectedException;

    void setPadTotalTimeS(int[] iArr) throws CarNotConnectedException;

    void setRadioChannelS(int i) throws CarNotConnectedException;

    void setRadioChannelS(long[] jArr) throws CarNotConnectedException;

    void setRadioChannelSd2(byte[] bArr) throws CarNotConnectedException;

    void setRadioChannelSd3(byte[] bArr) throws CarNotConnectedException;

    void setRadioChannelSd4(byte[] bArr) throws CarNotConnectedException;

    void setRadioChannelSd5(byte[] bArr) throws CarNotConnectedException;

    void setRadioChannelSd6(byte[] bArr) throws CarNotConnectedException;

    void setRadioChannelSd7(byte[] bArr) throws CarNotConnectedException;

    void setRadioChannelSd8(byte[] bArr) throws CarNotConnectedException;

    void setRadioChannelSd9(byte[] bArr) throws CarNotConnectedException;

    void setRadioChannelSdA(byte[] bArr) throws CarNotConnectedException;

    void setRadioChannelSdB(byte[] bArr) throws CarNotConnectedException;

    void setRadioChannelSdC(byte[] bArr) throws CarNotConnectedException;

    void setRadioChannelSdD(byte[] bArr) throws CarNotConnectedException;

    void setRadioChannelSdE(byte[] bArr) throws CarNotConnectedException;

    void setRearFogLightStateS(int i) throws CarNotConnectedException;

    void setSpeedUpCountStateS(int i) throws CarNotConnectedException;

    void setUnitFuelConsumptionAndDistanceS(int i) throws CarNotConnectedException;

    void setUnitPowerS(int i) throws CarNotConnectedException;

    void setUnitPressureS(int i) throws CarNotConnectedException;

    void setUnitTemperatureS(int i) throws CarNotConnectedException;

    void setclearDistanceMilesTypeS(int i) throws CarNotConnectedException;
}

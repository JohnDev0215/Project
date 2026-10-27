package com.ts.lib.car.hardware.uistyle;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes.dex */
public interface CarVendorUistyleManager {
    public static final int ID_ACCELERATOR_PEDAL_SUPPRESSION_SYSTEM_R = 557859221;
    public static final int ID_AC_FAULT_NUM_SHOWN_STATE = 557846542;
    public static final int ID_AC_TYPE = 557846529;
    public static final int ID_AC_WARM_SUPPORT = 557856074;
    public static final int ID_AC_WARM_TYPE_ONLINE_STATE = 557856017;
    public static final int ID_AC_WIND_MODE_NUM = 557856015;
    public static final int ID_ADAS_SYSTEM_STATER = 557870364;
    public static final int ID_ADAS_SYSTEM_STATE_R = 557859080;
    public static final int ID_ALL_REAR_SEAT_ONE_BUTTON_DOWN_SWITCH_DISPLAY_CONFIG_R = 557859142;
    public static final int ID_AMBIENT_AREA_SUPPORT = 557856186;
    public static final int ID_AMBIENT_COLORS_SUPPORT = 557856188;
    public static final int ID_AMBIENT_LIGHT_COLOR_SUPPORT = 557856184;
    public static final int ID_AMBIENT_LIGHT_SUPPORT = 557856185;
    public static final int ID_AMBIENT_MODE_SWITCH_SETTINGS_R = 557859125;
    public static final int ID_AMBIENT_MUSIC_MODE_SUPPORT = 557856187;
    public static final int ID_AMPLIFIER_CONFIGURATION_R = 557879608;
    public static final int ID_APP_USE_LIMIT_SUPPORT = 557856124;
    public static final int ID_AUTO_EXTERNAL_REAR_MIRROR_FOLLOW_UP_SWITCH_SUPPORT = 557856053;
    public static final int ID_AUTO_POWER_OFF_SUPPORT = 557856052;
    public static final int ID_AUTO_POWER_ON_STATE_SUPPORT = 557856051;
    public static final int ID_AUTO_PURIFICATION_SUPPORT = 557856067;
    public static final int ID_AUTO_WIPER_SUPPORT = 557856060;
    public static final int ID_AVERAGE_SPEED_INTERFACE_STATUSR = 557875218;
    public static final int ID_A_C_AUTO_AIR_SUPPORT = 557856064;
    public static final int ID_A_C_B_T_WIND_SUPPORT = 557856065;
    public static final int ID_A_C_PAUSE_CYCLE_SUPPORT = 557856066;
    public static final int ID_A_C_TUNNEL_CYCLE_SUPPORT = 557856071;
    public static final int ID_A_E_B_DOUBLE_CONFIRM_R = 557859248;
    public static final int ID_BACK_HOME_DELAY_SUPPORT = 557856037;
    public static final int ID_BACK_REST_ADJUSTMENT_SWITCH_CONFIG_ON_THE_LEFT_SIDE_OF_THE_THIRD_ROW_R = 557859134;
    public static final int ID_BACK_REST_ADJUSTMENT_SWITCH_CONFIG_ON_THE_RIGHT_SIDE_OF_THE_THIRD_ROW_R = 557859144;
    public static final int ID_BATTERY_HEALTH_CONFIG_R = 557859220;
    public static final int ID_BRAKE_PEDAL_MODE_SUPPORT = 557856056;
    public static final int ID_BSIS_SUPPORT_R = 557859167;
    public static final int ID_CABIN_MOVEMENT_MONITORING_FUNCTION_R = 557859217;
    public static final int ID_CALIPER_SPLINT_SUPPORT_BACK_L = 557856182;
    public static final int ID_CALIPER_SPLINT_SUPPORT_BACK_R = 557856183;
    public static final int ID_CALIPER_SPLINT_SUPPORT_COPILOT = 557856181;
    public static final int ID_CALIPER_SPLINT_SUPPORT_FIRST = 557856178;
    public static final int ID_CALIPER_SPLINT_SUPPORT_SECOND = 557856179;
    public static final int ID_CALIPER_SPLINT_SUPPORT_THIRD = 557856180;
    public static final int ID_CAMERA_EER_ROM_DEVICE_ADDRESS_R = 557859155;
    public static final int ID_CAMERA_ISP_ID_R = 557859162;
    public static final int ID_CAMERA_LENS_ID_R = 557859160;
    public static final int ID_CAMERA_SERIALIZER_ID_R = 557859161;
    public static final int ID_CAMERA_SUPPLIER_AND_TEST_BOARD_INFO_R = 557859156;
    public static final int ID_CAMERA_VIDEO_FORMAT_R = 557859159;
    public static final int ID_CAMERA_VIDEO_FRAME_RATE_R = 557859158;
    public static final int ID_CAMERA_VIDEO_RESOLUTION_R = 557859157;
    public static final int ID_CAR_DOOR_CONFIG_R = 557859259;
    public static final int ID_CAR_FRIDGE_TYPE_R = 557859199;
    public static final int ID_CAR_NUM = 557856001;
    public static final int ID_CAR_PLAN_SUPPORT_R = 557859081;
    public static final int ID_CAR_TIPS_SOUND_SUPPORT = 557856128;
    public static final int ID_CAR_TYPE = 557856031;
    public static final int ID_CHARGE_LIMIT_SUPPORT = 557856047;
    public static final int ID_CHARGE_LIMIT_SUPPORT_NOT_LI_R = 557859243;
    public static final int ID_CHARGE_PREHEAT_SUPPORT_R = 557859084;
    public static final int ID_CHARGE_TOAGGLE_GUARD_SUPPORT = 557856046;
    public static final int ID_COMFORTABLE_PARKING_BY37_F_R = 557859127;
    public static final int ID_COMFORTABLE_PARKING_SUPPORT = 557856057;
    public static final int ID_COMFORTABLE_SPEED_UP_R = 557856211;
    public static final int ID_CPD_SUPPORT_R = 557859169;
    public static final int ID_CPD_SW_MEM_STATUS_R = 557859180;
    public static final int ID_CROSS_DOMAIN_PLATFORM_CONFIGS_R = 557859230;
    public static final int ID_CUMULATIVE_AVERAGE_ENERGY_CONSUMPTION_INTERFACE_STATUSR = 557875208;
    public static final int ID_CUMULATIVE_AVERAGE_FUEL_CONSUMPTION_INTERFACE_STATUSR = 557875210;
    public static final int ID_CUMULATIVE_AVERAGE_POWER_CONSUMPTION_INTERFACE_STATUSR = 557875209;
    public static final int ID_CURA_ASSEMBLE_STATUS_FUNCTION_R = 557859223;
    public static final int ID_CURRENT_TRIP_CONFIG_R = 557859233;
    public static final int ID_CUSHION_SUPPORT_BACK_L = 557856176;
    public static final int ID_CUSHION_SUPPORT_BACK_R = 557856177;
    public static final int ID_CUSHION_SUPPORT_COPILOT = 557856175;
    public static final int ID_CUSHION_SUPPORT_SECOND = 557856174;
    public static final int ID_CVC_CONFIG_R = 557859120;
    public static final int ID_DAY_LIGHT_SUPPORT = 557856041;
    public static final int ID_DEFROST_REAR_CONFIG = 557846594;
    public static final int ID_DIGTAL_KEY_SUPPORT = 557856121;
    public static final int ID_DISCHARGE_SUPPORT = 557856196;
    public static final int ID_DISINF_SUPPORT = 557856172;
    public static final int ID_DI_DYNA_CONFIG_R = 557859222;
    public static final int ID_DI_SUS_SHOW_STATE_BY_CAN_F_D_R = 557859122;
    public static final int ID_DI_SUS_SHOW_STATE_BY_CAN_R = 557859121;
    public static final int ID_DMS_ASSEMBLE_STATUS_R = 557870369;
    public static final int ID_DOORKNOB_AUTO_SHRINK_SUPPORT = 557856119;
    public static final int ID_DOOR_HEIGHT_SUPPORT = 557856076;
    public static final int ID_DOOR_LIGHT_SUPPORT = 557856043;
    public static final int ID_DOOR_LIGHT_SUPPORT_R = 557859116;
    public static final int ID_DRIVER_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_ECU_R = 557859151;
    public static final int ID_DRIVER_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_LEFT_CTRL_R = 557859100;
    public static final int ID_DRIVER_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_UNIVERSAL_CTRL_R = 557859101;
    public static final int ID_DRIVE_CONFIG = 557856003;
    public static final int ID_DRIVE_DISTANCE_SHOW_MODE_SUPPORT = 557856199;
    public static final int ID_DRIVE_DISTANCE_SHOW_STANDARD_SUPPORT = 557856197;
    public static final int ID_DRIVE_SEAT_AUTO_RETURN_SUPPORT = 557856054;
    public static final int ID_DRIVE_TIME_INTERFACE_STATUS_FOR_SINGLE_TRIPR = 557856219;
    public static final int ID_DRIVING_RANGE_INTERFACE_STATUS_FOR_FUELR = 557875214;
    public static final int ID_DRIVING_VOICE_BROADCAST_SWITCH_CONFIG_ONER = 557870336;
    public static final int ID_DRIVING_VOICE_BROADCAST_SWITCH_CONFIG_THREER = 557859088;
    public static final int ID_DYNAMIC_SUSPENSION_MODE_BY_CAN_FD_R = 557859173;
    public static final int ID_DYNAMIC_SUSPENSION_MODE_BY_CAN_R = 557859172;
    public static final int ID_DYNAMIC_SUSPENSION_MODE_SUPPORT = 557856061;
    public static final int ID_D_M_TYPE = 557856004;
    public static final int ID_ELECTRIC_FRONT_HATCH_CONFIGURATION_R = 557859083;
    public static final int ID_ELECTRONIC_PARKING_BRAKE_CONFIGUTARION_R = 557850645;
    public static final int ID_ELECTRONI_CHILD_LOCK_CONFIG_FIRST = 557856206;
    public static final int ID_ELECTRONI_CHILD_LOCK_CONFIG_SECOND = 557856207;
    public static final int ID_ELEC_WHERE_CONFIG_R = 557859244;
    public static final int ID_ENERGY_CONTRAST_CONFIG_R = 557859235;
    public static final int ID_ENERGY_FEEDBACK_SUPPORT_R = 557859218;
    public static final int ID_ENERGY_FLOW_DIAGRAM_INTERFACE_STATUSR = 557875223;
    public static final int ID_ENERGY_SHOW_CONFIG_R = 557859232;
    public static final int ID_ENERGY_SHOW_SUPPORT_R = 557859216;
    public static final int ID_ENERGY_STATUS = 557856002;
    public static final int ID_ENGINE_SOUND_SUPPORT = 557856127;
    public static final int ID_ET_AMBIENT_SETTINGS = 557856189;
    public static final int ID_EXCLUSIVE_SAVE_FLAG_R = 557859200;
    public static final int ID_EXTERNAL_CHARGING_POWER_INTERFACE_STATUSR = 557875220;
    public static final int ID_E_L_K_A_DOUBLE_CONFIRM_R = 557859246;
    public static final int ID_E_PEDAL_STATE_SUPPORT = 557856198;
    public static final int ID_FEATURES_STATE_R = 557870415;
    public static final int ID_FOLD_DOWN_THE_SECOND_ROW_OF_SEATS_CONFIG_R = 557859132;
    public static final int ID_FRIDGE_CHILD_LOCK_FLAG_R = 557859201;
    public static final int ID_FRIDGE_DELAYED_POWER_OUTAGE_R = 557859203;
    public static final int ID_FRIDGE_EXPANSION_AND_CONTRACTION_FUNCTION_R = 557859197;
    public static final int ID_FRIDGE_MIN_WAGE_POWER_GENERATION_R = 557859198;
    public static final int ID_FRIDGE_NUM_R = 557859205;
    public static final int ID_FRIDGE_ONE_POSITION_R = 557859210;
    public static final int ID_FRIDGE_SCREEN_FLAG_R = 557859202;
    public static final int ID_FRIDGE_TEMPERATURE_REGULATION_R = 557859196;
    public static final int ID_FRIDGE_THREE_POSITION_R = 557859212;
    public static final int ID_FRIDGE_TWO_POSITION_R = 557859211;
    public static final int ID_FRIDGE_WORKING_MODE_R = 557859195;
    public static final int ID_FRONT_FRIDGE_DISINFECT_FLAG_R = 557859204;
    public static final int ID_FRONT_LIGHT_DIMMING_CTRL_CONFIG_BY3_A_C_R = 557859113;
    public static final int ID_FRONT_LIGHT_DIMMING_CTRL_CONFIG_BY3_A_D_R = 557859114;
    public static final int ID_FUEL_CONSUMPTION_INTERFACE_STATUSR = 557875216;
    public static final int ID_F_C_W_DOUBLE_CONFIRM_R = 557859247;
    public static final int ID_GUEST_LEFT_SEAT_REMEME_SUPPORT_FIRST_L = 557856202;
    public static final int ID_GUEST_RIGHT_SEAT_REMEME_SUPPORT_FIRST_R = 557856204;
    public static final int ID_GUEST_RIGHT_SEAT_REMEME_SUPPORT_SECOND_R = 557856205;
    public static final int ID_HAS_PREDICT_CRASH_ALERT_MODE_R = 557859108;
    public static final int ID_HEADLAMP_LEVEL_CONFIG = 557856035;
    public static final int ID_HEADLAMP_SUPPORT = 557856034;
    public static final int ID_HEADREST_HEIGHT_ADJUSTMENT_SWITCH_CONFIG_ON_THE_RIGHT_SIDE_OF_THE_THIRD_ROW_R = 557859149;
    public static final int ID_HEADREST_HIGHT_ADJUSTMENT_SWITCH_CONFIG_ON_THE_LEFT_SIDE_OF_THE_THIRD_ROW_R = 557859139;
    public static final int ID_HEADREST_LEVEL_ADJUSTMENT_SWITCH_CONFIG_ON_THE_LEFT_SIDE_OF_THE_THIRD_ROW_R = 557859138;
    public static final int ID_HEADREST_LEVEL_ADJUSTMENT_SWITCH_CONFIG_ON_THE_RIGHT_SIDE_OF_THE_THIRD_ROW_R = 557859148;
    public static final int ID_HEADREST_TURN_HEIGHT_SUPPORT_BACK_L = 557856168;
    public static final int ID_HEADREST_TURN_HEIGHT_SUPPORT_BACK_R = 557856169;
    public static final int ID_HEADREST_TURN_HEIGHT_SUPPORT_COPILOT = 557856167;
    public static final int ID_HEADREST_TURN_HEIGHT_SUPPORT_SECOND = 557856165;
    public static final int ID_HEADREST_TURN_HEIGHT_SUPPORT_THIRD = 557856166;
    public static final int ID_HEADREST_TURN_HORIZATIAL_SUPPORT_BACK_L = 557856159;
    public static final int ID_HEADREST_TURN_HORIZATIAL_SUPPORT_BACK_R = 557856160;
    public static final int ID_HEADREST_TURN_HORIZATIAL_SUPPORT_COPILOT = 557856158;
    public static final int ID_HEADREST_TURN_HORIZATIAL_SUPPORT_FIRST = 557856156;
    public static final int ID_HEADREST_TURN_HORIZATIAL_SUPPORT_SECOND = 557856157;
    public static final int ID_HEIGHT_ADJUSTMENT_SWITCH_CONFIG_ON_THE_LEFT_SIDE_OF_THE_THIRD_ROW_R = 557859137;
    public static final int ID_HEIGHT_ADJUSTMENT_SWITCH_CONFIG_ON_THE_RIGHT_SIDE_OF_THE_THIRD_ROW_R = 557859147;
    public static final int ID_HISTORY_ENERGY_CONFIG_R = 557859237;
    public static final int ID_HISTORY_ENERGY_STATISTICS_CONFIG_R = 557859236;
    public static final int ID_HUD_HEIGHT_ADJUST_SUPPORT = 557856191;
    public static final int ID_HUD_LIGHT_SDJUST_SUPPORT = 557856192;
    public static final int ID_HUD_MODE_CONFIGURATION_R = 557859092;
    public static final int ID_HUD_PARAMS_STATE_SUPPORT = 557856190;
    public static final int ID_HUD_SHOW_PARAMS_SUPPORT = 557856072;
    public static final int ID_H_E_V_CAR_PLAN_SUPPORT_R = 557859188;
    public static final int ID_INSTANTANEOUS_FUEL_CONSUMPTION_INTERFACE_STATUSR = 557875211;
    public static final int ID_INSTRUMENT_CONTENT_SUPPORT = 557856195;
    public static final int ID_INSTRUMENT_HEIGHT_SUPPORT = 555782656;
    public static final int ID_INSTRUMENT_THEME_SUPPORT = 557856193;
    public static final int ID_INTELLIGENT_AC_AREA_SUPPORT = 557856075;
    public static final int ID_INTELLIGENT_KEEP_TEM_SUPPORT = 557856045;
    public static final int ID_INTELLIGENT_LOCK_SUPPORT = 557856123;
    public static final int ID_INTELLIGENT_SCREEN_CASTING_SWITCH_CONFIGURATION_R = 557859093;
    public static final int ID_INTELLIGENT_SPEED_LIMIT_MAP_VERSION_DISPLAY_R = 557856845;
    public static final int ID_INTELLIGENT_UNLOCK_SUPPORT = 557856122;
    public static final int ID_LAND_ADVANCE_AT_SUPPORT = 557856062;
    public static final int ID_LEAVE_HOME_DELAY_SUPPORT = 557856038;
    public static final int ID_LEFT_PLATFORM_CONFIGS = 557856023;
    public static final int ID_LEFT_REAR_MEMORY_CONFIGURATION_R = 557856203;
    public static final int ID_LEFT_REAR_SEAT_BACK_ADJUSTMENT_CONFIGURATION_R = 557856246;
    public static final int ID_LEFT_REAR_SEAT_HORIZONTAL_ADJUSTMENT_CONFIGURATION_R = 557856247;
    public static final int ID_LEFT_REAR_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_ECU_R = 557859153;
    public static final int ID_LEFT_REAR_SEAT_LEG_BRACKET_R = 557856248;
    public static final int ID_LEFT_REAR_SEAT_RECLINE_CONFIGURATIONR = 557856221;
    public static final int ID_LEFT_REAR_SEAT_RECLINE_CONFIGURATION_BY_ECU_R = 557856245;
    public static final int ID_LEFT_RES_CONFIGURATION_R = 557859090;
    public static final int ID_LEG_REST_ADJUSTMENT_SWITCH_CONFIG_ON_THE_LEFT_SIDE_OF_THE_THIRD_ROW_R = 557859135;
    public static final int ID_LEG_REST_ADJUSTMENT_SWITCH_CONFIG_ON_THE_RIGHT_SIDE_OF_THE_THIRD_ROW_R = 557859145;
    public static final int ID_LEVEL_ADJUSTMENT_SWITCH_CONFIG_ON_THE_LEFT_SIDE_OF_THE_THIRD_ROW_R = 557859133;
    public static final int ID_LEVEL_ADJUSTMENT_SWITCH_CONFIG_ON_THE_RIGHT_SIDE_OF_THE_THIRD_ROW_R = 557859143;
    public static final int ID_LOCK_CAR_RISE_WINDOW_SUPPORT = 557856116;
    public static final int ID_LOCK_OFF_SUPPORT = 557856120;
    public static final int ID_LOW_SPEED_EMERGENCY_BRAKING_CONFIG_R = 557859128;
    public static final int ID_L_D_A_DOUBLE_CONFIRM_R = 557859245;
    public static final int ID_MAIN_DRIVER_SEAT_ADJUSTMENT_CONGIGURATION_BY_G_CTRL_R = 557856228;
    public static final int ID_MAIN_DRIVER_SEAT_ADJUSTMENT_CONGIGURATION_BY_LEFT_BODY_CTRL_R = 557856225;
    public static final int ID_MAIN_DRIVE_ELECTRIC_HEADREST_ADJUSTMENT_BY_G_CTRL_R = 557856230;
    public static final int ID_MAIN_DRIVE_ELECTRIC_HEADREST_ADJUSTMENT_BY_LEFT_BODY_CTRL_R = 557856227;
    public static final int ID_MAIN_DRIVE_ELECTRIC_LEG_SUPPORT_ADJUSTMENT_BY_G_CTRL_R = 557856229;
    public static final int ID_MAIN_DRIVE_ELECTRIC_LEG_SUPPORT_ADJUSTMENT_BY_LEFT_BODY_CTRL_R = 557856226;
    public static final int ID_MAIN_SEAT_REMEMBER_SUPPORT_SECOND = 557856200;
    public static final int ID_MEMORY_PARKINGR = 557856217;
    public static final int ID_MICRO_SWITCH_LOCK_WINDOW_SUPPORT = 557856114;
    public static final int ID_MICRO_SWITCH_UNLOCK_WINDOW_SUPPORT = 557856115;
    public static final int ID_MILEAGE_RESET_CONFIG_R = 557859225;
    public static final int ID_MOIS_SUPPORT_R = 557859168;
    public static final int ID_MOTOR_PRETENSIONER_SEATBELT_SUPPORT = 557856058;
    public static final int ID_NON_ELEC_LEFT_PORT_CONFIG_R = 557859257;
    public static final int ID_NON_ELEC_RIGHT_PORT_CONFIG_R = 557859258;
    public static final int ID_NO_SENSE_CHARGE_SUPPORT_R = 557859082;
    public static final int ID_ONE_BUTTON_DOWN_CONFIGURATION_OF_THE_THIRD_ROW_LEFT_REAR_SEAT_R = 557859140;
    public static final int ID_ONE_BUTTON_DOWN_CONFIGURATION_OF_THE_THIRD_ROW_RIGHT_REAR_SEAT_R = 557859150;
    public static final int ID_ONE_BUTTON_DOWN_SWITCH_DISPLAY_FOR_THE_THIRD_ROW_OF_SEATS_R = 557859141;
    public static final int ID_OVERSPEED_LOCK_SUPPORT = 557856117;
    public static final int ID_O_B_C_TYPE = 557856048;
    public static final int ID_PANORAMIC_SUNROOF_CONFIGUTARION_R = 557862942;
    public static final int ID_PASSENGER_SEAT_ELECTRIC_ADJUSTMENT_CONFIGURATION_BY_ECU_R = 557856232;
    public static final int ID_PASSENGER_SEAT_HEADREST_CONFIGURATION_BY_ECU_R = 557856234;
    public static final int ID_PASSENGER_SEAT_HEADREST_CONFIGURATION_BY_G_CTRL_R = 557856236;
    public static final int ID_PASSENGER_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_ECU_R = 557859152;
    public static final int ID_PASSENGER_SEAT_LEG_SUPPORT_ADJUSTMENT_CONFIGURATION_BY_ECU_R = 557856233;
    public static final int ID_PASSENGER_SEAT_LEG_SUPPORT_ADJUSTMENT_CONFIGURATION_BY_G_CTRL_R = 557856235;
    public static final int ID_PASSENGER_SEAT_MEMORY_FUNCTION_CANR = 557856218;
    public static final int ID_PASSENGER_SEAT_MEMORY_FUNCTION_CAN_BY_ECU_R = 557856231;
    public static final int ID_POT_ADJUSTMENT_SWITCH_CONFIG_ON_THE_LEFT_SIDE_OF_THE_THIRD_ROW_R = 557859136;
    public static final int ID_POT_ADJUSTMENT_SWITCH_CONFIG_ON_THE_RIGHT_SIDE_OF_THE_THIRD_ROW_R = 557859146;
    public static final int ID_PRE_SALE_MODEL_SUPPORT = 557856000;
    public static final int ID_P_A_B_SWITCH_STATE_R = 557859112;
    public static final int ID_P_M25_POWER_SUPPORT = 557856068;
    public static final int ID_P_M25_SWITCH_CHECK_SUPPORT = 557856069;
    public static final int ID_P_M25_TIME_CKECK_SUPPORT = 557856070;
    public static final int ID_RAIN_AUTO_LOCK_WINDOW_SUPPORT = 557856118;
    public static final int ID_REARVIEW_MIRROR_SUPPORT = 557856147;
    public static final int ID_REAR_AC_MAX_WIND_LEVEL = 557856007;
    public static final int ID_REAR_FRIDGE_DISINFECT_FLAG_R = 557859206;
    public static final int ID_REAR_FRIDGE_KEY_TYPE_FLAG_R = 557859207;
    public static final int ID_REAR_FRIDGE_WORK_TYPE_FLAG_R = 557859208;
    public static final int ID_REAR_LEFT_BACKREST_ADJUSTMENT_CONFIGURATION_BY_ECU_R = 557856238;
    public static final int ID_REAR_LEFT_BIDET_ADJUSTMENT_CONFIGURATION_BY_ECU_R = 557856240;
    public static final int ID_REAR_LEFT_HEADREST_HEIGHT_ADJUSTMENT_CONFIGURATION_BY_ECU_R = 557856243;
    public static final int ID_REAR_LEFT_HEADREST_LEVEL_ADJUSTMENT_CONFIGURATION_BY_ECU_R = 557856242;
    public static final int ID_REAR_LEFT_HEIGHT_ADJUSTMENT_CONFIGURATION_BY_ECU_R = 557856241;
    public static final int ID_REAR_LEFT_LEG_SUPPORT_ADJUSTMENT_CONFIGURATION_BY_ECU_R = 557856239;
    public static final int ID_REAR_LEFT_LEVEL_ADJUSTMENT_CONFIGURATION_BY_ECU_R = 557856237;
    public static final int ID_REAR_LEFT_MEMORY_SWITCH_CONFIGURATION_BY_ECU_R = 557856244;
    public static final int ID_REAR_PANEL_LOCK_TIME_SUPPORT = 557856073;
    public static final int ID_REAR_RIGHT_BACKREST_ADJUSTMENT_CONFIGURATION_BY_ECU_R = 557856252;
    public static final int ID_REAR_RIGHT_BIDET_ADJUSTMENT_CONFIGURATION_BY_ECU_R = 557856254;
    public static final int ID_REAR_RIGHT_FRIDGE_HEATING_SCHEME_R = 557859209;
    public static final int ID_REAR_RIGHT_HEADREST_HEIGHT_ADJUSTMENT_CONFIGURATION_BY_ECU_R = 557859073;
    public static final int ID_REAR_RIGHT_HEADREST_LEVEL_ADJUSTMENT_CONFIGURATION_BY_ECU_R = 557859072;
    public static final int ID_REAR_RIGHT_HEIGHT_ADJUSTMENT_CONFIGURATION_BY_ECU_R = 557856255;
    public static final int ID_REAR_RIGHT_LEG_SUPPORT_ADJUSTMENT_CONFIGURATION_BY_ECU_R = 557856253;
    public static final int ID_REAR_RIGHT_LEVEL_ADJUSTMENT_CONFIGURATION_BY_ECU_R = 557856251;
    public static final int ID_REAR_RIGHT_MEMORY_SWITCH_CONFIGURATION_BY_ECU_R = 557856204;
    public static final int ID_REAR_SEAT_RECLINE_CONFIGURATIONR = 557856250;
    public static final int ID_REAR_VIEW_MIRROR_FLIP_SUPPORT_FIRST = 557856049;
    public static final int ID_REAR_VIEW_MIRROR_FLIP_SUPPORT_SECOND = 557856050;
    public static final int ID_RECENTLY50KM_CONFIG_R = 557859234;
    public static final int ID_REFRIGERATOR_MODE_SUPPORT = 557856170;
    public static final int ID_REMIND_SPEED_SUPPORT = 557856125;
    public static final int ID_REMIND_SPEED_SUPPORT_R = 557859119;
    public static final int ID_REMOTE_CONTROL_UNLOCK_WINDOW_SUPPORT = 557856113;
    public static final int ID_REMOTE_KEY_START_AC_TIME_SUPPORT = 557856063;
    public static final int ID_REPORT_CONTENT_SET_SUPPORT = 557856129;
    public static final int ID_REPORT_ITEMS_SET_SUPPORT_BELT = 557856133;
    public static final int ID_REPORT_ITEMS_SET_SUPPORT_BRAKE = 557856131;
    public static final int ID_REPORT_ITEMS_SET_SUPPORT_CAR = 557856134;
    public static final int ID_REPORT_ITEMS_SET_SUPPORT_CLOSE = 557856132;
    public static final int ID_REPORT_ITEMS_SET_SUPPORT_KEY = 557856136;
    public static final int ID_REPORT_ITEMS_SET_SUPPORT_LOW = 557856135;
    public static final int ID_REPORT_ITEMS_SET_SUPPORT_MODE = 557856139;
    public static final int ID_REPORT_ITEMS_SET_SUPPORT_OIL = 557856138;
    public static final int ID_REPORT_ITEMS_SET_SUPPORT_ON = 557856137;
    public static final int ID_REPORT_ITEMS_SET_SUPPORT_PARK = 557856140;
    public static final int ID_REPORT_ITEMS_SET_SUPPORT_POWER = 557856130;
    public static final int ID_REPORT_VOLUME_SUPPORT = 557856126;
    public static final int ID_RESET_BACK_AND_LEAVE_DELAY_SUPPORT = 557856039;
    public static final int ID_RESET_DAY_LIGHT = 557856042;
    public static final int ID_RESET_HEADLAMP = 557856036;
    public static final int ID_RESTORE_FACTORY_SETTINGS = 557857573;
    public static final int ID_REST_MODE_CONFIGURATION_R = 557859089;
    public static final int ID_RIGHT_REAR_MEMORY_CONFIGURATION_R = 557859079;
    public static final int ID_RIGHT_REAR_SEAT_BACK_ADJUSTMENT_CONFIGURATION_R = 557859076;
    public static final int ID_RIGHT_REAR_SEAT_HORIZONTAL_ADJUSTMENT_CONFIGURATION_R = 557859077;
    public static final int ID_RIGHT_REAR_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_ECU_R = 557859154;
    public static final int ID_RIGHT_REAR_SEAT_LEG_BRACKET_R = 557859078;
    public static final int ID_RIGHT_REAR_SEAT_RECLINE_CONFIGURATIONR = 557856220;
    public static final int ID_RIGHT_REAR_SEAT_RECLINE_CONFIGURATION_BY_EUC_R = 557859075;
    public static final int ID_RIGHT_RES_CONFIGURATION_R = 557859091;
    public static final int ID_RNC_STATE_S = 557859185;
    public static final int ID_RUNNING_CLOSE_DOOR_SUPPORT_R = 557859171;
    public static final int ID_RUNNING_CODING_CONFIG_R = 557859229;
    public static final int ID_SAND_MODE_ABS_CONFIG_R = 557859228;
    public static final int ID_SEATBACKREST_SUPPORT_BACK_L = 557856163;
    public static final int ID_SEATBACKREST_SUPPORT_BACK_R = 557856164;
    public static final int ID_SEATBACKREST_SUPPORT_SECOND = 557856161;
    public static final int ID_SEATBACKREST_SUPPORT_THIRD = 557856162;
    public static final int ID_SEAT_SET_SUPPORT_BACK_L = 557856144;
    public static final int ID_SEAT_SET_SUPPORT_BACK_R = 557856145;
    public static final int ID_SEAT_SET_SUPPORT_COPILOT = 557856143;
    public static final int ID_SEAT_SET_SUPPORT_FIRST = 557856141;
    public static final int ID_SEAT_SET_SUPPORT_SECOND = 557856142;
    public static final int ID_SEAT_TURN_HEIGHT_SUPPORT_BACK_L = 557856154;
    public static final int ID_SEAT_TURN_HEIGHT_SUPPORT_BACK_R = 557856155;
    public static final int ID_SEAT_TURN_HEIGHT_SUPPORT_COPILOT = 557856153;
    public static final int ID_SEAT_TURN_HEIGHT_SUPPORT_SECOND = 557856152;
    public static final int ID_SEAT_TURN_HORIZATIAL_SUPPORT_BACK_L = 557856150;
    public static final int ID_SEAT_TURN_HORIZATIAL_SUPPORT_BACK_R = 557856151;
    public static final int ID_SEAT_TURN_HORIZATIAL_SUPPORT_COPILOT = 557856149;
    public static final int ID_SEAT_TURN_HORIZATIAL_SUPPORT_SECOND = 557856148;
    public static final int ID_SECOND_SEAT_REMEMBER_SUPPORT = 557856201;
    public static final int ID_SEND_D_LINK_COUNTRY_CODE_S = 557856104;
    public static final int ID_SIDE_DISTANCE_WARNING_CONFIG_R = 557859129;
    public static final int ID_STAR_TOP_SWITCH_SETTING_R = 557859126;
    public static final int ID_STEERING_WHEEL_HEATING_CONFIGURATIONR = 557856215;
    public static final int ID_STEERING_WHEEL_SUPPORT = 557856146;
    public static final int ID_STEER_ASSIS_SUPPORT = 557856059;
    public static final int ID_STEER_POSITION_AUTO_RETURN_SUPPORT = 557856055;
    public static final int ID_SUPPORTI_T_A_C_CONFIG_R = 557859187;
    public static final int ID_SUPPORTI_T_A_C_R = 557859186;
    public static final int ID_SUPPORT_AC_AUTO_KEY_CAN_CLOSE = 557856016;
    public static final int ID_SUPPORT_AI_CARE = 557859117;
    public static final int ID_SUPPORT_AI_DISTANCE_LIGHT = 557856106;
    public static final int ID_SUPPORT_AI_EMERGENCY_BRAKE = 557856099;
    public static final int ID_SUPPORT_AI_MATRIX_LIGHT_FIRST = 557856107;
    public static final int ID_SUPPORT_AI_MATRIX_LIGHT_SECOND = 557856108;
    public static final int ID_SUPPORT_AUTO_PARKING = 557856102;
    public static final int ID_SUPPORT_A_C_CHARGING_LIMIT_R = 557859178;
    public static final int ID_SUPPORT_A_C_C_R = 557859255;
    public static final int ID_SUPPORT_A_FS = 557856033;
    public static final int ID_SUPPORT_A_F_S_R = 557859108;
    public static final int ID_SUPPORT_A_V_H_R = 557859163;
    public static final int ID_SUPPORT_BABY_MODE_CONFIG_R = 557859240;
    public static final int ID_SUPPORT_BLIND_SPOT_ASSIST_FIRST = 557856103;
    public static final int ID_SUPPORT_BLIND_SPOT_ASSIST_SECOND = 557856100;
    public static final int ID_SUPPORT_CAMP_MODE_CONFIG_R = 557859227;
    public static final int ID_SUPPORT_CHARGE_H_E_V_THREE_R = 557859213;
    public static final int ID_SUPPORT_CHARGE_H_E_V_THREE_V_C_U_R = 557859214;
    public static final int ID_SUPPORT_DIMMING_CANOPY_CONFIGURATION_R = 557859165;
    public static final int ID_SUPPORT_DOOR_OPEN_WARNING = 557856091;
    public static final int ID_SUPPORT_DOUBLE_CONFIRM_R = 557859177;
    public static final int ID_SUPPORT_EMERGENCY_LANE_KEEP_ASSIST = 557856086;
    public static final int ID_SUPPORT_END_TO_END_NAVIGATION = 557870367;
    public static final int ID_SUPPORT_ESP_R = 557859107;
    public static final int ID_SUPPORT_EXTREMER = 557856223;
    public static final int ID_SUPPORT_E_S_P = 557856110;
    public static final int ID_SUPPORT_FRAGRANCE = 557856027;
    public static final int ID_SUPPORT_FRAGRANCE_TYPE_ONE = 557856028;
    public static final int ID_SUPPORT_FRAGRANCE_TYPE_THREE = 557856030;
    public static final int ID_SUPPORT_FRAGRANCE_TYPE_TWO = 557856029;
    public static final int ID_SUPPORT_FRONT_CROSSING_TRAFFIC_ALERT = 557856094;
    public static final int ID_SUPPORT_FRONT_CROSSING_TRAFFIC_BRAKE = 557856095;
    public static final int ID_SUPPORT_FR_ELECTRIC_AIR_OUTLET_R = 557859094;
    public static final int ID_SUPPORT_F_C_S_R_R = 557859256;
    public static final int ID_SUPPORT_HIGHWAY_NAVIGATION = 557856079;
    public static final int ID_SUPPORT_HIGH_TEMPERATURE_STERILIZE = 557856026;
    public static final int ID_SUPPORT_INS_U_I_MODE_R = 557859182;
    public static final int ID_SUPPORT_INS_U_I_SHAPE_R = 557859184;
    public static final int ID_SUPPORT_INS_U_I_SIZE_R = 557859183;
    public static final int ID_SUPPORT_INTELLIGENT_NAVIGATION_FIRST = 557856077;
    public static final int ID_SUPPORT_INTELLIGENT_NAVIGATION_SECOND = 557856078;
    public static final int ID_SUPPORT_INTELLIGNET_SPEED_LIMIT_ALERT = 557856088;
    public static final int ID_SUPPORT_INTELLIGNET_SPEED_LIMIT_CONTROL = 557856089;
    public static final int ID_SUPPORT_I_O_P_R = 557859238;
    public static final int ID_SUPPORT_LAMP_SINGNAL = 557856032;
    public static final int ID_SUPPORT_LANE_CHANGE_ASSIST = 557856082;
    public static final int ID_SUPPORT_LANE_DEPARTURE_STOP = 557856085;
    public static final int ID_SUPPORT_LANE_DEPARTURE_WARNING = 557856083;
    public static final int ID_SUPPORT_LANE_KEEP = 557856084;
    public static final int ID_SUPPORT_MANUAL_MODE_R = 557859174;
    public static final int ID_SUPPORT_MISTAKE_GAS = 557856101;
    public static final int ID_SUPPORT_NEGATIVE_ION = 557856025;
    public static final int ID_SUPPORT_OIL_BLADE_R = 557859193;
    public static final int ID_SUPPORT_OIL_BRAKE_R = 557859191;
    public static final int ID_SUPPORT_OIL_COOL_R = 557859192;
    public static final int ID_SUPPORT_OIL_ENGINE_R = 557859189;
    public static final int ID_SUPPORT_OIL_TRANSMISSION_R = 557859190;
    public static final int ID_SUPPORT_PANORAMA_IMAGE = 557856109;
    public static final int ID_SUPPORT_PREDICT_CRASH_ALERT_MODE_FIRST = 557856097;
    public static final int ID_SUPPORT_PREDICT_CRASH_ALERT_MODE_SECOND = 557856098;
    public static final int ID_SUPPORT_P_M25_MODE = 557856024;
    public static final int ID_SUPPORT_REAR_AC = 557846530;
    public static final int ID_SUPPORT_REAR_AC_AUTO_CONTROL = 557856010;
    public static final int ID_SUPPORT_REAR_AC_COMPRESSOR = 557846536;
    public static final int ID_SUPPORT_REAR_AC_KEY_LOCK = 557846533;
    public static final int ID_SUPPORT_REAR_AC_SWITCH = 557846531;
    public static final int ID_SUPPORT_REAR_AC_TEMPERTURE_CONTROL = 557846537;
    public static final int ID_SUPPORT_REAR_AC_WIND_LEVEL_CONTROL = 557846538;
    public static final int ID_SUPPORT_REAR_AC_WIND_MODE_CONTROL = 557846534;
    public static final int ID_SUPPORT_REAR_COLLISION_WARNING = 557856092;
    public static final int ID_SUPPORT_REAR_CONTROL_PANEL_SIGN_R = 557859164;
    public static final int ID_SUPPORT_REAR_CROSS_BRAKE = 557856105;
    public static final int ID_SUPPORT_REAR_TRAFFIC_CROSSING_ALERT = 557856093;
    public static final int ID_SUPPORT_REMENBER_POWER_OFF = 557856171;
    public static final int ID_SUPPORT_REST_MODE_CONFIG_R = 557859226;
    public static final int ID_SUPPORT_REVERSE_RADAR = 557856044;
    public static final int ID_SUPPORT_ROAD_MODE_R = 557859179;
    public static final int ID_SUPPORT_R_N_C_R = 557859181;
    public static final int ID_SUPPORT_SEAT_MASSAGE_DEPUTY = 557856020;
    public static final int ID_SUPPORT_SEAT_MASSAGE_LEFT_REAR = 557856021;
    public static final int ID_SUPPORT_SEAT_MASSAGE_MAIN = 557856019;
    public static final int ID_SUPPORT_SEAT_MASSAGE_RIGHT_REAR = 557856022;
    public static final int ID_SUPPORT_SHAKE_ALERT_R = 557856083;
    public static final int ID_SUPPORT_SHAKE_ALERT_SECOND = 557856096;
    public static final int ID_SUPPORT_SHOW_CAR_MODEL = 557856111;
    public static final int ID_SUPPORT_SINGLE_DOUBLE_TEMP_AREA = 557856014;
    public static final int ID_SUPPORT_SMART_CHARGING_PHEVR = 557856224;
    public static final int ID_SUPPORT_SMART_CHARGING_PHEV_PHASE2R = 557859170;
    public static final int ID_SUPPORT_STEERING_WHEEL_WARM = 557856018;
    public static final int ID_SUPPORT_S_A_O_C_R = 557859239;
    public static final int ID_SUPPORT_S_L_I_F_R = 557859241;
    public static final int ID_SUPPORT_S_L_W_R = 557856112;
    public static final int ID_SUPPORT_S_M_R_M_R = 557859194;
    public static final int ID_SUPPORT_TRAFFIC_LIGHT_ATTENTION = 557856090;
    public static final int ID_SUPPORT_TRAFFIC_SIGN_RECOGNITON = 557856087;
    public static final int ID_SUPPORT_T_S_R_FLOW_R = 557859176;
    public static final int ID_SUPPORT_URBAN_NAVIGATION = 557859118;
    public static final int ID_SYS_FACTORY_RESET = 557903888;
    public static final int ID_S_O_C_TARGET_CONFIG_R = 557859215;
    public static final int ID_TAILGATE_TYPE_MARKING_R = 557859175;
    public static final int ID_THE_LAST50_K_M_AVERAGE_ENERGY_CONSUMPTION_INTERFACE_STATER = 557875206;
    public static final int ID_THE_LAST50_K_M_AVERAGE_FUEL_CONSUMPTION_INTERFACE_STATER = 557875203;
    public static final int ID_THE_LAST50_K_M_AVERAGE_POWER_CONSUMPTION_INTERFACE_STATER = 557875200;
    public static final int ID_TIRE_PRESSURE_INTERFACE_STATUSR = 557875228;
    public static final int ID_TOWING_CRUISE_CONFIGURATION_R = 557859261;
    public static final int ID_TRAILER_DR_MODE_CONFIG_R = 557859260;
    public static final int ID_TRAILER_MODE_CONFIG_R = 557859131;
    public static final int ID_TRAY_CONTROL_SUPPORT = 557856173;
    public static final int ID_TRLR_CRS_CONFIG_R = 557859262;
    public static final int ID_T_S_R_LIT_SPD_CHG_SWITCH_STATE_R = 557859115;
    public static final int ID_UPPORT_SEAT_MASSAGE_LEFT_BACK_ECU = 557856212;
    public static final int ID_UPPORT_SEAT_MASSAGE_RIGHT_BACK_ECU = 557856213;
    public static final int ID_USHER_LIGHT_SUPPORT = 557856040;
    public static final int ID_V2X_CONFIG_R = 557859130;
    public static final int ID_VALET_PARKINGR = 557856216;
    public static final int ID_VCU_ENCODED_R = 561004970;
    public static final int ID_WADING_MODE_SET_FLAG_R = 557859224;
    public static final int ID_WARNING_AUXILIARY_SWITCH_SETTING_R = 557859123;
    public static final int ID_WELCOME_CEREMONY_SWITCH_SETTING_R = 557859124;
    public static final int ID_WINDOW_ANTI_PINCH_CONFIG_FIRST = 557856208;
    public static final int ID_WINDOW_ANTI_PINCH_CONFIG_SECOND = 557856209;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.uistyle";
    public static final String TAG = "CAR.VENDOR.UISTYLE";

    public interface OnCarUistyleEventListener {
        void onUistyleErrorEvent(int i, int i2);

        void onUistyleEventChange(UistyleEventChangeValue uistyleEventChangeValue);
    }

    void addCarUistyleEventListener(OnCarUistyleEventListener onCarUistyleEventListener) throws CarNotConnectedException;

    int getACAutoAirSupport() throws CarNotConnectedException;

    int getACBTWindSupport() throws CarNotConnectedException;

    int getACPauseCycleSupport() throws CarNotConnectedException;

    int getACTunnelCycleSupport() throws CarNotConnectedException;

    int getADASSystemStateR() throws CarNotConnectedException;

    int getAcFaultNumShownState() throws CarNotConnectedException;

    int getAcType() throws CarNotConnectedException;

    int getAcWarmSupport() throws CarNotConnectedException;

    int getAcWarmTypeOnlineState() throws CarNotConnectedException;

    int getAcWindModeNum() throws CarNotConnectedException;

    int getAcceleratorPedalSuppressionSystemR() throws CarNotConnectedException;

    int getAebDoubleConfirmR() throws CarNotConnectedException;

    int getAllRearSeatOneButtonDownSwitchDisplayConfigR() throws CarNotConnectedException;

    int getAmbientAreaSupport() throws CarNotConnectedException;

    int getAmbientColorsSupport() throws CarNotConnectedException;

    int getAmbientLightColorSupport() throws CarNotConnectedException;

    int getAmbientLightSupport() throws CarNotConnectedException;

    int getAmbientModeSwitchSettingsR() throws CarNotConnectedException;

    int getAmbientMusicModeSupport() throws CarNotConnectedException;

    int getAmplifierConfigurationR() throws CarNotConnectedException;

    int getAppUseLimitSupport() throws CarNotConnectedException;

    int getAutoExternalRearMirrorFollowUpSwitchSupport() throws CarNotConnectedException;

    int getAutoPowerOffSupport() throws CarNotConnectedException;

    int getAutoPowerOnStateSupport() throws CarNotConnectedException;

    int getAutoPurificationSupport() throws CarNotConnectedException;

    int getAutoWiperSupport() throws CarNotConnectedException;

    int getAverageSpeedInterfaceStatusR() throws CarNotConnectedException;

    int getBackHomeDelaySupport() throws CarNotConnectedException;

    int getBackRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRowR() throws CarNotConnectedException;

    int getBackRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRowR() throws CarNotConnectedException;

    int getBatteryHealthConfigR() throws CarNotConnectedException;

    int getBrakePedalModeSupport() throws CarNotConnectedException;

    int getBsisSupportR() throws CarNotConnectedException;

    int getCabinMovementMonitoringFunctionR() throws CarNotConnectedException;

    int getCaliperSplintSupportBackL() throws CarNotConnectedException;

    int getCaliperSplintSupportBackR() throws CarNotConnectedException;

    int getCaliperSplintSupportCopilot() throws CarNotConnectedException;

    int getCaliperSplintSupportFirst() throws CarNotConnectedException;

    int getCaliperSplintSupportSecond() throws CarNotConnectedException;

    int getCaliperSplintSupportThird() throws CarNotConnectedException;

    int getCameraEerRomDeviceAddressR() throws CarNotConnectedException;

    int getCameraIspIdR() throws CarNotConnectedException;

    int getCameraLensIdR() throws CarNotConnectedException;

    int getCameraSerializerIdR() throws CarNotConnectedException;

    int getCameraSupplierAndTestBoardInfoR() throws CarNotConnectedException;

    int getCameraVideoFormatR() throws CarNotConnectedException;

    int getCameraVideoFrameRateR() throws CarNotConnectedException;

    int getCameraVideoResolutionR() throws CarNotConnectedException;

    int getCarDoorConfigR() throws CarNotConnectedException;

    int getCarFridgeTypeR() throws CarNotConnectedException;

    int getCarNum() throws CarNotConnectedException;

    int getCarPlanSupportR() throws CarNotConnectedException;

    int getCarTipsSoundSupport() throws CarNotConnectedException;

    int getCarType() throws CarNotConnectedException;

    int getChargeLimitSupport() throws CarNotConnectedException;

    int getChargeLimitSupportNotLiR() throws CarNotConnectedException;

    int getChargePreheatSupportR() throws CarNotConnectedException;

    int getChargeToaggleGuardSupport() throws CarNotConnectedException;

    int getComfortableParkingBy37fR() throws CarNotConnectedException;

    int getComfortableParkingSupport() throws CarNotConnectedException;

    int getComfortableSpeedUp() throws CarNotConnectedException;

    int getCpdSupportR() throws CarNotConnectedException;

    int getCpdSwMemStatusR() throws CarNotConnectedException;

    int getCrossDomainPlatformConfigsR() throws CarNotConnectedException;

    int getCumulativeAverageEnergyConsumptionInterfaceStatusR() throws CarNotConnectedException;

    int getCumulativeAverageFuelConsumptionInterfaceStatusR() throws CarNotConnectedException;

    int getCumulativeAveragePowerConsumptionInterfaceStatusR() throws CarNotConnectedException;

    int getCuraAssembleStatusFunctionR() throws CarNotConnectedException;

    int getCurrentTripConfigR() throws CarNotConnectedException;

    int getCushionSupportBackL() throws CarNotConnectedException;

    int getCushionSupportBackR() throws CarNotConnectedException;

    int getCushionSupportCopilot() throws CarNotConnectedException;

    int getCushionSupportSecond() throws CarNotConnectedException;

    int getCvcConfigR() throws CarNotConnectedException;

    int getDMSAssembleStatusR() throws CarNotConnectedException;

    int getDMType() throws CarNotConnectedException;

    int getDayLightSupport() throws CarNotConnectedException;

    int getDefrostRearConfig() throws CarNotConnectedException;

    int getDiDynaConfigR() throws CarNotConnectedException;

    int getDiSusShowStateByCanFdR() throws CarNotConnectedException;

    int getDiSusShowStateByCanR() throws CarNotConnectedException;

    int getDigtalKeySupport() throws CarNotConnectedException;

    int getDischargeSupport() throws CarNotConnectedException;

    int getDisinfSupport() throws CarNotConnectedException;

    int getDoorHeightSupport() throws CarNotConnectedException;

    int getDoorLightSupport() throws CarNotConnectedException;

    int getDoorLightSupportR() throws CarNotConnectedException;

    int getDoorknobAutoShrinkSupport() throws CarNotConnectedException;

    int getDriveConfig() throws CarNotConnectedException;

    int getDriveDistanceShowModeSupport() throws CarNotConnectedException;

    int getDriveDistanceShowStandardSupport() throws CarNotConnectedException;

    int getDriveSeatAutoReturnSupport() throws CarNotConnectedException;

    int getDriveTimeInterfaceStatusForSingleTripR() throws CarNotConnectedException;

    int getDriverSeatHotStoneMassageConfigurationByEcuR() throws CarNotConnectedException;

    int getDriverSeatHotStoneMassageConfigurationByLeftCtrlR() throws CarNotConnectedException;

    int getDriverSeatHotStoneMassageConfigurationByUniversalCtrlR() throws CarNotConnectedException;

    int getDrivingRangeInterfaceStatusForFuelR() throws CarNotConnectedException;

    int getDrivingVoiceBroadcastSwitchConfigOneR() throws CarNotConnectedException;

    int getDrivingVoiceBroadcastSwitchConfigThreeR() throws CarNotConnectedException;

    int getDrivingVoiceBroadcastSwitchConfigTwoR() throws CarNotConnectedException;

    int getDynamicSuspensionModeByCanFdR() throws CarNotConnectedException;

    int getDynamicSuspensionModeByCanR() throws CarNotConnectedException;

    int getDynamicSuspensionModeSupport() throws CarNotConnectedException;

    int getEPedalStateSupport() throws CarNotConnectedException;

    int getElecWhereConfigR() throws CarNotConnectedException;

    int getElectricFrontHatchConfigurationR() throws CarNotConnectedException;

    int getElectroniChildLockConfigFirst() throws CarNotConnectedException;

    int getElectroniChildLockConfigSecond() throws CarNotConnectedException;

    int getElectronicParkingBrakeConfigutarionR() throws CarNotConnectedException;

    int getElkaDoubleConfirmR() throws CarNotConnectedException;

    int getEnergyContrastConfigR() throws CarNotConnectedException;

    int getEnergyFeedbackSupportR() throws CarNotConnectedException;

    int getEnergyFlowDiagramInterfaceStatusR() throws CarNotConnectedException;

    int getEnergyShowConfigR() throws CarNotConnectedException;

    int getEnergyShowSupportR() throws CarNotConnectedException;

    int getEnergyStatus() throws CarNotConnectedException;

    int getEngineSoundSupport() throws CarNotConnectedException;

    int getExclusiveSaveFlagR() throws CarNotConnectedException;

    int getExternalChargingPowerInterfaceStatusR() throws CarNotConnectedException;

    int getFcwDoubleConfirmR() throws CarNotConnectedException;

    int getFeaturesStateR() throws CarNotConnectedException;

    int getFoldDownTheSecondRowOfSeatsConfigR() throws CarNotConnectedException;

    int getFridgeChildLockFlagR() throws CarNotConnectedException;

    int getFridgeDelayedPowerOutageR() throws CarNotConnectedException;

    int getFridgeExpansionAndContractionFunctionR() throws CarNotConnectedException;

    int getFridgeMinWagePowerGenerationR() throws CarNotConnectedException;

    int getFridgeNumR() throws CarNotConnectedException;

    int getFridgeOnePositionR() throws CarNotConnectedException;

    int getFridgeScreenFlagR() throws CarNotConnectedException;

    int getFridgeTemperatureRegulationR() throws CarNotConnectedException;

    int getFridgeThreePositionR() throws CarNotConnectedException;

    int getFridgeTwoPositionR() throws CarNotConnectedException;

    int getFridgeWorkingModeR() throws CarNotConnectedException;

    int getFrontFridgeDisinfectFlagR() throws CarNotConnectedException;

    int getFrontLightDimmingCtrlConfigBy3acR() throws CarNotConnectedException;

    int getFrontLightDimmingCtrlConfigBy3adR() throws CarNotConnectedException;

    int getFuelConsumptionInterfaceStatusR() throws CarNotConnectedException;

    int getGuestLeftSeatRememeSupportFirstL() throws CarNotConnectedException;

    int getGuestLeftSeatRememeSupportSecondL() throws CarNotConnectedException;

    int getGuestRightSeatRememeSupportFirstR() throws CarNotConnectedException;

    int getGuestRightSeatRememeSupportSecondR() throws CarNotConnectedException;

    int getHUDModeConfigurationR() throws CarNotConnectedException;

    int getHasPredictCrashAlertModeR() throws CarNotConnectedException;

    int getHeadlampLevelConfig() throws CarNotConnectedException;

    int getHeadlampSupport() throws CarNotConnectedException;

    int getHeadrestHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRowR() throws CarNotConnectedException;

    int getHeadrestHightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRowR() throws CarNotConnectedException;

    int getHeadrestLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRowR() throws CarNotConnectedException;

    int getHeadrestLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRowR() throws CarNotConnectedException;

    int getHeadrestTurnHeightSupportBackL() throws CarNotConnectedException;

    int getHeadrestTurnHeightSupportBackR() throws CarNotConnectedException;

    int getHeadrestTurnHeightSupportCopilot() throws CarNotConnectedException;

    int getHeadrestTurnHeightSupportSecond() throws CarNotConnectedException;

    int getHeadrestTurnHeightSupportThird() throws CarNotConnectedException;

    int getHeadrestTurnHorizatialSupportBackL() throws CarNotConnectedException;

    int getHeadrestTurnHorizatialSupportBackR() throws CarNotConnectedException;

    int getHeadrestTurnHorizatialSupportCopilot() throws CarNotConnectedException;

    int getHeadrestTurnHorizatialSupportFirst() throws CarNotConnectedException;

    int getHeadrestTurnHorizatialSupportSecond() throws CarNotConnectedException;

    int getHeightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRowR() throws CarNotConnectedException;

    int getHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRowR() throws CarNotConnectedException;

    int getHistoryEnergyConfigR() throws CarNotConnectedException;

    int getHistoryEnergyStatisticsConfigR() throws CarNotConnectedException;

    int getHudHeightAdjustSupport() throws CarNotConnectedException;

    int getHudLightSdjustSupport() throws CarNotConnectedException;

    int getHudParamsStateSupport() throws CarNotConnectedException;

    int getHudShowParamsSupport() throws CarNotConnectedException;

    int getInstantaneousFuelConsumptionInterfaceStatusR() throws CarNotConnectedException;

    int getInstrumentContentSupport() throws CarNotConnectedException;

    boolean getInstrumentHeightSupport() throws CarNotConnectedException;

    int getInstrumentThemeSupport() throws CarNotConnectedException;

    int getIntelligentAcAreaSupport() throws CarNotConnectedException;

    int getIntelligentKeepTemSupport() throws CarNotConnectedException;

    int getIntelligentLockSupport() throws CarNotConnectedException;

    int getIntelligentScreenCastingSwitchConfigurationR() throws CarNotConnectedException;

    int getIntelligentSpeedLimitMapVersionDisplayR() throws CarNotConnectedException;

    int getIntelligentUnlockSupport() throws CarNotConnectedException;

    int getLandAdvanceAtSupport() throws CarNotConnectedException;

    int getLdaDoubleConfirmR() throws CarNotConnectedException;

    int getLeaveHomeDelaySupport() throws CarNotConnectedException;

    int getLeftPlatformConfigs() throws CarNotConnectedException;

    int getLeftRESConfigurationR() throws CarNotConnectedException;

    int getLeftRearMemoryConfigurationR() throws CarNotConnectedException;

    int getLeftRearSeatBackAdjustmentConfigurationR() throws CarNotConnectedException;

    int getLeftRearSeatHorizontalAdjustmentConfigurationR() throws CarNotConnectedException;

    int getLeftRearSeatHotStoneMassageConfigurationByECUR() throws CarNotConnectedException;

    int getLeftRearSeatHotStoneMassageConfigurationByEcuR() throws CarNotConnectedException;

    int getLeftRearSeatLegBracketR() throws CarNotConnectedException;

    int getLeftRearSeatReclineConfigurationByECUR() throws CarNotConnectedException;

    int getLeftRearSeatReclineConfigurationR() throws CarNotConnectedException;

    int getLegRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRowR() throws CarNotConnectedException;

    int getLegRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRowR() throws CarNotConnectedException;

    int getLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRowR() throws CarNotConnectedException;

    int getLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRowR() throws CarNotConnectedException;

    int getLockCarRiseWindowSupport() throws CarNotConnectedException;

    int getLockOffSupport() throws CarNotConnectedException;

    int getLowSpeedEmergencyBrakingConfigR() throws CarNotConnectedException;

    int getMainDriveElectricHeadrestAdjustmentByGCtrlR() throws CarNotConnectedException;

    int getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrlR() throws CarNotConnectedException;

    int getMainDriveElectricLegSupportAdjustmentByGCtrlR() throws CarNotConnectedException;

    int getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrlR() throws CarNotConnectedException;

    int getMainDriverSeatAdjustmentConfigurationByGCtrlR() throws CarNotConnectedException;

    int getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrlR() throws CarNotConnectedException;

    int getMainSeatRememberSupportSecond() throws CarNotConnectedException;

    int getMemoryParkingR() throws CarNotConnectedException;

    int getMicroSwitchLockWindowSupport() throws CarNotConnectedException;

    int getMicroSwitchUnlockWindowSupport() throws CarNotConnectedException;

    int getMileageResetConfigR() throws CarNotConnectedException;

    int getMoisSupportR() throws CarNotConnectedException;

    int getMotorPretensionerSeatbeltSupport() throws CarNotConnectedException;

    int getNoSenseChargeSupportR() throws CarNotConnectedException;

    int getNonElecLeftPortConfigR() throws CarNotConnectedException;

    int getNonElecRightPortConfigR() throws CarNotConnectedException;

    int getOBCType() throws CarNotConnectedException;

    int getOneButtonDownConfigurationOfTheThirdRowLeftRearSeatR() throws CarNotConnectedException;

    int getOneButtonDownConfigurationOfTheThirdRowRightRearSeatR() throws CarNotConnectedException;

    int getOneButtonDownSwitchDisplayForTheThirdRowOfSeatsR() throws CarNotConnectedException;

    int getOverspeedLockSupport() throws CarNotConnectedException;

    int getPM25PowerSupport() throws CarNotConnectedException;

    int getPM25SwitchCheckSupport() throws CarNotConnectedException;

    int getPM25TimeCkeckSupport() throws CarNotConnectedException;

    int getPabSwitchStateR() throws CarNotConnectedException;

    int getPanoramicSunroofConfigutarionR() throws CarNotConnectedException;

    int getPassengerSeatElectricAdjustmentConfigurationByECUR() throws CarNotConnectedException;

    int getPassengerSeatHeadrestConfigurationByECUR() throws CarNotConnectedException;

    int getPassengerSeatHeadrestConfigurationByGCtrlR() throws CarNotConnectedException;

    int getPassengerSeatHotStoneMassageConfigurationByECUR() throws CarNotConnectedException;

    int getPassengerSeatHotStoneMassageConfigurationByEcuR() throws CarNotConnectedException;

    int getPassengerSeatLegSupportAdjustmentConfigurationByECUR() throws CarNotConnectedException;

    int getPassengerSeatLegSupportAdjustmentConfigurationByGCtrlR() throws CarNotConnectedException;

    int getPassengerSeatMemoryFunctionCanByECUR() throws CarNotConnectedException;

    int getPassengerSeatMemoryFunctionCanR() throws CarNotConnectedException;

    int getPotAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRowR() throws CarNotConnectedException;

    int getPotAdjustmentSwitchConfigOnTheRightSideOfTheThirdRowR() throws CarNotConnectedException;

    int getPreSaleModelSupport() throws CarNotConnectedException;

    int getRainAutoLockWindowSupport() throws CarNotConnectedException;

    int getRearAcMaxWindLevel() throws CarNotConnectedException;

    int getRearFridgeDisinfectFlagR() throws CarNotConnectedException;

    int getRearFridgeKeyTypeFlagR() throws CarNotConnectedException;

    int getRearFridgeWorkTypeFlagR() throws CarNotConnectedException;

    int getRearLeftBackrestAdjustmentConfigurationByECUR() throws CarNotConnectedException;

    int getRearLeftBidetAdjustmentConfigurationByECUR() throws CarNotConnectedException;

    int getRearLeftHeadrestHeightAdjustmentConfigurationByECUR() throws CarNotConnectedException;

    int getRearLeftHeadrestLevelAdjustmentConfigurationByECUR() throws CarNotConnectedException;

    int getRearLeftHeightAdjustmentConfigurationByECUR() throws CarNotConnectedException;

    int getRearLeftLegSupportAdjustmentConfigurationByECUR() throws CarNotConnectedException;

    int getRearLeftLevelAdjustmentConfigurationByECUR() throws CarNotConnectedException;

    int getRearLeftMemorySwitchConfigurationByECUR() throws CarNotConnectedException;

    int getRearPanelLockTimeSupport() throws CarNotConnectedException;

    int getRearRightBackrestAdjustmentConfigurationByECUR() throws CarNotConnectedException;

    int getRearRightBidetAdjustmentConfigurationByECUR() throws CarNotConnectedException;

    int getRearRightFridgeHeatingSchemeR() throws CarNotConnectedException;

    int getRearRightHeadrestHeightAdjustmentConfigurationByECUR() throws CarNotConnectedException;

    int getRearRightHeadrestLevelAdjustmentConfigurationByECUR() throws CarNotConnectedException;

    int getRearRightHeightAdjustmentConfigurationByECUR() throws CarNotConnectedException;

    int getRearRightLegSupportAdjustmentConfigurationByECUR() throws CarNotConnectedException;

    int getRearRightLevelAdjustmentConfigurationByECUR() throws CarNotConnectedException;

    int getRearRightMemorySwitchConfigurationByECUR() throws CarNotConnectedException;

    int getRearSeatReclineConfigurationR() throws CarNotConnectedException;

    int getRearViewMirrorFlipSupportFirst() throws CarNotConnectedException;

    int getRearViewMirrorFlipSupportSecond() throws CarNotConnectedException;

    int getRearviewMirrorSupport() throws CarNotConnectedException;

    int getRecently50kmConfigR() throws CarNotConnectedException;

    int getRefrigeratorModeSupport() throws CarNotConnectedException;

    int getRemindSpeedSupport() throws CarNotConnectedException;

    int getRemindSpeedSupportR() throws CarNotConnectedException;

    int getRemoteControlUnlockWindowSupport() throws CarNotConnectedException;

    int getRemoteKeyStartAcTimeSupport() throws CarNotConnectedException;

    int getReportContentSetSupport() throws CarNotConnectedException;

    int getReportItemsSetSupportBelt() throws CarNotConnectedException;

    int getReportItemsSetSupportBrake() throws CarNotConnectedException;

    int getReportItemsSetSupportCar() throws CarNotConnectedException;

    int getReportItemsSetSupportClose() throws CarNotConnectedException;

    int getReportItemsSetSupportKey() throws CarNotConnectedException;

    int getReportItemsSetSupportLow() throws CarNotConnectedException;

    int getReportItemsSetSupportMode() throws CarNotConnectedException;

    int getReportItemsSetSupportOil() throws CarNotConnectedException;

    int getReportItemsSetSupportOn() throws CarNotConnectedException;

    int getReportItemsSetSupportPark() throws CarNotConnectedException;

    int getReportItemsSetSupportPower() throws CarNotConnectedException;

    int getReportVolumeSupport() throws CarNotConnectedException;

    int getRestModeConfigurationR() throws CarNotConnectedException;

    int getRightRESConfigurationR() throws CarNotConnectedException;

    int getRightRearMemoryConfigurationR() throws CarNotConnectedException;

    int getRightRearSeatBackAdjustmentConfigurationR() throws CarNotConnectedException;

    int getRightRearSeatHorizontalAdjustmentConfigurationR() throws CarNotConnectedException;

    int getRightRearSeatHotStoneMassageConfigurationByECUR() throws CarNotConnectedException;

    int getRightRearSeatHotStoneMassageConfigurationByEcuR() throws CarNotConnectedException;

    int getRightRearSeatLegBracketR() throws CarNotConnectedException;

    int getRightRearSeatReclineConfigurationByEUCR() throws CarNotConnectedException;

    int getRightRearSeatReclineConfigurationR() throws CarNotConnectedException;

    int getRunningCloseDoorSupportR() throws CarNotConnectedException;

    int getRunningCodingConfigR() throws CarNotConnectedException;

    int getSandModeAbsConfigR() throws CarNotConnectedException;

    int getSeatSetSupportBackL() throws CarNotConnectedException;

    int getSeatSetSupportBackR() throws CarNotConnectedException;

    int getSeatSetSupportCopilot() throws CarNotConnectedException;

    int getSeatSetSupportFirst() throws CarNotConnectedException;

    int getSeatSetSupportSecond() throws CarNotConnectedException;

    int getSeatTurnHeightSupportBackL() throws CarNotConnectedException;

    int getSeatTurnHeightSupportBackR() throws CarNotConnectedException;

    int getSeatTurnHeightSupportCopilot() throws CarNotConnectedException;

    int getSeatTurnHeightSupportSecond() throws CarNotConnectedException;

    int getSeatTurnHorizatialSupportBackL() throws CarNotConnectedException;

    int getSeatTurnHorizatialSupportBackR() throws CarNotConnectedException;

    int getSeatTurnHorizatialSupportCopilot() throws CarNotConnectedException;

    int getSeatTurnHorizatialSupportSecond() throws CarNotConnectedException;

    int getSeatbackrestSupportBackL() throws CarNotConnectedException;

    int getSeatbackrestSupportBackR() throws CarNotConnectedException;

    int getSeatbackrestSupportSecond() throws CarNotConnectedException;

    int getSeatbackrestSupportThird() throws CarNotConnectedException;

    int getSecondSeatRememberSupport() throws CarNotConnectedException;

    int getSideDistanceWarningConfigR() throws CarNotConnectedException;

    int getStarTopSwitchSettingR() throws CarNotConnectedException;

    int getSteerAssisSupport() throws CarNotConnectedException;

    int getSteerPositionAutoReturnSupport() throws CarNotConnectedException;

    int getSteeringWheelHeatingConfigurationR() throws CarNotConnectedException;

    int getSteeringWheelSupport() throws CarNotConnectedException;

    int getSupportAFS() throws CarNotConnectedException;

    int getSupportAcAutoKeyCanClose() throws CarNotConnectedException;

    int getSupportAcChargingLimitR() throws CarNotConnectedException;

    int getSupportAccR() throws CarNotConnectedException;

    int getSupportAfsR() throws CarNotConnectedException;

    int getSupportAiCare() throws CarNotConnectedException;

    int getSupportAiDistanceLight() throws CarNotConnectedException;

    int getSupportAiEmergencyBrake() throws CarNotConnectedException;

    int getSupportAiMatrixLightFirst() throws CarNotConnectedException;

    int getSupportAiMatrixLightSecond() throws CarNotConnectedException;

    int getSupportAutoParking() throws CarNotConnectedException;

    int getSupportAvhR() throws CarNotConnectedException;

    int getSupportBabyModeConfigR() throws CarNotConnectedException;

    int getSupportBlindSpotAssistFirst() throws CarNotConnectedException;

    int getSupportBlindSpotAssistSecond() throws CarNotConnectedException;

    int getSupportCampModeConfigR() throws CarNotConnectedException;

    int getSupportChargeHEVThreeR() throws CarNotConnectedException;

    int getSupportChargeHEVThreeVCUR() throws CarNotConnectedException;

    int getSupportDimmingCanopyConfigurationR() throws CarNotConnectedException;

    int getSupportDoorOpenWarning() throws CarNotConnectedException;

    int getSupportDoubleConfirmR() throws CarNotConnectedException;

    int getSupportESP() throws CarNotConnectedException;

    int getSupportESPR() throws CarNotConnectedException;

    int getSupportEmergencyLaneKeepAssist() throws CarNotConnectedException;

    int getSupportEndToEndNavigation() throws CarNotConnectedException;

    int getSupportExtremeR() throws CarNotConnectedException;

    int getSupportFRElectricAirOutletR() throws CarNotConnectedException;

    int getSupportFcsrR() throws CarNotConnectedException;

    int getSupportFragrance() throws CarNotConnectedException;

    int getSupportFragranceTypeOne() throws CarNotConnectedException;

    int getSupportFragranceTypeThree() throws CarNotConnectedException;

    int getSupportFragranceTypeTwo() throws CarNotConnectedException;

    int getSupportFrontCrossingTrafficAlert() throws CarNotConnectedException;

    int getSupportFrontCrossingTrafficBrake() throws CarNotConnectedException;

    int getSupportHighTemperatureSterilize() throws CarNotConnectedException;

    int getSupportHighwayNavigation() throws CarNotConnectedException;

    int getSupportInsUIModeR() throws CarNotConnectedException;

    int getSupportInsUIShapeR() throws CarNotConnectedException;

    int getSupportInsUISizeR() throws CarNotConnectedException;

    int getSupportIntelligentNavigationFirst() throws CarNotConnectedException;

    int getSupportIntelligentNavigationSecond() throws CarNotConnectedException;

    int getSupportIntellignetSpeedLimitAlert() throws CarNotConnectedException;

    int getSupportIntellignetSpeedLimitControl() throws CarNotConnectedException;

    int getSupportIopR() throws CarNotConnectedException;

    int getSupportLampSingnal() throws CarNotConnectedException;

    int getSupportLaneChangeAssist() throws CarNotConnectedException;

    int getSupportLaneDepartureStop() throws CarNotConnectedException;

    int getSupportLaneDepartureWarning() throws CarNotConnectedException;

    int getSupportLaneKeep() throws CarNotConnectedException;

    int getSupportManualModelR() throws CarNotConnectedException;

    int getSupportMistakeGas() throws CarNotConnectedException;

    int getSupportNegativeIon() throws CarNotConnectedException;

    int getSupportOilBladeR() throws CarNotConnectedException;

    int getSupportOilBrakeR() throws CarNotConnectedException;

    int getSupportOilCoolR() throws CarNotConnectedException;

    int getSupportOilEngineR() throws CarNotConnectedException;

    int getSupportOilTransmissionR() throws CarNotConnectedException;

    int getSupportPM25Mode() throws CarNotConnectedException;

    int getSupportPanoramaImage() throws CarNotConnectedException;

    int getSupportPredictCrashAlertModeFirst() throws CarNotConnectedException;

    int getSupportPredictCrashAlertModeSecond() throws CarNotConnectedException;

    int getSupportRNCR() throws CarNotConnectedException;

    int getSupportRearAc() throws CarNotConnectedException;

    int getSupportRearAcAutoControl() throws CarNotConnectedException;

    int getSupportRearAcCompressor() throws CarNotConnectedException;

    int getSupportRearAcKeyLock() throws CarNotConnectedException;

    int getSupportRearAcSwitch() throws CarNotConnectedException;

    int getSupportRearAcTempertureControl() throws CarNotConnectedException;

    int getSupportRearAcWindLevelControl() throws CarNotConnectedException;

    int getSupportRearAcWindModeControl() throws CarNotConnectedException;

    int getSupportRearCollisionWarning() throws CarNotConnectedException;

    int getSupportRearControlPanelSignR() throws CarNotConnectedException;

    int getSupportRearCrossBrake() throws CarNotConnectedException;

    int getSupportRearTrafficCrossingAlert() throws CarNotConnectedException;

    int getSupportRemenberPowerOff() throws CarNotConnectedException;

    int getSupportRestModeConfigR() throws CarNotConnectedException;

    int getSupportReverseRadar() throws CarNotConnectedException;

    int getSupportRoadModeR() throws CarNotConnectedException;

    int getSupportSaocR() throws CarNotConnectedException;

    int getSupportSeatMassageDeputy() throws CarNotConnectedException;

    int getSupportSeatMassageLeftBackECU() throws CarNotConnectedException;

    int getSupportSeatMassageLeftRear() throws CarNotConnectedException;

    int getSupportSeatMassageMain() throws CarNotConnectedException;

    int getSupportSeatMassageRightBackECU() throws CarNotConnectedException;

    int getSupportSeatMassageRightRear() throws CarNotConnectedException;

    int getSupportShakeAlertR() throws CarNotConnectedException;

    int getSupportShakeAlertSecond() throws CarNotConnectedException;

    int getSupportShowCarModel() throws CarNotConnectedException;

    int getSupportSingleDoubleTempArea() throws CarNotConnectedException;

    int getSupportSlifR() throws CarNotConnectedException;

    int getSupportSlwR() throws CarNotConnectedException;

    int getSupportSmartChargingPHEVR() throws CarNotConnectedException;

    int getSupportSmartChargingPhevPhase2R() throws CarNotConnectedException;

    int getSupportSmrmR() throws CarNotConnectedException;

    int getSupportSteeringWheelWarm() throws CarNotConnectedException;

    int getSupportTrafficLightAttention() throws CarNotConnectedException;

    int getSupportTrafficSignRecogniton() throws CarNotConnectedException;

    int getSupportTsrFlowR() throws CarNotConnectedException;

    int getSupportUrbanNavigation() throws CarNotConnectedException;

    int getSupportiTacConfigR() throws CarNotConnectedException;

    int getSupportiTacR() throws CarNotConnectedException;

    int getTailgateTypeMarkingR() throws CarNotConnectedException;

    int getTheLast50KMAverageEnergyConsumptionInterfaceStateR() throws CarNotConnectedException;

    int getTheLast50KMAverageFuelConsumptionInterfaceStateR() throws CarNotConnectedException;

    int getTheLast50KMAveragePowerConsumptionInterfaceStateR() throws CarNotConnectedException;

    int getTirePressureInterfaceStatusR() throws CarNotConnectedException;

    int getTowingCruiseConfigurationR() throws CarNotConnectedException;

    int getTrailerDrModeConfigR() throws CarNotConnectedException;

    int getTrailerModeConfigR() throws CarNotConnectedException;

    int getTrayControlSupport() throws CarNotConnectedException;

    int getTrlrCrsConfigR() throws CarNotConnectedException;

    int getTrunkConfigutarionR() throws CarNotConnectedException;

    int getTsrLitSpdChgSwitchStateR() throws CarNotConnectedException;

    int getUsherLightSupport() throws CarNotConnectedException;

    int getV2xConfigR() throws CarNotConnectedException;

    int getValetParkingR() throws CarNotConnectedException;

    byte[] getVcuEncodedR() throws CarNotConnectedException;

    int getWadingModeSetFlagR() throws CarNotConnectedException;

    int getWarningAuxiliarySwitchSettingR() throws CarNotConnectedException;

    int getWelcomeCeremonySwitchSettingR() throws CarNotConnectedException;

    int getWindowAntiPinchConfig() throws CarNotConnectedException;

    int gethevCarPlanSupportR() throws CarNotConnectedException;

    int getresetHeadlamp() throws CarNotConnectedException;

    int getsocTargetConfigR() throws CarNotConnectedException;

    void removeCarUistyleEventListener(OnCarUistyleEventListener onCarUistyleEventListener) throws CarNotConnectedException;

    void sendDLinkCountryCodeS(int i) throws CarNotConnectedException;

    void setResetAmbientSettings(int i) throws CarNotConnectedException;

    void setResetBackAndLeaveDelaySupport(int i) throws CarNotConnectedException;

    void setResetDayLight(int i) throws CarNotConnectedException;

    void setRncStateS(int i) throws CarNotConnectedException;

    void setSysFactoryReset(int i) throws CarNotConnectedException;

    void setrestoreFactorySettings(int i) throws CarNotConnectedException;
}

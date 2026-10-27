package com.ts.lib.car.hardware.setting;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes2.dex */
public interface CarVendorSettingManager {
    public static final int ID_5_LEVEL_SEAT_HEATING_LEVEL_BACK_LEFT_R = 557857912;
    public static final int ID_5_LEVEL_SEAT_HEATING_LEVEL_BACK_LEFT_S = 557857916;
    public static final int ID_5_LEVEL_SEAT_HEATING_LEVEL_BACK_RIGHT_R = 557857913;
    public static final int ID_5_LEVEL_SEAT_HEATING_LEVEL_BACK_RIGHT_S = 557857917;
    public static final int ID_5_LEVEL_SEAT_HEATING_LEVEL_DOMAIN_R = 557857910;
    public static final int ID_5_LEVEL_SEAT_HEATING_LEVEL_DOMAIN_S = 557857914;
    public static final int ID_5_LEVEL_SEAT_HEATING_LEVEL_PASSENGER_R = 557857911;
    public static final int ID_5_LEVEL_SEAT_HEATING_LEVEL_PASSENGER_S = 557857915;
    public static final int ID_5_LEVEL_SEAT_HEATING_MODE_BACK_LEFT_R = 557857896;
    public static final int ID_5_LEVEL_SEAT_HEATING_MODE_BACK_LEFT_S = 557857900;
    public static final int ID_5_LEVEL_SEAT_HEATING_MODE_BACK_RIGHT_R = 557857897;
    public static final int ID_5_LEVEL_SEAT_HEATING_MODE_BACK_RIGHT_S = 557857901;
    public static final int ID_5_LEVEL_SEAT_HEATING_MODE_DOMAIN_R = 557857894;
    public static final int ID_5_LEVEL_SEAT_HEATING_MODE_DOMAIN_S = 557857898;
    public static final int ID_5_LEVEL_SEAT_HEATING_MODE_PASSENGER_R = 557857895;
    public static final int ID_5_LEVEL_SEAT_HEATING_MODE_PASSENGER_S = 557857899;
    public static final int ID_5_LEVEL_SEAT_HEATING_STATE_BACK_LEFT_R = 557857880;
    public static final int ID_5_LEVEL_SEAT_HEATING_STATE_BACK_LEFT_S = 557857884;
    public static final int ID_5_LEVEL_SEAT_HEATING_STATE_BACK_RIGHT_R = 557857881;
    public static final int ID_5_LEVEL_SEAT_HEATING_STATE_BACK_RIGHT_S = 557857885;
    public static final int ID_5_LEVEL_SEAT_HEATING_STATE_DOMAIN_R = 557857878;
    public static final int ID_5_LEVEL_SEAT_HEATING_STATE_DOMAIN_S = 557857882;
    public static final int ID_5_LEVEL_SEAT_HEATING_STATE_PASSENGER_R = 557857879;
    public static final int ID_5_LEVEL_SEAT_HEATING_STATE_PASSENGER_S = 557857883;
    public static final int ID_5_LEVEL_SEAT_VENTILATING_LEVEL_BACK_LEFT_R = 557857904;
    public static final int ID_5_LEVEL_SEAT_VENTILATING_LEVEL_BACK_LEFT_S = 557857908;
    public static final int ID_5_LEVEL_SEAT_VENTILATING_LEVEL_BACK_RIGHT_R = 557857905;
    public static final int ID_5_LEVEL_SEAT_VENTILATING_LEVEL_BACK_RIGHT_S = 557857909;
    public static final int ID_5_LEVEL_SEAT_VENTILATING_LEVEL_DOMAIN_R = 557857902;
    public static final int ID_5_LEVEL_SEAT_VENTILATING_LEVEL_DOMAIN_S = 557857906;
    public static final int ID_5_LEVEL_SEAT_VENTILATING_LEVEL_PASSENGER_R = 557857903;
    public static final int ID_5_LEVEL_SEAT_VENTILATING_LEVEL_PASSENGER_S = 557857907;
    public static final int ID_5_LEVEL_SEAT_VENTILATING_MODE_BACK_LEFT_R = 557857888;
    public static final int ID_5_LEVEL_SEAT_VENTILATING_MODE_BACK_LEFT_S = 557857892;
    public static final int ID_5_LEVEL_SEAT_VENTILATING_MODE_BACK_RIGHT_R = 557857889;
    public static final int ID_5_LEVEL_SEAT_VENTILATING_MODE_BACK_RIGHT_S = 557857893;
    public static final int ID_5_LEVEL_SEAT_VENTILATING_MODE_DOMAIN_R = 557857886;
    public static final int ID_5_LEVEL_SEAT_VENTILATING_MODE_DOMAIN_S = 557857890;
    public static final int ID_5_LEVEL_SEAT_VENTILATING_MODE_PASSENGER_R = 557857887;
    public static final int ID_5_LEVEL_SEAT_VENTILATING_MODE_PASSENGER_S = 557857891;
    public static final int ID_5_LEVEL_SEAT_VENTILATING_STATE_BACK_LEFT_R = 557857872;
    public static final int ID_5_LEVEL_SEAT_VENTILATING_STATE_BACK_LEFT_S = 557857876;
    public static final int ID_5_LEVEL_SEAT_VENTILATING_STATE_BACK_RIGHT_R = 557857873;
    public static final int ID_5_LEVEL_SEAT_VENTILATING_STATE_BACK_RIGHT_S = 557857877;
    public static final int ID_5_LEVEL_SEAT_VENTILATING_STATE_DOMAIN_R = 557857870;
    public static final int ID_5_LEVEL_SEAT_VENTILATING_STATE_DOMAIN_S = 557857874;
    public static final int ID_5_LEVEL_SEAT_VENTILATING_STATE_PASSENGER_R = 557857871;
    public static final int ID_5_LEVEL_SEAT_VENTILATING_STATE_PASSENGER_S = 557857875;
    public static final int ID_ACBT_WIND_R = 557857536;
    public static final int ID_ACBT_WIND_S_4E3 = 557857539;
    public static final int ID_ACBT_WIND_S_X28 = 557857538;
    public static final int ID_ACBT_WIND_S_X31 = 557857537;
    public static final int ID_AC_AUTO_AIR_R = 557857543;
    public static final int ID_AC_AUTO_AIR_S = 557857544;
    public static final int ID_AC_PAUSE_CYCLE_R = 557857540;
    public static final int ID_AC_PAUSE_CYCLE_S_4E3 = 557857541;
    public static final int ID_AC_PAUSE_CYCLE_S_4EF = 557857542;
    public static final int ID_AC_TUNNEL_CYCLE_R = 557857555;
    public static final int ID_AC_TUNNEL_CYCLE_S = 557857556;
    public static final int ID_AC_WARM_MODE_R = 557857557;
    public static final int ID_AC_WARM_MODE_S = 557857558;
    public static final int ID_ADAS_REST_S = 557924014;
    public static final int ID_AFS_SWITCH_SATE = 557860626;
    public static final int ID_ALARM_PROMPT_STATE_R = 557858025;
    public static final int ID_AMBIENT_AREA_SUPPORT = 557856186;
    public static final int ID_AMBIENT_FEED_BACK = 557860631;
    public static final int ID_AMBIENT_LIGHT_COLOR_SUPPORT = 557856184;
    public static final int ID_AMBIENT_LIGHT_SUPPORT = 557856185;
    public static final int ID_AMBIENT_MULTI_COLOR_MODE_R = 557860617;
    public static final int ID_AMBIENT_MULTI_COLOR_MODE_S = 557860618;
    public static final int ID_AMBIENT_MULTI_COLOR_STATE_R = 557860615;
    public static final int ID_AMBIENT_MULTI_COLOR_STATE_S = 557860616;
    public static final int ID_AMBIENT_MUSIC_MODE_R = 557860613;
    public static final int ID_AMBIENT_MUSIC_MODE_S = 557860614;
    public static final int ID_AMBIENT_MUSIC_MODE_STATE_R = 557860611;
    public static final int ID_AMBIENT_MUSIC_MODE_STATE_S = 557860612;
    public static final int ID_AUTO_BACK_DEFAULT_RESET = 557858314;
    public static final int ID_AUTO_EXTERNAL_REAR_MIRROR_FOLLOW_UP_SWITCH_R = 557857796;
    public static final int ID_AUTO_EXTERNAL_REAR_MIRROR_FOLLOW_UP_SWITCH_S = 557857795;
    public static final int ID_AUTO_EXTERNAL_REAR_MIRROR_FOLLOW_UP_SWITCH_SUPPORT = 557856053;
    public static final int ID_AUTO_POWER_OFF_STATE_R = 557857596;
    public static final int ID_AUTO_POWER_OFF_STATE_S = 557857597;
    public static final int ID_AUTO_POWER_ON_STATE_R = 557857594;
    public static final int ID_AUTO_POWER_ON_STATE_S = 557857595;
    public static final int ID_AUTO_PURIFICATION = 557857546;
    public static final int ID_AUTO_PURIFICATION_STATE = 557846608;
    public static final int ID_AUTO_RAIN_WIPER_STATE_S = 557857609;
    public static final int ID_BACK_DOOR_ELECTRIC_MODE_S = 557857861;
    public static final int ID_BACK_DOOR_OPENED_HEIGHT_R = 557857561;
    public static final int ID_BACK_DOOR_OPENED_HEIGHT_S = 557857562;
    public static final int ID_BACK_HOME_LIGHT_DELAY_VALUE_R = 557857626;
    public static final int ID_BACK_HOME_LIGHT_DELAY_VALUE_S = 557857627;
    public static final int ID_BACK_LIGHT_DEFAULT_RESET = 557858318;
    public static final int ID_BD_MASSAGE_LEVEL_LEFT_REAR_R = 557858196;
    public static final int ID_BD_MASSAGE_LEVEL_RIGHT_REAR_R = 557858197;
    public static final int ID_BD_MASSAGE_MODE_LEFT_REAR_R = 557858194;
    public static final int ID_BD_MASSAGE_MODE_RIGHT_REAR_R = 557858195;
    public static final int ID_BLE_OPEN_DRIVER_DOOR_STATE_R = 557858415;
    public static final int ID_BLE_OPEN_DRIVER_DOOR_STATE_S = 557858423;
    public static final int ID_BRAKE_CLOSE_DOOR_R = 557858413;
    public static final int ID_BRAKE_CLOSE_DOOR_S = 557858426;
    public static final int ID_BRAKE_PEDAL_MODE_R = 557857602;
    public static final int ID_BRAKE_PEDAL_MODE_S = 557857603;
    public static final int ID_BSIS_COLLISION_STATE_R = 557856889;
    public static final int ID_BSIS_COLLISION_STATE_S = 557856890;
    public static final int ID_BSIS_FUNC_STATUS_R = 557856891;
    public static final int ID_CALL_STATE_S = 557858430;
    public static final int ID_CAMPING_BLANCE_ACTION_R = 557858366;
    public static final int ID_CAMPING_BLANCE_STATE_R = 557858365;
    public static final int ID_CAMPING_BLANCE_STATE_S = 557858405;
    public static final int ID_CAMPING_BLANCE_SUPPORTED_R = 557858375;
    public static final int ID_CAMPING_S = 557858304;
    public static final int ID_CANOPY_AUTOMATIC_DIMMING_R = 557858010;
    public static final int ID_CANOPY_AUTOMATIC_DIMMING_S = 557858012;
    public static final int ID_CARSETTING_REST_S = 557924046;
    public static final int ID_CAR_DOOR_TIPS_SOUND_TYPE_S = 557858105;
    public static final int ID_CAR_FRIDGE_TYPE_R = 557859199;
    public static final int ID_CAR_HEALTH_DEFAULT_RESET = 557858315;
    public static final int ID_CAR_SEAT_LAYOUT_R = 557858116;
    public static final int ID_CAR_SETTINGS_DEFAULT_RESETS = 557858309;
    public static final int ID_CAR_SETTINGS_RESETS = 557858307;
    public static final int ID_CENTER_REAR_SAFEBELT_STATUS_R = 557858142;
    public static final int ID_CHARGING_PORT_R_27C = 557857792;
    public static final int ID_CHARGING_PORT_R_475 = 557857793;
    public static final int ID_CHARGING_PORT_S_4E3 = 557857794;
    public static final int ID_CHECK_SUM_S = 557858410;
    public static final int ID_CLOCK_DEFAULT_RESET = 557858324;
    public static final int ID_CLOSE_DOOR_LEVEL_R = 557858411;
    public static final int ID_CLOSE_DOOR_LEVEL_S = 557858535;
    public static final int ID_CLOUD_SERVICE_STATUS_S = 557858137;
    public static final int ID_COMFORTABLE_PARKING_STATE_R = 557857604;
    public static final int ID_COMFORTABLE_PARKING_STATE_S = 557857605;
    public static final int ID_COM_SPEED_DOWN_STATER = 557857833;
    public static final int ID_COM_SPEED_DOWN_STATES = 557857834;
    public static final int ID_COM_SPEED_UP_STATER = 557857838;
    public static final int ID_COM_SPEED_UP_STATES = 557857832;
    public static final int ID_CONTROL_CALLBACK_STATE_R = 557857990;
    public static final int ID_CPD_IMS_SWITCH_STATE_R = 557858089;
    public static final int ID_CPD_IMS_SWITCH_STATE_S = 557858088;
    public static final int ID_CURA_SWITCH_STATE_R = 557858096;
    public static final int ID_CURA_SWITCH_STATE_S = 557858104;
    public static final int ID_CURRENT_MODE_OF_TAIL_STATE_R = 557858026;
    public static final int ID_CURRENT_STATE_OF_THE_TAIL_STATE_R = 557858021;
    public static final int ID_CURRENT_STATUS_OF_DIMMING_SUNROOF_R = 557858017;
    public static final int ID_CURRENT_TRANSMITTANCE_OF_SKYLIGHT_R = 557858016;
    public static final int ID_C_V_C_MODE_R = 557858374;
    public static final int ID_C_V_C_MODE_S = 557858408;
    public static final int ID_DAMP_STATE_R = 557858378;
    public static final int ID_DAMP_STATE_S = 557858401;
    public static final int ID_DELAY_DOWN_POWER_TIME_R = 557857982;
    public static final int ID_DELAY_POWER_DOWN_STATE_S = 557857999;
    public static final int ID_DELAY_POWER_DOWN_STATE_TIME_S = 557858000;
    public static final int ID_DELAY_POWER_DOWN_TIME_R = 557857972;
    public static final int ID_DELAY_POWER_DOWN_TIME_S = 557857971;
    public static final int ID_DELAY_POWER_OFF_STATE_R = 557857968;
    public static final int ID_DELAY_POWER_OFF_STATUS_R = 557857992;
    public static final int ID_DELAY_POWER_OFF_TIME_R = 557857970;
    public static final int ID_DELAY_POWER_OFF_TIME_S = 557857969;
    public static final int ID_DIMMING_CANOPY_CONFIGURATION = 557859165;
    public static final int ID_DIMMING_CANOPY_LOW_CODE_R = 557858018;
    public static final int ID_DISINF_STATE_R = 557857705;
    public static final int ID_DISINF_STATE_S = 557857704;
    public static final int ID_DLG_MESSAGE_CODE_R = 557857984;
    public static final int ID_DLINK_REST_S = 557924030;
    public static final int ID_DOORS_AND_WINDOW_DEFAULT_RESSETS = 557858311;
    public static final int ID_DOOR_HEIGHT_SUPPORT = 557856076;
    public static final int ID_DOOR_KNOB_AUTO_SHRINK_STATE_R = 557857565;
    public static final int ID_DOOR_KNOB_AUTO_SHRINK_STATE_S = 557857566;
    public static final int ID_DRAG_AND_DROP_MODE_TIPS_STATE_R = 557858462;
    public static final int ID_DRAG_CONNECTION_TYPE_R = 557858168;
    public static final int ID_DRAG_MODE_FORWARD_AND_BACKWARD_STATE_R = 557858461;
    public static final int ID_DRAG_TYPE_R = 557858167;
    public static final int ID_DRIVER_MAS_TOGGLE_MODE_CONFIG_R = 557858204;
    public static final int ID_DRIVER_SEAR_STATE_S = 557858526;
    public static final int ID_DRIVER_SEAT_AUTO_RETURNR = 557857598;
    public static final int ID_DRIVER_SEAT_AUTO_RETURNS = 557857599;
    public static final int ID_DRIVER_SEAT_AUTO_RETURN_B3_R = 557858429;
    public static final int ID_DRIVER_SEAT_HEATING437_STATE_R = 557858126;
    public static final int ID_DRIVER_SEAT_VENTILATING437_STATE_R = 557858125;
    public static final int ID_DRIVE_MODE_R = 557858112;
    public static final int ID_DRIVE_SETTINGS_RESETS = 557858308;
    public static final int ID_DYNAMIC_SUSPENSION_MODE_R = 557857610;
    public static final int ID_DYNAMIC_SUSPENSION_MODE_S = 557857611;
    public static final int ID_DY_HEIGHT_BY_VOICE_S = 557858407;
    public static final int ID_DY_HEIGHT_LEVEL_R = 557858379;
    public static final int ID_DY_HEIGHT_TOGGLE_STATE_R = 557858361;
    public static final int ID_DY_HEIGHT_TOGGLE_STATE_S = 557858402;
    public static final int ID_DY_HEIGHT_TURN_ACTION_R = 557858377;
    public static final int ID_DY_HEIGHT_VALUE_S = 557858400;
    public static final int ID_ECU_MASSAGE_LEVEL_BACK_LEFT_R = 557857922;
    public static final int ID_ECU_MASSAGE_LEVEL_BACK_RIGHT_R = 557857923;
    public static final int ID_ECU_MASSAGE_MODE_BACK_LEFT_R = 557857928;
    public static final int ID_ECU_MASSAGE_MODE_BACK_RIGHT_R = 557857929;
    public static final int ID_ELECTRIC_TAIL_AUTOMATIC_MODE_SWITCH_STATE_S = 557858028;
    public static final int ID_ELECTRIC_TAIL_AUTO_MODE_VOICE_SWITCH_STATE_S = 557858029;
    public static final int ID_ELECTRIC_TAIL_CONFIGURATION_R = 557858019;
    public static final int ID_ELECTRIC_TAIL_MAINTENANCE_FUNTATUS_STATE_R = 557858027;
    public static final int ID_ELECTRONI_CHILD_LOCK_CONFIG_SECOND = 557856207;
    public static final int ID_ENERGY_FEEDBACK_R_341 = 557857788;
    public static final int ID_ENERGY_FEEDBACK_S_4E3 = 557857789;
    public static final int ID_ENGINE_SOUND_TYPE_R = 557857587;
    public static final int ID_ENGINE_SOUND_TYPE_S = 557857588;
    public static final int ID_EQUALIZER_DEFAULT_RESET = 557858322;
    public static final int ID_EXCLUSIVE_SAVE_FLAG_R = 557859200;
    public static final int ID_EXHIBITION_MODE = 557871104;
    public static final int ID_EXHIBITION_START_ENGINE_PERMIT_R = 557857759;
    public static final int ID_EXHIBITION_START_ENGINE_PERMIT_S = 557857760;
    public static final int ID_E_OWNER_STATE_S = 557858002;
    public static final int ID_FACTORY_RESET_ALL = 557857573;
    public static final int ID_FD_CAMPING_BLANCE_ACTION_R = 557858391;
    public static final int ID_FD_CAMPING_BLANCE_STATE_R = 557858390;
    public static final int ID_FD_CAMPING_BLANCE_SUPPORTED_R = 557858399;
    public static final int ID_FD_COM_SPEED_UP_STATE_R = 557857951;
    public static final int ID_FD_C_R_C_CHECK_NUM_R = 557858380;
    public static final int ID_FD_DAMP_STATE_R = 557858383;
    public static final int ID_FD_DYNAMIC_SUSPENSION_MODE_R = 557858383;
    public static final int ID_FD_DY_HEIGHT_LEVEL_R = 557858384;
    public static final int ID_FD_DY_HEIGHT_TOGGLE_STATE_R = 557858385;
    public static final int ID_FD_DY_HEIGHT_TURN_ACTION_R = 557858382;
    public static final int ID_FD_FIXED_STATE_R = 557858393;
    public static final int ID_FD_FRONT_BOX_TAKE_STATE_R = 557858388;
    public static final int ID_FD_LIFT_MODE_STATE_R = 557858392;
    public static final int ID_FD_MODE_SUPPORTED_HTURN_R = 557858395;
    public static final int ID_FD_SPEED_SUPPORTED_TURN_R = 557858394;
    public static final int ID_FD_TAIL_BOX_TAKE_STATE_R = 557858389;
    public static final int ID_FD_TAIL_BOX_TAKE_SUPPORTED_R = 557858397;
    public static final int ID_FD_TRAILER_MODE_CODE_R = 557858398;
    public static final int ID_FD_WELCOME_ACTIVE_R = 557858387;
    public static final int ID_FD_WELCOME_STATE_R = 557858386;
    public static final int ID_FD_WELCOME_SUPPORTED_R = 557858396;
    public static final int ID_FIXED_STATE_R = 557858368;
    public static final int ID_FRIDGE_CHILD_LOCK_FLAG_R = 557859201;
    public static final int ID_FRIDGE_CHILD_LOCK_R = 557858063;
    public static final int ID_FRIDGE_CHILD_LOCK_S = 557858001;
    public static final int ID_FRIDGE_DELAYED_POWER_OUTAGE_R = 557859203;
    public static final int ID_FRIDGE_DOOR_STATE_R = 557857979;
    public static final int ID_FRIDGE_DOOR_STATE_S = 557857978;
    public static final int ID_FRIDGE_EXPANSION_AND_CONTRACTION_FUNCTION_R = 557859197;
    public static final int ID_FRIDGE_LOCATION_R = 557857985;
    public static final int ID_FRIDGE_MIN_WAGE_POWER_GENERATION_R = 557859198;
    public static final int ID_FRIDGE_NUM_R = 557859205;
    public static final int ID_FRIDGE_ONE_POSITION_R = 557859210;
    public static final int ID_FRIDGE_SCREEN_FLAG_R = 557859202;
    public static final int ID_FRIDGE_TEMPERATURE_REGULATION_R = 557859196;
    public static final int ID_FRIDGE_THREE_POSITION_R = 557859212;
    public static final int ID_FRIDGE_TWO_POSITION_R = 557859211;
    public static final int ID_FRIDGE_WORKING_MODE_R = 557859195;
    public static final int ID_FRIDGE_WORK_COLD_TEMPERATURE_R = 557857965;
    public static final int ID_FRIDGE_WORK_HEAT_TEMPERATURE_R = 557857966;
    public static final int ID_FRIDGE_WORK_SAVE_TEMPERATURE_R = 557857967;
    public static final int ID_FRONT_FRIDGE_DISINFECT_FLAG_R = 557859204;
    public static final int ID_FRONT_WIPER_SPEED_VALUE_R = 557858528;
    public static final int ID_GOAL_ROAD_MODE_R = 557847553;
    public static final int ID_GOAL_ROAD_MODE_S = 557847591;
    public static final int ID_HEADREST_HORIZATION_BACK_LEFT = 557857690;
    public static final int ID_HEADREST_HORIZATION_BACK_RIGHT = 557857691;
    public static final int ID_HEADREST_HORIZATION_COPILOT = 557857689;
    public static final int ID_HEADREST_HORIZATION_DRIVER = 557857688;
    public static final int ID_HEADREST_TURN_HEIGHT_BACK_LEFT = 559955504;
    public static final int ID_HEADREST_TURN_HEIGHT_BACK_RIGHT = 559955505;
    public static final int ID_HEADREST_TURN_HEIGHT_COPILOT = 559954845;
    public static final int ID_HEADREST_TURN_HEIGHT_DRIVER = 559954844;
    public static final int ID_HEAD_LAMP_CONFIG = 557857751;
    public static final int ID_HEAD_LAMP_LEVEL_R = 557857747;
    public static final int ID_HEAD_LAMP_LEVEL_S = 557857748;
    public static final int ID_HEV_CHARGING_PORT_R = 557857944;
    public static final int ID_HS_MASSAGE_STATE_BACK_LEFT_R = 557859104;
    public static final int ID_HS_MASSAGE_STATE_BACK_LEFT_S = 557857936;
    public static final int ID_HS_MASSAGE_STATE_BACK_RIGHT_R = 557859105;
    public static final int ID_HS_MASSAGE_STATE_BACK_RIGHT_S = 557857937;
    public static final int ID_HS_MASSAGE_STATE_DOMAIN_R = 557857938;
    public static final int ID_HS_MASSAGE_STATE_DOMAIN_S = 557857934;
    public static final int ID_HS_MASSAGE_STATE_PASSENGER_R = 557859103;
    public static final int ID_HS_MASSAGE_STATE_PASSENGER_S = 557857935;
    public static final int ID_HUD_BRIGHTNESS_R = 557857576;
    public static final int ID_HUD_BRIGHTNESS_S = 557857577;
    public static final int ID_HUD_DISPLAY_CONTENT_R_DRIVE = 557857582;
    public static final int ID_HUD_DISPLAY_CONTENT_R_NAVIGATION = 557857583;
    public static final int ID_HUD_DISPLAY_CONTENT_S_DRIVE = 557857584;
    public static final int ID_HUD_DISPLAY_CONTENT_S_NAVIGATION = 557857585;
    public static final int ID_HUD_HEIGHT_R = 557857574;
    public static final int ID_HUD_HEIGHT_S = 557857575;
    public static final int ID_HUD_MODE_PARAMS = 557857586;
    public static final int ID_HUD_MODE_R = 557857580;
    public static final int ID_HUD_MODE_S = 557857581;
    public static final int ID_HUD_NAVIGATION_STATE_R = 557857857;
    public static final int ID_HUD_NAVIGATION_STATE_S = 557857858;
    public static final int ID_HUD_PATTERN_R = 557857853;
    public static final int ID_HUD_PATTERN_S = 557857854;
    public static final int ID_HUD_ROTATION_ANGLE_R = 557857578;
    public static final int ID_HUD_ROTATION_ANGLE_S = 557857579;
    public static final int ID_HUD_SAFE_DRIVE_STATE_R = 557857855;
    public static final int ID_HUD_SAFE_DRIVE_STATE_S = 557857856;
    public static final int ID_HUD_SCREEN_PROJECTION_STATE_R = 557857859;
    public static final int ID_HUD_SCREEN_PROJECTION_STATE_S = 557857860;
    public static final int ID_HUD_STATE_R = 557857571;
    public static final int ID_HUD_STATE_S = 557857572;
    public static final int ID_H_SPEED_AUTO_CLOSE_STATER = 557857835;
    public static final int ID_H_SPEED_AUTO_CLOSE_STATES = 557857836;
    public static final int ID_IAL_AREA_DIFFERENT_WAYS = 557857722;
    public static final int ID_IAL_AREA_R = 557857721;
    public static final int ID_IAL_BRIGHTNESS_BACK_R = 557857718;
    public static final int ID_IAL_BRIGHTNESS_FRONT_R = 557857719;
    public static final int ID_IAL_BRIGHTNESS_S = 557857720;
    public static final int ID_IAL_BRIGHTNESS_STARRY_SKY_R = 557857717;
    public static final int ID_IAL_BRIGHTNESS_STAR_RING_R = 557857716;
    public static final int ID_IAL_COLOR_ADJUST_S = 557857715;
    public static final int ID_IAL_COLOR_BACK_R = 557857713;
    public static final int ID_IAL_COLOR_FRONT_R = 557857712;
    public static final int ID_IAL_COLOR_S = 557857714;
    public static final int ID_IAL_COLOR_STARRY_SKY_R = 557857711;
    public static final int ID_IAL_COLOR_STAR_RING_R = 557857710;
    public static final int ID_IAL_DEFAULT_RESET = 557858316;
    public static final int ID_ICE_BOX_TRIGGER_SOURCE_S = 557858082;
    public static final int ID_INSIDE_LIGHT_DOOR_STATE_R = 557857762;
    public static final int ID_INSIDE_LIGHT_DOOR_STATE_S = 557857763;
    public static final int ID_INSIDE_LIGHT_STATE_S = 557858532;
    public static final int ID_INS_THEME_R = 557857745;
    public static final int ID_INS_THEME_S = 557857025;
    public static final int ID_INTELLIGENT_AC_AREASTATE_R = 557857559;
    public static final int ID_INTELLIGENT_AC_AREASTATE_S = 557857560;
    public static final int ID_INTELLIGENT_DRIVE_DEFAULT_RESET = 557858320;
    public static final int ID_INTELLIGENT_LOCK_STATE_R = 557857569;
    public static final int ID_INTELLIGENT_LOCK_STATE_S = 557857570;
    public static final int ID_INTELLIGENT_UNLOCK_STATE_R = 557857567;
    public static final int ID_INTELLIGENT_UNLOCK_STATE_S = 557857568;
    public static final int ID_INTEL_LOCK_OPEN_DRIVER_DOOR_R = 557858412;
    public static final int ID_INTEL_LOCK_OPEN_DRIVER_DOOR_S = 557858425;
    public static final int ID_INTERNATIONAL_LANGUAGE_S = 557858343;
    public static final int ID_IVI_PROFILE_SETTINGS_R = 557857947;
    public static final int ID_I_V_I_PROFILE_SETTINGS_D_M_S = 557858129;
    public static final int ID_KEY_VIBRATOR_LEVEL_R = 557857945;
    public static final int ID_KEY_VIBRATOR_LEVEL_S = 557857946;
    public static final int ID_LANE_ASSIST_DRIVE_DEFAULT_RESET = 557858321;
    public static final int ID_LANGUAGE_DEFAULT_RESET = 557858325;
    public static final int ID_LANGUAGE_S = 557858342;
    public static final int ID_LANTERN_LANGUAGE_MODE_R = 557857744;
    public static final int ID_LANTERN_LANGUAGE_MODE_S = 557857743;
    public static final int ID_LEAVE_CAR_PIND = 557857803;
    public static final int ID_LEFT_ELECTRIC_PEDAL_R = 557858058;
    public static final int ID_LEFT_HEAD_LAMP_LEVEL = 557857749;
    public static final int ID_LEFT_HOME_LIGHT_DELAY_VALUE_R = 557857624;
    public static final int ID_LEFT_HOME_LIGHT_DELAY_VALUE_S = 557857625;
    public static final int ID_LEFT_REAR_SAFEBELT_STATUS_R = 557858141;
    public static final int ID_LEFT_RSE_CHILD_MODE_SWITCH_STATUS_R = 557858445;
    public static final int ID_LEFT_RSE_STATE_S = 557858439;
    public static final int ID_LEFT_RSE_SWITCH_COMMAND_S = 557858437;
    public static final int ID_LEFT_THIRD_SAFEBELT_STATUS_R = 557858144;
    public static final int ID_LIFT_MODE_STATE_R = 557858367;
    public static final int ID_LIFT_MODE_STATE_S = 557858403;
    public static final int ID_LIGHT_DEFAULT_RESET = 557856042;
    public static final int ID_LOCKS_DEFAULT_RESETS = 557858312;
    public static final int ID_LOCK_CAR_RISE_WINDOW_R12F = 557857622;
    public static final int ID_LOCK_CAR_RISE_WINDOW_R48E = 557857621;
    public static final int ID_LOCK_CAR_RISE_WINDOW_S = 557857623;
    public static final int ID_LOCK_OFF_R = 557857814;
    public static final int ID_LOCK_OFF_S = 557857797;
    public static final int ID_LRSE_CHILD_AREA_STATE_S = 557858534;
    public static final int ID_LRSE_CHILD_MODE_CODE1ST_BYTE_S = 557858451;
    public static final int ID_LRSE_CHILD_MODE_CODE2ND_BYTE_S = 557858452;
    public static final int ID_LRSE_CHILD_MODE_CODE3RD_BYTE_S = 557858453;
    public static final int ID_LRSE_CHILD_MODE_CODE4TH_BYTE_S = 557858454;
    public static final int ID_LRSE_CHILD_MODE_CODE_WRITE_FB_R = 557858459;
    public static final int ID_LRSE_CHILD_MODE_OPEN_TIME_S = 557858447;
    public static final int ID_LRSE_CHILD_MODE_TIME_WRITE_FB_R = 557858449;
    public static final int ID_LRSE_MACHINE_SWITCH_STATE_R = 557858441;
    public static final int ID_LR_HOT_STONE2_MASSAGE_CONFIG_R = 557858219;
    public static final int ID_LR_HOT_STONE3_MASSAGE_CONFIG_R = 557858220;
    public static final int ID_LR_HOT_STONE_MASSAGE_CONFIG_R = 557858218;
    public static final int ID_LR_MAS2_TOGGLE_MODE_CONFIG_R = 557858207;
    public static final int ID_LR_MAS_TOGGLE_MODE_CONFIG_R = 557858206;
    public static final int ID_LR_NORMAL2_MASSAGE_CONFIG_R = 557858217;
    public static final int ID_LR_NORMAL_MASSAGE_CONFIG_R = 557858216;
    public static final int ID_MAIN2_HOT_STONE_MASSAGE_CONFIG_R = 557858212;
    public static final int ID_MAIN_HS_MASSAGE_STATE_R = 557859101;
    public static final int ID_MAIN_NORMAL_MASSAGE_CONFIG_R = 557858210;
    public static final int ID_MAIN_SEAT_BACK_INITED_R = 557858162;
    public static final int ID_MAIN_SEAT_BACK_INIT_R = 557858147;
    public static final int ID_MAIN_SEAT_HIGHT_INITED_R = 557858163;
    public static final int ID_MAIN_SEAT_HORIZONTAL_INITED_R = 557858161;
    public static final int ID_MAIN_SEAT_HORIZONTAL_INIT_R = 557858146;
    public static final int ID_MASSAGE_LEVEL_4C1 = 557857772;
    public static final int ID_MASSAGE_LEVEL_4C2 = 557857773;
    public static final int ID_MASSAGE_LEVEL_4C3 = 557857774;
    public static final int ID_MASSAGE_LEVEL_4C4 = 557857775;
    public static final int ID_MASSAGE_LEVEL_LEFT_BACK = 557857768;
    public static final int ID_MASSAGE_LEVEL_LEFT_BACK_ECU = 557857769;
    public static final int ID_MASSAGE_LEVEL_LEFT_FRONT = 557857764;
    public static final int ID_MASSAGE_LEVEL_LEFT_FRONT_ECU = 557857765;
    public static final int ID_MASSAGE_LEVEL_RIGHT_BACK = 557857771;
    public static final int ID_MASSAGE_LEVEL_RIGHT_BACK_ECU = 557857770;
    public static final int ID_MASSAGE_LEVEL_RIGHT_FRONT = 557857766;
    public static final int ID_MASSAGE_LEVEL_RIGHT_FRONT_ECU = 557857767;
    public static final int ID_MASSAGE_MODE_R = 557857776;
    public static final int ID_MASSAGE_MODE_R_3CA = 557857779;
    public static final int ID_MASSAGE_MODE_R_402 = 557857781;
    public static final int ID_MASSAGE_MODE_R_42F = 557857782;
    public static final int ID_MASSAGE_MODE_R_437 = 557857777;
    public static final int ID_MASSAGE_MODE_R_4FA = 557857778;
    public static final int ID_MASSAGE_MODE_R_4FA_LEFT_BACK = 557857780;
    public static final int ID_MASSAGE_MODE_R_4FA_RIGHT_BACK = 557857783;
    public static final int ID_MASSAGE_MODE_S_4C1 = 557857784;
    public static final int ID_MASSAGE_MODE_S_4C2 = 557857785;
    public static final int ID_MASSAGE_MODE_S_4C3 = 557857786;
    public static final int ID_MASSAGE_MODE_S_4C4 = 557857787;
    public static final int ID_MEDITATION_MODE_S = 567857951;
    public static final int ID_MEMORY_SET_OPERATION_FLAG_R = 557858527;
    public static final int ID_MESSAGE_COUNTER_S = 557858409;
    public static final int ID_METER_EXPORT_AREA_R = 557858158;
    public static final int ID_MICRO_SWITCH_LOCK_WINDOW_STATE_R = 557857617;
    public static final int ID_MICRO_SWITCH_LOCK_WINDOW_STATE_S = 557857618;
    public static final int ID_MICRO_SWITCH_UNLOCK_WINDOW_STATE_R = 557857619;
    public static final int ID_MICRO_SWITCH_UNLOCK_WINDOW_STATE_S = 557857620;
    public static final int ID_MISS_PACKAGE_COUNT_R = 557858331;
    public static final int ID_MODE_S = 557857947;
    public static final int ID_MODE_SUPPORTED_HTURN_R = 557858370;
    public static final int ID_MOIS_ASSEMBLE_STATE_R = 557856892;
    public static final int ID_MOIS_ASSEMBLE_STATE_S = 557856893;
    public static final int ID_MOIS_FUNC_STATUS_R = 557856894;
    public static final int ID_MOTOR_PRETENSIONER_SEATBELT_STATE_R = 557857606;
    public static final int ID_MOTOR_PRETENSIONER_SEATBELT_STATE_S = 557856058;
    public static final int ID_NETWORK_SECURITY_STATUS_S = 557858139;
    public static final int ID_NEW_ENERGY_DEFAULT_RESETS = 557858310;
    public static final int ID_NFC_OPEN_DRIVER_DOOR_R = 557858414;
    public static final int ID_NFC_OPEN_DRIVER_DOOR_S = 557858422;
    public static final int ID_NOTIFY_ENTER_TUNNEL_S = 557858356;
    public static final int ID_NOTIFY_TUNNEL_LENGTH_S = 557858417;
    public static final int ID_OFF_REMINDER = 557857755;
    public static final int ID_ONE_KEY_ENTER_PIT_CONFIG_R = 557858154;
    public static final int ID_ONE_LEVEL_SEAT_HEATING_STATE_R = 557858355;
    public static final int ID_ON_FD_CRC_CHECK_NUM_R = 557858381;
    public static final int ID_ON_SAFE_BELT_WARN_STATE_CHANGED = 557858329;
    public static final int ID_ON_SAVE_SEAT_PARAMS_ALL_CHANGED_3CA = 557857807;
    public static final int ID_ON_SAVE_SEAT_PARAMS_ALL_CHANGED_402 = 557857808;
    public static final int ID_ON_SAVE_SEAT_PARAMS_ALL_CHANGED_42F = 557857809;
    public static final int ID_ON_SAVE_SEAT_PARAMS_ALL_CHANGED_PLACE5 = 557857805;
    public static final int ID_ON_SAVE_SEAT_PARAMS_ALL_CHANGED_PLACE7 = 557857806;
    public static final int ID_ON_SEAT_HEATING_STATE_CHANGED_437 = 557857810;
    public static final int ID_ON_SMART_WELCOME_LIGHT_STATE_CHANGED = 557857811;
    public static final int ID_ON_TRAY_STATE_CHANGED = 557857804;
    public static final int ID_OPENING_ANGLE_INFORMATION_SET_ON_THE_R_S = 557858015;
    public static final int ID_OPTIMIZATION_STATUS_S = 557858138;
    public static final int ID_OUTSIDE_LIGHT_STATE_SCENE_S = 557858533;
    public static final int ID_OUT_CHARGE_DEFAULT_RESET = 557858327;
    public static final int ID_OVERSPEED_LOCK_R = 557857799;
    public static final int ID_OVERSPEED_LOCK_S = 557857798;
    public static final int ID_O_T_A_MODE_S = 557860375;
    public static final int ID_PAS2_HOT_STONE_MASSAGE_CONFIG_R = 557858215;
    public static final int ID_PASSANGER_SEAT_BACK_INIT_R = 557858149;
    public static final int ID_PASSANGER_SEAT_HORIZONTAL_INIT_R = 557858148;
    public static final int ID_PASSENGER_LAST_LEFT_STATER = 557857844;
    public static final int ID_PASSENGER_LAST_MIDDLE_STATER = 557857846;
    public static final int ID_PASSENGER_LAST_RIGHT_STATER = 557857845;
    public static final int ID_PASSENGER_MAS_TOGGLE_MODE_CONFIG_R = 557858205;
    public static final int ID_PASSENGER_REAR_LEFT_STATER = 557857841;
    public static final int ID_PASSENGER_REAR_MIDDLE_STATER = 557857843;
    public static final int ID_PASSENGER_REAR_RIGHT_STATER = 557857842;
    public static final int ID_PASSENGER_SEAT_HEATING3_C_A_STATE_R = 557858128;
    public static final int ID_PASSENGER_SEAT_VENTILATING3_C_A_STATE_R = 557858127;
    public static final int ID_PASSENGER_STATER = 557857840;
    public static final int ID_PAS_HOT_STONE_MASSAGE_CONFIG_R = 557858214;
    public static final int ID_PAS_NORMAL_MASSAGE_CONFIG_R = 557858213;
    public static final int ID_PEDAL_SUPPRESSION_SYSTEM_STATE_R = 557858093;
    public static final int ID_PEDAL_SUPPRESSION_SYSTEM_STATE_S = 557858092;
    public static final int ID_PET_MODE_S = 557857949;
    public static final int ID_PHONE_REMIND_CODE_R = 557857997;
    public static final int ID_PHYSICALKEY_CONFIG_R = 557858097;
    public static final int ID_PHYSICALKEY_SECUP_CONFIG_R = 557858099;
    public static final int ID_PHYSICALKEY_SECURL_CONFIG_R = 557858100;
    public static final int ID_PHYSICALKEY_SECURR_CONFIG_R = 557858101;
    public static final int ID_PHYSICALKEY_SECU_CONFIG_R = 557858098;
    public static final int ID_PM25_POWER_R = 557857549;
    public static final int ID_PM25_POWER_S = 557857550;
    public static final int ID_PM25_SWITCH_CHECK_R = 557857551;
    public static final int ID_PM25_SWITCH_CHECK_S = 557857552;
    public static final int ID_PM25_TIMECHECK_R = 557857553;
    public static final int ID_PM25_TIMECHECK_S = 557857554;
    public static final int ID_P_A_B_CODE_R = 557858330;
    public static final int ID_P_A_B_STATE_R = 557858345;
    public static final int ID_P_A_B_STATE_S = 557858344;
    public static final int ID_RAIN_AUTO_LOCK_WINDOW_STATE_R = 557857563;
    public static final int ID_RAIN_AUTO_LOCK_WINDOW_STATE_S = 557857564;
    public static final int ID_REARVIEW_MIRROR_IS_AUTO_LIGHT_STATE_S = 557857954;
    public static final int ID_REARVIEW_MIRROR_IS_SCREEN_STATE_S = 557857955;
    public static final int ID_REARVIEW_MIRROR_IS_STATE_R = 557857958;
    public static final int ID_REARVIEW_MIRROR_IS_TURNED_STATE_AUTO_LIGHT_R = 557857959;
    public static final int ID_REARVIEW_MIRROR_IS_TURNED_STATE_R = 557857957;
    public static final int ID_REARVIEW_MIRROR_IS_TURNED_STATE_S = 557857952;
    public static final int ID_REARVIEW_MIRROR_X_LEFT = 559954814;
    public static final int ID_REARVIEW_MIRROR_X_RIGHT = 559954815;
    public static final int ID_REARVIEW_MIRROR_Y_LEFT = 559954812;
    public static final int ID_REARVIEW_MIRROR_Y_RIGHT = 559954813;
    public static final int ID_REAR_AC_PANEL_AUTO_LOCK_TIME_R = 557856073;
    public static final int ID_REAR_AC_PANEL_AUTO_LOCK_TIME_S = 557857757;
    public static final int ID_REAR_DOOR_STATE_R = 557857994;
    public static final int ID_REAR_FRIDGE_DELAY_STATE_S = 557858006;
    public static final int ID_REAR_FRIDGE_DELAY_TIME_S = 557858007;
    public static final int ID_REAR_FRIDGE_DISINFECT_FLAG_R = 557859206;
    public static final int ID_REAR_FRIDGE_DOOR_STATE_S = 557858008;
    public static final int ID_REAR_FRIDGE_KEY_TYPE_FLAG_R = 557859207;
    public static final int ID_REAR_FRIDGE_STERILIZER_STATE_S = 557858004;
    public static final int ID_REAR_FRIDGE_TEMPERATURE_S = 557858005;
    public static final int ID_REAR_FRIDGE_WORK_STATE_S = 557858003;
    public static final int ID_REAR_FRIDGE_WORK_TYPE_FLAG_R = 557859208;
    public static final int ID_REAR_HEAT_TEMP_R = 557857995;
    public static final int ID_REAR_LEFT_MASSAGE_SW_CONFIGURATION_R = 557858031;
    public static final int ID_REAR_MIRROR_DEFAULT_RESETS = 557858313;
    public static final int ID_REAR_RIGHT_FRIDGE_HEATING_SCHEME_R = 557859209;
    public static final int ID_REAR_RIGHT_MASSAGE_SW_CONFIGURATION_R = 557858030;
    public static final int ID_REAR_RIGHT_SEAT_GREET_STATE_R = 557858102;
    public static final int ID_REAR_RIGHT_SEAT_GREET_STATE_S = 557858103;
    public static final int ID_REAR_SAVE_TEMP_R = 557857996;
    public static final int ID_REAR_SEATS_FOLD_OR_EXPAND = 557857839;
    public static final int ID_REAR_SEAT_GREET_STATE_R = 557858376;
    public static final int ID_REAR_SEAT_GREET_STATE_S = 557858427;
    public static final int ID_REAR_VIEW_ECU_MIRROR_FLIP_STATE_R = 557858360;
    public static final int ID_REAR_VIEW_MIRROR_FLIP_R = 557857591;
    public static final int ID_REAR_VIEW_MIRROR_FLIP_SETS = 557857837;
    public static final int ID_REAR_VIEW_MIRROR_FLIP_S_WITHOUT_MEMORY = 557857593;
    public static final int ID_REAR_VIEW_MIRROR_FLIP_S_WITH_MEMORY = 557857592;
    public static final int ID_REAR_WIPER_SPEED_VALUE_R = 557858530;
    public static final int ID_REAR_WORK_TEMPERATURE_S = 557857989;
    public static final int ID_RECOVERORSAVEPARAMSCHANGED2 = 557857847;
    public static final int ID_RECOVERORSAVEPARAMSCHANGED3 = 557857848;
    public static final int ID_RECOVERORSAVEPARAMSCHANGED4 = 557857849;
    public static final int ID_RECOVERORSAVEPARAMSCHANGED5 = 557857850;
    public static final int ID_RECOVERORSAVEPARAMSCHANGED6 = 557857851;
    public static final int ID_RECOVERORSAVEPARAMSCHANGED7 = 557857852;
    public static final int ID_REFRIG1_POSITION_MARK_BIT_R = 557859210;
    public static final int ID_REFRIG2_POSITION_MARK_BIT_R = 557859211;
    public static final int ID_REFRIGERATOR_DOOR_STATE = 557857703;
    public static final int ID_REFRIGERATOR_MOBILE_TERMINAL_PUSH_RE_R = 557858119;
    public static final int ID_REFRIGERATOR_MODE_R = 557857699;
    public static final int ID_REFRIGERATOR_MODE_S = 557857700;
    public static final int ID_REFRIGERATOR_STATE = 557857708;
    public static final int ID_REFRIGERATOR_TEMPERATURE_R = 557857981;
    public static final int ID_REFRIGERATOR_WORK_MODE_R = 557857962;
    public static final int ID_REFRIGERATOR_WORK_MODE_S = 557857961;
    public static final int ID_REFRIGERATOR_WORK_STATE_R = 557857980;
    public static final int ID_REFRIGERATOR_WORK_TEMPERATURE_R = 557857964;
    public static final int ID_REFRIGERATOR_WORK_TEMPERATURE_S = 557857963;
    public static final int ID_REFRIG_NUM_FLAG_R = 557859205;
    public static final int ID_REFRIG_WORK_FLAG_R = 557858122;
    public static final int ID_REFRIG_WORK_MODE_R = 557858123;
    public static final int ID_REFRIG_WORK_MODE_S = 557858124;
    public static final int ID_REMBER_POWER_OFF_STATE_R = 557857974;
    public static final int ID_REMBER_POWER_OFF_STATE_S = 557857973;
    public static final int ID_REMENBER_POWER_OFF_STATER = 557857701;
    public static final int ID_REMENBER_POWER_OFF_STATES = 557857702;
    public static final int ID_REMOTE_CONTROL_DOWNWINDOW_STATE_R = 557857614;
    public static final int ID_REMOTE_CONTROL_DOWNWINDOW_STATE_S_48E = 557857615;
    public static final int ID_REMOTE_CONTROL_DOWNWINDOW_STATE_S_4E3 = 557857616;
    public static final int ID_REMOTE_CONTROL_UPWINDOW_STATE_R = 557857612;
    public static final int ID_REMOTE_CONTROL_UPWINDOW_STATE_S = 557857613;
    public static final int ID_REMOTE_KEY_START_AC_TIMES = 557857548;
    public static final int ID_REMOTE_KEY_START_AC_TIME_R = 557856063;
    public static final int ID_RESETSEAT_PARAMS_BACK_LEFT = 557857678;
    public static final int ID_RESETSEAT_PARAMS_BACK_RIGHT = 557857679;
    public static final int ID_RESETSEAT_PARAMS_COPILOT = 557857677;
    public static final int ID_RESETSEAT_PARAMS_DRIVER = 557857676;
    public static final int ID_RESET_HUD_STATE = 557857573;
    public static final int ID_RESET_MOTOR_PRETENSIONER_SEATBELT = 557857608;
    public static final int ID_RESET_REARVIEW_MIRROR_IS_SETS = 557857960;
    public static final int ID_REST_MODE_S = 557857948;
    public static final int ID_RIGHT_ELECTRIC_PEDAL_R = 557858060;
    public static final int ID_RIGHT_HEAD_LAMP_LEVEL = 557857750;
    public static final int ID_RIGHT_REAR_SAFEBELT_STATUS_R = 557858143;
    public static final int ID_RIGHT_RSE_CHILD_MODE_SWITCH_STATUS_R = 557858446;
    public static final int ID_RIGHT_RSE_STATE_S = 557858440;
    public static final int ID_RIGHT_RSE_SWITCH_COMMAND_S = 557858438;
    public static final int ID_RIGHT_THIRD_SAFEBELT_STATUS_R = 557858145;
    public static final int ID_RNC_STATE_R = 557858090;
    public static final int ID_RNC_STATE_S = 557859185;
    public static final int ID_RRSE_CHILD_AREA_STATE_S = 557858444;
    public static final int ID_RRSE_CHILD_MODE_CODE1ST_BYTE_S = 557858455;
    public static final int ID_RRSE_CHILD_MODE_CODE2ND_BYTE_S = 557858456;
    public static final int ID_RRSE_CHILD_MODE_CODE3RD_BYTE_S = 557858457;
    public static final int ID_RRSE_CHILD_MODE_CODE4TH_BYTE_S = 557858458;
    public static final int ID_RRSE_CHILD_MODE_CODE_WRITE_FB_R = 557858460;
    public static final int ID_RRSE_CHILD_MODE_OPEN_TIME_S = 557858448;
    public static final int ID_RRSE_CHILD_MODE_TIME_WRITE_FB_R = 557858450;
    public static final int ID_RRSE_MACHINE_SWITCH_STATE_R = 557858442;
    public static final int ID_RR_ECU_MASSAGE_LEVEL_R = 557858203;
    public static final int ID_RR_ECU_MASSAGE_MODE_R = 557858202;
    public static final int ID_RR_HOT_STONE2_MASSAGE_CONFIG_R = 557858225;
    public static final int ID_RR_HOT_STONE3_MASSAGE_CONFIG_R = 557858226;
    public static final int ID_RR_HOT_STONE4_MASSAGE_CONFIG_R = 557858227;
    public static final int ID_RR_HOT_STONE_MASSAGE_CONFIG_R = 557858224;
    public static final int ID_RR_MAS2_TOGGLE_MODE_CONFIG_R = 557858209;
    public static final int ID_RR_MAS_TOGGLE_MODE_CONFIG_R = 557858208;
    public static final int ID_RR_NORMAL2_MASSAGE_CONFIG_R = 557858222;
    public static final int ID_RR_NORMAL3_MASSAGE_CONFIG_R = 557858223;
    public static final int ID_RR_NORMAL_MASSAGE_CONFIG_R = 557858221;
    public static final int ID_RUNING_CLOSE_DOOR_STATE_R = 557857862;
    public static final int ID_RUNING_CLOSE_DOOR_STATE_S = 557857863;
    public static final int ID_SAFETY_BELT_PASSENGER_COMMAND_FRONT_ROW_SEAT_LEFT = 557860876;
    public static final int ID_SAFETY_BELT_PASSENGER_COMMAND_FRONT_ROW_SEAT_RIGHT = 557857840;
    public static final int ID_SAFETY_BELT_PASSENGER_COMMAND_SECOND_ROW_SEAT_LEFT = 557857841;
    public static final int ID_SAFETY_BELT_PASSENGER_COMMAND_SECOND_ROW_SEAT_MID = 557857842;
    public static final int ID_SAFETY_BELT_PASSENGER_COMMAND_SECOND_ROW_SEAT_RIGHT = 557857843;
    public static final int ID_SAFE_ASSIST_DEFAULT_RESET = 557858319;
    public static final int ID_SAFE_BELT_CLOSE_DRIVE_DOOR_R = 557858416;
    public static final int ID_SAFE_BELT_CLOSE_DRIVE_DOOR_STATE_S = 557858424;
    public static final int ID_SAFE_BELT_WARN_STATER = 557858305;
    public static final int ID_SAFE_BELT_WARN_STATES = 557858306;
    public static final int ID_SAND_ABS_MODE_STATE_R = 557858114;
    public static final int ID_SAND_ABS_MODE_STATE_S = 557858113;
    public static final int ID_SAND_ABS_TIP_MESSAGE_R = 557858115;
    public static final int ID_SAVESEAT_PARAMSALL_BACK_LEFT = 557857656;
    public static final int ID_SAVESEAT_PARAMSALL_BACK_RIGHT = 557857657;
    public static final int ID_SAVESEAT_PARAMSALL_COPILOT = 557857655;
    public static final int ID_SAVESEAT_PARAMSALL_DRIVER = 557857654;
    public static final int ID_SAVE_OWNER_STATE_R = 557858117;
    public static final int ID_SCREEN_ANGLE_R = 557858334;
    public static final int ID_SCREEN_ANGLE_VALUE_R = 557858338;
    public static final int ID_SCREEN_ANGLE_VALUE_S = 557858338;
    public static final int ID_SCREEN_ANTI_PINCH_STATE_R = 557858337;
    public static final int ID_SCREEN_CALLBACK_MESSAGE_R = 557857993;
    public static final int ID_SCREEN_ROTATION_ACTION_R = 557858336;
    public static final int ID_SCREEN_ROTATION_LOCK_S = 557858335;
    public static final int ID_SCREEN_ROTATION_OVER_HOT_STATE_R = 557858340;
    public static final int ID_SCREEN_ROTATION_S = 557858333;
    public static final int ID_SCREEN_ROTATION_STATUS_R = 557875283;
    public static final int ID_SCREEN_VOLTAGE_STATE_R = 557862926;
    public static final int ID_SEAT_BACKREST_POSTION_BACK_LEFT = 559954826;
    public static final int ID_SEAT_BACKREST_POSTION_BACK_RIGHT = 559954827;
    public static final int ID_SEAT_BACKREST_POSTION_COPILOT = 559954825;
    public static final int ID_SEAT_BACKREST_POSTION_DRIVER = 559954824;
    public static final int ID_SEAT_DISTRIBUTION_FORM_CONFIG_R = 557858157;
    public static final int ID_SEAT_HEATING_NOT_L1_P_STATE_R = 557857866;
    public static final int ID_SEAT_HEATING_STATE2_BACK_LEFT_R = 557857864;
    public static final int ID_SEAT_HEATING_STATE2_BACK_RIGHT_R = 557857865;
    public static final int ID_SEAT_HEATING_STATE_BACKLEFT_ECU = 557857726;
    public static final int ID_SEAT_HEATING_STATE_BACKRIGHT_ECU = 557857727;
    public static final int ID_SEAT_HEATING_STATE_BACK_LEFT_DOMAIN_CONTROLS = 557857827;
    public static final int ID_SEAT_HEATING_STATE_BACK_LEFT_DOMAIN_CONTROL_R = 557857730;
    public static final int ID_SEAT_HEATING_STATE_BACK_RIGHT_DOMAIN_CONTROL_R = 557857731;
    public static final int ID_SEAT_HEATING_STATE_BACK_RIGHT_DOMAIN_CONTROL_S = 557857828;
    public static final int ID_SEAT_HEATING_STATE_COPILOTNON_L1P = 557857729;
    public static final int ID_SEAT_HEATING_STATE_COPILOT_DOMAIN_CONTROL = 557857732;
    public static final int ID_SEAT_HEATING_STATE_COPILOT_ECU = 557857725;
    public static final int ID_SEAT_HEATING_STATE_COPILOT_L1P = 557857728;
    public static final int ID_SEAT_HEATING_STATE_DRIVER_DOMAIN_CONTROL = 557857723;
    public static final int ID_SEAT_HEATING_STATE_DRIVER_DOMAIN_CONTROL_S = 557857826;
    public static final int ID_SEAT_HEATING_STATE_DRIVER__ECU = 557857724;
    public static final int ID_SEAT_HORIZATION_BACK_LEFT = 557857666;
    public static final int ID_SEAT_HORIZATION_BACK_RIGHT = 557857667;
    public static final int ID_SEAT_HORIZATION_COPILOT = 557857665;
    public static final int ID_SEAT_HORIZATION_DRIVER = 557857664;
    public static final int ID_SEAT_SECULL_DISTRIBUTION_FORM_CONFIG_R = 557858228;
    public static final int ID_SEAT_SECULR_DISTRIBUTION_FORM_CONFIG_R = 557858229;
    public static final int ID_SEAT_SECURL_DISTRIBUTION_FORM_CONFIG_R = 557858230;
    public static final int ID_SEAT_SECURR_DISTRIBUTION_FORM_CONFIG_R = 557858231;
    public static final int ID_SEAT_TURN_HEIGHT_BACK_LEFT = 559954822;
    public static final int ID_SEAT_TURN_HEIGHT_BACK_RIGHT = 559954823;
    public static final int ID_SEAT_TURN_HEIGHT_COPILOT = 559954821;
    public static final int ID_SEAT_TURN_HEIGHT_DRIVER = 559954820;
    public static final int ID_SEAT_VENTILATING_NOT_L1_P_STATE_R = 557857869;
    public static final int ID_SEAT_VENTILATING_STATE2_BACK_LEFT_R = 557857867;
    public static final int ID_SEAT_VENTILATING_STATE2_BACK_RIGHT_R = 557857868;
    public static final int ID_SEAT_VENTILATING_STATE_BACK_LEFT_DOMAIN_CONTROLR = 557857740;
    public static final int ID_SEAT_VENTILATING_STATE_BACK_LEFT_DOMAIN_CONTROLS = 557857830;
    public static final int ID_SEAT_VENTILATING_STATE_BACK_LEFT_ECU = 557857736;
    public static final int ID_SEAT_VENTILATING_STATE_BACK_RIGHT_DOMAIN_CONTROLR = 557857741;
    public static final int ID_SEAT_VENTILATING_STATE_BACK_RIGHT_DOMAIN_CONTROLS = 557857831;
    public static final int ID_SEAT_VENTILATING_STATE_BACK_RIGHT_ECU = 557857737;
    public static final int ID_SEAT_VENTILATING_STATE_COPILOTNON_L1P = 557857739;
    public static final int ID_SEAT_VENTILATING_STATE_COPILOT_DOMAIN_CONTROL = 557857742;
    public static final int ID_SEAT_VENTILATING_STATE_COPILOT_ECU = 557857735;
    public static final int ID_SEAT_VENTILATING_STATE_COPILOT_L1P = 557857738;
    public static final int ID_SEAT_VENTILATING_STATE_DRIVER_DOMAIN_CONTROL = 557857829;
    public static final int ID_SEAT_VENTILATING_STATE_DRIVER_DOMAIN_CONTROL_R = 557857733;
    public static final int ID_SEAT_VENTILATING_STATE_DRIVER__ECU = 557857734;
    public static final int ID_SEA_MODE_CHARGING_SOC_TYPE_R = 557858111;
    public static final int ID_SECOND_LEFT_SEAT_BACK_INIT_R = 557858151;
    public static final int ID_SECOND_LEFT_SEAT_HORIZONTAL_INIT_R = 557858150;
    public static final int ID_SECOND_RIGHT_SEAT_BACK2_INIT_R = 557858166;
    public static final int ID_SECOND_RIGHT_SEAT_BACK_INIT_R = 557858153;
    public static final int ID_SECOND_RIGHT_SEAT_HORIZONTAL2_INIT_R = 557858165;
    public static final int ID_SECOND_RIGHT_SEAT_HORIZONTAL_INIT_R = 557858152;
    public static final int ID_SECU_MASSAGE_LEVEL_LEFT_REAR_R = 557858200;
    public static final int ID_SECU_MASSAGE_LEVEL_RIGHT_REAR_R = 557858201;
    public static final int ID_SECU_MASSAGE_MODE_LEFT_REAR_R = 557858198;
    public static final int ID_SECU_MASSAGE_MODE_RIGHT_REAR_R = 557858199;
    public static final int ID_SEND_OFF_REMINDER = 557857754;
    public static final int ID_SERVICE_CONTROL_CALLBACK_R = 557857998;
    public static final int ID_SETTING_THIRD_ROW_LEFT_CHAIR = 557857844;
    public static final int ID_SETTING_THIRD_ROW_MIDDLE_CHAIR = 557857845;
    public static final int ID_SETTING_THIRD_ROW_RIGHT_CHAIR = 557857846;
    public static final int ID_SET_UI_VERSION = 557858328;
    public static final int ID_SKY_CURTAIN_POWER_OFF_AND_DIM_R = 557858011;
    public static final int ID_SKY_CURTAIN_POWER_OFF_AND_DIM_S = 557858013;
    public static final int ID_SMART_WELCOME_LIGHT_STATE_R = 557857752;
    public static final int ID_SMART_WELCOME_LIGHT_STATE_S = 557857753;
    public static final int ID_SOC_TARGET_R = 557857790;
    public static final int ID_SOC_TARGET_S = 557857791;
    public static final int ID_SOMETHING_LEFT_INREFRIGERATOR_R = 557858354;
    public static final int ID_SOUND_FILED_DEFAULT_RESET = 557858323;
    public static final int ID_SPEED_REMINDER_VALUE_R = 557857589;
    public static final int ID_SPEED_REMINDER_VALUE_S = 557857590;
    public static final int ID_SPEED_SUPPORTED_TURN_R = 557858369;
    public static final int ID_STEERING_WHEEL_HEATED_PLAN_R = 557858106;
    public static final int ID_STEERING_WHEEL_HEATING_AUTO_STATE_R = 557857921;
    public static final int ID_STEERING_WHEEL_HEATING_AUTO_STATE_S = 557857920;
    public static final int ID_STEERING_WHEEL_HEATING_GEAR_R = 557857919;
    public static final int ID_STEERING_WHEEL_HEATING_GEAR_S = 557857918;
    public static final int ID_STEERING_WHEEL_HEATING_STATE_R = 557857813;
    public static final int ID_STEERING_WHEEL_HEATING_STATE_S = 557857761;
    public static final int ID_STEERING_WHEEL_Y = 559954810;
    public static final int ID_STEERING_WHEEL_Z = 559954811;
    public static final int ID_STEER_ASSIS_R = 557857800;
    public static final int ID_STEER_ASSIS_S = 557857801;
    public static final int ID_STEER_ASSIS_SUPPORTED = 557857802;
    public static final int ID_STEER_POSITION_AUTO_RETURNR = 557857600;
    public static final int ID_STEER_POSITION_AUTO_RETURNS = 557856146;
    public static final int ID_STERILIZATION_STATE_R = 557857976;
    public static final int ID_STERILIZATION_STATE_S = 557857975;
    public static final int ID_STERILIZATION_WORK_STATE_R = 557857983;
    public static final int ID_SUPPORT_SEAT_MASSAGE_DEPUTY = 557856020;
    public static final int ID_SUPPORT_SEAT_MASSAGE_LEFT_REAR = 557856021;
    public static final int ID_SUPPORT_SEAT_MASSAGE_MAIN = 557856019;
    public static final int ID_SUPPORT_SEAT_MASSAGE_RIGHT_REAR = 557856022;
    public static final int ID_SWCONTROL_STATE_R = 557858091;
    public static final int ID_SWS_MEDIUM_RELATED_KEYS_R = 557867008;
    public static final int ID_SYS_FACTORY_RESET = 557903888;
    public static final int ID_S_O_C_TARGET_LOW_LIMIT_R = 557858084;
    public static final int ID_S_O_C_TARGET_RANGE_R = 557858083;
    public static final int ID_TAIL_BOX_TAKE_STATE_R = 557858364;
    public static final int ID_TAIL_BOX_TAKE_STATE_S = 557858406;
    public static final int ID_TAIL_BOX_TAKE_SUPPORTED_R = 557858372;
    public static final int ID_TAIL_INITIALIZATION_STATE_R = 557858020;
    public static final int ID_TAIL_MOTOR_THERMAL_PROTECTION_STATE_R = 557858023;
    public static final int ID_TAIL_POSITION_FEEDBACK_R = 557858024;
    public static final int ID_TAIL_POSITION_PERCENTAGE_R = 557858022;
    public static final int ID_TAP_DOOR_STATE_R = 557858160;
    public static final int ID_THIRD_ROW_LEFT_BACKREST_DISPLAY_R = 557859134;
    public static final int ID_THIRD_ROW_RIGHT_BACKREST_DISPLAY_R = 557859144;
    public static final int ID_THIRD_ROW_SEAT_LEFT_BACKREST_S = 557858155;
    public static final int ID_THIRD_ROW_SEAT_RIGHT_BACKREST_S = 557858156;
    public static final int ID_THIRD_ROW_SEAT_WORK_MODE_R = 557858140;
    public static final int ID_TOWING_PROHIBITED_STATE_R = 557858177;
    public static final int ID_TRAILER_DRAG_TYPE_R = 557858173;
    public static final int ID_TRAILER_DRAG_TYPE_S = 557858172;
    public static final int ID_TRAILER_LARGE_ITEM_CONFIGURATION_R = 557858171;
    public static final int ID_TRAILER_LARGE_LIMIT_WEIGHT_R = 557858176;
    public static final int ID_TRAILER_LIGHT_CHECK_STATE_R = 557858358;
    public static final int ID_TRAILER_LIGHT_CHECK_STATE_S = 557858420;
    public static final int ID_TRAILER_MIDDLE_ITEM_CONFIGURATION_R = 557858170;
    public static final int ID_TRAILER_MIDDLE_LIMIT_WEIGHT_R = 557858175;
    public static final int ID_TRAILER_MODE_CODE_R = 557858373;
    public static final int ID_TRAILER_MODE_MILEAGE_R = 557858529;
    public static final int ID_TRAILER_MODE_STATE_R = 557858357;
    public static final int ID_TRAILER_MODE_STATE_S = 557858428;
    public static final int ID_TRAILER_SMALL_ITEM_CONFIGURATION_R = 557858169;
    public static final int ID_TRAILER_SMALL_LIMIT_WEIGHT_R = 557858174;
    public static final int ID_TRAY_INIT_STATE = 557857709;
    public static final int ID_TRAY_STATE_R = 557857706;
    public static final int ID_TRAY_STATE_S = 557857707;
    public static final int ID_TURN_CALIPER_SPLINT_HEIGHT_BACK_LEFT = 559954838;
    public static final int ID_TURN_CALIPER_SPLINT_HEIGHT_BACK_RIGHT = 559954839;
    public static final int ID_TURN_CALIPER_SPLINT_HEIGHT_COPILOT = 559954837;
    public static final int ID_TURN_CALIPER_SPLINT_HEIGHT_DRIVER = 559954836;
    public static final int ID_TURN_CALIPER_SPLINT_PERCENT_DRIVER = 557857823;
    public static final int ID_TURN_CALIPER_SPLINT_PERCENT_PASSENGER = 557857824;
    public static final int ID_TURN_CUSHION_HEIGHT_BACK_LEFT = 559954834;
    public static final int ID_TURN_CUSHION_HEIGHT_BACK_RIGHT = 559954835;
    public static final int ID_TURN_CUSHION_HEIGHT_COPILOT = 559954833;
    public static final int ID_TURN_CUSHION_HEIGHT_DRIVER = 559954832;
    public static final int ID_TURN_CUSHION_PERCENT_DRIVER = 557857825;
    public static final int ID_TURN_HEADREST_HEIGHT_PERCENT_DRIVER = 557857822;
    public static final int ID_TURN_HEADREST_HORIZATION_PERCENT_DRIVER = 557857821;
    public static final int ID_TURN_HEADREST_TURN_HEIGHT_BACK_LEFT = 557857630;
    public static final int ID_TURN_HEADREST_TURN_HEIGHT_BACK_RIGHT = 557857631;
    public static final int ID_TURN_HEADREST_TURN_HEIGHT_COPILOT = 557857629;
    public static final int ID_TURN_HEADREST_TURN_HEIGHT_DRIVER = 557857628;
    public static final int ID_TURN_REARVIEW_MIRROR = 557857652;
    public static final int ID_TURN_REARVIEW_MIRROR_IS = 557857956;
    public static final int ID_TURN_SEATBACKREST_PERCENT_DRIVER = 557857816;
    public static final int ID_TURN_SEATBACKREST_PERCENT_PASSENGER = 557857817;
    public static final int ID_TURN_SEAT_BACKREST_BACK_LEFT = 557857642;
    public static final int ID_TURN_SEAT_BACKREST_BACK_RIGHT = 557857643;
    public static final int ID_TURN_SEAT_BACKREST_DRIVER = 557857640;
    public static final int ID_TURN_SEAT_BACK_REAR_LEFT_LEFT_R_C_S = 557858047;
    public static final int ID_TURN_SEAT_BACK_REAR_LEFT_RIGHT_R_C_S = 557858048;
    public static final int ID_TURN_SEAT_BACK_REAR_LEFT_R_C_S = 557858046;
    public static final int ID_TURN_SEAT_BACK_REAR_RIGHT_LEFT_R_C_S = 557858050;
    public static final int ID_TURN_SEAT_BACK_REAR_RIGHT_RIGHT_R_C_S = 557858051;
    public static final int ID_TURN_SEAT_BACK_REAR_RIGHT_R_C_S = 557858049;
    public static final int ID_TURN_SEAT_CALIPER_SPLINT_REAR_LEFT_LEFT_R_C_S = 557858053;
    public static final int ID_TURN_SEAT_CALIPER_SPLINT_REAR_LEFT_RIGHT_R_C_S = 557858054;
    public static final int ID_TURN_SEAT_CALIPER_SPLINT_REAR_LEFT_R_C_S = 557858052;
    public static final int ID_TURN_SEAT_CALIPER_SPLINT_REAR_RIGHT_LEFT_R_C_S = 557858056;
    public static final int ID_TURN_SEAT_CALIPER_SPLINT_REAR_RIGHT_RIGHT_R_C_S = 557858057;
    public static final int ID_TURN_SEAT_CALIPER_SPLINT_REAR_RIGHT_R_C_S = 557858055;
    public static final int ID_TURN_SEAT_CUSHION_REAR_LEFT_LEFT_R_C_S = 557858041;
    public static final int ID_TURN_SEAT_CUSHION_REAR_LEFT_RIGHT_R_C_S = 557858042;
    public static final int ID_TURN_SEAT_CUSHION_REAR_LEFT_R_C_S = 557858040;
    public static final int ID_TURN_SEAT_CUSHION_REAR_RIGHT_LEFT_R_C_S = 557858044;
    public static final int ID_TURN_SEAT_CUSHION_REAR_RIGHT_RIGHT_R_C_S = 557858045;
    public static final int ID_TURN_SEAT_CUSHION_REAR_RIGHT_R_C_S = 557858043;
    public static final int ID_TURN_SEAT_HEIGHT_BACK_LEFT = 557857646;
    public static final int ID_TURN_SEAT_HEIGHT_BACK_RIGHT = 557857647;
    public static final int ID_TURN_SEAT_HEIGHT_COPILOT = 557857645;
    public static final int ID_TURN_SEAT_HEIGHT_DRIVER = 557857644;
    public static final int ID_TURN_SEAT_HEIGHT_PERCENT_COPILOT = 557857812;
    public static final int ID_TURN_SEAT_HEIGHT_PERCENT_DRIVER = 557857820;
    public static final int ID_TURN_SEAT_HORIZATION_BACK_LEFT = 557857650;
    public static final int ID_TURN_SEAT_HORIZATION_BACK_RIGHT = 557857651;
    public static final int ID_TURN_SEAT_HORIZATION_COPILOT = 557857649;
    public static final int ID_TURN_SEAT_HORIZATION_DRIVER = 557857648;
    public static final int ID_TURN_SEAT_HORIZATION_PERCENT_DRIVER = 557857818;
    public static final int ID_TURN_SEAT_HORIZATION_PERCENT_PASSENGER = 557857819;
    public static final int ID_TURN_SEAT_HORIZATION_REAR_LEFT_LEFT_R_C_S = 557858033;
    public static final int ID_TURN_SEAT_HORIZATION_REAR_LEFT_RIGHT_R_C_S = 557858034;
    public static final int ID_TURN_SEAT_HORIZATION_REAR_LEFT_R_C_S = 557858032;
    public static final int ID_TURN_SEAT_HORIZATION_REAR_RIGHT_LEFT_R_C_S = 557858036;
    public static final int ID_TURN_SEAT_HORIZATION_REAR_RIGHT_RIGHT_R_C_S = 557858037;
    public static final int ID_TURN_SEAT_HORIZATION_REAR_RIGHT_R_C_S = 557858035;
    public static final int ID_TURN_SEAT__CALIPER_SPLINT_BACK_LEFT = 557857634;
    public static final int ID_TURN_SEAT__CALIPER_SPLINT_BACK_RIGHT = 557857635;
    public static final int ID_TURN_SEAT__CALIPER_SPLINT_COPILOT = 557857633;
    public static final int ID_TURN_SEAT__CALIPER_SPLINT_DRIVER = 557857632;
    public static final int ID_TURN_SEAT__CUSHION_BACK_LEFT = 557857638;
    public static final int ID_TURN_SEAT__CUSHION_BACK_RIGHT = 557857639;
    public static final int ID_TURN_SEAT__CUSHION_COPILOT = 557857637;
    public static final int ID_TURN_SEAT__CUSHION_DRIVER = 557857636;
    public static final int ID_TURN_STEERING_WHEEL = 557857653;
    public static final int ID_UCU_MASSAGE_LEVEL_DEPUTY_R = 557858193;
    public static final int ID_UCU_MASSAGE_LEVEL_MAIN_R = 557858192;
    public static final int ID_UCU_MASSAGE_MODE_DEPUTY_R = 557858191;
    public static final int ID_UCU_MASSAGE_MODE_MAIN_R = 557858190;
    public static final int ID_UNIT_DEFAULT_RESET = 557858326;
    public static final int ID_VOICE_CTL_BACK_DOOR = 557857815;
    public static final int ID_VOICE_OPEN_MIRRORLS_TURNED_STATE = 557857953;
    public static final int ID_VOICE_TIPS_MESSAGE_R = 557857977;
    public static final int ID_V_S_P_STATE_R = 557858418;
    public static final int ID_V_S_P_STATE_S = 557858419;
    public static final int ID_WADING_PATTERN_SOC_TIPS_R = 557858107;
    public static final int ID_WADING_PATTERN_SPEED_TIPS_R = 557858108;
    public static final int ID_WADING_PATTERN_STATE_TIPS_R = 557858110;
    public static final int ID_WADING_PATTERN_TEST_TIPS_R = 557858109;
    public static final int ID_WASH_ELECTRIC_PEDAL_R = 557858159;
    public static final int ID_WELCOME_ACTIVE_R = 557858363;
    public static final int ID_WELCOME_STATE_R = 557858362;
    public static final int ID_WELCOME_STATE_S = 557858404;
    public static final int ID_WELCOME_SUPPORTED_R = 557858371;
    public static final int ID_WIPE_MODE_S = 557857950;
    public static final int ID_WORK_COMMOND_S = 557857988;
    public static final int ID_WORK_STATE_R = 557857991;
    public static final int ID_WORK_STATE_S = 557857986;
    public static final int ID_WORK_TEMPERATURE_S = 557857987;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.setting";
    public static final String TAG = "CAR.VENDOR.SETTING";

    public interface OnCarSettingEventListener {
        void onSettingErrorEvent(int i, int i2);

        void onSettingEventChange(SettingEventChangeValue settingEventChangeValue);
    }

    void addCarSettingEventListener(OnCarSettingEventListener onCarSettingEventListener) throws CarNotConnectedException;

    int get5LevelSeatHeatingLevelBackLeftR() throws CarNotConnectedException;

    int get5LevelSeatHeatingLevelBackRightR() throws CarNotConnectedException;

    int get5LevelSeatHeatingLevelDomainR() throws CarNotConnectedException;

    int get5LevelSeatHeatingLevelPassengerR() throws CarNotConnectedException;

    int get5LevelSeatHeatingModeBackLeftR() throws CarNotConnectedException;

    int get5LevelSeatHeatingModeBackRightR() throws CarNotConnectedException;

    int get5LevelSeatHeatingModeDomainR() throws CarNotConnectedException;

    int get5LevelSeatHeatingModePassengerR() throws CarNotConnectedException;

    int get5LevelSeatHeatingStateBackLeftR() throws CarNotConnectedException;

    int get5LevelSeatHeatingStateBackRightR() throws CarNotConnectedException;

    int get5LevelSeatHeatingStateDomainR() throws CarNotConnectedException;

    int get5LevelSeatHeatingStatePassengerR() throws CarNotConnectedException;

    int get5LevelSeatVentilatingLevelBackLeftR() throws CarNotConnectedException;

    int get5LevelSeatVentilatingLevelBackRightR() throws CarNotConnectedException;

    int get5LevelSeatVentilatingLevelDomainR() throws CarNotConnectedException;

    int get5LevelSeatVentilatingLevelPassengerR() throws CarNotConnectedException;

    int get5LevelSeatVentilatingModeBackLeftR() throws CarNotConnectedException;

    int get5LevelSeatVentilatingModeBackRightR() throws CarNotConnectedException;

    int get5LevelSeatVentilatingModeDomainR() throws CarNotConnectedException;

    int get5LevelSeatVentilatingModePassengerR() throws CarNotConnectedException;

    int get5LevelSeatVentilatingStateBackLeftR() throws CarNotConnectedException;

    int get5LevelSeatVentilatingStateBackRightR() throws CarNotConnectedException;

    int get5LevelSeatVentilatingStateDomainR() throws CarNotConnectedException;

    int get5LevelSeatVentilatingStatePassengerR() throws CarNotConnectedException;

    int getACAutoAirR() throws CarNotConnectedException;

    int getACBTWindR() throws CarNotConnectedException;

    int getACPauseCycleR() throws CarNotConnectedException;

    int getACTunnelCycleR() throws CarNotConnectedException;

    int getAcWarmModeR() throws CarNotConnectedException;

    int getAlarmPromptStateR() throws CarNotConnectedException;

    int getAmbientAreaSupport() throws CarNotConnectedException;

    int getAmbientFeedBack() throws CarNotConnectedException;

    int getAmbientLightColorSupport() throws CarNotConnectedException;

    int getAmbientLightSupport() throws CarNotConnectedException;

    int getAmbientMulticolorModeR() throws CarNotConnectedException;

    int getAmbientMulticolorStateR() throws CarNotConnectedException;

    int getAmbientMusicModeR() throws CarNotConnectedException;

    int getAmbientMusicModeStateR() throws CarNotConnectedException;

    int getAutoExternalRearMirrorFollowUpSwitchR() throws CarNotConnectedException;

    int getAutoExternalRearMirrorFollowUpSwitchSupport() throws CarNotConnectedException;

    int getAutoPowerOffStateR() throws CarNotConnectedException;

    int getAutoPowerOnStateR() throws CarNotConnectedException;

    int getAutoPurificationState() throws CarNotConnectedException;

    int getBackDoorOpenedHeightR() throws CarNotConnectedException;

    int getBackHomeLightDelayValueR() throws CarNotConnectedException;

    int getBdMassageLevelLeftRearR() throws CarNotConnectedException;

    int getBdMassageLevelRightRearR() throws CarNotConnectedException;

    int getBdMassageModeLeftRearR() throws CarNotConnectedException;

    int getBdMassageModeRightRearR() throws CarNotConnectedException;

    int getBleOpenDriverDoorStateR() throws CarNotConnectedException;

    int getBrakeCloseDoorR() throws CarNotConnectedException;

    int getBrakePedalModeR() throws CarNotConnectedException;

    int getBsisCollisionStateR() throws CarNotConnectedException;

    int getBsisFuncStatusR() throws CarNotConnectedException;

    int getCampingBlanceActionR() throws CarNotConnectedException;

    int getCampingBlanceStateR() throws CarNotConnectedException;

    int getCampingBlanceSupportedR() throws CarNotConnectedException;

    int getCanopyAutomaticDimmingR() throws CarNotConnectedException;

    int getCarFridgeTypeR() throws CarNotConnectedException;

    int getCarSeatLayoutR() throws CarNotConnectedException;

    int getCenterRearSafebeltStatusR() throws CarNotConnectedException;

    int getChargingPortR27C() throws CarNotConnectedException;

    int getChargingPortR475() throws CarNotConnectedException;

    int getCloseDoorLevelR() throws CarNotConnectedException;

    int getComSpeedDownStateR() throws CarNotConnectedException;

    int getComSpeedUpStateR() throws CarNotConnectedException;

    int getComfortableParkingStateR() throws CarNotConnectedException;

    int getControlCallbackStateR() throws CarNotConnectedException;

    int getCpdImsSwitchStateR() throws CarNotConnectedException;

    int getCuraSwitchStateR() throws CarNotConnectedException;

    int getCurrentModeOfTailStateR() throws CarNotConnectedException;

    int getCurrentStateOfTheTailStateR() throws CarNotConnectedException;

    int getCurrentStatusOfDimmingSunroofR() throws CarNotConnectedException;

    int getCurrentTransmittanceOfSkylightR() throws CarNotConnectedException;

    int getCvcModeR() throws CarNotConnectedException;

    int getDampStateR() throws CarNotConnectedException;

    int getDelayDownPowerTimeR() throws CarNotConnectedException;

    int getDelayPowerDownTimeR() throws CarNotConnectedException;

    int getDelayPowerOffStateR() throws CarNotConnectedException;

    int getDelayPowerOffStatusR() throws CarNotConnectedException;

    int getDelayPowerOffTimeR() throws CarNotConnectedException;

    int getDimmingCanopyConfigurationR() throws CarNotConnectedException;

    int getDimmingCanopyLowCodeR() throws CarNotConnectedException;

    int getDisinfStateR() throws CarNotConnectedException;

    int getDlgMessageCodeR() throws CarNotConnectedException;

    int getDoorHeightSupport() throws CarNotConnectedException;

    int getDoorknobAutoShrinkStateR() throws CarNotConnectedException;

    int getDragAndDropModeTipsStateR() throws CarNotConnectedException;

    int getDragConnectionTypeR() throws CarNotConnectedException;

    int getDragModeForwardAndBackwardStateR() throws CarNotConnectedException;

    int getDragTypeR() throws CarNotConnectedException;

    int getDriveModeR() throws CarNotConnectedException;

    int getDriverMasToggleModeConfigR() throws CarNotConnectedException;

    int getDriverSeatAutoReturnB3R() throws CarNotConnectedException;

    int getDriverSeatAutoReturnR() throws CarNotConnectedException;

    int getDriverSeatHeating437StateR() throws CarNotConnectedException;

    int getDriverSeatVentilating437StateR() throws CarNotConnectedException;

    int getDyHeightLevelR() throws CarNotConnectedException;

    int getDyHeightToggleStateR() throws CarNotConnectedException;

    int getDyHeightTurnActionR() throws CarNotConnectedException;

    int getDynamicSuspensionModeR() throws CarNotConnectedException;

    int getEcuMassageLevelBackLeftR() throws CarNotConnectedException;

    int getEcuMassageLevelBackRightR() throws CarNotConnectedException;

    int getEcuMassageModeBackLeftR() throws CarNotConnectedException;

    int getEcuMassageModeBackRightR() throws CarNotConnectedException;

    int getElectricTailConfigurationR() throws CarNotConnectedException;

    int getElectricTailMaintenanceFuntatusStateR() throws CarNotConnectedException;

    int getElectroniChildLockConfigSecond() throws CarNotConnectedException;

    int getEnergyFeedbackR341() throws CarNotConnectedException;

    int getEngineSoundTypeR() throws CarNotConnectedException;

    int getExclusiveSaveFlagR() throws CarNotConnectedException;

    int getExhibitionMode() throws CarNotConnectedException;

    int getExhibitionStartEnginePermitR() throws CarNotConnectedException;

    int getFdCampingBlanceActionR() throws CarNotConnectedException;

    int getFdCampingBlanceStateR() throws CarNotConnectedException;

    int getFdCampingBlanceSupportedR() throws CarNotConnectedException;

    int getFdComSpeedUpStateR() throws CarNotConnectedException;

    int getFdCrcCheckNumR() throws CarNotConnectedException;

    int getFdDampStateR() throws CarNotConnectedException;

    int getFdDyHeightLevelR() throws CarNotConnectedException;

    int getFdDyHeightToggleStateR() throws CarNotConnectedException;

    int getFdDyHeightTurnActionR() throws CarNotConnectedException;

    int getFdDynamicSuspensionModeR() throws CarNotConnectedException;

    int getFdFixedStateR() throws CarNotConnectedException;

    int getFdFrontBoxTakeStateR() throws CarNotConnectedException;

    int getFdLiftModeStateR() throws CarNotConnectedException;

    int getFdModeSupportedHturnR() throws CarNotConnectedException;

    int getFdSpeedSupportedTurnR() throws CarNotConnectedException;

    int getFdTailBoxTakeStateR() throws CarNotConnectedException;

    int getFdTailBoxTakeSupportedR() throws CarNotConnectedException;

    int getFdTrailerModeCodeR() throws CarNotConnectedException;

    int getFdWelcomeActiveR() throws CarNotConnectedException;

    int getFdWelcomeStateR() throws CarNotConnectedException;

    int getFdWelcomeSupportedR() throws CarNotConnectedException;

    int getFixedStateR() throws CarNotConnectedException;

    int getFridgeChildLockFlagR() throws CarNotConnectedException;

    int getFridgeChildLockR() throws CarNotConnectedException;

    int getFridgeDelayedPowerOutageR() throws CarNotConnectedException;

    int getFridgeDoorStateR() throws CarNotConnectedException;

    int getFridgeExpansionAndContractionFunctionR() throws CarNotConnectedException;

    int getFridgeLocationR() throws CarNotConnectedException;

    int getFridgeMinWagePowerGenerationR() throws CarNotConnectedException;

    int getFridgeNumR() throws CarNotConnectedException;

    int getFridgeOnePositionR() throws CarNotConnectedException;

    int getFridgeScreenFlagR() throws CarNotConnectedException;

    int getFridgeTemperatureRegulationR() throws CarNotConnectedException;

    int getFridgeThreePositionR() throws CarNotConnectedException;

    int getFridgeTwoPositionR() throws CarNotConnectedException;

    int getFridgeWorkColdTemperatureR() throws CarNotConnectedException;

    int getFridgeWorkHeatTemperatureR() throws CarNotConnectedException;

    int getFridgeWorkSaveTemperatureR() throws CarNotConnectedException;

    int getFridgeWorkingModeR() throws CarNotConnectedException;

    int getFrontFridgeDisinfectFlagR() throws CarNotConnectedException;

    int getFrontWiperSpeedValueR() throws CarNotConnectedException;

    int getGoalRoadModeR() throws CarNotConnectedException;

    int getHSpeedAutoCloseStateR() throws CarNotConnectedException;

    int getHeadLampConfig() throws CarNotConnectedException;

    int getHeadlampLevelR() throws CarNotConnectedException;

    int getHeadrestHorizationBackLeft() throws CarNotConnectedException;

    int getHeadrestHorizationBackRight() throws CarNotConnectedException;

    int getHeadrestHorizationCopilot() throws CarNotConnectedException;

    int getHeadrestHorizationDriver() throws CarNotConnectedException;

    float getHeadrestTurnHeightBackLeft() throws CarNotConnectedException;

    float getHeadrestTurnHeightBackRight() throws CarNotConnectedException;

    float getHeadrestTurnHeightCopilot() throws CarNotConnectedException;

    float getHeadrestTurnHeightDriver() throws CarNotConnectedException;

    int getHevChargingPortR() throws CarNotConnectedException;

    int getHsMassageStateBackLeftR() throws CarNotConnectedException;

    int getHsMassageStateBackRightR() throws CarNotConnectedException;

    int getHsMassageStateDomainR() throws CarNotConnectedException;

    int getHsMassageStatePassengerR() throws CarNotConnectedException;

    int getHudBrightnessR() throws CarNotConnectedException;

    int getHudDisplayContentRDrive() throws CarNotConnectedException;

    int getHudDisplayContentRNavigation() throws CarNotConnectedException;

    int getHudHeightR() throws CarNotConnectedException;

    int getHudModeParams() throws CarNotConnectedException;

    int getHudModeR() throws CarNotConnectedException;

    int getHudNavigationStateR() throws CarNotConnectedException;

    int getHudPatternR() throws CarNotConnectedException;

    int getHudRotationAngleR() throws CarNotConnectedException;

    int getHudSafeDriveStateR() throws CarNotConnectedException;

    int getHudScreenProjectionStateR() throws CarNotConnectedException;

    int getHudStateR() throws CarNotConnectedException;

    int getIALAreaR() throws CarNotConnectedException;

    int getIALBrightnessBackR() throws CarNotConnectedException;

    int getIALBrightnessFrontR() throws CarNotConnectedException;

    int getIALBrightnessStarRingR() throws CarNotConnectedException;

    int getIALBrightnessStarrySkyR() throws CarNotConnectedException;

    int getIALColorBackR() throws CarNotConnectedException;

    int getIALColorFrontR() throws CarNotConnectedException;

    int getIALColorStarRingR() throws CarNotConnectedException;

    int getIALColorStarrySkyR() throws CarNotConnectedException;

    int getINSThemeR() throws CarNotConnectedException;

    int getInsideLightDoorStateR() throws CarNotConnectedException;

    int getIntelLockOpenDriverDoorR() throws CarNotConnectedException;

    int getIntelligentAcAreaStateS() throws CarNotConnectedException;

    int getIntelligentLockStateR() throws CarNotConnectedException;

    int getIntelligentUnlockStateR() throws CarNotConnectedException;

    int getIviProfileSettingsR() throws CarNotConnectedException;

    int getKeyVibratorLevelR() throws CarNotConnectedException;

    int getLanternLanguageModeR() throws CarNotConnectedException;

    int getLeaveCarPInd() throws CarNotConnectedException;

    int getLeftElectricPedalR() throws CarNotConnectedException;

    int getLeftHeadlampLevel() throws CarNotConnectedException;

    int getLeftHomeLightDelayValueR() throws CarNotConnectedException;

    int getLeftRearSafebeltStatusR() throws CarNotConnectedException;

    int getLeftRseChildModeSwitchStatusR() throws CarNotConnectedException;

    int getLeftThirdSafebeltStatusR() throws CarNotConnectedException;

    int getLiftModeStateR() throws CarNotConnectedException;

    int getLockCarRiseWindowR12F() throws CarNotConnectedException;

    int getLockCarRiseWindowR48E() throws CarNotConnectedException;

    int getLockOffR() throws CarNotConnectedException;

    int getLrHotStone2MassageConfigR() throws CarNotConnectedException;

    int getLrHotStone3MassageConfigR() throws CarNotConnectedException;

    int getLrHotStoneMassageConfigR() throws CarNotConnectedException;

    int getLrMas2ToggleModeConfigR() throws CarNotConnectedException;

    int getLrMasToggleModeConfigR() throws CarNotConnectedException;

    int getLrNormal2MassageConfigR() throws CarNotConnectedException;

    int getLrNormalMassageConfigR() throws CarNotConnectedException;

    int getLrseChildModeCodeWriteFbR() throws CarNotConnectedException;

    int getLrseChildModeTimeWriteFbR() throws CarNotConnectedException;

    int getLrseMachineSwitchStateR() throws CarNotConnectedException;

    int getMain2HotStoneMassageConfigR() throws CarNotConnectedException;

    int getMainHsMassageStateR() throws CarNotConnectedException;

    int getMainNormalMassageConfigR() throws CarNotConnectedException;

    int getMainSeatBackInitR() throws CarNotConnectedException;

    int getMainSeatBackInitedR() throws CarNotConnectedException;

    int getMainSeatHightInitedR() throws CarNotConnectedException;

    int getMainSeatHorizontalInitR() throws CarNotConnectedException;

    int getMainSeatHorizontalInitedR() throws CarNotConnectedException;

    int getMassageLevelLeftBack() throws CarNotConnectedException;

    int getMassageLevelLeftBackECU() throws CarNotConnectedException;

    int getMassageLevelLeftFront() throws CarNotConnectedException;

    int getMassageLevelLeftFrontECU() throws CarNotConnectedException;

    int getMassageLevelRightBack() throws CarNotConnectedException;

    int getMassageLevelRightBackECU() throws CarNotConnectedException;

    int getMassageLevelRightFront() throws CarNotConnectedException;

    int getMassageLevelRightFrontECU() throws CarNotConnectedException;

    int getMassageModeR() throws CarNotConnectedException;

    int getMassageModeR3CA() throws CarNotConnectedException;

    int getMassageModeR402() throws CarNotConnectedException;

    int getMassageModeR42F() throws CarNotConnectedException;

    int getMassageModeR4FA() throws CarNotConnectedException;

    int getMassageModeR4FALeftBack() throws CarNotConnectedException;

    int getMassageModeR4FARightBack() throws CarNotConnectedException;

    int getMemorySetOperationFlagR() throws CarNotConnectedException;

    int getMeterExportAreaR() throws CarNotConnectedException;

    int getMicroSwitchLockWindowStateR() throws CarNotConnectedException;

    int getMicroSwitchUnlockWindowStateR() throws CarNotConnectedException;

    int getMissPackageCountR() throws CarNotConnectedException;

    int getModeSupportedHturnR() throws CarNotConnectedException;

    int getMoisAssembleStateR() throws CarNotConnectedException;

    int getMoisFuncStatusR() throws CarNotConnectedException;

    int getMotorPretensionerSeatbeltStateR() throws CarNotConnectedException;

    int getNfcOpenDriverDoorR() throws CarNotConnectedException;

    int getOffReminder() throws CarNotConnectedException;

    int getOnFdCrcCheckNumR() throws CarNotConnectedException;

    int getOnSafeBeltWarnStateChanged() throws CarNotConnectedException;

    int getOneKeyEnterPitConfigR() throws CarNotConnectedException;

    int getOneLevelSeatHeatingStateR() throws CarNotConnectedException;

    int getOverspeedLockR() throws CarNotConnectedException;

    int getPM25PowerR() throws CarNotConnectedException;

    int getPM25SwitchCheckR() throws CarNotConnectedException;

    int getPM25TimeCheckR() throws CarNotConnectedException;

    int getPabCodeR() throws CarNotConnectedException;

    int getPabStateR() throws CarNotConnectedException;

    int getPas2HotStoneMassageConfigR() throws CarNotConnectedException;

    int getPasHotStoneMassageConfigR() throws CarNotConnectedException;

    int getPasNormalMassageConfigR() throws CarNotConnectedException;

    int getPassangerSeatBackInitR() throws CarNotConnectedException;

    int getPassangerSeatHorizontalInitR() throws CarNotConnectedException;

    int getPassengerLastLeftStateR() throws CarNotConnectedException;

    int getPassengerLastMiddleStateR() throws CarNotConnectedException;

    int getPassengerLastRightStateR() throws CarNotConnectedException;

    int getPassengerMasToggleModeConfigR() throws CarNotConnectedException;

    int getPassengerRearLeftStateR() throws CarNotConnectedException;

    int getPassengerRearMiddleStateR() throws CarNotConnectedException;

    int getPassengerRearRightStateR() throws CarNotConnectedException;

    int getPassengerSeatHeating3caStateR() throws CarNotConnectedException;

    int getPassengerSeatVentilating3caStateR() throws CarNotConnectedException;

    int getPassengerStateR() throws CarNotConnectedException;

    int getPedalSuppressionSystemStateR() throws CarNotConnectedException;

    int getPhoneRemindCodeR() throws CarNotConnectedException;

    int getPhysicalkeyConfigR() throws CarNotConnectedException;

    int getPhysicalkeySecuConfigR() throws CarNotConnectedException;

    int getPhysicalkeySecupConfigR() throws CarNotConnectedException;

    int getPhysicalkeySecurlConfigR() throws CarNotConnectedException;

    int getPhysicalkeySecurrConfigR() throws CarNotConnectedException;

    int getRainAutoLockWindowStateR() throws CarNotConnectedException;

    int getRearAcPanelAutoLockTimeR() throws CarNotConnectedException;

    int getRearDoorStateR() throws CarNotConnectedException;

    int getRearFridgeDisinfectFlagR() throws CarNotConnectedException;

    int getRearFridgeKeyTypeFlagR() throws CarNotConnectedException;

    int getRearFridgeWorkTypeFlagR() throws CarNotConnectedException;

    int getRearHeatTempR() throws CarNotConnectedException;

    int getRearLeftMassageSwConfigurationR() throws CarNotConnectedException;

    int getRearRightFridgeHeatingSchemeR() throws CarNotConnectedException;

    int getRearRightMassageSwConfigurationR() throws CarNotConnectedException;

    int getRearRightSeatGreetStateR() throws CarNotConnectedException;

    int getRearSaveTempR() throws CarNotConnectedException;

    int getRearSeatGreetStateR() throws CarNotConnectedException;

    int getRearViewEcuMirrorFlipStateR() throws CarNotConnectedException;

    int getRearViewMirrorFlipR() throws CarNotConnectedException;

    int getRearViewMirrorFlipSetOneR() throws CarNotConnectedException;

    int getRearViewMirrorFlipSetTwoR() throws CarNotConnectedException;

    int getRearWiperSpeedValueR() throws CarNotConnectedException;

    int getRearviewMirrorIsAutoLightStateR() throws CarNotConnectedException;

    int getRearviewMirrorIsStateR() throws CarNotConnectedException;

    int getRearviewMirrorIsTurnedStateR() throws CarNotConnectedException;

    float getRearviewMirrorXLeft() throws CarNotConnectedException;

    float getRearviewMirrorXRight() throws CarNotConnectedException;

    float getRearviewMirrorYLeft() throws CarNotConnectedException;

    float getRearviewMirrorYRight() throws CarNotConnectedException;

    int getRecoverOrSaveParamsChanged2() throws CarNotConnectedException;

    int getRecoverOrSaveParamsChanged3() throws CarNotConnectedException;

    int getRecoverOrSaveParamsChanged4() throws CarNotConnectedException;

    int getRecoverOrSaveParamsChanged5() throws CarNotConnectedException;

    int getRecoverOrSaveParamsChanged6() throws CarNotConnectedException;

    int getRecoverOrSaveParamsChanged7() throws CarNotConnectedException;

    int getRefrig1PositionMarkBitR() throws CarNotConnectedException;

    int getRefrig2PositionMarkBitR() throws CarNotConnectedException;

    int getRefrigNumFlagR() throws CarNotConnectedException;

    int getRefrigWorkFlagR() throws CarNotConnectedException;

    int getRefrigWorkModeR() throws CarNotConnectedException;

    int getRefrigeratorDoorState() throws CarNotConnectedException;

    int getRefrigeratorMobileTerminalPushReR() throws CarNotConnectedException;

    int getRefrigeratorModeR() throws CarNotConnectedException;

    int getRefrigeratorState() throws CarNotConnectedException;

    int getRefrigeratorTemperatureR() throws CarNotConnectedException;

    int getRefrigeratorWorkModeR() throws CarNotConnectedException;

    int getRefrigeratorWorkStateR() throws CarNotConnectedException;

    int getRefrigeratorWorkTemperatureR() throws CarNotConnectedException;

    int getRemberPowerOffStateR() throws CarNotConnectedException;

    int getRemenberPowerOffStateR() throws CarNotConnectedException;

    int getRemoteControlDownwindowStateR() throws CarNotConnectedException;

    int getRemoteControlUpwindowStateR() throws CarNotConnectedException;

    int getRemoteKeyStartAcTimeR() throws CarNotConnectedException;

    int getRightElectricPedalR() throws CarNotConnectedException;

    int getRightHeadlampLevel() throws CarNotConnectedException;

    int getRightRearSafebeltStatusR() throws CarNotConnectedException;

    int getRightRseChildModeSwitchStatusR() throws CarNotConnectedException;

    int getRightThirdSafebeltStatusR() throws CarNotConnectedException;

    int getRncStateR() throws CarNotConnectedException;

    int getRrEcuMassageLevelR() throws CarNotConnectedException;

    int getRrEcuMassageModeR() throws CarNotConnectedException;

    int getRrHotStone2MassageConfigR() throws CarNotConnectedException;

    int getRrHotStone3MassageConfigR() throws CarNotConnectedException;

    int getRrHotStone4MassageConfigR() throws CarNotConnectedException;

    int getRrHotStoneMassageConfigR() throws CarNotConnectedException;

    int getRrMas2ToggleModeConfigR() throws CarNotConnectedException;

    int getRrMasToggleModeConfigR() throws CarNotConnectedException;

    int getRrNormal2MassageConfigR() throws CarNotConnectedException;

    int getRrNormal3MassageConfigR() throws CarNotConnectedException;

    int getRrNormalMassageConfigR() throws CarNotConnectedException;

    int getRrseChildModeCodeWriteFbR() throws CarNotConnectedException;

    int getRrseChildModeTimeWriteFbR() throws CarNotConnectedException;

    int getRrseMachineSwitchStateR() throws CarNotConnectedException;

    int getRuningCloseDoorStateR() throws CarNotConnectedException;

    int getSOCTargetR() throws CarNotConnectedException;

    int getSafeBeltCloseDriveDoorR() throws CarNotConnectedException;

    int getSafeBeltWarnStateR() throws CarNotConnectedException;

    int getSandAbsModeStateR() throws CarNotConnectedException;

    int getSandAbsTipMessageR() throws CarNotConnectedException;

    int getSaveOwnerStateR() throws CarNotConnectedException;

    int getScreenAngleR() throws CarNotConnectedException;

    int getScreenAngleValueR() throws CarNotConnectedException;

    int getScreenAntiPinchStateR() throws CarNotConnectedException;

    int getScreenCallbackMessageR() throws CarNotConnectedException;

    int getScreenRotationActionR() throws CarNotConnectedException;

    int getScreenRotationOverHotStateR() throws CarNotConnectedException;

    int getScreenVoltageStateR() throws CarNotConnectedException;

    int getSeaModeChargingSocTypeR() throws CarNotConnectedException;

    int getSeatDistributionFormConfigR() throws CarNotConnectedException;

    int getSeatHeatingNotL1PStateR() throws CarNotConnectedException;

    int getSeatHeatingState2BackLeftR() throws CarNotConnectedException;

    int getSeatHeatingState2BackRightR() throws CarNotConnectedException;

    int getSeatHeatingStateBackLeftDomainControlR() throws CarNotConnectedException;

    int getSeatHeatingStateBackLeftECU() throws CarNotConnectedException;

    int getSeatHeatingStateBackRightDomainControlR() throws CarNotConnectedException;

    int getSeatHeatingStateBackRightECU() throws CarNotConnectedException;

    int getSeatHeatingStateCopilotECU() throws CarNotConnectedException;

    int getSeatHeatingStateCopilotL1P() throws CarNotConnectedException;

    int getSeatHeatingStateCopilotNonL1P() throws CarNotConnectedException;

    int getSeatHeatingStateDriverDomainControl() throws CarNotConnectedException;

    int getSeatHeatingStateDriverECU() throws CarNotConnectedException;

    int getSeatHorizationBackLeft() throws CarNotConnectedException;

    int getSeatHorizationBackRight() throws CarNotConnectedException;

    int getSeatHorizationCopilot() throws CarNotConnectedException;

    int getSeatHorizationDriver() throws CarNotConnectedException;

    int getSeatSecullDistributionFormConfigR() throws CarNotConnectedException;

    int getSeatSeculrDistributionFormConfigR() throws CarNotConnectedException;

    int getSeatSecurlDistributionFormConfigR() throws CarNotConnectedException;

    int getSeatSecurrDistributionFormConfigR() throws CarNotConnectedException;

    float getSeatTurnHeightBackLeft() throws CarNotConnectedException;

    float getSeatTurnHeightBackRight() throws CarNotConnectedException;

    float getSeatTurnHeightCopilot() throws CarNotConnectedException;

    float getSeatTurnHeightDriver() throws CarNotConnectedException;

    int getSeatVentilatingNotL1PStateR() throws CarNotConnectedException;

    int getSeatVentilatingState2BackLeftR() throws CarNotConnectedException;

    int getSeatVentilatingState2BackRightR() throws CarNotConnectedException;

    int getSeatVentilatingStateBackLeftDomainControlR() throws CarNotConnectedException;

    int getSeatVentilatingStateBackLeftECU() throws CarNotConnectedException;

    int getSeatVentilatingStateBackRightDomainControlR() throws CarNotConnectedException;

    int getSeatVentilatingStateBackRightECU() throws CarNotConnectedException;

    int getSeatVentilatingStateCopilotECU() throws CarNotConnectedException;

    int getSeatVentilatingStateCopilotL1P() throws CarNotConnectedException;

    int getSeatVentilatingStateCopilotNonL1P() throws CarNotConnectedException;

    int getSeatVentilatingStateDriverDomainControlR() throws CarNotConnectedException;

    int getSeatVentilatingStateDriverECU() throws CarNotConnectedException;

    float getSeatbackrestPostionBackLeft() throws CarNotConnectedException;

    float getSeatbackrestPostionBackRight() throws CarNotConnectedException;

    float getSeatbackrestPostionCopilot() throws CarNotConnectedException;

    float getSeatbackrestPostionDriver() throws CarNotConnectedException;

    int getSecondLeftSeatBackInitR() throws CarNotConnectedException;

    int getSecondLeftSeatHorizontalInitR() throws CarNotConnectedException;

    int getSecondRightSeatBack2InitR() throws CarNotConnectedException;

    int getSecondRightSeatBackInitR() throws CarNotConnectedException;

    int getSecondRightSeatHorizontal2InitR() throws CarNotConnectedException;

    int getSecondRightSeatHorizontalInitR() throws CarNotConnectedException;

    int getSecuMassageLevelLeftRearR() throws CarNotConnectedException;

    int getSecuMassageLevelRightRearR() throws CarNotConnectedException;

    int getSecuMassageModeLeftRearR() throws CarNotConnectedException;

    int getSecuMassageModeRightRearR() throws CarNotConnectedException;

    int getServiceControlCallbackR() throws CarNotConnectedException;

    int getSkyCurtainPowerOffAndDimR() throws CarNotConnectedException;

    int getSmartWelcomeLightStateR() throws CarNotConnectedException;

    int getSomethingLeftInRefrigeratorR() throws CarNotConnectedException;

    int getSpeedReminderValueR() throws CarNotConnectedException;

    int getSpeedSupportedTurnR() throws CarNotConnectedException;

    int getSteerAssisR() throws CarNotConnectedException;

    int getSteerAssisSupported() throws CarNotConnectedException;

    int getSteerPositionAutoReturnR() throws CarNotConnectedException;

    int getSteeringWheelHeatedPlanR() throws CarNotConnectedException;

    int getSteeringWheelHeatingAutoStateR() throws CarNotConnectedException;

    int getSteeringWheelHeatingGearR() throws CarNotConnectedException;

    int getSteeringWheelHeatingStateR() throws CarNotConnectedException;

    float getSteeringWheelY() throws CarNotConnectedException;

    float getSteeringWheelZ() throws CarNotConnectedException;

    int getSterilizationStateR() throws CarNotConnectedException;

    int getSterilizationWorkStateR() throws CarNotConnectedException;

    int getSupportSeatMassageDeputy() throws CarNotConnectedException;

    int getSupportSeatMassageLeftRear() throws CarNotConnectedException;

    int getSupportSeatMassageMain() throws CarNotConnectedException;

    int getSupportSeatMassageRightRear() throws CarNotConnectedException;

    int getSwcontrolStateR() throws CarNotConnectedException;

    int getSwsMediumRelatedKeysR() throws CarNotConnectedException;

    int getTailBoxTakeStateR() throws CarNotConnectedException;

    int getTailBoxTakeSupportedR() throws CarNotConnectedException;

    int getTailInitializationStateR() throws CarNotConnectedException;

    int getTailMotorThermalProtectionStateR() throws CarNotConnectedException;

    int getTailPositionFeedbackR() throws CarNotConnectedException;

    int getTailPositionPercentageR() throws CarNotConnectedException;

    int getTapDoorStateR() throws CarNotConnectedException;

    int getThirdRowLeftBackrestDisplayR() throws CarNotConnectedException;

    int getThirdRowRightBackrestDisplayR() throws CarNotConnectedException;

    int getThirdRowSeatWorkModeR() throws CarNotConnectedException;

    int getTowingProhibitedStateR() throws CarNotConnectedException;

    int getTrailerDragTypeR() throws CarNotConnectedException;

    int getTrailerLargeItemConfigurationR() throws CarNotConnectedException;

    int getTrailerLargeLimitWeightR() throws CarNotConnectedException;

    int getTrailerLightCheckStateR() throws CarNotConnectedException;

    int getTrailerMiddleItemConfigurationR() throws CarNotConnectedException;

    int getTrailerMiddleLimitWeightR() throws CarNotConnectedException;

    int getTrailerModeCodeR() throws CarNotConnectedException;

    int getTrailerModeMileageR() throws CarNotConnectedException;

    int getTrailerModeStateR() throws CarNotConnectedException;

    int getTrailerSmallItemConfigurationR() throws CarNotConnectedException;

    int getTrailerSmallLimitWeightR() throws CarNotConnectedException;

    int getTrayInitState() throws CarNotConnectedException;

    int getTrayStateR() throws CarNotConnectedException;

    float getTurnCaliperSplintHeightBackLeft() throws CarNotConnectedException;

    float getTurnCaliperSplintHeightBackRight() throws CarNotConnectedException;

    float getTurnCaliperSplintHeightCopilot() throws CarNotConnectedException;

    float getTurnCaliperSplintHeightDriver() throws CarNotConnectedException;

    float getTurnCushionHeightBackLeft() throws CarNotConnectedException;

    float getTurnCushionHeightBackRight() throws CarNotConnectedException;

    float getTurnCushionHeightCopilot() throws CarNotConnectedException;

    float getTurnCushionHeightDriver() throws CarNotConnectedException;

    int getUcuMassageLevelDeputyR() throws CarNotConnectedException;

    int getUcuMassageLevelMainR() throws CarNotConnectedException;

    int getUcuMassageModeDeputyR() throws CarNotConnectedException;

    int getUcuMassageModeMainR() throws CarNotConnectedException;

    int getVoiceTipMessageR() throws CarNotConnectedException;

    int getVoiceTipsMessageR() throws CarNotConnectedException;

    int getVspStateR() throws CarNotConnectedException;

    int getWadingPatternSocTipsR() throws CarNotConnectedException;

    int getWadingPatternSpeedTipsR() throws CarNotConnectedException;

    int getWadingPatternStateTipsR() throws CarNotConnectedException;

    int getWadingPatternTestTipsR() throws CarNotConnectedException;

    int getWashElectricPedalR() throws CarNotConnectedException;

    int getWelcomeActiveR() throws CarNotConnectedException;

    int getWelcomeStateR() throws CarNotConnectedException;

    int getWelcomeSupportedR() throws CarNotConnectedException;

    int getWorkStateR() throws CarNotConnectedException;

    int getonSaveSeatParamsAllChanged3CA() throws CarNotConnectedException;

    int getonSaveSeatParamsAllChanged402() throws CarNotConnectedException;

    int getonSaveSeatParamsAllChanged42F() throws CarNotConnectedException;

    int getonSaveSeatParamsAllChangedPlace5() throws CarNotConnectedException;

    int getonSaveSeatParamsAllChangedPlace7() throws CarNotConnectedException;

    int getonScreenRotationStatus() throws CarNotConnectedException;

    int getonSeatHeatingStateChanged437() throws CarNotConnectedException;

    int getonSmartWelcomeLightStateChanged() throws CarNotConnectedException;

    int getonTrayStateChanged() throws CarNotConnectedException;

    int getresetMotorPretensionerSeatbelt() throws CarNotConnectedException;

    int getsocTargetLowLimitR() throws CarNotConnectedException;

    int getsocTargetRangeR() throws CarNotConnectedException;

    void removeCarSettingEventListener(OnCarSettingEventListener onCarSettingEventListener) throws CarNotConnectedException;

    void set5LevelSeatHeatingLevelBackLeftS(int i) throws CarNotConnectedException;

    void set5LevelSeatHeatingLevelBackRightS(int i) throws CarNotConnectedException;

    void set5LevelSeatHeatingLevelDomainS(int i) throws CarNotConnectedException;

    void set5LevelSeatHeatingLevelPassengerS(int i) throws CarNotConnectedException;

    void set5LevelSeatHeatingModeBackLeftS(int i) throws CarNotConnectedException;

    void set5LevelSeatHeatingModeBackRightS(int i) throws CarNotConnectedException;

    void set5LevelSeatHeatingModeDomainS(int i) throws CarNotConnectedException;

    void set5LevelSeatHeatingModePassengerS(int i) throws CarNotConnectedException;

    void set5LevelSeatHeatingStateBackLeftS(int i) throws CarNotConnectedException;

    void set5LevelSeatHeatingStateBackRightS(int i) throws CarNotConnectedException;

    void set5LevelSeatHeatingStateDomainS(int i) throws CarNotConnectedException;

    void set5LevelSeatHeatingStatePassengerS(int i) throws CarNotConnectedException;

    void set5LevelSeatVentilatingLevelBackLeftS(int i) throws CarNotConnectedException;

    void set5LevelSeatVentilatingLevelBackRightS(int i) throws CarNotConnectedException;

    void set5LevelSeatVentilatingLevelDomainS(int i) throws CarNotConnectedException;

    void set5LevelSeatVentilatingLevelPassengerS(int i) throws CarNotConnectedException;

    void set5LevelSeatVentilatingModeBackLeftS(int i) throws CarNotConnectedException;

    void set5LevelSeatVentilatingModeBackRightS(int i) throws CarNotConnectedException;

    void set5LevelSeatVentilatingModeDomainS(int i) throws CarNotConnectedException;

    void set5LevelSeatVentilatingModePassengerS(int i) throws CarNotConnectedException;

    void set5LevelSeatVentilatingStateBackLeftS(int i) throws CarNotConnectedException;

    void set5LevelSeatVentilatingStateBackRightS(int i) throws CarNotConnectedException;

    void set5LevelSeatVentilatingStateDomainS(int i) throws CarNotConnectedException;

    void set5LevelSeatVentilatingStatePassengerS(int i) throws CarNotConnectedException;

    void setACAutoAirS(int i) throws CarNotConnectedException;

    void setACBTWindS4E3(int i) throws CarNotConnectedException;

    void setACBTWindSX28(int i) throws CarNotConnectedException;

    void setACBTWindSX31(int i) throws CarNotConnectedException;

    void setACPauseCycleS4E3(int i) throws CarNotConnectedException;

    void setACPauseCycleS4EF(int i) throws CarNotConnectedException;

    void setACTunnelCycleS(int i) throws CarNotConnectedException;

    void setAcWarmModeS(int i) throws CarNotConnectedException;

    void setAdasRestS(int[] iArr) throws CarNotConnectedException;

    void setAfsSwitchSate(int i) throws CarNotConnectedException;

    void setAmbientMulticolorModeS(int i) throws CarNotConnectedException;

    void setAmbientMulticolorStateS(int i) throws CarNotConnectedException;

    void setAmbientMusicModeS(int i) throws CarNotConnectedException;

    void setAmbientMusicModeStateS(int i) throws CarNotConnectedException;

    void setAutoBackDefaultReset(int i) throws CarNotConnectedException;

    void setAutoExternalRearMirrorFollowUpSwitchS(int i) throws CarNotConnectedException;

    void setAutoPowerOffStateS(int i) throws CarNotConnectedException;

    void setAutoPowerOnStateS(int i) throws CarNotConnectedException;

    void setAutoPurification(int i) throws CarNotConnectedException;

    void setAutoRainWiperStateS(int i) throws CarNotConnectedException;

    void setBackDoorElectricModeS(int i) throws CarNotConnectedException;

    void setBackDoorOpenedHeightS(int i) throws CarNotConnectedException;

    void setBackHomeLightDelayValueS(int i) throws CarNotConnectedException;

    void setBackLightDefaultReset(int i) throws CarNotConnectedException;

    void setBleOpenDriverDoorStateS(int i) throws CarNotConnectedException;

    void setBrakeCloseDoorS(int i) throws CarNotConnectedException;

    void setBrakePedalModeS(int i) throws CarNotConnectedException;

    void setBsisCollisionStateS(int i) throws CarNotConnectedException;

    void setCallStateS(int i) throws CarNotConnectedException;

    void setCampingBlanceStateS(int i) throws CarNotConnectedException;

    void setCampingS(int i) throws CarNotConnectedException;

    void setCanopyAutomaticDimmingS(int i) throws CarNotConnectedException;

    void setCarDoorTipsSoundTypeS(int i) throws CarNotConnectedException;

    void setCarHealthDefaultReset(int i) throws CarNotConnectedException;

    void setCarSettingsDefaultResetS(int i) throws CarNotConnectedException;

    void setCarSettingsResetS(int i) throws CarNotConnectedException;

    void setCarsettingRestS(int[] iArr) throws CarNotConnectedException;

    void setChargingPortS4E3(int i) throws CarNotConnectedException;

    void setCheckSumS(int i) throws CarNotConnectedException;

    void setClockDefaultReset(int i) throws CarNotConnectedException;

    void setCloseDoorLevelS(int i) throws CarNotConnectedException;

    void setCloudServiceStatusS(int i) throws CarNotConnectedException;

    void setComSpeedDownStateS(int i) throws CarNotConnectedException;

    void setComSpeedUpStateS(int i) throws CarNotConnectedException;

    void setComfortableParkingStateS(int i) throws CarNotConnectedException;

    void setCpdImsSwitchStateS(int i) throws CarNotConnectedException;

    void setCuraSwitchStateS(int i) throws CarNotConnectedException;

    void setCvcModeS(int i) throws CarNotConnectedException;

    void setDampStateS(int i) throws CarNotConnectedException;

    void setDelayPowerDownStateS(int i) throws CarNotConnectedException;

    void setDelayPowerDownStateTimeS(int i) throws CarNotConnectedException;

    void setDelayPowerDownTimeS(int i) throws CarNotConnectedException;

    void setDelayPowerOffTimeS(int i) throws CarNotConnectedException;

    void setDisinfStateS(int i) throws CarNotConnectedException;

    void setDlinkRestS(int[] iArr) throws CarNotConnectedException;

    void setDoorknobAutoShrinkStateS(int i) throws CarNotConnectedException;

    void setDoorsAndWindowDefaultRessetS(int i) throws CarNotConnectedException;

    void setDriveSettingsResetS(int i) throws CarNotConnectedException;

    void setDriverSeatAutoReturnS(int i) throws CarNotConnectedException;

    void setDriverSeatStateS(int i) throws CarNotConnectedException;

    void setDyHeightByVoiceS(int i) throws CarNotConnectedException;

    void setDyHeightToggleStateS(int i) throws CarNotConnectedException;

    void setDyHeightValueS(int i) throws CarNotConnectedException;

    void setDynamicSuspensionModeS(int i) throws CarNotConnectedException;

    void setElectricTailAutoModeVoiceSwitchStateS(int i) throws CarNotConnectedException;

    void setElectricTailAutomaticModeSwitchStateS(int i) throws CarNotConnectedException;

    void setEnergyFeedbackS4E3(int i) throws CarNotConnectedException;

    void setEngineSoundTypeS(int i) throws CarNotConnectedException;

    void setEqualizerDefaultReset(int i) throws CarNotConnectedException;

    void setExhibitionStartEnginePermitS(int i) throws CarNotConnectedException;

    void setFactoryResetAll(int i) throws CarNotConnectedException;

    void setFridgeChildLockS(int i) throws CarNotConnectedException;

    void setFridgeDoorStateS(int i) throws CarNotConnectedException;

    void setFrontPassengerSeatSeatPotSwitchStateS(int i) throws CarNotConnectedException;

    void setGoalRoadModeS(int i) throws CarNotConnectedException;

    void setHSpeedAutoCloseStateS(int i) throws CarNotConnectedException;

    void setHeadlampLevelS(int i) throws CarNotConnectedException;

    void setHsMassageStateBackLeftS(int i) throws CarNotConnectedException;

    void setHsMassageStateBackRightS(int i) throws CarNotConnectedException;

    void setHsMassageStateDomainS(int i) throws CarNotConnectedException;

    void setHsMassageStatePassengerS(int i) throws CarNotConnectedException;

    void setHudBrightnessS(int i) throws CarNotConnectedException;

    void setHudDisplayContentSDrive(int i) throws CarNotConnectedException;

    void setHudDisplayContentSNavigation(int i) throws CarNotConnectedException;

    void setHudHeightS(int i) throws CarNotConnectedException;

    void setHudModeS(int i) throws CarNotConnectedException;

    void setHudNavigationStateS(int i) throws CarNotConnectedException;

    void setHudPatternS(int i) throws CarNotConnectedException;

    void setHudRotationAngleS(int i) throws CarNotConnectedException;

    void setHudSafeDriveStateS(int i) throws CarNotConnectedException;

    void setHudScreenProjectionStateS(int i) throws CarNotConnectedException;

    void setHudStateS(int i) throws CarNotConnectedException;

    void setIALAreaDifferentWays(int i) throws CarNotConnectedException;

    void setIALBrightnessS(int i) throws CarNotConnectedException;

    void setIALColorAdjustS(int i) throws CarNotConnectedException;

    void setIALColorS(int i) throws CarNotConnectedException;

    void setINSThemeS(int i) throws CarNotConnectedException;

    void setIalDefaultReset(int i) throws CarNotConnectedException;

    void setIceBoxTriggerSourceS(int i) throws CarNotConnectedException;

    void setInsideLightDoorStateS(int i) throws CarNotConnectedException;

    void setInsideLightStateS(int i) throws CarNotConnectedException;

    void setIntelLockOpenDriverDoorS(int i) throws CarNotConnectedException;

    void setIntelligentAcAreaStateR(int i) throws CarNotConnectedException;

    void setIntelligentDriveDefaultReset(int i) throws CarNotConnectedException;

    void setIntelligentLockStateS(int i) throws CarNotConnectedException;

    void setIntelligentUnlockStateS(int i) throws CarNotConnectedException;

    void setInternationalLanguageS(int i) throws CarNotConnectedException;

    void setIviProfileSettingsDmS(int i) throws CarNotConnectedException;

    void setIviProfileSettingsR(int i) throws CarNotConnectedException;

    void setKeyVibratorLevelS(int i) throws CarNotConnectedException;

    void setLaneAssistDriveDefaultReset(int i) throws CarNotConnectedException;

    void setLanguageDefaultReset(int i) throws CarNotConnectedException;

    void setLanguageS(int i) throws CarNotConnectedException;

    void setLanternLanguageModeS(int i) throws CarNotConnectedException;

    void setLeftHomeLightDelayValueS(int i) throws CarNotConnectedException;

    void setLeftRseStateS(int i) throws CarNotConnectedException;

    void setLeftRseSwitchCommandS(int i) throws CarNotConnectedException;

    void setLiftModeStateS(int i) throws CarNotConnectedException;

    void setLightDefaultReset(int i) throws CarNotConnectedException;

    void setLockCarRiseWindowS(int i) throws CarNotConnectedException;

    void setLockOffS(int i) throws CarNotConnectedException;

    void setLocksDefaultResetS(int i) throws CarNotConnectedException;

    void setLrseChildAreaStateS(int i) throws CarNotConnectedException;

    void setLrseChildModeCode1stByteS(int i) throws CarNotConnectedException;

    void setLrseChildModeCode2ndByteS(int i) throws CarNotConnectedException;

    void setLrseChildModeCode3rdByteS(int i) throws CarNotConnectedException;

    void setLrseChildModeCode4thByteS(int i) throws CarNotConnectedException;

    void setLrseChildModeOpenTimeS(int i) throws CarNotConnectedException;

    void setMassageLevel4C1(int i) throws CarNotConnectedException;

    void setMassageLevel4C2(int i) throws CarNotConnectedException;

    void setMassageLevel4C3(int i) throws CarNotConnectedException;

    void setMassageLevel4C4(int i) throws CarNotConnectedException;

    void setMassageModeS4C1(int i) throws CarNotConnectedException;

    void setMassageModeS4C2(int i) throws CarNotConnectedException;

    void setMassageModeS4C3(int i) throws CarNotConnectedException;

    void setMassageModeS4C4(int i) throws CarNotConnectedException;

    void setMeditationModeS(int i) throws CarNotConnectedException;

    void setMessageCounterS(int i) throws CarNotConnectedException;

    void setMicroSwitchLockWindowStateS(int i) throws CarNotConnectedException;

    void setMicroSwitchUnlockWindowStateS(int i) throws CarNotConnectedException;

    void setModeS(int i) throws CarNotConnectedException;

    void setMoisAssembleStateS(int i) throws CarNotConnectedException;

    void setMotorPretensionerSeatbeltStateS(int i) throws CarNotConnectedException;

    void setNetworkSecurityStatusS(int i) throws CarNotConnectedException;

    void setNewEnergyDefaultResetS(int i) throws CarNotConnectedException;

    void setNfcOpenDriverDoorS(int i) throws CarNotConnectedException;

    void setNotifyTunnelLengthS(int i) throws CarNotConnectedException;

    void setOpeningAngleInformationSetOnTheRS(int i) throws CarNotConnectedException;

    void setOptimizationStatusS(int i) throws CarNotConnectedException;

    void setOtaModeS(int i) throws CarNotConnectedException;

    void setOutChargeDefaultReset(int i) throws CarNotConnectedException;

    void setOutsideLightStateSceneS(int i) throws CarNotConnectedException;

    void setOverspeedLockS(int i) throws CarNotConnectedException;

    void setPM25PowerS(int i) throws CarNotConnectedException;

    void setPM25SwitchCheckS(int i) throws CarNotConnectedException;

    void setPM25TimeCheckS(int i) throws CarNotConnectedException;

    void setPabStateS(int i) throws CarNotConnectedException;

    void setPedalSuppressionSystemStateS(int i) throws CarNotConnectedException;

    void setPetModeS(int i) throws CarNotConnectedException;

    void setRainAutoLockWindowStateS(int i) throws CarNotConnectedException;

    void setRearAcPanelAutoLockTimeS(int i) throws CarNotConnectedException;

    void setRearFridgeDelayStateS(int i) throws CarNotConnectedException;

    void setRearFridgeDelayTimeS(int i) throws CarNotConnectedException;

    void setRearFridgeDoorStateS(int i) throws CarNotConnectedException;

    void setRearFridgeSterilizerStateS(int i) throws CarNotConnectedException;

    void setRearFridgeTemperatureS(int i) throws CarNotConnectedException;

    void setRearFridgeWorkStateS(int i) throws CarNotConnectedException;

    void setRearMirrorDefaultResetS(int i) throws CarNotConnectedException;

    void setRearRightSeatGreetStateS(int i) throws CarNotConnectedException;

    void setRearSeatGreetStateS(int i) throws CarNotConnectedException;

    void setRearSeatsFoldOrExpand(int i) throws CarNotConnectedException;

    void setRearViewMirrorFlipS(int i) throws CarNotConnectedException;

    void setRearViewMirrorFlipSetS(int i) throws CarNotConnectedException;

    void setRearWorkTemperatureS(int i) throws CarNotConnectedException;

    void setRearviewMirrorIsAutoLightStateS(int i) throws CarNotConnectedException;

    void setRearviewMirrorIsScreenStateS(int i) throws CarNotConnectedException;

    void setRearviewMirrorIsTurnedStateS(int i) throws CarNotConnectedException;

    void setRefrigWorkModeS(int i) throws CarNotConnectedException;

    void setRefrigeratorModeS(int i) throws CarNotConnectedException;

    void setRefrigeratorWorkModeS(int i) throws CarNotConnectedException;

    void setRefrigeratorWorkTemperatureS(int i) throws CarNotConnectedException;

    void setRemberPowerOffStateS(int i) throws CarNotConnectedException;

    void setRemenberPowerOffStateS(int i) throws CarNotConnectedException;

    void setRemoteControlDownwindowStateS(int i) throws CarNotConnectedException;

    void setRemoteControlUpwindowStateS(int i) throws CarNotConnectedException;

    void setRemoteKeyStartAcTimeS(int i) throws CarNotConnectedException;

    void setRestModeS(int i) throws CarNotConnectedException;

    void setRightRseStateS(int i) throws CarNotConnectedException;

    void setRightRseSwitchCommandS(int i) throws CarNotConnectedException;

    void setRncStateS(int i) throws CarNotConnectedException;

    void setRrseChildAreaStateS(int i) throws CarNotConnectedException;

    void setRrseChildModeCode1stByteS(int i) throws CarNotConnectedException;

    void setRrseChildModeCode2ndByteS(int i) throws CarNotConnectedException;

    void setRrseChildModeCode3rdByteS(int i) throws CarNotConnectedException;

    void setRrseChildModeCode4thByteS(int i) throws CarNotConnectedException;

    void setRrseChildModeOpenTimeS(int i) throws CarNotConnectedException;

    void setRuningCloseDoorStateS(int i) throws CarNotConnectedException;

    void setSOCTargetS(int i) throws CarNotConnectedException;

    void setSafeAssistDefaultReset(int i) throws CarNotConnectedException;

    void setSafeBeltCloseDriveDoorStateS(int i) throws CarNotConnectedException;

    void setSafeBeltWarnStateS(int i) throws CarNotConnectedException;

    void setSandAbsModeStateS(int i) throws CarNotConnectedException;

    void setScreenAngleValueS(int i) throws CarNotConnectedException;

    void setScreenRotationLockS(int i) throws CarNotConnectedException;

    void setScreenRotationS(int i) throws CarNotConnectedException;

    void setSeatHeatingStateBackLeftDomainControlS(int i) throws CarNotConnectedException;

    void setSeatHeatingStateBackRightDomainControlS(int i) throws CarNotConnectedException;

    void setSeatHeatingStateCopilotDomainControl(int i) throws CarNotConnectedException;

    void setSeatHeatingStateDriverDomainControlS(int i) throws CarNotConnectedException;

    void setSeatVentilatingStateBackLeftDomainControlS(int i) throws CarNotConnectedException;

    void setSeatVentilatingStateBackRightDomainControlS(int i) throws CarNotConnectedException;

    void setSeatVentilatingStateCopilotDomainControl(int i) throws CarNotConnectedException;

    void setSeatVentilatingStateDriverDomainControl(int i) throws CarNotConnectedException;

    void setSetUiVersion(int i) throws CarNotConnectedException;

    void setSkyCurtainPowerOffAndDimS(int i) throws CarNotConnectedException;

    void setSmartWelcomeLightStateS(int i) throws CarNotConnectedException;

    void setSoundFiledDefaultReset(int i) throws CarNotConnectedException;

    void setSpeedReminderValueS(int i) throws CarNotConnectedException;

    void setSteerAssisS(int i) throws CarNotConnectedException;

    void setSteerPositionAutoReturnS(int i) throws CarNotConnectedException;

    void setSteeringWheelHeatingAutoStateS(int i) throws CarNotConnectedException;

    void setSteeringWheelHeatingGearS(int i) throws CarNotConnectedException;

    void setSteeringWheelHeatingStateS(int i) throws CarNotConnectedException;

    void setSterilizationStateS(int i) throws CarNotConnectedException;

    void setSysFactoryReset(int i) throws CarNotConnectedException;

    void setTailBoxTakeStateS(int i) throws CarNotConnectedException;

    void setThirdRowSeatLeftBackrestS(int i) throws CarNotConnectedException;

    void setThirdRowSeatRightBackrestS(int i) throws CarNotConnectedException;

    void setTrailerDragTypeS(int i) throws CarNotConnectedException;

    void setTrailerLightCheckStateS(int i) throws CarNotConnectedException;

    void setTrailerModeStateS(int i) throws CarNotConnectedException;

    void setTrayStateS(int i) throws CarNotConnectedException;

    void setTurnSeatbackrestPercentDriver(int i) throws CarNotConnectedException;

    void setTurnSeatbackrestPercentPassenger(int i) throws CarNotConnectedException;

    void setUnitDefaultReset(int i) throws CarNotConnectedException;

    void setVoiceCtlBackDoor(int i) throws CarNotConnectedException;

    void setVspStateS(int i) throws CarNotConnectedException;

    void setWelcomeStateS(int i) throws CarNotConnectedException;

    void setWipeModeS(int i) throws CarNotConnectedException;

    void setWorkCommondS(int i) throws CarNotConnectedException;

    void setWorkStateS(int i) throws CarNotConnectedException;

    void setWorkTemperatureS(int i) throws CarNotConnectedException;

    void seteOwnerStateS(int i) throws CarNotConnectedException;

    void setflSeatBasinSwitchStateS(int i) throws CarNotConnectedException;

    void setnotifyEnterTunnelS(int i) throws CarNotConnectedException;

    void setresetHudState(int i) throws CarNotConnectedException;

    void setresetRearviewMirrorIsSetS(int i) throws CarNotConnectedException;

    void setresetSeatParamsBackLeft(int i) throws CarNotConnectedException;

    void setresetSeatParamsBackRight(int i) throws CarNotConnectedException;

    void setresetSeatParamsCopilot(int i) throws CarNotConnectedException;

    void setresetSeatParamsDriver(int i) throws CarNotConnectedException;

    void setsaveSeatParamsAllBackLeft(int i) throws CarNotConnectedException;

    void setsaveSeatParamsAllBackRight(int i) throws CarNotConnectedException;

    void setsaveSeatParamsAllCopilot(int i) throws CarNotConnectedException;

    void setsaveSeatParamsAllDriver(int i) throws CarNotConnectedException;

    void setsendOffReminder(int i) throws CarNotConnectedException;

    void setturnCaliperSplintPercentDriver(int i) throws CarNotConnectedException;

    void setturnCaliperSplintPercentPassenger(int i) throws CarNotConnectedException;

    void setturnCushionPercentDriver(int i) throws CarNotConnectedException;

    void setturnHeadrestHeightPercentDriver(int i) throws CarNotConnectedException;

    void setturnHeadrestHorizationPercentDriver(int i) throws CarNotConnectedException;

    void setturnHeadrestTurnHeightBackLeft(int i) throws CarNotConnectedException;

    void setturnHeadrestTurnHeightBackRight(int i) throws CarNotConnectedException;

    void setturnHeadrestTurnHeightCopilot(int i) throws CarNotConnectedException;

    void setturnHeadrestTurnHeightDriver(int i) throws CarNotConnectedException;

    void setturnRearviewMirror(int i) throws CarNotConnectedException;

    void setturnSeatBackRearLeftLeftRcS(int i) throws CarNotConnectedException;

    void setturnSeatBackRearLeftRcS(int i) throws CarNotConnectedException;

    void setturnSeatBackRearLeftRightRcS(int i) throws CarNotConnectedException;

    void setturnSeatBackRearRightLeftRcS(int i) throws CarNotConnectedException;

    void setturnSeatBackRearRightRcS(int i) throws CarNotConnectedException;

    void setturnSeatBackRearRightRightRcS(int i) throws CarNotConnectedException;

    void setturnSeatCaliperSplintBackLeft(int i) throws CarNotConnectedException;

    void setturnSeatCaliperSplintBackRight(int i) throws CarNotConnectedException;

    void setturnSeatCaliperSplintCopilot(int i) throws CarNotConnectedException;

    void setturnSeatCaliperSplintDriver(int i) throws CarNotConnectedException;

    void setturnSeatCaliperSplintRearLeftLeftRcS(int i) throws CarNotConnectedException;

    void setturnSeatCaliperSplintRearLeftRcS(int i) throws CarNotConnectedException;

    void setturnSeatCaliperSplintRearLeftRightRcS(int i) throws CarNotConnectedException;

    void setturnSeatCaliperSplintRearRightLeftRcS(int i) throws CarNotConnectedException;

    void setturnSeatCaliperSplintRearRightRcS(int i) throws CarNotConnectedException;

    void setturnSeatCaliperSplintRearRightRightRcS(int i) throws CarNotConnectedException;

    void setturnSeatCushionBackLeft(int i) throws CarNotConnectedException;

    void setturnSeatCushionBackRight(int i) throws CarNotConnectedException;

    void setturnSeatCushionCopilot(int i) throws CarNotConnectedException;

    void setturnSeatCushionDriver(int i) throws CarNotConnectedException;

    void setturnSeatCushionRearLeftLeftRcS(int i) throws CarNotConnectedException;

    void setturnSeatCushionRearLeftRcS(int i) throws CarNotConnectedException;

    void setturnSeatCushionRearLeftRightRcS(int i) throws CarNotConnectedException;

    void setturnSeatCushionRearRightLeftRcS(int i) throws CarNotConnectedException;

    void setturnSeatCushionRearRightRcS(int i) throws CarNotConnectedException;

    void setturnSeatCushionRearRightRightRcS(int i) throws CarNotConnectedException;

    void setturnSeatHeightBackLeft(int i) throws CarNotConnectedException;

    void setturnSeatHeightBackRight(int i) throws CarNotConnectedException;

    void setturnSeatHeightCopilot(int i) throws CarNotConnectedException;

    void setturnSeatHeightDriver(int i) throws CarNotConnectedException;

    void setturnSeatHeightPercentCopilot(int i) throws CarNotConnectedException;

    void setturnSeatHeightPercentDriver(int i) throws CarNotConnectedException;

    void setturnSeatHorizationBackLeft(int i) throws CarNotConnectedException;

    void setturnSeatHorizationBackRight(int i) throws CarNotConnectedException;

    void setturnSeatHorizationCopilot(int i) throws CarNotConnectedException;

    void setturnSeatHorizationDriver(int i) throws CarNotConnectedException;

    void setturnSeatHorizationPercentDriver(int i) throws CarNotConnectedException;

    void setturnSeatHorizationPercentPassenger(int i) throws CarNotConnectedException;

    void setturnSeatHorizationRearLeftLeftRcS(int i) throws CarNotConnectedException;

    void setturnSeatHorizationRearLeftRcS(int i) throws CarNotConnectedException;

    void setturnSeatHorizationRearLeftRightRcS(int i) throws CarNotConnectedException;

    void setturnSeatHorizationRearRightLeftRcS(int i) throws CarNotConnectedException;

    void setturnSeatHorizationRearRightRcS(int i) throws CarNotConnectedException;

    void setturnSeatHorizationRearRightRightRcS(int i) throws CarNotConnectedException;

    void setturnSeatbackrestBackLeft(int i) throws CarNotConnectedException;

    void setturnSeatbackrestBackRight(int i) throws CarNotConnectedException;

    void setturnSeatbackrestDriver(int i) throws CarNotConnectedException;

    void setturnSteeringWheel(int i) throws CarNotConnectedException;

    void setvoiceOpenMirrorlsTurnedStatS(int i) throws CarNotConnectedException;

    int testBlankPlace() throws CarNotConnectedException;

    void turnRearviewMirrorIS(int i) throws CarNotConnectedException;
}

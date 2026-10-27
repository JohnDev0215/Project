package com.ts.lib.car.hardware.carbody;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes3.dex */
public interface CarVendorCarbodyManager {
    public static final int ID_ALL_DOORS_LOCK = 557856519;
    public static final int ID_ALL_DOORS_LOCK_SCENE_S = 557863028;
    public static final int ID_ANTI_THEFT_STATUS_R = 557862994;
    public static final int ID_AUTO_EXTERNAL_REAR_MIRROR_FOLDED_STATER = 557858049;
    public static final int ID_AUTO_EXTERNAL_REAR_MIRROR_FOLD_ACTION_STATER = 557858048;
    public static final int ID_AUTO_EXTERNAL_REAR_MIRROR_FOLD_STATES = 557858050;
    public static final int ID_AUTO_SYSTEM_STATE = 557862931;
    public static final int ID_AUTO_TYPE = 557862930;
    public static final int ID_AUTO_TYPE_NEW = 557863031;
    public static final int ID_AWNING_CONFIGUTATION_R = 557863058;
    public static final int ID_BACK_DOOR_ANTI_PLAY_MODER = 557862981;
    public static final int ID_BACK_DOOR_CUR_STATER = 557862978;
    public static final int ID_BACK_DOOR_HEIGHT_POSITIONR = 557862977;
    public static final int ID_BACK_DOOR_HEIGHT_POSITION_MYCAR_S = 557863018;
    public static final int ID_BACK_DOOR_INIT_STATER = 557862975;
    public static final int ID_BACK_DOOR_OPENED_HEIGHT_S = 557857562;
    public static final int ID_BACK_DOOR_POSITIONR = 557862980;
    public static final int ID_BATTERY_VOLTAGE_LEVEL = 557862926;
    public static final int ID_BATTERY_VOLTAGE_LEVEL_LOW_TIPS = 557875227;
    public static final int ID_B_C_M_VECHILE_STATUS_R = 557863017;
    public static final int ID_CAN_055_STATE = 557945600;
    public static final int ID_CAN_0D5_STATE = 557945603;
    public static final int ID_CAN_10D_STATE = 557945605;
    public static final int ID_CAN_121_STATE = 557945606;
    public static final int ID_CAN_123_STATE = 557945607;
    public static final int ID_CAN_12D_STATE = 557945608;
    public static final int ID_CAN_12F_STATE = 557945609;
    public static final int ID_CAN_133_STATE = 557945610;
    public static final int ID_CAN_138_STATE = 557945611;
    public static final int ID_CAN_13F_STATE = 557945612;
    public static final int ID_CAN_144_STATE = 557945613;
    public static final int ID_CAN_145_STATE = 557945775;
    public static final int ID_CAN_151_STATE = 557945614;
    public static final int ID_CAN_167_STATE = 557945615;
    public static final int ID_CAN_18A_STATE = 557945616;
    public static final int ID_CAN_19F_STATE = 557945617;
    public static final int ID_CAN_1C0_STATE = 557945618;
    public static final int ID_CAN_1D1_STATE = 557945619;
    public static final int ID_CAN_1EA_STATE = 557945777;
    public static final int ID_CAN_218_STATE = 557945620;
    public static final int ID_CAN_223_STATE = 557945621;
    public static final int ID_CAN_233_STATE = 557945622;
    public static final int ID_CAN_235_STATE = 557945623;
    public static final int ID_CAN_23A_STATE = 557945624;
    public static final int ID_CAN_242_STATE = 557945625;
    public static final int ID_CAN_245_STATE = 557945626;
    public static final int ID_CAN_248_STATE = 557945627;
    public static final int ID_CAN_24A_STATE = 557945628;
    public static final int ID_CAN_24C_STATE = 557945629;
    public static final int ID_CAN_257_STATE = 557945769;
    public static final int ID_CAN_258_STATE = 557945765;
    public static final int ID_CAN_25D_STATE = 557945630;
    public static final int ID_CAN_261_STATE = 557945631;
    public static final int ID_CAN_26F_STATE = 557945632;
    public static final int ID_CAN_271_STATE = 557945633;
    public static final int ID_CAN_272_STATE = 557945634;
    public static final int ID_CAN_275_STATE = 557945635;
    public static final int ID_CAN_27A_STATE = 557945636;
    public static final int ID_CAN_287_STATE = 557945637;
    public static final int ID_CAN_288_STATE = 557945638;
    public static final int ID_CAN_28F_STATE = 557945639;
    public static final int ID_CAN_294_STATE = 557945640;
    public static final int ID_CAN_296_STATE = 557945641;
    public static final int ID_CAN_29C_STATE = 557945642;
    public static final int ID_CAN_29E_STATE = 557945643;
    public static final int ID_CAN_2A4_STATE = 557945644;
    public static final int ID_CAN_2A6_STATE = 557945645;
    public static final int ID_CAN_2A7_STATE = 557945646;
    public static final int ID_CAN_2B0_STATE = 557945647;
    public static final int ID_CAN_2B4_STATE = 557945648;
    public static final int ID_CAN_2B6_STATE = 557945649;
    public static final int ID_CAN_2BA_STATE = 557945650;
    public static final int ID_CAN_2BC_STATE = 557945651;
    public static final int ID_CAN_2C0_STATE = 557945652;
    public static final int ID_CAN_2D5_STATE = 557945653;
    public static final int ID_CAN_2EB_STATE = 557945654;
    public static final int ID_CAN_2F0_STATE = 557945655;
    public static final int ID_CAN_30D_STATE = 557945656;
    public static final int ID_CAN_319_STATE = 557945764;
    public static final int ID_CAN_31E_STATE = 557945657;
    public static final int ID_CAN_321_STATE = 557945658;
    public static final int ID_CAN_323_STATE = 557945659;
    public static final int ID_CAN_32D_STATE = 557945660;
    public static final int ID_CAN_334_STATE = 557945661;
    public static final int ID_CAN_337_STATE = 557945662;
    public static final int ID_CAN_338_STATE = 557945774;
    public static final int ID_CAN_341_STATE = 557945663;
    public static final int ID_CAN_342_STATE = 557945664;
    public static final int ID_CAN_344_STATE = 557945763;
    public static final int ID_CAN_346_STATE = 557945665;
    public static final int ID_CAN_347_STATE = 557945666;
    public static final int ID_CAN_34A_STATE = 557945667;
    public static final int ID_CAN_34F_STATE = 557945668;
    public static final int ID_CAN_351_STATE = 557945766;
    public static final int ID_CAN_359_STATE = 557945778;
    public static final int ID_CAN_36B_STATE = 557945669;
    public static final int ID_CAN_36D_STATE = 557945670;
    public static final int ID_CAN_36E_STATE = 557945671;
    public static final int ID_CAN_37F_STATE = 557945672;
    public static final int ID_CAN_38A_STATE = 557945673;
    public static final int ID_CAN_38B_STATE = 557945674;
    public static final int ID_CAN_38F_STATE = 557945772;
    public static final int ID_CAN_394_STATE = 557945675;
    public static final int ID_CAN_396_STATE = 557945676;
    public static final int ID_CAN_3A0_STATE = 557945677;
    public static final int ID_CAN_3A7_STATE = 557945678;
    public static final int ID_CAN_3AC_STATE = 557945679;
    public static final int ID_CAN_3AD_STATE = 557945680;
    public static final int ID_CAN_3B0_STATE = 557945681;
    public static final int ID_CAN_3B3_STATE = 557945682;
    public static final int ID_CAN_3B4_STATE = 557945683;
    public static final int ID_CAN_3BB_STATE = 557945684;
    public static final int ID_CAN_3BD_STATE = 557945685;
    public static final int ID_CAN_3C1_STATE = 557945686;
    public static final int ID_CAN_3C8_STATE = 557945687;
    public static final int ID_CAN_3CA_STATE = 557945688;
    public static final int ID_CAN_3CD_STATE = 557945689;
    public static final int ID_CAN_3D6_STATE = 557945771;
    public static final int ID_CAN_3D9_STATE = 557945690;
    public static final int ID_CAN_3DA_STATE = 557945691;
    public static final int ID_CAN_3F1_STATE = 557945692;
    public static final int ID_CAN_3F3_STATE = 557945693;
    public static final int ID_CAN_3FE_STATE = 557945694;
    public static final int ID_CAN_3FF_STATE = 557945695;
    public static final int ID_CAN_401_STATE = 557945696;
    public static final int ID_CAN_402_STATE = 557945697;
    public static final int ID_CAN_404_STATE = 557945698;
    public static final int ID_CAN_407_STATE = 557945699;
    public static final int ID_CAN_40D_STATE = 557945700;
    public static final int ID_CAN_417_STATE = 557945701;
    public static final int ID_CAN_418_STATE = 557945702;
    public static final int ID_CAN_41A_STATE = 557945703;
    public static final int ID_CAN_41C_STATE = 557945704;
    public static final int ID_CAN_41E_STATE = 557945705;
    public static final int ID_CAN_41F_STATE = 557945706;
    public static final int ID_CAN_429_STATE = 557945707;
    public static final int ID_CAN_42E_STATE = 557945708;
    public static final int ID_CAN_42F_STATE = 557945709;
    public static final int ID_CAN_432_STATE = 557945710;
    public static final int ID_CAN_433_STATE = 557945711;
    public static final int ID_CAN_434_STATE = 557945712;
    public static final int ID_CAN_436_STATE = 557945713;
    public static final int ID_CAN_437_STATE = 557945714;
    public static final int ID_CAN_438_STATE = 557945715;
    public static final int ID_CAN_43E_STATE = 557945716;
    public static final int ID_CAN_43F_STATE = 557945717;
    public static final int ID_CAN_443_STATE = 557945718;
    public static final int ID_CAN_444_STATE = 557945719;
    public static final int ID_CAN_447_STATE = 557945720;
    public static final int ID_CAN_449_STATE = 557945721;
    public static final int ID_CAN_453_STATE = 557945722;
    public static final int ID_CAN_454_STATE = 557945723;
    public static final int ID_CAN_461_STATE = 557945724;
    public static final int ID_CAN_464_STATE = 557945773;
    public static final int ID_CAN_46C_STATE = 557945725;
    public static final int ID_CAN_470_STATE = 557945726;
    public static final int ID_CAN_475_STATE = 557945727;
    public static final int ID_CAN_480_STATE = 557945728;
    public static final int ID_CAN_481_STATE = 557945729;
    public static final int ID_CAN_486_STATE = 557945730;
    public static final int ID_CAN_487_STATE = 557945731;
    public static final int ID_CAN_488_STATE = 557945732;
    public static final int ID_CAN_489_STATE = 557945733;
    public static final int ID_CAN_48A_STATE = 557945734;
    public static final int ID_CAN_48C_STATE = 557945770;
    public static final int ID_CAN_48E_STATE = 557945735;
    public static final int ID_CAN_48F_STATE = 557945736;
    public static final int ID_CAN_494_STATE = 557945737;
    public static final int ID_CAN_49C_STATE = 557945738;
    public static final int ID_CAN_4A1_STATE = 557945739;
    public static final int ID_CAN_4A4_STATE = 557945740;
    public static final int ID_CAN_4A5_STATE = 557945741;
    public static final int ID_CAN_4A7_STATE = 557945742;
    public static final int ID_CAN_4B1_STATE = 557945743;
    public static final int ID_CAN_4B2_STATE = 557945744;
    public static final int ID_CAN_4B3_STATE = 557945745;
    public static final int ID_CAN_4B6_STATE = 557945746;
    public static final int ID_CAN_4BB_STATE = 557945747;
    public static final int ID_CAN_4BD_STATE = 557945748;
    public static final int ID_CAN_4BF_STATE = 557945749;
    public static final int ID_CAN_4C2_STATE = 557945750;
    public static final int ID_CAN_4C8_STATE = 557945751;
    public static final int ID_CAN_4D9_STATE = 557945752;
    public static final int ID_CAN_4E5_STATE = 557945753;
    public static final int ID_CAN_4E6_STATE = 557945754;
    public static final int ID_CAN_4E8_STATE = 557945767;
    public static final int ID_CAN_4E9_STATE = 557945768;
    public static final int ID_CAN_4F2_STATE = 557945755;
    public static final int ID_CAN_4F4_STATE = 557945756;
    public static final int ID_CAN_4F6_STATE = 557945757;
    public static final int ID_CAN_4F7_STATE = 557945758;
    public static final int ID_CAN_4FA_STATE = 557945759;
    public static final int ID_CAN_4FB_STATE = 557945760;
    public static final int ID_CAN_4FD_STATE = 557945761;
    public static final int ID_CAN_4FE_STATE = 557945762;
    public static final int ID_CAN_MSG_STATE = 557969457;
    public static final int ID_DOOR_LOCK_AREA_LEFT_REAR = 557863023;
    public static final int ID_DOOR_LOCK_AREA_RIGHT_FRONT = 557863022;
    public static final int ID_DOOR_LOCK_AREA_RIGHT_REAR = 557863024;
    public static final int ID_DOOR_STATE_HOOD = 557862948;
    public static final int ID_DOOR_STATE_LEFT_FRONT = 557862943;
    public static final int ID_DOOR_STATE_LEFT_REAR = 557862945;
    public static final int ID_DOOR_STATE_LUGGAGE = 557862947;
    public static final int ID_DOOR_STATE_RIGHT_FRONT = 557862944;
    public static final int ID_DOOR_STATE_RIGHT_REAR = 557862946;
    public static final int ID_ELECTRIC_PEDAL_CONFIG_R = 557863046;
    public static final int ID_ELEC_BACK_DOOR_CONTROL_STATER = 557862976;
    public static final int ID_ELEC_SLIDE_DOOR_STATER = 557862982;
    public static final int ID_ELEC_SLIDE_DOOR_STATE_S = 557863030;
    public static final int ID_ENERGY_TYPER = 557862951;
    public static final int ID_EV_READY_INDICATOR_LIGHT_STATE_R = 557863057;
    public static final int ID_FOUR_PEOPLE_ENJOY_CONFIG2_R = 557863072;
    public static final int ID_FOUR_PEOPLE_ENJOY_CONFIG_R = 557863039;
    public static final int ID_FSE_LAGER_BED_MODE_CONFIG_R = 557863075;
    public static final int ID_FSE_LARGE_BED_MODE_S = 557863083;
    public static final int ID_FSE_SEAT_SYSTEM_WORK_MODE_R = 557863071;
    public static final int ID_FUEL_ELEC_LOW_POWER_R = 557845265;
    public static final int ID_INTERNAL_DOOR_HANDLE_STATE_BY_REAR_B_C_M_L_F_R = 557863061;
    public static final int ID_INTERNAL_DOOR_HANDLE_STATE_BY_REAR_B_C_M_L_R_R = 557863062;
    public static final int ID_INTERNAL_DOOR_HANDLE_STATE_BY_REAR_B_C_M_R_F_R = 557863063;
    public static final int ID_INTERNAL_DOOR_HANDLE_STATE_BY_REAR_B_C_M_R_R_R = 557863064;
    public static final int ID_INTERNAL_DOOR_HANDLE_STATE_LEFT_BACK_R = 557863044;
    public static final int ID_INTERNAL_DOOR_HANDLE_STATE_LEFT_FRONT_R = 557863042;
    public static final int ID_INTERNAL_DOOR_HANDLE_STATE_RIGHT_BACK_R = 557863045;
    public static final int ID_INTERNAL_DOOR_HANDLE_STATE_RIGHT_FRONT_R = 557863043;
    public static final int ID_LARGE_BED_MODE_S = 557863082;
    public static final int ID_LEFT_MID_DOOR_VOICE_WAKE_UP_CHANGED_R = 557863086;
    public static final int ID_LEFT_SEAT_RAILS_ROW3_FEEDBACK_POSITION_R = 557863068;
    public static final int ID_LEFT_SEAT_ROW3_NON_ELECTRICALLY_ADJUST_STATE_R = 557863070;
    public static final int ID_LEFT_SLIDE_DOOR_ACTIONR = 557862985;
    public static final int ID_LEFT_SLIDE_DOOR_PERCENTR = 557862987;
    public static final int ID_LEFT_SLIDE_DOOR_STATER = 557862983;
    public static final int ID_LEFT_SLIDE_DOOR_VOICE_CONTROL_STATES = 557862989;
    public static final int ID_LEFT_STRUT_MOROT_RUNNING_STATE = 557862953;
    public static final int ID_LIGHT_HIGH_BEAMR = 557860638;
    public static final int ID_LIGHT_LOW_BEAM_STATUSR = 557860637;
    public static final int ID_LIGHT_REAR_FOGR = 557860642;
    public static final int ID_LIGHT_REAR_POSITIONR = 557860644;
    public static final int ID_LIGHT_SIDE_STATUSR = 557879809;
    public static final int ID_MODE_COMMANDB_S = 557928602;
    public static final int ID_MODE_COMMANDC_S = 557928621;
    public static final int ID_MODE_COMMAND_S = 557928601;
    public static final int ID_MOON_ROOF_CONFIG = 557862942;
    public static final int ID_MOON_ROOF_CONFIG_R = 557863032;
    public static final int ID_MOON_ROOF_PERCENT_CONTROL_FLAG_R = 557863029;
    public static final int ID_ON_SAVE_SEAT_PARAMS_ALL_CHANGED_PLACE5 = 557857805;
    public static final int ID_ON_SAVE_SEAT_PARAMS_ALL_CHANGED_PLACE7 = 557857806;
    public static final int ID_POWER_LEVEL = 557862927;
    public static final int ID_READY_INDICATOR_LIGHT_STATER = 557863013;
    public static final int ID_REAL_AUTO_VIN_EIGHTR = 557863002;
    public static final int ID_REAL_AUTO_VIN_ELEVENR = 557863005;
    public static final int ID_REAL_AUTO_VIN_FIFTEENR = 557863009;
    public static final int ID_REAL_AUTO_VIN_FIVER = 557862999;
    public static final int ID_REAL_AUTO_VIN_FOURR = 557862998;
    public static final int ID_REAL_AUTO_VIN_FOURTEENR = 557863008;
    public static final int ID_REAL_AUTO_VIN_NINER = 557863003;
    public static final int ID_REAL_AUTO_VIN_ONER = 557862995;
    public static final int ID_REAL_AUTO_VIN_SEVENR = 557863001;
    public static final int ID_REAL_AUTO_VIN_SEVENTEENR = 557863011;
    public static final int ID_REAL_AUTO_VIN_SIXR = 557863000;
    public static final int ID_REAL_AUTO_VIN_SIXTEENR = 557863010;
    public static final int ID_REAL_AUTO_VIN_TENR = 557863004;
    public static final int ID_REAL_AUTO_VIN_THIRTEENR = 557863007;
    public static final int ID_REAL_AUTO_VIN_THREER = 557862997;
    public static final int ID_REAL_AUTO_VIN_TWELVER = 557863006;
    public static final int ID_REAL_AUTO_VIN_TWOR = 557862996;
    public static final int ID_REARVIEW_MIRROR_SUPPORT = 557856147;
    public static final int ID_REARVIEW_MIRROR_X_LEFT = 559954814;
    public static final int ID_REARVIEW_MIRROR_X_RIGHT = 559954815;
    public static final int ID_REARVIEW_MIRROR_Y_LEFT = 559954812;
    public static final int ID_REARVIEW_MIRROR_Y_RIGHT = 559954813;
    public static final int ID_REAR_WINDOWBLIND_INIT_STATER = 557862973;
    public static final int ID_REAR_WINDOWBLIND_INIT_STATE_NEW_R = 557863019;
    public static final int ID_REAR_WINDOWBLIND_POSITIONR = 557862974;
    public static final int ID_REAR_WINDOWBLIND_STATER = 557862972;
    public static final int ID_RECOVERORSAVEPARAMSCHANGED5 = 557857850;
    public static final int ID_RESTORE_SEAT_CONFIG_R = 557863073;
    public static final int ID_RIGHT_MID_DOOR_VOICE_WAKE_UP_CHANGED_R = 557863087;
    public static final int ID_RIGHT_SEAT_RAILS_ROW3_FEEDBACK_POSITION_R = 557863069;
    public static final int ID_RIGHT_SLIDE_DOOR_ACTIONR = 557862986;
    public static final int ID_RIGHT_SLIDE_DOOR_PERCENTR = 557862988;
    public static final int ID_RIGHT_SLIDE_DOOR_STATER = 557862984;
    public static final int ID_RIGHT_SLIDE_DOOR_VOICE_CONTROL_STATES = 557862990;
    public static final int ID_RIGHT_STRUT_MOROT_RUNNING_STATER = 557862979;
    public static final int ID_SCREEN_CONTROL_SLIDE_DOOR_LEFT_LEFT_R = 557863049;
    public static final int ID_SCREEN_CONTROL_SLIDE_DOOR_L_LEFT_RIGHT_R = 557863051;
    public static final int ID_SCREEN_CONTROL_SLIDE_DOOR_RIGHT_LEFT_R = 557863050;
    public static final int ID_SCREEN_CONTROL_SLIDE_DOOR_RIGHT_RIGHT_R = 557863052;
    public static final int ID_SCREEN_CONTROL_SLIDE_DOOR_SLIDE_LEFT_LEFT_R = 557863053;
    public static final int ID_SCREEN_CONTROL_SLIDE_DOOR_SLIDE_LEFT_RIGHT_R = 557863055;
    public static final int ID_SCREEN_CONTROL_SLIDE_DOOR_SLIDE_RIGHT_LEFT_R = 557863054;
    public static final int ID_SCREEN_CONTROL_SLIDE_DOOR_SLIDE_RIGHT_RIGHT_R = 557863056;
    public static final int ID_SEAT_LARGE_BED_MODE_CONFIG_R = 557863074;
    public static final int ID_SEAT_LUMBAR_SUPPORT_CONFIG_CO_PILOT_LEFT_R = 557863035;
    public static final int ID_SEAT_LUMBAR_SUPPORT_CONFIG_CO_PILOT_RIGHT_R = 557863036;
    public static final int ID_SEAT_LUMBAR_SUPPORT_CONFIG_MAIN_LEFT_R = 557863034;
    public static final int ID_SEAT_LUMBAR_SUPPORT_CONFIG_MAIN_RIGHT_R = 557863033;
    public static final int ID_SEAT_MODE_CONTROL_S = 557863040;
    public static final int ID_SEAT_MODE_CUR_FEEDBACK_STATUS2_R = 557863076;
    public static final int ID_SEAT_MODE_CUR_FEEDBACK_STATUS_R = 557863079;
    public static final int ID_SEAT_MODE_FAIL_FEEDBACK_STATUS2_R = 557863078;
    public static final int ID_SEAT_MODE_FAIL_FEEDBACK_STATUS_R = 557863081;
    public static final int ID_SEAT_MODE_SUCCESS_FEEDBACK_STATUS2_R = 557863077;
    public static final int ID_SEAT_MODE_SUCCESS_FEEDBACK_STATUS_R = 557863080;
    public static final int ID_SEAT_MODE_WAKEUP_FEEDBACK_STATE_R = 557863041;
    public static final int ID_SEAT_ROW3_ONEKEY_ENTER_PIT_S = 557863084;
    public static final int ID_SETTING_LUGGAGES = 557863012;
    public static final int ID_SHIFT_MODE_R = 557868039;
    public static final int ID_SIDE_WINDOW_SHADE_STATE_S = 557863059;
    public static final int ID_SMART_VOICE_LIMIT_R = 557863016;
    public static final int ID_STEERING_WHEEL_VALUE_ANGEL = 557862928;
    public static final int ID_STEERING_WHEEL_VALUE_SPEED = 557862929;
    public static final int ID_STEERING_WHEEL_Y = 559954810;
    public static final int ID_STEERING_WHEEL_Z = 559954811;
    public static final int ID_SUNROOF_AND_SUNSHADE_S = 557863027;
    public static final int ID_SUNROOF_CLOSE_NOTICER = 557862968;
    public static final int ID_SUNROOF_INIT_STATER = 557862967;
    public static final int ID_SUNROOF_POSITIONR = 557862970;
    public static final int ID_SUNROOF_WINDOWBLIND_POSITIONR = 557862969;
    public static final int ID_SUN_ROOF_STATER = 557862952;
    public static final int ID_SYSTEM_CONFIG_R = 557863060;
    public static final int ID_TAIL_DOOR_TYPE_CONFIG_R = 557859175;
    public static final int ID_THIRD_ROW_SEATS_EXECUTION_RESULTS_R = 557863067;
    public static final int ID_TURN_REARVIEW_MIRROR = 557857652;
    public static final int ID_TURN_STEERING_WHEEL = 557857653;
    public static final int ID_VOICE_CTL_MOON_ROOF_S = 557863020;
    public static final int ID_VOICE_CTL_SUNSHADE_PANEL_S = 557863021;
    public static final int ID_WAIST_SUPPORT_ADJUSTMENT_CO_PILOT_S = 557863038;
    public static final int ID_WAIST_SUPPORT_ADJUSTMENT_MAIN_S = 557863037;
    public static final int ID_WINDOWBLIND_INIT_STATER = 557862966;
    public static final int ID_WINDOWBLIND_STATER = 557862971;
    public static final int ID_WINDOWS_MODE_S = 557863026;
    public static final int ID_WINDOW_ANTI_PINCH_CONFIG_SECOND = 557856209;
    public static final int ID_WINDOW_CONTROL_STATE_LEFT_FRONT_R = 557862916;
    public static final int ID_WINDOW_CONTROL_STATE_LEFT_FRONT_S = 557862920;
    public static final int ID_WINDOW_CONTROL_STATE_LEFT_REAR_R = 557862918;
    public static final int ID_WINDOW_CONTROL_STATE_LEFT_REAR_S = 557862922;
    public static final int ID_WINDOW_CONTROL_STATE_RIGHT_FRONT_R = 557862917;
    public static final int ID_WINDOW_CONTROL_STATE_RIGHT_FRONT_S = 557862921;
    public static final int ID_WINDOW_CONTROL_STATE_RIGHT_REAR_R = 557862919;
    public static final int ID_WINDOW_CONTROL_STATE_RIGHT_REAR_S = 557862923;
    public static final int ID_WINDOW_CONTROL_STATE_S = 557928561;
    public static final int ID_WINDOW_INIT_STATE_FL = 557862954;
    public static final int ID_WINDOW_INIT_STATE_FR = 557862956;
    public static final int ID_WINDOW_INIT_STATE_RL = 557862955;
    public static final int ID_WINDOW_INIT_STATE_RR = 557862957;
    public static final int ID_WINDOW_OPEN_PERCENT_LEFT_FRONT_R = 557862936;
    public static final int ID_WINDOW_OPEN_PERCENT_LEFT_FRONT_S = 557862932;
    public static final int ID_WINDOW_OPEN_PERCENT_LEFT_REAR_R = 557862937;
    public static final int ID_WINDOW_OPEN_PERCENT_LEFT_REAR_S = 557862933;
    public static final int ID_WINDOW_OPEN_PERCENT_MOON_R = 557862940;
    public static final int ID_WINDOW_OPEN_PERCENT_MOON_S = 557862924;
    public static final int ID_WINDOW_OPEN_PERCENT_REAR_PANELR = 557862991;
    public static final int ID_WINDOW_OPEN_PERCENT_REAR_PANELS = 557862992;
    public static final int ID_WINDOW_OPEN_PERCENT_RIGHT_FRONT_R = 557862938;
    public static final int ID_WINDOW_OPEN_PERCENT_RIGHT_FRONT_S = 557862934;
    public static final int ID_WINDOW_OPEN_PERCENT_RIGHT_REAR_R = 557862939;
    public static final int ID_WINDOW_OPEN_PERCENT_RIGHT_REAR_S = 557862935;
    public static final int ID_WINDOW_OPEN_PERCENT_SUN_R = 557862941;
    public static final int ID_WINDOW_OPEN_PERCENT_SUN_S = 557862925;
    public static final int ID_WINDOW_PERCENTAGE_CONTROL_CONFIG_R = 557863048;
    public static final int ID_WINDOW_PERMIT_STATER = 557862965;
    public static final int ID_WINDOW_STATE_LEFT_FRONT = 557862912;
    public static final int ID_WINDOW_STATE_LEFT_REAR = 557862913;
    public static final int ID_WINDOW_STATE_MOON_ROOFR = 557862949;
    public static final int ID_WINDOW_STATE_RIGHT_FRONT = 557862914;
    public static final int ID_WINDOW_STATE_RIGHT_REAR = 557862915;
    public static final int ID_WINDOW_STATE_SUNSHADER = 557862950;
    public static final int ID_WINDOW_THERMAL_PROTECTION_STATE_FL = 557862959;
    public static final int ID_WINDOW_THERMAL_PROTECTION_STATE_FR = 557862960;
    public static final int ID_WINDOW_THERMAL_PROTECTION_STATE_FRONT_SUNSHADE = 557862963;
    public static final int ID_WINDOW_THERMAL_PROTECTION_STATE_REAR_SUNSHADE = 557862964;
    public static final int ID_WINDOW_THERMAL_PROTECTION_STATE_RL = 557862958;
    public static final int ID_WINDOW_THERMAL_PROTECTION_STATE_RR = 557862961;
    public static final int ID_WINDOW_THERMAL_PROTECTION_STATE_SKYLIGHT = 557862962;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.carbody";
    public static final String TAG = "CAR.VENDOR.CARBODY";

    public interface OnCarCarbodyEventListener {
        void onCarbodyErrorEvent(int i, int i2);

        void onCarbodyEventChange(CarbodyEventChangeValue carbodyEventChangeValue);
    }

    void addCarCarbodyEventListener(OnCarCarbodyEventListener onCarCarbodyEventListener) throws CarNotConnectedException;

    int getAntiTheftStatusR() throws CarNotConnectedException;

    int getAutoExternalRearMirrorFoldActionStateR() throws CarNotConnectedException;

    int getAutoExternalRearMirrorFoldedStateR() throws CarNotConnectedException;

    int getAutoSystemState() throws CarNotConnectedException;

    int getAutoType() throws CarNotConnectedException;

    int getAutoTypeNew() throws CarNotConnectedException;

    int getAwningConfigutationR() throws CarNotConnectedException;

    int getBackDoorAntiPlayModeR() throws CarNotConnectedException;

    int getBackDoorCurStateR() throws CarNotConnectedException;

    int getBackDoorInitStateR() throws CarNotConnectedException;

    int getBackDoorPositionR() throws CarNotConnectedException;

    int getBatteryVoltageLevel() throws CarNotConnectedException;

    int getBatteryVoltageLevelLowTips() throws CarNotConnectedException;

    int getBcmVechileStatus() throws CarNotConnectedException;

    int[] getCan055R() throws CarNotConnectedException;

    int[] getCan0d5R() throws CarNotConnectedException;

    int[] getCan10dR() throws CarNotConnectedException;

    int[] getCan121R() throws CarNotConnectedException;

    int[] getCan123R() throws CarNotConnectedException;

    int[] getCan12dR() throws CarNotConnectedException;

    int[] getCan12fR() throws CarNotConnectedException;

    int[] getCan133R() throws CarNotConnectedException;

    int[] getCan138R() throws CarNotConnectedException;

    int[] getCan13fR() throws CarNotConnectedException;

    int[] getCan144R() throws CarNotConnectedException;

    int[] getCan145R() throws CarNotConnectedException;

    int[] getCan151R() throws CarNotConnectedException;

    int[] getCan167R() throws CarNotConnectedException;

    int[] getCan18aR() throws CarNotConnectedException;

    int[] getCan19fR() throws CarNotConnectedException;

    int[] getCan1c0R() throws CarNotConnectedException;

    int[] getCan1d1R() throws CarNotConnectedException;

    int[] getCan1eaR() throws CarNotConnectedException;

    int[] getCan218R() throws CarNotConnectedException;

    int[] getCan223R() throws CarNotConnectedException;

    int[] getCan233R() throws CarNotConnectedException;

    int[] getCan235R() throws CarNotConnectedException;

    int[] getCan23aR() throws CarNotConnectedException;

    int[] getCan242R() throws CarNotConnectedException;

    int[] getCan245R() throws CarNotConnectedException;

    int[] getCan248R() throws CarNotConnectedException;

    int[] getCan24aR() throws CarNotConnectedException;

    int[] getCan24cR() throws CarNotConnectedException;

    int[] getCan257R() throws CarNotConnectedException;

    int[] getCan258R() throws CarNotConnectedException;

    int[] getCan25dR() throws CarNotConnectedException;

    int[] getCan261R() throws CarNotConnectedException;

    int[] getCan26fR() throws CarNotConnectedException;

    int[] getCan271R() throws CarNotConnectedException;

    int[] getCan272R() throws CarNotConnectedException;

    int[] getCan275R() throws CarNotConnectedException;

    int[] getCan27aR() throws CarNotConnectedException;

    int[] getCan287R() throws CarNotConnectedException;

    int[] getCan288R() throws CarNotConnectedException;

    int[] getCan28fR() throws CarNotConnectedException;

    int[] getCan294R() throws CarNotConnectedException;

    int[] getCan296R() throws CarNotConnectedException;

    int[] getCan29cR() throws CarNotConnectedException;

    int[] getCan29eR() throws CarNotConnectedException;

    int[] getCan2a4R() throws CarNotConnectedException;

    int[] getCan2a6R() throws CarNotConnectedException;

    int[] getCan2a7R() throws CarNotConnectedException;

    int[] getCan2b0R() throws CarNotConnectedException;

    int[] getCan2b4R() throws CarNotConnectedException;

    int[] getCan2b6R() throws CarNotConnectedException;

    int[] getCan2baR() throws CarNotConnectedException;

    int[] getCan2bcR() throws CarNotConnectedException;

    int[] getCan2c0R() throws CarNotConnectedException;

    int[] getCan2d5R() throws CarNotConnectedException;

    int[] getCan2ebR() throws CarNotConnectedException;

    int[] getCan2f0R() throws CarNotConnectedException;

    int[] getCan30dR() throws CarNotConnectedException;

    int[] getCan319R() throws CarNotConnectedException;

    int[] getCan31eR() throws CarNotConnectedException;

    int[] getCan321R() throws CarNotConnectedException;

    int[] getCan323R() throws CarNotConnectedException;

    int[] getCan32dR() throws CarNotConnectedException;

    int[] getCan334R() throws CarNotConnectedException;

    int[] getCan337R() throws CarNotConnectedException;

    int[] getCan338R() throws CarNotConnectedException;

    int[] getCan341R() throws CarNotConnectedException;

    int[] getCan342R() throws CarNotConnectedException;

    int[] getCan344R() throws CarNotConnectedException;

    int[] getCan346R() throws CarNotConnectedException;

    int[] getCan347R() throws CarNotConnectedException;

    int[] getCan34aR() throws CarNotConnectedException;

    int[] getCan34fR() throws CarNotConnectedException;

    int[] getCan351R() throws CarNotConnectedException;

    int[] getCan359R() throws CarNotConnectedException;

    int[] getCan36bR() throws CarNotConnectedException;

    int[] getCan36dR() throws CarNotConnectedException;

    int[] getCan36eR() throws CarNotConnectedException;

    int[] getCan37fR() throws CarNotConnectedException;

    int[] getCan38aR() throws CarNotConnectedException;

    int[] getCan38bR() throws CarNotConnectedException;

    int[] getCan38fR() throws CarNotConnectedException;

    int[] getCan394R() throws CarNotConnectedException;

    int[] getCan396R() throws CarNotConnectedException;

    int[] getCan3a0R() throws CarNotConnectedException;

    int[] getCan3a7R() throws CarNotConnectedException;

    int[] getCan3acR() throws CarNotConnectedException;

    int[] getCan3adR() throws CarNotConnectedException;

    int[] getCan3b0R() throws CarNotConnectedException;

    int[] getCan3b3R() throws CarNotConnectedException;

    int[] getCan3b4R() throws CarNotConnectedException;

    int[] getCan3bbR() throws CarNotConnectedException;

    int[] getCan3bdR() throws CarNotConnectedException;

    int[] getCan3c1R() throws CarNotConnectedException;

    int[] getCan3c8R() throws CarNotConnectedException;

    int[] getCan3caR() throws CarNotConnectedException;

    int[] getCan3cdR() throws CarNotConnectedException;

    int[] getCan3d6R() throws CarNotConnectedException;

    int[] getCan3d9R() throws CarNotConnectedException;

    int[] getCan3daR() throws CarNotConnectedException;

    int[] getCan3f1R() throws CarNotConnectedException;

    int[] getCan3f3R() throws CarNotConnectedException;

    int[] getCan3feR() throws CarNotConnectedException;

    int[] getCan3ffR() throws CarNotConnectedException;

    int[] getCan401R() throws CarNotConnectedException;

    int[] getCan402R() throws CarNotConnectedException;

    int[] getCan404R() throws CarNotConnectedException;

    int[] getCan407R() throws CarNotConnectedException;

    int[] getCan40dR() throws CarNotConnectedException;

    int[] getCan417R() throws CarNotConnectedException;

    int[] getCan418R() throws CarNotConnectedException;

    int[] getCan41aR() throws CarNotConnectedException;

    int[] getCan41cR() throws CarNotConnectedException;

    int[] getCan41eR() throws CarNotConnectedException;

    int[] getCan41fR() throws CarNotConnectedException;

    int[] getCan429R() throws CarNotConnectedException;

    int[] getCan42eR() throws CarNotConnectedException;

    int[] getCan42fR() throws CarNotConnectedException;

    int[] getCan432R() throws CarNotConnectedException;

    int[] getCan433R() throws CarNotConnectedException;

    int[] getCan434R() throws CarNotConnectedException;

    int[] getCan436R() throws CarNotConnectedException;

    int[] getCan437R() throws CarNotConnectedException;

    int[] getCan438R() throws CarNotConnectedException;

    int[] getCan43eR() throws CarNotConnectedException;

    int[] getCan43fR() throws CarNotConnectedException;

    int[] getCan443R() throws CarNotConnectedException;

    int[] getCan444R() throws CarNotConnectedException;

    int[] getCan447R() throws CarNotConnectedException;

    int[] getCan449R() throws CarNotConnectedException;

    int[] getCan453R() throws CarNotConnectedException;

    int[] getCan454R() throws CarNotConnectedException;

    int[] getCan461R() throws CarNotConnectedException;

    int[] getCan464R() throws CarNotConnectedException;

    int[] getCan46cR() throws CarNotConnectedException;

    int[] getCan470R() throws CarNotConnectedException;

    int[] getCan475R() throws CarNotConnectedException;

    int[] getCan480R() throws CarNotConnectedException;

    int[] getCan481R() throws CarNotConnectedException;

    int[] getCan486R() throws CarNotConnectedException;

    int[] getCan487R() throws CarNotConnectedException;

    int[] getCan488R() throws CarNotConnectedException;

    int[] getCan489R() throws CarNotConnectedException;

    int[] getCan48aR() throws CarNotConnectedException;

    int[] getCan48cR() throws CarNotConnectedException;

    int[] getCan48eR() throws CarNotConnectedException;

    int[] getCan48fR() throws CarNotConnectedException;

    int[] getCan494R() throws CarNotConnectedException;

    int[] getCan49cR() throws CarNotConnectedException;

    int[] getCan4a1R() throws CarNotConnectedException;

    int[] getCan4a4R() throws CarNotConnectedException;

    int[] getCan4a5R() throws CarNotConnectedException;

    int[] getCan4a7R() throws CarNotConnectedException;

    int[] getCan4b1R() throws CarNotConnectedException;

    int[] getCan4b2R() throws CarNotConnectedException;

    int[] getCan4b3R() throws CarNotConnectedException;

    int[] getCan4b6R() throws CarNotConnectedException;

    int[] getCan4bbR() throws CarNotConnectedException;

    int[] getCan4bdR() throws CarNotConnectedException;

    int[] getCan4bfR() throws CarNotConnectedException;

    int[] getCan4c2R() throws CarNotConnectedException;

    int[] getCan4c8R() throws CarNotConnectedException;

    int[] getCan4d9R() throws CarNotConnectedException;

    int[] getCan4e5R() throws CarNotConnectedException;

    int[] getCan4e6R() throws CarNotConnectedException;

    int[] getCan4e8R() throws CarNotConnectedException;

    int[] getCan4e9R() throws CarNotConnectedException;

    int[] getCan4f2R() throws CarNotConnectedException;

    int[] getCan4f4R() throws CarNotConnectedException;

    int[] getCan4f6R() throws CarNotConnectedException;

    int[] getCan4f7R() throws CarNotConnectedException;

    int[] getCan4faR() throws CarNotConnectedException;

    int[] getCan4fbR() throws CarNotConnectedException;

    int[] getCan4fdR() throws CarNotConnectedException;

    int[] getCan4feR() throws CarNotConnectedException;

    int getDoorLockStatusAreaRightRear() throws CarNotConnectedException;

    int getDoorLockStatusLeftRear() throws CarNotConnectedException;

    int getDoorLockStatusRightFront() throws CarNotConnectedException;

    int getDoorStateHood() throws CarNotConnectedException;

    int getDoorStateLeftFront() throws CarNotConnectedException;

    int getDoorStateLeftRear() throws CarNotConnectedException;

    int getDoorStateLuggage() throws CarNotConnectedException;

    int getDoorStateRightFront() throws CarNotConnectedException;

    int getDoorStateRightRear() throws CarNotConnectedException;

    int getElecSlideDoorStateR() throws CarNotConnectedException;

    int getElectricPedalConfigR() throws CarNotConnectedException;

    int getEnergyTypeR() throws CarNotConnectedException;

    int getEvReadyIndicatorLightStateR() throws CarNotConnectedException;

    int getFourPeopleEnjoyConfig2R() throws CarNotConnectedException;

    int getFourPeopleEnjoyConfigR() throws CarNotConnectedException;

    int getFseLagerBedModeConfigR() throws CarNotConnectedException;

    int getFseSeatSystemWorkModeR() throws CarNotConnectedException;

    int getFuelElecLowPowerR() throws CarNotConnectedException;

    int getInternalDoorHandleStateByRearBCMLFR() throws CarNotConnectedException;

    int getInternalDoorHandleStateByRearBCMLRR() throws CarNotConnectedException;

    int getInternalDoorHandleStateByRearBCMRFR() throws CarNotConnectedException;

    int getInternalDoorHandleStateByRearBCMRRR() throws CarNotConnectedException;

    int getInternalDoorHandleStateLeftBackR() throws CarNotConnectedException;

    int getInternalDoorHandleStateLeftFrontR() throws CarNotConnectedException;

    int getInternalDoorHandleStateRightBackR() throws CarNotConnectedException;

    int getInternalDoorHandleStateRightFrontR() throws CarNotConnectedException;

    int getLeftMidDoorVoiceWakeUpChangedR() throws CarNotConnectedException;

    int getLeftSeatRailsRow3FeedbackPositionR() throws CarNotConnectedException;

    int getLeftSeatRow3NonElectricallyAdjustStateR() throws CarNotConnectedException;

    int getLeftSlideDoorActionR() throws CarNotConnectedException;

    int getLeftSlideDoorPercentR() throws CarNotConnectedException;

    int getLeftSlideDoorStateR() throws CarNotConnectedException;

    int getLeftStrutMorotRunningState() throws CarNotConnectedException;

    int getLightHighBeamR() throws CarNotConnectedException;

    int getLightLowBeamStatusR() throws CarNotConnectedException;

    int getLightRearFog() throws CarNotConnectedException;

    int getLightRearPosition() throws CarNotConnectedException;

    int getLightSideStatusR() throws CarNotConnectedException;

    int getMoonRoofConfig() throws CarNotConnectedException;

    int getMoonRoofConfigR() throws CarNotConnectedException;

    int getMoonRoofPercentControlFlagR() throws CarNotConnectedException;

    int getPowerLevel() throws CarNotConnectedException;

    int getReadyIndicatorLightStateR() throws CarNotConnectedException;

    int getRealAutoVinEightR() throws CarNotConnectedException;

    int getRealAutoVinElevenR() throws CarNotConnectedException;

    int getRealAutoVinFifteenR() throws CarNotConnectedException;

    int getRealAutoVinFiveR() throws CarNotConnectedException;

    int getRealAutoVinFourR() throws CarNotConnectedException;

    int getRealAutoVinFourteenR() throws CarNotConnectedException;

    int getRealAutoVinNineR() throws CarNotConnectedException;

    int getRealAutoVinOneR() throws CarNotConnectedException;

    int getRealAutoVinSevenR() throws CarNotConnectedException;

    int getRealAutoVinSeventeenR() throws CarNotConnectedException;

    int getRealAutoVinSixR() throws CarNotConnectedException;

    int getRealAutoVinSixteenR() throws CarNotConnectedException;

    int getRealAutoVinTenR() throws CarNotConnectedException;

    int getRealAutoVinThirteenR() throws CarNotConnectedException;

    int getRealAutoVinThreeR() throws CarNotConnectedException;

    int getRealAutoVinTwelveR() throws CarNotConnectedException;

    int getRealAutoVinTwoR() throws CarNotConnectedException;

    int getRearWindowblindInitStateNewR() throws CarNotConnectedException;

    int getRearWindowblindInitStateR() throws CarNotConnectedException;

    int getRearWindowblindPositionR() throws CarNotConnectedException;

    int getRearWindowblindStateR() throws CarNotConnectedException;

    int getRearviewMirrorSupport() throws CarNotConnectedException;

    float getRearviewMirrorXLeft() throws CarNotConnectedException;

    float getRearviewMirrorXRight() throws CarNotConnectedException;

    float getRearviewMirrorYLeft() throws CarNotConnectedException;

    float getRearviewMirrorYRight() throws CarNotConnectedException;

    int getRecoverOrSaveParamsChanged5() throws CarNotConnectedException;

    int getRestoreSeatConfigR() throws CarNotConnectedException;

    int getRightMidDoorVoiceWakeUpChangedR() throws CarNotConnectedException;

    int getRightSeatRailsRow3FeedbackPositionR() throws CarNotConnectedException;

    int getRightSlideDoorActionR() throws CarNotConnectedException;

    int getRightSlideDoorPercentR() throws CarNotConnectedException;

    int getRightSlideDoorStateR() throws CarNotConnectedException;

    int getRightStrutMorotRunningStateR() throws CarNotConnectedException;

    int getScreenControlSlideDoorLLeftRightR() throws CarNotConnectedException;

    int getScreenControlSlideDoorLeftLeftR() throws CarNotConnectedException;

    int getScreenControlSlideDoorRightLeftR() throws CarNotConnectedException;

    int getScreenControlSlideDoorRightRightR() throws CarNotConnectedException;

    int getScreenControlSlideDoorSlidingLeftLeftR() throws CarNotConnectedException;

    int getScreenControlSlideDoorSlidingLeftRightR() throws CarNotConnectedException;

    int getScreenControlSlideDoorSlidingRightLeftR() throws CarNotConnectedException;

    int getScreenControlSlideDoorSlidingRightRightR() throws CarNotConnectedException;

    int getSeatLargeBedModeConfigR() throws CarNotConnectedException;

    int getSeatLumbarSupportConfigCoPilotLeftR() throws CarNotConnectedException;

    int getSeatLumbarSupportConfigCoPilotRightR() throws CarNotConnectedException;

    int getSeatLumbarSupportConfigMainLeftR() throws CarNotConnectedException;

    int getSeatLumbarSupportConfigMainRightR() throws CarNotConnectedException;

    int getSeatModeCurFeedbackStatus2R() throws CarNotConnectedException;

    int getSeatModeCurFeedbackStatusR() throws CarNotConnectedException;

    int getSeatModeFailFeedbackStatus2R() throws CarNotConnectedException;

    int getSeatModeFailFeedbackStatusR() throws CarNotConnectedException;

    int getSeatModeSuccessFeedbackStatus2R() throws CarNotConnectedException;

    int getSeatModeSuccessFeedbackStatusR() throws CarNotConnectedException;

    int getSeatModeWakeupFeedbackStateR() throws CarNotConnectedException;

    int getShiftModeR() throws CarNotConnectedException;

    int getSmartVoiceLimit() throws CarNotConnectedException;

    int getSteeringWheelValueAngel() throws CarNotConnectedException;

    int getSteeringWheelValueSpeed() throws CarNotConnectedException;

    float getSteeringWheelY() throws CarNotConnectedException;

    float getSteeringWheelZ() throws CarNotConnectedException;

    int getSunRoofStateR() throws CarNotConnectedException;

    int getSunroofCloseNoticeR() throws CarNotConnectedException;

    int getSunroofInitStateR() throws CarNotConnectedException;

    int getSunroofPositionR() throws CarNotConnectedException;

    int getSunroofWindowblindPositionR() throws CarNotConnectedException;

    int getSystemConfigR() throws CarNotConnectedException;

    int getTailDoorTypeConfigR() throws CarNotConnectedException;

    int getThirdRowSeatsExecutionResultsR() throws CarNotConnectedException;

    int getWindowAntiPinchConfig() throws CarNotConnectedException;

    int getWindowControlStateLeftFrontR() throws CarNotConnectedException;

    int getWindowControlStateLeftRearR() throws CarNotConnectedException;

    int getWindowControlStateRightFrontR() throws CarNotConnectedException;

    int getWindowControlStateRightRearR() throws CarNotConnectedException;

    int getWindowInitStateFL() throws CarNotConnectedException;

    int getWindowInitStateFR() throws CarNotConnectedException;

    int getWindowInitStateRL() throws CarNotConnectedException;

    int getWindowInitStateRR() throws CarNotConnectedException;

    int getWindowOpenPercentLeftFrontR() throws CarNotConnectedException;

    int getWindowOpenPercentLeftRearR() throws CarNotConnectedException;

    int getWindowOpenPercentMoonR() throws CarNotConnectedException;

    int getWindowOpenPercentRearPanelR() throws CarNotConnectedException;

    int getWindowOpenPercentRightFrontR() throws CarNotConnectedException;

    int getWindowOpenPercentRightRearR() throws CarNotConnectedException;

    int getWindowOpenPercentSunR() throws CarNotConnectedException;

    int getWindowPercentageControlConfigR() throws CarNotConnectedException;

    int getWindowPermitStateR() throws CarNotConnectedException;

    int getWindowStateLeftFront() throws CarNotConnectedException;

    int getWindowStateLeftRear() throws CarNotConnectedException;

    int getWindowStateMoonRoofR() throws CarNotConnectedException;

    int getWindowStateRightFront() throws CarNotConnectedException;

    int getWindowStateRightRear() throws CarNotConnectedException;

    int getWindowStateSunshadeR() throws CarNotConnectedException;

    int getWindowThermalProtectionStateFL() throws CarNotConnectedException;

    int getWindowThermalProtectionStateFR() throws CarNotConnectedException;

    int getWindowThermalProtectionStateFrontSunshade() throws CarNotConnectedException;

    int getWindowThermalProtectionStateRL() throws CarNotConnectedException;

    int getWindowThermalProtectionStateRR() throws CarNotConnectedException;

    int getWindowThermalProtectionStateRearSunshade() throws CarNotConnectedException;

    int getWindowThermalProtectionStateSkylight() throws CarNotConnectedException;

    int getWindowblindInitStateR() throws CarNotConnectedException;

    int getWindowblindStateR() throws CarNotConnectedException;

    int getonSaveSeatParamsAllChangedPlace5() throws CarNotConnectedException;

    int getonSaveSeatParamsAllChangedPlace7() throws CarNotConnectedException;

    void removeCarCarbodyEventListener(OnCarCarbodyEventListener onCarCarbodyEventListener) throws CarNotConnectedException;

    void setAllDoorsLock(int i) throws CarNotConnectedException;

    void setAllDoorsLockSceneS(int i) throws CarNotConnectedException;

    void setAutoExternalRearMirrorFoldStateS(int i) throws CarNotConnectedException;

    void setBackDoorHeightPositionMyCarS(int i) throws CarNotConnectedException;

    void setBackDoorHeightPositionR(int i) throws CarNotConnectedException;

    void setBackDoorOpenedHeightS(int i) throws CarNotConnectedException;

    void setElecBackDoorControlStateR(int i) throws CarNotConnectedException;

    void setElecSlideDoorStateS(int i) throws CarNotConnectedException;

    void setFseLargeBedModeS(int i) throws CarNotConnectedException;

    void setLargeBedModeS(int i) throws CarNotConnectedException;

    void setLeftSlideDoorVoiceControlStateS(int i) throws CarNotConnectedException;

    void setModeCommandBs(int[] iArr) throws CarNotConnectedException;

    void setModeCommandCs(int[] iArr) throws CarNotConnectedException;

    void setModeCommandS(int[] iArr) throws CarNotConnectedException;

    void setRightSlideDoorVoiceControlStateS(int i) throws CarNotConnectedException;

    void setSeatModeControlS(int i) throws CarNotConnectedException;

    void setSeatRow3OnekeyEnterPitS(int i) throws CarNotConnectedException;

    void setSettingLuggageS(int i) throws CarNotConnectedException;

    void setSideWindowShadeStateS(int i) throws CarNotConnectedException;

    void setSunroofAndSunshadeS(int i) throws CarNotConnectedException;

    void setVoiceCtlMoonRoofS(int i) throws CarNotConnectedException;

    void setVoiceCtlSunshadePanelS(int i) throws CarNotConnectedException;

    void setWaistSupportAdjustmentCoPilotS(int i) throws CarNotConnectedException;

    void setWaistSupportAdjustmentMainS(int i) throws CarNotConnectedException;

    void setWindowControlState(int[] iArr) throws CarNotConnectedException;

    void setWindowControlStateLeftFrontS(int i) throws CarNotConnectedException;

    void setWindowControlStateLeftRearS(int i) throws CarNotConnectedException;

    void setWindowControlStateRightFrontS(int i) throws CarNotConnectedException;

    void setWindowControlStateRightRearS(int i) throws CarNotConnectedException;

    void setWindowOpenPercentLeftFrontS(int i) throws CarNotConnectedException;

    void setWindowOpenPercentLeftRearS(int i) throws CarNotConnectedException;

    void setWindowOpenPercentMoonS(int i) throws CarNotConnectedException;

    void setWindowOpenPercentRearPanelS(int i) throws CarNotConnectedException;

    void setWindowOpenPercentRightFrontS(int i) throws CarNotConnectedException;

    void setWindowOpenPercentRightRearS(int i) throws CarNotConnectedException;

    void setWindowOpenPercentSunS(int i) throws CarNotConnectedException;

    void setWindowsModeS(int i) throws CarNotConnectedException;

    void setturnRearviewMirror(int i) throws CarNotConnectedException;

    void setturnSteeringWheel(int i) throws CarNotConnectedException;
}

package android.hardware.bydauto.bodywork;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.bodywork.tsimpl.BodyworkManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoBodyworkDevice extends AbsBYDAutoDevice {
    public static final int ACTION_FRONT = 3;
    public static final int ACTION_LEFT_DOWN = 4;
    public static final int ACTION_LEFT_LEFT = 1;
    public static final int ACTION_LEFT_RIGHT = 2;
    public static final int ACTION_LEFT_UP = 3;
    public static final int ACTION_RIGHT_DOWN = 8;
    public static final int ACTION_RIGHT_LEFT = 5;
    public static final int ACTION_RIGHT_RIGHT = 6;
    public static final int ACTION_RIGHT_UP = 7;
    public static final int ACTION_UP = 1;
    public static final int AUTO_MODEL_NEW_QIN_EV = 2;
    public static final int AUTO_MODEL_NEW_QIN_FUEL = 3;
    public static final int AUTO_MODEL_NEW_QIN_HEV = 1;
    public static final int AUTO_MODEL_NEW_TANG_EV = 6;
    public static final int AUTO_MODEL_NEW_TANG_FUEL = 5;
    public static final int AUTO_MODEL_NEW_TANG_HEV = 4;
    public static final int AUTO_MODEL_NULL = 11;
    public static final int AUTO_MODEL_SONG_18_EV = 9;
    public static final int AUTO_MODEL_SONG_18_FUEL = 7;
    public static final int AUTO_MODEL_SONG_18_HEV = 8;
    public static final int AUTO_MODEL_SONG_MAX_HEV = 10;
    public static final int AUTO_TYPE_3A = 20;
    public static final int AUTO_TYPE_3B = 36;
    public static final int AUTO_TYPE_5A = 5;
    public static final int AUTO_TYPE_5AEV = 29;
    public static final int AUTO_TYPE_5B = 22;
    public static final int AUTO_TYPE_5BH = 27;
    public static final int AUTO_TYPE_5BHE = 53;
    public static final int AUTO_TYPE_5BHI = 64;
    public static final int AUTO_TYPE_6A = 7;
    public static final int AUTO_TYPE_6B = 8;
    public static final int AUTO_TYPE_DA2EA = 172;
    public static final int AUTO_TYPE_EK = 138;
    public static final int AUTO_TYPE_EK_SPORT = 165;
    public static final int AUTO_TYPE_EL = 96;
    public static final int AUTO_TYPE_EL20 = 117;
    public static final int AUTO_TYPE_ELEBD = 149;
    public static final int AUTO_TYPE_EM2E = 127;
    public static final int AUTO_TYPE_EMEA = 95;
    public static final int AUTO_TYPE_EREA = 154;
    public static final int AUTO_TYPE_ESEA = 146;
    public static final int AUTO_TYPE_F0 = 0;
    public static final int AUTO_TYPE_F3 = 1;
    public static final int AUTO_TYPE_F3R = 2;
    public static final int AUTO_TYPE_F6 = 6;
    public static final int AUTO_TYPE_G3 = 3;
    public static final int AUTO_TYPE_HA = 13;
    public static final int AUTO_TYPE_HA2EM = 101;
    public static final int AUTO_TYPE_HA2FL = 122;
    public static final int AUTO_TYPE_HA2FM = 122;
    public static final int AUTO_TYPE_HA2H = 141;
    public static final int AUTO_TYPE_HA2HE = 102;
    public static final int AUTO_TYPE_HA3H = 163;
    public static final int AUTO_TYPE_HAC = 46;
    public static final int AUTO_TYPE_HAD = 50;
    public static final int AUTO_TYPE_HADA = 63;
    public static final int AUTO_TYPE_HADE = 51;
    public static final int AUTO_TYPE_HADF = 66;
    public static final int AUTO_TYPE_HADG = 85;
    public static final int AUTO_TYPE_HAEA = 49;
    public static final int AUTO_TYPE_HAEC = 62;
    public static final int AUTO_TYPE_HAEV = 28;
    public static final int AUTO_TYPE_HA_15A = 37;
    public static final int AUTO_TYPE_HB = 14;
    public static final int AUTO_TYPE_HC = 15;
    public static final int AUTO_TYPE_HCE = 92;
    public static final int AUTO_TYPE_HCEEC = 178;
    public static final int AUTO_TYPE_HCEF = 135;
    public static final int AUTO_TYPE_HCE_2023 = 176;
    public static final int AUTO_TYPE_HCF = 93;
    public static final int AUTO_TYPE_HCHY = 121;
    public static final int AUTO_TYPE_HCH_2023 = 177;
    public static final int AUTO_TYPE_HD = 31;
    public static final int AUTO_TYPE_HDE = 98;
    public static final int AUTO_TYPE_HDE21 = 124;
    public static final int AUTO_TYPE_HDF = 99;
    public static final int AUTO_TYPE_HTE_2021 = 171;
    public static final int AUTO_TYPE_HTH_2021 = 170;
    public static final int AUTO_TYPE_KD = 131;
    public static final int AUTO_TYPE_L3 = 4;
    public static final int AUTO_TYPE_L3G = 33;
    public static final int AUTO_TYPE_M6 = 10;
    public static final int AUTO_TYPE_MEE = 44;
    public static final int AUTO_TYPE_MEEY = 109;
    public static final int AUTO_TYPE_MEF = 42;
    public static final int AUTO_TYPE_MEFD = 81;
    public static final int AUTO_TYPE_MEH = 43;
    public static final int AUTO_TYPE_MEHD = 67;
    public static final int AUTO_TYPE_MEHM = 113;
    public static final int AUTO_TYPE_MRE = 60;
    public static final int AUTO_TYPE_MRH = 57;
    public static final int AUTO_TYPE_RSA = 45;
    public static final int AUTO_TYPE_S6 = 9;
    public static final int AUTO_TYPE_S6DM = 17;
    public static final int AUTO_TYPE_S8 = 16;
    public static final int AUTO_TYPE_SA = 11;
    public static final int AUTO_TYPE_SA2E = 84;
    public static final int AUTO_TYPE_SA2EM = 112;
    public static final int AUTO_TYPE_SA2FC = 82;
    public static final int AUTO_TYPE_SA2FL = 110;
    public static final int AUTO_TYPE_SA2H = 83;
    public static final int AUTO_TYPE_SA2HG = 111;
    public static final int AUTO_TYPE_SA3E = 72;
    public static final int AUTO_TYPE_SA3EE = 74;
    public static final int AUTO_TYPE_SA3EJ = 147;
    public static final int AUTO_TYPE_SA3F = 68;
    public static final int AUTO_TYPE_SA3FL = 139;
    public static final int AUTO_TYPE_SA3H = 71;
    public static final int AUTO_TYPE_SA3H_J_K_L = 162;
    public static final int AUTO_TYPE_SA3_22 = 173;
    public static final int AUTO_TYPE_SA5H = 161;
    public static final int AUTO_TYPE_SADM = 12;
    public static final int AUTO_TYPE_SAEA = 40;
    public static final int AUTO_TYPE_SAEC = 70;
    public static final int AUTO_TYPE_SAED = 97;
    public static final int AUTO_TYPE_SAEG = 94;
    public static final int AUTO_TYPE_SAEV = 39;
    public static final int AUTO_TYPE_SAFG = 73;
    public static final int AUTO_TYPE_SAFJ = 73;
    public static final int AUTO_TYPE_SAH = 32;
    public static final int AUTO_TYPE_SAHA = 88;
    public static final int AUTO_TYPE_SAHB = 89;
    public static final int AUTO_TYPE_SAHC = 90;
    public static final int AUTO_TYPE_SAHE = 65;
    public static final int AUTO_TYPE_SAHG = 61;
    public static final int AUTO_TYPE_SAHX = 74;
    public static final int AUTO_TYPE_SC = 30;
    public static final int AUTO_TYPE_SC2E = 128;
    public static final int AUTO_TYPE_SC3E = 160;
    public static final int AUTO_TYPE_SCEA = 41;
    public static final int AUTO_TYPE_SCED = 69;
    public static final int AUTO_TYPE_SCEU = 159;
    public static final int AUTO_TYPE_SCH = 35;
    public static final int AUTO_TYPE_SE = 23;
    public static final int AUTO_TYPE_SEF = 24;
    public static final int AUTO_TYPE_SEH = 48;
    public static final int AUTO_TYPE_SF = 153;
    public static final int AUTO_TYPE_SGE = 144;
    public static final int AUTO_TYPE_SGH = 145;
    public static final int AUTO_TYPE_SK2F = 130;
    public static final int AUTO_TYPE_SK2H = 129;
    public static final int AUTO_TYPE_SLHAB = 90;
    public static final int AUTO_TYPE_SQH = 155;
    public static final int AUTO_TYPE_ST = 47;
    public static final int AUTO_TYPE_ST2E = 157;
    public static final int AUTO_TYPE_ST2H = 156;
    public static final int AUTO_TYPE_STC = 56;
    public static final int AUTO_TYPE_STE = 58;
    public static final int AUTO_TYPE_STEAM = 167;
    public static final int AUTO_TYPE_STEB = 59;
    public static final int AUTO_TYPE_STEBU = 196;
    public static final int AUTO_TYPE_STEL = 114;
    public static final int AUTO_TYPE_STEM = 60;
    public static final int AUTO_TYPE_STF = 54;
    public static final int AUTO_TYPE_STF20 = 53;
    public static final int AUTO_TYPE_STF21 = 116;
    public static final int AUTO_TYPE_STFD = 55;
    public static final int AUTO_TYPE_STH20 = 52;
    public static final int AUTO_TYPE_STH21 = 115;
    public static final int AUTO_TYPE_STHAM = 166;
    public static final int AUTO_TYPE_STHM = 56;
    public static final int AUTO_TYPE_STM = 57;
    public static final int AUTO_TYPE_SUE = 150;
    public static final int AUTO_TYPE_SUH = 151;
    public static final int AUTO_TYPE_SZE_2023 = 169;
    public static final int AUTO_TYPE_SZH_2023 = 168;
    public static final int AUTO_TYPE_UFE = 158;
    public static final int AUTO_TYPE_UR = 164;
    public static final int AUTO_TYPE_UXE = 152;
    public static final int AUTO_TYPE_V3 = 174;
    public static final int AUTO_TYPE_V9 = 175;
    public static final int AUTO_TYPE_VA = 18;
    public static final int AUTO_TYPE_VB = 19;
    public static final int AUTO_TYPE_VBEV = 26;
    public static final int AUTO_TYPE_VBH = 25;
    public static final int AUTO_TYPE_VC = 21;
    public static final int AUTO_TYPE_e6B = 38;
    public static final int AUTO_TYPE_e6H = 34;
    public static final int AUTO_TYPE_e6K = 52;
    public static final int BACK_DOOR_ANTI_PLAY_MODE_CLOSE = 2;
    public static final int BACK_DOOR_ANTI_PLAY_MODE_OPEN = 1;
    public static final int BACK_DOOR_ANTI_PLAY_MODE_STOP = 0;
    public static final int BACK_DOOR_STATE_FULL_CLOSE = 2;
    public static final int BACK_DOOR_STATE_FULL_OPEN = 1;
    public static final int BACK_DOOR_STATE_HALF_OPEN = 3;
    public static final int BACK_DOOR_STATE_INVALID = 0;
    public static final int BATTERY_POWER_MAX = 255;
    public static final int BATTERY_POWER_MIN = 0;
    public static final int BODYWORK_ALARM_STATE_OFF = 0;
    public static final int BODYWORK_ALARM_STATE_ON = 1;
    public static final int BODYWORK_ALL_WINDOW_ANTI_PINCH = 4;
    public static final int BODYWORK_ANTI_PINCH_INVLAID = 0;
    public static final int BODYWORK_AUTO_SYSTEM_STATE_NORMAL = 0;
    public static final int BODYWORK_AUTO_SYSTEM_STATE_SET_SECURE = 1;
    public static final int BODYWORK_AUTO_SYSTEM_STATE_START_SECURE = 2;
    public static final int BODYWORK_AUTO_SYSTEM_STATE_UNDEFINED = 255;
    public static final int BODYWORK_BATTERY_MODE_DAY = 0;
    public static final int BODYWORK_BATTERY_MODE_INVALID = 255;
    public static final int BODYWORK_BATTERY_MODE_NIGHT = 1;
    public static final int BODYWORK_BATTERY_VOLTAGE_LEVEL_INVALID = 255;
    public static final int BODYWORK_BATTERY_VOLTAGE_LEVEL_LOW = 0;
    public static final int BODYWORK_BATTERY_VOLTAGE_LEVEL_NORMAL = 1;
    public static final int BODYWORK_CMD_DOOR_FUEL_TANK_CAP = 7;
    public static final int BODYWORK_CMD_DOOR_HOOD = 5;
    public static final int BODYWORK_CMD_DOOR_LEFT_FRONT = 1;
    public static final int BODYWORK_CMD_DOOR_LEFT_REAR = 3;
    public static final int BODYWORK_CMD_DOOR_LUGGAGE_DOOR = 6;
    public static final int BODYWORK_CMD_DOOR_RIGHT_FRONT = 2;
    public static final int BODYWORK_CMD_DOOR_RIGHT_REAR = 4;
    public static final int BODYWORK_CMD_MOON_ROOF = 5;
    public static final int BODYWORK_CMD_REAR_SUNSHADE_PANEL = 7;
    public static final int BODYWORK_CMD_STEERING_WHEEL_ANGEL = 1;
    public static final int BODYWORK_CMD_STEERING_WHEEL_SPEED = 2;
    public static final int BODYWORK_CMD_SUNSHADE_PANEL = 6;
    public static final int BODYWORK_CMD_WINDOW_LEFT_FRONT = 1;
    public static final int BODYWORK_CMD_WINDOW_LEFT_REAR = 3;
    public static final int BODYWORK_CMD_WINDOW_RIGHT_FRONT = 2;
    public static final int BODYWORK_CMD_WINDOW_RIGHT_REAR = 4;
    public static final int BODYWORK_COMMAND_BUSY = -2147482647;
    public static final int BODYWORK_COMMAND_FAILED = -2147482648;
    public static final int BODYWORK_COMMAND_INVALID_VALUE = -2147482645;
    public static final int BODYWORK_COMMAND_SUCCESS = 0;
    public static final int BODYWORK_COMMAND_TIMEOUT = -2147482646;
    public static final int BODYWORK_FRONT_WINDOW_ANTI_PINCH = 3;
    public static final int BODYWORK_LEFT_WINDOW_ANTI_PINCH_CONFIG = 2;
    public static final int BODYWORK_LOW_POWER_BOTH = 3;
    public static final int BODYWORK_LOW_POWER_ELEC = 2;
    public static final int BODYWORK_LOW_POWER_FUEL = 1;
    public static final int BODYWORK_LOW_POWER_NORMAL = 0;
    public static final int BODYWORK_NO_ANTI_PINCH = 1;
    public static final int BODYWORK_POWER_LEVEL_ACC = 1;
    public static final int BODYWORK_POWER_LEVEL_FAKE_OK = 4;
    public static final int BODYWORK_POWER_LEVEL_INVALID = 255;
    public static final int BODYWORK_POWER_LEVEL_OFF = 0;
    public static final int BODYWORK_POWER_LEVEL_OK = 3;
    public static final int BODYWORK_POWER_LEVEL_ON = 2;
    public static final int BODYWORK_STATE_CLOSED = 0;
    public static final int BODYWORK_STATE_LOCKED = 0;
    public static final int BODYWORK_STATE_OPEN = 1;
    public static final int BODYWORK_STATE_UNDEFINED = 255;
    public static final int BODYWORK_STATE_UNLOCK = 1;
    public static final double BODYWORK_STEERING_WHEEL_ANGEL_MAX = 780.0d;
    public static final double BODYWORK_STEERING_WHEEL_ANGEL_MIN = -780.0d;
    public static final double BODYWORK_STEERING_WHEEL_SPEED_MAX = 1016.0d;
    public static final double BODYWORK_STEERING_WHEEL_SPEED_MIN = 0.0d;
    public static final int CHARGING_CAP_LEFT = 3;
    public static final int CHARGING_CAP_RIGHT = 4;
    public static final int CLOSE_WINDOW_FOR_RAIN_INVALID = 0;
    public static final int CLOSE_WINDOW_FOR_RAIN_OFF = 2;
    public static final int CLOSE_WINDOW_FOR_RAIN_ON = 1;
    public static final int COMMAND_AIR = 11;
    public static final int COMMAND_AIR_SCREEN = 13;
    public static final int COMMAND_ATMOSPHERE_LIGHT = 6;
    public static final int COMMAND_DOOR_LOCK = 8;
    public static final int COMMAND_GEAR = 4;
    public static final int COMMAND_HAND_SCREEN = 12;
    public static final int COMMAND_HUD = 15;
    public static final int COMMAND_INDOOR_LIGHT = 5;
    public static final int COMMAND_INSTRUMENT = 14;
    public static final int COMMAND_OUTDOOR_LIGHT = 7;
    public static final int COMMAND_REAR_LOCK = 2;
    public static final int COMMAND_SEAT = 10;
    public static final int COMMAND_START_BUTTON = 0;
    public static final int COMMAND_SUNROOF_SUNSHADE = 1;
    public static final int COMMAND_WINDOW = 3;
    public static final int COMMAND_WIPER = 9;
    public static final int CONFIG_ANTI_PINCH_MOON_ROOF = 3;
    public static final int CONFIG_MOON_ROOF_SUNSHADE_PANEL = 1;
    public static final int CONFIG_NONE = 0;
    public static final int CONFIG_SUNSHADE_PANEL = 2;
    public static final int DATA_FLAG_INVALID = 0;
    public static final int DATA_FLAG_VALID = 1;
    public static final int DATA_STWHEEL_SENSOR_CALIBRATION_STATE = 1;
    public static final int DATA_STWHEEL_SENSOR_STATE = 0;
    public static final int DEVICE_HAS_THE_FEATURE = 1;
    public static final int DEVICE_NOT_HAS_THE_FEATURE = 0;
    public static final int DEVICE_THE_FEATURE_LINK_ERROR = 65535;
    public static final int DEVICE_THE_FEATURE_NEVER_GET = 2;
    public static final int DOOR_LOCK_AREA_LEFT_REAR = 2;
    public static final int DOOR_LOCK_AREA_RIGHT_FRONT = 3;
    public static final int DOOR_LOCK_AREA_RIGHT_REAR = 4;
    public static final int DRIVER_SEAT = 1;
    public static final int ELEC_SLIDE_DOOR_STATE_CLOSE = 2;
    public static final int ELEC_SLIDE_DOOR_STATE_INVALID = 0;
    public static final int ELEC_SLIDE_DOOR_STATE_OPEN = 1;
    public static final int ENERGY_TYPE_EV = 1;
    public static final int ENERGY_TYPE_FUEL = 3;
    public static final int ENERGY_TYPE_HEV = 2;
    public static final String FEATURE_MOON_ROOF = "MoonRoof";
    public static final String FEATURE_RAIN_CLOSE_WINDOW = "RainAutoCloseWindow";
    public static final int HAS_0X005500 = 24;
    public static final int HAS_0X00D500 = 33;
    public static final int HAS_0X010D00 = 49;
    public static final int HAS_0X012100 = 110;
    public static final int HAS_0X012300 = 18;
    public static final int HAS_0X012D00 = 111;
    public static final int HAS_0X012F00 = 5;
    public static final int HAS_0X013300 = 42;
    public static final int HAS_0X013800 = 112;
    public static final int HAS_0X013B00 = 4;
    public static final int HAS_0X013F00 = 54;
    public static final int HAS_0X014400 = 72;
    public static final int HAS_0X015100 = 69;
    public static final int HAS_0X016700 = 113;
    public static final int HAS_0X018A00 = 114;
    public static final int HAS_0X019F00 = 115;
    public static final int HAS_0X01C000 = 55;
    public static final int HAS_0X01D100 = 48;
    public static final int HAS_0X021800 = 43;
    public static final int HAS_0X022300 = 116;
    public static final int HAS_0X023300 = 117;
    public static final int HAS_0X0234A00 = 201;
    public static final int HAS_0X023500 = 118;
    public static final int HAS_0X023A00 = 119;
    public static final int HAS_0X024200 = 120;
    public static final int HAS_0X024500 = 28;
    public static final int HAS_0X024800 = 121;
    public static final int HAS_0X024A00 = 53;
    public static final int HAS_0X024C00 = 17;
    public static final int HAS_0X025700 = 218;
    public static final int HAS_0X025800 = 213;
    public static final int HAS_0X025D00 = 73;
    public static final int HAS_0X026100 = 122;
    public static final int HAS_0X026F00 = 74;
    public static final int HAS_0X027100 = 123;
    public static final int HAS_0X027200 = 124;
    public static final int HAS_0X027500 = 125;
    public static final int HAS_0X027A00 = 126;
    public static final int HAS_0X028700 = 127;
    public static final int HAS_0X028800 = 128;
    public static final int HAS_0X028F00 = 129;
    public static final int HAS_0X029400 = 56;
    public static final int HAS_0X029600 = 130;
    public static final int HAS_0X029C00 = 131;
    public static final int HAS_0X029E00 = 132;
    public static final int HAS_0X02A400 = 133;
    public static final int HAS_0X02A600 = 134;
    public static final int HAS_0X02A700 = 135;
    public static final int HAS_0X02B000 = 136;
    public static final int HAS_0X02B400 = 137;
    public static final int HAS_0X02B600 = 138;
    public static final int HAS_0X02BA00 = 139;
    public static final int HAS_0X02C000 = 140;
    public static final int HAS_0X02D500 = 141;
    public static final int HAS_0X02EB00 = 142;
    public static final int HAS_0X02F000 = 143;
    public static final int HAS_0X030D00 = 46;
    public static final int HAS_0X031900 = 212;
    public static final int HAS_0X031E00 = 144;
    public static final int HAS_0X032000 = 145;
    public static final int HAS_0X032100 = 19;
    public static final int HAS_0X032300 = 146;
    public static final int HAS_0X032D00 = 147;
    public static final int HAS_0X033400 = 51;
    public static final int HAS_0X033700 = 148;
    public static final int HAS_0X034100 = 149;
    public static final int HAS_0X034200 = 52;
    public static final int HAS_0X034400 = 208;
    public static final int HAS_0X034600 = 150;
    public static final int HAS_0X034700 = 151;
    public static final int HAS_0X034F00 = 20;
    public static final int HAS_0X035100 = 215;
    public static final int HAS_0X036B00 = 152;
    public static final int HAS_0X036D00 = 153;
    public static final int HAS_0X036E00 = 154;
    public static final int HAS_0X037F00 = 155;
    public static final int HAS_0X038A00 = 57;
    public static final int HAS_0X038B00 = 156;
    public static final int HAS_0X039400 = 44;
    public static final int HAS_0X039600 = 6;
    public static final int HAS_0X03A000 = 157;
    public static final int HAS_0X03A700 = 158;
    public static final int HAS_0X03AC00 = 37;
    public static final int HAS_0X03AD00 = 38;
    public static final int HAS_0X03B000 = 159;
    public static final int HAS_0X03B300 = 50;
    public static final int HAS_0X03B400 = 29;
    public static final int HAS_0X03BA00 = 160;
    public static final int HAS_0X03BB00 = 161;
    public static final int HAS_0X03BD00 = 162;
    public static final int HAS_0X03C100 = 41;
    public static final int HAS_0X03C800 = 163;
    public static final int HAS_0X03CA00 = 164;
    public static final int HAS_0X03CD00 = 47;
    public static final int HAS_0X03D901 = 80;
    public static final int HAS_0X03D902 = 81;
    public static final int HAS_0X03D903 = 82;
    public static final int HAS_0X03D904 = 83;
    public static final int HAS_0X03D905 = 84;
    public static final int HAS_0X03D906 = 85;
    public static final int HAS_0X03D911 = 86;
    public static final int HAS_0X03D955 = 34;
    public static final int HAS_0X03D956 = 87;
    public static final int HAS_0X03D958 = 88;
    public static final int HAS_0X03D959 = 89;
    public static final int HAS_0X03D95A = 90;
    public static final int HAS_0X03D95B = 91;
    public static final int HAS_0X03D95C = 92;
    public static final int HAS_0X03D95D = 93;
    public static final int HAS_0X03D9AA = 94;
    public static final int HAS_0X03DA00 = 166;
    public static final int HAS_0X03DF00 = 167;
    public static final int HAS_0X03F100 = 40;
    public static final int HAS_0X03F300 = 168;
    public static final int HAS_0X03FE00 = 169;
    public static final int HAS_0X03FF00 = 8;
    public static final int HAS_0X040100 = 58;
    public static final int HAS_0X040200 = 170;
    public static final int HAS_0X040400 = 59;
    public static final int HAS_0X040700 = 60;
    public static final int HAS_0X040800 = 39;
    public static final int HAS_0X040D00 = 1;
    public static final int HAS_0X041700 = 26;
    public static final int HAS_0X041800 = 11;
    public static final int HAS_0X041A00 = 61;
    public static final int HAS_0X041C00 = 62;
    public static final int HAS_0X041E00 = 30;
    public static final int HAS_0X041F00 = 171;
    public static final int HAS_0X042900 = 22;
    public static final int HAS_0X042E00 = 63;
    public static final int HAS_0X042F00 = 172;
    public static final int HAS_0X043200 = 10;
    public static final int HAS_0X043300 = 3;
    public static final int HAS_0X043400 = 64;
    public static final int HAS_0X043600 = 173;
    public static final int HAS_0X043700 = 174;
    public static final int HAS_0X043800 = 32;
    public static final int HAS_0X043E00 = 175;
    public static final int HAS_0X043FD1 = 176;
    public static final int HAS_0X044300 = 177;
    public static final int HAS_0X044400 = 202;
    public static final int HAS_0X044700 = 106;
    public static final int HAS_0X044900 = 178;
    public static final int HAS_0X045300 = 179;
    public static final int HAS_0X045400 = 180;
    public static final int HAS_0X046100 = 181;
    public static final int HAS_0X046C00 = 70;
    public static final int HAS_0X047000 = 95;
    public static final int HAS_0X047500 = 15;
    public static final int HAS_0X048004 = 35;
    public static final int HAS_0X048100 = 36;
    public static final int HAS_0X048600 = 65;
    public static final int HAS_0X048700 = 182;
    public static final int HAS_0X048800 = 183;
    public static final int HAS_0X048900 = 184;
    public static final int HAS_0X048A00 = 185;
    public static final int HAS_0X048E00 = 66;
    public static final int HAS_0X048F00 = 9;
    public static final int HAS_0X049400 = 67;
    public static final int HAS_0X049C00 = 27;
    public static final int HAS_0X04A100 = 186;
    public static final int HAS_0X04A401 = 187;
    public static final int HAS_0X04A402 = 188;
    public static final int HAS_0X04A501 = 96;
    public static final int HAS_0X04A502 = 97;
    public static final int HAS_0X04A503 = 98;
    public static final int HAS_0X04A504 = 99;
    public static final int HAS_0X04A505 = 100;
    public static final int HAS_0X04A506 = 71;
    public static final int HAS_0X04A507 = 101;
    public static final int HAS_0X04A508 = 102;
    public static final int HAS_0X04A509 = 103;
    public static final int HAS_0X04A50A = 104;
    public static final int HAS_0X04A50B = 105;
    public static final int HAS_0X04A710 = 198;
    public static final int HAS_0X04B000 = 189;
    public static final int HAS_0X04B100 = 190;
    public static final int HAS_0X04B200 = 191;
    public static final int HAS_0X04B300 = 192;
    public static final int HAS_0X04B600 = 7;
    public static final int HAS_0X04B800 = 45;
    public static final int HAS_0X04BB00 = 193;
    public static final int HAS_0X04BD08 = 194;
    public static final int HAS_0X04BF00 = 25;
    public static final int HAS_0X04C201 = 12;
    public static final int HAS_0X04C202 = 13;
    public static final int HAS_0X04C203 = 14;
    public static final int HAS_0X04C800 = 31;
    public static final int HAS_0X04D902 = 21;
    public static final int HAS_0X04E501 = 195;
    public static final int HAS_0X04E800 = 216;
    public static final int HAS_0X04E900 = 217;
    public static final int HAS_0X04EDFF = 196;
    public static final int HAS_0X04F401 = 197;
    public static final int HAS_0X04F600 = 2;
    public static final int HAS_0X04FA00 = 23;
    public static final int HAS_0X04FB00 = 16;
    public static final int HAS_0X04FD00 = 199;
    public static final int HAS_0X04FE00 = 200;
    public static final int HAS_0X24F200 = 203;
    public static final int HAS_0X24F701 = 205;
    public static final int HAS_0x014500 = 224;
    public static final int HAS_0x02BC00 = 206;
    public static final int HAS_0x033800 = 223;
    public static final int HAS_0x038F00 = 221;
    public static final int HAS_0x03D600 = 220;
    public static final int HAS_0x046400 = 222;
    public static final int HAS_0x048C00 = 219;
    public static final int HAS_0x04E600 = 207;
    public static final int LEFT_FRONT_DOOR = 1;
    public static final int LEFT_FRONT_INTERNAL_DOOR_HANDLE = 1;
    public static final int LEFT_REAR_DOOR = 3;
    public static final int LEFT_REAR_INTERNAL_DOOR_HANDLE = 3;
    public static final int LEFT_SLIDE_DOOR_ACTION_CLOSE = 3;
    public static final int LEFT_SLIDE_DOOR_ACTION_INVALID = 0;
    public static final int LEFT_SLIDE_DOOR_ACTION_OPEN = 2;
    public static final int LEFT_SLIDE_DOOR_ACTION_STOP = 1;
    public static final int LEFT_SLIDE_DOOR_STATE_FULL_CLOSE = 0;
    public static final int LEFT_SLIDE_DOOR_STATE_FULL_OPEN = 2;
    public static final int LEFT_SLIDE_DOOR_STATE_HALF_OPEN = 1;
    public static final int LEFT_SLIDE_DOOR_STATE_INVALID = 3;
    public static final int LEFT_STRUT_MOROT_STATE_CLOSE = 2;
    public static final int LEFT_STRUT_MOROT_STATE_INVALID = 0;
    public static final int LEFT_STRUT_MOROT_STATE_OPEN = 1;
    public static final int LIGHT_FOOT = 8;
    public static final int LIGHT_FRONT_FOG = 6;
    public static final int LIGHT_HIGH_BEAM = 3;
    public static final int LIGHT_LEFT_TURN_SIGNAL = 4;
    public static final int LIGHT_LOW_BEAM = 2;
    public static final int LIGHT_REAR_FOG = 7;
    public static final int LIGHT_REAR_POSITION = 9;
    public static final int LIGHT_RIGHT_TURN_SIGNAL = 5;
    public static final int LIGHT_SIDE = 1;
    public static final double MAX_BATTERY_POWER = 25.5d;
    public static final int MCU_RESTART_INVALID = 0;
    public static final int MCU_RESTART_VALID = 1;
    public static final int MESSAGE_55 = 1;
    public static final int MESSAGE_OFFLINE = 0;
    public static final int MESSAGE_ONLINE = 1;
    public static final double MIN_BATTERY_POWER = 0.0d;
    public static final int MOONROOF_BREATH = 253;
    public static final int MOONROOF_CLOSED = 0;
    public static final int MOONROOF_COMFORTABLE = 252;
    public static final int MOONROOF_INVALID = 255;
    public static final int MOONROOF_MIN = 21;
    public static final int MOONROOF_OPEN = 100;
    public static final int MOONROOF_STOP = 254;
    public static final int ORIENTATION_LEFT = 1;
    public static final int ORIENTATION_RIGHT = 2;
    public static final String PERMISSION_BYDAUTO_BODYWORK_COMMON = "android.permission.BYDAUTO_BODYWORK_COMMON";
    public static final String PERMISSION_BYDAUTO_BODYWORK_GET = "android.permission.BYDAUTO_BODYWORK_GET";
    public static final String PERMISSION_BYDAUTO_BODYWORK_SET = "android.permission.BYDAUTO_BODYWORK_SET";
    public static final int POSITION_THTEE = 3;
    public static final int REAR_WINDOW_BLIND_POSITION_FULL_CLOSE = 2;
    public static final int REAR_WINDOW_BLIND_POSITION_FULL_OPEN = 1;
    public static final int REAR_WINDOW_BLIND_POSITION_HALF_OPEN = 3;
    public static final int REAR_WINDOW_BLIND_POSITION_INVALID = 0;
    public static final int REAR_WINDOW_BLIND_POSITION_STOP = 4;
    public static final int REAR_WINDOW_BLIND_STATE_ANTI_FLIP = 4;
    public static final int REAR_WINDOW_BLIND_STATE_CLOSE = 3;
    public static final int REAR_WINDOW_BLIND_STATE_INVALID = 0;
    public static final int REAR_WINDOW_BLIND_STATE_OPEN = 2;
    public static final int REAR_WINDOW_BLIND_STATE_STOP = 1;
    public static final int RIGHT_FRONT_DOOR = 2;
    public static final int RIGHT_FRONT_INTERNAL_DOOR_HANDLE = 2;
    public static final int RIGHT_REAR_DOOR = 4;
    public static final int RIGHT_REAR_INTERNAL_DOOR_HANDLE = 4;
    public static final int RIGHT_SLIDE_DOOR_ACTION_CLOSE = 3;
    public static final int RIGHT_SLIDE_DOOR_ACTION_INVALID = 0;
    public static final int RIGHT_SLIDE_DOOR_ACTION_OPEN = 2;
    public static final int RIGHT_SLIDE_DOOR_ACTION_STOP = 1;
    public static final int RIGHT_SLIDE_DOOR_STATE_FULL_CLOSE = 0;
    public static final int RIGHT_SLIDE_DOOR_STATE_FULL_OPEN = 2;
    public static final int RIGHT_SLIDE_DOOR_STATE_HALF_OPEN = 1;
    public static final int RIGHT_SLIDE_DOOR_STATE_INVALID = 3;
    public static final int RIGHT_STRUT_MOROT_STATE_CLOSE = 2;
    public static final int RIGHT_STRUT_MOROT_STATE_INVALID = 0;
    public static final int RIGHT_STRUT_MOROT_STATE_OPEN = 1;
    public static final int SMART_VOICE_LIMIT_INVALID = 0;
    public static final int SMART_VOICE_LIMIT_VALID = 1;
    public static final int STATE_INITIALIZED = 1;
    public static final int STATE_INVALID = 0;
    public static final int STATE_UNINITIALIZED = 2;
    public static final int SUNROOF_CLOSE = 4;
    public static final int SUNROOF_CLOSE_NOTICE_CLOSE = 1;
    public static final int SUNROOF_CLOSE_NOTICE_INVALID = 0;
    public static final int SUNROOF_INVALID = 0;
    public static final int SUNROOF_OPEN = 3;
    public static final int SUNROOF_POSITION_COMFORTABLE = 6;
    public static final int SUNROOF_POSITION_FULL_CLOSE = 2;
    public static final int SUNROOF_POSITION_FULL_OPEN = 1;
    public static final int SUNROOF_POSITION_HALF_OPEN = 3;
    public static final int SUNROOF_POSITION_INVALID = 0;
    public static final int SUNROOF_POSITION_STOP = 4;
    public static final int SUNROOF_POSITION_UPDIP = 5;
    public static final int SUNROOF_STOP = 1;
    public static final int SUNROOF_TILTUP = 2;
    public static final int SUNSHADE_INVALID = 255;
    public static final int SUNSHADE_STOP = 254;
    public static final String TAG = "BYDAutoBodyworkDevice";
    public static final int VOICE_CMD_CLOSE = 2;
    public static final int VOICE_CMD_HALF_OPEN = 3;
    public static final int VOICE_CMD_OPEN = 1;
    public static final int VOICE_CMD_STOP = 4;
    public static final int VOICE_CMD_VENTILATE = 5;
    public static final int WINDOW_BLIND_STATE_ANTI_FLIP = 4;
    public static final int WINDOW_BLIND_STATE_CLOSE = 3;
    public static final int WINDOW_BLIND_STATE_INVALID = 0;
    public static final int WINDOW_BLIND_STATE_OPEN = 2;
    public static final int WINDOW_BLIND_STATE_STOP = 1;
    public static final int WINDOW_BREATH = 5;
    public static final int WINDOW_CLOSE = 2;
    public static final int WINDOW_DISABLE = 1;
    public static final int WINDOW_ENABLE = 0;
    public static final int WINDOW_INVALID = 0;
    public static final int WINDOW_OPEN_FULL = 1;
    public static final int WINDOW_OPEN_HALF = 4;
    public static final int WINDOW_OPEN_PERCENT_MAX = 100;
    public static final int WINDOW_OPEN_PERCENT_MIN = 0;
    public static final int WINDOW_STOP = 3;
    public static int mSensorCalFlag = -1;
    public static int mSensorFlag = -1;
    private static BYDAutoBodyworkDevice sDevice;
    private final BodyworkManagerImpl mBodyworkManagerImpl;

    public BYDAutoBodyworkDevice(Context context) {
        super(context.getApplicationContext());
        this.mBodyworkManagerImpl = BodyworkManagerImpl.getInstance(context.getApplicationContext());
    }

    public static synchronized BYDAutoBodyworkDevice getInstance(Context context) {
        if (sDevice == null) {
            sDevice = new BYDAutoBodyworkDevice(context);
        }
        return sDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.mBodyworkManagerImpl.get(iArr, cls);
    }

    public int getAlarmState() {
        return this.mBodyworkManagerImpl.getAlarmState();
    }

    public void getAllStatus() {
        throw new NullPointerException("Stub!");
    }

    public int getAntiTheftStatus() {
        return this.mBodyworkManagerImpl.getAntiTheftStatus();
    }

    public int getAutoExternalRearMirrorFoldActionState() {
        return this.mBodyworkManagerImpl.getAutoExternalRearMirrorFoldActionState();
    }

    public int getAutoExternalRearMirrorFoldedState() {
        return this.mBodyworkManagerImpl.getAutoExternalRearMirrorFoldedState();
    }

    public int getAutoModelName() {
        return this.mBodyworkManagerImpl.getAutoModelName();
    }

    public int getAutoSystemState() {
        return this.mBodyworkManagerImpl.getAutoSystemState();
    }

    public int getAutoType() {
        return this.mBodyworkManagerImpl.getAutoType();
    }

    public String getAutoVIN() {
        return this.mBodyworkManagerImpl.getAutoVIN();
    }

    public int getAwningConfigutation() {
        return this.mBodyworkManagerImpl.getAwningConfigutation();
    }

    public int getBCMVechileStatus() {
        return this.mBodyworkManagerImpl.getBcmVechileStatus();
    }

    public int getBackDoorAntiPlayMode() {
        return this.mBodyworkManagerImpl.getBackDoorAntiPlayMode();
    }

    public int getBackDoorCurState() {
        return this.mBodyworkManagerImpl.getBackDoorCurState();
    }

    public int getBackDoorInitState() {
        return this.mBodyworkManagerImpl.getBackDoorInitState();
    }

    public int getBackDoorPosition() {
        return this.mBodyworkManagerImpl.getBackDoorPosition();
    }

    public int getBatteryCapacity() {
        throw new NullPointerException("Stub!");
    }

    public double getBatteryPowerHEV() {
        throw new NullPointerException("Stub!");
    }

    @Deprecated
    public int getBatteryPowerValue() {
        throw new NullPointerException("Stub!");
    }

    public int getBatteryVoltageLevel() {
        return this.mBodyworkManagerImpl.getBatteryVoltageLevel();
    }

    public int getCarWindowAntiPinchConfig() {
        throw new NullPointerException("Stub!");
    }

    public int getDataFlag(int i) {
        throw new NullPointerException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        throw new NullPointerException("Stub!");
    }

    public int getDoorLockStatus(int i) {
        return this.mBodyworkManagerImpl.getDoorLockStatus(i);
    }

    public int getDoorState(int i) {
        return this.mBodyworkManagerImpl.getDoorState(i);
    }

    public int getElecSlideDoorState() {
        return this.mBodyworkManagerImpl.getElecSlideDoorState();
    }

    public int getElectricFrontHatchConfiguration() {
        return this.mBodyworkManagerImpl.getElectricFrontHatchConfiguration();
    }

    public int getElectricPedalConfig() {
        return this.mBodyworkManagerImpl.getElectricPedalConfig();
    }

    public int getEnergyType() {
        return this.mBodyworkManagerImpl.getEnergyType();
    }

    public int getEvReadyIndicatorLightState() {
        return this.mBodyworkManagerImpl.getEvReadyIndicatorLightState();
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        throw new NullPointerException("Stub!");
    }

    public int getFourPeopleEnjoyConfig() {
        return this.mBodyworkManagerImpl.getFourPeopleEnjoyConfig();
    }

    public int getFuelElecLowPower() {
        return this.mBodyworkManagerImpl.getFuelElecLowPower();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_BODYWORK_GET;
    }

    public int getInternalDoorHandleState(int i) {
        return this.mBodyworkManagerImpl.getInternalDoorHandleState(i);
    }

    public int getInternalDoorHandleStateByRearBcm(int i) {
        return this.mBodyworkManagerImpl.getInternalDoorHandleStateByRearBcm(i);
    }

    public int getLeftSlideDoorAction() {
        return this.mBodyworkManagerImpl.getLeftSlideDoorAction();
    }

    public int getLeftSlideDoorPercent() {
        return this.mBodyworkManagerImpl.getLeftSlideDoorPercent();
    }

    public int getLeftSlideDoorState() {
        return this.mBodyworkManagerImpl.getLeftSlideDoorState();
    }

    public int getLeftStrutMorotRunningState() {
        return this.mBodyworkManagerImpl.getLeftStrutMorotRunningState();
    }

    public int getLightStatus(int i) {
        return this.mBodyworkManagerImpl.getLightStatus(i);
    }

    public int getMain2SaveOrRecoverState() {
        return this.mBodyworkManagerImpl.getMain2SaveOrRecoverState();
    }

    public int getMainSaveOrRecoverState() {
        return this.mBodyworkManagerImpl.getMainSaveOrRecoverState();
    }

    public int getMessage5sOnlineState(int i) {
        throw new NullPointerException("Stub!");
    }

    public int getMoonRoofConfig() {
        return this.mBodyworkManagerImpl.getMoonRoofConfig();
    }

    public int getMoonRoofPercentControlFlag() {
        return this.mBodyworkManagerImpl.getMoonRoofPercentControlFlag();
    }

    public int getMoonRoofType() {
        return this.mBodyworkManagerImpl.getMoonRoofType();
    }

    public int getPowerDayMode() {
        throw new NullPointerException("Stub!");
    }

    public int getPowerLevel() {
        return this.mBodyworkManagerImpl.getPowerLevel();
    }

    public int getRainCloseWindow() {
        throw new NullPointerException("Stub!");
    }

    public int getReadyIndicatorLightState() {
        return this.mBodyworkManagerImpl.getReadyIndicatorLightState();
    }

    public String getRealAutoVIN() {
        return this.mBodyworkManagerImpl.getRealAutoVIN();
    }

    public int getRearLampLightState() {
        return this.mBodyworkManagerImpl.getRearLampLightState();
    }

    public int getRearWindowblindInitState() {
        return this.mBodyworkManagerImpl.getRearWindowblindInitState();
    }

    public int getRearWindowblindPosition() {
        return this.mBodyworkManagerImpl.getRearWindowblindPosition();
    }

    public int getRearWindowblindState() {
        return this.mBodyworkManagerImpl.getRearWindowblindState();
    }

    public int getRearviewMirrorSupport() {
        return this.mBodyworkManagerImpl.getRearviewMirrorSupport();
    }

    public float getRearviewMirrorX(int i) {
        return this.mBodyworkManagerImpl.getRearviewMirrorX(i);
    }

    public float getRearviewMirrorY(int i) {
        return this.mBodyworkManagerImpl.getRearviewMirrorY(i);
    }

    public int getRightSlideDoorAction() {
        return this.mBodyworkManagerImpl.getRightSlideDoorAction();
    }

    public int getRightSlideDoorPercent() {
        return this.mBodyworkManagerImpl.getRightSlideDoorPercent();
    }

    public int getRightSlideDoorState() {
        return this.mBodyworkManagerImpl.getRightSlideDoorState();
    }

    public int getRightStrutMorotRunningState() {
        return this.mBodyworkManagerImpl.getRightStrutMorotRunningState();
    }

    public int getSeatLumbarSupportConfig(int i) {
        return this.mBodyworkManagerImpl.getSeatLumbarSupportConfig(i);
    }

    public int getSeatModeWakeupFeedbackState() {
        return this.mBodyworkManagerImpl.getSeatModeWakeupFeedbackState();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_BODYWORK_SET;
    }

    public int getShiftMode() {
        return this.mBodyworkManagerImpl.getShiftMode();
    }

    public int getSmartVoiceLimit() {
        return this.mBodyworkManagerImpl.getSmartVoiceLimit();
    }

    public double getSteeringWheelValue(int i) {
        return this.mBodyworkManagerImpl.getSteeringWheelValue(i);
    }

    public float getSteeringWheelY() {
        return this.mBodyworkManagerImpl.getSteeringWheelY();
    }

    public float getSteeringWheelZ() {
        return this.mBodyworkManagerImpl.getSteeringWheelZ();
    }

    public int getSunroofCloseNotice() {
        return this.mBodyworkManagerImpl.getSunroofCloseNotice();
    }

    public int getSunroofInitState() {
        return this.mBodyworkManagerImpl.getSunroofInitState();
    }

    public int getSunroofPosition() {
        return this.mBodyworkManagerImpl.getSunroofPosition();
    }

    public int getSunroofState() {
        return this.mBodyworkManagerImpl.getSunroofState();
    }

    public int getSunroofWindowblindPosition() {
        return this.mBodyworkManagerImpl.getSunroofWindowblindPosition();
    }

    public int getSystemConfig() {
        return this.mBodyworkManagerImpl.getSystemConfig();
    }

    public int getTailDoorTypeConfig() {
        return this.mBodyworkManagerImpl.getTailDoorTypeConfig();
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        throw new NullPointerException("Stub!");
    }

    public int getWindoblindInitState() {
        throw new NullPointerException("Stub!");
    }

    public int getWindowAntiPinchConfig() {
        return this.mBodyworkManagerImpl.getWindowAntiPinchConfig();
    }

    public int getWindowControlState(int i) {
        return this.mBodyworkManagerImpl.getWindowControlState(i);
    }

    public int getWindowInitState(int i) {
        return this.mBodyworkManagerImpl.getWindowInitState(i);
    }

    public int getWindowInitStateLeftFront() {
        return this.mBodyworkManagerImpl.getWindowInitStateLeftFront();
    }

    public int getWindowInitStateLeftRear() {
        return this.mBodyworkManagerImpl.getWindowInitStateLeftRear();
    }

    public int getWindowInitStateRightFront() {
        return this.mBodyworkManagerImpl.getWindowInitStateRightFront();
    }

    public int getWindowInitStateRightRear() {
        return this.mBodyworkManagerImpl.getWindowInitStateRightRear();
    }

    public int getWindowLockState(int i) {
        return this.mBodyworkManagerImpl.getWindowLockState(i);
    }

    public int getWindowOpenPercent(int i) {
        return this.mBodyworkManagerImpl.getWindowOpenPercent(i);
    }

    public int getWindowPercentageControlConfig() {
        return this.mBodyworkManagerImpl.getWindowPercentageControlConfig();
    }

    public int getWindowPermitState() {
        return this.mBodyworkManagerImpl.getWindowPermitState();
    }

    public int getWindowState(int i) {
        return this.mBodyworkManagerImpl.getWindowState(i);
    }

    public int getWindowThermalProtectionState(int i) {
        return this.mBodyworkManagerImpl.getWindowThermalProtectionState(i);
    }

    public int getWindowThermalProtectionStateLeftFront() {
        return this.mBodyworkManagerImpl.getWindowThermalProtectionStateLeftFront();
    }

    public int getWindowThermalProtectionStateLeftRear() {
        return this.mBodyworkManagerImpl.getWindowThermalProtectionStateLeftRear();
    }

    public int getWindowThermalProtectionStateMoonRoof() {
        return this.mBodyworkManagerImpl.getWindowThermalProtectionStateMoonRoof();
    }

    public int getWindowThermalProtectionStatePanel() {
        return this.mBodyworkManagerImpl.getWindowThermalProtectionStatePanel();
    }

    public int getWindowThermalProtectionStateRearPanel() {
        return this.mBodyworkManagerImpl.getWindowThermalProtectionStateRearPanel();
    }

    public int getWindowThermalProtectionStateRightFront() {
        return this.mBodyworkManagerImpl.getWindowThermalProtectionStateRightFront();
    }

    public int getWindowThermalProtectionStateRightRear() {
        return this.mBodyworkManagerImpl.getWindowThermalProtectionStateRightRear();
    }

    public int getWindowblindInitState() {
        return this.mBodyworkManagerImpl.getWindowblindInitState();
    }

    public int getWindowblindState() {
        return this.mBodyworkManagerImpl.getWindowblindState();
    }

    public int hasFeature(String str) {
        throw new NullPointerException("Stub!");
    }

    public int hasMessage(int i) {
        return this.mBodyworkManagerImpl.hasMessage(i);
    }

    public boolean isConnectSuccess() {
        return this.mBodyworkManagerImpl.isConnectSuccess();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int i, int i2, float f, Object obj) {
        throw new NullPointerException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int i, int i2, int i3, Object obj) {
        throw new NullPointerException("Stub!");
    }

    public int queryAutoVIN() {
        throw new NullPointerException("Stub!");
    }

    public void registerListener(AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener) {
        this.mBodyworkManagerImpl.registerListener(absBYDAutoBodyworkListener);
    }

    public void registerListener(AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener, int[] iArr) {
        this.mBodyworkManagerImpl.registerListener(absBYDAutoBodyworkListener, iArr);
    }

    public void remoteWhistle() {
        this.mBodyworkManagerImpl.remoteWhistle();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        return this.mBodyworkManagerImpl.set(iArr, bYDAutoEventValue);
    }

    public int setAllDoorsLock(int i) {
        return this.mBodyworkManagerImpl.setAllDoorsLock(i);
    }

    public int setAllDoorsLockScene(int i) {
        return this.mBodyworkManagerImpl.setAllDoorsLockScene(i);
    }

    public void setAllStatus() {
        throw new NullPointerException("Stub!");
    }

    public int setAllWindowState(int i, int i2, int i3, int i4) {
        throw new NullPointerException("Stub!");
    }

    public int setAntiTheftStatus(int i) {
        return this.mBodyworkManagerImpl.setAntiTheftStatus(i);
    }

    public int setAutoExternalRearMirrorFoldState(int i) {
        return this.mBodyworkManagerImpl.setAutoExternalRearMirrorFoldState(i);
    }

    public int setBackDoorHeightPosition(int i) {
        return this.mBodyworkManagerImpl.setBackDoorHeightPositionMyCar(i);
    }

    public int setBackDoorOpenedHeight(int i) {
        return this.mBodyworkManagerImpl.setBackDoorOpenedHeight(i);
    }

    public int setBodyWindowCtrlState(int i, int i2) {
        throw new NullPointerException("Stub!");
    }

    public int setDriveDistanceShowMode(int i) {
        return this.mBodyworkManagerImpl.setDriveDistanceShowMode(i);
    }

    public int setElecBackDoorControlState(int i) {
        return this.mBodyworkManagerImpl.setElecBackDoorControlState(i);
    }

    public int setElecSlideDoorState(int i) {
        return this.mBodyworkManagerImpl.setElecSlideDoorState(i);
    }

    public int setLeftSlideDoorVoiceControlState(int i) {
        return this.mBodyworkManagerImpl.setLeftSlideDoorVoiceControlState(i);
    }

    public int setModeCommand(int[] iArr) {
        return this.mBodyworkManagerImpl.setModeCommand(iArr);
    }

    public int setMoonRoofAndSunshadeStop() {
        throw new NullPointerException("Stub!");
    }

    public int setMoonRoofState(int i) {
        throw new NullPointerException("Stub!");
    }

    public int setMoonRoofType(int i) {
        return this.mBodyworkManagerImpl.setMoonRoofType(i);
    }

    public int setRainCloseWindow(int i) {
        throw new NullPointerException("Stub!");
    }

    public int setRightSlideDoorVoiceControlState(int i) {
        return this.mBodyworkManagerImpl.setRightSlideDoorVoiceControlState(i);
    }

    public int setSeatModeControl(int i) {
        return this.mBodyworkManagerImpl.setSeatModeControl(i);
    }

    public int setSideWindowShadeState(int i) {
        return this.mBodyworkManagerImpl.setSideWindowShadeState(i);
    }

    public int setSunroofAndSunshade(int i) {
        return this.mBodyworkManagerImpl.setSunroofAndSunshade(i);
    }

    public int setSunshadeState(int i) {
        throw new NullPointerException("Stub!");
    }

    public int setWaistSupportAdjustment(int i, int i2) {
        return this.mBodyworkManagerImpl.setWaistSupportAdjustment(i, i2);
    }

    @Deprecated
    public int setWindowControlState(int i, int i2) {
        return this.mBodyworkManagerImpl.setWindowControlState(i, i2);
    }

    public int setWindowControlState(int[] iArr) {
        return this.mBodyworkManagerImpl.setWindowControlState(iArr);
    }

    public int setWindowLockState(int i, int i2) {
        return this.mBodyworkManagerImpl.setWindowLockState(i, i2);
    }

    public int setWindowOpenPercent(int i, int i2) {
        return this.mBodyworkManagerImpl.setWindowOpenPercent(i, i2);
    }

    public int setWindowsMode(int i) {
        return this.mBodyworkManagerImpl.setWindowsMode(i);
    }

    public void settingLuggage(int i) {
        this.mBodyworkManagerImpl.settingLuggage(i);
    }

    public void turnRearviewMirror(int i) {
        this.mBodyworkManagerImpl.turnRearviewMirror(i);
    }

    public void turnSteeringWheel(int i) {
        this.mBodyworkManagerImpl.turnSteeringWheel(i);
    }

    public void unregisterListener(AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener) {
        this.mBodyworkManagerImpl.unregisterListener(absBYDAutoBodyworkListener);
    }

    public int voiceCtlMoonRoof(int i) {
        return this.mBodyworkManagerImpl.setVoiceCtlMoonRoofS(i);
    }

    public int voiceCtlSunshadePanel(int i) {
        return this.mBodyworkManagerImpl.setVoiceCtlSunshadePanelS(i);
    }
}

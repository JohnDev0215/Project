package android.hardware.bydauto.setting;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.setting.tsimpl.SettingClusterManagerImpl;
import android.hardware.bydauto.setting.tsimpl.SettingManagerImpl;
import android.hardware.bydauto.setting.tsimpl.SettingQnxManagerImpl;
import android.hardware.bydauto_test.TestSettingManagerImplCallback;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoSettingDevice extends AbsBYDAutoDevice {
    public static final int ACTION_BACK = 4;
    public static final int ACTION_DOWN = 2;
    public static final int ACTION_FRONT = 3;
    public static final int ACTION_LEFT_DOWN = 4;
    public static final int ACTION_LEFT_LEFT = 1;
    public static final int ACTION_LEFT_RIGHT = 2;
    public static final int ACTION_LEFT_UP = 3;
    public static final int ACTION_NULL = 0;
    public static final int ACTION_RESERVED = 9;
    public static final int ACTION_RETRACTION = 2;
    public static final int ACTION_RIGHT_DOWN = 8;
    public static final int ACTION_RIGHT_LEFT = 5;
    public static final int ACTION_RIGHT_RIGHT = 6;
    public static final int ACTION_RIGHT_UP = 7;
    public static final int ACTION_STRETCH = 1;
    public static final int ACTION_UP = 1;
    public static final int AC_TEMPERATURE_UNIT_OC = 2;
    public static final int AC_TEMPERATURE_UNIT_OF = 1;
    public static final int AENGINE_SOUND_TYPE_COMFORTABLE = 2;
    public static final int AENGINE_SOUND_TYPE_INNERVATION = 1;
    public static final int AENGINE_SOUND_TYPE_QUIET = 3;
    public static final int AENGINE_SOUND_TYPE_RESERVED = 4;
    public static final int AIR_LIGHT_PANEL_STATE_CLOSE = 2;
    public static final int AIR_LIGHT_PANEL_STATE_FIRST_POWERON = 3;
    public static final int AIR_LIGHT_PANEL_STATE_INVALID = 0;
    public static final int AIR_LIGHT_PANEL_STATE_OPEN = 1;
    public static final int AMB_BRIGHTNESS_LEVEL_RESERVED = 7;
    public static final int AREA_BACK_ROW = 2;
    public static final int AREA_FRONT_ROW = 1;
    public static final int AREA_RESERVED = 4;
    public static final int AREA_VEHICLE = 3;
    public static final int AUTO_LOCK_TIME_30S = 1;
    public static final int AUTO_LOCK_TIME_60S = 2;
    public static final int AUTO_RAIN_WIPER_INVALID = 0;
    public static final int AUTO_RAIN_WIPER_OFF = 2;
    public static final int AUTO_RAIN_WIPER_ON = 1;
    public static final int AUTO_RAIN_WIPER_RESERVED = 3;
    public static final int AUTO_REPORT_0 = 0;
    public static final int AUTO_REPORT_1 = 1;
    public static final int AUTO_REPORT_10 = 10;
    public static final int AUTO_REPORT_2 = 2;
    public static final int AUTO_REPORT_3 = 3;
    public static final int AUTO_REPORT_4 = 4;
    public static final int AUTO_REPORT_5 = 5;
    public static final int AUTO_REPORT_6 = 6;
    public static final int AUTO_REPORT_7 = 7;
    public static final int AUTO_REPORT_8 = 8;
    public static final int AUTO_REPORT_9 = 9;
    public static final int AUTO_REPORT_SOUND_TEACHNOLOGY = 2;
    public static final int AUTO_REPORT_TIPS_SOUND_STANDARD = 1;
    public static final int BACK_DOOR_ELECTRIC = 2;
    public static final int BACK_DOOR_MANUAL = 1;
    public static final int BACK_DOOR_OPENED_HEIGHT_MAX = 100;
    public static final int BACK_DOOR_OPENED_HEIGHT_MIN = 15;
    public static final int BACK_DOOR_RESERVED = 3;
    public static final int BACK_DOOR_VALID = 0;
    public static final int BACK_HOME_DELAY_LEVEL_CLOSE = 7;
    public static final int BACK_HOME_DELAY_LEVEL_FIFTH = 5;
    public static final int BACK_HOME_DELAY_LEVEL_FIRST = 1;
    public static final int BACK_HOME_DELAY_LEVEL_FOURTH = 4;
    public static final int BACK_HOME_DELAY_LEVEL_INVALID = 0;
    public static final int BACK_HOME_DELAY_LEVEL_SECOND = 2;
    public static final int BACK_HOME_DELAY_LEVEL_SIXTH = 6;
    public static final int BACK_HOME_DELAY_LEVEL_THIRD = 3;
    public static final int BODY_COLOR_BLACKSTONE_BLUE = 8;
    public static final int BODY_COLOR_CRYSTAL_WHITE = 13;
    public static final int BODY_COLOR_DELAND_BLACK = 6;
    public static final int BODY_COLOR_FRENCH_RED = 14;
    public static final int BODY_COLOR_HONEY_ORANGE = 18;
    public static final int BODY_COLOR_INVALID = 0;
    public static final int BODY_COLOR_JET_BLACK = 1;
    public static final int BODY_COLOR_MOUNTAIN_ASH = 7;
    public static final int BODY_COLOR_POOP_PINK = 20;
    public static final int BODY_COLOR_RED_EMPEROR = 3;
    public static final int BODY_COLOR_RUSTLING_GREEN = 19;
    public static final int BODY_COLOR_SILVER_SAND_BLACK = 9;
    public static final int BODY_COLOR_SILVER_TITANATE = 10;
    public static final int BODY_COLOR_SKY_BLUE = 15;
    public static final int BODY_COLOR_SMART_BLUE = 17;
    public static final int BODY_COLOR_SNOW_WHITE = 4;
    public static final int BODY_COLOR_SPARKING_BLUE = 21;
    public static final int BODY_COLOR_STRATEGIC_BLUE = 11;
    public static final int BODY_COLOR_SURFING_BLUE = 22;
    public static final int BODY_COLOR_TIANSHAN_WHITE = 5;
    public static final int BODY_COLOR_TIME_GREY = 2;
    public static final int BODY_COLOR_TITANIUM_EMPTY_GRAY = 12;
    public static final int BODY_COLOR_VIBRANT_ORANGE = 16;
    public static final int BRAKE_MODE_COMFORTABLE = 2;
    public static final int BRAKE_MODE_INVALID = 0;
    public static final int BRAKE_MODE_RESERVED = 3;
    public static final int BRAKE_MODE_STANDARD = 1;
    public static final int BT_CALL_REDUCTION_WIND_MENU_OFF = 1;
    public static final int BT_CALL_REDUCTION_WIND_MENU_ON = 2;
    public static final int BT_CALL_STATE_BE_CALLED = 2;
    public static final int BT_CALL_STATE_CALL_SOMEBODY = 1;
    public static final int BT_CALL_STATE_DEFAULT = 0;
    public static final int BT_CALL_STATE_IGNORED = 4;
    public static final int BT_CALL_STATE_OVER = 5;
    public static final int BT_CALL_STATE_TALKING = 3;
    public static final int BT_LOW_POWER_MODE_ENTER = 1;
    public static final int BT_LOW_POWER_MODE_EXIT = 2;
    public static final int BT_LOW_POWER_MODE_INVALID = 0;
    public static final int CARSEAT_STATE_EXCEPTION = 6;
    public static final int CARSEAT_STATE_RECOVER_SAVE = 7;
    public static final int CARSEAT_STATE_WHISTL = 5;
    public static final int CAR_REAR_VIEW_FIXED_ANGLE = 3;
    protected static final int CAR_REAR_VIEW_FIXED_ANGLE_HAL = 2;
    public static final int CAR_REAR_VIEW_MEMORY_ANGLE = 1;
    public static final int CAR_REAR_VIEW_OFF = 2;
    public static final int CAR_SETTING_INVALID = 0;
    public static final int CAR_SETTING_VALID = 1;
    public static final int CMD_CLOSE = 3;
    public static final int CMD_OPEN = 1;
    public static final int CMD_RESERVED = 4;
    public static final int CMD_STOP = 2;
    public static final int COURTESY_LAMP_TIME_15S = 3;
    public static final int COURTESY_LAMP_TIME_30S = 4;
    public static final int COURTESY_LAMP_TIME_7P5S = 2;
    public static final int COURTESY_LAMP_TIME_OFF = 1;
    public static final int CRUISE_NOT_PROMPT = 0;
    public static final int CRUISE_PROMPT = 1;
    public static final int DEVICE_HAS_THE_FEATURE = 1;
    public static final int DEVICE_NOT_HAS_THE_FEATURE = 0;
    public static final int DEVICE_THE_FEATURE_LINK_ERROR = 65535;
    public static final int DEVICE_THE_FEATURE_NEVER_GET = 2;
    public static final int DIMMING_CONTROLLER_INTEGRATED = 2;
    public static final int DIMMING_CONTROLLER_INVALID = 0;
    public static final int DIMMING_CONTROLLER_NOT_INTEGRATED = 1;
    public static final int DM20_SOC_TARGET_MIN = 15;
    public static final int DM25_SOC_TARGET_MIN = 25;
    public static final int DOOR_AUTO_BACK_CLOSE = 1;
    public static final int DOOR_AUTO_BACK_INVALID = 0;
    public static final int DOOR_AUTO_BACK_MIN_10 = 4;
    public static final int DOOR_AUTO_BACK_MIN_2 = 2;
    public static final int DOOR_AUTO_BACK_MIN_5 = 3;
    public static final int DOOR_AUTO_BACK_MIN_RESERVED = 5;
    public static final int DRIVER_SEAT = 1;
    public static final int DRIVE_CONFIG_EV_FOUR_WHEEL = 4;
    public static final int DRIVE_CONFIG_FOUR_WHEEL = 2;
    public static final int DRIVE_CONFIG_FRONT = 1;
    public static final int DRIVE_CONFIG_REAR = 3;
    public static final int DYNAMIC_MODE_COMFORTABLE = 1;
    public static final int DYNAMIC_MODE_INVALID = 0;
    public static final int DYNAMIC_MODE_RESERVED = 3;
    public static final int DYNAMIC_MODE_SPORT = 2;
    public static final int DYNAMIC_SUSPENSION_VISIBLE = 1;
    public static final int ENGINE_OIL_LEVEL_1P8_MIN = 2;
    public static final int ENGINE_OIL_LEVEL_2P8_MIN = 3;
    public static final int ENGINE_OIL_LEVEL_3P8_MIN = 4;
    public static final int ENGINE_OIL_LEVEL_4P8_MIN = 5;
    public static final int ENGINE_OIL_LEVEL_5P8_MIN = 6;
    public static final int ENGINE_OIL_LEVEL_6P8_MIN = 7;
    public static final int ENGINE_OIL_LEVEL_7P8_MIN = 8;
    public static final int ENGINE_OIL_LEVEL_BELOW_MIN = 0;
    public static final int ENGINE_OIL_LEVEL_CONFIG = 255;
    public static final int ENGINE_OIL_LEVEL_FAULT = 13;
    public static final int ENGINE_OIL_LEVEL_MAX = 9;
    public static final int ENGINE_OIL_LEVEL_MEASUREMENT = 11;
    public static final int ENGINE_OIL_LEVEL_MIN = 1;
    public static final int ENGINE_OIL_LEVEL_OIL_OK = 12;
    public static final int ENGINE_OIL_LEVEL_OVERFILL = 10;
    public static final int ENGINE_OIL_UPDATE_SIGNAL_NO = 0;
    public static final int ENGINE_OIL_UPDATE_SIGNAL_YES = 1;
    public static final int ENGINE_SOUND_TYPE_STANDARD = 0;
    public static final int EXHIBITION_MODE_INDOOR = 1;
    public static final int EXHIBITION_MODE_INVALID = 0;
    public static final int EXHIBITION_MODE_NON = 3;
    public static final int EXHIBITION_MODE_OUTDOOR = 2;
    public static final int EXHIBITION_START_ENGINE_FLAG_FORBID = 2;
    public static final int EXHIBITION_START_ENGINE_FLAG_INVALID = 0;
    public static final int EXHIBITION_START_ENGINE_FLAG_PERMIT = 1;
    public static final int EXHIBITION_START_ENGINE_NOLPROMPT = 1;
    public static final int EXHIBITION_START_ENGINE_NO_PROMPT = 1;
    public static final int EXHIBITION_START_ENGINE_PROMPT = 0;
    public static final int EXIT_UPDATE = 1;
    public static final int E_PEDAL_MODE_CLOSE = 2;
    public static final int E_PEDAL_MODE_OPEN = 1;
    public static final int FACTORY_RESET_DEFAULT = 0;
    public static final int FACTORY_RESET_DISABLE = 2;
    public static final int FACTORY_RESET_ENABLE = 1;
    public static final String FEATURE_AUTO_RAIN_WIPER = "AutoRainWiper";
    public static final String FEATURE_BACK_DOOR = "BackDoor";
    public static final String FEATURE_BT_CALL_REDUCTION_WIND = "BTCallReductionWind";
    public static final String FEATURE_BT_KEY = "BluetoothKey";
    public static final String FEATURE_CAR_REAR_MIRROR_FLIP_ANGEL = "CarRearMirrorFilpAngle";
    public static final String FEATURE_CMD_HEADLAMP_HEIGHT_CONFIG = "HeadlampHeightConfig";
    public static final String FEATURE_DIMMING_CONTROLLER_CONFIG = "DimmingControllerConfig";
    public static final String FEATURE_DOOR_SC10_429 = "DoorSC19";
    public static final String FEATURE_DRIVER_SEAT_HEATING = "DriverSeatHeating";
    public static final String FEATURE_DRIVER_SEAT_HEATING_LEVEL1 = "DriverSeatHeatingLevel1";
    public static final String FEATURE_DRIVER_SEAT_LEGREST_CONFIG = "DriverSeatLegrestConfig";
    public static final String FEATURE_DRIVER_SEAT_VENTILATING = "DriverSeatVentilating";
    public static final String FEATURE_DRIVING_RECORDER = "DrivingRecorder";
    public static final String FEATURE_ENERGY_DISPLAY_TYPE_ONLINE = "EnergyDisplayTypeOnline";
    public static final String FEATURE_ENGINE_OIL_DETECTION = "EngineOilDetection";
    public static final String FEATURE_E_PEDAL_MODE_STATE = "EPedalModeState";
    public static final String FEATURE_FRONT_WINDSCREEN_WIPER_OVERHAUL = "FrontWindscreenWiperOverhaul";
    public static final String FEATURE_IAL_AREA_CONFIG = "IALAreaConfig";
    public static final String FEATURE_IAL_BRIGHTNESS_CONFIG = "IALBrightnessConfig";
    public static final String FEATURE_IAL_COLOR_CINFIG = "IALColorConfig";
    public static final String FEATURE_INSIDE_LIGHT = "InsideLight";
    public static final String FEATURE_INSIDE_LIGHT_DOOR_ONLINE = "InsideLightDoorOnline";
    public static final String FEATURE_INTERIOR_ATMOSPHERE_LAMP = "InteriorAtmosphereLamp";
    public static final String FEATURE_LANTERN_LANGUAGE_MODE = "LanternLanguageMode";
    public static final String FEATURE_LEARNER_DRIVER_MODE = "LearnerDriverMode";
    public static final String FEATURE_MASSAGE_ONLINE_LB = "MassageOnlineLB";
    public static final String FEATURE_MASSAGE_ONLINE_RB = "MassageOnlineRB";
    public static final String FEATURE_OVERSPEED_LOCKING = "OverspeedLocking";
    public static final String FEATURE_PARKING_AUTO_INNER_LOOP = "ParkingAutoInnerLoop";
    public static final String FEATURE_PASSENGER_SEAT_HEATING = "PassengerSeatHeating";
    public static final String FEATURE_PASSENGER_SEAT_HEATING_LEVEL1 = "PassengerSeatHeatingLevel1";
    public static final String FEATURE_PASSENGER_SEAT_VENTILATING = "PassengerSeatVentilating";
    public static final String FEATURE_POWER_STEERING = "PowerSteering";
    public static final String FEATURE_REARVIEW_MIRROR_FOLLOW_UP = "RearviewMirrorFollowUp";
    public static final String FEATURE_REAR_AC_PANEL_LOCK_TIME = "RearACPanelAutoLockTime";
    public static final String FEATURE_REAR_LEFT_SEAT_HEATING = "RearLeftSeatHeating";
    public static final String FEATURE_REAR_LEFT_SEAT_VENTILATING = "RearLeftSeatVentilating";
    public static final String FEATURE_REAR_RIGHT_SEAT_HEATING = "RearRightSeatHeating";
    public static final String FEATURE_REAR_RIGHT_SEAT_VENTILATING = "RearRightSeatVentilating";
    public static final String FEATURE_REAR_WINDSCREEN_WIPER_OVERHAUL = "RearWindscreenWiperOverhaul";
    public static final String FEATURE_SEAT_HEATING_AND_VENTILATING = "SeatHeatingAndVentilating";
    public static final String FEATURE_SMART_WELCOME_LIGHT_STATE = "SmartWelcomeLightState";
    public static final String FEATURE_SPEED_REMINDER_ONLINE = "SpeedReminderOnline";
    public static final String FEATURE_STEERING_WHEEL_HEATING_STATE = "SteeringWheelHeatingState";
    public static final String FEATURE_TUNNEL_AUTO_INNER_LOOP = "TunnelAutoInnerLoop";
    public static final int FESTIVAL_INFO_CHRISTMAS = 9;
    public static final int FESTIVAL_INFO_DRAGON_BOAT_FESTIVAL = 6;
    public static final int FESTIVAL_INFO_INVALID = 0;
    public static final int FESTIVAL_INFO_LABOR_DAY = 5;
    public static final int FESTIVAL_INFO_LANTERN_FESTIVAL = 3;
    public static final int FESTIVAL_INFO_NATIONAL_DAY = 8;
    public static final int FESTIVAL_INFO_NEW_YEAR = 1;
    public static final int FESTIVAL_INFO_SPRING_FESTIVAL = 2;
    public static final int FESTIVAL_INFO_TEACHER_DAY = 7;
    public static final int FESTIVAL_INFO_WOMEN_DAY = 4;
    public static final int FRONT_LEFT_SEAT = 5;
    public static final int FRONT_RIGHT_SEAT = 6;
    public static final int FRONT_WINDSCREEN_WIPER = 1;
    public static final int GET_FILED_ANGLE_FIXED = 2;
    public static final int GET_FILED_CLOSE = 3;
    public static final int GET_FILED_REMEMBER = 1;
    public static final int GET_INVALID = 0;
    public static final int HANDLE_AUTO_RESET_10MIN = 4;
    public static final int HANDLE_AUTO_RESET_2MIN = 2;
    public static final int HANDLE_AUTO_RESET_5MIN = 3;
    public static final int HANDLE_AUTO_RESET_OFF = 1;
    public static final int HEADLAMP_HEIGHT_LEVEL_EIGHTH = 8;
    public static final int HEADLAMP_HEIGHT_LEVEL_FIFTH = 5;
    public static final int HEADLAMP_HEIGHT_LEVEL_FOURTH = 4;
    public static final int HEADLAMP_HEIGHT_LEVEL_INVALD = 0;
    public static final int HEADLAMP_HEIGHT_LEVEL_INVALID = 0;
    public static final int HEADLAMP_HEIGHT_LEVEL_MAX = 11;
    public static final int HEADLAMP_HEIGHT_LEVEL_MIN = 1;
    public static final int HEADLAMP_HEIGHT_LEVEL_NINETH = 9;
    public static final int HEADLAMP_HEIGHT_LEVEL_RESERVED = 11;
    public static final int HEADLAMP_HEIGHT_LEVEL_SECONDE = 2;
    public static final int HEADLAMP_HEIGHT_LEVEL_SEVENTH = 7;
    public static final int HEADLAMP_HEIGHT_LEVEL_SIXTH = 6;
    public static final int HEADLAMP_HEIGHT_LEVEL_TENTH = 10;
    public static final int HEADLAMP_HEIGHT_LEVEL_THIRD = 3;
    public static final int HEAD_UP_DISPLAY = 1;
    public static final int HUD_ANGLE_1 = 1;
    public static final int HUD_ANGLE_10 = 10;
    public static final int HUD_ANGLE_11 = 11;
    public static final int HUD_ANGLE_2 = 2;
    public static final int HUD_ANGLE_3 = 3;
    public static final int HUD_ANGLE_4 = 4;
    public static final int HUD_ANGLE_5 = 5;
    public static final int HUD_ANGLE_6 = 6;
    public static final int HUD_ANGLE_7 = 7;
    public static final int HUD_ANGLE_8 = 8;
    public static final int HUD_ANGLE_9 = 9;
    public static final int HUD_BRIGHT_LEVEL_1 = 1;
    public static final int HUD_BRIGHT_LEVEL_10 = 10;
    public static final int HUD_BRIGHT_LEVEL_11 = 11;
    public static final int HUD_BRIGHT_LEVEL_2 = 2;
    public static final int HUD_BRIGHT_LEVEL_3 = 3;
    public static final int HUD_BRIGHT_LEVEL_4 = 4;
    public static final int HUD_BRIGHT_LEVEL_5 = 5;
    public static final int HUD_BRIGHT_LEVEL_6 = 6;
    public static final int HUD_BRIGHT_LEVEL_7 = 7;
    public static final int HUD_BRIGHT_LEVEL_8 = 8;
    public static final int HUD_BRIGHT_LEVEL_9 = 9;
    public static final int HUD_HEIGHT_LEVEL_1 = 1;
    public static final int HUD_HEIGHT_LEVEL_10 = 10;
    public static final int HUD_HEIGHT_LEVEL_11 = 11;
    public static final int HUD_HEIGHT_LEVEL_12 = 12;
    public static final int HUD_HEIGHT_LEVEL_13 = 13;
    public static final int HUD_HEIGHT_LEVEL_14 = 14;
    public static final int HUD_HEIGHT_LEVEL_15 = 15;
    public static final int HUD_HEIGHT_LEVEL_16 = 16;
    public static final int HUD_HEIGHT_LEVEL_17 = 17;
    public static final int HUD_HEIGHT_LEVEL_18 = 18;
    public static final int HUD_HEIGHT_LEVEL_19 = 19;
    public static final int HUD_HEIGHT_LEVEL_2 = 2;
    public static final int HUD_HEIGHT_LEVEL_20 = 20;
    public static final int HUD_HEIGHT_LEVEL_21 = 21;
    public static final int HUD_HEIGHT_LEVEL_3 = 3;
    public static final int HUD_HEIGHT_LEVEL_4 = 4;
    public static final int HUD_HEIGHT_LEVEL_5 = 5;
    public static final int HUD_HEIGHT_LEVEL_6 = 6;
    public static final int HUD_HEIGHT_LEVEL_7 = 7;
    public static final int HUD_HEIGHT_LEVEL_8 = 8;
    public static final int HUD_HEIGHT_LEVEL_9 = 9;
    public static final int HUD_HEIGHT_LEVEL_INVALID = 0;
    public static final int HUD_HEIGHT_LEVEL_RESERVED = 22;
    public static final int HUD_MODE_ARHUD = 2;
    public static final int HUD_MODE_CLASSICS = 1;
    public static final int HUD_MODE_DESERT = 3;
    public static final int HUD_MODE_RESERVED = 3;
    public static final int HUD_MODE_SNOWFIELD = 2;
    public static final int HUD_MODE_WHUD = 1;
    public static final int HUD_SET_SOURCE_DRIVE_ASSIS = 1;
    public static final int HUD_SET_SOURCE_NAVI = 2;
    public static final int H_SPEED_AUTO_CLOSE_CLOSE = 2;
    public static final int H_SPEED_AUTO_CLOSE_INVALID = 0;
    public static final int H_SPEED_AUTO_CLOSE_ON = 1;
    public static final int H_SPEED_AUTO_CLOSE_RESERVED = 3;
    public static final int IAL_AREA_ALL_ROW = 3;
    public static final int IAL_AREA_BACK_ROW = 2;
    public static final int IAL_AREA_FRONT_ROW = 1;
    public static final int IAL_AREA_INVALID = 0;
    public static final int IAL_AREA_RESERVED = 4;
    public static final int IAL_AREA_STARRY_SKY = 4;
    public static final int IAL_AREA_STAR_RING = 5;
    public static final int IAL_BRIGHTNESS_INVALID = 0;
    public static final int IAL_BRIGHTNESS_LEVEL_FIRST = 1;
    public static final int IAL_BRIGHTNESS_LEVEL_FOURTH = 4;
    public static final int IAL_BRIGHTNESS_LEVEL_SECOND = 2;
    public static final int IAL_BRIGHTNESS_LEVEL_THIRD = 3;
    public static final int IAL_BRIGHTNESS_MAX = 5;
    public static final int IAL_BRIGHTNESS_MIN = 0;
    public static final int IAL_COLDWHITE = 2;
    public static final int IAL_COLD_WHITE = 2;
    public static final int IAL_COLOR_INVALID = 0;
    public static final int IAL_COLOR_NONE = 1;
    public static final int IAL_COLOR_ONE = 2;
    public static final int IAL_COLOR_SEVEN = 3;
    public static final int IAL_COLOR_THIRTYONE = 4;
    public static final int IAL_CTRL_SOURCE_UI = 0;
    public static final int IAL_CTRL_SOURCE_VOICE = 1;
    public static final int IAL_DARK_BLUE = 7;
    public static final int IAL_ICE_BLUE = 1;
    public static final int IAL_INVALID = 0;
    public static final int IAL_OTHERL_COLOR = 8;
    public static final int IAL_OTHER_COLOR = 31;
    public static final int IAL_REDL_ORANGE = 4;
    public static final int IAL_RED_ORANGE = 4;
    public static final int IAL_RED_PURPLE = 6;
    public static final int IAL_TRUE_RED = 5;
    public static final int IAL_WARM_WHITE = 3;
    public static final int IL_DURATION_15_S = 3;
    public static final int IL_DURATION_30_S = 4;
    public static final int IL_DURATION_7p5_S = 2;
    public static final int IL_DURATION_OFF = 1;
    public static final int INSIDE_LGHT_DOOR_INVALD = 1;
    public static final int INSIDE_LIGHT_DOOR_CLOSE = 1;
    public static final int INSIDE_LIGHT_DOOR_INVALID = 0;
    public static final int INSIDE_LIGHT_DOOR_OPEN = 2;
    public static final int INSIDE_LIGHT_DOOR_RESERVED = 3;
    public static final int INSIGHT_LIGHT_OFF = 1;
    public static final int INSIGHT_LIGHT_ON = 2;
    public static final int INSTRUMENT_AC_KEY_AUTO = 2;
    public static final int INSTRUMENT_AC_KEY_AUTODIAGNOSIS_SIGNAL = 13;
    public static final int INSTRUMENT_AC_KEY_FRONT_DEFROST = 3;
    public static final int INSTRUMENT_AC_KEY_INVALID = 0;
    public static final int INSTRUMENT_AC_KEY_OFF = 1;
    public static final int INSTRUMENT_AC_KEY_PRESS_AC = 10;
    public static final int INSTRUMENT_BACKLIGHT_CTL_TYPE_N = 1;
    public static final int INSTRUMENT_BACKLIGHT_CTL_TYPE_R = 0;
    public static final int LANTERN_LANGUAGEMODE_INVALID = 0;
    public static final int LANTERN_LANGUAGEMODE_NONE = 1;
    public static final int LANTERN_LANGUAGEMODE_RESERVED = 4;
    public static final int LANTERN_LANGUAGE_MODE_HL_LOCK_WELCOME = 3;
    public static final int LANTERN_LANGUAGE_MODE_HL_UNLOCK_WELCOME = 2;
    public static final int LANTERN_LANGUAGE_MODE_NONE = 1;
    public static final int LAST_LEFT_SEAT = 6;
    public static final int LAST_MIDDLE_SEAT = 8;
    public static final int LAST_RIGHT_SEAT = 7;
    public static final int LEARNER_DRIVER_ACC_LIMIT_STATE_ACTIVATED = 0;
    public static final int LEARNER_DRIVER_ACC_LIMIT_STATE_INACTIVATED = 1;
    public static final int LEARNER_DRIVER_MODE_STATE_ENTERED = 2;
    public static final int LEARNER_DRIVER_MODE_STATE_ENTERING = 1;
    public static final int LEARNER_DRIVER_MODE_STATE_ERROR = 6;
    public static final int LEARNER_DRIVER_MODE_STATE_EXITED = 4;
    public static final int LEARNER_DRIVER_MODE_STATE_EXITING = 3;
    public static final int LEARNER_DRIVER_MODE_STATE_INVALID = 0;
    public static final int LEARNER_DRIVER_MODE_STATE_UNAVAILABLE = 5;
    protected static final int LEARNER_DRIVER_MODE_SWITCH_OFF = 2;
    protected static final int LEARNER_DRIVER_MODE_SWITCH_ON = 1;
    public static final int LEAVE_HOME_DELAY_LEVEL_CLOSE = 7;
    public static final int LEAVE_HOME_DELAY_LEVEL_FIFTH = 5;
    public static final int LEAVE_HOME_DELAY_LEVEL_FIRST = 1;
    public static final int LEAVE_HOME_DELAY_LEVEL_FOURTH = 4;
    public static final int LEAVE_HOME_DELAY_LEVEL_INVALID = 0;
    public static final int LEAVE_HOME_DELAY_LEVEL_SECOND = 2;
    public static final int LEAVE_HOME_DELAY_LEVEL_SIXTH = 6;
    public static final int LEAVE_HOME_DELAY_LEVEL_THIRD = 3;
    public static final int LEFT_ELECTRI_PEDAL = 1;
    public static final int LEFT_FIELD_PLATFORM_INVALID = 0;
    public static final int LEFT_FIELD_PLATFORM_L1 = 1;
    public static final int LEFT_FIELD_PLATFORM_L2 = 2;
    public static final int LEFT_FIELD_PLATFORM_L3 = 3;
    public static final int LEFT_FIELD_PLATFORM_L4 = 4;
    public static final int LOCATION_DEPUTY = 2;
    public static final int LOCATION_LEFT_REAR = 3;
    public static final int LOCATION_MAIN = 1;
    public static final int LOCATION_RIGHT_REAR = 4;
    public static final int MASSAGEL_MODE_SHOULDER = 6;
    public static final int MASSAGE_LEVEL_CLOSE = 4;
    public static final int MASSAGE_LEVEL_INTENSITY1 = 1;
    public static final int MASSAGE_LEVEL_INTENSITY2 = 2;
    public static final int MASSAGE_LEVEL_INTENSITY3 = 3;
    public static final int MASSAGE_LEVEL_INVALID = 0;
    public static final int MASSAGE_MODE_CLOSE = 1;
    public static final int MASSAGE_MODE_INVALID = 0;
    public static final int MASSAGE_MODE_OPEN = 7;
    public static final int MASSAGE_MODE_PULSE = 3;
    public static final int MASSAGE_MODE_SHOULDER = 6;
    public static final int MASSAGE_MODE_STRETCH = 4;
    public static final int MASSAGE_MODE_WAIST = 5;
    public static final int MASSAGE_MODE_WAVE = 2;
    public static final int MCU_PH2_STATE_H = 1;
    public static final int MCU_PH2_STATE_L = 0;
    public static final int MCU_RESTART_INVALID = 0;
    public static final int MCU_RESTART_VALID = 1;
    public static final int MODEL_AIRCONDTIONER = 3;
    public static final int MODEL_AMBIENTLIGHTS = 2;
    public static final int MODEL_CARLIGHTS = 1;
    public static final int MODEL_CARSEAT = 10;
    public static final int MODEL_COCKPIT = 12;
    public static final int MODEL_COMFORTABLE_DRIVE = 5;
    public static final int MODEL_DIBAR = 11;
    public static final int MODEL_DOOR_AND_LOCK = 4;
    public static final int MODEL_HEADREST_SCREEN = 13;
    public static final int MODEL_HUD = 6;
    public static final int MODEL_INSTRUMENT = 7;
    public static final int MODEL_INTELLIGENT_CHASSIS = 8;
    public static final int MODEL_REMINDER = 9;
    public static final int MODE_CLOSE = 0;
    public static final int MODE_COLD = 1;
    public static final int MODE_HOT = 2;
    public static final int MULTIMEDIA_MOTOR_TEMP_NORMAL = 1;
    public static final int MULTIMEDIA_MOTOR_TEMP_OVERHEAT = 2;
    public static final int NEED_NOTICE = 2;
    public static final int NIGHT_DRIVE_LIGHT_ON_REMINDER_OFF = 0;
    public static final int NIGHT_DRIVE_LIGHT_ON_REMINDER_ON = 1;
    public static final int NOT_NEED_NOTICE = 1;
    public static final int ORIENTATION_BACK = 4;
    public static final int ORIENTATION_DOWN = 2;
    public static final int ORIENTATION_FRONT = 3;
    public static final int ORIENTATION_INVALID = 0;
    public static final int ORIENTATION_LEFT = 1;
    public static final int ORIENTATION_RESERVED = 5;
    public static final int ORIENTATION_RIGHT = 2;
    public static final int ORIENTATION_UP = 1;
    public static final int OVERSPEED_LOCKING_INVALID = 0;
    public static final int OVERSPEED_LOCKING_OFF = 2;
    public static final int OVERSPEED_LOCKING_ON = 1;
    public static final int OVERSPEED_LOCKING_RESERVED = 3;
    public static final int PASSENGER_SEAT = 2;
    public static final String PERMISSION_BYDAUTO_SETTING_COMMON = "android.permission.BYDAUTO_SETTING_COMMON";
    public static final String PERMISSION_BYDAUTO_SETTING_GET = "android.permission.BYDAUTO_SETTING_GET";
    public static final String PERMISSION_BYDAUTO_SETTING_SET = "android.permission.BYDAUTO_SETTING_SET";
    public static final int POSITION_INVALID = 0;
    public static final int POSITION_ONE = 1;
    public static final int POSITION_RESERVED = 4;
    public static final int POSITION_THTEE = 3;
    public static final int POSITION_TWO = 2;
    public static final int REARVIEW_MIRROR_AUTO_FOLD_INVALID = 0;
    public static final int REARVIEW_MIRROR_AUTO_FOLD_NO = 1;
    public static final int REARVIEW_MIRROR_AUTO_FOLD_YES = 2;
    public static final int REAR_AC_PANEL_AUTO_LOCK_0S = 1;
    public static final int REAR_AC_PANEL_AUTO_LOCK_15S = 4;
    public static final int REAR_AC_PANEL_AUTO_LOCK_5S = 2;
    public static final int REAR_AC_PANEL_AUTO_LOCK_8S = 3;
    public static final int REAR_AC_PANEL_AUTO_LOCK_INVALID = 0;
    public static final int REAR_AC_PANEL_AUTO_LOCK_RESERVED = 5;
    public static final int REAR_LEFT_SEAT = 3;
    public static final int REAR_MIDDLE_SEAT = 5;
    public static final int REAR_RIGHT_SEAT = 4;
    public static final int REAR_WINDSCREEN_WIPER = 2;
    public static final int REMINDER_FINISH = 2;
    public static final int REMINDER_INVALID = 0;
    public static final int REMINDER_POWER_ONOFF = 2;
    public static final int REMINDER_REMINDING = 1;
    public static final int REMINDER_RESERVED = 3;
    public static final int REMINDER_SHUTDOWN_POWER = 1;
    public static final int REMOTE_CONTROL_UNLOCKING_OFF = 0;
    public static final int REMOTE_CONTROL_UNLOCKING_ON = 1;
    public static final int REMOTE_START_AC_INVALID = 0;
    public static final int REMOTE_START_AC_TIME10 = 1;
    public static final int REMOTE_START_AC_TIME15 = 2;
    public static final int REMOTE_START_AC_TIME20 = 3;
    public static final int REMOTE_START_AC_TIME25 = 4;
    public static final int REMOTE_START_AC_TIME30 = 5;
    public static final int REMOTE_START_AC_TIME_RESERVED = 6;
    public static final int REQUEST_UPDATE = 0;
    public static final int RESET_INVALID = 0;
    public static final int RESET_ITEM_ATMP = 4;
    public static final int RESET_ITEM_BACK_LIGHT = 6;
    public static final int RESET_ITEM_DOOR_WINDOW = 0;
    public static final int RESET_ITEM_LOCK = 1;
    public static final int RESET_ITEM_READING_LIGHT = 5;
    public static final int RESET_ITEM_REAR_MIRROR = 3;
    public static final int RESET_ITEM_SEAT_SELF_RETURN = 2;
    public static final int RESET_VALID = 1;
    public static final int RESTORE_DEFAULT_VALUES = 1;
    public static final int RIGHT_ELECTRI_PEDAL = 2;
    public static final int ROTATION_HORIZONTAL = 1;
    public static final int ROTATION_VERTICAL = 2;
    public static final int SEAT_HEATING_HIGH = 3;
    public static final int SEAT_HEATING_INVALID = 0;
    public static final int SEAT_HEATING_LEVEL1_OFF = 1;
    public static final int SEAT_HEATING_LEVEL1_ON = 2;
    public static final int SEAT_HEATING_LOW = 2;
    public static final int SEAT_HEATING_OFF = 1;
    public static final int SEAT_VENTILATING_HIGH = 3;
    public static final int SEAT_VENTILATING_LOW = 2;
    public static final int SEAT_VENTILATING_OFF = 1;
    public static final int SEAT_VENTILATION_HIGH = 3;
    public static final int SEAT_VENTILATION_INVALID = 1;
    public static final int SEAT_VENTILATION_LOW = 2;
    public static final int SETTING_CMD_MASSAGE_LB = 1;
    public static final int SETTING_CMD_MASSAGE_RB = 2;
    public static final int SETTING_COMMAND_BUSY = -2147482647;
    public static final int SETTING_COMMAND_FAILED = -2147482648;
    public static final int SETTING_COMMAND_INVALID = -2147482645;
    public static final int SETTING_COMMAND_SUCCESS = 0;
    public static final int SETTING_COMMAND_TIMEOUT = -2147482646;
    public static final int SETTING_RSET_COMMAND_BUSY = 3;
    public static final int SETTING_RSET_COMMAND_FAILED = 4;
    public static final int SETTING_RSET_COMMAND_INVALID = 1;
    public static final int SETTING_RSET_COMMAND_SUCCESS = 0;
    public static final int SETTING_RSET_COMMAND_TIMEOUT = 2;
    public static final int SET_AC_AUTO_AIR_COMFORT = 2;
    public static final int SET_AC_AUTO_AIR_ECONOMY = 1;
    public static final int SET_AC_AUTO_AIR_INVALID = 0;
    public static final int SET_AC_AUTO_AIR_RESERVED = 4;
    public static final int SET_AC_AUTO_AIR_SMART = 3;
    public static final int SET_AC_AUTO_WIND_LV_LARGE = 3;
    public static final int SET_AC_AUTO_WIND_LV_MEDIUM = 2;
    public static final int SET_AC_AUTO_WIND_LV_SMALL = 1;
    public static final int SET_AC_WARM_MODE_ELEC = 1;
    public static final int SET_AC_WARM_MODE_INVALID = 0;
    public static final int SET_AC_WARM_MODE_OIL = 2;
    public static final int SET_AC_WARM_MODE_RESERVED = 3;
    public static final int SET_ALL_INTERFACE_SETTINGS_AND_KEY = 3;
    public static final int SET_ALL_INTERFACE_SETTINGS_NO_KEY = 2;
    public static final int SET_CAR_LIGHT_DELAY_VALUE_MAX = 60;
    public static final int SET_CAR_LIGHT_DELAY_VALUE_MIN = 0;
    public static final int SET_CAR_LOCK_OFF_4DOORS = 2;
    public static final int SET_CAR_LOCK_OFF_DR_SIDE = 1;
    public static final int SET_CAR_REAR_VIEW_FLIP_ANGLE_MAX = 8;
    public static final int SET_CAR_REAR_VIEW_FLIP_ANGLE_MIN = 0;
    public static final int SET_CAR_SPEECH_VOL_HIGH = 3;
    public static final int SET_CAR_SPEECH_VOL_LOW = 1;
    public static final int SET_CAR_SPEECH_VOL_MEDIUM = 2;
    public static final int SET_CHARGE_PORT_LOCK_MODE = 2;
    public static final int SET_DM_PLATFORM_INVALID = 0;
    public static final int SET_DM_PLATFORM_ONE = 1;
    public static final int SET_DM_PLATFORM_TWO = 2;
    public static final int SET_DR_ENERGY_FB_LARGE = 3;
    public static final int SET_DR_ENERGY_FB_SMALL = 1;
    public static final int SET_DR_ENERGY_FB_STANDARD = 2;
    public static final int SET_DR_ENERGY_STATE_INVALID = 0;
    public static final int SET_DR_SOC_TARGET_MAX = 70;
    public static final int SET_DR_SOC_TARGET_MIN = 15;
    public static final int SET_DR_ST_ASSIS_COMFORT = 1;
    public static final int SET_DR_ST_ASSIS_INVALID = 0;
    public static final int SET_DR_ST_ASSIS_RESERVED = 4;
    public static final int SET_DR_ST_ASSIS_SPORT = 3;
    public static final int SET_DR_ST_ASSIS_STANDARD = 2;
    public static final int SET_DR_SWITCH_FAIL = 0;
    public static final int SET_DR_SWITCH_FAIL_WHILE_LOCKING = 2;
    public static final int SET_DR_SWITCH_SUCCESS = 1;
    public static final int SET_INSIDE_REAR_SCREEN_COMPASS = 2;
    public static final int SET_INSIDE_REAR_SCREEN_OFF = 3;
    public static final int SET_INSIDE_REAR_SCREEN_VIDEO_RECORDING = 1;
    public static final int SET_INS_THEME_FASHION = 3;
    public static final int SET_INS_THEME_MAX = 10;
    public static final int SET_INS_THEME_MIN = 1;
    public static final int SET_INS_THEME_NONE = 0;
    public static final int SET_INS_THEME_SIMPLE = 4;
    public static final int SET_INS_THEME_TEACHNOLOGY = 2;
    public static final int SET_INVALID = 0;
    public static final int SET_LANGUAGE_ARABIC = 5;
    public static final int SET_LANGUAGE_COMPLEX_CHINESE = 2;
    public static final int SET_LANGUAGE_ENGLISH = 3;
    public static final int SET_LANGUAGE_RUSSIAN = 4;
    public static final int SET_LANGUAGE_SIMPLE_CHINESE = 1;
    public static final int SET_MODEL_BABY = 2;
    public static final int SET_MODEL_CAMPING = 5;
    public static final int SET_MODEL_CLOSE = 1;
    public static final int SET_MODEL_PET = 4;
    public static final int SET_MODEL_REST = 3;
    public static final int SET_MODEL_ROMANCE = 6;
    public static final int SET_MUSIC_MODE_STATE = 1;
    public static final int SET_NO_DETECT = 0;
    public static final int SET_NO_INTERFACE_ALL_SETTINGS = 0;
    public static final int SET_OFF = 2;
    public static final int SET_ON = 1;
    public static final int SET_ONLINE = 1;
    public static final int SET_ONLY_RECORD = 1;
    public static final int SET_PM25_SWITCH_OFF_ALL = 3;
    public static final int SET_PM25_SWITCH_ON_ALL = 0;
    public static final int SET_PM25_SWITCH_ON_IN = 1;
    public static final int SET_PM25_SWITCH_ON_OUT = 2;
    public static final int SET_RESERVED = 3;
    public static final int SET_SMART_ELECTRIC = 1;
    public static final int SET_STEER_ASSIST_FORBIT = 1;
    public static final int SET_STEER_ASSIST_PERMISSION = 0;
    public static final int SET_UNINVALID = 255;
    public static final int SMART_WELCOME_LIGHT_CLOSE = 2;
    public static final int SMART_WELCOME_LIGHT_INVALID = 0;
    public static final int SMART_WELCOME_LIGHT_OPEN = 1;
    public static final int SMART_WELCOME_LIGHT_RESERVED = 3;
    public static final int SOC_CONFIG_DM20 = 3;
    public static final int SOC_CONFIG_DM25 = 2;
    public static final int SOC_CONFIG_OFFLINE = 1;
    public static final int SOC_RANGE_10_70 = 7;
    public static final int SOC_RANGE_15_70 = 3;
    public static final int SOC_RANGE_18_70 = 5;
    public static final int SOC_RANGE_20_70 = 4;
    public static final int SOC_RANGE_22_70 = 6;
    public static final int SOC_RANGE_25_70 = 2;
    public static final int SOC_RANGE_EV = 1;
    public static final int SPEED_REMINDER_MAX = 240;
    public static final int SPEED_REMINDER_MIN = 60;
    public static final int SPEED_REMINDER_MPH_MAX = 150;
    public static final int SPEED_REMINDER_MPH_MIN = 35;
    public static final int SPEED_REMINDER_OFF = 241;
    public static final int SPEED_REMINDER_TO_SET_MPH_ON = 75;
    public static final int SPEED_REMINDER_TO_SET_ON = 120;
    public static final int START_KEY_BOTH_VALID = 3;
    public static final int START_KEY_INVALID = 0;
    public static final int START_KEY_SIG1_VALID = 1;
    public static final int START_KEY_SIG2_VALID = 2;
    public static final int STATE_CARSEAT_INVALID = 0;
    public static final int STATE_CARSEAT_RECOVING = 4;
    public static final int STATE_CARSEAT_RECOVING_ACCOUNT = 3;
    public static final int STATE_CARSEAT_RECOVING_BREAK = 5;
    public static final int STATE_CARSEAT_RECOVING_SUCCESS = 6;
    public static final int STATE_CARSEAT_RESERVED = 7;
    public static final int STATE_CARSEAT_SAVE_FAILED = 2;
    public static final int STATE_CARSEAT_SAVE_SUCCESS = 1;
    public static final int STATE_GERMICIDAL_LAMP_ERROR = 5;
    public static final int STATE_INVALID = 0;
    public static final int STATE_LOCK_RECORD = 2;
    public static final int STATE_NORMAL_RECORD = 1;
    public static final int STATE_RCOVER_BRAKE = 4;
    public static final int STATE_RCOVER_FAILED = 3;
    public static final int STATE_RECOVER_RUNNING = 1;
    public static final int STATE_RECOVER_START = 0;
    public static final int STATE_RECOVER_SUCCESS = 2;
    public static final int STATE_RESERVED = 3;
    public static final int STATE_RETRACTION = 2;
    public static final int STATE_RETRACTIONED = 2;
    public static final int STATE_SAVE_FAIL = 2;
    public static final int STATE_SAVE_INVALID = 0;
    public static final int STATE_SAVE_RESEVED = 4;
    public static final int STATE_SAVE_SUCCESS = 1;
    public static final int STATE_SAVE_break = 3;
    public static final int STATE_STRETCH = 1;
    public static final int STATE_STRETCHED = 1;
    public static final int STATE_SUPPORT = 1;
    public static final int STATE_TAKE_PICTURE = 4;
    public static final int STATE_TC_ERROR = 3;
    public static final int STATE_TEMPERATURE_SENSOR_ERROR = 4;
    public static final int STATE_TEMPER_HIGHT = 1;
    public static final int STATE_UNSUPPORT = 2;
    public static final int STATE_VOLTAGE_LOW = 2;
    public static final int STEERING_WHEEL_HEATING_INVALID = 0;
    public static final int STEERING_WHEEL_HEATING_OFF = 1;
    public static final int STEERING_WHEEL_HEATING_ON = 2;
    public static final int STEERING_WHEEL_WARM_STATE_OFF = 1;
    public static final int STEERING_WHEEL_WARM_STATE_ON = 2;
    public static final int STEER_INVALID = 0;
    public static final int STEER_QIN_WITHOUT_ACC = 3;
    public static final int STEER_QIN_WITH_ACC = 4;
    public static final int STEER_YUAN_WITHOUT_ACC = 1;
    public static final int STEER_YUAN_WITH_ACC = 2;
    protected static final String TAG = "BYDAutoSettingDevice";
    public static final int TEMP_VALUE_MAX = 164;
    public static final int TEMP_VALUE_MIN = -80;
    public static final int TEST_MODE_ENTER = 1;
    public static final int TEST_MODE_INVALID = 0;
    public static final int TEXT_ANIMATION_TYPE_INVALID = 0;
    public static final int TEXT_ANIMATION_TYPE_MAX = 14;
    public static final int TEXT_ANIMATION_TYPE_MIN = 2;
    public static final int TEXT_BACKGROUND_INVALID = 0;
    public static final int TEXT_BACKGROUND_NUMBER_MAX = 14;
    public static final int TEXT_BACKGROUND_NUMBER_MIN = 2;
    public static final int TEXT_COLOR_B_VALUE_INVALID = 0;
    public static final int TEXT_COLOR_B_VALUE_MAX = 254;
    public static final int TEXT_COLOR_B_VALUE_MIN = 1;
    public static final int TEXT_COLOR_G_VALUE_INVALID = 0;
    public static final int TEXT_COLOR_G_VALUE_MAX = 254;
    public static final int TEXT_COLOR_G_VALUE_MIN = 1;
    public static final int TEXT_COLOR_R_VALUE_INVALID = 0;
    public static final int TEXT_COLOR_R_VALUE_MAX = 254;
    public static final int TEXT_COLOR_R_VALUE_MIN = 1;
    public static final int TEXT_CONTENT_LENGTH_MAX = 255;
    public static final int TEXT_CONTENT_LENGTH_MIN = 1;
    public static final int TEXT_INFO_NUMBER_INVALID = 0;
    public static final int TEXT_INFO_NUMBER_MAX = 62;
    public static final int TEXT_INFO_NUMBER_MIN = 1;
    public static final int TEXT_NO_ANIMATION = 1;
    public static final int TEXT_NO_BACKGROUND = 1;
    public static final int TEXT_PLAY_SPEED_INVALID = 0;
    public static final int TEXT_PLAY_SPEED_MAX = 14;
    public static final int TEXT_PLAY_SPEED_MIN = 2;
    public static final int TEXT_PLAY_SPEED_SHOW_NOW = 1;
    public static final int TEXT_SIZE_INVALID = 0;
    public static final int TEXT_SIZE_MAX = 254;
    public static final int TEXT_SIZE_MIN = 1;
    public static final int TEXT_STATE_INVALID = 0;
    public static final int TEXT_STATE_READ = 1;
    public static final int TEXT_STATE_READING = 1;
    public static final int TEXT_STATE_READ_COMPLETE = 2;
    public static final int TEXT_STATE_READ_FAILURE = 3;
    public static final int TEXT_STATE_WRITE = 2;
    public static final int TEXT_STATE_WRITE_COMPLETE = 5;
    public static final int TEXT_STATE_WRITE_FAILURE = 6;
    public static final int TEXT_STATE_WRITING = 4;
    public static final int UI_GENERATION_ONE = 0;
    public static final int UI_GENERATION_ONE_PONIT_FIVE = 1;
    public static final int VEHICLE_STATE_BT_ON = 6;
    public static final int VEHICLE_STATE_EXHIBITION_MODE = 8;
    public static final int VEHICLE_STATE_HIGH_TEMP_DISINFECT = 7;
    public static final int VEHICLE_STATE_INVALID = 0;
    public static final int VEHICLE_STATE_NON_REMOTE_BOOT = 2;
    public static final int VEHICLE_STATE_OUTDOOR_EXHIBITION_MODE = 9;
    public static final int VEHICLE_STATE_PRESS_STARTUP = 5;
    public static final int VEHICLE_STATE_REMOTE_BOOT = 1;
    public static final int VEHICLE_STATE_REMOTE_CONTROL_START = 3;
    public static final int VEHICLE_STATE_SMART_CHARGE = 4;
    public static final int VOICE_CMD_CLOSE = 3;
    public static final int VOICE_CMD_OPEN = 1;
    public static final int VOICE_CMD_STOP = 2;
    public static final int WEATHER_FORCAST_BYTE_LEN = 33;
    public static final int WEATHER_STATE_CLOUDY = 1;
    public static final int WEATHER_STATE_INVALID = 0;
    public static final int WEATHER_STATE_RAINY = 3;
    public static final int WEATHER_STATE_SNOWY = 4;
    public static final int WEATHER_STATE_SUNNY = 2;
    public static final int WINDSCREEN_WIPER_OVERHAUL_CLOSE = 2;
    public static final int WINDSCREEN_WIPER_OVERHAUL_INVALID = 0;
    public static final int WINDSCREEN_WIPER_OVERHAUL_OPEN = 1;
    public static final int WINDSCREEN_WIPER_UNALLOWED_OVERHAUL = 3;
    public static final int WIPER_STATE_FAST = 3;
    public static final int WIPER_STATE_INVALID = 0;
    public static final int WIPER_STATE_SLOW = 2;
    public static final int WIPER_STATE_STOP = 1;
    private static volatile BYDAutoSettingDevice mBYDAutoSettingDevice;
    private final SettingClusterManagerImpl mSettingClusterManagerImpl;
    private final SettingManagerImpl mSettingManagerImpl;
    private final SettingQnxManagerImpl mSettingQnxManagerImpl;

    private BYDAutoSettingDevice(Context con) {
        super(con);
        this.mSettingQnxManagerImpl = SettingQnxManagerImpl.getInstance(con);
        this.mSettingManagerImpl = SettingManagerImpl.getInstance(con);
        this.mSettingClusterManagerImpl = SettingClusterManagerImpl.getInstance(con);
    }

    public static BYDAutoSettingDevice getInstance(Context con) {
        if (mBYDAutoSettingDevice == null) {
            synchronized (BYDAutoSettingDevice.class) {
                if (mBYDAutoSettingDevice == null) {
                    mBYDAutoSettingDevice = new BYDAutoSettingDevice(con.getApplicationContext());
                }
            }
        }
        return mBYDAutoSettingDevice;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_SETTING_SET;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_SETTING_GET;
    }

    public void registerListener(AbsBYDAutoSettingListener listener) {
        this.mSettingManagerImpl.registerListener(listener);
        this.mSettingClusterManagerImpl.registerListener(listener);
    }

    public void registerListener(AbsBYDAutoSettingListener listener, int[] featureIDs) {
        this.mSettingManagerImpl.registerListener(listener, featureIDs);
    }

    public void unregisterListener(AbsBYDAutoSettingListener listener) {
        this.mSettingManagerImpl.unregisterListener(listener);
        this.mSettingClusterManagerImpl.unregisterListener(listener);
    }

    public int getACBTWind() {
        return this.mSettingManagerImpl.getACBTWind();
    }

    public int setACBTWind(int value) {
        return this.mSettingManagerImpl.setACBTWind(value);
    }

    public int getACTunnelCycle() {
        return this.mSettingManagerImpl.getACTunnelCycle();
    }

    public int setACTunnelCycle(int value) {
        return this.mSettingManagerImpl.setACTunnelCycle(value);
    }

    public int getACPauseCycle() {
        return this.mSettingManagerImpl.getACPauseCycle();
    }

    public int setACPauseCycle(int value) {
        return this.mSettingManagerImpl.setACPauseCycle(value);
    }

    public int getACAutoAir() {
        return this.mSettingManagerImpl.getACAutoAir();
    }

    public int setACAutoAir(int value) {
        return this.mSettingManagerImpl.setACAutoAir(value);
    }

    public int getPM25Power() {
        return this.mSettingManagerImpl.getPM25Power();
    }

    public int setPM25Power(int value) {
        return this.mSettingManagerImpl.setPM25Power(value);
    }

    public int getPM25SwitchCheck() {
        return this.mSettingManagerImpl.getPM25SwitchCheck();
    }

    public int setPM25SwitchCheck(int value) {
        return this.mSettingManagerImpl.setPM25SwitchCheck(value);
    }

    public int getPM25TimeCheck() {
        return this.mSettingManagerImpl.getPM25TimeCheck();
    }

    public int setPM25TimeCheck(int value) {
        return this.mSettingManagerImpl.setPM25TimeCheck(value);
    }

    public int getEnergyFeedback() {
        return this.mSettingManagerImpl.getEnergyFeedback();
    }

    public int setEnergyFeedback(int value) {
        return this.mSettingManagerImpl.setEnergyFeedback(value);
    }

    public int getSOCTarget() {
        return this.mSettingManagerImpl.getSOCTarget();
    }

    public int setSOCTarget(int value) {
        return this.mSettingManagerImpl.setSOCTarget(value);
    }

    public int getChargingPort() {
        return this.mSettingManagerImpl.getChargingPort();
    }

    public int setChargingPort(int value) {
        return this.mSettingManagerImpl.setChargingPort(value);
    }

    public int getAutoExternalRearMirrorFollowUpSwitch() {
        return this.mSettingManagerImpl.getAutoExternalRearMirrorFollowUpSwitch();
    }

    public int setAutoExternalRearMirrorFollowUpSwitch(int value) {
        return this.mSettingManagerImpl.setAutoExternalRearMirrorFollowUpSwitch(value);
    }

    public int getLockOff() {
        return this.mSettingManagerImpl.getLockOff();
    }

    public int setLockOff(int value) {
        return this.mSettingManagerImpl.setLockOff(value);
    }

    public int getOverspeedLock() {
        return this.mSettingManagerImpl.getOverspeedLock();
    }

    public int setOverspeedLock(int value) {
        return this.mSettingManagerImpl.setOverspeedLock(value);
    }

    public int getSafeWarnState() {
        return this.mSettingManagerImpl.getSafeWarnState();
    }

    public int getMaintainRemindState() {
        return this.mSettingManagerImpl.getMaintainRemindState();
    }

    public int getSteerAssis() {
        return this.mSettingManagerImpl.getSteerAssis();
    }

    public int setSteerAssis(int value) {
        return this.mSettingManagerImpl.setSteerAssis(value);
    }

    public int getRearViewMirrorFlip() {
        return this.mSettingManagerImpl.getRearViewMirrorFlip();
    }

    public int setRearViewMirrorFlip(int value) {
        return this.mSettingManagerImpl.setRearViewMirrorFlip(value);
    }

    public int getDriverSeatAutoReturn() {
        return this.mSettingManagerImpl.getDriverSeatAutoReturn();
    }

    public int setDriverSeatAutoReturn(int value) {
        return this.mSettingManagerImpl.setDriverSeatAutoReturn(value);
    }

    public int getSteerPositionAutoReturn() {
        return this.mSettingManagerImpl.getSteerPositionAutoReturn();
    }

    public int setSteerPositionAutoReturn(int value) {
        return this.mSettingManagerImpl.setSteerPositionAutoReturn(value);
    }

    public int getRemoteControlUpwindowState() {
        return this.mSettingManagerImpl.getRemoteControlUpwindowState();
    }

    public int setRemoteControlUpwindowState(int value) {
        return this.mSettingManagerImpl.setRemoteControlUpwindowState(value);
    }

    public int getRemoteControlDownwindowState() {
        return this.mSettingManagerImpl.getRemoteControlDownwindowState();
    }

    public int setRemoteControlDownwindowState(int value) {
        return this.mSettingManagerImpl.setRemoteControlDownwindowState(value);
    }

    public int getLockCarRiseWindow() {
        return this.mSettingManagerImpl.getLockCarRiseWindow();
    }

    public int setLockCarRiseWindow(int value) {
        return this.mSettingManagerImpl.setLockCarRiseWindow(value);
    }

    public int getMicroSwitchLockWindowState() {
        return this.mSettingManagerImpl.getMicroSwitchLockWindowState();
    }

    public int setMicroSwitchLockWindowState(int value) {
        return this.mSettingManagerImpl.setMicroSwitchLockWindowState(value);
    }

    public int getMicroSwitchUnlockWindowState() {
        return this.mSettingManagerImpl.getMicroSwitchUnlockWindowState();
    }

    public int setMicroSwitchUnlockWindowState(int value) {
        return this.mSettingManagerImpl.setMicroSwitchUnlockWindowState(value);
    }

    public int getBackHomeLightDelayValue() {
        return this.mSettingManagerImpl.getBackHomeLightDelayValue();
    }

    public int setBackHomeLightDelayValue(int value) {
        return this.mSettingManagerImpl.setBackHomeLightDelayValue(value);
    }

    public int getLeftHomeLightDelay() {
        return this.mSettingManagerImpl.getLeftHomeLightDelay();
    }

    public int setLeftHomeLightDelay(int value) {
        return this.mSettingManagerImpl.setLeftHomeLightDelay(value);
    }

    public int getBackDoorElectricMode() {
        return this.mSettingManagerImpl.getBackDoorElectricMode();
    }

    public int setBackDoorElectricMode(int value) {
        return this.mSettingManagerImpl.setBackDoorElectricMode(value);
    }

    public int getAutoPurificationState() {
        return this.mSettingManagerImpl.getAutoPurificationState();
    }

    public int setAutoPurification(int state) {
        return this.mSettingManagerImpl.setAutoPurification(state);
    }

    public int getRemoteKeyStartAcTime() {
        return this.mSettingManagerImpl.getRemoteKeyStartAcTime();
    }

    public int setRemoteKeyStartAcTime(int value) {
        return this.mSettingManagerImpl.setRemoteKeyStartAcTime(value);
    }

    public int getAcTemperatureUnit() {
        return this.mSettingManagerImpl.getAcTemperatureUnit();
    }

    public int setAcTemperatureUnit(int value) {
        return this.mSettingManagerImpl.setAcTemperatureUnit(value);
    }

    public int getAcWarmMode() {
        return this.mSettingManagerImpl.getAcWarmMode();
    }

    public int setAcWarmMode(int mode) {
        return this.mSettingManagerImpl.setAcWarmMode(mode);
    }

    public int setIntelligentAcAreaState(int value) {
        return this.mSettingManagerImpl.setIntelligentAcAreaState(value);
    }

    public int getIntelligentAcAreaState() {
        return this.mSettingManagerImpl.getIntelligentAcAreaState();
    }

    public int getRearAcOnlineState() {
        return this.mSettingManagerImpl.getRearAcOnlineState();
    }

    public int getBackDoorOpenedHeight() {
        return this.mSettingManagerImpl.getBackDoorOpenedHeight();
    }

    public int setBackDoorOpenedHeight(int value) {
        return this.mSettingManagerImpl.setBackDoorOpenedHeight(value);
    }

    public int getSupportDoorHeight() {
        return this.mSettingManagerImpl.getSupportDoorHeight();
    }

    public int getRemoteControlLockWindowState() {
        return this.mSettingManagerImpl.getRemoteControlLockWindowState();
    }

    public int setRemoteControlLockWindowState(int value) {
        return this.mSettingManagerImpl.setRemoteControlLockWindowState(value);
    }

    public int getRemoteControlUnlockWindowState() {
        return this.mSettingManagerImpl.getRemoteControlUnlockWindowState();
    }

    public int setRemoteControlUnlockWindowState(int value) {
        return this.mSettingManagerImpl.setRemoteControlUnlockWindowState(value);
    }

    public int getRainAutoLockWindowState() {
        return this.mSettingManagerImpl.getRainAutoLockWindowState();
    }

    public int setRainAutoLockWindowState(int value) {
        return this.mSettingManagerImpl.setRainAutoLockWindowState(value);
    }

    public int getDoorknobAutoShrinkState() {
        return this.mSettingManagerImpl.getDoorknobAutoShrinkState();
    }

    public int setDoorknobAutoShrinkState(int value) {
        return this.mSettingManagerImpl.setDoorknobAutoShrinkState(value);
    }

    public int getIntelligentUnlockState() {
        return this.mSettingManagerImpl.getIntelligentUnlockState();
    }

    public int setIntelligentUnlockState(int value) {
        return this.mSettingManagerImpl.setIntelligentUnlockState(value);
    }

    public int getIntelligentLockState() {
        return this.mSettingManagerImpl.getIntelligentLockState();
    }

    public int setIntelligentLockState(int value) {
        return this.mSettingManagerImpl.setIntelligentLockState(value);
    }

    public void setHudParamsEffect() {
        this.mSettingManagerImpl.setHudParamsEffect();
    }

    public int getHudState() {
        return this.mSettingManagerImpl.getHudState();
    }

    public int setHudState(int value) {
        return this.mSettingManagerImpl.setHudState(value);
    }

    public int getHudHeight() {
        return this.mSettingManagerImpl.getHudHeight();
    }

    public int setHudHeight(int value) {
        return this.mSettingManagerImpl.setHudHeight(value);
    }

    public void resetHudHeight() {
        this.mSettingManagerImpl.resetHudHeight();
    }

    public int getHudBrightness() {
        return this.mSettingManagerImpl.getHudBrightness();
    }

    public int setHudBrightness(int value) {
        return this.mSettingManagerImpl.setHudBrightness(value);
    }

    public void resetHudBrightness() {
        this.mSettingManagerImpl.resetHudBrightness();
    }

    public int getHudRotationAngle() {
        return this.mSettingManagerImpl.getHudRotationAngle();
    }

    public int setHudRotationAngle(int value) {
        return this.mSettingManagerImpl.setHudRotationAngle(value);
    }

    public void resetHudRotationAngle() {
        this.mSettingManagerImpl.resetHudRotationAngle();
    }

    public int getPassengerState(int location) {
        return this.mSettingManagerImpl.getPassengerState(location);
    }

    public int setRearSeatsFoldOrExpand(int value) {
        return this.mSettingManagerImpl.setRearSeatsFoldOrExpand(value);
    }

    public int getHudMode() {
        return this.mSettingManagerImpl.getHudMode();
    }

    public int setHudMode(int value) {
        return this.mSettingManagerImpl.setHudMode(value);
    }

    public void resetHudMode() {
        this.mSettingManagerImpl.resetHudMode();
    }

    public int getHudDisplayContent(int setSource) {
        return this.mSettingManagerImpl.getHudDisplayContent(setSource);
    }

    public int setHudDisplayContent(int setSource, int value) {
        return this.mSettingManagerImpl.setHudDisplayContent(setSource, value);
    }

    public void resetHudDisplayContent() {
        this.mSettingManagerImpl.resetHudDisplayContent();
    }

    public int getEngineSoundType() {
        return this.mSettingManagerImpl.getEngineSoundType();
    }

    public int setEngineSoundType(int value) {
        return this.mSettingManagerImpl.setEngineSoundType(value);
    }

    public int setCarTipsSoundType(int value) {
        return this.mSettingClusterManagerImpl.setCarTipsSoundType(value);
    }

    public int getCarTipsSoundType() {
        return this.mSettingClusterManagerImpl.getCarTipsSoundType();
    }

    public int getReportVolume() {
        return this.mSettingManagerImpl.getReportVolume();
    }

    public int setReportVolume(int value) {
        return this.mSettingManagerImpl.setReportVolume(value);
    }

    public int getSpeedReminderValue() {
        return this.mSettingManagerImpl.getSpeedReminderValue();
    }

    public int setSpeedReminderValue(int value) {
        return this.mSettingManagerImpl.setSpeedReminderValue(value);
    }

    public int getAppUseLimitState() {
        return this.mSettingManagerImpl.getAppUseLimitState();
    }

    public int setAppUseLimitState(int value) {
        return this.mSettingManagerImpl.setAppUseLimitState(value);
    }

    public int getNavigationMediaState() {
        return this.mSettingManagerImpl.getNavigationMediaState();
    }

    public int setNavigationMediaState(int value) {
        return this.mSettingManagerImpl.setNavigationMediaState(value);
    }

    public int getAutoPowerOnState() {
        return this.mSettingManagerImpl.getAutoPowerOnState();
    }

    public int setAutoPowerOnState(int value) {
        return this.mSettingManagerImpl.setAutoPowerOnState(value);
    }

    public int getAutoPowerOffState() {
        return this.mSettingManagerImpl.getAutoPowerOffState();
    }

    public int setAutoPowerOffState(int value) {
        return this.mSettingManagerImpl.setAutoPowerOffState(value);
    }

    public int resetAutoPowerOnState() {
        return this.mSettingManagerImpl.resetAutoPowerOnState();
    }

    public int resetAutoExternalRearMirrorFollowUp() {
        return this.mSettingManagerImpl.resetAutoExternalRearMirrorFollowUp();
    }

    public int getBrakePedalMode() {
        return this.mSettingManagerImpl.getBrakePedalMode();
    }

    public int setBrakePedalMode(int value) {
        return this.mSettingManagerImpl.setBrakePedalMode(value);
    }

    public int getComfortableParkingState() {
        return this.mSettingManagerImpl.getComfortableParkingState();
    }

    public int setComfortableParkingState(int value) {
        return this.mSettingManagerImpl.setComfortableParkingState(value);
    }

    public int getMotorPretensionerSeatbeltState() {
        return this.mSettingManagerImpl.getMotorPretensionerSeatbeltState();
    }

    public int setMotorPretensionerSeatbeltState(int value) {
        return this.mSettingManagerImpl.setMotorPretensionerSeatbeltState(value);
    }

    public int resetMotorPretensionerSeatbelt() {
        return this.mSettingManagerImpl.resetMotorPretensionerSeatbelt();
    }

    public int getAutoRainWiperState() {
        return this.mSettingManagerImpl.getAutoRainWiperState();
    }

    public int setAutoRainWiperState(int value) {
        return this.mSettingManagerImpl.setAutoRainWiperState(value);
    }

    public int getDynamicSuspensionMode() {
        return this.mSettingManagerImpl.getDynamicSuspensionMode();
    }

    public int setDynamicSuspensionMode(int value) {
        return this.mSettingManagerImpl.setDynamicSuspensionMode(value);
    }

    public int getLandAdvanceAtSate() {
        return this.mSettingManagerImpl.getLandAdvanceAtSate();
    }

    public int setLandAdvanceAtState(int state) {
        return this.mSettingManagerImpl.setLandAdvanceAtState(state);
    }

    public float getSteeringWheelY() {
        return this.mSettingManagerImpl.getSteeringWheelY();
    }

    public float getSteeringWheelZ() {
        return this.mSettingManagerImpl.getSteeringWheelZ();
    }

    public void resetSteeringWheelPosition(int postion) {
        this.mSettingManagerImpl.resetSteeringWheelPosition(postion);
    }

    public float getRearviewMirrorY(int orientation) {
        return this.mSettingManagerImpl.getRearviewMirrorY(orientation);
    }

    public float getRearviewMirrorX(int orientation) {
        return this.mSettingManagerImpl.getRearviewMirrorX(orientation);
    }

    public void resetRearviewMirrorParams(int position, int orientation) {
        this.mSettingManagerImpl.resetRearviewMirrorParams(position, orientation);
    }

    public int getSeatHorization(int location) {
        return this.mSettingManagerImpl.getSeatHorization(location);
    }

    public float getSeatTurnHeight(int location) {
        return this.mSettingManagerImpl.getSeatTurnHeight(location);
    }

    public float getSeatbackrestPostion(int location) {
        return this.mSettingManagerImpl.getSeatbackrestPostion(location);
    }

    public void resetSeatParams(int postion, int location) {
        this.mSettingManagerImpl.resetSeatParams(postion, location);
    }

    public int getCushionSupport(int position) {
        return this.mSettingManagerImpl.getCushionSupport(position);
    }

    public float getTurnCushionHeight(int location) {
        return this.mSettingManagerImpl.getTurnCushionHeight(location);
    }

    public void resetCushionHeight(int postion, int location) {
        this.mSettingManagerImpl.resetCushionHeight(postion, location);
    }

    public float getTurnCaliperSplintHeight(int location) {
        return this.mSettingManagerImpl.getTurnCaliperSplintHeight(location);
    }

    public void resetCaliperSplintHeight(int position, int location) {
        this.mSettingManagerImpl.resetCaliperSplintHeight(position, location);
    }

    public int getHeadrestHorization(int location) {
        return this.mSettingManagerImpl.getHeadrestHorization(location);
    }

    public float getHeadrestTurnHeight(int location) {
        return this.mSettingManagerImpl.getHeadrestTurnHeight(location);
    }

    public int resetHeadrestParms(int position, int location) {
        return this.mSettingManagerImpl.resetHeadrestParms(position, location);
    }

    public int setRefrigeratorMode(int state) {
        return this.mSettingManagerImpl.setRefrigeratorMode(state);
    }

    public int getRemenberPowerOffState() {
        return this.mSettingManagerImpl.getRemenberPowerOffState();
    }

    public int setRemenberPowerOffState(int state) {
        return this.mSettingManagerImpl.setRemenberPowerOffState(state);
    }

    public void resetRemenberPowerOff() {
        this.mSettingManagerImpl.resetRemenberPowerOff();
    }

    public int getRefrigeratorDoorState() {
        return this.mSettingManagerImpl.getRefrigeratorDoorState();
    }

    public int setDisinfState(int value) {
        return this.mSettingManagerImpl.setDisinfState(value);
    }

    public int getDisinfState() {
        return this.mSettingManagerImpl.getDisinfState();
    }

    public int getTrayState() {
        return this.mSettingManagerImpl.getTrayState();
    }

    public int setTrayState(int state) {
        return this.mSettingManagerImpl.setTrayState(state);
    }

    public int getRefrigeratorMode() {
        return this.mSettingManagerImpl.getRefrigeratorMode();
    }

    public int getModelSupport(int model) {
        return this.mSettingManagerImpl.getModelSupport(model);
    }

    public int getHudModeParams() {
        return this.mSettingManagerImpl.getHudModeParams();
    }

    public void turnHeadrestTurnHeight(int location, int orientation) {
        this.mSettingManagerImpl.turnHeadrestTurnHeight(location, orientation);
    }

    public void turnHeadrestTurnHorization(int location, int orientation) {
        this.mSettingManagerImpl.turnHeadrestTurnHorization(location, orientation);
    }

    public void turnSeatCaliperSplint(int location, int orientation) {
        this.mSettingManagerImpl.turnSeatCaliperSplint(location, orientation);
    }

    public void turnSeatCushion(int location, int orientation) {
        this.mSettingManagerImpl.turnSeatCushion(location, orientation);
    }

    public void turnSeatbackrest(int location, int orientation) {
        this.mSettingManagerImpl.turnSeatbackrest(location, orientation);
    }

    public void turnSeatHeight(int location, int orientation) {
        this.mSettingManagerImpl.turnSeatHeight(location, orientation);
    }

    public void turnSeatHorization(int location, int orientation) {
        this.mSettingManagerImpl.turnSeatHorization(location, orientation);
    }

    public void turnRearviewMirror(int action) {
        this.mSettingManagerImpl.turnRearviewMirror(action);
    }

    public void turnSteeringWheel(int action) {
        this.mSettingManagerImpl.turnSteeringWheel(action);
    }

    public int getRefrigeratorState() {
        return this.mSettingManagerImpl.getRefrigeratorState();
    }

    public int getTrayInitState() {
        return this.mSettingManagerImpl.getTrayInitState();
    }

    public int setSafeWarnState(int state) {
        return this.mSettingManagerImpl.setSafeWarnState(state);
    }

    public int setMaintainRemindState(int state) {
        return this.mSettingManagerImpl.setMaintainRemindState(state);
    }

    public int resetHudState() {
        return this.mSettingManagerImpl.resetHudState();
    }

    public int getSteerAssisSupported() {
        return this.mSettingManagerImpl.getSteerAssisSupported();
    }

    public int setBackHomeLightDelay(int value) {
        return this.mSettingManagerImpl.setBackHomeLightDelay(value);
    }

    public int getBackHomeLightDelay() {
        return this.mSettingManagerImpl.getBackHomeLightDelay();
    }

    public int getLeftHomeLightDelayValue() {
        return this.mSettingManagerImpl.getLeftHomeLightDelayValue();
    }

    public int setLeftHomeLightDelayValue(int value) {
        return this.mSettingManagerImpl.setLeftHomeLightDelayValue(value);
    }

    public void turnSeatbackrestPercent(int location, int percent) {
        this.mSettingManagerImpl.turnSeatbackrestPercent(location, percent);
    }

    public void turnSeatHorizationPercent(int location, int percent) {
        this.mSettingManagerImpl.turnSeatHorizationPercent(location, percent);
    }

    public void turnSeatHeightPercent(int location, int percent) {
        this.mSettingManagerImpl.turnSeatHeightPercent(location, percent);
    }

    public int setStartOrPowerInd(int value) {
        return this.mSettingManagerImpl.setStartOrPowerInd(value);
    }

    public int getStartOrPowerInd() {
        return this.mSettingManagerImpl.getStartOrPowerInd();
    }

    public int setParkBrakeInd(int value) {
        return this.mSettingManagerImpl.setParkBrakeInd(value);
    }

    public int getParkBrakeInd() {
        return this.mSettingManagerImpl.getParkBrakeInd();
    }

    public int setDriveDoorCloseInd(int value) {
        return this.mSettingManagerImpl.setDriveDoorCloseInd(value);
    }

    public int getDriveDoorCloseInd() {
        return this.mSettingManagerImpl.getDriveDoorCloseInd();
    }

    public int setSafeBeltInd(int value) {
        return this.mSettingManagerImpl.setSafeBeltInd(value);
    }

    public int getSafeBeltInd() {
        return this.mSettingManagerImpl.getSafeBeltInd();
    }

    public int setLeaveCarPInd(int value) {
        return this.mSettingManagerImpl.setLeaveCarPInd(value);
    }

    public int getLeaveCarPInd() {
        return this.mSettingManagerImpl.getLeaveCarPInd();
    }

    public int setKeyPowerLowInd(int value) {
        return this.mSettingManagerImpl.setKeyPowerLowInd(value);
    }

    public int getKeyPowerLowInd() {
        return this.mSettingManagerImpl.getKeyPowerLowInd();
    }

    public int setMissKeyInd(int value) {
        return this.mSettingManagerImpl.setMissKeyInd(value);
    }

    public int getMissKeyInd() {
        return this.mSettingManagerImpl.getMissKeyInd();
    }

    public int setNightDRLightOnInd(int value) {
        return this.mSettingManagerImpl.setNightDRLightOnInd(value);
    }

    public int getNightDRLightOnInd() {
        return this.mSettingManagerImpl.getNightDRLightOnInd();
    }

    public int setLowOilInd(int value) {
        return this.mSettingManagerImpl.setLowOilInd(value);
    }

    public int getLowOilInd() {
        return this.mSettingManagerImpl.getLowOilInd();
    }

    public int setLowBatteryInd(int value) {
        return this.mSettingManagerImpl.setLowBatteryInd(value);
    }

    public int getLowBatteryInd() {
        return this.mSettingManagerImpl.getLowBatteryInd();
    }

    public int setStopRemoteCtrlDriveInd(int value) {
        return this.mSettingManagerImpl.setStopRemoteCtrlDriveInd(value);
    }

    public int getStopRemoteCtrlDriveInd() {
        return this.mSettingManagerImpl.getStopRemoteCtrlDriveInd();
    }

    public int getAutomaticParking() {
        return this.mSettingManagerImpl.getAutomaticParking();
    }

    public int setAutomaticParking(int value) {
        return this.mSettingManagerImpl.setAutomaticParking(value);
    }

    public int getIALColor(int area) {
        return this.mSettingManagerImpl.getIALColor(area);
    }

    public int setIALColor(int area, int color, int source) {
        return this.mSettingManagerImpl.setIALColor(area, color, source);
    }

    public int getIALBrightness(int area) {
        return this.mSettingManagerImpl.getIALBrightness(area);
    }

    public int setIALBrightness(int area, int brightness, int source) {
        return this.mSettingManagerImpl.setIALBrightness(area, brightness, source);
    }

    public int getIALArea() {
        return this.mSettingManagerImpl.getIALArea();
    }

    public int setIALArea(int area, int source) {
        return this.mSettingManagerImpl.setIALArea(area, source);
    }

    public int setIALInfo(int area, int color, int brightness, int source) {
        return this.mSettingManagerImpl.setIALInfo(area, color, brightness, source);
    }

    public int getSeatHeatingState(int seatId) {
        return this.mSettingManagerImpl.getSeatHeatingState(seatId);
    }

    public int setSeatHeatingState(int seatId, int state) {
        return this.mSettingManagerImpl.setSeatHeatingState(seatId, state);
    }

    public int getSeatVentilatingState(int seatId) {
        return this.mSettingManagerImpl.getSeatVentilatingState(seatId);
    }

    public int setSeatVentilatingState(int seatId, int state) {
        return this.mSettingManagerImpl.setSeatVentilatingState(seatId, state);
    }

    public int getLanternLanguageMode() {
        return this.mSettingManagerImpl.getLanternLanguageMode();
    }

    public int setLanternLanguageMode(int mode) {
        return this.mSettingManagerImpl.setLanternLanguageMode(mode);
    }

    public int getINSTheme() {
        return this.mSettingClusterManagerImpl.getINSTheme();
    }

    public int setINSTheme(int value) {
        return this.mSettingClusterManagerImpl.setINSTheme(value);
    }

    public int resetSystemSetting(int state) {
        return this.mSettingManagerImpl.resetSystemSetting(state);
    }

    public int factoryResetAll(int state) {
        return this.mSettingManagerImpl.factoryResetAll(state);
    }

    public int setHeadlampLevel(int level) {
        return this.mSettingManagerImpl.setHeadlampLevel(level);
    }

    public int getLeftHeadlampLevel() {
        return this.mSettingManagerImpl.getLeftHeadlampLevel();
    }

    public int getRightHeadlampLevel() {
        return this.mSettingManagerImpl.getRightHeadlampLevel();
    }

    public int getHeadlampLevel() {
        return this.mSettingManagerImpl.getHeadlampLevel();
    }

    public int setSmartWelcomeLightState(int state) {
        return this.mSettingManagerImpl.setSmartWelcomeLightState(state);
    }

    public int getSmartWelcomeLightState() {
        return this.mSettingManagerImpl.getSmartWelcomeLightState();
    }

    public int sendOffReminder(int value) {
        return this.mSettingManagerImpl.sendOffReminder(value);
    }

    public int getOffReminder() {
        return this.mSettingManagerImpl.getOffReminder();
    }

    public int getRearAcPanelAutoLockTime() {
        return this.mSettingManagerImpl.getRearAcPanelAutoLockTime();
    }

    public int setRearAcPanelAutoLockTime(int time) {
        return this.mSettingManagerImpl.setRearAcPanelAutoLockTime(time);
    }

    public int getExhibitionMode() {
        return this.mSettingManagerImpl.getExhibitionMode();
    }

    public int getExhibitionStartEnginePrompt() {
        return this.mSettingManagerImpl.getExhibitionStartEnginePrompt();
    }

    public int setExhibitionStartEnginePermit(int value) {
        return this.mSettingManagerImpl.setExhibitionStartEnginePermit(value);
    }

    public int getSteeringWheelHeatingState() {
        return this.mSettingManagerImpl.getSteeringWheelHeatingState();
    }

    public int setSteeringWheelHeatingState(int state) {
        return this.mSettingManagerImpl.setSteeringWheelHeatingState(state);
    }

    public int getInsideLightDoorState() {
        return this.mSettingManagerImpl.getInsideLightDoorState();
    }

    public int setInsideLightDoorState(int state) {
        return this.mSettingManagerImpl.setInsideLightDoorState(state);
    }

    public int setPadRotation(int rotation) {
        return this.mSettingManagerImpl.setPadRotation(rotation);
    }

    public int resetSetting(int[] featureIds, int[] values) {
        return this.mSettingManagerImpl.resetSetting(featureIds, values);
    }

    public int setMassageMode(int type, int state) {
        return this.mSettingManagerImpl.setMassageMode(type, state);
    }

    public int getMassageMode(int type) {
        return this.mSettingManagerImpl.getMassageMode(type);
    }

    public int getMassageLevel(int type) {
        return this.mSettingManagerImpl.getMassageLevel(type);
    }

    public int setMassageLevel(int type, int state) {
        return this.mSettingManagerImpl.setMassageLevel(type, state);
    }

    public int saveSeatParamsAll(int postion, int location) {
        return this.mSettingManagerImpl.saveSeatParamsAll(postion, location);
    }

    public int hasFeature(String feature) {
        return 0;
    }

    public int getAutoLockSwitch() {
        return 0;
    }

    public int getACAutoWindLevel() {
        return 0;
    }

    public int setIntelligentVoiceIND(int value) {
        return 0;
    }

    public int getIntelligentVoiceIND() {
        return 0;
    }

    public int setVoiceINDLevel(int value) {
        return 0;
    }

    public int getVoiceINDLevel() {
        return 0;
    }

    public int getRearViewMirrorAngle() {
        return 0;
    }

    public int setLeftViewMirrorFlipAngle(int value) {
        return 0;
    }

    public int getLeftViewMirrorFlipAngle() {
        return 0;
    }

    public int setRightViewMirrorFlipAngle(int value) {
        return 0;
    }

    public int getRightViewMirrorFlipAngle() {
        return 0;
    }

    public int setBackRowControl(int value) {
        return 0;
    }

    public int getBackRowControl() {
        return 0;
    }

    public int setPM25Switch(int value) {
        return 0;
    }

    public int getPM25Switch() {
        return 0;
    }

    public int setLongPressUnlockWindow(int value) {
        return 0;
    }

    public int getLongPressUnlockWindow() {
        return 0;
    }

    public int setAutoLockSwitch(int value) {
        return 0;
    }

    public int setDoorLock(int value) {
        return 0;
    }

    public int getDoorLock() {
        return 0;
    }

    public int setCarRecorderRecording(int value) {
        return 0;
    }

    public int getCarRecorderRecording() {
        return 0;
    }

    public int getRearMirrorFlip() {
        return 0;
    }

    public int getRemoteControlUpwindow() {
        return 0;
    }

    public int getRemoteControlDownwindow() {
        return 0;
    }

    public int getLockUpwindow() {
        return 0;
    }

    public int getUnlockDownwindow() {
        return 0;
    }

    public int getLPSwitchUpwindow() {
        return 0;
    }

    public int getLPSwitchDownwindow() {
        return 0;
    }

    public int getHomeLightTime() {
        return 0;
    }

    public int getLeaveHomeLightTime() {
        return 0;
    }

    public int getDriverSeatBack() {
        return 0;
    }

    public int getSteerBack() {
        return 0;
    }

    public int getAutoLock() {
        return 0;
    }

    public int getDrivingRecorder() {
        return 0;
    }

    public int getBasePadAutoRise() {
        return 0;
    }

    public int setDrivingRecorderSwitchState(int value) {
        return 0;
    }

    public int setDrivingRecorderUIStatus(int value) {
        return 0;
    }

    public int getDrivingRecorderSwitchState() {
        return 0;
    }

    public int setInsideRearMirrorScreenSwitchState(int value) {
        return 0;
    }

    public int getInsideRearMirrorScreenSwitchState() {
        return 0;
    }

    public int setInsideRearMirrorScreenState(int value) {
        return 0;
    }

    public int setFormatMemoryCard(int value) {
        return 0;
    }

    public int setLockReplayVideo(int value) {
        return 0;
    }

    public int setBasePadAutoRiseState(int value) {
        return 0;
    }

    public int getBasePadAutoRiseState() {
        return 0;
    }

    public int getElecHandbrakeState() {
        return 0;
    }

    public int getDualTempAreaAcOnlineState() {
        return 0;
    }

    public int getBackDoorElectricModeOnlineState() {
        return 0;
    }

    public int getRearViewMirrorAutoFoldModeOnlineState() {
        return 0;
    }

    public int setRearViewMirrorAutoFoldMode(int value) {
        return 0;
    }

    public int getRearViewMirrorAutoFoldMode() {
        return 0;
    }

    public int getRemoteDrivingOnlineState() {
        return 0;
    }

    public int getUnlockSettingOnlineState() {
        return 0;
    }

    public int setUnlockSettingMode(int value) {
        return 0;
    }

    public int getUnlockSettingMode() {
        return 0;
    }

    public int getPanoramaSunRoofOnlineState() {
        return 0;
    }

    public int restoreDrDefaultValue() {
        return 0;
    }

    public int restoreCarDefaultValue() {
        return 0;
    }

    public int getILDuration() {
        return 0;
    }

    public int setAutoTime(int value) {
        return 0;
    }

    public int getAutoTime() {
        return 0;
    }

    public int getAutoLockTime() {
        return 0;
    }

    public int setAutoLockTime(int time) {
        return 0;
    }

    public int getRemoteCtlUnlockingState() {
        return 0;
    }

    public int setRemoteCtlUnlockingState(int state) {
        return 0;
    }

    public int getCourtesyLampTime() {
        return 0;
    }

    public int setCourtesyLampTime(int time) {
        return 0;
    }

    public int getWindscreenWiperOverhaulState(int area) {
        return 0;
    }

    public int setWindscreenWiperOverhaulState(int area, int state) {
        return 0;
    }

    public int getNightDriveLightOnReminder() {
        return 0;
    }

    public int getBTCallReductionWindMenuState() {
        return 0;
    }

    public int getEngineOilLevel() {
        return 0;
    }

    public int getEngineOilUpdateSignal() {
        return 0;
    }

    public int takeDrivingRecorderPicture() {
        return 0;
    }

    public int getEngineOilExitUpdateState() {
        return 0;
    }

    public int setEngineOilExitUpdateState(int state) {
        return 0;
    }

    public int getMultimediaMotorTemperatureState() {
        return 0;
    }

    public int getSOCConfig() {
        return 0;
    }

    public int setMcuPH2State(int state) {
        return 0;
    }

    public int turnOffInsideLight() {
        return 0;
    }

    public int turnOffInsideLight(int state) {
        return 0;
    }

    public int getStartKeyState() {
        return 0;
    }

    public int getSeatHeatingState1(int seatId) {
        return 0;
    }

    public int setSeatHeatingState1(int seatId, int state) {
        return 0;
    }

    public int getInsBacklightCtl() {
        return 0;
    }

    public int reset(int item, int value) {
        return 0;
    }

    public int setBTCallState(int state) {
        return 0;
    }

    public int getExternalRearMirrorAutoFoldSetting() {
        return 0;
    }

    public int getSteerType() {
        return 0;
    }

    public byte[] getBTMacAddr() {
        return new byte[0];
    }

    public int getIKEYBTLowPowerMode() {
        return 0;
    }

    public int sendBTMacToIKEY(byte[] addr) {
        return 0;
    }

    public int setUserdefinedBodyColor(int value) {
        return 0;
    }

    public int setCarSetReset(int doorWindowResetState, int lockResetState, int seatSelfReturnResetState, int rearMirrorResetState, int atmpResetState, int readingLightResetState, int backLightResetState, int acResetState, int equalizerState, int soundfieldState) {
        return 0;
    }

    public int setAirLightPanelState(int state) {
        return 0;
    }

    public int getAirLightPanelState() {
        return 0;
    }

    public int setTextInfo(int textState, int textNum, int textSize, int textRedColor, int textGreenColor, int textBlueColor, int animationType, int playSpeed, int textBackground) {
        return 0;
    }

    public int getAirLightTextState() {
        return 0;
    }

    public int getAirLightTextNumber() {
        return 0;
    }

    public int setTextContent(byte[] text) {
        return 0;
    }

    public int setFestivalInfo(int info) {
        return 0;
    }

    public int getCruiseBulletBox() {
        return 0;
    }

    public int setWeatherAndTempState(int weatherState, int tempValue) {
        return 0;
    }

    public int getLearnerDriverModeSwitchState() {
        return 0;
    }

    public int getLearnerDriverModeState() {
        return 0;
    }

    public int getLearnerDriverAccLimitState() {
        return 0;
    }

    public int setLearnerDriverModeSwitchState(int state) {
        return 0;
    }

    public int getVehicleState() {
        return 0;
    }

    public int getInstrumentAcKeyType() {
        return 0;
    }

    public int getHandleAutoResetTime() {
        return 0;
    }

    public int setHandleAutoResetTime(int time) {
        return 0;
    }

    public int getEPedalMode() {
        return 0;
    }

    public int setEPedalMode(int value) {
        return 0;
    }

    public int setWeatherForcast(byte[] value) {
        return 0;
    }

    public int getDmPlatform() {
        return 0;
    }

    public int getLeftFieldPlatform() {
        return 0;
    }

    public int setDigitalPanelTestMode(int value) {
        return 0;
    }

    public int setAllStatus() {
        return 0;
    }

    public int queryAllStatus() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        return false;
    }

    public int getKeypadToneState() {
        return this.mSettingManagerImpl.getKeypadToneState();
    }

    public int setKeypadToneState(int state) {
        return this.mSettingManagerImpl.setKeypadToneState(state);
    }

    public void setKeypadToneStatechanged(int state) {
        this.mSettingManagerImpl.setKeypadToneStatechanged(state);
    }

    public int getLanguage() {
        Log.d(TAG, "getLanguage");
        return this.mSettingClusterManagerImpl.getLanguage();
    }

    public int setLanguage(int value) {
        Log.d(TAG, TestSettingManagerImplCallback.SETLANGUAGE);
        return this.mSettingClusterManagerImpl.setLanguage(value);
    }

    public int getComSpeedUpState() {
        return this.mSettingManagerImpl.getComSpeedUpState();
    }

    public int setComSpeedUpState(int value) {
        return this.mSettingManagerImpl.setComSpeedUpState(value);
    }

    public int getComSpeedDownState() {
        return this.mSettingManagerImpl.getComSpeedDownState();
    }

    public int setComSpeedDownState(int value) {
        return this.mSettingManagerImpl.setComSpeedDownState(value);
    }

    public int getHSpeedAutoCloseState() {
        return this.mSettingManagerImpl.getHSpeedAutoCloseState();
    }

    public int setHSpeedAutoCloseState(int value) {
        return this.mSettingManagerImpl.setHSpeedAutoCloseState(value);
    }

    public int getRuningCloseDoorState() {
        return this.mSettingManagerImpl.getRuningCloseDoorState();
    }

    public int setRuningCloseDoorState(int value) {
        return this.mSettingManagerImpl.setRuningCloseDoorState(value);
    }

    public int getRearViewMirrorFlipSet() {
        return this.mSettingManagerImpl.getRearViewMirrorFlipSet();
    }

    public int setRearViewMirrorFlipSet(int value) {
        return 0;
    }

    public int voiceCtlBackDoor(int cmd) {
        return this.mSettingManagerImpl.voiceCtlBackDoor(cmd);
    }

    public int getAidedDrivingInd() {
        return this.mSettingManagerImpl.getAidedDrivingInd();
    }

    public int setAidedDrivingInd(int value) {
        return this.mSettingManagerImpl.setAidedDrivingInd(value);
    }

    public int getHeadLampConfig() {
        return this.mSettingManagerImpl.getHeadLampConfig();
    }

    public void turnCushionPercent(int location, int percent) {
        this.mSettingManagerImpl.turnCushionPercent(location, percent);
    }

    public void turnCaliperSplintPercent(int location, int percent) {
        this.mSettingManagerImpl.turnCaliperSplintPercent(location, percent);
    }

    public void turnHeadrestHeightPercent(int location, int percent) {
        this.mSettingManagerImpl.turnHeadrestHeightPercent(location, percent);
    }

    public void turnHeadrestHorizationPercent(int location, int percent) {
        this.mSettingManagerImpl.turnHeadrestHorizationPercent(location, percent);
    }

    public int getHudPattern() {
        return this.mSettingManagerImpl.getHudPattern();
    }

    public int setHudPattern(int mode) {
        return this.mSettingManagerImpl.setHudPattern(mode);
    }

    public int getHudSafeDriveState() {
        return this.mSettingManagerImpl.getHudSafeDriveState();
    }

    public int setHudSafeDriveState(int state) {
        return this.mSettingManagerImpl.setHudSafeDriveState(state);
    }

    public int getHudNavigationState() {
        return this.mSettingManagerImpl.getHudNavigationState();
    }

    public int setHudNavigationState(int state) {
        return this.mSettingManagerImpl.setHudNavigationState(state);
    }

    public int getHudScreenProjectionState() {
        return this.mSettingManagerImpl.getHudScreenProjectionState();
    }

    public int setHudScreenProjectionState(int state) {
        return this.mSettingManagerImpl.setHudScreenProjectionState(state);
    }

    public int getSeatHeatingNotL1PState() {
        return this.mSettingManagerImpl.getSeatHeatingNotL1PState();
    }

    public int getSeatVentilatingNotL1PState() {
        return this.mSettingManagerImpl.getSeatVentilatingNotL1PState();
    }

    public int get5LevelSeatVentilatingState(int seatId) {
        return this.mSettingManagerImpl.get5LevelSeatVentilatingState(seatId);
    }

    public int set5LevelSeatVentilatingState(int seatId, int state) {
        return this.mSettingManagerImpl.set5LevelSeatVentilatingState(seatId, state);
    }

    public int get5LevelSeatHeatingState(int seatId) {
        return this.mSettingManagerImpl.get5LevelSeatHeatingState(seatId);
    }

    public int set5LevelSeatHeatingState(int seatId, int state) {
        return this.mSettingManagerImpl.set5LevelSeatHeatingState(seatId, state);
    }

    public int set5LevelSeatHeatingMode(int seatId, int state) {
        return this.mSettingManagerImpl.set5LevelSeatHeatingMode(seatId, state);
    }

    public int setSteeringWheelHeatingGear(int value) {
        return this.mSettingManagerImpl.setSteeringWheelHeatingGear(value);
    }

    public int getSteeringWheelHeatingGear() {
        return this.mSettingManagerImpl.getSteeringWheelHeatingGear();
    }

    public int setSteeringWheelHeatingAutoState(int stat) {
        return this.mSettingManagerImpl.setSteeringWheelHeatingAutoState(stat);
    }

    public int getSteeringWheelHeatingAutoState() {
        return this.mSettingManagerImpl.getSteeringWheelHeatingAutoState();
    }

    public int getMainHsMassageState() {
        return this.mSettingManagerImpl.getMainHsMassageState();
    }

    public int getHevChargingPort() {
        return this.mSettingManagerImpl.getHevChargingPort();
    }

    public int getKeyVibratorLevel() {
        return this.mSettingManagerImpl.getKeyVibratorLevel();
    }

    public int setKeyVibratorLevel(int level) {
        return this.mSettingManagerImpl.setKeyVibratorLevel(level);
    }

    public int setMode(int mode) {
        return this.mSettingManagerImpl.setMode(mode);
    }

    public int setRestMode(int state) {
        return this.mSettingManagerImpl.setRestMode(state);
    }

    public int setPetMode(int state) {
        return this.mSettingManagerImpl.setPetMode(state);
    }

    public int setWipeMode(int state) {
        return this.mSettingManagerImpl.setWipeMode(state);
    }

    public int setMeditationMode(int state) {
        return this.mSettingManagerImpl.setMeditationMode(state);
    }

    public int setCamping(int state) {
        return this.mSettingManagerImpl.setCamping(state);
    }

    public int getSeatVentilatingState2(int seatId) {
        return this.mSettingManagerImpl.getSeatVentilatingState2(seatId);
    }

    public int getSeatHeatingState2(int seatId) {
        return this.mSettingManagerImpl.getSeatHeatingState2(seatId);
    }

    public int get5LevelSeatVentilatingMode(int seatId) {
        return this.mSettingManagerImpl.get5LevelSeatVentilatingMode(seatId);
    }

    public int set5LevelSeatVentilatingMode(int seatId, int state) {
        return this.mSettingManagerImpl.set5LevelSeatVentilatingMode(seatId, state);
    }

    public int get5LevelSeatHeatingMode(int seatId) {
        return this.mSettingManagerImpl.get5LevelSeatHeatingMode(seatId);
    }

    public int set5LevelSeatVentilatingLevel(int seatId, int state) {
        return this.mSettingManagerImpl.set5LevelSeatVentilatingLevel(seatId, state);
    }

    public int set5LevelSeatHeatingLevel(int seatId, int state) {
        return this.mSettingManagerImpl.set5LevelSeatHeatingLevel(seatId, state);
    }

    public int get5LevelSeatVentilatingLevel(int seatId) {
        return this.mSettingManagerImpl.get5LevelSeatVentilatingLevel(seatId);
    }

    public int get5LevelSeatHeatingLevel(int seatId) {
        return this.mSettingManagerImpl.get5LevelSeatHeatingLevel(seatId);
    }

    public int getEcuMassageLevel(int seatId) {
        return this.mSettingManagerImpl.getEcuMassageLevel(seatId);
    }

    public int getEcuMassageMode(int seatId) {
        return this.mSettingManagerImpl.getEcuMassageMode(seatId);
    }

    public int getHsMassageState(int seatId) {
        return this.mSettingManagerImpl.getHsMassageState(seatId);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        return this.mSettingManagerImpl.get(eventTypes, type);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] eventTypes, BYDAutoEventValue eventValue) {
        return this.mSettingManagerImpl.set(eventTypes, eventValue);
    }

    public int getSafeBeltWarnState() {
        return this.mSettingManagerImpl.getSafeBeltWarnState();
    }

    public int setSafeBeltWarnState(int state) {
        return this.mSettingManagerImpl.setSafeBeltWarnState(state);
    }

    @Deprecated
    public int carSettingsReset(int state) {
        return this.mSettingManagerImpl.carSettingsReset(state);
    }

    public int driveSettingsReset(int state) {
        return this.mSettingManagerImpl.driveSettingsReset(state);
    }

    @Deprecated
    public int carSettingsDefaultReset(int state) {
        return this.mSettingManagerImpl.carSettingsDefaultReset(state);
    }

    public int newEnergyDefaultReset(int state) {
        return this.mSettingManagerImpl.newEnergyDefaultReset(state);
    }

    @Deprecated
    public int doorsAndWindowDefaultResset(int state) {
        return this.mSettingManagerImpl.doorsAndWindowDefaultResset(state);
    }

    @Deprecated
    public int locksDefaultReset(int state) {
        return this.mSettingManagerImpl.locksDefaultReset(state);
    }

    @Deprecated
    public int rearMirrorDefaultReset(int state) {
        return this.mSettingManagerImpl.rearMirrorDefaultReset(state);
    }

    @Deprecated
    public int autoBackDefaultReset(int state) {
        return this.mSettingManagerImpl.autoBackDefaultReset(state);
    }

    public int carHealthDefaultReset(int state) {
        return this.mSettingManagerImpl.carHealthDefaultReset(state);
    }

    @Deprecated
    public int ialDefaultReset(int state) {
        return this.mSettingManagerImpl.ialDefaultReset(state);
    }

    @Deprecated
    public int lightDefaultReset(int state) {
        return this.mSettingManagerImpl.lightDefaultReset(state);
    }

    @Deprecated
    public int backLightDefaultReset(int state) {
        return this.mSettingManagerImpl.backLightDefaultReset(state);
    }

    @Deprecated
    public int safeAssistDefaultReset(int state) {
        return this.mSettingManagerImpl.safeAssistDefaultReset(state);
    }

    @Deprecated
    public int intelligentDriveDefaultReset(int state) {
        return this.mSettingManagerImpl.intelligentDriveDefaultReset(state);
    }

    @Deprecated
    public int laneAssistDriveDefaultReset(int state) {
        return this.mSettingManagerImpl.laneAssistDriveDefaultReset(state);
    }

    @Deprecated
    public int equalizerDefaultReset(int state) {
        return this.mSettingManagerImpl.equalizerDefaultReset(state);
    }

    @Deprecated
    public int soundFiledDefaultReset(int state) {
        return this.mSettingManagerImpl.soundFiledDefaultReset(state);
    }

    public int clockDefaultReset(int state) {
        return this.mSettingManagerImpl.clockDefaultReset(state);
    }

    public int languageDefaultReset(int state) {
        return this.mSettingManagerImpl.languageDefaultReset(state);
    }

    @Deprecated
    public int unitDefaultReset(int state) {
        return this.mSettingManagerImpl.unitDefaultReset(state);
    }

    public int outChargeDefaultReset(int state) {
        return this.mSettingManagerImpl.outChargeDefaultReset(state);
    }

    public int setUiVersion(int state) {
        return this.mSettingManagerImpl.setUiVersion(state);
    }

    public int setHsMassageState(int type, int state) {
        return this.mSettingManagerImpl.setHsMassageState(type, state);
    }

    public int getPABCode() {
        return this.mSettingManagerImpl.getPabCode();
    }

    public int getMissPackageCount() {
        return this.mSettingManagerImpl.getMissPackageCount();
    }

    public int getTrayActionState() {
        return this.mSettingManagerImpl.getTrayActionState();
    }

    public int getScreenAngle() {
        return this.mSettingManagerImpl.getScreenAngle();
    }

    public int getScreenRotationAction() {
        return this.mSettingManagerImpl.getScreenRotationAction();
    }

    public int getScreenAntiPinchState() {
        return this.mSettingManagerImpl.getScreenAntiPinchState();
    }

    public int getScreenAngleValue() {
        return this.mSettingManagerImpl.getScreenAngleValue();
    }

    public int getScreenVoltageState() {
        return this.mSettingManagerImpl.getScreenVoltageState();
    }

    public int getScreenRotationOverHotState() {
        return this.mSettingManagerImpl.getScreenRotationOverHotState();
    }

    public int isSomethingLeftInRefrigerator() {
        return this.mSettingManagerImpl.isSomethingLeftInRefrigerator();
    }

    public int setInternationalLanguage(int value) {
        return this.mSettingManagerImpl.setInternationalLanguage(value);
    }

    public int getPABState() {
        return this.mSettingManagerImpl.getPabState();
    }

    public int getMainSaveOrRecoverState() {
        return this.mSettingManagerImpl.getMainSaveOrRecoverState();
    }

    public int getMainSaveOrRecoveFailCode() {
        return this.mSettingManagerImpl.getMainSaveOrRecoveFailCode();
    }

    public int getMain2SaveOrRecoverState() {
        return this.mSettingManagerImpl.getMain2SaveOrRecoverState();
    }

    public int getMain2SaveOrRecoveFailCode() {
        return this.mSettingManagerImpl.getMain2SaveOrRecoveFailCode();
    }

    public int getPassangerSaveOrRecoverState() {
        return this.mSettingManagerImpl.getPassangerSaveOrRecoverState();
    }

    public int getRearLeftSaveOrRecoverState() {
        return this.mSettingManagerImpl.getRearLeftSaveOrRecoverState();
    }

    public int getRearLeftEcuSaveOrRecoverState() {
        return this.mSettingManagerImpl.getRearLeftEcuSaveOrRecoverState();
    }

    public int getRearRightSaveOrRecoverState() {
        return this.mSettingManagerImpl.getRearRightSaveOrRecoverState();
    }

    public int getRearRightEcuSaveOrRecoverState() {
        return this.mSettingManagerImpl.getRearRightEcuSaveOrRecoverState();
    }

    public int setScreenRotation(int orientation) {
        return this.mSettingManagerImpl.setScreenRotation(orientation);
    }

    public int setScreenRotationLock(int state) {
        return this.mSettingManagerImpl.setScreenRotationLock(state);
    }

    public int setPABState(int state) {
        return this.mSettingManagerImpl.setPabState(state);
    }

    public int getDoorHeightSupport() {
        return this.mSettingManagerImpl.getDoorHeightSupport();
    }

    public int getElectroniChildLockConfig() {
        return this.mSettingManagerImpl.getElectroniChildLockConfig();
    }

    public int getAmbientAreaSupport() {
        return this.mSettingManagerImpl.getAmbientAreaSupport();
    }

    public int getAmbientLightColorSupport() {
        return this.mSettingManagerImpl.getAmbientLightColorSupport();
    }

    public int getAmbientLightSupport() {
        return this.mSettingManagerImpl.getAmbientLightSupport();
    }

    public int getAutoExternalRearMirrorFollowUpSwitchSupport() {
        return this.mSettingManagerImpl.getAutoExternalRearMirrorFollowUpSwitchSupport();
    }

    public int getSupportSeatMassage(int area) {
        return this.mSettingManagerImpl.getSupportSeatMassage(area);
    }

    public int getAmbientFeedBack() {
        return this.mSettingManagerImpl.getAmbientFeedBack();
    }

    public int getAmbientMusicModeState(int area) {
        return this.mSettingManagerImpl.getAmbientMusicModeState(area);
    }

    public int setAmbientMusicModeState(int area, int state) {
        return this.mSettingManagerImpl.setAmbientMusicModeState(area, state);
    }

    @Deprecated
    public int getAmbientMusicMode(int area) {
        return this.mSettingManagerImpl.getAmbientMusicMode(area);
    }

    @Deprecated
    public int setAmbientMusicMode(int area, int value) {
        return this.mSettingManagerImpl.setAmbientMusicMode(area, value);
    }

    @Deprecated
    public int getAmbientMulticolorState(int area) {
        return this.mSettingManagerImpl.getAmbientMulticolorState(area);
    }

    @Deprecated
    public int setAmbientMulticolorState(int area, int value) {
        return this.mSettingManagerImpl.setAmbientMulticolorState(area, value);
    }

    @Deprecated
    public int getAmbientMulticolorMode(int area) {
        return this.mSettingManagerImpl.getAmbientMulticolorMode(area);
    }

    @Deprecated
    public int setAmbientMulticolorMode(int area, int value) {
        return this.mSettingManagerImpl.setAmbientMulticolorMode(area, value);
    }

    public int get1LevelSeatHeatingState(int seatId) {
        return this.mSettingManagerImpl.get1LevelSeatHeatingState(seatId);
    }

    public int getTrailerModeState() {
        return this.mSettingManagerImpl.getTrailerModeState();
    }

    public int setTrailerModeState(int state) {
        return this.mSettingManagerImpl.setTrailerModeState(state);
    }

    public int getTrailerLightCheckState() {
        return this.mSettingManagerImpl.getTrailerLightCheckState();
    }

    public int setTrailerLightCheckState(int state) {
        return this.mSettingManagerImpl.setTrailerLightCheckState(state);
    }

    public int getCVCMode() {
        return this.mSettingManagerImpl.getCvcMode();
    }

    public int setCVCMode(int mode) {
        return this.mSettingManagerImpl.setCvcMode(mode);
    }

    public int getDyHeightToggleState() {
        return this.mSettingManagerImpl.getDyHeightToggleState();
    }

    public int setDyHeightToggleState(int state) {
        return this.mSettingManagerImpl.setDyHeightToggleState(state);
    }

    public int getDyHeightLevel() {
        return this.mSettingManagerImpl.getDyHeightLevel();
    }

    public int setDyHeightValue(int value) {
        return this.mSettingManagerImpl.setDyHeightValue(value);
    }

    public int getFdDyHeightToggleState() {
        return this.mSettingManagerImpl.getFdDyHeightToggleState();
    }

    public int getFdDyHeightLevel() {
        return this.mSettingManagerImpl.getFdDyHeightLevel();
    }

    public int getWelcomeState() {
        return this.mSettingManagerImpl.getWelcomeState();
    }

    public int setWelcomeState(int state) {
        return this.mSettingManagerImpl.setWelcomeState(state);
    }

    public int getWelcomeSupported() {
        return this.mSettingManagerImpl.getWelcomeSupported();
    }

    public int getFdWelcomeState() {
        return this.mSettingManagerImpl.getFdWelcomeState();
    }

    public int getFdWelcomeSupported() {
        return this.mSettingManagerImpl.getFdWelcomeSupported();
    }

    public int setTailBoxTakeState(int state) {
        return this.mSettingManagerImpl.setTailBoxTakeState(state);
    }

    public int getFdTailBoxTakeSupported() {
        return this.mSettingManagerImpl.getFdTailBoxTakeSupported();
    }

    public int getTailBoxTakeState() {
        return this.mSettingManagerImpl.getTailBoxTakeState();
    }

    public int getTailBoxTakeSupported() {
        return this.mSettingManagerImpl.getTailBoxTakeSupported();
    }

    public int getCampingBlanceState() {
        return this.mSettingManagerImpl.getCampingBlanceState();
    }

    public int setCampingBlanceState(int state) {
        return this.mSettingManagerImpl.setCampingBlanceState(state);
    }

    public int getCampingBlanceAction() {
        return this.mSettingManagerImpl.getCampingBlanceAction();
    }

    public int getCampingBlanceSupported() {
        return this.mSettingManagerImpl.getCampingBlanceSupported();
    }

    public int getFdCampingBlanceState() {
        return this.mSettingManagerImpl.getFdCampingBlanceState();
    }

    public int getFdCampingBlanceAction() {
        return this.mSettingManagerImpl.getFdCampingBlanceAction();
    }

    public int getFdCampingBlanceSupported() {
        return this.mSettingManagerImpl.getFdCampingBlanceSupported();
    }

    public int getDampState() {
        return this.mSettingManagerImpl.getDampState();
    }

    public int setDampState(int state) {
        return this.mSettingManagerImpl.setDampState(state);
    }

    public int getFdDampState() {
        return this.mSettingManagerImpl.getFdDampState();
    }

    public int getLiftModeState() {
        return this.mSettingManagerImpl.getLiftModeState();
    }

    public int setLiftModeState(int state) {
        return this.mSettingManagerImpl.setLiftModeState(state);
    }

    public int getFdLiftModeState() {
        return this.mSettingManagerImpl.getFdLiftModeState();
    }

    public int getFixedState() {
        return this.mSettingManagerImpl.getFixedState();
    }

    public int getSpeedSupportedTurn() {
        return this.mSettingManagerImpl.getSpeedSupportedTurn();
    }

    public int getModeSupportedHturn() {
        return this.mSettingManagerImpl.getModeSupportedHturn();
    }

    public int getTrailerModeCode() {
        return this.mSettingManagerImpl.getTrailerModeCode();
    }

    public int getFdFixedState() {
        return this.mSettingManagerImpl.getFdFixedState();
    }

    public int getFdSpeedSupportedTurn() {
        return this.mSettingManagerImpl.getFdSpeedSupportedTurn();
    }

    public int getFdModeSupportedHturn() {
        return this.mSettingManagerImpl.getFdModeSupportedHturn();
    }

    public int getFdTrailerModeCode() {
        return this.mSettingManagerImpl.getFdTrailerModeCode();
    }

    public int getDyHeightTurnAction() {
        return this.mSettingManagerImpl.getDyHeightTurnAction();
    }

    public int getWelcomeActive() {
        return this.mSettingManagerImpl.getWelcomeActive();
    }

    public int setDyHeightByVoice(int state) {
        return this.mSettingManagerImpl.setDyHeightByVoice(state);
    }

    public int setMessageCounter(int count) {
        return this.mSettingManagerImpl.setMessageCounter(count);
    }

    public int setCheckSum(int sum) {
        return this.mSettingManagerImpl.setCheckSum(sum);
    }

    public int setOTAMode(int mode) {
        return this.mSettingManagerImpl.setOtaMode(mode);
    }

    public int getFdCRCCheckNum() {
        return this.mSettingManagerImpl.getFdCrcCheckNum();
    }

    public int getFdDyHeightTurnAction() {
        return this.mSettingManagerImpl.getFdDyHeightTurnAction();
    }

    public int getFdFrontBoxTakeState() {
        return this.mSettingManagerImpl.getFdFrontBoxTakeState();
    }

    public int getFdWelcomeActive() {
        return this.mSettingManagerImpl.getFdWelcomeActive();
    }

    public int setVSPState(int state) {
        return this.mSettingManagerImpl.setVspState(state);
    }

    public int getVSPState() {
        return this.mSettingManagerImpl.getVspState();
    }

    public int notifyEnterTunnel(int value) {
        return this.mSettingManagerImpl.notifyEnterTunnel(value);
    }

    public int notifyTunnelLength(int value) {
        return this.mSettingManagerImpl.notifyTunnelLength(value);
    }

    public int getRearViewEcuMirrorFlipState() {
        return this.mSettingManagerImpl.getRearViewEcuMirrorFlipState();
    }

    public int getRearSeatGreetState() {
        return this.mSettingManagerImpl.getRearSeatGreetState();
    }

    public int setRearSeatGreetState(int state) {
        return this.mSettingManagerImpl.setRearSeatGreetState(state);
    }

    public int getBrakeCloseDoor() {
        return this.mSettingManagerImpl.getBrakeCloseDoor();
    }

    public int setBrakeCloseDoor(int state) {
        return this.mSettingManagerImpl.setBrakeCloseDoor(state);
    }

    public int getSafeBeltCloseDriveDoor() {
        return this.mSettingManagerImpl.getSafeBeltCloseDriveDoor();
    }

    public int setSafeBeltCloseDriveDoorState(int state) {
        return this.mSettingManagerImpl.setSafeBeltCloseDriveDoorState(state);
    }

    public int getCloseDoorLevel() {
        return this.mSettingManagerImpl.getCloseDoorLevel();
    }

    public int setCloseDoorLevel(int level) {
        return this.mSettingManagerImpl.setCloseDoorLevel(level);
    }

    public int getBleOpenDriverDoorState() {
        return this.mSettingManagerImpl.getBleOpenDriverDoorState();
    }

    public int setBleOpenDriverDoorState(int state) {
        return this.mSettingManagerImpl.setBleOpenDriverDoorState(state);
    }

    public int getNfcOpenDriverDoor() {
        return this.mSettingManagerImpl.getNfcOpenDriverDoor();
    }

    public int setNfcOpenDriverDoor(int state) {
        return this.mSettingManagerImpl.setNfcOpenDriverDoor(state);
    }

    public int getIntelLockOpenDriverDoor() {
        return this.mSettingManagerImpl.getIntelLockOpenDriverDoor();
    }

    public int setIntelLockOpenDriverDoor(int state) {
        return this.mSettingManagerImpl.setIntelLockOpenDriverDoor(state);
    }

    public int setAFSSwitchSate(int value) {
        return this.mSettingManagerImpl.setAadsSwitchSate(value);
    }

    public int getDriverSeatAutoReturnB3() {
        return this.mSettingManagerImpl.getDriverSeatAutoReturnB3();
    }

    public int setCallState(int state) {
        return this.mSettingManagerImpl.setCallState(state);
    }

    public int getFdTailBoxTakeState() {
        return this.mSettingManagerImpl.getFdTailBoxTakeState();
    }

    public int setScreenAngleValue(int value) {
        return this.mSettingManagerImpl.setScreenAngleValue(value);
    }

    public int setBSISCollisionState(int value) {
        return this.mSettingManagerImpl.setBsisCollisionStateS(value);
    }

    public int setMOISAssembleState(int value) {
        return this.mSettingManagerImpl.setMoisAssembleStateS(value);
    }

    public int setLeftRSESwitchCommand(int value) {
        return this.mSettingManagerImpl.setLeftRseSwitchCommandS(value);
    }

    public int setRightRSESwitchCommand(int value) {
        return this.mSettingManagerImpl.setRightRseSwitchCommandS(value);
    }

    public int setLeftRSEState(int value) {
        return this.mSettingManagerImpl.setLeftRseStateS(value);
    }

    public int setRightRSEState(int value) {
        return this.mSettingManagerImpl.setRightRseStateS(value);
    }

    public int setLRSEChildAreaState(int state) {
        return this.mSettingManagerImpl.setLrseChildAreaStateS(state);
    }

    public int setRRSEChildAreaState(int state) {
        return this.mSettingManagerImpl.setRrseChildAreaStateS(state);
    }

    public int setLRSEChildModeOpenTime(int value) {
        return this.mSettingManagerImpl.setLrseChildModeOpenTimeS(value);
    }

    public int setRRSEChildModeOpenTime(int value) {
        return this.mSettingManagerImpl.setRrseChildModeOpenTimeS(value);
    }

    public int setLRSEChildModeCode1stByte(int value) {
        return this.mSettingManagerImpl.setLrseChildModeCode1stByteS(value);
    }

    public int setLRSEChildModeCode2ndByte(int value) {
        return this.mSettingManagerImpl.setLrseChildModeCode2ndByteS(value);
    }

    public int setLRSEChildModeCode3rdByte(int value) {
        return this.mSettingManagerImpl.setLrseChildModeCode3rdByteS(value);
    }

    public int setLRSEChildModeCode4thByte(int value) {
        return this.mSettingManagerImpl.setLrseChildModeCode4thByteS(value);
    }

    public int setRRSEChildModeCode1stByte(int value) {
        return this.mSettingManagerImpl.setRrseChildModeCode1stByteS(value);
    }

    public int setRRSEChildModeCode2ndByte(int value) {
        return this.mSettingManagerImpl.setRrseChildModeCode2ndByteS(value);
    }

    public int setRRSEChildModeCode3rdByte(int value) {
        return this.mSettingManagerImpl.setRrseChildModeCode3rdByteS(value);
    }

    public int setRRSEChildModeCode4thByte(int value) {
        return this.mSettingManagerImpl.setRrseChildModeCode4thByteS(value);
    }

    public int setSWSMediumRelatedKeys(int value) {
        return this.mSettingManagerImpl.setSwsMediumRelatedKeysS(value);
    }

    public int getBSISCollisionState() {
        return this.mSettingManagerImpl.getBsisCollisionStateR();
    }

    public int getBSISFuncStatus() {
        return this.mSettingManagerImpl.getBsisFuncStatusR();
    }

    public int getMOISAssembleState() {
        return this.mSettingManagerImpl.getMoisAssembleStateR();
    }

    public int getMOISFuncStatus() {
        return this.mSettingManagerImpl.getMoisFuncStatusR();
    }

    public int getLRSEMachineSwitchState() {
        return this.mSettingManagerImpl.getLrseMachineSwitchStateR();
    }

    public int getRRSEMachineSwitchState() {
        return this.mSettingManagerImpl.getRrseMachineSwitchStateR();
    }

    public int getLeftRSEChildModeSwitchStatus() {
        return this.mSettingManagerImpl.getLeftRseChildModeSwitchStatusR();
    }

    public int getRightRSEChildModeSwitchStatus() {
        return this.mSettingManagerImpl.getRightRseChildModeSwitchStatusR();
    }

    public int getLRSEChildModeTimeWriteFb() {
        return this.mSettingManagerImpl.getLrseChildModeTimeWriteFbR();
    }

    public int getRRSEChildModeTimeWriteFb() {
        return this.mSettingManagerImpl.getRrseChildModeTimeWriteFbR();
    }

    public int getLRSEChildModeCodeWriteFb() {
        return this.mSettingManagerImpl.getLrseChildModeCodeWriteFbR();
    }

    public int getRRSEChildModeCodeWriteFb() {
        return this.mSettingManagerImpl.getRrseChildModeCodeWriteFbR();
    }

    public int getIVIProfileSettings() {
        return this.mSettingManagerImpl.getIviProfileSettingsR();
    }

    public int getLeftRearMassageChairConfigurationForECU() {
        return this.mSettingManagerImpl.getLeftRearMassageChairConfigurationForEcu();
    }

    public int getRightRearMassageChairConfigurationForECU() {
        return this.mSettingManagerImpl.getRightRearMassageChairConfigurationForEcu();
    }

    public int getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl() {
        return this.mSettingManagerImpl.getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl();
    }

    public int getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl() {
        return this.mSettingManagerImpl.getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl();
    }

    public int getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl() {
        return this.mSettingManagerImpl.getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl();
    }

    public int getMainDriverSeatAdjustmentConfigurationByGCtrl() {
        return this.mSettingManagerImpl.getMainDriverSeatAdjustmentConfigurationByGCtrl();
    }

    public int getMainDriveElectricLegSupportAdjustmentByGCtrl() {
        return this.mSettingManagerImpl.getMainDriveElectricLegSupportAdjustmentByGCtrl();
    }

    public int getMainDriveElectricHeadrestAdjustmentByGCtrl() {
        return this.mSettingManagerImpl.getMainDriveElectricHeadrestAdjustmentByGCtrl();
    }

    public int getPassengerSeatMemoryFunctionCanByECU() {
        return this.mSettingManagerImpl.getPassengerSeatMemoryFunctionCanByEcu();
    }

    public int getPassengerSeatElectricAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getPassengerSeatElectricAdjustmentConfigurationByEcu();
    }

    public int getPassengerSeatLegSupportAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getPassengerSeatLegSupportAdjustmentConfigurationByEcu();
    }

    public int getPassengerSeatHeadrestConfigurationByECU() {
        return this.mSettingManagerImpl.getPassengerSeatHeadrestConfigurationByEcu();
    }

    public int getRearLeftBidetAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getRearLeftBidetAdjustmentConfigurationByEcu();
    }

    public int getRearLeftHeightAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getRearLeftHeightAdjustmentConfigurationByEcu();
    }

    public int getRearLeftHeadrestLevelAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getRearLeftHeadrestLevelAdjustmentConfigurationByEcu();
    }

    public int getRearLeftHeadrestHeightAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getRearLeftHeadrestHeightAdjustmentConfigurationByEcu();
    }

    public int getRearLeftMemorySwitchConfigurationByECU() {
        return this.mSettingManagerImpl.getRearLeftMemorySwitchConfigurationByEcu();
    }

    public int getRearLeftLevelAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getRearLeftLevelAdjustmentConfigurationByEcu();
    }

    public int getRearLeftBackrestAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getRearLeftBackrestAdjustmentConfigurationByEcu();
    }

    public int getRearLeftLegSupportAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getRearLeftLegSupportAdjustmentConfigurationByEcu();
    }

    public int getLeftRearSeatBackAdjustmentConfiguration() {
        return this.mSettingManagerImpl.getLeftRearSeatBackAdjustmentConfiguration();
    }

    public int getLeftRearSeatHorizontalAdjustmentConfiguration() {
        return this.mSettingManagerImpl.getLeftRearSeatHorizontalAdjustmentConfiguration();
    }

    public int getLeftRearSeatLegBracket() {
        return this.mSettingManagerImpl.getLeftRearSeatLegBracket();
    }

    public int getLeftRearMemoryConfiguration() {
        return this.mSettingManagerImpl.getLeftRearMemoryConfiguration();
    }

    public int getRearSeatReclineConfiguration() {
        return this.mSettingManagerImpl.getRearSeatReclineConfiguration();
    }

    public int getRearRightLevelAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getRearRightLevelAdjustmentConfigurationByEcu();
    }

    public int getRearRightBackrestAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getRearRightBackrestAdjustmentConfigurationByEcu();
    }

    public int getRearRightLegSupportAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getRearRightLegSupportAdjustmentConfigurationByEcu();
    }

    public int getRearRightBidetAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getRearRightBidetAdjustmentConfigurationByEcu();
    }

    public int getRearRightHeightAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getRearRightHeightAdjustmentConfigurationByEcu();
    }

    public int getRearRightHeadrestLevelAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getRearRightHeadrestLevelAdjustmentConfigurationByEcu();
    }

    public int getRearRightHeadrestHeightAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getRearRightHeadrestHeightAdjustmentConfigurationByEcu();
    }

    public int getRearRightMemorySwitchConfigurationByECU() {
        return this.mSettingManagerImpl.getRearRightMemorySwitchConfigurationByEcu();
    }

    public int getRightRearSeatReclineConfigurationByEUC() {
        return this.mSettingManagerImpl.getRightRearSeatReclineConfigurationByEuc();
    }

    public int getRightRearSeatBackAdjustmentConfiguration() {
        return this.mSettingManagerImpl.getRightRearSeatBackAdjustmentConfiguration();
    }

    public int getRightRearSeatHorizontalAdjustmentConfiguration() {
        return this.mSettingManagerImpl.getRightRearSeatHorizontalAdjustmentConfiguration();
    }

    public int getRightRearSeatLegBracket() {
        return this.mSettingManagerImpl.getRightRearSeatLegBracket();
    }

    public int getRightRearMemoryConfiguration() {
        return this.mSettingManagerImpl.getRightRearMemoryConfiguration();
    }

    public int notifyDilinkStarted() {
        return this.mSettingQnxManagerImpl.notifyDilinkStarted();
    }

    public int setIVIProfileSettings(int value) {
        return this.mSettingManagerImpl.setIviProfileSettingsR(value);
    }

    public int getDragModeForwardAndBackwardState() {
        return this.mSettingManagerImpl.getDragModeForwardAndBackwardStateR();
    }

    public int getDragAndDropModeTipsState() {
        return this.mSettingManagerImpl.getDragAndDropModeTipsStateR();
    }

    public int getFdDynamicSuspensionMode() {
        return this.mSettingManagerImpl.getFdDynamicSuspensionModeR();
    }

    public int setDriverSeatState(int value) {
        return this.mSettingManagerImpl.setDriverSeatState(value);
    }

    public int setAdasRest(int[] state) {
        return this.mSettingManagerImpl.setAdasRest(state);
    }

    public int getMemorySetOperationFlag() {
        return this.mSettingManagerImpl.getMemorySetOperationFlag();
    }

    public int setDlinkRest(int[] state) {
        return this.mSettingManagerImpl.setDlinkRest(state);
    }

    public int setCarsettingRest(int[] state) {
        return this.mSettingManagerImpl.setCarsettingRest(state);
    }

    public int getFrontWiperSpeedValue() {
        return this.mSettingManagerImpl.getFrontWiperSpeedValue();
    }

    public int getTrailerModeMileage() {
        return this.mSettingManagerImpl.getTrailerModeMileage();
    }

    public int getRearWiperSpeedValue() {
        return this.mSettingManagerImpl.getRearWiperSpeedValue();
    }

    public int setInsideLightState(int state) {
        return this.mSettingManagerImpl.setInsideLightState(state);
    }

    public int setOutsideLightStateScene(int state) {
        return this.mSettingManagerImpl.setOutsideLightStateScene(state);
    }

    public int getSwsMediumRelatedKeys() {
        return this.mSettingManagerImpl.getSwsMediumRelatedKeys();
    }

    public int getFdComSpeedUpState() {
        return this.mSettingManagerImpl.getFdComSpeedUpState();
    }

    public int voiceOpenMirrorlsTurnedState(int state) {
        return this.mSettingManagerImpl.voiceOpenMirrorlsTurnedState(state);
    }

    public int setRearviewMirrorIsAutoLightState(int state) {
        return this.mSettingManagerImpl.setRearviewMirrorIsAutoLightState(state);
    }

    public int setRearviewMirrorIsScreenState(int state) {
        return this.mSettingManagerImpl.setRearviewMirrorIsScreenState(state);
    }

    public int turnRearviewMirrorIs(int action) {
        return this.mSettingManagerImpl.turnRearviewMirrorIs(action);
    }

    public int getRearviewMirrorIsTurnedState() {
        return this.mSettingManagerImpl.getRearviewMirrorIsTurnedState();
    }

    public int getRearviewMirrorIsState() {
        return this.mSettingManagerImpl.getRearviewMirrorIsState();
    }

    public int getRearviewMirrorIsAutoLightState() {
        return this.mSettingManagerImpl.getRearviewMirrorIsAutoLightState();
    }

    public int resetRearviewMirrorIsSets(int state) {
        return this.mSettingManagerImpl.resetRearviewMirrorIsSets(state);
    }

    public int setRefrigeratorWorkMode(int mode) {
        return this.mSettingManagerImpl.setRefrigeratorWorkMode(mode);
    }

    public int getRefrigeratorWorkMode() {
        return this.mSettingManagerImpl.getRefrigeratorWorkMode();
    }

    public int setRefrigeratorWorkTemperature(int temperature) {
        return this.mSettingManagerImpl.setRefrigeratorWorkTemperature(temperature);
    }

    public int getRefrigeratorWorkTemperature() {
        return this.mSettingManagerImpl.getRefrigeratorWorkTemperature();
    }

    public int getFridgeWorkColdTemperature() {
        return this.mSettingManagerImpl.getFridgeWorkColdTemperature();
    }

    public int getFridgeWorkHeatTemperature() {
        return this.mSettingManagerImpl.getFridgeWorkHeatTemperature();
    }

    public int getFridgeWorkSaveTemperature() {
        return this.mSettingManagerImpl.getFridgeWorkSaveTemperature();
    }

    public int getDelayPowerOffState() {
        return this.mSettingManagerImpl.getDelayPowerOffState();
    }

    public int setDelayPowerOffTime(int time) {
        return this.mSettingManagerImpl.setDelayPowerOffTime(time);
    }

    public int getDelayPowerOffTime() {
        return this.mSettingManagerImpl.getDelayPowerOffTime();
    }

    public int setDelayPowerDownTime(int time) {
        return this.mSettingManagerImpl.setDelayPowerDownTime(time);
    }

    public int getDelayPowerDownTime() {
        return this.mSettingManagerImpl.getDelayPowerDownTime();
    }

    public int setRemberPowerOffState(int state) {
        return this.mSettingManagerImpl.setRemberPowerOffState(state);
    }

    public int getRemberPowerOffState() {
        return this.mSettingManagerImpl.getRemberPowerOffState();
    }

    public int setSterilizationState(int state) {
        return this.mSettingManagerImpl.setSterilizationState(state);
    }

    public int getSterilizationState() {
        return this.mSettingManagerImpl.getSterilizationState();
    }

    public int getVoiceTipMessage() {
        return this.mSettingManagerImpl.getVoiceTipMessage();
    }

    public int setFridgeDoorState(int state) {
        return this.mSettingManagerImpl.setFridgeDoorState(state);
    }

    public int getFridgeDoorState() {
        return this.mSettingManagerImpl.getFridgeDoorState();
    }

    public int getRefrigeratorWorkState() {
        return this.mSettingManagerImpl.getRefrigeratorWorkState();
    }

    public int getRefrigeratorTemperature() {
        return this.mSettingManagerImpl.getRefrigeratorTemperature();
    }

    public int getDelayDownPowerTime() {
        return this.mSettingManagerImpl.getDelayDownPowerTime();
    }

    public int getSterilizationWorkState() {
        return this.mSettingManagerImpl.getSterilizationWorkState();
    }

    public int getDlgMessageCode() {
        return this.mSettingManagerImpl.getDlgMessageCode();
    }

    public int getFridgeLocation() {
        return this.mSettingManagerImpl.getFridgeLocation();
    }

    public int setWorkState(int state) {
        return this.mSettingManagerImpl.setWorkState(state);
    }

    public int setWorkTemperature(int state) {
        return this.mSettingManagerImpl.setWorkTemperature(state);
    }

    public int setWorkCommond(int state) {
        return this.mSettingManagerImpl.setWorkCommond(state);
    }

    public int setRearWorkTemperature(int state) {
        return this.mSettingManagerImpl.setRearWorkTemperature(state);
    }

    public int getControlCallbackState() {
        return this.mSettingManagerImpl.getControlCallbackState();
    }

    public int getWorkState() {
        return this.mSettingManagerImpl.getWorkState();
    }

    public int getDelayPowerOffStatus() {
        return this.mSettingManagerImpl.getDelayPowerOffStatus();
    }

    public int getScreenCallbackMessage() {
        return this.mSettingManagerImpl.getScreenCallbackMessage();
    }

    public int getRearDoorState() {
        return this.mSettingManagerImpl.getRearDoorState();
    }

    public int getRearHeatTemp() {
        return this.mSettingManagerImpl.getRearHeatTemp();
    }

    public int getRearSaveTemp() {
        return this.mSettingManagerImpl.getRearSaveTemp();
    }

    public int getPhoneRemindCode(int state) {
        return this.mSettingManagerImpl.getPhoneRemindCode(state);
    }

    public int getServiceControlCallback() {
        return this.mSettingManagerImpl.getServiceControlCallback();
    }

    public int setDelayPowerDownState(int state) {
        return this.mSettingManagerImpl.setDelayPowerDownState(state);
    }

    public int setDelayPowerDownStateTime(int state) {
        return this.mSettingManagerImpl.setDelayPowerDownStateTime(state);
    }

    public int setFridgeChildLock(int state) {
        return this.mSettingManagerImpl.setFridgeChildLock(state);
    }

    public int saveOwnerState(int state) {
        return this.mSettingManagerImpl.saveOwnerState(state);
    }

    public int setRearFridgeWorkState(int state) {
        return this.mSettingManagerImpl.setRearFridgeWorkState(state);
    }

    public int setRearFridgeSterilizerState(int state) {
        return this.mSettingManagerImpl.setRearFridgeSterilizerState(state);
    }

    public int setRearFridgeTemperature(int state) {
        return this.mSettingManagerImpl.setRearFridgeTemperature(state);
    }

    public int setRearFridgeDelayState(int state) {
        return this.mSettingManagerImpl.setRearFridgeDelayState(state);
    }

    public int setRearFridgeDelayTime(int time) {
        return this.mSettingManagerImpl.setRearFridgeDelayTime(time);
    }

    public int setRearFridgeDoorState(int state) {
        return this.mSettingManagerImpl.setRearFridgeDoorState(state);
    }

    public int setRearviewMirrorIsTurnedState(int state) {
        return this.mSettingManagerImpl.setRearviewMirrorIsTurnedState(state);
    }

    public int getCanopyAutomaticDimming() {
        return this.mSettingManagerImpl.getCanopyAutomaticDimming();
    }

    public int getSkyCurtainPowerOffAndDim() {
        return this.mSettingManagerImpl.getSkyCurtainPowerOffAndDim();
    }

    public int setCanopyAutomaticDimming(int value) {
        return this.mSettingManagerImpl.setCanopyAutomaticDimming(value);
    }

    public int setSkyCurtainPowerOffAndDim(int value) {
        return this.mSettingManagerImpl.setSkyCurtainPowerOffAndDim(value);
    }

    public int getDimmingCanopyConfiguration() {
        return this.mSettingManagerImpl.getDimmingCanopyConfiguration();
    }

    public int setOpeningAngleInformationSetOnTheR(int state) {
        return this.mSettingManagerImpl.setOpeningAngleInformationSetOnTheR(state);
    }

    public int getCurrentTransmittanceOfSkylight() {
        return this.mSettingManagerImpl.getCurrentTransmittanceOfSkylight();
    }

    public int getCurrentStatusOfDimmingSunroof() {
        return this.mSettingManagerImpl.getCurrentStatusOfDimmingSunroof();
    }

    public int getDimmingCanopyLowCode() {
        return this.mSettingManagerImpl.getDimmingCanopyLowCode();
    }

    public int getElectricTailConfiguration() {
        return this.mSettingManagerImpl.getElectricTailConfiguration();
    }

    public int getTailInitializationState() {
        return this.mSettingManagerImpl.getTailInitializationState();
    }

    public int getCurrentStateOfTheTailState() {
        return this.mSettingManagerImpl.getCurrentStateOfTheTailState();
    }

    public int getTailPositionPercentage() {
        return this.mSettingManagerImpl.getTailPositionPercentage();
    }

    public int getTailMotorThermalProtectionState() {
        return this.mSettingManagerImpl.getTailMotorThermalProtectionState();
    }

    public int getTailPositionFeedback() {
        return this.mSettingManagerImpl.getTailPositionFeedback();
    }

    public int getAlarmPromptState() {
        return this.mSettingManagerImpl.getAlarmPromptState();
    }

    public int getCurrentModeOfTailState() {
        return this.mSettingManagerImpl.getCurrentModeOfTailState();
    }

    public int getElectricTailMaintenanceFuntatusState() {
        return this.mSettingManagerImpl.getElectricTailMaintenanceFuntatusState();
    }

    public int setElectricTailAutomaticModeSwitchState(int state) {
        return this.mSettingManagerImpl.setElectricTailAutomaticModeSwitchState(state);
    }

    public int setElectricTailAutoModeVoiceSwitchState(int state) {
        return this.mSettingManagerImpl.setElectricTailAutoModeVoiceSwitchState(state);
    }

    public int getRearRightMassageSwConfiguration() {
        return this.mSettingManagerImpl.getRearRightMassageSwConfiguration();
    }

    public int getRearLeftMassageSwConfiguration() {
        return this.mSettingManagerImpl.getRearLeftMassageSwConfiguration();
    }

    public int turnSeatHorizationRearLeftRcs(int action) {
        return this.mSettingManagerImpl.turnSeatHorizationRearLeftRcs(action);
    }

    public int turnSeatHorizationRearLeftLeftRcs(int action) {
        return this.mSettingManagerImpl.turnSeatHorizationRearLeftLeftRcs(action);
    }

    public int turnSeatHorizationRearLeftRightRcs(int action) {
        return this.mSettingManagerImpl.turnSeatHorizationRearLeftRightRcs(action);
    }

    public int turnSeatHorizationRearRightRcs(int action) {
        return this.mSettingManagerImpl.turnSeatHorizationRearRightRcs(action);
    }

    public int turnSeatHorizationRearRightLeftRcs(int action) {
        return this.mSettingManagerImpl.turnSeatHorizationRearRightLeftRcs(action);
    }

    public int turnSeatHorizationRearRightRightRcs(int action) {
        return this.mSettingManagerImpl.turnSeatHorizationRearRightRightRcs(action);
    }

    public int setFlSeatBasinSwitchState(int state) {
        return this.mSettingManagerImpl.setFlSeatBasinSwitchState(state);
    }

    public int setFrontPassengerSeatSeatPotSwitchState(int state) {
        return this.mSettingManagerImpl.setFrontPassengerSeatSeatPotSwitchState(state);
    }

    public int turnSeatCushionRearLeftRcs(int action) {
        return this.mSettingManagerImpl.turnSeatCushionRearLeftRcs(action);
    }

    public int turnSeatCushionRearLeftLeftRcs(int action) {
        return this.mSettingManagerImpl.turnSeatCushionRearLeftLeftRcs(action);
    }

    public int turnSeatCushionRearLeftRightRcs(int action) {
        return this.mSettingManagerImpl.turnSeatCushionRearLeftRightRcs(action);
    }

    public int turnSeatCushionRearRightRcs(int action) {
        return this.mSettingManagerImpl.turnSeatCushionRearRightRcs(action);
    }

    public int turnSeatCushionRearRightLeftRcs(int action) {
        return this.mSettingManagerImpl.turnSeatCushionRearRightLeftRcs(action);
    }

    public int turnSeatCushionRearRightRightRcs(int action) {
        return this.mSettingManagerImpl.turnSeatCushionRearRightRightRcs(action);
    }

    public int turnSeatBackRearLeftRcs(int action) {
        return this.mSettingManagerImpl.turnSeatBackRearLeftRcs(action);
    }

    public int turnSeatBackRearLeftLeftRcs(int action) {
        return this.mSettingManagerImpl.turnSeatBackRearLeftLeftRcs(action);
    }

    public int turnSeatBackRearLeftRightRcs(int action) {
        return this.mSettingManagerImpl.turnSeatBackRearLeftRightRcs(action);
    }

    public int turnSeatBackRearRightRcs(int action) {
        return this.mSettingManagerImpl.turnSeatBackRearRightRcs(action);
    }

    public int turnSeatBackRearRightLeftRcs(int action) {
        return this.mSettingManagerImpl.turnSeatBackRearRightLeftRcs(action);
    }

    public int turnSeatBackRearRightRightRcs(int action) {
        return this.mSettingManagerImpl.turnSeatBackRearRightRightRcs(action);
    }

    public int turnSeatCaliperSplintRearLeftRcs(int action) {
        return this.mSettingManagerImpl.turnSeatCaliperSplintRearLeftRcs(action);
    }

    public int turnSeatCaliperSplintRearLeftLeftRcs(int action) {
        return this.mSettingManagerImpl.turnSeatCaliperSplintRearLeftLeftRcs(action);
    }

    public int turnSeatCaliperSplintRearLeftRightRcs(int action) {
        return this.mSettingManagerImpl.turnSeatCaliperSplintRearLeftRightRcs(action);
    }

    public int turnSeatCaliperSplintRearRightRcs(int action) {
        return this.mSettingManagerImpl.turnSeatCaliperSplintRearRightRcs(action);
    }

    public int turnSeatCaliperSplintRearRightLeftRcs(int action) {
        return this.mSettingManagerImpl.turnSeatCaliperSplintRearRightLeftRcs(action);
    }

    public int turnSeatCaliperSplintRearRightRightRcs(int action) {
        return this.mSettingManagerImpl.turnSeatCaliperSplintRearRightRightRcs(action);
    }

    public int getLeftElectricPedal() {
        return this.mSettingManagerImpl.getLeftElectricPedal();
    }

    public int getRightElectricPedal() {
        return this.mSettingManagerImpl.getRightElectricPedal();
    }

    public int getVoiceTipsMessage() {
        return this.mSettingManagerImpl.getVoiceTipsMessage();
    }

    public int getFridgeChildLock() {
        return this.mSettingManagerImpl.getFridgeChildLock();
    }

    public int getFridgeWorkingMode() {
        return this.mSettingManagerImpl.getFridgeWorkingMode();
    }

    public int getFridgeTemperatureRegulation() {
        return this.mSettingManagerImpl.getFridgeTemperatureRegulation();
    }

    public int getFridgeExpansionAndContractionFunction() {
        return this.mSettingManagerImpl.getFridgeExpansionAndContractionFunction();
    }

    public int getFridgeMinWagePowerGeneration() {
        return this.mSettingManagerImpl.getFridgeMinWagePowerGeneration();
    }

    public int getCarFridgeType() {
        return this.mSettingManagerImpl.getCarFridgeType();
    }

    public int getExclusiveSaveFlag() {
        return this.mSettingManagerImpl.getExclusiveSaveFlag();
    }

    public int getFridgeChildLockFlag() {
        return this.mSettingManagerImpl.getFridgeChildLockFlag();
    }

    public int getFridgeScreenFlag() {
        return this.mSettingManagerImpl.getFridgeScreenFlag();
    }

    public int getFridgeDelayedPowerOutage() {
        return this.mSettingManagerImpl.getFridgeDelayedPowerOutage();
    }

    public int getFrontFridgeDisinfectFlag() {
        return this.mSettingManagerImpl.getFrontFridgeDisinfectFlag();
    }

    public int getFridgeNum() {
        return this.mSettingManagerImpl.getFridgeNum();
    }

    public int getRearFridgeDisinfectFlag() {
        return this.mSettingManagerImpl.getRearFridgeDisinfectFlag();
    }

    public int getRearFridgeKeyTypeFlag() {
        return this.mSettingManagerImpl.getRearFridgeKeyTypeFlag();
    }

    public int getRearFridgeWorkTypeFlag() {
        return this.mSettingManagerImpl.getRearFridgeWorkTypeFlag();
    }

    public int getRearRightFridgeHeatingScheme() {
        return this.mSettingManagerImpl.getRearRightFridgeHeatingScheme();
    }

    public int getFridgeOnePosition() {
        return this.mSettingManagerImpl.getFridgeOnePosition();
    }

    public int getFridgeTwoPosition() {
        return this.mSettingManagerImpl.getFridgeTwoPosition();
    }

    public int getFridgeThreePosition() {
        return this.mSettingManagerImpl.getFridgeThreePosition();
    }

    public int setIceBoxTriggerSource(int value) {
        return this.mSettingManagerImpl.setIceBoxTriggerSource(value);
    }

    public int getSocTargetRange() {
        return this.mSettingManagerImpl.getSocTargetRange();
    }

    public int getSocTargetLowLimit() {
        return this.mSettingManagerImpl.getSocTargetLowLimit();
    }

    public int setCpdImsSwitchState(int state) {
        return this.mSettingManagerImpl.setCpdImsSwitchState(state);
    }

    public int getCpdImsSwitchState() {
        return this.mSettingManagerImpl.getCpdImsSwitchState();
    }

    public int setRncState(int state) {
        return this.mSettingManagerImpl.setRncState(state);
    }

    public int getSwcontrolState() {
        return this.mSettingManagerImpl.getSwcontrolState();
    }

    public int setPedalSuppressionSystemState(int state) {
        return this.mSettingManagerImpl.setPedalSuppressionSystemState(state);
    }

    public int getPedalSuppressionSystemState() {
        return this.mSettingManagerImpl.getPedalSuppressionSystemState();
    }

    public int setCarDoorTipsSoundType(int type) {
        return this.mSettingManagerImpl.setCarDoorTipsSoundType(type);
    }

    public int getCuraSwitchState() {
        return this.mSettingManagerImpl.getCuraSwitchState();
    }

    public int getPhysicalkeyConfig() {
        return this.mSettingManagerImpl.getPhysicalkeyConfig();
    }

    public int getPhysicalkeySecuConfig() {
        return this.mSettingManagerImpl.getPhysicalkeySecuConfig();
    }

    public int getPhysicalkeySecupConfig() {
        return this.mSettingManagerImpl.getPhysicalkeySecupConfig();
    }

    public int getPhysicalkeySecurlConfig() {
        return this.mSettingManagerImpl.getPhysicalkeySecurlConfig();
    }

    public int getPhysicalkeySecurrConfig() {
        return this.mSettingManagerImpl.getPhysicalkeySecurrConfig();
    }

    public int getRearRightSeatGreetState() {
        return this.mSettingManagerImpl.getRearRightSeatGreetState();
    }

    public int setRearRightSeatGreetState(int state) {
        return this.mSettingManagerImpl.setRearRightSeatGreetState(state);
    }

    public int setCuraSwitchState(int state) {
        return this.mSettingManagerImpl.setCuraSwitchState(state);
    }

    public int getWadingPatternSocTips() {
        return this.mSettingManagerImpl.getWadingPatternSocTips();
    }

    public int getWadingPatternSpeedTips() {
        return this.mSettingManagerImpl.getWadingPatternSpeedTips();
    }

    public int getWadingPatternTestTips() {
        return this.mSettingManagerImpl.getWadingPatternTestTips();
    }

    public int getWadingPatternStateTips() {
        return this.mSettingManagerImpl.getWadingPatternStateTips();
    }

    public int getSteeringWheelHeatedPlan() {
        return this.mSettingManagerImpl.getSteeringWheelHeatedPlan();
    }

    public int getGoalRoadMode() {
        return this.mSettingManagerImpl.getGoalRoadMode();
    }

    public int setGoalRoadMode(int mode) {
        return this.mSettingManagerImpl.setGoalRoadMode(mode);
    }

    public int getSeaModeChargingSocType() {
        return this.mSettingManagerImpl.getSeaModeChargingSocType();
    }

    public int getDriveMode() {
        return this.mSettingManagerImpl.getDriveMode();
    }

    public int setSandAbsModeState(int state) {
        return this.mSettingManagerImpl.setSandAbsModeState(state);
    }

    public int getSandAbsModeState() {
        return this.mSettingManagerImpl.getSandAbsModeState();
    }

    public int getSandAbsTipMessage() {
        return this.mSettingManagerImpl.getSandAbsTipMessage();
    }

    public int getCarSeatLayout() {
        return this.mSettingManagerImpl.getCarSeatLayout();
    }

    public int getSaveOwnerState() {
        return this.mSettingManagerImpl.getSaveOwnerState();
    }

    public int getRefrigeratorMobileTerminalPushRe() {
        return this.mSettingManagerImpl.getRefrigeratorMobileTerminalPushRe();
    }

    public int getRefrig1PositionMarkBit() {
        return this.mSettingManagerImpl.getRefrig1PositionMarkBit();
    }

    public int getRefrig2PositionMarkBit() {
        return this.mSettingManagerImpl.getRefrig2PositionMarkBit();
    }

    public int getRefrigWorkFlag() {
        return this.mSettingManagerImpl.getRefrigWorkFlag();
    }

    public int getRefrigWorkMode() {
        return this.mSettingManagerImpl.getRefrigWorkMode();
    }

    public int setRefrigWorkMode(int mode) {
        return this.mSettingManagerImpl.setRefrigWorkMode(mode);
    }

    public int getRefrigNumFlag() {
        return this.mSettingManagerImpl.getRefrigNumFlag();
    }

    public int getDriverSeatVentilating437State() {
        return this.mSettingManagerImpl.getDriverSeatVentilating437State();
    }

    public int getDriverSeatHeating437State() {
        return this.mSettingManagerImpl.getDriverSeatHeating437State();
    }

    public int getPassengerSeatVentilating3caState() {
        return this.mSettingManagerImpl.getPassengerSeatVentilating3caState();
    }

    public int getPassengerSeatHeating3caState() {
        return this.mSettingManagerImpl.getPassengerSeatHeating3caState();
    }

    public int setIVIProfileSettingsDM(int mode) {
        return this.mSettingManagerImpl.setIviProfileSettingsDm(mode);
    }

    public int setCloudServiceStatus(int status) {
        return this.mSettingManagerImpl.setCloudServiceStatus(status);
    }

    public int setOptimizationStatus(int status) {
        return this.mSettingManagerImpl.setOptimizationStatus(status);
    }

    public int setNetworkSecurityStatus(int status) {
        return this.mSettingManagerImpl.setNetworkSecurityStatus(status);
    }

    public int getThirdRowSeatWorkMode() {
        return this.mSettingManagerImpl.getThirdRowSeatWorkMode();
    }

    public int getLeftRearSafebeltStatus() {
        return this.mSettingManagerImpl.getLeftRearSafebeltStatus();
    }

    public int getCenterRearSafebeltStatus() {
        return this.mSettingManagerImpl.getCenterRearSafebeltStatus();
    }

    public int getRightRearSafebeltStatus() {
        return this.mSettingManagerImpl.getRightRearSafebeltStatus();
    }

    public int getLeftThirdSafebeltStatus() {
        return this.mSettingManagerImpl.getLeftThirdSafebeltStatus();
    }

    public int getRightThirdSafebeltStatus() {
        return this.mSettingManagerImpl.getRightThirdSafebeltStatus();
    }

    public int getMainSeatHorizontalInit() {
        return this.mSettingManagerImpl.getMainSeatHorizontalInit();
    }

    public int getMainSeatBackInit() {
        return this.mSettingManagerImpl.getMainSeatBackInit();
    }

    public int getPassangerSeatHorizontalInit() {
        return this.mSettingManagerImpl.getPassangerSeatHorizontalInit();
    }

    public int getPassangerSeatBackInit() {
        return this.mSettingManagerImpl.getPassangerSeatBackInit();
    }

    public int getSecondLeftSeatHorizontalInit() {
        return this.mSettingManagerImpl.getSecondLeftSeatHorizontalInit();
    }

    public int getSecondLeftSeatBackInit() {
        return this.mSettingManagerImpl.getSecondLeftSeatBackInit();
    }

    public int getSecondRightSeatHorizontalInit() {
        return this.mSettingManagerImpl.getSecondRightSeatHorizontalInit();
    }

    public int getSecondRightSeatBackInit() {
        return this.mSettingManagerImpl.getSecondRightSeatBackInit();
    }

    public int getOneKeyEnterPitConfig() {
        return this.mSettingManagerImpl.getOneKeyEnterPitConfig();
    }

    public int setThirdRowSeatLeftBackrest(int state) {
        return this.mSettingManagerImpl.setThirdRowSeatLeftBackrest(state);
    }

    public int setThirdRowSeatRightBackrest(int state) {
        return this.mSettingManagerImpl.setThirdRowSeatRightBackrest(state);
    }

    public int getSeatDistributionFormConfig() {
        return this.mSettingManagerImpl.getSeatDistributionFormConfig();
    }

    public int getMeterExportArea() {
        return this.mSettingManagerImpl.getMeterExportArea();
    }

    public int getWashElectricPedal() {
        return this.mSettingManagerImpl.getWashElectricPedal();
    }

    public int getTapDoorState() {
        return this.mSettingManagerImpl.getTapDoorState();
    }

    public int getMainSeatHorizontalInited() {
        return this.mSettingManagerImpl.getMainSeatHorizontalInited();
    }

    public int getMainSeatBackInited() {
        return this.mSettingManagerImpl.getMainSeatBackInited();
    }

    public int getMainSeatHightInited() {
        return this.mSettingManagerImpl.getMainSeatHightInited();
    }

    public int getThirdRowLeftBackrestDisplay() {
        return this.mSettingManagerImpl.getThirdRowLeftBackrestDisplay();
    }

    public int getThirdRowRightBackrestDisplay() {
        return this.mSettingManagerImpl.getThirdRowRightBackrestDisplay();
    }

    public int getSecondRightSeatHorizontal2Init() {
        return this.mSettingManagerImpl.getSecondRightSeatHorizontal2Init();
    }

    public int getSecondRightSeatBack2Init() {
        return this.mSettingManagerImpl.getSecondRightSeatBack2Init();
    }

    public int getDragType() {
        return this.mSettingManagerImpl.getDragType();
    }

    public int getDragConnectionType() {
        return this.mSettingManagerImpl.getDragConnectionType();
    }

    public int getTrailerSmallItemConfiguration() {
        return this.mSettingManagerImpl.getTrailerSmallItemConfiguration();
    }

    public int getTrailerMiddleItemConfiguration() {
        return this.mSettingManagerImpl.getTrailerMiddleItemConfiguration();
    }

    public int getTrailerLargeItemConfiguration() {
        return this.mSettingManagerImpl.getTrailerLargeItemConfiguration();
    }

    public int setTrailerDragType(int type) {
        return this.mSettingManagerImpl.setTrailerDragType(type);
    }

    public int getTrailerDragType() {
        return this.mSettingManagerImpl.getTrailerDragType();
    }

    public int getTrailerSmallLimitWeight() {
        return this.mSettingManagerImpl.getTrailerSmallLimitWeight();
    }

    public int getTrailerMiddleLimitWeight() {
        return this.mSettingManagerImpl.getTrailerMiddleLimitWeight();
    }

    public int getTrailerLargeLimitWeight() {
        return this.mSettingManagerImpl.getTrailerLargeLimitWeight();
    }

    public int getTowingProhibitedState() {
        return this.mSettingManagerImpl.getTowingProhibitedState();
    }

    public int getUcuMassageMode(int position) {
        return this.mSettingManagerImpl.getUcuMassageMode(position);
    }

    public int getUcuMassageLevel(int position) {
        return this.mSettingManagerImpl.getUcuMassageLevel(position);
    }

    public int getBdMassageMode(int position) {
        return this.mSettingManagerImpl.getBdMassageMode(position);
    }

    public int getBdMassageLevel(int position) {
        return this.mSettingManagerImpl.getBdMassageLevel(position);
    }

    public int getSecuMassageMode(int position) {
        return this.mSettingManagerImpl.getSecuMassageMode(position);
    }

    public int getSecuMassageLevel(int position) {
        return this.mSettingManagerImpl.getSecuMassageLevel(position);
    }

    public int getRrEcuMassageMode() {
        return this.mSettingManagerImpl.getRrEcuMassageMode();
    }

    public int getRrEcuMassageLevel() {
        return this.mSettingManagerImpl.getRrEcuMassageLevel();
    }

    public int getDriverMasToggleModeConfig() {
        return this.mSettingManagerImpl.getDriverMasToggleModeConfig();
    }

    public int getPassengerMasToggleModeConfig() {
        return this.mSettingManagerImpl.getPassengerMasToggleModeConfig();
    }

    public int getLrMasToggleModeConfig() {
        return this.mSettingManagerImpl.getLrMasToggleModeConfig();
    }

    public int getLrMas2ToggleModeConfig() {
        return this.mSettingManagerImpl.getLrMas2ToggleModeConfig();
    }

    public int getRrMasToggleModeConfig() {
        return this.mSettingManagerImpl.getRrMasToggleModeConfig();
    }

    public int getRrMas2ToggleModeConfig() {
        return this.mSettingManagerImpl.getRrMas2ToggleModeConfig();
    }

    public int getMainNormalMassageConfig() {
        return this.mSettingManagerImpl.getMainNormalMassageConfig();
    }

    public int getMainHotStoneMassageConfig() {
        return this.mSettingManagerImpl.getMainHotStoneMassageConfig();
    }

    public int getMain2HotStoneMassageConfig() {
        return this.mSettingManagerImpl.getMain2HotStoneMassageConfig();
    }

    public int getPasNormalMassageConfig() {
        return this.mSettingManagerImpl.getPasNormalMassageConfig();
    }

    public int getPasHotStoneMassageConfig() {
        return this.mSettingManagerImpl.getPasHotStoneMassageConfig();
    }

    public int getPas2HotStoneMassageConfig() {
        return this.mSettingManagerImpl.getPas2HotStoneMassageConfig();
    }

    public int getLrNormalMassageConfig() {
        return this.mSettingManagerImpl.getLrNormalMassageConfig();
    }

    public int getLrNormal2MassageConfig() {
        return this.mSettingManagerImpl.getLrNormal2MassageConfig();
    }

    public int getLrHotStoneMassageConfig() {
        return this.mSettingManagerImpl.getLrHotStoneMassageConfig();
    }

    public int getLrHotStone2MassageConfig() {
        return this.mSettingManagerImpl.getLrHotStone2MassageConfig();
    }

    public int getLrHotStone3MassageConfig() {
        return this.mSettingManagerImpl.getLrHotStone3MassageConfig();
    }

    public int getRrNormalMassageConfig() {
        return this.mSettingManagerImpl.getRrNormalMassageConfig();
    }

    public int getRrNormal2MassageConfig() {
        return this.mSettingManagerImpl.getRrNormal2MassageConfig();
    }

    public int getRrNormal3MassageConfig() {
        return this.mSettingManagerImpl.getRrNormal3MassageConfig();
    }

    public int getRrHotStoneMassageConfig() {
        return this.mSettingManagerImpl.getRrHotStoneMassageConfig();
    }

    public int getRrHotStone2MassageConfig() {
        return this.mSettingManagerImpl.getRrHotStone2MassageConfig();
    }

    public int getRrHotStone3MassageConfig() {
        return this.mSettingManagerImpl.getRrHotStone3MassageConfig();
    }

    public int getRrHotStone4MassageConfig() {
        return this.mSettingManagerImpl.getRrHotStone4MassageConfig();
    }

    public int getSeatSecullDistributionFormConfig() {
        return this.mSettingManagerImpl.getSeatSecullDistributionFormConfig();
    }

    public int getSeatSeculrDistributionFormConfig() {
        return this.mSettingManagerImpl.getSeatSeculrDistributionFormConfig();
    }

    public int getSeatSecurlDistributionFormConfig() {
        return this.mSettingManagerImpl.getSeatSecurlDistributionFormConfig();
    }

    public int getSeatSecurrDistributionFormConfig() {
        return this.mSettingManagerImpl.getSeatSecurrDistributionFormConfig();
    }
}

package android.hardware.bydauto.setting;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.setting.tsimpl.SettingClusterManagerImpl;
import android.hardware.bydauto.setting.tsimpl.SettingManagerImpl;
import android.hardware.bydauto.setting.tsimpl.SettingQnxManagerImpl;
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
    public static final int CAR_REAR_VIEW_FIXED_ANGLE_HAL = 2;
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
    public static final int LEARNER_DRIVER_MODE_SWITCH_OFF = 2;
    public static final int LEARNER_DRIVER_MODE_SWITCH_ON = 1;
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
    public static final int LOCATION_BEHIND_LEFT = 2;
    public static final int LOCATION_BEHIND_RIGHT = 3;
    public static final int LOCATION_MAIN = 0;
    public static final int LOCATION_SECOND = 1;
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
    public static final String TAG = "BYDAutoSettingDevice";
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

    private BYDAutoSettingDevice(Context context) {
        super(context);
        this.mSettingQnxManagerImpl = SettingQnxManagerImpl.getInstance(context);
        this.mSettingManagerImpl = SettingManagerImpl.getInstance(context);
        this.mSettingClusterManagerImpl = SettingClusterManagerImpl.getInstance(context);
    }

    public static BYDAutoSettingDevice getInstance(Context context) {
        if (mBYDAutoSettingDevice == null) {
            synchronized (BYDAutoSettingDevice.class) {
                if (mBYDAutoSettingDevice == null) {
                    mBYDAutoSettingDevice = new BYDAutoSettingDevice(context.getApplicationContext());
                }
            }
        }
        return mBYDAutoSettingDevice;
    }

    @Deprecated
    public int autoBackDefaultReset(int i8) {
        return this.mSettingManagerImpl.autoBackDefaultReset(i8);
    }

    @Deprecated
    public int backLightDefaultReset(int i8) {
        return this.mSettingManagerImpl.backLightDefaultReset(i8);
    }

    public int carHealthDefaultReset(int i8) {
        return this.mSettingManagerImpl.carHealthDefaultReset(i8);
    }

    @Deprecated
    public int carSettingsDefaultReset(int i8) {
        return this.mSettingManagerImpl.carSettingsDefaultReset(i8);
    }

    @Deprecated
    public int carSettingsReset(int i8) {
        return this.mSettingManagerImpl.carSettingsReset(i8);
    }

    public int clockDefaultReset(int i8) {
        return this.mSettingManagerImpl.clockDefaultReset(i8);
    }

    @Deprecated
    public int doorsAndWindowDefaultResset(int i8) {
        return this.mSettingManagerImpl.doorsAndWindowDefaultResset(i8);
    }

    public int driveSettingsReset(int i8) {
        return this.mSettingManagerImpl.driveSettingsReset(i8);
    }

    @Deprecated
    public int equalizerDefaultReset(int i8) {
        return this.mSettingManagerImpl.equalizerDefaultReset(i8);
    }

    public int factoryResetAll(int i8) {
        return this.mSettingManagerImpl.factoryResetAll(i8);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.mSettingManagerImpl.get(iArr, cls);
    }

    public int get1LevelSeatHeatingState(int i8) {
        return this.mSettingManagerImpl.get1LevelSeatHeatingState(i8);
    }

    public int get5LevelSeatHeatingLevel(int i8) {
        return this.mSettingManagerImpl.get5LevelSeatHeatingLevel(i8);
    }

    public int get5LevelSeatHeatingMode(int i8) {
        return this.mSettingManagerImpl.get5LevelSeatHeatingMode(i8);
    }

    public int get5LevelSeatHeatingState(int i8) {
        return this.mSettingManagerImpl.get5LevelSeatHeatingState(i8);
    }

    public int get5LevelSeatVentilatingLevel(int i8) {
        return this.mSettingManagerImpl.get5LevelSeatVentilatingLevel(i8);
    }

    public int get5LevelSeatVentilatingMode(int i8) {
        return this.mSettingManagerImpl.get5LevelSeatVentilatingMode(i8);
    }

    public int get5LevelSeatVentilatingState(int i8) {
        return this.mSettingManagerImpl.get5LevelSeatVentilatingState(i8);
    }

    public int getACAutoAir() {
        return this.mSettingManagerImpl.getACAutoAir();
    }

    public int getACAutoWindLevel() {
        return 0;
    }

    public int getACBTWind() {
        return this.mSettingManagerImpl.getACBTWind();
    }

    public int getACPauseCycle() {
        return this.mSettingManagerImpl.getACPauseCycle();
    }

    public int getACTunnelCycle() {
        return this.mSettingManagerImpl.getACTunnelCycle();
    }

    public int getAcTemperatureUnit() {
        return this.mSettingManagerImpl.getAcTemperatureUnit();
    }

    public int getAcWarmMode() {
        return this.mSettingManagerImpl.getAcWarmMode();
    }

    public int getAidedDrivingInd() {
        return this.mSettingManagerImpl.getAidedDrivingInd();
    }

    public int getAirLightPanelState() {
        return 0;
    }

    public int getAirLightTextNumber() {
        return 0;
    }

    public int getAirLightTextState() {
        return 0;
    }

    public int getAlarmPromptState() {
        return this.mSettingManagerImpl.getAlarmPromptState();
    }

    public int getAmbientAreaSupport() {
        return this.mSettingManagerImpl.getAmbientAreaSupport();
    }

    public int getAmbientFeedBack() {
        return this.mSettingManagerImpl.getAmbientFeedBack();
    }

    public int getAmbientLightColorSupport() {
        return this.mSettingManagerImpl.getAmbientLightColorSupport();
    }

    public int getAmbientLightSupport() {
        return this.mSettingManagerImpl.getAmbientLightSupport();
    }

    @Deprecated
    public int getAmbientMulticolorMode(int i8) {
        return this.mSettingManagerImpl.getAmbientMulticolorMode(i8);
    }

    @Deprecated
    public int getAmbientMulticolorState(int i8) {
        return this.mSettingManagerImpl.getAmbientMulticolorState(i8);
    }

    @Deprecated
    public int getAmbientMusicMode(int i8) {
        return this.mSettingManagerImpl.getAmbientMusicMode(i8);
    }

    public int getAmbientMusicModeState(int i8) {
        return this.mSettingManagerImpl.getAmbientMusicModeState(i8);
    }

    public int getAppUseLimitState() {
        return this.mSettingManagerImpl.getAppUseLimitState();
    }

    public int getAutoExternalRearMirrorFollowUpSwitch() {
        return this.mSettingManagerImpl.getAutoExternalRearMirrorFollowUpSwitch();
    }

    public int getAutoExternalRearMirrorFollowUpSwitchSupport() {
        return this.mSettingManagerImpl.getAutoExternalRearMirrorFollowUpSwitchSupport();
    }

    public int getAutoLock() {
        return 0;
    }

    public int getAutoLockSwitch() {
        return 0;
    }

    public int getAutoLockTime() {
        return 0;
    }

    public int getAutoPowerOffState() {
        return this.mSettingManagerImpl.getAutoPowerOffState();
    }

    public int getAutoPowerOnState() {
        return this.mSettingManagerImpl.getAutoPowerOnState();
    }

    public int getAutoPurificationState() {
        return this.mSettingManagerImpl.getAutoPurificationState();
    }

    public int getAutoRainWiperState() {
        return this.mSettingManagerImpl.getAutoRainWiperState();
    }

    public int getAutoTime() {
        return 0;
    }

    public int getAutomaticParking() {
        return this.mSettingManagerImpl.getAutomaticParking();
    }

    public int getBSISCollisionState() {
        return this.mSettingManagerImpl.getBsisCollisionStateR();
    }

    public int getBSISFuncStatus() {
        return this.mSettingManagerImpl.getBsisFuncStatusR();
    }

    public int getBTCallReductionWindMenuState() {
        return 0;
    }

    public byte[] getBTMacAddr() {
        return new byte[0];
    }

    public int getBackDoorElectricMode() {
        return this.mSettingManagerImpl.getBackDoorElectricMode();
    }

    public int getBackDoorElectricModeOnlineState() {
        return 0;
    }

    public int getBackDoorOpenedHeight() {
        return this.mSettingManagerImpl.getBackDoorOpenedHeight();
    }

    public int getBackHomeLightDelay() {
        return this.mSettingManagerImpl.getBackHomeLightDelay();
    }

    public int getBackHomeLightDelayValue() {
        return this.mSettingManagerImpl.getBackHomeLightDelayValue();
    }

    public int getBackRowControl() {
        return 0;
    }

    public int getBasePadAutoRise() {
        return 0;
    }

    public int getBasePadAutoRiseState() {
        return 0;
    }

    public int getBleOpenDriverDoorState() {
        return this.mSettingManagerImpl.getBleOpenDriverDoorState();
    }

    public int getBrakeCloseDoor() {
        return this.mSettingManagerImpl.getBrakeCloseDoor();
    }

    public int getBrakePedalMode() {
        return this.mSettingManagerImpl.getBrakePedalMode();
    }

    public int getCVCMode() {
        return this.mSettingManagerImpl.getCvcMode();
    }

    public int getCampingBlanceAction() {
        return this.mSettingManagerImpl.getCampingBlanceAction();
    }

    public int getCampingBlanceState() {
        return this.mSettingManagerImpl.getCampingBlanceState();
    }

    public int getCampingBlanceSupported() {
        return this.mSettingManagerImpl.getCampingBlanceSupported();
    }

    public int getCanopyAutomaticDimming() {
        return this.mSettingManagerImpl.getCanopyAutomaticDimming();
    }

    public int getCarFridgeType() {
        return this.mSettingManagerImpl.getCarFridgeType();
    }

    public int getCarRecorderRecording() {
        return 0;
    }

    public int getCarSeatLayout() {
        return this.mSettingManagerImpl.getCarSeatLayout();
    }

    public int getCarTipsSoundType() {
        return this.mSettingClusterManagerImpl.getCarTipsSoundType();
    }

    public int getCenterRearSafebeltStatus() {
        return this.mSettingManagerImpl.getCenterRearSafebeltStatus();
    }

    public int getChargingPort() {
        return this.mSettingManagerImpl.getChargingPort();
    }

    public int getCloseDoorLevel() {
        return this.mSettingManagerImpl.getCloseDoorLevel();
    }

    public int getComSpeedDownState() {
        return this.mSettingManagerImpl.getComSpeedDownState();
    }

    public int getComSpeedUpState() {
        return this.mSettingManagerImpl.getComSpeedUpState();
    }

    public int getComfortableParkingState() {
        return this.mSettingManagerImpl.getComfortableParkingState();
    }

    public int getControlCallbackState() {
        return this.mSettingManagerImpl.getControlCallbackState();
    }

    public int getCourtesyLampTime() {
        return 0;
    }

    public int getCpdImsSwitchState() {
        return this.mSettingManagerImpl.getCpdImsSwitchState();
    }

    public int getCreepDoorState() {
        return this.mSettingManagerImpl.getCreepDoorState();
    }

    public int getCreepModeFailState() {
        return this.mSettingManagerImpl.getCreepModeFailState();
    }

    public int getCreepModeState() {
        return this.mSettingManagerImpl.getCreepModeState();
    }

    public int getCreepModeWorkState() {
        return this.mSettingManagerImpl.getCreepModeWorkState();
    }

    public int getCreepSpeedState() {
        return this.mSettingManagerImpl.getCreepSpeedState();
    }

    public int getCruiseBulletBox() {
        return 0;
    }

    public int getCuraSwitchState() {
        return this.mSettingManagerImpl.getCuraSwitchState();
    }

    public int getCurrentModeOfTailState() {
        return this.mSettingManagerImpl.getCurrentModeOfTailState();
    }

    public int getCurrentStateOfTheTailState() {
        return this.mSettingManagerImpl.getCurrentStateOfTheTailState();
    }

    public int getCurrentStatusOfDimmingSunroof() {
        return this.mSettingManagerImpl.getCurrentStatusOfDimmingSunroof();
    }

    public int getCurrentTransmittanceOfSkylight() {
        return this.mSettingManagerImpl.getCurrentTransmittanceOfSkylight();
    }

    public int getCushionSupport(int i8) {
        return this.mSettingManagerImpl.getCushionSupport(i8);
    }

    public int getDampState() {
        return this.mSettingManagerImpl.getDampState();
    }

    public int getDelayDownPowerTime() {
        return this.mSettingManagerImpl.getDelayDownPowerTime();
    }

    public int getDelayPowerDownTime() {
        return this.mSettingManagerImpl.getDelayPowerDownTime();
    }

    public int getDelayPowerOffState() {
        return this.mSettingManagerImpl.getDelayPowerOffState();
    }

    public int getDelayPowerOffStatus() {
        return this.mSettingManagerImpl.getDelayPowerOffStatus();
    }

    public int getDelayPowerOffTime() {
        return this.mSettingManagerImpl.getDelayPowerOffTime();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    public int getDimmingCanopyConfiguration() {
        return this.mSettingManagerImpl.getDimmingCanopyConfiguration();
    }

    public int getDimmingCanopyLowCode() {
        return this.mSettingManagerImpl.getDimmingCanopyLowCode();
    }

    public int getDisinfState() {
        return this.mSettingManagerImpl.getDisinfState();
    }

    public int getDlgMessageCode() {
        return this.mSettingManagerImpl.getDlgMessageCode();
    }

    public int getDmPlatform() {
        return 0;
    }

    public int getDoorHeightSupport() {
        return this.mSettingManagerImpl.getDoorHeightSupport();
    }

    public int getDoorLock() {
        return 0;
    }

    public int getDoorknobAutoShrinkState() {
        return this.mSettingManagerImpl.getDoorknobAutoShrinkState();
    }

    public int getDragAndDropModeTipsState() {
        return this.mSettingManagerImpl.getDragAndDropModeTipsStateR();
    }

    public int getDragModeForwardAndBackwardState() {
        return this.mSettingManagerImpl.getDragModeForwardAndBackwardStateR();
    }

    public int getDriveDoorCloseInd() {
        return this.mSettingManagerImpl.getDriveDoorCloseInd();
    }

    public int getDriveMode() {
        return this.mSettingManagerImpl.getDriveMode();
    }

    public int getDriverSeatAutoReturn() {
        return this.mSettingManagerImpl.getDriverSeatAutoReturn();
    }

    public int getDriverSeatAutoReturnB3() {
        return this.mSettingManagerImpl.getDriverSeatAutoReturnB3();
    }

    public int getDriverSeatBack() {
        return 0;
    }

    public int getDriverSeatHeating437State() {
        return this.mSettingManagerImpl.getDriverSeatHeating437State();
    }

    public int getDriverSeatVentilating437State() {
        return this.mSettingManagerImpl.getDriverSeatVentilating437State();
    }

    public int getDrivingRecorder() {
        return 0;
    }

    public int getDrivingRecorderSwitchState() {
        return 0;
    }

    public int getDualTempAreaAcOnlineState() {
        return 0;
    }

    public int getDyHeightLevel() {
        return this.mSettingManagerImpl.getDyHeightLevel();
    }

    public int getDyHeightToggleState() {
        return this.mSettingManagerImpl.getDyHeightToggleState();
    }

    public int getDyHeightTurnAction() {
        return this.mSettingManagerImpl.getDyHeightTurnAction();
    }

    public int getDynamicSuspensionMode() {
        return this.mSettingManagerImpl.getDynamicSuspensionMode();
    }

    public int getEPedalMode() {
        return 0;
    }

    public int getEcuMassageLevel(int i8) {
        return this.mSettingManagerImpl.getEcuMassageLevel(i8);
    }

    public int getEcuMassageMode(int i8) {
        return this.mSettingManagerImpl.getEcuMassageMode(i8);
    }

    public int getElecHandbrakeState() {
        return 0;
    }

    public int getElectricTailConfiguration() {
        return this.mSettingManagerImpl.getElectricTailConfiguration();
    }

    public int getElectricTailMaintenanceFuntatusState() {
        return this.mSettingManagerImpl.getElectricTailMaintenanceFuntatusState();
    }

    public int getElectroniChildLockConfig() {
        return this.mSettingManagerImpl.getElectroniChildLockConfig();
    }

    public int getEnergyFeedback() {
        return this.mSettingManagerImpl.getEnergyFeedback();
    }

    public int getEngineOilExitUpdateState() {
        return 0;
    }

    public int getEngineOilLevel() {
        return 0;
    }

    public int getEngineOilUpdateSignal() {
        return 0;
    }

    public int getEngineSoundType() {
        return this.mSettingManagerImpl.getEngineSoundType();
    }

    public int getExclusiveSaveFlag() {
        return this.mSettingManagerImpl.getExclusiveSaveFlag();
    }

    public int getExhibitionMode() {
        return this.mSettingManagerImpl.getExhibitionMode();
    }

    public int getExhibitionStartEnginePrompt() {
        return this.mSettingManagerImpl.getExhibitionStartEnginePrompt();
    }

    public int getExternalRearMirrorAutoFoldSetting() {
        return 0;
    }

    public int getFdCRCCheckNum() {
        return this.mSettingManagerImpl.getFdCrcCheckNum();
    }

    public int getFdCampingBlanceAction() {
        return this.mSettingManagerImpl.getFdCampingBlanceAction();
    }

    public int getFdCampingBlanceState() {
        return this.mSettingManagerImpl.getFdCampingBlanceState();
    }

    public int getFdCampingBlanceSupported() {
        return this.mSettingManagerImpl.getFdCampingBlanceSupported();
    }

    public int getFdComSpeedUpState() {
        return this.mSettingManagerImpl.getFdComSpeedUpState();
    }

    public int getFdDampState() {
        return this.mSettingManagerImpl.getFdDampState();
    }

    public int getFdDyHeightLevel() {
        return this.mSettingManagerImpl.getFdDyHeightLevel();
    }

    public int getFdDyHeightToggleState() {
        return this.mSettingManagerImpl.getFdDyHeightToggleState();
    }

    public int getFdDyHeightTurnAction() {
        return this.mSettingManagerImpl.getFdDyHeightTurnAction();
    }

    public int getFdDynamicSuspensionMode() {
        return this.mSettingManagerImpl.getFdDynamicSuspensionModeR();
    }

    public int getFdFixedState() {
        return this.mSettingManagerImpl.getFdFixedState();
    }

    public int getFdFrontBoxTakeState() {
        return this.mSettingManagerImpl.getFdFrontBoxTakeState();
    }

    public int getFdLiftModeState() {
        return this.mSettingManagerImpl.getFdLiftModeState();
    }

    public int getFdModeSupportedHturn() {
        return this.mSettingManagerImpl.getFdModeSupportedHturn();
    }

    public int getFdSpeedSupportedTurn() {
        return this.mSettingManagerImpl.getFdSpeedSupportedTurn();
    }

    public int getFdTailBoxTakeState() {
        return this.mSettingManagerImpl.getFdTailBoxTakeState();
    }

    public int getFdTailBoxTakeSupported() {
        return this.mSettingManagerImpl.getFdTailBoxTakeSupported();
    }

    public int getFdTrailerModeCode() {
        return this.mSettingManagerImpl.getFdTrailerModeCode();
    }

    public int getFdWelcomeActive() {
        return this.mSettingManagerImpl.getFdWelcomeActive();
    }

    public int getFdWelcomeState() {
        return this.mSettingManagerImpl.getFdWelcomeState();
    }

    public int getFdWelcomeSupported() {
        return this.mSettingManagerImpl.getFdWelcomeSupported();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    public int getFixedState() {
        return this.mSettingManagerImpl.getFixedState();
    }

    public int getFridgeChildLock() {
        return this.mSettingManagerImpl.getFridgeChildLock();
    }

    public int getFridgeChildLockFlag() {
        return this.mSettingManagerImpl.getFridgeChildLockFlag();
    }

    public int getFridgeDelayedPowerOutage() {
        return this.mSettingManagerImpl.getFridgeDelayedPowerOutage();
    }

    public int getFridgeDoorState() {
        return this.mSettingManagerImpl.getFridgeDoorState();
    }

    public int getFridgeExpansionAndContractionFunction() {
        return this.mSettingManagerImpl.getFridgeExpansionAndContractionFunction();
    }

    public int getFridgeLocation() {
        return this.mSettingManagerImpl.getFridgeLocation();
    }

    public int getFridgeMinWagePowerGeneration() {
        return this.mSettingManagerImpl.getFridgeMinWagePowerGeneration();
    }

    public int getFridgeNum() {
        return this.mSettingManagerImpl.getFridgeNum();
    }

    public int getFridgeOnePosition() {
        return this.mSettingManagerImpl.getFridgeOnePosition();
    }

    public int getFridgeScreenFlag() {
        return this.mSettingManagerImpl.getFridgeScreenFlag();
    }

    public int getFridgeTemperatureRegulation() {
        return this.mSettingManagerImpl.getFridgeTemperatureRegulation();
    }

    public int getFridgeThreePosition() {
        return this.mSettingManagerImpl.getFridgeThreePosition();
    }

    public int getFridgeTwoPosition() {
        return this.mSettingManagerImpl.getFridgeTwoPosition();
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

    public int getFridgeWorkingMode() {
        return this.mSettingManagerImpl.getFridgeWorkingMode();
    }

    public int getFrontFridgeDisinfectFlag() {
        return this.mSettingManagerImpl.getFrontFridgeDisinfectFlag();
    }

    public int getFrontWiperSpeedValue() {
        return this.mSettingManagerImpl.getFrontWiperSpeedValue();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_SETTING_GET;
    }

    public int getGoalRoadMode() {
        return this.mSettingManagerImpl.getGoalRoadMode();
    }

    public int getHSpeedAutoCloseState() {
        return this.mSettingManagerImpl.getHSpeedAutoCloseState();
    }

    public int getHandleAutoResetTime() {
        return 0;
    }

    public int getHeadLampConfig() {
        return this.mSettingManagerImpl.getHeadLampConfig();
    }

    public int getHeadlampLevel() {
        return this.mSettingManagerImpl.getHeadlampLevel();
    }

    public int getHeadrestHorization(int i8) {
        return this.mSettingManagerImpl.getHeadrestHorization(i8);
    }

    public float getHeadrestTurnHeight(int i8) {
        return this.mSettingManagerImpl.getHeadrestTurnHeight(i8);
    }

    public int getHevChargingPort() {
        return this.mSettingManagerImpl.getHevChargingPort();
    }

    public int getHomeLightTime() {
        return 0;
    }

    public int getHsMassageState(int i8) {
        return this.mSettingManagerImpl.getHsMassageState(i8);
    }

    public int getHudBrightness() {
        return this.mSettingManagerImpl.getHudBrightness();
    }

    public int getHudDisplayContent(int i8) {
        return this.mSettingManagerImpl.getHudDisplayContent(i8);
    }

    public int getHudHeight() {
        return this.mSettingManagerImpl.getHudHeight();
    }

    public int getHudMode() {
        return this.mSettingManagerImpl.getHudMode();
    }

    public int getHudModeParams() {
        return this.mSettingManagerImpl.getHudModeParams();
    }

    public int getHudNavigationState() {
        return this.mSettingManagerImpl.getHudNavigationState();
    }

    public int getHudPattern() {
        return this.mSettingManagerImpl.getHudPattern();
    }

    public int getHudRotationAngle() {
        return this.mSettingManagerImpl.getHudRotationAngle();
    }

    public int getHudSafeDriveState() {
        return this.mSettingManagerImpl.getHudSafeDriveState();
    }

    public int getHudScreenProjectionState() {
        return this.mSettingManagerImpl.getHudScreenProjectionState();
    }

    public int getHudState() {
        return this.mSettingManagerImpl.getHudState();
    }

    public int getIALArea() {
        return this.mSettingManagerImpl.getIALArea();
    }

    public int getIALBrightness(int i8) {
        return this.mSettingManagerImpl.getIALBrightness(i8);
    }

    public int getIALColor(int i8) {
        return this.mSettingManagerImpl.getIALColor(i8);
    }

    public int getIKEYBTLowPowerMode() {
        return 0;
    }

    public int getILDuration() {
        return 0;
    }

    public int getINSTheme() {
        return this.mSettingClusterManagerImpl.getINSTheme();
    }

    public int getIVIProfileSettings() {
        return this.mSettingManagerImpl.getIviProfileSettingsR();
    }

    public int getInsBacklightCtl() {
        return 0;
    }

    public int getInsideLightDoorState() {
        return this.mSettingManagerImpl.getInsideLightDoorState();
    }

    public int getInsideRearMirrorScreenSwitchState() {
        return 0;
    }

    public int getInstrumentAcKeyType() {
        return 0;
    }

    public int getIntelLockOpenDriverDoor() {
        return this.mSettingManagerImpl.getIntelLockOpenDriverDoor();
    }

    public int getIntelligentAcAreaState() {
        return this.mSettingManagerImpl.getIntelligentAcAreaState();
    }

    public int getIntelligentLockState() {
        return this.mSettingManagerImpl.getIntelligentLockState();
    }

    public int getIntelligentUnlockState() {
        return this.mSettingManagerImpl.getIntelligentUnlockState();
    }

    public int getIntelligentVoiceIND() {
        return 0;
    }

    public int getKeyPowerLowInd() {
        return this.mSettingManagerImpl.getKeyPowerLowInd();
    }

    public int getKeyVibratorLevel() {
        return this.mSettingManagerImpl.getKeyVibratorLevel();
    }

    public int getKeypadToneState() {
        return this.mSettingManagerImpl.getKeypadToneState();
    }

    public int getLPSwitchDownwindow() {
        return 0;
    }

    public int getLPSwitchUpwindow() {
        return 0;
    }

    public int getLRSEChildModeCodeWriteFb() {
        return this.mSettingManagerImpl.getLrseChildModeCodeWriteFbR();
    }

    public int getLRSEChildModeTimeWriteFb() {
        return this.mSettingManagerImpl.getLrseChildModeTimeWriteFbR();
    }

    public int getLRSEMachineSwitchState() {
        return this.mSettingManagerImpl.getLrseMachineSwitchStateR();
    }

    public int getLandAdvanceAtSate() {
        return this.mSettingManagerImpl.getLandAdvanceAtSate();
    }

    public int getLanguage() {
        Log.d(TAG, "getLanguage");
        return this.mSettingClusterManagerImpl.getLanguage();
    }

    public int getLanternLanguageMode() {
        return this.mSettingManagerImpl.getLanternLanguageMode();
    }

    public int getLearnerDriverAccLimitState() {
        return 0;
    }

    public int getLearnerDriverModeState() {
        return 0;
    }

    public int getLearnerDriverModeSwitchState() {
        return 0;
    }

    public int getLeaveCarPInd() {
        return this.mSettingManagerImpl.getLeaveCarPInd();
    }

    public int getLeaveHomeLightTime() {
        return 0;
    }

    public int getLeftElectricPedal() {
        return this.mSettingManagerImpl.getLeftElectricPedal();
    }

    public int getLeftFieldPlatform() {
        return 0;
    }

    public int getLeftHeadlampLevel() {
        return this.mSettingManagerImpl.getLeftHeadlampLevel();
    }

    public int getLeftHomeLightDelay() {
        return this.mSettingManagerImpl.getLeftHomeLightDelay();
    }

    public int getLeftHomeLightDelayValue() {
        return this.mSettingManagerImpl.getLeftHomeLightDelayValue();
    }

    public int getLeftRSEChildModeSwitchStatus() {
        return this.mSettingManagerImpl.getLeftRseChildModeSwitchStatusR();
    }

    public int getLeftRearMassageChairConfigurationForECU() {
        return this.mSettingManagerImpl.getLeftRearMassageChairConfigurationForEcu();
    }

    public int getLeftRearMemoryConfiguration() {
        return this.mSettingManagerImpl.getLeftRearMemoryConfiguration();
    }

    public int getLeftRearSafebeltStatus() {
        return this.mSettingManagerImpl.getLeftRearSafebeltStatus();
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

    public int getLeftThirdSafebeltStatus() {
        return this.mSettingManagerImpl.getLeftThirdSafebeltStatus();
    }

    public int getLeftViewMirrorFlipAngle() {
        return 0;
    }

    public int getLiftModeState() {
        return this.mSettingManagerImpl.getLiftModeState();
    }

    public int getLockCarRiseWindow() {
        return this.mSettingManagerImpl.getLockCarRiseWindow();
    }

    public int getLockOff() {
        return this.mSettingManagerImpl.getLockOff();
    }

    public int getLockUpwindow() {
        return 0;
    }

    public int getLongPressUnlockWindow() {
        return 0;
    }

    public int getLowBatteryInd() {
        return this.mSettingManagerImpl.getLowBatteryInd();
    }

    public int getLowOilInd() {
        return this.mSettingManagerImpl.getLowOilInd();
    }

    public int getMOISAssembleState() {
        return this.mSettingManagerImpl.getMoisAssembleStateR();
    }

    public int getMOISFuncStatus() {
        return this.mSettingManagerImpl.getMoisFuncStatusR();
    }

    public int getMain2SaveOrRecoveFailCode() {
        return this.mSettingManagerImpl.getMain2SaveOrRecoveFailCode();
    }

    public int getMain2SaveOrRecoverState() {
        return this.mSettingManagerImpl.getMain2SaveOrRecoverState();
    }

    public int getMainDriveElectricHeadrestAdjustmentByGCtrl() {
        return this.mSettingManagerImpl.getMainDriveElectricHeadrestAdjustmentByGCtrl();
    }

    public int getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl() {
        return this.mSettingManagerImpl.getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl();
    }

    public int getMainDriveElectricLegSupportAdjustmentByGCtrl() {
        return this.mSettingManagerImpl.getMainDriveElectricLegSupportAdjustmentByGCtrl();
    }

    public int getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl() {
        return this.mSettingManagerImpl.getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl();
    }

    public int getMainDriverSeatAdjustmentConfigurationByGCtrl() {
        return this.mSettingManagerImpl.getMainDriverSeatAdjustmentConfigurationByGCtrl();
    }

    public int getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl() {
        return this.mSettingManagerImpl.getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl();
    }

    public int getMainHsMassageState() {
        return this.mSettingManagerImpl.getMainHsMassageState();
    }

    public int getMainSaveOrRecoveFailCode() {
        return this.mSettingManagerImpl.getMainSaveOrRecoveFailCode();
    }

    public int getMainSaveOrRecoverState() {
        return this.mSettingManagerImpl.getMainSaveOrRecoverState();
    }

    public int getMainSeatBackInit() {
        return this.mSettingManagerImpl.getMainSeatBackInit();
    }

    public int getMainSeatHorizontalInit() {
        return this.mSettingManagerImpl.getMainSeatHorizontalInit();
    }

    public int getMaintainRemindState() {
        return this.mSettingManagerImpl.getMaintainRemindState();
    }

    public int getMassageLevel(int i8) {
        return this.mSettingManagerImpl.getMassageLevel(i8);
    }

    public int getMassageMode(int i8) {
        return this.mSettingManagerImpl.getMassageMode(i8);
    }

    public int getMemorySetOperationFlag() {
        return this.mSettingManagerImpl.getMemorySetOperationFlag();
    }

    public int getMeterExportArea() {
        return this.mSettingManagerImpl.getMeterExportArea();
    }

    public int getMicroSwitchLockWindowState() {
        return this.mSettingManagerImpl.getMicroSwitchLockWindowState();
    }

    public int getMicroSwitchUnlockWindowState() {
        return this.mSettingManagerImpl.getMicroSwitchUnlockWindowState();
    }

    public int getMissKeyInd() {
        return this.mSettingManagerImpl.getMissKeyInd();
    }

    public int getMissPackageCount() {
        return this.mSettingManagerImpl.getMissPackageCount();
    }

    public int getModeSupportedHturn() {
        return this.mSettingManagerImpl.getModeSupportedHturn();
    }

    public int getModelSupport(int i8) {
        return this.mSettingManagerImpl.getModelSupport(i8);
    }

    public int getMotorPretensionerSeatbeltState() {
        return this.mSettingManagerImpl.getMotorPretensionerSeatbeltState();
    }

    public int getMultimediaMotorTemperatureState() {
        return 0;
    }

    public int getNavigationMediaState() {
        return this.mSettingManagerImpl.getNavigationMediaState();
    }

    public int getNfcOpenDriverDoor() {
        return this.mSettingManagerImpl.getNfcOpenDriverDoor();
    }

    public int getNightDRLightOnInd() {
        return this.mSettingManagerImpl.getNightDRLightOnInd();
    }

    public int getNightDriveLightOnReminder() {
        return 0;
    }

    public int getOffReminder() {
        return this.mSettingManagerImpl.getOffReminder();
    }

    public int getOneKeyEnterPitConfig() {
        return this.mSettingManagerImpl.getOneKeyEnterPitConfig();
    }

    public int getOverspeedLock() {
        return this.mSettingManagerImpl.getOverspeedLock();
    }

    public int getPABCode() {
        return this.mSettingManagerImpl.getPabCode();
    }

    public int getPABState() {
        return this.mSettingManagerImpl.getPabState();
    }

    public int getPM25Power() {
        return this.mSettingManagerImpl.getPM25Power();
    }

    public int getPM25Switch() {
        return 0;
    }

    public int getPM25SwitchCheck() {
        return this.mSettingManagerImpl.getPM25SwitchCheck();
    }

    public int getPM25TimeCheck() {
        return this.mSettingManagerImpl.getPM25TimeCheck();
    }

    public int getPanoramaSunRoofOnlineState() {
        return 0;
    }

    public int getParkBrakeInd() {
        return this.mSettingManagerImpl.getParkBrakeInd();
    }

    public int getPassangerSaveOrRecoverState() {
        return this.mSettingManagerImpl.getPassangerSaveOrRecoverState();
    }

    public int getPassangerSeatBackInit() {
        return this.mSettingManagerImpl.getPassangerSeatBackInit();
    }

    public int getPassangerSeatHorizontalInit() {
        return this.mSettingManagerImpl.getPassangerSeatHorizontalInit();
    }

    public int getPassengerSeatElectricAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getPassengerSeatElectricAdjustmentConfigurationByEcu();
    }

    public int getPassengerSeatHeadrestConfigurationByECU() {
        return this.mSettingManagerImpl.getPassengerSeatHeadrestConfigurationByEcu();
    }

    public int getPassengerSeatHeating3caState() {
        return this.mSettingManagerImpl.getPassengerSeatHeating3caState();
    }

    public int getPassengerSeatLegSupportAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getPassengerSeatLegSupportAdjustmentConfigurationByEcu();
    }

    public int getPassengerSeatMemoryFunctionCanByECU() {
        return this.mSettingManagerImpl.getPassengerSeatMemoryFunctionCanByEcu();
    }

    public int getPassengerSeatVentilating3caState() {
        return this.mSettingManagerImpl.getPassengerSeatVentilating3caState();
    }

    public int getPassengerState(int i8) {
        return this.mSettingManagerImpl.getPassengerState(i8);
    }

    public int getPedalSuppressionSystemState() {
        return this.mSettingManagerImpl.getPedalSuppressionSystemState();
    }

    public int getPhoneRemindCode(int i8) {
        return this.mSettingManagerImpl.getPhoneRemindCode(i8);
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

    public int getRRSEChildModeCodeWriteFb() {
        return this.mSettingManagerImpl.getRrseChildModeCodeWriteFbR();
    }

    public int getRRSEChildModeTimeWriteFb() {
        return this.mSettingManagerImpl.getRrseChildModeTimeWriteFbR();
    }

    public int getRRSEMachineSwitchState() {
        return this.mSettingManagerImpl.getRrseMachineSwitchStateR();
    }

    public int getRainAutoLockWindowState() {
        return this.mSettingManagerImpl.getRainAutoLockWindowState();
    }

    public int getRearAcOnlineState() {
        return this.mSettingManagerImpl.getRearAcOnlineState();
    }

    public int getRearAcPanelAutoLockTime() {
        return this.mSettingManagerImpl.getRearAcPanelAutoLockTime();
    }

    public int getRearDoorState() {
        return this.mSettingManagerImpl.getRearDoorState();
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

    public int getRearHeatTemp() {
        return this.mSettingManagerImpl.getRearHeatTemp();
    }

    public int getRearLeftBackrestAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getRearLeftBackrestAdjustmentConfigurationByEcu();
    }

    public int getRearLeftBidetAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getRearLeftBidetAdjustmentConfigurationByEcu();
    }

    public int getRearLeftEcuSaveOrRecoverState() {
        return this.mSettingManagerImpl.getRearLeftEcuSaveOrRecoverState();
    }

    public int getRearLeftHeadrestHeightAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getRearLeftHeadrestHeightAdjustmentConfigurationByEcu();
    }

    public int getRearLeftHeadrestLevelAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getRearLeftHeadrestLevelAdjustmentConfigurationByEcu();
    }

    public int getRearLeftHeightAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getRearLeftHeightAdjustmentConfigurationByEcu();
    }

    public int getRearLeftLegSupportAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getRearLeftLegSupportAdjustmentConfigurationByEcu();
    }

    public int getRearLeftLevelAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getRearLeftLevelAdjustmentConfigurationByEcu();
    }

    public int getRearLeftMassageSwConfiguration() {
        return this.mSettingManagerImpl.getRearLeftMassageSwConfiguration();
    }

    public int getRearLeftMemorySwitchConfigurationByECU() {
        return this.mSettingManagerImpl.getRearLeftMemorySwitchConfigurationByEcu();
    }

    public int getRearLeftSaveOrRecoverState() {
        return this.mSettingManagerImpl.getRearLeftSaveOrRecoverState();
    }

    public int getRearMirrorFlip() {
        return 0;
    }

    public int getRearRightBackrestAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getRearRightBackrestAdjustmentConfigurationByEcu();
    }

    public int getRearRightBidetAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getRearRightBidetAdjustmentConfigurationByEcu();
    }

    public int getRearRightEcuSaveOrRecoverState() {
        return this.mSettingManagerImpl.getRearRightEcuSaveOrRecoverState();
    }

    public int getRearRightFridgeHeatingScheme() {
        return this.mSettingManagerImpl.getRearRightFridgeHeatingScheme();
    }

    public int getRearRightHeadrestHeightAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getRearRightHeadrestHeightAdjustmentConfigurationByEcu();
    }

    public int getRearRightHeadrestLevelAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getRearRightHeadrestLevelAdjustmentConfigurationByEcu();
    }

    public int getRearRightHeightAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getRearRightHeightAdjustmentConfigurationByEcu();
    }

    public int getRearRightLegSupportAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getRearRightLegSupportAdjustmentConfigurationByEcu();
    }

    public int getRearRightLevelAdjustmentConfigurationByECU() {
        return this.mSettingManagerImpl.getRearRightLevelAdjustmentConfigurationByEcu();
    }

    public int getRearRightMassageSwConfiguration() {
        return this.mSettingManagerImpl.getRearRightMassageSwConfiguration();
    }

    public int getRearRightMemorySwitchConfigurationByECU() {
        return this.mSettingManagerImpl.getRearRightMemorySwitchConfigurationByEcu();
    }

    public int getRearRightSaveOrRecoverState() {
        return this.mSettingManagerImpl.getRearRightSaveOrRecoverState();
    }

    public int getRearRightSeatGreetState() {
        return this.mSettingManagerImpl.getRearRightSeatGreetState();
    }

    public int getRearSaveTemp() {
        return this.mSettingManagerImpl.getRearSaveTemp();
    }

    public int getRearSeatGreetState() {
        return this.mSettingManagerImpl.getRearSeatGreetState();
    }

    public int getRearSeatReclineConfiguration() {
        return this.mSettingManagerImpl.getRearSeatReclineConfiguration();
    }

    public int getRearViewEcuMirrorFlipState() {
        return this.mSettingManagerImpl.getRearViewEcuMirrorFlipState();
    }

    public int getRearViewMirrorAngle() {
        return 0;
    }

    public int getRearViewMirrorAutoFoldMode() {
        return 0;
    }

    public int getRearViewMirrorAutoFoldModeOnlineState() {
        return 0;
    }

    public int getRearViewMirrorFlip() {
        return this.mSettingManagerImpl.getRearViewMirrorFlip();
    }

    public int getRearViewMirrorFlipSet() {
        return this.mSettingManagerImpl.getRearViewMirrorFlipSet();
    }

    public int getRearWiperSpeedValue() {
        return this.mSettingManagerImpl.getRearWiperSpeedValue();
    }

    public int getRearviewMirrorIsAutoLightState() {
        return this.mSettingManagerImpl.getRearviewMirrorIsAutoLightState();
    }

    public int getRearviewMirrorIsState() {
        return this.mSettingManagerImpl.getRearviewMirrorIsState();
    }

    public int getRearviewMirrorIsTurnedState() {
        return this.mSettingManagerImpl.getRearviewMirrorIsTurnedState();
    }

    public float getRearviewMirrorX(int i8) {
        return this.mSettingManagerImpl.getRearviewMirrorX(i8);
    }

    public float getRearviewMirrorY(int i8) {
        return this.mSettingManagerImpl.getRearviewMirrorY(i8);
    }

    public int getRefrig1PositionMarkBit() {
        return this.mSettingManagerImpl.getRefrig1PositionMarkBit();
    }

    public int getRefrig2PositionMarkBit() {
        return this.mSettingManagerImpl.getRefrig2PositionMarkBit();
    }

    public int getRefrigNumFlag() {
        return this.mSettingManagerImpl.getRefrigNumFlag();
    }

    public int getRefrigWorkFlag() {
        return this.mSettingManagerImpl.getRefrigWorkFlag();
    }

    public int getRefrigWorkMode() {
        return this.mSettingManagerImpl.getRefrigWorkMode();
    }

    public int getRefrigeratorDoorState() {
        return this.mSettingManagerImpl.getRefrigeratorDoorState();
    }

    public int getRefrigeratorMobileTerminalPushRe() {
        return this.mSettingManagerImpl.getRefrigeratorMobileTerminalPushRe();
    }

    public int getRefrigeratorMode() {
        return this.mSettingManagerImpl.getRefrigeratorMode();
    }

    public int getRefrigeratorState() {
        return this.mSettingManagerImpl.getRefrigeratorState();
    }

    public int getRefrigeratorTemperature() {
        return this.mSettingManagerImpl.getRefrigeratorTemperature();
    }

    public int getRefrigeratorWorkMode() {
        return this.mSettingManagerImpl.getRefrigeratorWorkMode();
    }

    public int getRefrigeratorWorkState() {
        return this.mSettingManagerImpl.getRefrigeratorWorkState();
    }

    public int getRefrigeratorWorkTemperature() {
        return this.mSettingManagerImpl.getRefrigeratorWorkTemperature();
    }

    public int getRemberPowerOffState() {
        return this.mSettingManagerImpl.getRemberPowerOffState();
    }

    public int getRemenberPowerOffState() {
        return this.mSettingManagerImpl.getRemenberPowerOffState();
    }

    public int getRemoteControlDownwindow() {
        return 0;
    }

    public int getRemoteControlDownwindowState() {
        return this.mSettingManagerImpl.getRemoteControlDownwindowState();
    }

    public int getRemoteControlLockWindowState() {
        return this.mSettingManagerImpl.getRemoteControlLockWindowState();
    }

    public int getRemoteControlUnlockWindowState() {
        return this.mSettingManagerImpl.getRemoteControlUnlockWindowState();
    }

    public int getRemoteControlUpwindow() {
        return 0;
    }

    public int getRemoteControlUpwindowState() {
        return this.mSettingManagerImpl.getRemoteControlUpwindowState();
    }

    public int getRemoteCtlUnlockingState() {
        return 0;
    }

    public int getRemoteDrivingOnlineState() {
        return 0;
    }

    public int getRemoteKeyStartAcTime() {
        return this.mSettingManagerImpl.getRemoteKeyStartAcTime();
    }

    public int getReportVolume() {
        return this.mSettingManagerImpl.getReportVolume();
    }

    public int getRightElectricPedal() {
        return this.mSettingManagerImpl.getRightElectricPedal();
    }

    public int getRightHeadlampLevel() {
        return this.mSettingManagerImpl.getRightHeadlampLevel();
    }

    public int getRightRSEChildModeSwitchStatus() {
        return this.mSettingManagerImpl.getRightRseChildModeSwitchStatusR();
    }

    public int getRightRearMassageChairConfigurationForECU() {
        return this.mSettingManagerImpl.getRightRearMassageChairConfigurationForEcu();
    }

    public int getRightRearMemoryConfiguration() {
        return this.mSettingManagerImpl.getRightRearMemoryConfiguration();
    }

    public int getRightRearSafebeltStatus() {
        return this.mSettingManagerImpl.getRightRearSafebeltStatus();
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

    public int getRightRearSeatReclineConfigurationByEUC() {
        return this.mSettingManagerImpl.getRightRearSeatReclineConfigurationByEuc();
    }

    public int getRightThirdSafebeltStatus() {
        return this.mSettingManagerImpl.getRightThirdSafebeltStatus();
    }

    public int getRightViewMirrorFlipAngle() {
        return 0;
    }

    public int getRuningCloseDoorState() {
        return this.mSettingManagerImpl.getRuningCloseDoorState();
    }

    public int getSOCConfig() {
        return 0;
    }

    public int getSOCTarget() {
        return this.mSettingManagerImpl.getSOCTarget();
    }

    public int getSafeBeltCloseDriveDoor() {
        return this.mSettingManagerImpl.getSafeBeltCloseDriveDoor();
    }

    public int getSafeBeltCreepState() {
        return this.mSettingManagerImpl.getSafeBeltCreepState();
    }

    public int getSafeBeltInd() {
        return this.mSettingManagerImpl.getSafeBeltInd();
    }

    public int getSafeBeltWarnState() {
        return this.mSettingManagerImpl.getSafeBeltWarnState();
    }

    public int getSafeWarnState() {
        return this.mSettingManagerImpl.getSafeWarnState();
    }

    public int getSandAbsModeState() {
        return this.mSettingManagerImpl.getSandAbsModeState();
    }

    public int getSandAbsTipMessage() {
        return this.mSettingManagerImpl.getSandAbsTipMessage();
    }

    public int getSaveOwnerState() {
        return this.mSettingManagerImpl.getSaveOwnerState();
    }

    public int getScreenAngle() {
        return this.mSettingManagerImpl.getScreenAngle();
    }

    public int getScreenAngleValue() {
        return this.mSettingManagerImpl.getScreenAngleValue();
    }

    public int getScreenAntiPinchState() {
        return this.mSettingManagerImpl.getScreenAntiPinchState();
    }

    public int getScreenCallbackMessage() {
        return this.mSettingManagerImpl.getScreenCallbackMessage();
    }

    public int getScreenRotationAction() {
        return this.mSettingManagerImpl.getScreenRotationAction();
    }

    public int getScreenRotationOverHotState() {
        return this.mSettingManagerImpl.getScreenRotationOverHotState();
    }

    public int getScreenVoltageState() {
        return this.mSettingManagerImpl.getScreenVoltageState();
    }

    public int getSeaModeChargingSocType() {
        return this.mSettingManagerImpl.getSeaModeChargingSocType();
    }

    public int getSeatDistributionFormConfig() {
        return this.mSettingManagerImpl.getSeatDistributionFormConfig();
    }

    public int getSeatHeatingNotL1PState() {
        return this.mSettingManagerImpl.getSeatHeatingNotL1PState();
    }

    public int getSeatHeatingState(int i8) {
        return this.mSettingManagerImpl.getSeatHeatingState(i8);
    }

    public int getSeatHeatingState1(int i8) {
        return 0;
    }

    public int getSeatHeatingState2(int i8) {
        return this.mSettingManagerImpl.getSeatHeatingState2(i8);
    }

    public int getSeatHorization(int i8) {
        return this.mSettingManagerImpl.getSeatHorization(i8);
    }

    public float getSeatTurnHeight(int i8) {
        return this.mSettingManagerImpl.getSeatTurnHeight(i8);
    }

    public int getSeatVentilatingNotL1PState() {
        return this.mSettingManagerImpl.getSeatVentilatingNotL1PState();
    }

    public int getSeatVentilatingState(int i8) {
        return this.mSettingManagerImpl.getSeatVentilatingState(i8);
    }

    public int getSeatVentilatingState2(int i8) {
        return this.mSettingManagerImpl.getSeatVentilatingState2(i8);
    }

    public float getSeatbackrestPostion(int i8) {
        return this.mSettingManagerImpl.getSeatbackrestPostion(i8);
    }

    public int getSecondLeftSeatBackInit() {
        return this.mSettingManagerImpl.getSecondLeftSeatBackInit();
    }

    public int getSecondLeftSeatHorizontalInit() {
        return this.mSettingManagerImpl.getSecondLeftSeatHorizontalInit();
    }

    public int getSecondRightSeatBackInit() {
        return this.mSettingManagerImpl.getSecondRightSeatBackInit();
    }

    public int getSecondRightSeatHorizontalInit() {
        return this.mSettingManagerImpl.getSecondRightSeatHorizontalInit();
    }

    public int getServiceControlCallback() {
        return this.mSettingManagerImpl.getServiceControlCallback();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_SETTING_SET;
    }

    public int getSkyCurtainPowerOffAndDim() {
        return this.mSettingManagerImpl.getSkyCurtainPowerOffAndDim();
    }

    public int getSmartWelcomeLightState() {
        return this.mSettingManagerImpl.getSmartWelcomeLightState();
    }

    public int getSocTargetLowLimit() {
        return this.mSettingManagerImpl.getSocTargetLowLimit();
    }

    public int getSocTargetRange() {
        return this.mSettingManagerImpl.getSocTargetRange();
    }

    public int getSpeedReminderValue() {
        return this.mSettingManagerImpl.getSpeedReminderValue();
    }

    public int getSpeedSupportedTurn() {
        return this.mSettingManagerImpl.getSpeedSupportedTurn();
    }

    public int getStartKeyState() {
        return 0;
    }

    public int getStartOrPowerInd() {
        return this.mSettingManagerImpl.getStartOrPowerInd();
    }

    public int getSteerAssis() {
        return this.mSettingManagerImpl.getSteerAssis();
    }

    public int getSteerAssisSupported() {
        return this.mSettingManagerImpl.getSteerAssisSupported();
    }

    public int getSteerBack() {
        return 0;
    }

    public int getSteerPositionAutoReturn() {
        return this.mSettingManagerImpl.getSteerPositionAutoReturn();
    }

    public int getSteerType() {
        return 0;
    }

    public int getSteeringWheelHeatedPlan() {
        return this.mSettingManagerImpl.getSteeringWheelHeatedPlan();
    }

    public int getSteeringWheelHeatingAutoState() {
        return this.mSettingManagerImpl.getSteeringWheelHeatingAutoState();
    }

    public int getSteeringWheelHeatingGear() {
        return this.mSettingManagerImpl.getSteeringWheelHeatingGear();
    }

    public int getSteeringWheelHeatingState() {
        return this.mSettingManagerImpl.getSteeringWheelHeatingState();
    }

    public float getSteeringWheelY() {
        return this.mSettingManagerImpl.getSteeringWheelY();
    }

    public float getSteeringWheelZ() {
        return this.mSettingManagerImpl.getSteeringWheelZ();
    }

    public int getSterilizationState() {
        return this.mSettingManagerImpl.getSterilizationState();
    }

    public int getSterilizationWorkState() {
        return this.mSettingManagerImpl.getSterilizationWorkState();
    }

    public int getStopRemoteCtrlDriveInd() {
        return this.mSettingManagerImpl.getStopRemoteCtrlDriveInd();
    }

    public int getSupportDoorHeight() {
        return this.mSettingManagerImpl.getSupportDoorHeight();
    }

    public int getSupportSeatMassage(int i8) {
        return this.mSettingManagerImpl.getSupportSeatMassage(i8);
    }

    public int getSwcontrolState() {
        return this.mSettingManagerImpl.getSwcontrolState();
    }

    public int getSwsMediumRelatedKeys() {
        return this.mSettingManagerImpl.getSwsMediumRelatedKeys();
    }

    public int getTailBoxTakeState() {
        return this.mSettingManagerImpl.getTailBoxTakeState();
    }

    public int getTailBoxTakeSupported() {
        return this.mSettingManagerImpl.getTailBoxTakeSupported();
    }

    public int getTailInitializationState() {
        return this.mSettingManagerImpl.getTailInitializationState();
    }

    public int getTailMotorThermalProtectionState() {
        return this.mSettingManagerImpl.getTailMotorThermalProtectionState();
    }

    public int getTailPositionFeedback() {
        return this.mSettingManagerImpl.getTailPositionFeedback();
    }

    public int getTailPositionPercentage() {
        return this.mSettingManagerImpl.getTailPositionPercentage();
    }

    public int getThirdRowSeatWorkMode() {
        return this.mSettingManagerImpl.getThirdRowSeatWorkMode();
    }

    public int getTrailerLightCheckState() {
        return this.mSettingManagerImpl.getTrailerLightCheckState();
    }

    public int getTrailerModeCode() {
        return this.mSettingManagerImpl.getTrailerModeCode();
    }

    public int getTrailerModeMileage() {
        return this.mSettingManagerImpl.getTrailerModeMileage();
    }

    public int getTrailerModeState() {
        return this.mSettingManagerImpl.getTrailerModeState();
    }

    public int getTrayActionState() {
        return this.mSettingManagerImpl.getTrayActionState();
    }

    public int getTrayInitState() {
        return this.mSettingManagerImpl.getTrayInitState();
    }

    public int getTrayState() {
        return this.mSettingManagerImpl.getTrayState();
    }

    public float getTurnCaliperSplintHeight(int i8) {
        return this.mSettingManagerImpl.getTurnCaliperSplintHeight(i8);
    }

    public float getTurnCushionHeight(int i8) {
        return this.mSettingManagerImpl.getTurnCushionHeight(i8);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return 0;
    }

    public int getUnlockDownwindow() {
        return 0;
    }

    public int getUnlockSettingMode() {
        return 0;
    }

    public int getUnlockSettingOnlineState() {
        return 0;
    }

    public int getVSPState() {
        return this.mSettingManagerImpl.getVspState();
    }

    public int getVehicleState() {
        return 0;
    }

    public int getVoiceINDLevel() {
        return 0;
    }

    public int getVoiceTipMessage() {
        return this.mSettingManagerImpl.getVoiceTipMessage();
    }

    public int getVoiceTipsMessage() {
        return this.mSettingManagerImpl.getVoiceTipsMessage();
    }

    public int getWadingPatternSocTips() {
        return this.mSettingManagerImpl.getWadingPatternSocTips();
    }

    public int getWadingPatternSpeedTips() {
        return this.mSettingManagerImpl.getWadingPatternSpeedTips();
    }

    public int getWadingPatternStateTips() {
        return this.mSettingManagerImpl.getWadingPatternStateTips();
    }

    public int getWadingPatternTestTips() {
        return this.mSettingManagerImpl.getWadingPatternTestTips();
    }

    public int getWelcomeActive() {
        return this.mSettingManagerImpl.getWelcomeActive();
    }

    public int getWelcomeState() {
        return this.mSettingManagerImpl.getWelcomeState();
    }

    public int getWelcomeSupported() {
        return this.mSettingManagerImpl.getWelcomeSupported();
    }

    public int getWindscreenWiperOverhaulState(int i8) {
        return 0;
    }

    public int getWorkState() {
        return this.mSettingManagerImpl.getWorkState();
    }

    public int hasFeature(String str) {
        return 0;
    }

    @Deprecated
    public int ialDefaultReset(int i8) {
        return this.mSettingManagerImpl.ialDefaultReset(i8);
    }

    @Deprecated
    public int intelligentDriveDefaultReset(int i8) {
        return this.mSettingManagerImpl.intelligentDriveDefaultReset(i8);
    }

    public int isSomethingLeftInRefrigerator() {
        return this.mSettingManagerImpl.isSomethingLeftInRefrigerator();
    }

    @Deprecated
    public int laneAssistDriveDefaultReset(int i8) {
        return this.mSettingManagerImpl.laneAssistDriveDefaultReset(i8);
    }

    public int languageDefaultReset(int i8) {
        return this.mSettingManagerImpl.languageDefaultReset(i8);
    }

    @Deprecated
    public int lightDefaultReset(int i8) {
        return this.mSettingManagerImpl.lightDefaultReset(i8);
    }

    @Deprecated
    public int locksDefaultReset(int i8) {
        return this.mSettingManagerImpl.locksDefaultReset(i8);
    }

    public int newEnergyDefaultReset(int i8) {
        return this.mSettingManagerImpl.newEnergyDefaultReset(i8);
    }

    public int notifyDilinkStarted() {
        return this.mSettingQnxManagerImpl.notifyDilinkStarted();
    }

    public int notifyEnterTunnel(int i8) {
        return this.mSettingManagerImpl.notifyEnterTunnel(i8);
    }

    public int notifyTunnelLength(int i8) {
        return this.mSettingManagerImpl.notifyTunnelLength(i8);
    }

    public int outChargeDefaultReset(int i8) {
        return this.mSettingManagerImpl.outChargeDefaultReset(i8);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i8, int i9, int i10, Object obj) {
        return false;
    }

    public int queryAllStatus() {
        return 0;
    }

    @Deprecated
    public int rearMirrorDefaultReset(int i8) {
        return this.mSettingManagerImpl.rearMirrorDefaultReset(i8);
    }

    public void registerListener(AbsBYDAutoSettingListener absBYDAutoSettingListener) {
        this.mSettingManagerImpl.registerListener(absBYDAutoSettingListener);
        this.mSettingClusterManagerImpl.registerListener(absBYDAutoSettingListener);
    }

    public int reset(int i8, int i9) {
        return 0;
    }

    public int resetAutoExternalRearMirrorFollowUp() {
        return this.mSettingManagerImpl.resetAutoExternalRearMirrorFollowUp();
    }

    public int resetAutoPowerOnState() {
        return this.mSettingManagerImpl.resetAutoPowerOnState();
    }

    public void resetCaliperSplintHeight(int i8, int i9) {
        this.mSettingManagerImpl.resetCaliperSplintHeight(i8, i9);
    }

    public void resetCushionHeight(int i8, int i9) {
        this.mSettingManagerImpl.resetCushionHeight(i8, i9);
    }

    public int resetHeadrestParms(int i8, int i9) {
        return this.mSettingManagerImpl.resetHeadrestParms(i8, i9);
    }

    public void resetHudBrightness() {
        this.mSettingManagerImpl.resetHudBrightness();
    }

    public void resetHudDisplayContent() {
        this.mSettingManagerImpl.resetHudDisplayContent();
    }

    public void resetHudHeight() {
        this.mSettingManagerImpl.resetHudHeight();
    }

    public void resetHudMode() {
        this.mSettingManagerImpl.resetHudMode();
    }

    public void resetHudRotationAngle() {
        this.mSettingManagerImpl.resetHudRotationAngle();
    }

    public int resetHudState() {
        return this.mSettingManagerImpl.resetHudState();
    }

    public int resetMotorPretensionerSeatbelt() {
        return this.mSettingManagerImpl.resetMotorPretensionerSeatbelt();
    }

    public int resetRearviewMirrorIsSets(int i8) {
        return this.mSettingManagerImpl.resetRearviewMirrorIsSets(i8);
    }

    public void resetRearviewMirrorParams(int i8, int i9) {
        this.mSettingManagerImpl.resetRearviewMirrorParams(i8, i9);
    }

    public void resetRemenberPowerOff() {
        this.mSettingManagerImpl.resetRemenberPowerOff();
    }

    public void resetSeatParams(int i8, int i9) {
        this.mSettingManagerImpl.resetSeatParams(i8, i9);
    }

    public int resetSetting(int[] iArr, int[] iArr2) {
        return this.mSettingManagerImpl.resetSetting(iArr, iArr2);
    }

    public void resetSteeringWheelPosition(int i8) {
        this.mSettingManagerImpl.resetSteeringWheelPosition(i8);
    }

    public int resetSystemSetting(int i8) {
        return this.mSettingManagerImpl.resetSystemSetting(i8);
    }

    public int restoreCarDefaultValue() {
        return 0;
    }

    public int restoreDrDefaultValue() {
        return 0;
    }

    @Deprecated
    public int safeAssistDefaultReset(int i8) {
        return this.mSettingManagerImpl.safeAssistDefaultReset(i8);
    }

    public int saveOwnerState(int i8) {
        return this.mSettingManagerImpl.saveOwnerState(i8);
    }

    public int saveSeatParamsAll(int i8, int i9) {
        return this.mSettingManagerImpl.saveSeatParamsAll(i8, i9);
    }

    public int sendBTMacToIKEY(byte[] bArr) {
        return 0;
    }

    public int sendOffReminder(int i8) {
        return this.mSettingManagerImpl.sendOffReminder(i8);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        return this.mSettingManagerImpl.set(iArr, bYDAutoEventValue);
    }

    public int set5LevelSeatHeatingLevel(int i8, int i9) {
        return this.mSettingManagerImpl.set5LevelSeatHeatingLevel(i8, i9);
    }

    public int set5LevelSeatHeatingMode(int i8, int i9) {
        return this.mSettingManagerImpl.set5LevelSeatHeatingMode(i8, i9);
    }

    public int set5LevelSeatHeatingState(int i8, int i9) {
        return this.mSettingManagerImpl.set5LevelSeatHeatingState(i8, i9);
    }

    public int set5LevelSeatVentilatingLevel(int i8, int i9) {
        return this.mSettingManagerImpl.set5LevelSeatVentilatingLevel(i8, i9);
    }

    public int set5LevelSeatVentilatingMode(int i8, int i9) {
        return this.mSettingManagerImpl.set5LevelSeatVentilatingMode(i8, i9);
    }

    public int set5LevelSeatVentilatingState(int i8, int i9) {
        return this.mSettingManagerImpl.set5LevelSeatVentilatingState(i8, i9);
    }

    public int setACAutoAir(int i8) {
        return this.mSettingManagerImpl.setACAutoAir(i8);
    }

    public int setACBTWind(int i8) {
        return this.mSettingManagerImpl.setACBTWind(i8);
    }

    public int setACPauseCycle(int i8) {
        return this.mSettingManagerImpl.setACPauseCycle(i8);
    }

    public int setACTunnelCycle(int i8) {
        return this.mSettingManagerImpl.setACTunnelCycle(i8);
    }

    public int setAFSSwitchSate(int i8) {
        return this.mSettingManagerImpl.setAadsSwitchSate(i8);
    }

    public int setAcTemperatureUnit(int i8) {
        return this.mSettingManagerImpl.setAcTemperatureUnit(i8);
    }

    public int setAcWarmMode(int i8) {
        return this.mSettingManagerImpl.setAcWarmMode(i8);
    }

    public int setAdasRest(int[] iArr) {
        return this.mSettingManagerImpl.setAdasRest(iArr);
    }

    public int setAidedDrivingInd(int i8) {
        return this.mSettingManagerImpl.setAidedDrivingInd(i8);
    }

    public int setAirLightPanelState(int i8) {
        return 0;
    }

    public int setAllStatus() {
        return 0;
    }

    @Deprecated
    public int setAmbientMulticolorMode(int i8, int i9) {
        return this.mSettingManagerImpl.setAmbientMulticolorMode(i8, i9);
    }

    @Deprecated
    public int setAmbientMulticolorState(int i8, int i9) {
        return this.mSettingManagerImpl.setAmbientMulticolorState(i8, i9);
    }

    @Deprecated
    public int setAmbientMusicMode(int i8, int i9) {
        return this.mSettingManagerImpl.setAmbientMusicMode(i8, i9);
    }

    public int setAmbientMusicModeState(int i8, int i9) {
        return this.mSettingManagerImpl.setAmbientMusicModeState(i8, i9);
    }

    public int setAppUseLimitState(int i8) {
        return this.mSettingManagerImpl.setAppUseLimitState(i8);
    }

    public int setAutoExternalRearMirrorFollowUpSwitch(int i8) {
        return this.mSettingManagerImpl.setAutoExternalRearMirrorFollowUpSwitch(i8);
    }

    public int setAutoLockSwitch(int i8) {
        return 0;
    }

    public int setAutoLockTime(int i8) {
        return 0;
    }

    public int setAutoPowerOffState(int i8) {
        return this.mSettingManagerImpl.setAutoPowerOffState(i8);
    }

    public int setAutoPowerOnState(int i8) {
        return this.mSettingManagerImpl.setAutoPowerOnState(i8);
    }

    public int setAutoPurification(int i8) {
        return this.mSettingManagerImpl.setAutoPurification(i8);
    }

    public int setAutoRainWiperState(int i8) {
        return this.mSettingManagerImpl.setAutoRainWiperState(i8);
    }

    public int setAutoTime(int i8) {
        return 0;
    }

    public int setAutomaticParking(int i8) {
        return this.mSettingManagerImpl.setAutomaticParking(i8);
    }

    public int setBSISCollisionState(int i8) {
        return this.mSettingManagerImpl.setBsisCollisionStateS(i8);
    }

    public int setBTCallState(int i8) {
        return 0;
    }

    public int setBackDoorElectricMode(int i8) {
        return this.mSettingManagerImpl.setBackDoorElectricMode(i8);
    }

    public int setBackDoorOpenedHeight(int i8) {
        return this.mSettingManagerImpl.setBackDoorOpenedHeight(i8);
    }

    public int setBackHomeLightDelay(int i8) {
        return this.mSettingManagerImpl.setBackHomeLightDelay(i8);
    }

    public int setBackHomeLightDelayValue(int i8) {
        return this.mSettingManagerImpl.setBackHomeLightDelayValue(i8);
    }

    public int setBackRowControl(int i8) {
        return 0;
    }

    public int setBasePadAutoRiseState(int i8) {
        return 0;
    }

    public int setBleOpenDriverDoorState(int i8) {
        return this.mSettingManagerImpl.setBleOpenDriverDoorState(i8);
    }

    public int setBrakeCloseDoor(int i8) {
        return this.mSettingManagerImpl.setBrakeCloseDoor(i8);
    }

    public int setBrakePedalMode(int i8) {
        return this.mSettingManagerImpl.setBrakePedalMode(i8);
    }

    public int setCVCMode(int i8) {
        return this.mSettingManagerImpl.setCvcMode(i8);
    }

    public int setCallState(int i8) {
        return this.mSettingManagerImpl.setCallState(i8);
    }

    public int setCamping(int i8) {
        return this.mSettingManagerImpl.setCamping(i8);
    }

    public int setCampingBlanceState(int i8) {
        return this.mSettingManagerImpl.setCampingBlanceState(i8);
    }

    public int setCanopyAutomaticDimming(int i8) {
        return this.mSettingManagerImpl.setCanopyAutomaticDimming(i8);
    }

    public int setCarDoorTipsSoundType(int i8) {
        return this.mSettingManagerImpl.setCarDoorTipsSoundType(i8);
    }

    public int setCarRecorderRecording(int i8) {
        return 0;
    }

    public int setCarSetReset(int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17) {
        return 0;
    }

    public int setCarTipsSoundType(int i8) {
        return this.mSettingClusterManagerImpl.setCarTipsSoundType(i8);
    }

    public int setCarsettingRest(int[] iArr) {
        return this.mSettingManagerImpl.setCarsettingRest(iArr);
    }

    public int setChargingPort(int i8) {
        return this.mSettingManagerImpl.setChargingPort(i8);
    }

    public int setCheckSum(int i8) {
        return this.mSettingManagerImpl.setCheckSum(i8);
    }

    public int setCloseDoorLevel(int i8) {
        return this.mSettingManagerImpl.setCloseDoorLevel(i8);
    }

    public int setCloudServiceStatus(int i8) {
        return this.mSettingManagerImpl.setCloudServiceStatus(i8);
    }

    public int setComSpeedDownState(int i8) {
        return this.mSettingManagerImpl.setComSpeedDownState(i8);
    }

    public int setComSpeedUpState(int i8) {
        return this.mSettingManagerImpl.setComSpeedUpState(i8);
    }

    public int setComfortableParkingState(int i8) {
        return this.mSettingManagerImpl.setComfortableParkingState(i8);
    }

    public int setCourtesyLampTime(int i8) {
        return 0;
    }

    public int setCpdImsSwitchState(int i8) {
        return this.mSettingManagerImpl.setCpdImsSwitchState(i8);
    }

    public int setCreepModeState(int i8) {
        return this.mSettingManagerImpl.setCreepModeState(i8);
    }

    public int setCuraSwitchState(int i8) {
        return this.mSettingManagerImpl.setCuraSwitchState(i8);
    }

    public int setDampState(int i8) {
        return this.mSettingManagerImpl.setDampState(i8);
    }

    public int setDelayPowerDownState(int i8) {
        return this.mSettingManagerImpl.setDelayPowerDownState(i8);
    }

    public int setDelayPowerDownStateTime(int i8) {
        return this.mSettingManagerImpl.setDelayPowerDownStateTime(i8);
    }

    public int setDelayPowerDownTime(int i8) {
        return this.mSettingManagerImpl.setDelayPowerDownTime(i8);
    }

    public int setDelayPowerOffTime(int i8) {
        return this.mSettingManagerImpl.setDelayPowerOffTime(i8);
    }

    public int setDigitalPanelTestMode(int i8) {
        return 0;
    }

    public int setDisinfState(int i8) {
        return this.mSettingManagerImpl.setDisinfState(i8);
    }

    public int setDlinkRest(int[] iArr) {
        return this.mSettingManagerImpl.setDlinkRest(iArr);
    }

    public int setDoorLock(int i8) {
        return 0;
    }

    public int setDoorknobAutoShrinkState(int i8) {
        return this.mSettingManagerImpl.setDoorknobAutoShrinkState(i8);
    }

    public int setDriveDoorCloseInd(int i8) {
        return this.mSettingManagerImpl.setDriveDoorCloseInd(i8);
    }

    public int setDriverSeatAutoReturn(int i8) {
        return this.mSettingManagerImpl.setDriverSeatAutoReturn(i8);
    }

    public int setDriverSeatState(int i8) {
        return this.mSettingManagerImpl.setDriverSeatState(i8);
    }

    public int setDrivingRecorderSwitchState(int i8) {
        return 0;
    }

    public int setDrivingRecorderUIStatus(int i8) {
        return 0;
    }

    public int setDyHeightByVoice(int i8) {
        return this.mSettingManagerImpl.setDyHeightByVoice(i8);
    }

    public int setDyHeightToggleState(int i8) {
        return this.mSettingManagerImpl.setDyHeightToggleState(i8);
    }

    public int setDyHeightValue(int i8) {
        return this.mSettingManagerImpl.setDyHeightValue(i8);
    }

    public int setDynamicSuspensionMode(int i8) {
        return this.mSettingManagerImpl.setDynamicSuspensionMode(i8);
    }

    public int setEPedalMode(int i8) {
        return 0;
    }

    public int setElectricTailAutoModeVoiceSwitchState(int i8) {
        return this.mSettingManagerImpl.setElectricTailAutoModeVoiceSwitchState(i8);
    }

    public int setElectricTailAutomaticModeSwitchState(int i8) {
        return this.mSettingManagerImpl.setElectricTailAutomaticModeSwitchState(i8);
    }

    public int setEnergyFeedback(int i8) {
        return this.mSettingManagerImpl.setEnergyFeedback(i8);
    }

    public int setEngineOilExitUpdateState(int i8) {
        return 0;
    }

    public int setEngineSoundType(int i8) {
        return this.mSettingManagerImpl.setEngineSoundType(i8);
    }

    public int setExhibitionStartEnginePermit(int i8) {
        return this.mSettingManagerImpl.setExhibitionStartEnginePermit(i8);
    }

    public int setFestivalInfo(int i8) {
        return 0;
    }

    public int setFlSeatBasinSwitchState(int i8) {
        return this.mSettingManagerImpl.setFlSeatBasinSwitchState(i8);
    }

    public int setFormatMemoryCard(int i8) {
        return 0;
    }

    public int setFridgeChildLock(int i8) {
        return this.mSettingManagerImpl.setFridgeChildLock(i8);
    }

    public int setFridgeDoorState(int i8) {
        return this.mSettingManagerImpl.setFridgeDoorState(i8);
    }

    public int setFrontPassengerSeatSeatPotSwitchState(int i8) {
        return this.mSettingManagerImpl.setFrontPassengerSeatSeatPotSwitchState(i8);
    }

    public int setGoalRoadMode(int i8) {
        return this.mSettingManagerImpl.setGoalRoadMode(i8);
    }

    public int setHSpeedAutoCloseState(int i8) {
        return this.mSettingManagerImpl.setHSpeedAutoCloseState(i8);
    }

    public int setHandleAutoResetTime(int i8) {
        return 0;
    }

    public int setHeadlampLevel(int i8) {
        return this.mSettingManagerImpl.setHeadlampLevel(i8);
    }

    public int setHsMassageState(int i8, int i9) {
        return this.mSettingManagerImpl.setHsMassageState(i8, i9);
    }

    public int setHudBrightness(int i8) {
        return this.mSettingManagerImpl.setHudBrightness(i8);
    }

    public int setHudDisplayContent(int i8, int i9) {
        return this.mSettingManagerImpl.setHudDisplayContent(i8, i9);
    }

    public int setHudHeight(int i8) {
        return this.mSettingManagerImpl.setHudHeight(i8);
    }

    public int setHudMode(int i8) {
        return this.mSettingManagerImpl.setHudMode(i8);
    }

    public int setHudNavigationState(int i8) {
        return this.mSettingManagerImpl.setHudNavigationState(i8);
    }

    public void setHudParamsEffect() {
        this.mSettingManagerImpl.setHudParamsEffect();
    }

    public int setHudPattern(int i8) {
        return this.mSettingManagerImpl.setHudPattern(i8);
    }

    public int setHudRotationAngle(int i8) {
        return this.mSettingManagerImpl.setHudRotationAngle(i8);
    }

    public int setHudSafeDriveState(int i8) {
        return this.mSettingManagerImpl.setHudSafeDriveState(i8);
    }

    public int setHudScreenProjectionState(int i8) {
        return this.mSettingManagerImpl.setHudScreenProjectionState(i8);
    }

    public int setHudState(int i8) {
        return this.mSettingManagerImpl.setHudState(i8);
    }

    public int setIALArea(int i8, int i9) {
        return this.mSettingManagerImpl.setIALArea(i8, i9);
    }

    public int setIALBrightness(int i8, int i9, int i10) {
        return this.mSettingManagerImpl.setIALBrightness(i8, i9, i10);
    }

    public int setIALColor(int i8, int i9, int i10) {
        return this.mSettingManagerImpl.setIALColor(i8, i9, i10);
    }

    public int setIALInfo(int i8, int i9, int i10, int i11) {
        return this.mSettingManagerImpl.setIALInfo(i8, i9, i10, i11);
    }

    public int setINSTheme(int i8) {
        return this.mSettingClusterManagerImpl.setINSTheme(i8);
    }

    public int setIVIProfileSettings(int i8) {
        return this.mSettingManagerImpl.setIviProfileSettingsR(i8);
    }

    public int setIceBoxTriggerSource(int i8) {
        return this.mSettingManagerImpl.setIceBoxTriggerSource(i8);
    }

    public int setInsideLightDoorState(int i8) {
        return this.mSettingManagerImpl.setInsideLightDoorState(i8);
    }

    public int setInsideLightState(int i8) {
        return this.mSettingManagerImpl.setInsideLightState(i8);
    }

    public int setInsideRearMirrorScreenState(int i8) {
        return 0;
    }

    public int setInsideRearMirrorScreenSwitchState(int i8) {
        return 0;
    }

    public int setIntelLockOpenDriverDoor(int i8) {
        return this.mSettingManagerImpl.setIntelLockOpenDriverDoor(i8);
    }

    public int setIntelligentAcAreaState(int i8) {
        return this.mSettingManagerImpl.setIntelligentAcAreaState(i8);
    }

    public int setIntelligentLockState(int i8) {
        return this.mSettingManagerImpl.setIntelligentLockState(i8);
    }

    public int setIntelligentUnlockState(int i8) {
        return this.mSettingManagerImpl.setIntelligentUnlockState(i8);
    }

    public int setIntelligentVoiceIND(int i8) {
        return 0;
    }

    public int setInternationalLanguage(int i8) {
        return this.mSettingManagerImpl.setInternationalLanguage(i8);
    }

    public int setKeyPowerLowInd(int i8) {
        return this.mSettingManagerImpl.setKeyPowerLowInd(i8);
    }

    public int setKeyVibratorLevel(int i8) {
        return this.mSettingManagerImpl.setKeyVibratorLevel(i8);
    }

    public int setKeypadToneState(int i8) {
        return this.mSettingManagerImpl.setKeypadToneState(i8);
    }

    public void setKeypadToneStatechanged(int i8) {
        this.mSettingManagerImpl.setKeypadToneStatechanged(i8);
    }

    public int setLRSEChildAreaState(int i8) {
        return this.mSettingManagerImpl.setLrseChildAreaStateS(i8);
    }

    public int setLRSEChildModeCode1stByte(int i8) {
        return this.mSettingManagerImpl.setLrseChildModeCode1stByteS(i8);
    }

    public int setLRSEChildModeCode2ndByte(int i8) {
        return this.mSettingManagerImpl.setLrseChildModeCode2ndByteS(i8);
    }

    public int setLRSEChildModeCode3rdByte(int i8) {
        return this.mSettingManagerImpl.setLrseChildModeCode3rdByteS(i8);
    }

    public int setLRSEChildModeCode4thByte(int i8) {
        return this.mSettingManagerImpl.setLrseChildModeCode4thByteS(i8);
    }

    public int setLRSEChildModeOpenTime(int i8) {
        return this.mSettingManagerImpl.setLrseChildModeOpenTimeS(i8);
    }

    public int setLandAdvanceAtState(int i8) {
        return this.mSettingManagerImpl.setLandAdvanceAtState(i8);
    }

    public int setLanguage(int i8) {
        Log.d(TAG, "setLanguage");
        return this.mSettingClusterManagerImpl.setLanguage(i8);
    }

    public int setLanternLanguageMode(int i8) {
        return this.mSettingManagerImpl.setLanternLanguageMode(i8);
    }

    public int setLearnerDriverModeSwitchState(int i8) {
        return 0;
    }

    public int setLeaveCarPInd(int i8) {
        return this.mSettingManagerImpl.setLeaveCarPInd(i8);
    }

    public int setLeftHomeLightDelay(int i8) {
        return this.mSettingManagerImpl.setLeftHomeLightDelay(i8);
    }

    public int setLeftHomeLightDelayValue(int i8) {
        return this.mSettingManagerImpl.setLeftHomeLightDelayValue(i8);
    }

    public int setLeftRSEState(int i8) {
        return this.mSettingManagerImpl.setLeftRseStateS(i8);
    }

    public int setLeftRSESwitchCommand(int i8) {
        return this.mSettingManagerImpl.setLeftRseSwitchCommandS(i8);
    }

    public int setLeftViewMirrorFlipAngle(int i8) {
        return 0;
    }

    public int setLiftModeState(int i8) {
        return this.mSettingManagerImpl.setLiftModeState(i8);
    }

    public int setLockCarRiseWindow(int i8) {
        return this.mSettingManagerImpl.setLockCarRiseWindow(i8);
    }

    public int setLockOff(int i8) {
        return this.mSettingManagerImpl.setLockOff(i8);
    }

    public int setLockReplayVideo(int i8) {
        return 0;
    }

    public int setLongPressUnlockWindow(int i8) {
        return 0;
    }

    public int setLowBatteryInd(int i8) {
        return this.mSettingManagerImpl.setLowBatteryInd(i8);
    }

    public int setLowOilInd(int i8) {
        return this.mSettingManagerImpl.setLowOilInd(i8);
    }

    public int setMOISAssembleState(int i8) {
        return this.mSettingManagerImpl.setMoisAssembleStateS(i8);
    }

    public int setMaintainRemindState(int i8) {
        return this.mSettingManagerImpl.setMaintainRemindState(i8);
    }

    public int setMassageLevel(int i8, int i9) {
        return this.mSettingManagerImpl.setMassageLevel(i8, i9);
    }

    public int setMassageMode(int i8, int i9) {
        return this.mSettingManagerImpl.setMassageMode(i8, i9);
    }

    public int setMcuPH2State(int i8) {
        return 0;
    }

    public int setMeditationMode(int i8) {
        return this.mSettingManagerImpl.setMeditationMode(i8);
    }

    public int setMessageCounter(int i8) {
        return this.mSettingManagerImpl.setMessageCounter(i8);
    }

    public int setMicroSwitchLockWindowState(int i8) {
        return this.mSettingManagerImpl.setMicroSwitchLockWindowState(i8);
    }

    public int setMicroSwitchUnlockWindowState(int i8) {
        return this.mSettingManagerImpl.setMicroSwitchUnlockWindowState(i8);
    }

    public int setMissKeyInd(int i8) {
        return this.mSettingManagerImpl.setMissKeyInd(i8);
    }

    public int setMode(int i8) {
        return this.mSettingManagerImpl.setMode(i8);
    }

    public int setMotorPretensionerSeatbeltState(int i8) {
        return this.mSettingManagerImpl.setMotorPretensionerSeatbeltState(i8);
    }

    public int setNavigationMediaState(int i8) {
        return this.mSettingManagerImpl.setNavigationMediaState(i8);
    }

    public int setNetworkSecurityStatus(int i8) {
        return this.mSettingManagerImpl.setNetworkSecurityStatus(i8);
    }

    public int setNfcOpenDriverDoor(int i8) {
        return this.mSettingManagerImpl.setNfcOpenDriverDoor(i8);
    }

    public int setNightDRLightOnInd(int i8) {
        return this.mSettingManagerImpl.setNightDRLightOnInd(i8);
    }

    public int setOTAMode(int i8) {
        return this.mSettingManagerImpl.setOtaMode(i8);
    }

    public int setOpeningAngleInformationSetOnTheR(int i8) {
        return this.mSettingManagerImpl.setOpeningAngleInformationSetOnTheR(i8);
    }

    public int setOptimizationStatus(int i8) {
        return this.mSettingManagerImpl.setOptimizationStatus(i8);
    }

    public int setOutsideLightStateScene(int i8) {
        return this.mSettingManagerImpl.setOutsideLightStateScene(i8);
    }

    public int setOverspeedLock(int i8) {
        return this.mSettingManagerImpl.setOverspeedLock(i8);
    }

    public int setPABState(int i8) {
        return this.mSettingManagerImpl.setPabState(i8);
    }

    public int setPM25Power(int i8) {
        return this.mSettingManagerImpl.setPM25Power(i8);
    }

    public int setPM25Switch(int i8) {
        return 0;
    }

    public int setPM25SwitchCheck(int i8) {
        return this.mSettingManagerImpl.setPM25SwitchCheck(i8);
    }

    public int setPM25TimeCheck(int i8) {
        return this.mSettingManagerImpl.setPM25TimeCheck(i8);
    }

    public int setPadRotation(int i8) {
        return this.mSettingManagerImpl.setPadRotation(i8);
    }

    public int setParkBrakeInd(int i8) {
        return this.mSettingManagerImpl.setParkBrakeInd(i8);
    }

    public int setPedalSuppressionSystemState(int i8) {
        return this.mSettingManagerImpl.setPedalSuppressionSystemState(i8);
    }

    public int setPetMode(int i8) {
        return this.mSettingManagerImpl.setPetMode(i8);
    }

    public int setRRSEChildAreaState(int i8) {
        return this.mSettingManagerImpl.setRrseChildAreaStateS(i8);
    }

    public int setRRSEChildModeCode1stByte(int i8) {
        return this.mSettingManagerImpl.setRrseChildModeCode1stByteS(i8);
    }

    public int setRRSEChildModeCode2ndByte(int i8) {
        return this.mSettingManagerImpl.setRrseChildModeCode2ndByteS(i8);
    }

    public int setRRSEChildModeCode3rdByte(int i8) {
        return this.mSettingManagerImpl.setRrseChildModeCode3rdByteS(i8);
    }

    public int setRRSEChildModeCode4thByte(int i8) {
        return this.mSettingManagerImpl.setRrseChildModeCode4thByteS(i8);
    }

    public int setRRSEChildModeOpenTime(int i8) {
        return this.mSettingManagerImpl.setRrseChildModeOpenTimeS(i8);
    }

    public int setRainAutoLockWindowState(int i8) {
        return this.mSettingManagerImpl.setRainAutoLockWindowState(i8);
    }

    public int setRearAcPanelAutoLockTime(int i8) {
        return this.mSettingManagerImpl.setRearAcPanelAutoLockTime(i8);
    }

    public int setRearFridgeDelayState(int i8) {
        return this.mSettingManagerImpl.setRearFridgeDelayState(i8);
    }

    public int setRearFridgeDelayTime(int i8) {
        return this.mSettingManagerImpl.setRearFridgeDelayTime(i8);
    }

    public int setRearFridgeDoorState(int i8) {
        return this.mSettingManagerImpl.setRearFridgeDoorState(i8);
    }

    public int setRearFridgeSterilizerState(int i8) {
        return this.mSettingManagerImpl.setRearFridgeSterilizerState(i8);
    }

    public int setRearFridgeTemperature(int i8) {
        return this.mSettingManagerImpl.setRearFridgeTemperature(i8);
    }

    public int setRearFridgeWorkState(int i8) {
        return this.mSettingManagerImpl.setRearFridgeWorkState(i8);
    }

    public int setRearRightSeatGreetState(int i8) {
        return this.mSettingManagerImpl.setRearRightSeatGreetState(i8);
    }

    public int setRearSeatGreetState(int i8) {
        return this.mSettingManagerImpl.setRearSeatGreetState(i8);
    }

    public int setRearSeatsFoldOrExpand(int i8) {
        return this.mSettingManagerImpl.setRearSeatsFoldOrExpand(i8);
    }

    public int setRearViewMirrorAutoFoldMode(int i8) {
        return 0;
    }

    public int setRearViewMirrorFlip(int i8) {
        return this.mSettingManagerImpl.setRearViewMirrorFlip(i8);
    }

    public int setRearViewMirrorFlipSet(int i8) {
        return 0;
    }

    public int setRearWorkTemperature(int i8) {
        return this.mSettingManagerImpl.setRearWorkTemperature(i8);
    }

    public int setRearviewMirrorIsAutoLightState(int i8) {
        return this.mSettingManagerImpl.setRearviewMirrorIsAutoLightState(i8);
    }

    public int setRearviewMirrorIsScreenState(int i8) {
        return this.mSettingManagerImpl.setRearviewMirrorIsScreenState(i8);
    }

    public int setRearviewMirrorIsTurnedState(int i8) {
        return this.mSettingManagerImpl.setRearviewMirrorIsTurnedState(i8);
    }

    public int setRefrigWorkMode(int i8) {
        return this.mSettingManagerImpl.setRefrigWorkMode(i8);
    }

    public int setRefrigeratorMode(int i8) {
        return this.mSettingManagerImpl.setRefrigeratorMode(i8);
    }

    public int setRefrigeratorWorkMode(int i8) {
        return this.mSettingManagerImpl.setRefrigeratorWorkMode(i8);
    }

    public int setRefrigeratorWorkTemperature(int i8) {
        return this.mSettingManagerImpl.setRefrigeratorWorkTemperature(i8);
    }

    public int setRemberPowerOffState(int i8) {
        return this.mSettingManagerImpl.setRemberPowerOffState(i8);
    }

    public int setRemenberPowerOffState(int i8) {
        return this.mSettingManagerImpl.setRemenberPowerOffState(i8);
    }

    public int setRemoteControlDownwindowState(int i8) {
        return this.mSettingManagerImpl.setRemoteControlDownwindowState(i8);
    }

    public int setRemoteControlLockWindowState(int i8) {
        return this.mSettingManagerImpl.setRemoteControlLockWindowState(i8);
    }

    public int setRemoteControlUnlockWindowState(int i8) {
        return this.mSettingManagerImpl.setRemoteControlUnlockWindowState(i8);
    }

    public int setRemoteControlUpwindowState(int i8) {
        return this.mSettingManagerImpl.setRemoteControlUpwindowState(i8);
    }

    public int setRemoteCtlUnlockingState(int i8) {
        return 0;
    }

    public int setRemoteKeyStartAcTime(int i8) {
        return this.mSettingManagerImpl.setRemoteKeyStartAcTime(i8);
    }

    public int setReportVolume(int i8) {
        return this.mSettingManagerImpl.setReportVolume(i8);
    }

    public int setRestMode(int i8) {
        return this.mSettingManagerImpl.setRestMode(i8);
    }

    public int setRightRSEState(int i8) {
        return this.mSettingManagerImpl.setRightRseStateS(i8);
    }

    public int setRightRSESwitchCommand(int i8) {
        return this.mSettingManagerImpl.setRightRseSwitchCommandS(i8);
    }

    public int setRightViewMirrorFlipAngle(int i8) {
        return 0;
    }

    public int setRncState(int i8) {
        return this.mSettingManagerImpl.setRncState(i8);
    }

    public int setRuningCloseDoorState(int i8) {
        return this.mSettingManagerImpl.setRuningCloseDoorState(i8);
    }

    public int setSOCTarget(int i8) {
        return this.mSettingManagerImpl.setSOCTarget(i8);
    }

    public int setSWSMediumRelatedKeys(int i8) {
        return this.mSettingManagerImpl.setSwsMediumRelatedKeysS(i8);
    }

    public int setSafeBeltCloseDriveDoorState(int i8) {
        return this.mSettingManagerImpl.setSafeBeltCloseDriveDoorState(i8);
    }

    public int setSafeBeltInd(int i8) {
        return this.mSettingManagerImpl.setSafeBeltInd(i8);
    }

    public int setSafeBeltWarnState(int i8) {
        return this.mSettingManagerImpl.setSafeBeltWarnState(i8);
    }

    public int setSafeWarnState(int i8) {
        return this.mSettingManagerImpl.setSafeWarnState(i8);
    }

    public int setSandAbsModeState(int i8) {
        return this.mSettingManagerImpl.setSandAbsModeState(i8);
    }

    public int setScreenAngleValue(int i8) {
        return this.mSettingManagerImpl.setScreenAngleValue(i8);
    }

    public int setScreenRotation(int i8) {
        return this.mSettingManagerImpl.setScreenRotation(i8);
    }

    public int setScreenRotationLock(int i8) {
        return this.mSettingManagerImpl.setScreenRotationLock(i8);
    }

    public int setSeatHeatingState(int i8, int i9) {
        return this.mSettingManagerImpl.setSeatHeatingState(i8, i9);
    }

    public int setSeatHeatingState1(int i8, int i9) {
        return 0;
    }

    public int setSeatVentilatingState(int i8, int i9) {
        return this.mSettingManagerImpl.setSeatVentilatingState(i8, i9);
    }

    public int setSkyCurtainPowerOffAndDim(int i8) {
        return this.mSettingManagerImpl.setSkyCurtainPowerOffAndDim(i8);
    }

    public int setSmartWelcomeLightState(int i8) {
        return this.mSettingManagerImpl.setSmartWelcomeLightState(i8);
    }

    public int setSpeedReminderValue(int i8) {
        return this.mSettingManagerImpl.setSpeedReminderValue(i8);
    }

    public int setStartOrPowerInd(int i8) {
        return this.mSettingManagerImpl.setStartOrPowerInd(i8);
    }

    public int setSteerAssis(int i8) {
        return this.mSettingManagerImpl.setSteerAssis(i8);
    }

    public int setSteerPositionAutoReturn(int i8) {
        return this.mSettingManagerImpl.setSteerPositionAutoReturn(i8);
    }

    public int setSteeringWheelHeatingAutoState(int i8) {
        return this.mSettingManagerImpl.setSteeringWheelHeatingAutoState(i8);
    }

    public int setSteeringWheelHeatingGear(int i8) {
        return this.mSettingManagerImpl.setSteeringWheelHeatingGear(i8);
    }

    public int setSteeringWheelHeatingState(int i8) {
        return this.mSettingManagerImpl.setSteeringWheelHeatingState(i8);
    }

    public int setSterilizationState(int i8) {
        return this.mSettingManagerImpl.setSterilizationState(i8);
    }

    public int setStopRemoteCtrlDriveInd(int i8) {
        return this.mSettingManagerImpl.setStopRemoteCtrlDriveInd(i8);
    }

    public int setTailBoxTakeState(int i8) {
        return this.mSettingManagerImpl.setTailBoxTakeState(i8);
    }

    public int setTextContent(byte[] bArr) {
        return 0;
    }

    public int setTextInfo(int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16) {
        return 0;
    }

    public int setThirdRowSeatLeftBackrest(int i8) {
        return this.mSettingManagerImpl.setThirdRowSeatLeftBackrest(i8);
    }

    public int setThirdRowSeatRightBackrest(int i8) {
        return this.mSettingManagerImpl.setThirdRowSeatRightBackrest(i8);
    }

    public int setTrailerLightCheckState(int i8) {
        return this.mSettingManagerImpl.setTrailerLightCheckState(i8);
    }

    public int setTrailerModeState(int i8) {
        return this.mSettingManagerImpl.setTrailerModeState(i8);
    }

    public int setTrayState(int i8) {
        return this.mSettingManagerImpl.setTrayState(i8);
    }

    public int setUiVersion(int i8) {
        return this.mSettingManagerImpl.setUiVersion(i8);
    }

    public int setUnlockSettingMode(int i8) {
        return 0;
    }

    public int setUserdefinedBodyColor(int i8) {
        return 0;
    }

    public int setVSPState(int i8) {
        return this.mSettingManagerImpl.setVspState(i8);
    }

    public int setVoiceINDLevel(int i8) {
        return 0;
    }

    public int setWeatherAndTempState(int i8, int i9) {
        return 0;
    }

    public int setWeatherForcast(byte[] bArr) {
        return 0;
    }

    public int setWelcomeState(int i8) {
        return this.mSettingManagerImpl.setWelcomeState(i8);
    }

    public int setWindscreenWiperOverhaulState(int i8, int i9) {
        return 0;
    }

    public int setWipeMode(int i8) {
        return this.mSettingManagerImpl.setWipeMode(i8);
    }

    public int setWorkCommond(int i8) {
        return this.mSettingManagerImpl.setWorkCommond(i8);
    }

    public int setWorkState(int i8) {
        return this.mSettingManagerImpl.setWorkState(i8);
    }

    public int setWorkTemperature(int i8) {
        return this.mSettingManagerImpl.setWorkTemperature(i8);
    }

    @Deprecated
    public int soundFiledDefaultReset(int i8) {
        return this.mSettingManagerImpl.soundFiledDefaultReset(i8);
    }

    public int takeDrivingRecorderPicture() {
        return 0;
    }

    public void turnCaliperSplintPercent(int i8, int i9) {
        this.mSettingManagerImpl.turnCaliperSplintPercent(i8, i9);
    }

    public void turnCushionPercent(int i8, int i9) {
        this.mSettingManagerImpl.turnCushionPercent(i8, i9);
    }

    public void turnHeadrestHeightPercent(int i8, int i9) {
        this.mSettingManagerImpl.turnHeadrestHeightPercent(i8, i9);
    }

    public void turnHeadrestHorizationPercent(int i8, int i9) {
        this.mSettingManagerImpl.turnHeadrestHorizationPercent(i8, i9);
    }

    public void turnHeadrestTurnHeight(int i8, int i9) {
        this.mSettingManagerImpl.turnHeadrestTurnHeight(i8, i9);
    }

    public void turnHeadrestTurnHorization(int i8, int i9) {
        this.mSettingManagerImpl.turnHeadrestTurnHorization(i8, i9);
    }

    public int turnOffInsideLight() {
        return 0;
    }

    public int turnOffInsideLight(int i8) {
        return 0;
    }

    public void turnRearviewMirror(int i8) {
        this.mSettingManagerImpl.turnRearviewMirror(i8);
    }

    public int turnRearviewMirrorIs(int i8) {
        return this.mSettingManagerImpl.turnRearviewMirrorIs(i8);
    }

    public int turnSeatBackRearLeftLeftRcs(int i8) {
        return this.mSettingManagerImpl.turnSeatBackRearLeftLeftRcs(i8);
    }

    public int turnSeatBackRearLeftRcs(int i8) {
        return this.mSettingManagerImpl.turnSeatBackRearLeftRcs(i8);
    }

    public int turnSeatBackRearLeftRightRcs(int i8) {
        return this.mSettingManagerImpl.turnSeatBackRearLeftRightRcs(i8);
    }

    public int turnSeatBackRearRightLeftRcs(int i8) {
        return this.mSettingManagerImpl.turnSeatBackRearRightLeftRcs(i8);
    }

    public int turnSeatBackRearRightRcs(int i8) {
        return this.mSettingManagerImpl.turnSeatBackRearRightRcs(i8);
    }

    public int turnSeatBackRearRightRightRcs(int i8) {
        return this.mSettingManagerImpl.turnSeatBackRearRightRightRcs(i8);
    }

    public void turnSeatCaliperSplint(int i8, int i9) {
        this.mSettingManagerImpl.turnSeatCaliperSplint(i8, i9);
    }

    public int turnSeatCaliperSplintRearLeftLeftRcs(int i8) {
        return this.mSettingManagerImpl.turnSeatCaliperSplintRearLeftLeftRcs(i8);
    }

    public int turnSeatCaliperSplintRearLeftRcs(int i8) {
        return this.mSettingManagerImpl.turnSeatCaliperSplintRearLeftRcs(i8);
    }

    public int turnSeatCaliperSplintRearLeftRightRcs(int i8) {
        return this.mSettingManagerImpl.turnSeatCaliperSplintRearLeftRightRcs(i8);
    }

    public int turnSeatCaliperSplintRearRightLeftRcs(int i8) {
        return this.mSettingManagerImpl.turnSeatCaliperSplintRearRightLeftRcs(i8);
    }

    public int turnSeatCaliperSplintRearRightRcs(int i8) {
        return this.mSettingManagerImpl.turnSeatCaliperSplintRearRightRcs(i8);
    }

    public int turnSeatCaliperSplintRearRightRightRcs(int i8) {
        return this.mSettingManagerImpl.turnSeatCaliperSplintRearRightRightRcs(i8);
    }

    public void turnSeatCushion(int i8, int i9) {
        this.mSettingManagerImpl.turnSeatCushion(i8, i9);
    }

    public int turnSeatCushionRearLeftLeftRcs(int i8) {
        return this.mSettingManagerImpl.turnSeatCushionRearLeftLeftRcs(i8);
    }

    public int turnSeatCushionRearLeftRcs(int i8) {
        return this.mSettingManagerImpl.turnSeatCushionRearLeftRcs(i8);
    }

    public int turnSeatCushionRearLeftRightRcs(int i8) {
        return this.mSettingManagerImpl.turnSeatCushionRearLeftRightRcs(i8);
    }

    public int turnSeatCushionRearRightLeftRcs(int i8) {
        return this.mSettingManagerImpl.turnSeatCushionRearRightLeftRcs(i8);
    }

    public int turnSeatCushionRearRightRcs(int i8) {
        return this.mSettingManagerImpl.turnSeatCushionRearRightRcs(i8);
    }

    public int turnSeatCushionRearRightRightRcs(int i8) {
        return this.mSettingManagerImpl.turnSeatCushionRearRightRightRcs(i8);
    }

    public void turnSeatHeight(int i8, int i9) {
        this.mSettingManagerImpl.turnSeatHeight(i8, i9);
    }

    public void turnSeatHeightPercent(int i8, int i9) {
        this.mSettingManagerImpl.turnSeatHeightPercent(i8, i9);
    }

    public void turnSeatHorization(int i8, int i9) {
        this.mSettingManagerImpl.turnSeatHorization(i8, i9);
    }

    public void turnSeatHorizationPercent(int i8, int i9) {
        this.mSettingManagerImpl.turnSeatHorizationPercent(i8, i9);
    }

    public int turnSeatHorizationRearLeftLeftRcs(int i8) {
        return this.mSettingManagerImpl.turnSeatHorizationRearLeftLeftRcs(i8);
    }

    public int turnSeatHorizationRearLeftRcs(int i8) {
        return this.mSettingManagerImpl.turnSeatHorizationRearLeftRcs(i8);
    }

    public int turnSeatHorizationRearLeftRightRcs(int i8) {
        return this.mSettingManagerImpl.turnSeatHorizationRearLeftRightRcs(i8);
    }

    public int turnSeatHorizationRearRightLeftRcs(int i8) {
        return this.mSettingManagerImpl.turnSeatHorizationRearRightLeftRcs(i8);
    }

    public int turnSeatHorizationRearRightRcs(int i8) {
        return this.mSettingManagerImpl.turnSeatHorizationRearRightRcs(i8);
    }

    public int turnSeatHorizationRearRightRightRcs(int i8) {
        return this.mSettingManagerImpl.turnSeatHorizationRearRightRightRcs(i8);
    }

    public void turnSeatbackrest(int i8, int i9) {
        this.mSettingManagerImpl.turnSeatbackrest(i8, i9);
    }

    public void turnSeatbackrestPercent(int i8, int i9) {
        this.mSettingManagerImpl.turnSeatbackrestPercent(i8, i9);
    }

    public void turnSteeringWheel(int i8) {
        this.mSettingManagerImpl.turnSteeringWheel(i8);
    }

    @Deprecated
    public int unitDefaultReset(int i8) {
        return this.mSettingManagerImpl.unitDefaultReset(i8);
    }

    public void unregisterListener(AbsBYDAutoSettingListener absBYDAutoSettingListener) {
        this.mSettingManagerImpl.unregisterListener(absBYDAutoSettingListener);
        this.mSettingClusterManagerImpl.unregisterListener(absBYDAutoSettingListener);
    }

    public int voiceCtlBackDoor(int i8) {
        return this.mSettingManagerImpl.voiceCtlBackDoor(i8);
    }

    public int voiceOpenMirrorlsTurnedState(int i8) {
        return this.mSettingManagerImpl.voiceOpenMirrorlsTurnedState(i8);
    }

    public void registerListener(AbsBYDAutoSettingListener absBYDAutoSettingListener, int[] iArr) {
        this.mSettingManagerImpl.registerListener(absBYDAutoSettingListener, iArr);
    }
}

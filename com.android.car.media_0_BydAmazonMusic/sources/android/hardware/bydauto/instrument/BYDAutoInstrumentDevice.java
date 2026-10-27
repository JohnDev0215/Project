package android.hardware.bydauto.instrument;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.instrument.tsimpl.InstrumentManagerImpl;
import android.hardware.bydauto.instrument.tsimpl.InstrumentMediaManagerImpl;
import android.hardware.bydauto.instrument.tsimpl.InstrumentSettingsManagerImpl;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoInstrumentDevice extends AbsBYDAutoDevice {
    public static final int ACCELERATE_TIMER = 8;
    public static final int ACC_COLOR_BEAUTIFUL_RED = 6;
    public static final int ACC_COLOR_BEAUTIFUL_YELLOW = 6;
    public static final int ACC_COLOR_BLUE = 5;
    public static final int ACC_COLOR_GREEN = 4;
    public static final int ACC_COLOR_GREEN_MAIN = 6;
    public static final int ACC_COLOR_INVALID = 0;
    public static final int ACC_COLOR_RED = 3;
    public static final int ACC_COLOR_WHITE = 1;
    public static final int ACC_COLOR_YELLOW = 2;
    public static final int ACC_SPEED_MAX = 240;
    public static final int ACC_SPEED_MIN = 0;
    public static final int ADAS_INTERFACE = 6;
    public static final int AIR_HEATING_OIL_WARN_ALARM = 2;
    public static final int AIR_HEATING_OIL_WARN_INVALID = 0;
    public static final int AIR_HEATING_OIL_WARN_NORMAL = 1;
    public static final int APPOINTMENT_HOUR_MAX = 23;
    public static final int APPOINTMENT_HOUR_MIN = 0;
    public static final int APPOINTMENT_MINUTE_MAX = 59;
    public static final int APPOINTMENT_MINUTE_MIN = 0;
    public static final int AUTOMATIC_PARKING_INTERFACE = 11;
    public static final int AVERAGE_SPEED_MAX = 240;
    public static final int AVERAGE_SPEED_MIN = 0;
    public static final int BAR = 1;
    public static final int BATTERY_PERCENT_PH_MAX = 100;
    public static final int BATTERY_PERCENT_PH_MIN = 0;
    public static final int BODY_POSITION_CENTER = 1;
    public static final int BODY_POSITION_HEAD_LEFT = 2;
    public static final int BODY_POSITION_HEAD_RIGHT = 3;
    public static final int BODY_POSITION_INVALID = 0;
    public static final int BOTH_GRAY = 1;
    public static final int BOTH_GREEN = 4;
    public static final int BOTH_YELLOW_MODE_LKS = 9;
    public static final int B_MALFUNCTION_ABS = 14;
    public static final int B_MALFUNCTION_ACC_SYSTEM = 36;
    public static final int B_MALFUNCTION_AC_HEAT_MANAGEMENT = 7;
    public static final int B_MALFUNCTION_AEB_SYSTEM = 43;
    public static final int B_MALFUNCTION_AUTO_NETWORK = 24;
    public static final int B_MALFUNCTION_BRAKE_FLUID_LEVEL_LOW = 3;
    public static final int B_MALFUNCTION_BRAKE_PAD = 32;
    public static final int B_MALFUNCTION_BRAKE_SYSTEM = 4;
    public static final int B_MALFUNCTION_BRAKE_SYSTEM_1 = 11;
    public static final int B_MALFUNCTION_BSD_SYSTEM = 40;
    public static final int B_MALFUNCTION_CHARGING_EQUIPMENT = 2;
    public static final int B_MALFUNCTION_CHARGING_SYSTEM = 20;
    public static final int B_MALFUNCTION_COOLANT_LEVEL_LOW = 6;
    public static final int B_MALFUNCTION_ELECTRONIC_PARKING_SYSTEM = 12;
    public static final int B_MALFUNCTION_ENGINE_ACCESSORY_FUNCTION_LIMIT = 26;
    public static final int B_MALFUNCTION_ENGINE_OIL_DETECTING_SYSTEM = 9;
    public static final int B_MALFUNCTION_ENGINE_OIL_PRESSURE_LACK = 8;
    public static final int B_MALFUNCTION_ENGINE_SYSTEM = 25;
    public static final int B_MALFUNCTION_ESP = 30;
    public static final int B_MALFUNCTION_EV_FUNCTION_LIMIT = 18;
    public static final int B_MALFUNCTION_FCW_SYSTEM = 41;
    public static final int B_MALFUNCTION_GEAR_SYSTEM = 19;
    public static final int B_MALFUNCTION_HDC = 31;
    public static final int B_MALFUNCTION_HMA_SYSTEM = 39;
    public static final int B_MALFUNCTION_IRON_BATTERY = 22;
    public static final int B_MALFUNCTION_IRON_BATTERY_POWER_LOW = 23;
    public static final int B_MALFUNCTION_LDW = 47;
    public static final int B_MALFUNCTION_LEFT_HEADLAMP_SYSTEM = 33;
    public static final int B_MALFUNCTION_LKS = 46;
    public static final int B_MALFUNCTION_LOW_VOLTAGE_POWER_SUPPLY_SYSTEM = 15;
    public static final int B_MALFUNCTION_MAX = 48;
    public static final int B_MALFUNCTION_MEMORY_SYSTEM = 35;
    public static final int B_MALFUNCTION_MULTIFUNCTION_VIDEO_CONTROLLER = 44;
    public static final int B_MALFUNCTION_MULTI_VIDEO_CON_FUNCTION_LIMIT = 45;
    public static final int B_MALFUNCTION_PARKING_POWER_LACK = 13;
    public static final int B_MALFUNCTION_PEDESTRIAN_DETECTION_SYSTEM = 37;
    public static final int B_MALFUNCTION_POWER_GENERATION_SYSTEM = 16;
    public static final int B_MALFUNCTION_POWER_SYSTEM = 17;
    public static final int B_MALFUNCTION_PRE_MILLIMETER_WAVE_RADAR = 42;
    public static final int B_MALFUNCTION_RIGHT_HEADLAMP_SYSTEM = 34;
    public static final int B_MALFUNCTION_SRS = 29;
    public static final int B_MALFUNCTION_START_TYPE_IRON_BATTERY_SYSTEM = 21;
    public static final int B_MALFUNCTION_STEERING_SYSTEM = 5;
    public static final int B_MALFUNCTION_TIRE_PRESSURE_DETECTING_SYSTEM = 10;
    public static final int B_MALFUNCTION_TRAFFIC_SIGN_RECOGNITION_SYSTEM = 38;
    public static final int B_MALFUNCTION_TRANSMISSION_FUNCTION_LIMIT = 27;
    public static final int B_MALFUNCTION_TRANSMISSION_SYSTEM = 28;
    public static final int B_MALFUNCTION_VEHICLE_CHARGING_SYSTEM = 1;
    public static final int CALL_DIAL = 1;
    public static final int CALL_HANG_UP = 2;
    public static final int CALL_STATE_CALLLING = 3;
    public static final int CALL_STATE_DIALING = 2;
    public static final int CALL_STATE_ENDING = 4;
    public static final int CALL_STATE_RINGING = 1;
    public static final int CALL_TIME_HOUR_INVALID = 255;
    public static final int CALL_TIME_HOUR_MAX = 99;
    public static final int CALL_TIME_HOUR_MIN = 0;
    public static final int CALL_TIME_MINUTE_INVALID = 255;
    public static final int CALL_TIME_MINUTE_MAX = 59;
    public static final int CALL_TIME_MINUTE_MIN = 0;
    public static final int CALL_TIME_SECOND_INVALID = 255;
    public static final int CALL_TIME_SECOND_MAX = 59;
    public static final int CALL_TIME_SECOND_MIN = 0;
    public static final int CAMERA_TYPE_BUS_LANE = 8;
    public static final int CAMERA_TYPE_EMERGENCY_LANE = 17;
    public static final int CAMERA_TYPE_HOV_LANE = 18;
    public static final int CAMERA_TYPE_INTERVAL_IN = 5;
    public static final int CAMERA_TYPE_INTERVAL_OUT = 9;
    public static final int CAMERA_TYPE_LEFT_TURN_FOBIDDEN = 12;
    public static final int CAMERA_TYPE_NONE = 0;
    public static final int CAMERA_TYPE_NO_ADMITTANCE = 15;
    public static final int CAMERA_TYPE_NO_AUTO_LANE = 6;
    public static final int CAMERA_TYPE_NO_PARKING = 10;
    public static final int CAMERA_TYPE_NO_PASS_GREEN_LIGHT = 19;
    public static final int CAMERA_TYPE_ONE_WAY_ROAD = 11;
    public static final int CAMERA_TYPE_PECCANRY = 3;
    public static final int CAMERA_TYPE_PRESS_PHOTO = 4;
    public static final int CAMERA_TYPE_RIGHT_TURN_FOBIDDEN = 13;
    public static final int CAMERA_TYPE_SECURITY_MONITORING = 7;
    public static final int CAMERA_TYPE_SPEED_LIMITED = 1;
    public static final int CAMERA_TYPE_TRAFFIC_LIGHT = 2;
    public static final int CAMERA_TYPE_U_TURN_FOBIDDEN = 14;
    public static final int CAMERA_TYPE_VEHICLE_LIMITED = 16;
    public static final int CAM_REMAINING_MILEAGE_MAX = 16777214;
    public static final int CAM_REMAINING_MILEAGE_MIN = 0;
    public static final int CHARGE_OPTION_HOUR = 1;
    public static final int CHARGE_OPTION_INVALID = 0;
    public static final int CHARGE_OPTION_MINUTE = 2;
    public static final int CHARGE_PERCENT_MAX = 100;
    public static final int CHARGE_PERCENT_MIN = 0;
    public static final double CHARGE_POWER_MAX = 100.0d;
    public static final double CHARGE_POWER_MIN = -50.0d;
    public static final int CHARGING_INTERFACE = 4;
    public static final int CLEAR_FAULT_FINISH = 1;
    public static final int CLEAR_FAULT_INVALID = 0;
    public static final int CLEAR_INFO_AVERAGE_ENERGY_CONSUMPTION_LATEST_50KM = 5;
    public static final int CLEAR_INFO_AVERAGE_SPEED = 3;
    public static final int CLEAR_INFO_EXTERNAL_CHARGE_POWER = 2;
    public static final int CLEAR_INFO_FUEL_CONSUMPTION = 1;
    public static final int CLEAR_INFO_TRAVEL_TIME = 4;
    public static final int COLOR_INVALID = 0;
    public static final int COLOR_RED = 3;
    public static final int COLOR_WHITE = 1;
    public static final int COLOR_YELLOW = 2;
    public static final int CONTINUE_ALARM = 11;
    public static final int COUNTDOWN_HOUR_MAX = 23;
    public static final int COUNTDOWN_HOUR_MIN = 0;
    public static final int COUNTDOWN_MINUTE_MAX = 59;
    public static final int COUNTDOWN_MINUTE_MIN = 0;
    public static final int DASHBOARD_ALARM = 0;
    public static final int DASHBOARD_NOT_ALARM = 1;
    public static final int DD_2IN1_FAULT_ENGINE_SPPED_HIGH = 91;
    public static final int DD_2IN1_FAULT_YUN_ABS_SYS = 98;
    public static final int DD_2IN1_FAULT_YUN_BRAKING_SYS_FAILUE = 95;
    public static final int DD_2IN1_FAULT_YUN_COOLANT_TEMP_HIGH = 106;
    public static final int DD_2IN1_FAULT_YUN_ENGINE = 97;
    public static final int DD_2IN1_FAULT_YUN_ESP = 99;
    public static final int DD_2IN1_FAULT_YUN_LOW_OIL_PRESS = 94;
    public static final int DD_2IN1_FAULT_YUN_LOW_VOLT_POWER_SUPPLY_SYS = 96;
    public static final int DD_2IN1_FAULT_YUN_OK = 105;
    public static final int DD_2IN1_FAULT_YUN_POWER_BATTARY = 103;
    public static final int DD_2IN1_FAULT_YUN_POWER_SYS = 104;
    public static final int DD_2IN1_FAULT_YUN_SRS = 100;
    public static final int DD_2IN1_FAULT_YUN_STEERING_SYS = 101;
    public static final int DD_2IN1_FAULT_YUN_TYRE_PRESS = 102;
    public static final int DD_FAULT_ACCELERATION_BREAK_PEDAL = 89;
    public static final int DD_FAULT_AC_WARM_DIESEL = 17;
    public static final int DD_FAULT_ADAPTIVE_CRUISE_LIMITED = 83;
    public static final int DD_FAULT_ADAPT_GEARBOX = 31;
    public static final int DD_FAULT_AIR_HOT_MANAGEMENT_FAILURE = 68;
    public static final int DD_FAULT_AP_LIMITED = 38;
    public static final int DD_FAULT_AUTO_EMERGENCY_BRAKING_LIMITED = 79;
    public static final int DD_FAULT_BATTERY_LOW = 92;
    public static final int DD_FAULT_BLIND_SPOT_MONITORING_LIMITED = 85;
    public static final int DD_FAULT_CAR_CHARGE_SYSTEM = 20;
    public static final int DD_FAULT_CELL_LOE_POWER = 33;
    public static final int DD_FAULT_CELL_LOW_POWER_AC_LIMITED = 43;
    public static final int DD_FAULT_CELL_LOW_POWER_DRIVE_LIMITED = 44;
    public static final int DD_FAULT_CELL_TEMP_AC_LIMITED = 42;
    public static final int DD_FAULT_CHARGE_POT_NOT_CLOSE = 34;
    public static final int DD_FAULT_CHECK_BRAKE_PAD = 76;
    public static final int DD_FAULT_CHECK_BRAKING_SYSTEM = 46;
    public static final int DD_FAULT_CHECK_CAR_NETWORK = 52;
    public static final int DD_FAULT_CHECK_ENGINE_SYSTEM = 60;
    public static final int DD_FAULT_CHECK_FRONT_WAVE_RADAR = 78;
    public static final int DD_FAULT_CHECK_GEAR_SYSTEM = 74;
    public static final int DD_FAULT_CHECK_HDC_SYSTEM = 75;
    public static final int DD_FAULT_CHECK_IRON_BATTERY_SYSTEM = 59;
    public static final int DD_FAULT_CHECK_MEMORY_SYATEM = 40;
    public static final int DD_FAULT_CHECK_MOONROOF = 41;
    public static final int DD_FAULT_CHECK_MUL_VIDEO_CONTROLLER = 77;
    public static final int DD_FAULT_CHECK_OIL_MONITORING_SYSTEM = 71;
    public static final int DD_FAULT_CHECK_PERSON_DETECTION_SYSTEM = 81;
    public static final int DD_FAULT_CHECK_POWERTRAIN = 49;
    public static final int DD_FAULT_CHECK_POWER_GENERATION_SYSTEM = 73;
    public static final int DD_FAULT_CHECK_PREDICTIVE_COLLISION_WARNING_SYSTEM = 80;
    public static final int DD_FAULT_CHECK_SAFETYBELT_SYSTEM = 39;
    public static final int DD_FAULT_CHECK_SRS_SYSTEM = 65;
    public static final int DD_FAULT_CHECK_TRANSMISSION_SYSTEM = 53;
    public static final int DD_FAULT_CHECK_TRANSMISSION_SYSTEM_SEC = 54;
    public static final int DD_FAULT_DISCHARGE_DEVICE = 18;
    public static final int DD_FAULT_DISCHARGE_STOP = 32;
    public static final int DD_FAULT_ENGINE_ASSESSORY_FUNC_LIMITED = 61;
    public static final int DD_FAULT_ENGINE_COOLANT_TEMP_HIGH = 87;
    public static final int DD_FAULT_ENGINE_COOLANT_TEMP_HIGH_SAFE_PARK = 88;
    public static final int DD_FAULT_ENGINE_START_FAILED = 66;
    public static final int DD_FAULT_ENTER_FORCED_EV = 22;
    public static final int DD_FAULT_EV_FUNC_LIMITED = 56;
    public static final int DD_FAULT_EXIT_SMART_DRIVING_MODE_MANUAL_TAKEOVER = 57;
    public static final int DD_FAULT_GPF_REBUILD = 25;
    public static final int DD_FAULT_HIGH_CLUTCH_TEMPERATURE = 63;
    public static final int DD_FAULT_HIGH_OIL_LEVEL = 70;
    public static final int DD_FAULT_HIGH_TRANSMISSION_OIL_TEMPERATURE = 64;
    public static final int DD_FAULT_INSUFFICIENT_OIL_PRESSURE = 48;
    public static final int DD_FAULT_IRON_BATTERY_FAILURE = 51;
    public static final int DD_FAULT_KEY_LOW_POWER = 27;
    public static final int DD_FAULT_KEY_NOT_DETECTED = 26;
    public static final int DD_FAULT_KEY_NOT_DETECTED_IN_CAR = 29;
    public static final int DD_FAULT_KEY_NOT_DETECTED_READER = 28;
    public static final int DD_FAULT_LANE_DEPARTURE_FUNC_LIMITED = 84;
    public static final int DD_FAULT_LANE_KEEPING_LIMITED = 82;
    public static final int DD_FAULT_LINK_ERROR_KEY_DAY = 24;
    public static final int DD_FAULT_LINK_ERROR_KEY_HOUR = 25;
    public static final int DD_FAULT_LOW_BATTERY_LIMITED_POWER = 55;
    public static final int DD_FAULT_LOW_BRAKE_FLUID_LEVEL = 47;
    public static final int DD_FAULT_LOW_COOLANT_LEVEL = 67;
    public static final int DD_FAULT_LOW_OIL_LEVEL = 69;
    public static final int DD_FAULT_LOW_POWER_LIMIT = 21;
    public static final int DD_FAULT_LOW_TYRE_PRESSURE = 72;
    public static final int DD_FAULT_LOW_VOLTAGE_POWER_SUPPLY_SYSTEM_FAILURE = 50;
    public static final int DD_FAULT_NEED_CHARGE = 36;
    public static final int DD_FAULT_NEED_MAINTAIN = 23;
    public static final int DD_FAULT_NOTICE_ABS_SYSTEM = 0;
    public static final int DD_FAULT_NOTICE_ADD_FUEL = 90;
    public static final int DD_FAULT_NOTICE_EPB = 1;
    public static final int DD_FAULT_NOTICE_EPB_NOT_RELEASED = 2;
    public static final int DD_FAULT_NOTICE_ESP_SYSTEM = 3;
    public static final int DD_FAULT_NOTICE_FRONT_LIGHT_STSTEM = 4;
    public static final int DD_FAULT_NOTICE_LEAVE_CAR = 5;
    public static final int DD_FAULT_NOTICE_LF_LIGHT_STSTEM = 6;
    public static final int DD_FAULT_NOTICE_LV_SUPPLY = 7;
    public static final int DD_FAULT_NOTICE_PARK_SYSTEM = 8;
    public static final int DD_FAULT_NOTICE_PARK_WEAK = 9;
    public static final int DD_FAULT_NOTICE_POWER_SYSTEM = 93;
    public static final int DD_FAULT_NOTICE_RF_LIGHT_STSTEM = 10;
    public static final int DD_FAULT_NOTICE_STEERING_SYSTEM = 11;
    public static final int DD_FAULT_NOTICE_TRYE_PRES_DET = 12;
    public static final int DD_FAULT_OUT_CHARGE_DEVICE = 19;
    public static final int DD_FAULT_PARK_BRAKE_UNRELEASED = 37;
    public static final int DD_FAULT_REMOTE_DRIVING_SUSPENDED = 45;
    public static final int DD_FAULT_SMART_CAMERA_USELESS = 86;
    public static final int DD_FAULT_SMART_DRIVING_FAILURE_MANUAL_TAKEOVER = 58;
    public static final int DD_FAULT_SWITCH_TO_P_GEAR = 35;
    public static final int DD_FAULT_TRANSMISSION_FUNC_LIMITED = 62;
    public static final int DD_FAULT_WASH_ELEC_GATE = 30;
    public static final int DD_INDIRECT_TYPE_PRES_LF = 13;
    public static final int DD_INDIRECT_TYPE_PRES_LR = 14;
    public static final int DD_INDIRECT_TYPE_PRES_RF = 15;
    public static final int DD_INDIRECT_TYPE_PRES_RR = 16;
    public static final int DEFAULT_STATE = 0;
    public static final int DEGREE_CENTIGRADE = 1;
    public static final int DEGREE_FAHRENHEIT = 2;
    public static final int DESTINATION_STATE_NOT_SET = 1;
    public static final int DESTINATION_STATE_SET_DONE = 2;
    public static final int DEVICE_HAS_THE_FEATURE = 1;
    public static final int DEVICE_HAS_THE_MALFUNCTION = 1;
    public static final int DEVICE_NOT_HAS_THE_FEATURE = 0;
    public static final int DEVICE_NOT_HAS_THE_MALFUNCTION = 0;
    public static final int DEVICE_THE_FEATURE_LINK_ERROR = 65535;
    public static final int DEVICE_THE_FEATURE_NEVER_GET = 2;
    public static final int DIRECT_EAST = 3;
    public static final int DIRECT_INVALID = 0;
    public static final int DIRECT_NORTH = 1;
    public static final int DIRECT_NORTHEAST = 2;
    public static final int DIRECT_NORTHWEST = 8;
    public static final int DIRECT_SOUTH = 5;
    public static final int DIRECT_SOUTHEAST = 4;
    public static final int DIRECT_WEST = 7;
    public static final int DIRECT_WESTSOUTH = 6;
    public static final int DISCHARGE_CONNECTING = 1;
    public static final int DISCHARGE_DOING = 2;
    public static final int DISCHARGE_FAILED = 4;
    public static final int DISCHARGE_FINISH = 3;
    public static final int DISCHARGE_INTERFACE = 5;
    public static final int DISCHARGE_INVALID = 0;
    public static final double DISCHARGE_MAX = 100.0d;
    public static final double DISCHARGE_MIN = 0.0d;
    public static final int DISCHARGE_NOT_DISPLAY = 4095;
    public static final int DISCHARGE_VTOL = 1;
    public static final int DISCHARGE_VTOV = 2;
    public static final int DISPLAY_50_MILES_AVE_ENERGY = 1;
    public static final int DISPLAY_AVE_SPEED = 10;
    public static final int DISPLAY_CHARGE_APPOINTMENT_CHARGING = 6;
    public static final int DISPLAY_CHARGE_APPOINTMENT_COUNTDOWN = 7;
    public static final int DISPLAY_CHARGE_OVER = 4;
    public static final int DISPLAY_CHARGE_PAUSING = 5;
    public static final int DISPLAY_CHARGING = 3;
    public static final int DISPLAY_CONNECTING = 2;
    public static final int DISPLAY_COUNT_ENERGY = 2;
    public static final int DISPLAY_DRVIE_TIME = 9;
    public static final int DISPLAY_ENDURANCE_MILEAGE = 7;
    public static final int DISPLAY_ENERGY_STREAM = 12;
    public static final int DISPLAY_FRONTCAR_NODISPLAY_DISTANCE = 1;
    public static final int DISPLAY_OIL_COUNT = 8;
    public static final int DISPLAY_OUT_CHARGE = 11;
    public static final int DISPLAY_PERIOD_OIL = 6;
    public static final int DISPLAY_PRESSURE_TYRE = 3;
    public static final int DISTANCE_INVALID = 0;
    public static final int DISTANCE_MAX = 16777214;
    public static final int DISTANCE_MIN = 0;
    public static final int DOOR_HOOD = 5;
    public static final int DOOR_LEFT_FRONT = 1;
    public static final int DOOR_LEFT_REAR = 3;
    public static final int DOOR_LUGGAGE_DOOR = 6;
    public static final int DOOR_RIGHT_FRONT = 2;
    public static final int DOOR_RIGHT_REAR = 4;
    public static final int DOOR_STATE_CLOSED = 0;
    public static final int DOOR_STATE_OPEN = 1;
    public static final double DRIVE_MILEAGE_MAX = 9999.9d;
    public static final double DRIVE_MILEAGE_MIN = 0.0d;
    public static final double DRIVE_TIME_MAX = 99.9d;
    public static final double DRIVE_TIME_MIN = 0.0d;
    public static final int DRIVINGINFO_50KMAVE = 1;
    public static final int DRIVINGINFO_AVERSPEED = 10;
    public static final int DRIVINGINFO_CUMENE = 2;
    public static final int DRIVINGINFO_ENERGYFLOW = 12;
    public static final int DRIVINGINFO_EXTERCHARG = 11;
    public static final int DRIVINGINFO_FUELCONSUMP = 8;
    public static final int DRIVINGINFO_INSTANTFUEL = 6;
    public static final int DRIVINGINFO_RANGE = 7;
    public static final int DRIVINGINFO_SINGLESTROKE = 9;
    public static final int DRIVINGINFO_TIREPREE = 3;
    public static final int DRIVING_INFORMATION = 1;
    public static final int DRIVING_INFO_INVALID = 0;
    public static final int DRIVING_INFO_NEXT = 2;
    public static final int DRIVING_INFO_PREVIOUS = 1;
    public static final int ENERY_CONSUMPTION_DISPLAY_NO = 1;
    public static final int ENERY_CONSUMPTION_DISPLAY_RESERVED = 3;
    public static final int ENERY_CONSUMPTION_DISPLAY_VAILID = 0;
    public static final int ENERY_CONSUMPTION_DISPLAY_YES = 2;
    public static final int EXP_NOT_DISPLAY = 1;
    public static final int EXP_SETTING = 2;
    public static final int EXP_TIME_COUNT = 3;
    public static final double EXTERNAL_CHARGE_POWER_PH_MAX = 9999.9d;
    public static final double EXTERNAL_CHARGE_POWER_PH_MIN = 0.0d;
    public static final double EXTERNAL_CHARGING_POWER_MAX = 99999.9d;
    public static final double EXTERNAL_CHARGING_POWER_MIN = 0.0d;
    public static final int FAULT_CRUISE_MAIN_INDICATOR = 0;
    public static final int FAULT_CRUISE_MAIN_INDICATOR_COLOR = 0;
    public static final int FAULT_INDICATOR_025HZ = 3;
    public static final int FAULT_INDICATOR_05HZ = 4;
    public static final int FAULT_INDICATOR_1HZ = 5;
    public static final int FAULT_INDICATOR_2HZ = 6;
    public static final int FAULT_INDICATOR_4HZ = 7;
    public static final int FAULT_INDICATOR_COLOR_BLUE = 5;
    public static final int FAULT_INDICATOR_COLOR_GREEN = 4;
    public static final int FAULT_INDICATOR_COLOR_INVALID = 0;
    public static final int FAULT_INDICATOR_COLOR_RED = 3;
    public static final int FAULT_INDICATOR_COLOR_WHITE = 1;
    public static final int FAULT_INDICATOR_COLOR_YELLOW = 2;
    public static final int FAULT_INDICATOR_INVALID = 0;
    public static final int FAULT_INDICATOR_LIGHT = 1;
    public static final int FAULT_INDICATOR_QUENCH = 2;
    public static final int FAULT_PROMPT = 3;
    public static final String FEATURE_CON_DIS_UNIT = "ConDisUnit";
    public static final String FEATURE_ENERGY_DISPLAY = "InstrumentEnergyDisplay";
    public static final String FEATURE_POWER_UNIT = "PowerUnit";
    public static final String FEATURE_PRESS_UNIT = "PressUnit";
    public static final String FEATURE_TEMP_UNIT = "TempUnit";
    public static final int FEEDBACK_INVALID = 0;
    public static final int FIRST_GEAR = 1;
    public static final int FIRST_GEAR_SPACING = 3;
    public static final int FIRST_MENU_MAX = 7;
    public static final int FIRST_MENU_MIN = 0;
    public static final int FORTH_GEAR = 4;
    public static final int FORTH_GEAR_SPACING = 6;
    public static final int FOUR_HZ_LIGHT = 7;
    public static final int FUEL_CONSUMPTION_AND_DISTANCE_UNIT = 2;
    public static final int FUEL_CONSUMPTION_UNIT = 5;
    public static final int GET_AC_WARM = 4;
    public static final int GET_AVE_SPEED = 10;
    public static final int GET_DRIVE_TIME = 9;
    public static final int GET_ENERGY_STREAM = 12;
    public static final int GET_INVLIDE = 0;
    public static final int GET_OUT_CHARGE = 11;
    public static final int GET_SPEED_COUNT = 5;
    public static final int GROUND_BAD = 4;
    public static final int GUN_UNLOCK = 7;
    public static final int HP = 2;
    public static final int INFOR_LENGTH_MAX = 255;
    public static final int INFOR_LENGTH_MIN = 0;
    public static final int INSTRUMENT_ADAS_DISPLAY = 1;
    public static final int INSTRUMENT_ADAS_ENTER_DISPLAY = 1;
    public static final int INSTRUMENT_ADAS_EXIT_DISPLAY = 2;
    public static final int INSTRUMENT_ADAS_INVALID = 0;
    public static final int INSTRUMENT_ADAS_NOT_DISPLAY = 0;
    public static final int INSTRUMENT_ADAS_RESRVED = 3;
    public static final int INSTRUMENT_AIR_HEATING_DISPLAY = 2;
    public static final int INSTRUMENT_AIR_HEATING_INVALID = 0;
    public static final int INSTRUMENT_AIR_HEATING_NODISPLAY = 1;
    public static final int INSTRUMENT_ALARM_BUZZLE_ATATE_OFF = 0;
    public static final int INSTRUMENT_ALARM_BUZZLE_ATATE_ON = 1;
    public static final int INSTRUMENT_BACKLIGHT_BRIGHTNESS_MAX = 22;
    public static final int INSTRUMENT_BACKLIGHT_BRIGHTNESS_MIN = 1;
    public static final int INSTRUMENT_BACKLIGHT_MODE_AUTO = 1;
    public static final int INSTRUMENT_BACKLIGHT_MODE_OFF = 0;
    public static final int INSTRUMENT_BACKLIGHT_MODE_ON = 1;
    public static final int INSTRUMENT_BACKLIGHT_MODE_RELATE = 2;
    public static final int INSTRUMENT_COMMAND_BUSY = -2147482647;
    public static final int INSTRUMENT_COMMAND_FAILED = -2147482648;
    public static final int INSTRUMENT_COMMAND_INVALID_VALUE = -2147482645;
    public static final int INSTRUMENT_COMMAND_SUCCESS = 0;
    public static final int INSTRUMENT_COMMAND_TIMEOUT = -2147482646;
    public static final int INSTRUMENT_DIRECT_TYRE_PRES__DISPLAY = 2;
    public static final int INSTRUMENT_DIRECT_TYRE_PRES__INVALID = 0;
    public static final int INSTRUMENT_DIRECT_TYRE_PRES__NODISPLAY = 1;
    public static final int INSTRUMENT_ENERGEY_DISPLAY_INVALID = 0;
    public static final int INSTRUMENT_ENERGEY_DISPLAY_REMAINING_MILEAGE = 1;
    public static final int INSTRUMENT_ENERGEY_DISPLAY_SOC = 2;
    public static final int INSTRUMENT_FUEL_LOW_ALARM_NO = 1;
    public static final int INSTRUMENT_FUEL_LOW_ALARM_YES = 0;
    public static final int INSTRUMENT_LCD_SCREEN = 2;
    public static final int INSTRUMENT_NAVI_CLOSE = 1;
    public static final int INSTRUMENT_NAVI_FULL = 4;
    public static final int INSTRUMENT_NAVI_GO_COMPANY = 2;
    public static final int INSTRUMENT_NAVI_GO_HOME = 1;
    public static final int INSTRUMENT_NAVI_SIMPLE = 2;
    public static final int INSTRUMENT_NAVI_SMALL = 3;
    public static final int INSTRUMENT_PEN_DUAN_SCREEN = 1;
    public static final int INSTRUMENT_ROAD_NAME_CHECK_INVALID = 2;
    public static final int INSTRUMENT_ROAD_NAME_CHECK_VALID = 1;
    public static final int INSTRUMENT_SCREEN_BROKE_CODE = 2;
    public static final int INSTRUMENT_SCREEN_LARGE_SIZE = 5;
    public static final int INSTRUMENT_SCREEN_SMALL_SIZE = 3;
    public static final int INSTRUMENT_SCREEN_SPLICING = 4;
    public static final int INSTRUMENT_SCREEN_TWO_IN_ONE = 1;
    public static final int INSTRUMENT_SPORT_MODE_CLOSE = 1;
    public static final int INSTRUMENT_SPORT_MODE_OPEN = 2;
    public static final int INSTRUMENT_UNIT_INSTANT_FUELCON_INVALID = 0;
    public static final int INSTRUMENT_UNIT_INSTANT_FUELCON_KM_L = 2;
    public static final int INSTRUMENT_UNIT_INSTANT_FUELCON_L_100KM = 1;
    public static final int INSTRUMENT_UNIT_INSTANT_FUELCON_L_H = 4;
    public static final int INSTRUMENT_UNIT_INSTANT_FUELCON_MPG = 3;
    public static final int INSTRUMET_ACCELEROMETER_VIEW = 8;
    public static final int INSTRUMET_ADAS_VIEW = 6;
    public static final int INSTRUMET_CHARGE_VIEW = 4;
    public static final int INSTRUMET_DISCHARGE_VIEW = 5;
    public static final int INSTRUMET_DRIVING_VIEW = 1;
    public static final int INSTRUMET_FAULT_VIEW = 3;
    public static final int INSTRUMET_INVALID_VIEW = 0;
    public static final int INSTRUMET_MENU_VIEW = 2;
    public static final int INSTRUMET_TRAVEL_VIEW = 7;
    public static final int INTERFACE_CLOSE = 2;
    public static final int INTERFACE_DISPLAY = 1;
    public static final int INTERFACE_INVALID = 0;
    public static final int INTERFACE_NOT_DISPLAY = 2;
    public static final int INTERFACE_OPEN = 1;
    public static final int INVALID = 0;
    public static final int IN_BOTH_SIDE = 3;
    public static final int IN_LEFT_SIDE = 1;
    public static final int IN_RIGHT_SIDE = 2;
    public static final int KEY_DETECTION_REMINDER_INVALID = 0;
    public static final int KEY_DETECTION_REMINDER_KEY_LEAVING = 2;
    public static final int KEY_DETECTION_REMINDER_NO_KEY = 1;
    public static final int KM_P_L_AND_KM = 2;
    public static final int KM_P_L_UNIT = 2;
    public static final int KPA = 3;
    public static final int KW = 1;
    public static final int KWH_P_100KM_AND_KM = 5;
    public static final int KWH_P_100MI_AND_MILE = 6;
    public static final int LARGER_FEEDBACK = 2;
    public static final int LEFT_DEVIATION = 1;
    public static final int LEFT_GRAY_RIGHT_GREEN = 2;
    public static final int LEFT_GRAY_RIGHT_RED = 7;
    public static final int LEFT_GREEN_RIGHT_GRAY = 3;
    public static final int LEFT_GREEN_RIGHT_RED = 8;
    public static final int LEFT_LINE = 1;
    public static final int LEFT_RED_RIGHT_GRAY = 5;
    public static final int LEFT_RED_RIGHT_GREEN = 6;
    public static final int LEFT_RED_RIGHT_YELLOW = 10;
    public static final int LEFT_YELLOW_RIGHT_RED = 11;
    public static final int LIGHT_INVALID = 0;
    public static final int LIGHT_OFF = 2;
    public static final int LIGHT_ON = 1;
    public static final int LIMITED_POWER = 6;
    public static final int LINE_GRAY_DASH = 1;
    public static final int LINE_GREEN_DASH = 2;
    public static final int LINE_INVALID = 0;
    public static final int LINE_RED_SOLID = 4;
    public static final int LINE_YELLOW_DASH = 3;
    public static final int LINK_ERROR_KEY_DAY = 0;
    public static final int LINK_ERROR_KEY_HOUR = 1;
    public static final int LINK_ERR_DAY_MAX = 7;
    public static final int LINK_ERR_DAY_MIN = 1;
    public static final int LINK_ERR_HOUR_MAX = 23;
    public static final int LINK_ERR_HOUR_MIN = 1;
    public static final int LINK_NORMAL = 0;
    public static final int LOW_TEMPERATURE = 8;
    public static final int L_P_100KM_AND_KM = 1;
    public static final int L_P_100KM_UNIT = 1;
    public static final int L_P_H_UNIT = 4;
    public static final int MAINTENANCE_MILEAGE = 2;
    public static final int MAINTENANCE_MILEAGE_KILOMETER_MAX = 34001;
    public static final int MAINTENANCE_MILEAGE_KILOMETER_MIN = 0;
    public static final int MAINTENANCE_TIME = 1;
    public static final int MAINTENANCE_TIME_DAY_MAX = 991;
    public static final int MAINTENANCE_TIME_DAY_MIN = 0;
    public static final int MALFUNCTION_ABS_SYSTEM = 6;
    public static final int MALFUNCTION_AC_CHARGING_EQUIPMENT = 50;
    public static final int MALFUNCTION_AC_HEAT_MANAGEMENT = 25;
    public static final int MALFUNCTION_ADAPTIVE_CRUISE_CONTROL_SYSTEM = 41;
    public static final int MALFUNCTION_BATTERY = 16;
    public static final int MALFUNCTION_BLIND_AREA_DETECTION_SYSTEM = 45;
    public static final int MALFUNCTION_BRAKE_PADS = 34;
    public static final int MALFUNCTION_CAR_CHARGER = 49;
    public static final int MALFUNCTION_CHARGING_CABINET = 48;
    public static final int MALFUNCTION_CHARGING_GUN_CONNECTION = 51;
    public static final int MALFUNCTION_CHARGING_GUN_NOT_DISCONNECT = 58;
    public static final int MALFUNCTION_CHARGING_SOCKET = 40;
    public static final int MALFUNCTION_CHARGING_SYSTEM = 4;
    public static final int MALFUNCTION_DC_SYSTEM = 47;
    public static final int MALFUNCTION_ELECTRIC_PARKING_BRAKE = 10;
    public static final int MALFUNCTION_ELECTRONIC_PARKING = 26;
    public static final int MALFUNCTION_ENGINE = 5;
    public static final int MALFUNCTION_ENGINE_COOLANT_LOW = 52;
    public static final int MALFUNCTION_ENGINE_OIL_DETECTION_SYSTEM = 53;
    public static final int MALFUNCTION_ENGINE_PART_FUNCTION = 31;
    public static final int MALFUNCTION_ENGINE_SYSTEM = 30;
    public static final int MALFUNCTION_EPS = 12;
    public static final int MALFUNCTION_ESP = 7;
    public static final int MALFUNCTION_EV = 20;
    public static final int MALFUNCTION_EV_FUNCTION = 27;
    public static final int MALFUNCTION_FRONT_BELT = 23;
    public static final int MALFUNCTION_FRONT_LIGHT_SYSTEM = 37;
    public static final int MALFUNCTION_GEAR_SYSTEM = 28;
    public static final int MALFUNCTION_HEV = 21;
    public static final int MALFUNCTION_HIGH_BATTERY_TEMPERATURE = 17;
    public static final int MALFUNCTION_HIGH_MOTOR_TEMPERATURE = 15;
    public static final int MALFUNCTION_HIGH_VOLTAGE_SYSTEM_LEAKAGE = 55;
    public static final int MALFUNCTION_HIGH_WATER_TEMPERATURE = 9;
    public static final int MALFUNCTION_INSTRUMENT_DISPLAY = 1;
    public static final int MALFUNCTION_INTELLIGENT_FAR_LIGHT_LAMP_SYSTEM = 44;
    public static final int MALFUNCTION_IRON_BATTERY = 29;
    public static final int MALFUNCTION_LANE_ASSISTANCE_SYSTEM = 42;
    public static final int MALFUNCTION_LEFT_FRONT_LIGHT_SYSTEM = 35;
    public static final int MALFUNCTION_LOW_VOLTAGE_BATTERY_SYSTEM = 57;
    public static final int MALFUNCTION_MACHINE_OIL_LOW_PRESSURE = 2;
    public static final int MALFUNCTION_MAX = 59;
    public static final int MALFUNCTION_MEMORY_SYSTEM = 38;
    public static final int MALFUNCTION_MOTOR_COOLANT_TEMP_OVER_HIGH = 56;
    public static final int MALFUNCTION_NETWORK_SYSTEM = 54;
    public static final int MALFUNCTION_OK = 19;
    public static final int MALFUNCTION_PARKING_BRAKE = 3;
    public static final int MALFUNCTION_POWER_SYSTEM = 18;
    public static final int MALFUNCTION_PREDICTIVE_BRAKE_SYSTEM = 46;
    public static final int MALFUNCTION_QUICK_AIR_LEAK = 8;
    public static final int MALFUNCTION_RIGHT_FRONT_LIGHT_SYSTEM = 36;
    public static final int MALFUNCTION_SMART_KEY = 22;
    public static final int MALFUNCTION_SRS = 11;
    public static final int MALFUNCTION_STEERING_SYSTEM = 24;
    public static final int MALFUNCTION_SUNROOF_SYSTEM = 39;
    public static final int MALFUNCTION_SVS = 14;
    public static final int MALFUNCTION_TRAFFIC_SIGN_RECOGNITION_SYSTEM = 43;
    public static final int MALFUNCTION_TRANSMISSION_FUNCTION = 32;
    public static final int MALFUNCTION_TRANSMISSION_SYSTEM = 33;
    public static final int MALFUNCTION_TYRE_PRESSURE = 13;
    public static final int MAP_STATE_FULL_CLOSE = 3;
    public static final int MAP_STATE_FULL_OPEN = 6;
    public static final int MAP_STATE_NAVI_CLOSE = 1;
    public static final int MAP_STATE_NOT_ACTIVATION = 8;
    public static final int MAP_STATE_NOT_AGREE_AGREEMENT = 9;
    public static final int MAP_STATE_SIMPLE_CLOSE = 4;
    public static final int MAP_STATE_SIMPLE_OPEN = 7;
    public static final int MAP_STATE_SMALL_CLOSE = 2;
    public static final int MAP_STATE_SMALL_OPEN = 5;
    public static final int MAP_TYPE_AUTO = 3;
    public static final int MAP_TYPE_CARPLAY = 2;
    public static final int MAP_TYPE_INVALID = 0;
    public static final int MAP_TYPE_LOCAL = 1;
    public static final int MEMORY_SYS_RESTORE_TURN_RING = 8;
    public static final int MEMORY_SYS_SET_FAILED = 10;
    public static final int MEMORY_SYS_SET_SUCCESS = 9;
    public static final int MENU_INTERFACE = 2;
    public static final int MENU_STATE_CLOSED = 1;
    public static final int MENU_STATE_INVALID = 0;
    public static final int MENU_STATE_OPEN = 2;
    public static final int MENU_STATE_REVERSE = 3;
    public static final int MILEAGE_50KM = 3;
    public static final int MILEAGE_NORMAL = 0;
    public static final int MILEAGE_ONE = 1;
    public static final int MILEAGE_RESET = 1;
    public static final int MILEAGE_TWO = 2;
    public static final int MILE_PH_MAX = 99999999;
    public static final int MILE_PH_MIN = 0;
    public static final int MILE_TYPE_EV = 2;
    public static final int MILE_TYPE_HEV = 1;
    public static final int MILE_TYPE_ONE = 3;
    public static final int MILE_TYPE_TOTAL = 0;
    public static final int MILE_TYPE_TWO = 4;
    public static final int MODED_INVALID = 0;
    public static final int MODE_DAY = 2;
    public static final int MODE_ECO = 1;
    public static final int MODE_INVALID = 0;
    public static final int MODE_LDW = 1;
    public static final int MODE_NIGHT = 1;
    public static final int MODE_NORMAL = 3;
    public static final int MODE_PCW = 2;
    public static final int MODE_SNOW = 4;
    public static final int MODE_SPORT = 2;
    public static final int MODULE_STATE_FAULT = 1;
    public static final int MODULE_STATE_NORMAL = 0;
    public static final int MPG_GB_AND_MILE = 3;
    public static final int MPG_UNIT = 3;
    public static final int MPG_US_AND_MILE = 4;
    public static final int MUSIC_DEFAULT = 0;
    public static final int MUSIC_LOADING = 3;
    public static final int MUSIC_PLAY = 1;
    public static final int MUSIC_PLAYBACK_PROGRESS_MAX = 100;
    public static final int MUSIC_PLAYBACK_PROGRESS_MIN = 0;
    public static final int MUSIC_SOURCE_AM = 18;
    public static final int MUSIC_SOURCE_AMAZON = 32;
    public static final int MUSIC_SOURCE_ARD_AUDIOTHEK = 38;
    public static final int MUSIC_SOURCE_AUDIALS_PLAY = 41;
    public static final int MUSIC_SOURCE_AUDIBLE = 44;
    public static final int MUSIC_SOURCE_AUTO = 31;
    public static final int MUSIC_SOURCE_AUX = 5;
    public static final int MUSIC_SOURCE_BT = 6;
    public static final int MUSIC_SOURCE_CARPLAY = 30;
    public static final int MUSIC_SOURCE_DAB = 17;
    public static final int MUSIC_SOURCE_DEEZER = 34;
    public static final int MUSIC_SOURCE_FLO = 33;
    public static final int MUSIC_SOURCE_FM = 7;
    public static final int MUSIC_SOURCE_HIMALAYA = 12;
    public static final int MUSIC_SOURCE_IHEART_RADIO = 40;
    public static final int MUSIC_SOURCE_KARAOKE = 15;
    public static final int MUSIC_SOURCE_KOALA = 10;
    public static final int MUSIC_SOURCE_KUGOU = 13;
    public static final int MUSIC_SOURCE_KUWO = 9;
    public static final int MUSIC_SOURCE_LIBBY = 43;
    public static final int MUSIC_SOURCE_LOCAL = 1;
    public static final int MUSIC_SOURCE_MAX = 14;
    public static final int MUSIC_SOURCE_MELON = 36;
    public static final int MUSIC_SOURCE_OTHERS = 11;
    public static final int MUSIC_SOURCE_POCKET_CASTS = 39;
    public static final int MUSIC_SOURCE_RADIOLINE = 42;
    public static final int MUSIC_SOURCE_RADIO_FRANCE = 45;
    public static final int MUSIC_SOURCE_SD = 4;
    public static final int MUSIC_SOURCE_SPORTSCHAU = 46;
    public static final int MUSIC_SOURCE_SPOTIFY = 16;
    public static final int MUSIC_SOURCE_TIDAL = 35;
    public static final int MUSIC_SOURCE_TUNELN_RADIO = 37;
    public static final int MUSIC_SOURCE_USB1 = 2;
    public static final int MUSIC_SOURCE_USB2 = 3;
    public static final int MUSIC_SOURCE_VIDEO = 8;
    public static final int MUSIC_SOURCE_YUNTING = 14;
    public static final int MUSIC_SOURCE_ZDFHEUTE = 47;
    public static final int MUSIC_STOP = 2;
    public static final int NATIVE_POWER = 5;
    public static final int NAVIGATION_INTERFACE_RESERVED = 10;
    public static final int NAVI_CLOSE = 4;
    public static final int NAVI_INVALID = 0;
    public static final int NAVI_OPENING = 3;
    public static final int NAVI_OPEN_NOT_SET_DEST = 1;
    public static final int NAVI_OPEN_SET_DEST = 2;
    public static final int NAVI_STATE_END = 2;
    public static final int NAVI_STATE_START = 1;
    public static final int NAVI_TYPE_ANDROID_AUTO = 3;
    public static final int NAVI_TYPE_CARPLAY = 2;
    public static final int NAVI_TYPE_LOCAL = 1;
    public static final int NAVI_TYPE_UNKNOWN = 0;
    public static final int NAV_CLOSE = 1;
    public static final int NAV_FULL = 4;
    public static final int NAV_SIMPLE = 2;
    public static final int NAV_SMALL = 3;
    public static final int NODISPLAY_FRONTCAR_DISTANCE = 2;
    public static final int NONE = 0;
    public static final int NORMAL_600_ALARM = 7;
    public static final int NORMAL_DEVIATION = 3;
    public static final int NOTICE_NOT_DISPLAY = 1;
    public static final int NOT_DISPLAY = 1;
    public static final int NO_B_MALFUNCTION = 0;
    public static final int NO_MALFUNCTION = 0;
    public static final int NO_OBJECT = 0;
    public static final int ODOMETER_EV = 5;
    public static final int ODOMETER_HEV = 4;
    public static final int ODOMETER_INVALID = 0;
    public static final int ODOMETER_ONE = 2;
    public static final int ODOMETER_TOTAL = 1;
    public static final int ODOMETER_TWO = 3;
    public static final int OIL_DISPLAY_MAX = 5;
    public static final int OIL_DISPLAY_MIN = 0;
    public static final int ONE_ALARM = 3;
    public static final int ONE_HZ_LIGHT = 5;
    public static final int PCW_LIGHT_GREEN = 4;
    public static final int PCW_LIGHT_ON = 1;
    public static final int PCW_LIGHT_RED = 3;
    public static final String PERMISSION_BYDAUTO_INSTRUMENT_COMMON = "android.permission.BYDAUTO_INSTRUMENT_COMMON";
    public static final String PERMISSION_BYDAUTO_INSTRUMENT_GET = "android.permission.BYDAUTO_INSTRUMENT_GET";
    public static final String PERMISSION_BYDAUTO_INSTRUMENT_SET = "android.permission.BYDAUTO_INSTRUMENT_SET";
    public static final int POINT_25_HZ_LIGHT = 3;
    public static final int POINT_50_HZ_LIGHT = 4;
    public static final int POSITION_LF = 3;
    public static final int POSITION_LR = 4;
    public static final int POSITION_RF = 1;
    public static final int POSITION_RR = 2;
    public static final double POWER_CONSUME_PH_DEFAULT = 4095.0d;
    public static final double POWER_CONSUME_PH_MAX = 99.9d;
    public static final double POWER_CONSUME_PH_MIN = -99.9d;
    public static final int POWER_OFF_ERROR_GEAR_SIGNAL_ERR = 3;
    public static final int POWER_OFF_ERROR_INVALID = 0;
    public static final int POWER_OFF_ERROR_NO_3G_SIGNAL = 6;
    public static final int POWER_OFF_ERROR_SPEED_OVER_5KMH = 4;
    public static final int POWER_OFF_ERROR_SPEED_OVER_5KMH_START_VALID = 5;
    public static final int POWER_OFF_ERROR_SPEED_SIGNAL_ERR = 1;
    public static final int POWER_OFF_ERROR_WRONG_GEAR = 2;
    public static final int POWER_ON_ERROR_AUTO_NOT_RIGHT_GEAR = 6;
    public static final int POWER_ON_ERROR_BRAKING_SWITCH_ERR = 5;
    public static final int POWER_ON_ERROR_CHECKING_WITH_ECM_ERR = 8;
    public static final int POWER_ON_ERROR_CHECKING_WITH_MM_ERR = 9;
    public static final int POWER_ON_ERROR_COMMU_WITH_ECU_ERR = 4;
    public static final int POWER_ON_ERROR_GEAR_SIGNAL_ERR = 3;
    public static final int POWER_ON_ERROR_IG1_POWER_ERR = 1;
    public static final int POWER_ON_ERROR_INVALID = 0;
    public static final int POWER_ON_ERROR_MANUAL_NOT_NEUTRAL_GEAR = 10;
    public static final int POWER_ON_ERROR_NO_DETECT_KEY = 11;
    public static final int POWER_ON_ERROR_NO_READY_SIGNAL = 7;
    public static final int POWER_ON_ERROR_PROHIBIT_STARTING = 2;
    public static final int POWER_UNIT = 3;
    public static final int POWER_UNIT_HP = 2;
    public static final int POWER_UNIT_KW = 1;
    public static final int PRESSURE_MAX = 1000;
    public static final int PRESSURE_MIN = 0;
    public static final int PRESSURE_UNIT = 1;
    public static final int PROMPT_ACC_ACTIVE = 3;
    public static final int PROMPT_ACC_CANCEL = 2;
    public static final int PROMPT_ACC_NOT_ACTIVE = 4;
    public static final int PROMPT_ACC_OPEN = 1;
    public static final int PROMPT_COMFIRM_DOOR_IS_CLOSED = 6;
    public static final int PROMPT_COMFIRM_GEAR_IS_D = 7;
    public static final int PROMPT_FASTEN_DRIVER_SEAT_BELT = 5;
    public static final int PROMPT_INVALID = 0;
    public static final int PROMPT_OPEN_EPB = 9;
    public static final int PROMPT_RELEASE_EPB = 8;
    public static final int PSI = 2;
    public static final int RADIO_PLAY = 1;
    public static final int RADIO_STOP = 2;
    public static final int REMOTE_DRIVING_REMINDER_OFF = 1;
    public static final int REMOTE_DRIVING_REMINDER_ON = 2;
    public static final int RESET_INVALID = 0;
    public static final int RESET_ITEM_MAINTAIN = 0;
    public static final int RESET_ITEM_UNIT = 1;
    public static final int RESET_VALID = 1;
    public static final int REST_HOURE_MAX = 254;
    public static final int REST_HOUR_MIN = 0;
    public static final long REST_MILEAGE_MAX = 4294967294L;
    public static final int REST_MILEAGE_MIN = 0;
    public static final int REST_MINUTE_MAX = 59;
    public static final int REST_MINUTE_MIN = 0;
    public static final int REST_SECOND_MAX = 59;
    public static final int REST_SECOND_MIN = 0;
    public static final int RIGHT_DEVIATION = 2;
    public static final int RIGHT_LINE = 2;
    public static final int RSET_HOUR_MAX = 254;
    public static final int RSET_HOUR_MIN = 0;
    public static final int RSET_MINUTE_MAX = 59;
    public static final int RSET_MINUTE_MIN = 0;
    public static final int SAFETY_BELT_AREA_DEPUTY = 2;
    public static final int SAFETY_BELT_AREA_MAIN = 1;
    public static final int SAFETY_BELT_AREA_SECOND_ROW_SEAT_LEFT = 3;
    public static final int SAFETY_BELT_AREA_SECOND_ROW_SEAT_MID = 5;
    public static final int SAFETY_BELT_AREA_SECOND_ROW_SEAT_RIGHT = 4;
    public static final int SAFETY_BELT_STATE_LOCK = 1;
    public static final int SAFETY_BELT_STATE_UNLOCK = 0;
    public static final int SAFETY_DISTANCE_MAX = 4094;
    public static final int SAFETY_DISTANCE_MIN = 0;
    public static final int SAFETY_REMAINING_MILEAGE_MAX = 16777214;
    public static final int SAFETY_REMAINING_MILEAGE_MIN = 0;
    public static final int SAFETY_TYPE_ACCIDENT = 10;
    public static final int SAFETY_TYPE_BLIND_BEND_CONTINUE = 25;
    public static final int SAFETY_TYPE_BLIND_BEND_LEFT = 22;
    public static final int SAFETY_TYPE_BLIND_BEND_REVERSE = 24;
    public static final int SAFETY_TYPE_BLIND_BEND_RIGHT = 23;
    public static final int SAFETY_TYPE_BLIND_SLOPE_CONDOWN = 21;
    public static final int SAFETY_TYPE_BLIND_SLOPE_DOWN = 20;
    public static final int SAFETY_TYPE_BLIND_SLOPE_UP = 19;
    public static final int SAFETY_TYPE_CROSS_WIND = 7;
    public static final int SAFETY_TYPE_HILLSIDE_DANGEROUS = 4;
    public static final int SAFETY_TYPE_HONK = 30;
    public static final int SAFETY_TYPE_HUMP_BRIDGE = 9;
    public static final int SAFETY_TYPE_JOINT_LEFT = 12;
    public static final int SAFETY_TYPE_JOINT_RIGHT = 13;
    public static final int SAFETY_TYPE_LOW_SPEED = 1;
    public static final int SAFETY_TYPE_NARROW_BOTH = 16;
    public static final int SAFETY_TYPE_NARROW_BRIDGE = 3;
    public static final int SAFETY_TYPE_NARROW_LEFT = 14;
    public static final int SAFETY_TYPE_NARROW_RIGHT = 15;
    public static final int SAFETY_TYPE_NONE = 0;
    public static final int SAFETY_TYPE_OVERTAKE_FORBIDDEN = 29;
    public static final int SAFETY_TYPE_RAILWAY_MANAGED = 26;
    public static final int SAFETY_TYPE_RAILWAY_UNMANAGED = 27;
    public static final int SAFETY_TYPE_ROCK_DOWN_LEFT = 17;
    public static final int SAFETY_TYPE_ROCK_DOWN_RIGHT = 18;
    public static final int SAFETY_TYPE_SCHOOL_ZONE = 8;
    public static final int SAFETY_TYPE_SLIP = 6;
    public static final int SAFETY_TYPE_TUNNEL = 31;
    public static final int SAFETY_TYPE_UNDER_WATER = 2;
    public static final int SAFETY_TYPE_UNEVEN = 5;
    public static final int SAFETY_TYPE_VILLAGE = 28;
    public static final int SAFETY_TYPE_ZIP_PASS = 11;
    public static final int SCHEDULE_CHARGE = 2;
    public static final int SCHEDULE_CHARGE_NOT_ALLOW = 3;
    public static final int SEAT_BELT_CONTINUE_ALARM = 5;
    public static final int SEAT_BELT_RE_ALARM = 6;
    public static final int SECOND_GEAR = 2;
    public static final int SECOND_GEAR_SPACING = 4;
    public static final int SECOND_MENU_MAX = 15;
    public static final int SECOND_MENU_MIN = 0;
    public static final int SEND_INFO_FAIL = 2;
    public static final int SEND_INFO_SUCCESS = 1;
    public static final int SET_50_MILES_AVE_ENERGY = 1;
    public static final int SET_AVE_SPEED = 9;
    public static final int SET_COUNT_ENERGY = 2;
    public static final int SET_DRIVE_TIME = 4;
    public static final int SET_ENDURANCE_MILEAGE = 6;
    public static final int SET_ENERGY_STREAM = 11;
    public static final int SET_INVLIDE = 0;
    public static final int SET_OIL_COUNT = 7;
    public static final int SET_OUT_CHARGE = 10;
    public static final int SET_PERIOD_OIL = 5;
    public static final int SET_PRESSURE_TYRE = 3;
    public static final int SET_SINGLE_MILE = 8;
    public static final int SMART_TEMT_CTL = 9;
    public static final int SOUND_FREQ_0POINT5HZ = 1;
    public static final int SOUND_FREQ_1HZ = 2;
    public static final int SOUND_FREQ_2HZ = 3;
    public static final int SOUND_FREQ_2POINT5HZ = 4;
    public static final int SOUND_FREQ_4HZ = 5;
    public static final int SOUND_FREQ_ALWAYS = 6;
    public static final int SOUND_FREQ_INVALID = 0;
    public static final int SPEED_COUNT_SET_INVALID = 0;
    public static final int SPEED_COUNT_SET_OFF = 2;
    public static final int SPEED_COUNT_SET_ON = 1;
    public static final int SPEED_INVALID = 0;
    public static final int SPEED_UNIT_KM = 1;
    public static final int SPEED_UNIT_MPH = 2;
    public static final int STANDARD_FEEDBACK = 1;
    public static final int STATE_FAULT = 1;
    public static final int STATE_GET_MILEAGE_EV = 5;
    public static final int STATE_GET_MILEAGE_HEV = 4;
    public static final int STATE_GET_MILEAGE_INVALID = 0;
    public static final int STATE_GET_MILEAGE_ONE = 2;
    public static final int STATE_GET_MILEAGE_TOTAL = 1;
    public static final int STATE_GET_MILEAGE_TWO = 3;
    public static final int STATE_HEADLIGHT_AUTO = 2;
    public static final int STATE_HEADLIGHT_LOW_BEAM = 4;
    public static final int STATE_HEADLIGHT_OFF = 1;
    public static final int STATE_HEADLIGHT_SMALL_LIGHT = 3;
    public static final int STATE_INVALID = 0;
    public static final int STATE_INVISIBLE = 1;
    public static final int STATE_MILEAGE_EV = 3;
    public static final int STATE_MILEAGE_HEV = 2;
    public static final int STATE_MILEAGE_INVALID = 0;
    public static final int STATE_MILEAGE_ONE = 4;
    public static final int STATE_MILEAGE_TOTAL = 1;
    public static final int STATE_MILEAGE_TWO = 5;
    public static final int STATE_MUSIC_NEXT = 2;
    public static final int STATE_MUSIC_PAUSE = 2;
    public static final int STATE_MUSIC_PLAY = 1;
    public static final int STATE_MUSIC_PRE = 1;
    public static final int STATE_MUTE_CANCEL = 2;
    public static final int STATE_MUTE_OK = 1;
    public static final int STATE_NORMAL = 0;
    public static final int STATE_OFF = 2;
    public static final int STATE_ON = 1;
    public static final int STATE_RESOURCE_SHOW = 1;
    public static final int STATE_SCREEN_1 = 1;
    public static final int STATE_SCREEN_2 = 2;
    public static final int STATE_VISIBLE = 2;
    public static final int STATE_VOL_INVALID = 63;
    public static final String TAG = "BYDAutoInstrumentDevice";
    public static final int TARGET_COMPANY = 2;
    public static final int TARGET_HOME = 1;
    public static final int TARGET_OBJECT_DETECTED = 1;
    public static final int TBT_ICON000_INVALID = 0;
    public static final int TBT_ICON001_TURN_LEFT = 1;
    public static final int TBT_ICON002_TURN_RIGHT = 2;
    public static final int TBT_ICON068_PATH_POINT5 = 68;
    public static final int TEMPERATURE_INVALID = 195;
    public static final int TEMPERATURE_MAX = 195;
    public static final int TEMPERATURE_MIN = -60;
    public static final int TEMPERATURE_UNIT = 1;
    public static final int TEMPERAURE_UNIT = 0;
    public static final int TEMP_MAX = 499;
    public static final int TEMP_MIN = -40;
    public static final int TEXT_COLOR_RED = 3;
    public static final int TEXT_COLOR_WHITE = 1;
    public static final int TEXT_COLOR_YELLOW = 2;
    public static final int THIRD_GEAR = 3;
    public static final int THIRD_GEAR_SPACING = 5;
    public static final int THIS_JOURNEY = 7;
    public static final int THREE_ALARM = 4;
    public static final int TIME_CALCULATING = 1;
    public static final int TIME_DISTANCE_FIFTH_GEAR = 5;
    public static final int TIME_DISTANCE_FIRST_GEAR = 1;
    public static final int TIME_DISTANCE_FORTH_GEAR = 4;
    public static final int TIME_DISTANCE_INVALID = 0;
    public static final int TIME_DISTANCE_SECOND_GEAR = 2;
    public static final int TIME_DISTANCE_THIRD_GEAR = 3;
    public static final int TIME_FIRST_GEAR = 1;
    public static final int TIME_FORTH_GEAR = 4;
    public static final int TIME_NOT_DISPLAY = 3;
    public static final int TIME_REST_TIME = 2;
    public static final int TIME_SECOND_GEAR = 2;
    public static final int TIME_THIRD_GEAR = 3;
    public static final int TOTAL_MILEAGE_INVALID = 1;
    public static final int TOTAL_MILEAGE_VALID = 0;
    public static final double TRAVEL_TIME_PH_MAX = 9999.9d;
    public static final double TRAVEL_TIME_PH_MIN = 0.0d;
    public static final int TURN_KIND_BACK = 5;
    public static final int TURN_KIND_BACK_2BRANCH_LEFT_BASE = 64;
    public static final int TURN_KIND_BACK_2BRANCH_RIGHT_BASE = 65;
    public static final int TURN_KIND_BACK_3BRANCH_LEFT_BASE = 66;
    public static final int TURN_KIND_BACK_3BRANCH_MIDDLE_BASE = 67;
    public static final int TURN_KIND_BACK_3BRANCH_RIGHT_BASE = 68;
    public static final int TURN_KIND_BLANK = 0;
    public static final int TURN_KIND_BRANCH_CENTER = 22;
    public static final int TURN_KIND_BRANCH_CENTER_IC = 40;
    public static final int TURN_KIND_BRANCH_CENTER_IC_STRAIGHT = 43;
    public static final int TURN_KIND_BRANCH_CENTER_STRAIGHT = 37;
    public static final int TURN_KIND_BRANCH_LEFT = 20;
    public static final int TURN_KIND_BRANCH_LEFT_IC = 39;
    public static final int TURN_KIND_BRANCH_LEFT_IC_STRAIGHT = 42;
    public static final int TURN_KIND_BRANCH_LEFT_MAIN = 14;
    public static final int TURN_KIND_BRANCH_LEFT_STRAIGHT = 36;
    public static final int TURN_KIND_BRANCH_RIGHT = 21;
    public static final int TURN_KIND_BRANCH_RIGHT_IC = 41;
    public static final int TURN_KIND_BRANCH_RIGHT_IC_STRAIGHT = 44;
    public static final int TURN_KIND_BRANCH_RIGHT_MAIN = 16;
    public static final int TURN_KIND_BRANCH_RIGHT_STRAIGHT = 38;
    public static final int TURN_KIND_CAR_SIGN = 102;
    public static final int TURN_KIND_CENTER_MAIN = 17;
    public static final int TURN_KIND_DEST = 24;
    public static final int TURN_KIND_FRONT = 1;
    public static final int TURN_KIND_INFERRY = 29;
    public static final int TURN_KIND_LEFT = 7;
    public static final int TURN_KIND_LEFT_2BRANCH_LEFT = 50;
    public static final int TURN_KIND_LEFT_2BRANCH_RIGHT = 51;
    public static final int TURN_KIND_LEFT_3BRANCH_LEFT = 52;
    public static final int TURN_KIND_LEFT_3BRANCH_MIDDLE = 53;
    public static final int TURN_KIND_LEFT_3BRANCH_RIGHT = 54;
    public static final int TURN_KIND_LEFT_BACK = 6;
    public static final int TURN_KIND_LEFT_FRONT = 8;
    public static final int TURN_KIND_LEFT_FRONT_2BRANCH_LEFT = 60;
    public static final int TURN_KIND_LEFT_FRONT_2BRANCH_RIGHT = 61;
    public static final int TURN_KIND_LEFT_SIDE = 11;
    public static final int TURN_KIND_LEFT_SIDE_BACK = 81;
    public static final int TURN_KIND_LEFT_SIDE_IC = 18;
    public static final int TURN_KIND_LEFT_SIDE_MAIN = 13;
    public static final int TURN_KIND_LEFT_SIDE_STRAIGHT = 34;
    public static final int TURN_KIND_LEFT_SIDE_STRAIGHT_IC = 32;
    public static final int TURN_KIND_OUTFERRY = 30;
    public static final int TURN_KIND_RIGHT = 3;
    public static final int TURN_KIND_RIGHT_2BRANCH_LEFT = 55;
    public static final int TURN_KIND_RIGHT_2BRANCH_RIGHT = 56;
    public static final int TURN_KIND_RIGHT_3BRANCH_LEFT = 57;
    public static final int TURN_KIND_RIGHT_3BRANCH_MIDDLE = 58;
    public static final int TURN_KIND_RIGHT_3BRANCH_RIGHT = 59;
    public static final int TURN_KIND_RIGHT_BACK = 4;
    public static final int TURN_KIND_RIGHT_FRONT = 2;
    public static final int TURN_KIND_RIGHT_SIDE = 12;
    public static final int TURN_KIND_RIGHT_SIDE_IC = 19;
    public static final int TURN_KIND_RIGHT_SIDE_MAIN = 15;
    public static final int TURN_KIND_RIGHT_SIDE_STRAIGHT = 35;
    public static final int TURN_KIND_RIGHT_SIDE_STRAIGHT_IC = 33;
    public static final int TURN_KIND_RING = 9;
    public static final int TURN_KIND_RING_BACK = 73;
    public static final int TURN_KIND_RING_FRONT = 69;
    public static final int TURN_KIND_RING_LEFT = 75;
    public static final int TURN_KIND_RING_LEFTBACK = 74;
    public static final int TURN_KIND_RING_LEFTFRONT = 76;
    public static final int TURN_KIND_RING_LEFT_SIDE_ANTICCLOCKWISE_IN_10OUT = 101;
    public static final int TURN_KIND_RING_LEFT_SIDE_ANTICCLOCKWISE_IN_1OUT = 92;
    public static final int TURN_KIND_RING_LEFT_SIDE_ANTICCLOCKWISE_IN_2OUT = 93;
    public static final int TURN_KIND_RING_LEFT_SIDE_ANTICCLOCKWISE_IN_3OUT = 94;
    public static final int TURN_KIND_RING_LEFT_SIDE_ANTICCLOCKWISE_IN_4OUT = 95;
    public static final int TURN_KIND_RING_LEFT_SIDE_ANTICCLOCKWISE_IN_5OUT = 96;
    public static final int TURN_KIND_RING_LEFT_SIDE_ANTICCLOCKWISE_IN_6OUT = 97;
    public static final int TURN_KIND_RING_LEFT_SIDE_ANTICCLOCKWISE_IN_7OUT = 98;
    public static final int TURN_KIND_RING_LEFT_SIDE_ANTICCLOCKWISE_IN_8OUT = 99;
    public static final int TURN_KIND_RING_LEFT_SIDE_ANTICCLOCKWISE_IN_9OUT = 100;
    public static final int TURN_KIND_RING_LEFT_SIDE_CLOCKWISE_IN = 79;
    public static final int TURN_KIND_RING_LEFT_SIDE_CLOCKWISE_OUT = 80;
    public static final int TURN_KIND_RING_OUT = 10;
    public static final int TURN_KIND_RING_RIGHT = 71;
    public static final int TURN_KIND_RING_RIGHTBACK = 72;
    public static final int TURN_KIND_RING_RIGHTFRONT = 70;
    public static final int TURN_KIND_RING_RIGHT_SIDE_ANTICCLOCKWISE_IN_10OUT = 91;
    public static final int TURN_KIND_RING_RIGHT_SIDE_ANTICCLOCKWISE_IN_1OUT = 82;
    public static final int TURN_KIND_RING_RIGHT_SIDE_ANTICCLOCKWISE_IN_2OUT = 83;
    public static final int TURN_KIND_RING_RIGHT_SIDE_ANTICCLOCKWISE_IN_3OUT = 84;
    public static final int TURN_KIND_RING_RIGHT_SIDE_ANTICCLOCKWISE_IN_4OUT = 85;
    public static final int TURN_KIND_RING_RIGHT_SIDE_ANTICCLOCKWISE_IN_5OUT = 86;
    public static final int TURN_KIND_RING_RIGHT_SIDE_ANTICCLOCKWISE_IN_6OUT = 87;
    public static final int TURN_KIND_RING_RIGHT_SIDE_ANTICCLOCKWISE_IN_7OUT = 88;
    public static final int TURN_KIND_RING_RIGHT_SIDE_ANTICCLOCKWISE_IN_8OUT = 89;
    public static final int TURN_KIND_RING_RIGHT_SIDE_ANTICCLOCKWISE_IN_91OUT = 90;
    public static final int TURN_KIND_RIRHGT_FRONT_2BRANCH_LEFT = 62;
    public static final int TURN_KIND_RIRHGT_FRONT_2BRANCH_RIGHT = 63;
    public static final int TURN_KIND_START = 23;
    public static final int TURN_KIND_STRAIGHT = 77;
    public static final int TURN_KIND_STRAIGHT_2BRANCH_LEFT = 45;
    public static final int TURN_KIND_STRAIGHT_2BRANCH_RIGHT = 46;
    public static final int TURN_KIND_STRAIGHT_3BRANCH_LEFT = 47;
    public static final int TURN_KIND_STRAIGHT_3BRANCH_MIDDLE = 48;
    public static final int TURN_KIND_STRAIGHT_3BRANCH_RIGHT = 49;
    public static final int TURN_KIND_TOLLGATE = 31;
    public static final int TURN_KIND_VIA_1 = 25;
    public static final int TURN_KIND_VIA_2 = 26;
    public static final int TURN_KIND_VIA_3 = 27;
    public static final int TURN_KIND_VIA_4 = 28;
    public static final int TURN_KIN_REST_AREA = 78;
    public static final int TURN_OFF_THE_INTERFACE = 15;
    public static final int TWO_HZ_LIGHT = 6;
    public static final int TYRE_DIRECT = 1;
    public static final int TYRE_INDIRECT_WITH_CAR = 2;
    public static final int TYRE_INVALID = 0;
    public static final int TYRE_INVALID_WITHOUT_CAR = 3;
    public static final int UISTATE_FULL_MAP_TRANS = 1;
    public static final int UISTATE_FULL_MAP_TRANS_NOT = 2;
    public static final int UISTATE_MAP_CLOSE = 7;
    public static final int UISTATE_SIMPLE_MAP_TRANS = 5;
    public static final int UISTATE_SIMPLE_MAP_TRANS_NOT = 6;
    public static final int UISTATE_SMALL_MAP_TRANS = 3;
    public static final int UISTATE_SMALL_MAP_TRANS_NOT = 4;
    public static final int UNIT_LENGTH_FT = 5;
    public static final int UNIT_LENGTH_KM = 1;
    public static final int UNIT_LENGTH_METRE = 3;
    public static final int UNIT_LENGTH_MILE = 2;
    public static final int UNIT_LENGTH_YARD = 4;
    public static final int UNIT_NAVI_FT = 5;
    public static final int UNIT_NAVI_INVALID = 0;
    public static final int UNIT_NAVI_KM = 1;
    public static final int UNIT_NAVI_M = 3;
    public static final int UNIT_NAVI_MILES = 2;
    public static final int UNIT_NAVI_YARDS = 4;
    public static final int UNRECOGNIZED_LINE = 4;
    public static final int VEHADASSTAT_INVALID = 0;
    public static final int VEHNAVISTAT_VAILD = 1;
    public static final int VERSION_NUMBER_READING_INTERFACE = 9;
    public static final int VIEW_STATUS_BAR = 2;
    public static final int VIEW_STATUS_HUGE_WIN = 1;
    public static final int VIEW_STATUS_INVALID = 0;
    public static final int VIEW_SWITCH_INVALID = 0;
    public static final int VIEW_SWITCH_WIN_CLOSE = 1;
    public static final int VIEW_SWITCH_WIN_OPEN = 2;
    public static final double WATER_TEMP_METER_PERCENT_MAX = 100.0d;
    public static final double WATER_TEMP_METER_PERCENT_MIN = 0.0d;
    private static BYDAutoInstrumentDevice sDevice;
    private final InstrumentManagerImpl mInstrumentManagerImpl;
    private final InstrumentMediaManagerImpl mMediaManagerImpl;
    private final InstrumentSettingsManagerImpl mSettingsManagerImpl;

    public BYDAutoInstrumentDevice(Context context) {
        super(context.getApplicationContext());
        this.mInstrumentManagerImpl = InstrumentManagerImpl.getInstance(context.getApplicationContext());
        this.mMediaManagerImpl = InstrumentMediaManagerImpl.getInstance(context.getApplicationContext());
        this.mSettingsManagerImpl = InstrumentSettingsManagerImpl.getInstance(context.getApplicationContext());
    }

    public static synchronized BYDAutoInstrumentDevice getInstance(Context context) {
        if (sDevice == null) {
            sDevice = new BYDAutoInstrumentDevice(context);
        }
        return sDevice;
    }

    public int abandonMapFocus(int i) {
        return this.mMediaManagerImpl.abandonMapFocus(i);
    }

    public int clearDistanceMilesType(int i) {
        return this.mInstrumentManagerImpl.clearDistanceMilesType(i);
    }

    public int clearInfo(int i) {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.mInstrumentManagerImpl.get(iArr, cls);
    }

    public int get2in1AccDistance() {
        return 0;
    }

    public int get2in1AccTextPrompt() {
        return 0;
    }

    public int get2in1AccTimeDistance() {
        return 0;
    }

    public int get2in1AccWorkInterface() {
        return 0;
    }

    public int get2in1BodyPosition() {
        return 0;
    }

    public int get2in1FaultFrontFogLightIndicator() {
        return 0;
    }

    public int get2in1FaultFrontFogLightIndicatorColor() {
        return 0;
    }

    public int get2in1FaultGrassIndicator() {
        return 0;
    }

    public int get2in1FaultGrassIndicatorColor() {
        return 0;
    }

    public int get2in1FaultIndicatorColor(int i) {
        return 0;
    }

    public int get2in1FaultMuddyIndicator() {
        return 0;
    }

    public int get2in1FaultMuddyIndicatorColor() {
        return 0;
    }

    public int get2in1FaultNormalIndicator() {
        return 0;
    }

    public int get2in1FaultNormalIndicatorColor() {
        return 0;
    }

    public int get2in1FaultOilLifeDetectIndicator() {
        return 0;
    }

    public int get2in1FaultOilLifeDetectIndicatorColor() {
        return 0;
    }

    public int get2in1FaultSandIndicator() {
        return 0;
    }

    public int get2in1FaultSandIndicatorColor() {
        return 0;
    }

    public int get2in1FaultSmallLightIndicator() {
        return 0;
    }

    public int get2in1FaultSmallLightIndicatorColor() {
        return 0;
    }

    public int get2in1MenuState() {
        return 0;
    }

    public int get50KmEneryConsumptionDisplayState() {
        return 0;
    }

    public int get50KmFuelConsumptionDisplayState() {
        return 0;
    }

    public int getABSFaultWarningLight() {
        return 0;
    }

    public int getABSFaultWarningLightColor() {
        return 0;
    }

    public int getACCIndicateLightColor() {
        return 0;
    }

    public int getACCIndicateLightState() {
        return 0;
    }

    public int getAccCruisingSpeed() {
        return 0;
    }

    public int getAccCruisingSpeedColor() {
        return 0;
    }

    public int getAccCruisingSpeedValue() {
        return 0;
    }

    public int getAirHeatOilDisplay() {
        return 0;
    }

    public int getAirHeatingDisplayState() {
        return 0;
    }

    public int getAirHeatingOilWarn() {
        return 0;
    }

    public int getAlarmBuzzleState() {
        return this.mInstrumentManagerImpl.getAlarmBuzzleState();
    }

    public void getAllStatus() {
    }

    public int getAllWeatherLightsState() {
        return this.mInstrumentManagerImpl.getAllWeatherLightsState();
    }

    public int getAppCountdownHour() {
        return 0;
    }

    public int getAppCountdownMinute() {
        return 0;
    }

    public int getAppointmentHour() {
        return 0;
    }

    public int getAppointmentMinute() {
        return 0;
    }

    public int getAreaInfo() {
        return this.mInstrumentManagerImpl.getAreaInfoR();
    }

    public int getAverageEneryConsumptionDisplayState() {
        return 0;
    }

    public int getAverageFuelConsumptionDisplayState() {
        return 0;
    }

    public int getAverageSpeed() {
        return 0;
    }

    public int getBacklightBrightness() {
        return this.mInstrumentManagerImpl.getInstrumentBrightness();
    }

    public int getBacklightModeState(int i) {
        return 0;
    }

    public int getBatteryPercent() {
        return 0;
    }

    public int getBrakeSysFaultLightColor() {
        return 0;
    }

    public int getBrakeSysFaultLightState() {
        return 0;
    }

    public int getCallInfoResult() {
        return 0;
    }

    public int getChargeAppTimeOption() {
        return 0;
    }

    public int getChargeDisplay() {
        return 0;
    }

    public int getChargeNotice() {
        return 0;
    }

    public int getChargePercent() {
        return 0;
    }

    public double getChargePower() {
        return 0.0d;
    }

    public int[] getChargeRestTime() {
        return new int[0];
    }

    public int getCoolantTempHighWarnLightColor() {
        return 0;
    }

    public int getCoolantTempHighWarnLightState() {
        return 0;
    }

    public int getCruiseCtrlIndicatorColor() {
        return 0;
    }

    public int getCruiseCtrlIndicatorState() {
        return 0;
    }

    public int getCurrentDriveInterFace() {
        return 0;
    }

    public double getCurrentJourneyDriveMileage() {
        return 0.0d;
    }

    public double getCurrentJourneyDriveTime() {
        return 0.0d;
    }

    public int getDashboardAlarmState() {
        return 0;
    }

    public int getDayNightMode() {
        return this.mSettingsManagerImpl.getDayNightMode();
    }

    public int getDeviationState() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    public int getDirectTypePressDisplayState() {
        return 0;
    }

    public int getDirectionInfo() {
        return 0;
    }

    public double getDischargeElecEnergy() {
        return 0.0d;
    }

    public int getDischargeIndicatorColor() {
        return 0;
    }

    public int getDischargeMode() {
        return 0;
    }

    public int getDischargeUiState() {
        return 0;
    }

    public int getDishargeIndicatorState() {
        return 0;
    }

    public int getDistanceMileageUnit() {
        return this.mInstrumentManagerImpl.getDistanceMileageUnit();
    }

    public int getDoorState(int i) {
        return 0;
    }

    public int getDrivePowerLimitIndicatorColor() {
        return 0;
    }

    public int getDrivePowerLimitIndicatorState() {
        return 0;
    }

    public int getECOIndicatorColor() {
        return 0;
    }

    public int getECOIndicatorState() {
        return 0;
    }

    public int getELECParkingColor() {
        return 0;
    }

    public int getELECParkingState() {
        return 0;
    }

    public int getESPFailWarnLightColor() {
        return 0;
    }

    public int getESPFailWarnLightState() {
        return 0;
    }

    public int getEVIndicatorColor() {
        return 0;
    }

    public int getEVIndicatorState() {
        return 0;
    }

    public int getEnergyDisplay() {
        return 0;
    }

    public int getEnergyFeedback() {
        return 0;
    }

    public int getEneryDisplayState() {
        return 0;
    }

    public int getEngineFailWarnLightColor() {
        return 0;
    }

    public int getEngineFailWarnLightState() {
        return 0;
    }

    public int getExpectChargeDisplay() {
        return 0;
    }

    public int getExpectChargeState() {
        return 0;
    }

    public double getExternalChargePower() {
        return 0.0d;
    }

    public double getExternalChargingPower() {
        return this.mInstrumentManagerImpl.getExternalChargingPower();
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    public int getFirstMenu() {
        return 0;
    }

    public int getFocusMapType() {
        return this.mMediaManagerImpl.getFocusMapType();
    }

    public int getFuelConsumptionDisplayState() {
        return 0;
    }

    public int getFuelLowAlarm() {
        return 0;
    }

    public int getGPFIndicatorColorState() {
        return 0;
    }

    public int getGPFIndicatorState() {
        return 0;
    }

    @Deprecated
    public int getGapDetection() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_INSTRUMENT_GET;
    }

    public int getHEVIndicatorColor() {
        return 0;
    }

    public int getHEVIndicatorState() {
        return 0;
    }

    public int getHeadlampFailWarnLightColor() {
        return 0;
    }

    public int getHeadlampFailWarnLightState() {
        return 0;
    }

    public int getHeadlightControlMode() {
        return this.mInstrumentManagerImpl.getHeadlightControlMode();
    }

    public int getINSAdasCenterDisplay() {
        return this.mSettingsManagerImpl.getINSAdasCenterDisplay();
    }

    public int getINSCenterDisplay() {
        return this.mSettingsManagerImpl.getINSCenterDisplay();
    }

    public int getINSNavDisplayMode() {
        return this.mInstrumentManagerImpl.getInsNavDisplayMode();
    }

    public int getInsNaviDisplayMode() {
        return this.mMediaManagerImpl.getInsNaviDisplayMode();
    }

    public int getInstantFuelConsumptionDisplayState() {
        return 0;
    }

    public int getInstantFuelConsumptionUnit() {
        return 0;
    }

    public int getInstrumentAutoDimmState() {
        return this.mInstrumentManagerImpl.getInstrumentAutoDimmState();
    }

    public int getInstrumentBrightness() {
        return this.mInstrumentManagerImpl.getInstrumentBrightness();
    }

    public int getInstrumentConfigure() {
        return this.mInstrumentManagerImpl.getInstrumentConfigure();
    }

    public int getInstrumentScreenType() {
        return this.mInstrumentManagerImpl.getInstrumentScreenType();
    }

    public int getInstrumentTheme() {
        return this.mInstrumentManagerImpl.getInstrumentTheme();
    }

    public int getInstrumentView() {
        return 0;
    }

    public int getKeyDetectionReminder() {
        return 0;
    }

    public int getLaneLineColor() {
        return 0;
    }

    @Deprecated
    public int getLaneLineState() {
        return 0;
    }

    public double getLast50KmPowerConsume() {
        return 0.0d;
    }

    public int getLineValue(int i) {
        return 0;
    }

    public int getLinkErrKeyTime(int i) {
        return 0;
    }

    public int getLowFuelWarnLightColor() {
        return this.mInstrumentManagerImpl.getLowFuelWarnLightColor();
    }

    public int getLowFuelWarnLightState() {
        return 0;
    }

    public int getLowPowerBatteryWarnLightColor() {
        return this.mInstrumentManagerImpl.getLowPowerBatteryWarnLightColor();
    }

    public int getLowPowerBatteryWarnLightState() {
        return 0;
    }

    public int getMainAlarmIndicatorColor() {
        return 0;
    }

    public int getMainAlarmIndicatorState() {
        return 0;
    }

    public int getMaintenanceInfo(int i) {
        return this.mInstrumentManagerImpl.getMaintenanceInfo(i);
    }

    public int getMaintenanceInfoMileState() {
        return this.mInstrumentManagerImpl.getMaintenanceInfoMileState();
    }

    public int getMaintenanceInfoTimeState() {
        return this.mInstrumentManagerImpl.getMaintenanceInfoTimeState();
    }

    public int getMalfunctionInfo(int i) {
        return this.mInstrumentManagerImpl.getMalfunctionInfo(i);
    }

    public int getMalfunctionInfo2(int i) {
        return 0;
    }

    public int[] getMalfunctionInfos() {
        return this.mInstrumentManagerImpl.getMalfunctionInfos();
    }

    public ArrayList<Integer> getMalfunctionList() {
        return new ArrayList<>();
    }

    public int getMasterVolume(int i) {
        return this.mInstrumentManagerImpl.getMasterVolume(i);
    }

    public int getMileageUnit() {
        return this.mInstrumentManagerImpl.getMileageUnit();
    }

    public int getMileageValidFlag() {
        return 0;
    }

    public long getMilesByType(int i) {
        return this.mInstrumentManagerImpl.getMilesByType(i);
    }

    public int getMilesType() {
        return this.mInstrumentManagerImpl.getMilesType();
    }

    public int getModuleState(int i) {
        return 0;
    }

    public int getMusicChannelStatus(int i) {
        return this.mInstrumentManagerImpl.getMusicChannelStatus(i);
    }

    public int getMusicControlStatus(int i) {
        return this.mInstrumentManagerImpl.getMusicControlStatus(i);
    }

    public int getMusicInfoResult() {
        return 0;
    }

    public int getMusicProgress(int i) {
        return this.mInstrumentManagerImpl.getMusicProgress(i);
    }

    public int getMusicResourceConfig(int i) {
        return this.mInstrumentManagerImpl.getMusicResourceConfig(i);
    }

    public int getMuteState(int i) {
        return this.mInstrumentManagerImpl.getMuteState(i);
    }

    public int getNaviDestinationCommand() {
        return 0;
    }

    public int getOKIndicatorColor() {
        return 0;
    }

    public int getOKIndicatorState() {
        return 0;
    }

    public int getOdometerDisplay() {
        return 0;
    }

    public int getOilLevelAlarmIndicator() {
        return 0;
    }

    public int getOilLevelAlarmIndicatorColor() {
        return 0;
    }

    public int getOutCarTemperature() {
        return 0;
    }

    public int getPCWAlarmInstruction() {
        return 0;
    }

    public int getPowerBatFailWarnLightColor() {
        return 0;
    }

    public int getPowerBatFailWarnLightState() {
        return 0;
    }

    public int getPowerBatteryChargeConnectIndicatorColor() {
        return 0;
    }

    public int getPowerBatteryChargeConnectIndicatorState() {
        return 0;
    }

    public int getPowerBatteryHeatWarnLightColor() {
        return 0;
    }

    public int getPowerBatteryHeatWarnLightState() {
        return 0;
    }

    public int getPowerOffErrInfo() {
        return 0;
    }

    public int getPowerOnErrInfo() {
        return 0;
    }

    public int getPowerSysFailWarnLightColor() {
        return 0;
    }

    public int getPowerSysFailWarnLightState() {
        return 0;
    }

    public int getPowerUnit() {
        return 0;
    }

    public int getPressureSupplySysFailWarnLightColor() {
        return 0;
    }

    public int getPressureSupplySysFailWarnLightState() {
        return 0;
    }

    public int getPressureWarnLightColor() {
        return 0;
    }

    public int getPressureWarnLightState() {
        return 0;
    }

    public int getRadioInfoResult() {
        return 0;
    }

    public int getRearFogLightState() {
        return this.mInstrumentManagerImpl.getRearFogLightState();
    }

    public int getRemoteDrivingReminder() {
        return 0;
    }

    public int getRoadNameCheckState() {
        return 0;
    }

    public int getSRSFaultWarningLight() {
        return 0;
    }

    public int getSRSFaultWarningLightColor() {
        return 0;
    }

    public int getSafetyBeltStatus(int i) {
        return 0;
    }

    public int getSecondMenu() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_INSTRUMENT_SET;
    }

    public int getSmartKeySysWarnLightColor() {
        return 0;
    }

    public int getSmartKeySysWarnLightState() {
        return 0;
    }

    public int getSoundFreq() {
        return 0;
    }

    public int getSoundType() {
        return 0;
    }

    public int getSpacingState() {
        return 0;
    }

    public int getSpeedUnit() {
        return this.mInstrumentManagerImpl.getSpeedUnit();
    }

    public int getSpeedUpCountState() {
        return this.mInstrumentManagerImpl.getSpeedUpCountState();
    }

    public int getSpeedUpFormIns() {
        return this.mInstrumentManagerImpl.getSpeedUpFormIns();
    }

    public int getSportIndicatorColor() {
        return 0;
    }

    public int getSportIndicatorState() {
        return 0;
    }

    public int getSportModeState() {
        return this.mInstrumentManagerImpl.getSportModeState();
    }

    public int getSteeringSYSFailWarnLightColor() {
        return 0;
    }

    public int getSteeringSYSFailWarnLightState() {
        return 0;
    }

    public int getTextColor() {
        return 0;
    }

    public int getTextInfo() {
        return 0;
    }

    public int getTimeIntervalState() {
        return 0;
    }

    public int getTotalMileage() {
        return this.mInstrumentManagerImpl.getTotalMileage();
    }

    public double getTravelTime() {
        return 0.0d;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    public int getTyrePressureCarType() {
        return 0;
    }

    public int getTyrePressureSYSFailWarnLightColor() {
        return 0;
    }

    public int getTyrePressureSYSFailWarnLightState() {
        return 0;
    }

    public int getUnit(int i) {
        return this.mInstrumentManagerImpl.getUnit(i);
    }

    public int getViewStatus() {
        return 0;
    }

    public double getWaterTempMeterPercent() {
        return 0.0d;
    }

    public int getWheelColor(int i) {
        return 0;
    }

    public int getWheelPressure(int i) {
        return 0;
    }

    public int getWheelTemperature(int i) {
        return 0;
    }

    public int getWheelTemperatureColor(int i) {
        return 0;
    }

    public int hasFeature(String str) {
        return 0;
    }

    public int notifyMapState(int i) {
        return this.mInstrumentManagerImpl.setNotifyMapStateS(i);
    }

    public int notifyScreenStatus(int i) {
        return this.mInstrumentManagerImpl.setNotifyScreenStatusS(i);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int i, int i2, int i3, Object obj) {
        return false;
    }

    public void registerListener(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener) {
        this.mInstrumentManagerImpl.registerListener(absBYDAutoInstrumentListener);
        this.mMediaManagerImpl.registerListener(absBYDAutoInstrumentListener);
        this.mSettingsManagerImpl.registerListener(absBYDAutoInstrumentListener);
    }

    public void registerListener(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener, int[] iArr) {
    }

    public int requestMapFocus(int i) {
        return this.mMediaManagerImpl.requestMapFocus(i);
    }

    public int reset(int i, int i2) {
        return 0;
    }

    public int resetMileage(int i, int i2) {
        return 0;
    }

    public int sendAddressInfo(int i, String str) {
        return 0;
    }

    public int sendAutoNaviStatus(int i) {
        return 0;
    }

    public int sendCallInfo(String str, String str2, int i, int i2) {
        return this.mMediaManagerImpl.sendCallInfo(str, str2, i, i2);
    }

    public int sendCallInfo(byte[] bArr) {
        return 0;
    }

    public int sendCallState(int i) {
        return 0;
    }

    public int sendCallTime(int i, int i2, int i3) {
        return 0;
    }

    public int sendCameraGuidanceInfo(int i, int i2, int i3) {
        return 0;
    }

    public int sendDestinationSetStatus(int i) {
        return 0;
    }

    public int sendMusicInfo(byte[] bArr) {
        return 0;
    }

    public int sendMusicInfoUrl(String str, String str2, int i, int i2, String str3) {
        return this.mMediaManagerImpl.sendMusicInfoUrl(str, str2, i, i2, str3);
    }

    public int sendMusicName(String str) {
        return 0;
    }

    public int sendMusicPlaybackProgress(int i) {
        return 0;
    }

    public int sendMusicProgress(int i, int i2) {
        return this.mMediaManagerImpl.sendMusicProgress(i, i2);
    }

    public int sendMusicSource(int i) {
        return 0;
    }

    public int sendMusicState(int i) {
        return 0;
    }

    public int sendNaviPresentationState(int i, int i2) {
        return this.mMediaManagerImpl.sendNaviPresentationState(i, i2);
    }

    public int sendNaviState(int i, int i2) {
        this.mInstrumentManagerImpl.setNaviState(i2);
        return this.mMediaManagerImpl.sendNaviState(i, i2);
    }

    public int sendNaviStateOnlyCPAA(int i, int i2) {
        return this.mMediaManagerImpl.sendNaviState(i, i2);
    }

    public int sendNextPathName(String str) {
        return 0;
    }

    public int sendRadioInfo(byte[] bArr) {
        return 0;
    }

    public int sendRadioState(int i) {
        return 0;
    }

    public int sendRestRouteInfo(int i, int i2, long j) {
        return 0;
    }

    public int sendSafeGuidanceInfo(int i, int i2, int i3) {
        return 0;
    }

    public int sendSimpleGuidanceInfo(int i, int i2) {
        return 0;
    }

    public int sendTbtNaviInfo(int i, int i2, String str, float f, int i3, int i4, float f2, int i5, float f3, int i6, int i7, int i8, int i9, int i10) {
        return this.mMediaManagerImpl.sendTbtNaviInfo(i, i2, str, f, i3, i4, f2, i5, f3, i6, i7, i8, i9, i10);
    }

    public int sendTbtSimpleNaviInfo(int i, int i2, String str, String str2, String str3, int i3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13) {
        return this.mMediaManagerImpl.sendTbtSimpleNaviInfo(i, i2, str, str2, str3, i3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        return this.mInstrumentManagerImpl.set(iArr, bYDAutoEventValue);
    }

    public void setAllStatus() {
    }

    public int setAreaInfo(int i) {
        return this.mInstrumentManagerImpl.setAreaInfo(i);
    }

    public int setBacklightBrightness(int i) {
        return this.mInstrumentManagerImpl.setInstrumentBrightness(i);
    }

    public int setBacklightModeState(int i, int i2) {
        return 0;
    }

    public int setClearFault(int i) {
        return 0;
    }

    public int setDayNightMode(int i) {
        return this.mSettingsManagerImpl.setDayNightMode(i);
    }

    public int setDrivingInfoSwitch(int i) {
        return 0;
    }

    public int setEnergyDisplay(int i) {
        return 0;
    }

    public int setHeadlightControlMode(int i) {
        return this.mInstrumentManagerImpl.setHeadlightControlMode(i);
    }

    public int setINSAdasCenterDisplay(int i) {
        return this.mSettingsManagerImpl.setINSAdasCenterDisplay(i);
    }

    public int setINSCenterDisplay(int i) {
        return this.mSettingsManagerImpl.setINSCenterDisplay(i);
    }

    public int setInsNaviDisplayMode(int i) {
        return this.mMediaManagerImpl.setInsNaviDisplayMode(i);
    }

    public int setInstrumentAutoDimmState(int i) {
        return this.mInstrumentManagerImpl.setInstrumentAutoDimmState(i);
    }

    public int setInstrumentBrightness(int i) {
        return this.mInstrumentManagerImpl.setInstrumentBrightness(i);
    }

    public int setInstrumentDisplayLanguage(int i) {
        return this.mInstrumentManagerImpl.setInstrumentDisplayLanguage(i);
    }

    public int setInstrumentTheme(int i) {
        return this.mInstrumentManagerImpl.setInstrumentTheme(i);
    }

    public int setMaintenanceInfo(int i, int i2) {
        return this.mInstrumentManagerImpl.setMaintenanceInfo(i, i2);
    }

    public int setMaintenanceInfoMileState(int i) {
        return this.mInstrumentManagerImpl.setMaintenanceInfoMileState(i);
    }

    public int setMaintenanceInfoTimeState(int i) {
        return this.mInstrumentManagerImpl.setMaintenanceInfoTimeState(i);
    }

    public int setMasterVolume(int i) {
        return this.mInstrumentManagerImpl.setMasterVolume(i);
    }

    public int setMilesType(int i) {
        return this.mInstrumentManagerImpl.setMilesType(i);
    }

    public int setMusicInfo(int i, int i2, int i3, int i4) {
        return this.mInstrumentManagerImpl.setMusicInfo(i, i2, i3, i4);
    }

    public int setMusicName(String str) {
        return this.mInstrumentManagerImpl.setMusicName(str);
    }

    public int setMusicSingerName(String str) {
        return this.mInstrumentManagerImpl.setMusicSingerName(str);
    }

    public int setMuteState(int i) {
        return this.mInstrumentManagerImpl.setMuteState(i);
    }

    public int setPadPrsntTime(int i, int i2, int i3) {
        return this.mInstrumentManagerImpl.setPadPrsntTime(i, i2, i3);
    }

    public int setPadTotalTime(int i, int i2, int i3) {
        return this.mInstrumentManagerImpl.setPadTotalTime(i, i2, i3);
    }

    public int setRadioChannel(String str) {
        return this.mInstrumentManagerImpl.setRadioChannel(str);
    }

    public int setRearFogLightState(int i) {
        return this.mInstrumentManagerImpl.setRearFogLightState(i);
    }

    public int setScreenDisplayState(int i) {
        return this.mInstrumentManagerImpl.setScreenDisplayState(i);
    }

    public int setSpeedUpCountState(int i) {
        return this.mSettingsManagerImpl.setSpeedUpCountState(i);
    }

    public int setUnit(int i, int i2) {
        return this.mInstrumentManagerImpl.setUnit(i, i2);
    }

    public int setViewSwitch(int i) {
        return 0;
    }

    public void unregisterListener(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener) {
        this.mInstrumentManagerImpl.unregisterListener(absBYDAutoInstrumentListener);
        this.mMediaManagerImpl.unregisterListener(absBYDAutoInstrumentListener);
        this.mSettingsManagerImpl.unregisterListener(absBYDAutoInstrumentListener);
    }

    public int sendCallInfo(String str, String str2, int i, int i2, int i3) {
        return this.mMediaManagerImpl.sendCallInfo(str, str2, i, i2, i3);
    }

    public int sendNaviState(int i, int i2, boolean z, String str) {
        return this.mMediaManagerImpl.setNaviState(i, i2, z, str);
    }
}

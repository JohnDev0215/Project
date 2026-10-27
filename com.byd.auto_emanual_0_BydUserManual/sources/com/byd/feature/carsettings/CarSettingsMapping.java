package com.byd.feature.carsettings;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class CarSettingsMapping {
    private static final Map<String, String> SETTINGS_CONFIG_KEY_VALUETYPE_MAP = new HashMap() { // from class: com.byd.feature.carsettings.CarSettingsMapping.1
        {
            put("model_value", "int");
            put("power_type", "int");
            put(CarSettingsConfig.PANORAMA_TYPE, "int");
            put(CarSettingsConfig.REAR_MIRROR_FOR_REVERSE_TYPE, "int");
            put(CarSettingsConfig.AC_SINGLE_OR_DAUL_TEMP_AREA, "int");
            put("atmosphere_lamp", "int");
            put("predictive_collision_warning", "int");
            put(CarSettingsConfig.ELECTRIC_BACK_DOOR_HEIGHT, "int");
            put(CarSettingsConfig.EXTERNAL_REAR_MIRROR_AUTO_FOLD, "int");
            put(CarSettingsConfig.UNIT_POWER, "int");
            put(CarSettingsConfig.DOOR_STATE, "int");
            put(CarSettingsConfig.UNIT_AIR_PRESSURE, "int");
            put(CarSettingsConfig.UNIT_SYSTEM, "int");
            put("ac_type", "int");
            put("wireless_charging", "int");
            put(CarSettingsConfig.UNIT_TEMPERATURE, "int");
            put("indirect_tire_pressure_detection", "int");
            put(CarSettingsConfig.AC_WIND_MODE_NUMBER, "int");
            put(CarSettingsConfig.UNIT_DISTANCE_FUEL_CONSUMPTION, "int");
            put("esp_assist", "int");
            put(CarSettingsConfig.INSIDE_LAMP, "int");
            put(CarSettingsConfig.HEADLAMP_HEIGHT_SETTING, "int");
            put("safety_belt_warning", "int");
            put(CarSettingsConfig.RAMP_SLOW_DOWN, "int");
            put(CarSettingsConfig.DAYTIME_RUNNING_LAMP, "int");
            put("auto_parking", "int");
            put(CarSettingsConfig.CHARGING_LOCK, "int");
            put(CarSettingsConfig.DRIVER_DETECTION, "int");
            put(CarSettingsConfig.CHILDREN_LOCK, "int");
            put(CarSettingsConfig.WINDOW_LOCK, "int");
            put(CarSettingsConfig.ATMOSPHERE_LAMP_MUSIC_MODE, "int");
            put(CarSettingsConfig.ATMOSPHERE_LAMP_MULTI_COLOR, "int");
            put(CarSettingsConfig.AC_REAR_DEFROST, "int");
            put(CarSettingsConfig.CHARGE_SMART, "int");
            put(CarSettingsConfig.GAME_VEHICLE, "int");
            put("green_net_value", "int");
            put("remote_screenage", "int");
        }
    };
    public static final Map<String, String> SETTINGS_GLOBAL_KEY_VALUETYPE_MAP = new HashMap() { // from class: com.byd.feature.carsettings.CarSettingsMapping.2
        {
            put("auto_time", "boolean");
            put("remote_location", "int");
            put("remote_screenage", "int");
            put("time_screen_saver_mode", "int");
            put("sys_day_or_night", "boolean");
            put("sys_screen_mode", "int");
            put("lighting_backlight_night_brightness", "int");
            put("smart_voice_warn_mode", "int");
            put("voice_play_volume_adjustment", "int");
            put("fail_prompt_for_vehicle_start_or_withdrawal", "boolean");
            put("parking_brake_warning", "boolean");
            put("door_unclosed_warning", "boolean");
            put("seat_belt_warning", "boolean");
            put("left_with_p_warning", "boolean");
            put("key_low_power_warning", "boolean");
            put("no_key_warning", "boolean");
            put("night_light_warning", "boolean");
            put("low_fuel_warning", "boolean");
            put("storage_battery_low_warning", "boolean");
            put("remote_driving_suspended_use_warning", "boolean");
            put("novice_mode_warning", "boolean");
            put("remote_camera_traffic_switch", "int");
            put("remote_camera_traffic_statistics", "int");
            put("vehicle_version_upgrade_time", "int");
            put(CarSettingsGlobal.VEHICLE_VERSION_UPGRADE_YEAR, "int");
            put("vehicle_version_upgrade_available", "boolean");
            put("vtol_set_time_count", "int");
            put("lighting_backlight_brightness", "int");
            put("custom_key_value", "int");
            put("app_auto_rotation", "int");
            put("rotation_memory_settings", "int");
            put("pad_rotation_value", "int");
            put("byd_app_theme", "int");
            put(CarSettingsGlobal.SYS_LIGHT_MODE_LINK, "int");
            put(CarSettingsGlobal.NAVIGATION_TONE_LOWER_MEDIA_TONE, "int");
            put(CarSettingsGlobal.AUTO_PARKING_WARNING, "int");
            put(CarSettingsGlobal.AUTO_SCREEN_SAVER_SWITCH_STATUS, "int");
            put(CarSettingsGlobal.AUTO_SCREEN_SAVER_PAGE_ID, "int");
            put(CarSettingsGlobal.AUTO_SCREEN_SAVER_LOCK_TIME, "int");
            put(CarSettingsGlobal.ASSIST_DRIVE_VOICE_WARN_MODE, "int");
            put(CarSettingsGlobal.TIME_SHUTDOWN_SWITCH_LEFT, "int");
            put(CarSettingsGlobal.TIME_SHUTDOWN_SWITCH_RIGHT, "int");
            put(CarSettingsGlobal.DESKTOP_WEATHER_STATUS, "int");
            put(CarSettingsGlobal.SUPPORT_CHANGE_SYSTEM_UIMODE, "int");
            put("donot_leave_phone_warning", "boolean");
            put(CarSettingsGlobal.CABIN_SENSING_WARNING, "boolean");
            put("upgrad_ota_status", "int");
            put(CarSettingsGlobal.LIGHTING_BACKLIGHT_NIGHT_BRIGHTNESS_FSE, "int");
            put(CarSettingsGlobal.PALM_KEY_VOICE_REMINDER_WARNING, "boolean");
            put(CarSettingsGlobal.LIGHTING_BACKLIGHT_BRIGHTNESS_FSE, "int");
            put(CarSettingsGlobal.AUTO_SPLIT_SCREEN, "boolean");
            put(CarSettingsGlobal.AUTO_SCREEN_SAVER_LOCK_TIME_2, "int");
            put("auto_lock_time", "int");
            put(CarSettingsGlobal.TIME_FLOAT_WINDOW_SWITCH, "int");
            put(CarSettingsGlobal.AUTO_SCREEN_SAVER_PAGE_ID_FSE, "int");
            put(CarSettingsGlobal.RIGHT_CUSTOM_KEY_VALUE, "int");
            put(CarSettingsGlobal.LEFT_CUSTOM_KEY_PACKAGE_SELECT, "String");
            put(CarSettingsGlobal.RIGHT_CUSTOM_RIGHT_KEY_PACKAGE_SELECT, "String");
            put(CarSettingsGlobal.AUTO_BRIGHTNESS, "boolean");
            put(CarSettingsGlobal.PERSONALIZED_RECOMMENDATIONS, "int");
        }
    };
    private static final Map<String, String> STATUS_TRAVELINFO_KEY_VALUETYPE_MAP = new HashMap() { // from class: com.byd.feature.carsettings.CarSettingsMapping.3
        {
            put(CarStatusSettingTravelInfo.COLUMN_CAR_TRAVEL_INFO_FUEL, "String");
            put(CarStatusSettingTravelInfo.COLUMN_CAR_TRAVEL_INFO_ELEC, "String");
            put(CarStatusSettingTravelInfo.COLUMN_CAR_STATUS_INFO, "String");
            put(CarStatusSettingTravelInfo.COLUMN_CAR_STATUS_ISSUE_NUM, "int");
            put(CarStatusSettingTravelInfo.COLUMN_MAINTENANCE_TIME, "int");
            put(CarStatusSettingTravelInfo.COLUMN_MAINTENANCE_MILE, "int");
            put(CarStatusSettingTravelInfo.SET_COLUMN_MAINTENANCE_TIME, "int");
            put(CarStatusSettingTravelInfo.SET_COLUMN_MAINTENANCE_MILE, "int");
            put(CarStatusSettingTravelInfo.MAINTENANCE_TIME_SWITCH_STATE, "int");
            put(CarStatusSettingTravelInfo.MAINTENANCE_MILE_SWITCH_STATE, "int");
            put(CarStatusSettingTravelInfo.TYRE_PRESSURE_GUIDANCE_NO_PROMPT, "int");
        }
    };
    private static final Map<String, String> STATUS_DICARE_KEY_VALUE_TYPE_MAP = new HashMap() { // from class: com.byd.feature.carsettings.CarSettingsMapping.4
        {
            put(CarStatusSettingDiCare.INSERT_DICARE_RECORT, "Serializable");
            put(CarStatusSettingDiCare.GET_DICARE_RECORD, "Serializable");
            put(CarStatusSettingDiCare.DELETE_DICARE_RECORD, "int");
            put(CarStatusSettingDiCare.CLEAR_DICARE_RECORD, "boolean");
        }
    };
    private static final Map<String, Map<String, String>> CAR_SETTINGS_TYPE_FEATURE_MAP = new HashMap() { // from class: com.byd.feature.carsettings.CarSettingsMapping.5
        {
            put(CarSettingsConfig.KEY, CarSettingsMapping.SETTINGS_CONFIG_KEY_VALUETYPE_MAP);
            put(CarSettingsGlobal.KEY, CarSettingsMapping.SETTINGS_GLOBAL_KEY_VALUETYPE_MAP);
            put(CarStatusSettingDiCare.KEY, CarSettingsMapping.STATUS_DICARE_KEY_VALUE_TYPE_MAP);
            put(CarStatusSettingTravelInfo.KEY, CarSettingsMapping.STATUS_TRAVELINFO_KEY_VALUETYPE_MAP);
        }
    };

    public static final Map<String, Map<String, String>> getKeyValueMap() {
        return CAR_SETTINGS_TYPE_FEATURE_MAP;
    }
}

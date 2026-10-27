package android.hardware.bydauto.properties;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.Settings;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class CarSettings {
    public static final String AUTHORITY = "carsettings";
    public static final String DATABASE_NAME = "CarSettings.db";
    public static final int DATABASE_VERSION = 100;
    public static final boolean DEBUG = false;
    public static final int DELETE = 2;
    public static final int INSERT = 1;
    public static final String PERMISSION_READ_CARSETTINGS_PROVIDER = "com.byd.permission.carsettings.READ_CARSETTINGS_PROVIDER";
    public static final String PERMISSION_WRITE_CARSETTINGS_PROVIDER = "com.byd.permission.carsettings.WRITE_CARSETTINGS_PROVIDER";
    public static final int QUERY = 4;
    public static final String REMOTE_CAMERA_TRAFFIC_STATISTICS = "remote_camera_traffic_statistics";
    public static final String REMOTE_CAMERA_TRAFFIC_SWITCH = "remote_camera_traffic_switch";
    public static final String REMOTE_SCREENAGE = "remote_screenage";
    public static final String TAG = "CarSettings";
    public static final int UPDATE = 3;
    public static final String USERS_TABLE_NAME = "carsettings";

    public static final class Global implements BaseColumns {
        public static final String AC_ANION_VISIBLE = "ac_anion_visible";
        public static final String AC_REAR_DEFROST_VISIBLE = "ac_rear_defrost_visible";
        public static final String AMBIENT_LIGHT_PANEL_VISIBLE = "ambient_light_panel_visible";
        public static final String AUTO_WIPE = "auto_wipe";
        public static final String BLUETOOTH_KEY_VISIBLE = "bluetooth_key_visible";
        public static final int CARSETTINGS_COMMAND_BUSY = -2147482647;
        public static final int CARSETTINGS_COMMAND_FAILED = -2147482648;
        public static final int CARSETTINGS_COMMAND_INVALID = -2147482645;
        public static final int CARSETTINGS_COMMAND_SUCCESS = 0;
        public static final int CARSETTINGS_COMMAND_TIMEOUT = -2147482646;
        public static final Uri CONTENT_URI = Uri.parse("content://carsettings/global");
        public static final String ELEC_CONSUMPTION_ONLINE = "elec_consumption_online";
        public static final String FUEL_CONSUMPTION_ONLINE = "fuel_consumption_online";
        public static final String FUEL_MILEAGE_ONLINE = "fuel_mileage_online";
        public static final String GAME_VEHICLE_VISIBLE = "game_vehicle_visible";
        public static final String INSIGHT_LIGHT_ONLINE = "insight_light_online";
        public static final String IS_WHOLE_WALLPAPER = "is_whole_wallpaper";
        public static final String ONLINE_THEME_PATH = "online_theme_path";
        public static final String REMOTE_CAMERA_TRAFFIC_STATISTICS = "remote_camera_traffic_statistics";
        public static final String REMOTE_CAMERA_TRAFFIC_SWITCH = "remote_camera_traffic_switch";
        public static final String REMOTE_LOCATION = "remote_location";
        public static final String REMOTE_SCREENAGE = "remote_screenage";
        public static final String SEAT_MASSAGE_VISIBLE_LEFT = "seat_massage_visible_left";
        public static final String SEAT_MASSAGE_VISIBLE_RIGHT = "seat_massage_visible_right";
        public static final String STEERING_WHEEL_HEATING_VISIBLE = "steering_wheel_heating_visible";
        public static final String SUPPORT_DARK_TEXT_EL = "support_dark_text_el";
        public static final String SUPPORT_DARK_TEXT_EP = "support_dark_text_ep";
        public static final String SUPPORT_DARK_TEXT_SL = "support_dark_text_sl";
        public static final String SUPPORT_DARK_TEXT_SP = "support_dark_text_sp";
        public static final String SYS_DAY_OR_NIGHT = "sys_day_or_night";
        public static final String SYS_SCREEN_MODE = "sys_screen_mode";
        public static final String TABLE_NAME_GLOBAL = "global";
        public static final String TAG = "CarSettingsGlobal";
        public static final String VOICE_CONTROL_WINDOW_VISIBLE = "voice_control_window_visible";
        public static final String WECHAT_REGISTER_PROMPT = "wechat_register_prompt";
        public static final String WECHAT_REGISTER_PROMPT_INFO = "wechat_register_prompt_info";
        public static final int WECHAT_REGISTER_PROMPT_INIT = 0;
        public static final int WECHAT_REGISTER_PROMPT_NO_LONGER = 2;
        public static final int WECHAT_REGISTER_PROMPT_USED = 1;

        public static boolean getBoolean(ContentResolver contentResolver, String str) {
            Log.i(TAG, "getBoolean: name is " + str);
            Cursor cursorQuery = contentResolver.query(CONTENT_URI, null, "key='" + str + "'", null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.getCount() > 0 && !cursorQuery.isLast()) {
                        cursorQuery.moveToNext();
                        return cursorQuery.getInt(cursorQuery.getColumnIndex("value")) == 1;
                    }
                } finally {
                    cursorQuery.close();
                }
            }
            if (cursorQuery != null) {
            }
            Log.i(TAG, "getBoolean name:" + str + " not found.");
            return false;
        }

        public static int getInt(ContentResolver contentResolver, String str) {
            Log.i(TAG, "getInt name:" + str);
            if ("remote_camera_traffic_switch".equals(str)) {
                int intForUser = Settings.System.getIntForUser(contentResolver, "remote_camera_traffic_switch_origins", 1, -2);
                Log.i(TAG, "getInt: name is " + str + ", value is " + intForUser);
                return intForUser;
            }
            if ("remote_camera_traffic_statistics".equals(str)) {
                int intForUser2 = Settings.System.getIntForUser(contentResolver, "remote_camera_traffic_statistics_origins", 0, -2);
                Log.i(TAG, "getInt: name is " + str + ", value is " + intForUser2);
                return intForUser2;
            }
            if ("remote_screenage".equals(str)) {
                int intForUser3 = Settings.System.getIntForUser(contentResolver, "remote_screenage_origins", 1, -2);
                Log.i(TAG, "getInt: name is " + str + ", value is " + intForUser3);
                return intForUser3;
            }
            Log.i(TAG, "getInt:name is:" + str);
            int i2 = -2147482647;
            Cursor cursorQuery = null;
            try {
                cursorQuery = contentResolver.query(CONTENT_URI, null, "key='" + str + "'", null, null);
                if (cursorQuery != null && cursorQuery.getCount() > 0) {
                    while (!cursorQuery.isLast()) {
                        cursorQuery.moveToNext();
                        i2 = cursorQuery.getInt(cursorQuery.getColumnIndex("value"));
                    }
                }
                return i2;
            } finally {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            }
        }

        public static String getString(ContentResolver contentResolver, String str) {
            Log.i(TAG, "getString: name is " + str);
            Cursor cursorQuery = contentResolver.query(CONTENT_URI, new String[]{"value"}, "key LIKE ?", new String[]{str}, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.getCount() > 0) {
                        cursorQuery.moveToFirst();
                        String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("value"));
                        cursorQuery.close();
                        return string;
                    }
                } finally {
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                }
            }
            Log.e(TAG, "cursor is null!");
        }

        public static boolean putBoolean(ContentResolver contentResolver, String str, boolean z) {
            Log.i(TAG, "putBoolean: name is " + str + ", value is " + z);
            try {
                Uri uri = CONTENT_URI;
                Cursor cursorQuery = contentResolver.query(uri, null, "key='" + str + "'", null, null);
                if (cursorQuery != null && cursorQuery.getCount() > 0) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("value", Boolean.valueOf(z));
                    if (contentResolver.update(uri, contentValues, "key=?", new String[]{str}) >= 0) {
                        contentResolver.notifyChange(uri, null, str, 3);
                    }
                    return true;
                }
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("key", str);
                contentValues2.put("value", Boolean.valueOf(z));
                if (contentResolver.insert(uri, contentValues2) != null) {
                    contentResolver.notifyChange(uri, null, str, 1);
                }
                Log.i(TAG, "putBoolean: insert into global name is " + str + ", value is " + z);
                return true;
            } catch (Exception e2) {
                Log.i(TAG, "putBoolean: e = " + e2);
                return false;
            }
        }

        public static boolean putInt(ContentResolver contentResolver, String str, int i2) {
            try {
                Log.i(TAG, "putInt name:" + str + " value :" + i2);
                if ("remote_camera_traffic_switch".equals(str)) {
                    Log.i(TAG, "putInt:name is REMOTE_CAMERA_TRAFFIC_SWITCH");
                    Settings.System.putIntForUser(contentResolver, "remote_camera_traffic_switch_origins", i2, -2);
                    return true;
                }
                if ("remote_camera_traffic_statistics".equals(str)) {
                    Log.i(TAG, "putInt:name is REMOTE_CAMERA_TRAFFIC_STATISTICS");
                    Settings.System.putIntForUser(contentResolver, "remote_camera_traffic_statistics_origins", i2, -2);
                    return true;
                }
                if ("remote_screenage".equals(str)) {
                    Log.i(TAG, "putInt:name is REMOTE_SCREENAGE");
                    Settings.System.putIntForUser(contentResolver, "remote_screenage_origins", i2, -2);
                    return true;
                }
                Log.i(TAG, "putInt:name is:" + str);
                ContentValues contentValues = new ContentValues();
                contentValues.put("value", Integer.valueOf(i2));
                Uri uri = CONTENT_URI;
                if (contentResolver.update(uri, contentValues, "key=?", new String[]{str}) >= 0) {
                    contentResolver.notifyChange(uri, null, str, 3);
                }
                return true;
            } catch (Exception e2) {
                Log.i(TAG, "putInt: e = " + e2);
                return false;
            }
        }

        public static boolean putString(ContentResolver contentResolver, String str, String str2) {
            Log.i(TAG, "putString: name is " + str + ", value is " + str2);
            try {
                Uri uri = CONTENT_URI;
                Cursor cursorQuery = contentResolver.query(uri, null, "key='" + str + "'", null, null);
                if (cursorQuery != null && cursorQuery.getCount() > 0) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("value", str2);
                    if (contentResolver.update(uri, contentValues, "key=?", new String[]{str}) >= 0) {
                        contentResolver.notifyChange(uri, null, str, 3);
                    }
                    return true;
                }
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("key", str);
                contentValues2.put("value", str2);
                if (contentResolver.insert(uri, contentValues2) != null) {
                    contentResolver.notifyChange(uri, null, str, 1);
                }
                Log.i(TAG, "putString: insert into global name is " + str + ", value is " + str2);
                return true;
            } catch (Exception e2) {
                Log.i(TAG, "putString: e = " + e2);
                return false;
            }
        }
    }

    public static final class UserTableData implements BaseColumns {
        public static final String AC_AUTO_LOCK_REAR_PANEL_VISIBLE = "ac_auto_lock_rear_panel_visible";
        public static final String AC_HEAT_MODE = "ac_heat_mode";
        public static final String AC_HEAT_MODE_VISIBLE = "ac_heat_mode_visible";
        public static final String AC_REMOTE_CTRL_TIME = "ac_remote_ctrl_time";
        public static final String AC_REMOTE_CTRL_TIME_VISIBLE = "ac_remote_ctrl_time_visible";
        public static final String AC_TYPE = "ac_type";
        public static final String ADAPTIVE_DRIVING_BEAM = "adaptive_driving_beam";
        public static final String ADAPTIVE_DRIVING_BEAM_VISIBLE = "adaptive_driving_beam_visible";
        public static final String AEB = "aeb";
        public static final String AEB_VISIBLE = "aeb_visible";
        public static final String ALARM_MODE = "alarm_mode";
        public static final String ALARM_MODE_VISIBLE = "alarm_mode_visible";
        public static final String AMBIENT_LAMP_MULTI_COLOR_VISIBLE = "ambient_lamp_multi_color_visible";
        public static final String APP_AUTO_ROTATION = "app_auto_rotation";
        public static final String ARKAMYS_SOUNDSTAGE_VISIBLE = "arkamys_soundstage_visible";
        public static final String ATMOSPHERE_LAMP = "atmosphere_lamp";
        public static final String AUTO_AC_MODE = "auto_ac_mode";
        public static final String AUTO_AC_MODE_VISIBLE = "auto_ac_mode_visible";
        public static final String AUTO_CIRCULATE_WITH_PARKING = "auto_circulate_with_parking";
        public static final String AUTO_CIRCULATE_WITH_PARKING_VISIBLE = "auto_circulate_with_parking_visible";
        public static final String AUTO_CIRCULATION_IN_TUNNEL = "auto_circulation_in_tunnel";
        public static final String AUTO_CIRCULATION_IN_TUNNEL_VISIBLE = "auto_circulation_in_tunnel_visible";
        public static final String AUTO_CLEAR_HAZE = "auto_clear_haze";
        public static final String AUTO_LOCK_TIME = "auto_lock_time";
        public static final String AUTO_LOCK_TIME_VISIBLE = "auto_lock_time_visible";
        public static final String AUTO_MIRROR_FOR_LOCK = "auto_mirror_for_lock";
        public static final String AUTO_MIRROR_FOR_LOCK_VISIBLE = "auto_mirror_for_lock_visible";
        public static final String AUTO_MIRROR_FOR_REVERSE = "auto_mirror_for_reverse";
        public static final String AUTO_MIRROR_FOR_REVERSE_VISIBLE = "auto_mirror_for_reverse_visible";
        public static final String AUTO_PARKING = "auto_parking";
        public static final String AUTO_PARKING_VISIBLE = "auto_parking_visible";
        public static final String AUTO_SEAT_MOVING_MODE = "auto_seat_moving_mode";
        public static final String AUTO_SEAT_MOVING_MODE_VISIBLE = "auto_seat_moving_mode_visible";
        public static final String AUTO_STEERING_RETURN = "auto_steering_return";
        public static final String AUTO_STEERING_RETURN_VISIBLE = "auto_steering_return_visible";
        public static final String AUTO_TIME = "auto_time";
        public static final String AUTO_WIPER_VISIBLE = "auto_wiper_visible";
        public static final String AVH_ASSIST = "avh_assist";
        public static final String AVH_ASSIST_VISIBLE = "avh_assist_visible";
        public static final String BACKDOOR_HEIGHT = "backdoor_height";
        public static final String BACKDOOR_HEIGHT_VISIBLE = "backdoor_height_visible";
        public static final String BLIND_DETECT = "blind_detect";
        public static final String BLIND_DETECT_VISIBLE = "blind_detect_visible";
        public static final String BRAKE_FOOT_SENSE_SETTING = "brake_foot_sense_setting";
        public static final String BRAKE_FOOT_SENSE_SETTING_VISIBLE = "brake_foot_sense_setting_visible";
        public static final String BYD_APP_THEME = "byd_app_theme";
        public static final String CAMERA_GALLERY_VISIBLE = "camera_gallery_visible";
        public static final String CARE_MODE = "def_care_mode";
        public static final String CARE_MODE_VISIBLE = "def_care_mode_visible";
        public static final int CARSETTINGS_COMMAND_BUSY = -2147482647;
        public static final int CARSETTINGS_COMMAND_FAILED = -1;
        public static final int CARSETTINGS_COMMAND_INVALID = -2147482645;
        public static final int CARSETTINGS_COMMAND_SUCCESS = 0;
        public static final int CARSETTINGS_COMMAND_TIMEOUT = -2147482646;
        public static final String CARSTATUS_AFTER_WIPER_AUTOMATIC_INSPECTION = "carstatus_after_wiper_inspection";
        public static final String CARSTATUS_DIAGNOSIS = "carstatus_diagnosis";
        public static final String CARSTATUS_FOUR_WHEEL_DRIVE = "carstatus_four_wheel_drive";
        public static final String CARSTATUS_FRONT_WIPER_AUTOMATIC_INSPECTION = "carstatus_front_wiper_inspection";
        public static final String CARSTATUS_OIL_DETECTION = "oil_detection";
        public static final String CAR_COLOR = "car_color";
        public static final String CAR_DISCHARGE_VISIBLE = "car_discharge_visible";
        public static final String CAR_ROOF_MODE = "car_roof_mode";
        public static final String CAR_TYPE_EL_VIN_VALUE = "car_type_el_vin_value";
        public static final String CHARGE_LIMIT = "charge_limit";
        public static final String CHARGE_LIMIT_SETTINGS = "charge_limit_settings";
        public static final String CHARGE_LIMIT_VISIBLE = "charge_limit_visible";
        public static final String CHARGE_SMART_VISIBLE = "charge_smart_visible";
        public static final String CHARGE_TEMP_CTL_STATE = "charge_temp_ctl_state";
        public static final String CHARGE_TEMP_CTL_STATE_VISIBLE = "charge_temp_ctl_state_visible";
        public static final String CHARGE_TIMER1 = "charge_timer1";
        public static final String CHARGE_TIMER1_CYCLE_DAYS = "charge_timer1_cycle_days";
        public static final String CHARGE_TIMER1_TIME = "charge_timer1_time";
        public static final String CHARGE_TIMER2 = "charge_timer2";
        public static final String CHARGE_TIMER2_CYCLE_DAYS = "charge_timer2_cycle_days";
        public static final String CHARGE_TIMER2_TIME = "charge_timer2_time";
        public static final String CHARGE_TIMER_VISIBLE = "charge_timer_visible";
        public static final String CHARGING_WORK_MODE_SETTING = "charging_work_mode_setting";
        public static final String CHARGING_WORK_MODE_SETTING_VISIBLE = "charging_work_mode_setting_visible";
        public static final String CLASSIC_BYD_APP_THEME = "classic_byd_app_theme";
        public static final String COMFORTABLE_PARKING_SWITCH = "comfortable_parking_switch";
        public static final String COMFORTABLE_PARKING_SWITCH_VISIBLE = "comfortable_parking_switch_visible";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/settings";
        public static final String CONTENT_TYPE_MULTIUSER = "vnd.android.cursor.dir/multi_user";
        public static final String CONTENT_TYPE_SYSTEM = "vnd.android.cursor.dir/system_settings";
        public static final String CONVENIENCE_DOOR_STATE = "convenience_door_state";
        public static final String CONVENIENCE_DRIVER_DESK_CHAIR_AERATION = "convenience_driver_desk_chair_aeration";
        public static final String CONVENIENCE_DRIVER_DESK_CHAIR_HEATING = "convenience_driver_desk_chair_heating";
        public static final String CONVENIENCE_ENERGY_BALANCE_POINT = "convenience_energy_balance_point";
        public static final String CONVENIENCE_ENGINE_SIMULATOR = "convenience_engine_simulator";
        public static final String CONVENIENCE_FRONT_PASSENGER_DESK_CHAIR_AERATION = "convenience_front_passenger_desk_chair_aeration";
        public static final String CONVENIENCE_FRONT_PASSENGER_DESK_CHAIR_HEATING = "convenience_front_passenger_desk_chair_heating";
        public static final String CONVENIENCE_INTELLIGENT_HIGH_BEAM_LAMP = "convenience_intelligent_high_beam_lamp";
        public static final String CURRENT_USER_ID = "current_user_id";
        public static final String CUSTOM_KEY_VALUE = "custom_key_value";
        public static final String DAYTIME_RUNNING_LIGHT_VISIBLE = "daytime_running_light_visible";
        public static final String DIRAC_LIVE_MODE_VISIBLE = "dirac_live_mode_visible";
        public static final String DIRAC_LIVE_PERFORMANCE_VISIBLE = "dirac_live_performance_visible";
        public static final String DIRAC_LIVE_VISIBLE = "dirac_live_visible";
        public static final String DITRAINER_MODE_VISIBLE = "ditrainer_mode_visible";
        public static final String DOOR_AUTOMATIC_DETECTION = "door_automatic_detection";
        public static final String DOOR_AUTOMATIC_DETECTION_VISIBLE = "door_automatic_detection_visible";
        public static final String DOOR_LOCK_CHILD_VISIBLE = "door_lock_child_visible";
        public static final String DOOR_LOCK_WINDOW_VISIBLE = "door_lock_window_visible";
        public static final String DOOR_STATE_ONLINE = "door_state_online";
        public static final String DOOR_UNCLOSED_WARNING = "door_unclosed_warning";
        public static final String DRIVER_MONITOR_VISIBLE = "driver_monitor_visible";
        public static final String DRIVER_RECORDER_VALUE = "driver_recorder_value";
        public static final String DRIVER_SEAT_HEATING_ONE = "driver_seat_heating_one";
        public static final String DRIVER_SEAT_HEATING_ONE_ONLINE = "driver_seat_heating_one_online";
        public static final String DRIVER_SEAT_HEATING_ONLINE = "driver_seat_heating_online";
        public static final String DRIVER_SEAT_VENTILATING_ONLINE = "driver_seat_ventilating_online";
        public static final String DUAL_TEMP_AREA_AC_ONLINE = "dual_temp_area_ac_online";
        public static final String DYNAMIC_SUSPENSION_VISIBLE = "dynamic_suspension_visible";
        public static final String DYN_AUDIO_VISIBLE = "dyn_audio_visible";
        public static final String ELECTRIC_BACK_DOOR_VALUE = "electric_back_door_value";
        public static final String ENERGY_AC_SETUP_VISIBLE = "energy_ac_setup_visible";
        public static final String ENERGY_DISPLAY_TYPE_VISIBLE = "energy_display_type_visible";
        public static final String ENERGY_RECYCLE_SETTING = "energy_recycle_setting";
        public static final String ENERGY_RECYCLE_SETTING_VISIBLE = "energy_recycle_setting_visible";
        public static final String ENGINE_SIMULATOR = "engine_simulator";
        public static final String ENGINE_SIMULATOR_SOUND_SOURCE = "engine_simulator_sound_source";
        public static final String ENGINE_SIMULATOR_SOUND_SOURCE_VISIBLE = "engine_simulator_sound_source_visible";
        public static final String ESP_ASSIST = "esp_assist";
        public static final String ESP_ASSIST_VISIBLE = "esp_assist_visible";
        public static final String E_PEDAL_MODE_VISIBLE = "e_pedal_mode_visible";
        public static final String FAIL_PROMPT_FOR_VEHICLE_START_OR_WITHDRAWAL = "fail_prompt_for_vehicle_start_or_withdrawal";
        public static final String FRONT_SEAT_HEATING_ONLINE = "front_seat_heating_online";
        public static final String FRONT_SEAT_VENTILATING_ONLINE = "front_seat_ventilating_online";
        public static final String GEAR_BOX_TYPE = "gear_box_type";
        public static final String GO_HOME_TIME = "go_home_time";
        public static final String GREEN_NET_VALUE = "green_net_value";
        public static final String HANDLE_AUTO_RESET = "handle_auto_reset_visible";
        public static final String HMA_VISIBLE = "intelligent_far_near_light";
        public static final String HOLD_SWITCH_FOR_WINDOW_DOWN = "hold_switch_for_window_down";
        public static final String HOLD_SWITCH_FOR_WINDOW_DOWN_VISIBLE = "hold_switch_for_window_down_visible";
        public static final String HOLD_SWITCH_FOR_WINDOW_UP = "hold_switch_for_window_up";
        public static final String HOLD_SWITCH_FOR_WINDOW_UP_VISIBLE = "hold_switch_for_window_up_visible";
        public static final String IBOOSTER_ASSIST = "ibooster_assist";
        public static final String IBOOSTER_ASSIST_VISIBLE = "ibooster_assist_visible";
        public static final String INDIRECT_TIRE_PRESSURE_DETECTION = "indirect_tire_pressure_detection";
        public static final String INTEGRATED_CRUISE_ASSIST = "integrated_cruise_assist";
        public static final String INTEGRATED_CRUISE_ASSIST_VISIBLE = "integrated_cruise_assist_visible";
        public static final String KEY_LOW_POWER_WARNING = "key_low_power_warning";
        public static final String LANE_ASSIST_LDWS = "lane_assist_ldws";
        public static final String LANE_ASSIST_LDWS_VISIBLE = "lane_assist_ldws_visible";
        public static final String LANE_KEEPING = "lane_keeping";
        public static final String LANE_KEEPING_VISIBLE = "lane_keeping_visible";
        public static final String LANE_LINE_ASSIST = "lane_line_assist";
        public static final String LANE_LINE_ASSIST_VISIBLE = "lane_line_assist_visible";
        public static final String LANTERN_LANGUAGE_VISIBLE = "lantern_language_visible";
        public static final String LAUNCH_AVO_FROM_SETTINGS = "launch_avo_from_settings";
        public static final String LEAVE_HOME_TIME = "leave_home_time";
        public static final String LEFT_WITH_P_WARNING = "left_with_p_warning";
        public static final String LEFT_WITH_P_WARNING_VISIBLE = "left_with_p_warning_visible";
        public static final String LIGHTING_AFS_SETTING = "lighting_afs_setting";
        public static final String LIGHTING_AFS_SETTING_VISIBLE = "lighting_afs_setting_visible";
        public static final String LIGHTING_AMBIENT_BRIGHTNESS = "lighting_ambient_brightness";
        public static final String LIGHTING_AMBIENT_BRIGHTNESS_VISIBLE = "lighting_ambient_brightness_visible";
        public static final String LIGHTING_AMBIENT_COLOR = "lighting_ambient_color";
        public static final String LIGHTING_AMBIENT_FIELD = "lighting_ambient_field";
        public static final String LIGHTING_AMBIENT_FIELD_VISIBLE = "lighting_ambient_field_visible";
        public static final String LIGHTING_BACKLIGHT_AUTO_BACKLIGHT = "lighting_backlight_auto_backlight";
        public static final String LIGHTING_BACKLIGHT_BRIGHTNESS = "lighting_backlight_brightness";
        public static final String LIGHTING_BACKLIGHT_DASHBOARD_AUTO = "lighting_backlight_dashboard_auto";
        public static final String LIGHTING_BACKLIGHT_DASHBOARD_BRIGHTNESS = "lighting_backlight_dashboard_brightness";
        public static final String LIGHTING_BACKLIGHT_DASHBOARD_BRIGHTNESS_VISIBLE = "lighting_backlight_dashboard_brightness_visible";
        public static final String LIGHTING_BACKLIGHT_NIGHT_BRIGHTNESS = "lighting_backlight_night_brightness";
        public static final String LIGHTING_HLL_SETTING = "lighting_hll_setting";
        public static final String LIGHTING_HLL_SETTING_VISIBLE = "lighting_hll_setting_visible";
        public static final String LIGHTING_ILLUMINATION_COURTESY_LIGHTING_TIME = "lighting_illumination_courtesy_lighting_time";
        public static final String LIGHTING_ILLUMINATION_HOME_LIGHTING_DELAY = "lighting_illumination_home_lighting_delay";
        public static final String LIGHTING_ILLUMINATION_INCAR_LIGHTING_TIME = "lighting_illumination_incar_lighting_time";
        public static final String LIGHTING_ILLUMINATION_LEAVE_LIGHTING_DELAY = "lighting_illumination_leave_lighting_delay";
        public static final String LIGHT_THEME_ON = "light_theme_on";
        public static final String LOW_FUEL = "low_fuel";
        public static final String LOW_FUEL_WARNING = "low_fuel_warning";
        public static final String MAX_EV_MODE_MEMORY_VISIBLE = "max_ev_mode_memory_visible";
        public static final String MODEL_VALUE = "model_value";
        public static final String MOON_ROOF = "def_moon_roof";
        public static final int MULTIUSER = 3;
        public static final String MUSIC_MODE_VISIBLE = "music_mode_visible";
        public static final String NFC_KEY_VISIBLE = "nfc_key_visible";
        public static final String NIGHT_LIGHT_WARNING = "night_light_warning";
        public static final String NOVICE_MODE_WARNING = "novice_mode_warning";
        public static final String NO_KEY_WARNING = "no_key_warning";
        public static final String OIL_LIFE_VISIBLE = "oil_life_visible";
        public static final String PAD_AUTO_RISE_SCREEN_VALUE = "pad_auto_rise_screen_value";
        public static final String PAD_ROTATION_VALUE = "pad_rotation_value";
        public static final String PANORAMA_AUTO_SWITCH = "panorama_auto_switch";
        public static final String PANORAMA_AUTO_SWITCH_VISIBLE = "panorama_auto_switch_visible";
        public static final String PANORAMA_ONLINE = "panorama_online";
        public static final String PANORAMA_SUN_ROOF_ONLINE_VALUE = "panorama_sun_roof_online_value";
        public static final String PARKING_BRAKE_TYPE = "parking_brake_type";
        public static final String PARKING_BRAKE_WARNING = "parking_brake_warning";
        public static final String PARKING_BRAKE_WARNING_VISIBLE = "parking_brake_warning_visible";
        public static final String PASSENGER_SEAT_HEATING_ONE = "passenger_seat_heating_one";
        public static final String PASSENGER_SEAT_HEATING_ONE_ONLINE = "passenger_seat_heating_one_online";
        public static final String PM25_CHANNEL_TYPE = "pm25_channel_type_value";
        public static final String POWER_AUTOMATIC_DETECTION = "power_automatic_detection";
        public static final String POWER_AUTOMATIC_DETECTION_VISIBLE = "power_automatic_detection_visible";
        public static final String POWER_MANAGEMENT = "power_management";
        public static final String POWER_MANAGEMENT_VISIBLE = "power_management_visible";
        public static final String POWER_TYPE = "power_type";
        public static final String PREDICTIVE_COLLISION_WARNING = "predictive_collision_warning";
        public static final String PREDICTIVE_COLLISION_WARNING_VISIBLE = "predictive_collision_warning_visible";
        public static final String PREFER_OIL_OR_ELECTRICITY_VISIBLE = "prefer_oil_or_electricity_visible";
        public static final String PRE_SALES_MODE_VISIBLE = "pre_sales_mode_visible";
        public static final String PURE_ELECTRIC_DRIVING_MILEAGE_SWITCH = "pure_electric_driving_mileage_switch";
        public static final String PURE_ELECTRIC_DRIVING_MILEAGE_SWITCH_VISIBLE = "pure_electric_driving_mileage_switch_visible";
        public static final String QUICK_ACCELERATION_LIMITED_WARNING = "quick_acceleration_limited_warning";
        public static final String RAIN_CLOSE_WINDOW = "rain_close_window";
        public static final String RAIN_CLOSE_WINDOW_VISIBLE = "rain_close_window_visible";
        public static final String RAMP_SLOW_DOWN_SWITCH = "ramp_slow_down_switch";
        public static final String RAMP_SLOW_DOWN_SWITCH_VISIBLE = "ramp_slow_down_switch_visible";
        public static final String REAR_AC_HAS_FEATURE_SET_AC = "rear_ac_has_feature_set_ac";
        public static final String REAR_AC_HAS_FEATURE_SET_AC_LOCK = "rear_ac_has_feature_set_ac_lock";
        public static final String REAR_AC_HAS_FEATURE_SET_AC_POWER = "rear_ac_has_feature_set_ac_power";
        public static final String REAR_AC_HAS_FEATURE_SET_AUTOMATIC = "rear_ac_has_feature_set_automatic";
        public static final String REAR_AC_HAS_FEATURE_SET_TEMPERATURE = "rear_ac_has_feature_set_temperature";
        public static final String REAR_AC_HAS_FEATURE_SET_WIND_LEVEL = "rear_ac_has_feature_set_wind_level";
        public static final String REAR_AC_HAS_FEATURE_SET_WIND_MODE = "rear_ac_has_feature_set_wind_mode";
        public static final String REAR_AC_MAX_WIND_LEVEL = "rear_ac_max_wind_level";
        public static final String REAR_AC_ONLINE = "rear_ac_online";
        public static final String REAR_AC_PANEL_LOCK = "rear_ac_panel_lock";
        public static final String REAR_AC_PANEL_LOCK_VISIBLE = "rear_ac_panel_lock_visible";
        public static final String REAR_LEFT_SEAT_HEATING = "rear_left_seat_heating";
        public static final String REAR_LEFT_SEAT_HEATING_ONLINE = "rear_left_seat_heating_online";
        public static final String REAR_LEFT_SEAT_VENTILATING = "rear_left_seat_ventilating";
        public static final String REAR_LEFT_SEAT_VENTILATING_ONLINE = "rear_left_seat_ventilating_online";
        public static final String REAR_RIGHT_SEAT_HEATING = "rear_right_seat_heating";
        public static final String REAR_RIGHT_SEAT_HEATING_ONLINE = "rear_right_seat_heating_online";
        public static final String REAR_RIGHT_SEAT_VENTILATING = "rear_right_seat_ventilating";
        public static final String REAR_RIGHT_SEAT_VENTILATING_ONLINE = "rear_right_seat_ventilating_online";
        public static final String REMOTE_DRIVING_SUSPENDED_USE = "remote_driving_suspended_use";
        public static final String REMOTE_DRIVING_SUSPENDED_USE_VALUE = "remote_driving_suspended_use_value";
        public static final String REMOTE_DRIVING_SUSPENDED_USE_WARNING = "remote_driving_suspended_use_warning";
        public static final String REMOTE_SCREENAGE_VISIBLE = "remote_screenage_visible";
        public static final String REMOTE_UNLOCK = "remote_unlock";
        public static final String REMOTE_UNLOCK_VISIBLE = "remote_unlock_visible";
        public static final String REMOTE_WINDOW_DOWN = "remote_window_down";
        public static final String REMOTE_WINDOW_DOWN_VISIBLE = "remote_window_down_visible";
        public static final String REMOTE_WINDOW_UP = "remote_window_up";
        public static final String REMOTE_WINDOW_UP_VISIBLE = "remote_window_up_visible";
        public static final String RIGHT_ANTERIOR_IMAGE_SETTING = "right_anterior_image_setting";
        public static final String ROTATION_MEMORY_SETTINS = "rotation_memory_settings";
        public static final String SAFETY_BELT_WARNING = "safety_belt_warning";
        public static final String SAFETY_BELT_WARNING_VISIBLE = "safety_belt_warning_visivle";
        public static final String SAFETY_WARNING = "safety_warning";
        public static final String SAVE_FUNCTION_MEMORY_VISIBLE = "save_function_memory_visible";
        public static final String SEAT_BELT_WARNING = "seat_belt_warning";
        public static final String SELF_STUDY_SWITCH = "self_study_switch";
        public static final int SETTINGS = 1;
        public static final int SETTINGS_SYSTEM = 2;
        public static final String SHUT_WINDOW_AFTER_LOCKING = "shut_window_after_locking";
        public static final String SHUT_WINDOW_AFTER_LOCKING_VISIBLE = "shut_window_after_locking_visible";
        public static final String SMART_POWER_MODE_VISIBLE = "smart_power_mode_visible";
        public static final String SMART_VOICE_WARN_MODE = "smart_voice_warn_mode";
        public static final String SMART_WELCOME_LIGHT_VISIBLE = "smart_welcome_light_visible";
        public static final String SOC_SAVE_VISIBLE = "soc_save_visible";
        public static final String SOC_SETTING_VISIBLE = "soc_setting";
        public static final String SOUND_FIELD_HIGH_CONFIG_VISIBLE = "sound_field_high_config_visible";
        public static final String SPEEDING_ATRESIA = "speeding_atresia";
        public static final String SPEEDING_ATRESIA_VISIBLE = "speeding_atresia_visible";
        public static final String SPORT_MODE_MEMORY_VISIBLE = "sport_mode_memory_visible";
        public static final String START_OR_FAILURE_STATUS = "start_or_failure_status";
        public static final String STEERING_ASSIST = "steering_assist";
        public static final String STEERING_ASSIST_VISIBLE = "steering_assist_visible";
        public static final String STORAGE_BATTERY_LOW = "storage_battery_low";
        public static final String STORAGE_BATTERY_LOW_WARNING = "storage_battery_low_warning";
        public static final String SUN_SHADE_PANEL = "def_sun_shade_panel";
        public static final String SUPPORT_SMART_LANDSCAPE = "support_smart_landscape";
        public static final String SWITCH_USER_DATA = "switch_user_data";
        public static final String SYSTEM_SENSITIVITY = "system_sensitivity";
        public static final String SYSTEM_SENSITIVITY_VISIBLE = "system_sensitivity_visible";
        public static final String SYSTEM_UNIT_VISIBLE = "system_unit_visible";
        public static final String TABLE_NAME_MULTIUSER = "multi_user";
        public static final String TABLE_NAME_SETTINGS = "settings";
        public static final String TABLE_NAME_SYSTEM = "system_settings";
        public static final String TACHOGRAPH_RECORD = "tachograph_record";
        public static final String TAG = "UserTableData";
        public static final String TEXT_TO_SPEECH_VALUE = "text_to_speech_value";
        public static final String THIRTY_MINUTES_AUTOMATIC_DETECTION = "thirty_minutes_automatic_detection";
        public static final String THIRTY_MINUTES_AUTOMATIC_DETECTION_VISIBLE = "thirty_minutes_automatic_detection_visible";
        public static final String TIME_SCREEN_SAVER_MODE = "time_screen_saver_mode";
        public static final String TRAFFIC_SIGNS = "traffic_signs";
        public static final String TRAFFIC_SIGNS_VISIBLE = "traffic_signs_visible";
        public static final String TRAVEL_SETTING_VALUE = "travel_setting_value";
        public static final String UNIT_AIR_PRESSURE_VISIBLE = "unit_air_pressure_visible";
        public static final String UNIT_DISTANCE_FUEL_CONSUMPTION_VISIBLE = "unit_distance_fuel_consumption_visible";
        public static final String UNIT_POWER_VISIBLE = "unit_power_visible";
        public static final String UNIT_TEMPERATURE_VISIBLE = "unit_temperature_visible";
        public static final String UNLOCK_SETTING_ONLINE_VALUE = "unlock_setting_online_value";
        public static final String UP_DOWN_SCREEN_VALUE = "up_down_screen_value";
        public static final String USER_ID = "user_id";
        public static final String VEHICLE_VELOCITY_OF_REMINDING_VISIBLE = "vehicle_velocity_of_reminding_visible";
        public static final String VEHICLE_VERSION_DIAGNOSIS_VALUE = "vehicle_version_diagnosis_value";
        public static final String VEHICLE_VERSION_UPGRADE_AVAILABLE = "vehicle_version_upgrade_available";
        public static final String VEHICLE_VERSION_UPGRADE_TIME = "vehicle_version_upgrade_time";
        public static final String VENTILATION_HEATING_SEAT = "ventilation_heating_seat";
        public static final String VOICE_PLAY_VOLUME_ADJUSTMENT = "voice_play_volume_adjustment";
        public static final String VTOL_SET_TIME_COUNT = "vtol_set_time_count";
        public static final String VTOL_SET_TIME_VISIBLE = "vtol_set_time_visible";
        public static final String WIND_DECREASE_FOR_BT = "wind_decrease_for_bt";
        public static final String WIND_DECREASE_FOR_BT_VISIBLE = "wind_decrease_for_bt_visible";
        public static final String WIND_MODE_NUMBER = "wind_mode_number";
        public static final String WIRELESS_CHARGING = "wireless_charging";
        public static final String WIRELESS_CHARGING_ONLINE = "wireless_charging_online";
        public static final UriMatcher uriMatcher;
        public static final Uri CONTENT_URI = Uri.parse("content://carsettings/settings");
        public static final Uri CONTENT_URI_SYSTEM = Uri.parse("content://carsettings/system_settings");
        public static final Uri CONTENT_URI_MULTIUSER = Uri.parse("content://carsettings/multi_user");

        static {
            UriMatcher uriMatcher2 = new UriMatcher(-1);
            uriMatcher = uriMatcher2;
            uriMatcher2.addURI("carsettings", TABLE_NAME_SETTINGS, 1);
            uriMatcher2.addURI("carsettings", TABLE_NAME_SYSTEM, 2);
            uriMatcher2.addURI("carsettings", TABLE_NAME_MULTIUSER, 3);
        }

        public static boolean createUserData(ContentResolver contentResolver, int i2) {
            try {
                Log.d(TAG, "createUserData current_user_id:" + i2);
                Cursor cursorQuery = contentResolver.query(CONTENT_URI_MULTIUSER, null, "current_user_id=" + i2, null, null);
                if (cursorQuery != null) {
                    int count = cursorQuery.getCount();
                    Log.d(TAG, "createUserData userCount:" + count);
                    if (count > 0) {
                        cursorQuery.close();
                        Log.d(TAG, "createUserData user data in multi_user TABLE already exsit.");
                        return false;
                    }
                    cursorQuery.close();
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put(CURRENT_USER_ID, Integer.valueOf(i2));
                Cursor cursorQuery2 = contentResolver.query(CONTENT_URI, null, null, null, null);
                if (cursorQuery2 != null) {
                    while (!cursorQuery2.isLast()) {
                        cursorQuery2.moveToNext();
                        String string = cursorQuery2.getString(cursorQuery2.getColumnIndex("key"));
                        int i3 = cursorQuery2.getInt(cursorQuery2.getColumnIndex("value"));
                        contentValues.put("key", string);
                        contentValues.put("value", Integer.valueOf(i3));
                        Uri uri = CONTENT_URI_MULTIUSER;
                        if (contentResolver.insert(uri, contentValues) != null) {
                            contentResolver.notifyChange(uri, null, String.valueOf(i2), 1);
                        }
                    }
                    cursorQuery2.close();
                }
                putSystemInt(contentResolver, CURRENT_USER_ID, i2);
                return true;
            } catch (Exception e2) {
                Log.i(TAG, "createUserData: e = " + e2);
                return false;
            }
        }

        public static boolean deleteUserData(ContentResolver contentResolver, int i2) {
            try {
                Log.d(TAG, "deleteUserData current_user_id:" + i2);
                Uri uri = CONTENT_URI_MULTIUSER;
                if (contentResolver.delete(uri, "current_user_id=" + i2, null) < 0) {
                    return true;
                }
                contentResolver.notifyChange(uri, null, String.valueOf(i2), 2);
                return true;
            } catch (Exception unused) {
                return false;
            }
        }

        public static boolean getBoolean(ContentResolver contentResolver, String str) {
            Log.i(TAG, "getBoolean: name is " + str);
            Cursor cursorQuery = contentResolver.query(CONTENT_URI, null, "key='" + str + "'", null, null);
            try {
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.getCount() > 0) {
                            if (!cursorQuery.isLast()) {
                                cursorQuery.moveToNext();
                                if (cursorQuery.getInt(cursorQuery.getColumnIndex("value")) == 1) {
                                    cursorQuery.close();
                                    return true;
                                }
                                cursorQuery.close();
                                return false;
                            }
                            cursorQuery.close();
                            Log.d(TAG, "getBoolean name:" + str + " not found.");
                            return false;
                        }
                    } catch (Exception e2) {
                        Log.i(TAG, "getBoolean: e = " + e2);
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return false;
                    }
                }
                Log.i(TAG, "getBoolean: cursor is null");
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return false;
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                throw th;
            }
        }

        public static int getInt(ContentResolver contentResolver, String str) {
            Log.i(TAG, "getInt: name is " + str);
            Cursor cursorQuery = contentResolver.query(CONTENT_URI, null, "key='" + str + "'", null, null);
            try {
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.getCount() > 0) {
                            int i2 = -1;
                            while (!cursorQuery.isLast()) {
                                cursorQuery.moveToNext();
                                i2 = cursorQuery.getInt(cursorQuery.getColumnIndex("value"));
                            }
                            cursorQuery.close();
                            return i2;
                        }
                    } catch (Exception e2) {
                        Log.i(TAG, "getInt: e = " + e2);
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return -1;
                    }
                }
                Log.i(TAG, "getInt: cursor is null");
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return -1;
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                throw th;
            }
        }

        public static int getMultiUserInt(ContentResolver contentResolver, String str, int i2) {
            Log.i(TAG, "getMultiUserInt: name is " + str + ", current_user_id is " + i2);
            Cursor cursorQuery = contentResolver.query(CONTENT_URI_MULTIUSER, null, "current_user_id=" + i2 + " and key = '" + str + "'", null, null);
            try {
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.getCount() > 0) {
                            int i3 = -1;
                            while (!cursorQuery.isLast()) {
                                cursorQuery.moveToNext();
                                i3 = cursorQuery.getInt(cursorQuery.getColumnIndex("value"));
                            }
                            cursorQuery.close();
                            return i3;
                        }
                    } catch (Exception e2) {
                        Log.i(TAG, "putMultiUserInt: e = " + e2);
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return -1;
                    }
                }
                Log.i(TAG, "getMultiUserInt: cursor is null");
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return -1;
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                throw th;
            }
        }

        public static boolean getSystemBoolean(ContentResolver contentResolver, String str) {
            Log.i(TAG, "getSystemBoolean: name is " + str);
            Cursor cursorQuery = contentResolver.query(CONTENT_URI_SYSTEM, null, "key='" + str + "'", null, null);
            try {
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.getCount() > 0) {
                            if (!cursorQuery.isLast()) {
                                cursorQuery.moveToNext();
                                if (cursorQuery.getInt(cursorQuery.getColumnIndex("value")) == 1) {
                                    cursorQuery.close();
                                    return true;
                                }
                                cursorQuery.close();
                                return false;
                            }
                            cursorQuery.close();
                            Log.d(TAG, "getSystemBoolean name:" + str + " not found.");
                            return false;
                        }
                    } catch (Exception e2) {
                        Log.i(TAG, "getSystemBoolean: e = " + e2);
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return false;
                    }
                }
                Log.i(TAG, "getSystemBoolean: cursor is null");
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return false;
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                throw th;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x005a A[Catch: all -> 0x0056, Exception -> 0x0058, TRY_LEAVE, TryCatch #1 {Exception -> 0x0058, blocks: (B:4:0x0039, B:7:0x0040, B:9:0x0046, B:15:0x005a), top: B:27:0x0039, outer: #0 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static int getSystemInt(android.content.ContentResolver r8, java.lang.String r9) {
            /*
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "getSystemInt: name is "
                r0.append(r1)
                r0.append(r9)
                java.lang.String r0 = r0.toString()
                java.lang.String r1 = "UserTableData"
                android.util.Log.i(r1, r0)
                android.net.Uri r3 = android.hardware.bydauto.properties.CarSettings.UserTableData.CONTENT_URI_SYSTEM
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r2 = "key='"
                r0.append(r2)
                r0.append(r9)
                java.lang.String r9 = "'"
                r0.append(r9)
                java.lang.String r5 = r0.toString()
                r4 = 0
                r6 = 0
                r7 = 0
                r2 = r8
                android.database.Cursor r8 = r2.query(r3, r4, r5, r6, r7)
                r9 = -1
                if (r8 == 0) goto L5a
                int r0 = r8.getCount()     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
                if (r0 <= 0) goto L5a
                r0 = r9
            L40:
                boolean r2 = r8.isLast()     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
                if (r2 != 0) goto L54
                r8.moveToNext()     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
                java.lang.String r0 = "value"
                int r0 = r8.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
                int r0 = r8.getInt(r0)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
                goto L40
            L54:
                r9 = r0
                goto L5f
            L56:
                r9 = move-exception
                goto L7f
            L58:
                r0 = move-exception
                goto L65
            L5a:
                java.lang.String r0 = "getSystemInt: cursor is null"
                android.util.Log.i(r1, r0)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
            L5f:
                if (r8 == 0) goto L64
                r8.close()
            L64:
                return r9
            L65:
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L56
                r2.<init>()     // Catch: java.lang.Throwable -> L56
                java.lang.String r3 = "getBoolean: e = "
                r2.append(r3)     // Catch: java.lang.Throwable -> L56
                r2.append(r0)     // Catch: java.lang.Throwable -> L56
                java.lang.String r0 = r2.toString()     // Catch: java.lang.Throwable -> L56
                android.util.Log.i(r1, r0)     // Catch: java.lang.Throwable -> L56
                if (r8 == 0) goto L7e
                r8.close()
            L7e:
                return r9
            L7f:
                if (r8 == 0) goto L84
                r8.close()
            L84:
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: android.hardware.bydauto.properties.CarSettings.UserTableData.getSystemInt(android.content.ContentResolver, java.lang.String):int");
        }

        public static boolean putBoolean(ContentResolver contentResolver, String str, boolean z) {
            Log.i(TAG, "putBoolean: name is " + str + ", value is " + z);
            try {
                Uri uri = CONTENT_URI;
                Cursor cursorQuery = contentResolver.query(uri, null, "key='" + str + "'", null, null);
                if (cursorQuery != null && cursorQuery.getCount() > 0) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("value", Boolean.valueOf(z));
                    if (contentResolver.update(uri, contentValues, "key=?", new String[]{str}) >= 0) {
                        contentResolver.notifyChange(uri, null, str, 3);
                    }
                    int systemInt = getSystemInt(contentResolver, CURRENT_USER_ID);
                    if (systemInt != -1) {
                        putMultiUserBoolean(contentResolver, str, z, systemInt);
                    }
                    return true;
                }
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("key", str);
                contentValues2.put("value", Boolean.valueOf(z));
                if (contentResolver.insert(uri, contentValues2) != null) {
                    contentResolver.notifyChange(uri, null, str, 1);
                }
                Log.i(TAG, "putBoolean: insert into settings name is " + str + ", value is " + z);
                return true;
            } catch (Exception e2) {
                Log.i(TAG, "putBoolean: e = " + e2);
                return false;
            }
        }

        public static boolean putInt(ContentResolver contentResolver, String str, int i2) {
            Log.i(TAG, "putInt: name is " + str + ", value is " + i2);
            try {
                Uri uri = CONTENT_URI;
                Cursor cursorQuery = contentResolver.query(uri, null, "key='" + str + "'", null, null);
                if (cursorQuery != null && cursorQuery.getCount() > 0) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("value", Integer.valueOf(i2));
                    if (contentResolver.update(uri, contentValues, "key=?", new String[]{str}) >= 0) {
                        contentResolver.notifyChange(uri, null, str, 3);
                    }
                    int systemInt = getSystemInt(contentResolver, CURRENT_USER_ID);
                    if (systemInt != -1) {
                        putMultiUserInt(contentResolver, str, i2, systemInt);
                    }
                    return true;
                }
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("key", str);
                contentValues2.put("value", Integer.valueOf(i2));
                if (contentResolver.insert(uri, contentValues2) != null) {
                    contentResolver.notifyChange(uri, null, str, 1);
                }
                Log.i(TAG, "putInt: insert into settings name is " + str + ", value is " + i2);
                return true;
            } catch (Exception e2) {
                Log.i(TAG, "putInt: e = " + e2);
                return false;
            }
        }

        public static boolean putMultiUserBoolean(ContentResolver contentResolver, String str, boolean z, int i2) {
            Log.i(TAG, "putMultiUserBoolean: name is " + str + ", value is " + z + ", current_user_id is " + i2);
            try {
                Uri uri = CONTENT_URI_MULTIUSER;
                Cursor cursorQuery = contentResolver.query(uri, null, "current_user_id=" + i2 + " and key = '" + str + "'", null, null);
                if (cursorQuery != null && cursorQuery.getCount() > 0) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("value", Boolean.valueOf(z));
                    if (contentResolver.update(uri, contentValues, "current_user_id=" + i2 + " and key = '" + str + "'", null) >= 0) {
                        contentResolver.notifyChange(uri, null, str, 3);
                    }
                    return true;
                }
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put(CURRENT_USER_ID, Integer.valueOf(i2));
                contentValues2.put("key", str);
                contentValues2.put("value", Boolean.valueOf(z));
                if (contentResolver.insert(uri, contentValues2) != null) {
                    contentResolver.notifyChange(uri, null, str, 1);
                }
                Log.i(TAG, "putMultiUserBoolean: insert into multi_user current_user_id is " + i2 + ", name is " + str + ", value is " + z);
                return true;
            } catch (Exception e2) {
                Log.i(TAG, "putMultiUserBoolean: e = " + e2);
                return false;
            }
        }

        public static boolean putMultiUserInt(ContentResolver contentResolver, String str, int i2, int i3) {
            Log.i(TAG, "putMultiUserInt: name is " + str + ", value is " + i2 + ", current userId is " + i3);
            try {
                Uri uri = CONTENT_URI_MULTIUSER;
                Cursor cursorQuery = contentResolver.query(uri, null, "current_user_id=" + i3 + " and key = '" + str + "'", null, null);
                if (cursorQuery != null && cursorQuery.getCount() > 0) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("value", Integer.valueOf(i2));
                    if (contentResolver.update(uri, contentValues, "current_user_id=" + i3 + " and key = '" + str + "'", null) >= 0) {
                        contentResolver.notifyChange(uri, null, str, 3);
                    }
                    return true;
                }
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put(CURRENT_USER_ID, Integer.valueOf(i3));
                contentValues2.put("key", str);
                contentValues2.put("value", Integer.valueOf(i2));
                if (contentResolver.insert(uri, contentValues2) != null) {
                    contentResolver.notifyChange(uri, null, str, 1);
                }
                Log.i(TAG, "putMultiUserInt: insert into multi_user current_user_id is " + i3 + ", name is " + str + ", value is " + i2);
                return true;
            } catch (Exception e2) {
                Log.i(TAG, "putMultiUserInt: e = " + e2);
                return false;
            }
        }

        public static boolean putSettingsInt(ContentResolver contentResolver, String str, int i2) {
            Log.i(TAG, "putSettingsInt: name is " + str + ", value is " + i2);
            try {
                Uri uri = CONTENT_URI;
                Cursor cursorQuery = contentResolver.query(uri, null, "key='" + str + "'", null, null);
                if (cursorQuery != null && cursorQuery.getCount() > 0) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("value", Integer.valueOf(i2));
                    if (contentResolver.update(uri, contentValues, "key=?", new String[]{str}) >= 0) {
                        contentResolver.notifyChange(uri, null, str, 3);
                    }
                    return true;
                }
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("key", str);
                contentValues2.put("value", Integer.valueOf(i2));
                if (contentResolver.insert(uri, contentValues2) != null) {
                    contentResolver.notifyChange(uri, null, str, 1);
                }
                Log.i(TAG, "putSettingsInt: insert into settings name is " + str + ", value is " + i2);
                return true;
            } catch (Exception e2) {
                Log.i(TAG, "putSettingsInt: e = " + e2);
                return false;
            }
        }

        public static boolean putSystemBoolean(ContentResolver contentResolver, String str, boolean z) {
            Log.i(TAG, "putSystemBoolean: name is " + str + ", value is " + z);
            try {
                Uri uri = CONTENT_URI_SYSTEM;
                Cursor cursorQuery = contentResolver.query(uri, null, "key='" + str + "'", null, null);
                if (cursorQuery != null && cursorQuery.getCount() > 0) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("value", Boolean.valueOf(z));
                    if (contentResolver.update(uri, contentValues, "key=?", new String[]{str}) >= 0) {
                        contentResolver.notifyChange(uri, null, str, 3);
                    }
                    return true;
                }
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("key", str);
                contentValues2.put("value", Boolean.valueOf(z));
                if (contentResolver.insert(uri, contentValues2) != null) {
                    contentResolver.notifyChange(uri, null, str, 1);
                }
                Log.i(TAG, "putSystemBoolean: insert into system_settings key is " + str + ", value is " + z);
                return true;
            } catch (Exception e2) {
                Log.i(TAG, "putSystemBoolean: e = " + e2);
                return false;
            }
        }

        public static boolean putSystemInt(ContentResolver contentResolver, String str, int i2) {
            Log.i(TAG, "putSystemInt: name is " + str + ", value is " + i2);
            try {
                Uri uri = CONTENT_URI_SYSTEM;
                Cursor cursorQuery = contentResolver.query(uri, null, "key='" + str + "'", null, null);
                if (cursorQuery != null && cursorQuery.getCount() > 0) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("value", Integer.valueOf(i2));
                    if (contentResolver.update(uri, contentValues, "key=?", new String[]{str}) >= 0) {
                        contentResolver.notifyChange(uri, null, str, 3);
                    }
                    return true;
                }
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("key", str);
                contentValues2.put("value", Integer.valueOf(i2));
                if (contentResolver.insert(uri, contentValues2) != null) {
                    contentResolver.notifyChange(uri, null, str, 1);
                }
                Log.i(TAG, "putSystemInt: insert into system_settings name is " + str + ", value is " + i2);
                return true;
            } catch (Exception e2) {
                Log.i(TAG, "putSystemInt: e = " + e2);
                return false;
            }
        }

        public static boolean switchUserData(ContentResolver contentResolver, int i2) {
            try {
                int systemInt = getSystemInt(contentResolver, CURRENT_USER_ID);
                Log.d(TAG, "switchUserData userId:" + i2 + " oldUserId:" + systemInt);
                if (i2 == systemInt) {
                    Log.d(TAG, "switchUserData error, switch userId is same with current userId!");
                    return false;
                }
                Cursor cursorQuery = contentResolver.query(CONTENT_URI, null, null, null, null);
                if (cursorQuery != null) {
                    while (!cursorQuery.isLast()) {
                        cursorQuery.moveToNext();
                        String string = cursorQuery.getString(cursorQuery.getColumnIndex("key"));
                        int i3 = cursorQuery.getInt(cursorQuery.getColumnIndex("value"));
                        int multiUserInt = getMultiUserInt(contentResolver, string, i2);
                        if (multiUserInt != i3) {
                            putSettingsInt(contentResolver, string, multiUserInt);
                        }
                    }
                    cursorQuery.close();
                }
                putSystemInt(contentResolver, CURRENT_USER_ID, i2);
                return true;
            } catch (Exception e2) {
                Log.i(TAG, "switchUserData: e = " + e2);
                return false;
            }
        }
    }

    public static Uri getUriFor(Uri uri, String str) {
        if ("remote_screenage".equals(str)) {
            Log.i(TAG, "getUriFor: REMOTE_SCREENAGE");
            return Settings.System.getUriFor("remote_screenage_origins");
        }
        if ("remote_camera_traffic_switch".equals(str)) {
            Log.i(TAG, "getUriFor: REMOTE_CAMERA_TRAFFIC_SWITCH");
            return Settings.System.getUriFor("remote_camera_traffic_switch_origins");
        }
        if ("remote_camera_traffic_statistics".equals(str)) {
            Log.i(TAG, "getUriFor: REMOTE_CAMERA_TRAFFIC_STATISTICS");
            return Settings.System.getUriFor("remote_camera_traffic_statistics_origins");
        }
        Log.i(TAG, "getUriFor: name is :" + str);
        return Uri.withAppendedPath(uri, str);
    }
}

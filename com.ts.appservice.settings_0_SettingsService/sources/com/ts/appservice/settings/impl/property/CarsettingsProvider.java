package com.ts.appservice.settings.impl.property;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import com.ts.lib.settings.common.utilities.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class CarsettingsProvider extends ContentProvider {
    public static final String AUTHORITY = "carsettings";
    public static final int DATABASE_VERSION = 5;
    public static final int GLOBAL_DIR = 6;
    public static final int GOLBAL_ITEM = 7;
    public static final int MULTI_USER_DIR = 0;
    public static final int MULTI_USER_ITEM = 1;
    public static final int SETTINGS_DIR = 2;
    public static final int SETTINGS_ITEM = 3;
    public static final int SYSTEM_SETTINGS_DIR = 4;
    public static final int SYSTEM_SETTINGS_ITEM = 5;
    private static final String TAG = CarsettingsProvider.class.getSimpleName();
    private static UriMatcher uriMatcher;
    private SQLiteDatabase db;
    private MyDatabaseHelper dbHelper;

    static {
        UriMatcher uriMatcher2 = new UriMatcher(-1);
        uriMatcher = uriMatcher2;
        uriMatcher2.addURI(AUTHORITY, "multi_user", 0);
        uriMatcher.addURI(AUTHORITY, "multi_user/#", 1);
        uriMatcher.addURI(AUTHORITY, "settings", 2);
        uriMatcher.addURI(AUTHORITY, "settings/#", 3);
        uriMatcher.addURI(AUTHORITY, "system_settings", 4);
        uriMatcher.addURI(AUTHORITY, "system_settings/#", 5);
        uriMatcher.addURI(AUTHORITY, "global", 6);
        uriMatcher.addURI(AUTHORITY, "global/#", 7);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        String path = getContext().getFilesDir().getPath();
        LogUtil.info(TAG, "onCreate path " + path);
        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(getContext(), path + "/Carsettings.db", null, 5);
        this.dbHelper = myDatabaseHelper;
        myDatabaseHelper.getWritableDatabase();
        this.db = this.dbHelper.getWritableDatabase();
        return true;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case 0:
            case 1:
                long id = db.insert("multi_user", null, values);
                Uri uriReturn = Uri.parse("content://carsettings/multi_user/" + id);
                return uriReturn;
            case 2:
            case 3:
                long id2 = db.insert("settings", null, values);
                Uri uriReturn2 = Uri.parse("content://carsettings/settings/" + id2);
                return uriReturn2;
            case 4:
            case 5:
                long id3 = db.insert("system_settings", null, values);
                Uri uriReturn3 = Uri.parse("content://carsettings/system_settings/" + id3);
                return uriReturn3;
            case 6:
            case 7:
                long id4 = db.insert("global", null, values);
                Uri uriReturn4 = Uri.parse("content://carsettings/global/" + id4);
                return uriReturn4;
            default:
                return null;
        }
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        switch (uriMatcher.match(uri)) {
            case 0:
                Cursor cursor = db.query("multi_user", projection, selection, selectionArgs, null, null, sortOrder);
                return cursor;
            case 1:
                String id = uri.getPathSegments().get(1);
                Cursor cursor2 = db.query("multi_user", projection, "id = ?", new String[]{id}, null, null, sortOrder);
                return cursor2;
            case 2:
                Cursor cursor3 = db.query("settings", projection, selection, selectionArgs, null, null, sortOrder);
                return cursor3;
            case 3:
                String id2 = uri.getPathSegments().get(1);
                Cursor cursor4 = db.query("settings", projection, "id = ?", new String[]{id2}, null, null, sortOrder);
                return cursor4;
            case 4:
                Cursor cursor5 = db.query("system_settings", projection, selection, selectionArgs, null, null, sortOrder);
                return cursor5;
            case 5:
                String id3 = uri.getPathSegments().get(1);
                Cursor cursor6 = db.query("system_settings", projection, "id = ?", new String[]{id3}, null, null, sortOrder);
                return cursor6;
            case 6:
                Cursor cursor7 = db.query("global", projection, selection, selectionArgs, null, null, sortOrder);
                return cursor7;
            case 7:
                String id4 = uri.getPathSegments().get(1);
                Cursor cursor8 = db.query("global", projection, "id = ?", new String[]{id4}, null, null, sortOrder);
                return cursor8;
            default:
                return null;
        }
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case 0:
                int updatedRows = db.update("multi_user", values, selection, selectionArgs);
                return updatedRows;
            case 1:
                String id = uri.getPathSegments().get(1);
                int updatedRows2 = db.update("multi_user", values, "id = ?", new String[]{id});
                return updatedRows2;
            case 2:
                int updatedRows3 = db.update("settings", values, selection, selectionArgs);
                return updatedRows3;
            case 3:
                String id2 = uri.getPathSegments().get(1);
                int updatedRows4 = db.update("settings", values, "id = ?", new String[]{id2});
                return updatedRows4;
            case 4:
                int updatedRows5 = db.update("system_settings", values, selection, selectionArgs);
                return updatedRows5;
            case 5:
                String id3 = uri.getPathSegments().get(1);
                int updatedRows6 = db.update("system_settings", values, "id = ?", new String[]{id3});
                return updatedRows6;
            case 6:
                int updatedRows7 = db.update("global", values, selection, selectionArgs);
                return updatedRows7;
            case 7:
                String id4 = uri.getPathSegments().get(1);
                int updatedRows8 = db.update("global", values, "id = ?", new String[]{id4});
                return updatedRows8;
            default:
                return 0;
        }
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case 0:
                int deleteRows = db.delete("multi_user", selection, selectionArgs);
                return deleteRows;
            case 1:
                String id = uri.getPathSegments().get(1);
                int deleteRows2 = db.delete("multi_user", "id = ?", new String[]{id});
                return deleteRows2;
            case 2:
                int deleteRows3 = db.delete("settings", selection, selectionArgs);
                return deleteRows3;
            case 3:
                String id2 = uri.getPathSegments().get(1);
                int deleteRows4 = db.delete("settings", "id = ?", new String[]{id2});
                return deleteRows4;
            case 4:
                int deleteRows5 = db.delete("system_settings", selection, selectionArgs);
                return deleteRows5;
            case 5:
                String id3 = uri.getPathSegments().get(1);
                int deleteRows6 = db.delete("system_settings", "id = ?", new String[]{id3});
                return deleteRows6;
            case 6:
                int deleteRows7 = db.delete("global", selection, selectionArgs);
                return deleteRows7;
            case 7:
                String id4 = uri.getPathSegments().get(1);
                int deleteRows8 = db.delete("global", "id = ?", new String[]{id4});
                return deleteRows8;
            default:
                return 0;
        }
    }

    public static class MyDatabaseHelper extends SQLiteOpenHelper {
        public static final String CREATE_GLOBAL = "create table global (id integer primary key autoincrement,key text,value integer)";
        public static final String CREATE_MULTI_USER = "create table multi_user (user_id integer primary key autoincrement,Disinf_State text,current_user_id,key text,value integer)";
        public static final String CREATE_SETTINGS = "create table settings (id integer primary key autoincrement,key text,value integer)";
        public static final String CREATE_SYSTEM_SETTINGS = "create table system_settings (id integer primary key autoincrement,key text,value integer)";
        private Context mContext;

        public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            this.mContext = context;
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase db) {
            LogUtil.info(CarsettingsProvider.TAG, "onCreate");
            db.execSQL(CREATE_MULTI_USER);
            db.execSQL(CREATE_SETTINGS);
            db.execSQL(CREATE_GLOBAL);
            db.execSQL(CREATE_SYSTEM_SETTINGS);
            createData(db);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            LogUtil.info(CarsettingsProvider.TAG, "onUpgrade oldVersion = " + oldVersion + "newVersion = " + newVersion);
            db.execSQL("drop table if exists multi_user");
            db.execSQL("drop table if exists settings");
            db.execSQL("drop table if exists global");
            db.execSQL("drop table if exists system_settings");
            onCreate(db);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            LogUtil.info(CarsettingsProvider.TAG, "onDowngrade oldVersion = " + oldVersion + "newVersion = " + newVersion);
            db.execSQL("drop table if exists multi_user");
            db.execSQL("drop table if exists settings");
            db.execSQL("drop table if exists global");
            db.execSQL("drop table if exists system_settings");
            onCreate(db);
        }

        public void createData(SQLiteDatabase db) {
            db.execSQL("insert into global (key,value) values ('remote_screenage',0)");
            db.execSQL("insert into global (key,value) values ('insight_light_online',0)");
            db.execSQL("insert into global (key,value) values ('auto_wipe',0)");
            db.execSQL("insert into global (key,value) values ('fuel_mileage_online',0)");
            db.execSQL("insert into global (key,value) values ('fuel_consumption_online',0)");
            db.execSQL("insert into global (key,value) values ('elec_consumption_online',0)");
            db.execSQL("insert into global (key,value) values ('wechat_register_prompt_info',0)");
            db.execSQL("insert into global (key,value) values ('voice_control_window_visible',0)");
            db.execSQL("insert into global (key,value) values ('seat_massage_visible_left',0)");
            db.execSQL("insert into global (key,value) values ('seat_massage_visible_right',0)");
            db.execSQL("insert into global (key,value) values ('seat_massage_visible_left_rear',0)");
            db.execSQL("insert into global (key,value) values ('seat_massage_visible_right_rear',0)");
            db.execSQL("insert into global (key,value) values ('steering_wheel_heating_visible',0)");
            db.execSQL("insert into global (key,value) values ('ac_anion_visible',0)");
            db.execSQL("insert into global (key,value) values ('ac_rear_defrost_visible',0)");
            db.execSQL("insert into global (key,value) values ('game_vehicle_visible',0)");
            db.execSQL("insert into global (key,value) values ('remote_camera_traffic_statistics',0)");
            db.execSQL("insert into global (key,value) values ('ambient_light_panel_visible',0)");
            db.execSQL("insert into global (key,value) values ('sys_day_or_night',0)");
            db.execSQL("insert into global (key,value) values ('sys_screen_mode',0)");
            db.execSQL("insert into global (key,value) values ('bluetooth_key_visible',0)");
            db.execSQL("insert into global (key,value) values ('online_theme_path','')");
            db.execSQL("insert into global (key,value) values ('remote_location','')");
            db.execSQL("insert into global (key,value) values ('is_whole_wallpaper','')");
            db.execSQL("insert into global (key,value) values ('wechat_register_prompt','')");
            db.execSQL("insert into global (key,value) values ('remote_camera_traffic_switch',false)");
            db.execSQL("insert into settings (key,value) values ('aeb',0)");
            db.execSQL("insert into settings (key,value) values ('brake_foot_sense_setting',0)");
            db.execSQL("insert into settings (key,value) values ('def_care_mode',0)");
            db.execSQL("insert into settings (key,value) values ('energy_recycle_setting',0)");
            db.execSQL("insert into settings (key,value) values ('blind_detect',0)");
            db.execSQL("insert into settings (key,value) values ('traffic_signs',0)");
            db.execSQL("insert into settings (key,value) values ('steering_assist',0)");
            db.execSQL("insert into settings (key,value) values ('esp_assist',0)");
            db.execSQL("insert into settings (key,value) values ('avh_assist',0)");
            db.execSQL("insert into settings (key,value) values ('ibooster_assist',0)");
            db.execSQL("insert into settings (key,value) values ('engine_simulator_sound_source',0)");
            db.execSQL("insert into settings (key,value) values ('panorama_auto_switch',0)");
            db.execSQL("insert into settings (key,value) values ('pure_electric_driving_mileage_switch',0)");
            db.execSQL("insert into settings (key,value) values ('ramp_slow_down_switch',0)");
            db.execSQL("insert into settings (key,value) values ('comfortable_parking_switch',0)");
            db.execSQL("insert into settings (key,value) values ('power_management',0)");
            db.execSQL("insert into settings (key,value) values ('auto_parking',0)");
            db.execSQL("insert into settings (key,value) values ('lane_line_assist',0)");
            db.execSQL("insert into settings (key,value) values ('lane_assist_ldws',0)");
            db.execSQL("insert into settings (key,value) values ('lane_keeping',0)");
            db.execSQL("insert into settings (key,value) values ('system_sensitivity',0)");
            db.execSQL("insert into settings (key,value) values ('alarm_mode',0)");
            db.execSQL("insert into settings (key,value) values ('integrated_cruise_assist',0)");
            db.execSQL("insert into settings (key,value) values ('smart_voice_warn_mode',0)");
            db.execSQL("insert into settings (key,value) values ('voice_play_volume_adjustment',0)");
            db.execSQL("insert into settings (key,value) values ('fail_prompt_for_vehicle_start_or_withdrawal',0)");
            db.execSQL("insert into settings (key,value) values ('energy_recycle_setting_visible',0)");
            db.execSQL("insert into settings (key,value) values ('ibooster_assist_visible',0)");
            db.execSQL("insert into settings (key,value) values ('engine_simulator_sound_source_visible',0)");
            db.execSQL("insert into settings (key,value) values ('power_management_visible',0)");
            db.execSQL("insert into settings (key,value) values ('lane_line_assist_visible',0)");
            db.execSQL("insert into settings (key,value) values ('system_sensitivity_visible',0)");
            db.execSQL("insert into settings (key,value) values ('parking_brake_warning_visible',0)");
            db.execSQL("insert into settings (key,value) values ('left_with_p_warning_visible',0)");
            db.execSQL("insert into settings (key,value) values ('lighting_ambient_color',0)");
            db.execSQL("insert into settings (key,value) values ('left_with_p_warning_visible',0)");
            db.execSQL("insert into settings (key,value) values ('left_with_p_warning_visible',0)");
            db.execSQL("insert into settings (key,value) values ('lighting_ambient_color',0)");
            db.execSQL("insert into settings (key,value) values ('lighting_ambient_brightness',0)");
            db.execSQL("insert into settings (key,value) values ('lighting_ambient_field',0)");
            db.execSQL("insert into settings (key,value) values ('lighting_illumination_home_lighting_delay',0)");
            db.execSQL("insert into settings (key,value) values ('lighting_illumination_leave_lighting_delay',0)");
            db.execSQL("insert into settings (key,value) values ('lighting_illumination_incar_lighting_time',0)");
            db.execSQL("insert into settings (key,value) values ('lighting_illumination_courtesy_lighting_time',0)");
            db.execSQL("insert into settings (key,value) values ('lighting_backlight_auto_backlight',0)");
            db.execSQL("insert into settings (key,value) values ('lighting_backlight_brightness',0)");
            db.execSQL("insert into settings (key,value) values ('lighting_backlight_night_brightness',0)");
            db.execSQL("insert into settings (key,value) values ('lighting_backlight_dashboard_auto',0)");
            db.execSQL("insert into settings (key,value) values ('lighting_backlight_dashboard_brightness',0)");
            db.execSQL("insert into settings (key,value) values ('lighting_afs_setting',0)");
            db.execSQL("insert into settings (key,value) values ('lighting_afs_setting_visible',0)");
            db.execSQL("insert into settings (key,value) values ('lighting_hll_setting',0)");
            db.execSQL("insert into settings (key,value) values ('adaptive_driving_beam',0)");
            db.execSQL("insert into settings (key,value) values ('convenience_engine_simulator',0)");
            db.execSQL("insert into settings (key,value) values ('convenience_intelligent_high_beam_lamp',0)");
            db.execSQL("insert into settings (key,value) values ('convenience_driver_desk_chair_aeration',0)");
            db.execSQL("insert into settings (key,value) values ('convenience_front_passenger_desk_chair_aeration',0)");
            db.execSQL("insert into settings (key,value) values ('convenience_driver_desk_chair_heating',0)");
            db.execSQL("insert into settings (key,value) values ('convenience_front_passenger_desk_chair_heating',0)");
            db.execSQL("insert into settings (key,value) values ('convenience_energy_balance_point',0)");
            db.execSQL("insert into settings (key,value) values ('convenience_door_state',0)");
            db.execSQL("insert into settings (key,value) values ('rear_left_seat_ventilating',0)");
            db.execSQL("insert into settings (key,value) values ('rear_left_seat_heating',0)");
            db.execSQL("insert into settings (key,value) values ('rear_right_seat_ventilating',0)");
            db.execSQL("insert into settings (key,value) values ('rear_right_seat_heating',0)");
            db.execSQL("insert into settings (key,value) values ('driver_seat_heating_one',0)");
            db.execSQL("insert into settings (key,value) values ('passenger_seat_heating_one',0)");
            db.execSQL("insert into settings (key,value) values ('rear_ac_panel_lock',0)");
            db.execSQL("insert into settings (key,value) values ('storage_battery_low',0)");
            db.execSQL("insert into settings (key,value) values ('remote_driving_suspended_use',0)");
            db.execSQL("insert into settings (key,value) values ('auto_time',0)");
            db.execSQL("insert into settings (key,value) values ('launch_avo_from_settings',0)");
            db.execSQL("insert into settings (key,value) values ('tachograph_record',0)");
            db.execSQL("insert into settings (key,value) values ('up_down_screen_value',0)");
            db.execSQL("insert into settings (key,value) values ('pad_auto_rise_screen_value',0)");
            db.execSQL("insert into settings (key,value) values ('travel_setting_value',0)");
            db.execSQL("insert into settings (key,value) values ('vehicle_version_diagnosis_value',0)");
            db.execSQL("insert into settings (key,value) values ('text_to_speech_value',0)");
            db.execSQL("insert into settings (key,value) values ('remote_driving_suspended_use_value',0)");
            db.execSQL("insert into settings (key,value) values ('unlock_setting_online_value',0)");
            db.execSQL("insert into settings (key,value) values ('start_or_failure_status',0)");
            db.execSQL("insert into settings (key,value) values ('engine_simulator',0)");
            db.execSQL("insert into settings (key,value) values ('ventilation_heating_seat',0)");
            db.execSQL("insert into settings (key,value) values ('power_type',0)");
            db.execSQL("insert into settings (key,value) values ('carstatus_four_wheel_drive',0)");
            db.execSQL("insert into settings (key,value) values ('self_study_switch',0)");
            db.execSQL("insert into settings (key,value) values ('wind_decrease_for_bt',0)");
            db.execSQL("insert into settings (key,value) values ('auto_circulation_in_tunnel',0)");
            db.execSQL("insert into settings (key,value) values ('auto_circulate_with_parking',0)");
            db.execSQL("insert into settings (key,value) values ('auto_ac_mode',0)");
            db.execSQL("insert into settings (key,value) values ('power_automatic_detection',0)");
            db.execSQL("insert into settings (key,value) values ('door_automatic_detection',0)");
            db.execSQL("insert into settings (key,value) values ('thirty_minutes_automatic_detection',0)");
            db.execSQL("insert into settings (key,value) values ('ac_remote_ctrl_time',0)");
            db.execSQL("insert into settings (key,value) values ('ac_heat_mode',0)");
            db.execSQL("insert into settings (key,value) values ('auto_clear_haze',0)");
            db.execSQL("insert into settings (key,value) values ('car_color',0)");
            db.execSQL("insert into settings (key,value) values ('charge_timer1',0)");
            db.execSQL("insert into settings (key,value) values ('charge_timer2',0)");
            db.execSQL("insert into settings (key,value) values ('charge_timer1_time',0)");
            db.execSQL("insert into settings (key,value) values ('charge_timer2_time',0)");
            db.execSQL("insert into settings (key,value) values ('charge_timer1_cycle_days',0)");
            db.execSQL("insert into settings (key,value) values ('charge_temp_ctl_state',0)");
            db.execSQL("insert into settings (key,value) values ('charge_limit',0)");
            db.execSQL("insert into settings (key,value) values ('charge_limit_settings',0)");
            db.execSQL("insert into settings (key,value) values ('remote_window_up',0)");
            db.execSQL("insert into settings (key,value) values ('remote_window_down',0)");
            db.execSQL("insert into settings (key,value) values ('shut_window_after_locking',0)");
            db.execSQL("insert into settings (key,value) values ('hold_switch_for_window_up',0)");
            db.execSQL("insert into settings (key,value) values ('hold_switch_for_window_down',0)");
            db.execSQL("insert into settings (key,value) values ('backdoor_height',0)");
            db.execSQL("insert into settings (key,value) values ('rain_close_window',0)");
            db.execSQL("insert into settings (key,value) values ('safety_belt_warning',0)");
            db.execSQL("insert into settings (key,value) values ('def_moon_roof',0)");
            db.execSQL("insert into settings (key,value) values ('def_sun_shade_panel',0)");
            db.execSQL("insert into settings (key,value) values ('speeding_atresia',0)");
            db.execSQL("insert into settings (key,value) values ('remote_unlock',0)");
            db.execSQL("insert into settings (key,value) values ('auto_lock_time',0)");
            db.execSQL("insert into settings (key,value) values ('charging_work_mode_setting',0)");
            db.execSQL("insert into settings (key,value) values ('auto_mirror_for_lock',0)");
            db.execSQL("insert into settings (key,value) values ('auto_mirror_for_reverse',0)");
            db.execSQL("insert into settings (key,value) values ('auto_seat_moving_mode',0)");
            db.execSQL("insert into settings (key,value) values ('auto_steering_return',0)");
            db.execSQL("insert into settings (key,value) values ('wind_decrease_for_bt_visible',0)");
            db.execSQL("insert into settings (key,value) values ('auto_circulation_in_tunnel_visible',0)");
            db.execSQL("insert into settings (key,value) values ('auto_circulate_with_parking_visible',0)");
            db.execSQL("insert into settings (key,value) values ('power_automatic_detection_visible',0)");
            db.execSQL("insert into settings (key,value) values ('door_automatic_detection_visible',0)");
            db.execSQL("insert into settings (key,value) values ('thirty_minutes_automatic_detection_visible',0)");
            db.execSQL("insert into settings (key,value) values ('charge_timer_visible',0)");
            db.execSQL("insert into settings (key,value) values ('backdoor_height_visible',0)");
            db.execSQL("insert into settings (key,value) values ('auto_lock_time_visible',0)");
            db.execSQL("insert into settings (key,value) values ('dirac_live_visible',0)");
            db.execSQL("insert into settings (key,value) values ('arkamys_soundstage_visible',0)");
            db.execSQL("insert into settings (key,value) values ('sound_field_high_config_visible',0)");
            db.execSQL("insert into settings (key,value) values ('vehicle_version_upgrade_time',0)");
            db.execSQL("insert into settings (key,value) values ('vehicle_version_upgrade_available',0)");
            db.execSQL("insert into settings (key,value) values ('energy_ac_setup_visible',0)");
            db.execSQL("insert into settings (key,value) values ('switch_user_data',0)");
            db.execSQL("insert into settings (key,value) values ('app_auto_rotation',0)");
            db.execSQL("insert into settings (key,value) values ('rotation_memory_settings',0)");
            db.execSQL("insert into settings (key,value) values ('pad_rotation_value',0)");
            db.execSQL("insert into settings (key,value) values ('custom_key_value',0)");
            db.execSQL("insert into settings (key,value) values ('rear_ac_has_feature_set_wind_level',0)");
            db.execSQL("insert into settings (key,value) values ('rear_ac_has_feature_set_temperature',0)");
            db.execSQL("insert into settings (key,value) values ('rear_ac_has_feature_set_ac',0)");
            db.execSQL("insert into settings (key,value) values ('rear_ac_has_feature_set_automatic',0)");
            db.execSQL("insert into settings (key,value) values ('rear_ac_has_feature_set_wind_mode',0)");
            db.execSQL("insert into settings (key,value) values ('rear_ac_has_feature_set_ac_lock',0)");
            db.execSQL("insert into settings (key,value) values ('rear_ac_has_feature_set_ac_power',0)");
            db.execSQL("insert into settings (key,value) values ('byd_app_theme',0)");
            db.execSQL("insert into settings (key,value) values ('light_theme_on',0)");
            db.execSQL("insert into settings (key,value) values ('classic_byd_app_theme',0)");
            db.execSQL("insert into settings (key,value) values ('wireless_charging',0)");
            db.execSQL("insert into settings (key,value) values ('support_smart_landscape',0)");
            db.execSQL("insert into settings (key,value) values ('time_screen_saver_mode',0)");
            db.execSQL("insert into settings (key,value) values ('vtol_set_time_count',0)");
            db.execSQL("insert into settings (key,value) values ('safety_warning',false)");
            db.execSQL("insert into settings (key,value) values ('predictive_collision_warning',false)");
            db.execSQL("insert into settings (key,value) values ('parking_brake_warning',false)");
            db.execSQL("insert into settings (key,value) values ('door_unclosed_warning',false)");
            db.execSQL("insert into settings (key,value) values ('seat_belt_warning',false)");
            db.execSQL("insert into settings (key,value) values ('left_with_p_warning',false)");
            db.execSQL("insert into settings (key,value) values ('key_low_power_warning',false)");
            db.execSQL("insert into settings (key,value) values ('no_key_warning',false)");
            db.execSQL("insert into settings (key,value) values ('night_light_warning',false)");
            db.execSQL("insert into settings (key,value) values ('low_fuel_warning',false)");
            db.execSQL("insert into settings (key,value) values ('storage_battery_low_warning',false)");
            db.execSQL("insert into settings (key,value) values ('remote_driving_suspended_use_warning',false)");
            db.execSQL("insert into settings (key,value) values ('novice_mode_warning',false)");
            db.execSQL("insert into settings (key,value) values ('quick_acceleration_limited_warning',false)");
            db.execSQL("insert into system_settings (key,value) values ('user_id',0)");
            db.execSQL("insert into system_settings (key,value) values ('current_user_id',0)");
            db.execSQL("insert into system_settings (key,value) values ('panorama_online',0)");
            db.execSQL("insert into system_settings (key,value) values ('go_home_time',0)");
            db.execSQL("insert into system_settings (key,value) values ('leave_home_time',0)");
            db.execSQL("insert into system_settings (key,value) values ('auto_seat_moving_mode_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('auto_steering_return_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('remote_window_up_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('remote_window_down_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('shut_window_after_locking_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('hold_switch_for_window_up_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('shut_window_after_locking_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('hold_switch_for_window_up_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('hold_switch_for_window_down_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('model_value',0)");
            db.execSQL("insert into system_settings (key,value) values ('green_net_value',0)");
            db.execSQL("insert into system_settings (key,value) values ('auto_mirror_for_reverse_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('driver_recorder_value',0)");
            db.execSQL("insert into system_settings (key,value) values ('gear_box_type',0)");
            db.execSQL("insert into system_settings (key,value) values ('parking_brake_type',0)");
            db.execSQL("insert into system_settings (key,value) values ('dual_temp_area_ac_online',0)");
            db.execSQL("insert into system_settings (key,value) values ('panorama_sun_roof_online_value',0)");
            db.execSQL("insert into system_settings (key,value) values ('atmosphere_lamp',0)");
            db.execSQL("insert into system_settings (key,value) values ('speeding_atresia_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('predictive_collision_warning_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('aeb_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('blind_detect_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('traffic_signs_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('alarm_mode_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('intelligent_far_near_light',0)");
            db.execSQL("insert into system_settings (key,value) values ('carstatus_front_wiper_inspection',0)");
            db.execSQL("insert into system_settings (key,value) values ('carstatus_after_wiper_inspection',0)");
            db.execSQL("insert into system_settings (key,value) values ('electric_back_door_value',0)");
            db.execSQL("insert into system_settings (key,value) values ('auto_mirror_for_lock_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('steering_assist_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('oil_detection',0)");
            db.execSQL("insert into system_settings (key,value) values ('esp_assist_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('avh_assist_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('soc_setting',0)");
            db.execSQL("insert into system_settings (key,value) values ('unit_power_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('auto_ac_mode_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('lane_assist_ldws_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('rear_ac_max_wind_level',0)");
            db.execSQL("insert into system_settings (key,value) values ('ac_remote_ctrl_time_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('door_state_online',0)");
            db.execSQL("insert into system_settings (key,value) values ('driver_seat_ventilating_online',0)");
            db.execSQL("insert into system_settings (key,value) values ('driver_seat_heating_online',0)");
            db.execSQL("insert into system_settings (key,value) values ('front_seat_ventilating_online',0)");
            db.execSQL("insert into system_settings (key,value) values ('front_seat_heating_online',0)");
            db.execSQL("insert into system_settings (key,value) values ('rear_left_seat_ventilating_online',0)");
            db.execSQL("insert into system_settings (key,value) values ('rear_left_seat_heating_online',0)");
            db.execSQL("insert into system_settings (key,value) values ('rear_right_seat_ventilating_online',0)");
            db.execSQL("insert into system_settings (key,value) values ('rear_right_seat_heating_online',0)");
            db.execSQL("insert into system_settings (key,value) values ('panorama_auto_switch_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('unit_air_pressure_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('system_unit_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('carstatus_diagnosis',0)");
            db.execSQL("insert into system_settings (key,value) values ('pure_electric_driving_mileage_switch_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('driver_seat_heating_one_online',0)");
            db.execSQL("insert into system_settings (key,value) values ('passenger_seat_heating_one_online',0)");
            db.execSQL("insert into system_settings (key,value) values ('ac_type',0)");
            db.execSQL("insert into system_settings (key,value) values ('wireless_charging_online',0)");
            db.execSQL("insert into system_settings (key,value) values ('unit_temperature_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('lane_keeping_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('integrated_cruise_assist_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('indirect_tire_pressure_detection',0)");
            db.execSQL("insert into system_settings (key,value) values ('camera_gallery_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('wind_mode_number',0)");
            db.execSQL("insert into system_settings (key,value) values ('lighting_backlight_dashboard_brightness_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('unit_distance_fuel_consumption_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('auto_wiper_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('rain_close_window_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('charge_temp_ctl_state_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('car_type_el_vin_value',0)");
            db.execSQL("insert into system_settings (key,value) values ('brake_foot_sense_setting_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('ac_heat_mode_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('lighting_hll_setting_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('comfortable_parking_switch_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('safety_belt_warning_visivle',0)");
            db.execSQL("insert into system_settings (key,value) values ('ramp_slow_down_switch_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('adaptive_driving_beam_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('charge_limit_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('rear_ac_panel_lock_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('remote_screenage_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('pm25_channel_type_value',0)");
            db.execSQL("insert into system_settings (key,value) values ('daytime_running_light_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('auto_parking_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('dirac_live_mode_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('dirac_live_performance_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('remote_unlock_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('def_care_mode_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('smart_welcome_light_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('car_discharge_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('ac_auto_lock_rear_panel_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('lighting_ambient_brightness_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('handle_auto_reset_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('charging_work_mode_setting_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('soc_save_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('vehicle_velocity_of_reminding_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('energy_display_type_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('oil_life_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('prefer_oil_or_electricity_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('vtol_set_time_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('sport_mode_memory_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('max_ev_mode_memory_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('save_function_memory_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('charge_smart_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('dyn_audio_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('driver_monitor_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('pre_sales_mode_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('door_lock_child_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('door_lock_window_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('lantern_language_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('music_mode_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('smart_power_mode_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('ditrainer_mode_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('nfc_key_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('ambient_lamp_multi_color_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('ac_compress_heating_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('dynamic_suspension_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('ac_perfume_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('dynamic_suspension_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('endurance_mileage_standard_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('at_fluit_life_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('battery_coolant_life_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('motor_coolant_life_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('brake_fluid_life_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('urgent_lane_assist_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('highway_navigation_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('interactive_lane_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('urban_navigation_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('lane_deviate_assist_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('front_traffic_crossing_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('front_traffic_brake_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('traffic_light_attention_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('door_open_warning_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('rear_traffic_crossing_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('rear_traffic_brake_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('rear_collision_warning_visible',0)");
            db.execSQL("insert into system_settings (key,value) values ('car_roof_mode',0)");
            db.execSQL("insert into system_settings (key,value) values ('low_fuel',false)");
        }
    }
}

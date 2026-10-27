package com.android.systemui.qs;

import com.android.systemui.Constants;
import com.android.systemui.R;
import com.android.systemui.SystemUIApplication;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class QSIconConstants {
    public static HashMap<String, Integer> icon = new HashMap<>();
    public static HashMap<String, String> tileId = new HashMap<>();
    public static HashMap<String, String> tileName = new HashMap<>();

    public static void initQSIcon() {
        icon.clear();
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_reading_light), Integer.valueOf(R.mipmap.icon_control_reading_light));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_hud), Integer.valueOf(R.mipmap.icon_control_hud));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_driver_monitor), Integer.valueOf(R.mipmap.icon_control_dms));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_electronic_parking_brake), Integer.valueOf(R.mipmap.icon_control_electronic_parking_brake));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_active_suspension_mode), Integer.valueOf(R.mipmap.icon_control_suspension_mode_comfort));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_massage), Integer.valueOf(R.mipmap.icon_control_seat_massage));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_ventilation_heating), Integer.valueOf(R.mipmap.icon_ventilation_heating));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_auto_hold), Integer.valueOf(R.mipmap.icon_light_control_auto_park));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_electric_defrosting), Integer.valueOf(R.mipmap.icon_electric_defrosting));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_trunk_open), Integer.valueOf(R.mipmap.icon_control_rear_door_open));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_skylight), Integer.valueOf(R.mipmap.icon_control_skylight));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_window_lock), Integer.valueOf(R.mipmap.icon_control_lock_window));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_left_child_lock), Integer.valueOf(R.mipmap.icon_control_child_lock_left_back));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_right_child_lock), Integer.valueOf(R.mipmap.icon_control_child_lock_right_back));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_rear_control_lock), Integer.valueOf(R.mipmap.icon_control_rear_lock));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_energy_feedback), Integer.valueOf(R.mipmap.icon_control_energy_feedback_standard));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_steering_wheel_heating), Integer.valueOf(R.mipmap.icon_control_steering_wheel_heating));
        HashMap<String, Integer> map = icon;
        String string = SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_wireless_charging);
        Integer numValueOf = Integer.valueOf(R.mipmap.icon_control_wireless_charging);
        map.put(string, numValueOf);
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_wireless_charging_l), numValueOf);
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_wireless_charging_r), numValueOf);
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_engine_sound_simulator), Integer.valueOf(R.mipmap.icon_control_engine_sound_simulation));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_door_light_auto), Integer.valueOf(R.mipmap.icon_control_door));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_hma), Integer.valueOf(R.mipmap.icon_center_automatic_headlights));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_interior_light_on), Integer.valueOf(R.mipmap.icon_control_indoor_light_switch_open));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_interior_light_off), Integer.valueOf(R.mipmap.icon_control_indoor_light_switch_close));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_esp_off), Integer.valueOf(R.mipmap.icon_control_esp));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_reversing_radar), Integer.valueOf(R.mipmap.icon_control_reversing_radar));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_auto_hold), Integer.valueOf(R.mipmap.icon_control_auto_park));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_hill_descent_control), Integer.valueOf(R.mipmap.icon_control_steep_slope));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_auto_parking), Integer.valueOf(R.mipmap.icon_control_parking_assistant));
        HashMap<String, Integer> map2 = icon;
        String string2 = SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_lka);
        Integer numValueOf2 = Integer.valueOf(R.mipmap.icon_control_lane_departure_warning);
        map2.put(string2, numValueOf2);
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_bsd), Integer.valueOf(R.mipmap.icon_control_blind_spot_monitoring));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_tsr), Integer.valueOf(R.mipmap.icon_control_traffic_recognition));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_icc), Integer.valueOf(R.mipmap.icon_control_acc));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_coach_mode), Integer.valueOf(R.mipmap.icon_control_care));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_smart_caring), Integer.valueOf(R.mipmap.icon_control_coach));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_new_energy), Integer.valueOf(R.mipmap.icon_new_energy));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_reverse_sensor), Integer.valueOf(R.mipmap.icon_control_driving_recorder));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_digital_panel), Integer.valueOf(R.mipmap.icon_control_digital_panel));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_karaoke), Integer.valueOf(R.mipmap.icon_control_song));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_sound), Integer.valueOf(R.mipmap.icon_control_audio));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_game_center), Integer.valueOf(R.mipmap.icon_control_game));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.transport_mode), Integer.valueOf(R.mipmap.icon_control_transport_mode));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_cpd), Integer.valueOf(R.mipmap.icon_control_cpd));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_slw), Integer.valueOf(R.mipmap.icon_tsr_slw_close));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_la), numValueOf2);
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.dialog_title_rest), Integer.valueOf(R.mipmap.icon_scene_respite));
        icon.put(SystemUIApplication.mSystemUIApplication.getString(R.string.dialog_title_black), Integer.valueOf(R.mipmap.icon_scene_wipe));
        initTileId();
        initTileName();
    }

    public static void initTileId() {
        tileId.clear();
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.qs_title_wifi), Constants.TileId.LINK_ID_0);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.qs_title_bluetooth), Constants.TileId.LINK_ID_1);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.qs_title_dark_mode), Constants.TileId.LINK_ID_2);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.qs_title_light_mode), Constants.TileId.LINK_ID_3);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.qs_title_auto_mode), Constants.TileId.LINK_ID_4);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.qs_title_rest_screen), Constants.TileId.LINK_ID_5);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.qs_title_automatic_rotation), Constants.TileId.LINK_ID_6);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.qs_title_telephone_data), Constants.TileId.LINK_ID_7);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.qs_title_mute), Constants.TileId.LINK_ID_8);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.qs_title_hot_spot), Constants.TileId.LINK_ID_9);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.qs_title_screen_shot), Constants.TileId.LINK_ID_10);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.qs_title_data_switch), Constants.TileId.LINK_ID_11);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.qs_title_privacy), Constants.TileId.LINK_ID_12);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.qs_title_device_attachment), Constants.TileId.LINK_ID_13);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_reading_light), Constants.TileId.CAR_ID_0);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_hud), Constants.TileId.CAR_ID_1);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_driver_monitor), Constants.TileId.CAR_ID_2);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_electronic_parking_brake), Constants.TileId.CAR_ID_3);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_active_suspension_mode), Constants.TileId.CAR_ID_4);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_massage), Constants.TileId.CAR_ID_5);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_ventilation_heating), Constants.TileId.CAR_ID_6);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_electric_defrosting), Constants.TileId.CAR_ID_7);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_trunk_open), Constants.TileId.CAR_ID_8);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_skylight), Constants.TileId.CAR_ID_9);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_window_lock), Constants.TileId.CAR_ID_10);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_left_child_lock), Constants.TileId.CAR_ID_11);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_right_child_lock), Constants.TileId.CAR_ID_12);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_rear_control_lock), Constants.TileId.CAR_ID_13);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_energy_feedback), Constants.TileId.CAR_ID_14);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_steering_wheel_heating), Constants.TileId.CAR_ID_15);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_wireless_charging), Constants.TileId.CAR_ID_16);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_wireless_charging_l), Constants.TileId.CAR_ID_40);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_wireless_charging_r), Constants.TileId.CAR_ID_41);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_engine_sound_simulator), Constants.TileId.CAR_ID_17);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_door_light_auto), Constants.TileId.CAR_ID_18);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_hma), Constants.TileId.CAR_ID_19);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_interior_light_on), Constants.TileId.CAR_ID_20);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_interior_light_off), Constants.TileId.CAR_ID_21);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_esp_off), Constants.TileId.CAR_ID_22);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_reversing_radar), Constants.TileId.CAR_ID_23);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_auto_hold), Constants.TileId.CAR_ID_24);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_hill_descent_control), Constants.TileId.CAR_ID_25);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_auto_parking), Constants.TileId.CAR_ID_26);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_lka), Constants.TileId.CAR_ID_27);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_bsd), Constants.TileId.CAR_ID_28);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_tsr), Constants.TileId.CAR_ID_29);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_icc), Constants.TileId.CAR_ID_30);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_coach_mode), Constants.TileId.CAR_ID_31);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_smart_caring), Constants.TileId.CAR_ID_32);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_new_energy), Constants.TileId.CAR_ID_33);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_reverse_sensor), Constants.TileId.CAR_ID_34);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_digital_panel), Constants.TileId.CAR_ID_35);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_karaoke), Constants.TileId.CAR_ID_36);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_sound), Constants.TileId.CAR_ID_37);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_game_center), Constants.TileId.CAR_ID_38);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.transport_mode), Constants.TileId.CAR_ID_39);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_cpd), Constants.TileId.CAR_ID_42);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_slw), Constants.TileId.CAR_ID_43);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.car_title_la), Constants.TileId.CAR_ID_44);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.dialog_title_rest), Constants.TileId.SCENE_ID_3);
        tileId.put(SystemUIApplication.mSystemUIApplication.getString(R.string.dialog_title_black), Constants.TileId.SCENE_ID_5);
    }

    private static void initTileName() {
        tileName.clear();
        for (String str : tileId.keySet()) {
            tileName.put(tileId.get(str), str);
        }
    }
}

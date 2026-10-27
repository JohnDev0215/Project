package com.byd.vrassistant.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.byd.vrassistant.R;

/* JADX INFO: loaded from: classes.dex */
public class ActivityAcBindingImpl extends ActivityAcBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ScrollView mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, Object obj) {
        return true;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.back, 1);
        sparseIntArray.put(R.id.rotate, 2);
        sparseIntArray.put(R.id.tv_power_level, 3);
        sparseIntArray.put(R.id.tv_ac_online_state, 4);
        sparseIntArray.put(R.id.tv_ac_temperature_control_mode, 5);
        sparseIntArray.put(R.id.tv_rear_ac_online_state, 6);
        sparseIntArray.put(R.id.tv_system_int, 7);
        sparseIntArray.put(R.id.tv_ac_start_state, 8);
        sparseIntArray.put(R.id.tv_rear_ac_start_state, 9);
        sparseIntArray.put(R.id.tv_start, 10);
        sparseIntArray.put(R.id.tv_start_rear_ac, 11);
        sparseIntArray.put(R.id.tv_stop, 12);
        sparseIntArray.put(R.id.tv_stop_rear_ac, 13);
        sparseIntArray.put(R.id.tv_temprature, 14);
        sparseIntArray.put(R.id.tv_set_ac_temperature, 15);
        sparseIntArray.put(R.id.tv_ac_wind_level, 16);
        sparseIntArray.put(R.id.tv_rear_ac_wind_level, 17);
        sparseIntArray.put(R.id.tv_set_ac_wind_level, 18);
        sparseIntArray.put(R.id.tv_set_rear_ac_wind_level, 19);
        sparseIntArray.put(R.id.tv_get_fan_mode_num, 20);
        sparseIntArray.put(R.id.tv_ac_wind_mode, 21);
        sparseIntArray.put(R.id.tv_ac_rear_wind_mode, 22);
        sparseIntArray.put(R.id.tv_set_ac_wind_mode, 23);
        sparseIntArray.put(R.id.tv_set_ac_rear_wind_mode, 24);
        sparseIntArray.put(R.id.tv_set_ac_temperature_control_mode, 25);
        sparseIntArray.put(R.id.tv_ac_cycle_mode, 26);
        sparseIntArray.put(R.id.tv_set_ac_cycle_mode, 27);
        sparseIntArray.put(R.id.tv_ac_compressor_mode, 28);
        sparseIntArray.put(R.id.tv_set_ac_compressor_mode, 29);
        sparseIntArray.put(R.id.tv_ac_ventilation_state, 30);
        sparseIntArray.put(R.id.tv_set_ac_ventilation_state, 31);
        sparseIntArray.put(R.id.tv_ac_defrost_state, 32);
        sparseIntArray.put(R.id.tv_set_ac_defrost_state, 33);
        sparseIntArray.put(R.id.tv_ac_type, 34);
        sparseIntArray.put(R.id.tv_set_ac_control_mode, 35);
        sparseIntArray.put(R.id.tv_set_rear_ac_control_mode, 36);
        sparseIntArray.put(R.id.tv_is_division_single, 37);
        sparseIntArray.put(R.id.tv_warm_online_state, 38);
        sparseIntArray.put(R.id.tv_voice_ctl_moon_roof, 39);
        sparseIntArray.put(R.id.tv_voice_ctl_sunshade_panel, 40);
        sparseIntArray.put(R.id.tv_window_state, 41);
        sparseIntArray.put(R.id.tv_window_open_percent, 42);
        sparseIntArray.put(R.id.tv_set_all_window_state, 43);
        sparseIntArray.put(R.id.tv_set_body_window_ctrl_state, 44);
        sparseIntArray.put(R.id.tv_has_feature_seat_ventilating_heating, 45);
        sparseIntArray.put(R.id.tv_seat_ventilating_state, 46);
        sparseIntArray.put(R.id.tv_set_seat_ventilating_state, 47);
        sparseIntArray.put(R.id.tv_seat_heating_state, 48);
        sparseIntArray.put(R.id.tv_set_seat_heating_state, 49);
        sparseIntArray.put(R.id.tv_support_ambient_light, 50);
        sparseIntArray.put(R.id.tv_ial_brightness, 51);
        sparseIntArray.put(R.id.tv_set_ial_brightness, 52);
        sparseIntArray.put(R.id.tv_ial_info, 53);
        sparseIntArray.put(R.id.tv_set_ial_info, 54);
        sparseIntArray.put(R.id.tv_open_system_setting, 55);
        sparseIntArray.put(R.id.tv_backlight_brightness, 56);
        sparseIntArray.put(R.id.tv_set_backlight_brightness, 57);
        sparseIntArray.put(R.id.tv_is_support_eco_and_sport, 58);
        sparseIntArray.put(R.id.tv_operation_mode, 59);
        sparseIntArray.put(R.id.tv_set_operation_mode, 60);
        sparseIntArray.put(R.id.tv_screen_brightness, 61);
        sparseIntArray.put(R.id.tv_set_screen_brightness, 62);
        sparseIntArray.put(R.id.tv_set_Stream_Mute, 63);
        sparseIntArray.put(R.id.tv_set_stream_volume, 64);
        sparseIntArray.put(R.id.tv_current_system_mode, 65);
        sparseIntArray.put(R.id.tv_set_current_system_mode, 66);
        sparseIntArray.put(R.id.tv_ac_max_cooling_state, 67);
        sparseIntArray.put(R.id.tv_tv_open_wifi_setting, 68);
        sparseIntArray.put(R.id.tv_tv_open_hot_spot_setting, 69);
        sparseIntArray.put(R.id.tv_tv_open_bluetooth_setting, 70);
        sparseIntArray.put(R.id.tv_tv_open_vehicle_image, 71);
        sparseIntArray.put(R.id.tv_open_bt_phone, 72);
        sparseIntArray.put(R.id.tv_open_bt_music, 73);
        sparseIntArray.put(R.id.tv_open_camera, 74);
        sparseIntArray.put(R.id.tv_open_photo, 75);
        sparseIntArray.put(R.id.tv_open_carsettings, 76);
        sparseIntArray.put(R.id.tv_open_filemanager, 77);
        sparseIntArray.put(R.id.tv_open_androidauto, 78);
        sparseIntArray.put(R.id.tv_open_carplay, 79);
        sparseIntArray.put(R.id.tv_open_spotify, 80);
        sparseIntArray.put(R.id.tv_open_navi, 81);
        sparseIntArray.put(R.id.tv_valide_camera, 82);
    }

    public ActivityAcBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 83, sIncludes, sViewsWithIds));
    }

    private ActivityAcBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[1], (TextView) objArr[2], (TextView) objArr[28], (TextView) objArr[26], (TextView) objArr[32], (TextView) objArr[67], (TextView) objArr[4], (TextView) objArr[22], (TextView) objArr[8], (TextView) objArr[5], (TextView) objArr[34], (TextView) objArr[30], (TextView) objArr[16], (TextView) objArr[21], (TextView) objArr[56], (TextView) objArr[65], (TextView) objArr[20], (TextView) objArr[45], (TextView) objArr[51], (TextView) objArr[53], (TextView) objArr[37], (TextView) objArr[58], (TextView) objArr[78], (TextView) objArr[73], (TextView) objArr[72], (TextView) objArr[74], (TextView) objArr[79], (TextView) objArr[76], (TextView) objArr[77], (TextView) objArr[81], (TextView) objArr[75], (TextView) objArr[80], (TextView) objArr[55], (TextView) objArr[59], (TextView) objArr[3], (TextView) objArr[6], (TextView) objArr[9], (TextView) objArr[17], (TextView) objArr[61], (TextView) objArr[48], (TextView) objArr[46], (TextView) objArr[29], (TextView) objArr[35], (TextView) objArr[27], (TextView) objArr[33], (TextView) objArr[24], (TextView) objArr[15], (TextView) objArr[25], (TextView) objArr[31], (TextView) objArr[18], (TextView) objArr[23], (TextView) objArr[43], (TextView) objArr[57], (TextView) objArr[44], (TextView) objArr[66], (TextView) objArr[52], (TextView) objArr[54], (TextView) objArr[60], (TextView) objArr[36], (TextView) objArr[19], (TextView) objArr[62], (TextView) objArr[49], (TextView) objArr[47], (TextView) objArr[63], (TextView) objArr[64], (TextView) objArr[10], (TextView) objArr[11], (TextView) objArr[12], (TextView) objArr[13], (TextView) objArr[50], (TextView) objArr[7], (TextView) objArr[14], (TextView) objArr[70], (TextView) objArr[69], (TextView) objArr[71], (TextView) objArr[68], (TextView) objArr[82], (TextView) objArr[39], (TextView) objArr[40], (TextView) objArr[38], (TextView) objArr[42], (TextView) objArr[41]);
        this.mDirtyFlags = -1L;
        ScrollView scrollView = (ScrollView) objArr[0];
        this.mboundView0 = scrollView;
        scrollView.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0L;
        }
    }
}

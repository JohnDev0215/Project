package com.android.car.developeroptions.fuelgauge.batterysaver;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import com.android.settingslib.fuelgauge.BatterySaverUtils;

/* JADX INFO: loaded from: classes.dex */
public class BatterySaverScheduleRadioButtonsController {
    private Context mContext;
    private BatterySaverScheduleSeekBarController mSeekBarController;

    public BatterySaverScheduleRadioButtonsController(Context context, BatterySaverScheduleSeekBarController batterySaverScheduleSeekBarController) {
        this.mContext = context;
        this.mSeekBarController = batterySaverScheduleSeekBarController;
    }

    public String getDefaultKey() {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        return Settings.Global.getInt(contentResolver, "automatic_power_save_mode", 0) == 0 ? Settings.Global.getInt(contentResolver, "low_power_trigger_level", 0) <= 0 ? "key_battery_saver_no_schedule" : "key_battery_saver_percentage" : "key_battery_saver_routine";
    }

    public boolean setDefaultKey(String str) {
        int i;
        int i2;
        int i3 = 0;
        if (str == null) {
            return false;
        }
        ContentResolver contentResolver = this.mContext.getContentResolver();
        Bundle bundle = new Bundle(3);
        byte b = -1;
        int iHashCode = str.hashCode();
        if (iHashCode != 188396680) {
            if (iHashCode != 225734937) {
                if (iHashCode == 1335810134 && str.equals("key_battery_saver_percentage")) {
                    b = 1;
                }
            } else if (str.equals("key_battery_saver_no_schedule")) {
                b = 0;
            }
        } else if (str.equals("key_battery_saver_routine")) {
            b = 2;
        }
        if (b == 0) {
            i = 0;
            i2 = 0;
        } else if (b == 1) {
            i = 5;
            bundle.putBoolean("extra_confirm_only", true);
            bundle.putInt("extra_power_save_mode_trigger", 0);
            bundle.putInt("extra_power_save_mode_trigger_level", 5);
            i2 = 0;
        } else if (b == 2) {
            bundle.putBoolean("extra_confirm_only", true);
            bundle.putInt("extra_power_save_mode_trigger", 1);
            i = 0;
            i2 = 1;
        } else {
            throw new IllegalStateException("Not a valid key for " + BatterySaverScheduleRadioButtonsController.class.getSimpleName());
        }
        if (TextUtils.equals(str, "key_battery_saver_no_schedule") || !BatterySaverUtils.maybeShowBatterySaverConfirmation(this.mContext, bundle)) {
            i3 = i2;
        } else {
            i = 0;
        }
        Settings.Global.putInt(contentResolver, "automatic_power_save_mode", i3);
        if (i3 != 1) {
            Settings.Global.putInt(contentResolver, "low_power_trigger_level", i);
        }
        this.mSeekBarController.updateSeekBar();
        return true;
    }
}

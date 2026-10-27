package com.android.car.developeroptions.fuelgauge.batterysaver;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.widget.SeekBarPreference;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settingslib.Utils;

/* JADX INFO: loaded from: classes.dex */
public class BatterySaverScheduleSeekBarController implements Preference.OnPreferenceChangeListener {
    private Context mContext;

    @VisibleForTesting
    public SeekBarPreference mSeekBarPreference;

    public BatterySaverScheduleSeekBarController(Context context) {
        this.mContext = context;
        SeekBarPreference seekBarPreference = new SeekBarPreference(context);
        this.mSeekBarPreference = seekBarPreference;
        seekBarPreference.setOnPreferenceChangeListener(this);
        this.mSeekBarPreference.setContinuousUpdates(true);
        this.mSeekBarPreference.setMax(15);
        this.mSeekBarPreference.setMin(1);
        this.mSeekBarPreference.setKey("battery_saver_seek_bar");
        updateSeekBar();
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        int iIntValue = ((Integer) obj).intValue() * 5;
        Settings.Global.putInt(this.mContext.getContentResolver(), "low_power_trigger_level", iIntValue);
        preference.setTitle(this.mContext.getString(R.string.battery_saver_seekbar_title, Utils.formatPercentage(iIntValue)));
        return true;
    }

    public void updateSeekBar() {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        if (Settings.Global.getInt(contentResolver, "automatic_power_save_mode", 0) == 0) {
            int i = Settings.Global.getInt(contentResolver, "low_power_trigger_level", 0);
            if (i <= 0) {
                this.mSeekBarPreference.setVisible(false);
                return;
            }
            int iMax = Math.max(i / 5, 1);
            this.mSeekBarPreference.setVisible(true);
            this.mSeekBarPreference.setProgress(iMax);
            this.mSeekBarPreference.setTitle(this.mContext.getString(R.string.battery_saver_seekbar_title, Utils.formatPercentage(iMax * 5)));
            return;
        }
        this.mSeekBarPreference.setVisible(false);
    }

    public void addToScreen(PreferenceScreen preferenceScreen) {
        preferenceScreen.addPreference(this.mSeekBarPreference);
    }
}

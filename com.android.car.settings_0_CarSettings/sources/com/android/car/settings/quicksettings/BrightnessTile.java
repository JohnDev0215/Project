package com.android.car.settings.quicksettings;

import android.app.ActivityManager;
import android.content.Context;
import android.os.PowerManager;
import android.provider.Settings;
import android.widget.SeekBar;
import com.android.car.settings.common.Logger;
import com.android.car.settings.quicksettings.QuickSettingGridAdapter;
import com.android.settingslib.display.BrightnessUtils;

/* JADX INFO: loaded from: classes5.dex */
public class BrightnessTile implements QuickSettingGridAdapter.SeekbarTile {
    private static final Logger LOG = new Logger((Class<?>) BrightnessTile.class);
    private final Context mContext;
    private final int mMaximumBacklight;
    private final int mMinimumBacklight;

    public BrightnessTile(Context context) {
        this.mContext = context;
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        this.mMaximumBacklight = powerManager.getMaximumScreenBrightnessSetting();
        this.mMinimumBacklight = powerManager.getMinimumScreenBrightnessSetting();
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int gamma, boolean fromUser) {
        int linear = BrightnessUtils.convertGammaToLinear(gamma, this.mMinimumBacklight, this.mMaximumBacklight);
        Settings.System.putIntForUser(this.mContext.getContentResolver(), "screen_brightness", linear, ActivityManager.getCurrentUser());
    }

    @Override // com.android.car.settings.quicksettings.QuickSettingGridAdapter.SeekbarTile
    public int getMax() {
        return 65535;
    }

    @Override // com.android.car.settings.quicksettings.QuickSettingGridAdapter.SeekbarTile
    public void start() {
    }

    @Override // com.android.car.settings.quicksettings.QuickSettingGridAdapter.SeekbarTile
    public void stop() {
    }

    @Override // com.android.car.settings.quicksettings.QuickSettingGridAdapter.SeekbarTile
    public int getCurrent() {
        try {
            int linear = Settings.System.getIntForUser(this.mContext.getContentResolver(), "screen_brightness", ActivityManager.getCurrentUser());
            int gamma = BrightnessUtils.convertLinearToGamma(linear, this.mMinimumBacklight, this.mMaximumBacklight);
            return gamma;
        } catch (Settings.SettingNotFoundException e) {
            LOG.w("Can't find setting for SCREEN_BRIGHTNESS.");
            return 65535;
        }
    }
}

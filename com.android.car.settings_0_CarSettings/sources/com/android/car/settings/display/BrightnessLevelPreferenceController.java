package com.android.car.settings.display;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.os.PowerManager;
import android.os.UserHandle;
import android.provider.Settings;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceController;
import com.android.car.settings.common.SeekBarPreference;
import com.android.settingslib.display.BrightnessUtils;

/* JADX INFO: loaded from: classes5.dex */
public class BrightnessLevelPreferenceController extends PreferenceController<SeekBarPreference> {
    private static final Logger LOG = new Logger((Class<?>) BrightnessLevelPreferenceController.class);
    private final int mMaximumBacklight;
    private final int mMinimumBacklight;

    public BrightnessLevelPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        this.mMaximumBacklight = powerManager.getMaximumScreenBrightnessSetting();
        this.mMinimumBacklight = powerManager.getMinimumScreenBrightnessSetting();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<SeekBarPreference> getPreferenceType() {
        return SeekBarPreference.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(SeekBarPreference preference) {
        preference.setMax(65535);
        preference.setValue(getSeekbarValue());
        preference.setContinuousUpdate(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public boolean handlePreferenceChanged(SeekBarPreference preference, Object newValue) {
        int gamma = ((Integer) newValue).intValue();
        int linear = BrightnessUtils.convertGammaToLinear(gamma, this.mMinimumBacklight, this.mMaximumBacklight);
        Settings.System.putIntForUser(getContext().getContentResolver(), "screen_brightness", linear, UserHandle.myUserId());
        return true;
    }

    private int getSeekbarValue() {
        try {
            int linear = Settings.System.getIntForUser(getContext().getContentResolver(), "screen_brightness", UserHandle.myUserId());
            int gamma = BrightnessUtils.convertLinearToGamma(linear, this.mMinimumBacklight, this.mMaximumBacklight);
            return gamma;
        } catch (Settings.SettingNotFoundException e) {
            LOG.w("Can't find setting for SCREEN_BRIGHTNESS.");
            return 65535;
        }
    }
}

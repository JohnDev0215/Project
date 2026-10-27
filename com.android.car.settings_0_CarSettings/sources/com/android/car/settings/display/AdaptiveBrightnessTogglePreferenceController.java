package com.android.car.settings.display;

import android.car.drivingstate.CarUxRestrictions;
import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import androidx.preference.TwoStatePreference;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;

/* JADX INFO: loaded from: classes5.dex */
public class AdaptiveBrightnessTogglePreferenceController extends PreferenceController<TwoStatePreference> {
    public AdaptiveBrightnessTogglePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<TwoStatePreference> getPreferenceType() {
        return TwoStatePreference.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(TwoStatePreference preference) {
        preference.setChecked(isAdaptiveBrightnessChecked());
    }

    @Override // com.android.car.settings.common.PreferenceController
    public int getAvailabilityStatus() {
        return supportsAdaptiveBrightness() ? 0 : 2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public boolean handlePreferenceChanged(TwoStatePreference preference, Object newValue) {
        int i;
        boolean enableAdaptiveBrightness = ((Boolean) newValue).booleanValue();
        ContentResolver contentResolver = getContext().getContentResolver();
        if (enableAdaptiveBrightness) {
            i = 1;
        } else {
            i = 0;
        }
        Settings.System.putInt(contentResolver, "screen_brightness_mode", i);
        return true;
    }

    private boolean isAdaptiveBrightnessChecked() {
        int brightnessMode = Settings.System.getInt(getContext().getContentResolver(), "screen_brightness_mode", 0);
        return brightnessMode != 0;
    }

    private boolean supportsAdaptiveBrightness() {
        return getContext().getResources().getBoolean(R.bool.config_automatic_brightness_available);
    }
}

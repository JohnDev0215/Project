package com.android.car.developeroptions.display;

import android.R;
import android.content.Context;
import androidx.preference.Preference;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.dream.DreamSettings;
import com.android.settingslib.core.AbstractPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class ScreenSaverPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "screensaver";
    }

    public ScreenSaverPreferenceController(Context context) {
        super(context);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return this.mContext.getResources().getBoolean(R.bool.config_cameraDoubleTapPowerGestureEnabled);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        preference.setSummary(DreamSettings.getSummaryTextWithDreamName(this.mContext));
    }
}

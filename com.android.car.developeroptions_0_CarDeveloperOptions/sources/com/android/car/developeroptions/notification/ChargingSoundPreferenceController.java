package com.android.car.developeroptions.notification;

import android.content.Context;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.settingslib.core.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public class ChargingSoundPreferenceController extends SettingPrefController {
    public ChargingSoundPreferenceController(Context context, SettingsPreferenceFragment settingsPreferenceFragment, Lifecycle lifecycle) {
        super(context, settingsPreferenceFragment, lifecycle);
        this.mPreference = new SettingPref(3, "charging_sounds", "charging_sounds_enabled", 1, new int[0]);
    }

    @Override // com.android.car.developeroptions.notification.SettingPrefController, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return this.mContext.getResources().getBoolean(R.bool.config_show_charging_sounds);
    }
}

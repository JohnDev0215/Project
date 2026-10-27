package com.android.car.developeroptions.notification;

import android.content.Context;
import androidx.preference.Preference;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.notification.ZenModeSettings;
import com.android.settingslib.core.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public class ZenModeCallsPreferenceController extends AbstractZenModePreferenceController implements PreferenceControllerMixin {
    private final String KEY_BEHAVIOR_SETTINGS;
    private final ZenModeSettings.SummaryBuilder mSummaryBuilder;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public ZenModeCallsPreferenceController(Context context, Lifecycle lifecycle, String str) {
        super(context, str, lifecycle);
        this.KEY_BEHAVIOR_SETTINGS = str;
        this.mSummaryBuilder = new ZenModeSettings.SummaryBuilder(context);
    }

    @Override // com.android.car.developeroptions.notification.AbstractZenModePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return this.KEY_BEHAVIOR_SETTINGS;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        preference.setSummary(this.mSummaryBuilder.getCallsSettingSummary(getPolicy()));
    }
}

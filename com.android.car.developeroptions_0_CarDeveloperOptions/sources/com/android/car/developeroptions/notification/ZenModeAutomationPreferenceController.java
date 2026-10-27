package com.android.car.developeroptions.notification;

import android.content.Context;
import androidx.preference.Preference;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.notification.ZenModeSettings;
import com.android.settingslib.core.AbstractPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class ZenModeAutomationPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    private final ZenModeSettings.SummaryBuilder mSummaryBuilder;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "zen_mode_automation_settings";
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public ZenModeAutomationPreferenceController(Context context) {
        super(context);
        this.mSummaryBuilder = new ZenModeSettings.SummaryBuilder(context);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        preference.setSummary(this.mSummaryBuilder.getAutomaticRulesSummary());
    }
}

package com.android.car.developeroptions.notification;

import android.content.Context;
import android.os.Bundle;
import com.android.car.developeroptions.R;
import com.android.settingslib.core.AbstractPreferenceController;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ZenCustomRuleCallsSettings extends ZenCustomRuleSettingsBase {
    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1611;
    }

    @Override // com.android.car.developeroptions.notification.ZenCustomRuleSettingsBase
    String getPreferenceCategoryKey() {
        return "zen_mode_settings_category_calls";
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.zen_mode_calls_settings;
    }

    @Override // com.android.car.developeroptions.notification.ZenModeSettingsBase, com.android.car.developeroptions.dashboard.RestrictedDashboardFragment, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        this.mControllers = arrayList;
        arrayList.add(new ZenRuleCallsPreferenceController(context, "zen_mode_calls", getSettingsLifecycle()));
        this.mControllers.add(new ZenRuleRepeatCallersPreferenceController(context, "zen_mode_repeat_callers", getSettingsLifecycle(), context.getResources().getInteger(android.R.integer.config_maxShortcutTargetsPerApp)));
        this.mControllers.add(new ZenRuleStarredContactsPreferenceController(context, getSettingsLifecycle(), 3, "zen_mode_starred_contacts_callers"));
        return this.mControllers;
    }

    @Override // com.android.car.developeroptions.notification.ZenCustomRuleSettingsBase
    public void updatePreferences() {
        super.updatePreferences();
        getPreferenceScreen().findPreference("footer_preference").setTitle(this.mContext.getResources().getString(R.string.zen_mode_custom_calls_footer, this.mRule.getName()));
    }
}

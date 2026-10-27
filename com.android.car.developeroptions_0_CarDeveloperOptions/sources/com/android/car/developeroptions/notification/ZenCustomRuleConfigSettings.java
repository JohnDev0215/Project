package com.android.car.developeroptions.notification;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.notification.ZenModeSettings;
import com.android.settingslib.core.AbstractPreferenceController;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ZenCustomRuleConfigSettings extends ZenCustomRuleSettingsBase {
    private Preference mCallsPreference;
    private Preference mMessagesPreference;
    private Preference mNotificationsPreference;
    private ZenModeSettings.SummaryBuilder mSummaryBuilder;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1605;
    }

    @Override // com.android.car.developeroptions.notification.ZenCustomRuleSettingsBase
    String getPreferenceCategoryKey() {
        return "zen_custom_rule_configuration_category";
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.zen_mode_custom_rule_configuration;
    }

    @Override // com.android.car.developeroptions.notification.ZenModeSettingsBase, com.android.car.developeroptions.dashboard.RestrictedDashboardFragment, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSummaryBuilder = new ZenModeSettings.SummaryBuilder(this.mContext);
        Preference preferenceFindPreference = getPreferenceScreen().findPreference("zen_rule_calls_settings");
        this.mCallsPreference = preferenceFindPreference;
        preferenceFindPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.notification.ZenCustomRuleConfigSettings.1
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public boolean onPreferenceClick(Preference preference) {
                SubSettingLauncher subSettingLauncher = new SubSettingLauncher(ZenCustomRuleConfigSettings.this.mContext);
                subSettingLauncher.setDestination(ZenCustomRuleCallsSettings.class.getName());
                subSettingLauncher.setArguments(ZenCustomRuleConfigSettings.this.createZenRuleBundle());
                subSettingLauncher.setSourceMetricsCategory(1611);
                subSettingLauncher.launch();
                return true;
            }
        });
        Preference preferenceFindPreference2 = getPreferenceScreen().findPreference("zen_rule_messages_settings");
        this.mMessagesPreference = preferenceFindPreference2;
        preferenceFindPreference2.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.notification.ZenCustomRuleConfigSettings.2
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public boolean onPreferenceClick(Preference preference) {
                SubSettingLauncher subSettingLauncher = new SubSettingLauncher(ZenCustomRuleConfigSettings.this.mContext);
                subSettingLauncher.setDestination(ZenCustomRuleMessagesSettings.class.getName());
                subSettingLauncher.setArguments(ZenCustomRuleConfigSettings.this.createZenRuleBundle());
                subSettingLauncher.setSourceMetricsCategory(1610);
                subSettingLauncher.launch();
                return true;
            }
        });
        Preference preferenceFindPreference3 = getPreferenceScreen().findPreference("zen_rule_notifications");
        this.mNotificationsPreference = preferenceFindPreference3;
        preferenceFindPreference3.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.notification.ZenCustomRuleConfigSettings.3
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public boolean onPreferenceClick(Preference preference) {
                SubSettingLauncher subSettingLauncher = new SubSettingLauncher(ZenCustomRuleConfigSettings.this.mContext);
                subSettingLauncher.setDestination(ZenCustomRuleNotificationsSettings.class.getName());
                subSettingLauncher.setArguments(ZenCustomRuleConfigSettings.this.createZenRuleBundle());
                subSettingLauncher.setSourceMetricsCategory(1608);
                subSettingLauncher.launch();
                return true;
            }
        });
        updateSummaries();
    }

    @Override // com.android.car.developeroptions.notification.ZenCustomRuleSettingsBase, com.android.car.developeroptions.notification.ZenModeSettingsBase
    public void onZenModeConfigChanged() {
        super.onZenModeConfigChanged();
        updateSummaries();
    }

    private void updateSummaries() {
        NotificationManager.Policy notificationPolicy = this.mBackend.toNotificationPolicy(this.mRule.getZenPolicy());
        this.mCallsPreference.setSummary(this.mSummaryBuilder.getCallsSettingSummary(notificationPolicy));
        this.mMessagesPreference.setSummary(this.mSummaryBuilder.getMessagesSettingSummary(notificationPolicy));
        this.mNotificationsPreference.setSummary(this.mSummaryBuilder.getBlockedEffectsSummary(notificationPolicy));
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        this.mControllers = arrayList;
        arrayList.add(new ZenRuleCustomSwitchPreferenceController(context, getSettingsLifecycle(), "zen_rule_alarms", 5, 1226));
        this.mControllers.add(new ZenRuleCustomSwitchPreferenceController(context, getSettingsLifecycle(), "zen_rule_media", 6, 1227));
        this.mControllers.add(new ZenRuleCustomSwitchPreferenceController(context, getSettingsLifecycle(), "zen_rule_system", 7, 1340));
        this.mControllers.add(new ZenRuleCustomSwitchPreferenceController(context, getSettingsLifecycle(), "zen_rule_reminders", 0, 167));
        this.mControllers.add(new ZenRuleCustomSwitchPreferenceController(context, getSettingsLifecycle(), "zen_rule_events", 1, 168));
        return this.mControllers;
    }

    @Override // com.android.car.developeroptions.notification.ZenCustomRuleSettingsBase, com.android.car.developeroptions.notification.ZenModeSettingsBase, com.android.car.developeroptions.dashboard.RestrictedDashboardFragment, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        updateSummaries();
    }
}

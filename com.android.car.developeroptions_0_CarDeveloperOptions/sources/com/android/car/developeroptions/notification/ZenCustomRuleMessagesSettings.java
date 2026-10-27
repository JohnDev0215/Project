package com.android.car.developeroptions.notification;

import android.content.Context;
import com.android.car.developeroptions.R;
import com.android.settingslib.core.AbstractPreferenceController;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ZenCustomRuleMessagesSettings extends ZenCustomRuleSettingsBase {
    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1610;
    }

    @Override // com.android.car.developeroptions.notification.ZenCustomRuleSettingsBase
    String getPreferenceCategoryKey() {
        return "zen_mode_settings_category_messages";
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.zen_mode_messages_settings;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        this.mControllers = arrayList;
        arrayList.add(new ZenRuleMessagesPreferenceController(context, "zen_mode_messages", getSettingsLifecycle()));
        this.mControllers.add(new ZenRuleStarredContactsPreferenceController(context, getSettingsLifecycle(), 2, "zen_mode_starred_contacts_messages"));
        return this.mControllers;
    }

    @Override // com.android.car.developeroptions.notification.ZenCustomRuleSettingsBase
    public void updatePreferences() {
        super.updatePreferences();
        getPreferenceScreen().findPreference("footer_preference").setTitle(this.mContext.getResources().getString(R.string.zen_mode_custom_messages_footer, this.mRule.getName()));
    }
}

package com.android.car.developeroptions.notification;

import android.content.Context;
import com.android.car.developeroptions.R;
import com.android.settingslib.core.AbstractPreferenceController;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ZenCustomRuleNotificationsSettings extends ZenCustomRuleSettingsBase {
    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1608;
    }

    @Override // com.android.car.developeroptions.notification.ZenCustomRuleSettingsBase
    String getPreferenceCategoryKey() {
        return "restrict_category";
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.zen_mode_restrict_notifications_settings;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        this.mControllers = arrayList;
        arrayList.add(new ZenRuleVisEffectsAllPreferenceController(context, getSettingsLifecycle(), "zen_mute_notifications"));
        this.mControllers.add(new ZenRuleVisEffectsNonePreferenceController(context, getSettingsLifecycle(), "zen_hide_notifications"));
        this.mControllers.add(new ZenRuleVisEffectsCustomPreferenceController(context, getSettingsLifecycle(), "zen_custom"));
        this.mControllers.add(new ZenRuleNotifFooterPreferenceController(context, getSettingsLifecycle(), "footer_preference"));
        return this.mControllers;
    }
}

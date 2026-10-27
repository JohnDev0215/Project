package com.android.car.developeroptions.notification;

import android.content.Context;
import android.os.Bundle;
import com.android.car.developeroptions.R;
import com.android.settingslib.core.AbstractPreferenceController;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ZenCustomRuleBlockedEffectsSettings extends ZenCustomRuleSettingsBase {
    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1609;
    }

    @Override // com.android.car.developeroptions.notification.ZenCustomRuleSettingsBase
    String getPreferenceCategoryKey() {
        return null;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.zen_mode_block_settings;
    }

    @Override // com.android.car.developeroptions.notification.ZenModeSettingsBase, com.android.car.developeroptions.dashboard.RestrictedDashboardFragment, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        this.mControllers = arrayList;
        arrayList.add(new ZenRuleVisEffectPreferenceController(context, getSettingsLifecycle(), "zen_effect_intent", 0, 1332, null));
        this.mControllers.add(new ZenRuleVisEffectPreferenceController(context, getSettingsLifecycle(), "zen_effect_light", 1, 1333, null));
        this.mControllers.add(new ZenRuleVisEffectPreferenceController(context, getSettingsLifecycle(), "zen_effect_peek", 2, 1334, null));
        this.mControllers.add(new ZenRuleVisEffectPreferenceController(context, getSettingsLifecycle(), "zen_effect_status", 3, 1335, new int[]{6}));
        this.mControllers.add(new ZenRuleVisEffectPreferenceController(context, getSettingsLifecycle(), "zen_effect_badge", 4, 1336, null));
        this.mControllers.add(new ZenRuleVisEffectPreferenceController(context, getSettingsLifecycle(), "zen_effect_ambient", 5, 1337, null));
        this.mControllers.add(new ZenRuleVisEffectPreferenceController(context, getSettingsLifecycle(), "zen_effect_list", 6, 1338, null));
        return this.mControllers;
    }
}

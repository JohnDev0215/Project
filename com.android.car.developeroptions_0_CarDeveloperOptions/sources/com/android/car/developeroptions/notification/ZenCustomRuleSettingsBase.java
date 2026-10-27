package com.android.car.developeroptions.notification;

import android.app.AutomaticZenRule;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.settingslib.core.AbstractPreferenceController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
abstract class ZenCustomRuleSettingsBase extends ZenModeSettingsBase {
    List<AbstractPreferenceController> mControllers = new ArrayList();
    String mId;
    AutomaticZenRule mRule;

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_uri_interruptions;
    }

    abstract String getPreferenceCategoryKey();

    ZenCustomRuleSettingsBase() {
    }

    @Override // com.android.car.developeroptions.notification.ZenModeSettingsBase, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey("RULE_ID")) {
            String string = arguments.getString("RULE_ID");
            this.mId = string;
            this.mRule = this.mBackend.getAutomaticZenRule(string);
        } else {
            Log.d("ZenCustomRuleSettings", "Rule id required to set custom dnd rule config settings");
            finish();
        }
    }

    @Override // com.android.car.developeroptions.notification.ZenModeSettingsBase
    public void onZenModeConfigChanged() {
        super.onZenModeConfigChanged();
        updatePreferences();
    }

    public void updatePreferences() {
        Preference preferenceFindPreference;
        this.mRule = this.mBackend.getAutomaticZenRule(this.mId);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        CharSequence preferenceCategoryKey = getPreferenceCategoryKey();
        if (preferenceCategoryKey != null && (preferenceFindPreference = preferenceScreen.findPreference(preferenceCategoryKey)) != null) {
            preferenceFindPreference.setTitle(this.mContext.getResources().getString(R.string.zen_mode_custom_behavior_category_title, this.mRule.getName()));
        }
        Iterator<AbstractPreferenceController> it = this.mControllers.iterator();
        while (it.hasNext()) {
            AbstractZenCustomRulePreferenceController abstractZenCustomRulePreferenceController = (AbstractZenCustomRulePreferenceController) it.next();
            abstractZenCustomRulePreferenceController.onResume(this.mRule, this.mId);
            abstractZenCustomRulePreferenceController.displayPreference(preferenceScreen);
            updatePreference(abstractZenCustomRulePreferenceController);
        }
    }

    @Override // com.android.car.developeroptions.notification.ZenModeSettingsBase, com.android.car.developeroptions.dashboard.RestrictedDashboardFragment, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        updatePreferences();
    }

    Bundle createZenRuleBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("RULE_ID", this.mId);
        return bundle;
    }
}

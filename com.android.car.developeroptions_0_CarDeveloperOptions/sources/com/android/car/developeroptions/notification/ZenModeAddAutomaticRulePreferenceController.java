package com.android.car.developeroptions.notification;

import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.notification.ZenRuleSelectionDialog;
import com.android.car.developeroptions.utils.ZenServiceListing;
import com.android.settingslib.core.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public class ZenModeAddAutomaticRulePreferenceController extends AbstractZenModeAutomaticRulePreferenceController implements Preference.OnPreferenceClickListener {
    private final ZenServiceListing mZenServiceListing;

    @Override // com.android.car.developeroptions.notification.AbstractZenModePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "zen_mode_add_automatic_rule";
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public ZenModeAddAutomaticRulePreferenceController(Context context, Fragment fragment, ZenServiceListing zenServiceListing, Lifecycle lifecycle) {
        super(context, "zen_mode_add_automatic_rule", fragment, lifecycle);
        this.mZenServiceListing = zenServiceListing;
    }

    @Override // com.android.car.developeroptions.notification.AbstractZenModePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        Preference preferenceFindPreference = preferenceScreen.findPreference("zen_mode_add_automatic_rule");
        preferenceFindPreference.setPersistent(false);
        preferenceFindPreference.setOnPreferenceClickListener(this);
    }

    @Override // androidx.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        ZenRuleSelectionDialog.show(this.mContext, this.mParent, new RuleSelectionListener(), this.mZenServiceListing);
        return true;
    }

    public class RuleSelectionListener implements ZenRuleSelectionDialog.PositiveClickListener {
        public RuleSelectionListener() {
        }

        @Override // com.android.car.developeroptions.notification.ZenRuleSelectionDialog.PositiveClickListener
        public void onSystemRuleSelected(ZenRuleInfo zenRuleInfo, Fragment fragment) {
            ZenModeAddAutomaticRulePreferenceController.this.showNameRuleDialog(zenRuleInfo, fragment);
        }

        @Override // com.android.car.developeroptions.notification.ZenRuleSelectionDialog.PositiveClickListener
        public void onExternalRuleSelected(ZenRuleInfo zenRuleInfo, Fragment fragment) {
            fragment.startActivity(new Intent().setComponent(zenRuleInfo.configurationActivity));
        }
    }
}

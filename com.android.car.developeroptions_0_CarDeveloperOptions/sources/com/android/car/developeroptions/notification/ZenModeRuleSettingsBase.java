package com.android.car.developeroptions.notification;

import android.app.AutomaticZenRule;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.SubSettingLauncher;

/* JADX INFO: loaded from: classes.dex */
public abstract class ZenModeRuleSettingsBase extends ZenModeSettingsBase {
    protected static final boolean DEBUG = ZenModeSettingsBase.DEBUG;
    protected ZenRuleButtonsPreferenceController mActionButtons;
    protected Context mContext;
    protected Preference mCustomBehaviorPreference;
    protected boolean mDisableListeners;
    protected ZenAutomaticRuleHeaderPreferenceController mHeader;
    protected String mId;
    protected AutomaticZenRule mRule;
    protected ZenAutomaticRuleSwitchPreferenceController mSwitch;

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_uri_interruptions;
    }

    protected abstract void onCreateInternal();

    protected abstract boolean setRule(AutomaticZenRule automaticZenRule);

    protected abstract void updateControlsInternal();

    @Override // com.android.car.developeroptions.notification.ZenModeSettingsBase, com.android.car.developeroptions.dashboard.RestrictedDashboardFragment, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        boolean z = DEBUG;
        this.mContext = getActivity();
        Intent intent = getActivity().getIntent();
        if (z) {
            Log.d("ZenModeSettings", "onCreate getIntent()=" + intent);
        }
        if (intent == null) {
            Log.w("ZenModeSettings", "No intent");
            toastAndFinish();
            return;
        }
        String stringExtra = intent.getStringExtra("android.service.notification.extra.RULE_ID");
        this.mId = stringExtra;
        if (stringExtra == null) {
            Log.w("ZenModeSettings", "rule id is null");
            toastAndFinish();
            return;
        }
        if (z) {
            Log.d("ZenModeSettings", "mId=" + this.mId);
        }
        if (refreshRuleOrFinish()) {
            return;
        }
        super.onCreate(bundle);
        Preference preferenceFindPreference = getPreferenceScreen().findPreference("zen_custom_setting");
        this.mCustomBehaviorPreference = preferenceFindPreference;
        preferenceFindPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.notification.ZenModeRuleSettingsBase.1
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public boolean onPreferenceClick(Preference preference) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("RULE_ID", ZenModeRuleSettingsBase.this.mId);
                SubSettingLauncher subSettingLauncher = new SubSettingLauncher(ZenModeRuleSettingsBase.this.mContext);
                subSettingLauncher.setDestination(ZenCustomRuleSettings.class.getName());
                subSettingLauncher.setArguments(bundle2);
                subSettingLauncher.setSourceMetricsCategory(0);
                subSettingLauncher.launch();
                return true;
            }
        });
        onCreateInternal();
    }

    @Override // com.android.car.developeroptions.notification.ZenModeSettingsBase, com.android.car.developeroptions.dashboard.RestrictedDashboardFragment, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (isUiRestricted() || refreshRuleOrFinish()) {
            return;
        }
        updateControls();
    }

    protected void updateHeader() {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        this.mSwitch.onResume(this.mRule, this.mId);
        this.mSwitch.displayPreference(preferenceScreen);
        updatePreference(this.mSwitch);
        this.mHeader.onResume(this.mRule, this.mId);
        this.mHeader.displayPreference(preferenceScreen);
        updatePreference(this.mHeader);
        this.mActionButtons.onResume(this.mRule, this.mId);
        this.mActionButtons.displayPreference(preferenceScreen);
        updatePreference(this.mActionButtons);
    }

    protected void updateRule(Uri uri) {
        this.mRule.setConditionId(uri);
        this.mBackend.updateZenRule(this.mId, this.mRule);
    }

    @Override // com.android.car.developeroptions.notification.ZenModeSettingsBase
    protected void onZenModeConfigChanged() {
        super.onZenModeConfigChanged();
        if (refreshRuleOrFinish()) {
            return;
        }
        updateControls();
    }

    private boolean refreshRuleOrFinish() {
        this.mRule = getZenRule();
        if (DEBUG) {
            Log.d("ZenModeSettings", "mRule=" + this.mRule);
        }
        if (setRule(this.mRule)) {
            return false;
        }
        toastAndFinish();
        return true;
    }

    private void toastAndFinish() {
        Toast.makeText(this.mContext, R.string.zen_mode_rule_not_found_text, 0).show();
        getActivity().finish();
    }

    private AutomaticZenRule getZenRule() {
        return NotificationManager.from(this.mContext).getAutomaticZenRule(this.mId);
    }

    private void updateControls() {
        this.mDisableListeners = true;
        updateControlsInternal();
        updateHeader();
        if (this.mRule.getZenPolicy() == null) {
            this.mCustomBehaviorPreference.setSummary(R.string.zen_mode_custom_behavior_summary_default);
        } else {
            this.mCustomBehaviorPreference.setSummary(R.string.zen_mode_custom_behavior_summary);
        }
        this.mDisableListeners = false;
    }
}

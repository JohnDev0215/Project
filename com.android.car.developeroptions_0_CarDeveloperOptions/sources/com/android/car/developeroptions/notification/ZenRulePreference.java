package com.android.car.developeroptions.notification;

import android.app.AutomaticZenRule;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.service.notification.ZenModeConfig;
import android.view.View;
import android.widget.CheckBox;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.utils.ManagedServiceSettings;
import com.android.car.developeroptions.utils.ZenServiceListing;
import com.android.settingslib.TwoTargetPreference;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ZenRulePreference extends TwoTargetPreference {
    private static final ManagedServiceSettings.Config CONFIG = ZenModeAutomationSettings.getConditionProviderConfig();
    final ZenModeBackend mBackend;
    private CheckBox mCheckBox;
    private boolean mChecked;
    final Context mContext;
    final String mId;
    private Intent mIntent;
    CharSequence mName;
    private View.OnClickListener mOnCheckBoxClickListener;
    final PackageManager mPm;
    final AutomaticZenRule mRule;
    final ZenServiceListing mServiceListing;

    public ZenRulePreference(Context context, Map.Entry<String, AutomaticZenRule> entry, Fragment fragment, MetricsFeatureProvider metricsFeatureProvider) {
        super(context);
        this.mOnCheckBoxClickListener = new View.OnClickListener() { // from class: com.android.car.developeroptions.notification.ZenRulePreference.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ZenRulePreference.this.mRule.setEnabled(!r3.mChecked);
                ZenRulePreference zenRulePreference = ZenRulePreference.this;
                zenRulePreference.mBackend.updateZenRule(zenRulePreference.mId, zenRulePreference.mRule);
                ZenRulePreference zenRulePreference2 = ZenRulePreference.this;
                zenRulePreference2.setChecked(zenRulePreference2.mRule.isEnabled());
                ZenRulePreference zenRulePreference3 = ZenRulePreference.this;
                zenRulePreference3.setAttributes(zenRulePreference3.mRule);
            }
        };
        setLayoutResource(R.layout.preference_checkable_two_target);
        this.mBackend = ZenModeBackend.getInstance(context);
        this.mContext = context;
        AutomaticZenRule value = entry.getValue();
        this.mRule = value;
        this.mName = value.getName();
        this.mId = entry.getKey();
        this.mPm = this.mContext.getPackageManager();
        ZenServiceListing zenServiceListing = new ZenServiceListing(this.mContext, CONFIG);
        this.mServiceListing = zenServiceListing;
        zenServiceListing.reloadApprovedServices();
        this.mChecked = this.mRule.isEnabled();
        setAttributes(this.mRule);
        setWidgetLayoutResource(getSecondTargetResId());
    }

    @Override // com.android.settingslib.TwoTargetPreference
    protected int getSecondTargetResId() {
        if (this.mIntent != null) {
            return R.layout.zen_rule_widget;
        }
        return 0;
    }

    @Override // com.android.settingslib.TwoTargetPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View viewFindViewById = preferenceViewHolder.findViewById(android.R.id.widget_frame);
        View viewFindViewById2 = preferenceViewHolder.findViewById(R.id.two_target_divider);
        if (this.mIntent != null) {
            viewFindViewById2.setVisibility(0);
            viewFindViewById.setVisibility(0);
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.notification.ZenRulePreference.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ZenRulePreference zenRulePreference = ZenRulePreference.this;
                    zenRulePreference.mContext.startActivity(zenRulePreference.mIntent);
                }
            });
        } else {
            viewFindViewById2.setVisibility(8);
            viewFindViewById.setVisibility(8);
            viewFindViewById.setOnClickListener(null);
        }
        View viewFindViewById3 = preferenceViewHolder.findViewById(R.id.checkbox_container);
        if (viewFindViewById3 != null) {
            viewFindViewById3.setOnClickListener(this.mOnCheckBoxClickListener);
        }
        CheckBox checkBox = (CheckBox) preferenceViewHolder.findViewById(android.R.id.checkbox);
        this.mCheckBox = checkBox;
        if (checkBox != null) {
            checkBox.setChecked(this.mChecked);
        }
    }

    public void updatePreference(AutomaticZenRule automaticZenRule) {
        if (!this.mRule.getName().equals(automaticZenRule.getName())) {
            String name = automaticZenRule.getName();
            this.mName = name;
            setTitle(name);
            this.mRule.setName(this.mName.toString());
        }
        if (this.mRule.isEnabled() != automaticZenRule.isEnabled()) {
            this.mRule.setEnabled(automaticZenRule.isEnabled());
            setChecked(this.mRule.isEnabled());
            setSummary(computeRuleSummary(this.mRule));
        }
    }

    @Override // androidx.preference.Preference
    public void onClick() {
        this.mOnCheckBoxClickListener.onClick(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setChecked(boolean z) {
        this.mChecked = z;
        CheckBox checkBox = this.mCheckBox;
        if (checkBox != null) {
            checkBox.setChecked(z);
        }
    }

    protected void setAttributes(AutomaticZenRule automaticZenRule) {
        boolean zIsValidScheduleConditionId = ZenModeConfig.isValidScheduleConditionId(automaticZenRule.getConditionId(), true);
        boolean zIsValidEventConditionId = ZenModeConfig.isValidEventConditionId(automaticZenRule.getConditionId());
        setSummary(computeRuleSummary(automaticZenRule));
        setTitle(this.mName);
        setPersistent(false);
        Intent ruleIntent = AbstractZenModeAutomaticRulePreferenceController.getRuleIntent(zIsValidScheduleConditionId ? "android.settings.ZEN_MODE_SCHEDULE_RULE_SETTINGS" : zIsValidEventConditionId ? "android.settings.ZEN_MODE_EVENT_RULE_SETTINGS" : "", AbstractZenModeAutomaticRulePreferenceController.getSettingsActivity(automaticZenRule, this.mServiceListing.findService(automaticZenRule.getOwner())), this.mId);
        this.mIntent = ruleIntent;
        if (ruleIntent.resolveActivity(this.mPm) == null) {
            this.mIntent = null;
        }
        setKey(this.mId);
    }

    private String computeRuleSummary(AutomaticZenRule automaticZenRule) {
        if (automaticZenRule == null || !automaticZenRule.isEnabled()) {
            return this.mContext.getResources().getString(R.string.switch_off_text);
        }
        return this.mContext.getResources().getString(R.string.switch_on_text);
    }
}

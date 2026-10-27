package com.android.car.developeroptions.notification;

import android.app.AutomaticZenRule;
import android.content.Context;
import android.service.notification.ZenPolicy;
import android.util.Log;
import android.util.Pair;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.R;
import com.android.settingslib.core.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public class ZenRuleRepeatCallersPreferenceController extends AbstractZenCustomRulePreferenceController implements Preference.OnPreferenceChangeListener {
    private final int mRepeatCallersThreshold;

    public ZenRuleRepeatCallersPreferenceController(Context context, String str, Lifecycle lifecycle, int i) {
        super(context, str, lifecycle);
        this.mRepeatCallersThreshold = i;
    }

    @Override // com.android.car.developeroptions.notification.AbstractZenModePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        setRepeatCallerSummary(preferenceScreen.findPreference(this.KEY));
    }

    @Override // com.android.car.developeroptions.notification.AbstractZenCustomRulePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        AutomaticZenRule automaticZenRule = this.mRule;
        if (automaticZenRule == null || automaticZenRule.getZenPolicy() == null) {
            return;
        }
        SwitchPreference switchPreference = (SwitchPreference) preference;
        if (this.mRule.getZenPolicy().getPriorityCallSenders() == 1) {
            switchPreference.setEnabled(false);
            switchPreference.setChecked(true);
        } else {
            switchPreference.setEnabled(true);
            switchPreference.setChecked(this.mRule.getZenPolicy().getPriorityCategoryRepeatCallers() == 1);
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        if (ZenModeSettingsBase.DEBUG) {
            Log.d("PrefControllerMixin", this.KEY + " onPrefChange mRule=" + this.mRule + " mCategory=4 allow=" + zBooleanValue);
        }
        this.mMetricsFeatureProvider.action(this.mContext, 171, Pair.create(1602, Integer.valueOf(zBooleanValue ? 1 : 0)), Pair.create(1603, this.mId));
        this.mRule.setZenPolicy(new ZenPolicy.Builder(this.mRule.getZenPolicy()).allowRepeatCallers(zBooleanValue).build());
        this.mBackend.updateZenRule(this.mId, this.mRule);
        return true;
    }

    private void setRepeatCallerSummary(Preference preference) {
        preference.setSummary(this.mContext.getString(R.string.zen_mode_repeat_callers_summary, Integer.valueOf(this.mRepeatCallersThreshold)));
    }
}

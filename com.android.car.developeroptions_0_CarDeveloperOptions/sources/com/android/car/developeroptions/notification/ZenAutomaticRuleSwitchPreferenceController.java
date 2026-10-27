package com.android.car.developeroptions.notification;

import android.app.AutomaticZenRule;
import android.content.Context;
import android.widget.Switch;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.widget.SwitchBar;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.widget.LayoutPreference;

/* JADX INFO: loaded from: classes.dex */
public class ZenAutomaticRuleSwitchPreferenceController extends AbstractZenModeAutomaticRulePreferenceController implements SwitchBar.OnSwitchChangeListener {
    private String mId;
    private AutomaticZenRule mRule;
    private SwitchBar mSwitchBar;

    @Override // com.android.car.developeroptions.notification.AbstractZenModePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "zen_automatic_rule_switch";
    }

    public ZenAutomaticRuleSwitchPreferenceController(Context context, Fragment fragment, Lifecycle lifecycle) {
        super(context, "zen_automatic_rule_switch", fragment, lifecycle);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return (this.mRule == null || this.mId == null) ? false : true;
    }

    @Override // com.android.car.developeroptions.notification.AbstractZenModePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        LayoutPreference layoutPreference = (LayoutPreference) preferenceScreen.findPreference("zen_automatic_rule_switch");
        SwitchBar switchBar = (SwitchBar) layoutPreference.findViewById(R.id.switch_bar);
        this.mSwitchBar = switchBar;
        if (switchBar != null) {
            switchBar.setSwitchBarText(R.string.zen_mode_use_automatic_rule, R.string.zen_mode_use_automatic_rule);
            try {
                layoutPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.notification.-$$Lambda$ZenAutomaticRuleSwitchPreferenceController$3p2QAVhRHO_Jz3AKCLBX6VVAJl4
                    @Override // androidx.preference.Preference.OnPreferenceClickListener
                    public final boolean onPreferenceClick(Preference preference) {
                        return this.f$0.lambda$displayPreference$0$ZenAutomaticRuleSwitchPreferenceController(preference);
                    }
                });
                this.mSwitchBar.addOnSwitchChangeListener(this);
            } catch (IllegalStateException unused) {
            }
            this.mSwitchBar.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$displayPreference$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$displayPreference$0$ZenAutomaticRuleSwitchPreferenceController(Preference preference) {
        this.mRule.setEnabled(!r3.isEnabled());
        ((AbstractZenModeAutomaticRulePreferenceController) this).mBackend.updateZenRule(this.mId, this.mRule);
        return true;
    }

    public void onResume(AutomaticZenRule automaticZenRule, String str) {
        this.mRule = automaticZenRule;
        this.mId = str;
    }

    @Override // com.android.car.developeroptions.notification.AbstractZenModeAutomaticRulePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        AutomaticZenRule automaticZenRule = this.mRule;
        if (automaticZenRule != null) {
            this.mSwitchBar.setChecked(automaticZenRule.isEnabled());
        }
    }

    @Override // com.android.car.developeroptions.widget.SwitchBar.OnSwitchChangeListener
    public void onSwitchChanged(Switch r1, boolean z) {
        if (z == this.mRule.isEnabled()) {
            return;
        }
        this.mRule.setEnabled(z);
        ((AbstractZenModeAutomaticRulePreferenceController) this).mBackend.updateZenRule(this.mId, this.mRule);
    }
}

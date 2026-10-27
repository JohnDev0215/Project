package com.android.car.developeroptions.notification;

import android.app.AutomaticZenRule;
import android.content.Context;
import android.service.notification.ZenPolicy;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.notification.ZenCustomRadioButtonPreference;
import com.android.settingslib.core.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public class ZenRuleCustomPolicyPreferenceController extends AbstractZenCustomRulePreferenceController {
    private ZenCustomRadioButtonPreference mPreference;

    public ZenRuleCustomPolicyPreferenceController(Context context, Lifecycle lifecycle, String str) {
        super(context, str, lifecycle);
    }

    @Override // com.android.car.developeroptions.notification.AbstractZenModePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        ZenCustomRadioButtonPreference zenCustomRadioButtonPreference = (ZenCustomRadioButtonPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mPreference = zenCustomRadioButtonPreference;
        zenCustomRadioButtonPreference.setOnGearClickListener(new ZenCustomRadioButtonPreference.OnGearClickListener() { // from class: com.android.car.developeroptions.notification.-$$Lambda$ZenRuleCustomPolicyPreferenceController$udYaPnicQlhQa8RIF6hzh9mx-i0
            @Override // com.android.car.developeroptions.notification.ZenCustomRadioButtonPreference.OnGearClickListener
            public final void onGearClick(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference2) {
                this.f$0.lambda$displayPreference$0$ZenRuleCustomPolicyPreferenceController(zenCustomRadioButtonPreference2);
            }
        });
        this.mPreference.setOnRadioButtonClickListener(new ZenCustomRadioButtonPreference.OnRadioButtonClickListener() { // from class: com.android.car.developeroptions.notification.-$$Lambda$ZenRuleCustomPolicyPreferenceController$BSKgTcyCM8Nfha3CLKzxEyvuAac
            @Override // com.android.car.developeroptions.notification.ZenCustomRadioButtonPreference.OnRadioButtonClickListener
            public final void onRadioButtonClick(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference2) {
                this.f$0.lambda$displayPreference$1$ZenRuleCustomPolicyPreferenceController(zenCustomRadioButtonPreference2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$displayPreference$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$displayPreference$0$ZenRuleCustomPolicyPreferenceController(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference) {
        setCustomPolicy();
        launchCustomSettings();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$displayPreference$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$displayPreference$1$ZenRuleCustomPolicyPreferenceController(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference) {
        setCustomPolicy();
        launchCustomSettings();
    }

    @Override // com.android.car.developeroptions.notification.AbstractZenCustomRulePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        AutomaticZenRule automaticZenRule;
        super.updateState(preference);
        if (this.mId == null || (automaticZenRule = this.mRule) == null) {
            return;
        }
        this.mPreference.setChecked(automaticZenRule.getZenPolicy() != null);
    }

    private void setCustomPolicy() {
        if (this.mRule.getZenPolicy() == null) {
            this.mRule.setZenPolicy(this.mBackend.setDefaultZenPolicy(new ZenPolicy()));
            this.mBackend.updateZenRule(this.mId, this.mRule);
        }
    }

    private void launchCustomSettings() {
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this.mContext);
        subSettingLauncher.setDestination(ZenCustomRuleConfigSettings.class.getName());
        subSettingLauncher.setArguments(createBundle());
        subSettingLauncher.setSourceMetricsCategory(1605);
        subSettingLauncher.launch();
    }
}

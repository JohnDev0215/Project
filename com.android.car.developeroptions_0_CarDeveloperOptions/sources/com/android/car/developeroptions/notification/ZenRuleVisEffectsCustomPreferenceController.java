package com.android.car.developeroptions.notification;

import android.app.AutomaticZenRule;
import android.content.Context;
import android.util.Pair;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.notification.ZenCustomRadioButtonPreference;
import com.android.settingslib.core.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public class ZenRuleVisEffectsCustomPreferenceController extends AbstractZenCustomRulePreferenceController implements PreferenceControllerMixin {
    private ZenCustomRadioButtonPreference mPreference;

    public ZenRuleVisEffectsCustomPreferenceController(Context context, Lifecycle lifecycle, String str) {
        super(context, str, lifecycle);
    }

    @Override // com.android.car.developeroptions.notification.AbstractZenModePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        ZenCustomRadioButtonPreference zenCustomRadioButtonPreference = (ZenCustomRadioButtonPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mPreference = zenCustomRadioButtonPreference;
        zenCustomRadioButtonPreference.setOnGearClickListener(new ZenCustomRadioButtonPreference.OnGearClickListener() { // from class: com.android.car.developeroptions.notification.-$$Lambda$ZenRuleVisEffectsCustomPreferenceController$EX0wYe9whxsGHgaX7UnUHKN6uoA
            @Override // com.android.car.developeroptions.notification.ZenCustomRadioButtonPreference.OnGearClickListener
            public final void onGearClick(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference2) {
                this.f$0.lambda$displayPreference$0$ZenRuleVisEffectsCustomPreferenceController(zenCustomRadioButtonPreference2);
            }
        });
        this.mPreference.setOnRadioButtonClickListener(new ZenCustomRadioButtonPreference.OnRadioButtonClickListener() { // from class: com.android.car.developeroptions.notification.-$$Lambda$ZenRuleVisEffectsCustomPreferenceController$0Gq8eHwZ8OgYYJJ-yc2Ej0lDSSs
            @Override // com.android.car.developeroptions.notification.ZenCustomRadioButtonPreference.OnRadioButtonClickListener
            public final void onRadioButtonClick(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference2) {
                this.f$0.lambda$displayPreference$1$ZenRuleVisEffectsCustomPreferenceController(zenCustomRadioButtonPreference2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$displayPreference$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$displayPreference$0$ZenRuleVisEffectsCustomPreferenceController(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference) {
        launchCustomSettings();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$displayPreference$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$displayPreference$1$ZenRuleVisEffectsCustomPreferenceController(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference) {
        launchCustomSettings();
    }

    @Override // com.android.car.developeroptions.notification.AbstractZenCustomRulePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        AutomaticZenRule automaticZenRule;
        super.updateState(preference);
        if (this.mId == null || (automaticZenRule = this.mRule) == null || automaticZenRule.getZenPolicy() == null) {
            return;
        }
        this.mPreference.setChecked((this.mRule.getZenPolicy().shouldHideAllVisualEffects() || this.mRule.getZenPolicy().shouldShowAllVisualEffects()) ? false : true);
    }

    private void launchCustomSettings() {
        this.mMetricsFeatureProvider.action(this.mContext, 1398, Pair.create(1603, this.mId));
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this.mContext);
        subSettingLauncher.setDestination(ZenCustomRuleBlockedEffectsSettings.class.getName());
        subSettingLauncher.setArguments(createBundle());
        subSettingLauncher.setSourceMetricsCategory(1609);
        subSettingLauncher.launch();
    }
}

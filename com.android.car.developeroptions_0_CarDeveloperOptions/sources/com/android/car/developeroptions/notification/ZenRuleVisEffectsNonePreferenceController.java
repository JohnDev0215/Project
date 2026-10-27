package com.android.car.developeroptions.notification;

import android.app.AutomaticZenRule;
import android.content.Context;
import android.service.notification.ZenPolicy;
import android.util.Pair;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.notification.ZenCustomRadioButtonPreference;
import com.android.settingslib.core.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public class ZenRuleVisEffectsNonePreferenceController extends AbstractZenCustomRulePreferenceController implements PreferenceControllerMixin {
    private ZenCustomRadioButtonPreference mPreference;

    public ZenRuleVisEffectsNonePreferenceController(Context context, Lifecycle lifecycle, String str) {
        super(context, str, lifecycle);
    }

    @Override // com.android.car.developeroptions.notification.AbstractZenModePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        ZenCustomRadioButtonPreference zenCustomRadioButtonPreference = (ZenCustomRadioButtonPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mPreference = zenCustomRadioButtonPreference;
        zenCustomRadioButtonPreference.setOnRadioButtonClickListener(new ZenCustomRadioButtonPreference.OnRadioButtonClickListener() { // from class: com.android.car.developeroptions.notification.-$$Lambda$ZenRuleVisEffectsNonePreferenceController$3Hb2VEGgGEi_7HJmwXtGH5ITWZc
            @Override // com.android.car.developeroptions.notification.ZenCustomRadioButtonPreference.OnRadioButtonClickListener
            public final void onRadioButtonClick(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference2) {
                this.f$0.lambda$displayPreference$0$ZenRuleVisEffectsNonePreferenceController(zenCustomRadioButtonPreference2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$displayPreference$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$displayPreference$0$ZenRuleVisEffectsNonePreferenceController(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference) {
        this.mMetricsFeatureProvider.action(this.mContext, 1397, Pair.create(1603, this.mId));
        this.mRule.setZenPolicy(new ZenPolicy.Builder(this.mRule.getZenPolicy()).hideAllVisualEffects().build());
        this.mBackend.updateZenRule(this.mId, this.mRule);
    }

    @Override // com.android.car.developeroptions.notification.AbstractZenCustomRulePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        AutomaticZenRule automaticZenRule;
        super.updateState(preference);
        if (this.mId == null || (automaticZenRule = this.mRule) == null || automaticZenRule.getZenPolicy() == null) {
            return;
        }
        this.mPreference.setChecked(this.mRule.getZenPolicy().shouldHideAllVisualEffects());
    }
}

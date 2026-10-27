package com.android.car.developeroptions.notification;

import android.content.Context;
import android.util.Pair;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.notification.ZenCustomRadioButtonPreference;
import com.android.settingslib.core.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public class ZenRuleDefaultPolicyPreferenceController extends AbstractZenCustomRulePreferenceController implements PreferenceControllerMixin {
    private ZenCustomRadioButtonPreference mPreference;

    public ZenRuleDefaultPolicyPreferenceController(Context context, Lifecycle lifecycle, String str) {
        super(context, str, lifecycle);
    }

    @Override // com.android.car.developeroptions.notification.AbstractZenModePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        ZenCustomRadioButtonPreference zenCustomRadioButtonPreference = (ZenCustomRadioButtonPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mPreference = zenCustomRadioButtonPreference;
        zenCustomRadioButtonPreference.setOnRadioButtonClickListener(new ZenCustomRadioButtonPreference.OnRadioButtonClickListener() { // from class: com.android.car.developeroptions.notification.-$$Lambda$ZenRuleDefaultPolicyPreferenceController$t73HkgiJQMv7_xSrV0rrL4K4KQM
            @Override // com.android.car.developeroptions.notification.ZenCustomRadioButtonPreference.OnRadioButtonClickListener
            public final void onRadioButtonClick(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference2) {
                this.f$0.lambda$displayPreference$0$ZenRuleDefaultPolicyPreferenceController(zenCustomRadioButtonPreference2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$displayPreference$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$displayPreference$0$ZenRuleDefaultPolicyPreferenceController(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference) {
        this.mRule.setZenPolicy(null);
        this.mBackend.updateZenRule(this.mId, this.mRule);
    }

    @Override // com.android.car.developeroptions.notification.AbstractZenCustomRulePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        if (this.mId == null || this.mRule == null) {
            return;
        }
        this.mMetricsFeatureProvider.action(this.mContext, 1606, Pair.create(1603, this.mId));
        this.mPreference.setChecked(this.mRule.getZenPolicy() == null);
    }
}

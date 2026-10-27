package com.android.car.developeroptions.notification;

import android.app.NotificationManager;
import android.content.Context;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.notification.ZenCustomRadioButtonPreference;
import com.android.settingslib.core.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public class ZenModeVisEffectsCustomPreferenceController extends AbstractZenModePreferenceController {
    private ZenCustomRadioButtonPreference mPreference;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public ZenModeVisEffectsCustomPreferenceController(Context context, Lifecycle lifecycle, String str) {
        super(context, str, lifecycle);
    }

    @Override // com.android.car.developeroptions.notification.AbstractZenModePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        ZenCustomRadioButtonPreference zenCustomRadioButtonPreference = (ZenCustomRadioButtonPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mPreference = zenCustomRadioButtonPreference;
        zenCustomRadioButtonPreference.setOnGearClickListener(new ZenCustomRadioButtonPreference.OnGearClickListener() { // from class: com.android.car.developeroptions.notification.-$$Lambda$ZenModeVisEffectsCustomPreferenceController$jSKEEWmIavTCqJRkvXx5UcGc-4M
            @Override // com.android.car.developeroptions.notification.ZenCustomRadioButtonPreference.OnGearClickListener
            public final void onGearClick(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference2) {
                this.f$0.lambda$displayPreference$0$ZenModeVisEffectsCustomPreferenceController(zenCustomRadioButtonPreference2);
            }
        });
        this.mPreference.setOnRadioButtonClickListener(new ZenCustomRadioButtonPreference.OnRadioButtonClickListener() { // from class: com.android.car.developeroptions.notification.-$$Lambda$ZenModeVisEffectsCustomPreferenceController$O2I3szX0-FLhHvNQ3nHijoyEFgI
            @Override // com.android.car.developeroptions.notification.ZenCustomRadioButtonPreference.OnRadioButtonClickListener
            public final void onRadioButtonClick(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference2) {
                this.f$0.lambda$displayPreference$1$ZenModeVisEffectsCustomPreferenceController(zenCustomRadioButtonPreference2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$displayPreference$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$displayPreference$0$ZenModeVisEffectsCustomPreferenceController(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference) {
        launchCustomSettings();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$displayPreference$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$displayPreference$1$ZenModeVisEffectsCustomPreferenceController(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference) {
        launchCustomSettings();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        this.mPreference.setChecked(areCustomOptionsSelected());
    }

    protected boolean areCustomOptionsSelected() {
        return (NotificationManager.Policy.areAllVisualEffectsSuppressed(this.mBackend.mPolicy.suppressedVisualEffects) || (this.mBackend.mPolicy.suppressedVisualEffects == 0)) ? false : true;
    }

    protected void select() {
        this.mMetricsFeatureProvider.action(this.mContext, 1399, true);
    }

    private void launchCustomSettings() {
        select();
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this.mContext);
        subSettingLauncher.setDestination(ZenModeBlockedEffectsSettings.class.getName());
        subSettingLauncher.setTitleRes(R.string.zen_mode_what_to_block_title);
        subSettingLauncher.setSourceMetricsCategory(1400);
        subSettingLauncher.launch();
    }
}

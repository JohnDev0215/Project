package com.android.car.developeroptions.notification;

import android.R;
import android.content.Context;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.widget.DisabledCheckBoxPreference;
import com.android.settingslib.core.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public class ZenModeVisEffectPreferenceController extends AbstractZenModePreferenceController implements Preference.OnPreferenceChangeListener, PreferenceControllerMixin {
    protected final int mEffect;
    protected final String mKey;
    protected final int mMetricsCategory;
    protected final int[] mParentSuppressedEffects;

    public ZenModeVisEffectPreferenceController(Context context, Lifecycle lifecycle, String str, int i, int i2, int[] iArr) {
        super(context, str, lifecycle);
        this.mKey = str;
        this.mEffect = i;
        this.mMetricsCategory = i2;
        this.mParentSuppressedEffects = iArr;
    }

    @Override // com.android.car.developeroptions.notification.AbstractZenModePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return this.mKey;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        if (this.mEffect == 8) {
            return this.mContext.getResources().getBoolean(R.bool.config_cecQuerySadDdDisabled_allowed);
        }
        return true;
    }

    @Override // com.android.car.developeroptions.notification.AbstractZenModePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        boolean zIsVisualEffectSuppressed;
        super.updateState(preference);
        boolean zIsVisualEffectSuppressed2 = this.mBackend.isVisualEffectSuppressed(this.mEffect);
        int[] iArr = this.mParentSuppressedEffects;
        if (iArr != null) {
            zIsVisualEffectSuppressed = false;
            for (int i : iArr) {
                zIsVisualEffectSuppressed |= this.mBackend.isVisualEffectSuppressed(i);
            }
        } else {
            zIsVisualEffectSuppressed = false;
        }
        if (zIsVisualEffectSuppressed) {
            ((CheckBoxPreference) preference).setChecked(zIsVisualEffectSuppressed);
            onPreferenceChange(preference, Boolean.valueOf(zIsVisualEffectSuppressed));
            ((DisabledCheckBoxPreference) preference).enableCheckbox(false);
        } else {
            ((DisabledCheckBoxPreference) preference).enableCheckbox(true);
            ((CheckBoxPreference) preference).setChecked(zIsVisualEffectSuppressed2);
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        this.mMetricsFeatureProvider.action(this.mContext, this.mMetricsCategory, zBooleanValue);
        this.mBackend.saveVisualEffectsPolicy(this.mEffect, zBooleanValue);
        return true;
    }
}

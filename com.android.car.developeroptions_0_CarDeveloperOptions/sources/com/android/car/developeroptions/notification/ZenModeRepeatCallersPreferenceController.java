package com.android.car.developeroptions.notification;

import android.content.Context;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.R;
import com.android.settingslib.core.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public class ZenModeRepeatCallersPreferenceController extends AbstractZenModePreferenceController implements Preference.OnPreferenceChangeListener {
    private final ZenModeBackend mBackend;
    private final int mRepeatCallersThreshold;

    @Override // com.android.car.developeroptions.notification.AbstractZenModePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "zen_mode_repeat_callers";
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public ZenModeRepeatCallersPreferenceController(Context context, Lifecycle lifecycle, int i) {
        super(context, "zen_mode_repeat_callers", lifecycle);
        this.mRepeatCallersThreshold = i;
        this.mBackend = ZenModeBackend.getInstance(context);
    }

    @Override // com.android.car.developeroptions.notification.AbstractZenModePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        setRepeatCallerSummary(preferenceScreen.findPreference("zen_mode_repeat_callers"));
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        SwitchPreference switchPreference = (SwitchPreference) preference;
        int zenMode = getZenMode();
        if (zenMode == 2 || zenMode == 3) {
            switchPreference.setEnabled(false);
            switchPreference.setChecked(false);
            return;
        }
        if (this.mBackend.isPriorityCategoryEnabled(8) && this.mBackend.getPriorityCallSenders() == 0) {
            switchPreference.setEnabled(false);
            switchPreference.setChecked(true);
        } else {
            switchPreference.setEnabled(true);
            switchPreference.setChecked(this.mBackend.isPriorityCategoryEnabled(16));
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        if (ZenModeSettingsBase.DEBUG) {
            Log.d("PrefControllerMixin", "onPrefChange allowRepeatCallers=" + zBooleanValue);
        }
        this.mMetricsFeatureProvider.action(this.mContext, 171, zBooleanValue);
        this.mBackend.saveSoundPolicy(16, zBooleanValue);
        return true;
    }

    private void setRepeatCallerSummary(Preference preference) {
        preference.setSummary(this.mContext.getString(R.string.zen_mode_repeat_callers_summary, Integer.valueOf(this.mRepeatCallersThreshold)));
    }
}

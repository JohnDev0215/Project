package com.android.car.developeroptions.notification;

import android.content.Context;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.SwitchPreference;
import com.android.settingslib.core.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public class ZenModeEventsPreferenceController extends AbstractZenModePreferenceController implements Preference.OnPreferenceChangeListener {
    @Override // com.android.car.developeroptions.notification.AbstractZenModePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "zen_mode_events";
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public ZenModeEventsPreferenceController(Context context, Lifecycle lifecycle) {
        super(context, "zen_mode_events", lifecycle);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        SwitchPreference switchPreference = (SwitchPreference) preference;
        int zenMode = getZenMode();
        if (zenMode == 2 || zenMode == 3) {
            switchPreference.setEnabled(false);
            switchPreference.setChecked(false);
        } else {
            switchPreference.setChecked(this.mBackend.isPriorityCategoryEnabled(2));
            switchPreference.setEnabled(true);
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        if (ZenModeSettingsBase.DEBUG) {
            Log.d("PrefControllerMixin", "onPrefChange allowEvents=" + zBooleanValue);
        }
        this.mMetricsFeatureProvider.action(this.mContext, 168, zBooleanValue);
        this.mBackend.saveSoundPolicy(2, zBooleanValue);
        return true;
    }
}

package com.android.car.developeroptions.development;

import android.content.Context;
import android.sysprop.DisplayProperties;
import androidx.preference.Preference;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;
import com.android.settingslib.development.SystemPropPoker;

/* JADX INFO: loaded from: classes.dex */
public class ForceMSAAPreferenceController extends DeveloperOptionsPreferenceController implements Preference.OnPreferenceChangeListener, PreferenceControllerMixin {
    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "force_msaa";
    }

    public ForceMSAAPreferenceController(Context context) {
        super(context);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        DisplayProperties.debug_force_msaa(Boolean.valueOf(((Boolean) obj).booleanValue()));
        SystemPropPoker.getInstance().poke();
        return true;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        ((SwitchPreference) this.mPreference).setChecked(((Boolean) DisplayProperties.debug_force_msaa().orElse(Boolean.FALSE)).booleanValue());
    }

    @Override // com.android.settingslib.development.DeveloperOptionsPreferenceController
    protected void onDeveloperOptionsSwitchDisabled() {
        super.onDeveloperOptionsSwitchDisabled();
        DisplayProperties.debug_force_msaa(Boolean.FALSE);
        ((SwitchPreference) this.mPreference).setChecked(false);
    }
}

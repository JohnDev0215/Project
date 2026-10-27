package com.android.car.developeroptions.development;

import android.content.Context;
import androidx.preference.Preference;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.development.AbstractLogdSizePreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class LogdSizePreferenceController extends AbstractLogdSizePreferenceController implements Preference.OnPreferenceChangeListener, PreferenceControllerMixin {
    public LogdSizePreferenceController(Context context) {
        super(context);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        updateLogdSizeValues();
    }

    @Override // com.android.settingslib.development.DeveloperOptionsPreferenceController
    protected void onDeveloperOptionsSwitchDisabled() {
        super.onDeveloperOptionsSwitchDisabled();
        writeLogdSizeOption(null);
    }
}

package com.android.car.developeroptions.development;

import androidx.preference.Preference;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.development.AbstractEnableAdbPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class AdbPreferenceController extends AbstractEnableAdbPreferenceController implements PreferenceControllerMixin {
    private final DevelopmentSettingsDashboardFragment mFragment;

    public void onAdbDialogConfirmed() {
        writeAdbSetting(true);
    }

    public void onAdbDialogDismissed() {
        updateState(((AbstractEnableAdbPreferenceController) this).mPreference);
    }

    @Override // com.android.settingslib.core.ConfirmationDialogController
    public void showConfirmationDialog(Preference preference) {
        EnableAdbWarningDialog.show(this.mFragment);
    }

    @Override // com.android.settingslib.development.DeveloperOptionsPreferenceController
    protected void onDeveloperOptionsSwitchDisabled() {
        super.onDeveloperOptionsSwitchDisabled();
        writeAdbSetting(false);
        ((AbstractEnableAdbPreferenceController) this).mPreference.setChecked(false);
    }
}

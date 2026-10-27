package com.android.car.developeroptions.backup;

import android.content.Context;
import android.content.Intent;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class BackupSettingsPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    private Intent mBackupSettingsIntent;
    private String mBackupSettingsSummary;
    private CharSequence mBackupSettingsTitle;
    private Intent mManufacturerIntent;
    private String mManufacturerLabel;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return null;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public BackupSettingsPreferenceController(Context context) {
        super(context);
        BackupSettingsHelper backupSettingsHelper = new BackupSettingsHelper(context);
        this.mBackupSettingsIntent = backupSettingsHelper.getIntentForBackupSettings();
        this.mBackupSettingsTitle = backupSettingsHelper.getLabelForBackupSettings();
        this.mBackupSettingsSummary = backupSettingsHelper.getSummaryForBackupSettings();
        this.mManufacturerIntent = backupSettingsHelper.getIntentProvidedByManufacturer();
        this.mManufacturerLabel = backupSettingsHelper.getLabelProvidedByManufacturer();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        Preference preferenceFindPreference = preferenceScreen.findPreference("backup_settings");
        Preference preferenceFindPreference2 = preferenceScreen.findPreference("manufacturer_backup");
        preferenceFindPreference.setIntent(this.mBackupSettingsIntent);
        preferenceFindPreference.setTitle(this.mBackupSettingsTitle);
        preferenceFindPreference.setSummary(this.mBackupSettingsSummary);
        preferenceFindPreference2.setIntent(this.mManufacturerIntent);
        preferenceFindPreference2.setTitle(this.mManufacturerLabel);
    }
}

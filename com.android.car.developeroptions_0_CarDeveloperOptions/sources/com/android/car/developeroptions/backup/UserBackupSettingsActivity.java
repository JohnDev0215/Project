package com.android.car.developeroptions.backup;

import android.R;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.settingslib.search.Indexable$SearchIndexProvider;

/* JADX INFO: loaded from: classes.dex */
public class UserBackupSettingsActivity extends FragmentActivity {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.backup.UserBackupSettingsActivity.1
    };
    private FragmentManager mFragmentManager;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        BackupSettingsHelper backupSettingsHelper = new BackupSettingsHelper(this);
        if (!backupSettingsHelper.isBackupProvidedByManufacturer()) {
            if (Log.isLoggable("BackupSettingsActivity", 3)) {
                Log.d("BackupSettingsActivity", "No manufacturer settings found, launching the backup settings directly");
            }
            Intent intentForBackupSettings = backupSettingsHelper.getIntentForBackupSettings();
            try {
                getPackageManager().setComponentEnabledSetting(intentForBackupSettings.getComponent(), 1, 1);
            } catch (SecurityException e) {
                Log.w("BackupSettingsActivity", "Trying to enable activity " + intentForBackupSettings.getComponent() + " but couldn't: " + e.getMessage());
            }
            startActivityForResult(intentForBackupSettings, 1);
            finish();
            return;
        }
        if (Log.isLoggable("BackupSettingsActivity", 3)) {
            Log.d("BackupSettingsActivity", "Manufacturer provided backup settings, showing the preference screen");
        }
        if (this.mFragmentManager == null) {
            this.mFragmentManager = getSupportFragmentManager();
        }
        FragmentTransaction fragmentTransactionBeginTransaction = this.mFragmentManager.beginTransaction();
        fragmentTransactionBeginTransaction.replace(R.id.content, new BackupSettingsFragment());
        fragmentTransactionBeginTransaction.commit();
    }

    void setFragmentManager(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }
}

package com.android.car.developeroptions.development;

import android.app.backup.IBackupManager;
import android.content.Context;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserManager;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class LocalBackupPasswordPreferenceController extends DeveloperOptionsPreferenceController implements PreferenceControllerMixin {
    private final IBackupManager mBackupManager;
    private final UserManager mUserManager;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "local_backup_password";
    }

    public LocalBackupPasswordPreferenceController(Context context) {
        super(context);
        this.mUserManager = (UserManager) context.getSystemService("user");
        this.mBackupManager = IBackupManager.Stub.asInterface(ServiceManager.getService("backup"));
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        updatePasswordSummary(preference);
    }

    private void updatePasswordSummary(Preference preference) {
        preference.setEnabled(isAdminUser() && this.mBackupManager != null);
        IBackupManager iBackupManager = this.mBackupManager;
        if (iBackupManager == null) {
            return;
        }
        try {
            if (iBackupManager.hasBackupPassword()) {
                preference.setSummary(R.string.local_backup_password_summary_change);
            } else {
                preference.setSummary(R.string.local_backup_password_summary_none);
            }
        } catch (RemoteException unused) {
        }
    }

    boolean isAdminUser() {
        return this.mUserManager.isAdminUser();
    }
}

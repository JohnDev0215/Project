package com.android.inputmethod.latin;

import android.app.backup.BackupAgentHelper;
import android.app.backup.BackupDataInput;
import android.app.backup.SharedPreferencesBackupHelper;
import android.content.SharedPreferences;
import android.os.ParcelFileDescriptor;
import com.android.inputmethod.latin.settings.LocalSettingsConstants;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class BackupAgent extends BackupAgentHelper {
    private static final String PREF_SUFFIX = "_preferences";

    @Override // android.app.backup.BackupAgent
    public void onCreate() {
        addHelper("shared_pref", new SharedPreferencesBackupHelper(this, getPackageName() + PREF_SUFFIX));
    }

    @Override // android.app.backup.BackupAgentHelper, android.app.backup.BackupAgent
    public void onRestore(BackupDataInput data, int appVersionCode, ParcelFileDescriptor newState) throws IOException {
        super.onRestore(data, appVersionCode, newState);
        SharedPreferences.Editor editorEdit = getSharedPreferences(getPackageName() + PREF_SUFFIX, 0).edit();
        for (String str : LocalSettingsConstants.PREFS_TO_SKIP_RESTORING) {
            editorEdit.remove(str);
        }
        editorEdit.commit();
    }
}

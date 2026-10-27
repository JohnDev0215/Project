package com.android.launcher3;

import android.app.backup.BackupAgent;
import android.app.backup.BackupDataInput;
import android.app.backup.BackupDataOutput;
import android.os.ParcelFileDescriptor;
import com.android.launcher3.logging.FileLog;
import com.android.launcher3.provider.RestoreDbTask;

/* JADX INFO: loaded from: classes4.dex */
public class LauncherBackupAgent extends BackupAgent {
    @Override // android.app.backup.BackupAgent
    public void onBackup(ParcelFileDescriptor oldState, BackupDataOutput data, ParcelFileDescriptor newState) {
    }

    @Override // android.app.backup.BackupAgent
    public void onRestore(BackupDataInput data, int appVersionCode, ParcelFileDescriptor newState) {
    }

    @Override // android.app.backup.BackupAgent
    public void onCreate() {
        super.onCreate();
        FileLog.setDir(getFilesDir());
    }

    @Override // android.app.backup.BackupAgent
    public void onRestoreFinished() {
        RestoreDbTask.setPending(this, true);
    }
}

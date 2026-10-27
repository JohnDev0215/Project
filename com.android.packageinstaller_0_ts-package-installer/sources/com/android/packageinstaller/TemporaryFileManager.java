package com.android.packageinstaller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class TemporaryFileManager extends BroadcastReceiver {
    private static final String LOG_TAG = TemporaryFileManager.class.getSimpleName();

    public static File getStagedFile(Context context) throws IOException {
        return File.createTempFile("package", ".apk", context.getNoBackupFilesDir());
    }

    public static File getInstallStateFile(Context context) {
        return new File(context.getNoBackupFilesDir(), "install_results.xml");
    }

    public static File getUninstallStateFile(Context context) {
        return new File(context.getNoBackupFilesDir(), "uninstall_results.xml");
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        long systemBootTime = System.currentTimeMillis() - SystemClock.elapsedRealtime();
        File[] filesOnBoot = context.getNoBackupFilesDir().listFiles();
        if (filesOnBoot == null) {
            return;
        }
        for (File fileOnBoot : filesOnBoot) {
            if (systemBootTime > fileOnBoot.lastModified()) {
                boolean wasDeleted = fileOnBoot.delete();
                if (!wasDeleted) {
                    Log.w(LOG_TAG, "Could not delete " + fileOnBoot.getName() + " onBoot");
                }
            } else {
                Log.w(LOG_TAG, fileOnBoot.getName() + " was created before onBoot broadcast was received");
            }
        }
    }
}

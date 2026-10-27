package com.amazon.mls.config.internal.sushi.uploader;

import android.annotation.TargetApi;

/* JADX INFO: loaded from: classes3.dex */
@TargetApi(21)
public class BackupUploaderScheduler {
    private static boolean shouldScheduleBackupUploader = true;

    public static synchronized void setShouldScheduleBackupUploader(boolean z) {
        shouldScheduleBackupUploader = z;
    }
}

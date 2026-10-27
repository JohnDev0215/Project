package com.amazon.mls.config.internal.sushi.uploader;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.Context;
import android.os.SystemClock;
import com.amazon.mls.config.internal.core.logcat.LogLevel;
import com.amazon.mls.config.internal.core.logcat.LogcatProxy;
import com.amazon.mls.config.internal.core.metrics.InternalMetrics;
import com.amazon.mls.config.internal.core.weblab.MlsWeblabs;
import com.amazon.mls.config.internal.sushi.SushiRecorder;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
@TargetApi(24)
public class BackupUploaderService extends JobService {
    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(final JobParameters jobParameters) {
        LogcatProxy.logForAutomation("MLS backup uploader job started");
        final String string = jobParameters.getExtras().getString("PARENT_DIR");
        final String string2 = jobParameters.getExtras().getString("SOURCE_GROUP");
        final long j = jobParameters.getExtras().getLong("MAX_STORAGE_BYTES");
        long j2 = jobParameters.getExtras().getLong("SCHEDULING_TIME");
        final int jobId = jobParameters.getJobId();
        if (string == null) {
            cancelJob(jobId, "Cannot execute backup upload because parentDir is null");
            return false;
        }
        if (string2 == null) {
            cancelJob(jobId, "Cannot execute backup upload because sourceGroup is null");
            return false;
        }
        if (j <= 0) {
            cancelJob(jobId, "Cannot execute backup upload because maxStorageBytes is <= 0");
            return false;
        }
        if (SystemClock.elapsedRealtime() - j2 < 1800000) {
            LogcatProxy.logForAutomation("Not executing backup job because it was called to soon after it was scheduled");
            return false;
        }
        final Context applicationContext = getApplicationContext();
        MlsWeblabs.getInstance(applicationContext);
        new Thread(new Runnable() { // from class: com.amazon.mls.config.internal.sushi.uploader.BackupUploaderService.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SushiRecorder sushiRecorderInstance = SushiRecorder.getSushiRecorderInstance(new File(string), string2, j, applicationContext);
                    if (!sushiRecorderInstance.hasEventsReadyForUpload()) {
                        LogcatProxy.log("Stopping MLS backup uploader because there are no more events to upload.");
                        BackupUploaderService.this.cancelJob(jobId);
                        BackupUploaderScheduler.setShouldScheduleBackupUploader(true);
                    } else {
                        sushiRecorderInstance.upload();
                    }
                } finally {
                    LogcatProxy.logForAutomation("MLS backup uploader upload finished");
                    BackupUploaderService.this.jobFinished(jobParameters, false);
                }
            }
        }).start();
        return true;
    }

    private void cancelJob(int i, String str) {
        LogcatProxy.log(LogLevel.ERROR, str);
        InternalMetrics.logCounter("sushi_backup_uploader_illegal_argument_exception_e");
        cancelJob(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelJob(int i) {
        JobScheduler jobScheduler = (JobScheduler) getApplicationContext().getSystemService("jobscheduler");
        if (jobScheduler != null) {
            jobScheduler.cancel(i);
        }
    }
}

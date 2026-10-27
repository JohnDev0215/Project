package com.android.providers.downloads;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.Environment;

/* JADX INFO: loaded from: classes.dex */
public class MediaScanTriggerJob extends JobService {
    private volatile boolean mJobStopped;

    @Override // android.app.job.JobService
    public boolean onStartJob(final JobParameters jobParameters) {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            jobFinished(jobParameters, true);
            return false;
        }
        Helpers.getAsyncHandler().post(new Runnable() { // from class: com.android.providers.downloads.-$$Lambda$MediaScanTriggerJob$bYo3kaowTqzFRItWXCsuH0OwQ2o
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onStartJob$0$MediaScanTriggerJob(jobParameters);
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00d6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX INFO: renamed from: lambda$onStartJob$0, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public /* synthetic */ void lambda$onStartJob$0$MediaScanTriggerJob(android.app.job.JobParameters r12) {
        /*
            Method dump skipped, instruction units count: 235
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.downloads.MediaScanTriggerJob.lambda$onStartJob$0$MediaScanTriggerJob(android.app.job.JobParameters):void");
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        this.mJobStopped = true;
        return true;
    }

    public static void schedule(Context context) {
        ((JobScheduler) context.getSystemService(JobScheduler.class)).schedule(new JobInfo.Builder(-101, new ComponentName(context, (Class<?>) MediaScanTriggerJob.class)).setRequiresCharging(true).setRequiresDeviceIdle(true).build());
    }
}

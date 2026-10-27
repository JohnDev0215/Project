package com.android.providers.media;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.ContentProviderClient;
import android.content.Context;
import android.os.CancellationSignal;
import android.os.OperationCanceledException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class IdleService extends JobService {
    private static final int IDLE_JOB_ID = -200;
    private CancellationSignal mSignal;

    @Override // android.app.job.JobService
    public boolean onStartJob(final JobParameters jobParameters) {
        this.mSignal = new CancellationSignal();
        new Thread(new Runnable() { // from class: com.android.providers.media.-$$Lambda$IdleService$JjwnmffYz-dHZaOMAebPNoWHP20
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onStartJob$0$IdleService(jobParameters);
            }
        }).start();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onStartJob$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onStartJob$0$IdleService(JobParameters jobParameters) {
        try {
            ContentProviderClient contentProviderClientAcquireContentProviderClient = getContentResolver().acquireContentProviderClient("media");
            try {
                ((MediaProvider) contentProviderClientAcquireContentProviderClient.getLocalContentProvider()).onIdleMaintenance(this.mSignal);
                if (contentProviderClientAcquireContentProviderClient != null) {
                    contentProviderClientAcquireContentProviderClient.close();
                }
            } finally {
            }
        } catch (OperationCanceledException unused) {
        }
        jobFinished(jobParameters, false);
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        this.mSignal.cancel();
        return false;
    }

    public static void scheduleIdlePass(Context context) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(JobScheduler.class);
        if (jobScheduler.getPendingJob(IDLE_JOB_ID) == null) {
            jobScheduler.schedule(new JobInfo.Builder(IDLE_JOB_ID, new ComponentName(context, (Class<?>) IdleService.class)).setPeriodic(TimeUnit.HOURS.toMillis(24L)).setRequiresCharging(true).setRequiresDeviceIdle(true).build());
        }
    }
}

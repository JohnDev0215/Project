package org.chromium.components.background_task_scheduler.internal;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.SystemClock;
import android.util.Log;
import defpackage.a73;
import defpackage.fc;
import defpackage.kc;
import defpackage.lc;
import defpackage.oj2;
import defpackage.qh1;
import defpackage.uv;
import defpackage.vd1;
import defpackage.xz;
import java.util.HashMap;
import java.util.concurrent.FutureTask;
import org.chromium.base.ThreadUtils;

/* JADX INFO: loaded from: classes2.dex */
public class BackgroundTaskJobService extends JobService {
    public final qh1 e = new qh1(17);
    public final HashMap f = new HashMap();

    public static class a implements fc.a {
        public final BackgroundTaskJobService e;
        public final fc f;
        public final JobParameters g;
        public final long h = SystemClock.uptimeMillis();

        /* JADX INFO: renamed from: org.chromium.components.background_task_scheduler.internal.BackgroundTaskJobService$a$a, reason: collision with other inner class name */
        public class RunnableC0119a implements Runnable {
            public final /* synthetic */ boolean e;

            public RunnableC0119a(boolean z) {
                this.e = z;
            }

            @Override // java.lang.Runnable
            public final void run() {
                String str;
                a aVar = a.this;
                HashMap map = aVar.e.f;
                JobParameters jobParameters = aVar.g;
                if (!(map.get(Integer.valueOf(jobParameters.getJobId())) == aVar.f)) {
                    Log.e("cr_BkgrdTaskJS", "Tried finishing non-current BackgroundTask.");
                    return;
                }
                BackgroundTaskJobService backgroundTaskJobService = aVar.e;
                backgroundTaskJobService.f.remove(Integer.valueOf(jobParameters.getJobId()));
                backgroundTaskJobService.jobFinished(jobParameters, this.e);
                lc lcVarL = lc.L();
                int jobId = jobParameters.getJobId();
                long jUptimeMillis = SystemClock.uptimeMillis() - aVar.h;
                lcVarL.getClass();
                StringBuilder sb = new StringBuilder("Android.BackgroundTaskScheduler.TaskFinished.");
                if (jobId == 1) {
                    str = "Gcm";
                } else if (jobId == 2) {
                    str = "ComponentUpdate";
                } else if (jobId == 21) {
                    str = "NotificationService";
                } else if (jobId == 22) {
                    str = "FeedRefresh";
                } else if (jobId == 42) {
                    str = "WebviewMinidumpUploading";
                } else if (jobId == 43) {
                    str = "ChromeMinidumpUploading";
                } else if (jobId == 83) {
                    str = "WebviewVariationsSeedFetch";
                } else if (jobId == 91) {
                    str = "WebApkUpdate";
                } else if (jobId == 110) {
                    str = "WebviewComponentUpdate";
                } else if (jobId == 33656) {
                    str = "Test";
                } else if (jobId != 71300) {
                    switch (jobId) {
                        case 53:
                            str = "DownloadService";
                            break;
                        case 54:
                            str = "DownloadCleanup";
                            break;
                        case 55:
                            str = "DeprecatedDownloadResumption";
                            break;
                        case 56:
                            str = "DownloadAutoResumption";
                            break;
                        case 57:
                            str = "DownloadLater";
                            break;
                        default:
                            switch (jobId) {
                                case 77:
                                    str = "OfflinePages";
                                    break;
                                case 78:
                                    str = "OfflinePagesPrefetch";
                                    break;
                                case 79:
                                    str = "OfflinePagesPrefetchNotification";
                                    break;
                                default:
                                    switch (jobId) {
                                        case 102:
                                            str = "BackgroundSyncOneShot";
                                            break;
                                        case 103:
                                            str = "NotificationScheduler";
                                            break;
                                        case 104:
                                            str = "NotificationTrigger";
                                            break;
                                        case 105:
                                            str = "PeriodicBackgroundSyncChromeWakeup";
                                            break;
                                        case 106:
                                            str = "QueryTile";
                                            break;
                                        case 107:
                                            str = "FeedV2Refresh";
                                            break;
                                        default:
                                            str = null;
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                } else {
                    str = "Omaha";
                }
                sb.append(str);
                oj2.T(sb.toString(), jUptimeMillis, 1L, 86400000L, 50);
            }
        }

        public a(BackgroundTaskJobService backgroundTaskJobService, fc fcVar, JobParameters jobParameters) {
            this.e = backgroundTaskJobService;
            this.f = fcVar;
            this.g = jobParameters;
        }

        @Override // fc.a
        public final void b(boolean z) {
            RunnableC0119a runnableC0119a = new RunnableC0119a(z);
            if (ThreadUtils.g()) {
                runnableC0119a.run();
                return;
            }
            FutureTask futureTask = new FutureTask(runnableC0119a, null);
            ThreadUtils.c().post(futureTask);
            try {
                futureTask.get();
            } catch (Exception e) {
                throw new uv("Exception occurred while waiting for runnable", e);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00ff  */
    @Override // android.app.job.JobService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onStartJob(android.app.job.JobParameters r13) {
        /*
            Method dump skipped, instruction units count: 356
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.background_task_scheduler.internal.BackgroundTaskJobService.onStartJob(android.app.job.JobParameters):boolean");
    }

    @Override // android.app.job.JobService
    public final boolean onStopJob(JobParameters jobParameters) {
        Object obj = ThreadUtils.a;
        HashMap map = this.f;
        if (!map.containsKey(Integer.valueOf(jobParameters.getJobId()))) {
            Log.w("cr_BkgrdTaskJS", "Failed to stop job, because job with job id " + jobParameters.getJobId() + " does not exist.");
            return false;
        }
        fc fcVar = (fc) map.get(Integer.valueOf(jobParameters.getJobId()));
        a73 a73VarA = kc.a(jobParameters);
        lc lcVarL = lc.L();
        int iH = vd1.H(a73VarA.a);
        lcVarL.getClass();
        lc.J(iH, "Android.BackgroundTaskScheduler.TaskStopped");
        boolean zB = fcVar.b(xz.a, a73VarA);
        map.remove(Integer.valueOf(jobParameters.getJobId()));
        return zB;
    }
}

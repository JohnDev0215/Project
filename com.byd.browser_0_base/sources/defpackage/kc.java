package defpackage;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.PersistableBundle;
import android.util.Log;
import defpackage.a73;
import java.util.Iterator;
import org.chromium.base.ThreadUtils;
import org.chromium.components.background_task_scheduler.TaskInfo;
import org.chromium.components.background_task_scheduler.internal.BackgroundTaskJobService;

/* JADX INFO: compiled from: BackgroundTaskSchedulerJobService.java */
/* JADX INFO: loaded from: classes2.dex */
public final class kc implements ic {

    /* JADX INFO: compiled from: BackgroundTaskSchedulerJobService.java */
    public static class a implements TaskInfo.d {
        public final JobInfo.Builder a;
        public final PersistableBundle b;

        public a(JobInfo.Builder builder, PersistableBundle persistableBundle) {
            this.a = builder;
            this.b = persistableBundle;
        }

        @Override // org.chromium.components.background_task_scheduler.TaskInfo.d
        public final void a(TaskInfo.c cVar) {
            boolean z = cVar.d;
            PersistableBundle persistableBundle = this.b;
            long j = cVar.b;
            boolean z2 = cVar.c;
            long j2 = cVar.a;
            if (z) {
                persistableBundle.putLong("_background_task_schedule_time", System.currentTimeMillis());
                persistableBundle.putLong("_background_task_interval_time", j2);
                if (z2) {
                    persistableBundle.putLong("_background_task_flex_time", j);
                }
            }
            JobInfo.Builder builder = this.a;
            builder.setExtras(persistableBundle);
            if (z2) {
                builder.setPeriodic(j2, j);
            } else {
                builder.setPeriodic(j2);
            }
        }

        @Override // org.chromium.components.background_task_scheduler.TaskInfo.d
        public final void b(TaskInfo.b bVar) {
            boolean z = bVar.d;
            PersistableBundle persistableBundle = this.b;
            long j = bVar.b;
            if (z) {
                persistableBundle.putLong("_background_task_schedule_time", System.currentTimeMillis());
                persistableBundle.putLong("_background_task_end_time", j);
            }
            JobInfo.Builder builder = this.a;
            builder.setExtras(persistableBundle);
            if (bVar.c) {
                builder.setMinimumLatency(bVar.a);
            }
            if (bVar.d) {
                j += 1000;
            }
            builder.setOverrideDeadline(j);
        }
    }

    static {
        new wd0(23);
    }

    public static a73 a(JobParameters jobParameters) {
        a73.a aVar = new a73.a(jobParameters.getJobId());
        PersistableBundle persistableBundle = jobParameters.getExtras().getPersistableBundle("_background_task_extras");
        PersistableBundle persistableBundle2 = new PersistableBundle();
        persistableBundle2.putAll(persistableBundle);
        aVar.b = persistableBundle2;
        return new a73(aVar);
    }

    public final boolean b(Context context, TaskInfo taskInfo) {
        boolean z;
        Object obj = ThreadUtils.a;
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putPersistableBundle("_background_task_extras", taskInfo.b);
        JobInfo.Builder requiredNetworkType = new JobInfo.Builder(taskInfo.a, new ComponentName(context, (Class<?>) BackgroundTaskJobService.class)).setPersisted(taskInfo.e).setRequiresCharging(taskInfo.d).setRequiredNetworkType(taskInfo.c);
        taskInfo.g.a(new a(requiredNetworkType, persistableBundle));
        JobInfo jobInfoBuild = requiredNetworkType.build();
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        if (!taskInfo.f) {
            Iterator<JobInfo> it = jobScheduler.getAllPendingJobs().iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                if (it.next().getId() == taskInfo.a) {
                    z = true;
                    break;
                }
            }
            if (z) {
                return true;
            }
        }
        try {
            return jobScheduler.schedule(jobInfoBuild) == 1;
        } catch (Exception e) {
            Log.e("cr_BkgrdTaskSchedulerJS", "Unable to schedule with Android.", e);
            return false;
        }
    }
}

package defpackage;

import android.app.job.JobScheduler;
import android.content.Context;
import android.util.Log;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.components.background_task_scheduler.TaskInfo;

/* JADX INFO: compiled from: BackgroundTaskSchedulerImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class jc implements hc {
    public final ic a;

    /* JADX INFO: compiled from: BackgroundTaskSchedulerImpl.java */
    public class a implements TaskInfo.d {
        public final int a;

        public a(int i) {
            this.a = i;
        }

        @Override // org.chromium.components.background_task_scheduler.TaskInfo.d
        public final void a(TaskInfo.c cVar) {
            lc lcVarL = lc.L();
            boolean z = cVar.d;
            lcVarL.getClass();
            int i = this.a;
            if (z) {
                lc.J(vd1.H(i), "Android.BackgroundTaskScheduler.TaskCreated.WithExpiration");
            } else {
                lc.J(vd1.H(i), "Android.BackgroundTaskScheduler.TaskCreated.WithoutExpiration");
            }
        }

        @Override // org.chromium.components.background_task_scheduler.TaskInfo.d
        public final void b(TaskInfo.b bVar) {
            lc lcVarL = lc.L();
            boolean z = bVar.d;
            lcVarL.getClass();
            int i = this.a;
            if (z) {
                lc.J(vd1.H(i), "Android.BackgroundTaskScheduler.TaskCreated.WithExpiration");
            } else {
                lc.J(vd1.H(i), "Android.BackgroundTaskScheduler.TaskCreated.WithoutExpiration");
            }
        }
    }

    /* JADX INFO: compiled from: BackgroundTaskSchedulerImpl.java */
    public class b implements TaskInfo.d {
        public final Context a;
        public final TaskInfo b;
        public boolean c;

        public b(Context context, TaskInfo taskInfo) {
            this.a = context;
            this.b = taskInfo;
        }

        @Override // org.chromium.components.background_task_scheduler.TaskInfo.d
        public final void a(TaskInfo.c cVar) {
            ic icVar = jc.this.a;
            this.c = ((kc) icVar).b(this.a, this.b);
        }

        @Override // org.chromium.components.background_task_scheduler.TaskInfo.d
        public final void b(TaskInfo.b bVar) {
            ic icVar = jc.this.a;
            this.c = ((kc) icVar).b(this.a, this.b);
        }
    }

    public jc(kc kcVar) {
        this.a = kcVar;
    }

    public final void a(Context context, int i) {
        TraceEvent traceEventH0 = TraceEvent.H0("BackgroundTaskScheduler.cancel", Integer.toString(i));
        try {
            Object obj = ThreadUtils.a;
            lc lcVarL = lc.L();
            int iH = vd1.H(i);
            lcVarL.getClass();
            lc.J(iH, "Android.BackgroundTaskScheduler.TaskCanceled");
            ((kc) this.a).getClass();
            try {
                ((JobScheduler) context.getSystemService("jobscheduler")).cancel(i);
            } catch (NullPointerException unused) {
                Log.e("cr_BkgrdTaskSchedulerJS", "Failed to cancel task: " + i);
            }
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused2) {
                }
            }
            throw th;
        }
    }

    public final boolean b(Context context, TaskInfo taskInfo) {
        if (iv.e().g("ignore-background-tasks")) {
            return true;
        }
        int i = taskInfo.a;
        TaskInfo.TimingInfo timingInfo = taskInfo.g;
        TraceEvent traceEventH0 = TraceEvent.H0("BackgroundTaskScheduler.schedule", Integer.toString(i));
        try {
            Object obj = ThreadUtils.a;
            b bVar = new b(context, taskInfo);
            timingInfo.a(bVar);
            boolean z = bVar.c;
            lc lcVarL = lc.L();
            int i2 = taskInfo.a;
            if (z) {
                int iH = vd1.H(i2);
                lcVarL.getClass();
                lc.J(iH, "Android.BackgroundTaskScheduler.TaskScheduled.Success");
            } else {
                int iH2 = vd1.H(i2);
                lcVarL.getClass();
                lc.J(iH2, "Android.BackgroundTaskScheduler.TaskScheduled.Failure");
            }
            timingInfo.a(new a(i2));
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            return z;
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }
}

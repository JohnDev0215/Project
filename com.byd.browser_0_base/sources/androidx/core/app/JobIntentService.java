package androidx.core.app;

import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobServiceEngine;
import android.app.job.JobWorkItem;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class JobIntentService extends Service {
    public b e;
    public a f;

    public final class a extends AsyncTask<Void, Void, Void> {
        public a() {
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x004f A[SYNTHETIC] */
        @Override // android.os.AsyncTask
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Void doInBackground(java.lang.Void[] r5) {
            /*
                r4 = this;
                java.lang.Void[] r5 = (java.lang.Void[]) r5
            L2:
                androidx.core.app.JobIntentService r5 = androidx.core.app.JobIntentService.this
                androidx.core.app.JobIntentService$b r5 = r5.e
                r5.getClass()
                java.lang.Object r0 = r5.b
                monitor-enter(r0)
                android.app.job.JobParameters r1 = r5.c     // Catch: java.lang.Throwable -> L50
                r2 = 0
                if (r1 != 0) goto L13
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L50
                goto L2d
            L13:
                android.app.job.JobWorkItem r1 = r1.dequeueWork()     // Catch: java.lang.Throwable -> L50
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L50
                if (r1 == 0) goto L2d
                android.content.Intent r0 = r1.getIntent()
                androidx.core.app.JobIntentService r3 = r5.a
                java.lang.ClassLoader r3 = r3.getClassLoader()
                r0.setExtrasClassLoader(r3)
                androidx.core.app.JobIntentService$b$a r0 = new androidx.core.app.JobIntentService$b$a
                r0.<init>(r1)
                goto L2e
            L2d:
                r0 = r2
            L2e:
                if (r0 == 0) goto L4f
                androidx.core.app.JobIntentService r5 = androidx.core.app.JobIntentService.this
                android.app.job.JobWorkItem r1 = r0.a
                r1.getIntent()
                r5.a()
                androidx.core.app.JobIntentService$b r5 = androidx.core.app.JobIntentService.b.this
                java.lang.Object r5 = r5.b
                monitor-enter(r5)
                androidx.core.app.JobIntentService$b r1 = androidx.core.app.JobIntentService.b.this     // Catch: java.lang.Throwable -> L4c
                android.app.job.JobParameters r1 = r1.c     // Catch: java.lang.Throwable -> L4c
                if (r1 == 0) goto L4a
                android.app.job.JobWorkItem r0 = r0.a     // Catch: java.lang.Throwable -> L4c
                r1.completeWork(r0)     // Catch: java.lang.Throwable -> L4c
            L4a:
                monitor-exit(r5)     // Catch: java.lang.Throwable -> L4c
                goto L2
            L4c:
                r4 = move-exception
                monitor-exit(r5)     // Catch: java.lang.Throwable -> L4c
                throw r4
            L4f:
                return r2
            L50:
                r4 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L50
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.JobIntentService.a.doInBackground(java.lang.Object[]):java.lang.Object");
        }

        @Override // android.os.AsyncTask
        public final void onCancelled(Void r1) {
            JobIntentService.this.getClass();
        }

        @Override // android.os.AsyncTask
        public final void onPostExecute(Void r1) {
            JobIntentService.this.getClass();
        }
    }

    public static final class b extends JobServiceEngine {
        public final JobIntentService a;
        public final Object b;
        public JobParameters c;

        public final class a {
            public final JobWorkItem a;

            public a(JobWorkItem jobWorkItem) {
                this.a = jobWorkItem;
            }
        }

        public b(JobIntentService jobIntentService) {
            super(jobIntentService);
            this.b = new Object();
            this.a = jobIntentService;
        }

        @Override // android.app.job.JobServiceEngine
        public final boolean onStartJob(JobParameters jobParameters) {
            this.c = jobParameters;
            JobIntentService jobIntentService = this.a;
            if (jobIntentService.f != null) {
                return true;
            }
            a aVar = jobIntentService.new a();
            jobIntentService.f = aVar;
            aVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return true;
        }

        @Override // android.app.job.JobServiceEngine
        public final boolean onStopJob(JobParameters jobParameters) {
            a aVar = this.a.f;
            if (aVar != null) {
                aVar.cancel(false);
            }
            synchronized (this.b) {
                this.c = null;
            }
            return true;
        }
    }

    static {
        new HashMap();
    }

    public abstract void a();

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        b bVar = this.e;
        if (bVar != null) {
            return bVar.getBinder();
        }
        return null;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        this.e = new b(this);
    }

    @Override // android.app.Service
    public final void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }
}

package androidx.core.app;

import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobServiceEngine;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class JobIntentService extends Service {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final Object f1149b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final HashMap<ComponentName, h> f1150c = new HashMap<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    b f1151d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    h f1152e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    a f1153f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    boolean f1154g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    boolean f1155h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    boolean f1156i = false;
    final ArrayList<d> j;

    final class a extends AsyncTask<Void, Void, Void> {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            while (true) {
                e eVarA = JobIntentService.this.a();
                if (eVarA == null) {
                    return null;
                }
                JobIntentService.this.e(eVarA.getIntent());
                eVarA.a();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onCancelled(Void r1) {
            JobIntentService.this.g();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r1) {
            JobIntentService.this.g();
        }
    }

    interface b {
        e a();

        IBinder b();
    }

    static final class c extends h {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final Context f1158d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final PowerManager.WakeLock f1159e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final PowerManager.WakeLock f1160f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        boolean f1161g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        boolean f1162h;

        c(Context context, ComponentName componentName) {
            super(componentName);
            this.f1158d = context.getApplicationContext();
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            PowerManager.WakeLock wakeLockNewWakeLock = powerManager.newWakeLock(1, componentName.getClassName() + ":launch");
            this.f1159e = wakeLockNewWakeLock;
            wakeLockNewWakeLock.setReferenceCounted(false);
            PowerManager.WakeLock wakeLockNewWakeLock2 = powerManager.newWakeLock(1, componentName.getClassName() + ":run");
            this.f1160f = wakeLockNewWakeLock2;
            wakeLockNewWakeLock2.setReferenceCounted(false);
        }

        @Override // androidx.core.app.JobIntentService.h
        public void b() {
            synchronized (this) {
                if (this.f1162h) {
                    if (this.f1161g) {
                        this.f1159e.acquire(60000L);
                    }
                    this.f1162h = false;
                    this.f1160f.release();
                }
            }
        }

        @Override // androidx.core.app.JobIntentService.h
        public void c() {
            synchronized (this) {
                if (!this.f1162h) {
                    this.f1162h = true;
                    this.f1160f.acquire(600000L);
                    this.f1159e.release();
                }
            }
        }

        @Override // androidx.core.app.JobIntentService.h
        public void d() {
            synchronized (this) {
                this.f1161g = false;
            }
        }
    }

    final class d implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Intent f1163a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f1164b;

        d(Intent intent, int i2) {
            this.f1163a = intent;
            this.f1164b = i2;
        }

        @Override // androidx.core.app.JobIntentService.e
        public void a() {
            JobIntentService.this.stopSelf(this.f1164b);
        }

        @Override // androidx.core.app.JobIntentService.e
        public Intent getIntent() {
            return this.f1163a;
        }
    }

    interface e {
        void a();

        Intent getIntent();
    }

    static final class f extends JobServiceEngine implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final JobIntentService f1166a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Object f1167b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        JobParameters f1168c;

        final class a implements e {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final JobWorkItem f1169a;

            a(JobWorkItem jobWorkItem) {
                this.f1169a = jobWorkItem;
            }

            @Override // androidx.core.app.JobIntentService.e
            public void a() {
                synchronized (f.this.f1167b) {
                    JobParameters jobParameters = f.this.f1168c;
                    if (jobParameters != null) {
                        jobParameters.completeWork(this.f1169a);
                    }
                }
            }

            @Override // androidx.core.app.JobIntentService.e
            public Intent getIntent() {
                return this.f1169a.getIntent();
            }
        }

        f(JobIntentService jobIntentService) {
            super(jobIntentService);
            this.f1167b = new Object();
            this.f1166a = jobIntentService;
        }

        @Override // androidx.core.app.JobIntentService.b
        public e a() {
            synchronized (this.f1167b) {
                JobParameters jobParameters = this.f1168c;
                if (jobParameters == null) {
                    return null;
                }
                JobWorkItem jobWorkItemDequeueWork = jobParameters.dequeueWork();
                if (jobWorkItemDequeueWork == null) {
                    return null;
                }
                jobWorkItemDequeueWork.getIntent().setExtrasClassLoader(this.f1166a.getClassLoader());
                return new a(jobWorkItemDequeueWork);
            }
        }

        @Override // androidx.core.app.JobIntentService.b
        public IBinder b() {
            return getBinder();
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStartJob(JobParameters jobParameters) {
            this.f1168c = jobParameters;
            this.f1166a.c(false);
            return true;
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStopJob(JobParameters jobParameters) {
            boolean zB = this.f1166a.b();
            synchronized (this.f1167b) {
                this.f1168c = null;
            }
            return zB;
        }
    }

    static final class g extends h {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final JobInfo f1171d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final JobScheduler f1172e;

        g(Context context, ComponentName componentName, int i2) {
            super(componentName);
            a(i2);
            this.f1171d = new JobInfo.Builder(i2, this.f1173a).setOverrideDeadline(0L).build();
            this.f1172e = (JobScheduler) context.getApplicationContext().getSystemService("jobscheduler");
        }
    }

    static abstract class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final ComponentName f1173a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f1174b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1175c;

        h(ComponentName componentName) {
            this.f1173a = componentName;
        }

        void a(int i2) {
            if (!this.f1174b) {
                this.f1174b = true;
                this.f1175c = i2;
            } else {
                if (this.f1175c == i2) {
                    return;
                }
                throw new IllegalArgumentException("Given job ID " + i2 + " is different than previous " + this.f1175c);
            }
        }

        public void b() {
        }

        public void c() {
        }

        public void d() {
        }
    }

    public JobIntentService() {
        if (Build.VERSION.SDK_INT >= 26) {
            this.j = null;
        } else {
            this.j = new ArrayList<>();
        }
    }

    static h d(Context context, ComponentName componentName, boolean z, int i2) {
        h cVar;
        HashMap<ComponentName, h> map = f1150c;
        h hVar = map.get(componentName);
        if (hVar != null) {
            return hVar;
        }
        if (Build.VERSION.SDK_INT < 26) {
            cVar = new c(context, componentName);
        } else {
            if (!z) {
                throw new IllegalArgumentException("Can't be here without a job id");
            }
            cVar = new g(context, componentName, i2);
        }
        h hVar2 = cVar;
        map.put(componentName, hVar2);
        return hVar2;
    }

    e a() {
        b bVar = this.f1151d;
        if (bVar != null) {
            return bVar.a();
        }
        synchronized (this.j) {
            if (this.j.size() <= 0) {
                return null;
            }
            return this.j.remove(0);
        }
    }

    boolean b() {
        a aVar = this.f1153f;
        if (aVar != null) {
            aVar.cancel(this.f1154g);
        }
        this.f1155h = true;
        return f();
    }

    void c(boolean z) {
        if (this.f1153f == null) {
            this.f1153f = new a();
            h hVar = this.f1152e;
            if (hVar != null && z) {
                hVar.c();
            }
            this.f1153f.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    protected abstract void e(Intent intent);

    public boolean f() {
        return true;
    }

    void g() {
        ArrayList<d> arrayList = this.j;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.f1153f = null;
                ArrayList<d> arrayList2 = this.j;
                if (arrayList2 != null && arrayList2.size() > 0) {
                    c(false);
                } else if (!this.f1156i) {
                    this.f1152e.b();
                }
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        b bVar = this.f1151d;
        if (bVar != null) {
            return bVar.b();
        }
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 26) {
            this.f1151d = new f(this);
            this.f1152e = null;
        } else {
            this.f1151d = null;
            this.f1152e = d(this, new ComponentName(this, getClass()), false, 0);
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ArrayList<d> arrayList = this.j;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.f1156i = true;
                this.f1152e.b();
            }
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        if (this.j == null) {
            return 2;
        }
        this.f1152e.d();
        synchronized (this.j) {
            ArrayList<d> arrayList = this.j;
            if (intent == null) {
                intent = new Intent();
            }
            arrayList.add(new d(intent, i3));
            c(true);
        }
        return 3;
    }
}

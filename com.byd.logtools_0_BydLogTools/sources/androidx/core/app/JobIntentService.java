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
    static final Object f1223b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final HashMap<ComponentName, h> f1224c = new HashMap<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    b f1225d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    h f1226e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    a f1227f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    boolean f1228g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    boolean f1229h = false;
    boolean i = false;
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
        private final Context f1231d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final PowerManager.WakeLock f1232e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final PowerManager.WakeLock f1233f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        boolean f1234g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        boolean f1235h;

        c(Context context, ComponentName componentName) {
            super(componentName);
            this.f1231d = context.getApplicationContext();
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            PowerManager.WakeLock wakeLockNewWakeLock = powerManager.newWakeLock(1, componentName.getClassName() + ":launch");
            this.f1232e = wakeLockNewWakeLock;
            wakeLockNewWakeLock.setReferenceCounted(false);
            PowerManager.WakeLock wakeLockNewWakeLock2 = powerManager.newWakeLock(1, componentName.getClassName() + ":run");
            this.f1233f = wakeLockNewWakeLock2;
            wakeLockNewWakeLock2.setReferenceCounted(false);
        }

        @Override // androidx.core.app.JobIntentService.h
        public void b() {
            synchronized (this) {
                if (this.f1235h) {
                    if (this.f1234g) {
                        this.f1232e.acquire(60000L);
                    }
                    this.f1235h = false;
                    this.f1233f.release();
                }
            }
        }

        @Override // androidx.core.app.JobIntentService.h
        public void c() {
            synchronized (this) {
                if (!this.f1235h) {
                    this.f1235h = true;
                    this.f1233f.acquire(600000L);
                    this.f1232e.release();
                }
            }
        }

        @Override // androidx.core.app.JobIntentService.h
        public void d() {
            synchronized (this) {
                this.f1234g = false;
            }
        }
    }

    final class d implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Intent f1236a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f1237b;

        d(Intent intent, int i) {
            this.f1236a = intent;
            this.f1237b = i;
        }

        @Override // androidx.core.app.JobIntentService.e
        public void a() {
            JobIntentService.this.stopSelf(this.f1237b);
        }

        @Override // androidx.core.app.JobIntentService.e
        public Intent getIntent() {
            return this.f1236a;
        }
    }

    interface e {
        void a();

        Intent getIntent();
    }

    static final class f extends JobServiceEngine implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final JobIntentService f1239a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Object f1240b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        JobParameters f1241c;

        final class a implements e {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final JobWorkItem f1242a;

            a(JobWorkItem jobWorkItem) {
                this.f1242a = jobWorkItem;
            }

            @Override // androidx.core.app.JobIntentService.e
            public void a() {
                synchronized (f.this.f1240b) {
                    JobParameters jobParameters = f.this.f1241c;
                    if (jobParameters != null) {
                        jobParameters.completeWork(this.f1242a);
                    }
                }
            }

            @Override // androidx.core.app.JobIntentService.e
            public Intent getIntent() {
                return this.f1242a.getIntent();
            }
        }

        f(JobIntentService jobIntentService) {
            super(jobIntentService);
            this.f1240b = new Object();
            this.f1239a = jobIntentService;
        }

        @Override // androidx.core.app.JobIntentService.b
        public e a() {
            synchronized (this.f1240b) {
                JobParameters jobParameters = this.f1241c;
                if (jobParameters == null) {
                    return null;
                }
                JobWorkItem jobWorkItemDequeueWork = jobParameters.dequeueWork();
                if (jobWorkItemDequeueWork == null) {
                    return null;
                }
                jobWorkItemDequeueWork.getIntent().setExtrasClassLoader(this.f1239a.getClassLoader());
                return new a(jobWorkItemDequeueWork);
            }
        }

        @Override // androidx.core.app.JobIntentService.b
        public IBinder b() {
            return getBinder();
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStartJob(JobParameters jobParameters) {
            this.f1241c = jobParameters;
            this.f1239a.c(false);
            return true;
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStopJob(JobParameters jobParameters) {
            boolean zB = this.f1239a.b();
            synchronized (this.f1240b) {
                this.f1241c = null;
            }
            return zB;
        }
    }

    static final class g extends h {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final JobInfo f1244d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final JobScheduler f1245e;

        g(Context context, ComponentName componentName, int i) {
            super(componentName);
            a(i);
            this.f1244d = new JobInfo.Builder(i, this.f1246a).setOverrideDeadline(0L).build();
            this.f1245e = (JobScheduler) context.getApplicationContext().getSystemService("jobscheduler");
        }
    }

    static abstract class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final ComponentName f1246a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f1247b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1248c;

        h(ComponentName componentName) {
            this.f1246a = componentName;
        }

        void a(int i) {
            if (!this.f1247b) {
                this.f1247b = true;
                this.f1248c = i;
            } else {
                if (this.f1248c == i) {
                    return;
                }
                throw new IllegalArgumentException("Given job ID " + i + " is different than previous " + this.f1248c);
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

    static h d(Context context, ComponentName componentName, boolean z, int i) {
        h cVar;
        HashMap<ComponentName, h> map = f1224c;
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
            cVar = new g(context, componentName, i);
        }
        h hVar2 = cVar;
        map.put(componentName, hVar2);
        return hVar2;
    }

    e a() {
        b bVar = this.f1225d;
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
        a aVar = this.f1227f;
        if (aVar != null) {
            aVar.cancel(this.f1228g);
        }
        this.f1229h = true;
        return f();
    }

    void c(boolean z) {
        if (this.f1227f == null) {
            this.f1227f = new a();
            h hVar = this.f1226e;
            if (hVar != null && z) {
                hVar.c();
            }
            this.f1227f.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
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
                this.f1227f = null;
                ArrayList<d> arrayList2 = this.j;
                if (arrayList2 != null && arrayList2.size() > 0) {
                    c(false);
                } else if (!this.i) {
                    this.f1226e.b();
                }
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        b bVar = this.f1225d;
        if (bVar != null) {
            return bVar.b();
        }
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 26) {
            this.f1225d = new f(this);
            this.f1226e = null;
        } else {
            this.f1225d = null;
            this.f1226e = d(this, new ComponentName(this, getClass()), false, 0);
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ArrayList<d> arrayList = this.j;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.i = true;
                this.f1226e.b();
            }
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (this.j == null) {
            return 2;
        }
        this.f1226e.d();
        synchronized (this.j) {
            ArrayList<d> arrayList = this.j;
            if (intent == null) {
                intent = new Intent();
            }
            arrayList.add(new d(intent, i2));
            c(true);
        }
        return 3;
    }
}

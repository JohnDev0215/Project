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

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f421c = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final HashMap<ComponentName, h> f422d = new HashMap<>();
    public boolean E3 = false;
    public boolean F3 = false;
    public boolean G3 = false;
    public final ArrayList<d> H3;
    public b s;
    public h x;
    public a y;

    public final class a extends AsyncTask<Void, Void, Void> {
        public a() {
        }

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

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onCancelled(Void r1) {
            JobIntentService.this.g();
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r1) {
            JobIntentService.this.g();
        }
    }

    public interface b {
        e a();

        IBinder b();
    }

    public static final class c extends h {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Context f424d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final PowerManager.WakeLock f425e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final PowerManager.WakeLock f426f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f427g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f428h;

        public c(Context context, ComponentName componentName) {
            super(componentName);
            this.f424d = context.getApplicationContext();
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            PowerManager.WakeLock wakeLockNewWakeLock = powerManager.newWakeLock(1, componentName.getClassName() + ":launch");
            this.f425e = wakeLockNewWakeLock;
            wakeLockNewWakeLock.setReferenceCounted(false);
            PowerManager.WakeLock wakeLockNewWakeLock2 = powerManager.newWakeLock(1, componentName.getClassName() + ":run");
            this.f426f = wakeLockNewWakeLock2;
            wakeLockNewWakeLock2.setReferenceCounted(false);
        }

        @Override // androidx.core.app.JobIntentService.h
        public void b() {
            synchronized (this) {
                if (this.f428h) {
                    if (this.f427g) {
                        this.f425e.acquire(60000L);
                    }
                    this.f428h = false;
                    this.f426f.release();
                }
            }
        }

        @Override // androidx.core.app.JobIntentService.h
        public void c() {
            synchronized (this) {
                if (!this.f428h) {
                    this.f428h = true;
                    this.f426f.acquire(600000L);
                    this.f425e.release();
                }
            }
        }

        @Override // androidx.core.app.JobIntentService.h
        public void d() {
            synchronized (this) {
                this.f427g = false;
            }
        }
    }

    public final class d implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Intent f429a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f430b;

        public d(Intent intent, int i2) {
            this.f429a = intent;
            this.f430b = i2;
        }

        @Override // androidx.core.app.JobIntentService.e
        public void a() {
            JobIntentService.this.stopSelf(this.f430b);
        }

        @Override // androidx.core.app.JobIntentService.e
        public Intent getIntent() {
            return this.f429a;
        }
    }

    public interface e {
        void a();

        Intent getIntent();
    }

    public static final class f extends JobServiceEngine implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final JobIntentService f432a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Object f433b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public JobParameters f434c;

        public final class a implements e {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final JobWorkItem f435a;

            public a(JobWorkItem jobWorkItem) {
                this.f435a = jobWorkItem;
            }

            @Override // androidx.core.app.JobIntentService.e
            public void a() {
                synchronized (f.this.f433b) {
                    JobParameters jobParameters = f.this.f434c;
                    if (jobParameters != null) {
                        jobParameters.completeWork(this.f435a);
                    }
                }
            }

            @Override // androidx.core.app.JobIntentService.e
            public Intent getIntent() {
                return this.f435a.getIntent();
            }
        }

        public f(JobIntentService jobIntentService) {
            super(jobIntentService);
            this.f433b = new Object();
            this.f432a = jobIntentService;
        }

        @Override // androidx.core.app.JobIntentService.b
        public e a() {
            synchronized (this.f433b) {
                JobParameters jobParameters = this.f434c;
                if (jobParameters == null) {
                    return null;
                }
                JobWorkItem jobWorkItemDequeueWork = jobParameters.dequeueWork();
                if (jobWorkItemDequeueWork == null) {
                    return null;
                }
                jobWorkItemDequeueWork.getIntent().setExtrasClassLoader(this.f432a.getClassLoader());
                return new a(jobWorkItemDequeueWork);
            }
        }

        @Override // androidx.core.app.JobIntentService.b
        public IBinder b() {
            return getBinder();
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStartJob(JobParameters jobParameters) {
            this.f434c = jobParameters;
            this.f432a.c(false);
            return true;
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStopJob(JobParameters jobParameters) {
            boolean zB = this.f432a.b();
            synchronized (this.f433b) {
                this.f434c = null;
            }
            return zB;
        }
    }

    public static final class g extends h {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final JobInfo f437d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final JobScheduler f438e;

        public g(Context context, ComponentName componentName, int i2) {
            super(componentName);
            a(i2);
            this.f437d = new JobInfo.Builder(i2, this.f439a).setOverrideDeadline(0L).build();
            this.f438e = (JobScheduler) context.getApplicationContext().getSystemService("jobscheduler");
        }
    }

    public static abstract class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ComponentName f439a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f440b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f441c;

        public h(ComponentName componentName) {
            this.f439a = componentName;
        }

        public void a(int i2) {
            if (!this.f440b) {
                this.f440b = true;
                this.f441c = i2;
            } else {
                if (this.f441c == i2) {
                    return;
                }
                throw new IllegalArgumentException("Given job ID " + i2 + " is different than previous " + this.f441c);
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
            this.H3 = null;
        } else {
            this.H3 = new ArrayList<>();
        }
    }

    public static h d(Context context, ComponentName componentName, boolean z, int i2) {
        h cVar;
        HashMap<ComponentName, h> map = f422d;
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

    public e a() {
        b bVar = this.s;
        if (bVar != null) {
            return bVar.a();
        }
        synchronized (this.H3) {
            if (this.H3.size() <= 0) {
                return null;
            }
            return this.H3.remove(0);
        }
    }

    public boolean b() {
        a aVar = this.y;
        if (aVar != null) {
            aVar.cancel(this.E3);
        }
        this.F3 = true;
        return f();
    }

    public void c(boolean z) {
        if (this.y == null) {
            this.y = new a();
            h hVar = this.x;
            if (hVar != null && z) {
                hVar.c();
            }
            this.y.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public abstract void e(Intent intent);

    public boolean f() {
        return true;
    }

    public void g() {
        ArrayList<d> arrayList = this.H3;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.y = null;
                ArrayList<d> arrayList2 = this.H3;
                if (arrayList2 != null && arrayList2.size() > 0) {
                    c(false);
                } else if (!this.G3) {
                    this.x.b();
                }
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        b bVar = this.s;
        if (bVar != null) {
            return bVar.b();
        }
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 26) {
            this.s = new f(this);
            this.x = null;
        } else {
            this.s = null;
            this.x = d(this, new ComponentName(this, getClass()), false, 0);
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ArrayList<d> arrayList = this.H3;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.G3 = true;
                this.x.b();
            }
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        if (this.H3 == null) {
            return 2;
        }
        this.x.d();
        synchronized (this.H3) {
            ArrayList<d> arrayList = this.H3;
            if (intent == null) {
                intent = new Intent();
            }
            arrayList.add(new d(intent, i3));
            c(true);
        }
        return 3;
    }
}

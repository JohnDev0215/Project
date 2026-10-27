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
    public static final Object f760b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final HashMap<ComponentName, h> f761c = new HashMap<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b f762d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public h f763e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public a f764f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f765g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f766h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f767i = false;
    public final ArrayList<d> j;

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
        public final Context f769d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final PowerManager.WakeLock f770e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final PowerManager.WakeLock f771f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f772g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f773h;

        public c(Context context, ComponentName componentName) {
            super(componentName);
            this.f769d = context.getApplicationContext();
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            PowerManager.WakeLock wakeLockNewWakeLock = powerManager.newWakeLock(1, componentName.getClassName() + ":launch");
            this.f770e = wakeLockNewWakeLock;
            wakeLockNewWakeLock.setReferenceCounted(false);
            PowerManager.WakeLock wakeLockNewWakeLock2 = powerManager.newWakeLock(1, componentName.getClassName() + ":run");
            this.f771f = wakeLockNewWakeLock2;
            wakeLockNewWakeLock2.setReferenceCounted(false);
        }

        @Override // androidx.core.app.JobIntentService.h
        public void b() {
            synchronized (this) {
                if (this.f773h) {
                    if (this.f772g) {
                        this.f770e.acquire(60000L);
                    }
                    this.f773h = false;
                    this.f771f.release();
                }
            }
        }

        @Override // androidx.core.app.JobIntentService.h
        public void c() {
            synchronized (this) {
                if (!this.f773h) {
                    this.f773h = true;
                    this.f771f.acquire(600000L);
                    this.f770e.release();
                }
            }
        }

        @Override // androidx.core.app.JobIntentService.h
        public void d() {
            synchronized (this) {
                this.f772g = false;
            }
        }
    }

    public final class d implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Intent f774a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f775b;

        public d(Intent intent, int i2) {
            this.f774a = intent;
            this.f775b = i2;
        }

        @Override // androidx.core.app.JobIntentService.e
        public void a() {
            JobIntentService.this.stopSelf(this.f775b);
        }

        @Override // androidx.core.app.JobIntentService.e
        public Intent getIntent() {
            return this.f774a;
        }
    }

    public interface e {
        void a();

        Intent getIntent();
    }

    public static final class f extends JobServiceEngine implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final JobIntentService f777a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Object f778b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public JobParameters f779c;

        public final class a implements e {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final JobWorkItem f780a;

            public a(JobWorkItem jobWorkItem) {
                this.f780a = jobWorkItem;
            }

            @Override // androidx.core.app.JobIntentService.e
            public void a() {
                synchronized (f.this.f778b) {
                    JobParameters jobParameters = f.this.f779c;
                    if (jobParameters != null) {
                        jobParameters.completeWork(this.f780a);
                    }
                }
            }

            @Override // androidx.core.app.JobIntentService.e
            public Intent getIntent() {
                return this.f780a.getIntent();
            }
        }

        public f(JobIntentService jobIntentService) {
            super(jobIntentService);
            this.f778b = new Object();
            this.f777a = jobIntentService;
        }

        @Override // androidx.core.app.JobIntentService.b
        public e a() {
            synchronized (this.f778b) {
                JobParameters jobParameters = this.f779c;
                if (jobParameters == null) {
                    return null;
                }
                JobWorkItem jobWorkItemDequeueWork = jobParameters.dequeueWork();
                if (jobWorkItemDequeueWork == null) {
                    return null;
                }
                jobWorkItemDequeueWork.getIntent().setExtrasClassLoader(this.f777a.getClassLoader());
                return new a(jobWorkItemDequeueWork);
            }
        }

        @Override // androidx.core.app.JobIntentService.b
        public IBinder b() {
            return getBinder();
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStartJob(JobParameters jobParameters) {
            this.f779c = jobParameters;
            this.f777a.c(false);
            return true;
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStopJob(JobParameters jobParameters) {
            boolean zB = this.f777a.b();
            synchronized (this.f778b) {
                this.f779c = null;
            }
            return zB;
        }
    }

    public static final class g extends h {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final JobInfo f782d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final JobScheduler f783e;

        public g(Context context, ComponentName componentName, int i2) {
            super(componentName);
            a(i2);
            this.f782d = new JobInfo.Builder(i2, this.f784a).setOverrideDeadline(0L).build();
            this.f783e = (JobScheduler) context.getApplicationContext().getSystemService("jobscheduler");
        }
    }

    public static abstract class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ComponentName f784a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f785b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f786c;

        public h(ComponentName componentName) {
            this.f784a = componentName;
        }

        public void a(int i2) {
            if (!this.f785b) {
                this.f785b = true;
                this.f786c = i2;
            } else {
                if (this.f786c == i2) {
                    return;
                }
                throw new IllegalArgumentException("Given job ID " + i2 + " is different than previous " + this.f786c);
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

    public static h d(Context context, ComponentName componentName, boolean z, int i2) {
        h cVar;
        HashMap<ComponentName, h> map = f761c;
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
        b bVar = this.f762d;
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

    public boolean b() {
        a aVar = this.f764f;
        if (aVar != null) {
            aVar.cancel(this.f765g);
        }
        this.f766h = true;
        return f();
    }

    public void c(boolean z) {
        if (this.f764f == null) {
            this.f764f = new a();
            h hVar = this.f763e;
            if (hVar != null && z) {
                hVar.c();
            }
            this.f764f.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public abstract void e(Intent intent);

    public boolean f() {
        return true;
    }

    public void g() {
        ArrayList<d> arrayList = this.j;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.f764f = null;
                ArrayList<d> arrayList2 = this.j;
                if (arrayList2 != null && arrayList2.size() > 0) {
                    c(false);
                } else if (!this.f767i) {
                    this.f763e.b();
                }
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        b bVar = this.f762d;
        if (bVar != null) {
            return bVar.b();
        }
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 26) {
            this.f762d = new f(this);
            this.f763e = null;
        } else {
            this.f762d = null;
            this.f763e = d(this, new ComponentName(this, getClass()), false, 0);
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ArrayList<d> arrayList = this.j;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.f767i = true;
                this.f763e.b();
            }
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        if (this.j == null) {
            return 2;
        }
        this.f763e.d();
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

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
    public static final Object f1122b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final HashMap<ComponentName, h> f1123c = new HashMap<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b f1124d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public h f1125e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public a f1126f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1127g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1128h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1129i = false;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final ArrayList<d> f1130j;

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
        public void onCancelled(Void r12) {
            JobIntentService.this.g();
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r12) {
            JobIntentService.this.g();
        }
    }

    public interface b {
        e a();

        IBinder b();
    }

    public static final class c extends h {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Context f1132d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final PowerManager.WakeLock f1133e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final PowerManager.WakeLock f1134f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f1135g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f1136h;

        public c(Context context, ComponentName componentName) {
            super(componentName);
            this.f1132d = context.getApplicationContext();
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            PowerManager.WakeLock wakeLockNewWakeLock = powerManager.newWakeLock(1, componentName.getClassName() + ":launch");
            this.f1133e = wakeLockNewWakeLock;
            wakeLockNewWakeLock.setReferenceCounted(false);
            PowerManager.WakeLock wakeLockNewWakeLock2 = powerManager.newWakeLock(1, componentName.getClassName() + ":run");
            this.f1134f = wakeLockNewWakeLock2;
            wakeLockNewWakeLock2.setReferenceCounted(false);
        }

        @Override // androidx.core.app.JobIntentService.h
        public void b() {
            synchronized (this) {
                if (this.f1136h) {
                    if (this.f1135g) {
                        this.f1133e.acquire(60000L);
                    }
                    this.f1136h = false;
                    this.f1134f.release();
                }
            }
        }

        @Override // androidx.core.app.JobIntentService.h
        public void c() {
            synchronized (this) {
                if (!this.f1136h) {
                    this.f1136h = true;
                    this.f1134f.acquire(600000L);
                    this.f1133e.release();
                }
            }
        }

        @Override // androidx.core.app.JobIntentService.h
        public void d() {
            synchronized (this) {
                this.f1135g = false;
            }
        }
    }

    public final class d implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Intent f1137a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f1138b;

        public d(Intent intent, int i3) {
            this.f1137a = intent;
            this.f1138b = i3;
        }

        @Override // androidx.core.app.JobIntentService.e
        public void a() {
            JobIntentService.this.stopSelf(this.f1138b);
        }

        @Override // androidx.core.app.JobIntentService.e
        public Intent getIntent() {
            return this.f1137a;
        }
    }

    public interface e {
        void a();

        Intent getIntent();
    }

    public static final class f extends JobServiceEngine implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final JobIntentService f1140a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Object f1141b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public JobParameters f1142c;

        public final class a implements e {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final JobWorkItem f1143a;

            public a(JobWorkItem jobWorkItem) {
                this.f1143a = jobWorkItem;
            }

            @Override // androidx.core.app.JobIntentService.e
            public void a() {
                synchronized (f.this.f1141b) {
                    JobParameters jobParameters = f.this.f1142c;
                    if (jobParameters != null) {
                        jobParameters.completeWork(this.f1143a);
                    }
                }
            }

            @Override // androidx.core.app.JobIntentService.e
            public Intent getIntent() {
                return this.f1143a.getIntent();
            }
        }

        public f(JobIntentService jobIntentService) {
            super(jobIntentService);
            this.f1141b = new Object();
            this.f1140a = jobIntentService;
        }

        @Override // androidx.core.app.JobIntentService.b
        public e a() {
            synchronized (this.f1141b) {
                JobParameters jobParameters = this.f1142c;
                if (jobParameters == null) {
                    return null;
                }
                JobWorkItem jobWorkItemDequeueWork = jobParameters.dequeueWork();
                if (jobWorkItemDequeueWork == null) {
                    return null;
                }
                jobWorkItemDequeueWork.getIntent().setExtrasClassLoader(this.f1140a.getClassLoader());
                return new a(jobWorkItemDequeueWork);
            }
        }

        @Override // androidx.core.app.JobIntentService.b
        public IBinder b() {
            return getBinder();
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStartJob(JobParameters jobParameters) {
            this.f1142c = jobParameters;
            this.f1140a.c(false);
            return true;
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStopJob(JobParameters jobParameters) {
            boolean zB = this.f1140a.b();
            synchronized (this.f1141b) {
                this.f1142c = null;
            }
            return zB;
        }
    }

    public static final class g extends h {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final JobInfo f1145d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final JobScheduler f1146e;

        public g(Context context, ComponentName componentName, int i3) {
            super(componentName);
            a(i3);
            this.f1145d = new JobInfo.Builder(i3, this.f1147a).setOverrideDeadline(0L).build();
            this.f1146e = (JobScheduler) context.getApplicationContext().getSystemService("jobscheduler");
        }
    }

    public static abstract class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ComponentName f1147a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1148b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1149c;

        public h(ComponentName componentName) {
            this.f1147a = componentName;
        }

        public void a(int i3) {
            if (!this.f1148b) {
                this.f1148b = true;
                this.f1149c = i3;
            } else {
                if (this.f1149c == i3) {
                    return;
                }
                throw new IllegalArgumentException("Given job ID " + i3 + " is different than previous " + this.f1149c);
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
            this.f1130j = null;
        } else {
            this.f1130j = new ArrayList<>();
        }
    }

    public static h d(Context context, ComponentName componentName, boolean z3, int i3) {
        h cVar;
        HashMap<ComponentName, h> map = f1123c;
        h hVar = map.get(componentName);
        if (hVar != null) {
            return hVar;
        }
        if (Build.VERSION.SDK_INT < 26) {
            cVar = new c(context, componentName);
        } else {
            if (!z3) {
                throw new IllegalArgumentException("Can't be here without a job id");
            }
            cVar = new g(context, componentName, i3);
        }
        h hVar2 = cVar;
        map.put(componentName, hVar2);
        return hVar2;
    }

    public e a() {
        b bVar = this.f1124d;
        if (bVar != null) {
            return bVar.a();
        }
        synchronized (this.f1130j) {
            if (this.f1130j.size() <= 0) {
                return null;
            }
            return this.f1130j.remove(0);
        }
    }

    public boolean b() {
        a aVar = this.f1126f;
        if (aVar != null) {
            aVar.cancel(this.f1127g);
        }
        this.f1128h = true;
        return f();
    }

    public void c(boolean z3) {
        if (this.f1126f == null) {
            this.f1126f = new a();
            h hVar = this.f1125e;
            if (hVar != null && z3) {
                hVar.c();
            }
            this.f1126f.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public abstract void e(Intent intent);

    public boolean f() {
        return true;
    }

    public void g() {
        ArrayList<d> arrayList = this.f1130j;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.f1126f = null;
                ArrayList<d> arrayList2 = this.f1130j;
                if (arrayList2 != null && arrayList2.size() > 0) {
                    c(false);
                } else if (!this.f1129i) {
                    this.f1125e.b();
                }
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        b bVar = this.f1124d;
        if (bVar != null) {
            return bVar.b();
        }
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 26) {
            this.f1124d = new f(this);
            this.f1125e = null;
        } else {
            this.f1124d = null;
            this.f1125e = d(this, new ComponentName(this, getClass()), false, 0);
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ArrayList<d> arrayList = this.f1130j;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.f1129i = true;
                this.f1125e.b();
            }
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i3, int i4) {
        if (this.f1130j == null) {
            return 2;
        }
        this.f1125e.d();
        synchronized (this.f1130j) {
            ArrayList<d> arrayList = this.f1130j;
            if (intent == null) {
                intent = new Intent();
            }
            arrayList.add(new d(intent, i4));
            c(true);
        }
        return 3;
    }
}

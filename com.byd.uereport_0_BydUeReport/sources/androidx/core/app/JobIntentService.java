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

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Object f1211d = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final HashMap<ComponentName, h> f1212e = new HashMap<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b f1213f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public h f1214g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public a f1215h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1216i = false;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f1217j = false;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f1218k = false;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ArrayList<d> f1219l;

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
        public final Context f1221d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final PowerManager.WakeLock f1222e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final PowerManager.WakeLock f1223f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f1224g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f1225h;

        public c(Context context, ComponentName componentName) {
            super(componentName);
            this.f1221d = context.getApplicationContext();
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            PowerManager.WakeLock wakeLockNewWakeLock = powerManager.newWakeLock(1, componentName.getClassName() + ":launch");
            this.f1222e = wakeLockNewWakeLock;
            wakeLockNewWakeLock.setReferenceCounted(false);
            PowerManager.WakeLock wakeLockNewWakeLock2 = powerManager.newWakeLock(1, componentName.getClassName() + ":run");
            this.f1223f = wakeLockNewWakeLock2;
            wakeLockNewWakeLock2.setReferenceCounted(false);
        }

        @Override // androidx.core.app.JobIntentService.h
        public void b() {
            synchronized (this) {
                if (this.f1225h) {
                    if (this.f1224g) {
                        this.f1222e.acquire(60000L);
                    }
                    this.f1225h = false;
                    this.f1223f.release();
                }
            }
        }

        @Override // androidx.core.app.JobIntentService.h
        public void c() {
            synchronized (this) {
                if (!this.f1225h) {
                    this.f1225h = true;
                    this.f1223f.acquire(600000L);
                    this.f1222e.release();
                }
            }
        }

        @Override // androidx.core.app.JobIntentService.h
        public void d() {
            synchronized (this) {
                this.f1224g = false;
            }
        }
    }

    public final class d implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Intent f1226a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f1227b;

        public d(Intent intent, int i8) {
            this.f1226a = intent;
            this.f1227b = i8;
        }

        @Override // androidx.core.app.JobIntentService.e
        public void a() {
            JobIntentService.this.stopSelf(this.f1227b);
        }

        @Override // androidx.core.app.JobIntentService.e
        public Intent getIntent() {
            return this.f1226a;
        }
    }

    public interface e {
        void a();

        Intent getIntent();
    }

    public static final class f extends JobServiceEngine implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final JobIntentService f1229a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Object f1230b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public JobParameters f1231c;

        public final class a implements e {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final JobWorkItem f1232a;

            public a(JobWorkItem jobWorkItem) {
                this.f1232a = jobWorkItem;
            }

            @Override // androidx.core.app.JobIntentService.e
            public void a() {
                synchronized (f.this.f1230b) {
                    JobParameters jobParameters = f.this.f1231c;
                    if (jobParameters != null) {
                        jobParameters.completeWork(this.f1232a);
                    }
                }
            }

            @Override // androidx.core.app.JobIntentService.e
            public Intent getIntent() {
                return this.f1232a.getIntent();
            }
        }

        public f(JobIntentService jobIntentService) {
            super(jobIntentService);
            this.f1230b = new Object();
            this.f1229a = jobIntentService;
        }

        @Override // androidx.core.app.JobIntentService.b
        public e a() {
            synchronized (this.f1230b) {
                JobParameters jobParameters = this.f1231c;
                if (jobParameters == null) {
                    return null;
                }
                JobWorkItem jobWorkItemDequeueWork = jobParameters.dequeueWork();
                if (jobWorkItemDequeueWork == null) {
                    return null;
                }
                jobWorkItemDequeueWork.getIntent().setExtrasClassLoader(this.f1229a.getClassLoader());
                return new a(jobWorkItemDequeueWork);
            }
        }

        @Override // androidx.core.app.JobIntentService.b
        public IBinder b() {
            return getBinder();
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStartJob(JobParameters jobParameters) {
            this.f1231c = jobParameters;
            this.f1229a.c(false);
            return true;
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStopJob(JobParameters jobParameters) {
            boolean zB = this.f1229a.b();
            synchronized (this.f1230b) {
                this.f1231c = null;
            }
            return zB;
        }
    }

    public static final class g extends h {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final JobInfo f1234d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final JobScheduler f1235e;

        public g(Context context, ComponentName componentName, int i8) {
            super(componentName);
            a(i8);
            this.f1234d = new JobInfo.Builder(i8, this.f1236a).setOverrideDeadline(0L).build();
            this.f1235e = (JobScheduler) context.getApplicationContext().getSystemService("jobscheduler");
        }
    }

    public static abstract class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ComponentName f1236a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1237b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1238c;

        public h(ComponentName componentName) {
            this.f1236a = componentName;
        }

        public void a(int i8) {
            if (!this.f1237b) {
                this.f1237b = true;
                this.f1238c = i8;
            } else {
                if (this.f1238c == i8) {
                    return;
                }
                throw new IllegalArgumentException("Given job ID " + i8 + " is different than previous " + this.f1238c);
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
            this.f1219l = null;
        } else {
            this.f1219l = new ArrayList<>();
        }
    }

    public static h d(Context context, ComponentName componentName, boolean z7, int i8) {
        h cVar;
        HashMap<ComponentName, h> map = f1212e;
        h hVar = map.get(componentName);
        if (hVar != null) {
            return hVar;
        }
        if (Build.VERSION.SDK_INT < 26) {
            cVar = new c(context, componentName);
        } else {
            if (!z7) {
                throw new IllegalArgumentException("Can't be here without a job id");
            }
            cVar = new g(context, componentName, i8);
        }
        h hVar2 = cVar;
        map.put(componentName, hVar2);
        return hVar2;
    }

    public e a() {
        b bVar = this.f1213f;
        if (bVar != null) {
            return bVar.a();
        }
        synchronized (this.f1219l) {
            if (this.f1219l.size() <= 0) {
                return null;
            }
            return this.f1219l.remove(0);
        }
    }

    public boolean b() {
        a aVar = this.f1215h;
        if (aVar != null) {
            aVar.cancel(this.f1216i);
        }
        this.f1217j = true;
        return f();
    }

    public void c(boolean z7) {
        if (this.f1215h == null) {
            this.f1215h = new a();
            h hVar = this.f1214g;
            if (hVar != null && z7) {
                hVar.c();
            }
            this.f1215h.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public abstract void e(Intent intent);

    public boolean f() {
        return true;
    }

    public void g() {
        ArrayList<d> arrayList = this.f1219l;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.f1215h = null;
                ArrayList<d> arrayList2 = this.f1219l;
                if (arrayList2 != null && arrayList2.size() > 0) {
                    c(false);
                } else if (!this.f1218k) {
                    this.f1214g.b();
                }
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        b bVar = this.f1213f;
        if (bVar != null) {
            return bVar.b();
        }
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 26) {
            this.f1213f = new f(this);
            this.f1214g = null;
        } else {
            this.f1213f = null;
            this.f1214g = d(this, new ComponentName(this, getClass()), false, 0);
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ArrayList<d> arrayList = this.f1219l;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.f1218k = true;
                this.f1214g.b();
            }
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i8, int i9) {
        if (this.f1219l == null) {
            return 2;
        }
        this.f1214g.d();
        synchronized (this.f1219l) {
            ArrayList<d> arrayList = this.f1219l;
            if (intent == null) {
                intent = new Intent();
            }
            arrayList.add(new d(intent, i9));
            c(true);
        }
        return 3;
    }
}

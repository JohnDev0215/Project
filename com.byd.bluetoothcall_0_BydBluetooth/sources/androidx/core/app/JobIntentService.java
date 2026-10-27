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
    static final Object f1147b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final HashMap<ComponentName, h> f1148c = new HashMap<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    b f1149d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    h f1150e;
    a f;
    boolean g = false;
    boolean h = false;
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
        private final Context f1152d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final PowerManager.WakeLock f1153e;
        private final PowerManager.WakeLock f;
        boolean g;
        boolean h;

        c(Context context, ComponentName componentName) {
            super(componentName);
            this.f1152d = context.getApplicationContext();
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            PowerManager.WakeLock wakeLockNewWakeLock = powerManager.newWakeLock(1, componentName.getClassName() + ":launch");
            this.f1153e = wakeLockNewWakeLock;
            wakeLockNewWakeLock.setReferenceCounted(false);
            PowerManager.WakeLock wakeLockNewWakeLock2 = powerManager.newWakeLock(1, componentName.getClassName() + ":run");
            this.f = wakeLockNewWakeLock2;
            wakeLockNewWakeLock2.setReferenceCounted(false);
        }

        @Override // androidx.core.app.JobIntentService.h
        public void b() {
            synchronized (this) {
                if (this.h) {
                    if (this.g) {
                        this.f1153e.acquire(60000L);
                    }
                    this.h = false;
                    this.f.release();
                }
            }
        }

        @Override // androidx.core.app.JobIntentService.h
        public void c() {
            synchronized (this) {
                if (!this.h) {
                    this.h = true;
                    this.f.acquire(600000L);
                    this.f1153e.release();
                }
            }
        }

        @Override // androidx.core.app.JobIntentService.h
        public void d() {
            synchronized (this) {
                this.g = false;
            }
        }
    }

    final class d implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Intent f1154a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f1155b;

        d(Intent intent, int i) {
            this.f1154a = intent;
            this.f1155b = i;
        }

        @Override // androidx.core.app.JobIntentService.e
        public void a() {
            JobIntentService.this.stopSelf(this.f1155b);
        }

        @Override // androidx.core.app.JobIntentService.e
        public Intent getIntent() {
            return this.f1154a;
        }
    }

    interface e {
        void a();

        Intent getIntent();
    }

    static final class f extends JobServiceEngine implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final JobIntentService f1157a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Object f1158b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        JobParameters f1159c;

        final class a implements e {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final JobWorkItem f1160a;

            a(JobWorkItem jobWorkItem) {
                this.f1160a = jobWorkItem;
            }

            @Override // androidx.core.app.JobIntentService.e
            public void a() {
                synchronized (f.this.f1158b) {
                    JobParameters jobParameters = f.this.f1159c;
                    if (jobParameters != null) {
                        jobParameters.completeWork(this.f1160a);
                    }
                }
            }

            @Override // androidx.core.app.JobIntentService.e
            public Intent getIntent() {
                return this.f1160a.getIntent();
            }
        }

        f(JobIntentService jobIntentService) {
            super(jobIntentService);
            this.f1158b = new Object();
            this.f1157a = jobIntentService;
        }

        @Override // androidx.core.app.JobIntentService.b
        public e a() {
            synchronized (this.f1158b) {
                JobParameters jobParameters = this.f1159c;
                if (jobParameters == null) {
                    return null;
                }
                JobWorkItem jobWorkItemDequeueWork = jobParameters.dequeueWork();
                if (jobWorkItemDequeueWork == null) {
                    return null;
                }
                jobWorkItemDequeueWork.getIntent().setExtrasClassLoader(this.f1157a.getClassLoader());
                return new a(jobWorkItemDequeueWork);
            }
        }

        @Override // androidx.core.app.JobIntentService.b
        public IBinder b() {
            return getBinder();
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStartJob(JobParameters jobParameters) {
            this.f1159c = jobParameters;
            this.f1157a.c(false);
            return true;
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStopJob(JobParameters jobParameters) {
            boolean zB = this.f1157a.b();
            synchronized (this.f1158b) {
                this.f1159c = null;
            }
            return zB;
        }
    }

    static final class g extends h {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final JobInfo f1162d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final JobScheduler f1163e;

        g(Context context, ComponentName componentName, int i) {
            super(componentName);
            a(i);
            this.f1162d = new JobInfo.Builder(i, this.f1164a).setOverrideDeadline(0L).build();
            this.f1163e = (JobScheduler) context.getApplicationContext().getSystemService("jobscheduler");
        }
    }

    static abstract class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final ComponentName f1164a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f1165b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1166c;

        h(ComponentName componentName) {
            this.f1164a = componentName;
        }

        void a(int i) {
            if (!this.f1165b) {
                this.f1165b = true;
                this.f1166c = i;
            } else {
                if (this.f1166c == i) {
                    return;
                }
                throw new IllegalArgumentException("Given job ID " + i + " is different than previous " + this.f1166c);
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
        HashMap<ComponentName, h> map = f1148c;
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
        b bVar = this.f1149d;
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
        a aVar = this.f;
        if (aVar != null) {
            aVar.cancel(this.g);
        }
        this.h = true;
        return f();
    }

    void c(boolean z) {
        if (this.f == null) {
            this.f = new a();
            h hVar = this.f1150e;
            if (hVar != null && z) {
                hVar.c();
            }
            this.f.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
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
                this.f = null;
                ArrayList<d> arrayList2 = this.j;
                if (arrayList2 != null && arrayList2.size() > 0) {
                    c(false);
                } else if (!this.i) {
                    this.f1150e.b();
                }
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        b bVar = this.f1149d;
        if (bVar != null) {
            return bVar.b();
        }
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 26) {
            this.f1149d = new f(this);
            this.f1150e = null;
        } else {
            this.f1149d = null;
            this.f1150e = d(this, new ComponentName(this, getClass()), false, 0);
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ArrayList<d> arrayList = this.j;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.i = true;
                this.f1150e.b();
            }
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (this.j == null) {
            return 2;
        }
        this.f1150e.d();
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

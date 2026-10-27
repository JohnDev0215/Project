package androidx.core.app;

import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobServiceEngine;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public abstract class JobIntentService extends Service {
    static final HashMap<ComponentName, h> i = new HashMap<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    b f410c;
    h d;
    a e;
    boolean f = false;
    boolean g = false;
    final ArrayList<d> h;

    /* JADX INFO: compiled from: caradapter-bug */
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

    /* JADX INFO: compiled from: caradapter-bug */
    interface b {
        e a();

        IBinder b();
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static final class c extends h {
        private final PowerManager.WakeLock d;
        private final PowerManager.WakeLock e;
        boolean f;
        boolean g;

        c(Context context, ComponentName componentName) {
            super(componentName);
            context.getApplicationContext();
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            PowerManager.WakeLock wakeLockNewWakeLock = powerManager.newWakeLock(1, componentName.getClassName() + ":launch");
            this.d = wakeLockNewWakeLock;
            wakeLockNewWakeLock.setReferenceCounted(false);
            PowerManager.WakeLock wakeLockNewWakeLock2 = powerManager.newWakeLock(1, componentName.getClassName() + ":run");
            this.e = wakeLockNewWakeLock2;
            wakeLockNewWakeLock2.setReferenceCounted(false);
        }

        @Override // androidx.core.app.JobIntentService.h
        public void b() {
            synchronized (this) {
                if (this.g) {
                    if (this.f) {
                        this.d.acquire(60000L);
                    }
                    this.g = false;
                    this.e.release();
                }
            }
        }

        @Override // androidx.core.app.JobIntentService.h
        public void c() {
            synchronized (this) {
                if (!this.g) {
                    this.g = true;
                    this.e.acquire(600000L);
                    this.d.release();
                }
            }
        }

        @Override // androidx.core.app.JobIntentService.h
        public void d() {
            synchronized (this) {
                this.f = false;
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    final class d implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Intent f412a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f413b;

        d(Intent intent, int i) {
            this.f412a = intent;
            this.f413b = i;
        }

        @Override // androidx.core.app.JobIntentService.e
        public void a() {
            JobIntentService.this.stopSelf(this.f413b);
        }

        @Override // androidx.core.app.JobIntentService.e
        public Intent getIntent() {
            return this.f412a;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    interface e {
        void a();

        Intent getIntent();
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(26)
    static final class f extends JobServiceEngine implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final JobIntentService f415a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Object f416b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        JobParameters f417c;

        /* JADX INFO: compiled from: caradapter-bug */
        final class a implements e {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final JobWorkItem f418a;

            a(JobWorkItem jobWorkItem) {
                this.f418a = jobWorkItem;
            }

            @Override // androidx.core.app.JobIntentService.e
            public void a() {
                synchronized (f.this.f416b) {
                    if (f.this.f417c != null) {
                        f.this.f417c.completeWork(this.f418a);
                    }
                }
            }

            @Override // androidx.core.app.JobIntentService.e
            public Intent getIntent() {
                return this.f418a.getIntent();
            }
        }

        f(JobIntentService jobIntentService) {
            super(jobIntentService);
            this.f416b = new Object();
            this.f415a = jobIntentService;
        }

        @Override // androidx.core.app.JobIntentService.b
        public e a() {
            synchronized (this.f416b) {
                if (this.f417c == null) {
                    return null;
                }
                JobWorkItem jobWorkItemDequeueWork = this.f417c.dequeueWork();
                if (jobWorkItemDequeueWork == null) {
                    return null;
                }
                jobWorkItemDequeueWork.getIntent().setExtrasClassLoader(this.f415a.getClassLoader());
                return new a(jobWorkItemDequeueWork);
            }
        }

        @Override // androidx.core.app.JobIntentService.b
        public IBinder b() {
            return getBinder();
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStartJob(JobParameters jobParameters) {
            this.f417c = jobParameters;
            this.f415a.c(false);
            return true;
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStopJob(JobParameters jobParameters) {
            boolean zB = this.f415a.b();
            synchronized (this.f416b) {
                this.f417c = null;
            }
            return zB;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(26)
    static final class g extends h {
        g(Context context, ComponentName componentName, int i) {
            super(componentName);
            a(i);
            new JobInfo.Builder(i, this.f420a).setOverrideDeadline(0L).build();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static abstract class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final ComponentName f420a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f421b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f422c;

        h(ComponentName componentName) {
            this.f420a = componentName;
        }

        void a(int i) {
            if (!this.f421b) {
                this.f421b = true;
                this.f422c = i;
            } else {
                if (this.f422c == i) {
                    return;
                }
                throw new IllegalArgumentException("Given job ID " + i + " is different than previous " + this.f422c);
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
            this.h = null;
        } else {
            this.h = new ArrayList<>();
        }
    }

    static h d(Context context, ComponentName componentName, boolean z, int i2) {
        h cVar;
        h hVar = i.get(componentName);
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
        i.put(componentName, hVar2);
        return hVar2;
    }

    e a() {
        b bVar = this.f410c;
        if (bVar != null) {
            return bVar.a();
        }
        synchronized (this.h) {
            if (this.h.size() <= 0) {
                return null;
            }
            return this.h.remove(0);
        }
    }

    boolean b() {
        a aVar = this.e;
        if (aVar != null) {
            aVar.cancel(this.f);
        }
        return f();
    }

    void c(boolean z) {
        if (this.e == null) {
            this.e = new a();
            h hVar = this.d;
            if (hVar != null && z) {
                hVar.c();
            }
            this.e.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    protected abstract void e(@NonNull Intent intent);

    public boolean f() {
        return true;
    }

    void g() {
        ArrayList<d> arrayList = this.h;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.e = null;
                if (this.h != null && this.h.size() > 0) {
                    c(false);
                } else if (!this.g) {
                    this.d.b();
                }
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(@NonNull Intent intent) {
        b bVar = this.f410c;
        if (bVar != null) {
            return bVar.b();
        }
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 26) {
            this.f410c = new f(this);
            this.d = null;
        } else {
            this.f410c = null;
            this.d = d(this, new ComponentName(this, (Class<?>) JobIntentService.class), false, 0);
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ArrayList<d> arrayList = this.h;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.g = true;
                this.d.b();
            }
        }
    }

    @Override // android.app.Service
    public int onStartCommand(@Nullable Intent intent, int i2, int i3) {
        if (this.h == null) {
            return 2;
        }
        this.d.d();
        synchronized (this.h) {
            ArrayList<d> arrayList = this.h;
            if (intent == null) {
                intent = new Intent();
            }
            arrayList.add(new d(intent, i3));
            c(true);
        }
        return 3;
    }
}

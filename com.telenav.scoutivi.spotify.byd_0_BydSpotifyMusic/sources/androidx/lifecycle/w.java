package androidx.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.lifecycle.i;
import androidx.lifecycle.x;

/* JADX INFO: compiled from: ProcessLifecycleOwner.java */
/* JADX INFO: loaded from: classes.dex */
public class w implements o {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private static final w f2562n = new w();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private Handler f2567j;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f2563e = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f2564g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f2565h = true;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f2566i = true;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final p f2568k = new p(this);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private Runnable f2569l = new a();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    x.a f2570m = new b();

    /* JADX INFO: compiled from: ProcessLifecycleOwner.java */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            w.this.i();
            w.this.j();
        }
    }

    /* JADX INFO: compiled from: ProcessLifecycleOwner.java */
    class b implements x.a {
        b() {
        }

        @Override // androidx.lifecycle.x.a
        public void a() {
            w.this.f();
        }

        @Override // androidx.lifecycle.x.a
        public void b() {
        }

        @Override // androidx.lifecycle.x.a
        public void onResume() {
            w.this.e();
        }
    }

    /* JADX INFO: compiled from: ProcessLifecycleOwner.java */
    class c extends e {

        /* JADX INFO: compiled from: ProcessLifecycleOwner.java */
        class a extends e {
            a() {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPostResumed(Activity activity) {
                w.this.e();
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPostStarted(Activity activity) {
                w.this.f();
            }
        }

        c() {
        }

        @Override // androidx.lifecycle.e, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            if (Build.VERSION.SDK_INT < 29) {
                x.f(activity).h(w.this.f2570m);
            }
        }

        @Override // androidx.lifecycle.e, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            w.this.b();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreCreated(Activity activity, Bundle bundle) {
            activity.registerActivityLifecycleCallbacks(new a());
        }

        @Override // androidx.lifecycle.e, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            w.this.g();
        }
    }

    private w() {
    }

    static void k(Context context) {
        f2562n.h(context);
    }

    @Override // androidx.lifecycle.o
    public i a() {
        return this.f2568k;
    }

    void b() {
        int i7 = this.f2564g - 1;
        this.f2564g = i7;
        if (i7 == 0) {
            this.f2567j.postDelayed(this.f2569l, 700L);
        }
    }

    void e() {
        int i7 = this.f2564g + 1;
        this.f2564g = i7;
        if (i7 == 1) {
            if (!this.f2565h) {
                this.f2567j.removeCallbacks(this.f2569l);
            } else {
                this.f2568k.h(i.b.ON_RESUME);
                this.f2565h = false;
            }
        }
    }

    void f() {
        int i7 = this.f2563e + 1;
        this.f2563e = i7;
        if (i7 == 1 && this.f2566i) {
            this.f2568k.h(i.b.ON_START);
            this.f2566i = false;
        }
    }

    void g() {
        this.f2563e--;
        j();
    }

    void h(Context context) {
        this.f2567j = new Handler();
        this.f2568k.h(i.b.ON_CREATE);
        ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(new c());
    }

    void i() {
        if (this.f2564g == 0) {
            this.f2565h = true;
            this.f2568k.h(i.b.ON_PAUSE);
        }
    }

    void j() {
        if (this.f2563e == 0 && this.f2565h) {
            this.f2568k.h(i.b.ON_STOP);
            this.f2566i = true;
        }
    }
}

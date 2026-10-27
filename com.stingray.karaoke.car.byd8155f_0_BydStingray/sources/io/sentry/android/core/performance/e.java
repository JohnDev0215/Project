package io.sentry.android.core.performance;

import B8.t;
import android.app.Activity;
import android.app.Application;
import android.content.ContentProvider;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.stingray.karaoke.car.MainApplication;
import h6.i;
import io.sentry.C1510s1;
import io.sentry.android.core.C1440p;
import io.sentry.android.core.SentryAndroidOptions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class e extends a {

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final long f16275x = SystemClock.uptimeMillis();

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static volatile e f16276y;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f16277m;
    public d l = d.UNKNOWN;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public C1440p f16282s = null;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public t f16283t = null;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public C1510s1 f16284u = null;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f16285v = false;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f16286w = false;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final f f16278n = new f();
    public final f o = new f();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final f f16279p = new f();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final HashMap f16280q = new HashMap();

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final ArrayList f16281r = new ArrayList();

    public e() {
        this.f16277m = false;
        this.f16277m = i.t();
    }

    public static e b() {
        if (f16276y == null) {
            synchronized (e.class) {
                try {
                    if (f16276y == null) {
                        f16276y = new e();
                    }
                } finally {
                }
            }
        }
        return f16276y;
    }

    public static void c(MainApplication mainApplication) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        e eVarB = b();
        if (eVarB.f16279p.a()) {
            String strConcat = mainApplication.getClass().getName().concat(".onCreate");
            f fVar = eVarB.f16279p;
            fVar.l = strConcat;
            fVar.o = jUptimeMillis;
        }
    }

    public static void d(ContentProvider contentProvider) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        f fVar = new f();
        fVar.d(jUptimeMillis);
        b().f16280q.put(contentProvider, fVar);
    }

    public static void e(ContentProvider contentProvider) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        f fVar = (f) b().f16280q.get(contentProvider);
        if (fVar == null || !fVar.a()) {
            return;
        }
        fVar.l = contentProvider.getClass().getName().concat(".onCreate");
        fVar.o = jUptimeMillis;
    }

    public final f a(SentryAndroidOptions sentryAndroidOptions) {
        if (sentryAndroidOptions.isEnablePerformanceV2()) {
            f fVar = this.f16278n;
            if (fVar.b()) {
                return (this.f16285v || !this.f16277m) ? new f() : fVar;
            }
        }
        return (this.f16285v || !this.f16277m) ? new f() : this.o;
    }

    public final void f(Application application) {
        if (this.f16286w) {
            return;
        }
        boolean z6 = true;
        this.f16286w = true;
        if (!this.f16277m && !i.t()) {
            z6 = false;
        }
        this.f16277m = z6;
        application.registerActivityLifecycleCallbacks(f16276y);
        new Handler(Looper.getMainLooper()).post(new c(this, application, 0));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        long jCurrentTimeMillis;
        if (this.f16277m && this.f16284u == null) {
            this.f16284u = new C1510s1();
            f fVar = this.f16278n;
            long j3 = fVar.f16287m;
            if (fVar.c()) {
                if (fVar.b()) {
                    jCurrentTimeMillis = (fVar.c() ? fVar.o - fVar.f16288n : 0L) + fVar.f16287m;
                }
            } else {
                jCurrentTimeMillis = System.currentTimeMillis();
            }
            if (jCurrentTimeMillis - j3 > TimeUnit.MINUTES.toMillis(1L)) {
                this.f16285v = true;
            }
        }
    }
}

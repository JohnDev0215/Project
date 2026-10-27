package io.sentry.android.core;

import android.R;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.SparseIntArray;
import android.view.View;
import androidx.core.app.FrameMetricsAggregator;
import h3.C1350a;
import io.sentry.A0;
import io.sentry.AbstractC1453b1;
import io.sentry.C1507r1;
import io.sentry.C1510s1;
import io.sentry.C1520w;
import io.sentry.EnumC1494p0;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import io.sentry.W1;
import io.sentry.b2;
import io.sentry.c2;
import java.io.Closeable;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class ActivityLifecycleIntegration implements io.sentry.Z, Closeable, Application.ActivityLifecycleCallbacks {
    public final N0.q B;
    public final Application l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final B f16018m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public io.sentry.E f16019n;
    public SentryAndroidOptions o;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public io.sentry.U f16025u;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f16020p = false;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f16021q = false;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f16023s = false;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public C1520w f16024t = null;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final WeakHashMap f16026v = new WeakHashMap();

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final WeakHashMap f16027w = new WeakHashMap();

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public AbstractC1453b1 f16028x = new C1510s1(new Date(0), 0);

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final Handler f16029y = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public Future f16030z = null;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final WeakHashMap f16017A = new WeakHashMap();

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f16022r = true;

    public ActivityLifecycleIntegration(Application application, B b10, N0.q qVar) {
        this.l = application;
        this.f16018m = b10;
        this.B = qVar;
    }

    public static void g(io.sentry.U u4, io.sentry.U u10) {
        if (u4 == null || u4.g()) {
            return;
        }
        String strA = u4.a();
        if (strA == null || !strA.endsWith(" - Deadline Exceeded")) {
            strA = u4.a() + " - Deadline Exceeded";
        }
        u4.e(strA);
        AbstractC1453b1 abstractC1453b1T = u10 != null ? u10.t() : null;
        if (abstractC1453b1T == null) {
            abstractC1453b1T = u4.B();
        }
        i(u4, abstractC1453b1T, W1.DEADLINE_EXCEEDED);
    }

    public static void i(io.sentry.U u4, AbstractC1453b1 abstractC1453b1, W1 w1) {
        if (u4 == null || u4.g()) {
            return;
        }
        if (w1 == null) {
            w1 = u4.s() != null ? u4.s() : W1.OK;
        }
        u4.v(w1, abstractC1453b1);
    }

    public final void b() {
        C1507r1 c1507r1;
        io.sentry.android.core.performance.f fVarA = io.sentry.android.core.performance.e.b().a(this.o);
        if (fVarA.c()) {
            if (fVarA.b()) {
                j = (fVarA.c() ? fVarA.o - fVarA.f16288n : 0L) + fVarA.f16287m;
            }
            c1507r1 = new C1507r1(j * 1000000);
        } else {
            c1507r1 = null;
        }
        if (!this.f16020p || c1507r1 == null) {
            return;
        }
        i(this.f16025u, c1507r1, null);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.l.unregisterActivityLifecycleCallbacks(this);
        SentryAndroidOptions sentryAndroidOptions = this.o;
        if (sentryAndroidOptions != null) {
            sentryAndroidOptions.getLogger().k(EnumC1495p1.DEBUG, "ActivityLifecycleIntegration removed.", new Object[0]);
        }
        N0.q qVar = this.B;
        synchronized (qVar) {
            try {
                if (qVar.H()) {
                    qVar.Q(new A2.l(qVar, 16), "FrameMetricsAggregator.stop");
                    h2.s sVar = ((FrameMetricsAggregator) qVar.f4399m).f11736a;
                    Object obj = sVar.f15238b;
                    sVar.f15238b = new SparseIntArray[9];
                }
                ((ConcurrentHashMap) qVar.o).clear();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void j(io.sentry.V v10, io.sentry.U u4, io.sentry.U u10) {
        if (v10 == null || v10.g()) {
            return;
        }
        W1 w1 = W1.DEADLINE_EXCEEDED;
        if (u4 != null && !u4.g()) {
            u4.q(w1);
        }
        g(u10, u4);
        Future future = this.f16030z;
        if (future != null) {
            future.cancel(false);
            this.f16030z = null;
        }
        W1 w1S = v10.s();
        if (w1S == null) {
            w1S = W1.OK;
        }
        v10.q(w1S);
        io.sentry.E e10 = this.f16019n;
        if (e10 != null) {
            e10.l(new C1430f(this, v10, 0));
        }
    }

    @Override // io.sentry.Z
    public final void k(F1 f12) {
        io.sentry.E e10 = io.sentry.E.f15834a;
        SentryAndroidOptions sentryAndroidOptions = f12 instanceof SentryAndroidOptions ? (SentryAndroidOptions) f12 : null;
        h6.e.q(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.o = sentryAndroidOptions;
        this.f16019n = e10;
        this.f16020p = sentryAndroidOptions.isTracingEnabled() && sentryAndroidOptions.isEnableAutoActivityLifecycleTracing();
        this.f16024t = this.o.getFullyDisplayedReporter();
        this.f16021q = this.o.isEnableTimeToFullDisplayTracing();
        this.l.registerActivityLifecycleCallbacks(this);
        this.o.getLogger().k(EnumC1495p1.DEBUG, "ActivityLifecycleIntegration installed.", new Object[0]);
        n7.z.c("ActivityLifecycle");
    }

    public final void m(io.sentry.U u4, io.sentry.U u10) {
        io.sentry.android.core.performance.e eVarB = io.sentry.android.core.performance.e.b();
        io.sentry.android.core.performance.f fVar = eVarB.f16278n;
        if (fVar.b() && fVar.a()) {
            fVar.e();
        }
        io.sentry.android.core.performance.f fVar2 = eVarB.o;
        if (fVar2.b() && fVar2.a()) {
            fVar2.e();
        }
        b();
        SentryAndroidOptions sentryAndroidOptions = this.o;
        if (sentryAndroidOptions == null || u10 == null) {
            if (u10 == null || u10.g()) {
                return;
            }
            u10.z();
            return;
        }
        AbstractC1453b1 abstractC1453b1A = sentryAndroidOptions.getDateProvider().a();
        long millis = TimeUnit.NANOSECONDS.toMillis(abstractC1453b1A.b(u10.B()));
        Long lValueOf = Long.valueOf(millis);
        EnumC1494p0 enumC1494p0 = EnumC1494p0.MILLISECOND;
        u10.n("time_to_initial_display", lValueOf, enumC1494p0);
        if (u4 != null && u4.g()) {
            u4.k(abstractC1453b1A);
            u10.n("time_to_full_display", Long.valueOf(millis), enumC1494p0);
        }
        i(u10, abstractC1453b1A, null);
    }

    public final void o(Bundle bundle) {
        SentryAndroidOptions sentryAndroidOptions;
        if (this.f16019n != null && this.f16028x.d() == 0) {
            this.f16028x = this.f16019n.q().getDateProvider().a();
        } else if (this.f16028x.d() == 0) {
            AbstractC1433i.f16177a.getClass();
            this.f16028x = new C1510s1();
        }
        if (this.f16023s || (sentryAndroidOptions = this.o) == null || sentryAndroidOptions.isEnablePerformanceV2()) {
            return;
        }
        io.sentry.android.core.performance.e.b().l = bundle == null ? io.sentry.android.core.performance.d.COLD : io.sentry.android.core.performance.d.WARM;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final synchronized void onActivityCreated(Activity activity, Bundle bundle) {
        C1520w c1520w;
        SentryAndroidOptions sentryAndroidOptions;
        try {
            o(bundle);
            if (this.f16019n != null && (sentryAndroidOptions = this.o) != null && sentryAndroidOptions.isEnableScreenTracking()) {
                this.f16019n.l(new C1428d(h6.r.h(activity), 0));
            }
            q(activity);
            io.sentry.U u4 = (io.sentry.U) this.f16027w.get(activity);
            this.f16023s = true;
            if (this.f16020p && u4 != null && (c1520w = this.f16024t) != null) {
                c1520w.f16980a.add(new C1350a(11));
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final synchronized void onActivityDestroyed(Activity activity) {
        try {
            if (this.f16020p) {
                io.sentry.U u4 = this.f16025u;
                W1 w1 = W1.CANCELLED;
                if (u4 != null && !u4.g()) {
                    u4.q(w1);
                }
                io.sentry.U u10 = (io.sentry.U) this.f16026v.get(activity);
                io.sentry.U u11 = (io.sentry.U) this.f16027w.get(activity);
                W1 w12 = W1.DEADLINE_EXCEEDED;
                if (u10 != null && !u10.g()) {
                    u10.q(w12);
                }
                g(u11, u10);
                Future future = this.f16030z;
                if (future != null) {
                    future.cancel(false);
                    this.f16030z = null;
                }
                if (this.f16020p) {
                    j((io.sentry.V) this.f16017A.get(activity), null, null);
                }
                this.f16025u = null;
                this.f16026v.remove(activity);
                this.f16027w.remove(activity);
            }
            this.f16017A.remove(activity);
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final synchronized void onActivityPaused(Activity activity) {
        try {
            if (!this.f16022r) {
                this.f16023s = true;
                io.sentry.E e10 = this.f16019n;
                if (e10 == null) {
                    AbstractC1433i.f16177a.getClass();
                    this.f16028x = new C1510s1();
                } else {
                    this.f16028x = e10.q().getDateProvider().a();
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPostResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPrePaused(Activity activity) {
        if (this.f16022r) {
            this.f16023s = true;
            io.sentry.E e10 = this.f16019n;
            if (e10 != null) {
                this.f16028x = e10.q().getDateProvider().a();
            } else {
                AbstractC1433i.f16177a.getClass();
                this.f16028x = new C1510s1();
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final synchronized void onActivityResumed(Activity activity) {
        try {
            if (this.f16020p) {
                io.sentry.U u4 = (io.sentry.U) this.f16026v.get(activity);
                io.sentry.U u10 = (io.sentry.U) this.f16027w.get(activity);
                View viewFindViewById = activity.findViewById(R.id.content);
                if (viewFindViewById != null) {
                    RunnableC1429e runnableC1429e = new RunnableC1429e(this, u10, u4, 0);
                    B b10 = this.f16018m;
                    io.sentry.android.core.internal.util.f fVar = new io.sentry.android.core.internal.util.f(viewFindViewById, runnableC1429e);
                    b10.getClass();
                    viewFindViewById.getViewTreeObserver().addOnDrawListener(fVar);
                } else {
                    this.f16029y.post(new RunnableC1429e(this, u10, u4, 1));
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final synchronized void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final synchronized void onActivityStarted(Activity activity) {
        if (this.f16020p) {
            N0.q qVar = this.B;
            synchronized (qVar) {
                if (qVar.H()) {
                    qVar.Q(new RunnableC1426b(qVar, activity, 0), "FrameMetricsAggregator.add");
                    C1427c c1427cT = qVar.t();
                    if (c1427cT != null) {
                        ((WeakHashMap) qVar.f4401p).put(activity, c1427cT);
                    }
                }
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final synchronized void onActivityStopped(Activity activity) {
    }

    public final void q(Activity activity) {
        WeakHashMap weakHashMap;
        WeakHashMap weakHashMap2;
        Boolean boolValueOf;
        C1507r1 c1507r1;
        AbstractC1453b1 abstractC1453b1;
        WeakReference weakReference = new WeakReference(activity);
        if (this.f16019n != null) {
            WeakHashMap weakHashMap3 = this.f16017A;
            if (weakHashMap3.containsKey(activity)) {
                return;
            }
            if (!this.f16020p) {
                weakHashMap3.put(activity, A0.f15812a);
                this.f16019n.l(new C1350a(14));
                return;
            }
            Iterator it = weakHashMap3.entrySet().iterator();
            while (true) {
                boolean zHasNext = it.hasNext();
                weakHashMap = this.f16027w;
                weakHashMap2 = this.f16026v;
                if (!zHasNext) {
                    break;
                }
                Map.Entry entry = (Map.Entry) it.next();
                j((io.sentry.V) entry.getValue(), (io.sentry.U) weakHashMap2.get(entry.getKey()), (io.sentry.U) weakHashMap.get(entry.getKey()));
            }
            String simpleName = activity.getClass().getSimpleName();
            io.sentry.android.core.performance.f fVarA = io.sentry.android.core.performance.e.b().a(this.o);
            B8.t tVar = null;
            if (h6.i.t() && fVarA.b()) {
                c1507r1 = fVarA.b() ? new C1507r1(fVarA.f16287m * 1000000) : null;
                boolValueOf = Boolean.valueOf(io.sentry.android.core.performance.e.b().l == io.sentry.android.core.performance.d.COLD);
            } else {
                boolValueOf = null;
                c1507r1 = null;
            }
            c2 c2Var = new c2();
            c2Var.f16486q = 30000L;
            if (this.o.isEnableActivityLifecycleTracingAutoFinish()) {
                c2Var.f16485p = this.o.getIdleTimeout();
                c2Var.l = true;
            }
            c2Var.o = true;
            c2Var.f16487r = new C1431g(this, weakReference, simpleName);
            if (this.f16023s || c1507r1 == null || boolValueOf == null) {
                abstractC1453b1 = this.f16028x;
            } else {
                B8.t tVar2 = io.sentry.android.core.performance.e.b().f16283t;
                io.sentry.android.core.performance.e.b().f16283t = null;
                tVar = tVar2;
                abstractC1453b1 = c1507r1;
            }
            c2Var.f16483m = abstractC1453b1;
            c2Var.f16484n = tVar != null;
            io.sentry.V vJ = this.f16019n.j(new b2(simpleName, io.sentry.protocol.C.COMPONENT, "ui.load", tVar), c2Var);
            if (vJ != null) {
                vJ.p().f15963t = "auto.ui.activity";
            }
            if (!this.f16023s && c1507r1 != null && boolValueOf != null) {
                io.sentry.U uY = vJ.y(boolValueOf.booleanValue() ? "app.start.cold" : "app.start.warm", boolValueOf.booleanValue() ? "Cold Start" : "Warm Start", c1507r1, io.sentry.Y.SENTRY);
                this.f16025u = uY;
                uY.p().f15963t = "auto.ui.activity";
                b();
            }
            String strConcat = simpleName.concat(" initial display");
            io.sentry.Y y10 = io.sentry.Y.SENTRY;
            io.sentry.U uY2 = vJ.y("ui.load.initial_display", strConcat, abstractC1453b1, y10);
            weakHashMap2.put(activity, uY2);
            uY2.p().f15963t = "auto.ui.activity";
            if (this.f16021q && this.f16024t != null && this.o != null) {
                io.sentry.U uY3 = vJ.y("ui.load.full_display", simpleName.concat(" full display"), abstractC1453b1, y10);
                uY3.p().f15963t = "auto.ui.activity";
                try {
                    weakHashMap.put(activity, uY3);
                    this.f16030z = this.o.getExecutorService().r(new RunnableC1429e(this, uY3, uY2, 2), 30000L);
                } catch (RejectedExecutionException e10) {
                    this.o.getLogger().s(EnumC1495p1.ERROR, "Failed to call the executor. Time to full display span will not be finished automatically. Did you call Sentry.close()?", e10);
                }
            }
            this.f16019n.l(new C1430f(this, vJ, 1));
            weakHashMap3.put(activity, vJ);
        }
    }
}

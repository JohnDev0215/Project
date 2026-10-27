package io.sentry.android.core.internal.util;

import A2.m;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.Choreographer;
import android.view.FrameMetrics;
import android.view.Window;
import io.sentry.EnumC1495p1;
import io.sentry.android.core.B;
import io.sentry.android.core.P;
import java.lang.Thread;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class l implements Application.ActivityLifecycleCallbacks {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final /* synthetic */ int f16228A = 0;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final long f16229y = TimeUnit.SECONDS.toNanos(1);

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final long f16230z = TimeUnit.MILLISECONDS.toNanos(700);
    public final B l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final CopyOnWriteArraySet f16231m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final P f16232n;
    public final Handler o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public WeakReference f16233p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final ConcurrentHashMap f16234q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f16235r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final b f16236s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final j f16237t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Choreographer f16238u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final Field f16239v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public long f16240w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public long f16241x;

    /* JADX WARN: Type inference failed for: r4v5, types: [io.sentry.android.core.internal.util.j] */
    public l(Context context, final P p10, final B b10) {
        b bVar = new b();
        this.f16231m = new CopyOnWriteArraySet();
        this.f16234q = new ConcurrentHashMap();
        this.f16235r = false;
        this.f16240w = 0L;
        this.f16241x = 0L;
        Context applicationContext = context.getApplicationContext();
        context = applicationContext != null ? applicationContext : context;
        h6.e.q(p10, "Logger is required");
        this.f16232n = p10;
        h6.e.q(b10, "BuildInfoProvider is required");
        this.l = b10;
        this.f16236s = bVar;
        if (context instanceof Application) {
            this.f16235r = true;
            HandlerThread handlerThread = new HandlerThread("io.sentry.android.core.internal.util.SentryFrameMetricsCollector");
            handlerThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: io.sentry.android.core.internal.util.i
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public final void uncaughtException(Thread thread, Throwable th) {
                    p10.s(EnumC1495p1.ERROR, "Error during frames measurements.", th);
                }
            });
            handlerThread.start();
            this.o = new Handler(handlerThread.getLooper());
            ((Application) context).registerActivityLifecycleCallbacks(this);
            new Handler(Looper.getMainLooper()).post(new m(this, 10, p10));
            try {
                Field declaredField = Choreographer.class.getDeclaredField("mLastFrameTimeNanos");
                this.f16239v = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException e10) {
                p10.s(EnumC1495p1.ERROR, "Unable to get the frame timestamp from the choreographer: ", e10);
            }
            this.f16237t = new Window.OnFrameMetricsAvailableListener() { // from class: io.sentry.android.core.internal.util.j
                @Override // android.view.Window.OnFrameMetricsAvailableListener
                public final void onFrameMetricsAvailable(Window window, FrameMetrics frameMetrics, int i7) {
                    l lVar = this.f16226a;
                    lVar.getClass();
                    long jNanoTime = System.nanoTime();
                    b10.getClass();
                    float refreshRate = Build.VERSION.SDK_INT >= 30 ? window.getContext().getDisplay().getRefreshRate() : window.getWindowManager().getDefaultDisplay().getRefreshRate();
                    float f10 = l.f16229y;
                    long metric = frameMetrics.getMetric(5) + frameMetrics.getMetric(4) + frameMetrics.getMetric(3) + frameMetrics.getMetric(2) + frameMetrics.getMetric(1) + frameMetrics.getMetric(0);
                    long jMax = Math.max(0L, metric - ((long) (f10 / refreshRate)));
                    lVar.l.getClass();
                    long metric2 = frameMetrics.getMetric(10);
                    if (metric2 < 0) {
                        metric2 = jNanoTime - metric;
                    }
                    long jMax2 = Math.max(metric2, lVar.f16241x);
                    if (jMax2 == lVar.f16240w) {
                        return;
                    }
                    lVar.f16240w = jMax2;
                    lVar.f16241x = jMax2 + metric;
                    boolean z6 = metric > ((long) (f10 / (refreshRate - 1.0f)));
                    boolean z10 = z6 && metric > l.f16230z;
                    Iterator it = lVar.f16234q.values().iterator();
                    while (it.hasNext()) {
                        ((k) it.next()).c(jMax2, lVar.f16241x, metric, jMax, z6, z10, refreshRate);
                    }
                }
            };
        }
    }

    public final void a(String str) {
        if (this.f16235r) {
            ConcurrentHashMap concurrentHashMap = this.f16234q;
            if (str != null) {
                concurrentHashMap.remove(str);
            }
            WeakReference weakReference = this.f16233p;
            Window window = weakReference != null ? (Window) weakReference.get() : null;
            if (window == null || !concurrentHashMap.isEmpty()) {
                return;
            }
            b(window);
        }
    }

    public final void b(Window window) {
        CopyOnWriteArraySet copyOnWriteArraySet = this.f16231m;
        if (copyOnWriteArraySet.contains(window)) {
            this.l.getClass();
            try {
                b bVar = this.f16236s;
                j jVar = this.f16237t;
                bVar.getClass();
                window.removeOnFrameMetricsAvailableListener(jVar);
            } catch (Exception e10) {
                this.f16232n.s(EnumC1495p1.ERROR, "Failed to remove frameMetricsAvailableListener", e10);
            }
            copyOnWriteArraySet.remove(window);
        }
    }

    public final void c() {
        WeakReference weakReference = this.f16233p;
        Window window = weakReference != null ? (Window) weakReference.get() : null;
        if (window == null || !this.f16235r) {
            return;
        }
        CopyOnWriteArraySet copyOnWriteArraySet = this.f16231m;
        if (copyOnWriteArraySet.contains(window) || this.f16234q.isEmpty()) {
            return;
        }
        this.l.getClass();
        Handler handler = this.o;
        if (handler != null) {
            copyOnWriteArraySet.add(window);
            j jVar = this.f16237t;
            this.f16236s.getClass();
            window.addOnFrameMetricsAvailableListener(jVar, handler);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        Window window = activity.getWindow();
        WeakReference weakReference = this.f16233p;
        if (weakReference == null || weakReference.get() != window) {
            this.f16233p = new WeakReference(window);
            c();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
        b(activity.getWindow());
        WeakReference weakReference = this.f16233p;
        if (weakReference == null || weakReference.get() != activity.getWindow()) {
            return;
        }
        this.f16233p = null;
    }
}

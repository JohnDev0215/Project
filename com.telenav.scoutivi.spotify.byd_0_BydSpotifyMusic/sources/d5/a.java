package d5;

import java.util.concurrent.Callable;
import q4.g;
import u4.c;
import v4.b;
import v4.d;
import v4.e;

/* JADX INFO: compiled from: RxJavaPlugins.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static volatile d<? super Throwable> f5949a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static volatile e<? super Runnable, ? extends Runnable> f5950b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static volatile e<? super Callable<q4.d>, ? extends q4.d> f5951c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static volatile e<? super Callable<q4.d>, ? extends q4.d> f5952d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static volatile e<? super Callable<q4.d>, ? extends q4.d> f5953e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    static volatile e<? super Callable<q4.d>, ? extends q4.d> f5954f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    static volatile e<? super q4.d, ? extends q4.d> f5955g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    static volatile e<? super q4.d, ? extends q4.d> f5956h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    static volatile e<? super q4.e, ? extends q4.e> f5957i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    static volatile b<? super q4.e, ? super g, ? extends g> f5958j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    static volatile boolean f5959k;

    static <T, U, R> R a(b<T, U, R> bVar, T t6, U u6) {
        try {
            return bVar.a(t6, u6);
        } catch (Throwable th) {
            throw b5.b.a(th);
        }
    }

    static <T, R> R b(e<T, R> eVar, T t6) {
        try {
            return eVar.a(t6);
        } catch (Throwable th) {
            throw b5.b.a(th);
        }
    }

    static q4.d c(e<? super Callable<q4.d>, ? extends q4.d> eVar, Callable<q4.d> callable) {
        return (q4.d) x4.b.c(b(eVar, callable), "Scheduler Callable result can't be null");
    }

    static q4.d d(Callable<q4.d> callable) {
        try {
            return (q4.d) x4.b.c(callable.call(), "Scheduler Callable result can't be null");
        } catch (Throwable th) {
            throw b5.b.a(th);
        }
    }

    public static q4.d e(Callable<q4.d> callable) {
        x4.b.c(callable, "Scheduler Callable can't be null");
        e<? super Callable<q4.d>, ? extends q4.d> eVar = f5951c;
        return eVar == null ? d(callable) : c(eVar, callable);
    }

    public static q4.d f(Callable<q4.d> callable) {
        x4.b.c(callable, "Scheduler Callable can't be null");
        e<? super Callable<q4.d>, ? extends q4.d> eVar = f5953e;
        return eVar == null ? d(callable) : c(eVar, callable);
    }

    public static q4.d g(Callable<q4.d> callable) {
        x4.b.c(callable, "Scheduler Callable can't be null");
        e<? super Callable<q4.d>, ? extends q4.d> eVar = f5954f;
        return eVar == null ? d(callable) : c(eVar, callable);
    }

    public static q4.d h(Callable<q4.d> callable) {
        x4.b.c(callable, "Scheduler Callable can't be null");
        e<? super Callable<q4.d>, ? extends q4.d> eVar = f5952d;
        return eVar == null ? d(callable) : c(eVar, callable);
    }

    static boolean i(Throwable th) {
        return (th instanceof c) || (th instanceof IllegalStateException) || (th instanceof NullPointerException) || (th instanceof IllegalArgumentException) || (th instanceof u4.a);
    }

    public static <T> q4.e<T> j(q4.e<T> eVar) {
        e<? super q4.e, ? extends q4.e> eVar2 = f5957i;
        return eVar2 != null ? (q4.e) b(eVar2, eVar) : eVar;
    }

    public static void k(Throwable th) {
        d<? super Throwable> dVar = f5949a;
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        } else if (!i(th)) {
            th = new u4.e(th);
        }
        if (dVar != null) {
            try {
                dVar.accept(th);
                return;
            } catch (Throwable th2) {
                th2.printStackTrace();
                q(th2);
            }
        }
        th.printStackTrace();
        q(th);
    }

    public static q4.d l(q4.d dVar) {
        e<? super q4.d, ? extends q4.d> eVar = f5956h;
        return eVar == null ? dVar : (q4.d) b(eVar, dVar);
    }

    public static Runnable m(Runnable runnable) {
        x4.b.c(runnable, "run is null");
        e<? super Runnable, ? extends Runnable> eVar = f5950b;
        return eVar == null ? runnable : (Runnable) b(eVar, runnable);
    }

    public static q4.d n(q4.d dVar) {
        e<? super q4.d, ? extends q4.d> eVar = f5955g;
        return eVar == null ? dVar : (q4.d) b(eVar, dVar);
    }

    public static <T> g<? super T> o(q4.e<T> eVar, g<? super T> gVar) {
        b<? super q4.e, ? super g, ? extends g> bVar = f5958j;
        return bVar != null ? (g) a(bVar, eVar, gVar) : gVar;
    }

    public static void p(d<? super Throwable> dVar) {
        if (f5959k) {
            throw new IllegalStateException("Plugins can't be changed anymore");
        }
        f5949a = dVar;
    }

    static void q(Throwable th) {
        Thread threadCurrentThread = Thread.currentThread();
        threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, th);
    }
}

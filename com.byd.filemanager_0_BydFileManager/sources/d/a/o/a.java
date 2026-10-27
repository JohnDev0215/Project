package d.a.o;

import d.a.i;
import d.a.j;
import d.a.m.b;
import d.a.m.e;
import d.a.m.f;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.internal.util.ExceptionHelper;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: RxJavaPlugins.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static volatile e<? super Throwable> f4533a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static volatile f<? super Runnable, ? extends Runnable> f4534b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static volatile f<? super Callable<j>, ? extends j> f4535c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static volatile f<? super Callable<j>, ? extends j> f4536d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static volatile f<? super Callable<j>, ? extends j> f4537e;
    static volatile f<? super Callable<j>, ? extends j> f;
    static volatile f<? super j, ? extends j> g;
    static volatile f<? super j, ? extends j> h;
    static volatile f<? super j, ? extends j> i;
    static volatile f<? super j, ? extends j> j;
    static volatile f<? super d.a.e, ? extends d.a.e> k;
    static volatile b<? super d.a.e, ? super i, ? extends i> l;
    static volatile boolean m;

    static <T, U, R> R a(b<T, U, R> bVar, T t, U u) {
        try {
            return bVar.a(t, u);
        } catch (Throwable th) {
            throw ExceptionHelper.c(th);
        }
    }

    static <T, R> R b(f<T, R> fVar, T t) {
        try {
            return fVar.apply(t);
        } catch (Throwable th) {
            throw ExceptionHelper.c(th);
        }
    }

    static j c(f<? super Callable<j>, ? extends j> fVar, Callable<j> callable) {
        return (j) d.a.n.a.b.c(b(fVar, callable), "Scheduler Callable result can't be null");
    }

    static j d(Callable<j> callable) {
        try {
            return (j) d.a.n.a.b.c(callable.call(), "Scheduler Callable result can't be null");
        } catch (Throwable th) {
            throw ExceptionHelper.c(th);
        }
    }

    public static j e(Callable<j> callable) {
        d.a.n.a.b.c(callable, "Scheduler Callable can't be null");
        f<? super Callable<j>, ? extends j> fVar = f4535c;
        return fVar == null ? d(callable) : c(fVar, callable);
    }

    public static j f(Callable<j> callable) {
        d.a.n.a.b.c(callable, "Scheduler Callable can't be null");
        f<? super Callable<j>, ? extends j> fVar = f4537e;
        return fVar == null ? d(callable) : c(fVar, callable);
    }

    public static j g(Callable<j> callable) {
        d.a.n.a.b.c(callable, "Scheduler Callable can't be null");
        f<? super Callable<j>, ? extends j> fVar = f;
        return fVar == null ? d(callable) : c(fVar, callable);
    }

    public static j h(Callable<j> callable) {
        d.a.n.a.b.c(callable, "Scheduler Callable can't be null");
        f<? super Callable<j>, ? extends j> fVar = f4536d;
        return fVar == null ? d(callable) : c(fVar, callable);
    }

    static boolean i(Throwable th) {
        return (th instanceof OnErrorNotImplementedException) || (th instanceof MissingBackpressureException) || (th instanceof IllegalStateException) || (th instanceof NullPointerException) || (th instanceof IllegalArgumentException) || (th instanceof CompositeException);
    }

    public static <T> d.a.e<T> j(d.a.e<T> eVar) {
        f<? super d.a.e, ? extends d.a.e> fVar = k;
        return fVar != null ? (d.a.e) b(fVar, eVar) : eVar;
    }

    public static j k(j jVar) {
        f<? super j, ? extends j> fVar = g;
        return fVar == null ? jVar : (j) b(fVar, jVar);
    }

    public static void l(Throwable th) {
        e<? super Throwable> eVar = f4533a;
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        } else if (!i(th)) {
            th = new UndeliverableException(th);
        }
        if (eVar != null) {
            try {
                eVar.accept(th);
                return;
            } catch (Throwable th2) {
                th2.printStackTrace();
                s(th2);
            }
        }
        th.printStackTrace();
        s(th);
    }

    public static j m(j jVar) {
        f<? super j, ? extends j> fVar = i;
        return fVar == null ? jVar : (j) b(fVar, jVar);
    }

    public static j n(j jVar) {
        f<? super j, ? extends j> fVar = j;
        return fVar == null ? jVar : (j) b(fVar, jVar);
    }

    public static Runnable o(Runnable runnable) {
        f<? super Runnable, ? extends Runnable> fVar = f4534b;
        return fVar == null ? runnable : (Runnable) b(fVar, runnable);
    }

    public static j p(j jVar) {
        f<? super j, ? extends j> fVar = h;
        return fVar == null ? jVar : (j) b(fVar, jVar);
    }

    public static <T> i<? super T> q(d.a.e<T> eVar, i<? super T> iVar) {
        b<? super d.a.e, ? super i, ? extends i> bVar = l;
        return bVar != null ? (i) a(bVar, eVar, iVar) : iVar;
    }

    public static void r(e<? super Throwable> eVar) {
        if (m) {
            throw new IllegalStateException("Plugins can't be changed anymore");
        }
        f4533a = eVar;
    }

    static void s(Throwable th) {
        Thread threadCurrentThread = Thread.currentThread();
        threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, th);
    }
}

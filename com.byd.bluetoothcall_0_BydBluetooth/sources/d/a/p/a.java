package d.a.p;

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

    /* JADX INFO: renamed from: a */
    static volatile e<? super Throwable> f4245a;

    /* JADX INFO: renamed from: b */
    static volatile f<? super Runnable, ? extends Runnable> f4246b;

    /* JADX INFO: renamed from: c */
    static volatile f<? super Callable<j>, ? extends j> f4247c;

    /* JADX INFO: renamed from: d */
    static volatile f<? super Callable<j>, ? extends j> f4248d;

    /* JADX INFO: renamed from: e */
    static volatile f<? super Callable<j>, ? extends j> f4249e;
    static volatile f<? super Callable<j>, ? extends j> f;
    static volatile f<? super j, ? extends j> g;
    static volatile f<? super j, ? extends j> h;
    static volatile f<? super d.a.e, ? extends d.a.e> i;
    static volatile b<? super d.a.e, ? super i, ? extends i> j;

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
        return (j) d.a.n.a.b.d(b(fVar, callable), "Scheduler Callable result can't be null");
    }

    static j d(Callable<j> callable) {
        try {
            return (j) d.a.n.a.b.d(callable.call(), "Scheduler Callable result can't be null");
        } catch (Throwable th) {
            throw ExceptionHelper.c(th);
        }
    }

    public static j e(Callable<j> callable) {
        d.a.n.a.b.d(callable, "Scheduler Callable can't be null");
        f<? super Callable<j>, ? extends j> fVar = f4247c;
        return fVar == null ? d(callable) : c(fVar, callable);
    }

    public static j f(Callable<j> callable) {
        d.a.n.a.b.d(callable, "Scheduler Callable can't be null");
        f<? super Callable<j>, ? extends j> fVar = f4249e;
        return fVar == null ? d(callable) : c(fVar, callable);
    }

    public static j g(Callable<j> callable) {
        d.a.n.a.b.d(callable, "Scheduler Callable can't be null");
        f<? super Callable<j>, ? extends j> fVar = f;
        return fVar == null ? d(callable) : c(fVar, callable);
    }

    public static j h(Callable<j> callable) {
        d.a.n.a.b.d(callable, "Scheduler Callable can't be null");
        f<? super Callable<j>, ? extends j> fVar = f4248d;
        return fVar == null ? d(callable) : c(fVar, callable);
    }

    static boolean i(Throwable th) {
        return (th instanceof OnErrorNotImplementedException) || (th instanceof MissingBackpressureException) || (th instanceof IllegalStateException) || (th instanceof NullPointerException) || (th instanceof IllegalArgumentException) || (th instanceof CompositeException);
    }

    public static <T> d.a.e<T> j(d.a.e<T> eVar) {
        f<? super d.a.e, ? extends d.a.e> fVar = i;
        return fVar != null ? (d.a.e) b(fVar, eVar) : eVar;
    }

    public static j k(j jVar) {
        f<? super j, ? extends j> fVar = g;
        return fVar == null ? jVar : (j) b(fVar, jVar);
    }

    public static void l(Throwable th) {
        e<? super Throwable> eVar = f4245a;
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
                p(th2);
            }
        }
        th.printStackTrace();
        p(th);
    }

    public static j m(j jVar) {
        f<? super j, ? extends j> fVar = h;
        return fVar == null ? jVar : (j) b(fVar, jVar);
    }

    public static Runnable n(Runnable runnable) {
        d.a.n.a.b.d(runnable, "run is null");
        f<? super Runnable, ? extends Runnable> fVar = f4246b;
        return fVar == null ? runnable : (Runnable) b(fVar, runnable);
    }

    public static <T> i<? super T> o(d.a.e<T> eVar, i<? super T> iVar) {
        b<? super d.a.e, ? super i, ? extends i> bVar = j;
        return bVar != null ? (i) a(bVar, eVar, iVar) : iVar;
    }

    static void p(Throwable th) {
        Thread threadCurrentThread = Thread.currentThread();
        threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, th);
    }
}

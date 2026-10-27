package e.a.o;

import e.a.i;
import e.a.j;
import e.a.m.b;
import e.a.m.e;
import e.a.m.f;
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
    static volatile e<? super Throwable> f4935a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static volatile f<? super Runnable, ? extends Runnable> f4936b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static volatile f<? super Callable<j>, ? extends j> f4937c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static volatile f<? super Callable<j>, ? extends j> f4938d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static volatile f<? super Callable<j>, ? extends j> f4939e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    static volatile f<? super Callable<j>, ? extends j> f4940f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    static volatile f<? super j, ? extends j> f4941g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    static volatile f<? super e.a.e, ? extends e.a.e> f4942h;
    static volatile b<? super e.a.e, ? super i, ? extends i> i;

    static <T, U, R> R a(b<T, U, R> bVar, T t, U u) {
        try {
            return bVar.apply(t, u);
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
        return (j) e.a.n.a.b.c(b(fVar, callable), "Scheduler Callable result can't be null");
    }

    static j d(Callable<j> callable) {
        try {
            return (j) e.a.n.a.b.c(callable.call(), "Scheduler Callable result can't be null");
        } catch (Throwable th) {
            throw ExceptionHelper.c(th);
        }
    }

    public static j e(Callable<j> callable) {
        e.a.n.a.b.c(callable, "Scheduler Callable can't be null");
        f<? super Callable<j>, ? extends j> fVar = f4937c;
        return fVar == null ? d(callable) : c(fVar, callable);
    }

    public static j f(Callable<j> callable) {
        e.a.n.a.b.c(callable, "Scheduler Callable can't be null");
        f<? super Callable<j>, ? extends j> fVar = f4939e;
        return fVar == null ? d(callable) : c(fVar, callable);
    }

    public static j g(Callable<j> callable) {
        e.a.n.a.b.c(callable, "Scheduler Callable can't be null");
        f<? super Callable<j>, ? extends j> fVar = f4940f;
        return fVar == null ? d(callable) : c(fVar, callable);
    }

    public static j h(Callable<j> callable) {
        e.a.n.a.b.c(callable, "Scheduler Callable can't be null");
        f<? super Callable<j>, ? extends j> fVar = f4938d;
        return fVar == null ? d(callable) : c(fVar, callable);
    }

    static boolean i(Throwable th) {
        return (th instanceof OnErrorNotImplementedException) || (th instanceof MissingBackpressureException) || (th instanceof IllegalStateException) || (th instanceof NullPointerException) || (th instanceof IllegalArgumentException) || (th instanceof CompositeException);
    }

    public static <T> e.a.e<T> j(e.a.e<T> eVar) {
        f<? super e.a.e, ? extends e.a.e> fVar = f4942h;
        return fVar != null ? (e.a.e) b(fVar, eVar) : eVar;
    }

    public static void k(Throwable th) {
        e<? super Throwable> eVar = f4935a;
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
                o(th2);
            }
        }
        th.printStackTrace();
        o(th);
    }

    public static j l(j jVar) {
        f<? super j, ? extends j> fVar = f4941g;
        return fVar == null ? jVar : (j) b(fVar, jVar);
    }

    public static Runnable m(Runnable runnable) {
        e.a.n.a.b.c(runnable, "run is null");
        f<? super Runnable, ? extends Runnable> fVar = f4936b;
        return fVar == null ? runnable : (Runnable) b(fVar, runnable);
    }

    public static <T> i<? super T> n(e.a.e<T> eVar, i<? super T> iVar) {
        b<? super e.a.e, ? super i, ? extends i> bVar = i;
        return bVar != null ? (i) a(bVar, eVar, iVar) : iVar;
    }

    static void o(Throwable th) {
        Thread threadCurrentThread = Thread.currentThread();
        threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, th);
    }
}

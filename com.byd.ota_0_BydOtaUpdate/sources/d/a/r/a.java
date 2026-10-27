package d.a.r;

import d.a.c;
import d.a.g;
import d.a.i;
import d.a.j;
import d.a.k;
import d.a.o.b;
import d.a.o.d;
import d.a.o.e;
import io.reactivex.internal.util.ExceptionHelper;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: RxJavaPlugins.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile d<Throwable> f3265a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile e<Runnable, Runnable> f3266b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static volatile e<Callable<j>, j> f3267c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static volatile e<Callable<j>, j> f3268d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static volatile e<Callable<j>, j> f3269e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static volatile e<Callable<j>, j> f3270f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static volatile e<j, j> f3271g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static volatile e<j, j> f3272h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static volatile e<c, c> f3273i;
    public static volatile e<g, g> j;
    public static volatile e<d.a.d, d.a.d> k;
    public static volatile e<k, k> l;
    public static volatile e<d.a.a, d.a.a> m;
    public static volatile b<g, i, i> n;

    public static <T, U, R> R a(b<T, U, R> bVar, T t, U u) {
        try {
            return bVar.a(t, u);
        } catch (Throwable th) {
            throw ExceptionHelper.c(th);
        }
    }

    public static <T, R> R b(e<T, R> eVar, T t) {
        try {
            return eVar.apply(t);
        } catch (Throwable th) {
            throw ExceptionHelper.c(th);
        }
    }

    public static j c(e<Callable<j>, j> eVar, Callable<j> callable) {
        return (j) d.a.p.b.b.d(b(eVar, callable), "Scheduler Callable result can't be null");
    }

    public static j d(Callable<j> callable) {
        try {
            return (j) d.a.p.b.b.d(callable.call(), "Scheduler Callable result can't be null");
        } catch (Throwable th) {
            throw ExceptionHelper.c(th);
        }
    }

    public static j e(Callable<j> callable) {
        d.a.p.b.b.d(callable, "Scheduler Callable can't be null");
        e<Callable<j>, j> eVar = f3267c;
        return eVar == null ? d(callable) : c(eVar, callable);
    }

    public static j f(Callable<j> callable) {
        d.a.p.b.b.d(callable, "Scheduler Callable can't be null");
        e<Callable<j>, j> eVar = f3269e;
        return eVar == null ? d(callable) : c(eVar, callable);
    }

    public static j g(Callable<j> callable) {
        d.a.p.b.b.d(callable, "Scheduler Callable can't be null");
        e<Callable<j>, j> eVar = f3270f;
        return eVar == null ? d(callable) : c(eVar, callable);
    }

    public static j h(Callable<j> callable) {
        d.a.p.b.b.d(callable, "Scheduler Callable can't be null");
        e<Callable<j>, j> eVar = f3268d;
        return eVar == null ? d(callable) : c(eVar, callable);
    }

    public static d.a.a i(d.a.a aVar) {
        e<d.a.a, d.a.a> eVar = m;
        return eVar != null ? (d.a.a) b(eVar, aVar) : aVar;
    }

    public static <T> c<T> j(c<T> cVar) {
        e<c, c> eVar = f3273i;
        return eVar != null ? (c) b(eVar, cVar) : cVar;
    }

    public static <T> d.a.d<T> k(d.a.d<T> dVar) {
        e<d.a.d, d.a.d> eVar = k;
        return eVar != null ? (d.a.d) b(eVar, dVar) : dVar;
    }

    public static <T> g<T> l(g<T> gVar) {
        e<g, g> eVar = j;
        return eVar != null ? (g) b(eVar, gVar) : gVar;
    }

    public static <T> k<T> m(k<T> kVar) {
        e<k, k> eVar = l;
        return eVar != null ? (k) b(eVar, kVar) : kVar;
    }

    public static j n(j jVar) {
        e<j, j> eVar = f3271g;
        return eVar == null ? jVar : (j) b(eVar, jVar);
    }

    public static void o(Throwable th) {
        d<Throwable> dVar = f3265a;
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        if (dVar != null) {
            try {
                dVar.accept(th);
                return;
            } catch (Throwable th2) {
                th2.printStackTrace();
                s(th2);
            }
        }
        th.printStackTrace();
        s(th);
    }

    public static j p(j jVar) {
        e<j, j> eVar = f3272h;
        return eVar == null ? jVar : (j) b(eVar, jVar);
    }

    public static Runnable q(Runnable runnable) {
        e<Runnable, Runnable> eVar = f3266b;
        return eVar == null ? runnable : (Runnable) b(eVar, runnable);
    }

    public static <T> i<? super T> r(g<T> gVar, i<? super T> iVar) {
        b<g, i, i> bVar = n;
        return bVar != null ? (i) a(bVar, gVar, iVar) : iVar;
    }

    public static void s(Throwable th) {
        Thread threadCurrentThread = Thread.currentThread();
        threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, th);
    }
}

package c.a.n;

import c.a.c;
import c.a.g;
import c.a.h;
import c.a.l.b;
import c.a.l.d;
import c.a.l.e;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static volatile d<? super Throwable> f1912a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static volatile e<? super Runnable, ? extends Runnable> f1913b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static volatile e<? super Callable<h>, ? extends h> f1914c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static volatile e<? super Callable<h>, ? extends h> f1915d;
    static volatile e<? super Callable<h>, ? extends h> e;
    static volatile e<? super Callable<h>, ? extends h> f;
    static volatile e<? super h, ? extends h> g;
    static volatile e<? super h, ? extends h> h;
    static volatile e<? super h, ? extends h> i;
    static volatile e<? super c, ? extends c> j;
    static volatile b<? super c, ? super g, ? extends g> k;
    static volatile boolean l;

    static <T, U, R> R a(b<T, U, R> bVar, T t, U u) {
        try {
            return bVar.a(t, u);
        } catch (Throwable th) {
            throw c.a.m.h.a.a(th);
        }
    }

    static <T, R> R b(e<T, R> eVar, T t) {
        try {
            return eVar.a(t);
        } catch (Throwable th) {
            throw c.a.m.h.a.a(th);
        }
    }

    static h c(e<? super Callable<h>, ? extends h> eVar, Callable<h> callable) {
        return (h) c.a.m.b.b.c(b(eVar, callable), "Scheduler Callable result can't be null");
    }

    static h d(Callable<h> callable) {
        try {
            return (h) c.a.m.b.b.c(callable.call(), "Scheduler Callable result can't be null");
        } catch (Throwable th) {
            throw c.a.m.h.a.a(th);
        }
    }

    public static h e(Callable<h> callable) {
        c.a.m.b.b.c(callable, "Scheduler Callable can't be null");
        e<? super Callable<h>, ? extends h> eVar = f1914c;
        return eVar == null ? d(callable) : c(eVar, callable);
    }

    public static h f(Callable<h> callable) {
        c.a.m.b.b.c(callable, "Scheduler Callable can't be null");
        e<? super Callable<h>, ? extends h> eVar = e;
        return eVar == null ? d(callable) : c(eVar, callable);
    }

    public static h g(Callable<h> callable) {
        c.a.m.b.b.c(callable, "Scheduler Callable can't be null");
        e<? super Callable<h>, ? extends h> eVar = f;
        return eVar == null ? d(callable) : c(eVar, callable);
    }

    public static h h(Callable<h> callable) {
        c.a.m.b.b.c(callable, "Scheduler Callable can't be null");
        e<? super Callable<h>, ? extends h> eVar = f1915d;
        return eVar == null ? d(callable) : c(eVar, callable);
    }

    static boolean i(Throwable th) {
        return (th instanceof c.a.k.c) || (th instanceof IllegalStateException) || (th instanceof NullPointerException) || (th instanceof IllegalArgumentException) || (th instanceof c.a.k.a);
    }

    public static <T> c<T> j(c<T> cVar) {
        e<? super c, ? extends c> eVar = j;
        return eVar != null ? (c) b(eVar, cVar) : cVar;
    }

    public static h k(h hVar) {
        e<? super h, ? extends h> eVar = g;
        return eVar == null ? hVar : (h) b(eVar, hVar);
    }

    public static void l(Throwable th) {
        d<? super Throwable> dVar = f1912a;
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        } else if (!i(th)) {
            th = new c.a.k.e(th);
        }
        if (dVar != null) {
            try {
                dVar.a(th);
                return;
            } catch (Throwable th2) {
                th2.printStackTrace();
                r(th2);
            }
        }
        th.printStackTrace();
        r(th);
    }

    public static h m(h hVar) {
        e<? super h, ? extends h> eVar = h;
        return eVar == null ? hVar : (h) b(eVar, hVar);
    }

    public static h n(h hVar) {
        e<? super h, ? extends h> eVar = i;
        return eVar == null ? hVar : (h) b(eVar, hVar);
    }

    public static Runnable o(Runnable runnable) {
        e<? super Runnable, ? extends Runnable> eVar = f1913b;
        return eVar == null ? runnable : (Runnable) b(eVar, runnable);
    }

    public static <T> g<? super T> p(c<T> cVar, g<? super T> gVar) {
        b<? super c, ? super g, ? extends g> bVar = k;
        return bVar != null ? (g) a(bVar, cVar, gVar) : gVar;
    }

    public static void q(d<? super Throwable> dVar) {
        if (l) {
            throw new IllegalStateException("Plugins can't be changed anymore");
        }
        f1912a = dVar;
    }

    static void r(Throwable th) {
        Thread threadCurrentThread = Thread.currentThread();
        threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, th);
    }
}

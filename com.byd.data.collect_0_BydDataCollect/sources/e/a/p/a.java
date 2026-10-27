package e.a.p;

import e.a.b;
import e.a.c;
import e.a.g;
import e.a.h;
import e.a.i;
import e.a.m.d;
import e.a.m.e;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: RxJavaPlugins.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a */
    static volatile d<? super Throwable> f4977a;

    /* JADX INFO: renamed from: b */
    static volatile e<? super Runnable, ? extends Runnable> f4978b;

    /* JADX INFO: renamed from: c */
    static volatile e<? super Callable<h>, ? extends h> f4979c;

    /* JADX INFO: renamed from: d */
    static volatile e<? super Callable<h>, ? extends h> f4980d;

    /* JADX INFO: renamed from: e */
    static volatile e<? super Callable<h>, ? extends h> f4981e;

    /* JADX INFO: renamed from: f */
    static volatile e<? super Callable<h>, ? extends h> f4982f;

    /* JADX INFO: renamed from: g */
    static volatile e<? super h, ? extends h> f4983g;

    /* JADX INFO: renamed from: h */
    static volatile e<? super c, ? extends c> f4984h;

    /* JADX INFO: renamed from: i */
    static volatile e<? super e.a.e, ? extends e.a.e> f4985i;
    static volatile e<? super e.a.d, ? extends e.a.d> j;
    static volatile e<? super i, ? extends i> k;
    static volatile e<? super b, ? extends b> l;
    static volatile e.a.m.b<? super e.a.e, ? super g, ? extends g> m;

    static <T, U, R> R a(e.a.m.b<T, U, R> bVar, T t, U u) {
        try {
            return bVar.apply(t, u);
        } catch (Throwable th) {
            throw e.a.n.h.b.a(th);
        }
    }

    static <T, R> R b(e<T, R> eVar, T t) {
        try {
            return eVar.apply(t);
        } catch (Throwable th) {
            throw e.a.n.h.b.a(th);
        }
    }

    static h c(e<? super Callable<h>, ? extends h> eVar, Callable<h> callable) {
        return (h) e.a.n.b.b.c(b(eVar, callable), "Scheduler Callable result can't be null");
    }

    static h d(Callable<h> callable) {
        try {
            return (h) e.a.n.b.b.c(callable.call(), "Scheduler Callable result can't be null");
        } catch (Throwable th) {
            throw e.a.n.h.b.a(th);
        }
    }

    public static h e(Callable<h> callable) {
        e.a.n.b.b.c(callable, "Scheduler Callable can't be null");
        e<? super Callable<h>, ? extends h> eVar = f4979c;
        return eVar == null ? d(callable) : c(eVar, callable);
    }

    public static h f(Callable<h> callable) {
        e.a.n.b.b.c(callable, "Scheduler Callable can't be null");
        e<? super Callable<h>, ? extends h> eVar = f4981e;
        return eVar == null ? d(callable) : c(eVar, callable);
    }

    public static h g(Callable<h> callable) {
        e.a.n.b.b.c(callable, "Scheduler Callable can't be null");
        e<? super Callable<h>, ? extends h> eVar = f4982f;
        return eVar == null ? d(callable) : c(eVar, callable);
    }

    public static h h(Callable<h> callable) {
        e.a.n.b.b.c(callable, "Scheduler Callable can't be null");
        e<? super Callable<h>, ? extends h> eVar = f4980d;
        return eVar == null ? d(callable) : c(eVar, callable);
    }

    static boolean i(Throwable th) {
        return (th instanceof e.a.l.c) || (th instanceof IllegalStateException) || (th instanceof NullPointerException) || (th instanceof IllegalArgumentException) || (th instanceof e.a.l.a);
    }

    public static b j(b bVar) {
        e<? super b, ? extends b> eVar = l;
        return eVar != null ? (b) b(eVar, bVar) : bVar;
    }

    public static <T> c<T> k(c<T> cVar) {
        e<? super c, ? extends c> eVar = f4984h;
        return eVar != null ? (c) b(eVar, cVar) : cVar;
    }

    public static <T> e.a.d<T> l(e.a.d<T> dVar) {
        e<? super e.a.d, ? extends e.a.d> eVar = j;
        return eVar != null ? (e.a.d) b(eVar, dVar) : dVar;
    }

    public static <T> e.a.e<T> m(e.a.e<T> eVar) {
        e<? super e.a.e, ? extends e.a.e> eVar2 = f4985i;
        return eVar2 != null ? (e.a.e) b(eVar2, eVar) : eVar;
    }

    public static <T> i<T> n(i<T> iVar) {
        e<? super i, ? extends i> eVar = k;
        return eVar != null ? (i) b(eVar, iVar) : iVar;
    }

    public static void o(Throwable th) {
        d<? super Throwable> dVar = f4977a;
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        } else if (!i(th)) {
            th = new e.a.l.e(th);
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

    public static h p(h hVar) {
        e<? super h, ? extends h> eVar = f4983g;
        return eVar == null ? hVar : (h) b(eVar, hVar);
    }

    public static Runnable q(Runnable runnable) {
        e<? super Runnable, ? extends Runnable> eVar = f4978b;
        return eVar == null ? runnable : (Runnable) b(eVar, runnable);
    }

    public static <T> g<? super T> r(e.a.e<T> eVar, g<? super T> gVar) {
        e.a.m.b<? super e.a.e, ? super g, ? extends g> bVar = m;
        return bVar != null ? (g) a(bVar, eVar, gVar) : gVar;
    }

    static void s(Throwable th) {
        Thread threadCurrentThread = Thread.currentThread();
        threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, th);
    }
}

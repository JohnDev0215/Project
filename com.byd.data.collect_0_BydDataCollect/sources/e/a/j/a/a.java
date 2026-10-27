package e.a.j.a;

import e.a.h;
import e.a.l.b;
import e.a.m.e;
import java.util.Objects;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: RxAndroidPlugins.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile e<Callable<h>, h> f4848a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static volatile e<h, h> f4849b;

    static <T, R> R a(e<T, R> eVar, T t) {
        try {
            return eVar.apply(t);
        } catch (Throwable th) {
            throw b.a(th);
        }
    }

    static h b(e<Callable<h>, h> eVar, Callable<h> callable) {
        h hVar = (h) a(eVar, callable);
        Objects.requireNonNull(hVar, "Scheduler Callable returned null");
        return hVar;
    }

    static h c(Callable<h> callable) {
        try {
            h hVarCall = callable.call();
            if (hVarCall != null) {
                return hVarCall;
            }
            throw new NullPointerException("Scheduler Callable returned null");
        } catch (Throwable th) {
            throw b.a(th);
        }
    }

    public static h d(Callable<h> callable) {
        Objects.requireNonNull(callable, "scheduler == null");
        e<Callable<h>, h> eVar = f4848a;
        return eVar == null ? c(callable) : b(eVar, callable);
    }

    public static h e(h hVar) {
        Objects.requireNonNull(hVar, "scheduler == null");
        e<h, h> eVar = f4849b;
        return eVar == null ? hVar : (h) a(eVar, hVar);
    }
}

package r4;

import java.util.Objects;
import java.util.concurrent.Callable;
import q4.d;
import u4.b;
import v4.e;

/* JADX INFO: compiled from: RxAndroidPlugins.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile e<Callable<d>, d> f9378a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static volatile e<d, d> f9379b;

    static <T, R> R a(e<T, R> eVar, T t6) {
        try {
            return eVar.a(t6);
        } catch (Throwable th) {
            throw b.a(th);
        }
    }

    static d b(e<Callable<d>, d> eVar, Callable<d> callable) {
        d dVar = (d) a(eVar, callable);
        Objects.requireNonNull(dVar, "Scheduler Callable returned null");
        return dVar;
    }

    static d c(Callable<d> callable) {
        try {
            d dVarCall = callable.call();
            if (dVarCall != null) {
                return dVarCall;
            }
            throw new NullPointerException("Scheduler Callable returned null");
        } catch (Throwable th) {
            throw b.a(th);
        }
    }

    public static d d(Callable<d> callable) {
        Objects.requireNonNull(callable, "scheduler == null");
        e<Callable<d>, d> eVar = f9378a;
        return eVar == null ? c(callable) : b(eVar, callable);
    }

    public static d e(d dVar) {
        Objects.requireNonNull(dVar, "scheduler == null");
        e<d, d> eVar = f9379b;
        return eVar == null ? dVar : (d) a(eVar, dVar);
    }
}

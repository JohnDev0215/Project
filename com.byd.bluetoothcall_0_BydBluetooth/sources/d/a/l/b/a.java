package d.a.l.b;

import d.a.j;
import d.a.m.f;
import java.util.Objects;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: RxAndroidPlugins.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile f<Callable<j>, j> f4225a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static volatile f<j, j> f4226b;

    static <T, R> R a(f<T, R> fVar, T t) {
        try {
            return fVar.apply(t);
        } catch (Throwable th) {
            throw io.reactivex.exceptions.a.a(th);
        }
    }

    static j b(f<Callable<j>, j> fVar, Callable<j> callable) {
        j jVar = (j) a(fVar, callable);
        Objects.requireNonNull(jVar, "Scheduler Callable returned null");
        return jVar;
    }

    static j c(Callable<j> callable) {
        try {
            j jVarCall = callable.call();
            if (jVarCall != null) {
                return jVarCall;
            }
            throw new NullPointerException("Scheduler Callable returned null");
        } catch (Throwable th) {
            throw io.reactivex.exceptions.a.a(th);
        }
    }

    public static j d(Callable<j> callable) {
        Objects.requireNonNull(callable, "scheduler == null");
        f<Callable<j>, j> fVar = f4225a;
        return fVar == null ? c(callable) : b(fVar, callable);
    }

    public static j e(j jVar) {
        Objects.requireNonNull(jVar, "scheduler == null");
        f<j, j> fVar = f4226b;
        return fVar == null ? jVar : (j) a(fVar, jVar);
    }
}

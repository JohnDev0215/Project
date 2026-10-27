package d.a;

import io.reactivex.internal.operators.observable.ObservableCreate;
import io.reactivex.internal.operators.observable.ObservableDebounceTimed;
import io.reactivex.internal.operators.observable.ObservableFlatMap;
import io.reactivex.internal.operators.observable.ObservableObserveOn;
import io.reactivex.internal.operators.observable.ObservableScalarXMap;
import io.reactivex.internal.operators.observable.ObservableSubscribeOn;
import io.reactivex.internal.operators.observable.ObservableUnsubscribeOn;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: Observable.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class e<T> implements h<T> {
    public static int b() {
        return c.a();
    }

    public static <T> e<T> c(g<T> gVar) {
        d.a.n.a.b.d(gVar, "source is null");
        return d.a.p.a.j(new ObservableCreate(gVar));
    }

    public static <T> e<T> f() {
        return d.a.p.a.j(io.reactivex.internal.operators.observable.b.f4293b);
    }

    public static <T> e<T> k(T t) {
        d.a.n.a.b.d(t, "The item is null");
        return d.a.p.a.j(new io.reactivex.internal.operators.observable.c(t));
    }

    @Override // d.a.h
    public final void a(i<? super T> iVar) {
        d.a.n.a.b.d(iVar, "observer is null");
        try {
            i<? super T> iVarO = d.a.p.a.o(this, iVar);
            d.a.n.a.b.d(iVarO, "Plugin returned null Observer");
            o(iVarO);
        } catch (NullPointerException e2) {
            throw e2;
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            d.a.p.a.l(th);
            NullPointerException nullPointerException = new NullPointerException("Actually not, but can't throw other exceptions due to RS");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }

    public final e<T> d(long j, TimeUnit timeUnit) {
        return e(j, timeUnit, d.a.q.a.a());
    }

    public final e<T> e(long j, TimeUnit timeUnit, j jVar) {
        d.a.n.a.b.d(timeUnit, "unit is null");
        d.a.n.a.b.d(jVar, "scheduler is null");
        return d.a.p.a.j(new ObservableDebounceTimed(this, j, timeUnit, jVar));
    }

    public final <R> e<R> g(d.a.m.f<? super T, ? extends h<? extends R>> fVar) {
        return h(fVar, false);
    }

    public final <R> e<R> h(d.a.m.f<? super T, ? extends h<? extends R>> fVar, boolean z) {
        return i(fVar, z, Integer.MAX_VALUE);
    }

    public final <R> e<R> i(d.a.m.f<? super T, ? extends h<? extends R>> fVar, boolean z, int i) {
        return j(fVar, z, i, b());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> e<R> j(d.a.m.f<? super T, ? extends h<? extends R>> fVar, boolean z, int i, int i2) {
        d.a.n.a.b.d(fVar, "mapper is null");
        d.a.n.a.b.e(i, "maxConcurrency");
        d.a.n.a.b.e(i2, "bufferSize");
        if (!(this instanceof d.a.n.b.c)) {
            return d.a.p.a.j(new ObservableFlatMap(this, fVar, z, i, i2));
        }
        Object objCall = ((d.a.n.b.c) this).call();
        return objCall == null ? f() : ObservableScalarXMap.a(objCall, fVar);
    }

    public final <R> e<R> l(d.a.m.f<? super T, ? extends R> fVar) {
        d.a.n.a.b.d(fVar, "mapper is null");
        return d.a.p.a.j(new io.reactivex.internal.operators.observable.d(this, fVar));
    }

    public final e<T> m(j jVar) {
        return n(jVar, false, b());
    }

    public final e<T> n(j jVar, boolean z, int i) {
        d.a.n.a.b.d(jVar, "scheduler is null");
        d.a.n.a.b.e(i, "bufferSize");
        return d.a.p.a.j(new ObservableObserveOn(this, jVar, z, i));
    }

    protected abstract void o(i<? super T> iVar);

    public final e<T> p(j jVar) {
        d.a.n.a.b.d(jVar, "scheduler is null");
        return d.a.p.a.j(new ObservableSubscribeOn(this, jVar));
    }

    public final e<T> q(j jVar) {
        d.a.n.a.b.d(jVar, "scheduler is null");
        return d.a.p.a.j(new ObservableUnsubscribeOn(this, jVar));
    }
}

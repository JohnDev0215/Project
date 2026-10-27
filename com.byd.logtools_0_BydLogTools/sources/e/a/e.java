package e.a;

import io.reactivex.internal.operators.observable.ObservableCreate;
import io.reactivex.internal.operators.observable.ObservableFlatMap;
import io.reactivex.internal.operators.observable.ObservableObserveOn;
import io.reactivex.internal.operators.observable.ObservableScalarXMap;
import io.reactivex.internal.operators.observable.ObservableSubscribeOn;

/* JADX INFO: compiled from: Observable.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class e<T> implements h<T> {
    public static int b() {
        return c.a();
    }

    public static <T> e<T> c(g<T> gVar) {
        e.a.n.a.b.c(gVar, "source is null");
        return e.a.o.a.j(new ObservableCreate(gVar));
    }

    public static <T> e<T> d() {
        return e.a.o.a.j(io.reactivex.internal.operators.observable.b.f5010b);
    }

    public static <T> e<T> i(T t) {
        e.a.n.a.b.c(t, "item is null");
        return e.a.o.a.j(new io.reactivex.internal.operators.observable.c(t));
    }

    @Override // e.a.h
    public final void a(i<? super T> iVar) {
        e.a.n.a.b.c(iVar, "observer is null");
        try {
            i<? super T> iVarN = e.a.o.a.n(this, iVar);
            e.a.n.a.b.c(iVarN, "The RxJavaPlugins.onSubscribe hook returned a null Observer. Please change the handler provided to RxJavaPlugins.setOnObservableSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
            m(iVarN);
        } catch (NullPointerException e2) {
            throw e2;
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            e.a.o.a.k(th);
            NullPointerException nullPointerException = new NullPointerException("Actually not, but can't throw other exceptions due to RS");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }

    public final <R> e<R> e(e.a.m.f<? super T, ? extends h<? extends R>> fVar) {
        return f(fVar, false);
    }

    public final <R> e<R> f(e.a.m.f<? super T, ? extends h<? extends R>> fVar, boolean z) {
        return g(fVar, z, Integer.MAX_VALUE);
    }

    public final <R> e<R> g(e.a.m.f<? super T, ? extends h<? extends R>> fVar, boolean z, int i) {
        return h(fVar, z, i, b());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> e<R> h(e.a.m.f<? super T, ? extends h<? extends R>> fVar, boolean z, int i, int i2) {
        e.a.n.a.b.c(fVar, "mapper is null");
        e.a.n.a.b.d(i, "maxConcurrency");
        e.a.n.a.b.d(i2, "bufferSize");
        if (!(this instanceof e.a.n.b.c)) {
            return e.a.o.a.j(new ObservableFlatMap(this, fVar, z, i, i2));
        }
        Object objCall = ((e.a.n.b.c) this).call();
        return objCall == null ? d() : ObservableScalarXMap.a(objCall, fVar);
    }

    public final <R> e<R> j(e.a.m.f<? super T, ? extends R> fVar) {
        e.a.n.a.b.c(fVar, "mapper is null");
        return e.a.o.a.j(new io.reactivex.internal.operators.observable.d(this, fVar));
    }

    public final e<T> k(j jVar) {
        return l(jVar, false, b());
    }

    public final e<T> l(j jVar, boolean z, int i) {
        e.a.n.a.b.c(jVar, "scheduler is null");
        e.a.n.a.b.d(i, "bufferSize");
        return e.a.o.a.j(new ObservableObserveOn(this, jVar, z, i));
    }

    protected abstract void m(i<? super T> iVar);

    public final e<T> n(j jVar) {
        e.a.n.a.b.c(jVar, "scheduler is null");
        return e.a.o.a.j(new ObservableSubscribeOn(this, jVar));
    }
}

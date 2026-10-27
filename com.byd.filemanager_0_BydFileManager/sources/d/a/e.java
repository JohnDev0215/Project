package d.a;

import io.reactivex.internal.observers.LambdaObserver;
import io.reactivex.internal.operators.observable.ObservableCreate;
import io.reactivex.internal.operators.observable.ObservableObserveOn;
import io.reactivex.internal.operators.observable.ObservableSubscribeOn;

/* JADX INFO: compiled from: Observable.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class e<T> implements h<T> {
    public static int b() {
        return c.a();
    }

    public static <T> e<T> c(g<T> gVar) {
        d.a.n.a.b.c(gVar, "source is null");
        return d.a.o.a.j(new ObservableCreate(gVar));
    }

    @Override // d.a.h
    public final void a(i<? super T> iVar) {
        d.a.n.a.b.c(iVar, "observer is null");
        try {
            i<? super T> iVarQ = d.a.o.a.q(this, iVar);
            d.a.n.a.b.c(iVarQ, "Plugin returned null Observer");
            j(iVarQ);
        } catch (NullPointerException e2) {
            throw e2;
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            d.a.o.a.l(th);
            NullPointerException nullPointerException = new NullPointerException("Actually not, but can't throw other exceptions due to RS");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }

    public final e<T> d(d.a.m.e<? super io.reactivex.disposables.b> eVar, d.a.m.a aVar) {
        d.a.n.a.b.c(eVar, "onSubscribe is null");
        d.a.n.a.b.c(aVar, "onDispose is null");
        return d.a.o.a.j(new io.reactivex.internal.operators.observable.b(this, eVar, aVar));
    }

    public final e<T> e(d.a.m.e<? super io.reactivex.disposables.b> eVar) {
        return d(eVar, d.a.n.a.a.f4529c);
    }

    public final e<T> f(j jVar) {
        return g(jVar, false, b());
    }

    public final e<T> g(j jVar, boolean z, int i) {
        d.a.n.a.b.c(jVar, "scheduler is null");
        d.a.n.a.b.d(i, "bufferSize");
        return d.a.o.a.j(new ObservableObserveOn(this, jVar, z, i));
    }

    public final io.reactivex.disposables.b h(d.a.m.e<? super T> eVar) {
        return i(eVar, d.a.n.a.a.f, d.a.n.a.a.f4529c, d.a.n.a.a.a());
    }

    public final io.reactivex.disposables.b i(d.a.m.e<? super T> eVar, d.a.m.e<? super Throwable> eVar2, d.a.m.a aVar, d.a.m.e<? super io.reactivex.disposables.b> eVar3) {
        d.a.n.a.b.c(eVar, "onNext is null");
        d.a.n.a.b.c(eVar2, "onError is null");
        d.a.n.a.b.c(aVar, "onComplete is null");
        d.a.n.a.b.c(eVar3, "onSubscribe is null");
        LambdaObserver lambdaObserver = new LambdaObserver(eVar, eVar2, aVar, eVar3);
        a(lambdaObserver);
        return lambdaObserver;
    }

    protected abstract void j(i<? super T> iVar);

    public final e<T> k(j jVar) {
        d.a.n.a.b.c(jVar, "scheduler is null");
        return d.a.o.a.j(new ObservableSubscribeOn(this, jVar));
    }
}

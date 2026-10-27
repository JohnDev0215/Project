package c.b;

import a.b.k.l;

/* JADX WARN: Unexpected interfaces in signature: [c.b.a<T>] */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<T> {
    public final c.b.e.b a(c.b.g.b<? super T> bVar) {
        c.b.g.b<Throwable> bVar2 = c.b.h.b.a.f2234d;
        c.b.g.a aVar = c.b.h.b.a.f2232b;
        c.b.g.b<Object> bVar3 = c.b.h.b.a.f2233c;
        c.b.h.b.b.a(bVar, "onNext is null");
        c.b.h.b.b.a(bVar2, "onError is null");
        c.b.h.b.b.a(aVar, "onComplete is null");
        c.b.h.b.b.a(bVar3, "onSubscribe is null");
        c.b.h.c.a aVar2 = new c.b.h.c.a(bVar, bVar2, aVar, bVar3);
        b(aVar2);
        return aVar2;
    }

    public final void b(b<? super T> bVar) {
        c.b.h.b.b.a(bVar, "observer is null");
        try {
            c.b.h.b.b.a(bVar, "The RxJavaPlugins.onSubscribe hook returned a null Observer. Please change the handler provided to RxJavaPlugins.setOnObservableSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
            c(bVar);
        } catch (NullPointerException e2) {
            throw e2;
        } catch (Throwable th) {
            l.i.C0(th);
            c.b.j.a.c(th);
            NullPointerException nullPointerException = new NullPointerException("Actually not, but can't throw other exceptions due to RS");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }

    public abstract void c(b<? super T> bVar);
}

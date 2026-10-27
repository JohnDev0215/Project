package c.a;

/* JADX INFO: loaded from: classes.dex */
public abstract class c<T> implements f<T> {
    public static int b() {
        return b.a();
    }

    public static <T> c<T> c(e<T> eVar) {
        c.a.m.b.b.c(eVar, "source is null");
        return c.a.n.a.j(new c.a.m.e.a.b(eVar));
    }

    @Override // c.a.f
    public final void a(g<? super T> gVar) {
        c.a.m.b.b.c(gVar, "observer is null");
        try {
            g<? super T> gVarP = c.a.n.a.p(this, gVar);
            c.a.m.b.b.c(gVarP, "Plugin returned null Observer");
            j(gVarP);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            c.a.k.b.b(th);
            c.a.n.a.l(th);
            NullPointerException nullPointerException = new NullPointerException("Actually not, but can't throw other exceptions due to RS");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }

    public final c<T> d(c.a.l.d<? super c.a.j.b> dVar, c.a.l.a aVar) {
        c.a.m.b.b.c(dVar, "onSubscribe is null");
        c.a.m.b.b.c(aVar, "onDispose is null");
        return c.a.n.a.j(new c.a.m.e.a.c(this, dVar, aVar));
    }

    public final c<T> e(c.a.l.d<? super c.a.j.b> dVar) {
        return d(dVar, c.a.m.b.a.f1825c);
    }

    public final c<T> f(h hVar) {
        return g(hVar, false, b());
    }

    public final c<T> g(h hVar, boolean z, int i) {
        c.a.m.b.b.c(hVar, "scheduler is null");
        c.a.m.b.b.d(i, "bufferSize");
        return c.a.n.a.j(new c.a.m.e.a.d(this, hVar, z, i));
    }

    public final c.a.j.b h(c.a.l.d<? super T> dVar) {
        return i(dVar, c.a.m.b.a.f, c.a.m.b.a.f1825c, c.a.m.b.a.a());
    }

    public final c.a.j.b i(c.a.l.d<? super T> dVar, c.a.l.d<? super Throwable> dVar2, c.a.l.a aVar, c.a.l.d<? super c.a.j.b> dVar3) {
        c.a.m.b.b.c(dVar, "onNext is null");
        c.a.m.b.b.c(dVar2, "onError is null");
        c.a.m.b.b.c(aVar, "onComplete is null");
        c.a.m.b.b.c(dVar3, "onSubscribe is null");
        c.a.m.d.c cVar = new c.a.m.d.c(dVar, dVar2, aVar, dVar3);
        a(cVar);
        return cVar;
    }

    protected abstract void j(g<? super T> gVar);

    public final c<T> k(h hVar) {
        c.a.m.b.b.c(hVar, "scheduler is null");
        return c.a.n.a.j(new c.a.m.e.a.e(this, hVar));
    }
}

package q4;

/* JADX INFO: compiled from: Single.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class e<T> implements i<T> {
    public static <T> e<T> b(h<T> hVar) {
        x4.b.c(hVar, "source is null");
        return d5.a.j(new y4.a(hVar));
    }

    public static <T> e<T> d(T t6) {
        x4.b.c(t6, "value is null");
        return d5.a.j(new y4.c(t6));
    }

    @Override // q4.i
    public final void a(g<? super T> gVar) {
        x4.b.c(gVar, "subscriber is null");
        g<? super T> gVarO = d5.a.o(this, gVar);
        x4.b.c(gVarO, "The RxJavaPlugins.onSubscribe hook returned a null SingleObserver. Please check the handler provided to RxJavaPlugins.setOnSingleSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
        try {
            g(gVarO);
        } catch (NullPointerException e7) {
            throw e7;
        } catch (Throwable th) {
            u4.b.b(th);
            NullPointerException nullPointerException = new NullPointerException("subscribeActual failed");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }

    public final <R> e<R> c(v4.e<? super T, ? extends i<? extends R>> eVar) {
        x4.b.c(eVar, "mapper is null");
        return d5.a.j(new y4.b(this, eVar));
    }

    public final <R> e<R> e(v4.e<? super T, ? extends R> eVar) {
        x4.b.c(eVar, "mapper is null");
        return d5.a.j(new y4.d(this, eVar));
    }

    public final e<T> f(d dVar) {
        x4.b.c(dVar, "scheduler is null");
        return d5.a.j(new y4.e(this, dVar));
    }

    protected abstract void g(g<? super T> gVar);

    public final e<T> h(d dVar) {
        x4.b.c(dVar, "scheduler is null");
        return d5.a.j(new y4.f(this, dVar));
    }

    public final <E extends g<? super T>> E i(E e7) {
        a(e7);
        return e7;
    }
}

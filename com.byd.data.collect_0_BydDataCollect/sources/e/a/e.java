package e.a;

/* JADX INFO: compiled from: Observable.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class e<T> implements f<T> {

    /* JADX INFO: compiled from: Observable.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a */
        static final /* synthetic */ int[] f4843a;

        static {
            int[] iArr = new int[e.a.a.values().length];
            f4843a = iArr;
            try {
                iArr[e.a.a.DROP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4843a[e.a.a.LATEST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4843a[e.a.a.MISSING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f4843a[e.a.a.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static int b() {
        return c.a();
    }

    @Override // e.a.f
    public final void a(g<? super T> gVar) {
        e.a.n.b.b.c(gVar, "observer is null");
        try {
            g<? super T> gVarR = e.a.p.a.r(this, gVar);
            e.a.n.b.b.c(gVarR, "Plugin returned null Observer");
            h(gVarR);
        } catch (NullPointerException e2) {
            throw e2;
        } catch (Throwable th) {
            e.a.l.b.b(th);
            e.a.p.a.o(th);
            NullPointerException nullPointerException = new NullPointerException("Actually not, but can't throw other exceptions due to RS");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }

    public final b c() {
        return e.a.p.a.j(new e.a.n.e.b.b(this));
    }

    public final e<T> d(h hVar) {
        return e(hVar, false, b());
    }

    public final e<T> e(h hVar, boolean z, int i2) {
        e.a.n.b.b.c(hVar, "scheduler is null");
        e.a.n.b.b.d(i2, "bufferSize");
        return e.a.p.a.m(new e.a.n.e.b.c(this, hVar, z, i2));
    }

    public final d<T> f() {
        return e.a.p.a.l(new e.a.n.e.b.d(this));
    }

    public final i<T> g() {
        return e.a.p.a.n(new e.a.n.e.b.e(this, null));
    }

    protected abstract void h(g<? super T> gVar);

    public final e<T> i(h hVar) {
        e.a.n.b.b.c(hVar, "scheduler is null");
        return e.a.p.a.m(new e.a.n.e.b.f(this, hVar));
    }

    public final <E extends g<? super T>> E j(E e2) {
        a(e2);
        return e2;
    }

    public final c<T> k(e.a.a aVar) {
        e.a.n.e.a.b bVar = new e.a.n.e.a.b(this);
        int i2 = a.f4843a[aVar.ordinal()];
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? bVar.b() : e.a.p.a.k(new e.a.n.e.a.e(bVar)) : bVar : bVar.e() : bVar.d();
    }
}

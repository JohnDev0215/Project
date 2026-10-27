package q1;

import l2.a;

/* JADX INFO: compiled from: LockedResource.java */
/* JADX INFO: loaded from: classes.dex */
final class u<Z> implements v<Z>, a.f {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final h0.e<u<?>> f9111j = l2.a.d(20, new a());

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final l2.c f9112e = l2.c.a();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private v<Z> f9113g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f9114h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f9115i;

    /* JADX INFO: compiled from: LockedResource.java */
    class a implements a.d<u<?>> {
        a() {
        }

        @Override // l2.a.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public u<?> a() {
            return new u<>();
        }
    }

    u() {
    }

    private void b(v<Z> vVar) {
        this.f9115i = false;
        this.f9114h = true;
        this.f9113g = vVar;
    }

    static <Z> u<Z> f(v<Z> vVar) {
        u<Z> uVar = (u) k2.j.d(f9111j.b());
        uVar.b(vVar);
        return uVar;
    }

    private void g() {
        this.f9113g = null;
        f9111j.a(this);
    }

    @Override // q1.v
    public synchronized void a() {
        this.f9112e.c();
        this.f9115i = true;
        if (!this.f9114h) {
            this.f9113g.a();
            g();
        }
    }

    @Override // q1.v
    public int c() {
        return this.f9113g.c();
    }

    @Override // l2.a.f
    public l2.c d() {
        return this.f9112e;
    }

    @Override // q1.v
    public Class<Z> e() {
        return this.f9113g.e();
    }

    @Override // q1.v
    public Z get() {
        return this.f9113g.get();
    }

    synchronized void h() {
        this.f9112e.c();
        if (!this.f9114h) {
            throw new IllegalStateException("Already unlocked");
        }
        this.f9114h = false;
        if (this.f9115i) {
            a();
        }
    }
}

package com.bumptech.glide.load.n;

import com.bumptech.glide.t.l.a;

/* JADX INFO: loaded from: classes.dex */
final class u<Z> implements v<Z>, a.f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final a.g.j.e<u<?>> f2190b = com.bumptech.glide.t.l.a.d(20, new a());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.bumptech.glide.t.l.c f2191c = com.bumptech.glide.t.l.c.a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private v<Z> f2192d;
    private boolean e;
    private boolean f;

    class a implements a.d<u<?>> {
        a() {
        }

        @Override // com.bumptech.glide.t.l.a.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public u<?> a() {
            return new u<>();
        }
    }

    u() {
    }

    private void a(v<Z> vVar) {
        this.f = false;
        this.e = true;
        this.f2192d = vVar;
    }

    static <Z> u<Z> g(v<Z> vVar) {
        u<Z> uVar = (u) com.bumptech.glide.t.j.d(f2190b.b());
        uVar.a(vVar);
        return uVar;
    }

    private void h() {
        this.f2192d = null;
        f2190b.a(this);
    }

    @Override // com.bumptech.glide.load.n.v
    public Z b() {
        return this.f2192d.b();
    }

    @Override // com.bumptech.glide.load.n.v
    public int c() {
        return this.f2192d.c();
    }

    @Override // com.bumptech.glide.load.n.v
    public Class<Z> d() {
        return this.f2192d.d();
    }

    @Override // com.bumptech.glide.t.l.a.f
    public com.bumptech.glide.t.l.c e() {
        return this.f2191c;
    }

    @Override // com.bumptech.glide.load.n.v
    public synchronized void f() {
        this.f2191c.c();
        this.f = true;
        if (!this.e) {
            this.f2192d.f();
            h();
        }
    }

    synchronized void i() {
        this.f2191c.c();
        if (!this.e) {
            throw new IllegalStateException("Already unlocked");
        }
        this.e = false;
        if (this.f) {
            f();
        }
    }
}

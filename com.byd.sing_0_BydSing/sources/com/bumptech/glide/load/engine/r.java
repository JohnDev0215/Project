package com.bumptech.glide.load.engine;

import com.bumptech.glide.m.l.a;

/* JADX INFO: compiled from: LockedResource.java */
/* JADX INFO: loaded from: classes.dex */
final class r<Z> implements s<Z>, a.f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final c.g.j.e<r<?>> f2828b = com.bumptech.glide.m.l.a.d(20, new a());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.bumptech.glide.m.l.c f2829c = com.bumptech.glide.m.l.c.a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private s<Z> f2830d;
    private boolean e;
    private boolean f;

    /* JADX INFO: compiled from: LockedResource.java */
    class a implements a.d<r<?>> {
        a() {
        }

        @Override // com.bumptech.glide.m.l.a.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public r<?> a() {
            return new r<>();
        }
    }

    r() {
    }

    private void e(s<Z> sVar) {
        this.f = false;
        this.e = true;
        this.f2830d = sVar;
    }

    static <Z> r<Z> f(s<Z> sVar) {
        r<Z> rVar = (r) com.bumptech.glide.m.j.d(f2828b.b());
        rVar.e(sVar);
        return rVar;
    }

    private void g() {
        this.f2830d = null;
        f2828b.a(this);
    }

    @Override // com.bumptech.glide.load.engine.s
    public synchronized void a() {
        this.f2829c.c();
        this.f = true;
        if (!this.e) {
            this.f2830d.a();
            g();
        }
    }

    @Override // com.bumptech.glide.load.engine.s
    public int b() {
        return this.f2830d.b();
    }

    @Override // com.bumptech.glide.load.engine.s
    public Class<Z> c() {
        return this.f2830d.c();
    }

    @Override // com.bumptech.glide.m.l.a.f
    public com.bumptech.glide.m.l.c d() {
        return this.f2829c;
    }

    @Override // com.bumptech.glide.load.engine.s
    public Z get() {
        return this.f2830d.get();
    }

    synchronized void h() {
        this.f2829c.c();
        if (!this.e) {
            throw new IllegalStateException("Already unlocked");
        }
        this.e = false;
        if (this.f) {
            a();
        }
    }
}

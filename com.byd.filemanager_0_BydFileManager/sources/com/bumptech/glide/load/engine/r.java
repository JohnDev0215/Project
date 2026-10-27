package com.bumptech.glide.load.engine;

import com.bumptech.glide.o.l.a;

/* JADX INFO: compiled from: LockedResource.java */
/* JADX INFO: loaded from: classes.dex */
final class r<Z> implements s<Z>, a.f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final b.g.j.e<r<?>> f3071b = com.bumptech.glide.o.l.a.d(20, new a());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.bumptech.glide.o.l.c f3072c = com.bumptech.glide.o.l.c.a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private s<Z> f3073d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f3074e;
    private boolean f;

    /* JADX INFO: compiled from: LockedResource.java */
    class a implements a.d<r<?>> {
        a() {
        }

        @Override // com.bumptech.glide.o.l.a.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public r<?> a() {
            return new r<>();
        }
    }

    r() {
    }

    private void b(s<Z> sVar) {
        this.f = false;
        this.f3074e = true;
        this.f3073d = sVar;
    }

    static <Z> r<Z> f(s<Z> sVar) {
        r<Z> rVar = (r) com.bumptech.glide.o.j.d(f3071b.b());
        rVar.b(sVar);
        return rVar;
    }

    private void g() {
        this.f3073d = null;
        f3071b.a(this);
    }

    @Override // com.bumptech.glide.load.engine.s
    public synchronized void a() {
        this.f3072c.c();
        this.f = true;
        if (!this.f3074e) {
            this.f3073d.a();
            g();
        }
    }

    @Override // com.bumptech.glide.load.engine.s
    public int c() {
        return this.f3073d.c();
    }

    @Override // com.bumptech.glide.load.engine.s
    public Class<Z> d() {
        return this.f3073d.d();
    }

    @Override // com.bumptech.glide.o.l.a.f
    public com.bumptech.glide.o.l.c e() {
        return this.f3072c;
    }

    @Override // com.bumptech.glide.load.engine.s
    public Z get() {
        return this.f3073d.get();
    }

    synchronized void h() {
        this.f3072c.c();
        if (!this.f3074e) {
            throw new IllegalStateException("Already unlocked");
        }
        this.f3074e = false;
        if (this.f) {
            a();
        }
    }
}

package com.bumptech.glide.load.n;

/* JADX INFO: loaded from: classes.dex */
class p<Z> implements v<Z> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f2176b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f2177c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final v<Z> f2178d;
    private final a e;
    private final com.bumptech.glide.load.g f;
    private int g;
    private boolean h;

    interface a {
        void a(com.bumptech.glide.load.g gVar, p<?> pVar);
    }

    p(v<Z> vVar, boolean z, boolean z2, com.bumptech.glide.load.g gVar, a aVar) {
        this.f2178d = (v) com.bumptech.glide.t.j.d(vVar);
        this.f2176b = z;
        this.f2177c = z2;
        this.f = gVar;
        this.e = (a) com.bumptech.glide.t.j.d(aVar);
    }

    synchronized void a() {
        if (this.h) {
            throw new IllegalStateException("Cannot acquire a recycled resource");
        }
        this.g++;
    }

    @Override // com.bumptech.glide.load.n.v
    public Z b() {
        return this.f2178d.b();
    }

    @Override // com.bumptech.glide.load.n.v
    public int c() {
        return this.f2178d.c();
    }

    @Override // com.bumptech.glide.load.n.v
    public Class<Z> d() {
        return this.f2178d.d();
    }

    v<Z> e() {
        return this.f2178d;
    }

    @Override // com.bumptech.glide.load.n.v
    public synchronized void f() {
        if (this.g > 0) {
            throw new IllegalStateException("Cannot recycle a resource while it is still acquired");
        }
        if (this.h) {
            throw new IllegalStateException("Cannot recycle a resource that has already been recycled");
        }
        this.h = true;
        if (this.f2177c) {
            this.f2178d.f();
        }
    }

    boolean g() {
        return this.f2176b;
    }

    void h() {
        boolean z;
        synchronized (this) {
            int i = this.g;
            if (i <= 0) {
                throw new IllegalStateException("Cannot release a recycled or not yet acquired resource");
            }
            z = true;
            int i2 = i - 1;
            this.g = i2;
            if (i2 != 0) {
                z = false;
            }
        }
        if (z) {
            this.e.a(this.f, this);
        }
    }

    public synchronized String toString() {
        return "EngineResource{isMemoryCacheable=" + this.f2176b + ", listener=" + this.e + ", key=" + this.f + ", acquired=" + this.g + ", isRecycled=" + this.h + ", resource=" + this.f2178d + '}';
    }
}

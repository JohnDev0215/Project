package com.bumptech.glide.load.engine;

/* JADX INFO: compiled from: EngineResource.java */
/* JADX INFO: loaded from: classes.dex */
class n<Z> implements s<Z> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f2819b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f2820c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final s<Z> f2821d;
    private final a e;
    private final com.bumptech.glide.load.c f;
    private int g;
    private boolean h;

    /* JADX INFO: compiled from: EngineResource.java */
    interface a {
        void d(com.bumptech.glide.load.c cVar, n<?> nVar);
    }

    n(s<Z> sVar, boolean z, boolean z2, com.bumptech.glide.load.c cVar, a aVar) {
        this.f2821d = (s) com.bumptech.glide.m.j.d(sVar);
        this.f2819b = z;
        this.f2820c = z2;
        this.f = cVar;
        this.e = (a) com.bumptech.glide.m.j.d(aVar);
    }

    @Override // com.bumptech.glide.load.engine.s
    public synchronized void a() {
        if (this.g > 0) {
            throw new IllegalStateException("Cannot recycle a resource while it is still acquired");
        }
        if (this.h) {
            throw new IllegalStateException("Cannot recycle a resource that has already been recycled");
        }
        this.h = true;
        if (this.f2820c) {
            this.f2821d.a();
        }
    }

    @Override // com.bumptech.glide.load.engine.s
    public int b() {
        return this.f2821d.b();
    }

    @Override // com.bumptech.glide.load.engine.s
    public Class<Z> c() {
        return this.f2821d.c();
    }

    synchronized void d() {
        if (this.h) {
            throw new IllegalStateException("Cannot acquire a recycled resource");
        }
        this.g++;
    }

    s<Z> e() {
        return this.f2821d;
    }

    boolean f() {
        return this.f2819b;
    }

    void g() {
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
            this.e.d(this.f, this);
        }
    }

    @Override // com.bumptech.glide.load.engine.s
    public Z get() {
        return this.f2821d.get();
    }

    public synchronized String toString() {
        return "EngineResource{isMemoryCacheable=" + this.f2819b + ", listener=" + this.e + ", key=" + this.f + ", acquired=" + this.g + ", isRecycled=" + this.h + ", resource=" + this.f2821d + '}';
    }
}

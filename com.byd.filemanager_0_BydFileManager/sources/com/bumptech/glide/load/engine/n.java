package com.bumptech.glide.load.engine;

/* JADX INFO: compiled from: EngineResource.java */
/* JADX INFO: loaded from: classes.dex */
class n<Z> implements s<Z> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f3061b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f3062c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final s<Z> f3063d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final a f3064e;
    private final com.bumptech.glide.load.c f;
    private int g;
    private boolean h;

    /* JADX INFO: compiled from: EngineResource.java */
    interface a {
        void a(com.bumptech.glide.load.c cVar, n<?> nVar);
    }

    n(s<Z> sVar, boolean z, boolean z2, com.bumptech.glide.load.c cVar, a aVar) {
        this.f3063d = (s) com.bumptech.glide.o.j.d(sVar);
        this.f3061b = z;
        this.f3062c = z2;
        this.f = cVar;
        this.f3064e = (a) com.bumptech.glide.o.j.d(aVar);
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
        if (this.f3062c) {
            this.f3063d.a();
        }
    }

    synchronized void b() {
        if (this.h) {
            throw new IllegalStateException("Cannot acquire a recycled resource");
        }
        this.g++;
    }

    @Override // com.bumptech.glide.load.engine.s
    public int c() {
        return this.f3063d.c();
    }

    @Override // com.bumptech.glide.load.engine.s
    public Class<Z> d() {
        return this.f3063d.d();
    }

    s<Z> e() {
        return this.f3063d;
    }

    boolean f() {
        return this.f3061b;
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
            this.f3064e.a(this.f, this);
        }
    }

    @Override // com.bumptech.glide.load.engine.s
    public Z get() {
        return this.f3063d.get();
    }

    public synchronized String toString() {
        return "EngineResource{isMemoryCacheable=" + this.f3061b + ", listener=" + this.f3064e + ", key=" + this.f + ", acquired=" + this.g + ", isRecycled=" + this.h + ", resource=" + this.f3063d + '}';
    }
}

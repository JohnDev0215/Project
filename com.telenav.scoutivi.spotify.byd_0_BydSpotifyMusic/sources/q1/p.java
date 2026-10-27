package q1;

/* JADX INFO: compiled from: EngineResource.java */
/* JADX INFO: loaded from: classes.dex */
class p<Z> implements v<Z> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final boolean f9089e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final boolean f9090g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final v<Z> f9091h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final a f9092i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final n1.f f9093j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f9094k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private boolean f9095l;

    /* JADX INFO: compiled from: EngineResource.java */
    interface a {
        void b(n1.f fVar, p<?> pVar);
    }

    p(v<Z> vVar, boolean z6, boolean z7, n1.f fVar, a aVar) {
        this.f9091h = (v) k2.j.d(vVar);
        this.f9089e = z6;
        this.f9090g = z7;
        this.f9093j = fVar;
        this.f9092i = (a) k2.j.d(aVar);
    }

    @Override // q1.v
    public synchronized void a() {
        if (this.f9094k > 0) {
            throw new IllegalStateException("Cannot recycle a resource while it is still acquired");
        }
        if (this.f9095l) {
            throw new IllegalStateException("Cannot recycle a resource that has already been recycled");
        }
        this.f9095l = true;
        if (this.f9090g) {
            this.f9091h.a();
        }
    }

    synchronized void b() {
        if (this.f9095l) {
            throw new IllegalStateException("Cannot acquire a recycled resource");
        }
        this.f9094k++;
    }

    @Override // q1.v
    public int c() {
        return this.f9091h.c();
    }

    v<Z> d() {
        return this.f9091h;
    }

    @Override // q1.v
    public Class<Z> e() {
        return this.f9091h.e();
    }

    boolean f() {
        return this.f9089e;
    }

    void g() {
        boolean z6;
        synchronized (this) {
            int i7 = this.f9094k;
            if (i7 <= 0) {
                throw new IllegalStateException("Cannot release a recycled or not yet acquired resource");
            }
            z6 = true;
            int i8 = i7 - 1;
            this.f9094k = i8;
            if (i8 != 0) {
                z6 = false;
            }
        }
        if (z6) {
            this.f9092i.b(this.f9093j, this);
        }
    }

    @Override // q1.v
    public Z get() {
        return this.f9091h.get();
    }

    public synchronized String toString() {
        return "EngineResource{isMemoryCacheable=" + this.f9089e + ", listener=" + this.f9092i + ", key=" + this.f9093j + ", acquired=" + this.f9094k + ", isRecycled=" + this.f9095l + ", resource=" + this.f9091h + '}';
    }
}

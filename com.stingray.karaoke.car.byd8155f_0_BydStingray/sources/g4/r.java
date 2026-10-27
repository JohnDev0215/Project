package g4;

/* JADX INFO: loaded from: classes.dex */
public final class r implements x {
    public final boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f14946m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final x f14947n;
    public final l o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final q f14948p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f14949q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f14950r;

    public r(x xVar, boolean z6, boolean z10, q qVar, l lVar) {
        A4.h.c(xVar, "Argument must not be null");
        this.f14947n = xVar;
        this.l = z6;
        this.f14946m = z10;
        this.f14948p = qVar;
        A4.h.c(lVar, "Argument must not be null");
        this.o = lVar;
    }

    public final synchronized void a() {
        if (this.f14950r) {
            throw new IllegalStateException("Cannot acquire a recycled resource");
        }
        this.f14949q++;
    }

    @Override // g4.x
    public final int b() {
        return this.f14947n.b();
    }

    public final void c() {
        boolean z6;
        synchronized (this) {
            int i7 = this.f14949q;
            if (i7 <= 0) {
                throw new IllegalStateException("Cannot release a recycled or not yet acquired resource");
            }
            z6 = true;
            int i10 = i7 - 1;
            this.f14949q = i10;
            if (i10 != 0) {
                z6 = false;
            }
        }
        if (z6) {
            this.o.e(this.f14948p, this);
        }
    }

    @Override // g4.x
    public final Class d() {
        return this.f14947n.d();
    }

    @Override // g4.x
    public final synchronized void e() {
        if (this.f14949q > 0) {
            throw new IllegalStateException("Cannot recycle a resource while it is still acquired");
        }
        if (this.f14950r) {
            throw new IllegalStateException("Cannot recycle a resource that has already been recycled");
        }
        this.f14950r = true;
        if (this.f14946m) {
            this.f14947n.e();
        }
    }

    @Override // g4.x
    public final Object get() {
        return this.f14947n.get();
    }

    public final synchronized String toString() {
        return "EngineResource{isMemoryCacheable=" + this.l + ", listener=" + this.o + ", key=" + this.f14948p + ", acquired=" + this.f14949q + ", isRecycled=" + this.f14950r + ", resource=" + this.f14947n + '}';
    }
}

package w4;

/* JADX INFO: loaded from: classes.dex */
public final class b implements d, c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f23288a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f23289b;
    public volatile c c;
    public volatile c d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f23290e = 3;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f23291f = 3;

    public b(Object obj, d dVar) {
        this.f23288a = obj;
        this.f23289b = dVar;
    }

    @Override // w4.d, w4.c
    public final boolean a() {
        boolean z6;
        synchronized (this.f23288a) {
            try {
                z6 = this.c.a() || this.d.a();
            } finally {
            }
        }
        return z6;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Object, w4.d] */
    @Override // w4.d
    public final boolean b(c cVar) {
        boolean z6;
        synchronized (this.f23288a) {
            ?? r12 = this.f23289b;
            z6 = (r12 == 0 || r12.b(this)) && cVar.equals(this.c);
        }
        return z6;
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.Object, w4.d] */
    @Override // w4.d
    public final void c(c cVar) {
        synchronized (this.f23288a) {
            try {
                if (cVar.equals(this.d)) {
                    this.f23291f = 5;
                    ?? r32 = this.f23289b;
                    if (r32 != 0) {
                        r32.c(this);
                    }
                    return;
                }
                this.f23290e = 5;
                if (this.f23291f != 1) {
                    this.f23291f = 1;
                    this.d.g();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // w4.c
    public final void clear() {
        synchronized (this.f23288a) {
            try {
                this.f23290e = 3;
                this.c.clear();
                if (this.f23291f != 3) {
                    this.f23291f = 3;
                    this.d.clear();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // w4.c
    public final boolean d() {
        boolean z6;
        synchronized (this.f23288a) {
            try {
                z6 = this.f23290e == 3 && this.f23291f == 3;
            } finally {
            }
        }
        return z6;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, w4.d] */
    @Override // w4.d
    public final boolean e(c cVar) {
        boolean z6;
        synchronized (this.f23288a) {
            ?? r02 = this.f23289b;
            z6 = r02 == 0 || r02.e(this);
        }
        return z6;
    }

    @Override // w4.c
    public final boolean f(c cVar) {
        if (!(cVar instanceof b)) {
            return false;
        }
        b bVar = (b) cVar;
        return this.c.f(bVar.c) && this.d.f(bVar.d);
    }

    @Override // w4.c
    public final void g() {
        synchronized (this.f23288a) {
            try {
                if (this.f23290e != 1) {
                    this.f23290e = 1;
                    this.c.g();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Object, w4.d] */
    /* JADX WARN: Type inference failed for: r2v2, types: [w4.d] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    @Override // w4.d
    public final d getRoot() {
        ?? root;
        synchronized (this.f23288a) {
            try {
                ?? r12 = this.f23289b;
                this = this;
                if (r12 != 0) {
                    root = r12.getRoot();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return root;
    }

    @Override // w4.c
    public final boolean h() {
        boolean z6;
        synchronized (this.f23288a) {
            try {
                z6 = this.f23290e == 4 || this.f23291f == 4;
            } finally {
            }
        }
        return z6;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Object, w4.d] */
    @Override // w4.d
    public final boolean i(c cVar) {
        boolean z6;
        int i7;
        synchronized (this.f23288a) {
            ?? r12 = this.f23289b;
            z6 = false;
            if (r12 == 0 || r12.i(this)) {
                if (this.f23290e != 5 ? cVar.equals(this.c) : cVar.equals(this.d) && ((i7 = this.f23291f) == 4 || i7 == 5)) {
                    z6 = true;
                }
            }
        }
        return z6;
    }

    @Override // w4.c
    public final boolean isRunning() {
        boolean z6;
        synchronized (this.f23288a) {
            try {
                z6 = true;
                if (this.f23290e != 1 && this.f23291f != 1) {
                    z6 = false;
                }
            } finally {
            }
        }
        return z6;
    }

    /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.Object, w4.d] */
    @Override // w4.d
    public final void j(c cVar) {
        synchronized (this.f23288a) {
            try {
                if (cVar.equals(this.c)) {
                    this.f23290e = 4;
                } else if (cVar.equals(this.d)) {
                    this.f23291f = 4;
                }
                ?? r42 = this.f23289b;
                if (r42 != 0) {
                    r42.j(this);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

package w4;

/* JADX INFO: loaded from: classes.dex */
public final class h implements d, c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d f23320a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f23321b;
    public volatile g c;
    public volatile c d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f23322e = 3;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f23323f = 3;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f23324g;

    public h(Object obj, d dVar) {
        this.f23321b = obj;
        this.f23320a = dVar;
    }

    @Override // w4.d, w4.c
    public final boolean a() {
        boolean z6;
        synchronized (this.f23321b) {
            try {
                z6 = this.d.a() || this.c.a();
            } finally {
            }
        }
        return z6;
    }

    @Override // w4.d
    public final boolean b(c cVar) {
        boolean z6;
        synchronized (this.f23321b) {
            try {
                d dVar = this.f23320a;
                z6 = (dVar == null || dVar.b(this)) && cVar.equals(this.c) && this.f23322e != 2;
            } finally {
            }
        }
        return z6;
    }

    @Override // w4.d
    public final void c(c cVar) {
        synchronized (this.f23321b) {
            try {
                if (!cVar.equals(this.c)) {
                    this.f23323f = 5;
                    return;
                }
                this.f23322e = 5;
                d dVar = this.f23320a;
                if (dVar != null) {
                    dVar.c(this);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // w4.c
    public final void clear() {
        synchronized (this.f23321b) {
            this.f23324g = false;
            this.f23322e = 3;
            this.f23323f = 3;
            this.d.clear();
            this.c.clear();
        }
    }

    @Override // w4.c
    public final boolean d() {
        boolean z6;
        synchronized (this.f23321b) {
            z6 = this.f23322e == 3;
        }
        return z6;
    }

    @Override // w4.d
    public final boolean e(c cVar) {
        boolean z6;
        synchronized (this.f23321b) {
            try {
                d dVar = this.f23320a;
                z6 = (dVar == null || dVar.e(this)) && (cVar.equals(this.c) || this.f23322e != 4);
            } finally {
            }
        }
        return z6;
    }

    @Override // w4.c
    public final boolean f(c cVar) {
        if (!(cVar instanceof h)) {
            return false;
        }
        h hVar = (h) cVar;
        if (this.c == null) {
            if (hVar.c != null) {
                return false;
            }
        } else if (!this.c.f(hVar.c)) {
            return false;
        }
        if (this.d == null) {
            if (hVar.d != null) {
                return false;
            }
        } else if (!this.d.f(hVar.d)) {
            return false;
        }
        return true;
    }

    @Override // w4.c
    public final void g() {
        synchronized (this.f23321b) {
            try {
                this.f23324g = true;
                try {
                    if (this.f23322e != 4 && this.f23323f != 1) {
                        this.f23323f = 1;
                        this.d.g();
                    }
                    if (this.f23324g && this.f23322e != 1) {
                        this.f23322e = 1;
                        this.c.g();
                    }
                    this.f23324g = false;
                } catch (Throwable th) {
                    this.f23324g = false;
                    throw th;
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [w4.d] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    @Override // w4.d
    public final d getRoot() {
        ?? root;
        synchronized (this.f23321b) {
            try {
                d dVar = this.f23320a;
                this = this;
                if (dVar != null) {
                    root = dVar.getRoot();
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
        synchronized (this.f23321b) {
            z6 = this.f23322e == 4;
        }
        return z6;
    }

    @Override // w4.d
    public final boolean i(c cVar) {
        boolean z6;
        synchronized (this.f23321b) {
            try {
                d dVar = this.f23320a;
                z6 = (dVar == null || dVar.i(this)) && cVar.equals(this.c) && !a();
            } finally {
            }
        }
        return z6;
    }

    @Override // w4.c
    public final boolean isRunning() {
        boolean z6;
        synchronized (this.f23321b) {
            z6 = true;
            if (this.f23322e != 1) {
                z6 = false;
            }
        }
        return z6;
    }

    @Override // w4.d
    public final void j(c cVar) {
        synchronized (this.f23321b) {
            try {
                if (cVar.equals(this.d)) {
                    this.f23323f = 4;
                    return;
                }
                this.f23322e = 4;
                d dVar = this.f23320a;
                if (dVar != null) {
                    dVar.j(this);
                }
                int i7 = this.f23323f;
                boolean z6 = false;
                if (i7 != 1 && i7 != 2 && i7 != 3) {
                    if (i7 != 4 && i7 != 5) {
                        throw null;
                    }
                    z6 = true;
                }
                if (!z6) {
                    this.d.clear();
                }
            } finally {
            }
        }
    }
}

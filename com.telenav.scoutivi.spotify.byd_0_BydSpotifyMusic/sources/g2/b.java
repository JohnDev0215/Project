package g2;

import g2.d;

/* JADX INFO: compiled from: ErrorRequestCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements d, c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f6484a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final d f6485b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile c f6486c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private volatile c f6487d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private d.a f6488e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private d.a f6489f;

    public b(Object obj, d dVar) {
        d.a aVar = d.a.CLEARED;
        this.f6488e = aVar;
        this.f6489f = aVar;
        this.f6484a = obj;
        this.f6485b = dVar;
    }

    private boolean l(c cVar) {
        return cVar.equals(this.f6486c) || (this.f6488e == d.a.FAILED && cVar.equals(this.f6487d));
    }

    private boolean m() {
        d dVar = this.f6485b;
        return dVar == null || dVar.b(this);
    }

    private boolean n() {
        d dVar = this.f6485b;
        return dVar == null || dVar.f(this);
    }

    private boolean o() {
        d dVar = this.f6485b;
        return dVar == null || dVar.a(this);
    }

    private boolean p() {
        d dVar = this.f6485b;
        return dVar != null && dVar.j();
    }

    @Override // g2.d
    public boolean a(c cVar) {
        boolean z6;
        synchronized (this.f6484a) {
            z6 = o() && l(cVar);
        }
        return z6;
    }

    @Override // g2.d
    public boolean b(c cVar) {
        boolean z6;
        synchronized (this.f6484a) {
            z6 = m() && l(cVar);
        }
        return z6;
    }

    @Override // g2.c
    public void c() {
        synchronized (this.f6484a) {
            d.a aVar = this.f6488e;
            d.a aVar2 = d.a.RUNNING;
            if (aVar == aVar2) {
                this.f6488e = d.a.PAUSED;
                this.f6486c.c();
            }
            if (this.f6489f == aVar2) {
                this.f6489f = d.a.PAUSED;
                this.f6487d.c();
            }
        }
    }

    @Override // g2.c
    public void clear() {
        synchronized (this.f6484a) {
            d.a aVar = d.a.CLEARED;
            this.f6488e = aVar;
            this.f6486c.clear();
            if (this.f6489f != aVar) {
                this.f6489f = aVar;
                this.f6487d.clear();
            }
        }
    }

    @Override // g2.c
    public void d() {
        synchronized (this.f6484a) {
            d.a aVar = this.f6488e;
            d.a aVar2 = d.a.RUNNING;
            if (aVar != aVar2) {
                this.f6488e = aVar2;
                this.f6486c.d();
            }
        }
    }

    @Override // g2.d
    public void e(c cVar) {
        synchronized (this.f6484a) {
            if (cVar.equals(this.f6487d)) {
                this.f6489f = d.a.FAILED;
                d dVar = this.f6485b;
                if (dVar != null) {
                    dVar.e(this);
                }
                return;
            }
            this.f6488e = d.a.FAILED;
            d.a aVar = this.f6489f;
            d.a aVar2 = d.a.RUNNING;
            if (aVar != aVar2) {
                this.f6489f = aVar2;
                this.f6487d.d();
            }
        }
    }

    @Override // g2.d
    public boolean f(c cVar) {
        boolean z6;
        synchronized (this.f6484a) {
            z6 = n() && l(cVar);
        }
        return z6;
    }

    @Override // g2.c
    public boolean g(c cVar) {
        if (!(cVar instanceof b)) {
            return false;
        }
        b bVar = (b) cVar;
        return this.f6486c.g(bVar.f6486c) && this.f6487d.g(bVar.f6487d);
    }

    @Override // g2.c
    public boolean h() {
        boolean z6;
        synchronized (this.f6484a) {
            d.a aVar = this.f6488e;
            d.a aVar2 = d.a.SUCCESS;
            z6 = aVar == aVar2 || this.f6489f == aVar2;
        }
        return z6;
    }

    @Override // g2.c
    public boolean i() {
        boolean z6;
        synchronized (this.f6484a) {
            d.a aVar = this.f6488e;
            d.a aVar2 = d.a.CLEARED;
            z6 = aVar == aVar2 && this.f6489f == aVar2;
        }
        return z6;
    }

    @Override // g2.c
    public boolean isRunning() {
        boolean z6;
        synchronized (this.f6484a) {
            d.a aVar = this.f6488e;
            d.a aVar2 = d.a.RUNNING;
            z6 = aVar == aVar2 || this.f6489f == aVar2;
        }
        return z6;
    }

    @Override // g2.d
    public boolean j() {
        boolean z6;
        synchronized (this.f6484a) {
            z6 = p() || h();
        }
        return z6;
    }

    @Override // g2.d
    public void k(c cVar) {
        synchronized (this.f6484a) {
            if (cVar.equals(this.f6486c)) {
                this.f6488e = d.a.SUCCESS;
            } else if (cVar.equals(this.f6487d)) {
                this.f6489f = d.a.SUCCESS;
            }
            d dVar = this.f6485b;
            if (dVar != null) {
                dVar.k(this);
            }
        }
    }

    public void q(c cVar, c cVar2) {
        this.f6486c = cVar;
        this.f6487d = cVar2;
    }
}

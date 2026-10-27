package g2;

import g2.d;

/* JADX INFO: compiled from: ThumbnailRequestCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public class i implements d, c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d f6519a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Object f6520b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile c f6521c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private volatile c f6522d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private d.a f6523e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private d.a f6524f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f6525g;

    public i(Object obj, d dVar) {
        d.a aVar = d.a.CLEARED;
        this.f6523e = aVar;
        this.f6524f = aVar;
        this.f6520b = obj;
        this.f6519a = dVar;
    }

    private boolean l() {
        boolean z6;
        synchronized (this.f6520b) {
            d.a aVar = this.f6523e;
            d.a aVar2 = d.a.SUCCESS;
            z6 = aVar == aVar2 || this.f6524f == aVar2;
        }
        return z6;
    }

    private boolean m() {
        d dVar = this.f6519a;
        return dVar == null || dVar.b(this);
    }

    private boolean n() {
        d dVar = this.f6519a;
        return dVar == null || dVar.f(this);
    }

    private boolean o() {
        d dVar = this.f6519a;
        return dVar == null || dVar.a(this);
    }

    private boolean p() {
        d dVar = this.f6519a;
        return dVar != null && dVar.j();
    }

    @Override // g2.d
    public boolean a(c cVar) {
        boolean z6;
        synchronized (this.f6520b) {
            z6 = o() && (cVar.equals(this.f6521c) || this.f6523e != d.a.SUCCESS);
        }
        return z6;
    }

    @Override // g2.d
    public boolean b(c cVar) {
        boolean z6;
        synchronized (this.f6520b) {
            z6 = m() && cVar.equals(this.f6521c) && this.f6523e != d.a.PAUSED;
        }
        return z6;
    }

    @Override // g2.c
    public void c() {
        synchronized (this.f6520b) {
            if (!this.f6524f.a()) {
                this.f6524f = d.a.PAUSED;
                this.f6522d.c();
            }
            if (!this.f6523e.a()) {
                this.f6523e = d.a.PAUSED;
                this.f6521c.c();
            }
        }
    }

    @Override // g2.c
    public void clear() {
        synchronized (this.f6520b) {
            this.f6525g = false;
            d.a aVar = d.a.CLEARED;
            this.f6523e = aVar;
            this.f6524f = aVar;
            this.f6522d.clear();
            this.f6521c.clear();
        }
    }

    @Override // g2.c
    public void d() {
        synchronized (this.f6520b) {
            this.f6525g = true;
            try {
                if (this.f6523e != d.a.SUCCESS) {
                    d.a aVar = this.f6524f;
                    d.a aVar2 = d.a.RUNNING;
                    if (aVar != aVar2) {
                        this.f6524f = aVar2;
                        this.f6522d.d();
                    }
                }
                if (this.f6525g) {
                    d.a aVar3 = this.f6523e;
                    d.a aVar4 = d.a.RUNNING;
                    if (aVar3 != aVar4) {
                        this.f6523e = aVar4;
                        this.f6521c.d();
                    }
                }
            } finally {
                this.f6525g = false;
            }
        }
    }

    @Override // g2.d
    public void e(c cVar) {
        synchronized (this.f6520b) {
            if (!cVar.equals(this.f6521c)) {
                this.f6524f = d.a.FAILED;
                return;
            }
            this.f6523e = d.a.FAILED;
            d dVar = this.f6519a;
            if (dVar != null) {
                dVar.e(this);
            }
        }
    }

    @Override // g2.d
    public boolean f(c cVar) {
        boolean z6;
        synchronized (this.f6520b) {
            z6 = n() && cVar.equals(this.f6521c) && !l();
        }
        return z6;
    }

    @Override // g2.c
    public boolean g(c cVar) {
        if (!(cVar instanceof i)) {
            return false;
        }
        i iVar = (i) cVar;
        if (this.f6521c == null) {
            if (iVar.f6521c != null) {
                return false;
            }
        } else if (!this.f6521c.g(iVar.f6521c)) {
            return false;
        }
        if (this.f6522d == null) {
            if (iVar.f6522d != null) {
                return false;
            }
        } else if (!this.f6522d.g(iVar.f6522d)) {
            return false;
        }
        return true;
    }

    @Override // g2.c
    public boolean h() {
        boolean z6;
        synchronized (this.f6520b) {
            z6 = this.f6523e == d.a.SUCCESS;
        }
        return z6;
    }

    @Override // g2.c
    public boolean i() {
        boolean z6;
        synchronized (this.f6520b) {
            z6 = this.f6523e == d.a.CLEARED;
        }
        return z6;
    }

    @Override // g2.c
    public boolean isRunning() {
        boolean z6;
        synchronized (this.f6520b) {
            z6 = this.f6523e == d.a.RUNNING;
        }
        return z6;
    }

    @Override // g2.d
    public boolean j() {
        boolean z6;
        synchronized (this.f6520b) {
            z6 = p() || l();
        }
        return z6;
    }

    @Override // g2.d
    public void k(c cVar) {
        synchronized (this.f6520b) {
            if (cVar.equals(this.f6522d)) {
                this.f6524f = d.a.SUCCESS;
                return;
            }
            this.f6523e = d.a.SUCCESS;
            d dVar = this.f6519a;
            if (dVar != null) {
                dVar.k(this);
            }
            if (!this.f6524f.a()) {
                this.f6522d.clear();
            }
        }
    }

    public void q(c cVar, c cVar2) {
        this.f6521c = cVar;
        this.f6522d = cVar2;
    }
}

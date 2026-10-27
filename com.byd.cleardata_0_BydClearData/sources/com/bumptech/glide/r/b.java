package com.bumptech.glide.r;

import com.bumptech.glide.r.d;

/* JADX INFO: loaded from: classes.dex */
public final class b implements d, c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f2498a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final d f2499b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile c f2500c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private volatile c f2501d;
    private d.a e;
    private d.a f;

    public b(Object obj, d dVar) {
        d.a aVar = d.a.CLEARED;
        this.e = aVar;
        this.f = aVar;
        this.f2498a = obj;
        this.f2499b = dVar;
    }

    private boolean m(c cVar) {
        return cVar.equals(this.f2500c) || (this.e == d.a.FAILED && cVar.equals(this.f2501d));
    }

    private boolean n() {
        d dVar = this.f2499b;
        return dVar == null || dVar.l(this);
    }

    private boolean o() {
        d dVar = this.f2499b;
        return dVar == null || dVar.k(this);
    }

    private boolean p() {
        d dVar = this.f2499b;
        return dVar == null || dVar.a(this);
    }

    @Override // com.bumptech.glide.r.d
    public boolean a(c cVar) {
        boolean z;
        synchronized (this.f2498a) {
            z = p() && m(cVar);
        }
        return z;
    }

    @Override // com.bumptech.glide.r.d, com.bumptech.glide.r.c
    public boolean b() {
        boolean z;
        synchronized (this.f2498a) {
            z = this.f2500c.b() || this.f2501d.b();
        }
        return z;
    }

    @Override // com.bumptech.glide.r.d
    public d c() {
        d dVarC;
        synchronized (this.f2498a) {
            d dVar = this.f2499b;
            dVarC = dVar != null ? dVar.c() : this;
        }
        return dVarC;
    }

    @Override // com.bumptech.glide.r.c
    public void clear() {
        synchronized (this.f2498a) {
            d.a aVar = d.a.CLEARED;
            this.e = aVar;
            this.f2500c.clear();
            if (this.f != aVar) {
                this.f = aVar;
                this.f2501d.clear();
            }
        }
    }

    @Override // com.bumptech.glide.r.d
    public void d(c cVar) {
        synchronized (this.f2498a) {
            if (cVar.equals(this.f2501d)) {
                this.f = d.a.FAILED;
                d dVar = this.f2499b;
                if (dVar != null) {
                    dVar.d(this);
                }
                return;
            }
            this.e = d.a.FAILED;
            d.a aVar = this.f;
            d.a aVar2 = d.a.RUNNING;
            if (aVar != aVar2) {
                this.f = aVar2;
                this.f2501d.f();
            }
        }
    }

    @Override // com.bumptech.glide.r.c
    public void e() {
        synchronized (this.f2498a) {
            d.a aVar = this.e;
            d.a aVar2 = d.a.RUNNING;
            if (aVar == aVar2) {
                this.e = d.a.PAUSED;
                this.f2500c.e();
            }
            if (this.f == aVar2) {
                this.f = d.a.PAUSED;
                this.f2501d.e();
            }
        }
    }

    @Override // com.bumptech.glide.r.c
    public void f() {
        synchronized (this.f2498a) {
            d.a aVar = this.e;
            d.a aVar2 = d.a.RUNNING;
            if (aVar != aVar2) {
                this.e = aVar2;
                this.f2500c.f();
            }
        }
    }

    @Override // com.bumptech.glide.r.d
    public void g(c cVar) {
        synchronized (this.f2498a) {
            if (cVar.equals(this.f2500c)) {
                this.e = d.a.SUCCESS;
            } else if (cVar.equals(this.f2501d)) {
                this.f = d.a.SUCCESS;
            }
            d dVar = this.f2499b;
            if (dVar != null) {
                dVar.g(this);
            }
        }
    }

    @Override // com.bumptech.glide.r.c
    public boolean h(c cVar) {
        if (!(cVar instanceof b)) {
            return false;
        }
        b bVar = (b) cVar;
        return this.f2500c.h(bVar.f2500c) && this.f2501d.h(bVar.f2501d);
    }

    @Override // com.bumptech.glide.r.c
    public boolean i() {
        boolean z;
        synchronized (this.f2498a) {
            d.a aVar = this.e;
            d.a aVar2 = d.a.SUCCESS;
            z = aVar == aVar2 || this.f == aVar2;
        }
        return z;
    }

    @Override // com.bumptech.glide.r.c
    public boolean isRunning() {
        boolean z;
        synchronized (this.f2498a) {
            d.a aVar = this.e;
            d.a aVar2 = d.a.RUNNING;
            z = aVar == aVar2 || this.f == aVar2;
        }
        return z;
    }

    @Override // com.bumptech.glide.r.c
    public boolean j() {
        boolean z;
        synchronized (this.f2498a) {
            d.a aVar = this.e;
            d.a aVar2 = d.a.CLEARED;
            z = aVar == aVar2 && this.f == aVar2;
        }
        return z;
    }

    @Override // com.bumptech.glide.r.d
    public boolean k(c cVar) {
        boolean z;
        synchronized (this.f2498a) {
            z = o() && m(cVar);
        }
        return z;
    }

    @Override // com.bumptech.glide.r.d
    public boolean l(c cVar) {
        boolean z;
        synchronized (this.f2498a) {
            z = n() && m(cVar);
        }
        return z;
    }

    public void q(c cVar, c cVar2) {
        this.f2500c = cVar;
        this.f2501d = cVar2;
    }
}

package com.bumptech.glide.r;

import com.bumptech.glide.r.d;

/* JADX INFO: loaded from: classes.dex */
public class i implements d, c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d f2512a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Object f2513b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile c f2514c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private volatile c f2515d;
    private d.a e;
    private d.a f;
    private boolean g;

    public i(Object obj, d dVar) {
        d.a aVar = d.a.CLEARED;
        this.e = aVar;
        this.f = aVar;
        this.f2513b = obj;
        this.f2512a = dVar;
    }

    private boolean m() {
        d dVar = this.f2512a;
        return dVar == null || dVar.l(this);
    }

    private boolean n() {
        d dVar = this.f2512a;
        return dVar == null || dVar.k(this);
    }

    private boolean o() {
        d dVar = this.f2512a;
        return dVar == null || dVar.a(this);
    }

    @Override // com.bumptech.glide.r.d
    public boolean a(c cVar) {
        boolean z;
        synchronized (this.f2513b) {
            z = o() && (cVar.equals(this.f2514c) || this.e != d.a.SUCCESS);
        }
        return z;
    }

    @Override // com.bumptech.glide.r.d, com.bumptech.glide.r.c
    public boolean b() {
        boolean z;
        synchronized (this.f2513b) {
            z = this.f2515d.b() || this.f2514c.b();
        }
        return z;
    }

    @Override // com.bumptech.glide.r.d
    public d c() {
        d dVarC;
        synchronized (this.f2513b) {
            d dVar = this.f2512a;
            dVarC = dVar != null ? dVar.c() : this;
        }
        return dVarC;
    }

    @Override // com.bumptech.glide.r.c
    public void clear() {
        synchronized (this.f2513b) {
            this.g = false;
            d.a aVar = d.a.CLEARED;
            this.e = aVar;
            this.f = aVar;
            this.f2515d.clear();
            this.f2514c.clear();
        }
    }

    @Override // com.bumptech.glide.r.d
    public void d(c cVar) {
        synchronized (this.f2513b) {
            if (!cVar.equals(this.f2514c)) {
                this.f = d.a.FAILED;
                return;
            }
            this.e = d.a.FAILED;
            d dVar = this.f2512a;
            if (dVar != null) {
                dVar.d(this);
            }
        }
    }

    @Override // com.bumptech.glide.r.c
    public void e() {
        synchronized (this.f2513b) {
            if (!this.f.a()) {
                this.f = d.a.PAUSED;
                this.f2515d.e();
            }
            if (!this.e.a()) {
                this.e = d.a.PAUSED;
                this.f2514c.e();
            }
        }
    }

    @Override // com.bumptech.glide.r.c
    public void f() {
        synchronized (this.f2513b) {
            this.g = true;
            try {
                if (this.e != d.a.SUCCESS) {
                    d.a aVar = this.f;
                    d.a aVar2 = d.a.RUNNING;
                    if (aVar != aVar2) {
                        this.f = aVar2;
                        this.f2515d.f();
                    }
                }
                if (this.g) {
                    d.a aVar3 = this.e;
                    d.a aVar4 = d.a.RUNNING;
                    if (aVar3 != aVar4) {
                        this.e = aVar4;
                        this.f2514c.f();
                    }
                }
            } finally {
                this.g = false;
            }
        }
    }

    @Override // com.bumptech.glide.r.d
    public void g(c cVar) {
        synchronized (this.f2513b) {
            if (cVar.equals(this.f2515d)) {
                this.f = d.a.SUCCESS;
                return;
            }
            this.e = d.a.SUCCESS;
            d dVar = this.f2512a;
            if (dVar != null) {
                dVar.g(this);
            }
            if (!this.f.a()) {
                this.f2515d.clear();
            }
        }
    }

    @Override // com.bumptech.glide.r.c
    public boolean h(c cVar) {
        if (!(cVar instanceof i)) {
            return false;
        }
        i iVar = (i) cVar;
        if (this.f2514c == null) {
            if (iVar.f2514c != null) {
                return false;
            }
        } else if (!this.f2514c.h(iVar.f2514c)) {
            return false;
        }
        if (this.f2515d == null) {
            if (iVar.f2515d != null) {
                return false;
            }
        } else if (!this.f2515d.h(iVar.f2515d)) {
            return false;
        }
        return true;
    }

    @Override // com.bumptech.glide.r.c
    public boolean i() {
        boolean z;
        synchronized (this.f2513b) {
            z = this.e == d.a.SUCCESS;
        }
        return z;
    }

    @Override // com.bumptech.glide.r.c
    public boolean isRunning() {
        boolean z;
        synchronized (this.f2513b) {
            z = this.e == d.a.RUNNING;
        }
        return z;
    }

    @Override // com.bumptech.glide.r.c
    public boolean j() {
        boolean z;
        synchronized (this.f2513b) {
            z = this.e == d.a.CLEARED;
        }
        return z;
    }

    @Override // com.bumptech.glide.r.d
    public boolean k(c cVar) {
        boolean z;
        synchronized (this.f2513b) {
            z = n() && cVar.equals(this.f2514c) && !b();
        }
        return z;
    }

    @Override // com.bumptech.glide.r.d
    public boolean l(c cVar) {
        boolean z;
        synchronized (this.f2513b) {
            z = m() && cVar.equals(this.f2514c) && this.e != d.a.PAUSED;
        }
        return z;
    }

    public void p(c cVar, c cVar2) {
        this.f2514c = cVar;
        this.f2515d = cVar2;
    }
}

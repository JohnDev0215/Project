package b.e.b.k.n;

/* JADX INFO: compiled from: GuidelineReference.java */
/* JADX INFO: loaded from: classes.dex */
class h extends m {
    public h(b.e.b.k.e eVar) {
        super(eVar);
        eVar.f2443f.f();
        eVar.f2444g.f();
        this.f2518f = ((b.e.b.k.h) eVar).L0();
    }

    private void q(f fVar) {
        this.f2520h.k.add(fVar);
        fVar.l.add(this.f2520h);
    }

    @Override // b.e.b.k.n.m, b.e.b.k.n.d
    public void a(d dVar) {
        f fVar = this.f2520h;
        if (fVar.f2488c && !fVar.j) {
            this.f2520h.d((int) ((fVar.l.get(0).f2492g * ((b.e.b.k.h) this.f2514b).O0()) + 0.5f));
        }
    }

    @Override // b.e.b.k.n.m
    void d() {
        b.e.b.k.h hVar = (b.e.b.k.h) this.f2514b;
        int iM0 = hVar.M0();
        int iN0 = hVar.N0();
        hVar.O0();
        if (hVar.L0() == 1) {
            if (iM0 != -1) {
                this.f2520h.l.add(this.f2514b.Q.f2443f.f2520h);
                this.f2514b.Q.f2443f.f2520h.k.add(this.f2520h);
                this.f2520h.f2491f = iM0;
            } else if (iN0 != -1) {
                this.f2520h.l.add(this.f2514b.Q.f2443f.f2521i);
                this.f2514b.Q.f2443f.f2521i.k.add(this.f2520h);
                this.f2520h.f2491f = -iN0;
            } else {
                f fVar = this.f2520h;
                fVar.f2487b = true;
                fVar.l.add(this.f2514b.Q.f2443f.f2521i);
                this.f2514b.Q.f2443f.f2521i.k.add(this.f2520h);
            }
            q(this.f2514b.f2443f.f2520h);
            q(this.f2514b.f2443f.f2521i);
            return;
        }
        if (iM0 != -1) {
            this.f2520h.l.add(this.f2514b.Q.f2444g.f2520h);
            this.f2514b.Q.f2444g.f2520h.k.add(this.f2520h);
            this.f2520h.f2491f = iM0;
        } else if (iN0 != -1) {
            this.f2520h.l.add(this.f2514b.Q.f2444g.f2521i);
            this.f2514b.Q.f2444g.f2521i.k.add(this.f2520h);
            this.f2520h.f2491f = -iN0;
        } else {
            f fVar2 = this.f2520h;
            fVar2.f2487b = true;
            fVar2.l.add(this.f2514b.Q.f2444g.f2521i);
            this.f2514b.Q.f2444g.f2521i.k.add(this.f2520h);
        }
        q(this.f2514b.f2444g.f2520h);
        q(this.f2514b.f2444g.f2521i);
    }

    @Override // b.e.b.k.n.m
    public void e() {
        if (((b.e.b.k.h) this.f2514b).L0() == 1) {
            this.f2514b.G0(this.f2520h.f2492g);
        } else {
            this.f2514b.H0(this.f2520h.f2492g);
        }
    }

    @Override // b.e.b.k.n.m
    void f() {
        this.f2520h.c();
    }

    @Override // b.e.b.k.n.m
    boolean m() {
        return false;
    }
}

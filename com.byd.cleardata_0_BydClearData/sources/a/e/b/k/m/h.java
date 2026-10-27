package a.e.b.k.m;

/* JADX INFO: loaded from: classes.dex */
class h extends m {
    public h(a.e.b.k.e eVar) {
        super(eVar);
        eVar.f.f();
        eVar.g.f();
        this.f = ((a.e.b.k.g) eVar).H0();
    }

    private void q(f fVar) {
        this.h.k.add(fVar);
        fVar.l.add(this.h);
    }

    @Override // a.e.b.k.m.m, a.e.b.k.m.d
    public void a(d dVar) {
        f fVar = this.h;
        if (fVar.f236c && !fVar.j) {
            this.h.d((int) ((fVar.l.get(0).g * ((a.e.b.k.g) this.f248b).K0()) + 0.5f));
        }
    }

    @Override // a.e.b.k.m.m
    void d() {
        f fVar;
        m mVar;
        f fVar2;
        a.e.b.k.g gVar = (a.e.b.k.g) this.f248b;
        int iI0 = gVar.I0();
        int iJ0 = gVar.J0();
        gVar.K0();
        if (gVar.H0() == 1) {
            f fVar3 = this.h;
            if (iI0 != -1) {
                fVar3.l.add(this.f248b.O.f.h);
                this.f248b.O.f.h.k.add(this.h);
                fVar2 = this.h;
            } else if (iJ0 != -1) {
                fVar3.l.add(this.f248b.O.f.i);
                this.f248b.O.f.i.k.add(this.h);
                fVar2 = this.h;
                iI0 = -iJ0;
            } else {
                fVar3.f235b = true;
                fVar3.l.add(this.f248b.O.f.i);
                this.f248b.O.f.i.k.add(this.h);
                q(this.f248b.f.h);
                mVar = this.f248b.f;
            }
            fVar2.f = iI0;
            q(this.f248b.f.h);
            mVar = this.f248b.f;
        } else {
            f fVar4 = this.h;
            if (iI0 != -1) {
                fVar4.l.add(this.f248b.O.g.h);
                this.f248b.O.g.h.k.add(this.h);
                fVar = this.h;
            } else if (iJ0 != -1) {
                fVar4.l.add(this.f248b.O.g.i);
                this.f248b.O.g.i.k.add(this.h);
                fVar = this.h;
                iI0 = -iJ0;
            } else {
                fVar4.f235b = true;
                fVar4.l.add(this.f248b.O.g.i);
                this.f248b.O.g.i.k.add(this.h);
                q(this.f248b.g.h);
                mVar = this.f248b.g;
            }
            fVar.f = iI0;
            q(this.f248b.g.h);
            mVar = this.f248b.g;
        }
        q(mVar.i);
    }

    @Override // a.e.b.k.m.m
    public void e() {
        if (((a.e.b.k.g) this.f248b).H0() == 1) {
            this.f248b.C0(this.h.g);
        } else {
            this.f248b.D0(this.h.g);
        }
    }

    @Override // a.e.b.k.m.m
    void f() {
        this.h.c();
    }

    @Override // a.e.b.k.m.m
    boolean m() {
        return false;
    }
}

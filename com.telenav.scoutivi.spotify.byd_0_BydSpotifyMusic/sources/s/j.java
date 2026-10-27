package s;

/* JADX INFO: compiled from: GuidelineReference.java */
/* JADX INFO: loaded from: classes.dex */
class j extends p {
    public j(r.e eVar) {
        super(eVar);
        eVar.f9212e.f();
        eVar.f9214f.f();
        this.f9457f = ((r.h) eVar).v1();
    }

    private void q(f fVar) {
        this.f9459h.f9417k.add(fVar);
        fVar.f9418l.add(this.f9459h);
    }

    @Override // s.p, s.d
    public void a(d dVar) {
        f fVar = this.f9459h;
        if (fVar.f9409c && !fVar.f9416j) {
            this.f9459h.d((int) ((fVar.f9418l.get(0).f9413g * ((r.h) this.f9453b).y1()) + 0.5f));
        }
    }

    @Override // s.p
    void d() {
        r.h hVar = (r.h) this.f9453b;
        int iW1 = hVar.w1();
        int iX1 = hVar.x1();
        hVar.y1();
        if (hVar.v1() == 1) {
            if (iW1 != -1) {
                this.f9459h.f9418l.add(this.f9453b.f9209c0.f9212e.f9459h);
                this.f9453b.f9209c0.f9212e.f9459h.f9417k.add(this.f9459h);
                this.f9459h.f9412f = iW1;
            } else if (iX1 != -1) {
                this.f9459h.f9418l.add(this.f9453b.f9209c0.f9212e.f9460i);
                this.f9453b.f9209c0.f9212e.f9460i.f9417k.add(this.f9459h);
                this.f9459h.f9412f = -iX1;
            } else {
                f fVar = this.f9459h;
                fVar.f9408b = true;
                fVar.f9418l.add(this.f9453b.f9209c0.f9212e.f9460i);
                this.f9453b.f9209c0.f9212e.f9460i.f9417k.add(this.f9459h);
            }
            q(this.f9453b.f9212e.f9459h);
            q(this.f9453b.f9212e.f9460i);
            return;
        }
        if (iW1 != -1) {
            this.f9459h.f9418l.add(this.f9453b.f9209c0.f9214f.f9459h);
            this.f9453b.f9209c0.f9214f.f9459h.f9417k.add(this.f9459h);
            this.f9459h.f9412f = iW1;
        } else if (iX1 != -1) {
            this.f9459h.f9418l.add(this.f9453b.f9209c0.f9214f.f9460i);
            this.f9453b.f9209c0.f9214f.f9460i.f9417k.add(this.f9459h);
            this.f9459h.f9412f = -iX1;
        } else {
            f fVar2 = this.f9459h;
            fVar2.f9408b = true;
            fVar2.f9418l.add(this.f9453b.f9209c0.f9214f.f9460i);
            this.f9453b.f9209c0.f9214f.f9460i.f9417k.add(this.f9459h);
        }
        q(this.f9453b.f9214f.f9459h);
        q(this.f9453b.f9214f.f9460i);
    }

    @Override // s.p
    public void e() {
        if (((r.h) this.f9453b).v1() == 1) {
            this.f9453b.p1(this.f9459h.f9413g);
        } else {
            this.f9453b.q1(this.f9459h.f9413g);
        }
    }

    @Override // s.p
    void f() {
        this.f9459h.c();
    }

    @Override // s.p
    boolean m() {
        return false;
    }
}

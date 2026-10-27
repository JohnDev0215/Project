package a.f.a.i.l;

/* JADX INFO: loaded from: classes.dex */
public class i extends o {
    public i(a.f.a.i.d dVar) {
        super(dVar);
        dVar.f558d.f();
        dVar.f559e.f();
        this.f611f = ((a.f.a.i.f) dVar).z0;
    }

    @Override // a.f.a.i.l.o, a.f.a.i.l.d
    public void a(d dVar) {
        f fVar = this.h;
        if (fVar.f584c && !fVar.j) {
            this.h.c((int) ((fVar.l.get(0).g * ((a.f.a.i.f) this.f607b).v0) + 0.5f));
        }
    }

    @Override // a.f.a.i.l.o
    public void d() {
        o oVar;
        a.f.a.i.d dVar = this.f607b;
        a.f.a.i.f fVar = (a.f.a.i.f) dVar;
        int i = fVar.w0;
        int i2 = fVar.x0;
        if (fVar.z0 == 1) {
            f fVar2 = this.h;
            if (i != -1) {
                fVar2.l.add(dVar.X.f558d.h);
                this.f607b.X.f558d.h.k.add(this.h);
                this.h.f587f = i;
            } else if (i2 != -1) {
                fVar2.l.add(dVar.X.f558d.i);
                this.f607b.X.f558d.i.k.add(this.h);
                this.h.f587f = -i2;
            } else {
                fVar2.f583b = true;
                fVar2.l.add(dVar.X.f558d.i);
                this.f607b.X.f558d.i.k.add(this.h);
            }
            m(this.f607b.f558d.h);
            oVar = this.f607b.f558d;
        } else {
            f fVar3 = this.h;
            if (i != -1) {
                fVar3.l.add(dVar.X.f559e.h);
                this.f607b.X.f559e.h.k.add(this.h);
                this.h.f587f = i;
            } else if (i2 != -1) {
                fVar3.l.add(dVar.X.f559e.i);
                this.f607b.X.f559e.i.k.add(this.h);
                this.h.f587f = -i2;
            } else {
                fVar3.f583b = true;
                fVar3.l.add(dVar.X.f559e.i);
                this.f607b.X.f559e.i.k.add(this.h);
            }
            m(this.f607b.f559e.h);
            oVar = this.f607b.f559e;
        }
        m(oVar.i);
    }

    @Override // a.f.a.i.l.o
    public void e() {
        a.f.a.i.d dVar = this.f607b;
        int i = ((a.f.a.i.f) dVar).z0;
        int i2 = this.h.g;
        if (i == 1) {
            dVar.c0 = i2;
        } else {
            dVar.d0 = i2;
        }
    }

    @Override // a.f.a.i.l.o
    public void f() {
        this.h.b();
    }

    @Override // a.f.a.i.l.o
    public boolean k() {
        return false;
    }

    public final void m(f fVar) {
        this.h.k.add(fVar);
        fVar.l.add(this.h);
    }
}

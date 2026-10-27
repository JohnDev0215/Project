package p;

/* JADX INFO: loaded from: classes.dex */
public final class e11 extends androidx.constraintlayout.core.widgets.analyzer.f {
    @Override // p.e90
    public final void a(e90 e90Var) {
        androidx.constraintlayout.core.widgets.analyzer.a aVar = this.h;
        if (aVar.c && !aVar.j) {
            aVar.d((int) ((((androidx.constraintlayout.core.widgets.analyzer.a) aVar.l.get(0)).g * ((d11) this.b).p0) + 0.5f));
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.f
    public final void d() {
        ty tyVar = this.b;
        d11 d11Var = (d11) tyVar;
        int i = d11Var.q0;
        int i2 = d11Var.r0;
        int i3 = d11Var.t0;
        androidx.constraintlayout.core.widgets.analyzer.a aVar = this.h;
        if (i3 == 1) {
            if (i != -1) {
                aVar.l.add(tyVar.T.d.h);
                this.b.T.d.h.k.add(aVar);
                aVar.f = i;
            } else if (i2 != -1) {
                aVar.l.add(tyVar.T.d.i);
                this.b.T.d.i.k.add(aVar);
                aVar.f = -i2;
            } else {
                aVar.b = true;
                aVar.l.add(tyVar.T.d.i);
                this.b.T.d.i.k.add(aVar);
            }
            m(this.b.d.h);
            m(this.b.d.i);
            return;
        }
        if (i != -1) {
            aVar.l.add(tyVar.T.e.h);
            this.b.T.e.h.k.add(aVar);
            aVar.f = i;
        } else if (i2 != -1) {
            aVar.l.add(tyVar.T.e.i);
            this.b.T.e.i.k.add(aVar);
            aVar.f = -i2;
        } else {
            aVar.b = true;
            aVar.l.add(tyVar.T.e.i);
            this.b.T.e.i.k.add(aVar);
        }
        m(this.b.e.h);
        m(this.b.e.i);
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.f
    public final void e() {
        ty tyVar = this.b;
        int i = ((d11) tyVar).t0;
        androidx.constraintlayout.core.widgets.analyzer.a aVar = this.h;
        if (i == 1) {
            tyVar.Y = aVar.g;
        } else {
            tyVar.Z = aVar.g;
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.f
    public final void f() {
        this.h.c();
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.f
    public final boolean k() {
        return false;
    }

    public final void m(androidx.constraintlayout.core.widgets.analyzer.a aVar) {
        androidx.constraintlayout.core.widgets.analyzer.a aVar2 = this.h;
        aVar2.k.add(aVar);
        aVar.l.add(aVar2);
    }
}

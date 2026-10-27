package defpackage;

/* JADX INFO: compiled from: GuidelineReference.java */
/* JADX INFO: loaded from: classes.dex */
public final class st0 extends gt3 {
    public st0(qx qxVar) {
        super(qxVar);
        qxVar.d.f();
        qxVar.e.f();
        this.f = ((rt0) qxVar).A0;
    }

    @Override // defpackage.gt3, defpackage.m80
    public final void a(m80 m80Var) {
        o80 o80Var = this.h;
        if (o80Var.c && !o80Var.j) {
            o80Var.d((int) ((((o80) o80Var.l.get(0)).g * ((rt0) this.b).w0) + 0.5f));
        }
    }

    @Override // defpackage.gt3
    public final void d() {
        qx qxVar = this.b;
        rt0 rt0Var = (rt0) qxVar;
        int i = rt0Var.x0;
        int i2 = rt0Var.y0;
        int i3 = rt0Var.A0;
        o80 o80Var = this.h;
        if (i3 == 1) {
            if (i != -1) {
                o80Var.l.add(qxVar.W.d.h);
                this.b.W.d.h.k.add(o80Var);
                o80Var.f = i;
            } else if (i2 != -1) {
                o80Var.l.add(qxVar.W.d.i);
                this.b.W.d.i.k.add(o80Var);
                o80Var.f = -i2;
            } else {
                o80Var.b = true;
                o80Var.l.add(qxVar.W.d.i);
                this.b.W.d.i.k.add(o80Var);
            }
            m(this.b.d.h);
            m(this.b.d.i);
            return;
        }
        if (i != -1) {
            o80Var.l.add(qxVar.W.e.h);
            this.b.W.e.h.k.add(o80Var);
            o80Var.f = i;
        } else if (i2 != -1) {
            o80Var.l.add(qxVar.W.e.i);
            this.b.W.e.i.k.add(o80Var);
            o80Var.f = -i2;
        } else {
            o80Var.b = true;
            o80Var.l.add(qxVar.W.e.i);
            this.b.W.e.i.k.add(o80Var);
        }
        m(this.b.e.h);
        m(this.b.e.i);
    }

    @Override // defpackage.gt3
    public final void e() {
        qx qxVar = this.b;
        int i = ((rt0) qxVar).A0;
        o80 o80Var = this.h;
        if (i == 1) {
            qxVar.b0 = o80Var.g;
        } else {
            qxVar.c0 = o80Var.g;
        }
    }

    @Override // defpackage.gt3
    public final void f() {
        this.h.c();
    }

    @Override // defpackage.gt3
    public final boolean k() {
        return false;
    }

    public final void m(o80 o80Var) {
        o80 o80Var2 = this.h;
        o80Var2.k.add(o80Var);
        o80Var.l.add(o80Var2);
    }
}

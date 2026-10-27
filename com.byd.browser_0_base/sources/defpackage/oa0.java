package defpackage;

import defpackage.ag3;
import defpackage.h20;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: DisclosureController.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class oa0 implements hk1, ag3.a, kr2 {
    public final ag3 e;
    public final h20 f;
    public boolean g;

    public oa0(ag3 ag3Var, q2 q2Var, h20 h20Var, String str) {
        this.e = ag3Var;
        this.f = h20Var;
        ag3Var.p(ag3.f, this);
        ag3Var.p(ag3.g, str);
        h20Var.i.a(new wq0(18, this));
        q2Var.f(this);
    }

    @Override // defpackage.hk1
    public final void G0() {
        this.g = d();
        h20.b bVar = this.f.h;
        if ((bVar == null || bVar.c == 2) ? false : true) {
            e();
        }
    }

    public abstract void a();

    @Override // ag3.a
    public void b() {
        this.e.l(ag3.e, false);
    }

    public abstract boolean c();

    public abstract boolean d();

    public final void e() {
        PropertyModel.p pVar = ag3.c;
        ag3 ag3Var = this.e;
        if ((ag3Var.h(pVar) == 1) || !d()) {
            return;
        }
        ag3Var.l(ag3.e, c());
        ag3Var.n(pVar, 1);
    }

    @Override // defpackage.kr2
    public final void l() {
        if (!this.g || d()) {
            return;
        }
        a();
        this.g = false;
    }

    @Override // defpackage.kr2
    public final void j() {
    }
}

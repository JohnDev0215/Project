package defpackage;

import android.view.Window;

/* JADX INFO: compiled from: IncognitoTabbedSnapshotController.java */
/* JADX INFO: loaded from: classes.dex */
public final class kz0 extends az0 implements e23, v80 {
    public final y13 g;
    public final c61 h;
    public final rm0 i;
    public final q2 j;
    public final nw2<Boolean> k;

    public kz0(Window window, c61 c61Var, a23 a23Var, r2 r2Var, p11 p11Var, iz0 iz0Var) {
        super(window, iz0Var);
        this.h = c61Var;
        this.g = a23Var;
        this.j = r2Var;
        this.k = p11Var;
        rm0 rm0Var = new rm0(new jz0(this));
        this.i = rm0Var;
        c61Var.j(rm0Var);
        a23Var.m(this);
        r2Var.f(this);
    }

    @Override // defpackage.e23
    public final void b() {
        a();
    }

    @Override // defpackage.v80
    public final void onDestroy() {
        this.h.f(this.i);
        this.g.l(this);
        this.j.e(this);
    }
}

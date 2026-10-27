package defpackage;

import defpackage.kj;

/* JADX INFO: compiled from: StartSurfaceMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class vr2 implements kj.a {
    public final /* synthetic */ yr2 e;

    public vr2(yr2 yr2Var) {
        this.e = yr2Var;
    }

    @Override // kj.a
    public final void c(boolean z, int i, int i2, int i3) {
        yr2 yr2Var = this.e;
        boolean zH = yr2Var.H();
        kj kjVar = yr2Var.I;
        if (zH) {
            yr2Var.w0(kjVar.r());
        } else if (yr2Var.C == 2) {
            yr2Var.w0(kjVar.f());
        } else {
            yr2Var.w0(0);
        }
    }

    @Override // kj.a
    public final void l(int i) {
        yr2 yr2Var = this.e;
        if (yr2Var.H()) {
            yr2Var.T(i);
        } else {
            yr2Var.T(0);
        }
    }
}

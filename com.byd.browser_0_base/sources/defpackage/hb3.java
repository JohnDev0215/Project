package defpackage;

import defpackage.l61;

/* JADX INFO: compiled from: ToggleTabStackButtonCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class hb3 implements l61.a {
    public boolean e;
    public final /* synthetic */ ib3 f;

    public hb3(ib3 ib3Var) {
        this.f = ib3Var;
    }

    @Override // l61.a
    public final void g(int i, boolean z) {
        if (i == 2) {
            ib3 ib3Var = this.f;
            if (ib3Var.k) {
                ib3Var.g.onResult(Boolean.TRUE);
                this.e = true;
            }
        }
    }

    @Override // l61.a
    public final void i(int i, boolean z, boolean z2) {
        if (i == 2 && this.e) {
            this.f.g.onResult(Boolean.FALSE);
            this.e = false;
        }
    }
}

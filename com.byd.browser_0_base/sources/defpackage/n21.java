package defpackage;

/* JADX INFO: compiled from: InvalidPaymentRequest.java */
/* JADX INFO: loaded from: classes3.dex */
public final class n21 implements oz1 {
    public pz1 e;

    @Override // defpackage.oz1
    public final void c(boolean z) {
        pz1 pz1Var = this.e;
        if (pz1Var != null) {
            pz1Var.onError(1, "Web Payments API is disabled.");
            this.e.close();
        }
    }

    @Override // defpackage.oz1
    public final void h0() {
        pz1 pz1Var = this.e;
        if (pz1Var != null) {
            pz1Var.i(1);
        }
    }

    @Override // defpackage.oz1
    public final void t0(pz1 pz1Var, lz1[] lz1VarArr, sy1 sy1Var, nz1 nz1Var) {
        this.e = pz1Var;
    }

    @Override // defpackage.oz1
    public final void w() {
        pz1 pz1Var = this.e;
        if (pz1Var != null) {
            pz1Var.V(1);
        }
    }

    @Override // defpackage.oz1
    public final void M() {
    }

    @Override // defpackage.y11, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // defpackage.oz1
    public final void y() {
    }

    @Override // defpackage.oz1
    public final void L0(int i) {
    }

    @Override // defpackage.oz1
    public final void j0(xz1 xz1Var) {
    }

    @Override // defpackage.fx
    public final void q(ki1 ki1Var) {
    }

    @Override // defpackage.oz1
    public final void z(sy1 sy1Var) {
    }
}

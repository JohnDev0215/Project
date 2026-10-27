package defpackage;

/* JADX INFO: compiled from: ChromeNextTabPolicySupplier.java */
/* JADX INFO: loaded from: classes.dex */
public final class pq implements zm1 {
    public l61 e;

    public pq(rs1 rs1Var) {
        rs1Var.h(new c3(2, this));
    }

    @Override // defpackage.nw2
    public final Integer get() {
        l61 l61Var = this.e;
        return (l61Var == null || !(l61Var.q(2) || this.e.q(16))) ? 0 : 1;
    }
}

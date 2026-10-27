package defpackage;

/* JADX INFO: compiled from: TextBubbleBackPressHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public final class v73 implements vb {
    public final aq1<Boolean> e = new aq1<>();
    public final se3 f;

    public v73() {
        se3 se3Var = new se3(15, this);
        this.f = se3Var;
        u73.p.p(se3Var);
    }

    @Override // defpackage.vb
    public final zp1<Boolean> getHandleBackPressChangedSupplier() {
        return this.e;
    }

    @Override // defpackage.vb
    public final int o() {
        aq1<Integer> aq1Var = u73.p;
        boolean z = aq1Var.f.intValue() > 0;
        u73.c();
        return (z && aq1Var.f.intValue() == 0) ? 0 : 1;
    }
}

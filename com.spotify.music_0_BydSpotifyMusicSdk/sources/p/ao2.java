package p;

/* JADX INFO: loaded from: classes.dex */
public final class ao2 extends xn2 {
    public bo2 a;

    @Override // p.vn2
    public final void a(wn2 wn2Var) {
        bo2 bo2Var = this.a;
        int i = bo2Var.H - 1;
        bo2Var.H = i;
        if (i == 0) {
            bo2Var.I = false;
            bo2Var.p();
        }
        wn2Var.z(this);
    }

    @Override // p.xn2, p.vn2
    public final void b() {
        bo2 bo2Var = this.a;
        if (bo2Var.I) {
            return;
        }
        bo2Var.L();
        bo2Var.I = true;
    }
}

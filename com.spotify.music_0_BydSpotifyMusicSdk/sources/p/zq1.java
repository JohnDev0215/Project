package p;

/* JADX INFO: loaded from: classes.dex */
public final class zq1 implements yy1, j80 {
    public static final iy2 c = new iy2(17);
    public static final fw d = new fw(1);
    public i80 a;
    public volatile yy1 b;

    public zq1(iy2 iy2Var, yy1 yy1Var) {
        this.a = iy2Var;
        this.b = yy1Var;
    }

    public final void a(i80 i80Var) {
        yy1 yy1Var;
        yy1 yy1Var2;
        yy1 yy1Var3 = this.b;
        fw fwVar = d;
        if (yy1Var3 != fwVar) {
            i80Var.e(yy1Var3);
            return;
        }
        synchronized (this) {
            yy1Var = this.b;
            if (yy1Var != fwVar) {
                yy1Var2 = yy1Var;
            } else {
                this.a = new rm(this.a, 3, i80Var);
                yy1Var2 = null;
            }
        }
        if (yy1Var2 != null) {
            i80Var.e(yy1Var);
        }
    }

    @Override // p.yy1
    public final Object get() {
        return this.b.get();
    }
}

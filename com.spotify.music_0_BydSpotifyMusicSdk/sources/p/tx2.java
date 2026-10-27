package p;

/* JADX INFO: loaded from: classes.dex */
public final class tx2 {
    public final qx1 a;
    public final yx2 b;

    public tx2(qx1 qx1Var, yx2 yx2Var) {
        v03.j(qx1Var, "processor");
        v03.j(yx2Var, "workTaskExecutor");
        this.a = qx1Var;
        this.b = yx2Var;
    }

    public final void a(xf2 xf2Var, int i) {
        v03.j(xf2Var, "workSpecId");
        this.b.a(new bg2(this.a, xf2Var, false, i));
    }
}

package p;

/* JADX INFO: loaded from: classes.dex */
public final class o41 implements ku2 {
    public final ju2[] c;

    public o41(ju2... ju2VarArr) {
        v03.j(ju2VarArr, "initializers");
        this.c = ju2VarArr;
    }

    @Override // p.ku2
    public final iu2 b(Class cls, yl1 yl1Var) {
        iu2 iu2Var = null;
        for (ju2 ju2Var : this.c) {
            if (v03.b(ju2Var.a, cls)) {
                Object objInvoke = ju2Var.b.invoke(yl1Var);
                iu2Var = objInvoke instanceof iu2 ? (iu2) objInvoke : null;
            }
        }
        if (iu2Var != null) {
            return iu2Var;
        }
        throw new IllegalArgumentException("No initializer set for given class ".concat(cls.getName()));
    }
}

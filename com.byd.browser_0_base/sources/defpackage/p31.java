package defpackage;

/* JADX INFO: compiled from: JobSupport.kt */
/* JADX INFO: loaded from: classes.dex */
public final class p31 extends r31 {
    public final boolean f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p31(m31 m31Var) {
        super(true);
        boolean z = true;
        r(m31Var);
        hn hnVarM = m();
        in inVar = hnVarM instanceof in ? (in) hnVarM : null;
        r31 r31VarM = inVar == null ? null : inVar.m();
        if (r31VarM == null) {
            z = false;
            break;
        }
        while (!r31VarM.k()) {
            hn hnVarM2 = r31VarM.m();
            in inVar2 = hnVarM2 instanceof in ? (in) hnVarM2 : null;
            r31VarM = inVar2 == null ? null : inVar2.m();
            if (r31VarM == null) {
                z = false;
                break;
            }
        }
        this.f = z;
    }

    @Override // defpackage.r31
    public final boolean k() {
        return this.f;
    }
}

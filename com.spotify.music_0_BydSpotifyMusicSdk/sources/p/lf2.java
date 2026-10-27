package p;

/* JADX INFO: loaded from: classes.dex */
public final class lf2 implements c00, f20 {
    public final c00 c;
    public final y10 e;

    public lf2(c00 c00Var, y10 y10Var) {
        this.c = c00Var;
        this.e = y10Var;
    }

    @Override // p.f20
    public final f20 f() {
        c00 c00Var = this.c;
        if (c00Var instanceof f20) {
            return (f20) c00Var;
        }
        return null;
    }

    @Override // p.c00
    public final y10 getContext() {
        return this.e;
    }

    @Override // p.c00
    public final void k(Object obj) {
        this.c.k(obj);
    }
}

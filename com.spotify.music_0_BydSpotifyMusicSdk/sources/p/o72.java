package p;

/* JADX INFO: loaded from: classes.dex */
public class o72 extends a1 implements f20 {
    public final c00 l;

    public o72(c00 c00Var, y10 y10Var) {
        super(y10Var, true, true);
        this.l = c00Var;
    }

    @Override // kotlinx.coroutines.g
    public final boolean N() {
        return true;
    }

    @Override // p.f20
    public final f20 f() {
        c00 c00Var = this.l;
        if (c00Var instanceof f20) {
            return (f20) c00Var;
        }
        return null;
    }

    @Override // kotlinx.coroutines.g
    public void r(Object obj) {
        fa0.a(vr2.k(this.l), he1.L(obj), null);
    }

    @Override // kotlinx.coroutines.g
    public void s(Object obj) {
        this.l.k(he1.L(obj));
    }
}

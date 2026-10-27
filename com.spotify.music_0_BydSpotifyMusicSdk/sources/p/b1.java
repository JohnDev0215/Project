package p;

/* JADX INFO: loaded from: classes.dex */
public abstract class b1 implements w10 {
    public final x10 c;

    public b1(x10 x10Var) {
        this.c = x10Var;
    }

    @Override // p.y10
    public y10 E(x10 x10Var) {
        return ux2.q(this, x10Var);
    }

    @Override // p.y10
    public w10 E0(x10 x10Var) {
        return ux2.k(this, x10Var);
    }

    @Override // p.y10
    public final Object c(Object obj, fz0 fz0Var) {
        v03.j(fz0Var, "operation");
        return fz0Var.g(obj, this);
    }

    @Override // p.w10
    public final x10 getKey() {
        return this.c;
    }

    @Override // p.y10
    public final y10 o0(y10 y10Var) {
        v03.j(y10Var, "context");
        return kotlin.coroutines.a.a(this, y10Var);
    }
}

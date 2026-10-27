package p;

/* JADX INFO: loaded from: classes.dex */
public final class z81 extends uf2 {
    public final c00 l;

    public z81(y10 y10Var, fz0 fz0Var) {
        super(y10Var, true, false);
        this.l = vr2.c(this, this, fz0Var);
    }

    @Override // kotlinx.coroutines.g
    public final void T() {
        try {
            fa0.a(vr2.k(this.l), lq2.a, null);
        } catch (Throwable th) {
            k(kotlin.a.b(th));
            throw th;
        }
    }
}

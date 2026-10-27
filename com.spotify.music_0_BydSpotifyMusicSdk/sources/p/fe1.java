package p;

/* JADX INFO: loaded from: classes.dex */
public final class fe1 implements uj1 {
    public uj1[] a;

    @Override // p.uj1
    public final tj1 a(Class cls) {
        for (uj1 uj1Var : this.a) {
            if (uj1Var.b(cls)) {
                return uj1Var.a(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(cls.getName()));
    }

    @Override // p.uj1
    public final boolean b(Class cls) {
        for (uj1 uj1Var : this.a) {
            if (uj1Var.b(cls)) {
                return true;
            }
        }
        return false;
    }
}

package x;

import T.C0735d;
import T.C0738e0;

/* JADX INFO: loaded from: classes.dex */
public final class P extends A9.b {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C0738e0 f23443n;
    public final C0738e0 o;

    public P(Object obj) {
        super(9);
        T.S s6 = T.S.f8224q;
        this.f23443n = C0735d.O(obj, s6);
        this.o = C0735d.O(obj, s6);
    }

    @Override // A9.b
    public final Object P0() {
        return this.f23443n.getValue();
    }

    @Override // A9.b
    public final Object Q0() {
        return this.o.getValue();
    }

    @Override // A9.b
    public final void W0(Object obj) {
        this.f23443n.setValue(obj);
    }

    @Override // A9.b
    public final void X0(x0 x0Var) {
    }

    @Override // A9.b
    public final void Y0() {
    }
}

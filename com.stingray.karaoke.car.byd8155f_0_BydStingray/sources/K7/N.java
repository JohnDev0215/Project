package K7;

import H7.InterfaceC0322g;

/* JADX INFO: loaded from: classes.dex */
public final class N extends f0 implements H7.l {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final Object f3739u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public N(E e10, T7.H h10) {
        super(e10, h10);
        A7.m.f(e10, "container");
        A7.m.f(h10, "descriptor");
        this.f3739u = h6.r.n(m7.g.l, new K(this, 1));
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, m7.f] */
    @Override // H7.l
    public final InterfaceC0322g d() {
        return (M) this.f3739u.getValue();
    }
}

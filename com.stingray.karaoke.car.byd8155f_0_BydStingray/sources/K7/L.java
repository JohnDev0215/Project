package K7;

import H7.InterfaceC0322g;

/* JADX INFO: loaded from: classes.dex */
public final class L extends c0 implements H7.k {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final Object f3737u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public L(E e10, String str, String str2, Object obj) {
        super(e10, str, str2, obj);
        A7.m.f(e10, "container");
        A7.m.f(str, "name");
        A7.m.f(str2, "signature");
        this.f3737u = h6.r.n(m7.g.l, new K(this, 0));
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, m7.f] */
    @Override // H7.l
    public final InterfaceC0322g d() {
        return (J) this.f3737u.getValue();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, m7.f] */
    @Override // H7.k, H7.l
    public final H7.j d() {
        return (J) this.f3737u.getValue();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public L(E e10, T7.H h10) {
        super(e10, h10);
        A7.m.f(e10, "container");
        A7.m.f(h10, "descriptor");
        this.f3737u = h6.r.n(m7.g.l, new K(this, 0));
    }
}

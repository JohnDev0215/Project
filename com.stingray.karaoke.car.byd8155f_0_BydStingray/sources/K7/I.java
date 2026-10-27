package K7;

import A.I0;
import H7.InterfaceC0322g;
import H7.InterfaceC0323h;
import H7.InterfaceC0324i;

/* JADX INFO: loaded from: classes.dex */
public final class I extends Z implements InterfaceC0324i {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final Object f3733u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public I(E e10, T7.H h10) {
        super(e10, h10);
        A7.m.f(e10, "container");
        A7.m.f(h10, "descriptor");
        this.f3733u = h6.r.n(m7.g.l, new I0(this, 29));
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, m7.f] */
    @Override // H7.l
    public final InterfaceC0322g d() {
        return (H) this.f3733u.getValue();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, m7.f] */
    @Override // H7.InterfaceC0324i, H7.l
    public final InterfaceC0323h d() {
        return (H) this.f3733u.getValue();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public I(E e10, String str, String str2, Object obj) {
        super(e10, str, str2, obj);
        A7.m.f(e10, "container");
        A7.m.f(str, "name");
        A7.m.f(str2, "signature");
        this.f3733u = h6.r.n(m7.g.l, new I0(this, 29));
    }
}

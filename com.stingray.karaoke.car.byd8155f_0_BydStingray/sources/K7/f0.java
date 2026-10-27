package K7;

import A7.C0055c;

/* JADX INFO: loaded from: classes.dex */
public class f0 extends m0 implements H7.v {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Object f3786t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f0(E e10, String str, String str2) {
        super(e10, str, str2, C0055c.l);
        A7.m.f(e10, "container");
        A7.m.f(str, "name");
        A7.m.f(str2, "signature");
        m7.g gVar = m7.g.l;
        this.f3786t = h6.r.n(gVar, new e0(this, 0));
        h6.r.n(gVar, new e0(this, 1));
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, m7.f] */
    @Override // H7.w
    public final H7.p c() {
        return (d0) this.f3786t.getValue();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, m7.f] */
    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((d0) this.f3786t.getValue()).h(obj, obj2);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, m7.f] */
    @Override // K7.m0
    public final i0 u() {
        return (d0) this.f3786t.getValue();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, m7.f] */
    @Override // H7.w
    public final H7.u c() {
        return (d0) this.f3786t.getValue();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f0(E e10, T7.H h10) {
        super(e10, h10);
        A7.m.f(e10, "container");
        A7.m.f(h10, "descriptor");
        m7.g gVar = m7.g.l;
        this.f3786t = h6.r.n(gVar, new e0(this, 0));
        h6.r.n(gVar, new e0(this, 1));
    }
}

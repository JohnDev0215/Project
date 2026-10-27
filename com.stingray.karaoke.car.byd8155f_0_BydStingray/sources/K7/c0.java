package K7;

/* JADX INFO: loaded from: classes.dex */
public class c0 extends m0 implements H7.t {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Object f3780t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c0(E e10, String str, String str2, Object obj) {
        super(e10, str, str2, obj);
        A7.m.f(e10, "container");
        A7.m.f(str, "name");
        A7.m.f(str2, "signature");
        m7.g gVar = m7.g.l;
        this.f3780t = h6.r.n(gVar, new C0378b0(this, 0));
        h6.r.n(gVar, new C0378b0(this, 1));
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        return get(obj);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, m7.f] */
    @Override // H7.w
    public final H7.p c() {
        return (C0376a0) this.f3780t.getValue();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, m7.f] */
    @Override // H7.t
    public final Object get(Object obj) {
        return ((C0376a0) this.f3780t.getValue()).h(obj);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, m7.f] */
    @Override // K7.m0
    public final i0 u() {
        return (C0376a0) this.f3780t.getValue();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, m7.f] */
    @Override // H7.w
    public final H7.s c() {
        return (C0376a0) this.f3780t.getValue();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c0(E e10, T7.H h10) {
        super(e10, h10);
        A7.m.f(e10, "container");
        A7.m.f(h10, "descriptor");
        m7.g gVar = m7.g.l;
        this.f3780t = h6.r.n(gVar, new C0378b0(this, 0));
        h6.r.n(gVar, new C0378b0(this, 1));
    }
}

package K7;

/* JADX INFO: loaded from: classes.dex */
public class Z extends m0 implements H7.r {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Object f3760t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Z(E e10, T7.H h10) {
        super(e10, h10);
        A7.m.f(e10, "container");
        A7.m.f(h10, "descriptor");
        m7.g gVar = m7.g.l;
        this.f3760t = h6.r.n(gVar, new Y(this, 0));
        h6.r.n(gVar, new Y(this, 1));
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        return get();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, m7.f] */
    @Override // H7.w
    public final H7.p c() {
        return (X) this.f3760t.getValue();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object, m7.f] */
    @Override // H7.r
    public final Object get() {
        return ((X) this.f3760t.getValue()).h(new Object[0]);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, m7.f] */
    @Override // K7.m0
    public final i0 u() {
        return (X) this.f3760t.getValue();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, m7.f] */
    @Override // H7.w
    public final H7.q c() {
        return (X) this.f3760t.getValue();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Z(E e10, String str, String str2, Object obj) {
        super(e10, str, str2, obj);
        A7.m.f(e10, "container");
        A7.m.f(str, "name");
        A7.m.f(str2, "signature");
        m7.g gVar = m7.g.l;
        this.f3760t = h6.r.n(gVar, new Y(this, 0));
        h6.r.n(gVar, new Y(this, 1));
    }
}

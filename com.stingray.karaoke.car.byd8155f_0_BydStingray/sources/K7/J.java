package K7;

/* JADX INFO: loaded from: classes.dex */
public final class J extends k0 implements H7.j {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final L f3734p;

    public J(L l) {
        A7.m.f(l, "property");
        this.f3734p = l;
    }

    @Override // H7.o
    public final H7.w f() {
        return this.f3734p;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, m7.f] */
    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws I7.a {
        ((J) this.f3734p.f3737u.getValue()).h(obj, obj2);
        return m7.q.f19267a;
    }

    @Override // K7.g0
    public final m0 t() {
        return this.f3734p;
    }
}

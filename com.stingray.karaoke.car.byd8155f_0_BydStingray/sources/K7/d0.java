package K7;

/* JADX INFO: loaded from: classes.dex */
public final class d0 extends i0 implements H7.u {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final f0 f3782p;

    public d0(f0 f0Var) {
        A7.m.f(f0Var, "property");
        this.f3782p = f0Var;
    }

    @Override // H7.o
    public final H7.w f() {
        return this.f3782p;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, m7.f] */
    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((d0) this.f3782p.f3786t.getValue()).h(obj, obj2);
    }

    @Override // K7.g0
    public final m0 t() {
        return this.f3782p;
    }
}

package K7;

/* JADX INFO: renamed from: K7.a0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0376a0 extends i0 implements H7.s {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final c0 f3762p;

    public C0376a0(c0 c0Var) {
        A7.m.f(c0Var, "property");
        this.f3762p = c0Var;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        return this.f3762p.get(obj);
    }

    @Override // H7.o
    public final H7.w f() {
        return this.f3762p;
    }

    @Override // K7.g0
    public final m0 t() {
        return this.f3762p;
    }
}

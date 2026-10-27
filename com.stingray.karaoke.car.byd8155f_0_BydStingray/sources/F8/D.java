package F8;

/* JADX INFO: loaded from: classes.dex */
public final class D extends AbstractC0279n implements Z {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final A f2432m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final AbstractC0287w f2433n;

    public D(A a10, AbstractC0287w abstractC0287w) {
        A7.m.f(a10, "delegate");
        A7.m.f(abstractC0287w, "enhancement");
        this.f2432m = a10;
        this.f2433n = abstractC0287w;
    }

    @Override // F8.A
    /* JADX INFO: renamed from: O0 */
    public final A L0(boolean z6) {
        a0 a0VarA = AbstractC0268c.A(this.f2432m.L0(z6), this.f2433n.K0().L0(z6));
        A7.m.d(a0VarA, "null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
        return (A) a0VarA;
    }

    @Override // F8.A
    /* JADX INFO: renamed from: P0 */
    public final A N0(H h10) {
        A7.m.f(h10, "newAttributes");
        a0 a0VarA = AbstractC0268c.A(this.f2432m.N0(h10), this.f2433n);
        A7.m.d(a0VarA, "null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
        return (A) a0VarA;
    }

    @Override // F8.AbstractC0279n
    public final A Q0() {
        return this.f2432m;
    }

    @Override // F8.AbstractC0279n
    public final AbstractC0279n S0(A a10) {
        return new D(a10, this.f2433n);
    }

    @Override // F8.AbstractC0279n
    /* JADX INFO: renamed from: T0, reason: merged with bridge method [inline-methods] */
    public final D I0(G8.f fVar) {
        A7.m.f(fVar, "kotlinTypeRefiner");
        A a10 = this.f2432m;
        A7.m.f(a10, "type");
        AbstractC0287w abstractC0287w = this.f2433n;
        A7.m.f(abstractC0287w, "type");
        return new D(a10, abstractC0287w);
    }

    @Override // F8.Z
    public final AbstractC0287w i() {
        return this.f2433n;
    }

    @Override // F8.Z
    public final a0 i0() {
        return this.f2432m;
    }

    @Override // F8.A
    public final String toString() {
        return "[@EnhancedForWarnings(" + this.f2433n + ")] " + this.f2432m;
    }
}

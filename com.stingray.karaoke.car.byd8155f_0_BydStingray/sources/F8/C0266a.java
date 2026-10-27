package F8;

/* JADX INFO: renamed from: F8.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0266a extends AbstractC0279n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final A f2459m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final A f2460n;

    public C0266a(A a10, A a11) {
        A7.m.f(a10, "delegate");
        A7.m.f(a11, "abbreviation");
        this.f2459m = a10;
        this.f2460n = a11;
    }

    @Override // F8.A
    /* JADX INFO: renamed from: P0 */
    public final A N0(H h10) {
        A7.m.f(h10, "newAttributes");
        return new C0266a(this.f2459m.N0(h10), this.f2460n);
    }

    @Override // F8.AbstractC0279n
    public final A Q0() {
        return this.f2459m;
    }

    @Override // F8.AbstractC0279n
    public final AbstractC0279n S0(A a10) {
        return new C0266a(a10, this.f2460n);
    }

    @Override // F8.A
    /* JADX INFO: renamed from: T0, reason: merged with bridge method [inline-methods] */
    public final C0266a L0(boolean z6) {
        return new C0266a(this.f2459m.L0(z6), this.f2460n.L0(z6));
    }

    @Override // F8.AbstractC0279n
    /* JADX INFO: renamed from: U0, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public final C0266a I0(G8.f fVar) {
        A7.m.f(fVar, "kotlinTypeRefiner");
        A a10 = this.f2459m;
        A7.m.f(a10, "type");
        A a11 = this.f2460n;
        A7.m.f(a11, "type");
        return new C0266a(a10, a11);
    }
}

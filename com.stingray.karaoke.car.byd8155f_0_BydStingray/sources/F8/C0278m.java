package F8;

/* JADX INFO: renamed from: F8.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0278m extends AbstractC0279n implements InterfaceC0276k, I8.d {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final A f2473m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f2474n;

    public C0278m(A a10, boolean z6) {
        this.f2473m = a10;
        this.f2474n = z6;
    }

    @Override // F8.InterfaceC0276k
    public final a0 A(AbstractC0287w abstractC0287w) {
        A7.m.f(abstractC0287w, "replacement");
        return AbstractC0268c.l(abstractC0287w.K0(), this.f2474n);
    }

    @Override // F8.AbstractC0279n, F8.AbstractC0287w
    public final boolean F0() {
        return false;
    }

    @Override // F8.A
    /* JADX INFO: renamed from: O0 */
    public final A L0(boolean z6) {
        return z6 ? this.f2473m.L0(z6) : this;
    }

    @Override // F8.A
    /* JADX INFO: renamed from: P0 */
    public final A N0(H h10) {
        A7.m.f(h10, "newAttributes");
        return new C0278m(this.f2473m.N0(h10), this.f2474n);
    }

    @Override // F8.AbstractC0279n
    public final A Q0() {
        return this.f2473m;
    }

    @Override // F8.AbstractC0279n
    public final AbstractC0279n S0(A a10) {
        return new C0278m(a10, this.f2474n);
    }

    @Override // F8.InterfaceC0276k
    public final boolean W() {
        A a10 = this.f2473m;
        a10.B0();
        return a10.B0().n() instanceof Q7.Q;
    }

    @Override // F8.A
    public final String toString() {
        return this.f2473m + " & Any";
    }
}

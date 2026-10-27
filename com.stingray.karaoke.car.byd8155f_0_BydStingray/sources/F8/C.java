package F8;

/* JADX INFO: loaded from: classes.dex */
public final class C extends AbstractC0280o {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final H f2431n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C(A a10, H h10) {
        super(a10);
        A7.m.f(h10, "attributes");
        this.f2431n = h10;
    }

    @Override // F8.AbstractC0279n
    public final AbstractC0279n S0(A a10) {
        return new C(a10, this.f2431n);
    }

    @Override // F8.AbstractC0279n, F8.AbstractC0287w
    public final H t0() {
        return this.f2431n;
    }
}

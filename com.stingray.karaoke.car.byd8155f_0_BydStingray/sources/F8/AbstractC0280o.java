package F8;

/* JADX INFO: renamed from: F8.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0280o extends AbstractC0279n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final A f2475m;

    public AbstractC0280o(A a10) {
        this.f2475m = a10;
    }

    @Override // F8.A
    /* JADX INFO: renamed from: O0 */
    public final A L0(boolean z6) {
        return z6 == F0() ? this : this.f2475m.L0(z6).N0(t0());
    }

    @Override // F8.A
    /* JADX INFO: renamed from: P0 */
    public final A N0(H h10) {
        A7.m.f(h10, "newAttributes");
        return h10 != t0() ? new C(this, h10) : this;
    }

    @Override // F8.AbstractC0279n
    public final A Q0() {
        return this.f2475m;
    }
}

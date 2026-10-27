package F8;

/* JADX INFO: renamed from: F8.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0290z extends AbstractC0280o {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f2489n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0290z(A a10, int i7) {
        super(a10);
        this.f2489n = i7;
    }

    @Override // F8.AbstractC0279n, F8.AbstractC0287w
    public final boolean F0() {
        switch (this.f2489n) {
            case 0:
                return false;
            default:
                return true;
        }
    }

    @Override // F8.AbstractC0279n
    public final AbstractC0279n S0(A a10) {
        switch (this.f2489n) {
            case 0:
                return new C0290z(a10, 0);
            default:
                return new C0290z(a10, 1);
        }
    }
}

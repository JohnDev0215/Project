package B2;

/* JADX INFO: renamed from: B2.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0066f implements F {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC0068h f566a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f567b;
    public final long c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f568e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f569f;

    public C0066f(InterfaceC0068h interfaceC0068h, long j3, long j10, long j11, long j12, long j13) {
        this.f566a = interfaceC0068h;
        this.f567b = j3;
        this.c = j10;
        this.d = j11;
        this.f568e = j12;
        this.f569f = j13;
    }

    @Override // B2.F
    public final boolean h() {
        return true;
    }

    @Override // B2.F
    public final E j(long j3) {
        G g7 = new G(j3, C0067g.a(this.f566a.h(j3), 0L, this.c, this.d, this.f568e, this.f569f));
        return new E(g7, g7);
    }

    @Override // B2.F
    public final long l() {
        return this.f567b;
    }
}

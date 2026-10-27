package k3;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class M0 implements P0 {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Q0 f17829m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f17830n;

    public /* synthetic */ M0(Q0 q02, int i7, int i10) {
        this.l = i10;
        this.f17829m = q02;
        this.f17830n = i7;
    }

    @Override // k3.P0
    public final void d(C1679y0 c1679y0) {
        switch (this.l) {
            case 0:
                this.f17829m.f17843f.f17811t.h(AbstractC1663q.q(this.f17830n));
                break;
            default:
                this.f17829m.f17843f.f17811t.F(AbstractC1663q.s(this.f17830n));
                break;
        }
    }
}

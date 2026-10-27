package k3;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class K0 implements P0 {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Q0 f17822m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ long f17823n;

    public /* synthetic */ K0(Q0 q02, long j3, int i7) {
        this.l = i7;
        this.f17822m = q02;
        this.f17823n = j3;
    }

    @Override // k3.P0
    public final void d(C1679y0 c1679y0) {
        switch (this.l) {
            case 0:
                this.f17822m.f17843f.f17811t.t((int) this.f17823n);
                break;
            default:
                this.f17822m.f17843f.f17811t.k(this.f17823n);
                break;
        }
    }
}

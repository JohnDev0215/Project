package k3;

import h5.C1375a;

/* JADX INFO: renamed from: k3.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RunnableC1666s implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1674w f18107m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1672v f18108n;

    public /* synthetic */ RunnableC1666s(C1674w c1674w, C1672v c1672v, int i7) {
        this.l = i7;
        this.f18107m = c1674w;
        this.f18108n = c1672v;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                C1674w c1674w = this.f18107m;
                C1672v c1672v = this.f18108n;
                c1674w.f18145t = c1672v;
                if (c1674w.f18146u) {
                    c1674w.l(c1672v);
                }
                c1674w.b(new RunnableC1666s(c1674w, c1672v, 1), new T0.C(c1674w, 1));
                break;
            default:
                C1674w c1674w2 = this.f18107m;
                C1672v c1672v2 = this.f18108n;
                if (c1674w2.l instanceof C1375a) {
                    c1672v2.a();
                }
                break;
        }
    }
}

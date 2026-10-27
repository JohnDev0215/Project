package q2;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class i implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ k f21205m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f21206n;

    public /* synthetic */ i(k kVar, l lVar, int i7) {
        this.l = i7;
        this.f21205m = kVar;
        this.f21206n = lVar;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object, q2.l] */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Object, q2.l] */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Object, q2.l] */
    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                k kVar = this.f21205m;
                this.f21206n.b(kVar.f21209a, kVar.f21210b);
                break;
            case 1:
                k kVar2 = this.f21205m;
                this.f21206n.n(kVar2.f21209a, kVar2.f21210b);
                break;
            default:
                k kVar3 = this.f21205m;
                this.f21206n.C(kVar3.f21209a, kVar3.f21210b);
                break;
        }
    }
}

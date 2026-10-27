package Q;

import z7.InterfaceC2782k;

/* JADX INFO: renamed from: Q.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0510i extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C0510i f5921n = new C0510i(1, 0);
    public static final C0510i o = new C0510i(1, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final C0510i f5922p = new C0510i(1, 2);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final C0510i f5923q = new C0510i(1, 3);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f5924m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0510i(int i7, int i10) {
        super(i7);
        this.f5924m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        m7.q qVar = m7.q.f19267a;
        switch (this.f5924m) {
            case 0:
                L0.t.e((L0.j) obj, 0);
                break;
            case 1:
                break;
            case 2:
                H7.w[] wVarArr = L0.t.f3964a;
                L0.u uVar = L0.r.l;
                H7.w wVar = L0.t.f3964a[5];
                uVar.a((L0.j) obj, Boolean.TRUE);
                break;
            default:
                break;
        }
        return qVar;
    }
}

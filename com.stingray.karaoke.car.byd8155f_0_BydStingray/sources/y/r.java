package y;

import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class r extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final r f23990n = new r(1, 0);
    public static final r o = new r(1, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final r f23991p = new r(1, 2);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final r f23992q = new r(1, 3);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final r f23993r = new r(1, 4);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f23994m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ r(int i7, int i10) {
        super(i7);
        this.f23994m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        m7.q qVar = m7.q.f19267a;
        switch (this.f23994m) {
            case 0:
                ((E0.H) obj).a();
                return qVar;
            case 1:
                return qVar;
            case 2:
                ((Number) obj).longValue();
                return qVar;
            case 3:
                L0.f fVar = L0.f.d;
                H7.w[] wVarArr = L0.t.f3964a;
                L0.u uVar = L0.r.c;
                H7.w wVar = L0.t.f3964a[1];
                uVar.a((L0.j) obj, fVar);
                return qVar;
            default:
                return new y0(((Number) obj).intValue());
        }
    }
}

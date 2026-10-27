package e1;

import z7.InterfaceC2782k;

/* JADX INFO: renamed from: e1.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1190c extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C1190c f13766n = new C1190c(1, 0);
    public static final C1190c o = new C1190c(1, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final C1190c f13767p = new C1190c(1, 2);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final C1190c f13768q = new C1190c(1, 3);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final C1190c f13769r = new C1190c(1, 4);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final C1190c f13770s = new C1190c(1, 5);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f13771m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1190c(int i7, int i10) {
        super(i7);
        this.f13771m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        m7.q qVar = m7.q.f19267a;
        switch (this.f13771m) {
            case 0:
                H7.w[] wVarArr = L0.t.f3964a;
                ((L0.j) obj).l(L0.r.f3954r, qVar);
                break;
            case 1:
                ((Number) obj).longValue();
                break;
            case 2:
                break;
            case 3:
                H7.w[] wVarArr2 = L0.t.f3964a;
                ((L0.j) obj).l(L0.r.f3953q, qVar);
                break;
            case 4:
                break;
            default:
                x xVar = (x) obj;
                if (xVar.isAttachedToWindow()) {
                    xVar.m();
                }
                break;
        }
        return qVar;
    }
}

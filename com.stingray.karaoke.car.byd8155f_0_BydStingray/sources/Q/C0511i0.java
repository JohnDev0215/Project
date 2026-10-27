package Q;

import n0.C1863M;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: Q.i0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0511i0 extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f5925m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f5926n;
    public final /* synthetic */ Object o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0511i0(int i7, Object obj, boolean z6) {
        super(1);
        this.f5925m = i7;
        this.f5926n = z6;
        this.o = obj;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        m7.q qVar = m7.q.f19267a;
        Object obj2 = this.o;
        boolean z6 = this.f5926n;
        switch (this.f5925m) {
            case 0:
                L0.j jVar = (L0.j) obj;
                if (!z6) {
                    H7.w[] wVarArr = L0.t.f3964a;
                    jVar.l(L0.r.f3947i, qVar);
                }
                C0505f0 c0505f0 = new C0505f0((C0523o0) obj2, 1);
                H7.w[] wVarArr2 = L0.t.f3964a;
                jVar.l(L0.i.f3891g, new L0.a(null, c0505f0));
                break;
            default:
                ((C1863M) obj).c(!z6 && ((Boolean) ((InterfaceC2772a) obj2).a()).booleanValue());
                break;
        }
        return qVar;
    }
}

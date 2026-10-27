package e1;

import T.C0761q;
import T.C0773y;
import T.X;
import g1.AbstractC1274a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: e1.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1191d extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f13772m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ X f13773n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1191d(X x8, int i7) {
        super(2);
        this.f13772m = i7;
        this.f13773n = x8;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        m7.q qVar = m7.q.f19267a;
        X x8 = this.f13773n;
        int i7 = 0;
        switch (this.f13772m) {
            case 0:
                C0761q c0761q = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
                    c0761q.K();
                } else {
                    ((InterfaceC2785n) x8.getValue()).invoke(c0761q, 0);
                }
                break;
            case 1:
                C0761q c0761q2 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    AbstractC1274a.v(L0.k.a(g0.l.f14684a, false, C1190c.f13766n), b0.g.b(-533674951, new C1191d(x8, i7), c0761q2), c0761q2, 48);
                }
                break;
            default:
                C0761q c0761q3 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q3.x()) {
                    c0761q3.K();
                } else {
                    C0773y c0773y = m.f13798a;
                    ((InterfaceC2785n) x8.getValue()).invoke(c0761q3, 0);
                }
                break;
        }
        return qVar;
    }
}

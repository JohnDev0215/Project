package X5;

import T.C0735d;
import T.C0761q;
import k6.AbstractC1692D;
import n3.C1930E;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class D implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ D0 f10099m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1930E f10100n;

    public /* synthetic */ D(D0 d02, C1930E c1930e, int i7) {
        this.l = i7;
        this.f10099m = d02;
        this.f10100n = c1930e;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                C0761q c0761q = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
                    c0761q.K();
                } else {
                    C7.a.l(this.f10099m, this.f10100n, true, c0761q);
                }
                break;
            default:
                C0761q c0761q2 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    C0735d.a(AbstractC1692D.f18238a.a(h6.i.m(c0761q2)), b0.g.b(-1223600163, new D(this.f10099m, this.f10100n, 0), c0761q2), c0761q2, 56);
                }
                break;
        }
        return m7.q.f19267a;
    }
}

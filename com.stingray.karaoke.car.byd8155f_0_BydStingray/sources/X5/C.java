package X5;

import T.C0735d;
import T.C0761q;
import g1.AbstractC1274a;
import k6.AbstractC1692D;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class C implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ D0 f10096m;

    public /* synthetic */ C(D0 d02, int i7) {
        this.l = i7;
        this.f10096m = d02;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                C0761q c0761q = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
                    c0761q.K();
                } else {
                    C7.a.k(this.f10096m, c0761q);
                }
                break;
            case 1:
                C0761q c0761q2 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    C0735d.a(AbstractC1692D.f18238a.a(h6.i.m(c0761q2)), b0.g.b(435012247, new C(this.f10096m, 0), c0761q2), c0761q2, 56);
                }
                break;
            default:
                C0761q c0761q3 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q3.x()) {
                    c0761q3.K();
                } else {
                    D0 d02 = this.f10096m;
                    AbstractC1274a.t(null, (e2.W) com.bumptech.glide.c.R(d02.f10121Q, c0761q3, 0).getValue(), ((Boolean) com.bumptech.glide.c.R(d02.f10161y0, c0761q3, 0).getValue()).booleanValue(), c0761q3, 0);
                }
                break;
        }
        return m7.q.f19267a;
    }
}

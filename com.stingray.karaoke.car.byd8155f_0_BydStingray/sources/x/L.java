package x;

import D.C0130l;
import T.C0735d;
import T.C0738e0;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class L {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final V.d f23435a = new V.d(new C2620I[16]);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0738e0 f23436b;
    public long c;
    public final C0738e0 d;

    public L() {
        Boolean bool = Boolean.FALSE;
        T.S s6 = T.S.f8224q;
        this.f23436b = C0735d.O(bool, s6);
        this.c = Long.MIN_VALUE;
        this.d = C0735d.O(Boolean.TRUE, s6);
    }

    public final void a(int i7, C0761q c0761q) {
        int i10;
        c0761q.S(-318043801);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(this) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i10 & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            Object objG = c0761q.G();
            T.S s6 = C0753m.f8270a;
            if (objG == s6) {
                objG = C0735d.O(null, T.S.f8224q);
                c0761q.a0(objG);
            }
            T.X x8 = (T.X) objG;
            if (((Boolean) this.d.getValue()).booleanValue() || ((Boolean) this.f23436b.getValue()).booleanValue()) {
                c0761q.Q(1719915818);
                boolean zH = c0761q.h(this);
                Object objG2 = c0761q.G();
                if (zH || objG2 == s6) {
                    objG2 = new C2622K(x8, this, null);
                    c0761q.a0(objG2);
                }
                C0735d.f(c0761q, this, (InterfaceC2785n) objG2);
                c0761q.p(false);
            } else {
                c0761q.Q(1721436120);
                c0761q.p(false);
            }
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0130l(i7, 10, this);
        }
    }
}

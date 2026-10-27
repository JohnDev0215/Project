package Q;

import T.C0735d;
import T.C0752l0;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import n0.C1891t;

/* JADX INFO: renamed from: Q.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0539x {
    static {
        new T.P(C0518m.f5987s);
    }

    public static final void a(C0516l c0516l, S s6, h1 h1Var, b0.b bVar, C0761q c0761q, int i7) {
        int i10;
        S s10;
        h1 h1Var2;
        c0761q.S(-2127166334);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(c0516l) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.h(bVar) ? 2048 : 1024;
        }
        if ((i10 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
            s10 = s6;
            h1Var2 = h1Var;
        } else {
            c0761q.M();
            if ((i7 & 1) == 0 || c0761q.w()) {
                s10 = (S) c0761q.k(T.f5665a);
                h1Var2 = (h1) c0761q.k(i1.f5927a);
            } else {
                c0761q.K();
                s10 = s6;
                h1Var2 = h1Var;
            }
            c0761q.q();
            y.Z zC = M.c(false, 0.0f, c0761q, 0, 7);
            long j3 = c0516l.f5958a;
            boolean zE = c0761q.e(j3);
            Object objG = c0761q.G();
            if (zE || objG == C0753m.f8270a) {
                objG = new O.S(j3, C1891t.b(0.4f, j3));
                c0761q.a0(objG);
            }
            C0735d.b(new C0752l0[]{AbstractC0520n.f5997a.a(c0516l), androidx.compose.foundation.d.f11433a.a(zC), P.y.f5261a.a(C0522o.f6001a), T.f5665a.a(s10), O.T.f4941a.a((O.S) objG), i1.f5927a.a(h1Var2)}, b0.g.b(-1066563262, new A.L(h1Var2, 9, bVar), c0761q), c0761q, 56);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new G.L(c0516l, s10, h1Var2, bVar, i7, 1);
        }
    }
}

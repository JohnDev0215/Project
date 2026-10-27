package Q;

import T.C0735d;
import T.C0752l0;
import T.C0761q;
import T.C0773y;
import n0.AbstractC1861K;
import n0.C1891t;
import n0.InterfaceC1866P;
import y.C2716v;

/* JADX INFO: renamed from: Q.s0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0530s0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0773y f6039a = new C0773y(T.S.f8224q, C0518m.f5991w);

    public static final void a(g0.o oVar, InterfaceC1866P interfaceC1866P, long j3, long j10, b0.b bVar, C0761q c0761q, int i7, int i10) {
        InterfaceC1866P interfaceC1866P2 = (i10 & 2) != 0 ? AbstractC1861K.f19381a : interfaceC1866P;
        long jA = (i10 & 8) != 0 ? AbstractC0520n.a(j3, c0761q) : j10;
        C0773y c0773y = f6039a;
        float f10 = 0 + ((a1.e) c0761q.k(c0773y)).l;
        C0735d.b(new C0752l0[]{AbstractC0524p.f6016a.a(new C1891t(jA)), c0773y.a(new a1.e(f10))}, b0.g.b(-70914509, new C0527q0(oVar, interfaceC1866P2, j3, f10, null, 0, bVar), c0761q), c0761q, 56);
    }

    public static final g0.o b(g0.o oVar, InterfaceC1866P interfaceC1866P, long j3, C2716v c2716v, float f10) {
        g0.o oVarD = g0.l.f14684a;
        g0.o oVarK = oVar.k(f10 > 0.0f ? androidx.compose.ui.graphics.a.b(oVarD, 0.0f, 0.0f, 0.0f, f10, 0.0f, 0L, interfaceC1866P, false, 124895) : oVarD);
        if (c2716v != null) {
            oVarD = h6.i.d(oVarD, c2716v.f24013a, c2716v.f24014b, interfaceC1866P);
        }
        return h6.q.m(androidx.compose.foundation.a.b(oVarK.k(oVarD), j3, interfaceC1866P), interfaceC1866P);
    }

    public static final long c(long j3, float f10, C0761q c0761q) {
        C0516l c0516l = (C0516l) c0761q.k(AbstractC0520n.f5997a);
        boolean zBooleanValue = ((Boolean) c0761q.k(AbstractC0520n.f5998b)).booleanValue();
        if (!C1891t.c(j3, c0516l.f5969p) || !zBooleanValue) {
            return j3;
        }
        boolean zA = a1.e.a(f10, 0);
        long j10 = c0516l.f5969p;
        return zA ? j10 : AbstractC1861K.n(C1891t.b(((((float) Math.log(f10 + 1)) * 4.5f) + 2.0f) / 100.0f, c0516l.f5973t), j10);
    }
}

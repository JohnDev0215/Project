package D;

import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import T.C0735d;
import T.C0754m0;
import T.C0761q;
import T.InterfaceC0744h0;
import h2.AbstractC1347d;
import java.util.HashMap;

/* JADX INFO: renamed from: D.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0133o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final HashMap f1091a = c(true);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final HashMap f1092b = c(false);
    public static final C0132n c = C0132n.f1089b;

    public static final void a(g0.o oVar, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(-211209833);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i10 & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            int i11 = c0761q.f8304P;
            g0.o oVarD = g0.a.d(c0761q, oVar);
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, c, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0735d.V(c0761q, oVarD, C0160j.c);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i11))) {
                AbstractC1347d.l(i11, c0761q, i11, c0158h);
            }
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0130l(i7, 0, oVar);
        }
    }

    public static final void b(C0.U u4, C0.V v10, C0.J j3, a1.k kVar, int i7, int i10, g0.c cVar) {
        g0.c cVar2;
        Object objI = j3.i();
        C0129k c0129k = objI instanceof C0129k ? (C0129k) objI : null;
        C0.U.e(u4, v10, ((c0129k == null || (cVar2 = c0129k.f1082y) == null) ? cVar : cVar2).a(H7.H.c(v10.l, v10.f871m), H7.H.c(i7, i10), kVar));
    }

    public static final HashMap c(boolean z6) {
        HashMap map = new HashMap(9);
        d(map, z6, g0.b.l);
        d(map, z6, g0.b.f14662m);
        d(map, z6, g0.b.f14663n);
        d(map, z6, g0.b.o);
        d(map, z6, g0.b.f14664p);
        d(map, z6, g0.b.f14665q);
        d(map, z6, g0.b.f14666r);
        d(map, z6, g0.b.f14667s);
        d(map, z6, g0.b.f14668t);
        return map;
    }

    public static final void d(HashMap map, boolean z6, g0.g gVar) {
        map.put(gVar, new r(gVar, z6));
    }

    public static final C0.K e(g0.c cVar, boolean z6) {
        C0.K k10 = (C0.K) (z6 ? f1091a : f1092b).get(cVar);
        return k10 == null ? new r(cVar, z6) : k10;
    }
}

package K;

import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import N0.C0409d;
import N0.C0411f;
import T.C0735d;
import T.C0754m0;
import T.C0761q;
import T.InterfaceC0744h0;
import h2.AbstractC1347d;
import java.util.List;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: K.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0338f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final m7.i f3251a;

    static {
        n7.u uVar = n7.u.l;
        f3251a = new m7.i(uVar, uVar);
    }

    public static final void a(C0411f c0411f, List list, C0761q c0761q, int i7) {
        c0761q.S(-1794596951);
        int i10 = (i7 & 6) == 0 ? (c0761q.f(c0411f) ? 4 : 2) | i7 : i7;
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(list) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            int size = list.size();
            for (int i11 = 0; i11 < size; i11++) {
                C0409d c0409d = (C0409d) list.get(i11);
                InterfaceC2786o interfaceC2786o = (InterfaceC2786o) c0409d.f4371a;
                C0336e c0336e = C0336e.f3247b;
                g0.l lVar = g0.l.f14684a;
                int i12 = c0761q.f8304P;
                InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
                g0.o oVarD = g0.a.d(c0761q, lVar);
                InterfaceC0161k.f1681b.getClass();
                C0159i c0159i = C0160j.f1678b;
                c0761q.U();
                if (c0761q.f8303O) {
                    c0761q.l(c0159i);
                } else {
                    c0761q.d0();
                }
                C0735d.V(c0761q, c0336e, C0160j.f1679e);
                C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
                C0158h c0158h = C0160j.f1680f;
                if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i12))) {
                    AbstractC1347d.l(i12, c0761q, i12, c0158h);
                }
                C0735d.V(c0761q, oVarD, C0160j.c);
                interfaceC2786o.g(c0411f.subSequence(c0409d.f4372b, c0409d.c).l, c0761q, 0);
                c0761q.p(true);
            }
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new F0.W(c0411f, list, i7, 2);
        }
    }
}

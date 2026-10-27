package Q5;

import D.AbstractC0133o;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import T.C0735d;
import T.C0761q;
import T.InterfaceC0744h0;
import h2.AbstractC1347d;
import java.util.List;
import n7.AbstractC1998m;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class W0 implements InterfaceC2785n {
    public final /* synthetic */ boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f6615m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ List f6616n;
    public final /* synthetic */ float o;

    public W0(boolean z6, boolean z10, List list, float f10) {
        this.l = z6;
        this.f6615m = z10;
        this.f6616n = list;
        this.o = f10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        C0761q c0761q = (C0761q) obj;
        if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            List<C0569g> listP0 = this.f6616n;
            boolean z6 = this.l;
            boolean z10 = this.f6615m;
            if (!z6 && !z10) {
                listP0 = AbstractC1998m.p0(listP0);
            }
            for (C0569g c0569g : listP0) {
                g0.l lVar = g0.l.f14684a;
                g0.o oVarD = androidx.compose.foundation.layout.a.d(androidx.compose.foundation.layout.c.g(lVar, 0.0f, this.o, 1), c0569g.f6809a.l ? 4.0f : 1.0f);
                C0.K kE = AbstractC0133o.e(g0.b.l, false);
                int i7 = c0761q.f8304P;
                InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
                g0.o oVarD2 = g0.a.d(c0761q, oVarD);
                InterfaceC0161k.f1681b.getClass();
                C0159i c0159i = C0160j.f1678b;
                c0761q.U();
                if (c0761q.f8303O) {
                    c0761q.l(c0159i);
                } else {
                    c0761q.d0();
                }
                C0735d.V(c0761q, kE, C0160j.f1679e);
                C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
                C0158h c0158h = C0160j.f1680f;
                if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i7))) {
                    AbstractC1347d.l(i7, c0761q, i7, c0158h);
                }
                C0735d.V(c0761q, oVarD2, C0160j.c);
                androidx.compose.foundation.layout.b bVar = androidx.compose.foundation.layout.b.f11474a;
                int iOrdinal = c0569g.f6809a.ordinal();
                InterfaceC2772a interfaceC2772a = c0569g.f6810b;
                if (iOrdinal == 0) {
                    c0761q.Q(1768403687);
                    w0.c.l(androidx.compose.foundation.layout.c.c(lVar, 1.0f), interfaceC2772a, c0761q, 6);
                    c0761q.p(false);
                } else if (iOrdinal == 1) {
                    c0761q.Q(1768667621);
                    w0.c.m(bVar.a(lVar, z10 ? g0.b.o : g0.b.f14665q), interfaceC2772a, c0761q, 0);
                    c0761q.p(false);
                } else if (iOrdinal == 2) {
                    c0761q.Q(1768966399);
                    w0.c.p(androidx.compose.foundation.layout.c.c(lVar, 1.0f), interfaceC2772a, c0761q, 6);
                    c0761q.p(false);
                } else if (iOrdinal == 3) {
                    c0761q.Q(1769242795);
                    w0.c.q(6, c0761q, androidx.compose.foundation.layout.c.c(lVar, 1.0f), interfaceC2772a, c0569g.c);
                    c0761q.p(false);
                } else {
                    if (iOrdinal != 4) {
                        throw M5.b.d(-1189881504, c0761q, false);
                    }
                    c0761q.Q(1769563397);
                    w0.c.n(androidx.compose.foundation.layout.c.c(lVar, 1.0f), interfaceC2772a, c0761q, 6);
                    c0761q.p(false);
                }
                c0761q.p(true);
            }
        }
        return m7.q.f19267a;
    }
}

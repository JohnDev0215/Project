package Q5;

import D.AbstractC0133o;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import Q.AbstractC0535v;
import T.C0735d;
import T.C0761q;
import T.InterfaceC0744h0;
import h2.AbstractC1347d;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class I2 implements InterfaceC2785n {
    public final /* synthetic */ Integer l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ long f6394m;

    public I2(long j3, Integer num) {
        this.l = num;
        this.f6394m = j3;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        C0761q c0761q = (C0761q) obj;
        if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            g0.l lVar = g0.l.f14684a;
            g0.o oVarL = androidx.compose.foundation.layout.c.l(lVar, Q.A0.f5488a);
            C0.K kE = AbstractC0133o.e(g0.b.l, false);
            int i7 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVarL);
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
            C0735d.V(c0761q, oVarD, C0160j.c);
            c0761q.Q(-2084591590);
            Integer num = this.l;
            if (num != null) {
                AbstractC0535v.a(l9.l.W(num.intValue(), c0761q, 0), null, androidx.compose.foundation.layout.c.c(lVar, 1.0f), this.f6394m, c0761q, 432, 0);
            }
            c0761q.p(false);
            c0761q.p(true);
        }
        return m7.q.f19267a;
    }
}

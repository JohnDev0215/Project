package U5;

import D.AbstractC0127i;
import D.AbstractC0139v;
import D.C0141x;
import D.P;
import D.S;
import E.C0147d;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import N5.f0;
import Q.c1;
import Q5.C0606p0;
import T.C0735d;
import T.C0753m;
import T.C0761q;
import T.InterfaceC0744h0;
import T.R0;
import com.stingray.karaoke.car.utils.FontSize;
import com.stingray.kca.model.KaraokeSongResponseV2;
import h2.AbstractC1347d;
import java.io.IOException;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2787p;

/* JADX INFO: loaded from: classes.dex */
public final class w extends A7.o implements InterfaceC2787p {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ List f9209m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f9210n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(List list, InterfaceC2782k interfaceC2782k) {
        super(4);
        this.f9209m = list;
        this.f9210n = interfaceC2782k;
    }

    @Override // z7.InterfaceC2787p
    public final Object o(Object obj, Object obj2, Object obj3, Object obj4) throws XmlPullParserException, IOException {
        int i7;
        C0147d c0147d = (C0147d) obj;
        int iIntValue = ((Number) obj2).intValue();
        C0761q c0761q = (C0761q) obj3;
        int iIntValue2 = ((Number) obj4).intValue();
        if ((iIntValue2 & 6) == 0) {
            i7 = (c0761q.f(c0147d) ? 4 : 2) | iIntValue2;
        } else {
            i7 = iIntValue2;
        }
        if ((iIntValue2 & 48) == 0) {
            i7 |= c0761q.d(iIntValue) ? 32 : 16;
        }
        if ((i7 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            f0 f0Var = (f0) this.f9209m.get(iIntValue);
            c0761q.Q(-279965209);
            g0.l lVar = g0.l.f14684a;
            g0.o oVarD = androidx.compose.foundation.layout.c.d(lVar, 1.0f);
            c0761q.Q(-147576662);
            InterfaceC2782k interfaceC2782k = this.f9210n;
            boolean zF = c0761q.f(interfaceC2782k) | c0761q.h(f0Var);
            Object objG = c0761q.G();
            if (zF || objG == C0753m.f8270a) {
                objG = new C0606p0(interfaceC2782k, f0Var, 3);
                c0761q.a0(objG);
            }
            c0761q.p(false);
            g0.o oVarO = com.bumptech.glide.c.O(oVarD, (InterfaceC2772a) objG);
            m6.b bVar = m6.c.f19252a;
            g0.o oVarO2 = androidx.compose.foundation.layout.a.o(androidx.compose.foundation.layout.c.e(oVarO, bVar.u0(c0761q)), bVar.F(c0761q), bVar.H(6, c0761q), 0.0f, bVar.H(6, c0761q), 4);
            S sB = P.b(AbstractC0127i.f1076a, g0.b.f14669u, c0761q, 0);
            int i10 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD2 = g0.a.d(c0761q, oVarO2);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0158h c0158h = C0160j.f1679e;
            C0735d.V(c0761q, sB, c0158h);
            C0158h c0158h2 = C0160j.d;
            C0735d.V(c0761q, interfaceC0744h0M, c0158h2);
            C0158h c0158h3 = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i10))) {
                AbstractC1347d.l(i10, c0761q, i10, c0158h3);
            }
            C0158h c0158h4 = C0160j.c;
            C0735d.V(c0761q, oVarD2, c0158h4);
            g0.o oVarM = h6.q.m(androidx.compose.foundation.layout.a.d(androidx.compose.foundation.layout.c.b(lVar, 1.0f), 1.0f), J.e.a(bVar.g0(c0761q)));
            KaraokeSongResponseV2 karaokeSongResponseV2 = f0Var.f4691a;
            String str = karaokeSongResponseV2.l;
            Boolean bool = Boolean.TRUE;
            boolean zA = A7.m.a(karaokeSongResponseV2.f13145m, bool);
            KaraokeSongResponseV2 karaokeSongResponseV22 = f0Var.f4691a;
            com.bumptech.glide.c.k(oVarM, str, A7.m.a(karaokeSongResponseV22.f13146n, bool), zA, A7.m.a(karaokeSongResponseV22.f13143j, bool), null, c0761q, 0, 32);
            g0.o oVarO3 = androidx.compose.foundation.layout.a.o(lVar, bVar.F(c0761q), 0.0f, 0.0f, 0.0f, 14);
            C0141x c0141xA = AbstractC0139v.a(AbstractC0127i.c, g0.b.f14672x, c0761q, 0);
            int i11 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M2 = c0761q.m();
            g0.o oVarD3 = g0.a.d(c0761q, oVarO3);
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, c0141xA, c0158h);
            C0735d.V(c0761q, interfaceC0744h0M2, c0158h2);
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i11))) {
                AbstractC1347d.l(i11, c0761q, i11, c0158h3);
            }
            C0735d.V(c0761q, oVarD3, c0158h4);
            String str2 = karaokeSongResponseV22.f13137b;
            String str3 = str2 == null ? "" : str2;
            long j3 = l6.d.c(c0761q).f18696b;
            long jG = h6.f.g(FontSize.H9, c0761q);
            R0 r02 = O5.c.f5157b;
            c1.b(str3, null, j3, jG, null, null, null, 0L, null, null, 0L, 2, false, 1, 0, null, ((O5.d) c0761q.k(r02)).f5158a, c0761q, 0, 3120, 55282);
            String str4 = karaokeSongResponseV22.c;
            c1.b(str4 == null ? "" : str4, null, l6.d.c(c0761q).c, h6.f.g(FontSize.Subtitle1, c0761q), null, S0.A.f7801t, null, 0L, null, null, 0L, 2, false, 1, 0, null, ((O5.d) c0761q.k(r02)).d, c0761q, 196608, 3120, 55250);
            M5.b.n(c0761q, true, true, false);
        }
        return m7.q.f19267a;
    }
}

package Q5;

import D.AbstractC0127i;
import D.AbstractC0133o;
import D.AbstractC0139v;
import D.C0141x;
import D.C0142y;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import T.C0735d;
import T.C0753m;
import T.C0761q;
import T.InterfaceC0744h0;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.car.utils.FontSize;
import h2.AbstractC1347d;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class d3 implements InterfaceC2785n {
    public final /* synthetic */ g0.o l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f6758m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f6759n;
    public final /* synthetic */ String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ String f6760p;

    public d3(g0.o oVar, InterfaceC2772a interfaceC2772a, int i7, String str, String str2) {
        this.l = oVar;
        this.f6758m = interfaceC2772a;
        this.f6759n = i7;
        this.o = str;
        this.f6760p = str2;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        m6.b bVar;
        C0761q c0761q;
        g0.l lVar;
        int i7;
        boolean z6;
        C0761q c0761q2;
        boolean z10;
        C0761q c0761q3;
        C0761q c0761q4 = (C0761q) obj;
        if ((((Number) obj2).intValue() & 3) == 2 && c0761q4.x()) {
            c0761q4.K();
        } else {
            c0761q4.Q(1738382229);
            InterfaceC2772a interfaceC2772a = this.f6758m;
            boolean zF = c0761q4.f(interfaceC2772a);
            Object objG = c0761q4.G();
            if (zF || objG == C0753m.f8270a) {
                objG = new C0549b(interfaceC2772a, 18);
                c0761q4.a0(objG);
            }
            c0761q4.p(false);
            g0.o oVarP = com.bumptech.glide.c.P(this.l, false, null, null, (InterfaceC2772a) objG, 15);
            m6.b bVar2 = m6.c.f19252a;
            g0.o oVarA = androidx.compose.foundation.a.a(androidx.compose.foundation.layout.a.d(androidx.compose.foundation.layout.c.d(h6.q.m(oVarP, J.e.a(bVar2.f0(c0761q4))), 1.0f), 1.0f), h6.r.l(this.f6759n, c0761q4), null, 6);
            C0141x c0141xA = AbstractC0139v.a(AbstractC0127i.c, g0.b.f14672x, c0761q4, 0);
            int i10 = c0761q4.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q4.m();
            g0.o oVarD = g0.a.d(c0761q4, oVarA);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q4.U();
            if (c0761q4.f8303O) {
                c0761q4.l(c0159i);
            } else {
                c0761q4.d0();
            }
            C0158h c0158h = C0160j.f1679e;
            C0735d.V(c0761q4, c0141xA, c0158h);
            C0158h c0158h2 = C0160j.d;
            C0735d.V(c0761q4, interfaceC0744h0M, c0158h2);
            C0158h c0158h3 = C0160j.f1680f;
            if (c0761q4.f8303O || !A7.m.a(c0761q4.G(), Integer.valueOf(i10))) {
                AbstractC1347d.l(i10, c0761q4, i10, c0158h3);
            }
            C0158h c0158h4 = C0160j.c;
            C0735d.V(c0761q4, oVarD, c0158h4);
            C0142y c0142y = C0142y.f1119a;
            c0761q4.Q(2085039957);
            g0.l lVar2 = g0.l.f14684a;
            String str = this.o;
            if (str != null) {
                g0.o oVarA2 = C0142y.a(c0142y, androidx.compose.foundation.layout.c.d(lVar2, 1.0f), 1.0f);
                C0.K kE = AbstractC0133o.e(g0.b.l, false);
                int i11 = c0761q4.f8304P;
                InterfaceC0744h0 interfaceC0744h0M2 = c0761q4.m();
                g0.o oVarD2 = g0.a.d(c0761q4, oVarA2);
                c0761q4.U();
                if (c0761q4.f8303O) {
                    c0761q4.l(c0159i);
                } else {
                    c0761q4.d0();
                }
                C0735d.V(c0761q4, kE, c0158h);
                C0735d.V(c0761q4, interfaceC0744h0M2, c0158h2);
                if (c0761q4.f8303O || !A7.m.a(c0761q4.G(), Integer.valueOf(i11))) {
                    AbstractC1347d.l(i11, c0761q4, i11, c0158h3);
                }
                C0735d.V(c0761q4, oVarD2, c0158h4);
                lVar = lVar2;
                bVar = bVar2;
                Q.c1.b(str, androidx.compose.foundation.layout.b.f11474a.a(androidx.compose.foundation.layout.c.r(androidx.compose.foundation.layout.c.d(lVar2, 0.925f)), g0.b.f14664p), l6.d.c(c0761q4).f18696b, h6.f.g(FontSize.f13042H8, c0761q4), null, null, null, 0L, null, new Y0.i(3), 0L, 2, false, 0, 0, null, ((O5.d) c0761q4.k(O5.c.f5157b)).f5161f, c0761q4, 0, 48, 62960);
                c0761q = c0761q4;
                i7 = 1;
                c0761q.p(true);
                z6 = false;
            } else {
                bVar = bVar2;
                c0761q = c0761q4;
                lVar = lVar2;
                i7 = 1;
                z6 = false;
            }
            c0761q.p(z6);
            c0761q.Q(2085065608);
            String str2 = this.f6760p;
            if (str2 == null || Q8.h.M0(str2)) {
                c0761q2 = c0761q;
            } else if (str2.equals("0")) {
                z10 = z6;
                c0761q3 = c0761q;
                c0761q3.p(z10);
                c0761q3.p(true);
            } else {
                g0.o oVarM = androidx.compose.foundation.layout.a.m(androidx.compose.foundation.layout.c.r(androidx.compose.foundation.layout.c.d(lVar, 1.0f)), 0.0f, bVar.H(6, c0761q), i7);
                c0761q2 = c0761q;
                Q.c1.b(str2 + " " + AbstractC2673a.X(str2.equals("1") ? R.string.Global_Song : R.string.Global_Songs, c0761q), oVarM, l6.d.c(c0761q).f18696b, h6.f.g(FontSize.Subtitle1, c0761q), null, null, null, 0L, null, new Y0.i(3), 0L, 2, false, 1, 0, null, ((O5.d) c0761q.k(O5.c.f5157b)).f5158a, c0761q2, 0, 3120, 54768);
            }
            c0761q3 = c0761q2;
            z10 = false;
            c0761q3.p(z10);
            c0761q3.p(true);
        }
        return m7.q.f19267a;
    }
}

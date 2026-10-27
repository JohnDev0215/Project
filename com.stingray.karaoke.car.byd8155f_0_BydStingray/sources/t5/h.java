package T5;

import D.AbstractC0120b;
import D.AbstractC0127i;
import D.P;
import D.S;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import F0.D0;
import H7.H;
import N0.C0408c;
import N0.C0411f;
import N0.D;
import N0.L;
import Q5.C0611q1;
import T.C0735d;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import T.InterfaceC0744h0;
import a.AbstractC0904a;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.car.player.LeadVocalState;
import com.stingray.karaoke.car.utils.FontSize;
import g0.o;
import h2.AbstractC1347d;
import java.util.Iterator;
import m7.q;
import n7.AbstractC1998m;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public abstract class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float f8717a = 52 * 1.25f;

    public static final void a(final o oVar, final boolean z6, final InterfaceC2772a interfaceC2772a, final boolean z10, final LeadVocalState leadVocalState, final boolean z11, final boolean z12, final InterfaceC2772a interfaceC2772a2, final boolean z13, final boolean z14, final FontSize fontSize, C0761q c0761q, final int i7, final int i10) {
        int i11;
        int i12;
        float fD;
        boolean z15;
        C0411f c0411fE;
        boolean z16;
        C0411f c0411fF;
        A7.m.f(interfaceC2772a, "onChangeVocal");
        A7.m.f(leadVocalState, "leadVocalState");
        A7.m.f(interfaceC2772a2, "onChangePerfectPitch");
        c0761q.S(-1450407521);
        if ((i7 & 6) == 0) {
            i11 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i11 = i7;
        }
        if ((i7 & 48) == 0) {
            i11 |= c0761q.g(z6) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i11 |= c0761q.h(interfaceC2772a) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i11 |= c0761q.g(z10) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i11 |= c0761q.f(leadVocalState) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i11 |= c0761q.g(z11) ? 131072 : 65536;
        }
        if ((i7 & 1572864) == 0) {
            i11 |= c0761q.g(z12) ? 1048576 : 524288;
        }
        if ((i7 & 12582912) == 0) {
            i11 |= c0761q.h(interfaceC2772a2) ? 8388608 : 4194304;
        }
        if ((i7 & 100663296) == 0) {
            i11 |= c0761q.g(z13) ? 67108864 : 33554432;
        }
        if ((i7 & 805306368) == 0) {
            i11 |= c0761q.g(z14) ? 536870912 : 268435456;
        }
        if ((i10 & 6) == 0) {
            i12 = i10 | (c0761q.f(fontSize) ? 4 : 2);
        } else {
            i12 = i10;
        }
        if ((i11 & 306783379) == 306783378 && (i12 & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            S sB = P.b(AbstractC0127i.f1076a, g0.b.f14670v, c0761q, 54);
            int i13 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            o oVarD = g0.a.d(c0761q, oVar);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, sB, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i13))) {
                AbstractC1347d.l(i13, c0761q, i13, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            L l = ((O5.d) c0761q.k(O5.c.f5157b)).f5158a;
            if (z14) {
                c0761q.Q(1424981933);
                fD = c(fontSize, l, z11, c0761q, (i12 & 14) | ((i11 >> 9) & 896));
                c0761q.p(false);
            } else {
                c0761q.Q(1424985637);
                fD = d(fontSize, l, z11, c0761q, (i12 & 14) | ((i11 >> 9) & 896));
                c0761q.p(false);
            }
            float f10 = fD;
            g0.l lVar = g0.l.f14684a;
            float f11 = f8717a;
            o oVarK = androidx.compose.foundation.layout.c.k(AbstractC2673a.r(f10, f11));
            c0761q.Q(1424994833);
            if (z13) {
                c0411fE = e(leadVocalState, c0761q);
                z15 = false;
            } else {
                z15 = false;
                c0411fE = null;
            }
            c0761q.p(z15);
            int i14 = z14 ? 2 : 1;
            Integer numValueOf = Integer.valueOf(R.drawable.ic_lead_vocal_active);
            c0761q.Q(1425001374);
            boolean z17 = (i11 & 896) == 256;
            Object objG = c0761q.G();
            T.S s6 = C0753m.f8270a;
            if (z17 || objG == s6) {
                objG = new C0611q1(interfaceC2772a, 2);
                c0761q.a0(objG);
            }
            InterfaceC2782k interfaceC2782k = (InterfaceC2782k) objG;
            c0761q.p(false);
            int i15 = ((i11 << 6) & 7168) | 1572864;
            int i16 = (i12 << 21) & 29360128;
            H.j(oVarK, numValueOf, c0411fE, z6, z10, interfaceC2782k, 1.25f, fontSize, l, i14, c0761q, i15 | ((i11 << 3) & 57344) | i16, 0);
            c0761q.Q(1425010260);
            if (z11) {
                AbstractC0120b.b(c0761q, androidx.compose.foundation.layout.c.o(lVar, m6.c.f19252a.F(c0761q)));
                o oVarK2 = androidx.compose.foundation.layout.c.k(AbstractC2673a.r(f10, f11));
                c0761q.Q(1425020245);
                if (z13) {
                    c0411fF = f(c0761q);
                    z16 = false;
                } else {
                    z16 = false;
                    c0411fF = null;
                }
                c0761q.p(z16);
                int i17 = z14 ? 2 : 1;
                Integer numValueOf2 = Integer.valueOf(R.drawable.ic_microphone);
                c0761q.Q(1425026085);
                boolean z18 = (i11 & 29360128) == 8388608;
                Object objG2 = c0761q.G();
                if (z18 || objG2 == s6) {
                    objG2 = new C0611q1(interfaceC2772a2, 3);
                    c0761q.a0(objG2);
                }
                c0761q.p(false);
                H.j(oVarK2, numValueOf2, c0411fF, z6, z12, (InterfaceC2782k) objG2, 1.25f, fontSize, l, i17, c0761q, i15 | ((i11 >> 6) & 57344) | i16, 0);
            }
            c0761q.p(false);
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: T5.g
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i7 | 1);
                    int iZ2 = C0735d.Z(i10);
                    boolean z19 = z14;
                    FontSize fontSize2 = fontSize;
                    h.a(oVar, z6, interfaceC2772a, z10, leadVocalState, z11, z12, interfaceC2772a2, z13, z19, fontSize2, (C0761q) obj, iZ, iZ2);
                    return q.f19267a;
                }
            };
        }
    }

    public static final float b(float f10, boolean z6, C0761q c0761q) {
        c0761q.Q(1351017401);
        float fMax = Math.max(f10, f8717a);
        c0761q.Q(1824760631);
        boolean z10 = z6 || ((Boolean) c0761q.k(D0.f2013a)).booleanValue();
        c0761q.p(false);
        if (z10) {
            fMax = (fMax * 2) + m6.c.f19252a.F(c0761q);
        }
        c0761q.p(false);
        return fMax;
    }

    public static final float c(FontSize fontSize, L l, boolean z6, C0761q c0761q, int i7) {
        c0761q.Q(-1299214472);
        long jG = h6.f.g(fontSize, c0761q);
        C0411f c0411fE = e(LeadVocalState.LeadVocalOn, c0761q);
        C0411f c0411fE2 = e(LeadVocalState.DisabledLeadVocalOff, c0761q);
        c0761q.Q(-313613943);
        c0761q.Q(-313613858);
        boolean z10 = false;
        boolean z11 = z6 || ((Boolean) c0761q.k(D0.f2013a)).booleanValue();
        c0761q.p(false);
        String str = z11 ? f(c0761q).l : "";
        c0761q.p(false);
        Iterator it = AbstractC1998m.U(AbstractC1998m.n0(AbstractC1998m.n0(Q8.h.V0(c0411fE.l, new String[]{" "}, 0, 6), Q8.h.V0(c0411fE2.l, new String[]{" "}, 0, 6)), Q8.h.V0(str, new String[]{" "}, 0, 6))).iterator();
        float f10 = 0;
        while (it.hasNext()) {
            float f11 = f10;
            float fJ = AbstractC0904a.J((String) it.next(), L.b(l, 0L, jG, null, null, 0L, null, 0L, null, null, null, 16777213), c0761q);
            f10 = Float.compare(fJ, f11) > 0 ? fJ : f11;
            z10 = false;
        }
        float f12 = f10;
        c0761q.p(z10);
        return f12;
    }

    public static final float d(FontSize fontSize, L l, boolean z6, C0761q c0761q, int i7) {
        c0761q.Q(-1709447224);
        float fJ = AbstractC0904a.J(e(LeadVocalState.LeadVocalOn, c0761q).l, L.b(l, 0L, h6.f.g(fontSize, c0761q), null, null, 0L, null, 0L, null, null, null, 16777213), c0761q);
        float fJ2 = AbstractC0904a.J(e(LeadVocalState.DisabledLeadVocalOff, c0761q).l, L.b(l, 0L, h6.f.g(fontSize, c0761q), null, null, 0L, null, 0L, null, null, null, 16777213), c0761q);
        c0761q.Q(1828113530);
        String str = z6 ? f(c0761q).l : "";
        c0761q.p(false);
        float fW0 = m6.c.f19252a.w0(c0761q) + ((a1.e) h6.f.o(new a1.e(fJ2), h6.f.o(new a1.e(fJ), new a1.e(AbstractC0904a.J(str, L.b(l, 0L, h6.f.g(fontSize, c0761q), null, null, 0L, null, 0L, null, null, null, 16777213), c0761q))))).l;
        c0761q.p(false);
        return fW0;
    }

    public static final C0411f e(LeadVocalState leadVocalState, C0761q c0761q) {
        c0761q.Q(-1711661757);
        C0408c c0408c = new C0408c();
        if (leadVocalState == LeadVocalState.DisabledLeadVocalOff) {
            c0761q.Q(-1441313586);
            int iE = c0408c.e(new D(l6.d.c(c0761q).f18702f, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, 65534));
            try {
                c0408c.c(AbstractC2673a.X(R.string.Global_NotAvailable, c0761q));
                c0408c.d(iE);
                c0761q.p(false);
            } catch (Throwable th) {
                c0408c.d(iE);
                throw th;
            }
        } else {
            c0761q.Q(-1441144884);
            c0408c.c(AbstractC2673a.X(R.string.LeadVocal_Switch, c0761q));
            c0761q.p(false);
        }
        C0411f c0411fF = c0408c.f();
        c0761q.p(false);
        return c0411fF;
    }

    public static final C0411f f(C0761q c0761q) {
        c0761q.Q(-1439785163);
        C0408c c0408c = new C0408c();
        c0408c.c("Perfect Pitch");
        C0411f c0411fF = c0408c.f();
        c0761q.p(false);
        return c0411fF;
    }
}

package z;

import A.C0004b;
import A.C0044v0;
import C0.C0114v;
import C0.K;
import D.AbstractC0127i;
import D.AbstractC0133o;
import D.AbstractC0139v;
import D.C0119a;
import D.C0124f;
import D.C0141x;
import D.C0142y;
import D.P;
import D.S;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import K.X;
import N0.L;
import Q.C0509h0;
import T.C0735d;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import T.InterfaceC0744h0;
import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import androidx.compose.foundation.layout.LayoutWeightElement;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import e1.C1187A;
import g0.o;
import h2.AbstractC1347d;
import h6.r;
import h8.AbstractC1386c;
import n0.AbstractC1861K;
import n0.C1891t;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public abstract class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C1187A f24252a = new C1187A(14, true);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C2751b f24253b;

    static {
        long j3 = C1891t.f19446e;
        long j10 = C1891t.f19445b;
        f24253b = new C2751b(j3, j10, j10, C1891t.b(0.38f, j10), C1891t.b(0.38f, j10));
    }

    public static final void a(C2751b c2751b, o oVar, b0.b bVar, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(-921259293);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(c2751b) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(oVar) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(bVar) ? 256 : 128;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            o oVarT = r.t(androidx.compose.foundation.layout.a.m(androidx.compose.foundation.layout.a.p(androidx.compose.foundation.a.b(AbstractC1386c.t(oVar, i.d, J.e.a(i.f24240e), 28), c2751b.f24226a, AbstractC1861K.f19381a)), 0.0f, i.f24244i, 1), r.q(c0761q));
            int i11 = (i10 << 3) & 7168;
            C0141x c0141xA = AbstractC0139v.a(AbstractC0127i.c, g0.b.f14672x, c0761q, 0);
            int i12 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            o oVarD = g0.a.d(c0761q, oVarT);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, c0141xA, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i12))) {
                AbstractC1347d.l(i12, c0761q, i12, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            bVar.g(C0142y.f1119a, c0761q, Integer.valueOf(((i11 >> 6) & 112) | 6));
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0114v(c2751b, oVar, bVar, i7);
        }
    }

    public static final void b(String str, boolean z6, C2751b c2751b, o oVar, InterfaceC2786o interfaceC2786o, InterfaceC2772a interfaceC2772a, C0761q c0761q, int i7) {
        int i10;
        boolean z10;
        c0761q.S(791018367);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(str) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.g(z6) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(c2751b) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.f(oVar) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.h(interfaceC2786o) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 131072 : 65536;
        }
        if ((74899 & i10) == 74898 && c0761q.x()) {
            c0761q.K();
        } else {
            g0.f fVar = i.f24241f;
            C0119a c0119a = AbstractC0127i.f1076a;
            float f10 = i.f24243h;
            C0124f c0124f = new C0124f(f10);
            boolean z11 = ((i10 & 112) == 32) | ((458752 & i10) == 131072);
            Object objG = c0761q.G();
            if (z11 || objG == C0753m.f8270a) {
                objG = new I.b(interfaceC2772a, z6);
                c0761q.a0(objG);
            }
            o oVarD = androidx.compose.foundation.layout.c.d(androidx.compose.foundation.a.e(oVar, z6, str, (InterfaceC2772a) objG, 4), 1.0f);
            float f11 = i.f24238a;
            float f12 = i.f24239b;
            float f13 = i.c;
            o oVarM = androidx.compose.foundation.layout.a.m(androidx.compose.foundation.layout.c.n(oVarD, f11, f13, f12, f13), f10, 0.0f, 2);
            S sB = P.b(c0124f, fVar, c0761q, 54);
            int i11 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            o oVarD2 = g0.a.d(c0761q, oVarM);
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
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i11))) {
                AbstractC1347d.l(i11, c0761q, i11, c0158h3);
            }
            C0158h c0158h4 = C0160j.c;
            C0735d.V(c0761q, oVarD2, c0158h4);
            if (interfaceC2786o == null) {
                c0761q.Q(554568909);
                z10 = false;
            } else {
                c0761q.Q(554568910);
                g0.l lVar = g0.l.f14684a;
                float f14 = i.f24245j;
                o oVarJ = androidx.compose.foundation.layout.c.j(lVar, f14, 0.0f, f14, f14, 2);
                K kE = AbstractC0133o.e(g0.b.l, false);
                int i12 = c0761q.f8304P;
                InterfaceC0744h0 interfaceC0744h0M2 = c0761q.m();
                o oVarD3 = g0.a.d(c0761q, oVarJ);
                c0761q.U();
                if (c0761q.f8303O) {
                    c0761q.l(c0159i);
                } else {
                    c0761q.d0();
                }
                C0735d.V(c0761q, kE, c0158h);
                C0735d.V(c0761q, interfaceC0744h0M2, c0158h2);
                if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i12))) {
                    AbstractC1347d.l(i12, c0761q, i12, c0158h3);
                }
                C0735d.V(c0761q, oVarD3, c0158h4);
                z10 = false;
                interfaceC2786o.g(new C1891t(z6 ? c2751b.c : c2751b.f24228e), c0761q, 0);
                c0761q.p(true);
            }
            c0761q.p(z10);
            L l = new L(z6 ? c2751b.f24227b : c2751b.d, i.f24246k, i.l, null, null, i.f24248n, i.f24242g, i.f24247m, null, 16613240);
            if (1.0f <= 0.0d) {
                throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero".toString());
            }
            X.b(str, new LayoutWeightElement(AbstractC2673a.s(1.0f, Float.MAX_VALUE), true), l, null, 0, false, 1, 0, null, c0761q, (i10 & 14) | 1572864, 440);
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0509h0(str, z6, c2751b, oVar, interfaceC2786o, interfaceC2772a, i7);
        }
    }

    public static final void c(g gVar, InterfaceC2772a interfaceC2772a, o oVar, C0004b c0004b, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(712057293);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(gVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(oVar) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.h(c0004b) ? 2048 : 1024;
        }
        if ((i10 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
        } else {
            Context context = (Context) c0761q.k(AndroidCompositionLocals_androidKt.f11688b);
            boolean zF = c0761q.f((Configuration) c0761q.k(AndroidCompositionLocals_androidKt.f11687a)) | c0761q.f(context);
            Object objG = c0761q.G();
            if (zF || objG == C0753m.f8270a) {
                C2751b c2751b = f24253b;
                long jC = c2751b.f24226a;
                TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(R.style.Widget.PopupMenu, new int[]{R.attr.colorBackground});
                int iE = AbstractC1861K.E(jC);
                int color = typedArrayObtainStyledAttributes.getColor(0, iE);
                typedArrayObtainStyledAttributes.recycle();
                if (color != iE) {
                    jC = AbstractC1861K.c(color);
                }
                long j3 = jC;
                TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(R.style.TextAppearance.Widget.PopupMenu.Large, new int[]{R.attr.textColorPrimary});
                ColorStateList colorStateList = typedArrayObtainStyledAttributes2.getColorStateList(0);
                typedArrayObtainStyledAttributes2.recycle();
                long jC2 = c2751b.f24227b;
                int iE2 = AbstractC1861K.E(jC2);
                Integer numValueOf = colorStateList != null ? Integer.valueOf(colorStateList.getColorForState(new int[]{R.attr.state_enabled}, iE2)) : null;
                if (numValueOf != null && numValueOf.intValue() != iE2) {
                    jC2 = AbstractC1861K.c(numValueOf.intValue());
                }
                long j10 = jC2;
                long jC3 = c2751b.d;
                int iE3 = AbstractC1861K.E(jC3);
                Integer numValueOf2 = colorStateList != null ? Integer.valueOf(colorStateList.getColorForState(new int[]{-16842910}, iE3)) : null;
                if (numValueOf2 != null && numValueOf2.intValue() != iE3) {
                    jC3 = AbstractC1861K.c(numValueOf2.intValue());
                }
                long j11 = jC3;
                objG = new C2751b(j3, j10, j10, j11, j11);
                c0761q.a0(objG);
            }
            d(gVar, interfaceC2772a, oVar, (C2751b) objG, c0004b, c0761q, (i10 & 1022) | ((i10 << 3) & 57344));
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new G.L(gVar, interfaceC2772a, oVar, c0004b, i7);
        }
    }

    public static final void d(g gVar, InterfaceC2772a interfaceC2772a, o oVar, C2751b c2751b, C0004b c0004b, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(1447189339);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(gVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(oVar) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.f(c2751b) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.h(c0004b) ? 16384 : 8192;
        }
        if ((i10 & 9363) == 9362 && c0761q.x()) {
            c0761q.K();
        } else {
            e1.m.a(gVar, interfaceC2772a, f24252a, b0.g.b(795909757, new C0044v0(c2751b, oVar, c0004b, 7), c0761q), c0761q, (i10 & 14) | 3456 | (i10 & 112), 0);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new b0.a(gVar, interfaceC2772a, oVar, c2751b, c0004b, i7);
        }
    }
}

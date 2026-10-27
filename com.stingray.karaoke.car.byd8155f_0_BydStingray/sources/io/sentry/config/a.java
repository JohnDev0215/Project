package io.sentry.config;

import A.EnumC0017h0;
import A7.E;
import A7.m;
import A9.k;
import B8.A;
import C0.K;
import C0.c0;
import D.AbstractC0120b;
import D.AbstractC0127i;
import D.AbstractC0133o;
import D.AbstractC0139v;
import D.C0119a;
import D.C0122d;
import D.C0124f;
import D.C0141x;
import D.InterfaceC0123e;
import D.L;
import D.P;
import E.C0146c;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import E0.d0;
import F0.AbstractC0227s0;
import F0.C0221p;
import F0.T;
import F8.AbstractC0287w;
import F8.C0269d;
import F8.F;
import F8.O;
import H7.H;
import H7.InterfaceC0318c;
import I5.y;
import K7.o0;
import K7.r0;
import L0.u;
import N7.n;
import Q.AbstractC0535v;
import Q.c1;
import Q5.C0549b;
import Q5.C0593m;
import Q5.C0605p;
import Q5.C0607p1;
import Q5.C0618s1;
import Q5.C0641y0;
import Q5.C0645z0;
import Q5.C1;
import Q5.D1;
import Q5.G1;
import Q5.H1;
import Q5.M;
import Q5.M1;
import Q5.N0;
import Q5.N1;
import Q5.O1;
import Q5.Q1;
import Q5.S1;
import Q5.T1;
import Q5.U;
import Q5.U1;
import Q5.X1;
import Q5.Y1;
import Q7.D;
import Q7.InterfaceC0654g;
import Q7.Q;
import S0.B;
import S0.C0691c;
import S5.h;
import S8.AbstractC0727y;
import S8.C0720q;
import T.C0735d;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import T.InterfaceC0744h0;
import T.Q0;
import T.S;
import T.X;
import V8.N;
import V8.f0;
import W7.AbstractC0839c;
import X5.C0857f;
import X5.D0;
import Y0.i;
import a.AbstractC0904a;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import androidx.compose.foundation.layout.HorizontalAlignElement;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import b0.g;
import com.stingray.drivemodedetector.DriveMode;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.car.player.SongQueuePosition;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import com.stingray.karaoke.car.utils.FontSize;
import com.stingray.karaoke.car.utils.LayoutMode;
import com.stingray.karaoke.car.utils.ToastType;
import com.stingray.kca.model.KaraokeItem;
import com.stingray.kca.model.KaraokeItemType;
import com.stingray.kca.model.KaraokeSongResponseV2;
import com.stingray.shared.logs.segment.songQueue.SourceType;
import d6.C1104a;
import d6.C1105b;
import d6.C1106c;
import d6.C1107d;
import d6.InterfaceC1108e;
import d6.j;
import f7.AbstractC1264a;
import g0.l;
import g0.o;
import g1.AbstractC1274a;
import g1.C1283j;
import g1.C1285l;
import g1.p;
import h2.AbstractC1347d;
import h2.t;
import h6.f;
import h6.r;
import h8.AbstractC1386c;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import k6.AbstractC1692D;
import k6.C1701i;
import m7.q;
import n0.AbstractC1861K;
import n0.C1891t;
import n0.InterfaceC1866P;
import n6.C1966G;
import n7.AbstractC1996k;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.AbstractC2000o;
import n7.z;
import o8.C2057b;
import o8.C2058c;
import o8.C2061f;
import org.xmlpull.v1.XmlPullParserException;
import r0.AbstractC2268a;
import r7.EnumC2303a;
import w8.EnumC2609d;
import x.AbstractC2631e;
import x.AbstractC2637h;
import x3.AbstractC2673a;
import y.C2716v;
import y.s0;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f16507a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f16508b = 0;

    /* JADX WARN: Multi-variable type inference failed */
    public static final void A(o oVar, InterfaceC2782k interfaceC2782k, DriveMode driveMode, C0761q c0761q, int i7) {
        int i10;
        boolean z6;
        boolean z10;
        boolean z11;
        c0761q.S(44434583);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(interfaceC2782k) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.g(false) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.f(driveMode) ? 2048 : 1024;
        }
        int i11 = i10;
        if ((i11 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
        } else {
            K kE = AbstractC0133o.e(g0.b.l, false);
            int i12 = c0761q.f8304P;
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
            C0158h c0158h = C0160j.f1679e;
            C0735d.V(c0761q, kE, c0158h);
            C0158h c0158h2 = C0160j.d;
            C0735d.V(c0761q, interfaceC0744h0M, c0158h2);
            C0158h c0158h3 = C0160j.f1680f;
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i12))) {
                AbstractC1347d.l(i12, c0761q, i12, c0158h3);
            }
            C0158h c0158h4 = C0160j.c;
            C0735d.V(c0761q, oVarD, c0158h4);
            l lVar = l.f14684a;
            m6.b bVar = m6.c.f19252a;
            bVar.getClass();
            c0761q.Q(-1786434816);
            float f10 = 400;
            LayoutMode layoutMode = (LayoutMode) c0761q.k(AbstractC1692D.f18238a);
            if (layoutMode == LayoutMode.NARROW_COMPACT || layoutMode == LayoutMode.WIDE_COMPACT || layoutMode == LayoutMode.WIDE_NORMAL || layoutMode == LayoutMode.WIDE_ULTRA || layoutMode == LayoutMode.NARROW_ULTRA || layoutMode == LayoutMode.SQUARE_ULTRA) {
                z6 = false;
            } else {
                if (layoutMode != LayoutMode.SQUARE_COMPACT && layoutMode != LayoutMode.NARROW_NORMAL && layoutMode != LayoutMode.SQUARE_NORMAL) {
                    throw new k();
                }
                z6 = false;
                if (!M5.b.r(c0761q, -609592150, bVar, c0761q, false)) {
                    c0761q.Q(-609591093);
                    m6.b.j(bVar, c0761q);
                    c0761q.p(false);
                }
            }
            c0761q.p(z6);
            o oVarQ = androidx.compose.foundation.layout.c.q(lVar, 0.0f, f10, 1);
            g0.e eVar = g0.b.f14673y;
            C0119a c0119a = AbstractC0127i.f1076a;
            C0141x c0141xA = AbstractC0139v.a(new C0124f(bVar.H(6, c0761q)), eVar, c0761q, 48);
            int i13 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M2 = c0761q.m();
            o oVarD2 = g0.a.d(c0761q, oVarQ);
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, c0141xA, c0158h);
            C0735d.V(c0761q, interfaceC0744h0M2, c0158h2);
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i13))) {
                AbstractC1347d.l(i13, c0761q, i13, c0158h3);
            }
            C0735d.V(c0761q, oVarD2, c0158h4);
            S s6 = C0753m.f8270a;
            c0761q.Q(-1542639659);
            o oVarD3 = androidx.compose.foundation.layout.c.d(lVar, 1.0f);
            Integer numValueOf = Integer.valueOf(R.drawable.ic_sing_now);
            String strX = AbstractC2673a.X(R.string.Global_SingNow, c0761q);
            c0761q.Q(-1435228496);
            boolean z12 = (i11 & 112) == 32;
            Object objG = c0761q.G();
            if (z12 || objG == s6) {
                z10 = false;
                objG = new G1(0 == true ? 1 : 0, interfaceC2782k);
                c0761q.a0(objG);
            } else {
                z10 = false;
            }
            c0761q.p(z10);
            p(oVarD3, numValueOf, false, strX, 0L, null, null, 0L, 0.0f, (InterfaceC2772a) objG, c0761q, 6, 500);
            c0761q.p(false);
            o oVarD4 = androidx.compose.foundation.layout.c.d(lVar, 1.0f);
            Integer numValueOf2 = Integer.valueOf(R.drawable.ic_add);
            String strX2 = AbstractC2673a.X(R.string.Global_SingLast, c0761q);
            c0761q.Q(-1435219031);
            boolean z13 = (i11 & 112) == 32;
            Object objG2 = c0761q.G();
            if (z13 || objG2 == s6) {
                z11 = true;
                objG2 = new G1(1 == true ? 1 : 0, interfaceC2782k);
                c0761q.a0(objG2);
            } else {
                z11 = true;
            }
            c0761q.p(false);
            o(oVarD4, numValueOf2, false, strX2, 0L, null, false, null, null, 0L, 0.0f, (InterfaceC2772a) objG2, c0761q, 6, 0, 2036);
            c0761q.p(z11);
            c0761q.p(z11);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C1(oVar, interfaceC2782k, driveMode, i7, 1);
        }
    }

    public static final void B(o oVar, Integer num, boolean z6, final String str, final long j3, long j10, InterfaceC0123e interfaceC0123e, boolean z10, L l, N0.L l10, final InterfaceC2772a interfaceC2772a, C0761q c0761q, final int i7, final int i10) {
        int i11;
        int i12;
        L lA;
        N0.L l11;
        Integer num2;
        long j11;
        InterfaceC0123e interfaceC0123e2;
        boolean z11;
        boolean z12;
        o oVar2;
        final o oVar3;
        final Integer num3;
        final boolean z13;
        final long j12;
        final InterfaceC0123e interfaceC0123e3;
        final boolean z14;
        final L l12;
        final N0.L l13;
        m.f(str, "text");
        m.f(interfaceC2772a, "onClick");
        c0761q.S(376472784);
        int i13 = i7 | 438;
        if ((i7 & 3072) == 0) {
            i13 |= c0761q.f(str) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i13 |= c0761q.e(j3) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i13 |= 65536;
        }
        int i14 = 14155776 | i13;
        if ((100663296 & i7) == 0) {
            i14 = 47710208 | i13;
        }
        if ((805306368 & i7) == 0) {
            i14 |= 268435456;
        }
        if ((i10 & 6) == 0) {
            i11 = i10 | (c0761q.h(interfaceC2772a) ? 4 : 2);
        } else {
            i11 = i10;
        }
        if ((306783379 & i14) == 306783378 && (i11 & 3) == 2 && c0761q.x()) {
            c0761q.K();
            oVar3 = oVar;
            num3 = num;
            z13 = z6;
            j12 = j10;
            interfaceC0123e3 = interfaceC0123e;
            z14 = z10;
            l12 = l;
            l13 = l10;
        } else {
            c0761q.M();
            if ((i7 & 1) == 0 || c0761q.w()) {
                l lVar = l.f14684a;
                long jG = f.g(FontSize.Subtitle1, c0761q);
                C0122d c0122d = AbstractC0127i.d;
                m6.b bVar = m6.c.f19252a;
                bVar.getClass();
                c0761q.Q(-19348114);
                float f10 = 12;
                float f11 = 18;
                float f12 = 24;
                switch (m6.a.f19251a[((LayoutMode) c0761q.k(AbstractC1692D.f18238a)).ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                        c0761q.Q(-2036471007);
                        c0761q.p(false);
                        break;
                    case 4:
                        c0761q.Q(-2036469598);
                        c0761q.p(false);
                        f10 = f11;
                        break;
                    case 5:
                    case 6:
                        c0761q.Q(-2036466911);
                        c0761q.p(false);
                        f10 = f12;
                        break;
                    case 7:
                        c0761q.Q(-2036465437);
                        f10 = m6.b.i(bVar, c0761q) ? f11 : f12;
                        c0761q.p(false);
                        break;
                    case 8:
                        c0761q.Q(-2036462878);
                        f10 = m6.b.k(bVar, c0761q) ? f11 : f12;
                        c0761q.p(false);
                        break;
                    case 9:
                        c0761q.Q(-2036460330);
                        c0761q.Q(-2036460225);
                        boolean z15 = m6.b.k(bVar, c0761q) || m6.b.i(bVar, c0761q);
                        c0761q.p(false);
                        f10 = z15 ? f11 : f12;
                        c0761q.p(false);
                        break;
                    default:
                        throw M5.b.d(-2036475262, c0761q, false);
                }
                c0761q.p(false);
                i12 = i14 & (-2114387969);
                lA = androidx.compose.foundation.layout.a.a(f10, 2);
                l11 = ((O5.d) c0761q.k(O5.c.f5157b)).f5159b;
                num2 = null;
                j11 = jG;
                interfaceC0123e2 = c0122d;
                z11 = true;
                z12 = true;
                oVar2 = lVar;
            } else {
                c0761q.K();
                i12 = i14 & (-2114387969);
                oVar2 = oVar;
                num2 = num;
                z11 = z6;
                j11 = j10;
                interfaceC0123e2 = interfaceC0123e;
                z12 = z10;
                lA = l;
                l11 = l10;
            }
            c0761q.q();
            int i15 = 65534 & i12;
            int i16 = i12 >> 3;
            o(oVar2, num2, z11, str, j3, interfaceC0123e2, z12, lA, l11, j11, m6.c.f19252a.o0(c0761q), interfaceC2772a, c0761q, i15 | (458752 & i16) | (i16 & 3670016), (i11 << 3) & 112, 0);
            oVar3 = oVar2;
            num3 = num2;
            z13 = z11;
            j12 = j11;
            interfaceC0123e3 = interfaceC0123e2;
            z14 = z12;
            l12 = lA;
            l13 = l11;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: Q5.j
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i7 | 1);
                    int iZ2 = C0735d.Z(i10);
                    N0.L l14 = l13;
                    InterfaceC2772a interfaceC2772a2 = interfaceC2772a;
                    io.sentry.config.a.B(oVar3, num3, z13, str, j3, j12, interfaceC0123e3, z14, l12, l14, interfaceC2772a2, (C0761q) obj, iZ, iZ2);
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static final void C(int i7, int i10, L l, C0761q c0761q, DriveMode driveMode, LoadingInterface$LoadingState loadingInterface$LoadingState, KaraokeSongResponseV2 karaokeSongResponseV2, o oVar, List list, C1966G c1966g, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, InterfaceC2782k interfaceC2782k, InterfaceC2785n interfaceC2785n, boolean z6) {
        int i11;
        o oVar2;
        c0761q.S(820857742);
        int i12 = i7 | 6;
        if ((i7 & 48) == 0) {
            i12 |= c0761q.h(karaokeSongResponseV2) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i12 |= (i7 & 512) == 0 ? c0761q.f(c1966g) : c0761q.h(c1966g) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i12 |= c0761q.h(list) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i12 |= c0761q.f(loadingInterface$LoadingState) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i12 |= c0761q.h(interfaceC2772a) ? 131072 : 65536;
        }
        if ((1572864 & i7) == 0) {
            i12 |= c0761q.h(interfaceC2785n) ? 1048576 : 524288;
        }
        if ((12582912 & i7) == 0) {
            i12 |= c0761q.g(z6) ? 8388608 : 4194304;
        }
        if ((100663296 & i7) == 0) {
            i12 |= c0761q.h(interfaceC2782k) ? 67108864 : 33554432;
        }
        if ((805306368 & i7) == 0) {
            i12 |= c0761q.h(interfaceC2772a2) ? 536870912 : 268435456;
        }
        if ((i10 & 6) == 0) {
            i11 = i10 | (c0761q.f(driveMode) ? 4 : 2);
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= c0761q.f(l) ? 32 : 16;
        }
        if ((306783379 & i12) == 306783378 && (i11 & 19) == 18 && c0761q.x()) {
            c0761q.K();
            oVar2 = oVar;
        } else {
            l lVar = l.f14684a;
            int i13 = Y1.f6659a[((LayoutMode) c0761q.k(AbstractC1692D.f18238a)).ordinal()];
            if (i13 == 1) {
                c0761q.Q(1816439627);
                int i14 = i12 >> 18;
                int i15 = (i12 & 14) | ((i12 >> 15) & 112) | (i14 & 896) | (i14 & 7168);
                int i16 = i12 << 9;
                E(((i12 << 6) & 1879048192) | i15 | (i16 & 57344) | 262144 | (i16 & 458752) | (i16 & 3670016) | (i16 & 29360128) | (i16 & 234881024), i11 & 126, l, c0761q, driveMode, loadingInterface$LoadingState, karaokeSongResponseV2, lVar, list, c1966g, interfaceC2772a2, interfaceC2772a, interfaceC2782k, interfaceC2785n, z6);
                c0761q.p(false);
            } else if (i13 == 2 || i13 == 3) {
                c0761q.Q(1817112885);
                int i17 = i12 >> 18;
                int i18 = (i12 & 14) | ((i12 >> 15) & 112) | (i17 & 896) | (i17 & 7168);
                int i19 = i12 << 9;
                F(((i12 << 6) & 1879048192) | i18 | (i19 & 57344) | 262144 | (i19 & 458752) | (i19 & 3670016) | (i19 & 29360128) | (i19 & 234881024), i11 & 126, l, c0761q, driveMode, loadingInterface$LoadingState, karaokeSongResponseV2, lVar, list, c1966g, interfaceC2772a2, interfaceC2772a, interfaceC2782k, interfaceC2785n, z6);
                c0761q.p(false);
            } else {
                c0761q.Q(1817728948);
                int i20 = i12 >> 18;
                int i21 = i12 << 9;
                D(((i12 << 6) & 1879048192) | (i12 & 14) | ((i12 >> 15) & 112) | (i20 & 896) | (i20 & 7168) | (i21 & 57344) | 262144 | (i21 & 458752) | (i21 & 3670016) | (i21 & 29360128) | (i21 & 234881024), i11 & 126, l, c0761q, driveMode, loadingInterface$LoadingState, karaokeSongResponseV2, lVar, list, c1966g, interfaceC2772a2, interfaceC2772a, interfaceC2782k, interfaceC2785n, z6);
                c0761q.p(false);
            }
            oVar2 = lVar;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new D1(oVar2, karaokeSongResponseV2, c1966g, list, loadingInterface$LoadingState, interfaceC2772a, interfaceC2785n, z6, interfaceC2782k, interfaceC2772a2, driveMode, l, i7, i10);
        }
    }

    public static final void D(int i7, int i10, L l, C0761q c0761q, DriveMode driveMode, LoadingInterface$LoadingState loadingInterface$LoadingState, KaraokeSongResponseV2 karaokeSongResponseV2, o oVar, List list, C1966G c1966g, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, InterfaceC2782k interfaceC2782k, InterfaceC2785n interfaceC2785n, boolean z6) {
        int i11;
        int i12;
        c0761q.S(1083030864);
        if ((i7 & 6) == 0) {
            i11 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i11 = i7;
        }
        if ((i7 & 48) == 0) {
            i11 |= c0761q.h(interfaceC2785n) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i11 |= c0761q.h(interfaceC2782k) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i11 |= c0761q.h(interfaceC2772a) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i11 |= c0761q.h(karaokeSongResponseV2) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i11 |= (262144 & i7) == 0 ? c0761q.f(c1966g) : c0761q.h(c1966g) ? 131072 : 65536;
        }
        if ((1572864 & i7) == 0) {
            i11 |= c0761q.h(list) ? 1048576 : 524288;
        }
        if ((12582912 & i7) == 0) {
            i11 |= c0761q.f(loadingInterface$LoadingState) ? 8388608 : 4194304;
        }
        if ((100663296 & i7) == 0) {
            i11 |= c0761q.h(interfaceC2772a2) ? 67108864 : 33554432;
        }
        if ((i7 & 805306368) == 0) {
            i11 |= c0761q.g(z6) ? 536870912 : 268435456;
        }
        if ((i10 & 6) == 0) {
            i12 = i10 | (c0761q.f(driveMode) ? 4 : 2);
        } else {
            i12 = i10;
        }
        if ((i10 & 48) == 0) {
            i12 |= c0761q.f(l) ? 32 : 16;
        }
        if ((i11 & 306783379) == 306783378 && (i12 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            AbstractC0120b.a(androidx.compose.foundation.layout.c.c(oVar, 1.0f), null, false, g.b(520848954, new Q1(l, driveMode, loadingInterface$LoadingState, karaokeSongResponseV2, list, c1966g, interfaceC2772a2, interfaceC2772a, interfaceC2782k, interfaceC2785n, z6), c0761q), c0761q, 3072, 6);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new D1(oVar, interfaceC2785n, interfaceC2782k, interfaceC2772a, karaokeSongResponseV2, c1966g, list, loadingInterface$LoadingState, interfaceC2772a2, z6, driveMode, l, i7, i10, 2);
        }
    }

    public static final void E(int i7, int i10, L l, C0761q c0761q, DriveMode driveMode, LoadingInterface$LoadingState loadingInterface$LoadingState, KaraokeSongResponseV2 karaokeSongResponseV2, o oVar, List list, C1966G c1966g, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, InterfaceC2782k interfaceC2782k, InterfaceC2785n interfaceC2785n, boolean z6) {
        int i11;
        int i12;
        int i13;
        int i14;
        C1285l c1285l;
        X x8;
        c0761q.S(-1509667113);
        if ((i7 & 6) == 0) {
            i11 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i11 = i7;
        }
        if ((i7 & 48) == 0) {
            i11 |= c0761q.h(interfaceC2785n) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i11 |= c0761q.h(interfaceC2782k) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i11 |= c0761q.h(interfaceC2772a) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i11 |= c0761q.h(karaokeSongResponseV2) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i11 |= (262144 & i7) == 0 ? c0761q.f(c1966g) : c0761q.h(c1966g) ? 131072 : 65536;
        }
        if ((1572864 & i7) == 0) {
            i11 |= c0761q.h(list) ? 1048576 : 524288;
        }
        if ((i7 & 12582912) == 0) {
            i11 |= c0761q.f(loadingInterface$LoadingState) ? 8388608 : 4194304;
        }
        if ((i7 & 100663296) == 0) {
            i11 |= c0761q.h(interfaceC2772a2) ? 67108864 : 33554432;
        }
        if ((i7 & 805306368) == 0) {
            i11 |= c0761q.g(z6) ? 536870912 : 268435456;
        }
        if ((i10 & 6) == 0) {
            i12 = i10 | (c0761q.f(driveMode) ? 4 : 2);
        } else {
            i12 = i10;
        }
        if ((i10 & 48) == 0) {
            i12 |= c0761q.f(l) ? 32 : 16;
        }
        if ((306783379 & i11) == 306783378 && (i12 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            o oVarO = androidx.compose.foundation.layout.a.o(oVar, AbstractC2673a.J(l, c0761q), AbstractC2673a.K(l, c0761q), AbstractC2673a.I(l, c0761q), 0.0f, 8);
            C0119a c0119a = AbstractC0127i.f1076a;
            m6.b bVar = m6.c.f19252a;
            D.S sB = P.b(new C0124f(bVar.E0(c0761q)), g0.b.f14669u, c0761q, 0);
            int i15 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            o oVarD = g0.a.d(c0761q, oVarO);
            InterfaceC0161k.f1681b.getClass();
            InterfaceC2772a interfaceC2772a3 = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(interfaceC2772a3);
            } else {
                c0761q.d0();
            }
            C0158h c0158h = C0160j.f1679e;
            C0735d.V(c0761q, sB, c0158h);
            C0158h c0158h2 = C0160j.d;
            C0735d.V(c0761q, interfaceC0744h0M, c0158h2);
            C0158h c0158h3 = C0160j.f1680f;
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i15))) {
                AbstractC1347d.l(i15, c0761q, i15, c0158h3);
            }
            C0158h c0158h4 = C0160j.c;
            C0735d.V(c0761q, oVarD, c0158h4);
            l lVar = l.f14684a;
            o oVarO2 = androidx.compose.foundation.layout.a.o(androidx.compose.foundation.layout.c.d(r.t(androidx.compose.foundation.layout.c.b(lVar, 1.0f), r.q(c0761q)), 0.667f), 0.0f, bVar.F0(c0761q), 0.0f, AbstractC2673a.H(l, c0761q), 5);
            C0141x c0141xA = AbstractC0139v.a(new C0124f(bVar.r0(c0761q)), g0.b.f14672x, c0761q, 0);
            int i16 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M2 = c0761q.m();
            o oVarD2 = g0.a.d(c0761q, oVarO2);
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(interfaceC2772a3);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, c0141xA, c0158h);
            C0735d.V(c0761q, interfaceC0744h0M2, c0158h2);
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i16))) {
                AbstractC1347d.l(i16, c0761q, i16, c0158h3);
            }
            C0735d.V(c0761q, oVarD2, c0158h4);
            o oVarR = androidx.compose.foundation.layout.c.r(androidx.compose.foundation.layout.c.d(lVar, 1.0f));
            c0761q.Q(-1003410150);
            c0761q.Q(212064437);
            c0761q.p(false);
            a1.b bVar2 = (a1.b) c0761q.k(AbstractC0227s0.f2259f);
            Object objG = c0761q.G();
            Object obj = C0753m.f8270a;
            if (objG == obj) {
                objG = new p(bVar2);
                c0761q.a0(objG);
            }
            p pVar = (p) objG;
            Object objG2 = c0761q.G();
            if (objG2 == obj) {
                objG2 = new C1283j();
                c0761q.a0(objG2);
            }
            C1283j c1283j = (C1283j) objG2;
            Object objG3 = c0761q.G();
            if (objG3 == obj) {
                objG3 = C0735d.O(Boolean.FALSE, S.f8224q);
                c0761q.a0(objG3);
            }
            X x9 = (X) objG3;
            Object objG4 = c0761q.G();
            if (objG4 == obj) {
                objG4 = new C1285l(c1283j);
                c0761q.a0(objG4);
            }
            C1285l c1285l2 = (C1285l) objG4;
            Object objG5 = c0761q.G();
            if (objG5 == obj) {
                objG5 = C0735d.O(q.f19267a, S.f8222n);
                c0761q.a0(objG5);
            }
            X x10 = (X) objG5;
            boolean zH = c0761q.h(pVar) | c0761q.d(257);
            Object objG6 = c0761q.G();
            if (zH || objG6 == obj) {
                i13 = i12;
                i14 = i11;
                c1285l = c1285l2;
                x8 = x9;
                Object m12 = new M1(x10, pVar, c1285l2, x9, 1);
                c0761q.a0(m12);
                objG6 = m12;
            } else {
                i14 = i11;
                i13 = i12;
                c1285l = c1285l2;
                x8 = x9;
            }
            K k10 = (K) objG6;
            Object objG7 = c0761q.G();
            if (objG7 == obj) {
                objG7 = new N1(x8, c1285l, 1);
                c0761q.a0(objG7);
            }
            InterfaceC2772a interfaceC2772a4 = (InterfaceC2772a) objG7;
            boolean zH2 = c0761q.h(pVar);
            Object objG8 = c0761q.G();
            if (zH2 || objG8 == obj) {
                objG8 = new O1(pVar, 1);
                c0761q.a0(objG8);
            }
            c0.a(L0.k.a(oVarR, false, (InterfaceC2782k) objG8), g.b(1200550679, new T1(x10, c1283j, interfaceC2772a4, karaokeSongResponseV2, c1966g, z6, interfaceC2772a), c0761q), k10, c0761q, 48);
            c0761q.p(false);
            o oVarR2 = androidx.compose.foundation.layout.c.r(androidx.compose.foundation.layout.c.q(lVar, 0.0f, bVar.q0(c0761q), 1));
            if (karaokeSongResponseV2 != null) {
                m.a(karaokeSongResponseV2.f13146n, Boolean.TRUE);
            }
            z(oVarR2, interfaceC2782k, driveMode, c0761q, ((i14 >> 3) & 112) | 384 | ((i13 << 12) & 57344));
            c0761q.p(true);
            AbstractC0120b.a(androidx.compose.foundation.layout.a.o(androidx.compose.foundation.layout.c.c(r.t(lVar, r.q(c0761q)), 1.0f), 0.0f, 0.0f, 0.0f, AbstractC2673a.H(l, c0761q), 7), null, false, g.b(-1300260591, new S1(list, interfaceC2785n, loadingInterface$LoadingState, interfaceC2772a2, karaokeSongResponseV2), c0761q), c0761q, 3072, 6);
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new D1(oVar, interfaceC2785n, interfaceC2782k, interfaceC2772a, karaokeSongResponseV2, c1966g, list, loadingInterface$LoadingState, interfaceC2772a2, z6, driveMode, l, i7, i10, 3);
        }
    }

    public static final void F(int i7, int i10, L l, C0761q c0761q, DriveMode driveMode, LoadingInterface$LoadingState loadingInterface$LoadingState, KaraokeSongResponseV2 karaokeSongResponseV2, o oVar, List list, C1966G c1966g, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, InterfaceC2782k interfaceC2782k, InterfaceC2785n interfaceC2785n, boolean z6) {
        int i11;
        int i12;
        c0761q.S(-1558907506);
        if ((i7 & 6) == 0) {
            i11 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i11 = i7;
        }
        if ((i7 & 48) == 0) {
            i11 |= c0761q.h(interfaceC2785n) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i11 |= c0761q.h(interfaceC2782k) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i11 |= c0761q.h(interfaceC2772a) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i11 |= c0761q.h(karaokeSongResponseV2) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i11 |= (262144 & i7) == 0 ? c0761q.f(c1966g) : c0761q.h(c1966g) ? 131072 : 65536;
        }
        if ((1572864 & i7) == 0) {
            i11 |= c0761q.h(list) ? 1048576 : 524288;
        }
        if ((12582912 & i7) == 0) {
            i11 |= c0761q.f(loadingInterface$LoadingState) ? 8388608 : 4194304;
        }
        if ((100663296 & i7) == 0) {
            i11 |= c0761q.h(interfaceC2772a2) ? 67108864 : 33554432;
        }
        if ((i7 & 805306368) == 0) {
            i11 |= c0761q.g(z6) ? 536870912 : 268435456;
        }
        if ((i10 & 6) == 0) {
            i12 = i10 | (c0761q.f(driveMode) ? 4 : 2);
        } else {
            i12 = i10;
        }
        if ((i10 & 48) == 0) {
            i12 |= c0761q.f(l) ? 32 : 16;
        }
        if ((i11 & 306783379) == 306783378 && (i12 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            AbstractC0120b.a(androidx.compose.foundation.layout.c.c(oVar, 1.0f), null, false, g.b(916809572, new U1(l, driveMode, loadingInterface$LoadingState, karaokeSongResponseV2, list, c1966g, interfaceC2772a2, interfaceC2772a, interfaceC2782k, interfaceC2785n, z6), c0761q), c0761q, 3072, 6);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new D1(oVar, interfaceC2785n, interfaceC2782k, interfaceC2772a, karaokeSongResponseV2, c1966g, list, loadingInterface$LoadingState, interfaceC2772a2, z6, driveMode, l, i7, i10, 1);
        }
    }

    public static final void G(j jVar, boolean z6, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(-2072853951);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(jVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if (((i10 | 48) & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            AbstractC0904a.l(null, new C0607p1(jVar, null, true, new C1701i(0.9f, 5), 58), g.b(2068576383, new y(jVar, 10), c0761q), c0761q, 384, 1);
            z6 = false;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new H1(jVar, z6, i7, 0);
        }
    }

    public static final void H(String str, C0761q c0761q, int i7) {
        int i10;
        m.f(str, "id");
        c0761q.S(-1814591159);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(str) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i10 & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            AbstractC1274a.h(str, true, false, false, false, g.b(1673115362, new S5.k(str, 2), c0761q), c0761q, (i10 & 14) | 196656, 28);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new h(str, i7, 0);
        }
    }

    public static final void I(SongQueuePosition songQueuePosition, Context context, boolean z6, C0221p c0221p, C0221p c0221p2, C0221p c0221p3, X1 x12, KaraokeSongResponseV2 karaokeSongResponseV2, F6.b bVar, f0 f0Var) {
        m.c(karaokeSongResponseV2);
        InterfaceC1108e interfaceC1108e = (InterfaceC1108e) x12.o(karaokeSongResponseV2, songQueuePosition, Boolean.FALSE, bVar);
        if (interfaceC1108e instanceof C1104a) {
            if (songQueuePosition == SongQueuePosition.SING_NOW || ((C1104a) interfaceC1108e).f13466a || songQueuePosition == SongQueuePosition.SCORE_NOW) {
                c0221p2.a();
                c0221p3.a();
                return;
            } else {
                ToastType toastType = ToastType.ADD_TO_QUEUE;
                m.f(toastType, "toastType");
                h6.e.c(h6.e.d(toastType), new b0.b(-1064300120, new C0618s1(toastType, 2), true));
                return;
            }
        }
        if (interfaceC1108e instanceof C1105b) {
            z.b(null, z6, new b0.b(-499896941, new U(x12, karaokeSongResponseV2, songQueuePosition, bVar, 1), true), 7);
        } else if (interfaceC1108e instanceof C1106c) {
            z.b(null, z6, new b0.b(1010124884, new U1(songQueuePosition, context, z6, c0221p, c0221p2, c0221p3, x12, karaokeSongResponseV2, bVar, f0Var), true), 7);
        } else {
            if (!(interfaceC1108e instanceof C1107d)) {
                throw new k();
            }
            z.b(null, z6, new b0.b(-1774820587, new y(f0Var, 11), true), 7);
        }
    }

    public static final int J(int i7) {
        if (i7 < 8191) {
            return 262142;
        }
        if (i7 < 32767) {
            return 65534;
        }
        if (i7 < 65535) {
            return 32766;
        }
        if (i7 < 262143) {
            return 8190;
        }
        throw new IllegalArgumentException(A7.k.g("Can't represent a size of ", i7, " in Constraints"));
    }

    public static void K(StringBuilder sb, Object obj, InterfaceC2782k interfaceC2782k) {
        m.f(sb, "<this>");
        if (interfaceC2782k != null) {
            sb.append((CharSequence) interfaceC2782k.b(obj));
            return;
        }
        if (obj == null ? true : obj instanceof CharSequence) {
            sb.append((CharSequence) obj);
        } else if (obj instanceof Character) {
            sb.append(((Character) obj).charValue());
        } else {
            sb.append((CharSequence) obj.toString());
        }
    }

    public static final int L(int i7) {
        if (i7 < 8191) {
            return 13;
        }
        if (i7 < 32767) {
            return 15;
        }
        if (i7 < 65535) {
            return 16;
        }
        return i7 < 262143 ? 18 : 255;
    }

    public static final String M(String str) {
        char cCharAt;
        m.f(str, "<this>");
        if (str.length() == 0 || 'a' > (cCharAt = str.charAt(0)) || cCharAt >= '{') {
            return str;
        }
        char upperCase = Character.toUpperCase(cCharAt);
        String strSubstring = str.substring(1);
        m.e(strSubstring, "this as java.lang.String).substring(startIndex)");
        return upperCase + strSubstring;
    }

    public static int N(int i7, int i10) {
        long j3 = ((long) i7) + ((long) i10);
        int i11 = (int) j3;
        if (j3 == ((long) i11)) {
            return i11;
        }
        throw new ArithmeticException("overflow: checkedAdd(" + i7 + ", " + i10 + ")");
    }

    /* JADX WARN: Type inference failed for: r3v12, types: [java.lang.Object, m7.f] */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.lang.Object, m7.f] */
    public static t8.f O(Class cls) {
        int i7 = 0;
        while (cls.isArray()) {
            i7++;
            cls = cls.getComponentType();
            m.e(cls, "currentClass.componentType");
        }
        if (cls.isPrimitive()) {
            if (cls.equals(Void.TYPE)) {
                return new t8.f(C2057b.j(n.d.g()), i7);
            }
            N7.k kVarD = EnumC2609d.b(cls.getName()).d();
            m.e(kVarD, "get(currentClass.name).primitiveType");
            return i7 > 0 ? new t8.f(C2057b.j((C2058c) kVarD.o.getValue()), i7 - 1) : new t8.f(C2057b.j((C2058c) kVarD.f4812n.getValue()), i7);
        }
        C2057b c2057bA = AbstractC0839c.a(cls);
        String str = P7.d.f5417a;
        C2057b c2057b = (C2057b) P7.d.f5422h.get(c2057bA.b().i());
        if (c2057b != null) {
            c2057bA = c2057b;
        }
        return new t8.f(c2057bA, i7);
    }

    public static final long P(long j3, long j10) {
        return H.c(AbstractC2673a.v((int) (j10 >> 32), a1.a.j(j3), a1.a.h(j3)), AbstractC2673a.v((int) (j10 & 4294967295L), a1.a.i(j3), a1.a.g(j3)));
    }

    public static final long Q(long j3, long j10) {
        return e(AbstractC2673a.v(a1.a.j(j10), a1.a.j(j3), a1.a.h(j3)), AbstractC2673a.v(a1.a.h(j10), a1.a.j(j3), a1.a.h(j3)), AbstractC2673a.v(a1.a.i(j10), a1.a.i(j3), a1.a.g(j3)), AbstractC2673a.v(a1.a.g(j10), a1.a.i(j3), a1.a.g(j3)));
    }

    public static final int R(long j3, int i7) {
        return AbstractC2673a.v(i7, a1.a.i(j3), a1.a.g(j3));
    }

    public static final int S(long j3, int i7) {
        return AbstractC2673a.v(i7, a1.a.j(j3), a1.a.h(j3));
    }

    public static final Object T(Class cls, Map map, List list) {
        m.f(cls, "annotationClass");
        m.f(list, "methods");
        m7.n nVarO = r.o(new K7.K(map, 3));
        Object objNewProxyInstance = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new L7.b(cls, map, r.o(new A(cls, 17, map)), nVarO, list));
        m.d(objNewProxyInstance, "null cannot be cast to non-null type T of kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt.createAnnotationInstance");
        return objNewProxyInstance;
    }

    public static final long U(int i7, int i10, int i11, int i12) {
        int i13 = i12 == Integer.MAX_VALUE ? i11 : i12;
        int iL = L(i13);
        int i14 = i10 == Integer.MAX_VALUE ? i7 : i10;
        int iL2 = L(i14);
        if (iL + iL2 > 31) {
            throw new IllegalArgumentException("Can't represent a width of " + i14 + " and height of " + i13 + " in Constraints");
        }
        int i15 = i10 + 1;
        int i16 = i15 & (~(i15 >> 31));
        int i17 = i12 + 1;
        int i18 = i17 & (~(i17 >> 31));
        int i19 = 0;
        if (iL2 != 13) {
            if (iL2 == 18) {
                i19 = 3;
            } else if (iL2 == 15) {
                i19 = 1;
            } else if (iL2 == 16) {
                i19 = 2;
            }
        }
        int i20 = (((i19 & 2) >> 1) * 3) + ((i19 & 1) << 1);
        return (((long) i16) << 33) | ((long) i19) | (((long) i7) << 2) | (((long) i11) << (i20 + 15)) | (((long) i18) << (i20 + 46));
    }

    public static final S0.q V(Context context) {
        return new S0.q(new P6.a(context, 1), new C0691c(Build.VERSION.SDK_INT >= 31 ? B.f7806a.a(context) : 0));
    }

    public static final o0 W(InterfaceC0318c interfaceC0318c, List list, boolean z6, List list2) {
        InterfaceC0654g interfaceC0654gE;
        F8.H h10;
        O f10;
        m.f(interfaceC0318c, "<this>");
        m.f(list, "arguments");
        m.f(list2, "annotations");
        K7.A a10 = interfaceC0318c instanceof K7.A ? (K7.A) interfaceC0318c : null;
        if (a10 == null || (interfaceC0654gE = a10.e()) == null) {
            throw new r0("Cannot create type for an unsupported classifier: " + interfaceC0318c + " (" + interfaceC0318c.getClass() + ')');
        }
        F8.K kC = interfaceC0654gE.C();
        m.e(kC, "descriptor.typeConstructor");
        List listP = kC.p();
        m.e(listP, "typeConstructor.parameters");
        if (listP.size() != list.size()) {
            throw new IllegalArgumentException("Class declares " + listP.size() + " type parameters, but " + list.size() + " were provided.");
        }
        if (list2.isEmpty()) {
            F8.H.f2437m.getClass();
            h10 = F8.H.f2438n;
        } else {
            F8.H.f2437m.getClass();
            h10 = F8.H.f2438n;
        }
        List listP2 = kC.p();
        m.e(listP2, "typeConstructor.parameters");
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(list, 10));
        int i7 = 0;
        for (Object obj : list) {
            int i10 = i7 + 1;
            if (i7 < 0) {
                AbstractC1999n.I();
                throw null;
            }
            H7.A a11 = (H7.A) obj;
            o0 o0Var = (o0) a11.f2840b;
            AbstractC0287w abstractC0287w = o0Var != null ? o0Var.f3824a : null;
            H7.B b10 = a11.f2839a;
            int i11 = b10 == null ? -1 : I7.b.f3036a[b10.ordinal()];
            if (i11 == -1) {
                Object obj2 = listP2.get(i7);
                m.e(obj2, "parameters[index]");
                f10 = new F((Q) obj2);
            } else if (i11 == 1) {
                m.c(abstractC0287w);
                f10 = new F8.P(1, abstractC0287w);
            } else if (i11 == 2) {
                m.c(abstractC0287w);
                f10 = new F8.P(2, abstractC0287w);
            } else {
                if (i11 != 3) {
                    throw new k();
                }
                m.c(abstractC0287w);
                f10 = new F8.P(3, abstractC0287w);
            }
            arrayList.add(f10);
            i7 = i10;
        }
        return new o0(C0269d.r(h10, kC, arrayList, z6), null);
    }

    public static final void X(r9.o oVar, r9.z zVar) throws IOException {
        try {
            IOException iOException = null;
            for (r9.z zVar2 : oVar.f(zVar)) {
                try {
                    if (oVar.g(zVar2).c) {
                        X(oVar, zVar2);
                    }
                    oVar.c(zVar2);
                } catch (IOException e10) {
                    if (iOException == null) {
                        iOException = e10;
                    }
                }
            }
            if (iOException != null) {
                throw iOException;
            }
        } catch (FileNotFoundException unused) {
        }
    }

    public static final void Y(p0.d dVar, float f10, long j3, p0.h hVar) {
        float f11 = 2;
        float f12 = hVar.f20642a / f11;
        float fD = m0.f.d(dVar.e()) - (f11 * f12);
        p0.d.s(dVar, j3, 270.0f, f10, h6.e.a(f12, f12), h6.j.f(fD, fD), 0.0f, hVar, 832);
    }

    public static byte[] Z(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = AbstractC1264a.f14640f;
        int i7 = (length / 3) * 4;
        if (length % 3 > 0) {
            i7 += 4;
        }
        if (length > 0) {
            i7 += ((length - 1) / 57) + 1;
        }
        byte[] bArr3 = new byte[i7];
        int i10 = 0;
        int i11 = 19;
        int i12 = 0;
        while (true) {
            int i13 = i10 + 3;
            if (i13 > length) {
                break;
            }
            int i14 = (bArr[i10 + 2] & 255) | ((bArr[i10] & 255) << 16) | ((bArr[i10 + 1] & 255) << 8);
            bArr3[i12] = bArr2[(i14 >> 18) & 63];
            bArr3[i12 + 1] = bArr2[(i14 >> 12) & 63];
            bArr3[i12 + 2] = bArr2[(i14 >> 6) & 63];
            bArr3[i12 + 3] = bArr2[i14 & 63];
            int i15 = i12 + 4;
            i11--;
            if (i11 == 0) {
                i12 += 5;
                bArr3[i15] = 10;
                i11 = 19;
            } else {
                i12 = i15;
            }
            i10 = i13;
        }
        if (i10 == length - 1) {
            int i16 = (bArr[i10] & 255) << 4;
            bArr3[i12] = bArr2[(i16 >> 6) & 63];
            bArr3[i12 + 1] = bArr2[i16 & 63];
            bArr3[i12 + 2] = 61;
            bArr3[i12 + 3] = 61;
            bArr3[i12 + 4] = 10;
        } else if (i10 == length - 2) {
            int i17 = ((bArr[i10 + 1] & 255) << 2) | ((bArr[i10] & 255) << 10);
            bArr3[i12] = bArr2[(i17 >> 12) & 63];
            bArr3[i12 + 1] = bArr2[(i17 >> 6) & 63];
            bArr3[i12 + 2] = bArr2[i17 & 63];
            bArr3[i12 + 3] = 61;
            bArr3[i12 + 4] = 10;
        } else if (i12 > 0 && i11 != 19) {
            bArr3[i12] = 10;
        }
        return bArr3;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0226  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x02c0  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x02fd  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x030a  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0336  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0344  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0366  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x036a  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0385  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0415  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x042e  */
    /* JADX WARN: Removed duplicated region for block: B:214:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0140  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void a(g0.o r48, boolean r49, final int r50, final long r51, long r53, boolean r55, java.lang.String r56, boolean r57, float r58, float r59, com.stingray.karaoke.car.utils.FontSize r60, final z7.InterfaceC2772a r61, T.C0761q r62, final int r63, final int r64, final int r65) {
        /*
            Method dump skipped, instruction units count: 1101
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.config.a.a(g0.o, boolean, int, long, long, boolean, java.lang.String, boolean, float, float, com.stingray.karaoke.car.utils.FontSize, z7.a, T.q, int, int, int):void");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static c9.K a0(String str) {
        m.f(str, "javaName");
        int iHashCode = str.hashCode();
        if (iHashCode != 79201641) {
            if (iHashCode != 79923350) {
                switch (iHashCode) {
                    case -503070503:
                        if (str.equals("TLSv1.1")) {
                            return c9.K.TLS_1_1;
                        }
                        break;
                    case -503070502:
                        if (str.equals("TLSv1.2")) {
                            return c9.K.TLS_1_2;
                        }
                        break;
                    case -503070501:
                        if (str.equals("TLSv1.3")) {
                            return c9.K.TLS_1_3;
                        }
                        break;
                }
            } else if (str.equals("TLSv1")) {
                return c9.K.TLS_1_0;
            }
        } else if (str.equals("SSLv3")) {
            return c9.K.SSL_3_0;
        }
        throw new IllegalArgumentException("Unexpected TLS version: ".concat(str));
    }

    public static final void b(final o oVar, final int i7, final long j3, final boolean z6, C0761q c0761q, final int i10) throws XmlPullParserException, IOException {
        int i11;
        c0761q.S(-1670681375);
        if ((i10 & 6) == 0) {
            i11 = (c0761q.f(oVar) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= c0761q.d(i7) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i11 |= c0761q.e(j3) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= c0761q.g(z6) ? 2048 : 1024;
        }
        if ((i11 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
        } else {
            m6.b bVar = m6.c.f19252a;
            float fB = bVar.B(c0761q);
            AbstractC2268a abstractC2268aW = l9.l.W(i7, c0761q, (i11 >> 3) & 14);
            o oVarL = androidx.compose.foundation.layout.c.l(oVar, bVar.y(c0761q));
            float f10 = !z6 ? fB : 0;
            if (!z6) {
                fB = 0;
            }
            AbstractC0535v.a(abstractC2268aW, null, androidx.compose.foundation.layout.a.o(oVarL, f10, 0.0f, fB, 0.0f, 10), j3, c0761q, ((i11 << 3) & 7168) | 48, 0);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: Q5.l
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) throws XmlPullParserException, IOException {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i10 | 1);
                    long j10 = j3;
                    boolean z10 = z6;
                    io.sentry.config.a.b(oVar, i7, j10, z10, (C0761q) obj, iZ);
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static Set b0() {
        try {
            Object objInvoke = Class.forName("android.text.EmojiConsistency").getMethod("getEmojiConsistencySet", null).invoke(null, null);
            if (objInvoke == null) {
                return Collections.emptySet();
            }
            Set set = (Set) objInvoke;
            Iterator it = set.iterator();
            while (it.hasNext()) {
                if (!(it.next() instanceof int[])) {
                    return Collections.emptySet();
                }
            }
            return set;
        } catch (Throwable unused) {
            return Collections.emptySet();
        }
    }

    public static final void c(o oVar, String str, long j3, long j10, N0.L l, C0761q c0761q, int i7, int i10) {
        int i11;
        N0.L l10;
        N0.L l11;
        c0761q.S(1614539754);
        if ((i7 & 6) == 0) {
            i11 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i11 = i7;
        }
        if ((i7 & 48) == 0) {
            i11 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i11 |= c0761q.e(j3) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i11 |= c0761q.e(j10) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            if ((i10 & 16) == 0) {
                l10 = l;
                int i12 = c0761q.f(l10) ? 16384 : 8192;
                i11 |= i12;
            } else {
                l10 = l;
            }
            i11 |= i12;
        } else {
            l10 = l;
        }
        if ((i11 & 9363) == 9362 && c0761q.x()) {
            c0761q.K();
            l11 = l10;
        } else {
            c0761q.M();
            if ((i7 & 1) == 0 || c0761q.w()) {
                if ((i10 & 16) != 0) {
                    l10 = ((O5.d) c0761q.k(O5.c.f5157b)).d;
                    i11 &= -57345;
                }
                N0.L l12 = l10;
                c0761q.q();
                c1.b(str, oVar, j3, j10, null, null, null, 0L, null, new i(3), 0L, 2, false, 2, 0, null, l12, c0761q, ((i11 >> 3) & 14) | ((i11 << 3) & 112) | (i11 & 896) | (i11 & 7168), ((i11 << 6) & 3670016) | 3120, 54768);
                l11 = l12;
            } else {
                c0761q.K();
                if ((i10 & 16) != 0) {
                    i11 &= -57345;
                }
                N0.L l122 = l10;
                c0761q.q();
                c1.b(str, oVar, j3, j10, null, null, null, 0L, null, new i(3), 0L, 2, false, 2, 0, null, l122, c0761q, ((i11 >> 3) & 14) | ((i11 << 3) & 112) | (i11 & 896) | (i11 & 7168), ((i11 << 6) & 3670016) | 3120, 54768);
                l11 = l122;
            }
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0593m(oVar, str, j3, j10, l11, i7, i10);
        }
    }

    public static final int c0(int i7, int i10) {
        return (i7 >> i10) & 31;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r10v3 */
    public static final void d(final o oVar, final float f10, final long j3, final long j10, final float f11, C0761q c0761q, final int i7) {
        int i10;
        o oVar2;
        ?? r10;
        c0761q.S(-2062926597);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.c(f10) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.e(j3) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.e(j10) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.c(f11) ? 16384 : 8192;
        }
        if ((i10 & 9363) == 9362 && c0761q.x()) {
            c0761q.K();
        } else {
            final p0.h hVar = new p0.h(((a1.b) c0761q.k(AbstractC0227s0.f2259f)).y(f11), 0.0f, 0, 0, 26);
            o oVarA = L0.k.a(oVar, true, new s0(f10, new G7.a(0.0f, 1.0f), 0));
            c0761q.Q(1455792404);
            boolean zH = ((i10 & 112) == 32) | ((i10 & 7168) == 2048) | c0761q.h(hVar) | ((i10 & 896) == 256);
            Object objG = c0761q.G();
            if (zH || objG == C0753m.f8270a) {
                oVar2 = oVarA;
                r10 = 0;
                InterfaceC2782k interfaceC2782k = new InterfaceC2782k() { // from class: Q5.w0
                    @Override // z7.InterfaceC2782k
                    public final Object b(Object obj) {
                        p0.d dVar = (p0.d) obj;
                        A7.m.f(dVar, "$this$Canvas");
                        float f12 = f10 * 360.0f;
                        p0.h hVar2 = hVar;
                        io.sentry.config.a.Y(dVar, 360.0f, j10, hVar2);
                        io.sentry.config.a.Y(dVar, f12, j3, hVar2);
                        return m7.q.f19267a;
                    }
                };
                c0761q.a0(interfaceC2782k);
                objG = interfaceC2782k;
            } else {
                oVar2 = oVarA;
                r10 = 0;
            }
            c0761q.p(r10);
            h6.j.b(r10, c0761q, oVar2, (InterfaceC2782k) objG);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: Q5.x0
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i7 | 1);
                    long j11 = j10;
                    float f12 = f11;
                    io.sentry.config.a.d(oVar, f10, j3, j11, f12, (C0761q) obj, iZ);
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static int d0(Uri uri) {
        String lastPathSegment = uri.getLastPathSegment();
        if (lastPathSegment == null) {
            return -1;
        }
        if (lastPathSegment.endsWith(".ac3") || lastPathSegment.endsWith(".ec3")) {
            return 0;
        }
        if (lastPathSegment.endsWith(".ac4")) {
            return 1;
        }
        if (lastPathSegment.endsWith(".adts") || lastPathSegment.endsWith(".aac")) {
            return 2;
        }
        if (lastPathSegment.endsWith(".amr")) {
            return 3;
        }
        if (lastPathSegment.endsWith(".flac")) {
            return 4;
        }
        if (lastPathSegment.endsWith(".flv")) {
            return 5;
        }
        if (lastPathSegment.endsWith(".mid") || lastPathSegment.endsWith(".midi") || lastPathSegment.endsWith(".smf")) {
            return 15;
        }
        if (lastPathSegment.startsWith(".mk", lastPathSegment.length() - 4) || lastPathSegment.endsWith(".webm")) {
            return 6;
        }
        if (lastPathSegment.endsWith(".mp3")) {
            return 7;
        }
        if (lastPathSegment.endsWith(".mp4") || lastPathSegment.startsWith(".m4", lastPathSegment.length() - 4) || lastPathSegment.startsWith(".mp4", lastPathSegment.length() - 5) || lastPathSegment.startsWith(".cmf", lastPathSegment.length() - 5)) {
            return 8;
        }
        if (lastPathSegment.startsWith(".og", lastPathSegment.length() - 4) || lastPathSegment.endsWith(".opus")) {
            return 9;
        }
        if (lastPathSegment.endsWith(".ps") || lastPathSegment.endsWith(".mpeg") || lastPathSegment.endsWith(".mpg") || lastPathSegment.endsWith(".m2p")) {
            return 10;
        }
        if (lastPathSegment.endsWith(".ts") || lastPathSegment.startsWith(".ts", lastPathSegment.length() - 4)) {
            return 11;
        }
        if (lastPathSegment.endsWith(".wav") || lastPathSegment.endsWith(".wave")) {
            return 12;
        }
        if (lastPathSegment.endsWith(".vtt") || lastPathSegment.endsWith(".webvtt")) {
            return 13;
        }
        if (lastPathSegment.endsWith(".jpg") || lastPathSegment.endsWith(".jpeg")) {
            return 14;
        }
        if (lastPathSegment.endsWith(".avi")) {
            return 16;
        }
        if (lastPathSegment.endsWith(".png")) {
            return 17;
        }
        if (lastPathSegment.endsWith(".webp")) {
            return 18;
        }
        if (lastPathSegment.endsWith(".bmp") || lastPathSegment.endsWith(".dib")) {
            return 19;
        }
        if (lastPathSegment.endsWith(".heic") || lastPathSegment.endsWith(".heif")) {
            return 20;
        }
        return lastPathSegment.endsWith(".avif") ? 21 : -1;
    }

    public static final long e(int i7, int i10, int i11, int i12) {
        boolean z6 = false;
        if (!(i10 >= i7)) {
            w0.c.e0("maxWidth(" + i10 + ") must be >= than minWidth(" + i7 + ')');
            throw null;
        }
        if (!(i12 >= i11)) {
            w0.c.e0("maxHeight(" + i12 + ") must be >= than minHeight(" + i11 + ')');
            throw null;
        }
        if (i7 >= 0 && i11 >= 0) {
            z6 = true;
        }
        if (z6) {
            return U(i7, i10, i11, i12);
        }
        w0.c.e0("minWidth(" + i7 + ") and minHeight(" + i11 + ") must be >= 0");
        throw null;
    }

    public static final boolean e0(long j3, long j10) {
        int iJ = a1.a.j(j3);
        int iH = a1.a.h(j3);
        int i7 = (int) (j10 >> 32);
        if (iJ <= i7 && i7 <= iH) {
            int i10 = a1.a.i(j3);
            int iG = a1.a.g(j3);
            int i11 = (int) (j10 & 4294967295L);
            if (i10 <= i11 && i11 <= iG) {
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ long f(int i7, int i10, int i11) {
        if ((i11 & 2) != 0) {
            i7 = Integer.MAX_VALUE;
        }
        if ((i11 & 8) != 0) {
            i10 = Integer.MAX_VALUE;
        }
        return e(0, i7, 0, i10);
    }

    public static final boolean f0(int i7, String str) {
        char cCharAt = str.charAt(i7);
        return 'A' <= cCharAt && cCharAt < '[';
    }

    public static final void g(o oVar, N n10, InterfaceC2785n interfaceC2785n, C0761q c0761q, int i7) {
        int i10;
        boolean z6;
        C0857f c0857f;
        boolean z10;
        m.f(n10, "countdownData");
        m.f(interfaceC2785n, "playSoundFx");
        c0761q.S(-1093467494);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(n10) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(interfaceC2785n) ? 256 : 128;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            C0857f c0857f2 = (C0857f) com.bumptech.glide.c.R(n10, c0761q, (i10 >> 3) & 14).getValue();
            c0761q.Q(2037917999);
            if (c0857f2 != null) {
                K kE = AbstractC0133o.e(g0.b.f14664p, false);
                int i11 = c0761q.f8304P;
                InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
                o oVarD = g0.a.d(c0761q, oVar);
                InterfaceC0161k.f1681b.getClass();
                InterfaceC2772a interfaceC2772a = C0160j.f1678b;
                c0761q.U();
                if (c0761q.f8303O) {
                    c0761q.l(interfaceC2772a);
                } else {
                    c0761q.d0();
                }
                C0735d.V(c0761q, kE, C0160j.f1679e);
                C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
                C0158h c0158h = C0160j.f1680f;
                if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i11))) {
                    AbstractC1347d.l(i11, c0761q, i11, c0158h);
                }
                C0735d.V(c0761q, oVarD, C0160j.c);
                long j3 = c0857f2.f10236a % ((long) 1000);
                z6 = false;
                Q0 q0B = AbstractC2637h.b(c0857f2.c, (500 > j3 || j3 >= 1000) ? AbstractC2631e.o() : AbstractC2631e.q(300, 0, null, 6), "scaleAnimateFloatAsState", null, c0761q, 3072, 20);
                c0857f = c0857f2;
                c1.b(c0857f2.f10237b, androidx.compose.ui.graphics.a.b(androidx.compose.foundation.layout.c.d(l.f14684a, 1.0f), ((Number) q0B.getValue()).floatValue(), ((Number) q0B.getValue()).floatValue(), ((Number) AbstractC2637h.b(c0857f2.d, (500 > j3 || j3 >= 1000) ? AbstractC2631e.o() : AbstractC2631e.q(300, 0, null, 6), "alphaAnimateFloatAsState", null, c0761q, 3072, 20).getValue()).floatValue(), 0.0f, 0.0f, AbstractC1861K.j(0.5f, 1.0f), null, false, 130040), l6.d.c(c0761q).f18691X, f.g(FontSize.f13036H2, c0761q), null, null, null, 0L, null, new i(3), 0L, 0, false, 0, 0, null, ((O5.a) c0761q.k(O5.b.f5155b)).l, c0761q, 0, 0, 65008);
                z10 = true;
                c0761q.p(true);
            } else {
                z6 = false;
                c0857f = c0857f2;
                z10 = true;
            }
            c0761q.p(z6);
            C0857f c0857f3 = c0857f;
            String str = c0857f3 != null ? c0857f3.f10237b : null;
            c0761q.Q(2037962931);
            boolean zF = c0761q.f(c0857f3) | ((i10 & 896) == 256 ? z10 : z6);
            Object objG = c0761q.G();
            if (zF || objG == C0753m.f8270a) {
                objG = new C0645z0(c0857f3, interfaceC2785n, null);
                c0761q.a0(objG);
            }
            c0761q.p(z6);
            C0735d.f(c0761q, str, (InterfaceC2785n) objG);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0641y0(i7, 0, oVar, n10, interfaceC2785n);
        }
    }

    public static final long g0(int i7, int i10, long j3) {
        int iJ = a1.a.j(j3) + i7;
        if (iJ < 0) {
            iJ = 0;
        }
        int iH = a1.a.h(j3);
        if (iH != Integer.MAX_VALUE && (iH = iH + i7) < 0) {
            iH = 0;
        }
        int i11 = a1.a.i(j3) + i10;
        if (i11 < 0) {
            i11 = 0;
        }
        int iG = a1.a.g(j3);
        if (iG != Integer.MAX_VALUE) {
            int i12 = iG + i10;
            iG = i12 >= 0 ? i12 : 0;
        }
        return e(iJ, iH, i11, iG);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void h(final g0.o r15, final int r16, Y0.i r17, T.C0761q r18, final int r19, final int r20) {
        /*
            r10 = r18
            r11 = r19
            r0 = -1693227733(0xffffffff9b13652b, float:-1.2192244E-22)
            r10.S(r0)
            r0 = r11 & 6
            r12 = r15
            if (r0 != 0) goto L1a
            boolean r0 = r10.f(r15)
            if (r0 == 0) goto L17
            r0 = 4
            goto L18
        L17:
            r0 = 2
        L18:
            r0 = r0 | r11
            goto L1b
        L1a:
            r0 = r11
        L1b:
            r1 = r11 & 48
            r13 = r16
            if (r1 != 0) goto L2d
            boolean r1 = r10.d(r13)
            if (r1 == 0) goto L2a
            r1 = 32
            goto L2c
        L2a:
            r1 = 16
        L2c:
            r0 = r0 | r1
        L2d:
            r1 = r20 & 4
            if (r1 == 0) goto L36
            r0 = r0 | 384(0x180, float:5.38E-43)
        L33:
            r2 = r17
            goto L48
        L36:
            r2 = r11 & 384(0x180, float:5.38E-43)
            if (r2 != 0) goto L33
            r2 = r17
            boolean r3 = r10.f(r2)
            if (r3 == 0) goto L45
            r3 = 256(0x100, float:3.59E-43)
            goto L47
        L45:
            r3 = 128(0x80, float:1.8E-43)
        L47:
            r0 = r0 | r3
        L48:
            r3 = r0 & 147(0x93, float:2.06E-43)
            r4 = 146(0x92, float:2.05E-43)
            if (r3 != r4) goto L5a
            boolean r3 = r18.x()
            if (r3 != 0) goto L55
            goto L5a
        L55:
            r18.K()
            r3 = r2
            goto L89
        L5a:
            if (r1 == 0) goto L5f
            r1 = 0
            r14 = r1
            goto L60
        L5f:
            r14 = r2
        L60:
            com.stingray.karaoke.car.utils.FontSize r1 = com.stingray.karaoke.car.utils.FontSize.f13041H7
            long r4 = h6.f.g(r1, r10)
            l6.a r1 = l6.d.c(r18)
            long r2 = r1.f18682O
            T.R0 r1 = O5.b.f5155b
            java.lang.Object r1 = r10.k(r1)
            O5.a r1 = (O5.a) r1
            N0.L r6 = r1.f5144b
            r1 = r0 & 126(0x7e, float:1.77E-43)
            int r0 = r0 << 9
            r7 = 458752(0x70000, float:6.42848E-40)
            r0 = r0 & r7
            r9 = r1 | r0
            r0 = r15
            r1 = r16
            r7 = r14
            r8 = r18
            n7.AbstractC1985B.b(r0, r1, r2, r4, r6, r7, r8, r9)
            r3 = r14
        L89:
            T.m0 r6 = r18.r()
            if (r6 == 0) goto L9e
            U5.u r7 = new U5.u
            r0 = r7
            r1 = r15
            r2 = r16
            r4 = r19
            r5 = r20
            r0.<init>()
            r6.d = r7
        L9e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.config.a.h(g0.o, int, Y0.i, T.q, int, int):void");
    }

    public static /* synthetic */ long h0(int i7, int i10, int i11, long j3) {
        if ((i11 & 1) != 0) {
            i7 = 0;
        }
        if ((i11 & 2) != 0) {
            i10 = 0;
        }
        return g0(i7, i10, j3);
    }

    public static final void i(o oVar, final String str, final boolean z6, final int i7, C0761q c0761q, final int i10, final int i11) {
        o oVar2;
        int i12;
        final o oVar3;
        c0761q.S(1194558410);
        int i13 = i11 & 1;
        if (i13 != 0) {
            i12 = i10 | 6;
            oVar2 = oVar;
        } else if ((i10 & 6) == 0) {
            oVar2 = oVar;
            i12 = (c0761q.f(oVar2) ? 4 : 2) | i10;
        } else {
            oVar2 = oVar;
            i12 = i10;
        }
        if ((i10 & 48) == 0) {
            i12 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i12 |= c0761q.g(z6) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i12 |= c0761q.d(i7) ? 2048 : 1024;
        }
        int i14 = i12;
        if ((i14 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
            oVar3 = oVar2;
        } else {
            o oVar4 = i13 != 0 ? l.f14684a : oVar2;
            Q0 q0B = AbstractC2637h.b(z6 ? 1.0f : 0.5f, null, "encouragementScale", null, c0761q, 3072, 22);
            Q0 q0B2 = AbstractC2637h.b(z6 ? 1.0f : 0.0f, null, "encouragementAlpha", null, c0761q, 3072, 22);
            c0761q.Q(74256445);
            boolean zF = c0761q.f(q0B2) | c0761q.f(q0B);
            Object objG = c0761q.G();
            if (zF || objG == C0753m.f8270a) {
                objG = new Q5.O(q0B2, 5, q0B);
                c0761q.a0(objG);
            }
            c0761q.p(false);
            c1.b(str, androidx.compose.ui.graphics.a.a(oVar4, (InterfaceC2782k) objG), l6.d.c(c0761q).f18696b, f.g(FontSize.f13041H7, c0761q), null, null, null, 0L, null, new i(i7), 0L, 0, false, 0, 0, null, com.bumptech.glide.d.O(((O5.a) c0761q.k(O5.b.f5155b)).f5144b, l6.d.c(c0761q).f18687T, 20.0f), c0761q, ((i14 >> 3) & 14) | ((i14 << 18) & 1879048192), 0, 65008);
            oVar3 = oVar4;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: U5.t
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i10 | 1);
                    String str2 = str;
                    int i15 = i7;
                    io.sentry.config.a.i(oVar3, str2, z6, i15, (C0761q) obj, iZ, i11);
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static final int i0(F.p pVar, EnumC0017h0 enumC0017h0) {
        return (int) (enumC0017h0 == EnumC0017h0.l ? pVar.f1943u & 4294967295L : pVar.f1943u >> 32);
    }

    public static final void j(String str, String str2, String str3, InterfaceC2772a interfaceC2772a, C0761q c0761q, int i7) {
        int i10;
        int i11;
        String strX;
        String strX2;
        String str4;
        String str5;
        m.f(str, "id");
        m.f(interfaceC2772a, "onRetry");
        c0761q.S(-867901443);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(str) ? 4 : 2) | i7;
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
            i10 |= c0761q.h(interfaceC2772a) ? 2048 : 1024;
        }
        if ((i10 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
            str4 = str2;
            str5 = str3;
        } else {
            c0761q.M();
            if ((i7 & 1) == 0 || c0761q.w()) {
                i11 = i10 & (-1009);
                strX = AbstractC2673a.X(R.string.Global_Error, c0761q);
                strX2 = AbstractC2673a.X(R.string.Error_Default, c0761q);
            } else {
                c0761q.K();
                i11 = i10 & (-1009);
                strX = str2;
                strX2 = str3;
            }
            c0761q.q();
            AbstractC1274a.h(str, false, false, false, false, g.b(95041878, new S5.j(str, strX, strX2, interfaceC2772a), c0761q), c0761q, (i11 & 14) | 196608, 30);
            str4 = strX;
            str5 = strX2;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new G6.c(str, (Object) str4, (Object) str5, interfaceC2772a, i7, 4);
        }
    }

    public static void j0(h8.m mVar, Annotation annotation, Class cls) {
        Method[] declaredMethods = cls.getDeclaredMethods();
        m.e(declaredMethods, "annotationType.declaredMethods");
        for (Method method : declaredMethods) {
            try {
                Object objInvoke = method.invoke(annotation, null);
                m.c(objInvoke);
                C2061f c2061fE = C2061f.e(method.getName());
                Class<?> enclosingClass = objInvoke.getClass();
                if (enclosingClass.equals(Class.class)) {
                    mVar.j(c2061fE, O((Class) objInvoke));
                } else if (V7.d.f9550a.contains(enclosingClass)) {
                    mVar.q(c2061fE, objInvoke);
                } else {
                    List list = AbstractC0839c.f9878a;
                    if (Enum.class.isAssignableFrom(enclosingClass)) {
                        if (!enclosingClass.isEnum()) {
                            enclosingClass = enclosingClass.getEnclosingClass();
                        }
                        m.e(enclosingClass, "if (clazz.isEnum) clazz else clazz.enclosingClass");
                        mVar.m(c2061fE, AbstractC0839c.a(enclosingClass), C2061f.e(((Enum) objInvoke).name()));
                    } else if (Annotation.class.isAssignableFrom(enclosingClass)) {
                        Class<?>[] interfaces = enclosingClass.getInterfaces();
                        m.e(interfaces, "clazz.interfaces");
                        Class cls2 = (Class) AbstractC1996k.Y(interfaces);
                        m.e(cls2, "annotationClass");
                        h8.m mVarL = mVar.l(AbstractC0839c.a(cls2), c2061fE);
                        if (mVarL != null) {
                            j0(mVarL, (Annotation) objInvoke, cls2);
                        }
                    } else {
                        if (!enclosingClass.isArray()) {
                            throw new UnsupportedOperationException("Unsupported annotation argument value (" + enclosingClass + "): " + objInvoke);
                        }
                        h8.n nVarK = mVar.k(c2061fE);
                        if (nVarK != null) {
                            Class<?> componentType = enclosingClass.getComponentType();
                            if (componentType.isEnum()) {
                                C2057b c2057bA = AbstractC0839c.a(componentType);
                                for (Object obj : (Object[]) objInvoke) {
                                    m.d(obj, "null cannot be cast to non-null type kotlin.Enum<*>");
                                    nVarK.i0(c2057bA, C2061f.e(((Enum) obj).name()));
                                }
                            } else if (componentType.equals(Class.class)) {
                                for (Object obj2 : (Object[]) objInvoke) {
                                    m.d(obj2, "null cannot be cast to non-null type java.lang.Class<*>");
                                    nVarK.o0(O((Class) obj2));
                                }
                            } else if (Annotation.class.isAssignableFrom(componentType)) {
                                for (Object obj3 : (Object[]) objInvoke) {
                                    h8.m mVarW = nVarK.W(AbstractC0839c.a(componentType));
                                    if (mVarW != null) {
                                        m.d(obj3, "null cannot be cast to non-null type kotlin.Annotation");
                                        j0(mVarW, (Annotation) obj3, componentType);
                                    }
                                }
                            } else {
                                for (Object obj4 : (Object[]) objInvoke) {
                                    nVarK.B0(obj4);
                                }
                            }
                            nVarK.i();
                        }
                    }
                }
            } catch (IllegalAccessException unused) {
            }
        }
        mVar.i();
    }

    public static final void k(int i7, C0761q c0761q, o oVar, InterfaceC2772a interfaceC2772a, boolean z6) {
        int i10;
        long j3;
        c0761q.S(-1423175098);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.g(z6) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 256 : 128;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            int i11 = z6 ? R.drawable.ic_liked : R.drawable.ic_like;
            if (z6) {
                c0761q.Q(-1155729159);
                j3 = l6.d.c(c0761q).f18670C;
            } else {
                c0761q.Q(-1155728357);
                j3 = l6.d.c(c0761q).f18696b;
            }
            c0761q.p(false);
            c0761q.Q(-1155727890);
            boolean z10 = (i10 & 896) == 256;
            Object objG = c0761q.G();
            if (z10 || objG == C0753m.f8270a) {
                objG = new C0549b(interfaceC2772a, 7);
                c0761q.a0(objG);
            }
            c0761q.p(false);
            a(oVar, false, i11, j3, 0L, false, null, false, 0.5f, 0.0f, null, (InterfaceC2772a) objG, c0761q, (i10 & 14) | 100663296, 0, 1778);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new N0(oVar, z6, interfaceC2772a, i7, 1);
        }
    }

    public static long k0(t tVar, int i7, int i10) {
        tVar.H(i7);
        if (tVar.a() < 5) {
            return -9223372036854775807L;
        }
        int iH = tVar.h();
        if ((8388608 & iH) != 0 || ((2096896 & iH) >> 8) != i10 || (iH & 32) == 0 || tVar.v() < 7 || tVar.a() < 7 || (tVar.v() & 16) != 16) {
            return -9223372036854775807L;
        }
        byte[] bArr = new byte[6];
        tVar.f(0, 6, bArr);
        return ((((long) bArr[0]) & 255) << 25) | ((((long) bArr[1]) & 255) << 17) | ((((long) bArr[2]) & 255) << 9) | ((((long) bArr[3]) & 255) << 1) | ((((long) bArr[4]) & 255) >> 7);
    }

    public static final void l(String str, C0761q c0761q, int i7) {
        int i10;
        m.f(str, "id");
        c0761q.S(1677934292);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(str) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i10 & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            AbstractC1274a.h(str, true, false, false, false, g.b(-61324517, new S5.k(str, 0), c0761q), c0761q, (i10 & 14) | 196656, 28);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new h(str, i7, 1);
        }
    }

    public static final void l0(Y7.a aVar, Y7.b bVar, D d, C2061f c2061f) {
        m.f(aVar, "<this>");
        m.f(bVar, "from");
        m.f(d, "scopeOwner");
        m.f(c2061f, "name");
        ((T7.B) d).f8767q.b();
        m.e(c2061f.b(), "name.asString()");
    }

    public static final void m(final InterfaceC2772a interfaceC2772a, final o oVar, boolean z6, C.k kVar, final InterfaceC1866P interfaceC1866P, final C2716v c2716v, List list, final InterfaceC0123e interfaceC0123e, final L l, final float f10, final b0.b bVar, C0761q c0761q, final int i7, final int i10) {
        int i11;
        int i12;
        C.k kVar2;
        boolean z10;
        int i13;
        List list2;
        List list3;
        final C.k kVar3;
        final boolean z11;
        c0761q.S(-1181265831);
        if ((i7 & 6) == 0) {
            i11 = (c0761q.h(interfaceC2772a) ? 4 : 2) | i7;
        } else {
            i11 = i7;
        }
        if ((i7 & 48) == 0) {
            i11 |= c0761q.f(oVar) ? 32 : 16;
        }
        int i14 = i11 | 3456;
        if ((i7 & 24576) == 0) {
            i14 |= c0761q.f(interfaceC1866P) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i14 |= c0761q.f(c2716v) ? 131072 : 65536;
        }
        if ((1572864 & i7) == 0) {
            i14 |= 524288;
        }
        if ((12582912 & i7) == 0) {
            i14 |= c0761q.f(interfaceC0123e) ? 8388608 : 4194304;
        }
        if ((i7 & 100663296) == 0) {
            i14 |= c0761q.f(l) ? 67108864 : 33554432;
        }
        if ((805306368 & i7) == 0) {
            i14 |= c0761q.c(f10) ? 536870912 : 268435456;
        }
        if ((i10 & 6) == 0) {
            i12 = i10 | (c0761q.h(bVar) ? 4 : 2);
        } else {
            i12 = i10;
        }
        if ((306783379 & i14) == 306783378 && (i12 & 3) == 2 && c0761q.x()) {
            c0761q.K();
            z11 = z6;
            kVar3 = kVar;
            list3 = list;
        } else {
            c0761q.M();
            if ((i7 & 1) == 0 || c0761q.w()) {
                c0761q.Q(719152495);
                Object objG = c0761q.G();
                if (objG == C0753m.f8270a) {
                    objG = new C.k();
                    c0761q.a0(objG);
                }
                c0761q.p(false);
                c0761q.Q(-2010783876);
                List listD = AbstractC1999n.D(new C1891t(l6.d.c(c0761q).f18715q), new C1891t(l6.d.c(c0761q).f18716r));
                c0761q.p(false);
                kVar2 = (C.k) objG;
                z10 = true;
                i13 = i14 & (-3670017);
                list2 = listD;
            } else {
                c0761q.K();
                int i15 = i14 & (-3670017);
                kVar2 = kVar;
                list2 = list;
                i13 = i15;
                z10 = z6;
            }
            c0761q.q();
            n(interfaceC2772a, oVar, z10, interfaceC1866P, list2, c2716v, 0.0f, kVar2, g.b(784593283, new C0605p(f10, l, interfaceC0123e, bVar), c0761q), c0761q, (i13 & 14) | 100663296 | (i13 & 112) | (i13 & 896) | ((i13 >> 3) & 7168) | (458752 & i13) | ((i13 << 12) & 29360128));
            list3 = list2;
            kVar3 = kVar2;
            z11 = z10;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            final List list4 = list3;
            c0754m0R.d = new InterfaceC2785n() { // from class: Q5.o
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i7 | 1);
                    int iZ2 = C0735d.Z(i10);
                    b0.b bVar2 = bVar;
                    io.sentry.config.a.m(interfaceC2772a, oVar, z11, kVar3, interfaceC1866P, c2716v, list4, interfaceC0123e, l, f10, bVar2, (C0761q) obj, iZ, iZ2);
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static final Object m0(X8.q qVar, X8.q qVar2, InterfaceC2785n interfaceC2785n) {
        Object c0720q;
        Object objU;
        try {
            E.e(2, interfaceC2785n);
            c0720q = interfaceC2785n.invoke(qVar2, qVar);
        } catch (Throwable th) {
            c0720q = new C0720q(th, false);
        }
        EnumC2303a enumC2303a = EnumC2303a.l;
        if (c0720q == enumC2303a || (objU = qVar.U(c0720q)) == AbstractC0727y.f8146e) {
            return enumC2303a;
        }
        if (objU instanceof C0720q) {
            throw ((C0720q) objU).f8130a;
        }
        return AbstractC0727y.D(objU);
    }

    public static final void n(final InterfaceC2772a interfaceC2772a, final o oVar, final boolean z6, final InterfaceC1866P interfaceC1866P, final List list, final C2716v c2716v, float f10, final C.k kVar, final b0.b bVar, C0761q c0761q, final int i7) {
        int i10;
        int i11;
        float fJ;
        float f11;
        m.f(interfaceC2772a, "onClick");
        m.f(list, "gradientColors");
        c0761q.S(-788475256);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(interfaceC2772a) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(oVar) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.g(z6) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.f(interfaceC1866P) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.h(list) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i10 |= c0761q.f(c2716v) ? 131072 : 65536;
        }
        if ((1572864 & i7) == 0) {
            i10 |= 524288;
        }
        if ((12582912 & i7) == 0) {
            i10 |= c0761q.f(kVar) ? 8388608 : 4194304;
        }
        if ((100663296 & i7) == 0) {
            i10 |= c0761q.h(bVar) ? 67108864 : 33554432;
        }
        if ((38347923 & i10) == 38347922 && c0761q.x()) {
            c0761q.K();
            f11 = f10;
        } else {
            c0761q.M();
            if ((i7 & 1) == 0 || c0761q.w()) {
                i11 = i10 & (-3670017);
                fJ = w0.c.J(R.dimen.elevation0, c0761q);
            } else {
                c0761q.K();
                fJ = f10;
                i11 = i10 & (-3670017);
            }
            c0761q.q();
            k6.p pVarI = r.i(list, c0761q, ((i11 >> 12) & 14) | 48, 0);
            o oVarT = AbstractC1386c.t(oVar, fJ, interfaceC1866P, 24);
            o oVarD = l.f14684a;
            if (c2716v != null) {
                oVarD = h6.i.d(oVarD, c2716v.f24013a, c2716v.f24014b, interfaceC1866P);
            }
            f11 = fJ;
            o oVarP = com.bumptech.glide.c.P(h6.q.m(androidx.compose.foundation.a.a(oVarT.k(oVarD), pVarI, interfaceC1866P, 4), interfaceC1866P), z6, kVar, new L0.g(0), interfaceC2772a, 2);
            K kE = AbstractC0133o.e(g0.b.l, true);
            int i12 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            o oVarD2 = g0.a.d(c0761q, oVarP);
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
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i12))) {
                AbstractC1347d.l(i12, c0761q, i12, c0158h);
            }
            C0735d.V(c0761q, oVarD2, C0160j.c);
            A7.k.o((i11 >> 24) & 14, bVar, c0761q, true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            final float f12 = f11;
            c0754m0R.d = new InterfaceC2785n() { // from class: Q5.h
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i7 | 1);
                    b0.b bVar2 = bVar;
                    io.sentry.config.a.n(interfaceC2772a, oVar, z6, interfaceC1866P, list, c2716v, f12, kVar, bVar2, (C0761q) obj, iZ);
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static final String n0(String str) {
        m.f(str, "<this>");
        StringBuilder sb = new StringBuilder(str.length());
        int length = str.length();
        for (int i7 = 0; i7 < length; i7++) {
            char cCharAt = str.charAt(i7);
            if ('A' <= cCharAt && cCharAt < '[') {
                cCharAt = Character.toLowerCase(cCharAt);
            }
            sb.append(cCharAt);
        }
        String string = sb.toString();
        m.e(string, "builder.toString()");
        return string;
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x020a  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0226  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0239  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0244  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x024a  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0265  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02e7  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02f1  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x030e  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0319  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x035d  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x037e  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0431  */
    /* JADX WARN: Removed duplicated region for block: B:251:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0117  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void o(g0.o r35, java.lang.Integer r36, boolean r37, final java.lang.String r38, long r39, D.InterfaceC0123e r41, boolean r42, D.L r43, N0.L r44, long r45, float r47, final z7.InterfaceC2772a r48, T.C0761q r49, final int r50, final int r51, final int r52) {
        /*
            Method dump skipped, instruction units count: 1104
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.config.a.o(g0.o, java.lang.Integer, boolean, java.lang.String, long, D.e, boolean, D.L, N0.L, long, float, z7.a, T.q, int, int, int):void");
    }

    public static final m0.d o0(C0.r rVar) {
        m0.d dVarF = c0.f(rVar);
        long jD = rVar.d(h6.e.a(dVarF.f18882a, dVarF.f18883b));
        long jD2 = rVar.d(h6.e.a(dVarF.c, dVarF.d));
        return new m0.d(m0.c.d(jD), m0.c.e(jD), m0.c.d(jD2), m0.c.e(jD2));
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:116:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x013e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void p(g0.o r34, java.lang.Integer r35, boolean r36, final java.lang.String r37, long r38, D.InterfaceC0123e r40, D.L r41, long r42, float r44, final z7.InterfaceC2772a r45, T.C0761q r46, final int r47, final int r48) {
        /*
            Method dump skipped, instruction units count: 541
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.config.a.p(g0.o, java.lang.Integer, boolean, java.lang.String, long, D.e, D.L, long, float, z7.a, T.q, int, int):void");
    }

    /* JADX WARN: Type inference failed for: r3v5, types: [A7.o, z7.a] */
    public static final void p0(L0.o oVar, int i7, K0.k kVar) {
        L0.o oVar2;
        V.d dVar = new V.d(new L0.o[16]);
        List listG = oVar.g(false, false, false);
        while (true) {
            dVar.d(dVar.f9279n, listG);
            while (dVar.m()) {
                oVar2 = (L0.o) dVar.o(dVar.f9279n - 1);
                if (T.w(oVar2)) {
                    u uVar = L0.r.f3947i;
                    L0.j jVar = oVar2.d;
                    LinkedHashMap linkedHashMap = jVar.l;
                    if (linkedHashMap.containsKey(uVar)) {
                        continue;
                    } else {
                        d0 d0VarC = oVar2.c();
                        if (d0VarC == null) {
                            l9.d.i0("Expected semantics node to have a coordinator.");
                            throw null;
                        }
                        a1.i iVarX = C7.a.X(c0.f(d0VarC));
                        if (iVarX.f11035a < iVarX.c && iVarX.f11036b < iVarX.d) {
                            Object obj = jVar.l.get(L0.i.f3889e);
                            if (obj == null) {
                                obj = null;
                            }
                            InterfaceC2785n interfaceC2785n = (InterfaceC2785n) obj;
                            Object obj2 = linkedHashMap.get(L0.r.f3952p);
                            L0.h hVar = (L0.h) (obj2 != null ? obj2 : null);
                            if (interfaceC2785n == null || hVar == null || ((Number) hVar.f3885b.a()).floatValue() <= 0.0f) {
                                break;
                            }
                            int i10 = i7 + 1;
                            kVar.b(new K0.m(oVar2, i10, iVarX, d0VarC));
                            p0(oVar2, i10, kVar);
                        }
                    }
                }
            }
            return;
            listG = oVar2.g(false, false, false);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:98:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void q(g0.o r24, E.D r25, D.L r26, boolean r27, D.InterfaceC0125g r28, g0.e r29, A.r r30, boolean r31, z7.InterfaceC2782k r32, T.C0761q r33, int r34, int r35) {
        /*
            Method dump skipped, instruction units count: 405
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.config.a.q(g0.o, E.D, D.L, boolean, D.g, g0.e, A.r, boolean, z7.k, T.q, int, int):void");
    }

    public static final void r(o oVar, E.D d, L l, boolean z6, InterfaceC0123e interfaceC0123e, g0.f fVar, A.r rVar, boolean z10, InterfaceC2782k interfaceC2782k, C0761q c0761q, int i7) {
        int i10;
        int i11;
        boolean z11;
        InterfaceC0123e interfaceC0123e2;
        L l10;
        g0.f fVar2;
        A.r rVarC;
        L l11;
        boolean z12;
        InterfaceC0123e interfaceC0123e3;
        g0.f fVar3;
        A.r rVar2;
        c0761q.S(-1724297413);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(d) ? 32 : 16;
        }
        int i12 = i10 | 3456;
        if ((i7 & 24576) == 0) {
            i12 = i10 | 11648;
        }
        int i13 = 196608 | i12;
        if ((1572864 & i7) == 0) {
            i13 = 720896 | i12;
        }
        if ((12582912 & i7) == 0) {
            i13 |= c0761q.g(z10) ? 8388608 : 4194304;
        }
        if ((100663296 & i7) == 0) {
            i13 |= c0761q.h(interfaceC2782k) ? 67108864 : 33554432;
        }
        if ((38347923 & i13) == 38347922 && c0761q.x()) {
            c0761q.K();
            l11 = l;
            z12 = z6;
            interfaceC0123e3 = interfaceC0123e;
            fVar3 = fVar;
            rVar2 = rVar;
        } else {
            c0761q.M();
            if ((i7 & 1) == 0 || c0761q.w()) {
                float f10 = 0;
                L l12 = new L(f10, f10, f10, f10);
                i11 = i13 & (-3727361);
                z11 = false;
                interfaceC0123e2 = AbstractC0127i.f1076a;
                l10 = l12;
                fVar2 = g0.b.f14669u;
                rVarC = AbstractC0904a.C(c0761q);
            } else {
                c0761q.K();
                i11 = i13 & (-3727361);
                l10 = l;
                z11 = z6;
                interfaceC0123e2 = interfaceC0123e;
                fVar2 = fVar;
                rVarC = rVar;
            }
            c0761q.q();
            l9.d.k(oVar, d, l10, z11, false, rVarC, z10, 0, null, null, fVar2, interfaceC0123e2, interfaceC2782k, c0761q, (i11 & 14) | 24576 | (i11 & 112) | (i11 & 896) | (i11 & 7168) | ((i11 >> 3) & 3670016), ((i11 >> 15) & 14) | ((i11 >> 18) & 896), 896);
            l11 = l10;
            z12 = z11;
            interfaceC0123e3 = interfaceC0123e2;
            fVar3 = fVar2;
            rVar2 = rVarC;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0146c(oVar, d, l11, z12, interfaceC0123e3, fVar3, rVar2, z10, interfaceC2782k, i7);
        }
    }

    public static final void s(String str, boolean z6, C0761q c0761q, int i7) {
        int i10;
        m.f(str, "id");
        c0761q.S(-2129686247);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(str) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.g(z6) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            Boolean boolValueOf = Boolean.valueOf(z6);
            c0761q.Q(-374209399);
            boolean z10 = (i10 & 112) == 32;
            int i11 = i10 & 14;
            boolean z11 = z10 | (i11 == 4);
            Object objG = c0761q.G();
            if (z11 || objG == C0753m.f8270a) {
                objG = new S5.l(z6, str, null);
                c0761q.a0(objG);
            }
            c0761q.p(false);
            C0735d.f(c0761q, boolValueOf, (InterfaceC2785n) objG);
            AbstractC1274a.h(str, false, false, false, false, g.b(1951752928, new S5.k(str, 1), c0761q), c0761q, i11 | 196656, 28);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new H1(str, z6, i7, 1);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0166  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void t(g0.o r21, long r22, T.C0761q r24, final int r25, final int r26) {
        /*
            Method dump skipped, instruction units count: 404
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.config.a.t(g0.o, long, T.q, int, int):void");
    }

    public static final void u(String str, InterfaceC2772a interfaceC2772a, C0761q c0761q, int i7) {
        int i10;
        m.f(str, "id");
        m.f(interfaceC2772a, "onDismiss");
        c0761q.S(288421047);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(str) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            AbstractC1274a.h(str, true, false, false, false, g.b(-2035983408, new S5.m(0, str, interfaceC2772a), c0761q), c0761q, (i10 & 14) | 196656, 28);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new S5.i(str, interfaceC2772a, i7, 0);
        }
    }

    public static final void v(String str, D0 d02, C0761q c0761q, int i7) {
        int i10;
        m.f(str, "id");
        c0761q.S(-1127934167);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(str) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(d02) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            AbstractC1274a.h(str, false, false, false, false, g.b(-1707937086, new S5.n(str, 0, d02), c0761q), c0761q, (i10 & 14) | 196608, 30);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new M(str, d02, i7, 5);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v30 */
    /* JADX WARN: Type inference failed for: r10v31, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r10v32 */
    public static final void w(final o oVar, final List list, final int i7, final InterfaceC2785n interfaceC2785n, final LoadingInterface$LoadingState loadingInterface$LoadingState, final InterfaceC2772a interfaceC2772a, final String str, C0761q c0761q, final int i10) throws XmlPullParserException, IOException {
        int i11;
        boolean z6;
        boolean z10;
        char c;
        float fH;
        Iterator it;
        int i12;
        ?? r10;
        Object obj;
        c0761q.S(708437257);
        int i13 = (i10 & 6) == 0 ? (c0761q.f(oVar) ? 4 : 2) | i10 : i10;
        if ((i10 & 48) == 0) {
            i13 |= c0761q.h(list) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i13 |= c0761q.d(i7) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i13 |= c0761q.h(interfaceC2785n) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i13 |= c0761q.f(loadingInterface$LoadingState) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i13 |= c0761q.h(interfaceC2772a) ? 131072 : 65536;
        }
        if ((1572864 & i10) == 0) {
            i13 |= c0761q.f(str) ? 1048576 : 524288;
        }
        if ((599187 & i13) == 599186 && c0761q.x()) {
            c0761q.K();
        } else if (list != null && !list.isEmpty()) {
            o oVarD = androidx.compose.foundation.layout.c.d(oVar, 1.0f);
            C0119a c0119a = AbstractC0127i.f1076a;
            C0124f c0124f = new C0124f(m6.c.f19252a.H(6, c0761q));
            g0.e eVar = g0.b.f14672x;
            C0141x c0141xA = AbstractC0139v.a(c0124f, eVar, c0761q, 0);
            int i14 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            o oVarD2 = g0.a.d(c0761q, oVarD);
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
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i14))) {
                AbstractC1347d.l(i14, c0761q, i14, c0158h);
            }
            C0735d.V(c0761q, oVarD2, C0160j.c);
            int i15 = i13;
            int i16 = 1;
            c1.b(AbstractC2673a.X(R.string.Global_YouMayAlsoLike, c0761q), new HorizontalAlignElement(eVar), l6.d.c(c0761q).f18696b, f.g(FontSize.H9, c0761q), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, ((O5.d) c0761q.k(O5.c.f5157b)).f5158a, c0761q, 0, 0, 65520);
            int i17 = Y1.c[loadingInterface$LoadingState.ordinal()];
            if (i17 == 1) {
                c0761q.Q(1560063343);
                t(null, 0L, c0761q, 0, 3);
                c0761q.p(false);
            } else if (i17 == 2) {
                c0761q.Q(1117396415);
                H.b(null, AbstractC2673a.X(R.string.Error_RelatedSongs, c0761q), false, interfaceC2772a, c0761q, ((i15 >> 6) & 7168) | 384, 1);
                c0761q.p(false);
            } else {
                if (i17 != 3) {
                    throw M5.b.d(1560063531, c0761q, false);
                }
                c0761q.Q(1117708368);
                int integer = ((Context) c0761q.k(AndroidCompositionLocals_androidKt.f11688b)).getResources().getInteger(R.integer.rows_in_related_songs);
                int i18 = 0;
                while (i18 < integer) {
                    int i19 = i7 * i18;
                    c0761q.Q(1560082759);
                    if (i19 < list.size()) {
                        int size = ((i18 + 1) * i7) - i16;
                        if (size >= list.size()) {
                            size = list.size() - 1;
                        }
                        G7.d dVar = new G7.d(i19, size, i16);
                        List listZ0 = dVar.isEmpty() ? n7.u.l : AbstractC1998m.z0(list.subList(i19, dVar.f2712m + i16));
                        l lVar = l.f14684a;
                        o oVarD3 = androidx.compose.foundation.layout.c.d(lVar, listZ0.size() / i7);
                        c0761q.Q(1560099157);
                        if (i18 == integer - 1) {
                            z10 = false;
                            fH = 0;
                            c = 6;
                        } else {
                            z10 = false;
                            c = 6;
                            fH = m6.c.f19252a.H(6, c0761q);
                        }
                        c0761q.p(z10);
                        o oVarO = androidx.compose.foundation.layout.a.o(oVarD3, 0.0f, 0.0f, 0.0f, fH, 7);
                        C0119a c0119a2 = AbstractC0127i.f1076a;
                        D.S sB = P.b(new C0124f(m6.c.f19252a.F(c0761q)), g0.b.f14669u, c0761q, 0);
                        int i20 = c0761q.f8304P;
                        InterfaceC0744h0 interfaceC0744h0M2 = c0761q.m();
                        o oVarD4 = g0.a.d(c0761q, oVarO);
                        InterfaceC0161k.f1681b.getClass();
                        C0159i c0159i2 = C0160j.f1678b;
                        c0761q.U();
                        if (c0761q.f8303O) {
                            c0761q.l(c0159i2);
                        } else {
                            c0761q.d0();
                        }
                        C0735d.V(c0761q, sB, C0160j.f1679e);
                        C0735d.V(c0761q, interfaceC0744h0M2, C0160j.d);
                        C0158h c0158h2 = C0160j.f1680f;
                        if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i20))) {
                            AbstractC1347d.l(i20, c0761q, i20, c0158h2);
                        }
                        C0735d.V(c0761q, oVarD4, C0160j.c);
                        c0761q.Q(-1820334755);
                        for (Iterator it2 = listZ0.iterator(); it2.hasNext(); it2 = it) {
                            final KaraokeItem karaokeItem = (KaraokeItem) it2.next();
                            KaraokeItemType karaokeItemType = karaokeItem.f13109e;
                            int i21 = karaokeItemType == null ? -1 : Y1.f6660b[karaokeItemType.ordinal()];
                            S s6 = C0753m.f8270a;
                            if (i21 == 1) {
                                it = it2;
                                i12 = i15;
                                c0761q.Q(-1820331135);
                                o oVarB = D.T.b(lVar, 1.0f);
                                c0761q.Q(-1820324884);
                                boolean zH = ((i12 & 3670016) == 1048576) | ((i12 & 7168) == 2048) | c0761q.h(karaokeItem);
                                Object objG = c0761q.G();
                                if (zH || objG == s6) {
                                    r10 = 0;
                                    final boolean z11 = false ? 1 : 0;
                                    InterfaceC2772a interfaceC2772a2 = new InterfaceC2772a() { // from class: Q5.E1
                                        @Override // z7.InterfaceC2772a
                                        public final Object a() {
                                            switch (z11) {
                                                case 0:
                                                    String str2 = str;
                                                    interfaceC2785n.invoke(karaokeItem, str2 != null ? new F6.b(SourceType.PAGE, str2) : null);
                                                    break;
                                                default:
                                                    String str3 = str;
                                                    interfaceC2785n.invoke(karaokeItem, str3 != null ? new F6.b(SourceType.PAGE, str3) : null);
                                                    break;
                                            }
                                            return m7.q.f19267a;
                                        }
                                    };
                                    c0761q.a0(interfaceC2772a2);
                                    obj = interfaceC2772a2;
                                } else {
                                    r10 = 0;
                                    obj = objG;
                                }
                                c0761q.p(r10);
                                com.bumptech.glide.c.t(oVarB, karaokeItem, (InterfaceC2772a) obj, c0761q, r10);
                                c0761q.p(r10);
                            } else if (i21 != 2) {
                                c0761q.Q(-594773634);
                                c0761q.p(false);
                                it = it2;
                                i12 = i15;
                            } else {
                                c0761q.Q(-1820318604);
                                o oVarD5 = androidx.compose.foundation.layout.a.d(androidx.compose.foundation.layout.c.d(D.T.b(lVar, 1.0f), 1.0f), 1.0f);
                                c0761q.Q(-1820306804);
                                i12 = i15;
                                it = it2;
                                boolean zH2 = ((i12 & 7168) == 2048) | c0761q.h(karaokeItem) | ((i12 & 3670016) == 1048576);
                                Object objG2 = c0761q.G();
                                Object obj2 = objG2;
                                if (zH2 || objG2 == s6) {
                                    final int i22 = 1;
                                    InterfaceC2772a interfaceC2772a3 = new InterfaceC2772a() { // from class: Q5.E1
                                        @Override // z7.InterfaceC2772a
                                        public final Object a() {
                                            switch (i22) {
                                                case 0:
                                                    String str2 = str;
                                                    interfaceC2785n.invoke(karaokeItem, str2 != null ? new F6.b(SourceType.PAGE, str2) : null);
                                                    break;
                                                default:
                                                    String str3 = str;
                                                    interfaceC2785n.invoke(karaokeItem, str3 != null ? new F6.b(SourceType.PAGE, str3) : null);
                                                    break;
                                            }
                                            return m7.q.f19267a;
                                        }
                                    };
                                    c0761q.a0(interfaceC2772a3);
                                    obj2 = interfaceC2772a3;
                                }
                                c0761q.p(false);
                                com.bumptech.glide.c.i(oVarD5, karaokeItem, (InterfaceC2772a) obj2, c0761q, 0);
                                c0761q.p(false);
                            }
                            i15 = i12;
                        }
                        i11 = i15;
                        z6 = false;
                        c0761q.p(false);
                        i16 = 1;
                        c0761q.p(true);
                    } else {
                        i11 = i15;
                        z6 = false;
                    }
                    c0761q.p(z6);
                    i18 += i16;
                    i15 = i11;
                }
                c0761q.p(false);
            }
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: Q5.F1
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj3, Object obj4) throws XmlPullParserException, IOException {
                    ((Integer) obj4).getClass();
                    int iZ = C0735d.Z(i10 | 1);
                    InterfaceC2772a interfaceC2772a4 = interfaceC2772a;
                    String str2 = str;
                    io.sentry.config.a.w(oVar, list, i7, interfaceC2785n, loadingInterface$LoadingState, interfaceC2772a4, str2, (C0761q) obj3, iZ);
                    return m7.q.f19267a;
                }
            };
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:144:0x0363  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0367  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0374  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0382  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void x(final g0.o r37, final boolean r38, final java.lang.String r39, final int r40, final int r41, final int r42, final m7.i r43, final int r44, final int r45, final boolean r46, final z7.InterfaceC2785n r47, T.C0761q r48, final int r49, final int r50) {
        /*
            Method dump skipped, instruction units count: 1034
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.config.a.x(g0.o, boolean, java.lang.String, int, int, int, m7.i, int, int, boolean, z7.n, T.q, int, int):void");
    }

    public static final void y(final o oVar, final float f10, final int i7, final int i10, final int i11, final int i12, final m7.i iVar, final boolean z6, final InterfaceC2785n interfaceC2785n, C0761q c0761q, final int i13) {
        int i14;
        boolean z10;
        m.f(iVar, "multiplierColor");
        m.f(interfaceC2785n, "playSoundFx");
        c0761q.S(-170241229);
        if ((i13 & 6) == 0) {
            i14 = (c0761q.f(oVar) ? 4 : 2) | i13;
        } else {
            i14 = i13;
        }
        if ((i13 & 48) == 0) {
            i14 |= c0761q.c(f10) ? 32 : 16;
        }
        if ((i13 & 384) == 0) {
            i14 |= c0761q.d(i7) ? 256 : 128;
        }
        if ((i13 & 3072) == 0) {
            i14 |= c0761q.d(i10) ? 2048 : 1024;
        }
        if ((i13 & 24576) == 0) {
            i14 |= c0761q.d(i11) ? 16384 : 8192;
        }
        if ((196608 & i13) == 0) {
            i14 |= c0761q.d(i12) ? 131072 : 65536;
        }
        if ((1572864 & i13) == 0) {
            i14 |= c0761q.f(iVar) ? 1048576 : 524288;
        }
        if ((12582912 & i13) == 0) {
            i14 |= c0761q.g(z6) ? 8388608 : 4194304;
        }
        if ((100663296 & i13) == 0) {
            i14 |= c0761q.h(interfaceC2785n) ? 67108864 : 33554432;
        }
        if ((38347923 & i14) == 38347922 && c0761q.x()) {
            c0761q.K();
        } else {
            o oVarI = androidx.compose.foundation.layout.a.i(androidx.compose.foundation.layout.c.l(oVar, f10), (-f10) / 2, 0.0f, 2);
            m6.b bVar = m6.c.f19252a;
            bVar.getClass();
            c0761q.Q(1550105666);
            float fW = (float) (((double) bVar.w(6, c0761q)) * 0.625d);
            c0761q.p(false);
            c0761q.Q(1600613920);
            float f11 = 3;
            LayoutMode layoutMode = (LayoutMode) c0761q.k(AbstractC1692D.f18238a);
            if (layoutMode == LayoutMode.WIDE_ULTRA || layoutMode == LayoutMode.NARROW_NORMAL || layoutMode == LayoutMode.NARROW_ULTRA || layoutMode == LayoutMode.SQUARE_ULTRA) {
                z10 = false;
            } else {
                if (layoutMode != LayoutMode.WIDE_COMPACT && layoutMode != LayoutMode.NARROW_COMPACT && layoutMode != LayoutMode.SQUARE_COMPACT && layoutMode != LayoutMode.WIDE_NORMAL && layoutMode != LayoutMode.SQUARE_NORMAL) {
                    throw new k();
                }
                z10 = false;
                if (!A7.k.t(c0761q, 398348334, bVar, c0761q, false)) {
                    A7.k.r(c0761q, 398349423, bVar, c0761q, false);
                }
            }
            c0761q.p(z10);
            h6.e.b(oVarI, i7, i10, i11, i12, iVar, l6.d.c(c0761q).f18681N, fW, f.g(FontSize.H9, c0761q), f11, ((O5.a) c0761q.k(O5.b.f5155b)).f5143a, z6, interfaceC2785n, c0761q, (i14 >> 3) & 524272, (i14 >> 18) & 1008);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: U5.s
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i13 | 1);
                    boolean z11 = z6;
                    InterfaceC2785n interfaceC2785n2 = interfaceC2785n;
                    io.sentry.config.a.y(oVar, f10, i7, i10, i11, i12, iVar, z11, interfaceC2785n2, (C0761q) obj, iZ);
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static final void z(o oVar, InterfaceC2782k interfaceC2782k, DriveMode driveMode, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(294957737);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(interfaceC2782k) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.g(false) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.g(false) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.f(driveMode) ? 16384 : 8192;
        }
        if ((i10 & 9363) == 9362 && c0761q.x()) {
            c0761q.K();
        } else {
            AbstractC0120b.a(oVar, null, false, g.b(889410963, new y(driveMode, interfaceC2782k), c0761q), c0761q, (i10 & 14) | 3072, 6);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C1(oVar, interfaceC2782k, driveMode, i7, 0);
        }
    }
}

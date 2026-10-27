package X5;

import D.AbstractC0127i;
import D.AbstractC0133o;
import D.AbstractC0139v;
import D.C0119a;
import D.C0124f;
import D.C0138u;
import D.C0141x;
import D.C0142y;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import T.C0735d;
import T.C0761q;
import T.InterfaceC0744h0;
import com.stingray.karaoke.car.player.LeadVocalState;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.karaoke.car.utils.LayoutMode;
import h2.AbstractC1347d;
import java.util.Arrays;
import java.util.List;
import k6.AbstractC1692D;
import n0.C1853C;
import n0.C1886o;
import n0.C1891t;
import n6.C1967H;
import n6.C1972e;
import n7.AbstractC1999n;
import o6.C2048a;
import w.AbstractC2565A;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: X5.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0892y implements InterfaceC2786o {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final /* synthetic */ g0.o f10466A;
    public final /* synthetic */ K5.v B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final /* synthetic */ t6.h f10467C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final /* synthetic */ LeadVocalState f10468D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final /* synthetic */ x6.e f10469E;
    public final /* synthetic */ boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1967H f10470m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ PlayerStatus f10471n;
    public final /* synthetic */ boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f10472p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ m7.i f10473q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f10474r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ C1972e f10475s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ V8.N f10476t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f10477u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ boolean f10478v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ x.u0 f10479w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final /* synthetic */ T.X f10480x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final /* synthetic */ x.u0 f10481y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final /* synthetic */ g0.o f10482z;

    public C0892y(boolean z6, C1967H c1967h, PlayerStatus playerStatus, boolean z10, int i7, m7.i iVar, InterfaceC2785n interfaceC2785n, C1972e c1972e, V8.N n10, InterfaceC2772a interfaceC2772a, boolean z11, x.u0 u0Var, T.X x8, x.u0 u0Var2, g0.o oVar, g0.o oVar2, K5.v vVar, t6.h hVar, LeadVocalState leadVocalState, x6.e eVar) {
        this.l = z6;
        this.f10470m = c1967h;
        this.f10471n = playerStatus;
        this.o = z10;
        this.f10472p = i7;
        this.f10473q = iVar;
        this.f10474r = interfaceC2785n;
        this.f10475s = c1972e;
        this.f10476t = n10;
        this.f10477u = interfaceC2772a;
        this.f10478v = z11;
        this.f10479w = u0Var;
        this.f10480x = x8;
        this.f10481y = u0Var2;
        this.f10482z = oVar;
        this.f10466A = oVar2;
        this.B = vVar;
        this.f10467C = hVar;
        this.f10468D = leadVocalState;
        this.f10469E = eVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v19 */
    /* JADX WARN: Type inference failed for: r11v20 */
    /* JADX WARN: Type inference failed for: r11v36 */
    /* JADX WARN: Type inference failed for: r8v5 */
    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        V8.N n10;
        C0158h c0158h;
        boolean z6;
        g0.g gVar;
        C0142y c0142y;
        C0158h c0158h2;
        C0158h c0158h3;
        C0158h c0158h4;
        boolean z10;
        C0159i c0159i;
        g0.l lVar;
        C0761q c0761q;
        boolean z11;
        C0142y c0142y2;
        boolean z12;
        g0.o oVarE;
        g0.g gVar2;
        V8.N n11;
        boolean z13;
        T.X x8;
        String str;
        int i7;
        V8.N n12;
        boolean z14;
        T.X x9;
        boolean z15;
        T.X x10;
        ?? IntValue;
        boolean z16;
        C0892y c0892y = this;
        C0138u c0138u = (C0138u) obj;
        C0761q c0761q2 = (C0761q) obj2;
        int iIntValue = ((Number) obj3).intValue();
        A7.m.f(c0138u, "$this$BoxWithConstraints");
        if ((iIntValue & 6) == 0) {
            iIntValue |= c0761q2.f(c0138u) ? 4 : 2;
        }
        if ((iIntValue & 19) == 18 && c0761q2.x()) {
            c0761q2.K();
        } else {
            g0.l lVar2 = g0.l.f14684a;
            androidx.compose.animation.a.c(!c0892y.l, lVar2, AbstractC2565A.b(null, 3), AbstractC2565A.c(null, 3), null, b0.g.b(-287154089, new I5.y(c0138u, 18), c0761q2), c0761q2, 200112, 16);
            float fA = c0138u.a();
            m6.b bVar = m6.c.f19252a;
            boolean z17 = Float.compare(bVar.i0(c0761q2) * fA, bVar.h0(c0761q2)) > 0;
            g0.o oVarC = androidx.compose.foundation.layout.c.c(lVar2, 1.0f);
            C0119a c0119a = AbstractC0127i.f1076a;
            C0141x c0141xA = AbstractC0139v.a(new C0124f(bVar.H(6, c0761q2) * 2), g0.b.f14672x, c0761q2, 0);
            int i10 = c0761q2.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q2.m();
            g0.o oVarD = g0.a.d(c0761q2, oVarC);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i2 = C0160j.f1678b;
            c0761q2.U();
            if (c0761q2.f8303O) {
                c0761q2.l(c0159i2);
            } else {
                c0761q2.d0();
            }
            C0158h c0158h5 = C0160j.f1679e;
            C0735d.V(c0761q2, c0141xA, c0158h5);
            C0158h c0158h6 = C0160j.d;
            C0735d.V(c0761q2, interfaceC0744h0M, c0158h6);
            C0158h c0158h7 = C0160j.f1680f;
            if (c0761q2.f8303O || !A7.m.a(c0761q2.G(), Integer.valueOf(i10))) {
                AbstractC1347d.l(i10, c0761q2, i10, c0158h7);
            }
            C0158h c0158h8 = C0160j.c;
            C0735d.V(c0761q2, oVarD, c0158h8);
            C0142y c0142y3 = C0142y.f1119a;
            c0761q2.Q(-752791508);
            g0.g gVar3 = g0.b.l;
            boolean z18 = c0892y.l;
            V8.N n13 = c0892y.f10476t;
            if (z18) {
                n10 = n13;
                c0158h = c0158h6;
                z6 = z18;
                gVar = gVar3;
                c0142y = c0142y3;
                c0158h2 = c0158h8;
                c0158h3 = c0158h7;
                c0158h4 = c0158h5;
                z10 = false;
                c0159i = c0159i2;
                lVar = lVar2;
                c0761q = c0761q2;
            } else {
                g0.o oVarD2 = androidx.compose.foundation.layout.c.d(lVar2, 1.0f);
                if (z17) {
                    c0761q2.Q(-752773500);
                    float fH0 = bVar.h0(c0761q2);
                    c0761q2.Q(784174016);
                    float f10 = 800;
                    z11 = z18;
                    LayoutMode layoutMode = (LayoutMode) c0761q2.k(AbstractC1692D.f18238a);
                    c0142y2 = c0142y3;
                    if (layoutMode == LayoutMode.WIDE_ULTRA || layoutMode == LayoutMode.NARROW_NORMAL || layoutMode == LayoutMode.NARROW_ULTRA || layoutMode == LayoutMode.SQUARE_ULTRA) {
                        z12 = false;
                    } else {
                        if (layoutMode != LayoutMode.WIDE_COMPACT && layoutMode != LayoutMode.NARROW_COMPACT && layoutMode != LayoutMode.SQUARE_COMPACT && layoutMode != LayoutMode.WIDE_NORMAL && layoutMode != LayoutMode.SQUARE_NORMAL) {
                            throw new A9.k();
                        }
                        z12 = false;
                        if (!A7.k.t(c0761q2, 398348334, bVar, c0761q2, false)) {
                            A7.k.r(c0761q2, 398349423, bVar, c0761q2, false);
                        }
                    }
                    c0761q2.p(z12);
                    oVarE = androidx.compose.foundation.layout.c.b(androidx.compose.foundation.layout.c.f(lVar2, fH0, f10), bVar.i0(c0761q2));
                    c0761q2.p(z12);
                } else {
                    z11 = z18;
                    c0142y2 = c0142y3;
                    z12 = false;
                    c0761q2.Q(-752769237);
                    oVarE = androidx.compose.foundation.layout.c.e(lVar2, bVar.h0(c0761q2));
                    c0761q2.p(false);
                }
                g0.o oVarK = oVarD2.k(oVarE);
                C0.K kE = AbstractC0133o.e(gVar3, z12);
                int i11 = c0761q2.f8304P;
                InterfaceC0744h0 interfaceC0744h0M2 = c0761q2.m();
                g0.o oVarD3 = g0.a.d(c0761q2, oVarK);
                c0761q2.U();
                if (c0761q2.f8303O) {
                    c0761q2.l(c0159i2);
                } else {
                    c0761q2.d0();
                }
                C0735d.V(c0761q2, kE, c0158h5);
                C0735d.V(c0761q2, interfaceC0744h0M2, c0158h6);
                if (c0761q2.f8303O || !A7.m.a(c0761q2.G(), Integer.valueOf(i11))) {
                    AbstractC1347d.l(i11, c0761q2, i11, c0158h7);
                }
                C0735d.V(c0761q2, oVarD3, c0158h8);
                g0.o oVarC2 = androidx.compose.foundation.layout.c.c(lVar2, 1.0f);
                C0141x c0141xA2 = AbstractC0139v.a(AbstractC0127i.c, g0.b.f14673y, c0761q2, 48);
                int i12 = c0761q2.f8304P;
                InterfaceC0744h0 interfaceC0744h0M3 = c0761q2.m();
                g0.o oVarD4 = g0.a.d(c0761q2, oVarC2);
                c0761q2.U();
                if (c0761q2.f8303O) {
                    c0761q2.l(c0159i2);
                } else {
                    c0761q2.d0();
                }
                C0735d.V(c0761q2, c0141xA2, c0158h5);
                C0735d.V(c0761q2, interfaceC0744h0M3, c0158h6);
                if (c0761q2.f8303O || !A7.m.a(c0761q2.G(), Integer.valueOf(i12))) {
                    AbstractC1347d.l(i12, c0761q2, i12, c0158h7);
                }
                C0735d.V(c0761q2, oVarD4, c0158h8);
                g0.o oVarD5 = androidx.compose.foundation.layout.c.d(h6.n.e(lVar2, ((Number) c0892y.f10479w.f23639u.getValue()).floatValue()), 1.0f);
                T.X x11 = c0892y.f10480x;
                int i13 = ((n6.J) x11.getValue()).f20032h;
                int i14 = ((n6.J) x11.getValue()).f20031g;
                C1967H c1967h = c0892y.f10470m;
                if (c1967h != null) {
                    n11 = c1967h.f20022x;
                    gVar2 = gVar3;
                } else {
                    gVar2 = gVar3;
                    n11 = null;
                }
                c0761q2.Q(-633802797);
                if (n11 == null) {
                    x8 = null;
                    z13 = false;
                } else {
                    z13 = false;
                    x8 = C0735d.x(n11, c0761q2, 0);
                }
                c0761q2.p(z13);
                if (x8 == null || (str = (String) x8.getValue()) == null) {
                    str = "";
                }
                if (c1967h != null) {
                    n12 = c1967h.f20020v;
                    i7 = i14;
                } else {
                    i7 = i14;
                    n12 = null;
                }
                c0761q2.Q(-633795757);
                if (n12 == null) {
                    x9 = null;
                    z14 = false;
                } else {
                    z14 = false;
                    x9 = C0735d.x(n12, c0761q2, 0);
                }
                c0761q2.p(z14);
                int iIntValue2 = x9 != null ? ((Number) x9.getValue()).intValue() : 0;
                V8.N n14 = c1967h != null ? c1967h.f20010j : null;
                c0761q2.Q(-633790189);
                if (n14 == null) {
                    x10 = null;
                    z15 = false;
                } else {
                    z15 = false;
                    x10 = C0735d.x(n14, c0761q2, 0);
                }
                c0761q2.p(z15);
                if (x10 != null) {
                    c0158h = c0158h6;
                    IntValue = ((Number) x10.getValue()).intValue();
                } else {
                    c0158h = c0158h6;
                    IntValue = z15;
                }
                PlayerStatus playerStatus = PlayerStatus.PLAYING;
                n10 = n13;
                PlayerStatus playerStatus2 = c0892y.f10471n;
                boolean z19 = playerStatus2 == playerStatus ? true : z15;
                boolean z20 = c0892y.o;
                int i15 = c0892y.f10472p;
                m7.i iVar = c0892y.f10473q;
                z6 = z11;
                gVar = gVar2;
                C0142y c0142y4 = c0142y2;
                c0158h2 = c0158h8;
                c0158h4 = c0158h5;
                c0158h3 = c0158h7;
                c0159i = c0159i2;
                io.sentry.config.a.x(oVarD5, z20, str, iIntValue2, IntValue == true ? 1 : 0, i15, iVar, i13, i7, z19, c0892y.f10474r, c0761q2, 0, 0);
                g0.o oVarA = C0142y.a(c0142y4, androidx.compose.foundation.layout.c.d(h6.n.e(lVar2, ((Number) c0892y.f10481y.f23639u.getValue()).floatValue()), 1.0f), 1.0f);
                n6.J j3 = (n6.J) x11.getValue();
                long j10 = l6.d.c(c0761q2).f18676I;
                long j11 = l6.d.c(c0761q2).f18676I;
                long j12 = l6.d.c(c0761q2).f18677J;
                long j13 = l6.d.c(c0761q2).f18678K;
                long j14 = ((C1891t) iVar.l).f19453a;
                lVar = lVar2;
                c0142y = c0142y4;
                long j15 = ((C1891t) iVar.f19259m).f19453a;
                long j16 = l6.d.c(c0761q2).f18690W;
                long j17 = l6.d.c(c0761q2).f18692Y;
                c0761q2.Q(44593087);
                c0761q2.Q(256994523);
                Float fValueOf = Float.valueOf(0.0f);
                long j18 = C1891t.f19451j;
                m7.i iVar2 = new m7.i(fValueOf, new C1891t(j18));
                m7.i iVar3 = new m7.i(Float.valueOf(0.1f), new C1891t(j18));
                Float fValueOf2 = Float.valueOf(0.4f);
                long j19 = l6.e.f18779f;
                List listD = AbstractC1999n.D(iVar2, iVar3, new m7.i(fValueOf2, new C1891t(j19)), new m7.i(Float.valueOf(0.8f), new C1891t(j19)), new m7.i(Float.valueOf(0.9f), new C1891t(j18)), new m7.i(Float.valueOf(1.0f), new C1891t(j18)));
                z10 = false;
                c0761q2.p(false);
                m7.i[] iVarArr = (m7.i[]) listD.toArray(new m7.i[0]);
                C1853C c1853cK = C1886o.k((m7.i[]) Arrays.copyOf(iVarArr, iVarArr.length));
                c0761q2.p(false);
                c0761q2.Q(1359873354);
                c0761q2.Q(-79189516);
                m7.i iVar4 = new m7.i(Float.valueOf(0.0f), new C1891t(j18));
                m7.i iVar5 = new m7.i(Float.valueOf(0.1f), new C1891t(j18));
                Float fValueOf3 = Float.valueOf(0.4f);
                long j20 = l6.e.f18748K0;
                List listD2 = AbstractC1999n.D(iVar4, iVar5, new m7.i(fValueOf3, new C1891t(j20)), new m7.i(Float.valueOf(0.6f), new C1891t(j20)), new m7.i(Float.valueOf(0.9f), new C1891t(j18)), new m7.i(Float.valueOf(1.0f), new C1891t(j18)));
                c0761q2.p(false);
                m7.i[] iVarArr2 = (m7.i[]) listD2.toArray(new m7.i[0]);
                C1853C c1853cK2 = C1886o.k((m7.i[]) Arrays.copyOf(iVarArr2, iVarArr2.length));
                c0761q2.p(false);
                c0761q2.Q(426519017);
                c0761q2.Q(997363893);
                m7.i iVar6 = new m7.i(Float.valueOf(0.0f), new C1891t(j18));
                m7.i iVar7 = new m7.i(Float.valueOf(0.1f), new C1891t(j18));
                Float fValueOf4 = Float.valueOf(0.4f);
                long j21 = l6.e.f18750L0;
                List listD3 = AbstractC1999n.D(iVar6, iVar7, new m7.i(fValueOf4, new C1891t(j21)), new m7.i(Float.valueOf(0.6f), new C1891t(j21)), new m7.i(Float.valueOf(0.9f), new C1891t(j18)), new m7.i(Float.valueOf(1.0f), new C1891t(j18)));
                c0761q2.p(false);
                m7.i[] iVarArr3 = (m7.i[]) listD3.toArray(new m7.i[0]);
                C1853C c1853cK3 = C1886o.k((m7.i[]) Arrays.copyOf(iVarArr3, iVarArr3.length));
                c0761q2.p(false);
                C2048a c2048a = new C2048a(j10, j11, j12, j13, j15, j14, j14, j15, j16, j17, c1853cK, c1853cK2, c1853cK3);
                if (playerStatus2 == playerStatus) {
                    c0761q = c0761q2;
                    z16 = true;
                } else {
                    c0761q = c0761q2;
                    z16 = false;
                }
                c0892y = this;
                h6.q.g(oVarA, j3, c0892y.f10475s, n10, c2048a, c0892y.f10477u, c0892y.f10478v, z6, z16, c0761q, 805306880);
                c0761q.p(true);
                c0761q.p(true);
            }
            c0761q.p(z10);
            g0.o oVarD6 = androidx.compose.foundation.layout.c.d(C0142y.a(c0142y, lVar, 1.0f), 1.0f);
            C0.K kE2 = AbstractC0133o.e(gVar, z10);
            int i16 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M4 = c0761q.m();
            g0.o oVarD7 = g0.a.d(c0761q, oVarD6);
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, kE2, c0158h4);
            C0735d.V(c0761q, interfaceC0744h0M4, c0158h);
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i16))) {
                AbstractC1347d.l(i16, c0761q, i16, c0158h3);
            }
            C0735d.V(c0761q, oVarD7, c0158h2);
            g0.o oVar = !z6 ? c0892y.f10482z : c0892y.f10466A;
            K5.v vVar = c0892y.B;
            l9.l.l(oVar, c0892y.f10467C, vVar != null ? vVar.f3661b : null, vVar != null ? vVar.f3660a : null, n10, c0892y.f10468D, c0892y.f10469E, true, c0761q, 12582912, 0);
            c0761q.p(true);
            c0761q.p(true);
        }
        return m7.q.f19267a;
    }
}

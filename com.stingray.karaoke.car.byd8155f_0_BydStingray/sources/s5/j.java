package S5;

import C0.K;
import D.AbstractC0120b;
import D.AbstractC0127i;
import D.AbstractC0133o;
import D.AbstractC0139v;
import D.C0124f;
import D.C0141x;
import D.L;
import D.P;
import D.T;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import H7.H;
import Q.AbstractC0535v;
import Q.c1;
import Q5.C0549b;
import T.C0735d;
import T.C0753m;
import T.C0761q;
import T.InterfaceC0744h0;
import T.R0;
import T.S;
import X5.H0;
import androidx.lifecycle.InterfaceC0977o;
import androidx.lifecycle.f0;
import androidx.lifecycle.l0;
import b2.C0991a;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.karaoke.car.ui.navigation.NavigationType;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import com.stingray.karaoke.car.utils.FontSize;
import h2.AbstractC1347d;
import java.io.IOException;
import n3.C1930E;
import n3.C1945j;
import org.xmlpull.v1.XmlPullParserException;
import w.C2576g;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2787p;

/* JADX INFO: loaded from: classes.dex */
public final class j implements InterfaceC2787p {
    public final /* synthetic */ int l = 3;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f8012m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f8013n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f8014p;

    public j(PlayerStatus playerStatus, C1930E c1930e, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2) {
        this.f8013n = playerStatus;
        this.o = c1930e;
        this.f8012m = interfaceC2772a;
        this.f8014p = interfaceC2772a2;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // z7.InterfaceC2787p
    public final Object o(Object obj, Object obj2, Object obj3, Object obj4) throws XmlPullParserException, IOException {
        int i7;
        int i10;
        C0158h c0158h;
        C0158h c0158h2;
        int i11;
        boolean z6;
        C0761q c0761q;
        switch (this.l) {
            case 0:
                g0.o oVar = (g0.o) obj;
                L l = (L) obj2;
                C0761q c0761q2 = (C0761q) obj3;
                int iIntValue = ((Number) obj4).intValue();
                A7.m.f(oVar, "modifier");
                A7.m.f(l, "contentPadding");
                if ((iIntValue & 6) == 0) {
                    i7 = (c0761q2.f(oVar) ? 4 : 2) | iIntValue;
                } else {
                    i7 = iIntValue;
                }
                if ((iIntValue & 48) == 0) {
                    i7 |= c0761q2.f(l) ? 32 : 16;
                }
                if ((i7 & 147) == 146 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    com.bumptech.glide.d.e(oVar, (String) this.f8013n, (String) this.o, (String) this.f8014p, AbstractC2673a.X(R.string.Global_Retry, c0761q2), AbstractC2673a.X(R.string.Global_Cancel, c0761q2), this.f8012m, null, l, c0761q2, (i7 & 14) | ((i7 << 21) & 234881024), 128);
                }
                return m7.q.f19267a;
            case 1:
                g0.o oVar2 = (g0.o) obj;
                L l10 = (L) obj2;
                C0761q c0761q3 = (C0761q) obj3;
                int iIntValue2 = ((Number) obj4).intValue();
                A7.m.f(oVar2, "modifier");
                A7.m.f(l10, "contentPadding");
                if ((iIntValue2 & 6) == 0) {
                    i10 = (c0761q3.f(oVar2) ? 4 : 2) | iIntValue2;
                } else {
                    i10 = iIntValue2;
                }
                if ((iIntValue2 & 48) == 0) {
                    i10 |= c0761q3.f(l10) ? 32 : 16;
                }
                if ((i10 & 147) == 146 && c0761q3.x()) {
                    c0761q3.K();
                } else {
                    g0.o oVarN = androidx.compose.foundation.layout.a.n(h6.r.t(oVar2, h6.r.q(c0761q3)), AbstractC2673a.J(l10, c0761q3), AbstractC2673a.K(l10, c0761q3) * 2.5f, AbstractC2673a.I(l10, c0761q3), AbstractC2673a.H(l10, c0761q3) * 2.5f);
                    g0.e eVar = g0.b.f14673y;
                    C0141x c0141xA = AbstractC0139v.a(AbstractC0127i.f1079f, eVar, c0761q3, 54);
                    int i12 = c0761q3.f8304P;
                    InterfaceC0744h0 interfaceC0744h0M = c0761q3.m();
                    g0.o oVarD = g0.a.d(c0761q3, oVarN);
                    InterfaceC0161k.f1681b.getClass();
                    C0159i c0159i = C0160j.f1678b;
                    c0761q3.U();
                    if (c0761q3.f8303O) {
                        c0761q3.l(c0159i);
                    } else {
                        c0761q3.d0();
                    }
                    C0158h c0158h3 = C0160j.f1679e;
                    C0735d.V(c0761q3, c0141xA, c0158h3);
                    C0158h c0158h4 = C0160j.d;
                    C0735d.V(c0761q3, interfaceC0744h0M, c0158h4);
                    C0158h c0158h5 = C0160j.f1680f;
                    if (c0761q3.f8303O || !A7.m.a(c0761q3.G(), Integer.valueOf(i12))) {
                        AbstractC1347d.l(i12, c0761q3, i12, c0158h5);
                    }
                    C0158h c0158h6 = C0160j.c;
                    C0735d.V(c0761q3, oVarD, c0158h6);
                    g0.l lVar = g0.l.f14684a;
                    g0.o oVarD2 = androidx.compose.foundation.layout.c.d(lVar, 1.0f);
                    K kE = AbstractC0133o.e(g0.b.l, false);
                    int i13 = c0761q3.f8304P;
                    InterfaceC0744h0 interfaceC0744h0M2 = c0761q3.m();
                    g0.o oVarD3 = g0.a.d(c0761q3, oVarD2);
                    c0761q3.U();
                    if (c0761q3.f8303O) {
                        c0761q3.l(c0159i);
                    } else {
                        c0761q3.d0();
                    }
                    C0735d.V(c0761q3, kE, c0158h3);
                    C0735d.V(c0761q3, interfaceC0744h0M2, c0158h4);
                    if (c0761q3.f8303O || !A7.m.a(c0761q3.G(), Integer.valueOf(i13))) {
                        AbstractC1347d.l(i13, c0761q3, i13, c0158h5);
                    }
                    C0735d.V(c0761q3, oVarD3, c0158h6);
                    androidx.compose.foundation.layout.b bVar = androidx.compose.foundation.layout.b.f11474a;
                    m6.b bVar2 = m6.c.f19252a;
                    h6.q.a(l9.l.W(R.drawable.brand_logo_color, c0761q3, 0), null, bVar.a(androidx.compose.foundation.layout.a.k(androidx.compose.foundation.layout.c.e(lVar, bVar2.D(c0761q3)), bVar2.E(c0761q3)), g0.b.f14662m), null, null, 0.0f, null, c0761q3, 48, 120);
                    c0761q3.p(true);
                    g0.o oVarD4 = androidx.compose.foundation.layout.c.d(lVar, 0.9f);
                    C0141x c0141xA2 = AbstractC0139v.a(new C0124f(bVar2.F(c0761q3) * 0.5f), eVar, c0761q3, 48);
                    int i14 = c0761q3.f8304P;
                    InterfaceC0744h0 interfaceC0744h0M3 = c0761q3.m();
                    g0.o oVarD5 = g0.a.d(c0761q3, oVarD4);
                    c0761q3.U();
                    if (c0761q3.f8303O) {
                        c0761q3.l(c0159i);
                    } else {
                        c0761q3.d0();
                    }
                    C0735d.V(c0761q3, c0141xA2, c0158h3);
                    C0735d.V(c0761q3, interfaceC0744h0M3, c0158h4);
                    if (c0761q3.f8303O || !A7.m.a(c0761q3.G(), Integer.valueOf(i14))) {
                        c0158h = c0158h5;
                        AbstractC1347d.l(i14, c0761q3, i14, c0158h);
                        c0158h2 = c0158h6;
                    } else {
                        c0158h2 = c0158h6;
                        c0158h = c0158h5;
                    }
                    C0735d.V(c0761q3, oVarD5, c0158h2);
                    String strX = AbstractC2673a.X(R.string.Driving_WarningVehiculeInMotion_Title, c0761q3);
                    long j3 = l6.d.c(c0761q3).f18696b;
                    long jG = h6.f.g(FontSize.f13042H8, c0761q3);
                    Y0.i iVar = new Y0.i(3);
                    R0 r02 = O5.c.f5157b;
                    C0158h c0158h7 = c0158h2;
                    C0158h c0158h8 = c0158h;
                    c1.b(strX, null, j3, jG, null, null, null, 0L, null, iVar, 0L, 0, false, 0, 0, null, ((O5.d) c0761q3.k(r02)).f5160e, c0761q3, 0, 0, 65010);
                    c1.b(AbstractC2673a.X(R.string.Driving_WarningVehiculeInMotion, c0761q3), null, l6.d.c(c0761q3).f18696b, h6.f.g(FontSize.H10, c0761q3), null, null, null, 0L, null, new Y0.i(3), 0L, 0, false, 0, 0, null, ((O5.d) c0761q3.k(r02)).f5159b, c0761q3, 0, 0, 65010);
                    c0761q3.p(true);
                    g0.o oVarO = androidx.compose.foundation.layout.a.o(androidx.compose.foundation.layout.a.p(lVar), 0.0f, bVar2.H(6, c0761q3), 0.0f, 0.0f, 13);
                    C0141x c0141xA3 = AbstractC0139v.a(new C0124f(bVar2.H(6, c0761q3)), eVar, c0761q3, 48);
                    int i15 = c0761q3.f8304P;
                    InterfaceC0744h0 interfaceC0744h0M4 = c0761q3.m();
                    g0.o oVarD6 = g0.a.d(c0761q3, oVarO);
                    c0761q3.U();
                    if (c0761q3.f8303O) {
                        c0761q3.l(c0159i);
                    } else {
                        c0761q3.d0();
                    }
                    C0735d.V(c0761q3, c0141xA3, c0158h3);
                    C0735d.V(c0761q3, interfaceC0744h0M4, c0158h4);
                    if (c0761q3.f8303O || !A7.m.a(c0761q3.G(), Integer.valueOf(i15))) {
                        AbstractC1347d.l(i15, c0761q3, i15, c0158h8);
                    }
                    C0735d.V(c0761q3, oVarD6, c0158h7);
                    g0.o oVarD7 = androidx.compose.foundation.layout.c.d(lVar, 1.0f);
                    String strX2 = AbstractC2673a.X(R.string.Global_TermsAndConditions, c0761q3);
                    c0761q3.Q(1891378358);
                    InterfaceC2772a interfaceC2772a = this.f8012m;
                    boolean zF = c0761q3.f(interfaceC2772a);
                    Object objG = c0761q3.G();
                    S s6 = C0753m.f8270a;
                    if (zF || objG == s6) {
                        objG = new C0549b(interfaceC2772a, 20);
                        c0761q3.a0(objG);
                    }
                    c0761q3.p(false);
                    io.sentry.config.a.o(oVarD7, null, false, strX2, 0L, null, false, null, null, 0L, 0.0f, (InterfaceC2772a) objG, c0761q3, 6, 0, 2038);
                    g0.o oVarD8 = androidx.compose.foundation.layout.c.d(lVar, 1.0f);
                    String strX3 = AbstractC2673a.X(R.string.Global_PrivacyPolicy, c0761q3);
                    c0761q3.Q(1891386040);
                    InterfaceC2772a interfaceC2772a2 = (InterfaceC2772a) this.o;
                    boolean zF2 = c0761q3.f(interfaceC2772a2);
                    Object objG2 = c0761q3.G();
                    if (zF2 || objG2 == s6) {
                        objG2 = new C0549b(interfaceC2772a2, 21);
                        c0761q3.a0(objG2);
                    }
                    c0761q3.p(false);
                    io.sentry.config.a.o(oVarD8, null, false, strX3, 0L, null, false, null, null, 0L, 0.0f, (InterfaceC2772a) objG2, c0761q3, 6, 0, 2038);
                    g0.o oVarD9 = androidx.compose.foundation.layout.c.d(lVar, 1.0f);
                    String strX4 = AbstractC2673a.X(R.string.Global_Accept, c0761q3);
                    c0761q3.Q(1891393872);
                    InterfaceC2772a interfaceC2772a3 = (InterfaceC2772a) this.f8014p;
                    boolean zF3 = c0761q3.f(interfaceC2772a3);
                    String str = (String) this.f8013n;
                    boolean zF4 = zF3 | c0761q3.f(str);
                    Object objG3 = c0761q3.G();
                    if (zF4 || objG3 == s6) {
                        objG3 = new a(2, str, interfaceC2772a3);
                        c0761q3.a0(objG3);
                    }
                    c0761q3.p(false);
                    io.sentry.config.a.p(oVarD9, null, false, strX4, 0L, null, null, 0L, 0.0f, (InterfaceC2772a) objG3, c0761q3, 6, 502);
                    c0761q3.p(true);
                    c0761q3.p(true);
                }
                return m7.q.f19267a;
            case 2:
                g0.o oVar3 = (g0.o) obj;
                L l11 = (L) obj2;
                C0761q c0761q4 = (C0761q) obj3;
                int iIntValue3 = ((Number) obj4).intValue();
                A7.m.f(oVar3, "modifier");
                A7.m.f(l11, "contentPadding");
                if ((iIntValue3 & 6) == 0) {
                    i11 = (c0761q4.f(oVar3) ? 4 : 2) | iIntValue3;
                } else {
                    i11 = iIntValue3;
                }
                if ((iIntValue3 & 48) == 0) {
                    i11 |= c0761q4.f(l11) ? 32 : 16;
                }
                if ((i11 & 147) == 146 && c0761q4.x()) {
                    c0761q4.K();
                } else {
                    C0141x c0141xA4 = AbstractC0139v.a(AbstractC0127i.c, g0.b.f14673y, c0761q4, 48);
                    int i16 = c0761q4.f8304P;
                    InterfaceC0744h0 interfaceC0744h0M5 = c0761q4.m();
                    g0.o oVarD10 = g0.a.d(c0761q4, oVar3);
                    InterfaceC0161k.f1681b.getClass();
                    C0159i c0159i2 = C0160j.f1678b;
                    c0761q4.U();
                    if (c0761q4.f8303O) {
                        c0761q4.l(c0159i2);
                    } else {
                        c0761q4.d0();
                    }
                    C0158h c0158h9 = C0160j.f1679e;
                    C0735d.V(c0761q4, c0141xA4, c0158h9);
                    C0158h c0158h10 = C0160j.d;
                    C0735d.V(c0761q4, interfaceC0744h0M5, c0158h10);
                    C0158h c0158h11 = C0160j.f1680f;
                    if (c0761q4.f8303O || !A7.m.a(c0761q4.G(), Integer.valueOf(i16))) {
                        AbstractC1347d.l(i16, c0761q4, i16, c0158h11);
                    }
                    C0158h c0158h12 = C0160j.c;
                    C0735d.V(c0761q4, oVarD10, c0158h12);
                    g0.l lVar2 = g0.l.f14684a;
                    g0.o oVarJ = androidx.compose.foundation.layout.a.j(lVar2, l11);
                    D.S sB = P.b(AbstractC0127i.f1079f, g0.b.f14670v, c0761q4, 54);
                    int i17 = c0761q4.f8304P;
                    InterfaceC0744h0 interfaceC0744h0M6 = c0761q4.m();
                    g0.o oVarD11 = g0.a.d(c0761q4, oVarJ);
                    c0761q4.U();
                    if (c0761q4.f8303O) {
                        c0761q4.l(c0159i2);
                    } else {
                        c0761q4.d0();
                    }
                    C0735d.V(c0761q4, sB, c0158h9);
                    C0735d.V(c0761q4, interfaceC0744h0M6, c0158h10);
                    if (c0761q4.f8303O || !A7.m.a(c0761q4.G(), Integer.valueOf(i17))) {
                        AbstractC1347d.l(i17, c0761q4, i17, c0158h11);
                    }
                    C0735d.V(c0761q4, oVarD11, c0158h12);
                    m6.b bVar3 = m6.c.f19252a;
                    AbstractC0120b.b(c0761q4, androidx.compose.foundation.layout.c.l(lVar2, bVar3.I(c0761q4)));
                    h6.q.a(l9.l.W(R.drawable.brand_logo_color, c0761q4, 0), null, androidx.compose.foundation.layout.a.k(androidx.compose.foundation.layout.c.e(T.b(lVar2, 1.0f), bVar3.D(c0761q4)), bVar3.E(c0761q4)), null, null, 0.0f, null, c0761q4, 48, 120);
                    g0.o oVarL = androidx.compose.foundation.layout.c.l(lVar2, bVar3.I(c0761q4));
                    c0761q4.Q(-692408644);
                    String str2 = (String) this.o;
                    boolean zF5 = c0761q4.f(str2);
                    Object objG4 = c0761q4.G();
                    if (zF5 || objG4 == C0753m.f8270a) {
                        objG4 = new e(str2, 3);
                        c0761q4.a0(objG4);
                    }
                    c0761q4.p(false);
                    AbstractC0535v.a(l9.l.W(R.drawable.ic_close, c0761q4, 0), null, com.bumptech.glide.c.Q(7, oVarL, (InterfaceC2772a) objG4, false), l6.d.c(c0761q4).f18696b, c0761q4, 48, 0);
                    c0761q4.p(true);
                    int i18 = o.f8021a[((LoadingInterface$LoadingState) this.f8014p).ordinal()];
                    if (i18 == 1) {
                        z6 = true;
                        c0761q = c0761q4;
                        c0761q.Q(-1412694559);
                        io.sentry.config.a.t(null, 0L, c0761q, 0, 3);
                        c0761q.p(false);
                    } else if (i18 != 2) {
                        c0761q4.Q(-843700360);
                        g0.o oVarD12 = androidx.compose.foundation.layout.c.d(lVar2, 1.0f);
                        long j10 = l6.d.c(c0761q4).f18696b;
                        long jG2 = h6.f.g(FontSize.Subtitle1, c0761q4);
                        long j11 = l6.d.c(c0761q4).f18707h0;
                        long j12 = l6.d.c(c0761q4).f18709i0;
                        c0761q4.Q(922098432);
                        float fO = bVar3.O(6, c0761q4) * 0.25f;
                        c0761q4.p(false);
                        K6.n nVar = new K6.n(j11, j12, fO, bVar3.P(c0761q4));
                        L lC = androidx.compose.foundation.layout.a.c(bVar3.O(6, c0761q4), 0.0f, bVar3.O(6, c0761q4) * 0.5f, AbstractC2673a.H(l11, c0761q4), 2);
                        c0761q = c0761q4;
                        w0.c.k(oVarD12, null, (String) this.f8013n, j10, jG2, 0, 0, null, null, false, null, nVar, lC, c0761q, 6, 0, 2018);
                        c0761q.p(false);
                        z6 = true;
                    } else {
                        z6 = true;
                        c0761q = c0761q4;
                        c0761q.Q(-1412692333);
                        H.b(null, null, false, this.f8012m, c0761q, 0, 7);
                        c0761q.p(false);
                    }
                    c0761q.p(z6);
                }
                return m7.q.f19267a;
            default:
                C0761q c0761q5 = (C0761q) obj3;
                ((Number) obj4).intValue();
                A7.m.f((C2576g) obj, "$this$composable");
                A7.m.f((C1945j) obj2, "it");
                c0761q5.R(1890788296);
                l0 l0VarA = c2.b.a(c0761q5);
                if (l0VarA == null) {
                    throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner");
                }
                N6.f fVarU = H.u(l0VarA, c0761q5);
                c0761q5.R(1729797275);
                f0 f0VarB0 = C7.a.b0(H0.class, l0VarA, fVarU, l0VarA instanceof InterfaceC0977o ? ((InterfaceC0977o) l0VarA).e() : C0991a.f12202b, c0761q5);
                c0761q5.p(false);
                c0761q5.p(false);
                H0 h02 = (H0) f0VarB0;
                h02.f12239i = this.f8012m;
                h02.f12240j = (InterfaceC2772a) this.f8014p;
                h02.m(NavigationType.PLAYER);
                l9.l.v((PlayerStatus) this.f8013n, h02, (C1930E) this.o, c0761q5, 0);
                return m7.q.f19267a;
        }
    }

    public j(LoadingInterface$LoadingState loadingInterface$LoadingState, InterfaceC2772a interfaceC2772a, String str, String str2) {
        this.f8014p = loadingInterface$LoadingState;
        this.f8012m = interfaceC2772a;
        this.f8013n = str;
        this.o = str2;
    }

    public j(String str, String str2, String str3, InterfaceC2772a interfaceC2772a) {
        this.f8013n = str;
        this.o = str2;
        this.f8014p = str3;
        this.f8012m = interfaceC2772a;
    }

    public j(InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, InterfaceC2772a interfaceC2772a3, String str) {
        this.f8012m = interfaceC2772a;
        this.o = interfaceC2772a2;
        this.f8014p = interfaceC2772a3;
        this.f8013n = str;
    }
}

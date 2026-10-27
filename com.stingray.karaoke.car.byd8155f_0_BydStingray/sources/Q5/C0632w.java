package Q5;

import D.AbstractC0133o;
import I5.C0326b;
import T.C0735d;
import T.C0753m;
import T.C0761q;
import androidx.lifecycle.InterfaceC0977o;
import b2.C0991a;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import java.util.Arrays;
import java.util.List;
import n0.C1853C;
import n0.C1886o;
import n0.C1891t;
import n7.AbstractC1999n;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: Q5.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0632w implements InterfaceC2786o {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final C0632w f7098m = new C0632w(0);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C0632w f7099n = new C0632w(1);
    public static final C0632w o = new C0632w(2);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final C0632w f7100p = new C0632w(3);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final C0632w f7101q = new C0632w(4);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final C0632w f7102r = new C0632w(5);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final C0632w f7103s = new C0632w(6);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final C0632w f7104t = new C0632w(7);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final C0632w f7105u = new C0632w(8);
    public final /* synthetic */ int l;

    public /* synthetic */ C0632w(int i7) {
        this.l = i7;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        T.S s6;
        T.S s10;
        switch (this.l) {
            case 0:
                D.L l = (D.L) obj;
                C0761q c0761q = (C0761q) obj2;
                int iIntValue = ((Number) obj3).intValue();
                A7.m.f(l, "contentPadding");
                if ((iIntValue & 6) == 0) {
                    iIntValue |= c0761q.f(l) ? 4 : 2;
                }
                if ((iIntValue & 19) == 18 && c0761q.x()) {
                    c0761q.K();
                } else {
                    w0.c.g(null, AbstractC2673a.X(R.string.Driving_FeatureDisabled, c0761q), l, c0761q, (iIntValue << 6) & 896);
                }
                return m7.q.f19267a;
            case 1:
                String str = (String) obj;
                C0761q c0761q2 = (C0761q) obj2;
                int iIntValue2 = ((Number) obj3).intValue();
                A7.m.f(str, "id");
                if ((iIntValue2 & 6) == 0) {
                    iIntValue2 |= c0761q2.f(str) ? 4 : 2;
                }
                int i7 = iIntValue2 & 19;
                m7.q qVar = m7.q.f19267a;
                if (i7 == 18 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    c0761q2.R(1890788296);
                    androidx.lifecycle.l0 l0VarA = c2.b.a(c0761q2);
                    if (l0VarA == null) {
                        throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner");
                    }
                    N6.f fVarU = H7.H.u(l0VarA, c0761q2);
                    c0761q2.R(1729797275);
                    androidx.lifecycle.f0 f0VarB0 = C7.a.b0(h6.p.class, l0VarA, fVarU, l0VarA instanceof InterfaceC0977o ? ((InterfaceC0977o) l0VarA).e() : C0991a.f12202b, c0761q2);
                    c0761q2.p(false);
                    c0761q2.p(false);
                    h6.p pVar = (h6.p) f0VarB0;
                    String str2 = (String) pVar.o.getValue();
                    LoadingInterface$LoadingState loadingInterface$LoadingStateE = pVar.e();
                    c0761q2.Q(-988068927);
                    boolean zH = c0761q2.h(pVar);
                    Object objG = c0761q2.G();
                    T.S s11 = C0753m.f8270a;
                    if (zH || objG == s11) {
                        s6 = s11;
                        objG = new C0326b(0, pVar, h6.p.class, "reloadData", "reloadData(Z)V", 0, 2);
                        c0761q2.a0(objG);
                    } else {
                        s6 = s11;
                    }
                    c0761q2.p(false);
                    w0.c.i(str, str2, loadingInterface$LoadingStateE, (InterfaceC2772a) objG, c0761q2, iIntValue2 & 14);
                    c0761q2.Q(-988065598);
                    boolean zH2 = c0761q2.h(pVar);
                    Object objG2 = c0761q2.G();
                    if (zH2 || objG2 == s6) {
                        objG2 = new C0636x(pVar, null);
                        c0761q2.a0(objG2);
                    }
                    c0761q2.p(false);
                    C0735d.f(c0761q2, qVar, (InterfaceC2785n) objG2);
                }
                return qVar;
            case 2:
                String str3 = (String) obj;
                C0761q c0761q3 = (C0761q) obj2;
                int iIntValue3 = ((Number) obj3).intValue();
                A7.m.f(str3, "id");
                if ((iIntValue3 & 6) == 0) {
                    iIntValue3 |= c0761q3.f(str3) ? 4 : 2;
                }
                int i10 = iIntValue3 & 19;
                m7.q qVar2 = m7.q.f19267a;
                if (i10 == 18 && c0761q3.x()) {
                    c0761q3.K();
                } else {
                    c0761q3.R(1890788296);
                    androidx.lifecycle.l0 l0VarA2 = c2.b.a(c0761q3);
                    if (l0VarA2 == null) {
                        throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner");
                    }
                    N6.f fVarU2 = H7.H.u(l0VarA2, c0761q3);
                    c0761q3.R(1729797275);
                    androidx.lifecycle.f0 f0VarB02 = C7.a.b0(h6.l.class, l0VarA2, fVarU2, l0VarA2 instanceof InterfaceC0977o ? ((InterfaceC0977o) l0VarA2).e() : C0991a.f12202b, c0761q3);
                    c0761q3.p(false);
                    c0761q3.p(false);
                    h6.l lVar = (h6.l) f0VarB02;
                    String str4 = (String) lVar.o.getValue();
                    LoadingInterface$LoadingState loadingInterface$LoadingStateE2 = lVar.e();
                    c0761q3.Q(519523042);
                    boolean zH3 = c0761q3.h(lVar);
                    Object objG3 = c0761q3.G();
                    T.S s12 = C0753m.f8270a;
                    if (zH3 || objG3 == s12) {
                        s10 = s12;
                        C0326b c0326b = new C0326b(0, lVar, h6.l.class, "reloadData", "reloadData(Z)V", 0, 3);
                        c0761q3.a0(c0326b);
                        objG3 = c0326b;
                    } else {
                        s10 = s12;
                    }
                    c0761q3.p(false);
                    w0.c.i(str3, str4, loadingInterface$LoadingStateE2, (InterfaceC2772a) objG3, c0761q3, iIntValue3 & 14);
                    c0761q3.Q(519526371);
                    boolean zH4 = c0761q3.h(lVar);
                    Object objG4 = c0761q3.G();
                    if (zH4 || objG4 == s10) {
                        objG4 = new C0640y(lVar, null);
                        c0761q3.a0(objG4);
                    }
                    c0761q3.p(false);
                    C0735d.f(c0761q3, qVar2, (InterfaceC2785n) objG4);
                }
                return qVar2;
            case 3:
                D.L l10 = (D.L) obj;
                C0761q c0761q4 = (C0761q) obj2;
                int iIntValue4 = ((Number) obj3).intValue();
                A7.m.f(l10, "contentPadding");
                if ((iIntValue4 & 6) == 0) {
                    iIntValue4 |= c0761q4.f(l10) ? 4 : 2;
                }
                if ((iIntValue4 & 19) == 18 && c0761q4.x()) {
                    c0761q4.K();
                } else {
                    w0.c.g(null, AbstractC2673a.X(R.string.Driving_FeatureDisabled, c0761q4), l10, c0761q4, (iIntValue4 << 6) & 896);
                }
                return m7.q.f19267a;
            case 4:
                D.L l11 = (D.L) obj;
                C0761q c0761q5 = (C0761q) obj2;
                int iIntValue5 = ((Number) obj3).intValue();
                A7.m.f(l11, "contentPadding");
                if ((iIntValue5 & 6) == 0) {
                    iIntValue5 |= c0761q5.f(l11) ? 4 : 2;
                }
                if ((iIntValue5 & 19) == 18 && c0761q5.x()) {
                    c0761q5.K();
                } else {
                    l9.l.g(null, l11, c0761q5, (iIntValue5 << 3) & 112);
                }
                return m7.q.f19267a;
            case 5:
                D.L l12 = (D.L) obj;
                C0761q c0761q6 = (C0761q) obj2;
                int iIntValue6 = ((Number) obj3).intValue();
                A7.m.f(l12, "contentPadding");
                if ((iIntValue6 & 6) == 0) {
                    iIntValue6 |= c0761q6.f(l12) ? 4 : 2;
                }
                if ((iIntValue6 & 19) == 18 && c0761q6.x()) {
                    c0761q6.K();
                } else {
                    w0.c.g(null, AbstractC2673a.X(R.string.Driving_FeatureDisabled, c0761q6), l12, c0761q6, (iIntValue6 << 6) & 896);
                }
                return m7.q.f19267a;
            case 6:
                C0761q c0761q7 = (C0761q) obj2;
                ((Number) obj3).intValue();
                A7.m.f((w.q) obj, "$this$AnimatedVisibility");
                g0.o oVarC = androidx.compose.foundation.layout.c.c(g0.l.f14684a, 1.0f);
                c0761q7.Q(-440252848);
                c0761q7.Q(50679456);
                List listD = AbstractC1999n.D(new m7.i(Float.valueOf(0.0f), new C1891t(l6.e.f18780f0)), new m7.i(Float.valueOf(0.35f), new C1891t(l6.e.f18782g0)), new m7.i(Float.valueOf(0.65f), new C1891t(l6.e.f18784h0)), new m7.i(Float.valueOf(1.0f), new C1891t(l6.e.f18786i0)));
                c0761q7.p(false);
                m7.i[] iVarArr = (m7.i[]) listD.toArray(new m7.i[0]);
                C1853C c1853cN = C1886o.n((m7.i[]) Arrays.copyOf(iVarArr, iVarArr.length));
                c0761q7.p(false);
                AbstractC0133o.a(androidx.compose.foundation.a.a(oVarC, c1853cN, null, 6), c0761q7, 0);
                return m7.q.f19267a;
            case 7:
                String str5 = (String) obj;
                C0761q c0761q8 = (C0761q) obj2;
                int iIntValue7 = ((Number) obj3).intValue();
                A7.m.f(str5, "id");
                if ((iIntValue7 & 6) == 0) {
                    iIntValue7 |= c0761q8.f(str5) ? 4 : 2;
                }
                if ((iIntValue7 & 19) == 18 && c0761q8.x()) {
                    c0761q8.K();
                } else {
                    io.sentry.config.a.l(str5, c0761q8, iIntValue7 & 14);
                }
                return m7.q.f19267a;
            default:
                ((Number) obj3).intValue();
                A7.m.f((List) obj, "it");
                return m7.q.f19267a;
        }
    }
}

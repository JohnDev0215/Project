package I5;

import H7.H;
import T.C0735d;
import T.C0753m;
import T.C0761q;
import T.S;
import androidx.lifecycle.InterfaceC0977o;
import androidx.lifecycle.f0;
import androidx.lifecycle.l0;
import b2.C0991a;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class d implements InterfaceC2786o {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final d f2948m = new d(0);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final d f2949n = new d(1);
    public final /* synthetic */ int l;

    public /* synthetic */ d(int i7) {
        this.l = i7;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        S s6;
        S s10;
        switch (this.l) {
            case 0:
                String str = (String) obj;
                C0761q c0761q = (C0761q) obj2;
                int iIntValue = ((Number) obj3).intValue();
                A7.m.f(str, "id");
                if ((iIntValue & 6) == 0) {
                    iIntValue |= c0761q.f(str) ? 4 : 2;
                }
                int i7 = iIntValue & 19;
                m7.q qVar = m7.q.f19267a;
                if (i7 == 18 && c0761q.x()) {
                    c0761q.K();
                } else {
                    c0761q.R(1890788296);
                    l0 l0VarA = c2.b.a(c0761q);
                    if (l0VarA == null) {
                        throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner");
                    }
                    N6.f fVarU = H.u(l0VarA, c0761q);
                    c0761q.R(1729797275);
                    f0 f0VarB0 = C7.a.b0(h6.p.class, l0VarA, fVarU, l0VarA instanceof InterfaceC0977o ? ((InterfaceC0977o) l0VarA).e() : C0991a.f12202b, c0761q);
                    c0761q.p(false);
                    c0761q.p(false);
                    h6.p pVar = (h6.p) f0VarB0;
                    String str2 = (String) pVar.o.getValue();
                    LoadingInterface$LoadingState loadingInterface$LoadingStateE = pVar.e();
                    c0761q.Q(-1079487125);
                    boolean zH = c0761q.h(pVar);
                    Object objG = c0761q.G();
                    S s11 = C0753m.f8270a;
                    if (zH || objG == s11) {
                        s6 = s11;
                        objG = new C0326b(0, pVar, h6.p.class, "reloadData", "reloadData(Z)V", 0, 0);
                        c0761q.a0(objG);
                    } else {
                        s6 = s11;
                    }
                    c0761q.p(false);
                    w0.c.i(str, str2, loadingInterface$LoadingStateE, (InterfaceC2772a) objG, c0761q, iIntValue & 14);
                    c0761q.Q(-1079482772);
                    boolean zH2 = c0761q.h(pVar);
                    Object objG2 = c0761q.G();
                    if (zH2 || objG2 == s6) {
                        objG2 = new C0327c(pVar, null);
                        c0761q.a0(objG2);
                    }
                    c0761q.p(false);
                    C0735d.f(c0761q, qVar, (InterfaceC2785n) objG2);
                }
                return qVar;
            default:
                String str3 = (String) obj;
                C0761q c0761q2 = (C0761q) obj2;
                int iIntValue2 = ((Number) obj3).intValue();
                A7.m.f(str3, "id");
                if ((iIntValue2 & 6) == 0) {
                    iIntValue2 |= c0761q2.f(str3) ? 4 : 2;
                }
                int i10 = iIntValue2 & 19;
                m7.q qVar2 = m7.q.f19267a;
                if (i10 == 18 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    c0761q2.R(1890788296);
                    l0 l0VarA2 = c2.b.a(c0761q2);
                    if (l0VarA2 == null) {
                        throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner");
                    }
                    N6.f fVarU2 = H.u(l0VarA2, c0761q2);
                    c0761q2.R(1729797275);
                    f0 f0VarB02 = C7.a.b0(h6.l.class, l0VarA2, fVarU2, l0VarA2 instanceof InterfaceC0977o ? ((InterfaceC0977o) l0VarA2).e() : C0991a.f12202b, c0761q2);
                    c0761q2.p(false);
                    c0761q2.p(false);
                    h6.l lVar = (h6.l) f0VarB02;
                    String str4 = (String) lVar.o.getValue();
                    LoadingInterface$LoadingState loadingInterface$LoadingStateE2 = lVar.e();
                    c0761q2.Q(428100268);
                    boolean zH3 = c0761q2.h(lVar);
                    Object objG3 = c0761q2.G();
                    S s12 = C0753m.f8270a;
                    if (zH3 || objG3 == s12) {
                        s10 = s12;
                        C0326b c0326b = new C0326b(0, lVar, h6.l.class, "reloadData", "reloadData(Z)V", 0, 1);
                        c0761q2.a0(c0326b);
                        objG3 = c0326b;
                    } else {
                        s10 = s12;
                    }
                    c0761q2.p(false);
                    w0.c.i(str3, str4, loadingInterface$LoadingStateE2, (InterfaceC2772a) objG3, c0761q2, iIntValue2 & 14);
                    c0761q2.Q(428104621);
                    boolean zH4 = c0761q2.h(lVar);
                    Object objG4 = c0761q2.G();
                    if (zH4 || objG4 == s10) {
                        objG4 = new e(lVar, null);
                        c0761q2.a0(objG4);
                    }
                    c0761q2.p(false);
                    C0735d.f(c0761q2, qVar2, (InterfaceC2785n) objG4);
                }
                return qVar2;
        }
    }
}

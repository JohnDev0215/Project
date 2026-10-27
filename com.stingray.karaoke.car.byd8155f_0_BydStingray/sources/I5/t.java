package I5;

import T.C0753m;
import T.C0761q;
import T.S;
import T.X;
import com.stingray.karaoke.car.MainActivity;
import z7.InterfaceC2772a;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class t implements InterfaceC2786o {
    public final /* synthetic */ int l = 1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ X f3019m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ MainActivity f3020n;

    public t(X x8, MainActivity mainActivity) {
        this.f3019m = x8;
        this.f3020n = mainActivity;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        m7.q qVar = m7.q.f19267a;
        MainActivity mainActivity = this.f3020n;
        int i7 = 3;
        S s6 = C0753m.f8270a;
        X x8 = this.f3019m;
        int i10 = 2;
        switch (this.l) {
            case 0:
                C0761q c0761q = (C0761q) obj2;
                ((Number) obj3).intValue();
                A7.m.f((w.q) obj, "$this$AnimatedVisibility");
                boolean zBooleanValue = ((Boolean) x8.getValue()).booleanValue();
                c0761q.Q(1149600703);
                boolean zH = c0761q.h(mainActivity);
                Object objG = c0761q.G();
                if (zH || objG == s6) {
                    objG = new q(mainActivity, i10);
                    c0761q.a0(objG);
                }
                c0761q.p(false);
                h6.n.a(zBooleanValue, (InterfaceC2772a) objG, c0761q, 0, 0);
                int i11 = MainActivity.f12974j0;
                C7.a.j(null, mainActivity.s(), c0761q, 0);
                break;
            default:
                String str = (String) obj;
                C0761q c0761q2 = (C0761q) obj2;
                int iIntValue = ((Number) obj3).intValue();
                A7.m.f(str, "id");
                if ((iIntValue & 6) == 0) {
                    iIntValue |= c0761q2.f(str) ? 4 : 2;
                }
                if ((iIntValue & 19) == 18 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    c0761q2.Q(-519519924);
                    boolean zF = c0761q2.f(x8);
                    Object objG2 = c0761q2.G();
                    if (zF || objG2 == s6) {
                        objG2 = new G6.i(x8, 2);
                        c0761q2.a0(objG2);
                    }
                    InterfaceC2772a interfaceC2772a = (InterfaceC2772a) objG2;
                    c0761q2.p(false);
                    c0761q2.Q(-519484031);
                    boolean zF2 = c0761q2.f(x8);
                    Object objG3 = c0761q2.G();
                    if (zF2 || objG3 == s6) {
                        objG3 = new G6.i(x8, 3);
                        c0761q2.a0(objG3);
                    }
                    InterfaceC2772a interfaceC2772a2 = (InterfaceC2772a) objG3;
                    c0761q2.p(false);
                    c0761q2.Q(-519448220);
                    boolean zH2 = c0761q2.h(mainActivity);
                    Object objG4 = c0761q2.G();
                    if (zH2 || objG4 == s6) {
                        objG4 = new q(mainActivity, i7);
                        c0761q2.a0(objG4);
                    }
                    c0761q2.p(false);
                    l9.d.l(str, interfaceC2772a, interfaceC2772a2, (InterfaceC2772a) objG4, c0761q2, iIntValue & 14);
                }
                break;
        }
        return qVar;
    }

    public t(MainActivity mainActivity, X x8) {
        this.f3020n = mainActivity;
        this.f3019m = x8;
    }
}

package S5;

import D.L;
import Q5.C0;
import Q5.O;
import T.C0735d;
import T.C0753m;
import T.C0761q;
import T.X;
import X5.D0;
import a.AbstractC0904a;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.lifecycle.A;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.car.utils.FontSize;
import n3.C1930E;
import n3.C1945j;
import w.C2576g;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2787p;

/* JADX INFO: loaded from: classes.dex */
public final class n implements InterfaceC2787p {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f8019m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f8020n;

    public /* synthetic */ n(Object obj, int i7, Object obj2) {
        this.l = i7;
        this.f8019m = obj;
        this.f8020n = obj2;
    }

    @Override // z7.InterfaceC2787p
    public final Object o(Object obj, Object obj2, Object obj3, Object obj4) {
        int i7;
        int i10;
        switch (this.l) {
            case 0:
                g0.o oVar = (g0.o) obj;
                L l = (L) obj2;
                C0761q c0761q = (C0761q) obj3;
                int iIntValue = ((Number) obj4).intValue();
                A7.m.f(oVar, "modifier");
                A7.m.f(l, "contentPadding");
                if ((iIntValue & 6) == 0) {
                    i7 = (c0761q.f(oVar) ? 4 : 2) | iIntValue;
                } else {
                    i7 = iIntValue;
                }
                if ((iIntValue & 48) == 0) {
                    i7 |= c0761q.f(l) ? 32 : 16;
                }
                if ((i7 & 147) == 146 && c0761q.x()) {
                    c0761q.K();
                } else {
                    String strX = AbstractC2673a.X(R.string.Error_Player, c0761q);
                    String strX2 = AbstractC2673a.X(R.string.Error_Player_NetworkError, c0761q);
                    String strX3 = AbstractC2673a.X(R.string.Global_Retry, c0761q);
                    String strX4 = AbstractC2673a.X(R.string.Global_Cancel, c0761q);
                    c0761q.Q(180963220);
                    D0 d02 = (D0) this.f8020n;
                    boolean zH = c0761q.h(d02);
                    Object objG = c0761q.G();
                    if (zH || objG == C0753m.f8270a) {
                        objG = new C0(d02, 2);
                        c0761q.a0(objG);
                    }
                    c0761q.p(false);
                    com.bumptech.glide.d.e(oVar, (String) this.f8019m, strX, strX2, strX3, strX4, (InterfaceC2772a) objG, null, l, c0761q, (i7 & 14) | ((i7 << 21) & 234881024), 128);
                }
                break;
            case 1:
                g0.o oVar2 = (g0.o) obj;
                int iIntValue2 = ((Number) obj2).intValue();
                C0761q c0761q2 = (C0761q) obj3;
                int iIntValue3 = ((Number) obj4).intValue();
                A7.m.f(oVar2, "modifier");
                if ((iIntValue3 & 6) == 0) {
                    i10 = (c0761q2.f(oVar2) ? 4 : 2) | iIntValue3;
                } else {
                    i10 = iIntValue3;
                }
                if ((iIntValue3 & 48) == 0) {
                    i10 |= c0761q2.d(iIntValue2) ? 32 : 16;
                }
                if ((i10 & 147) == 146 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    P8.n.f((String) ((InterfaceC2782k) this.f8019m).b(Integer.valueOf(iIntValue2)), oVar2, l6.d.c(c0761q2).f18696b, null, l6.d.c(c0761q2).f18687T, null, null, 0.0f, h6.f.g((FontSize) this.f8020n, c0761q2), null, null, null, 0L, null, new Y0.i(3), 0L, 0, false, 0, 0, null, N0.L.b(com.bumptech.glide.d.O(((O5.a) c0761q2.k(O5.b.f5155b)).f5144b, l6.d.c(c0761q2).f18687T, 20.0f), 0L, 0L, null, null, 0L, null, 0L, null, null, Y0.p.d, 8388607), null, c0761q2, (i10 << 3) & 112, 0, 0, 6274792);
                }
                break;
            default:
                C0761q c0761q3 = (C0761q) obj3;
                ((Number) obj4).intValue();
                A7.m.f((C2576g) obj, "$this$composable");
                A7.m.f((C1945j) obj2, "it");
                e6.h hVar = (e6.h) this.f8019m;
                AbstractC0904a.c(hVar, false, c0761q3, 0, 2);
                X xW = C0735d.w(((C1930E) this.f8020n).f19799D, null, null, c0761q3, 48, 2);
                A a10 = (A) c0761q3.k(AndroidCompositionLocals_androidKt.getLocalLifecycleOwner());
                c0761q3.Q(-1469540612);
                boolean zF = c0761q3.f(xW) | c0761q3.f(hVar);
                Object objG2 = c0761q3.G();
                if (zF || objG2 == C0753m.f8270a) {
                    objG2 = new O(xW, 7, hVar);
                    c0761q3.a0(objG2);
                }
                c0761q3.p(false);
                C0735d.d(a10, (InterfaceC2782k) objG2, c0761q3);
                break;
        }
        return m7.q.f19267a;
    }
}

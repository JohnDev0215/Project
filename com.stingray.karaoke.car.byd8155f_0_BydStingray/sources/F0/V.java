package F0;

import android.view.Window;
import c6.C1034a;
import com.stingray.karaoke.car.ui.navigation.NavigationType;
import h.InterfaceC1331c;
import i.C1395b;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class V implements T.F {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2142a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f2143b;

    public /* synthetic */ V(Object obj, int i7) {
        this.f2142a = i7;
        this.f2143b = obj;
    }

    @Override // T.F
    public final void a() {
        switch (this.f2142a) {
            case 0:
                ((C0231u0) this.f2143b).f2280a.a();
                break;
            case 1:
                ((G.F) this.f2143b).d = null;
                break;
            case 2:
                ((G.V) this.f2143b).c = null;
                break;
            case 3:
                G.Q q4 = (G.Q) this.f2143b;
                int i7 = q4.d.i();
                for (int i10 = 0; i10 < i7; i10++) {
                    q4.b();
                }
                break;
            case 4:
                ((O.K) this.f2143b).k();
                break;
            case 5:
                ((Window) this.f2143b).clearFlags(128);
                break;
            case 6:
                NavigationType navigationType = NavigationType.TAB;
                C1034a c1034a = (C1034a) this.f2143b;
                c1034a.m(navigationType);
                c1034a.f12240j = null;
                break;
            case 7:
                e1.v vVar = (e1.v) this.f2143b;
                vVar.dismiss();
                vVar.f13815r.c();
                break;
            case 8:
                e1.x xVar = (e1.x) this.f2143b;
                xVar.c();
                xVar.getClass();
                androidx.lifecycle.a0.j(xVar, null);
                xVar.f13837y.removeViewImmediate(xVar);
                break;
            case 9:
                Iterator it = ((C1395b) this.f2143b).f15132b.iterator();
                while (it.hasNext()) {
                    ((InterfaceC1331c) it.next()).cancel();
                }
                break;
            default:
                Iterator it2 = ((i.g) this.f2143b).f15132b.iterator();
                while (it2.hasNext()) {
                    ((InterfaceC1331c) it2.next()).cancel();
                }
                break;
        }
    }
}

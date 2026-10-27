package F0;

import T.C0735d;
import T.C0753m;
import T.C0761q;
import android.view.View;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import com.stingray.drivemodedetector.R;
import f0.AbstractC1245b;
import java.util.Set;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class t1 extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f2276m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ u1 f2277n;
    public final /* synthetic */ b0.b o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ t1(u1 u1Var, b0.b bVar, int i7) {
        super(2);
        this.f2276m = i7;
        this.f2277n = u1Var;
        this.o = bVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f2276m) {
            case 0:
                C0761q c0761q = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
                    c0761q.K();
                } else {
                    AndroidCompositionLocals_androidKt.a(this.f2277n.l, this.o, c0761q, 0);
                }
                break;
            default:
                C0761q c0761q2 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    u1 u1Var = this.f2277n;
                    Object tag = u1Var.l.getTag(R.id.inspection_slot_table_set);
                    Set set = (tag instanceof Set) && (!(tag instanceof B7.a) || (tag instanceof B7.f)) ? (Set) tag : null;
                    AndroidComposeView androidComposeView = u1Var.l;
                    if (set == null) {
                        Object parent = androidComposeView.getParent();
                        View view = parent instanceof View ? (View) parent : null;
                        Object tag2 = view != null ? view.getTag(R.id.inspection_slot_table_set) : null;
                        set = (!(tag2 instanceof Set) || ((tag2 instanceof B7.a) && !(tag2 instanceof B7.f))) ? null : (Set) tag2;
                    }
                    if (set != null) {
                        set.add(c0761q2.c);
                        c0761q2.f8316p = true;
                        c0761q2.B = true;
                        c0761q2.c.l();
                        c0761q2.f8295G.l();
                        T.B0 b02 = c0761q2.f8296H;
                        T.z0 z0Var = b02.f8157a;
                        b02.f8159e = z0Var.f8406t;
                        b02.f8160f = z0Var.f8407u;
                    }
                    boolean zH = c0761q2.h(u1Var);
                    Object objG = c0761q2.G();
                    T.S s6 = C0753m.f8270a;
                    if (zH || objG == s6) {
                        objG = new r1(u1Var, null);
                        c0761q2.a0(objG);
                    }
                    C0735d.f(c0761q2, androidComposeView, (InterfaceC2785n) objG);
                    boolean zH2 = c0761q2.h(u1Var);
                    Object objG2 = c0761q2.G();
                    if (zH2 || objG2 == s6) {
                        objG2 = new s1(u1Var, null);
                        c0761q2.a0(objG2);
                    }
                    C0735d.f(c0761q2, androidComposeView, (InterfaceC2785n) objG2);
                    C0735d.a(AbstractC1245b.f14529a.a(set), b0.g.b(-1193460702, new t1(u1Var, this.o, 0), c0761q2), c0761q2, 56);
                }
                break;
        }
        return m7.q.f19267a;
    }
}

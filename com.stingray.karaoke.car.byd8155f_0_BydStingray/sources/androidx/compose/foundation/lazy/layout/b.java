package androidx.compose.foundation.lazy.layout;

import A.C0032p;
import A.L;
import A7.o;
import C0.c0;
import C0.f0;
import E.n;
import G.G;
import G.J;
import G.RunnableC0291a;
import G.V;
import G.W;
import G.k0;
import M.t;
import T.C0735d;
import T.C0753m;
import T.C0761q;
import T.S;
import T.X;
import android.view.View;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import d0.InterfaceC1054c;
import java.util.LinkedHashMap;
import m7.q;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class b extends o implements InterfaceC2786o {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ V f11497m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ g0.o f11498n;
    public final /* synthetic */ InterfaceC2785n o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ X f11499p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(V v10, g0.o oVar, InterfaceC2785n interfaceC2785n, X x8) {
        super(3);
        this.f11497m = v10;
        this.f11498n = oVar;
        this.o = interfaceC2785n;
        this.f11499p = x8;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        g0.o oVarK;
        Object obj4;
        InterfaceC1054c interfaceC1054c = (InterfaceC1054c) obj;
        C0761q c0761q = (C0761q) obj2;
        ((Number) obj3).intValue();
        Object objG = c0761q.G();
        S s6 = C0753m.f8270a;
        if (objG == s6) {
            objG = new G(interfaceC1054c, new n(this.f11499p, 2));
            c0761q.a0(objG);
        }
        G g7 = (G) objG;
        Object objG2 = c0761q.G();
        if (objG2 == s6) {
            t tVar = new t();
            tVar.l = g7;
            tVar.f4151m = new LinkedHashMap();
            objG2 = new f0(tVar);
            c0761q.a0(objG2);
        }
        f0 f0Var = (f0) objG2;
        V v10 = this.f11497m;
        if (v10 != null) {
            c0761q.Q(205264983);
            c0761q.Q(6622915);
            J j3 = k0.f2598a;
            if (j3 != null) {
                c0761q.Q(1213893039);
                c0761q.p(false);
                obj4 = j3;
            } else {
                c0761q.Q(1213931944);
                View view = (View) c0761q.k(AndroidCompositionLocals_androidKt.f11690f);
                boolean zF = c0761q.f(view);
                Object objG3 = c0761q.G();
                if (zF || objG3 == s6) {
                    objG3 = new RunnableC0291a(view);
                    c0761q.a0(objG3);
                }
                c0761q.p(false);
                obj4 = (RunnableC0291a) objG3;
            }
            c0761q.p(false);
            Object[] objArr = {v10, g7, f0Var, obj4};
            boolean zF2 = c0761q.f(v10) | c0761q.h(g7) | c0761q.h(f0Var) | c0761q.h(obj4);
            Object objG4 = c0761q.G();
            if (zF2 || objG4 == s6) {
                C0032p c0032p = new C0032p(v10, g7, f0Var, obj4, 1);
                c0761q.a0(c0032p);
                objG4 = c0032p;
            }
            C0735d.e(objArr, (InterfaceC2782k) objG4, c0761q);
            c0761q.p(false);
        } else {
            c0761q.Q(205858881);
            c0761q.p(false);
        }
        int i7 = W.f2528b;
        g0.o oVar = this.f11498n;
        if (v10 != null && (oVarK = oVar.k(new TraversablePrefetchStateModifierElement(v10))) != null) {
            oVar = oVarK;
        }
        boolean zF3 = c0761q.f(g7);
        InterfaceC2785n interfaceC2785n = this.o;
        boolean zF4 = zF3 | c0761q.f(interfaceC2785n);
        Object objG5 = c0761q.G();
        if (zF4 || objG5 == s6) {
            objG5 = new L(g7, 6, interfaceC2785n);
            c0761q.a0(objG5);
        }
        c0.c(f0Var, oVar, (InterfaceC2785n) objG5, c0761q, 8);
        return q.f19267a;
    }
}

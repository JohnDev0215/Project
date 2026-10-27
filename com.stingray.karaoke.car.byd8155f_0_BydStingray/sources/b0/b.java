package b0;

import A7.E;
import A7.m;
import C0.C0114v;
import F0.W;
import G.L;
import T.C0754m0;
import T.C0761q;
import g0.o;
import java.io.Serializable;
import java.util.ArrayList;
import z7.InterfaceC2772a;
import z7.InterfaceC2773b;
import z7.InterfaceC2774c;
import z7.InterfaceC2775d;
import z7.InterfaceC2776e;
import z7.InterfaceC2777f;
import z7.InterfaceC2778g;
import z7.InterfaceC2779h;
import z7.InterfaceC2780i;
import z7.InterfaceC2781j;
import z7.InterfaceC2783l;
import z7.InterfaceC2784m;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;
import z7.InterfaceC2787p;
import z7.InterfaceC2788q;
import z7.InterfaceC2789r;
import z7.InterfaceC2790s;
import z7.InterfaceC2791t;
import z7.InterfaceC2792u;

/* JADX INFO: loaded from: classes.dex */
public final class b implements InterfaceC2785n, InterfaceC2786o, InterfaceC2787p, InterfaceC2788q, InterfaceC2789r, InterfaceC2790s, InterfaceC2791t, InterfaceC2792u, InterfaceC2773b, InterfaceC2774c, InterfaceC2775d, InterfaceC2776e, InterfaceC2777f, InterfaceC2778g, InterfaceC2779h, InterfaceC2780i, InterfaceC2781j, InterfaceC2783l, InterfaceC2784m {
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f12190m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f12191n;
    public C0754m0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ArrayList f12192p;

    public b(int i7, Object obj, boolean z6) {
        this.l = i7;
        this.f12190m = z6;
        this.f12191n = obj;
    }

    @Override // z7.InterfaceC2788q
    public final /* bridge */ /* synthetic */ Object e(Object obj, Object obj2, Object obj3, Object obj4, Serializable serializable) {
        return j(obj, obj2, obj3, (C0761q) obj4, ((Number) serializable).intValue());
    }

    public final Object f(o oVar, Integer num, Boolean bool, InterfaceC2772a interfaceC2772a, C0761q c0761q, int i7) {
        c0761q.S(this.l);
        l(c0761q);
        int iA = c0761q.f(this) ? g.a(2, 4) : g.a(1, 4);
        Object obj = this.f12191n;
        m.d(obj, "null cannot be cast to non-null type kotlin.Function6<@[ParameterName(name = 'p1')] kotlin.Any?, @[ParameterName(name = 'p2')] kotlin.Any?, @[ParameterName(name = 'p3')] kotlin.Any?, @[ParameterName(name = 'p4')] kotlin.Any?, @[ParameterName(name = 'c')] androidx.compose.runtime.Composer, @[ParameterName(name = 'changed')] kotlin.Int, kotlin.Any?>");
        E.e(6, obj);
        Object objK = ((InterfaceC2789r) obj).k(oVar, num, bool, interfaceC2772a, c0761q, Integer.valueOf(i7 | iA));
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new a(this, oVar, num, bool, interfaceC2772a, i7, 0);
        }
        return objK;
    }

    @Override // z7.InterfaceC2786o
    public final /* bridge */ /* synthetic */ Object g(Object obj, Object obj2, Object obj3) {
        return h(obj, (C0761q) obj2, ((Number) obj3).intValue());
    }

    public final Object h(Object obj, C0761q c0761q, int i7) {
        c0761q.S(this.l);
        l(c0761q);
        int iA = c0761q.f(this) ? g.a(2, 1) : g.a(1, 1);
        Object obj2 = this.f12191n;
        m.d(obj2, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = 'p1')] kotlin.Any?, @[ParameterName(name = 'c')] androidx.compose.runtime.Composer, @[ParameterName(name = 'changed')] kotlin.Int, kotlin.Any?>");
        E.e(3, obj2);
        Object objG = ((InterfaceC2786o) obj2).g(obj, c0761q, Integer.valueOf(iA | i7));
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new W(this, obj, i7);
        }
        return objG;
    }

    public final Object i(Object obj, Object obj2, C0761q c0761q, int i7) {
        c0761q.S(this.l);
        l(c0761q);
        int iA = c0761q.f(this) ? g.a(2, 2) : g.a(1, 2);
        Object obj3 = this.f12191n;
        m.d(obj3, "null cannot be cast to non-null type kotlin.Function4<@[ParameterName(name = 'p1')] kotlin.Any?, @[ParameterName(name = 'p2')] kotlin.Any?, @[ParameterName(name = 'c')] androidx.compose.runtime.Composer, @[ParameterName(name = 'changed')] kotlin.Int, kotlin.Any?>");
        E.e(4, obj3);
        Object objO = ((InterfaceC2787p) obj3).o(obj, obj2, c0761q, Integer.valueOf(iA | i7));
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0114v(this, obj, obj2, i7, 7, false);
        }
        return objO;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        C0761q c0761q = (C0761q) obj;
        int iIntValue = ((Number) obj2).intValue();
        c0761q.S(this.l);
        l(c0761q);
        int iA = iIntValue | (c0761q.f(this) ? g.a(2, 0) : g.a(1, 0));
        Object obj3 = this.f12191n;
        m.d(obj3, "null cannot be cast to non-null type kotlin.Function2<@[ParameterName(name = 'c')] androidx.compose.runtime.Composer, @[ParameterName(name = 'changed')] kotlin.Int, kotlin.Any?>");
        E.e(2, obj3);
        Object objInvoke = ((InterfaceC2785n) obj3).invoke(c0761q, Integer.valueOf(iA));
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            E.e(2, this);
            c0754m0R.d = this;
        }
        return objInvoke;
    }

    public final Object j(Object obj, Object obj2, Object obj3, C0761q c0761q, int i7) {
        c0761q.S(this.l);
        l(c0761q);
        int iA = c0761q.f(this) ? g.a(2, 3) : g.a(1, 3);
        Object obj4 = this.f12191n;
        m.d(obj4, "null cannot be cast to non-null type kotlin.Function5<@[ParameterName(name = 'p1')] kotlin.Any?, @[ParameterName(name = 'p2')] kotlin.Any?, @[ParameterName(name = 'p3')] kotlin.Any?, @[ParameterName(name = 'c')] androidx.compose.runtime.Composer, @[ParameterName(name = 'changed')] kotlin.Int, kotlin.Any?>");
        E.e(5, obj4);
        Object objE = ((InterfaceC2788q) obj4).e(obj, obj2, obj3, c0761q, Integer.valueOf(iA | i7));
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new L(this, obj, obj2, obj3, i7, 2);
        }
        return objE;
    }

    @Override // z7.InterfaceC2789r
    public final /* bridge */ /* synthetic */ Object k(o oVar, Integer num, Boolean bool, InterfaceC2772a interfaceC2772a, Object obj, Integer num2) {
        return f(oVar, num, bool, interfaceC2772a, (C0761q) obj, num2.intValue());
    }

    public final void l(C0761q c0761q) {
        C0754m0 c0754m0V;
        if (!this.f12190m || (c0754m0V = c0761q.v()) == null) {
            return;
        }
        c0761q.getClass();
        c0754m0V.f8271a |= 1;
        if (g.c(this.o, c0754m0V)) {
            this.o = c0754m0V;
            return;
        }
        ArrayList arrayList = this.f12192p;
        if (arrayList == null) {
            ArrayList arrayList2 = new ArrayList();
            this.f12192p = arrayList2;
            arrayList2.add(c0754m0V);
            return;
        }
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            if (g.c((C0754m0) arrayList.get(i7), c0754m0V)) {
                arrayList.set(i7, c0754m0V);
                return;
            }
        }
        arrayList.add(c0754m0V);
    }

    @Override // z7.InterfaceC2787p
    public final /* bridge */ /* synthetic */ Object o(Object obj, Object obj2, Object obj3, Object obj4) {
        return i(obj, obj2, (C0761q) obj3, ((Number) obj4).intValue());
    }
}

package S5;

import C0.K;
import D.AbstractC0133o;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import T.C0735d;
import T.C0761q;
import T.InterfaceC0744h0;
import g1.AbstractC1274a;
import h2.AbstractC1347d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import n0.C1891t;
import n6.C1966G;
import n7.AbstractC2000o;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class r implements InterfaceC2785n {
    public final /* synthetic */ g0.o l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f8038m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f8039n;
    public final /* synthetic */ String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C1966G f8040p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ boolean f8041q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ String f8042r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f8043s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f8044t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ boolean f8045u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f8046v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f8047w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f8048x;

    public r(g0.o oVar, String str, String str2, String str3, C1966G c1966g, boolean z6, String str4, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, boolean z10, InterfaceC2772a interfaceC2772a3, InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2) {
        this.l = oVar;
        this.f8038m = str;
        this.f8039n = str2;
        this.o = str3;
        this.f8040p = c1966g;
        this.f8041q = z6;
        this.f8042r = str4;
        this.f8043s = interfaceC2772a;
        this.f8044t = interfaceC2772a2;
        this.f8045u = z10;
        this.f8046v = interfaceC2772a3;
        this.f8047w = interfaceC2782k;
        this.f8048x = interfaceC2782k2;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        C0761q c0761q = (C0761q) obj;
        if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            c0761q.Q(1183016596);
            List listK = h6.r.k(c0761q);
            ArrayList arrayList = new ArrayList(AbstractC2000o.J(listK, 10));
            Iterator it = listK.iterator();
            while (it.hasNext()) {
                arrayList.add(new C1891t(((C1891t) ((m7.i) it.next()).f19259m).f19453a));
            }
            List listK2 = h6.r.k(c0761q);
            ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(listK2, 10));
            Iterator it2 = listK2.iterator();
            while (it2.hasNext()) {
                arrayList2.add(Float.valueOf(((Number) ((m7.i) it2.next()).l).floatValue()));
            }
            k6.p pVar = new k6.p(arrayList, arrayList2, 360.0f, false);
            c0761q.p(false);
            g0.o oVarA = androidx.compose.foundation.a.a(this.l, pVar, null, 6);
            K kE = AbstractC0133o.e(g0.b.f14667s, false);
            int i7 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVarA);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, kE, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i7))) {
                AbstractC1347d.l(i7, c0761q, i7, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            AbstractC1274a.o(g0.l.f14684a, this.f8038m, this.f8039n, this.o, this.f8040p, this.f8041q, this.f8042r, this.f8043s, this.f8044t, this.f8045u, this.f8046v, this.f8047w, this.f8048x, c0761q, 32774, 0);
            c0761q.p(true);
        }
        return m7.q.f19267a;
    }
}

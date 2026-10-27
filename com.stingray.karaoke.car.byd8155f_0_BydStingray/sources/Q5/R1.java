package Q5;

import g1.C1277d;
import g1.C1278e;
import g1.C1286m;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class R1 implements InterfaceC2782k {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1278e f6530m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ float f6531n;

    public /* synthetic */ R1(C1278e c1278e, float f10, int i7) {
        this.l = i7;
        this.f6530m = c1278e;
        this.f6531n = f10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        m7.q qVar = m7.q.f19267a;
        float f10 = this.f6531n;
        C1278e c1278e = this.f6530m;
        switch (this.l) {
            case 0:
                C1277d c1277d = (C1277d) obj;
                A7.m.f(c1277d, "$this$constrainAs");
                io.sentry.internal.debugmeta.c.O(c1277d.f14711e, c1278e.f14716e, 0.0f, 6);
                io.sentry.internal.debugmeta.c.O(c1277d.f14713g, c1278e.f14718g, 0.0f, 6);
                M.t.J(c1277d.d, c1278e.f14717f, f10, 4);
                M.t.J(c1277d.f14712f, c1277d.c.f14717f, 0.0f, 6);
                C1286m c1286m = new C1286m("spread");
                H7.w[] wVarArr = C1277d.f14708j;
                c1277d.f14714h.b(wVarArr[0], c1286m);
                C1286m c1286m2 = new C1286m("spread");
                c1277d.f14715i.b(wVarArr[1], c1286m2);
                break;
            case 1:
                C1277d c1277d2 = (C1277d) obj;
                A7.m.f(c1277d2, "$this$constrainAs");
                io.sentry.internal.debugmeta.c.O(c1277d2.f14711e, c1278e.f14718g, f10, 4);
                break;
            case 2:
                C1277d c1277d3 = (C1277d) obj;
                A7.m.f(c1277d3, "$this$constrainAs");
                io.sentry.internal.debugmeta.c.O(c1277d3.f14711e, c1278e.f14718g, f10, 4);
                break;
            case 3:
                C1277d c1277d4 = (C1277d) obj;
                A7.m.f(c1277d4, "$this$constrainAs");
                io.sentry.internal.debugmeta.c.O(c1277d4.f14711e, c1278e.f14718g, f10, 4);
                break;
            case 4:
                C1277d c1277d5 = (C1277d) obj;
                A7.m.f(c1277d5, "$this$constrainAs");
                H7.w[] wVarArr2 = C1277d.f14708j;
                C1277d.b(c1277d5, c1278e.f14716e, c1278e.f14718g, 0.5f, 60);
                M.t.J(c1277d5.d, c1278e.f14717f, f10, 4);
                break;
            default:
                C1277d c1277d6 = (C1277d) obj;
                A7.m.f(c1277d6, "$this$constrainAs");
                io.sentry.internal.debugmeta.c.O(c1277d6.f14711e, c1278e.f14718g, f10, 4);
                C1277d.a(c1277d6, c1278e);
                break;
        }
        return qVar;
    }
}

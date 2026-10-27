package Q5;

import g1.C1277d;
import g1.C1278e;
import g1.C1286m;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class J1 implements InterfaceC2782k {
    public final /* synthetic */ C1278e l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ float f6410m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ float f6411n;
    public final /* synthetic */ float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C1278e f6412p;

    public J1(C1278e c1278e, float f10, float f11, float f12, C1278e c1278e2) {
        this.l = c1278e;
        this.f6410m = f10;
        this.f6411n = f11;
        this.o = f12;
        this.f6412p = c1278e2;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        C1277d c1277d = (C1277d) obj;
        A7.m.f(c1277d, "$this$constrainAs");
        C1278e c1278e = this.l;
        io.sentry.internal.debugmeta.c.O(c1277d.f14711e, c1278e.f14716e, this.f6410m, 4);
        float f10 = this.f6411n;
        io.sentry.internal.debugmeta.c.O(c1277d.f14713g, c1278e.f14718g, f10, 4);
        float f11 = this.o;
        M.t.J(c1277d.d, c1278e.f14717f, f11, 4);
        M.t.J(c1277d.f14712f, this.f6412p.d, 0.0f, 6);
        C1286m c1286m = new C1286m("spread");
        H7.w[] wVarArr = C1277d.f14708j;
        c1277d.f14714h.b(wVarArr[0], c1286m);
        C1286m c1286m2 = new C1286m("spread");
        c1277d.f14715i.b(wVarArr[1], c1286m2);
        return m7.q.f19267a;
    }
}

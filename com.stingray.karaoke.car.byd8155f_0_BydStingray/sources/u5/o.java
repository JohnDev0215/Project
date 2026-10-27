package U5;

import g1.C1277d;
import g1.C1278e;
import g1.C1286m;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final o f9172m = new o(0);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final o f9173n = new o(1);
    public static final o o = new o(2);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final o f9174p = new o(3);
    public final /* synthetic */ int l;

    public /* synthetic */ o(int i7) {
        this.l = i7;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.l) {
            case 0:
                C1277d c1277d = (C1277d) obj;
                A7.m.f(c1277d, "$this$constrainAs");
                C1278e c1278e = c1277d.c;
                io.sentry.internal.debugmeta.c.O(c1277d.f14711e, c1278e.f14716e, 0.0f, 6);
                io.sentry.internal.debugmeta.c.O(c1277d.f14713g, c1278e.f14718g, 0.0f, 6);
                M.t.J(c1277d.f14712f, c1278e.f14717f, 0.0f, 6);
                c1277d.f14715i.b(C1277d.f14708j[1], new C1286m("spread"));
                return m7.q.f19267a;
            case 1:
                return ((Number) obj).intValue() + "%";
            case 2:
                C1277d c1277d2 = (C1277d) obj;
                A7.m.f(c1277d2, "$this$constrainAs");
                io.sentry.internal.debugmeta.c.O(c1277d2.f14711e, c1277d2.c.f14716e, 0.0f, 6);
                return m7.q.f19267a;
            default:
                return ((Number) obj).intValue() + "%";
        }
    }
}

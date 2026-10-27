package Q5;

import g1.C1277d;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class I1 implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final I1 f6390m = new I1(0);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final I1 f6391n = new I1(1);
    public static final I1 o = new I1(2);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final I1 f6392p = new I1(3);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final I1 f6393q = new I1(4);
    public final /* synthetic */ int l;

    public /* synthetic */ I1(int i7) {
        this.l = i7;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.l) {
            case 0:
                C1277d c1277d = (C1277d) obj;
                A7.m.f(c1277d, "$this$constrainAs");
                M.t.J(c1277d.d, c1277d.c.d, 0.0f, 6);
                break;
            case 1:
                C1277d c1277d2 = (C1277d) obj;
                A7.m.f(c1277d2, "$this$constrainAs");
                M.t.J(c1277d2.f14712f, c1277d2.c.f14717f, 0.0f, 6);
                break;
            case 2:
                C1277d c1277d3 = (C1277d) obj;
                A7.m.f(c1277d3, "$this$constrainAs");
                M.t.J(c1277d3.d, c1277d3.c.d, 0.0f, 6);
                break;
            case 3:
                C1277d c1277d4 = (C1277d) obj;
                A7.m.f(c1277d4, "$this$constrainAs");
                io.sentry.internal.debugmeta.c.O(c1277d4.f14711e, c1277d4.c.f14716e, 0.0f, 6);
                break;
            default:
                C1277d c1277d5 = (C1277d) obj;
                A7.m.f(c1277d5, "$this$constrainAs");
                io.sentry.internal.debugmeta.c.O(c1277d5.f14711e, c1277d5.c.f14716e, 0.0f, 6);
                break;
        }
        return m7.q.f19267a;
    }
}

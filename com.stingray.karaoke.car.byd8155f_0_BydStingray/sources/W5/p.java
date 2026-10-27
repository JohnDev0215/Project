package W5;

import M.t;
import g1.C1277d;
import g1.C1278e;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class p implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final p f9858m = new p(0);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final p f9859n = new p(1);
    public final /* synthetic */ int l;

    public /* synthetic */ p(int i7) {
        this.l = i7;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.l) {
            case 0:
                C1277d c1277d = (C1277d) obj;
                A7.m.f(c1277d, "$this$constrainAs");
                C1278e c1278e = c1277d.c;
                C1277d.b(c1277d, c1278e.f14716e, c1278e.f14718g, 0.3f, 60);
                break;
            default:
                C1277d c1277d2 = (C1277d) obj;
                A7.m.f(c1277d2, "$this$constrainAs");
                t.J(c1277d2.d, c1277d2.c.d, 0.0f, 6);
                break;
        }
        return m7.q.f19267a;
    }
}

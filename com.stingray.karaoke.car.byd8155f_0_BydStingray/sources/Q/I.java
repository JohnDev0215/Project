package Q;

import n0.C1891t;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class I extends A7.o implements InterfaceC2786o {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final I f5549n = new I(3, 0);
    public static final I o = new I(3, 1);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f5550m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ I(int i7, int i10) {
        super(i7);
        this.f5550m = i10;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        switch (this.f5550m) {
            case 0:
                C0.M m10 = (C0.M) obj;
                long j3 = ((a1.a) obj3).f11027a;
                int iO = m10.O(J.f5557a);
                int i7 = iO * 2;
                C0.V vB = ((C0.J) obj2).b(io.sentry.config.a.g0(0, i7, j3));
                int i10 = vB.f871m - i7;
                return m10.m0(vB.l, i10, n7.v.l, new H(iO, 0, vB));
            default:
                p0.d dVar = (p0.d) obj;
                dVar.Q(((C1891t) obj3).f19453a, dVar.y(C0497b0.c) / 2.0f, (120 & 4) != 0 ? dVar.S() : ((m0.c) obj2).f18880a, 1.0f, (120 & 16) != 0 ? p0.g.f20641a : null, null, 3);
                return m7.q.f19267a;
        }
    }
}

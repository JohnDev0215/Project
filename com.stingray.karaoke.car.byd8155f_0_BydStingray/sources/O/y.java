package O;

import x.C2650p;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class y extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final y f5004n = new y(1, 0);
    public static final y o = new y(1, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final y f5005p = new y(1, 2);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f5006m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ y(int i7, int i10) {
        super(i7);
        this.f5006m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f5006m) {
            case 0:
                long j3 = ((m0.c) obj).f18880a;
                return h6.e.m(j3) ? new C2650p(m0.c.d(j3), m0.c.e(j3)) : C.f4892a;
            case 1:
                C2650p c2650p = (C2650p) obj;
                return new m0.c(h6.e.a(c2650p.f23598a, c2650p.f23599b));
            default:
                return m7.q.f19267a;
        }
    }
}

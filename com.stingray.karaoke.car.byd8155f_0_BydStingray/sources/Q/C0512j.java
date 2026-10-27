package Q;

import T.C0761q;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0512j extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f5928m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ long f5929n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f5930p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0512j(long j3, Object obj, InterfaceC2785n interfaceC2785n, int i7) {
        super(2);
        this.f5928m = i7;
        this.f5929n = j3;
        this.o = obj;
        this.f5930p = interfaceC2785n;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f5928m) {
            case 0:
                C0761q c0761q = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
                    c0761q.K();
                } else {
                    l9.d.o(this.f5929n, ((h1) c0761q.k(i1.f5927a)).f5919m, b0.g.b(1327513942, new A.L((D.L) this.o, 8, (b0.b) this.f5930p), c0761q), c0761q, 384);
                }
                break;
            default:
                C0761q c0761q2 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    R.O.b(this.f5929n, (N0.L) this.o, this.f5930p, c0761q2, 0);
                }
                break;
        }
        return m7.q.f19267a;
    }
}

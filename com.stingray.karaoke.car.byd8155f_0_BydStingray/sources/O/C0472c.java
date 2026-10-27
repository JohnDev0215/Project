package O;

import F0.AbstractC0227s0;
import F0.W0;
import T.C0735d;
import T.C0761q;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: O.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0472c extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ W0 f4948m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ long f4949n;
    public final /* synthetic */ boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ g0.o f4950p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0480k f4951q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0472c(W0 w02, long j3, boolean z6, g0.o oVar, InterfaceC0480k interfaceC0480k) {
        super(2);
        this.f4948m = w02;
        this.f4949n = j3;
        this.o = z6;
        this.f4950p = oVar;
        this.f4951q = interfaceC0480k;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        C0761q c0761q = (C0761q) obj;
        if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            C0735d.a(AbstractC0227s0.f2268q.a(this.f4948m), b0.g.b(-1426434671, new C0471b(this.f4949n, this.o, this.f4950p, this.f4951q), c0761q), c0761q, 56);
        }
        return m7.q.f19267a;
    }
}

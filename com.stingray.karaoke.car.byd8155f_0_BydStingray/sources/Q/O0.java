package Q;

import T.C0761q;
import n0.InterfaceC1866P;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class O0 extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f5626m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f5627n;
    public final /* synthetic */ C.k o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ M0 f5628p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ InterfaceC1866P f5629q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public O0(boolean z6, boolean z10, C.k kVar, M0 m02, InterfaceC1866P interfaceC1866P) {
        super(2);
        this.f5626m = z6;
        this.f5627n = z10;
        this.o = kVar;
        this.f5628p = m02;
        this.f5629q = interfaceC1866P;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        C0761q c0761q = (C0761q) obj;
        if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            S0 s02 = S0.f5662a;
            g0.l lVar = g0.l.f14684a;
            float f10 = S0.f5664e;
            float f11 = S0.d;
            s02.a(this.f5626m, this.f5627n, this.o, lVar, this.f5628p, this.f5629q, f10, f11, c0761q, 114822144);
        }
        return m7.q.f19267a;
    }
}

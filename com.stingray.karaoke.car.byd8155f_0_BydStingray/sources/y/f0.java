package y;

import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class f0 extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f23902m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ h0 f23903n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ f0(h0 h0Var, int i7) {
        super(0);
        this.f23902m = i7;
        this.f23903n = h0Var;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f23902m) {
            case 0:
                C0.r rVar = (C0.r) this.f23903n.f23915L.getValue();
                return new m0.c(rVar != null ? rVar.N(0L) : 9205357640488583168L);
            case 1:
                return new m0.c(this.f23903n.f23917N);
            default:
                this.f23903n.J0();
                return m7.q.f19267a;
        }
    }
}

package K;

import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class r extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3379m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ O.K f3380n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ r(O.K k10, int i7) {
        super(1);
        this.f3379m = i7;
        this.f3380n = k10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f3379m) {
            case 0:
                return new F0.V(this.f3380n, 4);
            default:
                long j3 = ((m0.c) obj).f18880a;
                this.f3380n.o();
                return m7.q.f19267a;
        }
    }
}

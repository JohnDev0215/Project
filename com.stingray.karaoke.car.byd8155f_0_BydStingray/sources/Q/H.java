package Q;

import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class H extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f5537m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0.V f5538n;
    public final /* synthetic */ int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ H(int i7, int i10, C0.V v10) {
        super(1);
        this.f5537m = i10;
        this.f5538n = v10;
        this.o = i7;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f5537m) {
            case 0:
                C0.U.d((C0.U) obj, this.f5538n, 0, -this.o);
                break;
            default:
                C0.U.d((C0.U) obj, this.f5538n, -this.o, 0);
                break;
        }
        return m7.q.f19267a;
    }
}

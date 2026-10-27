package e0;

import z7.InterfaceC2782k;

/* JADX INFO: renamed from: e0.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1173m extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f13718m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ A7.o f13719n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public C1173m(int i7, InterfaceC2782k interfaceC2782k) {
        super(1);
        this.f13718m = i7;
        switch (i7) {
            case 1:
                this.f13719n = (A7.o) interfaceC2782k;
                super(1);
                break;
            default:
                this.f13719n = (A7.o) interfaceC2782k;
                break;
        }
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [A7.o, z7.k] */
    /* JADX WARN: Type inference failed for: r2v5, types: [A7.o, z7.k] */
    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f13718m) {
            case 0:
                AbstractC1167g abstractC1167g = (AbstractC1167g) this.f13719n.b((C1171k) obj);
                synchronized (AbstractC1174n.f13721b) {
                    AbstractC1174n.c = AbstractC1174n.c.s(abstractC1167g.d());
                }
                return abstractC1167g;
            default:
                return this.f13719n.b(Long.valueOf(((Number) obj).longValue()));
        }
    }
}

package x;

import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class n0 extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ A7.z f23573m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ float f23574n;
    public final /* synthetic */ InterfaceC2639i o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C2648n f23575p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f23576q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n0(A7.z zVar, float f10, InterfaceC2639i interfaceC2639i, C2648n c2648n, InterfaceC2782k interfaceC2782k) {
        super(1);
        this.f23573m = zVar;
        this.f23574n = f10;
        this.o = interfaceC2639i;
        this.f23575p = c2648n;
        this.f23576q = interfaceC2782k;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        long jLongValue = ((Number) obj).longValue();
        Object obj2 = this.f23573m.l;
        A7.m.c(obj2);
        AbstractC2631e.k((C2645l) obj2, jLongValue, this.f23574n, this.o, this.f23575p, this.f23576q);
        return m7.q.f19267a;
    }
}

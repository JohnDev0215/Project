package N1;

import q7.InterfaceC2189c;
import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class B extends AbstractC2410c {
    public Q l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public c0 f4447m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f4448n;
    public /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Q f4449p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f4450q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public B(Q q4, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f4449p = q4;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.o = obj;
        this.f4450q |= Integer.MIN_VALUE;
        return Q.b(this.f4449p, false, this);
    }
}

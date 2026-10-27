package A;

import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class Q extends AbstractC2410c {
    public V l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C0049y f89m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public C.b f90n;
    public /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ V f91p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f92q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Q(V v10, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f91p = v10;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.o = obj;
        this.f92q |= Integer.MIN_VALUE;
        return V.L0(this.f91p, null, this);
    }
}

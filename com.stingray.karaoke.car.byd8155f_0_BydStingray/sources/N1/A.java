package N1;

import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class A extends AbstractC2410c {
    public Q l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f4444m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f4445n;
    public final /* synthetic */ Q o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f4446p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public A(Q q4, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.o = q4;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f4445n = obj;
        this.f4446p |= Integer.MIN_VALUE;
        return this.o.g(this);
    }
}

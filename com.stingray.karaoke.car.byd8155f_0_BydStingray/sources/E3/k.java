package E3;

import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class k extends AbstractC2410c {
    public l l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C3.g f1733m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f1734n;
    public /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ l f1735p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f1736q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(l lVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f1735p = lVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.o = obj;
        this.f1736q |= Integer.MIN_VALUE;
        return this.f1735p.a(this);
    }
}

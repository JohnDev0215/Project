package P1;

import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class g extends AbstractC2410c {
    public i l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public c f5279m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f5280n;
    public /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ i f5281p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f5282q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(i iVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f5281p = iVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.o = obj;
        this.f5282q |= Integer.MIN_VALUE;
        return this.f5281p.a(null, this);
    }
}

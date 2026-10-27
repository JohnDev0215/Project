package P;

import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class i extends AbstractC2410c {
    public p l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f5231m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ p f5232n;
    public int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(p pVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f5232n = pVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f5231m = obj;
        this.o |= Integer.MIN_VALUE;
        return this.f5232n.a(this);
    }
}

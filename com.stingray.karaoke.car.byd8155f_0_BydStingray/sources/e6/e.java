package e6;

import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class e extends AbstractC2410c {
    public g l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f14441m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ g f14442n;
    public int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(g gVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f14442n = gVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f14441m = obj;
        this.o |= Integer.MIN_VALUE;
        return g.o(this.f14442n, this);
    }
}

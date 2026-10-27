package K0;

import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class i extends AbstractC2410c {
    public j l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f3498m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ j f3499n;
    public int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(j jVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f3499n = jVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f3498m = obj;
        this.o |= Integer.MIN_VALUE;
        return this.f3499n.b(0.0f, this);
    }
}

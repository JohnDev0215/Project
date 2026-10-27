package S0;

import s7.AbstractC2410c;

/* JADX INFO: renamed from: S0.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0694f extends AbstractC2410c {
    public G l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f7833m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0696h f7834n;
    public int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0694f(C0696h c0696h, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f7834n = c0696h;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f7833m = obj;
        this.o |= Integer.MIN_VALUE;
        return this.f7834n.c(null, this);
    }
}

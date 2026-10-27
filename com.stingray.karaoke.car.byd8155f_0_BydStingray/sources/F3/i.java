package F3;

import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class i extends AbstractC2410c {
    public j l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public h f2401m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f2402n;
    public final /* synthetic */ j o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f2403p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(j jVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.o = jVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f2402n = obj;
        this.f2403p |= Integer.MIN_VALUE;
        return this.o.b(null, this);
    }
}

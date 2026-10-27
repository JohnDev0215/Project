package F3;

import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class f extends AbstractC2410c {
    public h l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public j f2390m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f2391n;
    public final /* synthetic */ h o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f2392p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(h hVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.o = hVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f2391n = obj;
        this.f2392p |= Integer.MIN_VALUE;
        return this.o.d(null, this);
    }
}

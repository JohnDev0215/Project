package H;

import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class b extends AbstractC2410c {
    public m0.d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object[] f2744m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f2745n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public /* synthetic */ Object f2746p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ c f2747q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f2748r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(c cVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f2747q = cVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f2746p = obj;
        this.f2748r |= Integer.MIN_VALUE;
        return this.f2747q.a(null, this);
    }
}

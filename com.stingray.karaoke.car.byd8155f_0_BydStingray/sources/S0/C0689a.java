package S0;

import s7.AbstractC2410c;

/* JADX INFO: renamed from: S0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0689a extends AbstractC2410c {
    public P6.a l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public G f7818m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f7819n;
    public final /* synthetic */ P6.a o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f7820p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0689a(P6.a aVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.o = aVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f7819n = obj;
        this.f7820p |= Integer.MIN_VALUE;
        return this.o.c(null, this);
    }
}

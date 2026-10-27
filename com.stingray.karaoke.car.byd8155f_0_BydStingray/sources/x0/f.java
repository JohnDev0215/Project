package x0;

import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class f extends AbstractC2410c {
    public g l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f23684m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f23685n;
    public final /* synthetic */ g o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f23686p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(g gVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.o = gVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f23685n = obj;
        this.f23686p |= Integer.MIN_VALUE;
        return this.o.B(0L, this);
    }
}

package x0;

import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class e extends AbstractC2410c {
    public g l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f23680m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f23681n;
    public /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ g f23682p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f23683q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(g gVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f23682p = gVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.o = obj;
        this.f23683q |= Integer.MIN_VALUE;
        return this.f23682p.q0(0L, 0L, this);
    }
}

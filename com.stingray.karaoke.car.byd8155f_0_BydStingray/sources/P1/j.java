package P1;

import r9.C;
import r9.u;
import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class j extends AbstractC2410c {
    public u l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public u f5292m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public C f5293n;
    public /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ k f5294p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f5295q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(k kVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f5294p = kVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.o = obj;
        this.f5295q |= Integer.MIN_VALUE;
        return this.f5294p.b(null, this);
    }
}

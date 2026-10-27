package P1;

import r9.D;
import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class b extends AbstractC2410c {
    public c l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public D f5267m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f5268n;
    public final /* synthetic */ c o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f5269p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(c cVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.o = cVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f5268n = obj;
        this.f5269p |= Integer.MIN_VALUE;
        return c.a(this.o, this);
    }
}

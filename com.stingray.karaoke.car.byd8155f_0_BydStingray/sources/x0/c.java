package x0;

import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class c extends AbstractC2410c {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ d f23676m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f23677n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(d dVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f23676m = dVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.l = obj;
        this.f23677n |= Integer.MIN_VALUE;
        return this.f23676m.b(0L, this);
    }
}

package P5;

import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class x extends AbstractC2410c {
    public y l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f5406m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ y f5407n;
    public int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(y yVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f5407n = yVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f5406m = obj;
        this.o |= Integer.MIN_VALUE;
        return this.f5407n.a(null, 0L, this);
    }
}

package A;

import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class M0 extends AbstractC2410c {
    public A7.y l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f72m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ S0 f73n;
    public int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public M0(S0 s02, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f73n = s02;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f72m = obj;
        this.o |= Integer.MIN_VALUE;
        return this.f73n.b(0L, this);
    }
}

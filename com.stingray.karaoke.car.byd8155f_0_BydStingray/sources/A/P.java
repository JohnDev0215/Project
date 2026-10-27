package A;

import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class P extends AbstractC2410c {
    public V l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f86m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ V f87n;
    public int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public P(V v10, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f87n = v10;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f86m = obj;
        this.o |= Integer.MIN_VALUE;
        return V.K0(this.f87n, this);
    }
}

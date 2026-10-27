package N1;

import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class O extends AbstractC2410c {
    public A7.x l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f4481m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Q f4482n;
    public int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public O(Q q4, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f4482n = q4;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f4481m = obj;
        this.o |= Integer.MIN_VALUE;
        return this.f4482n.i(null, false, this);
    }
}

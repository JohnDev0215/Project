package x;

import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class Y extends AbstractC2410c {
    public C2634f0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f23470m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C2634f0 f23471n;
    public int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Y(C2634f0 c2634f0, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f23471n = c2634f0;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f23470m = obj;
        this.o |= Integer.MIN_VALUE;
        return C2634f0.c1(this.f23471n, this);
    }
}

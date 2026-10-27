package y;

import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class T extends AbstractC2410c {
    public W l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f23867m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ W f23868n;
    public int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public T(W w10, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f23868n = w10;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f23867m = obj;
        this.o |= Integer.MIN_VALUE;
        return W.I0(this.f23868n, this);
    }
}

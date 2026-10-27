package y;

import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class S extends AbstractC2410c {
    public W l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C.h f23864m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f23865n;
    public final /* synthetic */ W o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f23866p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public S(W w10, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.o = w10;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f23865n = obj;
        this.f23866p |= Integer.MIN_VALUE;
        return W.H0(this.o, this);
    }
}

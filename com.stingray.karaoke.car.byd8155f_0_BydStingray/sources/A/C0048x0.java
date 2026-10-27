package A;

import s7.AbstractC2410c;

/* JADX INFO: renamed from: A.x0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0048x0 extends AbstractC2410c {
    public long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f297m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0050y0 f298n;
    public int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0048x0(C0050y0 c0050y0, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f298n = c0050y0;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f297m = obj;
        this.o |= Integer.MIN_VALUE;
        return this.f298n.q0(0L, 0L, this);
    }
}

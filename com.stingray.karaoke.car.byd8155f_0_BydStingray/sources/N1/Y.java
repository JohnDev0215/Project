package N1;

import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class Y extends AbstractC2410c {
    public Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public b9.c f4507m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f4508n;
    public final /* synthetic */ b0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f4509p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Y(b0 b0Var, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.o = b0Var;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f4508n = obj;
        this.f4509p |= Integer.MIN_VALUE;
        return this.o.b(null, this);
    }
}

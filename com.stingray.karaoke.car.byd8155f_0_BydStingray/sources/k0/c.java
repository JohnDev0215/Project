package K0;

import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class c extends AbstractC2410c {
    public f l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f3482m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public a1.i f3483n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f3484p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public /* synthetic */ Object f3485q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ f f3486r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f3487s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(f fVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f3486r = fVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f3485q = obj;
        this.f3487s |= Integer.MIN_VALUE;
        return f.a(this.f3486r, null, null, this);
    }
}

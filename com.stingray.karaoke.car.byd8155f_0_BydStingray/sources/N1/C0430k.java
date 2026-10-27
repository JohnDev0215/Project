package N1;

import s7.AbstractC2410c;

/* JADX INFO: renamed from: N1.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0430k extends AbstractC2410c {
    public Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f4533m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f4534n;
    public A7.z o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Q f4535p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public /* synthetic */ Object f4536q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ C0431l f4537r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f4538s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0430k(C0431l c0431l, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f4537r = c0431l;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f4536q = obj;
        this.f4538s |= Integer.MIN_VALUE;
        return this.f4537r.a(null, this);
    }
}

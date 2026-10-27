package A;

import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class S extends AbstractC2410c {
    public V l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C0051z f97m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f98n;
    public final /* synthetic */ V o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f99p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public S(V v10, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.o = v10;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f98n = obj;
        this.f99p |= Integer.MIN_VALUE;
        return V.M0(this.o, null, this);
    }
}

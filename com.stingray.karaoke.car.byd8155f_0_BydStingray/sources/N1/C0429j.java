package N1;

import s7.AbstractC2410c;

/* JADX INFO: renamed from: N1.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0429j extends AbstractC2410c {
    public B8.t l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f4531m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ B8.t f4532n;
    public int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0429j(B8.t tVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f4532n = tVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f4531m = obj;
        this.o |= Integer.MIN_VALUE;
        return this.f4532n.L(this);
    }
}

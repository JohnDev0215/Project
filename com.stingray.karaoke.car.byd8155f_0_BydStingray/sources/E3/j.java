package E3;

import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class j extends AbstractC2410c {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ l f1731m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f1732n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(l lVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f1731m = lVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.l = obj;
        this.f1732n |= Integer.MIN_VALUE;
        return this.f1731m.b(null, this);
    }
}

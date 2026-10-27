package b6;

import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class j extends AbstractC2410c {
    public o l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f12251m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f12252n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public /* synthetic */ Object f12253p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ o f12254q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f12255r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(o oVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f12254q = oVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f12253p = obj;
        this.f12255r |= Integer.MIN_VALUE;
        return this.f12254q.o(false, 0, 0, this);
    }
}

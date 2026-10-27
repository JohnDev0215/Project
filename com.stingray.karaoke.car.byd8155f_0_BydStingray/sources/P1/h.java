package P1;

import r9.z;
import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class h extends AbstractC2410c {
    public i l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f5283m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public z f5284n;
    public Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public /* synthetic */ Object f5285p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ i f5286q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f5287r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(i iVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f5286q = iVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f5285p = obj;
        this.f5287r |= Integer.MIN_VALUE;
        return this.f5286q.b(null, this);
    }
}

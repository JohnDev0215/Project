package B3;

import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class d extends AbstractC2410c {
    public Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public b9.i f619m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f620n;
    public final /* synthetic */ e o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f621p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(e eVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.o = eVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f620n = obj;
        this.f621p |= Integer.MIN_VALUE;
        return this.o.a(this);
    }
}

package Q1;

import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class c extends AbstractC2410c {
    public d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f6109m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ d f6110n;
    public int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(d dVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f6110n = dVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f6109m = obj;
        this.o |= Integer.MIN_VALUE;
        return this.f6110n.a(null, this);
    }
}

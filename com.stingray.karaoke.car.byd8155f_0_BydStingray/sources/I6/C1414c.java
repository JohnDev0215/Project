package i6;

import s7.AbstractC2410c;

/* JADX INFO: renamed from: i6.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1414c extends AbstractC2410c {
    public C1415d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f15757m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1415d f15758n;
    public int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1414c(C1415d c1415d, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f15758n = c1415d;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f15757m = obj;
        this.o |= Integer.MIN_VALUE;
        return this.f15758n.f(this);
    }
}

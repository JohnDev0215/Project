package S0;

import l3.P;
import s7.AbstractC2410c;

/* JADX INFO: renamed from: S0.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0699k extends AbstractC2410c {
    public P l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C0698j f7844m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f7845n;
    public final /* synthetic */ P o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f7846p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0699k(P p10, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.o = p10;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f7845n = obj;
        this.f7846p |= Integer.MIN_VALUE;
        return this.o.C(null, null, null, this);
    }
}

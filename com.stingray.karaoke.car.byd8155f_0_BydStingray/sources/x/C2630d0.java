package x;

import s7.AbstractC2410c;

/* JADX INFO: renamed from: x.d0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2630d0 extends AbstractC2410c {
    public C2634f0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f23500m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f23501n;
    public final /* synthetic */ C2634f0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f23502p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2630d0(C2634f0 c2634f0, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.o = c2634f0;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f23501n = obj;
        this.f23502p |= Integer.MIN_VALUE;
        return C2634f0.d1(this.o, this);
    }
}

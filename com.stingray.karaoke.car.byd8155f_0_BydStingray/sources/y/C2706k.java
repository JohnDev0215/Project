package y;

import s7.AbstractC2410c;

/* JADX INFO: renamed from: y.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2706k extends AbstractC2410c {
    public C2709n l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f23944m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f23945n;
    public final /* synthetic */ C2709n o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f23946p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2706k(C2709n c2709n, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.o = c2709n;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f23945n = obj;
        this.f23946p |= Integer.MIN_VALUE;
        return this.o.b(0L, null, this);
    }
}

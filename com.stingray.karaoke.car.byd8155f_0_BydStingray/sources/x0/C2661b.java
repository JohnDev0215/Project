package x0;

import s7.AbstractC2410c;

/* JADX INFO: renamed from: x0.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2661b extends AbstractC2410c {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ d f23674m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f23675n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2661b(d dVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f23674m = dVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.l = obj;
        this.f23675n |= Integer.MIN_VALUE;
        return this.f23674m.a(0L, 0L, this);
    }
}

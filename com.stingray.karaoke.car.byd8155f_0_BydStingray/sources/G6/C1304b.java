package g6;

import q7.InterfaceC2189c;
import s7.AbstractC2410c;

/* JADX INFO: renamed from: g6.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1304b extends AbstractC2410c {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f14983m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1305c f14984n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1304b(C1305c c1305c, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f14984n = c1305c;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.l = obj;
        this.f14983m |= Integer.MIN_VALUE;
        return this.f14984n.f(null, this);
    }
}

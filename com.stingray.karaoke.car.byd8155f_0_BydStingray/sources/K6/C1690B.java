package k6;

import g6.C1305c;
import q7.InterfaceC2189c;
import s7.AbstractC2410c;

/* JADX INFO: renamed from: k6.B, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1690B extends AbstractC2410c {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f18227m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1305c f18228n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1690B(C1305c c1305c, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f18228n = c1305c;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.l = obj;
        this.f18227m |= Integer.MIN_VALUE;
        return this.f18228n.f(null, this);
    }
}

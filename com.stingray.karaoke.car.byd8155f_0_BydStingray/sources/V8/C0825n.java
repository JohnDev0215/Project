package V8;

import N1.C0436q;
import q7.InterfaceC2189c;
import s7.AbstractC2410c;

/* JADX INFO: renamed from: V8.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0825n extends AbstractC2410c {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0436q f9636m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f9637n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0825n(C0436q c0436q, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f9636m = c0436q;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.l = obj;
        this.f9637n |= Integer.MIN_VALUE;
        return this.f9636m.f(null, this);
    }
}

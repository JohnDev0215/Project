package T;

import F0.C0208i0;
import q7.InterfaceC2189c;
import s7.AbstractC2410c;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: T.f0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0740f0 extends AbstractC2410c {
    public C0208i0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public InterfaceC2782k f8247m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f8248n;
    public final /* synthetic */ C0208i0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f8249p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0740f0(C0208i0 c0208i0, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.o = c0208i0;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f8248n = obj;
        this.f8249p |= Integer.MIN_VALUE;
        return this.o.o(null, this);
    }
}

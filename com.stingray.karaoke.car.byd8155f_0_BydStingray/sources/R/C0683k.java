package R;

import q7.InterfaceC2189c;
import s7.AbstractC2410c;

/* JADX INFO: renamed from: R.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0683k extends AbstractC2410c {
    public C0684l l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f7302m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f7303n;
    public final /* synthetic */ C0684l o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f7304p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0683k(C0684l c0684l, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.o = c0684l;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f7303n = obj;
        this.f7304p |= Integer.MIN_VALUE;
        return this.o.f(null, this);
    }
}

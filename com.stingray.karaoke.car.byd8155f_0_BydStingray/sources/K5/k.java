package K5;

import q7.InterfaceC2189c;
import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class k extends AbstractC2410c {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f3639m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ A3.m f3640n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(A3.m mVar, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f3640n = mVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.l = obj;
        this.f3639m |= Integer.MIN_VALUE;
        return this.f3640n.f(null, this);
    }
}

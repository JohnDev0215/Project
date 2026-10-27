package A3;

import q7.InterfaceC2189c;
import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class s extends AbstractC2410c {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f402m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ m f403n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(m mVar, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f403n = mVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.l = obj;
        this.f402m |= Integer.MIN_VALUE;
        return this.f403n.f(null, this);
    }
}

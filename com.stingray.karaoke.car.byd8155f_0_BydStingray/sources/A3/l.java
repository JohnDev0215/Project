package A3;

import q7.InterfaceC2189c;
import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class l extends AbstractC2410c {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f393m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ m f394n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(m mVar, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f394n = mVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.l = obj;
        this.f393m |= Integer.MIN_VALUE;
        return this.f394n.f(null, this);
    }
}

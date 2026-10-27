package W8;

import q7.InterfaceC2189c;
import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class p extends AbstractC2410c {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ q f9928m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f9929n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(q qVar, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f9928m = qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.l = obj;
        this.f9929n |= Integer.MIN_VALUE;
        return this.f9928m.f(null, this);
    }
}

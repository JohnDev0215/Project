package V8;

import q7.InterfaceC2189c;
import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class H extends AbstractC2410c {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f9570m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C.f f9571n;
    public Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public InterfaceC0818g f9572p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public H(C.f fVar, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f9571n = fVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.l = obj;
        this.f9570m |= Integer.MIN_VALUE;
        return this.f9571n.f(null, this);
    }
}

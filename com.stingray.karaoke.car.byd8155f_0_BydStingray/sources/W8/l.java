package W8;

import K.C0358p;
import q7.InterfaceC2189c;
import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class l extends AbstractC2410c {
    public C0358p l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f9922m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f9923n;
    public final /* synthetic */ C0358p o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f9924p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(C0358p c0358p, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.o = c0358p;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f9923n = obj;
        this.f9924p |= Integer.MIN_VALUE;
        return this.o.f(null, this);
    }
}

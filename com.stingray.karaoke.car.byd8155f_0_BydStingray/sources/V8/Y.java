package V8;

import q7.InterfaceC2189c;
import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class Y extends AbstractC2410c {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C.f f9600m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f9601n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Y(C.f fVar, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f9600m = fVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.l = obj;
        this.f9601n |= Integer.MIN_VALUE;
        return this.f9600m.a(0, this);
    }
}

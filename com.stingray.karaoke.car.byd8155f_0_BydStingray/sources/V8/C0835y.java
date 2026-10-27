package V8;

import q7.InterfaceC2189c;
import s7.AbstractC2410c;

/* JADX INFO: renamed from: V8.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0835y extends AbstractC2410c {
    public C.f l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f9664m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f9665n;
    public final /* synthetic */ C.f o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Object f9666p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0835y(C.f fVar, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.o = fVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f9664m = obj;
        this.f9665n |= Integer.MIN_VALUE;
        return this.o.f(null, this);
    }
}

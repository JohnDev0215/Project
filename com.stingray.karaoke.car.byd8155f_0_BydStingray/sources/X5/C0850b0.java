package X5;

import q7.InterfaceC2189c;
import s7.AbstractC2410c;

/* JADX INFO: renamed from: X5.b0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0850b0 extends AbstractC2410c {
    public C.f l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public N5.f0 f10225m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public D0 f10226n;
    public /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C.f f10227p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f10228q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0850b0(C.f fVar, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f10227p = fVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.o = obj;
        this.f10228q |= Integer.MIN_VALUE;
        return this.f10227p.b(null, this);
    }
}

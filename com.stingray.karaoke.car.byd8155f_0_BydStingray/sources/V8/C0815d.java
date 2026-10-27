package V8;

import R.C0684l;
import q7.InterfaceC2189c;
import s7.AbstractC2410c;

/* JADX INFO: renamed from: V8.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0815d extends AbstractC2410c {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0684l f9612m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f9613n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0815d(C0684l c0684l, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f9612m = c0684l;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.l = obj;
        this.f9613n |= Integer.MIN_VALUE;
        return this.f9612m.f(null, this);
    }
}

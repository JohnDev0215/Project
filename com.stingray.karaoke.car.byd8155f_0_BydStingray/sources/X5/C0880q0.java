package X5;

import q7.InterfaceC2189c;
import s7.AbstractC2410c;

/* JADX INFO: renamed from: X5.q0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0880q0 extends AbstractC2410c {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f10374m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ A3.m f10375n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0880q0(A3.m mVar, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f10375n = mVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.l = obj;
        this.f10374m |= Integer.MIN_VALUE;
        return this.f10375n.f(null, this);
    }
}

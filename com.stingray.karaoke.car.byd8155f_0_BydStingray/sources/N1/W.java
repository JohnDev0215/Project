package N1;

import b9.InterfaceC1014a;
import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class W extends AbstractC2410c {
    public B8.t l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public InterfaceC1014a f4502m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f4503n;
    public final /* synthetic */ B8.t o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f4504p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public W(B8.t tVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.o = tVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f4503n = obj;
        this.f4504p |= Integer.MIN_VALUE;
        return this.o.m0(this);
    }
}

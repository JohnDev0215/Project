package V8;

import q7.InterfaceC2189c;
import r7.EnumC2303a;
import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class Q extends AbstractC2410c {
    public S l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public InterfaceC0818g f9580m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public T f9581n;
    public S8.W o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public /* synthetic */ Object f9582p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ S f9583q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f9584r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Q(S s6, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f9583q = s6;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        this.f9582p = obj;
        this.f9584r |= Integer.MIN_VALUE;
        S.m(this.f9583q, null, this);
        return EnumC2303a.l;
    }
}

package A;

import s7.AbstractC2410c;

/* JADX INFO: renamed from: A.i0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0019i0 extends AbstractC2410c {
    public C0023k0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f185m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0023k0 f186n;
    public int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0019i0(C0023k0 c0023k0, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f186n = c0023k0;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f185m = obj;
        this.o |= Integer.MIN_VALUE;
        return this.f186n.a(this);
    }
}

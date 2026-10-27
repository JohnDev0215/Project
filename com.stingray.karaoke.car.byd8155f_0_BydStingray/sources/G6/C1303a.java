package g6;

import s7.AbstractC2410c;

/* JADX INFO: renamed from: g6.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1303a extends AbstractC2410c {
    public C1306d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f14981m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1306d f14982n;
    public int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1303a(C1306d c1306d, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f14982n = c1306d;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f14981m = obj;
        this.o |= Integer.MIN_VALUE;
        return this.f14982n.f(this);
    }
}

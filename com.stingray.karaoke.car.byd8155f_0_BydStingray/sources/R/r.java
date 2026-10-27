package R;

import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class r extends AbstractC2410c {
    public w l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f7317m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ w f7318n;
    public int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(w wVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f7318n = wVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f7317m = obj;
        this.o |= Integer.MIN_VALUE;
        return this.f7318n.a(null, null, null, this);
    }
}

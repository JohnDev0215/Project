package R;

import s7.AbstractC2410c;

/* JADX INFO: renamed from: R.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0686n extends AbstractC2410c {
    public w l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f7309m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ w f7310n;
    public int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0686n(w wVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f7310n = wVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f7309m = obj;
        this.o |= Integer.MIN_VALUE;
        return this.f7310n.b(null, null, this);
    }
}

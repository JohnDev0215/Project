package G;

import q7.C2196j;
import s7.AbstractC2410c;

/* JADX INFO: renamed from: G.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0292b extends AbstractC2410c {
    public C2196j l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f2540m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0293c f2541n;
    public int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0292b(C0293c c0293c, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f2541n = c0293c;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f2540m = obj;
        this.o |= Integer.MIN_VALUE;
        return this.f2541n.i(this);
    }
}

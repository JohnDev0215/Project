package y0;

import s7.AbstractC2408a;
import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class y extends AbstractC2410c {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ z f24106m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f24107n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y(z zVar, AbstractC2408a abstractC2408a) {
        super(abstractC2408a);
        this.f24106m = zVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.l = obj;
        this.f24107n |= Integer.MIN_VALUE;
        return this.f24106m.f(0L, null, this);
    }
}

package y0;

import S8.k0;
import s7.AbstractC2408a;
import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class w extends AbstractC2410c {
    public k0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f24102m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ z f24103n;
    public int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(z zVar, AbstractC2408a abstractC2408a) {
        super(abstractC2408a);
        this.f24103n = zVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f24102m = obj;
        this.o |= Integer.MIN_VALUE;
        return this.f24103n.d(0L, null, this);
    }
}

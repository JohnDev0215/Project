package F0;

import s7.AbstractC2410c;
import u.C2477q;

/* JADX INFO: loaded from: classes.dex */
public final class I extends AbstractC2410c {
    public M l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C2477q f2037m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public U8.d f2038n;
    public /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ M f2039p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f2040q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public I(M m10, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f2039p = m10;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.o = obj;
        this.f2040q |= Integer.MIN_VALUE;
        return this.f2039p.l(this);
    }
}

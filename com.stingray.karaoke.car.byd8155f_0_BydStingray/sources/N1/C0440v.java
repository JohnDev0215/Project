package N1;

import S8.C0718o;
import s7.AbstractC2410c;

/* JADX INFO: renamed from: N1.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0440v extends AbstractC2410c {
    public Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Q f4556m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public C0718o f4557n;
    public /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Q f4558p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f4559q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0440v(Q q4, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f4558p = q4;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.o = obj;
        this.f4559q |= Integer.MIN_VALUE;
        return Q.a(this.f4558p, null, this);
    }
}

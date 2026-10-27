package N1;

import java.io.Serializable;
import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class E extends AbstractC2410c {
    public Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f4456m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Serializable f4457n;
    public A7.z o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f4458p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f4459q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public /* synthetic */ Object f4460r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Q f4461s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f4462t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public E(Q q4, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f4461s = q4;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f4460r = obj;
        this.f4462t |= Integer.MIN_VALUE;
        return Q.e(this.f4461s, false, this);
    }
}

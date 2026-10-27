package S0;

import java.util.List;
import s7.AbstractC2410c;

/* JADX INFO: renamed from: S0.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0692d extends AbstractC2410c {
    public C0696h l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public List f7825m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public G f7826n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f7827p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public /* synthetic */ Object f7828q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ C0696h f7829r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f7830s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0692d(C0696h c0696h, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f7829r = c0696h;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f7828q = obj;
        this.f7830s |= Integer.MIN_VALUE;
        return this.f7829r.b(this);
    }
}

package A;

import s7.AbstractC2410c;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class t1 extends AbstractC2410c {
    public v1 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public m7.c f270m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public InterfaceC2772a f271n;
    public float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public /* synthetic */ Object f272p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ v1 f273q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f274r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t1(v1 v1Var, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f273q = v1Var;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f272p = obj;
        this.f274r |= Integer.MIN_VALUE;
        return this.f273q.a(null, null, this);
    }
}

package F;

import s7.AbstractC2410c;
import y.j0;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class w extends AbstractC2410c {
    public y l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public j0 f1966m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public InterfaceC2785n f1967n;
    public /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ y f1968p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f1969q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(y yVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f1968p = yVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.o = obj;
        this.f1969q |= Integer.MIN_VALUE;
        return this.f1968p.e(null, null, this);
    }
}

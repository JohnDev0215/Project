package D;

import z7.InterfaceC2782k;

/* JADX INFO: renamed from: D.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0134p extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0.V f1093m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0.J f1094n;
    public final /* synthetic */ C0.M o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f1095p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f1096q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ r f1097r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0134p(C0.V v10, C0.J j3, C0.M m10, int i7, int i10, r rVar) {
        super(1);
        this.f1093m = v10;
        this.f1094n = j3;
        this.o = m10;
        this.f1095p = i7;
        this.f1096q = i10;
        this.f1097r = rVar;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        a1.k layoutDirection = this.o.getLayoutDirection();
        g0.c cVar = this.f1097r.f1103a;
        AbstractC0133o.b((C0.U) obj, this.f1093m, this.f1094n, layoutDirection, this.f1095p, this.f1096q, cVar);
        return m7.q.f19267a;
    }
}

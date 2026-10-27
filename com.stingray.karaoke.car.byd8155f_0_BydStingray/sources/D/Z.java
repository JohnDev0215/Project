package D;

import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class Z extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ a0 f1060m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f1061n;
    public final /* synthetic */ C0.V o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f1062p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ C0.M f1063q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Z(a0 a0Var, int i7, C0.V v10, int i10, C0.M m10) {
        super(1);
        this.f1060m = a0Var;
        this.f1061n = i7;
        this.o = v10;
        this.f1062p = i10;
        this.f1063q = m10;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [A7.o, z7.n] */
    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        ?? r02 = this.f1060m.f1065A;
        C0.V v10 = this.o;
        C0.U.e((C0.U) obj, v10, ((a1.h) r02.invoke(new a1.j(H7.H.c(this.f1061n - v10.l, this.f1062p - v10.f871m)), this.f1063q.getLayoutDirection())).f11033a);
        return m7.q.f19267a;
    }
}

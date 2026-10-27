package K;

import F0.C0229t0;
import F0.R0;
import a.AbstractC0904a;
import l0.C1717h;
import l0.C1723n;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class B extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0331b0 f3120m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1723n f3121n;
    public final /* synthetic */ boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ boolean f3122p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ O.K f3123q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ T0.s f3124r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public B(C0331b0 c0331b0, C1723n c1723n, boolean z6, boolean z10, O.K k10, T0.s sVar) {
        super(1);
        this.f3120m = c0331b0;
        this.f3121n = c1723n;
        this.o = z6;
        this.f3122p = z10;
        this.f3123q = k10;
        this.f3124r = sVar;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        R0 r02;
        long j3 = ((m0.c) obj).f18880a;
        C0331b0 c0331b0 = this.f3120m;
        if (!c0331b0.b()) {
            this.f3121n.a(C1717h.f18335q);
        } else if (!this.o && (r02 = c0331b0.c) != null) {
            ((C0229t0) r02).b();
        }
        if (c0331b0.b() && this.f3122p) {
            if (c0331b0.a() != P.f3194m) {
                G0 g0D = c0331b0.d();
                if (g0D != null) {
                    int iA = this.f3124r.a(g0D.b(j3, true));
                    c0331b0.f3235t.b(T0.y.a((T0.y) c0331b0.d.l, null, AbstractC0904a.q(iA, iA), 5));
                    if (c0331b0.f3220a.f3338a.l.length() > 0) {
                        c0331b0.f3228k.setValue(P.f3195n);
                    }
                }
            } else {
                this.f3123q.e(new m0.c(j3));
            }
        }
        return m7.q.f19267a;
    }
}

package Q;

import T.C0735d;
import T.C0761q;
import n0.C1868S;
import n0.C1891t;
import x.AbstractC2631e;
import x.AbstractC2637h;
import y.C2716v;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class Q0 extends A7.o implements InterfaceC2786o {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C.k f5654m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f5655n;
    public final /* synthetic */ boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ M0 f5656p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ float f5657q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ float f5658r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Q0(C.k kVar, boolean z6, boolean z10, M0 m02, float f10, float f11) {
        super(3);
        this.f5654m = kVar;
        this.f5655n = z6;
        this.o = z10;
        this.f5656p = m02;
        this.f5657q = f10;
        this.f5658r = f11;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        T.Q0 q0R;
        T.Q0 q0R2;
        C0761q c0761q = (C0761q) obj2;
        ((Number) obj3).intValue();
        c0761q.Q(-891038934);
        boolean zBooleanValue = ((Boolean) com.bumptech.glide.c.S(this.f5654m, c0761q, 0).getValue()).booleanValue();
        float f10 = R.O.f7278b;
        M0 m02 = this.f5656p;
        boolean z6 = this.f5655n;
        long j3 = !z6 ? m02.f5603n : this.o ? m02.o : zBooleanValue ? m02.l : m02.f5602m;
        if (z6) {
            c0761q.Q(1023053998);
            q0R = w.L.a(j3, AbstractC2631e.q(150, 0, null, 6), null, c0761q, 48, 12);
            c0761q.p(false);
        } else {
            c0761q.Q(1023165505);
            q0R = C0735d.R(new C1891t(j3), c0761q);
            c0761q.p(false);
        }
        T.Q0 q02 = q0R;
        float f11 = this.f5658r;
        if (z6) {
            c0761q.Q(1023269417);
            if (zBooleanValue) {
                f11 = this.f5657q;
            }
            q0R2 = AbstractC2637h.a(f11, AbstractC2631e.q(150, 0, null, 6), null, c0761q, 48, 12);
            c0761q.p(false);
        } else {
            c0761q.Q(1023478388);
            q0R2 = C0735d.R(new a1.e(f11), c0761q);
            c0761q.p(false);
        }
        T.X xR = C0735d.R(new C2716v(((a1.e) q0R2.getValue()).l, new C1868S(((C1891t) q02.getValue()).f19453a)), c0761q);
        g0.l lVar = g0.l.f14684a;
        float f12 = X0.f5757a;
        g0.o oVarC = androidx.compose.ui.draw.a.c(lVar, new A.L0(xR, 5));
        c0761q.p(false);
        return oVarC;
    }
}

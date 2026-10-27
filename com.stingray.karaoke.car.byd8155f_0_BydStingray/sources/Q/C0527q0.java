package Q;

import D.AbstractC0133o;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import F0.AbstractC0227s0;
import T.C0735d;
import T.C0761q;
import T.InterfaceC0744h0;
import h2.AbstractC1347d;
import n0.InterfaceC1866P;
import y.C2716v;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q.q0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0527q0 extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g0.o f6018m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC1866P f6019n;
    public final /* synthetic */ long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ float f6020p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ C2716v f6021q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ float f6022r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ b0.b f6023s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0527q0(g0.o oVar, InterfaceC1866P interfaceC1866P, long j3, float f10, C2716v c2716v, float f11, b0.b bVar) {
        super(2);
        this.f6018m = oVar;
        this.f6019n = interfaceC1866P;
        this.o = j3;
        this.f6020p = f10;
        this.f6021q = c2716v;
        this.f6022r = f11;
        this.f6023s = bVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        C0761q c0761q = (C0761q) obj;
        int iIntValue = ((Number) obj2).intValue() & 3;
        m7.q qVar = m7.q.f19267a;
        if (iIntValue == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            long jC = AbstractC0530s0.c(this.o, this.f6020p, c0761q);
            float fY = ((a1.b) c0761q.k(AbstractC0227s0.f2259f)).y(this.f6022r);
            g0.o oVarA = y0.v.a(L0.k.a(AbstractC0530s0.b(this.f6018m, this.f6019n, jC, this.f6021q, fY), false, C0510i.f5922p), qVar, new C0525p0(2, null));
            C0.K kE = AbstractC0133o.e(g0.b.l, true);
            int i7 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVarA);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, kE, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i7))) {
                AbstractC1347d.l(i7, c0761q, i7, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            this.f6023s.invoke(c0761q, 0);
            c0761q.p(true);
        }
        return qVar;
    }
}

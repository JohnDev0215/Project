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
import androidx.compose.material3.MinimumInteractiveModifier;
import h2.AbstractC1347d;
import n0.InterfaceC1866P;
import y.C2716v;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q.r0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0528r0 extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g0.o f6028m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC1866P f6029n;
    public final /* synthetic */ long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ float f6030p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ C2716v f6031q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ C.k f6032r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ boolean f6033s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f6034t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ float f6035u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ b0.b f6036v;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0528r0(g0.o oVar, InterfaceC1866P interfaceC1866P, long j3, float f10, C2716v c2716v, C.k kVar, boolean z6, InterfaceC2772a interfaceC2772a, float f11, b0.b bVar) {
        super(2);
        this.f6028m = oVar;
        this.f6029n = interfaceC1866P;
        this.o = j3;
        this.f6030p = f10;
        this.f6031q = c2716v;
        this.f6032r = kVar;
        this.f6033s = z6;
        this.f6034t = interfaceC2772a;
        this.f6035u = f11;
        this.f6036v = bVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        C0761q c0761q = (C0761q) obj;
        if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            T.R0 r02 = AbstractC0537w.f6062a;
            g0.o oVarD = androidx.compose.foundation.a.d(AbstractC0530s0.b(this.f6028m.k(MinimumInteractiveModifier.f11534a), this.f6029n, AbstractC0530s0.c(this.o, this.f6030p, c0761q), this.f6031q, ((a1.b) c0761q.k(AbstractC0227s0.f2259f)).y(this.f6035u)), this.f6032r, M.c(false, 0.0f, c0761q, 0, 7), this.f6033s, null, this.f6034t, 24);
            C0.K kE = AbstractC0133o.e(g0.b.l, true);
            int i7 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD2 = g0.a.d(c0761q, oVarD);
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
            C0735d.V(c0761q, oVarD2, C0160j.c);
            this.f6036v.invoke(c0761q, 0);
            c0761q.p(true);
        }
        return m7.q.f19267a;
    }
}

package R;

import D.AbstractC0133o;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import K.AbstractC0334d;
import T.C0735d;
import T.C0761q;
import T.InterfaceC0744h0;
import h2.AbstractC1347d;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class G extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f7244m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ long f7245n;
    public final /* synthetic */ Object o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ G(int i7, long j3, Object obj) {
        super(2);
        this.f7244m = i7;
        this.f7245n = j3;
        this.o = obj;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f7244m) {
            case 0:
                C0761q c0761q = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
                    c0761q.K();
                } else {
                    O.c(this.f7245n, (InterfaceC2785n) this.o, c0761q, 0);
                }
                break;
            case 1:
                C0761q c0761q2 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    O.c(this.f7245n, (InterfaceC2785n) this.o, c0761q2, 0);
                }
                break;
            default:
                C0761q c0761q3 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q3.x()) {
                    c0761q3.K();
                } else {
                    long j3 = this.f7245n;
                    if (j3 != 9205357640488583168L) {
                        c0761q3.Q(1828881000);
                        g0.o oVarJ = androidx.compose.foundation.layout.c.j((g0.o) this.o, a1.g.b(j3), a1.g.a(j3), 0.0f, 0.0f, 12);
                        C0.K kE = AbstractC0133o.e(g0.b.f14662m, false);
                        int i7 = c0761q3.f8304P;
                        InterfaceC0744h0 interfaceC0744h0M = c0761q3.m();
                        g0.o oVarD = g0.a.d(c0761q3, oVarJ);
                        InterfaceC0161k.f1681b.getClass();
                        C0159i c0159i = C0160j.f1678b;
                        c0761q3.U();
                        if (c0761q3.f8303O) {
                            c0761q3.l(c0159i);
                        } else {
                            c0761q3.d0();
                        }
                        C0735d.V(c0761q3, kE, C0160j.f1679e);
                        C0735d.V(c0761q3, interfaceC0744h0M, C0160j.d);
                        C0158h c0158h = C0160j.f1680f;
                        if (c0761q3.f8303O || !A7.m.a(c0761q3.G(), Integer.valueOf(i7))) {
                            AbstractC1347d.l(i7, c0761q3, i7, c0158h);
                        }
                        C0735d.V(c0761q3, oVarD, C0160j.c);
                        AbstractC0334d.b(null, c0761q3, 0, 1);
                        c0761q3.p(true);
                        c0761q3.p(false);
                    } else {
                        c0761q3.Q(1829217412);
                        AbstractC0334d.b((g0.o) this.o, c0761q3, 0, 0);
                        c0761q3.p(false);
                    }
                }
                break;
        }
        return m7.q.f19267a;
    }
}

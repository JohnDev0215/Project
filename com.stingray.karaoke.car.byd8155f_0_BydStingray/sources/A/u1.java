package A;

import h2.AbstractC1347d;
import l3.C1764u;
import n0.C1877f;
import n0.C1883l;
import x.C2629d;
import x.C2645l;
import x.C2649o;
import x3.AbstractC2673a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class u1 extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f278m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ float f279n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f280p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ u1(float f10, Object obj, Object obj2, int i7) {
        super(1);
        this.f278m = i7;
        this.f279n = f10;
        this.o = obj;
        this.f280p = obj2;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f278m) {
            case 0:
                long jLongValue = ((Number) obj).longValue();
                v1 v1Var = (v1) this.o;
                if (v1Var.f290b == Long.MIN_VALUE) {
                    v1Var.f290b = jLongValue;
                }
                float f10 = v1Var.f291e;
                C2649o c2649o = new C2649o(f10);
                float f11 = this.f279n;
                C2649o c2649o2 = v1.f288f;
                long jB = f11 == 0.0f ? v1Var.f289a.b(new C2649o(f10), c2649o2, v1Var.c) : C7.a.Y((jLongValue - v1Var.f290b) / f11);
                float f12 = ((C2649o) v1Var.f289a.o(jB, c2649o, c2649o2, v1Var.c)).f23577a;
                v1Var.c = (C2649o) v1Var.f289a.c(jB, c2649o, c2649o2, v1Var.c);
                v1Var.f290b = jLongValue;
                float f13 = v1Var.f291e - f12;
                v1Var.f291e = f12;
                ((InterfaceC2782k) this.f280p).b(Float.valueOf(f13));
                return m7.q.f19267a;
            case 1:
                C2645l c2645l = (C2645l) obj;
                float f14 = this.f279n;
                float fR = 0.0f;
                if (f14 > 0.0f) {
                    fR = AbstractC2673a.s(((Number) c2645l.f23554e.getValue()).floatValue(), f14);
                } else if (f14 < 0.0f) {
                    fR = AbstractC2673a.r(((Number) c2645l.f23554e.getValue()).floatValue(), f14);
                }
                A7.w wVar = (A7.w) this.o;
                float f15 = fR - wVar.l;
                if (f15 != ((InterfaceC0033p0) this.f280p).a(f15) || fR != ((Number) c2645l.f23554e.getValue()).floatValue()) {
                    c2645l.a();
                }
                wVar.l += f15;
                return m7.q.f19267a;
            case 2:
                E0.H h10 = (E0.H) obj;
                h10.a();
                float f16 = this.f279n;
                C1877f c1877f = (C1877f) this.o;
                C1883l c1883l = (C1883l) this.f280p;
                h3.y yVar = h10.l.f20638m;
                long jY = yVar.y();
                yVar.m().m();
                try {
                    C1764u c1764u = (C1764u) yVar.f15499m;
                    c1764u.X(f16, 0.0f);
                    c1764u.S(45.0f, 0L);
                    p0.d.t(h10, c1877f, c1883l);
                    AbstractC1347d.o(yVar, jY);
                    return m7.q.f19267a;
                } catch (Throwable th) {
                    AbstractC1347d.o(yVar, jY);
                    throw th;
                }
            default:
                C0.U u4 = (C0.U) obj;
                C2629d c2629d = ((Q.g1) this.f280p).B;
                C0.U.f(u4, (C0.V) this.o, (int) (c2629d != null ? ((Number) c2629d.d()).floatValue() : this.f279n), 0);
                return m7.q.f19267a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u1(v1 v1Var, float f10, InterfaceC2782k interfaceC2782k) {
        super(1);
        this.f278m = 0;
        this.o = v1Var;
        this.f279n = f10;
        this.f280p = interfaceC2782k;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u1(C0.V v10, Q.g1 g1Var, float f10) {
        super(1);
        this.f278m = 3;
        this.o = v10;
        this.f280p = g1Var;
        this.f279n = f10;
    }
}

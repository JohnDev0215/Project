package k3;

import c5.InterfaceC1027d;
import java.util.List;
import n2.C1913m;
import o0.InterfaceC2013i;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class X0 implements h2.g, l1, k1, InterfaceC1027d, h2.l, InterfaceC2013i {
    public final /* synthetic */ int l;

    public /* synthetic */ X0(int i7) {
        this.l = i7;
    }

    @Override // k3.l1
    public Object a(I0 i02, C1679y0 c1679y0, int i7) {
        switch (this.l) {
            case 4:
                i02.getClass();
                throw new ClassCastException();
            case 7:
                i02.r(c1679y0);
                i02.f17798e.getClass();
                return l9.l.T(new C1(-6));
            case 11:
                i02.getClass();
                throw new ClassCastException();
            case 13:
                i02.getClass();
                throw new ClassCastException();
            case 16:
                i02.getClass();
                throw new ClassCastException();
            case 18:
                i02.getClass();
                throw new ClassCastException();
            default:
                i02.r(c1679y0);
                i02.f17798e.getClass();
                return l9.l.T(new C1(-6));
        }
    }

    @Override // h2.g
    public void accept(Object obj) {
        u1 u1Var = (u1) obj;
        switch (this.l) {
            case 0:
                u1Var.w0();
                break;
            case 1:
                u1Var.H0();
                break;
            case 2:
                u1Var.M0();
                break;
            case 3:
                u1Var.W();
                break;
            case 4:
            case 7:
            case 8:
            case 10:
            case 11:
            case 13:
            default:
                u1Var.D();
                break;
            case 5:
                u1Var.E0();
                break;
            case 6:
                u1Var.C0();
                break;
            case 9:
                u1Var.e0();
                break;
            case 12:
                u1Var.stop();
                break;
            case 14:
                u1Var.c();
                break;
            case 15:
                u1Var.Y();
                break;
        }
    }

    @Override // c5.InterfaceC1027d
    public Object apply(Object obj) {
        return new o2.e((h2.v) obj);
    }

    @Override // h2.l
    public void b(Object obj) {
        switch (this.l) {
            case 22:
                ((e2.U) obj).f(new C1913m(2, new A9.k("Player release timed out."), 1003));
                break;
            case 23:
                ((e2.U) obj).l();
                break;
            case 24:
            case 25:
            case 26:
            default:
                ((o2.j) obj).getClass();
                break;
            case 27:
                ((o2.j) obj).getClass();
                break;
            case 28:
                ((o2.j) obj).getClass();
                break;
        }
    }

    @Override // o0.InterfaceC2013i
    public double c(double d) {
        double d10;
        switch (this.l) {
            case 24:
                double dPow = d < 0.0d ? -d : d;
                if (dPow >= 0.0031308049535603718d) {
                    dPow = Math.pow(dPow, 0.4166666666666667d) - 0.05213270142180095d;
                    d10 = 0.9478672985781991d;
                } else {
                    d10 = 0.07739938080495357d;
                }
                return Math.copySign(dPow / d10, d);
            case 25:
                double d11 = d < 0.0d ? -d : d;
                return Math.copySign(d11 >= 0.04045d ? Math.pow((0.9478672985781991d * d11) + 0.05213270142180095d, 2.4d) : d11 * 0.07739938080495357d, d);
            default:
                return d;
        }
    }

    @Override // k3.k1
    public void d(u1 u1Var, C1679y0 c1679y0, List list) {
        switch (this.l) {
            case 8:
                u1Var.r0(list);
                break;
            default:
                u1Var.r0(list);
                break;
        }
    }

    public /* synthetic */ X0(String str, e2.X x8) {
        this.l = 7;
    }
}

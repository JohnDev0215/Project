package C0;

import T.AbstractC0764s;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class e0 extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f894m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ f0 f895n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e0(f0 f0Var, int i7) {
        super(2);
        this.f894m = i7;
        this.f895n = f0Var;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f894m) {
            case 0:
                this.f895n.a().f849m = (AbstractC0764s) obj2;
                break;
            case 1:
                G gA = this.f895n.a();
                ((E0.F) obj).x0(new D(gA, (InterfaceC2785n) obj2, gA.f848A));
                break;
            default:
                E0.F f10 = (E0.F) obj;
                G g7 = f10.f1470I;
                f0 f0Var = this.f895n;
                if (g7 == null) {
                    g7 = new G(f10, f0Var.f897a);
                    f10.f1470I = g7;
                }
                f0Var.f898b = g7;
                f0Var.a().b();
                G gA2 = f0Var.a();
                i0 i0Var = gA2.f850n;
                i0 i0Var2 = f0Var.f897a;
                if (i0Var != i0Var2) {
                    gA2.f850n = i0Var2;
                    gA2.c(false);
                    E0.F.s0(gA2.l, false, 7);
                }
                break;
        }
        return m7.q.f19267a;
    }
}

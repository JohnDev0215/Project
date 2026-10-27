package y;

import E0.AbstractC0156f;
import E0.InterfaceC0166p;

/* JADX INFO: renamed from: y.O, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2694O extends g0.n implements E0.s0, InterfaceC0166p {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final o0 f23857A = new o0(6);

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f23858y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public E0.d0 f23859z;

    public final C2695P H0() {
        if (!this.f14695x) {
            return null;
        }
        E0.s0 s0VarJ = AbstractC0156f.j(this, C2695P.f23860z);
        if (s0VarJ instanceof C2695P) {
            return (C2695P) s0VarJ;
        }
        return null;
    }

    @Override // E0.InterfaceC0166p
    public final void l0(E0.d0 d0Var) {
        C2695P c2695pH0;
        this.f23859z = d0Var;
        if (this.f23858y) {
            if (!d0Var.Q0().f14695x) {
                C2695P c2695pH02 = H0();
                if (c2695pH02 != null) {
                    c2695pH02.H0(null);
                    return;
                }
                return;
            }
            E0.d0 d0Var2 = this.f23859z;
            if (d0Var2 == null || !d0Var2.Q0().f14695x || (c2695pH0 = H0()) == null) {
                return;
            }
            c2695pH0.H0(this.f23859z);
        }
    }

    @Override // E0.s0
    public final Object r() {
        return f23857A;
    }

    @Override // g0.n
    public final boolean w0() {
        return false;
    }
}

package p;

/* JADX INFO: loaded from: classes.dex */
public final class nq2 extends kotlinx.coroutines.b {
    public static final nq2 k = new nq2();

    @Override // kotlinx.coroutines.b
    public final void G0(y10 y10Var, Runnable runnable) {
        s70 s70Var = s70.l;
        s70Var.k.c(runnable, fj2.h, false);
    }

    @Override // kotlinx.coroutines.b
    public final void H0(y10 y10Var, Runnable runnable) {
        s70 s70Var = s70.l;
        s70Var.k.c(runnable, fj2.h, true);
    }

    @Override // kotlinx.coroutines.b
    public final kotlinx.coroutines.b J0(int i) {
        zu.p(i);
        return i >= fj2.d ? this : super.J0(i);
    }
}

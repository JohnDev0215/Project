package p;

/* JADX INFO: loaded from: classes.dex */
public abstract class d72 extends kotlinx.coroutines.f {
    public final d20 k;

    public d72(int i, int i2, long j, String str) {
        this.k = new d20(i, i2, j, str);
    }

    @Override // kotlinx.coroutines.b
    public final void G0(y10 y10Var, Runnable runnable) {
        d20.i(this.k, runnable, false, 6);
    }

    @Override // kotlinx.coroutines.b
    public final void H0(y10 y10Var, Runnable runnable) {
        d20.i(this.k, runnable, true, 2);
    }
}

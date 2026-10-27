package defpackage;

/* JADX INFO: compiled from: chromium-webapk8.dex-default-8 */
/* JADX INFO: loaded from: assets/webapk8.dex */
public abstract class L0 extends W {
    private D e;

    public L0(int i, int i2, long j) {
        this.e = new D(i, i2, j, "DefaultDispatcher");
    }

    public final void c(AbstractC0208s abstractC0208s, Runnable runnable) {
        D d = this.e;
        T0 t0 = D.m;
        d.b(runnable, a1.f, false);
    }

    public final void e(Runnable runnable, X0 x0) {
        this.e.b(runnable, x0, false);
    }
}

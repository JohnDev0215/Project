package h5;

/* JADX INFO: renamed from: h5.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC1380f implements Runnable {
    public final C1373E l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final y f15549m;

    public RunnableC1380f(C1373E c1373e, y yVar) {
        this.l = c1373e;
        this.f15549m = yVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.l.l != this) {
            return;
        }
        if (p.f15557q.F(this.l, this, p.h(this.f15549m))) {
            p.e(this.l, false);
        }
    }
}

package p;

/* JADX INFO: loaded from: classes.dex */
public final class e10 {
    public final u81 a;
    public final Object b = new Object();
    public y00 c;

    public e10(u81 u81Var) {
        this.a = u81Var;
    }

    public final void a() {
        synchronized (this.b) {
            try {
                if (this.c == null) {
                    lc.c("destroy() called without create()");
                }
                y00 y00Var = this.c;
                if (y00Var != null) {
                    ((d10) y00Var).b();
                }
                this.c = null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

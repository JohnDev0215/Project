package spi;

/* JADX INFO: compiled from: PendingPostQueue.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h8 {
    public g8 a;
    public g8 b;

    public final synchronized void a(g8 g8Var) {
        g8 g8Var2 = this.b;
        if (g8Var2 != null) {
            g8Var2.c = g8Var;
            this.b = g8Var;
        } else {
            if (this.a != null) {
                throw new IllegalStateException("Head present, but no tail");
            }
            this.b = g8Var;
            this.a = g8Var;
        }
        notifyAll();
    }

    public final synchronized g8 b() {
        g8 g8Var;
        g8Var = this.a;
        if (g8Var != null) {
            g8 g8Var2 = g8Var.c;
            this.a = g8Var2;
            if (g8Var2 == null) {
                this.b = null;
            }
        }
        return g8Var;
    }
}

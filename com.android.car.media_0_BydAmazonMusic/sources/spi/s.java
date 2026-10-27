package spi;

/* JADX INFO: compiled from: AsyncPoster.java */
/* JADX INFO: loaded from: classes2.dex */
public final class s implements Runnable {
    public final h8 a = new h8();
    public final o3 b;

    public s(o3 o3Var) {
        this.b = o3Var;
    }

    public final void a(pa paVar, Object obj) {
        this.a.a(g8.a(paVar, obj));
        this.b.j.execute(this);
    }

    @Override // java.lang.Runnable
    public final void run() {
        g8 g8VarB = this.a.b();
        if (g8VarB == null) {
            throw new IllegalStateException("No pending post available");
        }
        this.b.c(g8VarB);
    }
}

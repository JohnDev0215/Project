package spi;

import java.util.logging.Level;

/* JADX INFO: compiled from: BackgroundPoster.java */
/* JADX INFO: loaded from: classes2.dex */
public final class v implements Runnable {
    public final h8 a = new h8();
    public final o3 b;
    public volatile boolean c;

    public v(o3 o3Var) {
        this.b = o3Var;
    }

    public final void a(pa paVar, Object obj) {
        g8 g8VarA = g8.a(paVar, obj);
        synchronized (this) {
            this.a.a(g8VarA);
            if (!this.c) {
                this.c = true;
                this.b.j.execute(this);
            }
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        g8 g8VarB;
        while (true) {
            try {
                h8 h8Var = this.a;
                synchronized (h8Var) {
                    if (h8Var.a == null) {
                        h8Var.wait(1000);
                    }
                    g8VarB = h8Var.b();
                }
                if (g8VarB == null) {
                    synchronized (this) {
                        g8VarB = this.a.b();
                        if (g8VarB == null) {
                            return;
                        }
                    }
                }
                this.b.c(g8VarB);
            } catch (InterruptedException e) {
                this.b.p.b(Level.WARNING, Thread.currentThread().getName() + " was interruppted", e);
                return;
            } finally {
                this.c = false;
            }
        }
    }
}

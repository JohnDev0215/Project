package defpackage;

import java.util.logging.Level;

/* JADX INFO: compiled from: BackgroundPoster.java */
/* JADX INFO: loaded from: classes.dex */
public final class am implements Runnable, jm {
    public final im b = new im();
    public final bm c;
    public volatile boolean d;

    public am(bm bmVar) {
        this.c = bmVar;
    }

    @Override // defpackage.jm
    public void a(om omVar, Object obj) {
        hm hmVarA = hm.a(omVar, obj);
        synchronized (this) {
            this.b.a(hmVarA);
            if (!this.d) {
                this.d = true;
                this.c.d().execute(this);
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        hm hmVarC;
        while (true) {
            try {
                hmVarC = this.b.c(1000);
            } catch (InterruptedException e) {
                this.c.e().b(Level.WARNING, Thread.currentThread().getName() + " was interruppted", e);
                return;
            } finally {
                this.d = false;
            }
            if (hmVarC == null) {
                synchronized (this) {
                    hmVarC = this.b.b();
                    if (hmVarC == null) {
                        return;
                    }
                    this.d = false;
                }
            }
            this.c.g(hmVarC);
        }
    }
}

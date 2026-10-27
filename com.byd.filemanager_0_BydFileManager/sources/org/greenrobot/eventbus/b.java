package org.greenrobot.eventbus;

import java.util.logging.Level;

/* JADX INFO: compiled from: BackgroundPoster.java */
/* JADX INFO: loaded from: classes.dex */
final class b implements Runnable, k {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final j f4672b = new j();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c f4673c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private volatile boolean f4674d;

    b(c cVar) {
        this.f4673c = cVar;
    }

    @Override // org.greenrobot.eventbus.k
    public void a(p pVar, Object obj) {
        i iVarA = i.a(pVar, obj);
        synchronized (this) {
            this.f4672b.a(iVarA);
            if (!this.f4674d) {
                this.f4674d = true;
                this.f4673c.d().execute(this);
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        i iVarC;
        while (true) {
            try {
                iVarC = this.f4672b.c(1000);
            } catch (InterruptedException e2) {
                this.f4673c.e().b(Level.WARNING, Thread.currentThread().getName() + " was interruppted", e2);
                return;
            } finally {
                this.f4674d = false;
            }
            if (iVarC == null) {
                synchronized (this) {
                    iVarC = this.f4672b.b();
                    if (iVarC == null) {
                        return;
                    }
                    this.f4674d = false;
                }
            }
            this.f4673c.g(iVarC);
        }
    }
}

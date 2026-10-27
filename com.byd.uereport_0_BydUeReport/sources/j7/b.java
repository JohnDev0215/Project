package j7;

import java.util.logging.Level;

/* JADX INFO: compiled from: BackgroundPoster.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b implements Runnable, k {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final j f5604d = new j();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final c f5605e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile boolean f5606f;

    public b(c cVar) {
        this.f5605e = cVar;
    }

    @Override // j7.k
    public void a(p pVar, Object obj) {
        i iVarA = i.a(pVar, obj);
        synchronized (this) {
            this.f5604d.a(iVarA);
            if (!this.f5606f) {
                this.f5606f = true;
                this.f5605e.d().execute(this);
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        i iVarC;
        while (true) {
            try {
                iVarC = this.f5604d.c(1000);
            } catch (InterruptedException e8) {
                this.f5605e.e().b(Level.WARNING, Thread.currentThread().getName() + " was interruppted", e8);
                return;
            } finally {
                this.f5606f = false;
            }
            if (iVarC == null) {
                synchronized (this) {
                    iVarC = this.f5604d.b();
                    if (iVarC == null) {
                        return;
                    }
                    this.f5606f = false;
                }
            }
            this.f5605e.g(iVarC);
        }
    }
}

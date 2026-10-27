package f.a.a;

import java.util.logging.Level;

/* JADX INFO: loaded from: classes.dex */
public final class b implements Runnable, l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k f2536a = new k();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f2537b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile boolean f2538c;

    public b(c cVar) {
        this.f2537b = cVar;
    }

    @Override // f.a.a.l
    public void a(q qVar, Object obj) {
        j jVarA = j.a(qVar, obj);
        synchronized (this) {
            this.f2536a.a(jVarA);
            if (!this.f2538c) {
                this.f2538c = true;
                this.f2537b.j.execute(this);
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        j jVarB;
        while (true) {
            try {
                k kVar = this.f2536a;
                synchronized (kVar) {
                    if (kVar.f2566a == null) {
                        kVar.wait(1000);
                    }
                    jVarB = kVar.b();
                }
                if (jVarB == null) {
                    synchronized (this) {
                        jVarB = this.f2536a.b();
                        if (jVarB == null) {
                            return;
                        }
                    }
                }
                this.f2537b.c(jVarB);
            } catch (InterruptedException e2) {
                this.f2537b.r.b(Level.WARNING, Thread.currentThread().getName() + " was interruppted", e2);
                return;
            } finally {
                this.f2538c = false;
            }
        }
    }
}

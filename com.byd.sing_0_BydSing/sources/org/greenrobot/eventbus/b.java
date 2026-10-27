package org.greenrobot.eventbus;

import java.util.logging.Level;

/* JADX INFO: compiled from: BackgroundPoster.java */
/* JADX INFO: loaded from: classes.dex */
final class b implements Runnable, k {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final j f4124b = new j();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c f4125c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private volatile boolean f4126d;

    b(c cVar) {
        this.f4125c = cVar;
    }

    @Override // org.greenrobot.eventbus.k
    public void a(p pVar, Object obj) {
        i iVarA = i.a(pVar, obj);
        synchronized (this) {
            this.f4124b.a(iVarA);
            if (!this.f4126d) {
                this.f4126d = true;
                this.f4125c.d().execute(this);
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        i iVarC;
        while (true) {
            try {
                iVarC = this.f4124b.c(1000);
            } catch (InterruptedException e) {
                this.f4125c.e().b(Level.WARNING, Thread.currentThread().getName() + " was interruppted", e);
                return;
            } finally {
                this.f4126d = false;
            }
            if (iVarC == null) {
                synchronized (this) {
                    iVarC = this.f4124b.b();
                    if (iVarC == null) {
                        return;
                    }
                    this.f4126d = false;
                }
            }
            this.f4125c.g(iVarC);
        }
    }
}

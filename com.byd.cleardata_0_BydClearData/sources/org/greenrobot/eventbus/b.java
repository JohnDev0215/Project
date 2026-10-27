package org.greenrobot.eventbus;

import java.util.logging.Level;

/* JADX INFO: loaded from: classes.dex */
final class b implements Runnable, l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final k f3287b = new k();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c f3288c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private volatile boolean f3289d;

    b(c cVar) {
        this.f3288c = cVar;
    }

    @Override // org.greenrobot.eventbus.l
    public void a(q qVar, Object obj) {
        j jVarA = j.a(qVar, obj);
        synchronized (this) {
            this.f3287b.a(jVarA);
            if (!this.f3289d) {
                this.f3289d = true;
                this.f3288c.d().execute(this);
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        j jVarC;
        while (true) {
            try {
                jVarC = this.f3287b.c(1000);
            } catch (InterruptedException e) {
                this.f3288c.e().b(Level.WARNING, Thread.currentThread().getName() + " was interruppted", e);
                return;
            } finally {
                this.f3289d = false;
            }
            if (jVarC == null) {
                synchronized (this) {
                    jVarC = this.f3287b.b();
                    if (jVarC == null) {
                        return;
                    }
                    this.f3289d = false;
                }
            }
            this.f3288c.g(jVarC);
        }
    }
}

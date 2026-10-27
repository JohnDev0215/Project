package kotlinx.coroutines.scheduling;

import kotlinx.coroutines.m0;

/* JADX INFO: compiled from: Tasks.kt */
/* JADX INFO: loaded from: classes.dex */
public final class k extends i {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Runnable f7780h;

    public k(Runnable runnable, long j7, j jVar) {
        super(j7, jVar);
        this.f7780h = runnable;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.f7780h.run();
        } finally {
            this.f7779g.Z();
        }
    }

    public String toString() {
        return "Task[" + m0.a(this.f7780h) + '@' + m0.b(this.f7780h) + ", " + this.f7778e + ", " + this.f7779g + ']';
    }
}

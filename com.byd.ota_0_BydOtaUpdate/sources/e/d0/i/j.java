package e.d0.i;

import java.util.concurrent.CountDownLatch;

/* JADX INFO: compiled from: Ping.java */
/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CountDownLatch f3516a = new CountDownLatch(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f3517b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f3518c = -1;

    public void a() {
        if (this.f3518c == -1) {
            long j = this.f3517b;
            if (j != -1) {
                this.f3518c = j - 1;
                this.f3516a.countDown();
                return;
            }
        }
        throw new IllegalStateException();
    }

    public void b() {
        if (this.f3518c != -1 || this.f3517b == -1) {
            throw new IllegalStateException();
        }
        this.f3518c = System.nanoTime();
        this.f3516a.countDown();
    }

    public void c() {
        if (this.f3517b != -1) {
            throw new IllegalStateException();
        }
        this.f3517b = System.nanoTime();
    }
}

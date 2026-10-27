package f.b0.i;

import java.util.concurrent.CountDownLatch;

/* JADX INFO: compiled from: Ping.java */
/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CountDownLatch f5254a = new CountDownLatch(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f5255b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f5256c = -1;

    public void a() {
        if (this.f5256c == -1) {
            long j = this.f5255b;
            if (j != -1) {
                this.f5256c = j - 1;
                this.f5254a.countDown();
                return;
            }
        }
        throw new IllegalStateException();
    }

    public void b() {
        if (this.f5256c != -1 || this.f5255b == -1) {
            throw new IllegalStateException();
        }
        this.f5256c = System.nanoTime();
        this.f5254a.countDown();
    }

    public void c() {
        if (this.f5255b != -1) {
            throw new IllegalStateException();
        }
        this.f5255b = System.nanoTime();
    }
}

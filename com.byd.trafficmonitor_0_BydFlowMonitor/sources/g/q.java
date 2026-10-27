package g;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: Timeout.java */
/* JADX INFO: loaded from: classes.dex */
public class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final q f5505a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f5506b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f5507c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f5508d;

    /* JADX INFO: compiled from: Timeout.java */
    public class a extends q {
        @Override // g.q
        public q d(long j) {
            return this;
        }

        @Override // g.q
        public void f() throws IOException {
        }

        @Override // g.q
        public q g(long j, TimeUnit timeUnit) {
            return this;
        }
    }

    public q a() {
        this.f5506b = false;
        return this;
    }

    public q b() {
        this.f5508d = 0L;
        return this;
    }

    public long c() {
        if (this.f5506b) {
            return this.f5507c;
        }
        throw new IllegalStateException("No deadline");
    }

    public q d(long j) {
        this.f5506b = true;
        this.f5507c = j;
        return this;
    }

    public boolean e() {
        return this.f5506b;
    }

    public void f() throws IOException {
        if (Thread.interrupted()) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException("interrupted");
        }
        if (this.f5506b && this.f5507c - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }

    public q g(long j, TimeUnit timeUnit) {
        if (j >= 0) {
            if (timeUnit == null) {
                throw new IllegalArgumentException("unit == null");
            }
            this.f5508d = timeUnit.toNanos(j);
            return this;
        }
        throw new IllegalArgumentException("timeout < 0: " + j);
    }

    public long h() {
        return this.f5508d;
    }
}

package okio;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class Timeout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Timeout f5853a = new Timeout() { // from class: okio.Timeout.1
        @Override // okio.Timeout
        public Timeout d(long j) {
            return this;
        }

        @Override // okio.Timeout
        public void g() {
        }

        @Override // okio.Timeout
        public Timeout h(long j, TimeUnit timeUnit) {
            return this;
        }
    };

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f5854b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private long f5855c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private long f5856d;

    static long f(long j, long j2) {
        return j == 0 ? j2 : (j2 != 0 && j >= j2) ? j2 : j;
    }

    public Timeout a() {
        this.f5854b = false;
        return this;
    }

    public Timeout b() {
        this.f5856d = 0L;
        return this;
    }

    public long c() {
        if (this.f5854b) {
            return this.f5855c;
        }
        throw new IllegalStateException("No deadline");
    }

    public Timeout d(long j) {
        this.f5854b = true;
        this.f5855c = j;
        return this;
    }

    public boolean e() {
        return this.f5854b;
    }

    public void g() throws InterruptedIOException {
        if (Thread.interrupted()) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException("interrupted");
        }
        if (this.f5854b && this.f5855c - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }

    public Timeout h(long j, TimeUnit timeUnit) {
        if (j >= 0) {
            if (timeUnit == null) {
                throw new IllegalArgumentException("unit == null");
            }
            this.f5856d = timeUnit.toNanos(j);
            return this;
        }
        throw new IllegalArgumentException("timeout < 0: " + j);
    }

    public long i() {
        return this.f5856d;
    }

    public final void j(Object obj) throws InterruptedIOException {
        try {
            boolean zE = e();
            long jI = i();
            long jNanoTime = 0;
            if (!zE && jI == 0) {
                obj.wait();
                return;
            }
            long jNanoTime2 = System.nanoTime();
            if (zE && jI != 0) {
                jI = Math.min(jI, c() - jNanoTime2);
            } else if (zE) {
                jI = c() - jNanoTime2;
            }
            if (jI > 0) {
                long j = jI / 1000000;
                obj.wait(j, (int) (jI - (1000000 * j)));
                jNanoTime = System.nanoTime() - jNanoTime2;
            }
            if (jNanoTime >= jI) {
                throw new InterruptedIOException("timeout");
            }
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException("interrupted");
        }
    }
}

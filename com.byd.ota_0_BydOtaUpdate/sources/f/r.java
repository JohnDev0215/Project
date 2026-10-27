package f;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: Timeout.java */
/* JADX INFO: loaded from: classes.dex */
public class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final r f3730a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f3731b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f3732c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f3733d;

    /* JADX INFO: compiled from: Timeout.java */
    public class a extends r {
        @Override // f.r
        public r d(long j) {
            return this;
        }

        @Override // f.r
        public void f() throws IOException {
        }

        @Override // f.r
        public r g(long j, TimeUnit timeUnit) {
            return this;
        }
    }

    public r a() {
        this.f3731b = false;
        return this;
    }

    public r b() {
        this.f3733d = 0L;
        return this;
    }

    public long c() {
        if (this.f3731b) {
            return this.f3732c;
        }
        throw new IllegalStateException("No deadline");
    }

    public r d(long j) {
        this.f3731b = true;
        this.f3732c = j;
        return this;
    }

    public boolean e() {
        return this.f3731b;
    }

    public void f() throws IOException {
        if (Thread.interrupted()) {
            throw new InterruptedIOException("thread interrupted");
        }
        if (this.f3731b && this.f3732c - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }

    public r g(long j, TimeUnit timeUnit) {
        if (j >= 0) {
            if (timeUnit == null) {
                throw new IllegalArgumentException("unit == null");
            }
            this.f3733d = timeUnit.toNanos(j);
            return this;
        }
        throw new IllegalArgumentException("timeout < 0: " + j);
    }

    public long h() {
        return this.f3733d;
    }
}

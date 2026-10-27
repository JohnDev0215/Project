package r9;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class L {
    public static final K d = new K();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f21909a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f21910b;
    public long c;

    public L a() {
        this.f21909a = false;
        return this;
    }

    public L b() {
        this.c = 0L;
        return this;
    }

    public long c() {
        if (this.f21909a) {
            return this.f21910b;
        }
        throw new IllegalStateException("No deadline");
    }

    public L d(long j3) {
        this.f21909a = true;
        this.f21910b = j3;
        return this;
    }

    public boolean e() {
        return this.f21909a;
    }

    public void f() throws InterruptedIOException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedIOException("interrupted");
        }
        if (this.f21909a && this.f21910b - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }

    public L g(long j3, TimeUnit timeUnit) {
        A7.m.f(timeUnit, "unit");
        if (j3 < 0) {
            throw new IllegalArgumentException(M5.b.f("timeout < 0: ", j3).toString());
        }
        this.c = timeUnit.toNanos(j3);
        return this;
    }

    public long h() {
        return this.c;
    }
}

package p;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class tl2 {
    public static final sl2 d = new sl2();
    public boolean a;
    public long b;
    public long c;

    public tl2 a() {
        this.a = false;
        return this;
    }

    public tl2 b() {
        this.c = 0L;
        return this;
    }

    public long c() {
        if (this.a) {
            return this.b;
        }
        throw new IllegalStateException("No deadline".toString());
    }

    public tl2 d(long j) {
        this.a = true;
        this.b = j;
        return this;
    }

    public boolean e() {
        return this.a;
    }

    public void f() throws InterruptedIOException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedIOException("interrupted");
        }
        if (this.a && this.b - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }

    public tl2 g(long j, TimeUnit timeUnit) {
        v03.j(timeUnit, "unit");
        if (j < 0) {
            throw new IllegalArgumentException(u4.g("timeout < 0: ", j).toString());
        }
        this.c = timeUnit.toNanos(j);
        return this;
    }

    public long h() {
        return this.c;
    }
}

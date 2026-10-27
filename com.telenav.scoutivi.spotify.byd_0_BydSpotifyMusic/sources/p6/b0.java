package p6;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: Timeout.kt */
/* JADX INFO: loaded from: classes.dex */
public class b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f8852a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f8853b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private long f8854c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b f8851e = new b(null);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b0 f8850d = new a();

    /* JADX INFO: compiled from: Timeout.kt */
    public static final class a extends b0 {
        a() {
        }

        @Override // p6.b0
        public b0 d(long j7) {
            return this;
        }

        @Override // p6.b0
        public void f() {
        }

        @Override // p6.b0
        public b0 g(long j7, TimeUnit unit) {
            kotlin.jvm.internal.l.e(unit, "unit");
            return this;
        }
    }

    /* JADX INFO: compiled from: Timeout.kt */
    public static final class b {
        private b() {
        }

        public /* synthetic */ b(kotlin.jvm.internal.g gVar) {
            this();
        }
    }

    public b0 a() {
        this.f8852a = false;
        return this;
    }

    public b0 b() {
        this.f8854c = 0L;
        return this;
    }

    public long c() {
        if (this.f8852a) {
            return this.f8853b;
        }
        throw new IllegalStateException("No deadline".toString());
    }

    public b0 d(long j7) {
        this.f8852a = true;
        this.f8853b = j7;
        return this;
    }

    public boolean e() {
        return this.f8852a;
    }

    public void f() throws InterruptedIOException {
        if (Thread.interrupted()) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException("interrupted");
        }
        if (this.f8852a && this.f8853b - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }

    public b0 g(long j7, TimeUnit unit) {
        kotlin.jvm.internal.l.e(unit, "unit");
        if (j7 >= 0) {
            this.f8854c = unit.toNanos(j7);
            return this;
        }
        throw new IllegalArgumentException(("timeout < 0: " + j7).toString());
    }

    public long h() {
        return this.f8854c;
    }
}

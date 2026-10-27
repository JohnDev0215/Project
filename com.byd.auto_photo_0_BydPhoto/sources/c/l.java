package c;

import java.io.InterruptedIOException;

/* JADX INFO: loaded from: classes2.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final l f644a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f645b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private long f646c;

    final class a extends l {
        a() {
        }

        @Override // c.l
        public void a() {
        }
    }

    public void a() throws InterruptedIOException {
        if (Thread.interrupted()) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException("interrupted");
        }
        if (this.f645b && this.f646c - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }
}

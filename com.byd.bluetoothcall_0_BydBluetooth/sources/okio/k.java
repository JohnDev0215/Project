package okio;

import java.io.IOException;
import java.io.InterruptedIOException;

/* JADX INFO: compiled from: Timeout.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final k f4388a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f4389b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private long f4390c;

    /* JADX INFO: compiled from: Timeout.java */
    final class a extends k {
        a() {
        }

        @Override // okio.k
        public void a() throws IOException {
        }
    }

    public void a() throws IOException {
        if (Thread.interrupted()) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException("interrupted");
        }
        if (this.f4389b && this.f4390c - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }
}

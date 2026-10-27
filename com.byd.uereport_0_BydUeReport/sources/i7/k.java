package i7;

import java.io.IOException;
import java.io.InterruptedIOException;

/* JADX INFO: compiled from: Timeout.java */
/* JADX INFO: loaded from: classes2.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final k f5531a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f5532b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f5533c;

    /* JADX INFO: compiled from: Timeout.java */
    public class a extends k {
        @Override // i7.k
        public void a() throws IOException {
        }
    }

    public void a() throws IOException {
        if (Thread.interrupted()) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException("interrupted");
        }
        if (this.f5532b && this.f5533c - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }
}

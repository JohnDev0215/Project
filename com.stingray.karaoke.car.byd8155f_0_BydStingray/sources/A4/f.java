package A4;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import n4.w;

/* JADX INFO: loaded from: classes.dex */
public final class f extends InputStream {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final ArrayDeque f417n;
    public w l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public IOException f418m;

    static {
        char[] cArr = q.f432a;
        f417n = new ArrayDeque(0);
    }

    @Override // java.io.InputStream
    public final int available() {
        return this.l.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.l.close();
    }

    @Override // java.io.InputStream
    public final void mark(int i7) {
        this.l.mark(i7);
    }

    @Override // java.io.InputStream
    public final boolean markSupported() {
        this.l.getClass();
        return true;
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        try {
            return this.l.read();
        } catch (IOException e10) {
            this.f418m = e10;
            throw e10;
        }
    }

    @Override // java.io.InputStream
    public final synchronized void reset() {
        this.l.reset();
    }

    @Override // java.io.InputStream
    public final long skip(long j3) throws IOException {
        try {
            return this.l.skip(j3);
        } catch (IOException e10) {
            this.f418m = e10;
            throw e10;
        }
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr) throws IOException {
        try {
            return this.l.read(bArr);
        } catch (IOException e10) {
            this.f418m = e10;
            throw e10;
        }
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i7, int i10) throws IOException {
        try {
            return this.l.read(bArr, i7, i10);
        } catch (IOException e10) {
            this.f418m = e10;
            throw e10;
        }
    }
}

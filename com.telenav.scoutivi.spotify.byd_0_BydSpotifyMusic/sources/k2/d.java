package k2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;

/* JADX INFO: compiled from: ExceptionCatchingInputStream.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends InputStream {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final Queue<d> f7243h = k.e(0);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private InputStream f7244e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private IOException f7245g;

    d() {
    }

    public static d z(InputStream inputStream) {
        d dVarPoll;
        Queue<d> queue = f7243h;
        synchronized (queue) {
            dVarPoll = queue.poll();
        }
        if (dVarPoll == null) {
            dVarPoll = new d();
        }
        dVarPoll.G(inputStream);
        return dVarPoll;
    }

    public void A() {
        this.f7245g = null;
        this.f7244e = null;
        Queue<d> queue = f7243h;
        synchronized (queue) {
            queue.offer(this);
        }
    }

    void G(InputStream inputStream) {
        this.f7244e = inputStream;
    }

    @Override // java.io.InputStream
    public int available() {
        return this.f7244e.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f7244e.close();
    }

    public IOException m() {
        return this.f7245g;
    }

    @Override // java.io.InputStream
    public void mark(int i7) {
        this.f7244e.mark(i7);
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.f7244e.markSupported();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        try {
            return this.f7244e.read(bArr);
        } catch (IOException e7) {
            this.f7245g = e7;
            return -1;
        }
    }

    @Override // java.io.InputStream
    public synchronized void reset() {
        this.f7244e.reset();
    }

    @Override // java.io.InputStream
    public long skip(long j7) {
        try {
            return this.f7244e.skip(j7);
        } catch (IOException e7) {
            this.f7245g = e7;
            return 0L;
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i7, int i8) {
        try {
            return this.f7244e.read(bArr, i7, i8);
        } catch (IOException e7) {
            this.f7245g = e7;
            return -1;
        }
    }

    @Override // java.io.InputStream
    public int read() {
        try {
            return this.f7244e.read();
        } catch (IOException e7) {
            this.f7245g = e7;
            return -1;
        }
    }
}

package com.bumptech.glide.t;

import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;

/* JADX INFO: loaded from: classes.dex */
public class d extends InputStream {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Queue<d> f2539b = k.e(0);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private InputStream f2540c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private IOException f2541d;

    d() {
    }

    public static d b(InputStream inputStream) {
        d dVarPoll;
        Queue<d> queue = f2539b;
        synchronized (queue) {
            dVarPoll = queue.poll();
        }
        if (dVarPoll == null) {
            dVarPoll = new d();
        }
        dVarPoll.d(inputStream);
        return dVarPoll;
    }

    public IOException a() {
        return this.f2541d;
    }

    @Override // java.io.InputStream
    public int available() {
        return this.f2540c.available();
    }

    public void c() {
        this.f2541d = null;
        this.f2540c = null;
        Queue<d> queue = f2539b;
        synchronized (queue) {
            queue.offer(this);
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f2540c.close();
    }

    void d(InputStream inputStream) {
        this.f2540c = inputStream;
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        this.f2540c.mark(i);
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.f2540c.markSupported();
    }

    @Override // java.io.InputStream
    public int read() {
        try {
            return this.f2540c.read();
        } catch (IOException e) {
            this.f2541d = e;
            return -1;
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        try {
            return this.f2540c.read(bArr);
        } catch (IOException e) {
            this.f2541d = e;
            return -1;
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        try {
            return this.f2540c.read(bArr, i, i2);
        } catch (IOException e) {
            this.f2541d = e;
            return -1;
        }
    }

    @Override // java.io.InputStream
    public synchronized void reset() {
        this.f2540c.reset();
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        try {
            return this.f2540c.skip(j);
        } catch (IOException e) {
            this.f2541d = e;
            return 0L;
        }
    }
}

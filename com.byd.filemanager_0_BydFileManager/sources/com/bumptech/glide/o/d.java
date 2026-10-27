package com.bumptech.glide.o;

import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;

/* JADX INFO: compiled from: ExceptionCatchingInputStream.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends InputStream {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Queue<d> f3453b = k.e(0);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private InputStream f3454c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private IOException f3455d;

    d() {
    }

    public static d k(InputStream inputStream) {
        d dVarPoll;
        Queue<d> queue = f3453b;
        synchronized (queue) {
            dVarPoll = queue.poll();
        }
        if (dVarPoll == null) {
            dVarPoll = new d();
        }
        dVarPoll.m(inputStream);
        return dVarPoll;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.f3454c.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f3454c.close();
    }

    public IOException j() {
        return this.f3455d;
    }

    public void l() {
        this.f3455d = null;
        this.f3454c = null;
        Queue<d> queue = f3453b;
        synchronized (queue) {
            queue.offer(this);
        }
    }

    void m(InputStream inputStream) {
        this.f3454c = inputStream;
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        this.f3454c.mark(i);
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.f3454c.markSupported();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        try {
            return this.f3454c.read(bArr);
        } catch (IOException e2) {
            this.f3455d = e2;
            return -1;
        }
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        this.f3454c.reset();
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        try {
            return this.f3454c.skip(j);
        } catch (IOException e2) {
            this.f3455d = e2;
            return 0L;
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        try {
            return this.f3454c.read(bArr, i, i2);
        } catch (IOException e2) {
            this.f3455d = e2;
            return -1;
        }
    }

    @Override // java.io.InputStream
    public int read() {
        try {
            return this.f3454c.read();
        } catch (IOException e2) {
            this.f3455d = e2;
            return -1;
        }
    }
}

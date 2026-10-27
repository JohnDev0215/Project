package com.bumptech.glide.m;

import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;

/* JADX INFO: compiled from: ExceptionCatchingInputStream.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends InputStream {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Queue<d> f3172b = k.e(0);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private InputStream f3173c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private IOException f3174d;

    d() {
    }

    public static d b(InputStream inputStream) {
        d dVarPoll;
        Queue<d> queue = f3172b;
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
        return this.f3174d;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.f3173c.available();
    }

    public void c() {
        this.f3174d = null;
        this.f3173c = null;
        Queue<d> queue = f3172b;
        synchronized (queue) {
            queue.offer(this);
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f3173c.close();
    }

    void d(InputStream inputStream) {
        this.f3173c = inputStream;
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        this.f3173c.mark(i);
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.f3173c.markSupported();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        try {
            return this.f3173c.read(bArr);
        } catch (IOException e) {
            this.f3174d = e;
            return -1;
        }
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        this.f3173c.reset();
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        try {
            return this.f3173c.skip(j);
        } catch (IOException e) {
            this.f3174d = e;
            return 0L;
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        try {
            return this.f3173c.read(bArr, i, i2);
        } catch (IOException e) {
            this.f3174d = e;
            return -1;
        }
    }

    @Override // java.io.InputStream
    public int read() {
        try {
            return this.f3173c.read();
        } catch (IOException e) {
            this.f3174d = e;
            return -1;
        }
    }
}

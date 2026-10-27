package com.bumptech.glide.t;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class c extends FilterInputStream {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final long f2537b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f2538c;

    private c(InputStream inputStream, long j) {
        super(inputStream);
        this.f2537b = j;
    }

    private int a(int i) throws IOException {
        if (i >= 0) {
            this.f2538c += i;
        } else if (this.f2537b - ((long) this.f2538c) > 0) {
            throw new IOException("Failed to read all expected data, expected: " + this.f2537b + ", but read: " + this.f2538c);
        }
        return i;
    }

    public static InputStream b(InputStream inputStream, long j) {
        return new c(inputStream, j);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int available() {
        return (int) Math.max(this.f2537b - ((long) this.f2538c), ((FilterInputStream) this).in.available());
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read() {
        int i;
        i = super.read();
        a(i >= 0 ? 1 : -1);
        return i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read(byte[] bArr, int i, int i2) {
        return a(super.read(bArr, i, i2));
    }
}

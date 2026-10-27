package com.bumptech.glide.o;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: ContentLengthInputStream.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends FilterInputStream {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final long f3451b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3452c;

    private c(InputStream inputStream, long j) {
        super(inputStream);
        this.f3451b = j;
    }

    private int j(int i) throws IOException {
        if (i >= 0) {
            this.f3452c += i;
        } else if (this.f3451b - ((long) this.f3452c) > 0) {
            throw new IOException("Failed to read all expected data, expected: " + this.f3451b + ", but read: " + this.f3452c);
        }
        return i;
    }

    public static InputStream k(InputStream inputStream, long j) {
        return new c(inputStream, j);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int available() throws IOException {
        return (int) Math.max(this.f3451b - ((long) this.f3452c), ((FilterInputStream) this).in.available());
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read() throws IOException {
        int i;
        i = super.read();
        j(i >= 0 ? 1 : -1);
        return i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read(byte[] bArr, int i, int i2) throws IOException {
        return j(super.read(bArr, i, i2));
    }
}

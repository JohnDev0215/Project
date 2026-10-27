package com.bumptech.glide.load.p.d;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class s extends FilterInputStream {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private volatile byte[] f2359b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f2360c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f2361d;
    private int e;
    private int f;
    private final com.bumptech.glide.load.n.a0.b g;

    static class a extends IOException {
        a(String str) {
            super(str);
        }
    }

    public s(InputStream inputStream, com.bumptech.glide.load.n.a0.b bVar) {
        this(inputStream, bVar, 65536);
    }

    s(InputStream inputStream, com.bumptech.glide.load.n.a0.b bVar, int i) {
        super(inputStream);
        this.e = -1;
        this.g = bVar;
        this.f2359b = (byte[]) bVar.e(i, byte[].class);
    }

    private int a(InputStream inputStream, byte[] bArr) throws IOException {
        int i = this.e;
        if (i != -1) {
            int i2 = this.f - i;
            int i3 = this.f2361d;
            if (i2 < i3) {
                if (i == 0 && i3 > bArr.length && this.f2360c == bArr.length) {
                    int length = bArr.length * 2;
                    if (length <= i3) {
                        i3 = length;
                    }
                    byte[] bArr2 = (byte[]) this.g.e(i3, byte[].class);
                    System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                    this.f2359b = bArr2;
                    this.g.d(bArr);
                    bArr = bArr2;
                } else if (i > 0) {
                    System.arraycopy(bArr, i, bArr, 0, bArr.length - i);
                }
                int i4 = this.f - this.e;
                this.f = i4;
                this.e = 0;
                this.f2360c = 0;
                int i5 = inputStream.read(bArr, i4, bArr.length - i4);
                int i6 = this.f;
                if (i5 > 0) {
                    i6 += i5;
                }
                this.f2360c = i6;
                return i5;
            }
        }
        int i7 = inputStream.read(bArr);
        if (i7 > 0) {
            this.e = -1;
            this.f = 0;
            this.f2360c = i7;
        }
        return i7;
    }

    private static IOException d() throws IOException {
        throw new IOException("BufferedInputStream is closed");
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int available() {
        InputStream inputStream;
        inputStream = ((FilterInputStream) this).in;
        if (this.f2359b == null || inputStream == null) {
            throw d();
        }
        return (this.f2360c - this.f) + inputStream.available();
    }

    public synchronized void b() {
        this.f2361d = this.f2359b.length;
    }

    public synchronized void c() {
        if (this.f2359b != null) {
            this.g.d(this.f2359b);
            this.f2359b = null;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.f2359b != null) {
            this.g.d(this.f2359b);
            this.f2359b = null;
        }
        InputStream inputStream = ((FilterInputStream) this).in;
        ((FilterInputStream) this).in = null;
        if (inputStream != null) {
            inputStream.close();
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i) {
        this.f2361d = Math.max(this.f2361d, i);
        this.e = this.f;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read() {
        byte[] bArr = this.f2359b;
        InputStream inputStream = ((FilterInputStream) this).in;
        if (bArr == null || inputStream == null) {
            throw d();
        }
        if (this.f >= this.f2360c && a(inputStream, bArr) == -1) {
            return -1;
        }
        if (bArr != this.f2359b && (bArr = this.f2359b) == null) {
            throw d();
        }
        int i = this.f2360c;
        int i2 = this.f;
        if (i - i2 <= 0) {
            return -1;
        }
        this.f = i2 + 1;
        return bArr[i2] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        byte[] bArr2 = this.f2359b;
        if (bArr2 == null) {
            throw d();
        }
        if (i2 == 0) {
            return 0;
        }
        InputStream inputStream = ((FilterInputStream) this).in;
        if (inputStream == null) {
            throw d();
        }
        int i5 = this.f;
        int i6 = this.f2360c;
        if (i5 < i6) {
            int i7 = i6 - i5 >= i2 ? i2 : i6 - i5;
            System.arraycopy(bArr2, i5, bArr, i, i7);
            this.f += i7;
            if (i7 == i2 || inputStream.available() == 0) {
                return i7;
            }
            i += i7;
            i3 = i2 - i7;
        } else {
            i3 = i2;
        }
        while (true) {
            if (this.e == -1 && i3 >= bArr2.length) {
                i4 = inputStream.read(bArr, i, i3);
                if (i4 == -1) {
                    return i3 != i2 ? i2 - i3 : -1;
                }
            } else {
                if (a(inputStream, bArr2) == -1) {
                    return i3 != i2 ? i2 - i3 : -1;
                }
                if (bArr2 != this.f2359b && (bArr2 = this.f2359b) == null) {
                    throw d();
                }
                int i8 = this.f2360c;
                int i9 = this.f;
                i4 = i8 - i9 >= i3 ? i3 : i8 - i9;
                System.arraycopy(bArr2, i9, bArr, i, i4);
                this.f += i4;
            }
            i3 -= i4;
            if (i3 == 0) {
                return i2;
            }
            if (inputStream.available() == 0) {
                return i2 - i3;
            }
            i += i4;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() {
        if (this.f2359b == null) {
            throw new IOException("Stream is closed");
        }
        int i = this.e;
        if (-1 == i) {
            throw new a("Mark has been invalidated, pos: " + this.f + " markLimit: " + this.f2361d);
        }
        this.f = i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized long skip(long j) {
        if (j < 1) {
            return 0L;
        }
        byte[] bArr = this.f2359b;
        if (bArr == null) {
            throw d();
        }
        InputStream inputStream = ((FilterInputStream) this).in;
        if (inputStream == null) {
            throw d();
        }
        int i = this.f2360c;
        int i2 = this.f;
        if (i - i2 >= j) {
            this.f = (int) (((long) i2) + j);
            return j;
        }
        long j2 = ((long) i) - ((long) i2);
        this.f = i;
        if (this.e == -1 || j > this.f2361d) {
            return j2 + inputStream.skip(j - j2);
        }
        if (a(inputStream, bArr) == -1) {
            return j2;
        }
        int i3 = this.f2360c;
        int i4 = this.f;
        if (i3 - i4 >= j - j2) {
            this.f = (int) ((((long) i4) + j) - j2);
            return j;
        }
        long j3 = (j2 + ((long) i3)) - ((long) i4);
        this.f = i3;
        return j3;
    }
}

package n4;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class w extends FilterInputStream {
    public volatile byte[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f19968m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f19969n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f19970p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final F.l f19971q;

    public w(InputStream inputStream, F.l lVar) {
        super(inputStream);
        this.o = -1;
        this.f19971q = lVar;
        this.l = (byte[]) lVar.d(65536, byte[].class);
    }

    public static void i() throws IOException {
        throw new IOException("BufferedInputStream is closed");
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final synchronized int available() {
        InputStream inputStream;
        inputStream = ((FilterInputStream) this).in;
        if (this.l == null || inputStream == null) {
            i();
            throw null;
        }
        return (this.f19968m - this.f19970p) + inputStream.available();
    }

    public final int b(InputStream inputStream, byte[] bArr) throws IOException {
        int i7 = this.o;
        if (i7 != -1) {
            int i10 = this.f19970p - i7;
            int i11 = this.f19969n;
            if (i10 < i11) {
                if (i7 == 0 && i11 > bArr.length && this.f19968m == bArr.length) {
                    int length = bArr.length * 2;
                    if (length <= i11) {
                        i11 = length;
                    }
                    byte[] bArr2 = (byte[]) this.f19971q.d(i11, byte[].class);
                    System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                    this.l = bArr2;
                    this.f19971q.i(bArr);
                    bArr = bArr2;
                } else if (i7 > 0) {
                    System.arraycopy(bArr, i7, bArr, 0, bArr.length - i7);
                }
                int i12 = this.f19970p - this.o;
                this.f19970p = i12;
                this.o = 0;
                this.f19968m = 0;
                int i13 = inputStream.read(bArr, i12, bArr.length - i12);
                int i14 = this.f19970p;
                if (i13 > 0) {
                    i14 += i13;
                }
                this.f19968m = i14;
                return i13;
            }
        }
        int i15 = inputStream.read(bArr);
        if (i15 > 0) {
            this.o = -1;
            this.f19970p = 0;
            this.f19968m = i15;
        }
        return i15;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        if (this.l != null) {
            this.f19971q.i(this.l);
            this.l = null;
        }
        InputStream inputStream = ((FilterInputStream) this).in;
        ((FilterInputStream) this).in = null;
        if (inputStream != null) {
            inputStream.close();
        }
    }

    public final synchronized void g() {
        if (this.l != null) {
            this.f19971q.i(this.l);
            this.l = null;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final synchronized void mark(int i7) {
        this.f19969n = Math.max(this.f19969n, i7);
        this.o = this.f19970p;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final boolean markSupported() {
        return true;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final synchronized int read() {
        byte[] bArr = this.l;
        InputStream inputStream = ((FilterInputStream) this).in;
        if (bArr == null || inputStream == null) {
            i();
            throw null;
        }
        if (this.f19970p >= this.f19968m && b(inputStream, bArr) == -1) {
            return -1;
        }
        if (bArr != this.l && (bArr = this.l) == null) {
            i();
            throw null;
        }
        int i7 = this.f19968m;
        int i10 = this.f19970p;
        if (i7 - i10 <= 0) {
            return -1;
        }
        this.f19970p = i10 + 1;
        return bArr[i10] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final synchronized void reset() {
        if (this.l == null) {
            throw new IOException("Stream is closed");
        }
        int i7 = this.o;
        if (-1 == i7) {
            throw new E5.s("Mark has been invalidated, pos: " + this.f19970p + " markLimit: " + this.f19969n);
        }
        this.f19970p = i7;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final synchronized long skip(long j3) {
        if (j3 < 1) {
            return 0L;
        }
        byte[] bArr = this.l;
        if (bArr == null) {
            i();
            throw null;
        }
        InputStream inputStream = ((FilterInputStream) this).in;
        if (inputStream == null) {
            i();
            throw null;
        }
        int i7 = this.f19968m;
        int i10 = this.f19970p;
        if (i7 - i10 >= j3) {
            this.f19970p = (int) (((long) i10) + j3);
            return j3;
        }
        long j10 = ((long) i7) - ((long) i10);
        this.f19970p = i7;
        if (this.o == -1 || j3 > this.f19969n) {
            long jSkip = inputStream.skip(j3 - j10);
            if (jSkip > 0) {
                this.o = -1;
            }
            return j10 + jSkip;
        }
        if (b(inputStream, bArr) == -1) {
            return j10;
        }
        int i11 = this.f19968m;
        int i12 = this.f19970p;
        if (i11 - i12 >= j3 - j10) {
            this.f19970p = (int) ((((long) i12) + j3) - j10);
            return j3;
        }
        long j11 = (j10 + ((long) i11)) - ((long) i12);
        this.f19970p = i11;
        return j11;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final synchronized int read(byte[] bArr, int i7, int i10) {
        int i11;
        int i12;
        byte[] bArr2 = this.l;
        if (bArr2 == null) {
            i();
            throw null;
        }
        if (i10 == 0) {
            return 0;
        }
        InputStream inputStream = ((FilterInputStream) this).in;
        if (inputStream != null) {
            int i13 = this.f19970p;
            int i14 = this.f19968m;
            if (i13 < i14) {
                int i15 = i14 - i13;
                if (i15 >= i10) {
                    i15 = i10;
                }
                System.arraycopy(bArr2, i13, bArr, i7, i15);
                this.f19970p += i15;
                if (i15 == i10 || inputStream.available() == 0) {
                    return i15;
                }
                i7 += i15;
                i11 = i10 - i15;
            } else {
                i11 = i10;
            }
            while (true) {
                if (this.o == -1 && i11 >= bArr2.length) {
                    i12 = inputStream.read(bArr, i7, i11);
                    if (i12 == -1) {
                        return i11 != i10 ? i10 - i11 : -1;
                    }
                } else {
                    if (b(inputStream, bArr2) == -1) {
                        return i11 != i10 ? i10 - i11 : -1;
                    }
                    if (bArr2 != this.l && (bArr2 = this.l) == null) {
                        i();
                        throw null;
                    }
                    int i16 = this.f19968m;
                    int i17 = this.f19970p;
                    i12 = i16 - i17;
                    if (i12 >= i11) {
                        i12 = i11;
                    }
                    System.arraycopy(bArr2, i17, bArr, i7, i12);
                    this.f19970p += i12;
                }
                i11 -= i12;
                if (i11 == 0) {
                    return i10;
                }
                if (inputStream.available() == 0) {
                    return i10 - i11;
                }
                i7 += i12;
            }
        } else {
            i();
            throw null;
        }
    }
}

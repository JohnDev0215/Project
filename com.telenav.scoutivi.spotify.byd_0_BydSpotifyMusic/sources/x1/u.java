package x1;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: RecyclableBufferedInputStream.java */
/* JADX INFO: loaded from: classes.dex */
public class u extends FilterInputStream {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private volatile byte[] f9802e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f9803g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f9804h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f9805i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private int f9806j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final r1.b f9807k;

    /* JADX INFO: compiled from: RecyclableBufferedInputStream.java */
    static class a extends IOException {
        private static final long serialVersionUID = -4338378848813561757L;

        a(String str) {
            super(str);
        }
    }

    public u(InputStream inputStream, r1.b bVar) {
        this(inputStream, bVar, 65536);
    }

    private static IOException G() throws IOException {
        throw new IOException("BufferedInputStream is closed");
    }

    private int m(InputStream inputStream, byte[] bArr) throws IOException {
        int i7 = this.f9805i;
        if (i7 != -1) {
            int i8 = this.f9806j - i7;
            int i9 = this.f9804h;
            if (i8 < i9) {
                if (i7 == 0 && i9 > bArr.length && this.f9803g == bArr.length) {
                    int length = bArr.length * 2;
                    if (length <= i9) {
                        i9 = length;
                    }
                    byte[] bArr2 = (byte[]) this.f9807k.e(i9, byte[].class);
                    System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                    this.f9802e = bArr2;
                    this.f9807k.d(bArr);
                    bArr = bArr2;
                } else if (i7 > 0) {
                    System.arraycopy(bArr, i7, bArr, 0, bArr.length - i7);
                }
                int i10 = this.f9806j - this.f9805i;
                this.f9806j = i10;
                this.f9805i = 0;
                this.f9803g = 0;
                int i11 = inputStream.read(bArr, i10, bArr.length - i10);
                int i12 = this.f9806j;
                if (i11 > 0) {
                    i12 += i11;
                }
                this.f9803g = i12;
                return i11;
            }
        }
        int i13 = inputStream.read(bArr);
        if (i13 > 0) {
            this.f9805i = -1;
            this.f9806j = 0;
            this.f9803g = i13;
        }
        return i13;
    }

    public synchronized void A() {
        if (this.f9802e != null) {
            this.f9807k.d(this.f9802e);
            this.f9802e = null;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int available() {
        InputStream inputStream;
        inputStream = ((FilterInputStream) this).in;
        if (this.f9802e == null || inputStream == null) {
            throw G();
        }
        return (this.f9803g - this.f9806j) + inputStream.available();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.f9802e != null) {
            this.f9807k.d(this.f9802e);
            this.f9802e = null;
        }
        InputStream inputStream = ((FilterInputStream) this).in;
        ((FilterInputStream) this).in = null;
        if (inputStream != null) {
            inputStream.close();
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i7) {
        this.f9804h = Math.max(this.f9804h, i7);
        this.f9805i = this.f9806j;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read() {
        byte[] bArr = this.f9802e;
        InputStream inputStream = ((FilterInputStream) this).in;
        if (bArr == null || inputStream == null) {
            throw G();
        }
        if (this.f9806j >= this.f9803g && m(inputStream, bArr) == -1) {
            return -1;
        }
        if (bArr != this.f9802e && (bArr = this.f9802e) == null) {
            throw G();
        }
        int i7 = this.f9803g;
        int i8 = this.f9806j;
        if (i7 - i8 <= 0) {
            return -1;
        }
        this.f9806j = i8 + 1;
        return bArr[i8] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() {
        if (this.f9802e == null) {
            throw new IOException("Stream is closed");
        }
        int i7 = this.f9805i;
        if (-1 == i7) {
            throw new a("Mark has been invalidated, pos: " + this.f9806j + " markLimit: " + this.f9804h);
        }
        this.f9806j = i7;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized long skip(long j7) {
        if (j7 < 1) {
            return 0L;
        }
        byte[] bArr = this.f9802e;
        if (bArr == null) {
            throw G();
        }
        InputStream inputStream = ((FilterInputStream) this).in;
        if (inputStream == null) {
            throw G();
        }
        int i7 = this.f9803g;
        int i8 = this.f9806j;
        if (i7 - i8 >= j7) {
            this.f9806j = (int) (((long) i8) + j7);
            return j7;
        }
        long j8 = ((long) i7) - ((long) i8);
        this.f9806j = i7;
        if (this.f9805i == -1 || j7 > this.f9804h) {
            return j8 + inputStream.skip(j7 - j8);
        }
        if (m(inputStream, bArr) == -1) {
            return j8;
        }
        int i9 = this.f9803g;
        int i10 = this.f9806j;
        if (i9 - i10 >= j7 - j8) {
            this.f9806j = (int) ((((long) i10) + j7) - j8);
            return j7;
        }
        long j9 = (j8 + ((long) i9)) - ((long) i10);
        this.f9806j = i9;
        return j9;
    }

    public synchronized void z() {
        this.f9804h = this.f9802e.length;
    }

    u(InputStream inputStream, r1.b bVar, int i7) {
        super(inputStream);
        this.f9805i = -1;
        this.f9807k = bVar;
        this.f9802e = (byte[]) bVar.e(i7, byte[].class);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read(byte[] bArr, int i7, int i8) {
        int i9;
        int i10;
        byte[] bArr2 = this.f9802e;
        if (bArr2 == null) {
            throw G();
        }
        if (i8 == 0) {
            return 0;
        }
        InputStream inputStream = ((FilterInputStream) this).in;
        if (inputStream != null) {
            int i11 = this.f9806j;
            int i12 = this.f9803g;
            if (i11 < i12) {
                int i13 = i12 - i11 >= i8 ? i8 : i12 - i11;
                System.arraycopy(bArr2, i11, bArr, i7, i13);
                this.f9806j += i13;
                if (i13 == i8 || inputStream.available() == 0) {
                    return i13;
                }
                i7 += i13;
                i9 = i8 - i13;
            } else {
                i9 = i8;
            }
            while (true) {
                if (this.f9805i == -1 && i9 >= bArr2.length) {
                    i10 = inputStream.read(bArr, i7, i9);
                    if (i10 == -1) {
                        return i9 != i8 ? i8 - i9 : -1;
                    }
                } else {
                    if (m(inputStream, bArr2) == -1) {
                        return i9 != i8 ? i8 - i9 : -1;
                    }
                    if (bArr2 != this.f9802e && (bArr2 = this.f9802e) == null) {
                        throw G();
                    }
                    int i14 = this.f9803g;
                    int i15 = this.f9806j;
                    i10 = i14 - i15 >= i9 ? i9 : i14 - i15;
                    System.arraycopy(bArr2, i15, bArr, i7, i10);
                    this.f9806j += i10;
                }
                i9 -= i10;
                if (i9 == 0) {
                    return i8;
                }
                if (inputStream.available() == 0) {
                    return i8 - i9;
                }
                i7 += i10;
            }
        } else {
            throw G();
        }
    }
}

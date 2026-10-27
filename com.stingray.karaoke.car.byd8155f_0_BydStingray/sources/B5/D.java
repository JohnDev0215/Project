package B5;

import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final class D implements Closeable {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final Logger f647r = Logger.getLogger(D.class.getName());

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final byte[] f648s = new byte[4096];
    public final RandomAccessFile l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f649m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f650n;
    public B o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public B f651p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final byte[] f652q;

    public D(File file) throws IOException {
        byte[] bArr = new byte[16];
        this.f652q = bArr;
        if (!file.exists()) {
            File file2 = new File(file.getPath() + ".tmp");
            RandomAccessFile randomAccessFile = new RandomAccessFile(file2, "rwd");
            try {
                randomAccessFile.setLength(4096L);
                randomAccessFile.seek(0L);
                byte[] bArr2 = new byte[16];
                z(0, 4096, bArr2);
                randomAccessFile.write(bArr2);
                randomAccessFile.close();
                if (!file2.renameTo(file)) {
                    throw new IOException("Rename failed!");
                }
            } catch (Throwable th) {
                randomAccessFile.close();
                throw th;
            }
        }
        RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rwd");
        this.l = randomAccessFile2;
        randomAccessFile2.seek(0L);
        randomAccessFile2.readFully(bArr);
        this.f649m = k(0, bArr);
        this.f650n = k(4, bArr);
        int iK = k(8, bArr);
        int iK2 = k(12, bArr);
        if (this.f649m > randomAccessFile2.length()) {
            throw new IOException("File is truncated. Expected length: " + this.f649m + ", Actual length: " + randomAccessFile2.length());
        }
        int i7 = this.f649m;
        if (i7 <= 0) {
            throw new IOException(M5.b.h(new StringBuilder("File is corrupt; length stored in header ("), this.f649m, ") is invalid."));
        }
        if (iK < 0 || i7 <= v(iK)) {
            throw new IOException(A7.k.g("File is corrupt; first position stored in header (", iK, ") is invalid."));
        }
        if (iK2 < 0 || this.f649m <= v(iK2)) {
            throw new IOException(A7.k.g("File is corrupt; last position stored in header (", iK2, ") is invalid."));
        }
        this.o = j(iK);
        this.f651p = j(iK2);
    }

    public static int k(int i7, byte[] bArr) {
        return ((bArr[i7] & 255) << 24) + ((bArr[i7 + 1] & 255) << 16) + ((bArr[i7 + 2] & 255) << 8) + (bArr[i7 + 3] & 255);
    }

    public static void z(int i7, int i10, byte[] bArr) {
        bArr[i7] = (byte) (i10 >> 24);
        bArr[i7 + 1] = (byte) (i10 >> 16);
        bArr[i7 + 2] = (byte) (i10 >> 8);
        bArr[i7 + 3] = (byte) i10;
    }

    public final synchronized void b() {
        y(4096, 0, 0, 0);
        this.l.seek(16L);
        this.l.write(f648s, 0, 4080);
        this.f650n = 0;
        B b10 = B.c;
        this.o = b10;
        this.f651p = b10;
        if (this.f649m > 4096) {
            RandomAccessFile randomAccessFile = this.l;
            randomAccessFile.setLength(4096);
            randomAccessFile.getChannel().force(true);
        }
        this.f649m = 4096;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        this.l.close();
    }

    public final void g(int i7) throws IOException {
        int i10;
        int i11 = i7 + 4;
        int i12 = this.f649m;
        if (this.f650n == 0) {
            i10 = 16;
        } else {
            B b10 = this.f651p;
            int i13 = b10.f643a;
            int i14 = this.o.f643a;
            int i15 = b10.f644b;
            i10 = i13 >= i14 ? (i13 - i14) + 4 + i15 + 16 : (((i13 + 4) + i15) + i12) - i14;
        }
        int i16 = i12 - i10;
        if (i16 >= i11) {
            return;
        }
        while (true) {
            i16 += i12;
            int i17 = i12 << 1;
            if (i17 < i12) {
                throw new EOFException(A7.k.g("Cannot grow file beyond ", i12, " bytes"));
            }
            if (i16 >= i11) {
                RandomAccessFile randomAccessFile = this.l;
                randomAccessFile.setLength(i17);
                randomAccessFile.getChannel().force(true);
                B b11 = this.f651p;
                int iV = v(b11.f643a + 4 + b11.f644b);
                if (iV <= this.o.f643a) {
                    FileChannel channel = randomAccessFile.getChannel();
                    channel.position(this.f649m);
                    int i18 = iV - 16;
                    long j3 = i18;
                    if (channel.transferTo(16L, j3, channel) != j3) {
                        throw new AssertionError("Copied insufficient number of bytes!");
                    }
                    int i19 = 16;
                    while (i18 > 0) {
                        byte[] bArr = f648s;
                        int iMin = Math.min(i18, bArr.length);
                        q(i19, iMin, bArr);
                        i18 -= iMin;
                        i19 += iMin;
                    }
                }
                int i20 = this.f651p.f643a;
                int i21 = this.o.f643a;
                if (i20 < i21) {
                    int i22 = (this.f649m + i20) - 16;
                    y(i17, this.f650n, i21, i22);
                    this.f651p = new B(i22, this.f651p.f644b);
                } else {
                    y(i17, this.f650n, i21, i20);
                }
                this.f649m = i17;
                return;
            }
            i12 = i17;
        }
    }

    public final synchronized void i(v vVar) {
        int iV = this.o.f643a;
        for (int i7 = 0; i7 < this.f650n; i7++) {
            B bJ = j(iV);
            if (!vVar.a(new C(this, bJ), bJ.f644b)) {
                return;
            }
            iV = v(bJ.f643a + 4 + bJ.f644b);
        }
    }

    public final B j(int i7) {
        if (i7 == 0) {
            return B.c;
        }
        byte[] bArr = this.f652q;
        o(i7, 0, 4, bArr);
        return new B(i7, k(0, bArr));
    }

    public final synchronized void m(int i7) {
        int i10;
        synchronized (this) {
            i10 = this.f650n;
        }
        if (i10 == 0) {
            throw new NoSuchElementException();
        }
        if (i7 < 0) {
            throw new IllegalArgumentException("Cannot remove negative (" + i7 + ") number of elements.");
        }
        if (i7 == 0) {
            return;
        }
        if (i7 == i10) {
            b();
            return;
        }
        if (i7 > i10) {
            throw new IllegalArgumentException("Cannot remove more elements (" + i7 + ") than present in queue (" + this.f650n + ").");
        }
        B b10 = this.o;
        int i11 = b10.f643a;
        int iK = b10.f644b;
        int iV = i11;
        int i12 = 0;
        for (int i13 = 0; i13 < i7; i13++) {
            i12 += iK + 4;
            iV = v(iV + 4 + iK);
            o(iV, 0, 4, this.f652q);
            iK = k(0, this.f652q);
        }
        y(this.f649m, this.f650n - i7, iV, this.f651p.f643a);
        this.f650n -= i7;
        this.o = new B(iV, iK);
        while (i12 > 0) {
            byte[] bArr = f648s;
            int iMin = Math.min(i12, bArr.length);
            q(i11, iMin, bArr);
            i12 -= iMin;
            i11 += iMin;
        }
    }

    public final void o(int i7, int i10, int i11, byte[] bArr) {
        int iV = v(i7);
        int i12 = iV + i11;
        int i13 = this.f649m;
        RandomAccessFile randomAccessFile = this.l;
        if (i12 <= i13) {
            randomAccessFile.seek(iV);
            randomAccessFile.readFully(bArr, i10, i11);
            return;
        }
        int i14 = i13 - iV;
        randomAccessFile.seek(iV);
        randomAccessFile.readFully(bArr, i10, i14);
        randomAccessFile.seek(16L);
        randomAccessFile.readFully(bArr, i10 + i14, i11 - i14);
    }

    public final void q(int i7, int i10, byte[] bArr) throws IOException {
        int iV = v(i7);
        int i11 = iV + i10;
        int i12 = this.f649m;
        RandomAccessFile randomAccessFile = this.l;
        if (i11 <= i12) {
            randomAccessFile.seek(iV);
            randomAccessFile.write(bArr, 0, i10);
            return;
        }
        int i13 = i12 - iV;
        randomAccessFile.seek(iV);
        randomAccessFile.write(bArr, 0, i13);
        randomAccessFile.seek(16L);
        randomAccessFile.write(bArr, i13, i10 - i13);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(D.class.getSimpleName());
        sb.append("[fileLength=");
        sb.append(this.f649m);
        sb.append(", size=");
        sb.append(this.f650n);
        sb.append(", first=");
        sb.append(this.o);
        sb.append(", last=");
        sb.append(this.f651p);
        sb.append(", element lengths=[");
        try {
            i(new A(sb));
        } catch (IOException e10) {
            f647r.log(Level.WARNING, "read error", (Throwable) e10);
        }
        sb.append("]]");
        return sb.toString();
    }

    public final int v(int i7) {
        int i10 = this.f649m;
        return i7 < i10 ? i7 : (i7 + 16) - i10;
    }

    public final void y(int i7, int i10, int i11, int i12) throws IOException {
        byte[] bArr = this.f652q;
        z(0, i7, bArr);
        z(4, i10, bArr);
        z(8, i11, bArr);
        z(12, i12, bArr);
        RandomAccessFile randomAccessFile = this.l;
        randomAccessFile.seek(0L);
        randomAccessFile.write(bArr);
    }
}

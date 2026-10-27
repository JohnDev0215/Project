package p;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final class nz1 implements Closeable {
    public static final Logger o = Logger.getLogger(nz1.class.getName());
    public final RandomAccessFile c;
    public int e;
    public int k;
    public kz1 l;
    public kz1 m;
    public final byte[] n;

    public nz1(File file) throws IOException {
        byte[] bArr = new byte[16];
        this.n = bArr;
        if (!file.exists()) {
            File file2 = new File(file.getPath() + ".tmp");
            RandomAccessFile randomAccessFile = new RandomAccessFile(file2, "rwd");
            try {
                randomAccessFile.setLength(4096L);
                randomAccessFile.seek(0L);
                byte[] bArr2 = new byte[16];
                int[] iArr = {4096, 0, 0, 0};
                int i = 0;
                for (int i2 = 0; i2 < 4; i2++) {
                    I0(bArr2, i, iArr[i2]);
                    i += 4;
                }
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
        this.c = randomAccessFile2;
        randomAccessFile2.seek(0L);
        randomAccessFile2.readFully(bArr);
        int iE = E(bArr, 0);
        this.e = iE;
        if (iE > randomAccessFile2.length()) {
            throw new IOException("File is truncated. Expected length: " + this.e + ", Actual length: " + randomAccessFile2.length());
        }
        this.k = E(bArr, 4);
        int iE2 = E(bArr, 8);
        int iE3 = E(bArr, 12);
        this.l = q(iE2);
        this.m = q(iE3);
    }

    public static int E(byte[] bArr, int i) {
        return ((bArr[i] & 255) << 24) + ((bArr[i + 1] & 255) << 16) + ((bArr[i + 2] & 255) << 8) + (bArr[i + 3] & 255);
    }

    public static void I0(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 >> 24);
        bArr[i + 1] = (byte) (i2 >> 16);
        bArr[i + 2] = (byte) (i2 >> 8);
        bArr[i + 3] = (byte) i2;
    }

    public final int E0() {
        if (this.k == 0) {
            return 16;
        }
        kz1 kz1Var = this.m;
        int i = kz1Var.a;
        int i2 = this.l.a;
        return i >= i2 ? (i - i2) + 4 + kz1Var.b + 16 : (((i + 4) + kz1Var.b) + this.e) - i2;
    }

    public final int G0(int i) {
        int i2 = this.e;
        return i < i2 ? i : (i + 16) - i2;
    }

    public final void H0(int i, int i2, int i3, int i4) throws IOException {
        int[] iArr = {i, i2, i3, i4};
        int i5 = 0;
        int i6 = 0;
        while (true) {
            byte[] bArr = this.n;
            if (i5 >= 4) {
                RandomAccessFile randomAccessFile = this.c;
                randomAccessFile.seek(0L);
                randomAccessFile.write(bArr);
                return;
            } else {
                I0(bArr, i6, iArr[i5]);
                i6 += 4;
                i5++;
            }
        }
    }

    public final synchronized void J() {
        int i;
        synchronized (this) {
            i = this.k;
        }
        if (i == 0) {
            throw new NoSuchElementException();
        }
        if (i == 1) {
            synchronized (this) {
                H0(4096, 0, 0, 0);
                this.k = 0;
                kz1 kz1Var = kz1.c;
                this.l = kz1Var;
                this.m = kz1Var;
                if (this.e > 4096) {
                    RandomAccessFile randomAccessFile = this.c;
                    randomAccessFile.setLength(4096);
                    randomAccessFile.getChannel().force(true);
                }
                this.e = 4096;
            }
        } else {
            kz1 kz1Var2 = this.l;
            int iG0 = G0(kz1Var2.a + 4 + kz1Var2.b);
            X(iG0, 0, 4, this.n);
            int iE = E(this.n, 0);
            H0(this.e, this.k - 1, iG0, this.m.a);
            this.k--;
            this.l = new kz1(iG0, iE);
        }
    }

    public final void X(int i, int i2, int i3, byte[] bArr) throws IOException {
        int iG0 = G0(i);
        int i4 = iG0 + i3;
        int i5 = this.e;
        RandomAccessFile randomAccessFile = this.c;
        if (i4 <= i5) {
            randomAccessFile.seek(iG0);
            randomAccessFile.readFully(bArr, i2, i3);
            return;
        }
        int i6 = i5 - iG0;
        randomAccessFile.seek(iG0);
        randomAccessFile.readFully(bArr, i2, i6);
        randomAccessFile.seek(16L);
        randomAccessFile.readFully(bArr, i2 + i6, i3 - i6);
    }

    public final void b(byte[] bArr) {
        boolean z;
        int iG0;
        int length = bArr.length;
        synchronized (this) {
            if (length >= 0) {
                if (length <= bArr.length) {
                    c(length);
                    synchronized (this) {
                        z = this.k == 0;
                    }
                }
            }
            throw new IndexOutOfBoundsException();
        }
        if (z) {
            iG0 = 16;
        } else {
            kz1 kz1Var = this.m;
            iG0 = G0(kz1Var.a + 4 + kz1Var.b);
        }
        kz1 kz1Var2 = new kz1(iG0, length);
        I0(this.n, 0, length);
        o0(iG0, 4, this.n);
        o0(iG0 + 4, length, bArr);
        H0(this.e, this.k + 1, z ? iG0 : this.l.a, iG0);
        this.m = kz1Var2;
        this.k++;
        if (z) {
            this.l = kz1Var2;
        }
    }

    public final void c(int i) throws IOException {
        int i2 = i + 4;
        int iE0 = this.e - E0();
        if (iE0 >= i2) {
            return;
        }
        int i3 = this.e;
        do {
            iE0 += i3;
            i3 <<= 1;
        } while (iE0 < i2);
        RandomAccessFile randomAccessFile = this.c;
        randomAccessFile.setLength(i3);
        randomAccessFile.getChannel().force(true);
        kz1 kz1Var = this.m;
        int iG0 = G0(kz1Var.a + 4 + kz1Var.b);
        if (iG0 < this.l.a) {
            FileChannel channel = randomAccessFile.getChannel();
            channel.position(this.e);
            long j = iG0 - 4;
            if (channel.transferTo(16L, j, channel) != j) {
                throw new AssertionError("Copied insufficient number of bytes!");
            }
        }
        int i4 = this.m.a;
        int i5 = this.l.a;
        if (i4 < i5) {
            int i6 = (this.e + i4) - 16;
            H0(i3, this.k, i5, i6);
            this.m = new kz1(i6, this.m.b);
        } else {
            H0(i3, this.k, i5, i4);
        }
        this.e = i3;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        this.c.close();
    }

    public final synchronized void i(mz1 mz1Var) {
        int iG0 = this.l.a;
        for (int i = 0; i < this.k; i++) {
            kz1 kz1VarQ = q(iG0);
            mz1Var.b(kz1VarQ.b, new lz1(this, kz1VarQ));
            iG0 = G0(kz1VarQ.a + 4 + kz1VarQ.b);
        }
    }

    public final void o0(int i, int i2, byte[] bArr) throws IOException {
        int iG0 = G0(i);
        int i3 = iG0 + i2;
        int i4 = this.e;
        RandomAccessFile randomAccessFile = this.c;
        if (i3 <= i4) {
            randomAccessFile.seek(iG0);
            randomAccessFile.write(bArr, 0, i2);
            return;
        }
        int i5 = i4 - iG0;
        randomAccessFile.seek(iG0);
        randomAccessFile.write(bArr, 0, i5);
        randomAccessFile.seek(16L);
        randomAccessFile.write(bArr, i5, i2 - i5);
    }

    public final kz1 q(int i) throws IOException {
        if (i == 0) {
            return kz1.c;
        }
        RandomAccessFile randomAccessFile = this.c;
        randomAccessFile.seek(i);
        return new kz1(i, randomAccessFile.readInt());
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nz1.class.getSimpleName());
        sb.append("[fileLength=");
        sb.append(this.e);
        sb.append(", size=");
        sb.append(this.k);
        sb.append(", first=");
        sb.append(this.l);
        sb.append(", last=");
        sb.append(this.m);
        sb.append(", element lengths=[");
        try {
            i(new p7(sb));
        } catch (IOException e) {
            o.log(Level.WARNING, "read error", (Throwable) e);
        }
        sb.append("]]");
        return sb.toString();
    }
}

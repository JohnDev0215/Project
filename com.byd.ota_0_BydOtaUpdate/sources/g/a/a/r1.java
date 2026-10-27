package g.a.a;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class r1 extends w1 {
    public static final byte[] s = new byte[0];
    public final int x;
    public int y;

    public r1(InputStream inputStream, int i2) {
        super(inputStream, i2);
        if (i2 < 0) {
            throw new IllegalArgumentException("negative lengths not allowed");
        }
        this.x = i2;
        this.y = i2;
        if (i2 == 0) {
            d(true);
        }
    }

    @Override // g.a.a.w1
    public int c() {
        return this.y;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.y == 0) {
            return -1;
        }
        int i2 = this.f4107c.read();
        if (i2 >= 0) {
            int i3 = this.y - 1;
            this.y = i3;
            if (i3 == 0) {
                d(true);
            }
            return i2;
        }
        throw new EOFException("DEF length " + this.x + " object truncated by " + this.y);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) throws IOException {
        int i4 = this.y;
        if (i4 == 0) {
            return -1;
        }
        int i5 = this.f4107c.read(bArr, i2, Math.min(i3, i4));
        if (i5 >= 0) {
            int i6 = this.y - i5;
            this.y = i6;
            if (i6 == 0) {
                d(true);
            }
            return i5;
        }
        throw new EOFException("DEF length " + this.x + " object truncated by " + this.y);
    }

    public byte[] s() throws IOException {
        int i2 = this.y;
        if (i2 == 0) {
            return s;
        }
        byte[] bArr = new byte[i2];
        int iC = i2 - g.a.f.k.a.c(this.f4107c, bArr);
        this.y = iC;
        if (iC == 0) {
            d(true);
            return bArr;
        }
        throw new EOFException("DEF length " + this.x + " object truncated by " + this.y);
    }
}

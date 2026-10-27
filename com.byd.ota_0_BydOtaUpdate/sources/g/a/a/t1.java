package g.a.a;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class t1 extends w1 {
    public boolean E3;
    public int s;
    public int x;
    public boolean y;

    public t1(InputStream inputStream, int i2) throws IOException {
        super(inputStream, i2);
        this.y = false;
        this.E3 = true;
        this.s = inputStream.read();
        int i3 = inputStream.read();
        this.x = i3;
        if (i3 < 0) {
            throw new EOFException();
        }
        s();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (s()) {
            return -1;
        }
        int i2 = this.f4107c.read();
        if (i2 < 0) {
            throw new EOFException();
        }
        int i3 = this.s;
        this.s = this.x;
        this.x = i2;
        return i3;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) throws IOException {
        if (this.E3 || i3 < 3) {
            return super.read(bArr, i2, i3);
        }
        if (this.y) {
            return -1;
        }
        int i4 = this.f4107c.read(bArr, i2 + 2, i3 - 2);
        if (i4 < 0) {
            throw new EOFException();
        }
        bArr[i2] = (byte) this.s;
        bArr[i2 + 1] = (byte) this.x;
        this.s = this.f4107c.read();
        int i5 = this.f4107c.read();
        this.x = i5;
        if (i5 >= 0) {
            return i4 + 2;
        }
        throw new EOFException();
    }

    public final boolean s() {
        if (!this.y && this.E3 && this.s == 0 && this.x == 0) {
            this.y = true;
            d(true);
        }
        return this.y;
    }

    public void v(boolean z) {
        this.E3 = z;
        s();
    }
}

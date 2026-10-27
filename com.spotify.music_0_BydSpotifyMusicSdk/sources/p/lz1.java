package p;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class lz1 extends InputStream {
    public int c;
    public int e;
    public final /* synthetic */ nz1 k;

    public lz1(nz1 nz1Var, kz1 kz1Var) {
        this.k = nz1Var;
        this.c = nz1Var.G0(kz1Var.a + 4);
        this.e = kz1Var.b;
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        if (this.e == 0) {
            return -1;
        }
        nz1 nz1Var = this.k;
        nz1Var.c.seek(this.c);
        int i = nz1Var.c.read();
        this.c = nz1Var.G0(this.c + 1);
        this.e--;
        return i;
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        if (bArr != null) {
            if ((i | i2) < 0 || i2 > bArr.length - i) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int i3 = this.e;
            if (i3 <= 0) {
                return -1;
            }
            if (i2 > i3) {
                i2 = i3;
            }
            int i4 = this.c;
            nz1 nz1Var = this.k;
            nz1Var.X(i4, i, i2, bArr);
            this.c = nz1Var.G0(this.c + i2);
            this.e -= i2;
            return i2;
        }
        throw new NullPointerException("buffer");
    }
}

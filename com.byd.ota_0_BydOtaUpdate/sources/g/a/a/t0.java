package g.a.a;

import java.io.IOException;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class t0 extends h {
    public t0(Date date) {
        super(date);
    }

    public t0(byte[] bArr) {
        super(bArr);
    }

    @Override // g.a.a.h, g.a.a.q
    public void h(p pVar) throws IOException {
        pVar.g(24, w());
    }

    @Override // g.a.a.h, g.a.a.q
    public int i() {
        int length = w().length;
        return y1.a(length) + 1 + length;
    }

    public final byte[] w() {
        byte[] bArr = this.f3855c;
        if (bArr[bArr.length - 1] != 90) {
            return bArr;
        }
        if (!t()) {
            byte[] bArr2 = this.f3855c;
            byte[] bArr3 = new byte[bArr2.length + 4];
            System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length - 1);
            System.arraycopy(g.a.f.i.e("0000Z"), 0, bArr3, this.f3855c.length - 1, 5);
            return bArr3;
        }
        if (!u()) {
            byte[] bArr4 = this.f3855c;
            byte[] bArr5 = new byte[bArr4.length + 2];
            System.arraycopy(bArr4, 0, bArr5, 0, bArr4.length - 1);
            System.arraycopy(g.a.f.i.e("00Z"), 0, bArr5, this.f3855c.length - 1, 3);
            return bArr5;
        }
        if (!s()) {
            return this.f3855c;
        }
        int length = this.f3855c.length - 2;
        while (length > 0 && this.f3855c[length] == 48) {
            length--;
        }
        byte[] bArr6 = this.f3855c;
        if (bArr6[length] == 46) {
            byte[] bArr7 = new byte[length + 1];
            System.arraycopy(bArr6, 0, bArr7, 0, length);
            bArr7[length] = 90;
            return bArr7;
        }
        byte[] bArr8 = new byte[length + 2];
        int i2 = length + 1;
        System.arraycopy(bArr6, 0, bArr8, 0, i2);
        bArr8[i2] = 90;
        return bArr8;
    }
}

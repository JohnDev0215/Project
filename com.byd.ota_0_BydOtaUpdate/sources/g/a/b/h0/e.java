package g.a.b.h0;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class e extends g.a.b.f {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4861g;

    public e(g.a.b.e eVar) {
        if (eVar instanceof g.a.b.v) {
            throw new IllegalArgumentException("CTSBlockCipher can only accept ECB, or CBC ciphers");
        }
        this.f4736d = eVar;
        int iB = eVar.b();
        this.f4861g = iB;
        this.f4733a = new byte[iB * 2];
        this.f4734b = 0;
    }

    @Override // g.a.b.f
    public int a(byte[] bArr, int i2) throws InvalidCipherTextException, IllegalStateException, DataLengthException {
        if (this.f4734b + i2 > bArr.length) {
            throw new OutputLengthException("output buffer to small in doFinal");
        }
        int iB = this.f4736d.b();
        int i3 = this.f4734b;
        int i4 = i3 - iB;
        byte[] bArr2 = new byte[iB];
        if (this.f4735c) {
            if (i3 < iB) {
                throw new DataLengthException("need at least one block of input for CTS");
            }
            this.f4736d.a(this.f4733a, 0, bArr2, 0);
            int i5 = this.f4734b;
            if (i5 > iB) {
                while (true) {
                    byte[] bArr3 = this.f4733a;
                    if (i5 == bArr3.length) {
                        break;
                    }
                    bArr3[i5] = bArr2[i5 - iB];
                    i5++;
                }
                for (int i6 = iB; i6 != this.f4734b; i6++) {
                    byte[] bArr4 = this.f4733a;
                    bArr4[i6] = (byte) (bArr4[i6] ^ bArr2[i6 - iB]);
                }
                g.a.b.e eVar = this.f4736d;
                if (eVar instanceof b) {
                    ((b) eVar).e().a(this.f4733a, iB, bArr, i2);
                } else {
                    eVar.a(this.f4733a, iB, bArr, i2);
                }
                System.arraycopy(bArr2, 0, bArr, i2 + iB, i4);
            } else {
                System.arraycopy(bArr2, 0, bArr, i2, iB);
            }
        } else {
            if (i3 < iB) {
                throw new DataLengthException("need at least one block of input for CTS");
            }
            byte[] bArr5 = new byte[iB];
            if (i3 > iB) {
                g.a.b.e eVar2 = this.f4736d;
                if (eVar2 instanceof b) {
                    ((b) eVar2).e().a(this.f4733a, 0, bArr2, 0);
                } else {
                    eVar2.a(this.f4733a, 0, bArr2, 0);
                }
                for (int i7 = iB; i7 != this.f4734b; i7++) {
                    int i8 = i7 - iB;
                    bArr5[i8] = (byte) (bArr2[i8] ^ this.f4733a[i7]);
                }
                System.arraycopy(this.f4733a, iB, bArr2, 0, i4);
                this.f4736d.a(bArr2, 0, bArr, i2);
                System.arraycopy(bArr5, 0, bArr, i2 + iB, i4);
            } else {
                this.f4736d.a(this.f4733a, 0, bArr2, 0);
                System.arraycopy(bArr2, 0, bArr, i2, iB);
            }
        }
        int i9 = this.f4734b;
        i();
        return i9;
    }

    @Override // g.a.b.f
    public int c(int i2) {
        return i2 + this.f4734b;
    }

    @Override // g.a.b.f
    public int e(int i2) {
        int i3 = i2 + this.f4734b;
        byte[] bArr = this.f4733a;
        int length = i3 % bArr.length;
        return length == 0 ? i3 - bArr.length : i3 - length;
    }

    @Override // g.a.b.f
    public int g(byte b2, byte[] bArr, int i2) throws IllegalStateException, DataLengthException {
        int i3 = this.f4734b;
        byte[] bArr2 = this.f4733a;
        int i4 = 0;
        if (i3 == bArr2.length) {
            int iA = this.f4736d.a(bArr2, 0, bArr, i2);
            byte[] bArr3 = this.f4733a;
            int i5 = this.f4861g;
            System.arraycopy(bArr3, i5, bArr3, 0, i5);
            this.f4734b = this.f4861g;
            i4 = iA;
        }
        byte[] bArr4 = this.f4733a;
        int i6 = this.f4734b;
        this.f4734b = i6 + 1;
        bArr4[i6] = b2;
        return i4;
    }

    @Override // g.a.b.f
    public int h(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) throws IllegalStateException, DataLengthException {
        if (i3 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        int iB = b();
        int iE = e(i3);
        if (iE > 0 && iE + i4 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        byte[] bArr3 = this.f4733a;
        int length = bArr3.length;
        int i5 = this.f4734b;
        int i6 = length - i5;
        int i7 = 0;
        if (i3 > i6) {
            System.arraycopy(bArr, i2, bArr3, i5, i6);
            int iA = this.f4736d.a(this.f4733a, 0, bArr2, i4) + 0;
            byte[] bArr4 = this.f4733a;
            System.arraycopy(bArr4, iB, bArr4, 0, iB);
            this.f4734b = iB;
            i3 -= i6;
            i2 += i6;
            while (i3 > iB) {
                System.arraycopy(bArr, i2, this.f4733a, this.f4734b, iB);
                iA += this.f4736d.a(this.f4733a, 0, bArr2, i4 + iA);
                byte[] bArr5 = this.f4733a;
                System.arraycopy(bArr5, iB, bArr5, 0, iB);
                i3 -= iB;
                i2 += iB;
            }
            i7 = iA;
        }
        System.arraycopy(bArr, i2, this.f4733a, this.f4734b, i3);
        this.f4734b += i3;
        return i7;
    }
}

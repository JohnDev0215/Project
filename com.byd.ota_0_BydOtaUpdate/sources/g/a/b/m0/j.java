package g.a.b.m0;

import g.a.b.j0.e1;
import org.bouncycastle.crypto.CryptoException;

/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g.a.b.n f5147a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g.a.b.a f5148b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5149c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5150d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f5151e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public byte[] f5152f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f5153g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f5154h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public byte[] f5155i;
    public byte[] j;
    public byte[] k;

    public j(g.a.b.a aVar, g.a.b.n nVar, boolean z) {
        int iIntValue;
        this.f5148b = aVar;
        this.f5147a = nVar;
        if (z) {
            iIntValue = 188;
        } else {
            Integer numA = k.a(nVar);
            if (numA == null) {
                throw new IllegalArgumentException("no valid trailer for digest: " + nVar.getAlgorithmName());
            }
            iIntValue = numA.intValue();
        }
        this.f5149c = iIntValue;
    }

    public final void a(byte[] bArr) {
        for (int i2 = 0; i2 != bArr.length; i2++) {
            bArr[i2] = 0;
        }
    }

    public byte[] b() throws CryptoException {
        int i2;
        int length;
        byte b2;
        int i3;
        int digestSize = this.f5147a.getDigestSize();
        if (this.f5149c == 188) {
            byte[] bArr = this.f5151e;
            length = (bArr.length - digestSize) - 1;
            this.f5147a.doFinal(bArr, length);
            byte[] bArr2 = this.f5151e;
            bArr2[bArr2.length - 1] = -68;
            i2 = 8;
        } else {
            i2 = 16;
            byte[] bArr3 = this.f5151e;
            int length2 = (bArr3.length - digestSize) - 2;
            this.f5147a.doFinal(bArr3, length2);
            byte[] bArr4 = this.f5151e;
            int length3 = bArr4.length - 2;
            int i4 = this.f5149c;
            bArr4[length3] = (byte) (i4 >>> 8);
            bArr4[bArr4.length - 1] = (byte) i4;
            length = length2;
        }
        int i5 = this.f5153g;
        int i6 = ((((digestSize + i5) * 8) + i2) + 4) - this.f5150d;
        if (i6 > 0) {
            int i7 = i5 - ((i6 + 7) / 8);
            b2 = 96;
            i3 = length - i7;
            System.arraycopy(this.f5152f, 0, this.f5151e, i3, i7);
            this.f5155i = new byte[i7];
        } else {
            b2 = 64;
            i3 = length - i5;
            System.arraycopy(this.f5152f, 0, this.f5151e, i3, i5);
            this.f5155i = new byte[this.f5153g];
        }
        int i8 = i3 - 1;
        if (i8 > 0) {
            for (int i9 = i8; i9 != 0; i9--) {
                this.f5151e[i9] = -69;
            }
            byte[] bArr5 = this.f5151e;
            bArr5[i8] = (byte) (bArr5[i8] ^ 1);
            bArr5[0] = 11;
            bArr5[0] = (byte) (bArr5[0] | b2);
        } else {
            byte[] bArr6 = this.f5151e;
            bArr6[0] = 10;
            bArr6[0] = (byte) (bArr6[0] | b2);
        }
        g.a.b.a aVar = this.f5148b;
        byte[] bArr7 = this.f5151e;
        byte[] bArrB = aVar.b(bArr7, 0, bArr7.length);
        this.f5154h = (b2 & 32) == 0;
        byte[] bArr8 = this.f5152f;
        byte[] bArr9 = this.f5155i;
        System.arraycopy(bArr8, 0, bArr9, 0, bArr9.length);
        this.f5153g = 0;
        a(this.f5152f);
        a(this.f5151e);
        return bArrB;
    }

    public void c(boolean z, g.a.b.i iVar) {
        e1 e1Var = (e1) iVar;
        this.f5148b.init(z, e1Var);
        int iBitLength = e1Var.c().bitLength();
        this.f5150d = iBitLength;
        byte[] bArr = new byte[(iBitLength + 7) / 8];
        this.f5151e = bArr;
        int i2 = this.f5149c;
        int length = bArr.length;
        if (i2 == 188) {
            this.f5152f = new byte[(length - this.f5147a.getDigestSize()) - 2];
        } else {
            this.f5152f = new byte[(length - this.f5147a.getDigestSize()) - 3];
        }
        e();
    }

    public final boolean d(byte[] bArr, byte[] bArr2) {
        boolean z;
        int i2 = this.f5153g;
        byte[] bArr3 = this.f5152f;
        if (i2 > bArr3.length) {
            z = bArr3.length <= bArr2.length;
            for (int i3 = 0; i3 != this.f5152f.length; i3++) {
                if (bArr[i3] != bArr2[i3]) {
                    z = false;
                }
            }
        } else {
            z = i2 == bArr2.length;
            for (int i4 = 0; i4 != bArr2.length; i4++) {
                if (bArr[i4] != bArr2[i4]) {
                    z = false;
                }
            }
        }
        return z;
    }

    public void e() {
        this.f5147a.reset();
        this.f5153g = 0;
        a(this.f5152f);
        byte[] bArr = this.f5155i;
        if (bArr != null) {
            a(bArr);
        }
        this.f5155i = null;
        this.f5154h = false;
        if (this.j != null) {
            this.j = null;
            a(this.k);
            this.k = null;
        }
    }

    public final boolean f(byte[] bArr) {
        this.f5153g = 0;
        a(this.f5152f);
        a(bArr);
        return false;
    }

    public void g(byte b2) {
        this.f5147a.update(b2);
        int i2 = this.f5153g;
        byte[] bArr = this.f5152f;
        if (i2 < bArr.length) {
            bArr[i2] = b2;
        }
        this.f5153g = i2 + 1;
    }

    public void h(byte[] bArr, int i2, int i3) {
        while (i3 > 0 && this.f5153g < this.f5152f.length) {
            g(bArr[i2]);
            i2++;
            i3--;
        }
        this.f5147a.update(bArr, i2, i3);
        this.f5153g += i3;
    }

    public boolean i(byte[] bArr) {
        byte[] bArrB;
        byte[] bArr2 = this.j;
        if (bArr2 == null) {
            try {
                bArrB = this.f5148b.b(bArr, 0, bArr.length);
            } catch (Exception unused) {
                return false;
            }
        } else {
            if (!g.a.f.a.a(bArr2, bArr)) {
                throw new IllegalStateException("updateWithRecoveredMessage called on different signature");
            }
            bArrB = this.k;
            this.j = null;
            this.k = null;
        }
        if (((bArrB[0] & 192) ^ 64) == 0 && ((bArrB[bArrB.length - 1] & 15) ^ 12) == 0) {
            int i2 = 2;
            if (((bArrB[bArrB.length - 1] & 255) ^ 188) == 0) {
                i2 = 1;
            } else {
                int i3 = ((bArrB[bArrB.length - 2] & 255) << 8) | (bArrB[bArrB.length - 1] & 255);
                Integer numA = k.a(this.f5147a);
                if (numA == null) {
                    throw new IllegalArgumentException("unrecognised hash in signature");
                }
                int iIntValue = numA.intValue();
                if (i3 != iIntValue && (iIntValue != 15052 || i3 != 16588)) {
                    throw new IllegalStateException("signer initialised with wrong digest for trailer " + i3);
                }
            }
            int i4 = 0;
            while (i4 != bArrB.length && ((bArrB[i4] & 15) ^ 10) != 0) {
                i4++;
            }
            int i5 = i4 + 1;
            int digestSize = this.f5147a.getDigestSize();
            byte[] bArr3 = new byte[digestSize];
            int length = (bArrB.length - i2) - digestSize;
            int i6 = length - i5;
            if (i6 <= 0) {
                return f(bArrB);
            }
            if ((bArrB[0] & 32) == 0) {
                this.f5154h = true;
                if (this.f5153g > i6) {
                    return f(bArrB);
                }
                this.f5147a.reset();
                this.f5147a.update(bArrB, i5, i6);
                this.f5147a.doFinal(bArr3, 0);
                boolean z = true;
                for (int i7 = 0; i7 != digestSize; i7++) {
                    int i8 = length + i7;
                    bArrB[i8] = (byte) (bArrB[i8] ^ bArr3[i7]);
                    if (bArrB[i8] != 0) {
                        z = false;
                    }
                }
                if (!z) {
                    return f(bArrB);
                }
                byte[] bArr4 = new byte[i6];
                this.f5155i = bArr4;
                System.arraycopy(bArrB, i5, bArr4, 0, bArr4.length);
            } else {
                this.f5154h = false;
                this.f5147a.doFinal(bArr3, 0);
                boolean z2 = true;
                for (int i9 = 0; i9 != digestSize; i9++) {
                    int i10 = length + i9;
                    bArrB[i10] = (byte) (bArrB[i10] ^ bArr3[i9]);
                    if (bArrB[i10] != 0) {
                        z2 = false;
                    }
                }
                if (!z2) {
                    return f(bArrB);
                }
                byte[] bArr5 = new byte[i6];
                this.f5155i = bArr5;
                System.arraycopy(bArrB, i5, bArr5, 0, bArr5.length);
            }
            if (this.f5153g != 0 && !d(this.f5152f, this.f5155i)) {
                return f(bArrB);
            }
            a(this.f5152f);
            a(bArrB);
            this.f5153g = 0;
            return true;
        }
        return f(bArrB);
    }
}

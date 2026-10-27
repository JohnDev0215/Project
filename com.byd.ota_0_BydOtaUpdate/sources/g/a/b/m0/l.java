package g.a.b.m0;

import g.a.b.j0.c1;
import g.a.b.j0.e1;
import g.a.b.j0.x0;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.DataLengthException;

/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g.a.b.n f5157a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g.a.b.n f5158b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.b.a f5159c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public SecureRandom f5160d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5161e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f5162f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f5163g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f5164h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f5165i;
    public byte[] j;
    public byte[] k;
    public byte[] l;
    public byte m;

    public l(g.a.b.a aVar, g.a.b.n nVar, g.a.b.n nVar2, int i2, byte b2) {
        this.f5159c = aVar;
        this.f5157a = nVar;
        this.f5158b = nVar2;
        this.f5161e = nVar.getDigestSize();
        this.f5162f = nVar2.getDigestSize();
        this.f5164h = i2;
        this.j = new byte[i2];
        this.k = new byte[i2 + 8 + this.f5161e];
        this.m = b2;
    }

    public final void a(int i2, byte[] bArr) {
        bArr[0] = (byte) (i2 >>> 24);
        bArr[1] = (byte) (i2 >>> 16);
        bArr[2] = (byte) (i2 >>> 8);
        bArr[3] = (byte) (i2 >>> 0);
    }

    public final void b(byte[] bArr) {
        for (int i2 = 0; i2 != bArr.length; i2++) {
            bArr[i2] = 0;
        }
    }

    public byte[] c() throws DataLengthException, CryptoException {
        g.a.b.n nVar = this.f5157a;
        byte[] bArr = this.k;
        nVar.doFinal(bArr, (bArr.length - this.f5161e) - this.f5164h);
        if (this.f5164h != 0) {
            if (!this.f5163g) {
                this.f5160d.nextBytes(this.j);
            }
            byte[] bArr2 = this.j;
            byte[] bArr3 = this.k;
            int length = bArr3.length;
            int i2 = this.f5164h;
            System.arraycopy(bArr2, 0, bArr3, length - i2, i2);
        }
        int i3 = this.f5161e;
        byte[] bArr4 = new byte[i3];
        g.a.b.n nVar2 = this.f5157a;
        byte[] bArr5 = this.k;
        nVar2.update(bArr5, 0, bArr5.length);
        this.f5157a.doFinal(bArr4, 0);
        byte[] bArr6 = this.l;
        int length2 = bArr6.length;
        int i4 = this.f5164h;
        int i5 = this.f5161e;
        bArr6[(((length2 - i4) - 1) - i5) - 1] = 1;
        System.arraycopy(this.j, 0, bArr6, ((bArr6.length - i4) - i5) - 1, i4);
        byte[] bArrE = e(bArr4, 0, i3, (this.l.length - this.f5161e) - 1);
        for (int i6 = 0; i6 != bArrE.length; i6++) {
            byte[] bArr7 = this.l;
            bArr7[i6] = (byte) (bArr7[i6] ^ bArrE[i6]);
        }
        byte[] bArr8 = this.l;
        bArr8[0] = (byte) (bArr8[0] & (255 >> ((bArr8.length * 8) - this.f5165i)));
        int length3 = bArr8.length;
        int i7 = this.f5161e;
        System.arraycopy(bArr4, 0, bArr8, (length3 - i7) - 1, i7);
        byte[] bArr9 = this.l;
        bArr9[bArr9.length - 1] = this.m;
        byte[] bArrB = this.f5159c.b(bArr9, 0, bArr9.length);
        b(this.l);
        return bArrB;
    }

    public void d(boolean z, g.a.b.i iVar) {
        g.a.b.i iVarA;
        e1 e1VarA;
        if (iVar instanceof x0) {
            x0 x0Var = (x0) iVar;
            iVarA = x0Var.a();
            this.f5160d = x0Var.b();
        } else {
            if (z) {
                this.f5160d = g.a.b.j.b();
            }
            iVarA = iVar;
        }
        if (iVarA instanceof c1) {
            e1VarA = ((c1) iVarA).a();
            this.f5159c.init(z, iVar);
        } else {
            e1VarA = (e1) iVarA;
            this.f5159c.init(z, iVarA);
        }
        int iBitLength = e1VarA.c().bitLength() - 1;
        this.f5165i = iBitLength;
        if (iBitLength < (this.f5161e * 8) + (this.f5164h * 8) + 9) {
            throw new IllegalArgumentException("key too small for specified hash and salt lengths");
        }
        this.l = new byte[(iBitLength + 7) / 8];
        f();
    }

    public final byte[] e(byte[] bArr, int i2, int i3, int i4) {
        int i5;
        byte[] bArr2 = new byte[i4];
        byte[] bArr3 = new byte[this.f5162f];
        byte[] bArr4 = new byte[4];
        this.f5158b.reset();
        int i6 = 0;
        while (true) {
            i5 = this.f5162f;
            if (i6 >= i4 / i5) {
                break;
            }
            a(i6, bArr4);
            this.f5158b.update(bArr, i2, i3);
            this.f5158b.update(bArr4, 0, 4);
            this.f5158b.doFinal(bArr3, 0);
            int i7 = this.f5162f;
            System.arraycopy(bArr3, 0, bArr2, i6 * i7, i7);
            i6++;
        }
        if (i5 * i6 < i4) {
            a(i6, bArr4);
            this.f5158b.update(bArr, i2, i3);
            this.f5158b.update(bArr4, 0, 4);
            this.f5158b.doFinal(bArr3, 0);
            int i8 = this.f5162f;
            System.arraycopy(bArr3, 0, bArr2, i6 * i8, i4 - (i6 * i8));
        }
        return bArr2;
    }

    public void f() {
        this.f5157a.reset();
    }

    public void g(byte b2) {
        this.f5157a.update(b2);
    }

    public void h(byte[] bArr, int i2, int i3) {
        this.f5157a.update(bArr, i2, i3);
    }

    public boolean i(byte[] bArr) {
        g.a.b.n nVar = this.f5157a;
        byte[] bArr2 = this.k;
        nVar.doFinal(bArr2, (bArr2.length - this.f5161e) - this.f5164h);
        try {
            byte[] bArrB = this.f5159c.b(bArr, 0, bArr.length);
            byte[] bArr3 = this.l;
            System.arraycopy(bArrB, 0, bArr3, bArr3.length - bArrB.length, bArrB.length);
            byte[] bArr4 = this.l;
            if (bArr4[bArr4.length - 1] != this.m) {
                b(bArr4);
                return false;
            }
            int length = bArr4.length;
            int i2 = this.f5161e;
            byte[] bArrE = e(bArr4, (length - i2) - 1, i2, (bArr4.length - i2) - 1);
            for (int i3 = 0; i3 != bArrE.length; i3++) {
                byte[] bArr5 = this.l;
                bArr5[i3] = (byte) (bArr5[i3] ^ bArrE[i3]);
            }
            byte[] bArr6 = this.l;
            bArr6[0] = (byte) (bArr6[0] & (255 >> ((bArr6.length * 8) - this.f5165i)));
            int i4 = 0;
            while (true) {
                byte[] bArr7 = this.l;
                int length2 = bArr7.length;
                int i5 = this.f5161e;
                int i6 = this.f5164h;
                if (i4 != ((length2 - i5) - i6) - 2) {
                    if (bArr7[i4] != 0) {
                        b(bArr7);
                        return false;
                    }
                    i4++;
                } else {
                    if (bArr7[((bArr7.length - i5) - i6) - 2] != 1) {
                        b(bArr7);
                        return false;
                    }
                    if (this.f5163g) {
                        byte[] bArr8 = this.j;
                        byte[] bArr9 = this.k;
                        System.arraycopy(bArr8, 0, bArr9, bArr9.length - i6, i6);
                    } else {
                        int length3 = ((bArr7.length - i6) - i5) - 1;
                        byte[] bArr10 = this.k;
                        System.arraycopy(bArr7, length3, bArr10, bArr10.length - i6, i6);
                    }
                    g.a.b.n nVar2 = this.f5157a;
                    byte[] bArr11 = this.k;
                    nVar2.update(bArr11, 0, bArr11.length);
                    g.a.b.n nVar3 = this.f5157a;
                    byte[] bArr12 = this.k;
                    nVar3.doFinal(bArr12, bArr12.length - this.f5161e);
                    int length4 = this.l.length;
                    int i7 = this.f5161e;
                    int i8 = (length4 - i7) - 1;
                    int length5 = this.k.length - i7;
                    while (true) {
                        byte[] bArr13 = this.k;
                        if (length5 == bArr13.length) {
                            b(bArr13);
                            b(this.l);
                            return true;
                        }
                        if ((this.l[i8] ^ bArr13[length5]) != 0) {
                            b(bArr13);
                            b(this.l);
                            return false;
                        }
                        i8++;
                        length5++;
                    }
                }
            }
        } catch (Exception unused) {
            return false;
        }
    }
}

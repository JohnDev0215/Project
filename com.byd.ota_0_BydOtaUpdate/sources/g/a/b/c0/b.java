package g.a.b.c0;

import g.a.b.i;
import g.a.b.j;
import g.a.b.j0.x0;
import g.a.b.n;
import java.security.SecureRandom;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;

/* JADX INFO: loaded from: classes.dex */
public class b implements g.a.b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f4392a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public n f4393b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.b.a f4394c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public SecureRandom f4395d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f4396e;

    public b(g.a.b.a aVar, n nVar, n nVar2, byte[] bArr) {
        this.f4394c = aVar;
        this.f4393b = nVar2;
        this.f4392a = new byte[nVar.getDigestSize()];
        nVar.reset();
        if (bArr != null) {
            nVar.update(bArr, 0, bArr.length);
        }
        nVar.doFinal(this.f4392a, 0);
    }

    public b(g.a.b.a aVar, n nVar, byte[] bArr) {
        this(aVar, nVar, nVar, bArr);
    }

    @Override // g.a.b.a
    public int a() {
        int iA = this.f4394c.a();
        return this.f4396e ? iA : (iA - 1) - (this.f4392a.length * 2);
    }

    @Override // g.a.b.a
    public byte[] b(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        return this.f4396e ? f(bArr, i2, i3) : e(bArr, i2, i3);
    }

    @Override // g.a.b.a
    public int c() {
        int iC = this.f4394c.c();
        return this.f4396e ? (iC - 1) - (this.f4392a.length * 2) : iC;
    }

    public final void d(int i2, byte[] bArr) {
        bArr[0] = (byte) (i2 >>> 24);
        bArr[1] = (byte) (i2 >>> 16);
        bArr[2] = (byte) (i2 >>> 8);
        bArr[3] = (byte) (i2 >>> 0);
    }

    public byte[] e(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        byte[] bArr2;
        byte[] bArr3;
        byte[] bArrB = this.f4394c.b(bArr, i2, i3);
        int iA = this.f4394c.a();
        byte[] bArr4 = new byte[iA];
        System.arraycopy(bArrB, 0, bArr4, iA - bArrB.length, bArrB.length);
        byte[] bArr5 = this.f4392a;
        boolean z = iA < (bArr5.length * 2) + 1;
        byte[] bArrG = g(bArr4, bArr5.length, iA - bArr5.length, bArr5.length);
        int i4 = 0;
        while (true) {
            bArr2 = this.f4392a;
            if (i4 == bArr2.length) {
                break;
            }
            bArr4[i4] = (byte) (bArr4[i4] ^ bArrG[i4]);
            i4++;
        }
        byte[] bArrG2 = g(bArr4, 0, bArr2.length, iA - bArr2.length);
        for (int length = this.f4392a.length; length != iA; length++) {
            bArr4[length] = (byte) (bArr4[length] ^ bArrG2[length - this.f4392a.length]);
        }
        int i5 = 0;
        boolean z2 = false;
        while (true) {
            bArr3 = this.f4392a;
            if (i5 == bArr3.length) {
                break;
            }
            if (bArr3[i5] != bArr4[bArr3.length + i5]) {
                z2 = true;
            }
            i5++;
        }
        int i6 = iA;
        for (int length2 = bArr3.length * 2; length2 != iA; length2++) {
            if ((bArr4[length2] != 0) & (i6 == iA)) {
                i6 = length2;
            }
        }
        boolean z3 = i6 > iA + (-1);
        boolean z4 = bArr4[i6] != 1;
        int i7 = i6 + 1;
        if ((z3 | z4) || (z | z2)) {
            g.a.f.a.u(bArr4, (byte) 0);
            throw new InvalidCipherTextException("data wrong");
        }
        int i8 = iA - i7;
        byte[] bArr6 = new byte[i8];
        System.arraycopy(bArr4, i7, bArr6, 0, i8);
        return bArr6;
    }

    public byte[] f(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        if (i3 > c()) {
            throw new DataLengthException("input data too long");
        }
        int iC = c() + 1 + (this.f4392a.length * 2);
        byte[] bArr2 = new byte[iC];
        int i4 = iC - i3;
        System.arraycopy(bArr, i2, bArr2, i4, i3);
        bArr2[i4 - 1] = 1;
        byte[] bArr3 = this.f4392a;
        System.arraycopy(bArr3, 0, bArr2, bArr3.length, bArr3.length);
        int length = this.f4392a.length;
        byte[] bArr4 = new byte[length];
        this.f4395d.nextBytes(bArr4);
        byte[] bArrG = g(bArr4, 0, length, iC - this.f4392a.length);
        for (int length2 = this.f4392a.length; length2 != iC; length2++) {
            bArr2[length2] = (byte) (bArr2[length2] ^ bArrG[length2 - this.f4392a.length]);
        }
        System.arraycopy(bArr4, 0, bArr2, 0, this.f4392a.length);
        byte[] bArr5 = this.f4392a;
        byte[] bArrG2 = g(bArr2, bArr5.length, iC - bArr5.length, bArr5.length);
        for (int i5 = 0; i5 != this.f4392a.length; i5++) {
            bArr2[i5] = (byte) (bArr2[i5] ^ bArrG2[i5]);
        }
        return this.f4394c.b(bArr2, 0, iC);
    }

    public final byte[] g(byte[] bArr, int i2, int i3, int i4) {
        byte[] bArr2 = new byte[i4];
        int digestSize = this.f4393b.getDigestSize();
        byte[] bArr3 = new byte[digestSize];
        byte[] bArr4 = new byte[4];
        this.f4393b.reset();
        int i5 = 0;
        while (i5 < i4 / digestSize) {
            d(i5, bArr4);
            this.f4393b.update(bArr, i2, i3);
            this.f4393b.update(bArr4, 0, 4);
            this.f4393b.doFinal(bArr3, 0);
            System.arraycopy(bArr3, 0, bArr2, i5 * digestSize, digestSize);
            i5++;
        }
        int i6 = digestSize * i5;
        if (i6 < i4) {
            d(i5, bArr4);
            this.f4393b.update(bArr, i2, i3);
            this.f4393b.update(bArr4, 0, 4);
            this.f4393b.doFinal(bArr3, 0);
            System.arraycopy(bArr3, 0, bArr2, i6, i4 - i6);
        }
        return bArr2;
    }

    @Override // g.a.b.a
    public void init(boolean z, i iVar) {
        this.f4395d = iVar instanceof x0 ? ((x0) iVar).b() : j.b();
        this.f4394c.init(z, iVar);
        this.f4396e = z;
    }
}

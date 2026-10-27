package g.a.b.d0;

import java.security.SecureRandom;
import org.bouncycastle.crypto.InvalidCipherTextException;

/* JADX INFO: loaded from: classes.dex */
public class q implements g.a.b.x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f4556a = {74, -35, -94, 44, 121, -24, 33, 5};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g.a.b.h0.b f4557b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.b.j0.s0 f4558c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.b.j0.w0 f4559d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f4560e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4561f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public g.a.b.n f4562g = g.a.b.n0.a.b();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public byte[] f4563h = new byte[20];

    public static byte[] e(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        int i2 = 0;
        while (i2 < bArr.length) {
            int i3 = i2 + 1;
            bArr2[i2] = bArr[bArr.length - i3];
            i2 = i3;
        }
        return bArr2;
    }

    @Override // g.a.b.x
    public byte[] a(byte[] bArr, int i2, int i3) {
        if (!this.f4561f) {
            throw new IllegalStateException("Not initialized for wrapping");
        }
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        byte[] bArrC = c(bArr2);
        int length = bArrC.length + i3;
        byte[] bArr3 = new byte[length];
        System.arraycopy(bArr2, 0, bArr3, 0, i3);
        System.arraycopy(bArrC, 0, bArr3, i3, bArrC.length);
        int iB = this.f4557b.b();
        if (length % iB != 0) {
            throw new IllegalStateException("Not multiple of block length");
        }
        this.f4557b.init(true, this.f4559d);
        byte[] bArr4 = new byte[length];
        for (int i4 = 0; i4 != length; i4 += iB) {
            this.f4557b.a(bArr3, i4, bArr4, i4);
        }
        byte[] bArr5 = this.f4560e;
        byte[] bArr6 = new byte[bArr5.length + length];
        System.arraycopy(bArr5, 0, bArr6, 0, bArr5.length);
        System.arraycopy(bArr4, 0, bArr6, this.f4560e.length, length);
        byte[] bArrE = e(bArr6);
        this.f4557b.init(true, new g.a.b.j0.w0(this.f4558c, f4556a));
        for (int i5 = 0; i5 != bArrE.length; i5 += iB) {
            this.f4557b.a(bArrE, i5, bArrE, i5);
        }
        return bArrE;
    }

    @Override // g.a.b.x
    public byte[] b(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        if (this.f4561f) {
            throw new IllegalStateException("Not set for unwrapping");
        }
        if (bArr == null) {
            throw new InvalidCipherTextException("Null pointer as ciphertext");
        }
        int iB = this.f4557b.b();
        if (i3 % iB != 0) {
            throw new InvalidCipherTextException("Ciphertext not multiple of " + iB);
        }
        this.f4557b.init(false, new g.a.b.j0.w0(this.f4558c, f4556a));
        byte[] bArr2 = new byte[i3];
        for (int i4 = 0; i4 != i3; i4 += iB) {
            this.f4557b.a(bArr, i2 + i4, bArr2, i4);
        }
        byte[] bArrE = e(bArr2);
        byte[] bArr3 = new byte[8];
        this.f4560e = bArr3;
        int length = bArrE.length - 8;
        byte[] bArr4 = new byte[length];
        System.arraycopy(bArrE, 0, bArr3, 0, 8);
        System.arraycopy(bArrE, 8, bArr4, 0, bArrE.length - 8);
        g.a.b.j0.w0 w0Var = new g.a.b.j0.w0(this.f4558c, this.f4560e);
        this.f4559d = w0Var;
        this.f4557b.init(false, w0Var);
        byte[] bArr5 = new byte[length];
        for (int i5 = 0; i5 != length; i5 += iB) {
            this.f4557b.a(bArr4, i5, bArr5, i5);
        }
        int i6 = length - 8;
        byte[] bArr6 = new byte[i6];
        byte[] bArr7 = new byte[8];
        System.arraycopy(bArr5, 0, bArr6, 0, i6);
        System.arraycopy(bArr5, i6, bArr7, 0, 8);
        if (d(bArr6, bArr7)) {
            return bArr6;
        }
        throw new InvalidCipherTextException("Checksum inside ciphertext is corrupted");
    }

    public final byte[] c(byte[] bArr) {
        byte[] bArr2 = new byte[8];
        this.f4562g.update(bArr, 0, bArr.length);
        this.f4562g.doFinal(this.f4563h, 0);
        System.arraycopy(this.f4563h, 0, bArr2, 0, 8);
        return bArr2;
    }

    public final boolean d(byte[] bArr, byte[] bArr2) {
        return g.a.f.a.q(c(bArr), bArr2);
    }

    @Override // g.a.b.x
    public String getAlgorithmName() {
        return "DESede";
    }

    @Override // g.a.b.x
    public void init(boolean z, g.a.b.i iVar) {
        SecureRandom secureRandomB;
        this.f4561f = z;
        this.f4557b = new g.a.b.h0.b(new p());
        if (iVar instanceof g.a.b.j0.x0) {
            g.a.b.j0.x0 x0Var = (g.a.b.j0.x0) iVar;
            g.a.b.i iVarA = x0Var.a();
            SecureRandom secureRandomB2 = x0Var.b();
            iVar = iVarA;
            secureRandomB = secureRandomB2;
        } else {
            secureRandomB = g.a.b.j.b();
        }
        if (iVar instanceof g.a.b.j0.s0) {
            this.f4558c = (g.a.b.j0.s0) iVar;
            if (this.f4561f) {
                byte[] bArr = new byte[8];
                this.f4560e = bArr;
                secureRandomB.nextBytes(bArr);
                this.f4559d = new g.a.b.j0.w0(this.f4558c, this.f4560e);
                return;
            }
            return;
        }
        if (iVar instanceof g.a.b.j0.w0) {
            g.a.b.j0.w0 w0Var = (g.a.b.j0.w0) iVar;
            this.f4559d = w0Var;
            this.f4560e = w0Var.a();
            this.f4558c = (g.a.b.j0.s0) this.f4559d.b();
            if (!this.f4561f) {
                throw new IllegalArgumentException("You should not supply an IV for unwrapping");
            }
            byte[] bArr2 = this.f4560e;
            if (bArr2 == null || bArr2.length != 8) {
                throw new IllegalArgumentException("IV is not 8 octets");
            }
        }
    }
}

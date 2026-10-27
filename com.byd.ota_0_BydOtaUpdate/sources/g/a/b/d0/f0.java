package g.a.b.d0;

import java.security.SecureRandom;
import org.bouncycastle.crypto.InvalidCipherTextException;

/* JADX INFO: loaded from: classes.dex */
public class f0 implements g.a.b.x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f4464a = {74, -35, -94, 44, 121, -24, 33, 5};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g.a.b.h0.b f4465b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.b.i f4466c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.b.j0.w0 f4467d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f4468e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4469f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public SecureRandom f4470g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public g.a.b.n f4471h = g.a.b.n0.a.b();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public byte[] f4472i = new byte[20];

    @Override // g.a.b.x
    public byte[] a(byte[] bArr, int i2, int i3) {
        if (!this.f4469f) {
            throw new IllegalStateException("Not initialized for wrapping");
        }
        int i4 = i3 + 1;
        int i5 = i4 % 8;
        int i6 = i5 != 0 ? (8 - i5) + i4 : i4;
        byte[] bArr2 = new byte[i6];
        bArr2[0] = (byte) i3;
        System.arraycopy(bArr, i2, bArr2, 1, i3);
        int i7 = (i6 - i3) - 1;
        byte[] bArr3 = new byte[i7];
        if (i7 > 0) {
            this.f4470g.nextBytes(bArr3);
            System.arraycopy(bArr3, 0, bArr2, i4, i7);
        }
        byte[] bArrC = c(bArr2);
        int length = bArrC.length + i6;
        byte[] bArr4 = new byte[length];
        System.arraycopy(bArr2, 0, bArr4, 0, i6);
        System.arraycopy(bArrC, 0, bArr4, i6, bArrC.length);
        byte[] bArr5 = new byte[length];
        System.arraycopy(bArr4, 0, bArr5, 0, length);
        int iB = length / this.f4465b.b();
        if (length % this.f4465b.b() != 0) {
            throw new IllegalStateException("Not multiple of block length");
        }
        this.f4465b.init(true, this.f4467d);
        for (int i8 = 0; i8 < iB; i8++) {
            int iB2 = this.f4465b.b() * i8;
            this.f4465b.a(bArr5, iB2, bArr5, iB2);
        }
        byte[] bArr6 = this.f4468e;
        int length2 = bArr6.length + length;
        byte[] bArr7 = new byte[length2];
        System.arraycopy(bArr6, 0, bArr7, 0, bArr6.length);
        System.arraycopy(bArr5, 0, bArr7, this.f4468e.length, length);
        byte[] bArr8 = new byte[length2];
        int i9 = 0;
        while (i9 < length2) {
            int i10 = i9 + 1;
            bArr8[i9] = bArr7[length2 - i10];
            i9 = i10;
        }
        this.f4465b.init(true, new g.a.b.j0.w0(this.f4466c, f4464a));
        for (int i11 = 0; i11 < iB + 1; i11++) {
            int iB3 = this.f4465b.b() * i11;
            this.f4465b.a(bArr8, iB3, bArr8, iB3);
        }
        return bArr8;
    }

    @Override // g.a.b.x
    public byte[] b(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        if (this.f4469f) {
            throw new IllegalStateException("Not set for unwrapping");
        }
        if (bArr == null) {
            throw new InvalidCipherTextException("Null pointer as ciphertext");
        }
        if (i3 % this.f4465b.b() != 0) {
            throw new InvalidCipherTextException("Ciphertext not multiple of " + this.f4465b.b());
        }
        this.f4465b.init(false, new g.a.b.j0.w0(this.f4466c, f4464a));
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        for (int i4 = 0; i4 < i3 / this.f4465b.b(); i4++) {
            int iB = this.f4465b.b() * i4;
            this.f4465b.a(bArr2, iB, bArr2, iB);
        }
        byte[] bArr3 = new byte[i3];
        int i5 = 0;
        while (i5 < i3) {
            int i6 = i5 + 1;
            bArr3[i5] = bArr2[i3 - i6];
            i5 = i6;
        }
        byte[] bArr4 = new byte[8];
        this.f4468e = bArr4;
        int i7 = i3 - 8;
        byte[] bArr5 = new byte[i7];
        System.arraycopy(bArr3, 0, bArr4, 0, 8);
        System.arraycopy(bArr3, 8, bArr5, 0, i7);
        g.a.b.j0.w0 w0Var = new g.a.b.j0.w0(this.f4466c, this.f4468e);
        this.f4467d = w0Var;
        this.f4465b.init(false, w0Var);
        byte[] bArr6 = new byte[i7];
        System.arraycopy(bArr5, 0, bArr6, 0, i7);
        for (int i8 = 0; i8 < i7 / this.f4465b.b(); i8++) {
            int iB2 = this.f4465b.b() * i8;
            this.f4465b.a(bArr6, iB2, bArr6, iB2);
        }
        int i9 = i7 - 8;
        byte[] bArr7 = new byte[i9];
        byte[] bArr8 = new byte[8];
        System.arraycopy(bArr6, 0, bArr7, 0, i9);
        System.arraycopy(bArr6, i9, bArr8, 0, 8);
        if (!d(bArr7, bArr8)) {
            throw new InvalidCipherTextException("Checksum inside ciphertext is corrupted");
        }
        if (i9 - ((bArr7[0] & 255) + 1) <= 7) {
            int i10 = bArr7[0];
            byte[] bArr9 = new byte[i10];
            System.arraycopy(bArr7, 1, bArr9, 0, i10);
            return bArr9;
        }
        throw new InvalidCipherTextException("too many pad bytes (" + (i9 - ((bArr7[0] & 255) + 1)) + ")");
    }

    public final byte[] c(byte[] bArr) {
        byte[] bArr2 = new byte[8];
        this.f4471h.update(bArr, 0, bArr.length);
        this.f4471h.doFinal(this.f4472i, 0);
        System.arraycopy(this.f4472i, 0, bArr2, 0, 8);
        return bArr2;
    }

    public final boolean d(byte[] bArr, byte[] bArr2) {
        return g.a.f.a.q(c(bArr), bArr2);
    }

    @Override // g.a.b.x
    public String getAlgorithmName() {
        return "RC2";
    }

    @Override // g.a.b.x
    public void init(boolean z, g.a.b.i iVar) {
        this.f4469f = z;
        this.f4465b = new g.a.b.h0.b(new e0());
        if (iVar instanceof g.a.b.j0.x0) {
            g.a.b.j0.x0 x0Var = (g.a.b.j0.x0) iVar;
            this.f4470g = x0Var.b();
            iVar = x0Var.a();
        } else {
            this.f4470g = g.a.b.j.b();
        }
        if (!(iVar instanceof g.a.b.j0.w0)) {
            this.f4466c = iVar;
            if (this.f4469f) {
                byte[] bArr = new byte[8];
                this.f4468e = bArr;
                this.f4470g.nextBytes(bArr);
                this.f4467d = new g.a.b.j0.w0(this.f4466c, this.f4468e);
                return;
            }
            return;
        }
        g.a.b.j0.w0 w0Var = (g.a.b.j0.w0) iVar;
        this.f4467d = w0Var;
        this.f4468e = w0Var.a();
        this.f4466c = this.f4467d.b();
        if (!this.f4469f) {
            throw new IllegalArgumentException("You should not supply an IV for unwrapping");
        }
        byte[] bArr2 = this.f4468e;
        if (bArr2 == null || bArr2.length != 8) {
            throw new IllegalArgumentException("IV is not 8 octets");
        }
    }
}

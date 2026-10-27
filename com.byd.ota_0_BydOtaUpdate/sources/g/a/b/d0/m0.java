package g.a.b.d0;

import org.bouncycastle.crypto.InvalidCipherTextException;

/* JADX INFO: loaded from: classes.dex */
public class m0 implements g.a.b.x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g.a.b.e f4526a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g.a.b.j0.s0 f4527b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f4528c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f4529d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f4530e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public byte[] f4531f = null;

    public m0(g.a.b.e eVar) {
        byte[] bArr = {-90, 89, 89, -90};
        this.f4529d = bArr;
        this.f4530e = bArr;
        this.f4526a = eVar;
    }

    @Override // g.a.b.x
    public byte[] a(byte[] bArr, int i2, int i3) {
        if (!this.f4528c) {
            throw new IllegalStateException("not set for wrapping");
        }
        byte[] bArr2 = new byte[8];
        byte[] bArrF = g.a.f.f.f(i3);
        byte[] bArr3 = this.f4530e;
        int iB = 0;
        System.arraycopy(bArr3, 0, bArr2, 0, bArr3.length);
        System.arraycopy(bArrF, 0, bArr2, this.f4530e.length, bArrF.length);
        byte[] bArr4 = new byte[i3];
        System.arraycopy(bArr, i2, bArr4, 0, i3);
        byte[] bArrC = c(bArr4);
        if (bArrC.length != 8) {
            l0 l0Var = new l0(this.f4526a);
            l0Var.init(true, new g.a.b.j0.w0(this.f4527b, bArr2));
            return l0Var.a(bArrC, 0, bArrC.length);
        }
        int length = bArrC.length + 8;
        byte[] bArr5 = new byte[length];
        System.arraycopy(bArr2, 0, bArr5, 0, 8);
        System.arraycopy(bArrC, 0, bArr5, 8, bArrC.length);
        this.f4526a.init(true, this.f4527b);
        while (iB < length) {
            this.f4526a.a(bArr5, iB, bArr5, iB);
            iB += this.f4526a.b();
        }
        return bArr5;
    }

    @Override // g.a.b.x
    public byte[] b(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        byte[] bArrD;
        if (this.f4528c) {
            throw new IllegalStateException("not set for unwrapping");
        }
        int i4 = i3 / 8;
        if (i4 * 8 != i3) {
            throw new InvalidCipherTextException("unwrap data must be a multiple of 8 bytes");
        }
        if (i4 == 1) {
            throw new InvalidCipherTextException("unwrap data must be at least 16 bytes");
        }
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        byte[] bArr3 = new byte[i3];
        if (i4 == 2) {
            this.f4526a.init(false, this.f4527b);
            int iB = 0;
            while (iB < i3) {
                this.f4526a.a(bArr2, iB, bArr3, iB);
                iB += this.f4526a.b();
            }
            byte[] bArr4 = new byte[8];
            this.f4531f = bArr4;
            System.arraycopy(bArr3, 0, bArr4, 0, bArr4.length);
            byte[] bArr5 = this.f4531f;
            int length = i3 - bArr5.length;
            bArrD = new byte[length];
            System.arraycopy(bArr3, bArr5.length, bArrD, 0, length);
        } else {
            bArrD = d(bArr, i2, i3);
        }
        byte[] bArr6 = new byte[4];
        byte[] bArr7 = new byte[4];
        System.arraycopy(this.f4531f, 0, bArr6, 0, 4);
        System.arraycopy(this.f4531f, 4, bArr7, 0, 4);
        int iA = g.a.f.f.a(bArr7, 0);
        boolean zQ = g.a.f.a.q(bArr6, this.f4530e);
        int length2 = bArrD.length;
        if (iA <= length2 - 8) {
            zQ = false;
        }
        if (iA > length2) {
            zQ = false;
        }
        int length3 = length2 - iA;
        if (length3 >= bArrD.length) {
            length3 = bArrD.length;
            zQ = false;
        }
        byte[] bArr8 = new byte[length3];
        System.arraycopy(bArrD, bArrD.length - length3, bArr8, 0, length3);
        if (!g.a.f.a.q(bArr8, new byte[length3])) {
            zQ = false;
        }
        if (!zQ) {
            throw new InvalidCipherTextException("checksum failed");
        }
        byte[] bArr9 = new byte[iA];
        System.arraycopy(bArrD, 0, bArr9, 0, iA);
        return bArr9;
    }

    public final byte[] c(byte[] bArr) {
        int length = bArr.length;
        int i2 = (8 - (length % 8)) % 8;
        byte[] bArr2 = new byte[length + i2];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        if (i2 != 0) {
            System.arraycopy(new byte[i2], 0, bArr2, length, i2);
        }
        return bArr2;
    }

    public final byte[] d(byte[] bArr, int i2, int i3) {
        int i4 = i3 - 8;
        byte[] bArr2 = new byte[i4];
        byte[] bArr3 = new byte[8];
        byte[] bArr4 = new byte[16];
        System.arraycopy(bArr, i2, bArr3, 0, 8);
        System.arraycopy(bArr, i2 + 8, bArr2, 0, i4);
        this.f4526a.init(false, this.f4527b);
        int i5 = (i3 / 8) - 1;
        for (int i6 = 5; i6 >= 0; i6--) {
            for (int i7 = i5; i7 >= 1; i7--) {
                System.arraycopy(bArr3, 0, bArr4, 0, 8);
                int i8 = (i7 - 1) * 8;
                System.arraycopy(bArr2, i8, bArr4, 8, 8);
                int i9 = (i5 * i6) + i7;
                int i10 = 1;
                while (i9 != 0) {
                    int i11 = 8 - i10;
                    bArr4[i11] = (byte) (((byte) i9) ^ bArr4[i11]);
                    i9 >>>= 8;
                    i10++;
                }
                this.f4526a.a(bArr4, 0, bArr4, 0);
                System.arraycopy(bArr4, 0, bArr3, 0, 8);
                System.arraycopy(bArr4, 8, bArr2, i8, 8);
            }
        }
        this.f4531f = bArr3;
        return bArr2;
    }

    @Override // g.a.b.x
    public String getAlgorithmName() {
        return this.f4526a.getAlgorithmName();
    }

    @Override // g.a.b.x
    public void init(boolean z, g.a.b.i iVar) {
        this.f4528c = z;
        if (iVar instanceof g.a.b.j0.x0) {
            iVar = ((g.a.b.j0.x0) iVar).a();
        }
        if (iVar instanceof g.a.b.j0.s0) {
            this.f4527b = (g.a.b.j0.s0) iVar;
            this.f4530e = this.f4529d;
        } else if (iVar instanceof g.a.b.j0.w0) {
            g.a.b.j0.w0 w0Var = (g.a.b.j0.w0) iVar;
            this.f4530e = w0Var.a();
            this.f4527b = (g.a.b.j0.s0) w0Var.b();
            if (this.f4530e.length != 4) {
                throw new IllegalArgumentException("IV length not equal to 4");
            }
        }
    }
}

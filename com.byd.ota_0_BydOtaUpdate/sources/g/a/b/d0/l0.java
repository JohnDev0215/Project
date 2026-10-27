package g.a.b.d0;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;

/* JADX INFO: loaded from: classes.dex */
public class l0 implements g.a.b.x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g.a.b.e f4521a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f4522b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.b.j0.s0 f4523c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f4524d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f4525e;

    public l0(g.a.b.e eVar) {
        this(eVar, false);
    }

    public l0(g.a.b.e eVar, boolean z) {
        this.f4525e = new byte[]{-90, -90, -90, -90, -90, -90, -90, -90};
        this.f4521a = eVar;
        this.f4522b = !z;
    }

    @Override // g.a.b.x
    public byte[] a(byte[] bArr, int i2, int i3) {
        if (!this.f4524d) {
            throw new IllegalStateException("not set for wrapping");
        }
        int i4 = i3 / 8;
        if (i4 * 8 != i3) {
            throw new DataLengthException("wrap data must be a multiple of 8 bytes");
        }
        byte[] bArr2 = this.f4525e;
        byte[] bArr3 = new byte[bArr2.length + i3];
        byte[] bArr4 = new byte[bArr2.length + 8];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        System.arraycopy(bArr, i2, bArr3, this.f4525e.length, i3);
        this.f4521a.init(this.f4522b, this.f4523c);
        for (int i5 = 0; i5 != 6; i5++) {
            for (int i6 = 1; i6 <= i4; i6++) {
                System.arraycopy(bArr3, 0, bArr4, 0, this.f4525e.length);
                int i7 = i6 * 8;
                System.arraycopy(bArr3, i7, bArr4, this.f4525e.length, 8);
                this.f4521a.a(bArr4, 0, bArr4, 0);
                int i8 = (i4 * i5) + i6;
                int i9 = 1;
                while (i8 != 0) {
                    int length = this.f4525e.length - i9;
                    bArr4[length] = (byte) (((byte) i8) ^ bArr4[length]);
                    i8 >>>= 8;
                    i9++;
                }
                System.arraycopy(bArr4, 0, bArr3, 0, 8);
                System.arraycopy(bArr4, 8, bArr3, i7, 8);
            }
        }
        return bArr3;
    }

    @Override // g.a.b.x
    public byte[] b(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        if (this.f4524d) {
            throw new IllegalStateException("not set for unwrapping");
        }
        int i4 = i3 / 8;
        if (i4 * 8 != i3) {
            throw new InvalidCipherTextException("unwrap data must be a multiple of 8 bytes");
        }
        byte[] bArr2 = this.f4525e;
        byte[] bArr3 = new byte[i3 - bArr2.length];
        byte[] bArr4 = new byte[bArr2.length];
        byte[] bArr5 = new byte[bArr2.length + 8];
        System.arraycopy(bArr, i2, bArr4, 0, bArr2.length);
        byte[] bArr6 = this.f4525e;
        System.arraycopy(bArr, i2 + bArr6.length, bArr3, 0, i3 - bArr6.length);
        this.f4521a.init(!this.f4522b, this.f4523c);
        int i5 = i4 - 1;
        for (int i6 = 5; i6 >= 0; i6--) {
            for (int i7 = i5; i7 >= 1; i7--) {
                System.arraycopy(bArr4, 0, bArr5, 0, this.f4525e.length);
                int i8 = (i7 - 1) * 8;
                System.arraycopy(bArr3, i8, bArr5, this.f4525e.length, 8);
                int i9 = (i5 * i6) + i7;
                int i10 = 1;
                while (i9 != 0) {
                    int length = this.f4525e.length - i10;
                    bArr5[length] = (byte) (((byte) i9) ^ bArr5[length]);
                    i9 >>>= 8;
                    i10++;
                }
                this.f4521a.a(bArr5, 0, bArr5, 0);
                System.arraycopy(bArr5, 0, bArr4, 0, 8);
                System.arraycopy(bArr5, 8, bArr3, i8, 8);
            }
        }
        if (g.a.f.a.q(bArr4, this.f4525e)) {
            return bArr3;
        }
        throw new InvalidCipherTextException("checksum failed");
    }

    @Override // g.a.b.x
    public String getAlgorithmName() {
        return this.f4521a.getAlgorithmName();
    }

    @Override // g.a.b.x
    public void init(boolean z, g.a.b.i iVar) {
        this.f4524d = z;
        if (iVar instanceof g.a.b.j0.x0) {
            iVar = ((g.a.b.j0.x0) iVar).a();
        }
        if (iVar instanceof g.a.b.j0.s0) {
            this.f4523c = (g.a.b.j0.s0) iVar;
            return;
        }
        if (iVar instanceof g.a.b.j0.w0) {
            g.a.b.j0.w0 w0Var = (g.a.b.j0.w0) iVar;
            this.f4525e = w0Var.a();
            this.f4523c = (g.a.b.j0.s0) w0Var.b();
            if (this.f4525e.length != 8) {
                throw new IllegalArgumentException("IV not equal to 8");
            }
        }
    }
}

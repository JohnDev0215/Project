package g.a.b.d0;

import java.util.ArrayList;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;

/* JADX INFO: loaded from: classes.dex */
public class s implements g.a.b.x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f4581a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public r f4582b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f4583c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f4584d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f4585e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public byte[] f4586f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ArrayList<byte[]> f4587g;

    public s(int i2) {
        r rVar = new r(i2);
        this.f4582b = rVar;
        this.f4583c = new byte[rVar.b() / 2];
        this.f4585e = new byte[this.f4582b.b()];
        this.f4586f = new byte[this.f4582b.b()];
        this.f4587g = new ArrayList<>();
        this.f4584d = new byte[4];
    }

    @Override // g.a.b.x
    public byte[] a(byte[] bArr, int i2, int i3) {
        if (!this.f4581a) {
            throw new IllegalStateException("not set for wrapping");
        }
        if (i3 % this.f4582b.b() != 0) {
            throw new DataLengthException("wrap data must be a multiple of " + this.f4582b.b() + " bytes");
        }
        if (i2 + i3 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        int iB = ((i3 / this.f4582b.b()) + 1) * 2;
        int i4 = iB - 1;
        int i5 = i4 * 6;
        int iB2 = this.f4582b.b() + i3;
        byte[] bArr2 = new byte[iB2];
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        System.arraycopy(bArr2, 0, this.f4583c, 0, this.f4582b.b() / 2);
        this.f4587g.clear();
        int iB3 = iB2 - (this.f4582b.b() / 2);
        int iB4 = this.f4582b.b() / 2;
        while (iB3 != 0) {
            byte[] bArr3 = new byte[this.f4582b.b() / 2];
            System.arraycopy(bArr2, iB4, bArr3, 0, this.f4582b.b() / 2);
            this.f4587g.add(bArr3);
            iB3 -= this.f4582b.b() / 2;
            iB4 += this.f4582b.b() / 2;
        }
        int i6 = 0;
        while (i6 < i5) {
            System.arraycopy(this.f4583c, 0, bArr2, 0, this.f4582b.b() / 2);
            System.arraycopy(this.f4587g.get(0), 0, bArr2, this.f4582b.b() / 2, this.f4582b.b() / 2);
            this.f4582b.a(bArr2, 0, bArr2, 0);
            i6++;
            c(i6, this.f4584d, 0);
            for (int i7 = 0; i7 < 4; i7++) {
                int iB5 = (this.f4582b.b() / 2) + i7;
                bArr2[iB5] = (byte) (bArr2[iB5] ^ this.f4584d[i7]);
            }
            System.arraycopy(bArr2, this.f4582b.b() / 2, this.f4583c, 0, this.f4582b.b() / 2);
            for (int i8 = 2; i8 < iB; i8++) {
                System.arraycopy(this.f4587g.get(i8 - 1), 0, this.f4587g.get(i8 - 2), 0, this.f4582b.b() / 2);
            }
            System.arraycopy(bArr2, 0, this.f4587g.get(iB - 2), 0, this.f4582b.b() / 2);
        }
        System.arraycopy(this.f4583c, 0, bArr2, 0, this.f4582b.b() / 2);
        int iB6 = this.f4582b.b() / 2;
        for (int i9 = 0; i9 < i4; i9++) {
            System.arraycopy(this.f4587g.get(i9), 0, bArr2, iB6, this.f4582b.b() / 2);
            iB6 += this.f4582b.b() / 2;
        }
        return bArr2;
    }

    @Override // g.a.b.x
    public byte[] b(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        if (this.f4581a) {
            throw new IllegalStateException("not set for unwrapping");
        }
        if (i3 % this.f4582b.b() != 0) {
            throw new DataLengthException("unwrap data must be a multiple of " + this.f4582b.b() + " bytes");
        }
        int iB = (i3 * 2) / this.f4582b.b();
        int i4 = iB - 1;
        int i5 = i4 * 6;
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        byte[] bArr3 = new byte[this.f4582b.b() / 2];
        System.arraycopy(bArr2, 0, bArr3, 0, this.f4582b.b() / 2);
        this.f4587g.clear();
        int iB2 = i3 - (this.f4582b.b() / 2);
        int iB3 = this.f4582b.b() / 2;
        while (iB2 != 0) {
            byte[] bArr4 = new byte[this.f4582b.b() / 2];
            System.arraycopy(bArr2, iB3, bArr4, 0, this.f4582b.b() / 2);
            this.f4587g.add(bArr4);
            iB2 -= this.f4582b.b() / 2;
            iB3 += this.f4582b.b() / 2;
        }
        for (int i6 = 0; i6 < i5; i6++) {
            System.arraycopy(this.f4587g.get(iB - 2), 0, bArr2, 0, this.f4582b.b() / 2);
            System.arraycopy(bArr3, 0, bArr2, this.f4582b.b() / 2, this.f4582b.b() / 2);
            c(i5 - i6, this.f4584d, 0);
            for (int i7 = 0; i7 < 4; i7++) {
                int iB4 = (this.f4582b.b() / 2) + i7;
                bArr2[iB4] = (byte) (bArr2[iB4] ^ this.f4584d[i7]);
            }
            this.f4582b.a(bArr2, 0, bArr2, 0);
            System.arraycopy(bArr2, 0, bArr3, 0, this.f4582b.b() / 2);
            for (int i8 = 2; i8 < iB; i8++) {
                int i9 = iB - i8;
                System.arraycopy(this.f4587g.get(i9 - 1), 0, this.f4587g.get(i9), 0, this.f4582b.b() / 2);
            }
            System.arraycopy(bArr2, this.f4582b.b() / 2, this.f4587g.get(0), 0, this.f4582b.b() / 2);
        }
        System.arraycopy(bArr3, 0, bArr2, 0, this.f4582b.b() / 2);
        int iB5 = this.f4582b.b() / 2;
        for (int i10 = 0; i10 < i4; i10++) {
            System.arraycopy(this.f4587g.get(i10), 0, bArr2, iB5, this.f4582b.b() / 2);
            iB5 += this.f4582b.b() / 2;
        }
        System.arraycopy(bArr2, i3 - this.f4582b.b(), this.f4585e, 0, this.f4582b.b());
        byte[] bArr5 = new byte[i3 - this.f4582b.b()];
        if (!g.a.f.a.a(this.f4585e, this.f4586f)) {
            throw new InvalidCipherTextException("checksum failed");
        }
        System.arraycopy(bArr2, 0, bArr5, 0, i3 - this.f4582b.b());
        return bArr5;
    }

    public final void c(int i2, byte[] bArr, int i3) {
        bArr[i3 + 3] = (byte) (i2 >> 24);
        bArr[i3 + 2] = (byte) (i2 >> 16);
        bArr[i3 + 1] = (byte) (i2 >> 8);
        bArr[i3] = (byte) i2;
    }

    @Override // g.a.b.x
    public String getAlgorithmName() {
        return "DSTU7624WrapEngine";
    }

    @Override // g.a.b.x
    public void init(boolean z, g.a.b.i iVar) {
        if (iVar instanceof g.a.b.j0.x0) {
            iVar = ((g.a.b.j0.x0) iVar).a();
        }
        this.f4581a = z;
        if (!(iVar instanceof g.a.b.j0.s0)) {
            throw new IllegalArgumentException("invalid parameters passed to DSTU7624WrapEngine");
        }
        this.f4582b.init(z, iVar);
    }
}

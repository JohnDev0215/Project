package g.a.b.d0;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class d0 implements g.a.b.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f4454a = {0, 0, 0, 0};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f4455b = {128, 27, 54, 108, 216, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, 212};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int[] f4456c = new int[4];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int[] f4457d = new int[4];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f4458e = new int[4];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4459f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f4460g;

    @Override // g.a.b.e
    public int a(byte[] bArr, int i2, byte[] bArr2, int i3) {
        if (!this.f4459f) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
        if (i2 + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + 16 <= bArr2.length) {
            return this.f4460g ? e(bArr, i2, bArr2, i3) : d(bArr, i2, bArr2, i3);
        }
        throw new OutputLengthException("output buffer too short");
    }

    @Override // g.a.b.e
    public int b() {
        return 16;
    }

    public final int c(byte[] bArr, int i2) {
        int i3 = i2 + 1;
        int i4 = i3 + 1;
        int i5 = (bArr[i2] << 24) | ((bArr[i3] & 255) << 16);
        int i6 = i4 + 1;
        return (bArr[i6] & 255) | i5 | ((bArr[i4] & 255) << 8);
    }

    public final int d(byte[] bArr, int i2, byte[] bArr2, int i3) {
        this.f4456c[0] = c(bArr, i2);
        this.f4456c[1] = c(bArr, i2 + 4);
        this.f4456c[2] = c(bArr, i2 + 8);
        this.f4456c[3] = c(bArr, i2 + 12);
        int[] iArr = this.f4457d;
        System.arraycopy(iArr, 0, this.f4458e, 0, iArr.length);
        l(this.f4458e, f4454a);
        int i4 = 16;
        while (true) {
            int[] iArr2 = this.f4456c;
            int[] iArr3 = this.f4458e;
            if (i4 <= 0) {
                l(iArr2, iArr3);
                int[] iArr4 = this.f4456c;
                iArr4[0] = f4455b[i4] ^ iArr4[0];
                g(iArr4[0], bArr2, i3);
                g(this.f4456c[1], bArr2, i3 + 4);
                g(this.f4456c[2], bArr2, i3 + 8);
                g(this.f4456c[3], bArr2, i3 + 12);
                return 16;
            }
            l(iArr2, iArr3);
            int[] iArr5 = this.f4456c;
            iArr5[0] = iArr5[0] ^ f4455b[i4];
            h(iArr5);
            f(this.f4456c);
            i(this.f4456c);
            i4--;
        }
    }

    public final int e(byte[] bArr, int i2, byte[] bArr2, int i3) {
        this.f4456c[0] = c(bArr, i2);
        this.f4456c[1] = c(bArr, i2 + 4);
        this.f4456c[2] = c(bArr, i2 + 8);
        this.f4456c[3] = c(bArr, i2 + 12);
        int i4 = 0;
        while (i4 < 16) {
            int[] iArr = this.f4456c;
            iArr[0] = iArr[0] ^ f4455b[i4];
            l(iArr, this.f4457d);
            h(this.f4456c);
            f(this.f4456c);
            i(this.f4456c);
            i4++;
        }
        int[] iArr2 = this.f4456c;
        iArr2[0] = f4455b[i4] ^ iArr2[0];
        l(iArr2, this.f4457d);
        g(this.f4456c[0], bArr2, i3);
        g(this.f4456c[1], bArr2, i3 + 4);
        g(this.f4456c[2], bArr2, i3 + 8);
        g(this.f4456c[3], bArr2, i3 + 12);
        return 16;
    }

    public final void f(int[] iArr) {
        iArr[1] = iArr[1] ^ ((~iArr[3]) & (~iArr[2]));
        iArr[0] = iArr[0] ^ (iArr[2] & iArr[1]);
        int i2 = iArr[3];
        iArr[3] = iArr[0];
        iArr[0] = i2;
        iArr[2] = iArr[2] ^ ((iArr[0] ^ iArr[1]) ^ iArr[3]);
        iArr[1] = ((~iArr[3]) & (~iArr[2])) ^ iArr[1];
        iArr[0] = (iArr[1] & iArr[2]) ^ iArr[0];
    }

    public final void g(int i2, byte[] bArr, int i3) {
        int i4 = i3 + 1;
        bArr[i3] = (byte) (i2 >>> 24);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i2 >>> 16);
        bArr[i5] = (byte) (i2 >>> 8);
        bArr[i5 + 1] = (byte) i2;
    }

    @Override // g.a.b.e
    public String getAlgorithmName() {
        return "Noekeon";
    }

    public final void h(int[] iArr) {
        iArr[1] = j(iArr[1], 1);
        iArr[2] = j(iArr[2], 5);
        iArr[3] = j(iArr[3], 2);
    }

    public final void i(int[] iArr) {
        iArr[1] = j(iArr[1], 31);
        iArr[2] = j(iArr[2], 27);
        iArr[3] = j(iArr[3], 30);
    }

    @Override // g.a.b.e
    public void init(boolean z, g.a.b.i iVar) {
        if (iVar instanceof g.a.b.j0.s0) {
            this.f4460g = z;
            this.f4459f = true;
            k(((g.a.b.j0.s0) iVar).a());
        } else {
            throw new IllegalArgumentException("invalid parameter passed to Noekeon init - " + iVar.getClass().getName());
        }
    }

    public final int j(int i2, int i3) {
        return (i2 >>> (32 - i3)) | (i2 << i3);
    }

    public final void k(byte[] bArr) {
        this.f4457d[0] = c(bArr, 0);
        this.f4457d[1] = c(bArr, 4);
        this.f4457d[2] = c(bArr, 8);
        this.f4457d[3] = c(bArr, 12);
    }

    public final void l(int[] iArr, int[] iArr2) {
        int i2 = iArr[0] ^ iArr[2];
        int iJ = i2 ^ (j(i2, 8) ^ j(i2, 24));
        iArr[1] = iArr[1] ^ iJ;
        iArr[3] = iJ ^ iArr[3];
        for (int i3 = 0; i3 < 4; i3++) {
            iArr[i3] = iArr[i3] ^ iArr2[i3];
        }
        int i4 = iArr[1] ^ iArr[3];
        int iJ2 = i4 ^ (j(i4, 8) ^ j(i4, 24));
        iArr[0] = iArr[0] ^ iJ2;
        iArr[2] = iJ2 ^ iArr[2];
    }

    @Override // g.a.b.e
    public void reset() {
    }
}
